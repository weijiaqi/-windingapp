package app.winding.com.windingapp.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import app.winding.com.windingapp.MainActivity;
import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.CodeEntity;
import app.winding.com.windingapp.entity.UserEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.CommonUtils;
import app.winding.com.windingapp.util.CountDownButtonHelper;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.LogUtil;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;

import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.code)
    EditText code;
    @Bind(R.id.getcode)
    TextView getcode;
    @Bind(R.id.Invitationcode)
    TextView Invitationcode;
    private CountDownButtonHelper countHelper;
    int clicktime = 0;//记录上一次单击的时间，初始值为0
    String codeid;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {

        countHelper = new CountDownButtonHelper(this, getcode, getString(R.string.login_send_code), 60, 1, CountDownButtonHelper.TYPE_USRE_LOGIN);


        phone.setOnClickListener(v -> {
            phone.setCursorVisible(true);// 再次点击显示光标
        });
        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @SuppressLint("StringFormatMatches")
            @Override
            public void afterTextChanged(Editable text) {
                //输入框为0
                if (code.getText().toString().length() == 4) {
                    phone.setCursorVisible(false);
                    code.clearFocus();//失去焦点
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }

            }
        });
    }


    @OnClick({R.id.cofimlogin, R.id.getcode})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.cofimlogin:
                LoadingDialog loadingDialog = new LoadingDialog(this, this.getString(R.string.loading));
                loadingDialog.show();
                if (CommonUtils.checkusername(this, loadingDialog, phone.getText().toString(), code.getText().toString())) {

                    ApiInterface.ApiFactory.createApi().user_reg(phone.getText().toString(), code.getText().toString(), Invitationcode.getText().toString(), "1.0").enqueue(new Callback<UserEntity>() {
                        @Override
                        public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                            if (response.body().getCode() == 200) {
                                loadingDialog.dismiss();
                                SharedPreferencesUtils.putInt(Constants.ID, response.body().getResult().getId());
                                SharedPreferencesUtils.putString(Constants.TOKEN, response.body().getResult().getToken());
                                SharedPreferencesUtils.putString(Constants.MOBILE, response.body().getResult().getMobile());
                                SharedPreferencesUtils.putString(Constants.USERNAME, response.body().getResult().getUsername());
                                SharedPreferencesUtils.putString(Constants.HEADIMAGEURL, response.body().getResult().getHeadimgurl());
                                JumpActivityUtil.launchActivity(LoginActivity.this, MainActivity.class);
                            } else {
                                loadingDialog.dismiss();
                                ToastUtil.show(response.body().getMessage().toString(), 200);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserEntity> call, Throwable t) {
                            loadingDialog.dismiss();
                            ToastUtil.show("登录失败", 200);
                        }
                    });

                }
                break;
            case R.id.getcode:
                if (CommonUtils.getphonecode(this, phone.getText().toString())) {

                    if (clicktime == 0) {
                        clicktime = 1;
                        GETCODE();
                    } else if (getcode.getText().toString().equals("发送验证码")) {
                        clicktime = 1;
                        GETCODE();
                    }
                }

                break;

        }
    }


    public void GETCODE() {
        ApiInterface.ApiFactory.createApi().regcode(phone.getText().toString()).enqueue(new Callback<CodeEntity>() {
            @Override
            public void onResponse(Call<CodeEntity> call, Response<CodeEntity> response) {
                if (response.body().getCode() == 200) {
                    countHelper.start();
                    ToastUtil.show(getText(R.string.send_success), 25);
                    codeid = response.body().getResult().getCode();
                    LogUtil.e("codeid", response.body().getResult().getCode() + "");
                    code.setFocusable(true);
                    code.setFocusableInTouchMode(true);
                    code.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(code, 0);
                    return;
                } else {
                    ToastUtil.showShort(response.body().getMessage().toString());
                    clicktime = 0;
                    return;
                }
            }

            @Override
            public void onFailure(Call<CodeEntity> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
