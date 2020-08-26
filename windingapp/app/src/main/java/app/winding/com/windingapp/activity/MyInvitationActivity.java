package app.winding.com.windingapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.GetInfoEntity;
import app.winding.com.windingapp.fragment.ButtonFragment;
import app.winding.com.windingapp.fragment.IncomeFragment;
import app.winding.com.windingapp.fragment.PayFragment;
import app.winding.com.windingapp.popuwindow.InvitationPopwindow;
import app.winding.com.windingapp.util.AnimationUtils;
import app.winding.com.windingapp.util.ShareUtils;
import app.winding.com.windingapp.util.SystemUtils;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//我的邀请
public class MyInvitationActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    FragmentManager fm;
    @Bind(R.id.button)
    TextView button;
    @Bind(R.id.pay)
    TextView pay;
    @Bind(R.id.income)
    TextView income;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.ll_income)
    LinearLayout ll_income;

    private InvitationPopwindow invitationPopwindow;
    private IncomeFragment incomeFragment;
    private PayFragment payFragment;
    private ButtonFragment buttonFragment;
    private String title, desc, link, imgurl;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_my_invitation;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("我的邀请");

        ivRight.setImageResource(R.mipmap.share);
        ivRight.setOnClickListener(v -> {
            invitationPopwindow = new InvitationPopwindow(this, this, itemsOnClick);
            AnimationUtils.darkenBackground(this, 0.7f);
            if (!invitationPopwindow.isShowing()) {
                invitationPopwindow.showAtLocation(ll_income, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            }
        });
        get_info();
        fm = getSupportFragmentManager();
        button.setBackground(getResources().getDrawable(R.mipmap.whiteban));
        button.setTextColor(getResources().getColor(R.color.color_24));
        setTabSelection(0);
    }


    @OnClick({R.id.button, R.id.pay, R.id.income})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.button:
                setTabSelection(0);
                button.setBackground(getResources().getDrawable(R.mipmap.whiteban));
                button.setTextColor(getResources().getColor(R.color.color_24));
                pay.setBackground(getResources().getDrawable(R.mipmap.yellban));
                pay.setTextColor(getResources().getColor(R.color.color_e7));
                income.setBackground(getResources().getDrawable(R.mipmap.yellban));
                income.setTextColor(getResources().getColor(R.color.color_e7));
                break;
            case R.id.pay:
                button.setBackground(getResources().getDrawable(R.mipmap.yellban));
                button.setTextColor(getResources().getColor(R.color.color_e7));
                pay.setBackground(getResources().getDrawable(R.mipmap.whiteban));
                pay.setTextColor(getResources().getColor(R.color.color_24));
                income.setBackground(getResources().getDrawable(R.mipmap.yellban));
                income.setTextColor(getResources().getColor(R.color.color_e7));
                setTabSelection(1);
                break;
            case R.id.income:
                button.setBackground(getResources().getDrawable(R.mipmap.yellban));
                button.setTextColor(getResources().getColor(R.color.color_e7));
                pay.setBackground(getResources().getDrawable(R.mipmap.yellban));
                pay.setTextColor(getResources().getColor(R.color.color_e7));
                income.setBackground(getResources().getDrawable(R.mipmap.whiteban));
                income.setTextColor(getResources().getColor(R.color.color_24));
                setTabSelection(2);
                break;


        }
    }

    private void setTabSelection(int index) {
        FragmentTransaction ft = fm.beginTransaction();
        hideFragment(ft);
        switch (index) {
            case 0:
                if (incomeFragment == null) {
                    incomeFragment = new IncomeFragment();
                    ft.add(R.id.fl, incomeFragment);
                } else {
                    ft.show(incomeFragment);
                }

                break;

            case 1:
                if (payFragment == null) {
                    payFragment = new PayFragment();
                    ft.add(R.id.fl, payFragment);
                }
                ft.show(payFragment);
                break;
            case 2:
                if (buttonFragment == null) {
                    buttonFragment = new ButtonFragment();
                    ft.add(R.id.fl, buttonFragment);
                }
                ft.show(buttonFragment);
                break;
        }
        ft.commit();
    }

    //用于隐藏fragment
    private void hideFragment(FragmentTransaction ft) {
        if (incomeFragment != null) {
            ft.hide(incomeFragment);
        }
        if (payFragment != null) {
            ft.hide(payFragment);
        }
        if (buttonFragment != null) {
            ft.hide(buttonFragment);
        }
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_invite_WX_chat:
                    if (SystemUtils.isWeixinAvilible(getApplicationContext())) {
                        ShareUtils.shareWeb(MyInvitationActivity.this, link, title
                                , desc, imgurl, R.mipmap.logo, SHARE_MEDIA.WEIXIN
                        );
                    } else {
                        ToastUtil.show("未安装微信,请进行安装后再进行分享!", 200);
                    }

                    invitationPopwindow.dismiss();
                    break;
                case R.id.ll_invite_WX_friend:
                    if (SystemUtils.isWeixinAvilible(getApplicationContext())) {
                        ShareUtils.shareWeb(MyInvitationActivity.this, link, title
                                , desc, imgurl, R.mipmap.logo, SHARE_MEDIA.WEIXIN_CIRCLE
                        );
                    } else {
                        ToastUtil.show("未安装微信,请进行安装后再进行分享!", 200);
                    }

                    invitationPopwindow.dismiss();
                    break;
                case R.id.ll_invite_qq:
                    if (SystemUtils.isQQClientAvailable(getApplicationContext())) {
                        ShareUtils.shareWeb(MyInvitationActivity.this, link, title
                                , desc, imgurl, R.mipmap.logo, SHARE_MEDIA.QQ
                        );
                    } else {
                        ToastUtil.show("未安装QQ,请进行安装后再进行分享!", 200);
                    }

                    invitationPopwindow.dismiss();
                    break;
                case R.id.ll_invite_wb:

                    ShareUtils.shareWeb(MyInvitationActivity.this, link, title
                            , desc, imgurl, R.mipmap.logo, SHARE_MEDIA.SINA
                    );
                    invitationPopwindow.dismiss();
                    break;
                case R.id.ll_invite_qq_space:
                    if (SystemUtils.isQQClientAvailable(getApplicationContext())) {
                        ShareUtils.shareWeb(MyInvitationActivity.this, link, title
                                , desc, imgurl, R.mipmap.logo, SHARE_MEDIA.QZONE
                        );
                    } else {
                        ToastUtil.show("未安装QQ,请进行安装后再进行分享!", 200);
                    }
                    invitationPopwindow.dismiss();
                    break;
                case R.id.ll_off:
                    invitationPopwindow.dismiss();
                    break;
            }
        }
    };


    public void get_info() {
        ApiInterface.ApiFactory.createApi().get_info().enqueue(new Callback<GetInfoEntity>() {
            @Override
            public void onResponse(Call<GetInfoEntity> call, Response<GetInfoEntity> response) {
                if (response.body().getCode() == 200) {
                    title = response.body().getResult().getTitle();
                    desc = response.body().getResult().getDesc();
                    link = response.body().getResult().getLink();
                    imgurl = response.body().getResult().getImgUrl();
                }
            }

            @Override
            public void onFailure(Call<GetInfoEntity> call, Throwable t) {

            }
        });
    }
}
