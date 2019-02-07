package com.example.melani.mas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AsignTechnicianActivity extends AppCompatActivity {
    TextView TechnicianName, Serial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asign_technician);

        TechnicianName = findViewById(R.id.Name);
        Serial  = findViewById(R.id.serial);

        Bundle bundle = getIntent().getExtras();
        String SerialNumber = bundle.getString("Serial");
        String TechnicianName1 = bundle.getString("Techname");

       Serial.setText(SerialNumber);
        TechnicianName.setText(TechnicianName1);
        Toast.makeText(this, SerialNumber, Toast.LENGTH_SHORT).show();

    }
}
