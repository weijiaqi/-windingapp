package app.winding.com.windingapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.pager.BasePager;
import app.winding.com.windingapp.util.UiUtils;



public class TariffTransactionPagerAdapter extends PagerAdapter {
    private List<BasePager> basePagers;

    private boolean isDestroyItem;

    public TariffTransactionPagerAdapter(List<BasePager> basePagers) {
        this(basePagers, true);
    }

    public TariffTransactionPagerAdapter(List<BasePager> basePagers, boolean isDestroyItem) {
        this.isDestroyItem = true;
        this.basePagers = basePagers;
        this.isDestroyItem = isDestroyItem;
    }


    @Override
    public int getCount() {
        return basePagers.size();
    }

    public Object instantiateItem(ViewGroup container, int position) {
        try {
            container.addView(((BasePager)this.basePagers.get(position)).getView());
        } catch (Exception var4) {
            ;
        }

        return ((BasePager)this.basePagers.get(position)).getView();
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        if(this.isDestroyItem && this.basePagers.size() > 3) {
            container.removeView((View)object);
            object = null;
        }

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return UiUtils.getStringArray(R.array.tariff_transaction)[position];
    }
}
