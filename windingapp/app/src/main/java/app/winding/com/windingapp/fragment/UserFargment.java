package app.winding.com.windingapp.fragment;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.BindingMobileActivity;
import app.winding.com.windingapp.activity.ContactCustomerActivity;
import app.winding.com.windingapp.activity.LoginActivity;
import app.winding.com.windingapp.activity.MyInvitationActivity;
import app.winding.com.windingapp.activity.MyReleaseActivity;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseFragment;
import app.winding.com.windingapp.entity.UserInfosEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.ui.AboutMeActivity;
import app.winding.com.windingapp.ui.CircleImageView;
import app.winding.com.windingapp.ui.MyWalletActivity;
import app.winding.com.windingapp.ui.PushInfoActivity;
import app.winding.com.windingapp.ui.SettingActivity;
import app.winding.com.windingapp.ui.TransactionDetailsActivity;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.GlideUtils;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 我的
 */
public class UserFargment extends BaseFragment implements NoticeObserver.Observer {

    @Bind(R.id.headpic)
    CircleImageView headpic;
    @Bind(R.id.info_nikename)
    TextView infoNikename;
    @Bind(R.id.My_release)
    SuperTextView MyRelease;
    @Bind(R.id.My_invitation)
    SuperTextView MyInvitation;
    @Bind(R.id.Binding_Mobile)
    SuperTextView BindingMobile;
    @Bind(R.id.Contact_Customer)
    SuperTextView ContactCustomer;
    @Bind(R.id.withdrawal)
    TextView withdrawal;
    @Bind(R.id.FrozenBlance)
    TextView FrozenBlance;
    @Bind(R.id.llheight)
    LinearLayout llheight;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.iv_right)
    ImageView iv_right;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_user_fargment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) throws IOException {

        DisplayMetrics dm2 = getResources().getDisplayMetrics();

        if (dm2.heightPixels == 2960 || dm2.heightPixels == 2220) {

        } else if (dm2.heightPixels > 2000) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llheight.getLayoutParams();
            layoutParams.setMargins(0, 20, 0, 0);
            llheight.setLayoutParams(layoutParams);
        }


        NoticeObserver.getInstance().addObserver(this);
        //获取用户信息
        get_user_info();


        refreshLayout.setOnRefreshListener(refreshlayout -> {

            get_user_info();
            refreshLayout.finishRefresh();
        });


        iv_right.setOnClickListener(v -> {
            JumpActivityUtil.launchActivity(mContext,PushInfoActivity.class);
        });
    }

    public void get_user_info() {
        ApiInterface.ApiFactory.createApi().get_user_info().enqueue(new Callback<UserInfosEntity>() {
            @Override
            public void onResponse(Call<UserInfosEntity> call, Response<UserInfosEntity> response) {
                if (response.body().getCode().equals("00000")) {
                    if (response.body().getResult().getData().getHeadimgurl() != null && !TextUtils.isEmpty(response.body().getResult().getData().getHeadimgurl())) {
                        GlideUtils.loadImgWithGlide(response.body().getResult().getData().getHeadimgurl(), headpic);
                        SharedPreferencesUtils.putString(Constants.HEADIMAGEURL, response.body().getResult().getData().getHeadimgurl());
                    }
                    if (response.body().getResult().getData().getUsername() != null && !TextUtils.isEmpty(response.body().getResult().getData().getUsername())) {
                        infoNikename.setText(response.body().getResult().getData().getUsername());
                    }
                    withdrawal.setText("￥" + response.body().getResult().getData().getMoney());
                    FrozenBlance.setText("￥" + response.body().getResult().getData().getLock_money());

                } else if (response.body().getCode().equals("21818")) {
                    SharedPreferencesUtils.putString(Constants.TOKEN, "");
                    ToastUtil.show("登录失效,请重新登录!", 200);
                    JumpActivityUtil.launchActivity(mContext, LoginActivity.class);
                }
            }

            @Override
            public void onFailure(Call<UserInfosEntity> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.My_release, R.id.about_me, R.id.transaction, R.id.My_invitation, R.id.Binding_Mobile, R.id.Contact_Customer, R.id.Withdrawable, R.id.Frozen, R.id.headpic})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.My_release:  //我的发布
                JumpActivityUtil.launchActivity(mContext, MyReleaseActivity.class);
                break;
            case R.id.My_invitation: //我的邀请
                JumpActivityUtil.launchActivity(mContext, MyInvitationActivity.class);
                break;
            case R.id.Binding_Mobile: //绑定手机号

                break;
            case R.id.Contact_Customer: //联系客服
                DialogUtils.getInstance().showSimpleDialog(mContext, R.layout.dialog_backup, R.style.dialog, (contentView, utils) -> {
                    utils.setCancelable(false);
                    Button cofim = contentView.findViewById(R.id.submit);
                    Button exit = contentView.findViewById(R.id.cancel);
                    TextView content=contentView.findViewById(R.id.content);
                    ApiInterface.ApiFactory.createApi().get_words_list().enqueue(new Callback<WordsListEntity>() {
                        @Override
                        public void onResponse(Call<WordsListEntity> call, Response<WordsListEntity> response) {
                            if (response.body().getCode() == 200) {

                                content.setText(response.body().getResult().getContact_word());
                            }
                        }

                        @Override
                        public void onFailure(Call<WordsListEntity> call, Throwable t) {

                        }
                    });

                    exit.setOnClickListener(v -> {
                        utils.close();
                    });

                    cofim.setOnClickListener(v -> {
                        utils.close();
                    });
                });

                break;
            case R.id.Withdrawable: //可提现余额
                JumpActivityUtil.launchActivity(mContext, MyWalletActivity.class);
                break;
            case R.id.Frozen://冻结余额
                JumpActivityUtil.launchActivity(mContext, MyWalletActivity.class);
                break;


            case R.id.headpic: //设置
                JumpActivityUtil.launchActivity(mContext, SettingActivity.class);
                break;
            case R.id.transaction: //交易明细
                JumpActivityUtil.launchActivity(mContext, TransactionDetailsActivity.class);
                break;
            case R.id.about_me: //关于我们
                JumpActivityUtil.launchActivity(mContext, AboutMeActivity.class);
                break;
        }
    }

    @Override
    public <T> void update(int what, T t) {
        if (what == Constants.HEADIMGURL) {
//             String result=(String)t;
//             GlideUtils.loadImgWithGlide(result, headpic);
            get_user_info();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoticeObserver.getInstance().removeObserver(this);
    }
}
