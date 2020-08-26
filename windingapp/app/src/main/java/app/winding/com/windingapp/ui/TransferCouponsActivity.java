package app.winding.com.windingapp.ui;

import android.Manifest;

import android.annotation.SuppressLint;
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
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.CommodityAdapter;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.adapter.WritemoviewAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.GoodTypeEntity;
import app.winding.com.windingapp.entity.GoodsAddEntity;
import app.winding.com.windingapp.entity.GoodsSupplierEntity;
import app.winding.com.windingapp.entity.GoodsTypeEntity;
import app.winding.com.windingapp.entity.IDCard;
import app.winding.com.windingapp.entity.UpdateTemporaryEntity;
import app.winding.com.windingapp.entity.UploadEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.popuwindow.CustomPopWindow;
import app.winding.com.windingapp.popuwindow.PhotoPopupWindow;
import app.winding.com.windingapp.util.BitmapAndStringUtils;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.MD5Util;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.PhotoUtils;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import app.winding.com.windingapp.util.permission.PermissionFail;
import app.winding.com.windingapp.util.permission.PermissionSuccess;
import app.winding.com.windingapp.util.permission.PermissionUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static app.winding.com.windingapp.ui.SettingActivity.hasSdcard;

//转让优惠券
public class TransferCouponsActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.Update)
    TextView Update;
    @Bind(R.id.rlSweepcode)
    RelativeLayout rlSweepcode;
    @Bind(R.id.lluploadtwo)
    LinearLayout lluploadtwo;
    @Bind(R.id.uploadstatus)
    TextView uploadstatus;
    @Bind(R.id.uploads)
    TextView uploads;
    @Bind(R.id.llmain)
    LinearLayout llmain;

    @Bind(R.id.Commodity)
    TextView Commodity;
    @Bind(R.id.writemoview)
    TextView writemoview;
    @Bind(R.id.moviename)
    TextView moviename;
    @Bind(R.id.remark)
    EditText remark;
    @Bind(R.id.Actualselling)
    EditText Actualselling;
    @Bind(R.id.facevalue)
    EditText facevalue;


    String filename = "";
    String filepath = "";
    private PhotoPopupWindow mPhotoPopupWindow;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private Uri mImageUri;
    LoadingDialog loadingDialog;
    int status;
    String pics, pics2, typename, typename2;
    private CustomPopWindow popWindow, popWindow2;
    private CommodityAdapter commodityAdapter;
    private WritemoviewAdapter writemoviewAdapter;
    private int type_id, type_id2;
    private String order_details;
    //创建属于主线程的handler
    private Handler handler = null;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_transfer_coupons;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("我要卖卷");
        handler = new Handler();
    }


    @SuppressLint("WrongConstant")
    @OnClick({R.id.rlSweepcode, R.id.lladd_more, R.id.lluploadtwo, R.id.Commodity, R.id.writemoview, R.id.Confirm_publication, R.id.Commoditynext, R.id.writemoviewnext})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.rlSweepcode:
                status = 1;
                mPhotoPopupWindow = new PhotoPopupWindow(this, v -> {
                    backgroundAlpha(1.0f);
                    requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                        @Override
                        public void granted() {
                            PhotoUtils.openPic(TransferCouponsActivity.this, CODE_GALLERY_REQUEST);
                            mPhotoPopupWindow.dismiss();
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(TransferCouponsActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
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
                                    imageUri = FileProvider.getUriForFile(TransferCouponsActivity.this, "app.winding.com.windingapp.fileProvider", fileUri);
                                PhotoUtils.takePicture(TransferCouponsActivity.this, imageUri, CODE_CAMERA_REQUEST);
                                mPhotoPopupWindow.dismiss();
                            } else {
                                Toast.makeText(TransferCouponsActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                                Log.e("asd", "设备没有SD卡");
                                mPhotoPopupWindow.dismiss();
                            }
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(TransferCouponsActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                            mPhotoPopupWindow.dismiss();
                        }
                    });
                });
                View rootView = LayoutInflater.from(this)
                        .inflate(R.layout.activity_main, null);
                mPhotoPopupWindow.showAtLocation(rootView,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;

            case R.id.lladd_more:
                if (lluploadtwo.getVisibility() == 0) {
                    ToastUtil.show("最多只允许添加两次", 200);
                    return;
                } else {
                    lluploadtwo.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.lluploadtwo:
                status = 2;
                mPhotoPopupWindow = new PhotoPopupWindow(this, v -> {
                    backgroundAlpha(1.0f);
                    requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                        @Override
                        public void granted() {
                            PhotoUtils.openPic(TransferCouponsActivity.this, CODE_GALLERY_REQUEST);
                            mPhotoPopupWindow.dismiss();
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(TransferCouponsActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
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
                                    imageUri = FileProvider.getUriForFile(TransferCouponsActivity.this, "app.winding.com.windingapp.fileProvider", fileUri);
                                PhotoUtils.takePicture(TransferCouponsActivity.this, imageUri, CODE_CAMERA_REQUEST);
                                mPhotoPopupWindow.dismiss();
                            } else {
                                Toast.makeText(TransferCouponsActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                                Log.e("asd", "设备没有SD卡");
                                mPhotoPopupWindow.dismiss();
                            }
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(TransferCouponsActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                            mPhotoPopupWindow.dismiss();
                        }
                    });
                });
                View rootView2 = LayoutInflater.from(this)
                        .inflate(R.layout.activity_main, null);
                mPhotoPopupWindow.showAtLocation(rootView2,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.Commodity:

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
            case R.id.writemoview:
                if (Commodity.getText().toString().trim().equals("商品类别")) {
                    ToastUtil.show("请选择商品类别!", 200);
                    return;
                }

                View contentView2 = LayoutInflater.from(this).inflate(R.layout.spiner_window_layout, null);
                //处理popWindow 显示内容
                initPopView2(contentView2);
                //创建并显示popWindow
                popWindow2 = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView2)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                        .setOnDissmissListener(() -> Log.e("TAG", "onDismiss"))
                        .create();
                popWindow2.showAtLocation(llmain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
            case R.id.Confirm_publication:
                if (moviename.getText().toString().equals("") || TextUtils.isEmpty(moviename.getText().toString())) {
                    ToastUtil.show("请输入商品的名称!", 200);
                    return;
                }
                if (remark.getText().toString().equals("") || TextUtils.isEmpty(remark.getText().toString())) {
                    ToastUtil.show("请详细的描述商品的有效期和所适合用的门店!", 200);
                    return;
                }
                if (typename == null || TextUtils.isEmpty(typename)) {
                    ToastUtil.show("请选择商品类别!", 200);
                    return;
                }
                if (typename2 == null || TextUtils.isEmpty(typename2)) {
                    ToastUtil.show("请选择或输入商家名称!", 200);
                    return;
                }
                if (Actualselling.getText().toString().equals("") || TextUtils.isEmpty(Actualselling.getText().toString())) {
                    ToastUtil.show("请输入实际售价!", 200);
                    return;
                }

                if (facevalue.getText().toString().equals("") || TextUtils.isEmpty(facevalue.getText().toString())) {
                    ToastUtil.show("请输入实际面值!", 200);
                    return;
                }

                if (pics == null || TextUtils.isEmpty(pics)) {
                    ToastUtil.show("请上传图片!", 200);
                    return;
                }
                if (lluploadtwo.getVisibility() == View.VISIBLE) {
                    if (pics2 == null || TextUtils.isEmpty(pics2)) {
                        ToastUtil.show("请上传图片!", 200);
                        return;
                    }
                    order_details = "[" + "\"" + pics + "\"" + "," + "\"" + pics2 + "\"" + "]";
                } else {
                    order_details = "[" + "\"" + pics + "\"" + "]";
                }

                if (UiUtils.isFastClick()) {
                    goods_add(moviename.getText().toString(), "", "", 0, "", Actualselling.getText().toString(), facevalue.getText().toString(), order_details, 4, type_id, 39, 1, 1, type_id2, 2, remark.getText().toString(), "", "", "", 0);
                }

                break;

            case R.id.Commoditynext:
                View contentView3 = LayoutInflater.from(this).inflate(R.layout.spiner_window_layout, null);
                //处理popWindow 显示内容
                initPopView(contentView3);
                //创建并显示popWindow
                popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView3)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                        .setOnDissmissListener(() -> Log.e("TAG", "onDismiss"))
                        .create();
                popWindow.showAtLocation(llmain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
            case R.id.writemoviewnext:

                if (Commodity.getText().toString().trim().equals("商品类别")) {
                    ToastUtil.show("请选择商品类别!", 200);
                    return;
                }

                View contentView4 = LayoutInflater.from(this).inflate(R.layout.spiner_window_layout, null);
                //处理popWindow 显示内容
                initPopView2(contentView4);
                //创建并显示popWindow
                popWindow2 = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView4)
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.7f) // 控制亮度
                        .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                        .setOnDissmissListener(() -> Log.e("TAG", "onDismiss"))
                        .create();
                popWindow2.showAtLocation(llmain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
        }
    }


    public void goods_add(String good_name, String brief, String time, int num, String good_img, String price, String money, String details, int classify_id, int type_id, int config_id, int is_inside, int is_shelf, int supplier_id, int is_sell, String memo, String order_mobile, String order_name, String order_address, int city) {
        ApiInterface.ApiFactory.createApi().goods_add(good_name, brief, time, num, good_img, price, money, details, classify_id, type_id, config_id, is_inside, is_shelf, supplier_id, is_sell, memo, order_mobile, order_name, order_address, city).enqueue(new Callback<GoodsAddEntity>() {
            @Override
            public void onResponse(Call<GoodsAddEntity> call, Response<GoodsAddEntity> response) {
                if (response.body().getCode() == 200) {
                    DialogUtils.getInstance().showSimpleDialog(context, R.layout.dialog_cofimorder, R.style.dialog, (contentView, utils) -> {
                        utils.setCancelable(false);
                        TextView btn_click = contentView.findViewById(R.id.btn_click);
                        btn_click.setOnClickListener(v -> {
                            NoticeObserver.getInstance().notifyObservers(Constants.UPDATETASKSTATUS);
                            finish();
                            utils.close();
                        });
                    });
                }
            }

            @Override
            public void onFailure(Call<GoodsAddEntity> call, Throwable t) {

            }
        });

    }

    private void initPopView(View view) {

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        ApiInterface.ApiFactory.createApi().goodss_type(4, 13).enqueue(new Callback<GoodTypeEntity>() {
            @Override
            public void onResponse(Call<GoodTypeEntity> call, Response<GoodTypeEntity> response) {
                if (response.body().getCode() == 200) {
                    List<GoodTypeEntity.ResultBean> resultBeanList = response.body().getResult();
                    if (resultBeanList.size() > 0) {
                        commodityAdapter = new CommodityAdapter(TransferCouponsActivity.this, R.layout.item_mattersbuy, resultBeanList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(TransferCouponsActivity.this));
                        recyclerView.setAdapter(commodityAdapter);

                        commodityAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                                type_id = resultBeanList.get(position).getId();
                                Commodity.setText(resultBeanList.get(position).getType_name());
                                writemoview.setText("商家名称");
                                typename2 = "";
                                typename = resultBeanList.get(position).getType_name();
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

    private void initPopView2(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        ApiInterface.ApiFactory.createApi().goods_supplier(type_id).enqueue(new Callback<GoodsSupplierEntity>() {
            @Override
            public void onResponse(Call<GoodsSupplierEntity> call, Response<GoodsSupplierEntity> response) {
                if (response.body().getCode() == 200) {
                    List<GoodsSupplierEntity.ResultBean> resultBeanList = response.body().getResult();

                    GoodsSupplierEntity.ResultBean resultBean = new GoodsSupplierEntity.ResultBean();
                    resultBean.setSupplier_name("其他");
                    resultBeanList.add(resultBean);
                    if (resultBeanList.size() > 0) {

                        writemoviewAdapter = new WritemoviewAdapter(TransferCouponsActivity.this, R.layout.item_mattersbuy, resultBeanList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(TransferCouponsActivity.this));
                        recyclerView.setAdapter(writemoviewAdapter);
                        writemoviewAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(ViewGroup parent, View view, Object o, int position) {

                                if (resultBeanList.get(position).getSupplier_name().equals("其他")) {
                                    DialogUtils.getInstance().showSimpleDialog(context, R.layout.dialog_accountse, R.style.dialog, (contentView, utils) -> {
                                        utils.setCancelable(false);
                                        EditText tips = contentView.findViewById(R.id.tips);
                                        TextView cancel = contentView.findViewById(R.id.cancel);
                                        TextView submit = contentView.findViewById(R.id.submit);
                                        cancel.setOnClickListener(v -> {
                                            writemoview.setText("商家名称");
                                            typename2 = "";
                                            utils.close();
                                        });
                                        submit.setOnClickListener(v -> {
                                            if (tips.getText().toString().equals("") || TextUtils.isEmpty(tips.getText().toString())) {
                                                ToastUtil.show("请输入内容!", 200);
                                                return;
                                            }
                                            ApiInterface.ApiFactory.createApi().add_goods_supplier(tips.getText().toString()).enqueue(new Callback<UpdateTemporaryEntity>() {
                                                @Override
                                                public void onResponse(Call<UpdateTemporaryEntity> call, Response<UpdateTemporaryEntity> response) {
                                                    if (response.body().getCode() == 200) {
                                                        type_id2 = Integer.parseInt(response.body().getResult().toString());
                                                        writemoview.setText(tips.getText().toString());
                                                        typename2 = tips.getText().toString();
                                                        utils.close();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<UpdateTemporaryEntity> call, Throwable t) {

                                                }
                                            });


                                        });
                                    });


                                } else {
                                    type_id2 = resultBeanList.get(position).getId();
                                    writemoview.setText(resultBeanList.get(position).getSupplier_name());
                                    typename2 = resultBeanList.get(position).getSupplier_name();
                                }
                                popWindow2.dissmiss();
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
            public void onFailure(Call<GoodsSupplierEntity> call, Throwable t) {

            }
        });

    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = TransferCouponsActivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        TransferCouponsActivity.this.getWindow().setAttributes(lp);
        TransferCouponsActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
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
                        Toast.makeText(TransferCouponsActivity.this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
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
                    if (status == 1) {
                        pics = url;

                        new Thread() {
                            public void run() {

                                handler.post(runnableUi);
                            }
                        }.start();
                    } else if (status == 2) {
                        pics2 = url;
                        new Thread() {
                            public void run() {

                                handler.post(runnableUi);
                            }
                        }.start();

                    }
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

                uploadstatus.setText("已上传");
            } else {

                uploads.setText("已上传");
            }

        }

    };


}
