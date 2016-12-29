package com.mask.singleshopcar.listener;


import com.mask.singleshopcar.Model.ShopCarModel;

/**
 * 复选框
 * Created by zhuangAH on 2016-11-7.
 */

public interface CheckGoodsListener {
    /**
     * 组选框状态改变触发的事件
     *
     * @param groupPosition 组元素位置
     * @param isChecked     组元素选中与否
     */
    void checkGroup(int groupPosition, boolean isChecked);

    /**
     * 子选框状态改变时触发的事件
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param isChecked     子元素选中与否
     */
    void checkChild(int groupPosition, int childPosition, boolean isChecked);


    /**
     * 购买
     * @param groupPosition
     * @param childPosition
     * @param isChecked
     */
    void checkGoodsBuy(ShopCarModel shopCarModel, double totalMonery, int totalCount);
}
