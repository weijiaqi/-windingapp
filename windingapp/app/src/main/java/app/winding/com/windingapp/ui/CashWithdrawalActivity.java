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
import app.winding.com.windingapp.entity.UserInfosEntity;
import app.winding.com.windingapp.entity.UserPresentEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 提现
 */
public class CashWithdrawalActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.Withdrawable)
    TextView Withdrawable;
    @Bind(R.id.Immediate)
    TextView Immediate;
    @Bind(R.id.writewithdrawals)
    EditText writewithdrawals;
    @Bind(R.id.content)
    TextView content;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_cash_withdrawal;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("金额提现");
        get_user_info();
        get_words_list();
    }


    public void get_words_list() {
        ApiInterface.ApiFactory.createApi().get_words_list().enqueue(new Callback<WordsListEntity>() {
            @Override
            public void onResponse(Call<WordsListEntity> call, Response<WordsListEntity> response) {
                if (response.body().getCode() == 200) {
                    content.setText(response.body().getResult().getPresent_page().toString());
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
                if (response.body().getCode().equals("00000")) {

                    Withdrawable.setText("可提现金额：￥" + response.body().getResult().getData().getMoney());

                    Immediate.setOnClickListener(v -> {
                        if (writewithdrawals.getText().toString() == null && TextUtils.isEmpty(writewithdrawals.getText().toString())) {
                            ToastUtil.show("请输入提现金额!", 200);
                            return;
                        }
                        if (Double.parseDouble(writewithdrawals.getText().toString()) == 0) {
                            ToastUtil.show("输入金额不能为0!", 200);
                            return;
                        }
                        if (Double.parseDouble(writewithdrawals.getText().toString()) > Double.parseDouble(response.body().getResult().getData().getMoney())) {
                            ToastUtil.show("提现金额输入错误!", 200);
                            return;
                        }
                        user_present(writewithdrawals.getText().toString());
                    });
                }
            }

            @Override
            public void onFailure(Call<UserInfosEntity> call, Throwable t) {

            }
        });
    }

    public void user_present(String money) {
        ApiInterface.ApiFactory.createApi().user_present(money).enqueue(new Callback<UserPresentEntity>() {
            @Override
            public void onResponse(Call<UserPresentEntity> call, Response<UserPresentEntity> response) {
                if (response.body().getCode().equals("00000")) {
                    NoticeObserver.getInstance().notifyObservers(Constants.HEADIMGURL);
                    ToastUtil.show("提现成功!", 200);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserPresentEntity> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.Withdrawals})
    public void Withdrawals(View view) {
        switch (view.getId()) {
            case R.id.Withdrawals:
                JumpActivityUtil.launchActivity(this,WithdrawalsRecordActivity.class);
                break;
        }
    }
}
