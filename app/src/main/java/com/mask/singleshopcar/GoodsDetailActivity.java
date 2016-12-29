package com.mask.singleshopcar;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;

/**
 * Created by zhuangAh on 2016-12-29.
 */

public class GoodsDetailActivity extends BaseActivity {
    @Bind(R.id.iamge)
    public ImageView iamge = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        Glide.with(GoodsDetailActivity.this)
                .load("http://d6.yihaodianimg.com/N07/M04/7B/1C/ChEbvFVKKlCASVATAAW664fSV9Q78900.jpg")
                .centerCrop()
                .placeholder(R.mipmap.test2)
                .crossFade()
                .into(iamge);

    }
}
