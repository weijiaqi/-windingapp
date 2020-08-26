package app.winding.com.windingapp.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import app.winding.com.windingapp.api.RestApi;
import app.winding.com.windingapp.manage.log.LogLevel;
import app.winding.com.windingapp.manage.log.Logger;
import cn.jpush.android.api.JPushInterface;


public class MainApplication extends Application {
    private static final String TAG = "JIGUANG-Example";
    private static MainApplication globalApp;
    private Gson mGson;
    @Override
    public void onCreate() {
        super.onCreate();

        globalApp = this;
        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);

        initData();
        Logger
                .init(getPackageName())
                .methodCount(3)
                .hideThreadInfo()
                .logLevel(RestApi.isDebug ? LogLevel.FULL : LogLevel.NONE);

        Logger.d(TAG, "[ExampleApplication] onCreate");
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        UMConfigure.init(this, "5c05336eb465f5e72900044c", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "");
    }


    public synchronized static MainApplication getContext() {
        return globalApp;
    }

    public static MainApplication getInstance() {
        return globalApp;
    }

    public static Resources getAppResources() {
        return globalApp.getResources();
    }


    /**
     * 初始化数据
     */
    private void initData() {
        // 不转换没有 @Expose 注解的字段
        mGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();

    }

    /**
     * 获取不使用注解的gson实例
     *
     * @return
     */
    public synchronized Gson getSimpleGson() {
        if (mGson == null)
            mGson = new Gson();
        return mGson;
    }

    //各个平台的配置
    {
        //微信
        //wxaef55d1fea669618   7d22591487cfc7cec3a09abb162fad36
        PlatformConfig.setWeixin("wx0a1222d5324260b1", "2b30cca0a62b57c00cc1bc7ac0114ce4");
        PlatformConfig.setSinaWeibo("3411236321","86299425323c95dcf6b9dce190014eee","http://qqt.ruimofang.com/");
        PlatformConfig.setQQZone("1108360678","0Q54GIr0ahOP6GxH");
    }
}
