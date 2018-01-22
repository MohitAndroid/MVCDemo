package com.demoproject.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.demoproject.R;
import com.demoproject.asynctask.AsyncTaskForNetworkManager;
import com.demoproject.interfaces.ResponseListener;
import com.demoproject.model.AddressModel;
import com.demoproject.model.EmployeeModel;
import com.demoproject.model.WebserviceModel;
import com.demoproject.model.WebserviceModelResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ResponseListener , OnMapReadyCallback {

    private GoogleMap mMap;
    RecyclerView recyclerView;
    List<WebserviceModel> webserviceModels = new ArrayList<>();
    CustomAdapter customAdapter;

    String jsonString = " [\n" +
            "    {\n" +
            "      \"street\": \"BTM 1st Stage\",\n" +
            "      \"city\": \"Bangalore\",\n" +
            "      \"zipcode\": 560100\n" +
            "    },\n" +
            "    {\n" +
            "      \"street\": \"BTM 1st Stage\",\n" +
            "      \"city\": \"Bangalore\",\n" +
            "      \"zipcode\": 560100\n" +
            "    }\n" +
            "  ]\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        customAdapter = new CustomAdapter(webserviceModels);
//        recyclerView.setAdapter(customAdapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        callWebservice();


//        EmployeeModel employeeModel = new Gson().fromJson(jsonString, EmployeeModel.class);
//        List<AddressModel> employeeModel = Arrays.asList(new Gson().fromJson(jsonString, AddressModel[].class));
//
//        Log.d("APP-RESPONSE", employeeModel.toString());


//        AIzaSyDwubgs73mrqULbJmE4vxduU15AQgoN3LU


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    public void callWebservice() {
        new AsyncTaskForNetworkManager(this, this, generateJSONForWebService()).execute();
    }

    public RequestBody generateJSONForWebService() {
        final FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.addEncoded("key", "454545454");
        return builder.build();
    }

    @Override
    public void onResponse(Object object) {
        WebserviceModelResponse webserviceModelResponse = (WebserviceModelResponse) object;
        webserviceModels.clear();
        webserviceModels.addAll(webserviceModelResponse.getWebserviceModels());
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
