package app.winding.com.windingapp.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.TelephoneAdapter;
import app.winding.com.windingapp.base.BaseActivity;

import app.winding.com.windingapp.manage.log.Logger;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.pager.BasePager;
import app.winding.com.windingapp.pager.PrepaidRefillPager;
import app.winding.com.windingapp.pager.TelephoneRecoveryPager;

import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.UiUtils;
import butterknife.Bind;


public class TelephoneActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @Bind(R.id.sequencing_tab)
    TabLayout mTabLayout;
    @Bind(R.id.meal_viewpager)
    NoScrollViewPager mViewPager;
    public static TelephoneActivity instance;
    private List<BasePager> mBasePagers;

    private Class[] aClass = {TelephoneRecoveryPager.class, PrepaidRefillPager.class};

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_telephone;
    }

    @Override
    protected void onInitialization(Bundle bundle) throws IOException {
        instance=this;
        //设置横线长度
        mTabLayout.post(() -> {
            try {
                Class<?> tablayout = mTabLayout.getClass();
                Field tabStrip = tablayout.getDeclaredField("mTabStrip");
                tabStrip.setAccessible(true);
                LinearLayout ll_tab = (LinearLayout) tabStrip.get(mTabLayout);
                for (int i = 0; i < ll_tab.getChildCount(); i++) {
                    View child = ll_tab.getChildAt(i);
                    child.setPadding(0, 0, 0, 0);
                    LinearLayout.LayoutParams params = new
                            LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                    params.setMarginStart(UiUtils.dip2px((int) 20f));
                    params.setMarginEnd(UiUtils.dip2px((int) 20f));
                    child.setLayoutParams(params);
                    child.invalidate();
                }
            } catch (Exception e) {
            }
        });
        mBasePagers = new ArrayList<>();
        for (Class aClass1 : aClass) {
            BasePager basePager = createPager(aClass1);
            mBasePagers.add(basePager);
        }

        //----------------------------------------设置标题------------------------------------------
        String[] strings = UiUtils.getStringArray(R.array.tabs_telephone);
        for (String string : strings) {
            mTabLayout.addTab(mTabLayout.newTab().setText(string));
        }
        //------------------------------------------------------------------------------------------
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        TelephoneAdapter mAdapter = new TelephoneAdapter(mBasePagers);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
        mViewPager.addOnPageChangeListener(this);
    }


    //这里用反射的原因就是方便创建对象
    private <T extends BasePager> T createPager(Class<T> pager) {
        Constructor<T> constructor;
        T page = null;
        try {
            constructor = pager.getConstructor(Context.class);
            page = constructor.newInstance(this);
            Logger.i("CenterInfoFragment", "page--->" + page);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 1:
                NoticeObserver.getInstance().notifyObservers(Constants.PHONERecharge);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (BasePager mBasePager : mBasePagers) {
            mBasePager.onDestroy();
        }
    }


}
