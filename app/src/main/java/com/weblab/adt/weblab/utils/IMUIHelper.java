package com.weblab.adt.weblab.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.weblab.adt.weblab.R;
import com.weblab.adt.weblab.imservice.event.SocketEvent;

public class IMUIHelper {
	public static int getSocketErrorTip(SocketEvent event) {
		switch (event) {
			case CONNECT_MSG_SERVER_FAILED :
				return R.string.connect_msg_server_failed;
			case REQ_MSG_SERVER_ADDRS_FAILED :
				return R.string.req_msg_server_addrs_failed;
			default :
				return  R.string.login_error_unexpected;
		}
	}
}
