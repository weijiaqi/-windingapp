package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.CascadeEntity;
import app.winding.com.windingapp.entity.CityLetterEntity;

/**
 * Created by Administrator on 2019/3/7.
 */

public class CityAdapter  extends RecycleAdapter<CascadeEntity.ResultBean> {
    public CityAdapter(Context context, int layoutId, List<CascadeEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CascadeEntity.ResultBean resultBean) {
        holder.setText(R.id.title, resultBean.getCity());

    }
}
