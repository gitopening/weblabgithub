package com.weblab.adt.weblab.app;

import android.app.Application;
import android.content.Intent;

import com.weblab.adt.weblab.imservice.service.IMService;
import com.weblab.adt.weblab.utils.ImageLoaderUtil;
import com.weblab.adt.weblab.utils.Logger;


/**
 * 全局的Application 应用起来，先进这里
 */
public class IMApplication  extends Application {

    /**
     * 引入自定义的工具类
     */
    private Logger logger = Logger.getLogger(IMApplication.class);

    /**
     * 先定义一个全局常量，暂时不清楚用在哪里
     */
    public static boolean gifRunning = true;//gif是否运行

    public void onCreate() {
        super.onCreate();
        logger.i("Application starts");
        startIMService();
        ImageLoaderUtil.initImageLoaderConfig(getApplicationContext());
    }

    private void startIMService() {
        logger.i("start IMService");
        Intent intent = new Intent();
        intent.setClass(this, IMService.class);
        startService(intent);
    }
}
