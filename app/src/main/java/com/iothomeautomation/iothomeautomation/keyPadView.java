package com.iothomeautomation.iothomeautomation;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.iothomeautomation.iothomeautomation.Adapter.AdapterData;
import com.iothomeautomation.iothomeautomation.Model.ModelData;
import com.iothomeautomation.iothomeautomation.Util.AppController;
import com.iothomeautomation.iothomeautomation.Util.ServerAPI;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.util.ArrayList;
import java.util.List;

import com.iothomeautomation.iothomeautomation.Adapter.AdapterDataKey;
import com.iothomeautomation.iothomeautomation.Model.ModelKeyData;



public class keyPadView extends AppCompatActivity {


    RecyclerView nRecyclerview;
    RecyclerView.Adapter nAdapter;
    RecyclerView.LayoutManager nManager;
    List<ModelKeyData> nItems;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_pad_view);



        nRecyclerview = (RecyclerView) findViewById(R.id.recyclerviewkey);
        pd = new ProgressDialog(keyPadView.this);
        nItems = new ArrayList<>();

        loadJson();

        nManager = new LinearLayoutManager(keyPadView.this,LinearLayoutManager.VERTICAL,false);
        nRecyclerview.setLayoutManager(nManager);
        nAdapter = new AdapterDataKey(keyPadView.this,nItems);
        nRecyclerview.setAdapter(nAdapter);
    }

    private void loadJson()
    {
        pd.setMessage("Loading Data");
        pd.setCancelable(false);
        pd.show();


        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_KEY,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelKeyData md = new ModelKeyData();
                                md.setDate(data.getString("Date"));
                                md.setLogin(data.getString("Login"));


                                nItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        nAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }
}
