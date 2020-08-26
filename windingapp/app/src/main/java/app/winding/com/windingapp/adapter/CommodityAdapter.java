package app.winding.com.windingapp.adapter;

import android.content.Context;

import java.util.List;

import app.winding.com.windingapp.R;

import app.winding.com.windingapp.entity.GoodTypeEntity;


/**
 * Created by Administrator on 2019/3/11.
 */

public class CommodityAdapter extends RecycleAdapter<GoodTypeEntity.ResultBean> {
    public CommodityAdapter(Context context, int layoutId, List<GoodTypeEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, GoodTypeEntity.ResultBean resultBean) {
        holder.setText(R.id.walletname, resultBean.getType_name());

    }
}
