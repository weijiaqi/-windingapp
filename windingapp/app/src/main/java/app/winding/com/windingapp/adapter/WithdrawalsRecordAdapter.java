package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.UserPresentListEntity;
import app.winding.com.windingapp.util.UiUtils;

/**
 * Created by Administrator on 2019/3/15.
 */

public class WithdrawalsRecordAdapter extends RecycleAdapter<UserPresentListEntity.ResultBean> {
    public WithdrawalsRecordAdapter(Context context, int layoutId, List<UserPresentListEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UserPresentListEntity.ResultBean resultBean) {
        TextView status = holder.getConvertView().findViewById(R.id.status);
        holder.setText(R.id.Cashwithdrawal, resultBean.getMoney());
        holder.setText(R.id.createtime, resultBean.getCreate_time()+"");
        switch (resultBean.getStatus()) {
            case 1:
                status.setText("成功");
                break;
            case 2:
                status.setText("失败");
                break;
            case 3:
                status.setText("提现中");
                break;
        }


    }
}

