package com.example.sugengsfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.graphics.Color;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LifecycleDemo";
    private int count = 0;
    private int resumeCount = 0;
    private TextView tvCounter;
    private TextView tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvCounter = findViewById(R.id.tvCounter);
        tvStatus = findViewById(R.id.tvStatus);
        Button btnTambah = findViewById(R.id.btnTambah);

        updateStatus("onCreate");
        tvCounter.setText(String.valueOf(count));

        btnTambah.setOnClickListener(v -> {
            count++;
            tvCounter.setText(String.valueOf(count));
        });

        Button btnReset = findViewById(R.id.btnReset);

        btnTambah.setOnClickListener(v -> {
            count++;
            tvCounter.setText(String.valueOf(count));
            if (count >= 10) {
                tvCounter.setTextColor(Color.parseColor("#2E7D32")); // Hijau
            }
        });

        btnReset.setOnClickListener(v -> {
            count = 0;
            tvCounter.setText(String.valueOf(count));
            tvCounter.setTextColor(Color.BLACK);
        });

        Button btnBukaInfo = findViewById(R.id.btnBukaInfo);
        btnBukaInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateStatus("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumeCount++;
        updateStatus("onResume (#" + resumeCount + ")");
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateStatus("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateStatus("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy dipanggil");
    }

    private void updateStatus(String namaMethod) {
        Log.d(TAG, "Method dipanggil: " + namaMethod);
        if (tvStatus != null) {
            tvStatus.setText("Status: " + namaMethod);
        }
    }
}