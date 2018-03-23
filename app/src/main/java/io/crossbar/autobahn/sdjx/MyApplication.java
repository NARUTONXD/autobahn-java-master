package io.crossbar.autobahn.sdjx;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import io.crossbar.autobahn.sdjx.utils.PreferenceUtil;
import io.crossbar.autobahn.websocket.WebSocketConnection;
import io.crossbar.autobahn.websocket.WebSocketConnectionHandler;
import io.crossbar.autobahn.websocket.exceptions.WebSocketException;
import io.crossbar.autobahn.websocket.interfaces.IWebSocket;
import io.crossbar.autobahn.websocket.types.WebSocketOptions;

/**
 * Created by Administrator on 2018/1/30 0030.
 */

public class MyApplication extends Application {
    public static final IWebSocket mConnection = new WebSocketConnection();
    private static MyApplication instance;
    public static String LOGINTYPE = "";
    //以下属性应用于整个应用程序，合理利用资源，减少资源浪费
    private static Context mContext;//上下文
    private static Thread mMainThread;//主线程
    private static long mMainThreadId;//主线程id
    private static Looper mMainLooper;//循环队列
    private static Handler mHandler;//主线程Handler

    public static Context getmContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        MyApplication.mContext = mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        MyApplication.mMainThread = mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        MyApplication.mMainThreadId = mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainLooper(Looper mMainLooper) {
        MyApplication.mMainLooper = mMainLooper;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    public static void setMainHandler(Handler mHandler) {
        MyApplication.mHandler = mHandler;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        start();
        PreferenceUtil.init(this);
        instance = this;
        if (isPad()) {
            LOGINTYPE = "1";
        } else {
            LOGINTYPE = "1";
        }
//        EMOptions options = new EMOptions();
//// 默认添加好友时，是不需要验证的，改成需要验证
////        options.setAcceptInvitationAlways(false);
//        EaseUI.getInstance().init(instance, options);
    }

    //判断是否是平板登录
    private boolean isPad() {
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        // 屏幕宽度
        float screenWidth = display.getWidth();
        // 屏幕高度
        float screenHeight = display.getHeight();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        // 屏幕尺寸
        double screenInches = Math.sqrt(x + y);
        // 大于6尺寸则为Pad
        if (screenInches >= 6.0) {
            return true;
        }
        return false;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void start() {
        String hostname = "ws://192.168.1.199";
//        if (!hostname.startsWith("ws://") && !hostname.startsWith("wss://")) {
//            hostname = "ws://" + hostname;
//        }
        String port = "8282";
        String wsuri;
        if (!port.isEmpty()) {
            wsuri = hostname + ":" + port;
        } else {
            wsuri = hostname;
        }
        WebSocketOptions connectOptions = new WebSocketOptions();
        connectOptions.setReconnectInterval(5000);

        try {
            mConnection.connect(wsuri, new WebSocketConnectionHandler() {
                @Override
                public void onOpen() {
                }

                @Override
                public void onMessage(String payload) {
                    alert("Got echo: " + payload);
                    Log.d("Got", payload);
//                    if (payload.startsWith("{\"type\":\"talk\"")) {
//                        IMmessageBean iMmessageBean = Gson.fromJson(payload, IMmessageBean.class);
//                        tv_msg.setText("及时消息:    " + iMmessageBean.getVal());
//                    } else if (payload.startsWith("{\"type\":\"storeMsg\"")) {
//                        StringBuffer asd = new StringBuffer();
//                        MessageBean messageBean = Gson.fromJson(payload, MessageBean.class);
//                        for (int i = 0; i < messageBean.getInfo().size(); i++) {
//                            asd = asd.append(messageBean.getInfo().get(i).getVal() + " : ");
//                        }
//                        tv_msg.setText("历史消息:    " + asd.toString());
//                    } else {
//                    }
                }

                @Override
                public void onClose(int code, String reason) {
                    alert("Connection lost.");
                }
            }, connectOptions);
        } catch (WebSocketException e) {

        }
    }

    private void alert(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
