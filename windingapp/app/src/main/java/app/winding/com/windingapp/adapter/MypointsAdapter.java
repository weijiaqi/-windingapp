package app.winding.com.windingapp.adapter;

import android.content.Context;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.CascadesEntity;
import app.winding.com.windingapp.entity.InviteListEntity;

/**
 * Created by Administrator on 2019/3/13.
 */

public class MypointsAdapter extends RecycleAdapter<InviteListEntity.ResultBean.ListBean> {
    public MypointsAdapter(Context context, int layoutId, List<InviteListEntity.ResultBean.ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, InviteListEntity.ResultBean.ListBean resultBean) {
        holder.setText(R.id.name, resultBean.getUsername());
        holder.setText(R.id.time, resultBean.getRegtime());

    }
}
