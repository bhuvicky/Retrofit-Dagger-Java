package com.example.retrofit_dagger_java.dependency;

import com.example.retrofit_dagger_java.BuildConfig;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static Retrofit retrofit;

    @Provides
    Retrofit provideRetrofit(OkHttpClient.Builder builder) {
        return retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }

    @Provides
    OkHttpClient.Builder provideOkHttp(HttpLoggingInterceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder;
    }

    @Provides
    HttpLoggingInterceptor provideLoggingInterceptor() {
        return new HttpLoggingInterceptor();
    }

    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
