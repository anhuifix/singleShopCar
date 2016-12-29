package com.mask.singleshopcar;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * 基础类
 *
 * @author zhuangAH
 */
public abstract class BaseActivity extends FragmentActivity {


    private TextView txtTitleView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        txtTitleView = (TextView) findViewById(R.id.tv_header);
        setViewClickListener(R.id.img_back, new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClick(view);
            }
        });
        //    	if(layoutResID != R.layout.activity_order_details && layoutResID != R.layout.activity_print_setter && layoutResID != R.layout.activity_userinfo){
        //    		ButterKnife.bind(this);
        //    	}
        ButterKnife.bind(this);
    }

    public void setContentView(int layoutResID, boolean isBind) {
        super.setContentView(layoutResID);
        txtTitleView = (TextView) findViewById(R.id.tv_header);
        setViewClickListener(R.id.img_back, new OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClick(view);
            }
        });
    }

    /**
     * 点击返回
     *
     * @param view
     */
    public void onBackClick(View view) {
        this.finish();
    }

    /**
     * 设置button点击事件
     *
     * @param resId
     * @param onClickListener
     */
    public void setViewClickListener(int resId, OnClickListener onClickListener) {
        View view = findViewById(resId);
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    /**
     * 设置标题点击事件
     *
     * @param onClickListener
     */
    public void setTitleViewClickListener(OnClickListener onClickListener) {
        setViewClickListener(R.id.tv_header, onClickListener);
    }

    public void setViewHide(int resId, int visibility) {
        View view = findViewById(resId);
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (txtTitleView != null) {
            txtTitleView.setText(title);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitleId(int title) {
        if (txtTitleView != null) {
            txtTitleView.setText(title);
        }
    }


    public boolean isPrint() {
        return false;
    }

    /**
     * 返回事件
     */
    public void onKeyBack() {
        this.finish();
    }
}
