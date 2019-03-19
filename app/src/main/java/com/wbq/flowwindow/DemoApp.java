package com.wbq.flowwindow;

import android.app.Application;

/**
 * Created by Jerry on 2019/3/15 11:21 AM
 */
public class DemoApp extends Application {

    static Application sApplication;

    public static Application getsApplication() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;

        Test.showTestToast();
    }
}
