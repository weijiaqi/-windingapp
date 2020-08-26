package app.winding.com.windingapp.pager;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.LoginActivity;
import app.winding.com.windingapp.adapter.PrepaidRefillAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.entity.CheckStatusEntity;
import app.winding.com.windingapp.entity.FareEntity;
import app.winding.com.windingapp.entity.FareNewEntity;
import app.winding.com.windingapp.entity.FareOrderEntity;
import app.winding.com.windingapp.entity.PayOrderEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.CommonUtils;
import app.winding.com.windingapp.util.IsInstallWeChatOrAliPay;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 话费充值
 */

public class PrepaidRefillPager extends BasePager implements NoticeObserver.Observer {
    RecyclerView RcyTelephoneRecharge;
    PrepaidRefillAdapter prepaidRefillAdapter;
    EditText phone;
    IWXAPI api;
    LoadingDialog loadingDialog;
    TextView content;
    public PrepaidRefillPager(Context context) {
        super(context);
    }

    @Override
    public View initView(LayoutInflater layoutInflater) {
        NoticeObserver.getInstance().addObserver(this);
        View view = layoutInflater.inflate(R.layout.prepaid_refill_pager, null);
        RcyTelephoneRecharge = view.findViewById(R.id.RcyTelephoneRecharge);
        content= view.findViewById(R.id.content);
        phone = view.findViewById(R.id.phone);
        RcyTelephoneRecharge.setLayoutManager(new GridLayoutManager(context, 3));

        fare();
        get_words_list();
        return view;
    }

    @Override
    public <DATA> void initData(int type, DATA data) {

    }

    public void get_words_list() {
        ApiInterface.ApiFactory.createApi().get_words_list().enqueue(new Callback<WordsListEntity>() {
            @Override
            public void onResponse(Call<WordsListEntity> call, Response<WordsListEntity> response) {
                if (response.body().getCode() == 200) {
                    content.setText(response.body().getResult().getFare_rechage().toString());
                }
            }

            @Override
            public void onFailure(Call<WordsListEntity> call, Throwable t) {

            }
        });
    }


    public void fare() {
        ApiInterface.ApiFactory.createApi().fare().enqueue(new Callback<FareEntity>() {
            @Override
            public void onResponse(Call<FareEntity> call, Response<FareEntity> response) {
                if (response.body().getCode() == 200) {
                    List<FareEntity.ResultBean> resultBeanList = response.body().getResult();
                    prepaidRefillAdapter = new PrepaidRefillAdapter(context, resultBeanList);
                    RcyTelephoneRecharge.setAdapter(prepaidRefillAdapter);
                    prepaidRefillAdapter.setOnItemClickListener((view, position) -> {
                        if (phone.getText().toString().equals("") || TextUtils.isEmpty(phone.getText().toString())) {
                            ToastUtil.show("请输入要充值的手机号!", 200);
                            return;
                        }
                        if (!CommonUtils.checkMobile(phone.getText().toString())) {
                            ToastUtil.showShort(context.getString(R.string.please_correct_phone));
                            return;
                        }

                        loadingDialog = new LoadingDialog(context, context.getString(R.string.loading));
                        loadingDialog.show();
                        fare_order_add(resultBeanList.get(position).getId(), "HF", phone.getText().toString());




                    });
                }else if (response.body().getCode() == 21818) {
                SharedPreferencesUtils.putString(Constants.TOKEN,"");
                ToastUtil.show("登录失效,请重新登录!",200);
                JumpActivityUtil.launchActivity(context, LoginActivity.class);
            }
            }

            @Override
            public void onFailure(Call<FareEntity> call, Throwable t) {

            }
        });
    }

    @Override
    public <T> void update(int what, T t) {
        if (what == Constants.PHONERecharge) {
            get_fare_new();
        }
    }


    //查看是否有历史交易记录
    public void get_fare_new() {
        ApiInterface.ApiFactory.createApi().get_fare_new().enqueue(new Callback<FareNewEntity>() {
            @Override
            public void onResponse(Call<FareNewEntity> call, Response<FareNewEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult() != null && !TextUtils.isEmpty(response.body().getResult())) {
                        phone.setText(response.body().getResult());
                        Editable etext = phone.getText();
                        Selection.setSelection(etext, etext.length());
                    }
                }
            }

            @Override
            public void onFailure(Call<FareNewEntity> call, Throwable t) {

            }
        });
    }


    //话费充值下单
    public void fare_order_add(int goods_id, String prefix, String mobile) {
        String token=SharedPreferencesUtils.getString(Constants.TOKEN,"");
        ApiInterface.ApiFactory.createApi().fare_order_add(goods_id, prefix, mobile).enqueue(new Callback<FareOrderEntity>() {
            @Override
            public void onResponse(Call<FareOrderEntity> call, Response<FareOrderEntity> response) {
                if (response.body().getCode() == 200) {
                     String order_id=response.body().getResult().getOrder_id();
                    ApiInterface.ApiFactory.createApi().check_status(order_id).enqueue(new Callback<CheckStatusEntity>() {
                        @Override
                        public void onResponse(Call<CheckStatusEntity> call, Response<CheckStatusEntity> response2) {
                            if (response2.body().getResult().equals("2")) {
                                ToastUtil.show("该订单已完成支付,请勿重复支付!",200);
                                loadingDialog.dismiss();
                                return;
                            }else {
                                pay_order(order_id, response.body().getResult().getPay_price(), 1);
                            }
                        }

                        @Override
                        public void onFailure(Call<CheckStatusEntity> call, Throwable t) {

                        }
                    });




                }
            }

            @Override
            public void onFailure(Call<FareOrderEntity> call, Throwable t) {
                loadingDialog.dismiss();
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
                        SharedPreferencesUtils.putInt(Constants.PAYTYPE,1);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoticeObserver.getInstance().removeObserver(this);
    }
}
