package com.wbq.flowwindow;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.wbq.flowwindow.toast.FloatWindowToast;

import static com.wbq.flowwindow.toast.FloatWindowToast.TAG;

/**
 * Created by Jerry on 2019/3/19 2:13 PM
 */
public class Test {
    public static void showTestToast() {
        Context context = DemoApp.getsApplication();
        View view = LayoutInflater.from(context).inflate(R.layout.view_item, null);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"FloatWindowToast:onClick");
            }
        };
        view.findViewById(R.id.img).setOnClickListener(listener);
        view.findViewById(R.id.txt).setOnClickListener(listener);

        Log.e(TAG, "TargetSdkVersion=" + context.getApplicationInfo().targetSdkVersion);

        FloatWindowToast toast = new FloatWindowToast(context);

        toast.show(view, getWindowLayoutParams());
    }

    private static WindowManager.LayoutParams getWindowLayoutParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_FULLSCREEN;

//        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        params.format = PixelFormat.TRANSLUCENT;
        params.gravity = Gravity.CENTER;

        params.type = WindowManager.LayoutParams.TYPE_TOAST;

        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.x = 0;
        params.y = 0;

        return params;
    }
}
