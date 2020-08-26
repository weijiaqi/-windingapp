package app.winding.com.windingapp.activity;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.Locale;

import app.winding.com.windingapp.MainActivity;
import app.winding.com.windingapp.R;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.ui.GuideOneActivity;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.MyHandler;
import app.winding.com.windingapp.util.SharedPreferencesUtils;

public class SplashActivity extends BaseActivity implements MyHandler.OnHandlerListener {

    private MyHandler myHandler;
    private String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        myHandler = new MyHandler<>(this);
        myHandler.setOnHandlerListener(this);
        myHandler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
    }

    @Override
    public void handlerMessage(Message msg) {
        int what = msg.what;
        if (what == 0) {
            String token = (String) SharedPreferencesUtils.getSp(Constants.TOKEN, "");
            if (!TextUtils.isEmpty(token) && token != null) {
                JumpActivityUtil.launchActivity(this, MainActivity.class);
                finish();
            } else {

                boolean flag = (boolean) SharedPreferencesUtils.getSp(Constants.GUIDEONELOGIN, false);
                if (flag == true) {
                    Log.e(TAG, "SplashActivity------1");
                    JumpActivityUtil.launchActivity(this, LoginActivity.class);
                    finish();
                } else {
                    JumpActivityUtil.launchActivity(this, GuideOneActivity.class);
                    finish();
                }

            }
        }
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
        super.onBackPressed();
    }

}
