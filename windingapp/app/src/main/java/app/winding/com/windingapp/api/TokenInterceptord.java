package app.winding.com.windingapp.api;


import android.text.TextUtils;

import java.io.IOException;


import app.winding.com.windingapp.model.Constants;
import app.winding.com.windingapp.util.SharedPreferencesUtils;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public class TokenInterceptord implements Interceptor {
    Request updateRequest;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!TextUtils.isEmpty((String) SharedPreferencesUtils.getSp(Constants.TOKEN, "")) && SharedPreferencesUtils.getSp(Constants.TOKEN, "") != null) {
            String token = (String) SharedPreferencesUtils.getSp(Constants.TOKEN, "");
             updateRequest = request.newBuilder()
                    .header("Authority", token)
                    .build();

        }else{
            updateRequest = request.newBuilder().build();
        }
        return chain.proceed(updateRequest);
    }

}

