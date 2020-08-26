package app.winding.com.windingapp;

import android.*;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.view.EasyNavigationBar;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.service.DownloadService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.fragment.IndexFragment;
import app.winding.com.windingapp.fragment.MarginBuyingFragment;
import app.winding.com.windingapp.fragment.TaskFragment;
import app.winding.com.windingapp.fragment.UserFargment;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.ui.AboutMeActivity;
import app.winding.com.windingapp.util.CProgressDialogUtils;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.HProgressDialogUtils;
import app.winding.com.windingapp.util.LogUtil;
import app.winding.com.windingapp.util.OkGoUpdateHttpUtil;
import app.winding.com.windingapp.util.PhoneUtil;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.code.PyAdapter;
import app.winding.com.windingapp.window.MoreWindow;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends BaseActivity {
    @Bind(R.id.navigationBar)
    EasyNavigationBar navigationBar;
    @Bind(R.id.id_container)
    RelativeLayout idContainer;
    private String[] tabText = {"首页", "任务", "发布", "订单", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.index, R.mipmap.task, R.mipmap.ic_tab_more, R.mipmap.order, R.mipmap.user};
    //选中时icon
    private int[] selectIcon = {R.mipmap.indexred, R.mipmap.taskred, R.mipmap.ic_tab_more, R.mipmap.orderred, R.mipmap.userred};
    private List<Fragment> fragments = new ArrayList<>();
    MoreWindow mMoreWindow;
    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;
    public static MainActivity instance;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ButterKnife.bind(this);

        String token= SharedPreferencesUtils.getInt(Constants.ID,0)+"";
        JPushInterface.setAlias(context, token, (i, s, set) -> {
                if (i==0){
                    LogUtil.e(TAG,"设置成功");
                }
        });

        instance = this;
        fragments.add(new IndexFragment());
        fragments.add(new TaskFragment());
        fragments.add(new MarginBuyingFragment());
        fragments.add(new UserFargment());

        //Tab点击事件  return true 页面不会切换
        navigationBar.titleItems(tabText)      //必传  Tab文字集合
                .normalIconItems(normalIcon)   //必传  Tab未选中图标集合
                .selectIconItems(selectIcon)   //必传  Tab选中图标集合
                .fragmentList(fragments)       //必传  fragment集合
                .fragmentManager(getSupportFragmentManager())     //必传
                .iconSize(20)     //Tab图标大小
                .tabTextSize(10)   //Tab文字大小
                .tabTextTop(2)     //Tab文字距Tab图标的距离
                .normalTextColor(Color.parseColor("#666666"))   //Tab未选中时字体颜色
                .selectTextColor(Color.parseColor("#FB374F"))   //Tab选中时字体颜色
                .scaleType(ImageView.ScaleType.CENTER_INSIDE)  //同 ImageView的ScaleType

                .onTabClickListener((view, position) -> {
                    if (position == 2) {
                        showMoreWindow();
                        return true;
                    }
                    return false;
                })
                .smoothScroll(false)  //点击Tab  Viewpager切换是否有动画
                .canScroll(false)    //Viewpager能否左右滑动
                .mode(EasyNavigationBar.MODE_ADD)   //默认MODE_NORMAL 普通模式  //MODE_ADD 带加号模式
                .anim(Anim.ZoomIn)                //点击Tab时的动画
                .addIconSize(36)    //中间加号图片的大小
                .addLayoutHeight(58)   //包含加号的布局高度 背景透明  所以加号看起来突出一块
                .navigationHeight(40)  //导航栏高度
                .addLayoutRule(EasyNavigationBar.RULE_CENTER) //RULE_CENTER 加号居中addLayoutHeight调节位置 EasyNavigationBar.RULE_BOTTOM 加号在导航栏靠下
                .addLayoutBottom(10)   //加号到底部的距离
                .hasPadding(true)    //true ViewPager布局在导航栏之上 false有重叠
                .hintPointLeft(-3)  //调节提示红点的位置hintPointLeft hintPointTop（看文档说明）
                .hintPointTop(-3)
                .hintPointSize(6)    //提示红点的大小
                .msgPointLeft(-10)  //调节数字消息的位置msgPointLeft msgPointTop（看文档说明）
                .msgPointTop(-10)
                .msgPointTextSize(9)  //数字消息中字体大小
                .msgPointSize(18)    //数字消息红色背景的大小
                .addAlignBottom(true)  //加号是否同Tab文字底部对齐  RULE_BOTTOM时有效；
                .addTextTopMargin(3)  //加号文字距离加号图片的距离
                .addTextSize(10)      //加号文字大小
                .addNormalTextColor(Color.parseColor("#666666"))    //加号文字未选中时字体颜色
                .addSelectTextColor(Color.parseColor("#FB374F"))    //加号文字选中时字体颜色
                .build();
        getPermission();
    }


    private void showMoreWindow() {
        if (null == mMoreWindow) {
            mMoreWindow = new MoreWindow(this);
            mMoreWindow.init(idContainer);
        }

        mMoreWindow.showMoreWindow(idContainer);
    }

    public EasyNavigationBar getNavigationBar() {
        return navigationBar;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                ToastUtil.show("再按一次退出程序", 25);
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
//                System.exit(0);
                //退出当前activity,返回系统桌面
                Intent home = new Intent(Intent.ACTION_MAIN);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
                        CProgressDialogUtils.showProgressDialog(MainActivity.this);
                    }

                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
                        CProgressDialogUtils.cancelProgressDialog(MainActivity.this);
                    }

                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp(String error) {

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
                        RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
                        rxPermissions.request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                                .subscribe(granted -> {
                                    if (granted) {
                                        utils.close();
                                        //显示下载进度
                                        if (isShowDownloadProgress) {
                                            updateAppManager.download(new DownloadService.DownloadCallback() {
                                                @Override
                                                public void onStart() {
                                                    HProgressDialogUtils.showHorizontalProgressDialog(MainActivity.this, "下载进度", false);
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
                                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
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
