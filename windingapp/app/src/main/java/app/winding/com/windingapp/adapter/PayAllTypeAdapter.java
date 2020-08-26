package app.winding.com.windingapp.adapter;

import android.content.Context;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.UserinfoEntity;

/**
 * Created by Administrator on 2019/3/14.
 */

public class PayAllTypeAdapter extends RecycleAdapter<UserinfoEntity.ResultBean> {
    public PayAllTypeAdapter(Context context, int layoutId, List<UserinfoEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UserinfoEntity.ResultBean resultBean) {
        holder.setText(R.id.title, resultBean.getClassify_name());
    }
}

