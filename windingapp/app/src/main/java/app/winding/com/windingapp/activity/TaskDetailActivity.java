package app.winding.com.windingapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.util.List;

import app.winding.com.windingapp.MainActivity;
import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.api.Defaultcontent;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.CheckStatusEntity;
import app.winding.com.windingapp.entity.FareRobOrdersEntity;
import app.winding.com.windingapp.entity.GetInfoEntity;
import app.winding.com.windingapp.entity.GoodsDetailsEntity;
import app.winding.com.windingapp.entity.OrderAddEntity;
import app.winding.com.windingapp.entity.OrdersAddEntity;
import app.winding.com.windingapp.entity.PayOrderEntity;
import app.winding.com.windingapp.entity.RobOrderEntity;
import app.winding.com.windingapp.entity.UpdateTemporaryEntity;
import app.winding.com.windingapp.fragment.MarginBuyingFragment;
import app.winding.com.windingapp.fragment.TaskFragment;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.popuwindow.InvitationPopwindow;
import app.winding.com.windingapp.ui.GenerateOrderActivity;
import app.winding.com.windingapp.ui.PurchaseFilmActivity;
import app.winding.com.windingapp.util.AnimationUtils;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.GlideUtils;
import app.winding.com.windingapp.util.IsInstallWeChatOrAliPay;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.ShareUtils;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.SystemUtils;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;

    @Bind(R.id.pic)
    ImageView pic;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.city)
    TextView city;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.titlename)
    TextView titlename;
    @Bind(R.id.titledetail)
    TextView titledetail;

    @Bind(R.id.create_time)
    TextView createTime;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.lladdress)
    LinearLayout lladdress;
    @Bind(R.id.Order_for_him)
    TextView Order_for_him;
    @Bind(R.id.ll_income)
    LinearLayout ll_income;
    IWXAPI api;
    LoadingDialog loadingDialog;
    public static TaskDetailActivity instance;
    String order_id;
    int status = 1;



    @Override
    protected int getLayoutResource() {
        return R.layout.activity_task_detail;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        instance = this;
        ivBack.setOnClickListener(v -> {
            status = 2;
            finish();
        });
        goods_details(getIntent().getExtras().getInt("id"));
    }



    public void goods_details(int id) {
        ApiInterface.ApiFactory.createApi().goods_details(id).enqueue(new Callback<GoodsDetailsEntity>() {
            @Override
            public void onResponse(Call<GoodsDetailsEntity> call, Response<GoodsDetailsEntity> response) {
                if (status == 1) {
                    if (response.body().getCode() == 200) {
                        GoodsDetailsEntity.ResultBean resultBeanList = response.body().getResult();
                        if (resultBeanList.getUser_info().getHeadimgurl() != null && !TextUtils.isEmpty(resultBeanList.getUser_info().getHeadimgurl())) {
                            GlideUtils.loadImgWithGlide(resultBeanList.getUser_info().getHeadimgurl(), pic);
                        }
                        name.setText(resultBeanList.getUser_info().getUsername());
                        if (resultBeanList.getCity_name() != null && !TextUtils.isEmpty(resultBeanList.getCity_name())) {
                            city.setText("发布于 " + resultBeanList.getCity_name());
                        }
                        price.setText("￥" + resultBeanList.getPrice());
                        titlename.setText(resultBeanList.getGood_name());
                        titledetail.setText(resultBeanList.getMemo());

                        createTime.setText(resultBeanList.getCreate_time());
                        if (resultBeanList.getCity_name() != null && !TextUtils.isEmpty(resultBeanList.getCity_name())) {
                            address.setText(resultBeanList.getCity_name() + " " + resultBeanList.getBrief());
                        } else {
                            lladdress.setVisibility(View.GONE);
                        }
                        switch (resultBeanList.getClassify_id()) {
                            case 4:
                                Order_for_him.setText("支付");
                                break;
                            case 5:
                                Order_for_him.setText("抢单");
                                break;
                            case 6:
                                if (resultBeanList.getIs_sell() == 1) {
                                    Order_for_him.setText("抢单");
                                } else {
                                    Order_for_him.setText("生成订单");
                                }
                                break;
                        }
                        Order_for_him.setOnClickListener(v -> {
                            switch (Order_for_him.getText().toString()) {
                                case "支付":
                                    ApiInterface.ApiFactory.createApi().order_add(id, "", resultBeanList.getPrefix()).enqueue(new Callback<OrderAddEntity>() {
                                        @Override
                                        public void onResponse(Call<OrderAddEntity> call, Response<OrderAddEntity> response) {
                                            if (response.body().getCode() == 200) {
                                                order_id = response.body().getResult().getOrder_id();
                                                ApiInterface.ApiFactory.createApi().check_status(order_id).enqueue(new Callback<CheckStatusEntity>() {
                                                    @Override
                                                    public void onResponse(Call<CheckStatusEntity> call, Response<CheckStatusEntity> response2) {
                                                        if (response2.body().getResult().equals("2")) {
                                                            ToastUtil.show("该订单已完成支付,请勿重复支付!", 200);
                                                            return;
                                                        } else {
                                                            loadingDialog = new LoadingDialog(context, context.getString(R.string.loading));
                                                            loadingDialog.show();
                                                            pay_order(order_id, response.body().getResult().getPay_price(), 1);
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<CheckStatusEntity> call, Throwable t) {

                                                    }
                                                });

                                            } else {
                                                ToastUtil.show(response.body().getMessage().toString(), 200);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<OrderAddEntity> call, Throwable t) {

                                        }
                                    });
                                    break;
                                case "抢单":
                                    rob_order(getIntent().getExtras().getInt("id"), 0, 0, 0);
                                    break;
                                case "生成订单":

                                    ApiInterface.ApiFactory.createApi().order_add(resultBeanList.getId(), "", "DY").enqueue(new Callback<OrderAddEntity>() {
                                        @Override
                                        public void onResponse(Call<OrderAddEntity> call, Response<OrderAddEntity> response) {
                                            if (response.body().getCode() == 200) {
                                                NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                                                NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                                                Bundle bundle = new Bundle();
                                                bundle.putString("id", response.body().getResult().getId() + "");
                                                bundle.putString("order_id", response.body().getResult().getOrder_id());
                                                bundle.putInt("cityid", response.body().getResult().getGoods().getCity());
                                                bundle.putString("good_name", resultBeanList.getGood_name());
                                                bundle.putString("memo", resultBeanList.getMemo());
                                                bundle.putString("city_name", resultBeanList.getCity_name());
                                                bundle.putString("brief", resultBeanList.getBrief());
                                                bundle.putString("payprice", resultBeanList.getPrice());
                                                JumpActivityUtil.launchActivity(TaskDetailActivity.this, GenerateOrderActivity.class, bundle);
                                                finish();
                                            } else {
                                                ToastUtil.show(response.body().getMessage().toString(), 200);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<OrderAddEntity> call, Throwable t) {

                                        }
                                    });

                                    break;
                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<GoodsDetailsEntity> call, Throwable t) {

            }
        });
    }

    public void pay_order(String order_id, String money, int pay_type) {
        ApiInterface.ApiFactory.createApi().pay_order(order_id, money, pay_type).enqueue(new Callback<PayOrderEntity>() {
            @Override
            public void onResponse(Call<PayOrderEntity> call, Response<PayOrderEntity> response) {
                if (response.body().getCode() == 200) {
                    loadingDialog.dismiss();
                    if (IsInstallWeChatOrAliPay.isWeixinAvilible(context)) {
                        SharedPreferencesUtils.putInt(Constants.PAYTYPE, 4);
                        String AppId = response.body().getResult().getAppid();
                        String PartnerId = response.body().getResult().getPartnerid();
                        String NonceStr = response.body().getResult().getNoncestr();
                        String Sign = response.body().getResult().getSign();
                        String PrepayId = response.body().getResult().getPrepayid();
                        String TimeStamp = String.valueOf(response.body().getResult().getTimestamp());
                        api = WXAPIFactory.createWXAPI(context, "wx0a1222d5324260b1", false);
                        PayReq req = new PayReq();
                        req.appId = AppId;//你的微信appid
                        req.partnerId = PartnerId;//商户号
                        req.prepayId = PrepayId;//预支付交易会话ID
                        req.nonceStr = NonceStr;//随机字符串
                        req.timeStamp = TimeStamp;//时间戳
                        req.packageValue = "Sign=WXPay";
                        req.sign = Sign;//签名
                        api.sendReq(req);
                    } else {
                        ToastUtil.show("未安装微信客户端!", 200);
                    }
                }
            }

            @Override
            public void onFailure(Call<PayOrderEntity> call, Throwable t) {
                loadingDialog.dismiss();
            }
        });
    }

    //抢单
    public void rob_order(int goods_id, int config_id, int type_id, int classify_id) {
        String token = SharedPreferencesUtils.getString(Constants.TOKEN, "");
        ApiInterface.ApiFactory.createApi().rob_order(goods_id, config_id, type_id, classify_id).enqueue(new Callback<RobOrderEntity>() {
            @Override
            public void onResponse(Call<RobOrderEntity> call, Response<RobOrderEntity> response) {
                if (response.body().getCode() == 200) {
                    DialogUtils.getInstance().showSimpleDialog(context, R.layout.dialog_generateorder, R.style.dialog, (contentView, utils) -> {
                        utils.setCancelable(false);
                        TextView btn_click = contentView.findViewById(R.id.btn_click);
                        btn_click.setOnClickListener(v -> {
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                            MainActivity.instance.getNavigationBar().selectTab(2);
                            ((MarginBuyingFragment) (MainActivity.instance.getNavigationBar().getAdapter().getItem(2))).showToast(4);
                            utils.close();
                            finish();
                        });
                    });

                } else if (response.body().getCode() == 500) {
                    ToastUtil.show(response.body().getMessage().toString(), 200);
                }
            }

            @Override
            public void onFailure(Call<RobOrderEntity> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        status = 2;
    }
}
