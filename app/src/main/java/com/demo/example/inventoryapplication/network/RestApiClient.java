package com.demo.example.inventoryapplication.network;

import android.content.Context;
import com.demo.example.inventoryapplication.InventoryApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiClient
{
    /*Application Base Api Url*/
    public static final String BaseApiUrl = "http://localhost:4000/inventory";
    private static final String TAG = "CLIENTLOG";
    private static RestApiClient _apiClientInstance;
    private static RestApiInterface _restApiInterface;

    private RestApiClient()
    {
        /* IGNORED */
    }

    public static RestApiClient getRestInstance()
    {
        if (_apiClientInstance == null)
        {
            _apiClientInstance = new RestApiClient();
        }
        return _apiClientInstance;
    }

    public RestApiInterface  getRetrofitApiInstance()
    {
        if (_restApiInterface == null)
        {
            OkHttpClient client = buildOkHttpClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseApiUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            _restApiInterface = retrofit.create(RestApiInterface.class);
        }
        return _restApiInterface;
    }

    private OkHttpClient buildOkHttpClient()
    {
        return new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .addNetworkInterceptor(new NetworkCacheInterceptor())
                .cache(buildAndGetCacheDirecory())
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }


//    public static Interceptor sOkHttpInterceptor = (chain) -> {
//        Request request = chain.request();
//
//            request = request.newBuilder().url(request.url()).build();
//
//
//        if(!NetworkChangeReceiver.isOnline()) {
//            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//        }
//
//        return chain.proceed(request);
//    };

    /**
     * build cache directory with custom size of cache
     * @return   cache
     */
    private Cache buildAndGetCacheDirecory()
    {
        //setup cache
        Context currentApplicationContext = InventoryApplication.getApplicationInstance().getApplicationContext();
        File httpCacheDirectory = new File(currentApplicationContext.getCacheDir(), "ChickenBuckResponse");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        //add cache to the client
        return cache;
    }
}
