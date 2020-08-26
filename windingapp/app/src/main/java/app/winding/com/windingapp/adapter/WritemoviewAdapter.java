package app.winding.com.windingapp.adapter;

import android.content.Context;

import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.entity.GoodTypeEntity;
import app.winding.com.windingapp.entity.GoodsSupplierEntity;
import app.winding.com.windingapp.entity.GoodsTypeEntity;

/**
 * Created by Administrator on 2019/3/11.
 */

public class WritemoviewAdapter extends RecycleAdapter<GoodsSupplierEntity.ResultBean> {
    public WritemoviewAdapter(Context context, int layoutId, List<GoodsSupplierEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, GoodsSupplierEntity.ResultBean resultBean) {
        holder.setText(R.id.walletname, resultBean.getSupplier_name());

    }
}

