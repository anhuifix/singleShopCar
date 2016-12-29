package com.mask.singleshopcar.Model;

import java.util.List;

/**
 * 购物车
 * Created by zhuangAH on 2016-11-23.
 */

public class ShopCarModel extends DIFactoryModel {


    private static final long serialVersionUID = 522896327567983686L;
    //商品数据
    private List<GoodsModel> goodsModel;

    public List<GoodsModel> getGoodsModel() {
        return goodsModel;
    }

    public void setGoodsModel(List<GoodsModel> goodsModel) {
        this.goodsModel = goodsModel;
    }
}
