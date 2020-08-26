package app.winding.com.windingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.CodeEntity;
import app.winding.com.windingapp.entity.UserAlipayEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.CommonUtils;
import app.winding.com.windingapp.util.CountDownButtonHelper;
import app.winding.com.windingapp.util.LogUtil;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//更改支付宝绑定
public class UpdateUserAlipayActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.pay_Account)
    TextView pay_Account;
    @Bind(R.id.phones)
    TextView phones;

    @Bind(R.id.code)
    TextView code;
    @Bind(R.id.getcode)
    TextView getcode;
    int clicktime = 0;//记录上一次单击的时间，初始值为0
    private CountDownButtonHelper countHelper;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_update_user_alipay;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("更改支付宝绑定");
        countHelper = new CountDownButtonHelper(this, getcode, "获取验证码", 60, 1, CountDownButtonHelper.TYPE_USRE_LOGIN);
    }


    @OnClick({R.id.Bind_immediately, R.id.getcode})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.Bind_immediately:

                if (TextUtils.isEmpty(name.getText().toString())) {
                    ToastUtil.showShort(context.getString(R.string.login_empty_phone));

                    return;
                }


                if (TextUtils.isEmpty(code.getText().toString())) {
                    ToastUtil.showShort(context.getString(R.string.login_empty_code));

                    return;
                }
                if (!CommonUtils.checkMobile(phones.getText().toString())) {
                    ToastUtil.showShort(context.getString(R.string.please_correct_phone));
                    return;
                }
                if (TextUtils.isEmpty(phones.getText().toString())) {
                    ToastUtil.showShort(context.getString(R.string.login_empty_code));

                    return;
                }
                if (TextUtils.isEmpty(pay_Account.getText().toString())) {
                    ToastUtil.showShort("请输入支付宝账号!");
                    return;
                }

                updateUserAlipay(phones.getText().toString(), code.getText().toString(), pay_Account.getText().toString(), name.getText().toString());
                break;
            case R.id.getcode:
                if (TextUtils.isEmpty(phones.getText().toString())) {
                    ToastUtil.showShort(context.getString(R.string.login_empty_phone));
                    return;
                }
                if (!CommonUtils.checkMobile(phones.getText().toString())) {
                    ToastUtil.showShort(context.getString(R.string.please_correct_phone));
                    return;
                }

                if (clicktime == 0) {
                    clicktime = 1;
                    GETCODE();
                } else if (getcode.getText().toString().equals("获取验证码")) {
                    clicktime = 1;
                    GETCODE();
                }

                break;

        }
    }

    public void updateUserAlipay(String mobile, String verify, String alipay_account, String real_name) {
        ApiInterface.ApiFactory.createApi().updateUserAlipay(mobile, verify, alipay_account, real_name).enqueue(new Callback<UserAlipayEntity>() {
            @Override
            public void onResponse(Call<UserAlipayEntity> call, Response<UserAlipayEntity> response) {
                if (response.body().getCode().equals("00000")) {
                    ToastUtil.show("更改成功", 200);
                    finish();
                    NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYBIDING);

                }else
                {
                    ToastUtil.show(response.body().getMessage().toString(), 200);
                }
            }

            @Override
            public void onFailure(Call<UserAlipayEntity> call, Throwable t) {

            }
        });
    }

    public void GETCODE() {
        ApiInterface.ApiFactory.createApi().regcode(phones.getText().toString()).enqueue(new Callback<CodeEntity>() {
            @Override
            public void onResponse(Call<CodeEntity> call, Response<CodeEntity> response) {
                if (response.body().getCode() == 200) {
                    countHelper.start();
                    ToastUtil.show(getText(R.string.send_success), 25);
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
}
