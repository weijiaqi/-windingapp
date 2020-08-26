package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.InviteListEntity;
import app.winding.com.windingapp.entity.RewardListEntity;

/**
 * Created by Administrator on 2019/3/13.
 */

public class PayAdapter extends RecycleAdapter<RewardListEntity.ResultBean.ListBean> {
    public PayAdapter(Context context, int layoutId, List<RewardListEntity.ResultBean.ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RewardListEntity.ResultBean.ListBean resultBean) {
        holder.setText(R.id.name, resultBean.getType_name());
        TextView price = holder.getConvertView().findViewById(R.id.price);
        price.setText(resultBean.getMoney());
        holder.setText(R.id.time, resultBean.getCreate_time());

    }
}

