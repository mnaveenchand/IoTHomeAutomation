package com.iothomeautomation.iothomeautomation;

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

public class RFView extends AppCompatActivity {
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnInsert, btnDelete;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfview);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerviewTemp);
        btnInsert = (Button) findViewById(R.id.btn_insert);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        pd = new ProgressDialog(RFView.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(RFView.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterData(RFView.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RFView.this,RFinsertData.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hapus = new Intent(RFView.this,RFdelete.class);
                startActivity(hapus);
            }
        });
    }


    private void loadJson()
    {
        pd.setMessage("Loading Data");
        pd.setCancelable(false);
        pd.show();


        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA,
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
                                ModelData md = new ModelData();
                                md.setTime(data.getString("Time"));
                                md.setName(data.getString("Name"));
                                md.setTagg(data.getString("Tag"));
                                md.setUid(data.getString("Uid"));

                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
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
