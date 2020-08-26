package app.winding.com.windingapp.util.code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import app.winding.com.windingapp.R;
import app.winding.com.windingapp.adapter.CityAdapter;
import app.winding.com.windingapp.adapter.CityHotAdapter;
import app.winding.com.windingapp.adapter.RecycleAdapter;
import app.winding.com.windingapp.api.ApiInterface;
import app.winding.com.windingapp.base.BaseActivity;
import app.winding.com.windingapp.entity.CascadeEntity;
import app.winding.com.windingapp.entity.CascadesEntity;
import app.winding.com.windingapp.entity.CityLetterEntity;
import app.winding.com.windingapp.util.ToastUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_titles)
    TextView tvTitles;
    @Bind(R.id.select_china)
    LinearLayout select_china;
    @Bind(R.id.rcyPopular)
    RecyclerView rcyPopular;
    @Bind(R.id.search)
    EditText search;
    @Bind(R.id.remen)
    TextView remen;
    @Bind(R.id.rltwo)
    RelativeLayout rltwo;
    @Bind(R.id.rcyhot)
    RecyclerView rcyhot;
    private List<CityLetterEntity.ResultBean.ListBean> selectedCountries = new ArrayList<>();
    private List<CityLetterEntity.ResultBean.ListBean> allCountries = new ArrayList<>();
    CAdapter adapter;
    CityAdapter cityAdapter;
    RecyclerView rvPick;
    CityHotAdapter cityHotAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_pick;
    }

    @Override
    protected void onInitialization(Bundle bundle) {
        ivBack.setOnClickListener(v -> {
            finish();
        });
        tvTitles.setText("选择城市");
        rvPick = findViewById(R.id.rv_pick);
        SideBar side = findViewById(R.id.side);

        TextView tvLetter = findViewById(R.id.tv_letter);
        LinearLayoutManager manager = new LinearLayoutManager(PickActivity.this);
        rvPick.setLayoutManager(manager);
        rcyPopular.setLayoutManager(new GridLayoutManager(PickActivity.this, 3));

        search.setOnEditorActionListener((v, actionId, event) -> {
            /*判断是否是“搜索”键*/
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                String key = search.getText().toString().trim();
                if (TextUtils.isEmpty(key)) {
                    ToastUtil.show("请输入您想要搜索的城市", 200);
                    return true;
                }

                city_screen(key);

                return true;
            }
            return false;
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if (TextUtils.isEmpty(search.getText().toString()) || search.getText().toString().equals("")) {
                    remen.setVisibility(View.VISIBLE);
                    rcyPopular.setVisibility(View.VISIBLE);
                    rltwo.setVisibility(View.VISIBLE);
                    rcyhot.setVisibility(View.GONE);
                }
            }
        });
        city_hot();
        city_letter();


//        rvPick.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        side.addIndex("#", side.indexes.size());
        side.setOnLetterChangeListener(new SideBar.OnLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                tvLetter.setVisibility(View.GONE);
                tvLetter.setText(letter);
                int position = adapter.getLetterPosition(letter);
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }

            @Override
            public void onReset() {
                tvLetter.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void city_letter() {
        ApiInterface.ApiFactory.createApi().city_letter().enqueue(new Callback<CityLetterEntity>() {
            @Override
            public void onResponse(Call<CityLetterEntity> call, Response<CityLetterEntity> response) {
                allCountries.clear();
                for (int i = 0; i < response.body().getResult().size(); i++) {
                    allCountries.addAll(response.body().getResult().get(i).getList());
                }

                selectedCountries.clear();
                selectedCountries.addAll(allCountries);
                adapter = new CAdapter(selectedCountries);

                rvPick.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<CityLetterEntity> call, Throwable t) {

            }
        });
    }

    public void city_hot() {
        ApiInterface.ApiFactory.createApi().city_hot().enqueue(new Callback<CascadeEntity>() {
            @Override
            public void onResponse(Call<CascadeEntity> call, Response<CascadeEntity> response) {
                List<CascadeEntity.ResultBean> resultBeanList = response.body().getResult();
                cityAdapter = new CityAdapter(PickActivity.this, R.layout.item_popurcity, resultBeanList);
                rcyPopular.setAdapter(cityAdapter);
                cityAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                        Intent data = new Intent();
                        data.putExtra("name", resultBeanList.get(position).getCity());
                        data.putExtra("id", resultBeanList.get(position).getId()+"");
                        setResult(Activity.RESULT_OK, data);
                        finish();
                    }

                    @Override
                    public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                        return false;
                    }
                });
            }

            @Override
            public void onFailure(Call<CascadeEntity> call, Throwable t) {

            }
        });
    }

    class CAdapter extends PyAdapter<RecyclerView.ViewHolder> {

        public CAdapter(List<? extends PyEntity> entities) {
            super(entities);
        }

        @Override
        public RecyclerView.ViewHolder onCreateLetterHolder(ViewGroup parent, int viewType) {
            return new LetterHolder(getLayoutInflater().inflate(R.layout.item_letter, parent, false));
        }

        @Override
        public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
            return new VH(getLayoutInflater().inflate(R.layout.item_country_large_padding, parent, false));
        }

        @Override
        public void onBindHolder(RecyclerView.ViewHolder holder, PyEntity entity, int position) {
            VH vh = (VH) holder;
            final CityLetterEntity.ResultBean.ListBean country = (CityLetterEntity.ResultBean.ListBean) entity;

            vh.tvName.setText(country.getCity());

            holder.itemView.setOnClickListener(v -> {
                Intent data = new Intent();
                data.putExtra("name", country.getCity());
                data.putExtra("id", country.getId()+"");
                setResult(Activity.RESULT_OK, data);
                finish();
            });
        }

        @Override
        public void onBindLetterHolder(RecyclerView.ViewHolder holder, LetterEntity entity, int position) {
            ((LetterHolder) holder).textView.setText(entity.letter.toUpperCase());
        }
    }


    public void city_screen(String key) {
        ApiInterface.ApiFactory.createApi().city_screen(key).enqueue(new Callback<CascadesEntity>() {
            @Override
            public void onResponse(Call<CascadesEntity> call, Response<CascadesEntity> response) {
                if (response.body().getResult().size() > 0) {
                    rcyhot.setVisibility(View.VISIBLE);
                    remen.setVisibility(View.GONE);
                    rcyPopular.setVisibility(View.GONE);
                    rltwo.setVisibility(View.GONE);
                    List<CascadesEntity.ResultBean> resultBeanList = response.body().getResult();
                    cityHotAdapter = new CityHotAdapter(PickActivity.this, R.layout.item_popurcity, resultBeanList);
                    rcyhot.setLayoutManager(new LinearLayoutManager(PickActivity.this));
                    rcyhot.setAdapter(cityHotAdapter);
                    cityHotAdapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                            Intent data = new Intent();
                            data.putExtra("name", resultBeanList.get(position).getCity());
                            data.putExtra("id", resultBeanList.get(position).getId()+"");
                            setResult(Activity.RESULT_OK, data);
                            finish();
                        }

                        @Override
                        public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                            return false;
                        }
                    });
                } else {
                    ToastUtil.show("你搜索的城市不存在!", 200);
                }
            }

            @Override
            public void onFailure(Call<CascadesEntity> call, Throwable t) {
                ToastUtil.show("你搜索的城市不存在!", 200);
            }
        });
    }
}
