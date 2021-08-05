package com.example.digitallibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class splash extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread=new Thread(){

            public void run()
            {

                try
                {
                    sleep(5000);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                    Intent intent=new Intent(splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }



        };thread.start();




    }



}