package app.winding.com.windingapp.window;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ms_square.etsyblur.BlurringView;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.animator.KickBackAnimator;
import app.winding.com.windingapp.ui.PurchaseFilmActivity;
import app.winding.com.windingapp.ui.ReplaceTasksActivity;
import app.winding.com.windingapp.ui.TransferActivity;
import app.winding.com.windingapp.ui.TransferCouponsActivity;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.JumpActivityUtil;

public class MoreWindow extends PopupWindow implements OnClickListener {

    private Activity mContext;
    private RelativeLayout layout;
    private ImageView close;
    private View bgView;
    private BlurringView blurringView;
    private int mWidth;
    private int mHeight;
    private int statusBarHeight;
    private Handler mHandler = new Handler();

    public MoreWindow(Activity context) {
        mContext = context;
    }

    /**
     * 初始化
     *
     * @param view 要显示的模糊背景View,一般选择跟布局layout
     */
    public void init(View view) {
        Rect frame = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;

        setWidth(mWidth);
        setHeight(mHeight);

        layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.more_window, null);

        setContentView(layout);

        close = (ImageView) layout.findViewById(R.id.iv_close);
        close.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    closeAnimation();
                }
            }

        });

        blurringView = layout.findViewById(R.id.blurring_view);
        blurringView.blurredView(view);//模糊背景

        bgView = layout.findViewById(R.id.rel);
        setOutsideTouchable(true);
        setFocusable(true);
        setClippingEnabled(false);//使popupwindow全屏显示
    }

    public int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 显示window动画
     *
     * @param anchor
     */
    public void showMoreWindow(View anchor) {

        showAtLocation(anchor, Gravity.TOP | Gravity.START, 0, 0);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //圆形扩展的动画
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        int x = mWidth / 2;
                        int y = (int) (mHeight - fromDpToPx(25));
                        Animator animator = ViewAnimationUtils.createCircularReveal(bgView, x,
                                y, 0, bgView.getHeight());
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
//                                bgView.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                //							layout.setVisibility(View.VISIBLE);
                            }
                        });
                        animator.setDuration(300);
                        animator.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        showAnimation(layout);

    }

    private void showAnimation(ViewGroup layout) {
        try {
            LinearLayout linearLayout = layout.findViewById(R.id.lin);
            LinearLayout linearLayout2 = layout.findViewById(R.id.lly);
            mHandler.post(() -> {
                //＋ 旋转动画
                close.animate().rotation(90).setDuration(400);
            });
            //菜单项弹出动画
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                final View child = linearLayout.getChildAt(i);
                child.setOnClickListener(this);
                child.setVisibility(View.INVISIBLE);
                mHandler.postDelayed(() -> {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(200);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(150);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }, i * 50 + 100);
            }

            for (int i = 0; i < linearLayout2.getChildCount(); i++) {
                final View child = linearLayout2.getChildAt(i);
                child.setOnClickListener(this);
                child.setVisibility(View.INVISIBLE);
                mHandler.postDelayed(() -> {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(200);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(150);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }, i * 50 + 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭window动画
     */
    private void closeAnimation() {
        mHandler.post(() -> close.animate().rotation(-90).setDuration(400));

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                int x = mWidth / 2;
                int y = (int) (mHeight - fromDpToPx(25));
                Animator animator = ViewAnimationUtils.createCircularReveal(bgView, x,
                        y, bgView.getHeight(), 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //							layout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
//                        bgView.setVisibility(View.GONE);
                        dismiss();
                    }
                });
                animator.setDuration(300);
                animator.start();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 点击事件处理
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (isShowing()) {
            closeAnimation();
        }

        switch (v.getId()) {
            case R.id.tv_sbs:
                //买电影票
                JumpActivityUtil.launchActivity(mContext, PurchaseFilmActivity.class);
                break;
            case R.id.tv_search:
                //转让电影票
                JumpActivityUtil.launchActivity(mContext, TransferActivity.class);
                break;
            case R.id.tv_course:
                //转让优惠券
                JumpActivityUtil.launchActivity(mContext,TransferCouponsActivity.class);
                break;
            case R.id.tv_task:
                //代下任务
                DialogUtils.getInstance().showSimpleDialog(mContext, R.layout.dialog_coming_soon, R.style.dialog, (contentView, utils) -> {
                    utils.setCancelable(false);
                    TextView cancel = contentView.findViewById(R.id.Getinto);
                    cancel.setOnClickListener(v2 -> {
                        utils.close();
                    });

                });

//                JumpActivityUtil.launchActivity(mContext, ReplaceTasksActivity.class);
                break;
        }

    }

    float fromDpToPx(float dp) {
        return dp * Resources.getSystem().getDisplayMetrics().density;
    }
}
