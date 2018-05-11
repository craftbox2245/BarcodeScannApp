package com.barcodescannapp;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends RuntimePermissionsActivity {

    Button button;
    TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        detail = (TextView) findViewById(R.id.detail);

        MainActivity.super.requestAppPermissions(new
                        String[]{Manifest.permission.CAMERA
                }, R.string.runtime_permissions_txt
                , 20);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BarcodeCaptureActivity.class);
                intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                intent.putExtra(BarcodeCaptureActivity.UseFlash, false);
                startActivityForResult(intent, 9001);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == -1 && requestCode == 9001) {
            String barcodeValue = data.getStringExtra("Barcode");
            //Toast.makeText(Info.this, "" + barcodeValue, Toast.LENGTH_SHORT).show();
            try {
                detail.setText("" + barcodeValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("BarCodeError", barcodeValue);
        } /*else if (resultCode == 120) {
            finish();
        }*/
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        if (requestCode == 20) {
            button.setVisibility(View.VISIBLE);
            detail.setVisibility(View.VISIBLE);
        }
    }
}
