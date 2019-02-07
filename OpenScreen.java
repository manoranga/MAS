package com.example.melani.mas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpenScreen extends AppCompatActivity {
    Button btnPendingList,btnCompleteList,btnInComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_screen);

        btnPendingList = findViewById(R.id.button);
        btnCompleteList = findViewById(R.id.button1);
        btnInComplete = findViewById(R.id.button2);

        btnPendingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnCompleteList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CompleteListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnInComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InCompleteListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
