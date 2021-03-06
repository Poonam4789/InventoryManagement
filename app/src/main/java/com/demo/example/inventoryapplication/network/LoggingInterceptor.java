package com.demo.example.inventoryapplication.network;

import android.content.Context;
import android.util.Log;
import com.demo.example.inventoryapplication.InventoryApplication;

import java.io.IOException;
import java.io.OutputStreamWriter;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LoggingInterceptor implements Interceptor
{

    private static final String LOG_TAG = "OkHttp";
    /**
     * .header(key, val): will override preexisting headers identified by key
     * .addHeader(key, val): will add the header and don’t override preexisting ones
     *
     * @param chain network chain request
     * @return
     * @throws IOException
     */
    @Override
    public Response intercept(Chain chain) throws IOException
    {
       Request finalRequest = chain.request();

        long t1 = System.nanoTime();
        Log.d(LOG_TAG, String.format("--> Sending request %s on %s%n%s", finalRequest.url(), chain.connection(), finalRequest.headers()));

        Buffer requestBuffer = new Buffer();
        if (finalRequest.body() != null)
        {
            finalRequest.body().writeTo(requestBuffer);
            Log.d(LOG_TAG, requestBuffer.readUtf8());
        }
        Response response = chain.proceed(finalRequest);

        long t2 = System.nanoTime();
        Log.d(LOG_TAG, String.format("<-- Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        MediaType contentType = response.body().contentType();
        String content = response.body().string();

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(InventoryApplication.getApplicationInstance().openFileOutput("response.json", Context.MODE_PRIVATE));
            outputStreamWriter.write(content);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        Log.d(LOG_TAG, content);

        ResponseBody wrappedBody = ResponseBody.create(contentType, content);
        return response.newBuilder().body(wrappedBody).build();
    }
}
