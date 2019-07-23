package com.cniupay.app.cniupaytest;

import android.app.Application;

import com.cniupay.pay.CNiuPay;

/**
 * Created by zhuzhuodong on 2018/12/18.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CNiuPay.getInstance(this).init("bf5a7bc6c9c0bffc0d3d55cf0991183b116a0fa15d654101b388e688060b85e6");
    }
}
