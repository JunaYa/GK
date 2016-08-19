package gank.sin.me.gk.dagger.module;

import android.content.res.Resources;

import com.google.gson.GsonBuilder;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import gank.sin.me.gk.R;
import gank.sin.me.gk.data.remote.GankApi;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by sin on 2016/8/5.
 */
@Module
public class ApiModule {

    @Provides
    @Named("api_url")
    public String providesApiUrl(Resources resources) {
        return resources.getString(R.string.api_url);
    }

    @Provides
    public Factory providesConverterFactory() {
        GsonBuilder gsonFactory = new GsonBuilder();
        gsonFactory.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return GsonConverterFactory.create(gsonFactory.create());
    }

    @Provides
    public HttpLoggingInterceptor providesHttpLogger() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(Level.BODY);
        return interceptor;
    }

    @Provides
    public OkHttpClient providesHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new Builder().addInterceptor(loggingInterceptor).build();
    }

    @Provides
    public Retrofit providesRetrofit(Factory converterFactory, @Named("api_url") String url, OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(url).client(client).addConverterFactory(converterFactory).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    @Provides
    GankApi providesApi(Retrofit retrofit) {
        return  retrofit.create(GankApi.class);
    }


}
