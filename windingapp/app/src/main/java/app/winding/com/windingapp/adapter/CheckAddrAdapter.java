package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.ClassifyEntity;
import app.winding.com.windingapp.entity.TaskClassifylistEntity;
import app.winding.com.windingapp.entity.UserinfoEntity;
import app.winding.com.windingapp.util.GlideUtils;

/**
 * Created by Administrator on 2019/2/25.
 */

public class CheckAddrAdapter extends RecycleAdapter<TaskClassifylistEntity.ResultBean> {
    public CheckAddrAdapter(Context context, int layoutId, List<TaskClassifylistEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TaskClassifylistEntity.ResultBean resultBean) {
        holder.setText(R.id.title, resultBean.getClassify_name());
    }
}
