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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import app.winding.com.windingapp.AppManager;
import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.LoginActivity;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.IDCard;
import app.winding.com.windingapp.entity.UpdateHeadEntity;
import app.winding.com.windingapp.entity.UserInfosEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.popuwindow.PhotoPopupWindow;
import app.winding.com.windingapp.util.BitmapAndStringUtils;
import app.winding.com.windingapp.util.GlideUtils;
import app.winding.com.windingapp.util.JumpActivityUtil;
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

//设置
public class SettingActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.headpic)
    ImageView headimg;

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
    private LoadingDialog loadingDialog;
    String username;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("个人资料");
        //获取用户信息
        get_user_info();
    }

    public void get_user_info() {
        ApiInterface.ApiFactory.createApi().get_user_info().enqueue(new Callback<UserInfosEntity>() {
            @Override
            public void onResponse(Call<UserInfosEntity> call, Response<UserInfosEntity> response) {
                if (response.body().getCode().equals("00000")) {
                    if (response.body().getResult().getData().getHeadimgurl() != null && !TextUtils.isEmpty(response.body().getResult().getData().getHeadimgurl())) {
                        GlideUtils.loadImgWithGlide(response.body().getResult().getData().getHeadimgurl(), headimg);
                    }
                    username = response.body().getResult().getData().getUsername();
                }
            }

            @Override
            public void onFailure(Call<UserInfosEntity> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.headpic, R.id.rlnikename, R.id.exit})
    public void onclick(View view) {
        switch (view.getId()) {

            case R.id.rlnikename:
                Bundle bundle = new Bundle();
                bundle.putString("name", username);
                JumpActivityUtil.launchActivity(SettingActivity.this, UpdateNikenameActivity.class, bundle);
                break;

            case R.id.headpic:
                mPhotoPopupWindow = new PhotoPopupWindow(SettingActivity.this, v -> {
                    backgroundAlpha(1.0f);
                    requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new RequestPermissionCallBack() {
                        @Override
                        public void granted() {
                            PhotoUtils.openPic(SettingActivity.this, CODE_GALLERY_REQUEST);
                            mPhotoPopupWindow.dismiss();
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(SettingActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
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
                                    imageUri = FileProvider.getUriForFile(SettingActivity.this, "app.winding.com.windingapp.fileProvider", fileUri);
                                PhotoUtils.takePicture(SettingActivity.this, imageUri, CODE_CAMERA_REQUEST);
                                mPhotoPopupWindow.dismiss();
                            } else {
                                Toast.makeText(SettingActivity.this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
                                Log.e("asd", "设备没有SD卡");
                            }
                        }

                        @Override
                        public void denied() {
                            Toast.makeText(SettingActivity.this, "部分权限获取失败，正常功能受到影响", Toast.LENGTH_LONG).show();
                        }
                    });

                });
                View rootView = LayoutInflater.from(SettingActivity.this)
                        .inflate(R.layout.activity_main, null);
                mPhotoPopupWindow.showAtLocation(rootView,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.exit:
                SharedPreferencesUtils.saveSp(Constants.TOKEN, "");
                JumpActivityUtil.launchActivity(this, LoginActivity.class);
                AppManager.getAppManager().finishAllActivity();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 600, output_Y = 600;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(SettingActivity.this, imageUri, cropImageUri, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "app.winding.com.windingapp.fileProvider", new File(newUri.getPath()));
                        PhotoUtils.cropImageUri(SettingActivity.this, newUri, cropImageUri, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        Toast.makeText(SettingActivity.this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        try {
                            filepath = BitmapAndStringUtils.getPath(this, cropImageUri);
                            filename = filepath.substring(filepath.lastIndexOf("/") + 1);
                            uploadphoto();
                            showImages(bitmap);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }


    private void showImages(Bitmap bitmap) {
        headimg.setImageBitmap(bitmap);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = SettingActivity.this.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        SettingActivity.this.getWindow().setAttributes(lp);
        SettingActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
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

                    String url = p.getResult().toString();
                    update_head(url);


                }
            }
        });

    }


    public void update_head(String url) {
        ApiInterface.ApiFactory.createApi().update_head(url).enqueue(new Callback<UpdateHeadEntity>() {
            @Override
            public void onResponse(Call<UpdateHeadEntity> call, Response<UpdateHeadEntity> response) {
                if (response.body().getCode() == 200) {
                    loadingDialog.dismiss();
                    if (response.body().getResult() != null && !TextUtils.isEmpty(response.body().getResult())) {
                        NoticeObserver.getInstance().notifyObservers(Constants.HEADIMGURL, response.body().getResult());
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateHeadEntity> call, Throwable t) {
                loadingDialog.dismiss();
                ToastUtil.show("上传失败", 200);
            }
        });
    }


    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
