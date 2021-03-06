package app.winding.com.windingapp.util;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * Author     wildma
 * DATE       2017/07/16
 * Des	      ${友盟分享工具类}
 */

public class ShareUtils {

    /**
     * 分享链接
     */
    public static void shareWeb(final Activity activity, String WebUrl, String title, String description, String imageUrl, int imageID, SHARE_MEDIA platform) {
        UMWeb web = new UMWeb(WebUrl);//连接地址
        web.setTitle(title);//标题
        web.setDescription(description);//描述
        if (TextUtils.isEmpty(imageUrl)) {
            web.setThumb(new UMImage(activity, imageID));  //本地缩略图
        } else {
            web.setThumb(new UMImage(activity, imageUrl));  //网络缩略图
        }
        new ShareAction(activity)
                .setPlatform(platform)
                .withMedia(web)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(final SHARE_MEDIA share_media) {
                                      }

                    @Override
                    public void onError(final SHARE_MEDIA share_media, final Throwable throwable) {
                        if (throwable != null) {
                            Log.d("throw", "throw:" + throwable.getMessage());
                        }
                        activity.runOnUiThread(() -> {
                            switch (share_media) {
                                case WEIXIN:
                                    Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
                                    break;
                                case WEIXIN_CIRCLE:
                                    Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
                                    break;
                                case QZONE:
                                    Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
                                    break;
                                case QQ:
                                    Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
                                    break;
                                case SINA:
                                    Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
                                    break;
                            }


                        });
                    }

                    @Override
                    public void onCancel(final SHARE_MEDIA share_media) {

                    }
                })
                .share();

        //新浪微博中图文+链接
        /*new ShareAction(activity)
                .setPlatform(platform)
                .withText(description + " " + WebUrl)
                .withMedia(new UMImage(activity,imageID))
                .share();*/
    }
}
