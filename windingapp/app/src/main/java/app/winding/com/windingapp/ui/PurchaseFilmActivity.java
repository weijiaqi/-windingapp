package app.winding.com.windingapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.CheckStatusEntity;
import app.winding.com.windingapp.entity.GoodsAddEntity;
import app.winding.com.windingapp.entity.OrderAddEntity;
import app.winding.com.windingapp.entity.PayOrderEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.IsInstallWeChatOrAliPay;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import app.winding.com.windingapp.util.code.PickActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 求购电影票
 */
public class PurchaseFilmActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.cofimorder)
    TextView cofimorder;
    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.moviename)
    EditText moviename;
    @Bind(R.id.remark)
    EditText remark;
    @Bind(R.id.city)
    TextView city;
    @Bind(R.id.writemoview)
    EditText writemoview;
    @Bind(R.id.rldate)
    RelativeLayout rldate;
    @Bind(R.id.Price)
    EditText Price;
    @Bind(R.id.selling_price)
    EditText sellingPrice;
    @Bind(R.id.Total)
    TextView Total;
    private int id;
    LoadingDialog loadingDialog;
    IWXAPI api;
    public static PurchaseFilmActivity instance;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_purchase_film;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        instance = this;
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("买电影票");

        Price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (Price.getText().toString() != null && !TextUtils.isEmpty(Price.getText().toString())) {
                    if (sellingPrice.getText().toString() != null&&!TextUtils.isEmpty(sellingPrice.getText().toString())){
                        Total.setText(String.format(UiUtils.getText(R.string.Total), Integer.parseInt(Price.getText().toString()) * Double.parseDouble(sellingPrice.getText().toString())));
                    }
                }else{
                    Total.setText("0.0 元");
                }

            }
        });

        sellingPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("beforeTextChanged", "beforeTextChanged: 输入过程中执行该方法");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("onTextChanged", "onTextChanged: 输入前确认执行该方法");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("afterTextChanged", "afterTextChanged: 输入结束执行该方法");
                if (Price.getText().toString() != null && !TextUtils.isEmpty(Price.getText().toString())) {
                    if (sellingPrice.getText().toString() != null&&!TextUtils.isEmpty(sellingPrice.getText().toString())){
                        Total.setText(String.format(UiUtils.getText(R.string.Total), Integer.parseInt(Price.getText().toString()) * Double.parseDouble(sellingPrice.getText().toString())));
                    }else{
                        Total.setText("0.0 元");
                    }

                }
            }
        });


    }

    @OnClick({R.id.cofimorder, R.id.rldate, R.id.textView32, R.id.next, R.id.rlcity})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.cofimorder:


                if (moviename.getText().toString().equals("") || TextUtils.isEmpty(moviename.getText().toString())) {
                    ToastUtil.show("请输入电影名称!", 200);
                    return;
                }
                if (remark.getText().toString().equals("") || TextUtils.isEmpty(remark.getText().toString())) {
                    ToastUtil.show("请详细的描述您需要备注的需求!", 200);
                    return;
                }
                if (writemoview.getText().toString().equals("") || TextUtils.isEmpty(writemoview.getText().toString())) {
                    ToastUtil.show("输入影院名称!", 200);
                    return;
                }
                if (Price.getText().toString().equals("") || TextUtils.isEmpty(Price.getText().toString())) {
                    ToastUtil.show("输入购买张数!", 200);
                    return;
                }
                if (sellingPrice.getText().toString().equals("") || TextUtils.isEmpty(sellingPrice.getText().toString())) {
                    ToastUtil.show("输入实际售价!", 200);
                    return;
                }
                if (city.getText().toString().equals("") || TextUtils.isEmpty(city.getText().toString())) {
                    ToastUtil.show("请选择城市!", 200);
                    return;
                }

                if (content.getText().toString().equals("") || TextUtils.isEmpty(content.getText().toString())) {
                    ToastUtil.show("请选择时间!", 200);
                    return;
                }

                if (UiUtils.isFastClick()) {
                    loadingDialog = new LoadingDialog(this, this.getString(R.string.loading));
                    loadingDialog.show();
                    goods_add(moviename.getText().toString(), writemoview.getText().toString(), content.getText().toString(), Integer.parseInt(Price.getText().toString()), "", Integer.parseInt(Price.getText().toString()) * Double.parseDouble(sellingPrice.getText().toString()) + "", sellingPrice.getText().toString(), "", 6, 21, 41, 1, 1, 1, 1, remark.getText().toString(), "", "", "", id);
                }
                break;
            case R.id.rldate:

                TimePickerView pvTime = new TimePickerView.Builder(this, (date2, v) -> {//选中事件回调
                    String time = getTime(date2);
                    content.setText(time);
                })
                        .setType(TimePickerView.Type.YEAR_MONTH_DAY)//默认全部显示
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确认")//确认按钮文字
                        .setContentSize(20)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
//                        .setTitleText("请选择时间")//标题文字
                        .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(true)//是否循环滚动
                        .setTextColorCenter(getResources().getColor(R.color.color_67))//设置选中项的颜色
                        .setTitleColor(getResources().getColor(R.color.color_67))//标题文字颜色
                        .setSubmitColor(getResources().getColor(R.color.color_67))//确定按钮文字颜色
                        .setCancelColor(getResources().getColor(R.color.color_67))//取消按钮文字颜色
//                        .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
//                        .setBgColor(0xFF333333)//滚轮背景颜色 Night mode
                        .setRange(2019, 2100)//默认是1900-2100年
//                        .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
//                        .setRangDate(startDate,endDate)//起始终止年月日设定
//                        .setLabel("年","月","日","时","分","秒")
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                        .isDialog(true)//是否显示为对话框样式
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();

                break;


            case R.id.rlcity:
                startActivityForResult(new Intent(getApplicationContext(), PickActivity.class), 111);
                break;
        }
    }


    public String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            city.setText(data.getStringExtra("name"));
            id = Integer.parseInt(data.getStringExtra("id"));

        }

    }


    public void goods_add(String good_name, String brief, String time, int num, String good_img, String price, String money, String details, int classify_id, int type_id, int config_id, int is_inside, int is_shelf, int supplier_id, int is_sell, String memo, String order_mobile, String order_name, String order_address, int city) {
        ApiInterface.ApiFactory.createApi().goods_add(good_name, brief, time, num, good_img, price, money, details, classify_id, type_id, config_id, is_inside, is_shelf, supplier_id, is_sell, memo, order_mobile, order_name, order_address, city).enqueue(new Callback<GoodsAddEntity>() {
            @Override
            public void onResponse(Call<GoodsAddEntity> call, Response<GoodsAddEntity> response) {
                if (response.body().getCode() == 200) {
                    order_add(Integer.parseInt(response.body().getResult()), "", "DY");
                }
            }

            @Override
            public void onFailure(Call<GoodsAddEntity> call, Throwable t) {

            }
        });
    }


    public void order_add(int goods_id, String order_details, String prefix) {
        ApiInterface.ApiFactory.createApi().order_add(goods_id, order_details, prefix).enqueue(new Callback<OrderAddEntity>() {
            @Override
            public void onResponse(Call<OrderAddEntity> call, Response<OrderAddEntity> response) {
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

                } else {
                    ToastUtil.show(response.body().getMessage().toString(), 200);
                }
            }

            @Override
            public void onFailure(Call<OrderAddEntity> call, Throwable t) {

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
                        SharedPreferencesUtils.putInt(Constants.PAYTYPE, 5);
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
