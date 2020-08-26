package app.winding.com.windingapp.adapter;

import android.content.Context;

import java.util.List;



public class TaskAdapter extends RecycleAdapter<String> {
    public TaskAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String  resultBean) {


    }
}