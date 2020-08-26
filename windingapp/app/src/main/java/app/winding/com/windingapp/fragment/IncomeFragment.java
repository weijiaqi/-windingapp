package app.winding.com.windingapp.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.MyInvitationActivity;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseFragment;
import app.winding.com.windingapp.entity.GetInfoEntity;
import app.winding.com.windingapp.entity.QrcodeEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import app.winding.com.windingapp.popuwindow.InvitationPopwindow;
import app.winding.com.windingapp.util.AnimationUtils;
import app.winding.com.windingapp.util.GlideUtils;
import app.winding.com.windingapp.util.ShareUtils;
import app.winding.com.windingapp.util.SystemUtils;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 我的邀请
 */

public class IncomeFragment extends BaseFragment {
    @Bind(R.id.code)
    ImageView code;
    @Bind(R.id.Invitation_code)
    TextView InvitationCode;
    @Bind(R.id.copycode)
    TextView copycode;
     @Bind(R.id.content)
     TextView content;
     @Bind(R.id.sharefriendquan)
    RelativeLayout sharefriendquan;
     @Bind(R.id.sharefriend)
     RelativeLayout sharefriend;
    @Bind(R.id.ll_income)
    LinearLayout ll_income;
    private InvitationPopwindow invitationPopwindow;
    private String title, desc, link, imgurl;
    @Override
    protected int getLayoutResource() {
        return R.layout.income_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) throws IOException {
        user_qrcode();
        get_words_list();
        get_info();
        sharefriendquan.setOnClickListener(v->{
            invitationPopwindow = new InvitationPopwindow(getActivity(), getActivity(), itemsOnClick);
            AnimationUtils.darkenBackground(getActivity(), 0.7f);
            if (!invitationPopwindow.isShowing()) {
                invitationPopwindow.showAtLocation(ll_income, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            }
        });
        sharefriend.setOnClickListener(v->{
            invitationPopwindow = new InvitationPopwindow(getActivity(), getActivity(), itemsOnClick);
            AnimationUtils.darkenBackground(getActivity(), 0.7f);
            if (!invitationPopwindow.isShowing()) {
                invitationPopwindow.showAtLocation(ll_income, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            }
        });
    }


    public void get_words_list() {
        ApiInterface.ApiFactory.createApi().get_words_list().enqueue(new Callback<WordsListEntity>() {
            @Override
            public void onResponse(Call<WordsListEntity> call, Response<WordsListEntity> response) {
                if (response.body().getCode() == 200) {
                    content.setText(response.body().getResult().getInvite_word().toString());
                }
            }
            @Override
            public void onFailure(Call<WordsListEntity> call, Throwable t) {

            }
        });
    }

    public void user_qrcode() {
        ApiInterface.ApiFactory.createApi().user_qrcode().enqueue(new Callback<QrcodeEntity>() {
            @Override
            public void onResponse(Call<QrcodeEntity> call, Response<QrcodeEntity> response) {
                if (response.body().getCode().equals("00000")) {
                    GlideUtils.loadImgWithGlide( response.body().getResult().getData().getQr_code(), code);
                    InvitationCode.setText("邀请码：" + response.body().getResult().getData().getCode());
                    copycode.setOnClickListener(v->{
                        //获取剪贴板管理器：
                        ClipboardManager cm = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                        // 创建普通字符型ClipData
                        ClipData mClipData = ClipData.newPlainText("Label", response.body().getResult().getData().getCode());
                        // 将ClipData内容放到系统剪贴板里。
                        cm.setPrimaryClip(mClipData);
                        ToastUtil.show("复制成功", 25);
                    });
                }
            }

            @Override
            public void onFailure(Call<QrcodeEntity> call, Throwable t) {

            }
        });
    }



    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_invite_WX_chat:
                    if (SystemUtils.isWeixinAvilible(getActivity().getApplicationContext())) {
                        ShareUtils.shareWeb(getActivity(), link, title
                                , desc, imgurl, R.mipmap.logo, SHARE_MEDIA.WEIXIN
                        );
                    } else {
                        ToastUtil.show("未安装微信,请进行安装后再进行分享!", 200);
                    }

                    invitationPopwindow.dismiss();
                    break;
                case R.id.ll_invite_WX_friend:
                    if (SystemUtils.isWeixinAvilible(getActivity().getApplicationContext())) {
                        ShareUtils.shareWeb(getActivity(), link, title
                                , desc, imgurl, R.mipmap.logo, SHARE_MEDIA.WEIXIN_CIRCLE
                        );
                    } else {
                        ToastUtil.show("未安装微信,请进行安装后再进行分享!", 200);
                    }

                    invitationPopwindow.dismiss();
                    break;
                case R.id.ll_invite_qq:
                    if (SystemUtils.isQQClientAvailable(getActivity().getApplicationContext())) {
                        ShareUtils.shareWeb(getActivity(), link, title
                                , desc, imgurl, R.mipmap.logo, SHARE_MEDIA.QQ
                        );
                    } else {
                        ToastUtil.show("未安装QQ,请进行安装后再进行分享!", 200);
                    }

                    invitationPopwindow.dismiss();
                    break;
                case R.id.ll_invite_wb:

                    ShareUtils.shareWeb(getActivity(), link, title
                            , desc, imgurl, R.mipmap.logo, SHARE_MEDIA.SINA
                    );
                    invitationPopwindow.dismiss();
                    break;
                case R.id.ll_invite_qq_space:
                    if (SystemUtils.isQQClientAvailable(getActivity().getApplicationContext())) {
                        ShareUtils.shareWeb(getActivity(), link, title
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
