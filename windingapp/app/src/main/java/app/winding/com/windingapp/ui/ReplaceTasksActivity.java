package app.winding.com.windingapp.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.CommodityAdapter;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.CheckStatusEntity;
import app.winding.com.windingapp.entity.GoodTypeEntity;
import app.winding.com.windingapp.entity.GoodsAddEntity;
import app.winding.com.windingapp.entity.IDCard;
import app.winding.com.windingapp.entity.OrderAddEntity;
import app.winding.com.windingapp.entity.PayOrderEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.popuwindow.CustomPopWindow;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.IsInstallWeChatOrAliPay;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.SoftHideKeyBoardUtil;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 代下任务
 */
public class ReplaceTasksActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, BGASortableNinePhotoLayout.Delegate {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.remark)
    EditText remark;
    @Bind(R.id.typename)
    TextView typename;
    @Bind(R.id.snpl_moment_add_photos)
    BGASortableNinePhotoLayout mPhotosSnpl;
    @Bind(R.id.llmain)
    LinearLayout llmain;
    private static final int PRC_PHOTO_PICKER = 1;
    private static final int RC_CHOOSE_PHOTO = 1;
    private static final int RC_PHOTO_PREVIEW = 2;
    private static final String EXTRA_MOMENT = "EXTRA_MOMENT";
    @Bind(R.id.moviename)
    EditText moviename;
    @Bind(R.id.Detailed)
    EditText Detailed;
    @Bind(R.id.Please_enter)
    EditText PleaseEnter;
    @Bind(R.id.receiving_phone)
    EditText receivingPhone;
    @Bind(R.id.sum)
    TextView sums;
    @Bind(R.id.llname)
    RelativeLayout llname;
    @Bind(R.id.PlaceAnOrder)
    TextView PlaceAnOrder;
    @Bind(R.id.Settlementprice)
    EditText Settlementprice;
    @Bind(R.id.Gratuity)
    TextView Gratuity;
    private CustomPopWindow popWindow;
    CommodityAdapter commodityAdapter;
    LoadingDialog loadingDialog;
    String url1, url2, url3, good_img;
    int type_id;
    IWXAPI api;

    public static ReplaceTasksActivity instance;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_replace_tasks;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        instance = this;//在onCreate里面写
        SoftHideKeyBoardUtil.assistActivity(this);
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("我要发布任务");

        mPhotosSnpl.setMaxItemCount(3);
        // 设置拖拽排序控件的代理
        mPhotosSnpl.setDelegate(this);
        Settlementprice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Settlementprice.getText().toString() != null && !TextUtils.isEmpty(Settlementprice.getText().toString())) {
                    Double sum =(double) Math.round(Double.parseDouble(Settlementprice.getText().toString()) * 0.2);
                    Gratuity.setText(sum + "");
                    sums.setText(String.format(UiUtils.getText(R.string.Total), sum));
                }
            }
        });


    }

    @OnClick({R.id.PlaceAnOrder, R.id.snpl_moment_add_photos, R.id.llname})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.PlaceAnOrder: //下单


                if (moviename.getText().toString().equals("") || TextUtils.isEmpty(moviename.getText().toString())) {
                    ToastUtil.show("请输入商品的名称!", 200);
                    return;
                }
                if (Detailed.getText().toString().equals("") || TextUtils.isEmpty(Detailed.getText().toString())) {
                    ToastUtil.show("请输入收货人详细地址!", 200);
                    return;
                }

                if (PleaseEnter.getText().toString().equals("") || TextUtils.isEmpty(PleaseEnter.getText().toString())) {
                    ToastUtil.show("请输入收货姓名!", 200);
                    return;
                }


                if (receivingPhone.getText().toString().equals("") || TextUtils.isEmpty(receivingPhone.getText().toString())) {
                    ToastUtil.show("请输入收货联系电话!", 200);
                    return;
                }

                if (TextUtils.isEmpty(remark.getText().toString()) ||remark.getText().toString().equals("")) {
                    Toast.makeText(this, "请描述购买攻略！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mPhotosSnpl.getItemCount() == 0) {
                    Toast.makeText(this, "请上传照片！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Settlementprice.getText().toString().equals("") || TextUtils.isEmpty(Settlementprice.getText().toString())) {
                    ToastUtil.show("预估商品结算价格!", 200);
                    return;
                }

                if (typename.getText().toString().equals("") || TextUtils.isEmpty(typename.getText().toString())) {
                    ToastUtil.show("选择类别!", 200);
                    return;
                }

                if (UiUtils.isFastClick()) {
                    loadingDialog = new LoadingDialog(this, getString(R.string.loading));
                    loadingDialog.show();

                    List<String> list = mPhotosSnpl.getData();

                    for (int i = 0; i < list.size(); i++) {

                        int finalI = i;
                        OkHttpClient mOkHttpClent = new OkHttpClient();
                        File file = new File(list.get(i).toString());

                        MultipartBody.Builder builder = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("position", "headImage/")
                                .addFormDataPart("image", list.get(i).toString(),
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

                                    if (finalI == 0) {
                                        url1 = p.getResult().toString();
                                    } else if (finalI == 1) {
                                        url2 = p.getResult().toString();
                                    } else if (finalI == 2) {
                                        url3 = p.getResult().toString();
                                    }

                                    if (finalI == list.size() - 1) {
                                        if (url2 != null && url3 != null) {

                                            good_img = "[" + "\"" + url1 + "\"" + "," + "\"" + url2 + "\"" + "," + "\"" + url3 + "\"" + "]";
                                            goods_add(moviename.getText().toString(), "", "", 1, good_img, Gratuity.getText().toString(), Settlementprice.getText().toString(), "", 5, type_id, 40, 1, 1, 1, 1, remark.getText().toString(), receivingPhone.getText().toString(), PleaseEnter.getText().toString(), Detailed.getText().toString(), 0);
                                        } else if (url2 != null) {
                                            good_img = "[" + "\"" + url1 + "\"" + "," + "\"" + url2 + "\"" + "]";
                                            goods_add(moviename.getText().toString(), "", "", 1, good_img, Gratuity.getText().toString(), Settlementprice.getText().toString(), "", 5, type_id, 40, 1, 1, 1, 1, remark.getText().toString(), receivingPhone.getText().toString(), PleaseEnter.getText().toString(), Detailed.getText().toString(), 0);
                                        } else {
                                            good_img = "[" + "\"" + url1 + "\"" + "]";
                                            goods_add(moviename.getText().toString(), "", "", 1, good_img, Gratuity.getText().toString(), Settlementprice.getText().toString(), "", 5, type_id, 40, 1, 1, 1, 1, remark.getText().toString(), receivingPhone.getText().toString(), PleaseEnter.getText().toString(), Detailed.getText().toString(), 0);
                                        }
                                    }
                                }
                            }
                        });
                    }
                }
                break;
            case R.id.snpl_moment_add_photos:
                choicePhotoWrapper();
                break;
            case R.id.llname:
                View contentView = LayoutInflater.from(this).inflate(R.layout.spiner_window_layout, null);
                //处理popWindow 显示内容
                initPopView(contentView);
                //创建并显示popWindow
                popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                        .setOnDissmissListener(() -> Log.e("TAG", "onDismiss"))
                        .create();
                popWindow.showAtLocation(llmain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;

        }
    }


    public void goods_add(String good_name, String brief, String time, int num, String good_img, String price, String money, String details, int classify_id, int type_id, int config_id, int is_inside, int is_shelf, int supplier_id, int is_sell, String memo, String order_mobile, String order_name, String order_address, int city) {
        ApiInterface.ApiFactory.createApi().goods_add(good_name, brief, time, num, good_img, price, money, details, classify_id, type_id, config_id, is_inside, is_shelf, supplier_id, is_sell, memo, order_mobile, order_name, order_address, city).enqueue(new Callback<GoodsAddEntity>() {
            @Override
            public void onResponse(Call<GoodsAddEntity> call, Response<GoodsAddEntity> response) {
                if (response.body().getCode() == 200) {
                    order_add(Integer.parseInt(response.body().getResult()), "", "DG");
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
                        SharedPreferencesUtils.putInt(Constants.PAYTYPE, 6);
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

    private void initPopView(View view) {

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        ApiInterface.ApiFactory.createApi().goodss_type(5, 18).enqueue(new Callback<GoodTypeEntity>() {
            @Override
            public void onResponse(Call<GoodTypeEntity> call, Response<GoodTypeEntity> response) {
                if (response.body().getCode() == 200) {
                    List<GoodTypeEntity.ResultBean> resultBeanList = response.body().getResult();
                    if (resultBeanList.size() > 0) {
                        commodityAdapter = new CommodityAdapter(ReplaceTasksActivity.this, R.layout.item_mattersbuy, resultBeanList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ReplaceTasksActivity.this));
                        recyclerView.setAdapter(commodityAdapter);

                        commodityAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                                type_id = resultBeanList.get(position).getId();
                                typename.setText(resultBeanList.get(position).getType_name());
                                popWindow.dissmiss();
                            }

                            @Override
                            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                                return false;
                            }
                        });
                    }


                }
            }

            @Override
            public void onFailure(Call<GoodTypeEntity> call, Throwable t) {

            }
        });


    }

    @AfterPermissionGranted(PRC_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

            Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                    .cameraFileDir(true ? takePhotoDir : null) // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话则不开启图库里的拍照功能
                    .maxChooseCount(mPhotosSnpl.getMaxItemCount() - mPhotosSnpl.getItemCount()) // 图片选择张数的最大值
                    .selectedPhotos(null) // 当前已选中的图片路径集合
                    .pauseOnScroll(false) // 滚动列表时是否暂停加载图片
                    .build();
            startActivityForResult(photoPickerIntent, RC_CHOOSE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", PRC_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onClickAddNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, ArrayList<String> models) {
        choicePhotoWrapper();

    }

    @Override
    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
    }

    @Override
    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        Intent photoPickerPreviewIntent = new BGAPhotoPickerPreviewActivity.IntentBuilder(this)
                .previewPhotos(models) // 当前预览的图片路径集合
                .selectedPhotos(models) // 当前已选中的图片路径集合
                .maxChooseCount(mPhotosSnpl.getMaxItemCount()) // 图片选择张数的最大值
                .currentPosition(position) // 当前预览图片的索引
                .isFromTakePhoto(false) // 是否是拍完照后跳转过来
                .build();
        startActivityForResult(photoPickerPreviewIntent, RC_PHOTO_PREVIEW);
    }

    @Override
    public void onNinePhotoItemExchanged(BGASortableNinePhotoLayout sortableNinePhotoLayout, int fromPosition, int toPosition, ArrayList<String> models) {
        Toast.makeText(this, "排序发生变化", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == PRC_PHOTO_PICKER) {
            Toast.makeText(this, "您拒绝了「图片选择」所需要的相关权限!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_CHOOSE_PHOTO) {

            mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedPhotos(data));

        } else if (requestCode == RC_PHOTO_PREVIEW) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedPhotos(data));

        }
    }


}
