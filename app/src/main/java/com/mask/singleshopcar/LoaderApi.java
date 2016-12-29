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
            shopCarModel.setFactoryName("广州金属有限公司" + i);
            List<GoodsModel> goodsModelList = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                GoodsModel goods = new GoodsModel();
                goods.setId(j + "");
                goods.setName("奔驰豪华轮胎" + i);
                goods.setCountry("中国");
                goods.setQty(21);
                goods.setCity("广州");
                goods.setPrice(new BigDecimal(1.5598));
                goods.setSelectQty(1);
                goods.setSelect(true);
                goods.setImageSrc("http://pic93.nipic.com/file/20160318/20584984_105122996275_2.jpg");
                goods.setShopId(i + "");
                goodsModelList.add(goods);

            }
            shopCarModel.setGoodsModel(goodsModelList);
            shopCarModelList.add(shopCarModel);
        }
        return shopCarModelList;
    }
}
