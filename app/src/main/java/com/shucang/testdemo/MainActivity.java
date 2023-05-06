package com.shucang.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends Activity {
    public TextView textUrl;
    public TextView textParam1;
    public TextView textParam1Title;
    public TextView textParam2;
    public TextView textParam2Title;
    String key = "12345678901234567890";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.SetTextView();
        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                String data = uri.getQuery();
                String name = uri.getQueryParameter("name");
                textUrl.setText(uri.toString());
                String[] arr = data.split("&");
                for(int i = 0;i < arr.length;i++){
                    String[] arr1 = arr[i].split("=");
                    String tempStr = "解码异常:target--" + arr1[1] + "key--" + key;
                    try {
                        tempStr = ZzSecurityHelper.decryptAES(arr1[1]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(i == 0){
                        textParam1Title.setText(arr1[0]);
                        textParam1.setText(tempStr);
                    }else{
                        textParam2Title.setText(arr1[0]);
                        textParam2.setText(tempStr);
                    }
                }

                textParam2.setText(name);
            }
        }
    }

    private void SetTextView(){
        textUrl  = findViewById(R.id.url);
        textParam1Title = findViewById(R.id.param1Title);
        textParam2Title = findViewById(R.id.param2Title);
        textParam1  = findViewById(R.id.param1);
        textParam2  = findViewById(R.id.param2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }



}


