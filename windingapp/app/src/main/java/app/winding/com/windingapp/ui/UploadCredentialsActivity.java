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
import app.winding.com.windingapp.entity.CancelEntity;
import app.winding.com.windingapp.entity.IDCard;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.popuwindow.PhotoPopupWindow;
import app.winding.com.windingapp.util.BitmapAndStringUtils;
import app.winding.com.windingapp.util.LoadingDialog;
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

//上传凭证
public class UploadCredentialsActivity extends BaseActivity {

    @Bind(R.id.tv_titles)
    TextView tvTitles;


    @Bind(R.id.Update)
    TextView Update;



    @Override
    protected int getLayoutResource() {
        return R.layout.activity_upload_credentials;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        tvTitles.setText("上传凭证");

    }


}
