package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.ClassifyEntity;
import app.winding.com.windingapp.entity.RecomEntity;
import app.winding.com.windingapp.util.GlideUtils;

/**
 * Created by Administrator on 2019/2/22.
 */

public class RecomAdapter extends RecycleAdapter<RecomEntity.ResultBean> {
    public RecomAdapter(Context context, int layoutId, List<RecomEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RecomEntity.ResultBean resultBean) {
        holder.setText(R.id.title, resultBean.getGood_name());
        holder.setText(R.id.Presentprice, resultBean.getPrice());
        holder.setText(R.id.Originalprice, resultBean.getMoney());
        TextView Originalprice = holder.getConvertView().findViewById(R.id.Originalprice);
        Originalprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        ImageView pic = holder.getConvertView().findViewById(R.id.pic);
        if (resultBean.getGood_img().size() != 0) {
            GlideUtils.loadImgWithGlide(resultBean.getGood_img().get(0).toString(), pic);
        }

    }
}

