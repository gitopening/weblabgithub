package com.weblab.adt.weblab.imservice.support;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.weblab.adt.weblab.imservice.service.IMService;
import com.weblab.adt.weblab.utils.Logger;


/**
 * IMService绑定
 * @modify yingmu
 * 1. 供上层使用【activity】
 * 同层次的manager没有必要使用。
 * 暴露IMService绑定功能，这样在不同的地方调用的时候，针对不同的情况进行处理
 */
public abstract class IMServiceConnector {
    protected static Logger logger = Logger.getLogger(IMServiceConnector.class);

    public abstract void onIMServiceConnected();
    public abstract void onServiceDisconnected();

	private IMService imService;
	public IMService getIMService() {
		return imService;
	}

	// todo eric when to release?
	private ServiceConnection imServiceConnection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// todo eric when to unbind the service?
			// TODO Auto-generated method stub
			logger.i("onService(imService)Disconnected");
            IMServiceConnector.this.onServiceDisconnected();
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			logger.i("im#onService(imService)Connected");

			if (imService == null) {
				IMService.IMServiceBinder binder = (IMService.IMServiceBinder) service;
				imService = binder.getService();

				if (imService == null) {
					logger.e("im#get imService failed");
					return;
				}
				logger.d("im#get imService ok");
			}
            IMServiceConnector.this.onIMServiceConnected();
		}
	};

    public boolean connect(Context ctx) {
		return bindService(ctx);
	}

    public void disconnect(Context ctx) {
		logger.d("im#disconnect");
		unbindService(ctx);
        IMServiceConnector.this.onServiceDisconnected();
	}

	public boolean bindService(Context ctx) {
		logger.d("im#bindService");

		Intent intent = new Intent();
		intent.setClass(ctx, IMService.class);

		if (!ctx.bindService(intent, imServiceConnection, Context.BIND_AUTO_CREATE)) {
			logger.e("im#bindService(imService) failed");
			return false;
		} else {
			logger.i("im#bindService(imService) ok");
			return true;
		}
	}

	public void unbindService(Context ctx) {
		try {
			// todo eric .check the return value .check the right place to call it
			ctx.unbindService(imServiceConnection);
		} catch (IllegalArgumentException exception) {
			logger.w("im#got exception becuase of unmatched bind/unbind, we sould place to onStop next version.e:%s", exception.getMessage());
		}
		logger.i("unbindservice ok");
	}

}
