package com.mask.singleshopcar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mask.singleshopcar.Model.GoodsModel;
import com.mask.singleshopcar.Model.ShopCarModel;
import com.mask.singleshopcar.Utils.CarUtrils;
import com.mask.singleshopcar.Utils.NumberUtils;
import com.mask.singleshopcar.Utils.TypeUtils;
import com.mask.singleshopcar.listener.CheckGoodsListener;
import com.mask.singleshopcar.listener.ModifyCountListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车数据适配器
 *
 * @author zhuangAH
 */
public class GoodsCarAdapter extends BaseExpandableListAdapter {

    private List<ShopCarModel> goodShop = new ArrayList<>();
    private Context context;
    private CheckGoodsListener checkInterface;
    private ModifyCountListener modifyCountInterface;


    /**
     * 构造函数
     *
     * @param context
     */
    public GoodsCarAdapter(Context context, List<ShopCarModel> goodShopList) {
        this.context = context;
        this.goodShop = goodShopList;

    }

    public void setCheckInterface(CheckGoodsListener checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountListener modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        int size = 0;
        if (goodShop != null && goodShop.size() > 0) {
            size = goodShop.size();
        }
        return size;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (goodShop != null) {
            if (groupPosition >= goodShop.size()) {
                return 0;
            }
            List<GoodsModel> goodsList = goodShop.get(groupPosition).getGoodsModel();
            return goodsList == null ? 0 : goodsList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return goodShop.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        List<GoodsModel> goodsModel = goodShop.get(groupPosition).getGoodsModel();
        if (!goodsModel.isEmpty()) {
            return goodsModel.get(childPosition);
        } else return 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupViewHolder gholder = null;
        if (convertView != null && !(convertView.getTag() instanceof GroupViewHolder)) {
            convertView = null;
        }
        if (convertView == null) {
            gholder = new GroupViewHolder();
            convertView = View.inflate(context, R.layout.item_shopcart_group, null);
            gholder.determineChekbox = (CheckBox) convertView.findViewById(R.id.determine_chekbox);
            gholder.tvSourceName = (TextView) convertView.findViewById(R.id.tv_source_name);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }

        final ShopCarModel group = (ShopCarModel) getGroup(groupPosition);
        gholder.tvSourceName.setText(group.getFactoryName());
        gholder.determineChekbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                group.setChoosed(((CheckBox) v).isChecked());
                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());
            }
        });
        gholder.determineChekbox.setChecked(group.isChoosed());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
        GoodsViewHolder goodsViewHolder = null;
        int totalCount = 0;
        double totalPrice = 0.00;

        if (convertView != null && !(convertView.getTag() instanceof GoodsViewHolder)) {
            convertView = null;
        }
        if (convertView == null) {
            goodsViewHolder = new GoodsViewHolder();
            convertView = View.inflate(context, R.layout.item_shopcart_product, null);
            goodsViewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.check_box);
            goodsViewHolder.ivAdapterListPic = (ImageView) convertView.findViewById(R.id.iv_adapter_list_pic);
            goodsViewHolder.tvIntro = (TextView) convertView.findViewById(R.id.tv_intro);
            goodsViewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            goodsViewHolder.tvBuyNum = (TextView) convertView.findViewById(R.id.tv_buy_num);
            goodsViewHolder.rlNoEdtor = (RelativeLayout) convertView.findViewById(R.id.rl_no_edtor);
            goodsViewHolder.tvReduce = (TextView) convertView.findViewById(R.id.tv_reduce);
            goodsViewHolder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            goodsViewHolder.tvAdd = (TextView) convertView.findViewById(R.id.tv_add);
            goodsViewHolder.llChangeNum = (LinearLayout) convertView.findViewById(R.id.ll_change_num);
            goodsViewHolder.layout_item_foot = (LinearLayout) convertView.findViewById(R.id.layout_item_foot);
            goodsViewHolder.tv_goods_number = (TextView) convertView.findViewById(R.id.tv_goods_number);
            goodsViewHolder.tv_goods_menoy = (TextView) convertView.findViewById(R.id.tv_goods_menoy);
            goodsViewHolder.tv_buys = (TextView) convertView.findViewById(R.id.tv_buys);
            goodsViewHolder.laytou_car = (LinearLayout) convertView.findViewById(R.id.laytou_car);
            convertView.setTag(goodsViewHolder);
        } else {
            goodsViewHolder = (GoodsViewHolder) convertView.getTag();
        }
        //进行数据操作
        final GoodsModel goodsInfo = (GoodsModel) getChild(groupPosition, childPosition);
        if (goodsInfo != null) {
            //数量初始化为0,金额初始化为0
            goodsViewHolder.tv_goods_number.setText(TypeUtils.toString(0));
            goodsViewHolder.tv_goods_menoy.setText("￥ " + NumberUtils.formatMoneyScale(0.00));

            List<GoodsModel> goodsModel = CarUtrils.getGoodsList(goodShop, groupPosition);
            //判断是否最后一个
            if ((goodsModel.size() - 1) == childPosition) {
                goodsViewHolder.layout_item_foot.setVisibility(View.VISIBLE);
                /**
                 * 统计操作<br>
                 * 1.先清空全局计数器<br>
                 * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
                 * 3.给底部的textView进行数据填充
                 */
                //1判断商品是否选中,再进行计算
                for (int j = 0; j < goodsModel.size(); j++) {
                    GoodsModel model = goodsModel.get(j);
                    if (model.isChoosed()) {
                        totalCount += model.getSelectQty();
                        totalPrice += TypeUtils.toDouble(NumberUtils.multiply(TypeUtils.toBigDecimal(NumberUtils.formatRounded(model.getPrice())), NumberUtils.toBigDecimal(model.getSelectQty())));
                    }
                }

                if (totalPrice != 0.00) {
                    goodsViewHolder.tv_goods_number.setText(TypeUtils.toString(totalCount));
                    goodsViewHolder.tv_goods_menoy.setText(NumberUtils.formatRounded(TypeUtils.toBigDecimal(totalPrice)));
                    goodsViewHolder.tv_buys.setBackgroundColor(context.getResources().getColor(R.color.main_color));
                    goodsViewHolder.tv_buys.setEnabled(true);
                } else {
                    goodsViewHolder.tv_goods_number.setText(TypeUtils.toString(0));
                    goodsViewHolder.tv_goods_menoy.setText("￥ " + NumberUtils.formatMoneyScale(0.00));
                    goodsViewHolder.tv_buys.setBackgroundColor(context.getResources().getColor(R.color.resport_line));
                    goodsViewHolder.tv_buys.setEnabled(false);
                }

            } else {
                goodsViewHolder.layout_item_foot.setVisibility(View.GONE);
            }
            //设置基础数据
            if (goodsInfo.getImageSrc() != null) {
                Glide.with(context)
                        .load(goodsInfo.getImageSrc())
                        .centerCrop()
                        .placeholder(R.mipmap.test2)
                        .crossFade()
                        .into(goodsViewHolder.ivAdapterListPic);
            }

            goodsViewHolder.tvIntro.setText(goodsInfo.getName());
            goodsViewHolder.tvPrice.setText("￥ " + NumberUtils.formatRounded(goodsInfo.getPrice()));
            goodsViewHolder.tvBuyNum.setText("X " + NumberUtils.formatQty(goodsInfo.getQty()));
            //set Goods Check
            goodsViewHolder.checkBox.setChecked(goodsInfo.isChoosed());
            goodsViewHolder.tvNum.setText(TypeUtils.toString(goodsInfo.getSelectQty()));

            //选中的状态下才能触发点击事件
            goodsViewHolder.tvAdd.setEnabled(true);
            goodsViewHolder.tvReduce.setEnabled(true);
            //加减
            final GoodsViewHolder finalGoodsViewHolder = goodsViewHolder;
            goodsViewHolder.tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, finalGoodsViewHolder.tvNum, finalGoodsViewHolder.checkBox.isChecked());// 暴露增加接口

                }
            });
            final GoodsViewHolder finalGoodsViewHolder1 = goodsViewHolder;
            goodsViewHolder.tvReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, finalGoodsViewHolder1.tvNum, finalGoodsViewHolder1.checkBox.isChecked());// 暴露删减接口
                }
            });

            //goods check state OnClick
            goodsViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goodsInfo.setChoosed(((CheckBox) v).isChecked());
                    ((CheckBox) v).setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());
                }
            });
            //onClick to OrderDetailActivity
            final double finalTotalPrice = totalPrice;
            final int finalTotalCount = totalCount;
            goodsViewHolder.tv_buys.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //check goods is not null
                    //if not null can go other activity
                    ShopCarModel shopCar = CarUtrils.getCheckShopCar(goodShop, groupPosition);
                    if (shopCar != null && shopCar.getGoodsModel().size() > 0) {
                        checkInterface.checkGoodsBuy(shopCar, finalTotalPrice, finalTotalCount);
                    }
                }
            });
        }

        goodsViewHolder.laytou_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UIHelper.toShopCarGoodsDetailActivity(context, goodsInfo);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;

    }


    /**
     * 组元素绑定器
     */
    static class GroupViewHolder {
        CheckBox determineChekbox;
        TextView tvSourceName;
    }

    /**
     * 子元素绑定器
     */
    static class GoodsViewHolder {
        CheckBox checkBox;
        ImageView ivAdapterListPic;
        TextView tvIntro;
        TextView tvPrice;
        TextView tvBuyNum;
        RelativeLayout rlNoEdtor;
        TextView tvReduce;
        TextView tvNum;
        TextView tvAdd;
        LinearLayout llChangeNum;
        LinearLayout layout_item_foot;
        public TextView tv_goods_number;
        public TextView tv_goods_menoy;
        public TextView tv_buys;
        LinearLayout laytou_car;


    }

    public void setGoodShop(List<ShopCarModel> goodShopList) {
        this.goodShop.clear();
        if (goodShopList != null) {
            goodShop.addAll(goodShopList);
        }
        notifyDataSetChanged();
    }

}
