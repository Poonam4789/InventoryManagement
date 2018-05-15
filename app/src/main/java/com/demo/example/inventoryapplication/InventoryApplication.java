package com.demo.example.inventoryapplication;

import android.app.Application;
import android.content.Context;

import com.demo.example.inventoryapplication.network.NetworkChangeReceiver;
import com.demo.example.inventoryapplication.network.RestApiClient;
import com.demo.example.inventoryapplication.network.RestApiInterface;
import com.facebook.stetho.Stetho;

/**
 * Created by poonampatel on 08/05/18.
 */

public class InventoryApplication extends Application
{
    private static InventoryApplication _inventoryApplication;
    private static RestApiInterface _restApiClientInterface;
    public static final String DEVELOPMENT_BUILD = "development", PRODUCTION_BUILD = "production";

    public static InventoryApplication getApplicationInstance()
    {
        return _inventoryApplication;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());

        _inventoryApplication = this;
        _restApiClientInterface = RestApiClient.getRestInstance().getRetrofitApiInstance();
        NetworkChangeReceiver.initNetworkChange(getApplicationContext());
    }
    public static RestApiInterface getRestApiClientInterface()
    {
        if (_restApiClientInterface == null)
        {
            return _restApiClientInterface = RestApiClient.getRestInstance().getRetrofitApiInstance();
        }
        return _restApiClientInterface;
    }

    public Context getApplicationContext()
    {
        return this;
    }
}
