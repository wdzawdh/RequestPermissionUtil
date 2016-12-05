package com.cw.requestpermissionutil;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_granted;
    private TextView tv_denied;
    private TextView tv_rational;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button multiBtn = (Button) findViewById(R.id.btn_request);
        tv_granted = (TextView) findViewById(R.id.tv_granted);
        tv_denied = (TextView) findViewById(R.id.tv_denied);
        tv_rational = (TextView) findViewById(R.id.tv_rational);
        multiBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        tv_granted.setText("");
        tv_denied.setText("");
        tv_rational.setText("");

        PermissionUtil.with(this)
                .add(Manifest.permission.CAMERA)
                .add(Manifest.permission.READ_CONTACTS)
                .add(Manifest.permission.READ_SMS)
                .add(Manifest.permission.READ_CALENDAR)
                .request(new ResultCallBack() {
                    @Override
                    void onGrantedAll() {
                        Toast.makeText(getApplicationContext(), "onGrantedAll", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    void onGranted(String... permissions) {
                        tv_granted.setText(Arrays.toString(permissions));
                    }

                    @Override
                    void onDenied(String... permissions) {
                        tv_denied.setText(Arrays.toString(permissions));
                    }

                    @Override
                    void onRationalShow(String... permissions) {
                        tv_rational.setText(Arrays.toString(permissions));
                    }
                });
    }
}

