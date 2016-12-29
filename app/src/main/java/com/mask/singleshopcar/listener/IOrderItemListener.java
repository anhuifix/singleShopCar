package com.mask.singleshopcar.listener;

/**
 * Created by Administrator on 2016-11-18.
 */

public interface IOrderItemListener {

    /**
     * 延长收货点击事件
     * @param groupPosition 组元素位置
     * @param storeId     店铺id
     */
    void extendedReceipt(int groupPosition, String storeId);

    /**
     * 确认收货点击事件
     * @param groupPosition 组元素位置
     * @param storeId     店铺id
     */
    void confirmReceipt(int groupPosition, String storeId);

    /**
     * 去支付点击事件
     * @param groupPosition 组元素位置
     * @param storeId     店铺id
     */
    void gotoPay(int groupPosition, String storeId);

    void childItemClick(int groupPosition, String storeId);

}
