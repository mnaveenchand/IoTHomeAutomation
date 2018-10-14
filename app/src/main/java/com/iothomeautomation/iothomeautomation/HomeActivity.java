package com.iothomeautomation.iothomeautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button buttonTemp;
    Button buttonRF;
    Button buttonKey;
    Button buttonApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonRF = findViewById(R.id.btnRFID);
        buttonTemp = findViewById(R.id.btnTempHumid);
        buttonKey = findViewById(R.id.btnKEY);
        buttonApp = findViewById(R.id.btnApp);

        buttonTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, listviewTemp.class);
                startActivity(intent);
            }

        });
        buttonRF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RFView.class);
                startActivity(intent);
            }
        });
        buttonKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, keyPadView.class);
                startActivity(intent);
    }
});
        buttonApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ApplianceControlActivity.class);
                startActivity(intent);
            }
        });


    }
}