package com.mask.singleshopcar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mask.singleshopcar.Model.GoodsModel;
import com.mask.singleshopcar.Model.ShopCarModel;
import com.mask.singleshopcar.Utils.LoadingDialog;
import com.mask.singleshopcar.Utils.TypeUtils;
import com.mask.singleshopcar.listener.CheckGoodsListener;
import com.mask.singleshopcar.listener.ModifyCountListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author  zhuangAh
 */
public class ShopCarAvtivity extends BaseActivity implements CheckGoodsListener, ModifyCountListener {

    @Bind(R.id.img_delete)
    public ImageView img_delete = null;
    @Bind(R.id.lv_refresh)
    ExpandableListView lv_refresh;
    @Bind(R.id.tv_total_price)
    TextView tvTotalPrice;
    @Bind(R.id.all_chekbox)
    CheckBox allChekbox;
    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.tv_go_to_pay)
    TextView tvGoToPay;
    @Bind(R.id.ll_shar)
    LinearLayout llShar;
    @Bind(R.id.view_show)
    public View view_show = null;
    @Bind(R.id.ll_info)
    LinearLayout llInfo;
    @Bind(R.id.ll_cart)
    LinearLayout llCart;
    @Bind(R.id.layout_cart_empty)
    LinearLayout cart_empty;
    private GoodsCarAdapter goodsAdapter;
    //购物车数据
    private List<ShopCarModel> shopCarModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        setTitle("购物车");
        initDatas();

    }


    /**
     * 模拟数据<br>
     * 遵循适配器的数据列表填充原则，组元素被放在一个List中，对应的组元素下辖的子元素被放在Map中，<br>
     * 其键是组元素的Id(通常是一个唯一指定组元素身份的值)
     */
    private void initDatas() {
        final LoadingDialog dialog = LoadingDialog.show(this, "正在加载数据.....");
        new AsyncTask<Void, Void, List<ShopCarModel>>() {

            @Override
            protected List<ShopCarModel> doInBackground(Void... params) {
                List<ShopCarModel> goodsModels = LoaderApi.loaderShopCar();
                return goodsModels;
            }

            @Override
            protected void onPostExecute(List<ShopCarModel> result) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                if (result != null && result.size() > 0) {
                    shopCarModelList = result;
                    goodsAdapter = new GoodsCarAdapter(ShopCarAvtivity.this, result);
                    lv_refresh.setAdapter(goodsAdapter);
                    goodsAdapter.setCheckInterface(ShopCarAvtivity.this);// 关键步骤1,设置复选框接口
                    goodsAdapter.setModifyCountInterface(ShopCarAvtivity.this);// 关键步骤2,设置数量增减接口
                    for (int i = 0; i < goodsAdapter.getGroupCount(); i++) {
                        lv_refresh.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
                    }
                }
            }
        }.execute();


    }

    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete() {
        // 待删除的组元素列表
        List<ShopCarModel> shopList = new ArrayList<>();
        for (int i = 0; i < shopCarModelList.size(); i++) {

            ShopCarModel goodsModel = shopCarModelList.get(i);
            if (goodsModel.isChoosed()) {
                shopList.add(goodsModel);
            }
            // 待删除的子元素列表
            List<GoodsModel> newGoodsList = new ArrayList<>();
            List<GoodsModel> goodList = goodsModel.getGoodsModel();
            for (int j = 0; j < goodList.size(); j++) {
                if (goodList.get(j).isChoosed()) {
                    newGoodsList.add(goodList.get(j));
                }
            }
            goodList.removeAll(newGoodsList);
        }
        shopCarModelList.removeAll(shopList);
        //记得重新设置购物车
        goodsAdapter.notifyDataSetChanged();
    }

    /**
     * 是否可以点击删除的按钮
     *
     * @return boolean
     */
    private boolean isdoDelete() {
        boolean isDelete = false;

        // 待删除的组元素列表,组有选择上，那就return true
        for (int i = 0; i < shopCarModelList.size(); i++) {
            ShopCarModel goodsModel = shopCarModelList.get(i);
            if (goodsModel.isChoosed()) {
                isDelete = true;
            }
            // 待删除的子元素列表
            List<GoodsModel> goodList = goodsModel.getGoodsModel();
            for (int j = 0; j < goodList.size(); j++) {
                if (goodList.get(j).isChoosed()) {
                    isDelete = true;
                }
            }
        }
        return isDelete;
    }

    /**
     * 添加
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        GoodsModel goods = (GoodsModel) goodsAdapter.getChild(groupPosition, childPosition);
        int currentCount = goods.getSelectQty();
        if (currentCount >= goods.getQty()) {
            return;
        }
        currentCount++;
        goods.setSelectQty(currentCount);
        ((TextView) showCountView).setText(TypeUtils.toString(currentCount));
        goodsAdapter.notifyDataSetChanged();
    }

    /**
     * 减
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        GoodsModel goods = (GoodsModel) goodsAdapter.getChild(groupPosition, childPosition);
        int currentCount = goods.getSelectQty();
        if (currentCount == 1)
            return;
        currentCount--;
        goods.setSelectQty(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        goodsAdapter.notifyDataSetChanged();
    }


    @Override
    public void childDelete(int groupPosition, int childPosition) {
        ShopCarModel shopList = shopCarModelList.get(groupPosition);
        List<GoodsModel> goodsList = shopList.getGoodsModel();
        goodsList.remove(goodsList.get(childPosition));

        if (goodsList.size() == 0) {
            shopCarModelList.remove(groupPosition);
        }
        goodsAdapter.notifyDataSetChanged();
    }

    /***
     * 校对组元素
     *
     * @param groupPosition 组元素位置
     * @param isChecked     组元素选中与否
     */
    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {

        List<GoodsModel> goodsModelList = shopCarModelList.get(groupPosition).getGoodsModel();
        for (int i = 0; i < goodsModelList.size(); i++) {
            goodsModelList.get(i).setChoosed(isChecked);
        }
        goodsAdapter.notifyDataSetChanged();
    }

    /**
     * 校对子商品原素
     *
     * @param groupPosition 组元素位置
     * @param childPosiTion
     * @param isChecked     子元素选中与否
     */
    @Override
    public void checkChild(int groupPosition, int childPosiTion, boolean isChecked) {
        // 判断改组下面的所有子元素是否是同一种状态
        boolean allChildSameState = true;
        ShopCarModel shopCarModel = shopCarModelList.get(groupPosition);
        List<GoodsModel> goodsList = shopCarModelList.get(groupPosition).getGoodsModel();
        for (int i = 0; i < goodsList.size(); i++) {
            // 不全选中
            if (goodsList.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState) {
            // 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
            shopCarModel.setChoosed(isChecked);
        } else {
            // 否则，组元素一律设置为未选中状态
            shopCarModel.setChoosed(false);
        }
        goodsAdapter.notifyDataSetChanged();
    }

    /**
     * 点击之后去订单详情界面
     *
     * @param shopCarModel
     * @param totalMonery
     */
    @Override
    public void checkGoodsBuy(ShopCarModel shopCarModel, double totalMonery, int totalCount) {
        if (shopCarModel.getGoodsModel() != null && shopCarModel.getGoodsModel().size() > 0) {
            //前往订单详情
            startActivity(new Intent(ShopCarAvtivity.this,GoodsDetailActivity.class));
        }

    }


    @OnClick(R.id.img_delete)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_delete:
                if (isdoDelete()) {
                    AlertDialog alert = new AlertDialog.Builder(ShopCarAvtivity.this).create();
                    alert.setTitle("操作提示");
                    alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;
                                }
                            });
                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    doDelete();
                                }
                            });
                    alert.show();
                }
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //界面销毁的时候要清除数据
        goodsAdapter = null;
        shopCarModelList.clear();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1002 && resultCode == 20001) {
            shopCarModelList.clear();
            initDatas();
        } else if (requestCode == 10003 && resultCode == 20001) {
            shopCarModelList.clear();
            initDatas();
        }
    }
}
