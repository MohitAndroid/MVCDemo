package com.demoproject.network;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;


public class NetworkManager {

    private static final NetworkManager INSTANCE = new NetworkManager();

    private NetworkManager() {
    }

    public static NetworkManager getInstance() {
        return INSTANCE;
    }


    public static final MediaType JSON
            = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    /**
     * Post Data method for simple response
     */
    public String postData(final String URL, final RequestBody requestBody) {

        Response response;

        try {
            Log.d("TAG", "Posted Url: " + URL + "\n" + requestBody);
            final Request request = new Request.Builder()
                    .url(URL)
                    .post(requestBody)
                    .build();
            response = client.newCall(request).execute();
            String resp = "";
            if (response.isSuccessful()) {
                resp = response.body().string();
                Log.d("TAG", "Response : " + resp);
            } else {
                Log.d("TAG", "HTTP Fail, Response Code: " + response.message());
            }
            return resp;

        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage() != null) {
                Log.d("TAG", "Message : " + e.getMessage() + "\t" + e.fillInStackTrace());
            }
        }
        return null;
    }


}