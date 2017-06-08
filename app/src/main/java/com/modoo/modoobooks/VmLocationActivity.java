package com.modoo.modoobooks;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.modoo.modoobooks.db.CONFIG;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VmLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    List<String> vmList = new ArrayList<>();
    @BindView(R.id.et_vm_search_name)
    EditText et_vm_search_name;
    @BindView(R.id.btn_vm_search_name)
    Button btn_vm_search_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vm_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ButterKnife.bind(this);

        btn_vm_search_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchedValue = et_vm_search_name.getText().toString();

                for(int i=0; i<vmList.size(); i++){
                    String[] cols = vmList.get(i).split("#");

                    if(cols[0].equals(searchedValue)){
                        double lat = Double.parseDouble(cols[1]);
                        double lng = Double.parseDouble(cols[2]);
                        LatLng latlng = new LatLng(lat, lng);
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18.0f));
                    }

                }


            }
        });

        SelectAllVmTask selectAllVmTask = new SelectAllVmTask();
        selectAllVmTask.execute();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.6283032, 127.4561366), 15.0f));
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("DB", "vmList: " + vmList.size());
                for(String row : vmList){
                    String[] col = row.split("#");
                    String title = col[0];
                    Double lat = Double.parseDouble(col[1]);
                    Double lng = Double.parseDouble(col[2]);
                    LatLng latLng = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(latLng).title(title));
                }
            }
        },2000);


    }



     class SelectAllVmTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .build();

            Request request = new Request.Builder()
                    .url(CONFIG.MODOO_SELET_ALL_VM_LIST)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                String returnValue = response.body().string();


                return returnValue;
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String paramValue) {
            super.onPostExecute(paramValue);

            Log.d("DB", paramValue);

            String[] rows = paramValue.split("&");

            Log.d("DB", "rows : " + rows.length);

            for(String row : rows){
               vmList.add(row);
            }
        }
    }

}



