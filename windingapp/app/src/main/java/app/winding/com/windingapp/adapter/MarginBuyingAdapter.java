package app.winding.com.windingapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.pager.BasePager;
import app.winding.com.windingapp.util.UiUtils;

/**
 * Created by Administrator on 2019/2/22.
 */

public class MarginBuyingAdapter extends PagerAdapter {
    private List<BasePager> basePagers;

    private boolean isDestroyItem = true;

    public MarginBuyingAdapter(List<BasePager> basePagers) {
        this.basePagers = basePagers;
    }


    @Override
    public int getCount() {
        return basePagers.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        try {
            container.addView(basePagers.get(position).getView());
        } catch (Exception e) {

        }
        return basePagers.get(position).getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        if (isDestroyItem && basePagers.size() > 5) {
            container.removeView((View) object);
            object = null;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return UiUtils.getStringArray(R.array.tabs_order)[position];
    }
}

