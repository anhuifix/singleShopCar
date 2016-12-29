package com.mask.singleshopcar.listener;

import android.view.View;

/**
 * 改变数量的接口
 * Created by zhuangAH on 2016-11-7.
 */

public interface ModifyCountListener {

    /**
     * 增加操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

    /**
     * 删减操作
     *
     * @param groupPosition 组元素位置
     * @param childPosition 子元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

    /**
     * 删除子item
     *
     * @param groupPosition
     * @param childPosition
     */
    void childDelete(int groupPosition, int childPosition);

}
