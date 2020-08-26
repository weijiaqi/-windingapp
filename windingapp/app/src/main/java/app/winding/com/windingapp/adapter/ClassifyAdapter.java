package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.entity.ClassifyEntity;
import app.winding.com.windingapp.util.GlideUtils;

/**
 * Created by Administrator on 2019/2/22.
 */

public class ClassifyAdapter extends RecycleAdapter<ClassifyEntity.ResultBean> {
    public ClassifyAdapter(Context context, int layoutId, List<ClassifyEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ClassifyEntity.ResultBean resultBean) {
        holder.setText(R.id.title, resultBean.getClassify_name());
        ImageView  pic = holder.getConvertView().findViewById(R.id.pic);
        GlideUtils.loadImgWithGlide(resultBean.getClassify_img(), pic);
    }
}
