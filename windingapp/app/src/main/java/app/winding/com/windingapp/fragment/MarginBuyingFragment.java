package app.winding.com.windingapp.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.LoginActivity;
import app.winding.com.windingapp.adapter.MarginBuyingAdapter;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.adapter.TaskAdapter;
import app.winding.com.windingapp.adapter.TypeAllAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseFragment;
import app.winding.com.windingapp.entity.UserinfoEntity;
import app.winding.com.windingapp.manage.log.Logger;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.pager.AftersalePager;
import app.winding.com.windingapp.pager.BasePager;
import app.winding.com.windingapp.pager.CompletedPager;
import app.winding.com.windingapp.pager.DeterminedPager;
import app.winding.com.windingapp.pager.PayAllPager;
import app.winding.com.windingapp.pager.TobepaidPager;
import app.winding.com.windingapp.pager.TobeusedPager;
import app.winding.com.windingapp.popuwindow.CheckAddrPopwindow;
import app.winding.com.windingapp.ui.NoScrollViewPager;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 订单
 */
public class MarginBuyingFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    @Bind(R.id.sequencing_tab)
    TabLayout mTabLayout;
    @Bind(R.id.meal_viewpager)
    NoScrollViewPager mViewPager;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.lly)
    LinearLayout lly;
    @Bind(R.id.linearlayout_base)
    LinearLayout linearlayout_base;
    private List<BasePager> mBasePagers;

    private Class[] aClass = {PayAllPager.class, TobepaidPager.class,DeterminedPager.class, TobeusedPager.class, CompletedPager.class, AftersalePager.class};


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_selling;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) throws IOException {
        ivBack.setVisibility(View.GONE);
        tvTitles.setText("我的订单");
        ivRight.setBackgroundResource(R.mipmap.alltype);
        ivRight.setOnClickListener(v -> {
            goods_classify();
        });


        mBasePagers = new ArrayList<>();
        for (Class aClass1 : aClass) {
            BasePager basePager = createPager(aClass1);
            mBasePagers.add(basePager);
        }

        //----------------------------------------设置标题------------------------------------------
        String[] strings = UiUtils.getStringArray(R.array.tabs_order);
        for (String string : strings) {
            mTabLayout.addTab(mTabLayout.newTab().setText(string));
        }
        //------------------------------------------------------------------------------------------

        MarginBuyingAdapter mAdapter = new MarginBuyingAdapter(mBasePagers);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
        mViewPager.addOnPageChangeListener(this);
    }

    //提示消息
    public void showToast(int str) {
       if (str==4){
           NoticeObserver.getInstance().notifyObservers(Constants.UPDATEPAYALL);
       }
    }



    public void goods_classify() {
        ApiInterface.ApiFactory.createApi().goods_classify().enqueue(new Callback<UserinfoEntity>() {
            @Override
            public void onResponse(Call<UserinfoEntity> call, Response<UserinfoEntity> response) {
                if (response.body().getCode() == 200) {
                    List<UserinfoEntity.ResultBean> resultBeanList = response.body().getResult();
                    if (resultBeanList.size() > 0) {
                        new CheckAddrPopwindow(getActivity(),resultBeanList).showPopupWindow(linearlayout_base);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserinfoEntity> call, Throwable t) {

            }
        });
    }


    //这里用反射的原因就是方便创建对象
    private <T extends BasePager> T createPager(Class<T> pager) {
        Constructor<T> constructor;
        T page = null;
        try {
            constructor = pager.getConstructor(Context.class);
            page = constructor.newInstance(getActivity());
            Logger.i("CenterInfoFragment", "page--->" + page);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        for (BasePager mBasePager : mBasePagers) {
            mBasePager.onDestroy();
        }
        ButterKnife.unbind(this);
    }


}
