package app.winding.com.windingapp.adapter;

import android.content.Context;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.CascadesEntity;

/**
 * Created by Administrator on 2019/3/7.
 */

public class CityHotAdapter  extends RecycleAdapter<CascadesEntity.ResultBean> {
    public CityHotAdapter(Context context, int layoutId, List<CascadesEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CascadesEntity.ResultBean resultBean) {
        holder.setText(R.id.title, resultBean.getCity());

    }
}
