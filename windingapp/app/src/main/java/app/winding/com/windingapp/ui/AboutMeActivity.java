package app.winding.com.windingapp.ui;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.model.HttpHeaders;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.service.DownloadService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.CProgressDialogUtils;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.HProgressDialogUtils;
import app.winding.com.windingapp.util.OkGoUpdateHttpUtil;
import app.winding.com.windingapp.util.PhoneUtil;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AboutMeActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.currentversion)
    TextView currentversion;
    @Bind(R.id.newversion)
    RelativeLayout newversion;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_about_me;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("关于我们");
        currentversion.setText("当前版本:" + PhoneUtil.getVersionName(this));
    }


    @OnClick({ R.id.newversion})
    public void onclick(View view) {
        switch (view.getId()) {

            case R.id.newversion:
                getPermission();
                break;

        }
    }


    public void getPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        //版本更新
                        versionUpdate(true);
                    } else {

                    }
                });
    }



    private void versionUpdate(boolean isShowDownloadProgress) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Map<String, String> params = new HashMap<>();
        /**需要向后台传的参数*/


        String version = String.valueOf(PhoneUtil.getVersionName(this));
        params.put("version", version);
        params.put("type", "1");

        new UpdateAppManager
                .Builder()
                //必须设置，当前Activity
                .setActivity(this)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new OkGoUpdateHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(ApiInterface.APP_VERSION)
                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(true)
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                .setParams(params)
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
                .setTargetPath(path)
                //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
//                .setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")

                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            JSONObject jsonObject1=jsonObject.getJSONObject("result");
                            updateAppBean
                                    //（必须）是否更新Yes,No
                                    .setUpdate(jsonObject1.optString("update"))
                                    //（必须）新版本号，
                                    .setNewVersion(jsonObject1.optString("new_version"))
                                    //（必须）下载地址
                                    .setApkFileUrl(jsonObject1.optString("apk_url"))
                                    //（必须）更新内容
                                    .setUpdateLog(jsonObject1.optString("update_log"))
                                    //是否强制更新，可以不设置
                                    .setConstraint(false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return updateAppBean;
                    }

                    /**
                     * 有新版本
                     *
                     * @param updateApp        新版本信息
                     * @param updateAppManager app更新管理器
                     */
                    @Override
                    public void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {


                        showDiyDialog(isShowDownloadProgress, updateApp, updateAppManager);

                    }

                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
                        CProgressDialogUtils.showProgressDialog(AboutMeActivity.this);
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
                        CProgressDialogUtils.cancelProgressDialog(AboutMeActivity.this);
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp(String error) {
                        Toast.makeText(AboutMeActivity.this, "没有新版本", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 自定义对话框
     *
     * @param isShowDownloadProgress
     * @param updateApp
     * @param updateAppManager
     */
    private void showDiyDialog(boolean isShowDownloadProgress, final UpdateAppBean updateApp, final UpdateAppManager updateAppManager) {
        String targetSize = updateApp.getTargetSize();
        String updateLog = updateApp.getUpdateLog();

        String msg = "";

        if (!TextUtils.isEmpty(targetSize)) {
            msg = "新版本大小：" + targetSize + "\n\n";
        }

        if (!TextUtils.isEmpty(updateLog)) {
            msg += updateLog;
        }
        DialogUtils.getInstance().showTimeDialog(
               this, R.layout.dialog_version,
                R.style.dialog_lhp, (contentView, utils) -> {

                    TextView tvUpdate = contentView.findViewById(R.id.tv_update);
                    ImageView iv_close = contentView.findViewById(R.id.iv_close);
                    TextView updatecontent = contentView.findViewById(R.id.update_content);
                    updatecontent.setText(updateApp.getUpdateLog());

                    tvUpdate.setOnClickListener(v -> {
                        RxPermissions rxPermissions = new RxPermissions(AboutMeActivity.this);
                        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                .subscribe(granted -> {
                                    if (granted) {
                                        utils.close();
                                        //显示下载进度
                                        if (isShowDownloadProgress) {
                                            updateAppManager.download(new DownloadService.DownloadCallback() {
                                                @Override
                                                public void onStart() {
                                                    HProgressDialogUtils.showHorizontalProgressDialog(AboutMeActivity.this, "下载进度", false);
                                                }

                                                /**
                                                 * 进度
                                                 *
                                                 * @param progress  进度 0.00 -1.00 ，总大小
                                                 * @param totalSize 总大小 单位B
                                                 */
                                                @Override
                                                public void onProgress(float progress, long totalSize) {
                                                    HProgressDialogUtils.setProgress(Math.round(progress * 100));
                                                }

                                                /**
                                                 *
                                                 * @param total 总大小 单位B
                                                 */
                                                @Override
                                                public void setMax(long total) {

                                                }


                                                @Override
                                                public boolean onFinish(File file) {
                                                    HProgressDialogUtils.cancel();
                                                    return true;
                                                }

                                                @Override
                                                public void onError(String msg) {
                                                    Toast.makeText(AboutMeActivity.this, msg, Toast.LENGTH_SHORT).show();
                                                    HProgressDialogUtils.cancel();

                                                }

                                                @Override
                                                public boolean onInstallAppAndAppOnForeground(File file) {
                                                    return false;
                                                }
                                            });
                                        } else {
                                            //不显示下载进度
                                            updateAppManager.download();
                                        }
                                    }
                                });
                    });
                    iv_close.setOnClickListener(v -> {
                        utils.close();

                    });

                });
    }
}
