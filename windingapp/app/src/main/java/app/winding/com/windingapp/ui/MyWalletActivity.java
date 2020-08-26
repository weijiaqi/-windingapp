package app.winding.com.windingapp.ui;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.UserInfosEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 我的钱包
 */
public class MyWalletActivity extends BaseActivity implements NoticeObserver.Observer {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.rlDe_binding)
    TextView rlDe_binding;
    @Bind(R.id.withdrawal)
    TextView withdrawal;
    @Bind(R.id.FrozenBlance)
    TextView FrozenBlance;
    @Bind(R.id.updateaccount)
    TextView updateaccount;
    @Bind(R.id.Cash_withdrawal)
    TextView Cash_withdrawal;
    @Bind(R.id.content)
    TextView content;
    int state = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        NoticeObserver.getInstance().addObserver(this);
        ivBack.setOnClickListener(v -> {
            finish();
            state = 2;
        });
        tvTitles.setText("我的钱包");

        get_user_info();
        get_words_list();
    }

    @OnClick({R.id.rlDe_binding})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.rlDe_binding:
                JumpActivityUtil.launchActivity(MyWalletActivity.this, BindingAlipayActivity.class);
                break;
//
        }
    }


    public void get_words_list() {
        ApiInterface.ApiFactory.createApi().get_words_list().enqueue(new Callback<WordsListEntity>() {
            @Override
            public void onResponse(Call<WordsListEntity> call, Response<WordsListEntity> response) {
                if (state == 1) {
                    if (response.body().getCode() == 200) {
                        content.setText(response.body().getResult().getUser_present().toString());
                    }
                }

            }

            @Override
            public void onFailure(Call<WordsListEntity> call, Throwable t) {

            }
        });
    }

    public void get_user_info() {
        ApiInterface.ApiFactory.createApi().get_user_info().enqueue(new Callback<UserInfosEntity>() {
            @Override
            public void onResponse(Call<UserInfosEntity> call, Response<UserInfosEntity> response) {
                if (state == 1) {
                    if (response.body().getCode().equals("00000")) {

                        withdrawal.setText("￥" + response.body().getResult().getData().getMoney());
                        FrozenBlance.setText("￥" + response.body().getResult().getData().getLock_money());
                        if (response.body().getResult().getData().getAlipay_account() != null && !TextUtils.isEmpty(response.body().getResult().getData().getAlipay_account())) {
                            rlDe_binding.setText(response.body().getResult().getData().getAlipay_account());
                            updateaccount.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
                            updateaccount.getPaint().setAntiAlias(true);//抗锯齿
                            updateaccount.setVisibility(View.VISIBLE);
                            updateaccount.setOnClickListener(v -> {
                                JumpActivityUtil.launchActivity(MyWalletActivity.this, UpdateUserAlipayActivity.class);
                            });
                        }
                        Cash_withdrawal.setOnClickListener(v -> {
                            if (response.body().getResult().getData().getAlipay_account() != null && !TextUtils.isEmpty(response.body().getResult().getData().getAlipay_account())) {
                                JumpActivityUtil.launchActivity(MyWalletActivity.this, CashWithdrawalActivity.class);
                            } else {
                                ToastUtil.show("请先绑定支付宝!", 200);
                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<UserInfosEntity> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        state = 2;
        NoticeObserver.getInstance().removeObserver(this);
    }

    @Override
    public <T> void update(int what, T t) {
        if (what == Constants.UPDATEPAYBIDING) {
            get_user_info();
        } else if (what == Constants.HEADIMGURL) {
            get_user_info();
        }
    }
}
