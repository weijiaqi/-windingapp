package app.winding.com.windingapp.adapter;

import android.content.Context;

import java.util.List;

import app.winding.com.windingapp.R;

/**
 * Created by Administrator on 2019/2/23.
 */

public class TypeAllAdapter extends RecycleAdapter<String> {
    public TypeAllAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String resultBean) {
        holder.setText(R.id.title, resultBean.toString());

    }
}