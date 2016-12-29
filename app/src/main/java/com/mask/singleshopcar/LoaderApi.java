package com.mask.singleshopcar;


import com.mask.singleshopcar.Model.GoodsModel;
import com.mask.singleshopcar.Model.ShopCarModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaugnAh on 2016-11-02.
 */

public class LoaderApi {


    /**
     * 加载购物车数据
     *
     * @return GoodsModel
     */
    public static List<ShopCarModel> loaderShopCar() {
        List<ShopCarModel> shopCarModelList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ShopCarModel shopCarModel = new ShopCarModel();
            shopCarModel.setId(i + "");
            shopCarModel.setFactoryName("春之花零食" + i);
            List<GoodsModel> goodsModelList = new ArrayList<>();


            //1
            GoodsModel goods = new GoodsModel();
            goods.setId(1+"");
            goods.setName("好吃的豆腐");
            goods.setCountry("中国");
            goods.setQty(21);
            goods.setCity("广州");
            goods.setPrice(new BigDecimal(2.23));
            goods.setSelectQty(15);
            goods.setSelect(true);
            goods.setImageSrc("http://d6.yihaodianimg.com/N07/M04/7B/1C/ChEbvFVKKlCASVATAAW664fSV9Q78900.jpg");
            goods.setShopId(i + "");
            goodsModelList.add(goods);
            //2
            GoodsModel goods1 = new GoodsModel();
            goods1.setId(2+"");
            goods1.setName("旺财小馒头");
            goods1.setCountry("中国");
            goods1.setQty(21);
            goods1.setCity("广州");
            goods1.setPrice(new BigDecimal(2.23));
            goods1.setSelectQty(15);
            goods1.setSelect(true);
            goods1.setImageSrc("http://img007.hc360.cn/y4/M00/24/97/wKhQiFU15o2EJvpQAAAAAD52B8s406.jpg");
            goods1.setShopId(i + "");
            goodsModelList.add(goods1);

            for (int j = 3; j < 6; j++) {
                GoodsModel goods2 = new GoodsModel();
                goods2.setId(i + "");
                goods2.setName("曲曲饼干");
                goods2.setCountry("中国");
                goods2.setQty(21);
                goods2.setCity("广州");
                goods2.setPrice(new BigDecimal(2.23));
                goods2.setSelectQty(15);
                goods2.setSelect(true);
                goods2.setImageSrc("http://d6.yihaodianimg.com/N07/M09/EC/90/CgQI0FTv_YqAPkfLAAO-1FIRBVc53800.jpg");
                goods2.setShopId(i + "");
                goodsModelList.add(goods2);
            }

            shopCarModel.setGoodsModel(goodsModelList);
            shopCarModelList.add(shopCarModel);
        }
        return shopCarModelList;
    }
}
