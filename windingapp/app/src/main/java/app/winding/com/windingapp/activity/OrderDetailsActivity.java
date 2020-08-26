package app.winding.com.windingapp.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.CancelEntity;
import app.winding.com.windingapp.entity.CheckStatusEntity;
import app.winding.com.windingapp.entity.ConfirmOrderEntity;
import app.winding.com.windingapp.entity.GoodsDetailEntity;
import app.winding.com.windingapp.entity.GoodsOrderEntity;
import app.winding.com.windingapp.entity.IDCard;
import app.winding.com.windingapp.entity.PayOrderEntity;
import app.winding.com.windingapp.entity.UserEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.popuwindow.PhotoPopupWindow;

import app.winding.com.windingapp.ui.GenerateOrderActivity;
import app.winding.com.windingapp.ui.UpdateMoviewActivity;
import app.winding.com.windingapp.util.BitmapAndStringUtils;
import app.winding.com.windingapp.util.BitmapUtil;
import app.winding.com.windingapp.util.GlideUtils;
import app.winding.com.windingapp.util.GsonUtil;
import app.winding.com.windingapp.util.IsInstallWeChatOrAliPay;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.PhotoUtils;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.winding.com.windingapp.ui.SettingActivity.hasSdcard;

//订单详情
public class OrderDetailsActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.rlbk)
    RelativeLayout rlbk;
    @Bind(R.id.Picture_details)
    TextView Picture_details;
    @Bind(R.id.Completed)
    TextView Completed;
    @Bind(R.id.create_time)
    TextView create_time;
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.typename)
    TextView typename;
    @Bind(R.id.title)
    TextView title;


    @Bind(R.id.pay_price)
    TextView pay_price;
    @Bind(R.id.Order_Number)
    TextView Order_Number;
    @Bind(R.id.createtime)
    TextView createtime;
    @Bind(R.id.remarkdetail)
    TextView remarkdetail;
    @Bind(R.id.pay)
    TextView pay;
    @Bind(R.id.pic2)
    ImageView pic2;
    @Bind(R.id.pic3)
    ImageView pic3;
    @Bind(R.id.llvoucher)
    LinearLayout llvoucher;
    @Bind(R.id.Update)
    TextView Update;

    @Bind(R.id.llcancelorder)
    LinearLayout llcancelorder;
    @Bind(R.id.llcofimend)
    LinearLayout llcofimend;
    @Bind(R.id.rlpicdetail)
    LinearLayout rlpicdetail;
    @Bind(R.id.copy)
    TextView copy;
    @Bind(R.id.Cancellation)
    TextView Cancellation;
    int id;
    private PhotoPopupWindow mPhotoPopupWindow;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    String filename = "";
    String filepath, pics = "";
    LoadingDialog loadingDialog;
    private Handler handler = null;
    int status;
    GoodsDetailEntity.ResultBean resultBeanList;
    IWXAPI api;
    public static OrderDetailsActivity instance;
    String orderid;
    int t_userid;
    int typeid = 0;
    private int state = 1;
    List<String> list;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_order_details;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        instance = this;
        handler = new Handler();
        ivBack.setOnClickListener(v -> {
            finish();
            state = 2;
        });
        tvTitles.setText("订单详情");
        id = getIntent().getExtras().getInt("id");
        if (id == 1) {
            llvoucher.setVisibility(View.VISIBLE);
        } else if (id == 2) {
            t_userid = getIntent().getExtras().getInt("t_userid");
            llcancelorder.setVisibility(View.VISIBLE);
        } else if (id == 3) {
            llcofimend.setVisibility(View.VISIBLE);
        } else if (id == 4) {
            rlpicdetail.setVisibility(View.GONE);
        } else if (id == 5) {
            llvoucher.setVisibility(View.VISIBLE);
            Cancellation.setVisibility(View.GONE);
        } else if (id == 6) {
            llcancelorder.setVisibility(View.VISIBLE);
            pay.setVisibility(View.GONE);
        }

        goods_order_details(getIntent().getExtras().getString("order_id"));

    }

    public void goods_order_details(String id) {
        String token = SharedPreferencesUtils.getString(Constants.TOKEN, "");
        ApiInterface.ApiFactory.createApi().goods_order_detail(id).enqueue(new Callback<GoodsDetailEntity>() {
            @Override
            public void onResponse(Call<GoodsDetailEntity> call, Response<GoodsDetailEntity> response) {
                if (state == 1) {
                    if (response.body().getCode() == 200) {
                        resultBeanList = response.body().getResult();
                        if (resultBeanList.getStatus() == 1) {
                            if (resultBeanList.getClassify_id() == 3 || resultBeanList.getClassify_id() == 4) {
                                Completed.setText("已发布");
                                rlbk.setBackgroundResource(R.mipmap.hasbeen);
                            } else {
                                Completed.setText("未支付");
                                rlbk.setBackgroundResource(R.mipmap.unpaid);
                            }

                        } else if (resultBeanList.getStatus() == 2) {
                            if (resultBeanList.getUser_id() > 0) {
                                Completed.setText("待确定");
                                rlbk.setBackgroundResource(R.mipmap.determined);
                            } else {
                                Completed.setText("已支付");
                                rlbk.setBackgroundResource(R.mipmap.paid);
                            }
                        } else if (resultBeanList.getStatus() == 3) {
                            Completed.setText("已完成");
                            rlbk.setBackgroundResource(R.mipmap.completed);
                        } else if (resultBeanList.getStatus() == 4) {
                            Completed.setText("已退款");
                            rlbk.setBackgroundResource(R.mipmap.refunded);
                        } else if (resultBeanList.getStatus() == 5) {
                            if (resultBeanList.getClassify_id() == 3) {
                                Completed.setText("审核失败");
                                rlbk.setBackgroundResource(R.mipmap.auditfailure);
                            } else {
                                Completed.setText("待确认");
                                rlbk.setBackgroundResource(R.mipmap.determined);
                            }
                        }

                        create_time.setText(resultBeanList.getPay_time());
                        if (resultBeanList.getIcon() == 0) {
                            //求购  买单
                            imageView.setImageResource(R.mipmap.wantbuy);
                            pay_price.setText("￥" + resultBeanList.getPay_price());
                        } else {
                            //转让   卖单
                            imageView.setImageResource(R.mipmap.possession);
                            pay_price.setText("￥" + resultBeanList.getCash_money());
                        }
                        typename.setText(resultBeanList.getClassify_name());
                        title.setText(resultBeanList.getGoods().getGood_name());
                        typeid = resultBeanList.getGoods().getType_id();


                        orderid = resultBeanList.getOrder_id();
                        if (resultBeanList.getClassify_id() == 1 || resultBeanList.getClassify_id() == 2) {

                            if (resultBeanList.getKalman() != null && !TextUtils.isEmpty(resultBeanList.getKalman().trim())) {
                                Order_Number.setText("号码：" + resultBeanList.getKalman());
                            }
                        } else {
                            Order_Number.setText("订单编号：" + resultBeanList.getOrder_id());
                        }

                        createtime.setText("发布日期：" + resultBeanList.getCreate_time());
                        if (resultBeanList.getClassify_id() != 3) {
                            if (resultBeanList.getGoods().getMemo() == null || TextUtils.isEmpty(resultBeanList.getGoods().getMemo())) {
                                remarkdetail.setText("备注详情：无");
                            } else {
                                remarkdetail.setText("备注详情：" + resultBeanList.getGoods().getMemo());
                            }
                        }


                        copy.setOnClickListener(v -> {
                            //获取剪贴板管理器：
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            // 创建普通字符型ClipData
                            ClipData mClipData = ClipData.newPlainText("Label", resultBeanList.getOrder_id());
                            // 将ClipData内容放到系统剪贴板里。
                            cm.setPrimaryClip(mClipData);
                            ToastUtil.show("复制成功", 25);
                        });


                        if (resultBeanList.getClassify_id() == 1 || resultBeanList.getClassify_id() == 2) {
                            rlpicdetail.setVisibility(View.GONE);
                        } else {
                            if (resultBeanList.getClassify_id() == 4) {
                                 list = GsonUtil.fromToJson(resultBeanList.getGoods().getDetails().toString(), new TypeToken<List<String>>() {
                                }.getType());


                                if (list.size() == 1) {
                                    rlpicdetail.setVisibility(View.VISIBLE);
                                    String names=list.get(0).toString();
                                    GlideUtils.loadImgWithGlide(list.get(0).toString(), pic2);
                                    pic3.setVisibility(View.GONE);
                                } else if (list.size() > 1) {
                                    rlpicdetail.setVisibility(View.VISIBLE);
                                    GlideUtils.loadImgWithGlide(list.get(0).toString(), pic2);
                                    GlideUtils.loadImgWithGlide(list.get(1).toString(), pic3);
                                }

                            } else if (resultBeanList.getClassify_id() == 5) {  //代购

                            } else {
                                if (resultBeanList.getVoucher() != null && !TextUtils.isEmpty(resultBeanList.getVoucher())) {
                                    rlpicdetail.setVisibility(View.VISIBLE);
                                    GlideUtils.loadImgWithGlide(resultBeanList.getVoucher(), pic2);
                                    pic3.setVisibility(View.GONE);
                                }
                            }

                        }

                        pic2.setOnLongClickListener(view -> {
                            new Thread(() -> {
                                if (resultBeanList.getClassify_id() == 4) {
                                    Bitmap bitmap = BitmapUtil.returnBitmap(list.get(0).toString());
                                    BitmapUtil.saveImageToGallery(getApplicationContext(), bitmap, () -> {
                                        ToastUtil.show("保存成功", 200);
                                    });
                                }else {
                                    Bitmap bitmap = BitmapUtil.returnBitmap(resultBeanList.getVoucher());
                                    BitmapUtil.saveImageToGallery(getApplicationContext(), bitmap, () -> {
                                        ToastUtil.show("保存成功", 200);
                                    });
                                }

                            }).start();
                            return true;
                        });

                        pic3.setOnLongClickListener(view -> {
                            new Thread(() -> {
                                Bitmap bitmap = BitmapUtil.returnBitmap(list.get(1).toString());
                                BitmapUtil.saveImageToGallery(getApplicationContext(), bitmap, () -> {
                                    ToastUtil.show("保存成功", 200);
                                });
                            }).start();
                            return true;
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<GoodsDetailEntity> call, Throwable t) {

            }
        });
    }


    @OnClick({R.id.Uploadcredentials, R.id.submit, R.id.Cancellation, R.id.cancelorder, R.id.pay, R.id.cofimend})
    public void onclick(View view) {
        switch (view.getId()) {


            case R.id.Uploadcredentials:
                status = 1;
                mPhotoPopupWindow = new PhotoPopupWindow(this, v -> {
                    backgroundAlpha(1.0f);
                    requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                        @Override
                        public void granted() {
                            PhotoUtils.openPic(OrderDetailsActivity.this, CODE_GALLERY_REQUEST);
                            mPhotoPopupWindow.dismiss();
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(OrderDetailsActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                            mPhotoPopupWindow.dismiss();
                        }
                    });
                }, v -> {
                    backgroundAlpha(1.0f);
                    requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                        @Override
                        public void granted() {
                            if (hasSdcard()) {
                                imageUri = Uri.fromFile(fileUri);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                                    //通过FileProvider创建一个content类型的Uri
                                    imageUri = FileProvider.getUriForFile(OrderDetailsActivity.this, "app.winding.com.windingapp.fileProvider", fileUri);
                                PhotoUtils.takePicture(OrderDetailsActivity.this, imageUri, CODE_CAMERA_REQUEST);
                                mPhotoPopupWindow.dismiss();
                            } else {
                                Toast.makeText(OrderDetailsActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                                Log.e("asd", "设备没有SD卡");
                                mPhotoPopupWindow.dismiss();
                            }
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(OrderDetailsActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                            mPhotoPopupWindow.dismiss();
                        }
                    });
                });
                View rootView2 = LayoutInflater.from(this)
                        .inflate(R.layout.activity_main, null);
                mPhotoPopupWindow.showAtLocation(rootView2,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.submit:
                if (pics == null || TextUtils.isEmpty(pics)) {
                    ToastUtil.show("请上传凭证截图!", 200);
                    return;
                }
                confirm_goods_order(getIntent().getExtras().getString("order_id"), pics);

                break;
            case R.id.Cancellation:
                cancel_order(getIntent().getExtras().getString("order_id"));
                break;
            case R.id.cancelorder:
                cancel_order(getIntent().getExtras().getString("order_id"));
                break;
            case R.id.pay:  //支付
                if (resultBeanList.getGoods().getClassify_id() == 6 && resultBeanList.getIs_sell() == 2) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id", resultBeanList.getId() + "");
                    bundle.putString("order_id", resultBeanList.getOrder_id());
                    bundle.putString("payprice",resultBeanList.getPay_price());
                    bundle.putInt("cityid", resultBeanList.getGoods().getCity());
                    bundle.putString("good_name", resultBeanList.getGoods().getGood_name());
                    bundle.putString("memo", resultBeanList.getGoods().getMemo());
                    bundle.putString("city_name", resultBeanList.getGoods().getCity_name());
                    bundle.putString("brief", resultBeanList.getGoods().getBrief());
                    JumpActivityUtil.launchActivity(OrderDetailsActivity.this, UpdateMoviewActivity.class, bundle);

                } else {
                    if (resultBeanList.getPay_price() != null && !TextUtils.isEmpty(resultBeanList.getPay_price())) {
                        ApiInterface.ApiFactory.createApi().check_status(orderid).enqueue(new Callback<CheckStatusEntity>() {
                            @Override
                            public void onResponse(Call<CheckStatusEntity> call, Response<CheckStatusEntity> response2) {
                                if (response2.body().getResult().equals("2")) {
                                    ToastUtil.show("该订单已完成支付,请勿重复支付!", 200);
                                    return;
                                } else {
                                    loadingDialog = new LoadingDialog(context, context.getString(R.string.loading));
                                    loadingDialog.show();
                                    pay_order(orderid, resultBeanList.getPay_price().toString(), 1);
                                }
                            }

                            @Override
                            public void onFailure(Call<CheckStatusEntity> call, Throwable t) {
                            }
                        });

                    }
                }

                break;
            case R.id.cofimend://确认完成

                complete_goods_order(getIntent().getExtras().getString("order_id"), "");
                break;
        }
    }


    //支付
    public void pay_order(String order_id, String money, int pay_type) {
        ApiInterface.ApiFactory.createApi().pay_order(order_id, money, pay_type).enqueue(new Callback<PayOrderEntity>() {
            @Override
            public void onResponse(Call<PayOrderEntity> call, Response<PayOrderEntity> response) {
                if (response.body().getCode() == 200) {
                    loadingDialog.dismiss();
                    if (IsInstallWeChatOrAliPay.isWeixinAvilible(context)) {
                        SharedPreferencesUtils.putInt(Constants.PAYTYPE, 3);
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

    //完成订单
    public void complete_goods_order(String order_id, String voucher) {
        ApiInterface.ApiFactory.createApi().complete_goods_order(order_id, voucher).enqueue(new Callback<ConfirmOrderEntity>() {
            @Override
            public void onResponse(Call<ConfirmOrderEntity> call, Response<ConfirmOrderEntity> response) {
                if (response.body().getCode() == 200) {
                    ToastUtil.show(response.body().getMessage().toString(), 200);
                    finish();
                    NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                }
            }

            @Override
            public void onFailure(Call<ConfirmOrderEntity> call, Throwable t) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 600, output_Y = 600;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileCropUri);


                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "app.winding.com.windingapp.fileProvider", new File(newUri.getPath()));
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        Toast.makeText(OrderDetailsActivity.this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        try {
                            filepath = BitmapAndStringUtils.getPath(this, cropImageUri);
                            filename = filepath.substring(filepath.lastIndexOf("/") + 1);
                            uploadphoto();
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }


                    }
                    break;
            }
        }
    }


    //上传凭证
    public void confirm_goods_order(String id, String pic) {
        ApiInterface.ApiFactory.createApi().confirm_goods_order(id, pic).enqueue(new Callback<ConfirmOrderEntity>() {
            @Override
            public void onResponse(Call<ConfirmOrderEntity> call, Response<ConfirmOrderEntity> response) {
                if (response.body().getCode() == 200) {
                    NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                    ToastUtil.show("上传成功!", 200);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<ConfirmOrderEntity> call, Throwable t) {

            }
        });
    }

    //取消订单
    public void cancel_order(String id) {
        ApiInterface.ApiFactory.createApi().cancel_order(id).enqueue(new Callback<CancelEntity>() {
            @Override
            public void onResponse(Call<CancelEntity> call, Response<CancelEntity> response) {
                if (response.body().getCode() == 200) {
                    ToastUtil.show("取消成功", 200);
                    finish();
                    NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                    NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                } else {
                    ToastUtil.show(response.body().getMessage().toString(), 200);
                }
            }

            @Override
            public void onFailure(Call<CancelEntity> call, Throwable t) {

            }
        });
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = OrderDetailsActivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        OrderDetailsActivity.this.getWindow().setAttributes(lp);
        OrderDetailsActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }


    public void uploadphoto() {

        loadingDialog = new LoadingDialog(this, getString(R.string.loading));
        loadingDialog.show();
        OkHttpClient mOkHttpClent = new OkHttpClient();
        File file = new File(filepath);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("position", "headImage/")
                .addFormDataPart("image", filename,
                        RequestBody.create(MediaType.parse("image/*"), file));

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .header("Authority", (String) SharedPreferencesUtils.getSp(Constants.TOKEN, ""))
                .url(ApiInterface.HEAD_IMG)
                .post(requestBody)
                .build();
        okhttp3.Call call = mOkHttpClent.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                loadingDialog.dismiss();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                IDCard p = new Gson().fromJson(response.body().string(), IDCard.class);
                if (p.getCode() == 200) {
                    loadingDialog.dismiss();
                    String url = p.getResult().toString();

                    pics = url;

                    new Thread() {
                        public void run() {

                            handler.post(runnableUi);
                        }
                    }.start();
                }
            }

        });

    }

    // 构建Runnable对象，在runnable中更新界面
    Runnable runnableUi = new Runnable() {
        @Override
        public void run() {
            //更新界面
            if (status == 1) {
                Update.setText("已上传");
            }


        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        state = 2;
    }
}
