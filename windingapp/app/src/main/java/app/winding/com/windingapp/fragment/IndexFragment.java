package app.winding.com.windingapp.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.MainActivity;
import app.winding.com.windingapp.R;
import app.winding.com.windingapp.activity.LoginActivity;
import app.winding.com.windingapp.activity.NoticeDetailsActivity;
import app.winding.com.windingapp.activity.TaskDetailActivity;
import app.winding.com.windingapp.adapter.ClassifyAdapter;
import app.winding.com.windingapp.adapter.RecomAdapter;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseFragment;
import app.winding.com.windingapp.entity.ArticleDetailsEntity;
import app.winding.com.windingapp.entity.ArticleEntity;
import app.winding.com.windingapp.entity.BannerEntity;
import app.winding.com.windingapp.entity.ClassifyEntity;
import app.winding.com.windingapp.entity.RecomEntity;
import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.ui.RechargeableRecoveryActivity;
import app.winding.com.windingapp.ui.SpacesItemDecoration;
import app.winding.com.windingapp.ui.TariffTransactionActivity;
import app.winding.com.windingapp.ui.TelephoneActivity;
import app.winding.com.windingapp.util.DialogUtils;
import app.winding.com.windingapp.util.GlideUtils;
import app.winding.com.windingapp.util.GridDividerItemDecoration;
import app.winding.com.windingapp.util.JumpActivityUtil;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import app.winding.com.windingapp.util.ToastUtil;
import app.winding.com.windingapp.util.UiUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import cc.ibooker.ztextviewlib.AutoVerticalScrollTextView;
import cc.ibooker.ztextviewlib.AutoVerticalScrollTextViewUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IndexFragment extends BaseFragment {

    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.Accumulated)
    AutoVerticalScrollTextView Accumulated;
    @Bind(R.id.rcymenu)
    RecyclerView rcymenu;
    @Bind(R.id.PopularOne)
    ImageView PopularOne;
    @Bind(R.id.PopularTwo)
    ImageView PopularTwo;
    @Bind(R.id.rcyfinance)
    RecyclerView rcyfinance;
    private List<BannerEntity.ResultBean> bannerlist;
    private List<String> images;

    private AutoVerticalScrollTextViewUtil aUtil;
    private ClassifyAdapter classifyAdapter;
    private RecomAdapter recomAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.index_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) throws IOException {
        rcymenu.setLayoutManager(new GridLayoutManager(mContext, 4));
        rcyfinance.setLayoutManager(new GridLayoutManager(mContext, 2));
        getBanner(2);  //轮播图
        setNotice(2);//公告
        get_classify();//首页类别
        getArticle_list(3, 2, 2);
        recom_goods(10); //首页推荐商品
    }

    public void getBanner(int type) {
        ApiInterface.ApiFactory.createApi().banner(type).enqueue(new Callback<BannerEntity>() {
            @Override
            public void onResponse(Call<BannerEntity> call, Response<BannerEntity> response) {
                if (response.body().getCode() == 200) {
                    bannerlist = response.body().getResult();
                    images = new ArrayList<>();
                    images.clear();
                    for (BannerEntity.ResultBean dataBean : bannerlist) {
                        images.add(dataBean.getImg());
                    }
                    //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
                    //可选样式如下:
                    //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
                    //2. Banner.NUM_INDICATOR   显示数字指示器
                    //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
                    //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
                    banner.setBannerStyle(Banner.NOT_INDICATOR);
                    //设置轮播样式（没有标题默认为右边,有标题时默认左边）
                    //可选样式:
                    //Banner.LEFT   指示器居左
                    //Banner.CENTER 指示器居中
                    //Banner.RIGHT  指示器居右
                    banner.setIndicatorGravity(Banner.CENTER);
                    //设置是否自动轮播（不设置则默认自动）

                    if (images.size() == 1) {
                        banner.isAutoPlay(false);
                    } else {
                        banner.isAutoPlay(true);
                        //设置轮播图片间隔时间（不设置默认为2000）
                        banner.setDelayTime(3000);
                    }


                    //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
                    //所有设置参数方法都放在此方法之前执行
                    //banner.setImages(images);

                    //自定义图片加载框架
                    banner.setImages(images, (view, url) -> {
                        Glide.with(mContext)
                                .asBitmap()
                                .load(url)
                                .into(view);
                    });

                } else if (response.body().getCode() == 21818) {
                    SharedPreferencesUtils.putString(Constants.TOKEN,"");
                    ToastUtil.show("登录失效,请重新登录!",200);
                    JumpActivityUtil.launchActivity(mContext, LoginActivity.class);
                }
            }

            @Override
            public void onFailure(Call<BannerEntity> call, Throwable t) {

            }
        });
    }

    public void setNotice(int type) {
        ApiInterface.ApiFactory.createApi().article_notice(type).enqueue(new Callback<ArticleDetailsEntity>() {
            @Override
            public void onResponse(Call<ArticleDetailsEntity> call, Response<ArticleDetailsEntity> response) {
                if (response.body().getCode() == 200) {

                    List<ArticleDetailsEntity.ResultBean> resultBeans = response.body().getResult();
                    if (resultBeans.size() > 0) {
                        ArrayList<CharSequence> demographicsList = new ArrayList<>();
                        ArrayList<CharSequence> demographicsList2 = new ArrayList<>();
                        for (int i = 0; i < resultBeans.size(); i++) {
                            demographicsList.add(resultBeans.get(i).getTitle());
                            demographicsList2.add(resultBeans.get(i).getId() + "");
                        }

                        aUtil = new AutoVerticalScrollTextViewUtil(Accumulated, demographicsList);
                        aUtil.setDuration(3000).setTextColor(Color.parseColor("#236398"))
                                .setTextSize(12).start();
                        // 点击事件监听
                        aUtil.setOnMyClickListener((i, charSequence) -> {


                            Bundle bundle = new Bundle();
                            bundle.putString("id", demographicsList2.get(i).toString());

                            JumpActivityUtil.launchActivity(mContext, NoticeDetailsActivity.class, bundle);
                        });
                    }

                } else if (response.body().getCode() == 21818) {
                    SharedPreferencesUtils.putString(Constants.TOKEN,"");
                    ToastUtil.show("登录失效,请重新登录!",200);
                    JumpActivityUtil.launchActivity(mContext, LoginActivity.class);
                }
            }

            @Override
            public void onFailure(Call<ArticleDetailsEntity> call, Throwable t) {

            }
        });
    }

    public void get_classify() {
        String token=SharedPreferencesUtils.getString(Constants.TOKEN,"");
        ApiInterface.ApiFactory.createApi().get_classify().enqueue(new Callback<ClassifyEntity>() {
            @Override
            public void onResponse(Call<ClassifyEntity> call, Response<ClassifyEntity> response) {
                if (response.body().getCode() == 200) {
                    List<ClassifyEntity.ResultBean> resultBeanList = response.body().getResult();
                    classifyAdapter = new ClassifyAdapter(mContext, R.layout.item_sify, resultBeanList);
                    rcymenu.setAdapter(classifyAdapter);
                    classifyAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                            if (resultBeanList.get(position).getId() == 1) {
                                JumpActivityUtil.launchActivity(mContext, TelephoneActivity.class);
                            } else if (resultBeanList.get(position).getId() == 2) {
                                JumpActivityUtil.launchActivity(mContext, TariffTransactionActivity.class);
                            } else if (resultBeanList.get(position).getId() == 3) {
                                JumpActivityUtil.launchActivity(mContext, RechargeableRecoveryActivity.class);
                            } else if (resultBeanList.get(position).getId() == 4) {
                                ((MainActivity) getActivity()).getNavigationBar().selectTab(1);
                                //调用第二个页面的方法
                                ((TaskFragment) (((MainActivity) getActivity()).getNavigationBar().getAdapter().getItem(1))).showToast(4);
                            } else if (resultBeanList.get(position).getId() == 5) {
//                                ((MainActivity) getActivity()).getNavigationBar().selectTab(1);
//                                //调用第二个页面的方法
//                                ((TaskFragment) (((MainActivity) getActivity()).getNavigationBar().getAdapter().getItem(1))).showToast(5);
                                DialogUtils.getInstance().showSimpleDialog(mContext, R.layout.dialog_coming_soon, R.style.dialog, (contentView, utils) -> {
                                    utils.setCancelable(false);
                                    TextView cancel = contentView.findViewById(R.id.Getinto);
                                    cancel.setOnClickListener(v2 -> {
                                        utils.close();
                                    });

                                });

                            } else if (resultBeanList.get(position).getId() == 6) {
                                ((MainActivity) getActivity()).getNavigationBar().selectTab(1);
                                //调用第二个页面的方法
                                ((TaskFragment) (((MainActivity) getActivity()).getNavigationBar().getAdapter().getItem(1))).showToast(6);
                            }else if (resultBeanList.get(position).getId() == 7){
                                DialogUtils.getInstance().showSimpleDialog(mContext, R.layout.dialog_coming_soon, R.style.dialog, (contentView, utils) -> {
                                    utils.setCancelable(false);
                                    TextView cancel = contentView.findViewById(R.id.Getinto);
                                    cancel.setOnClickListener(v -> {
                                        utils.close();
                                    });

                                });

                            }
                        }

                        @Override
                        public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                            return false;
                        }
                    });
                } else if (response.body().getCode() == 21818) {
                    SharedPreferencesUtils.putString(Constants.TOKEN,"");
                    ToastUtil.show("登录失效,请重新登录!",200);
                    JumpActivityUtil.launchActivity(mContext, LoginActivity.class);
                }
            }

            @Override
            public void onFailure(Call<ClassifyEntity> call, Throwable t) {

            }
        });
    }

    public void getArticle_list(int type, int limit, int is_recom) {
        ApiInterface.ApiFactory.createApi().article_list(type, limit, is_recom).enqueue(new Callback<ArticleEntity>() {
            @Override
            public void onResponse(Call<ArticleEntity> call, Response<ArticleEntity> response) {
                if (response.body().getCode() == 200) {
                    List<ArticleEntity.ResultBean> resultBeanList = response.body().getResult();
                    GlideUtils.loadImgWithGlide(resultBeanList.get(0).getImg(), PopularOne);
                    GlideUtils.loadImgWithGlide(resultBeanList.get(1).getImg(), PopularTwo);
                } else if (response.body().getCode() == 21818) {
                    SharedPreferencesUtils.putString(Constants.TOKEN,"");
                    ToastUtil.show("登录失效,请重新登录!",200);
                    JumpActivityUtil.launchActivity(mContext, LoginActivity.class);
                }
            }

            @Override
            public void onFailure(Call<ArticleEntity> call, Throwable t) {

            }
        });
    }


    public void recom_goods(int limt) {
        ApiInterface.ApiFactory.createApi().recom_goods(limt).enqueue(new Callback<RecomEntity>() {
            @Override
            public void onResponse(Call<RecomEntity> call, Response<RecomEntity> response) {
                if (response.body().getCode() == 200) {
                    if (response.body().getResult() != null && response.body().getResult().size() > 0) {
                        List<RecomEntity.ResultBean> resultBeanList = response.body().getResult();
                        recomAdapter = new RecomAdapter(mContext, R.layout.recom_item, resultBeanList);
                        rcyfinance.setAdapter(recomAdapter);
                        recomAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                                Bundle bundle=new Bundle();
                                bundle.putInt("id",resultBeanList.get(position).getId());
                                JumpActivityUtil.launchActivity(mContext, TaskDetailActivity.class,bundle);
                            }

                            @Override
                            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                                return false;
                            }
                        });
                    }
                } else if (response.body().getCode() == 21818) {
                    SharedPreferencesUtils.putString(Constants.TOKEN,"");
                    ToastUtil.show("登录失效,请重新登录!",200);
                    JumpActivityUtil.launchActivity(mContext, LoginActivity.class);
                }
            }

            @Override
            public void onFailure(Call<RecomEntity> call, Throwable t) {

            }
        });
    }
}
