package app.winding.com.windingapp.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.ElectricAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.entity.CheckStatusEntity;
import app.winding.com.windingapp.entity.ElectricEntity;
import app.winding.com.windingapp.entity.ElectricOrderEntity;
import app.winding.com.windingapp.entity.ElectricOrdersEntity;
import app.winding.com.windingapp.entity.FareNewEntity;
import app.winding.com.windingapp.entity.OrderAddEntity;
import app.winding.com.windingapp.entity.PayOrderEntity;
import app.winding.com.windingapp.entity.UserelectricorderEntity;
import app.winding.com.windingapp.entity.WordsListEntity;
import app.winding.com.windingapp.model.Constants;

import app.winding.com.windingapp.util.DialogUtils;
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
 * 买电费
 */

public class ElectricityPager extends BasePager implements NoticeObserver.Observer {
    TextView city;
    RecyclerView rcyElectricity;
    TextView HowToKnowTheAccount;
    EditText Enter_number;
    int id;
    private Handler handler = new Handler();
    LoadingDialog loadingDialog;
    TextView content;
    IWXAPI api;

    public ElectricityPager(Context context) {
        super(context);
    }

    @Override
    public View initView(LayoutInflater layoutInflater) {
        NoticeObserver.getInstance().addObserver(this);
        View view = layoutInflater.inflate(R.layout.electricity_pager, null);
        RelativeLayout selectcity = view.findViewById(R.id.selectcity);
        rcyElectricity = view.findViewById(R.id.rcyElectricity);
        HowToKnowTheAccount = view.findViewById(R.id.HowToKnowTheAccount);
        Enter_number = view.findViewById(R.id.Enter_number);
        content=view.findViewById(R.id.content);
        rcyElectricity.setLayoutManager(new GridLayoutManager(context, 3));
        get_electric_new();
        electric();
        get_words_list();
        city = view.findViewById(R.id.city);
        selectcity.setOnClickListener(v -> {
            NoticeObserver.getInstance().notifyObservers(Constants.SELECTCITY);

        });
        HowToKnowTheAccount.setOnClickListener(v -> {
            DialogUtils.getInstance().showSimpleDialog(context, R.layout.dialog_placeanorder, R.style.dialog, (contentView, utils) -> {
                utils.setCancelable(false);
                TextView  contents=contentView.findViewById(R.id.content);
                Button btn_click = contentView.findViewById(R.id.cancel);

                ApiInterface.ApiFactory.createApi().get_words_list().enqueue(new Callback<WordsListEntity>() {
                    @Override
                    public void onResponse(Call<WordsListEntity> call, Response<WordsListEntity> response) {
                        if (response.body().getCode() == 200) {

                            contents.setText(response.body().getResult().getElectricity_rechage_tips());
                        }
                    }

                    @Override
                    public void onFailure(Call<WordsListEntity> call, Throwable t) {

                    }
                });

                btn_click.setOnClickListener(v2 -> {
                    utils.close();
                });
            });

        });
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
                    content.setText(response.body().getResult().getElectricity_rechage().toString());

                }
            }

            @Override
            public void onFailure(Call<WordsListEntity> call, Throwable t) {

            }
        });
    }


    public void get_electric_new() {
        ApiInterface.ApiFactory.createApi().get_electric_new().enqueue(new Callback<FareNewEntity>() {
            @Override
            public void onResponse(Call<FareNewEntity> call, Response<FareNewEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult() != null && !TextUtils.isEmpty(response.body().getResult())) {
                        Enter_number.setText(response.body().getResult());
                        Editable etext = Enter_number.getText();
                        Selection.setSelection(etext, etext.length());
                    }
                }
            }

            @Override
            public void onFailure(Call<FareNewEntity> call, Throwable t) {

            }
        });
    }


    @Override
    public <T> void update(int what, T t) {
        if (what == Constants.UPDATESUCCESS) {
            String name = (String) t;
            String[] strings = name.split("&");
            city.setText(strings[0]);
            id = Integer.parseInt(strings[1]);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoticeObserver.getInstance().removeObserver(this);
    }

    public void electric() {
        ApiInterface.ApiFactory.createApi().electric().enqueue(new Callback<ElectricEntity>() {
            @Override
            public void onResponse(Call<ElectricEntity> call, Response<ElectricEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult().size() > 0) {
                        List<ElectricEntity.ResultBean> resultBeanList = response.body().getResult();
                        ElectricAdapter fenleiLeftAdapter = new ElectricAdapter(context, resultBeanList);
                        rcyElectricity.setAdapter(fenleiLeftAdapter);
                        fenleiLeftAdapter.setOnItemClickListener((view, position) -> {
                            if (TextUtils.isEmpty(Enter_number.getText().toString()) || Enter_number.getText().toString().equals("")) {
                                ToastUtil.show("请填写户号!", 200);
                                return;
                            }
                            if (TextUtils.isEmpty(city.getText().toString()) || city.getText().toString().equals("")) {
                                ToastUtil.show("请选择城市!", 200);
                                return;
                            }

                            loadingDialog = new LoadingDialog(context, context.getString(R.string.loading));
                            loadingDialog.show();
                            electric_order_add(resultBeanList.get(position).getId(), "DF", Enter_number.getText().toString(), id);


                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<ElectricEntity> call, Throwable t) {

            }
        });
    }


    //电费下单
    public void electric_order_add(int goods_id, String prefix, String number, int city) {
        ApiInterface.ApiFactory.createApi().electric_order_add(goods_id, prefix, number, city).enqueue(new Callback<ElectricOrdersEntity>() {
            @Override
            public void onResponse(Call<ElectricOrdersEntity> call, Response<ElectricOrdersEntity> response) {
                if (response.body().getCode() == 200) {

                    String order_id = response.body().getResult().getOrder_id();
                    ApiInterface.ApiFactory.createApi().check_status(order_id).enqueue(new Callback<CheckStatusEntity>() {
                        @Override
                        public void onResponse(Call<CheckStatusEntity> call, Response<CheckStatusEntity> response2) {
                            if (response2.body().getResult().equals("2")) {
                                loadingDialog.dismiss();
                                ToastUtil.show("该订单已完成支付,请勿重复支付!", 200);
                                return;
                            } else {
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
            public void onFailure(Call<ElectricOrdersEntity> call, Throwable t) {

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
                        SharedPreferencesUtils.putInt(Constants.PAYTYPE,7);
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
}
