package app.winding.com.windingapp.base.rx;


import app.winding.com.windingapp.base.BaseView;

public interface RxView<T> extends BaseView {

    void onReceiveData2Api(T t, boolean isMore);
}
