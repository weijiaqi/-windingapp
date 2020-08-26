package app.winding.com.windingapp.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.BindUserAlipayEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 绑定支付宝
 */
public class BindingAlipayActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.pay_Account)
    EditText payAccount;
    @Bind(R.id.Bind_immediately)
    TextView BindImmediately;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_binding_alipay;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v->{finish();});
        tvTitles.setText("支付宝绑定");


    }




    @OnClick({R.id.Bind_immediately})
    public void onclik(View view) {
        switch (view.getId()) {
            case R.id.Bind_immediately:
                if (name.getText().toString() == null && TextUtils.isEmpty(name.getText().toString())) {
                    ToastUtil.show("请输入支付宝账号认证的真实姓名!", 200);
                    return;
                }
                if (payAccount.getText().toString() == null && TextUtils.isEmpty(payAccount.getText().toString())) {
                    ToastUtil.show("请输入支付宝账号!", 200);
                    return;
                }
                bindUserAlipay(payAccount.getText().toString(),name.getText().toString());
                break;
        }
    }

    public void  bindUserAlipay(String alipay_account,String real_name){
        ApiInterface.ApiFactory.createApi().bindUserAlipay(alipay_account,real_name).enqueue(new Callback<BindUserAlipayEntity>() {
            @Override
            public void onResponse(Call<BindUserAlipayEntity> call, Response<BindUserAlipayEntity> response) {
                if (response.body().getCode().equals("00000")){
                    ToastUtil.show("绑定成功!",200);
                    finish();
                    NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYBIDING);
                }
            }

            @Override
            public void onFailure(Call<BindUserAlipayEntity> call, Throwable t) {

            }
        });
    }
}
