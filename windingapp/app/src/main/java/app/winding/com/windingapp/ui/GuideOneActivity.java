package app.winding.com.windingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.LoginActivity;
import app.winding.com.windingapp.adapter.GuidePageAdapter;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.SharedPreferencesUtils;

//引导页1
public class GuideOneActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager vp;
    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合


    //最后一页的按钮
    private ImageView ib_start;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_guide_one;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        ib_start = findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(v -> {
            SharedPreferencesUtils.saveSp(Constants.GUIDEONELOGIN, true);
            startActivity(new Intent(GuideOneActivity.this, LoginActivity.class));
            finish();
        });

        //加载ViewPager
        initViewPager();

    }


    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.mipmap.guideone};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        if (viewList.size()==1){
            ib_start.setVisibility(View.VISIBLE);
        }
        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 滑动后的监听
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        //判断是否是最后一页，若是则显示按钮


            if (position == imageIdArray.length - 1) {
                ib_start.setVisibility(View.VISIBLE);
            } else {
                ib_start.setVisibility(View.GONE);
            }



    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
