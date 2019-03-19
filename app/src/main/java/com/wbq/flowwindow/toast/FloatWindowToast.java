package com.wbq.flowwindow.toast;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FloatWindowToast {
    public static final String TAG = "FloatWindowToast";
    private final Context mContext;
    private Toast mToast;
    private boolean isShowing = false;

    private Object mTN;
    private Method mHide;

    public FloatWindowToast(Context context) {
        mContext = context;
        mToast = new Toast(mContext);
    }

    /**
     * Show the view for the specified duration.
     */
    public void show(View view, WindowManager.LayoutParams layoutParams) {
        if (isShowing) {
            return;
        }

        if (mToast == null) {
            mToast = new Toast(mContext);
        }

        mToast.setView(view);
        initTN(layoutParams);

        try {
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.show();
            Log.i(TAG, "FloatWindowToast:show success");
        } catch (Exception e) {
            Log.e(TAG, "FloatWindowToast:show error", e);
        }

        isShowing = true;
    }

    public void hide() {
        if (!isShowing) {
            return;
        }

        if (null == mHide) {
            Log.w(TAG,"FloatWindowToast:hide", new IllegalStateException());
            return;
        }
        try {
            mHide.invoke(mTN);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        isShowing = false;
    }

    private void initTN(WindowManager.LayoutParams layoutParams) {
        try {
            Field tnField = mToast.getClass().getDeclaredField("mTN");
            tnField.setAccessible(true);
            mTN = tnField.get(mToast);

            mHide = mTN.getClass().getMethod("hide");

            Field tnParamsField = mTN.getClass().getDeclaredField("mParams");
            tnParamsField.setAccessible(true);
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) tnParamsField.get(mTN);
            params.flags = layoutParams.flags;
            params.format = layoutParams.format;
            params.gravity = layoutParams.gravity;
            params.width = layoutParams.width;
            params.height = layoutParams.height;
            params.x = layoutParams.x;
            params.y = layoutParams.y;

            /** must set mNextView before calling show*/
            Field tnNextViewField = mTN.getClass().getDeclaredField("mNextView");
            tnNextViewField.setAccessible(true);
            tnNextViewField.set(mTN, mToast.getView());

            setGravity(layoutParams.gravity, layoutParams.x, layoutParams.y);
            Log.e(TAG, "initTN success");
        } catch (Exception e) {
            Log.e(TAG, "initTN exception", e);
        }

    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        mToast.setGravity(gravity, xOffset, yOffset);
    }

}
