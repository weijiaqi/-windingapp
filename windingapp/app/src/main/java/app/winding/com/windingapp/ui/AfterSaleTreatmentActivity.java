package app.winding.com.windingapp.ui;

import android.Manifest;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.AfterSaleEntity;
import app.winding.com.windingapp.entity.GoodsAddEntity;
import app.winding.com.windingapp.entity.IDCard;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.popuwindow.PhotoPopupWindow;
import app.winding.com.windingapp.util.BitmapAndStringUtils;
import app.winding.com.windingapp.util.LoadingDialog;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.PhotoUtils;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
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

/**
 * 更新凭证
 */
public class AfterSaleTreatmentActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;

    @Bind(R.id.Update)
    TextView Update;
    @Bind(R.id.sweepcode)
    ImageView sweepcode;
    @Bind(R.id.rlupdate)
    RelativeLayout rlupdate;
    @Bind(R.id.submit)
            TextView submit;
    String pics = "";
    LoadingDialog loadingDialog;
    private Handler handler = null;


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
   int id;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_after_sale_treatment;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        handler = new Handler();
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("售后处理");
        id=getIntent().getExtras().getInt("id");
        if (id==2){
            rlupdate.setVisibility(View.GONE);
            submit.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.submit, R.id.rlupdate, R.id.recharge_anomaly})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.submit:

                if (pics == null || TextUtils.isEmpty(pics)) {
                    ToastUtil.show("请上传凭证截图!", 200);
                    return;
                }
                String order_id = getIntent().getExtras().getString("order_id", "");
                confirm_new_voucher(order_id, pics);


                break;
            case R.id.rlupdate:
                mPhotoPopupWindow = new PhotoPopupWindow(AfterSaleTreatmentActivity.this, v -> {
                    backgroundAlpha(1.0f);
                    requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                        @Override
                        public void granted() {
                            PhotoUtils.openPic(AfterSaleTreatmentActivity.this, CODE_GALLERY_REQUEST);
                            mPhotoPopupWindow.dismiss();
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(AfterSaleTreatmentActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
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
                                    imageUri = FileProvider.getUriForFile(AfterSaleTreatmentActivity.this, "app.winding.com.windingapp.fileProvider", fileUri);
                                PhotoUtils.takePicture(AfterSaleTreatmentActivity.this, imageUri, CODE_CAMERA_REQUEST);
                                mPhotoPopupWindow.dismiss();
                            } else {
                                Toast.makeText(AfterSaleTreatmentActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                                Log.e("asd", "设备没有SD卡");
                                mPhotoPopupWindow.dismiss();
                            }
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(AfterSaleTreatmentActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                            mPhotoPopupWindow.dismiss();
                        }
                    });

                });
                View rootView = LayoutInflater.from(AfterSaleTreatmentActivity.this)
                        .inflate(R.layout.activity_main, null);
                mPhotoPopupWindow.showAtLocation(rootView,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.recharge_anomaly:
                after_sale(getIntent().getExtras().getString("order_id", ""));
                break;
        }
    }

    public void after_sale(String order_id) {
        ApiInterface.ApiFactory.createApi().after_sale(order_id).enqueue(new Callback<AfterSaleEntity>() {
            @Override
            public void onResponse(Call<AfterSaleEntity> call, Response<AfterSaleEntity> response) {
                if (response.body().getCode() == 200) {
                    ToastUtil.show("申请成功", 200);
                    NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AfterSaleEntity> call, Throwable t) {

            }
        });
    }

    public void confirm_new_voucher(String type_id, String new_voucher) {
        ApiInterface.ApiFactory.createApi().confirm_new_voucher(type_id, new_voucher).enqueue(new Callback<GoodsAddEntity>() {
            @Override
            public void onResponse(Call<GoodsAddEntity> call, Response<GoodsAddEntity> response) {
                if (response.body().getCode() == 200) {
                    ToastUtil.show("更新成功", 200);
                    NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
                    finish();
                } else {
                    ToastUtil.show(response.body().getMessage().toString(), 200);
                }
            }

            @Override
            public void onFailure(Call<GoodsAddEntity> call, Throwable t) {

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
                        Toast.makeText(AfterSaleTreatmentActivity.this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
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


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = AfterSaleTreatmentActivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        AfterSaleTreatmentActivity.this.getWindow().setAttributes(lp);
        AfterSaleTreatmentActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
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
            Update.setText("已上传");


        }

    };


}
