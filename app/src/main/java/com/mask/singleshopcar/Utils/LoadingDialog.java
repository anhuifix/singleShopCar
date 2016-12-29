package com.mask.singleshopcar.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mask.singleshopcar.R;


/**
 * @date 2015-4-1
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
        init();
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    private ImageView imageView = null;
    private TextView txt = null;

    private void init() {
        this.setContentView(R.layout.dialog_loading);
        imageView = (ImageView) findViewById(R.id.iv_loading);
        txt = (TextView) findViewById(R.id.txt_message);
    }

    /**
	 * 
	 */
    public void onWindowFocusChanged(boolean hasFocus) {
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

    /**
     * 
     * @param message
     */
    public void setMessage(CharSequence message) {
        if (message != null && message.length() > 0) {
            txt.setVisibility(View.VISIBLE);
            txt.setText(message);
            txt.invalidate();
        }
    }

    /**
     */
    public void setMessage(int resid) {
        txt.setVisibility(View.VISIBLE);
        txt.setText(resid);
        txt.invalidate();
    }
    
    /**
     * 
     * @param context
     * @param message
     * @param cancelable
     * @param cancelListener
     * @return
     */
    public static LoadingDialog show(Context context, CharSequence message, boolean cancelable,
                                     OnCancelListener cancelListener) {
        LoadingDialog progressHUD = create(context, message, cancelable, cancelListener);
        progressHUD.show();
        return progressHUD;
    }
    
    public static LoadingDialog show(Context context, int message) {
        LoadingDialog progressHUD = show(context, context.getResources().getString(message), false, null);
        progressHUD.show();
        return progressHUD;
    }
    
    public static LoadingDialog show(Context context, String message) {
        LoadingDialog progressHUD = show(context, message, false, null);
        progressHUD.show();
        return progressHUD;
    }

    /**
     * @param context
     * @param message
     * @param cancelable
     * @param cancelListener
     * @return
     */
    public static LoadingDialog create(Context context, CharSequence message, boolean cancelable,
                                       OnCancelListener cancelListener) {
        LoadingDialog dialog = new LoadingDialog(context, R.style.ProgressHUD);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle(null);
        dialog.setMessage(message);
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.setOnCancelListener(cancelListener);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.1f;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        return dialog;
    }

}
