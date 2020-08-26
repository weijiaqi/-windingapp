package app.winding.com.windingapp.base;

public interface Presenter<V> {
    void attachView(V mvpView);
    void detachView();
}
