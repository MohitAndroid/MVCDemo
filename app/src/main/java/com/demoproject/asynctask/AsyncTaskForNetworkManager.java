package com.demoproject.asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.demoproject.R;
import com.demoproject.configuration.AppConstants;
import com.demoproject.interfaces.ResponseListener;
import com.demoproject.model.WebserviceModel;
import com.demoproject.model.WebserviceModelResponse;
import com.demoproject.network.NetworkManager;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.RequestBody;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AsyncTaskForNetworkManager extends
        AsyncTask<RequestBody, String, Object> {
    private RequestBody requestBody;
    private Context context;
    private ProgressDialog progressDialog;
    private NetworkManager networkManager;
    private ResponseListener responseListener;

    public AsyncTaskForNetworkManager(Context context, ResponseListener responseListener, RequestBody requestBody) {
        this.requestBody = requestBody;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getString(R.string.get_response));
        networkManager = NetworkManager.getInstance();
        this.responseListener = responseListener;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected Object doInBackground(RequestBody... params) {

        try {
            WebserviceModelResponse webserviceModelResponse = new WebserviceModelResponse();
            String jsonResponse = networkManager.postData(AppConstants.URL, requestBody);
            final JSONObject objMain = new JSONObject(jsonResponse);
            if (objMain.getString("status").equalsIgnoreCase("true")) {
                final JSONArray jsonReportArray = objMain.getJSONArray("mainArray");
                final Type typeFormEntity = new TypeToken<List<WebserviceModel>>() {
                }.getType();
                webserviceModelResponse.setWebserviceModels((((ArrayList<WebserviceModel>) new GsonBuilder().create().fromJson(jsonReportArray.toString(), typeFormEntity))));
                return webserviceModelResponse;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
        progressDialog.dismiss();
        responseListener.onResponse(object);
    }
}