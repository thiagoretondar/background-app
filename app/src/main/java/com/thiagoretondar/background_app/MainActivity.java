package com.thiagoretondar.background_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Start the  service
    public void startNewService(View view) {

        startService(new Intent(this, MyService.class));
    }

    // Stop the  service
    public void stopNewService(View view) {

        stopService(new Intent(this, MyService.class));
    }
}
