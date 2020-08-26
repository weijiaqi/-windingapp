package app.winding.com.windingapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.CheckStatusEntity;
import app.winding.com.windingapp.entity.PayOrderEntity;
import app.winding.com.windingapp.entity.TemporaryEntity;
import app.winding.com.windingapp.entity.UpdateTemporaryEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.IsInstallWeChatOrAliPay;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.code.PickActivity;
import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMoviewActivity extends BaseActivity {
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.moviename)
    EditText moviename;
    @Bind(R.id.city)
    TextView city;
    @Bind(R.id.next)
    ImageView next;
    @Bind(R.id.writemoview)
    EditText writemoview;
    @Bind(R.id.rlcity)
    RelativeLayout rlcity;
    @Bind(R.id.remark)
    TextView remark;
    @Bind(R.id.detail)
    EditText detail;

    String order_id;
    String payprice;
    LoadingDialog loadingDialog;
    IWXAPI api;
    public static UpdateMoviewActivity instance;
    int id;
    int ids;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_generate_order;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        instance = this;
        ivBack.setOnClickListener(v -> {
            finish();
        });
        payprice = getIntent().getExtras().getString("payprice");
        tvTitles.setText("确认信息");
        order_id = getIntent().getExtras().getString("order_id");
        id=Integer.parseInt(getIntent().getExtras().getString("id")) ;
        get_temporary(order_id);

    }


    public void get_temporary(String order_id) {
        ApiInterface.ApiFactory.createApi().get_temporary(order_id).enqueue(new Callback<TemporaryEntity>() {
            @Override
            public void onResponse(Call<TemporaryEntity> call, Response<TemporaryEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult().getName()!=null &&!TextUtils.isEmpty(response.body().getResult().getName())){
                        ids = 1;
                        moviename.setText(response.body().getResult().getName());
                        city.setText(response.body().getResult().getCity_name());
                        writemoview.setText(response.body().getResult().getCname());
                        detail.setText(response.body().getResult().getMemo());
                        remark.setText(response.body().getResult().getRemark());
                    }else {
                        ids = 2;
                        moviename.setText(getIntent().getExtras().getString("good_name"));
                        city.setText(getIntent().getExtras().getString("city_name"));
                        writemoview.setText(getIntent().getExtras().getString("brief"));
                        remark.setText(getIntent().getExtras().getString("memo"));
                    }
                } else if (response.body().getCode() == 500) {
                    ids = 2;
                    moviename.setText(getIntent().getExtras().getString("good_name"));
                    city.setText(getIntent().getExtras().getString("city_name"));
                    writemoview.setText(getIntent().getExtras().getString("brief"));
                    remark.setText(getIntent().getExtras().getString("memo"));
                }
            }

            @Override
            public void onFailure(Call<TemporaryEntity> call, Throwable t) {

            }
        });
    }


    @OnClick({R.id.cofimOrder, R.id.rlcity})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.cofimOrder:
                if (moviename.getText().toString().trim().equals("") || TextUtils.isEmpty(moviename.getText().toString().trim())) {
                    ToastUtil.show("请输入电影名称!", 200);
                    return;
                }
                if (city.getText().toString().trim().equals("") || TextUtils.isEmpty(city.getText().toString().trim())) {
                    ToastUtil.show("请选择城市!", 200);
                    return;
                }
                if (writemoview.getText().toString().trim().equals("") || TextUtils.isEmpty(writemoview.getText().toString().trim())) {
                    ToastUtil.show("输入影院名称!", 200);
                    return;
                }
                if (remark.getText().toString().trim().equals("") || TextUtils.isEmpty(remark.getText().toString().trim())) {
                    ToastUtil.show("请填写商家备注!", 200);
                    return;
                }
                if (detail.getText().toString().trim().equals("") || TextUtils.isEmpty(detail.getText().toString().trim())) {
                    ToastUtil.show("输入所需电影票详情!", 200);
                    return;
                }
                if (ids == 1) {
                    update_temporary(moviename.getText().toString(), writemoview.getText().toString(), id, remark.getText().toString(), getIntent().getExtras().getString("order_id"));
                }else {
                    add_temporary(moviename.getText().toString(), writemoview.getText().toString(), id, remark.getText().toString(), getIntent().getExtras().getString("order_id"));
                }

                break;
            case R.id.rlcity:

                startActivityForResult(new Intent(getApplicationContext(), PickActivity.class), 111);
                break;
        }
    }

    public void update_temporary(String name, String cname, int city, String memo, String order_ids) {
        ApiInterface.ApiFactory.createApi().update_temporary(name, cname, city, memo, order_ids).enqueue(new Callback<UpdateTemporaryEntity>() {
            @Override
            public void onResponse(Call<UpdateTemporaryEntity> call, Response<UpdateTemporaryEntity> response) {
                if (response.body().getCode() == 200) {
                    ApiInterface.ApiFactory.createApi().check_status(order_ids).enqueue(new Callback<CheckStatusEntity>() {
                        @Override
                        public void onResponse(Call<CheckStatusEntity> call, Response<CheckStatusEntity> response2) {
                            if (response2.body().getResult().equals("2")) {
                                ToastUtil.show("该订单已完成支付,请勿重复支付!", 200);
                                return;
                            } else {
                                loadingDialog = new LoadingDialog(context, context.getString(R.string.loading));
                                loadingDialog.show();
                                pay_order(order_ids, payprice, 1);
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
            public void onFailure(Call<UpdateTemporaryEntity> call, Throwable t) {

            }
        });

    }

    public void add_temporary(String name, String cname, int city, String memo, String order_id) {
        ApiInterface.ApiFactory.createApi().add_temporary(name, cname, city, memo, order_id).enqueue(new Callback<UpdateTemporaryEntity>() {
            @Override
            public void onResponse(Call<UpdateTemporaryEntity> call, Response<UpdateTemporaryEntity> response) {
                if (response.body().getCode() == 200) {
                    ApiInterface.ApiFactory.createApi().check_status(order_id).enqueue(new Callback<CheckStatusEntity>() {
                        @Override
                        public void onResponse(Call<CheckStatusEntity> call, Response<CheckStatusEntity> response2) {
                            if (response2.body().getResult().equals("2")) {
                                ToastUtil.show("该订单已完成支付,请勿重复支付!", 200);
                                return;
                            } else {
                                loadingDialog = new LoadingDialog(context, context.getString(R.string.loading));
                                loadingDialog.show();
                                pay_order(order_id, payprice, 1);
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
            public void onFailure(Call<UpdateTemporaryEntity> call, Throwable t) {

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
                        SharedPreferencesUtils.putInt(Constants.PAYTYPE, 2);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            city.setText(data.getStringExtra("name"));
            id = Integer.parseInt(data.getStringExtra("id"));

        }

    }

}
