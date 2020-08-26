package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.PushEntity;


/**
 * Created by Administrator on 2019/4/2.
 */

public class PushInfoAdapter extends RecycleAdapter<PushEntity.ResultBean> {
    public PushInfoAdapter(Context context, int layoutId, List<PushEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, PushEntity.ResultBean resultBean) {
        holder.setText(R.id.title,resultBean.getTitle());
        holder.setText(R.id.content,resultBean.getDetail());
    }
}


