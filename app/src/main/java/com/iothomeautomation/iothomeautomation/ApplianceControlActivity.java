package com.iothomeautomation.iothomeautomation;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;
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

public class ApplianceControlActivity extends AppCompatActivity {

    private String TAG = ApplianceControlActivity.class.getSimpleName();
    private TextView msgResponse;
    private ProgressDialog pDialog;
    ToggleButton tbutton1, tbutton2;

    // These tags will be used to cancel the requests


    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }
    public void Switch_on(final String appk) {
        pDialog.setMessage("switching control");
        pDialog.setCancelable(false);
        pDialog.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_APPLIANCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(ApplianceControlActivity.this, " Light: "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //startActivity( new Intent(MainActivity.this));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.cancel();
                        Toast.makeText(ApplianceControlActivity.this, "Error in switching light", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override

            protected Map<String, String> getParams() {
                Map<String,String> map = new HashMap<>();

                map.put("light",appk);
                map.put("state","on");

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
    public void Switch_off(final String appk) {
        pDialog.setMessage("switching control");
        pDialog.setCancelable(false);
        pDialog.show();

        StringRequest sendData = new StringRequest(Request.Method.POST, ServerAPI.URL_APPLIANCE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(ApplianceControlActivity.this, " Light: "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //startActivity( new Intent(MainActivity.this));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.cancel();
                        Toast.makeText(ApplianceControlActivity.this, "Error in switching light", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> map = new HashMap<>();

                map.put("light",appk);
                map.put("state","off");

                return map;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_control);


        tbutton1 = (ToggleButton)findViewById(R.id.toggleButton1);
        tbutton2 = (ToggleButton)findViewById(R.id.toggleButton2);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);


        tbutton1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String apc = "light1";

                if(tbutton1.isChecked())
                {
                    Switch_on(apc);
                }
                else {
                    Switch_off(apc);
                }
            }
        });
        tbutton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String apc = "light2";

                if(tbutton2.isChecked())
                {
                    Switch_on(apc);
                }
                else {
                    Switch_off(apc);
                }
            }
        });
    }
}
