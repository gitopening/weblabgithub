package com.weblab.adt.weblab.imservice.service;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.weblab.adt.weblab.DB.sp.LoginSp;
import com.weblab.adt.weblab.config.SysConstant;
import com.weblab.adt.weblab.imservice.event.LoginEvent;
import com.weblab.adt.weblab.imservice.manager.IMContactManager;
import com.weblab.adt.weblab.imservice.manager.IMGroupManager;
import com.weblab.adt.weblab.imservice.manager.IMLoginManager;
import com.weblab.adt.weblab.imservice.manager.IMSocketManager;
import com.weblab.adt.weblab.utils.Logger;

import de.greenrobot.event.EventBus;

public class IMService extends Service {
    private Logger logger = Logger.getLogger(IMService.class);

    //所有的管理类
    private IMSocketManager socketMgr = IMSocketManager.instance();
    private IMLoginManager loginMgr = IMLoginManager.instance();
    private LoginSp loginSp = LoginSp.instance();
    private IMContactManager contactMgr = IMContactManager.instance();
    private IMGroupManager groupMgr = IMGroupManager.instance();


    /**
     * binder
     */
    private IMServiceBinder binder = new IMServiceBinder();

    public class IMServiceBinder extends Binder {
        public IMService getService() {
            return IMService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        //当第一次运行程序时，从IMApplication过来后，第三个执行这里
        logger.i("IMService onBind");
        return binder;
    }

    @Override
    public void onCreate() {
        logger.i("IMService onCreate");
        super.onCreate();
        EventBus.getDefault().register(this, SysConstant.SERVICE_EVENTBUS_PRIORITY);
        /**
         * 设置该服务为前台服务
         */
        startForeground((int) System.currentTimeMillis(), new Notification());
    }

    @Override
    public void onDestroy() {
        logger.i("IMService onDestroy");
        // todo 在onCreate中使用startForeground
        // 在这个地方是否执行 stopForeground呐
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    // EventBus 事件驱动
    public void onEvent(LoginEvent event) {
        switch (event) {
            case LOGIN_OK:
//                onNormalLoginOk();
                break;
            case LOCAL_LOGIN_SUCCESS:
//                onLocalLoginOk();
                break;
            case LOCAL_LOGIN_MSG_SERVICE:
//                onLocalNetOk();
                break;
            case LOGIN_OUT:
//                handleLoginout();
                break;
        }
    }

    /**-----------------get/set 的实体定义---------------------*/
    public IMLoginManager getLoginManager() {
        return loginMgr;
    }

    public LoginSp getLoginSp() {
        return loginSp;
    }

    public IMContactManager getContactManager() {
        return contactMgr;
    }

    public IMGroupManager getGroupManager() {
        return groupMgr;
    }



    // 负责初始化 每个manager
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        logger.i("IMService onStartCommand");
        //应用开启初始化 下面这几个怎么释放 todo
        //当第一次运行程序时，从IMApplication过来后，第二个执行这里
        Context ctx = getApplicationContext();
        loginSp.init(ctx);
        // 放在这里还有些问题 todo
        socketMgr.onStartIMManager(ctx);
        loginMgr.onStartIMManager(ctx);

        return START_STICKY;
    }

}
