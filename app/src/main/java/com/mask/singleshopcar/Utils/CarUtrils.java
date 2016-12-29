package com.mask.singleshopcar.Utils;


import com.mask.singleshopcar.Model.GoodsModel;
import com.mask.singleshopcar.Model.ShopCarModel;

import java.util.List;

/**
 * Created by zhuangAH on 2016-11-25.
 */

public class CarUtrils {


    /**
     * 返回商品数据集合
     *
     * @param shopCarModelList
     * @param groupPosition
     * @return
     */
    public static List<GoodsModel> getGoodsList(List<ShopCarModel> shopCarModelList, int groupPosition) {
        if (shopCarModelList != null && shopCarModelList.size() > 0) {
            ShopCarModel shopCarModel = shopCarModelList.get(groupPosition);
            return shopCarModel.getGoodsModel();
        } else {
            return null;
        }
    }

    /**
     * 返回选择过的商品数据
     *
     * @param shopList
     * @param groupPosition
     * @return goodsList
     */
    public static ShopCarModel getCheckShopCar(List<ShopCarModel> shopList, int groupPosition) {
        ShopCarModel shopCarModel = null;
        if (shopList != null && shopList.size() > 0) {
            shopCarModel = shopList.get(groupPosition);
            List<GoodsModel> goodsList = shopCarModel.getGoodsModel();
            for (int i = 0; i < goodsList.size(); i++) {
                if (!goodsList.get(i).isChoosed()) {
                    goodsList.remove(goodsList.get(i));

                }
            }
        }
        return shopCarModel;
    }
}
