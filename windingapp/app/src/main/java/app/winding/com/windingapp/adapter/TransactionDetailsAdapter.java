package app.winding.com.windingapp.adapter;

import android.content.Context;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.MoneyLogEntity;

/**
 * Created by Administrator on 2019/3/22.
 */

public class TransactionDetailsAdapter extends RecycleAdapter<MoneyLogEntity.ResultBean.ListBean> {
    public TransactionDetailsAdapter(Context context, int layoutId, List<MoneyLogEntity.ResultBean.ListBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MoneyLogEntity.ResultBean.ListBean resultBean) {
        holder.setText(R.id.title, resultBean.getRemark().toString());
        holder.setText(R.id.type, resultBean.getUsername().toString());

        holder.setText(R.id.price, resultBean.getMoney().toString());
        holder.setText(R.id.date, resultBean.getCreate_time().toString());
    }
}
