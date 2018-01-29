package com.cw.requestpermissionutil;

import android.Manifest;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cw.requestpermissionutil.permission.PermissionInfo;
import com.cw.requestpermissionutil.permission.PermissionUtil;
import com.cw.requestpermissionutil.permission.ResultCallBack;

import java.util.Arrays;
import java.util.List;

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

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    public void onClick(View v) {
        tv_granted.setText("");
        tv_denied.setText("");
        tv_rational.setText("");

        PermissionUtil.with(this)
                .add(Manifest.permission.READ_CALENDAR)
                .add(Manifest.permission.CAMERA)
                .add(Manifest.permission.READ_CONTACTS)
                .add(Manifest.permission.RECORD_AUDIO)
                .add(Manifest.permission.CALL_PHONE)
                .add(Manifest.permission.BODY_SENSORS)
                .add(Manifest.permission.READ_SMS)
                .add(Manifest.permission.READ_EXTERNAL_STORAGE)
                .request(new ResultCallBack() {
                    @Override
                    public void onGrantedAll() {
                        Toast.makeText(getApplicationContext(), "onGrantedAll", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onGranted(String... permissions) {
                        tv_granted.setText(Arrays.toString(permissions));
                    }

                    @Override
                    public boolean onDenied(String... permissions) {
                        //返回true表示自己处理，不使用默认的PermissionDialog引导用户打开权限
                        return super.onDenied(permissions);
                    }

                    @Override
                    public void onRationalShow(String... permissions) {
                        tv_rational.setText(Arrays.toString(permissions));
                    }

                    @Override
                    public void onNotAgree(List<PermissionInfo> permissions) {
                        super.onNotAgree(permissions);
                    }
                });
    }
}

