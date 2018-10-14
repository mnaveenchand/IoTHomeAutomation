package com.iothomeautomation.iothomeautomation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.iothomeautomation.iothomeautomation.Util.AppController;
import com.iothomeautomation.iothomeautomation.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RFinsertData extends AppCompatActivity {
    EditText Uid,Name,Tagg;
    Button btnbatal,btnsimpan;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfinsert_data);


               /*get data from intent*/
        Intent data = getIntent();
        final int update = data.getIntExtra("update",0);

        String intent_Name = data.getStringExtra("Name");
        String intent_Tagg = data.getStringExtra("Tagg");
        String intent_Uid = data.getStringExtra("Uid");
        /*String intent_Faculty = data.getStringExtra("Faculty");
        /*end get data from intent*/


        Name = (EditText) findViewById(R.id.inp_Name);
        Tagg = (EditText) findViewById(R.id.inp_Tagg);
        Uid = (EditText) findViewById(R.id.inp_Uid);
        //Faculty= (EditText) findViewById(R.id.inp_faculty);
        btnbatal = (Button) findViewById(R.id.btn_cancel);
        btnsimpan = (Button) findViewById(R.id.btn_simpan);
        pd = new ProgressDialog(RFinsertData.this);

        /*kondisi update / insert*/
        if(update == 1)
        {
            btnsimpan.setText("Update Data");

            Name.setText(intent_Name);
            Tagg.setText(intent_Tagg);
            Uid.setText(intent_Uid);
            Uid.setVisibility(View.GONE);
            Tagg.setVisibility(View.GONE);
            //Faculty.setText(intent_Faculty);

        }


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(update == 1)
                {
                    Update_data();
                }else {
                    simpanData();
                }
            }
        });

        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(RFinsertData.this,RFView.class);
                startActivity(main);
            }
        });
    }

    private void Update_data()
    {
        pd.setMessage("Update Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest updateReq = new StringRequest(Request.Method.POST, ServerAPI.URL_UPDATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(RFinsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(RFinsertData.this,RFView.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(RFinsertData.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();

                map.put("Name",Name.getText().toString());
                map.put("Tag",Tagg.getText().toString());
                map.put("Uid",Uid.getText().toString());
                //map.put("Faculty",Faculty.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(updateReq);
    }



    private void simpanData()
    {

        pd.setMessage("Save Data");
        pd.setCancelable(false);
        pd.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_INSERT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(RFinsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(RFinsertData.this,RFView.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(RFinsertData.this, "pesan : Gagal Insert Data", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();

                map.put("Name",Name.getText().toString());
                map.put("Tag",Tagg.getText().toString());
                map.put("Uid",Uid.getText().toString());
                //map.put("Faculty",Faculty.getText().toString());

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
    }

