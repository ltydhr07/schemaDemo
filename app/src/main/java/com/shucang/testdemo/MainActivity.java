package com.shucang.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
    public TextView textUrl;
    public TextView textParam1;
    public TextView textParam1Title;
    public TextView textParam2;
    public TextView textParam2Title;
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
                    if(i == 0){
                        textParam1Title.setText(arr1[0]);
                        textParam1.setText(arr1[1]);
                    }else{
                        textParam2Title.setText(arr1[0]);
                        textParam2.setText(arr1[1]);
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
