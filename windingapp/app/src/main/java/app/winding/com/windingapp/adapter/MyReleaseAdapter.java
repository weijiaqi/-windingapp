package app.winding.com.windingapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.entity.MysGoodsEntity;
import app.winding.com.windingapp.entity.UpdateTemporaryEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.GlideUtils;
import app.winding.com.windingapp.util.NoticeObserver;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2019/3/1.
 */

public class MyReleaseAdapter extends RecycleAdapter<MysGoodsEntity.ResultBean> {

    public MyReleaseAdapter(Context context, int layoutId, List<MysGoodsEntity.ResultBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MysGoodsEntity.ResultBean resultBean) {
        holder.setText(R.id.title, resultBean.getGood_name());

        ImageView pic = holder.getConvertView().findViewById(R.id.pic);
        if (resultBean.getGood_img() != null && resultBean.getGood_img().length != 0) {
            String[] sst = resultBean.getGood_img();
            GlideUtils.loadImgWithGlide(sst[0], pic);
        }else{
            pic.setImageResource(R.mipmap.bitmaplogo);
        }


        holder.setText(R.id.Presentprice, String.format(UiUtils.getText(R.string.prices), resultBean.getPrice()));
        holder.setText(R.id.Originalprice, String.format(UiUtils.getText(R.string.Originalprice), resultBean.getMoney()));

        TextView Upper_shelf = holder.getConvertView().findViewById(R.id.Upper_shelf);

        if (resultBean.getStatus() == 1 && resultBean.getIs_sell() == 2) {

            Upper_shelf.setVisibility(View.VISIBLE);
            if (resultBean.getIs_shelf() == 1) {
                Upper_shelf.setText("下架");
            } else if (resultBean.getIs_shelf() == 2) {
                Upper_shelf.setText("上架");
            }
        } else {
            Upper_shelf.setVisibility(View.GONE);
        }

        Upper_shelf.setOnClickListener(v -> {
            if (Upper_shelf.getText().toString().equals("上架")) {
                ApiInterface.ApiFactory.createApi().goods_shelf(resultBean.getId(), 1).enqueue(new Callback<UpdateTemporaryEntity>() {
                    @Override
                    public void onResponse(Call<UpdateTemporaryEntity> call, Response<UpdateTemporaryEntity> response) {
                        if (response.body().getCode() == 200) {
                            ToastUtil.show("上架成功", 200);
                            Upper_shelf.setText("下架");
                            NoticeObserver.getInstance().notifyObservers(Constants.ADDCUUENCY);
                        }else {
                            ToastUtil.show(response.body().getMessage().toString(), 200);
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateTemporaryEntity> call, Throwable t) {

                    }
                });

            } else {

                ApiInterface.ApiFactory.createApi().goods_shelf(resultBean.getId(), 2).enqueue(new Callback<UpdateTemporaryEntity>() {
                    @Override
                    public void onResponse(Call<UpdateTemporaryEntity> call, Response<UpdateTemporaryEntity> response) {
                        if (response.body().getCode() == 200) {
                            ToastUtil.show("下架成功", 200);
                            Upper_shelf.setText("上架");
                            NoticeObserver.getInstance().notifyObservers(Constants.ADDCUUENCY);
                        } else if (response.body().getCode() == 500) {
                            ToastUtil.show(response.body().getMessage().toString(), 200);
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateTemporaryEntity> call, Throwable t) {

                    }
                });

            }
        });


    }


}