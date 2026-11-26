package com.example.sugengsfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.graphics.Color;
import android.widget.Toast;


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


        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("STATE_COUNT", 0);
            Log.d(TAG, "data count dipulihkan dari savedInstanceState : " + count);

        }

        updateStatus("onCreate");
        tvCounter.setText(String.valueOf(count));

        if (count >= 10) {
            tvCounter.setTextColor(Color.parseColor("#2E7D32"));
        }


        Button btnReset = findViewById(R.id.btnReset);

        btnTambah.setOnClickListener(v -> {
            count++;
            Log.d(TAG, "Tombol tambah ditekan, nilai count sekarang = " + count);
            tvCounter.setText(String.valueOf(count));
            if (count >= 10) {
                Log.i(TAG, " Target Tercapai! Count sudah >= 10");
                if (count == 10) {
                    Toast.makeText(MainActivity.this, "Hitungan Sudah sampai 10 tambah terus!", Toast.LENGTH_SHORT).show();
                }
                tvCounter.setTextColor(Color.parseColor("#2E7D32"));
            }
        });

        btnReset.setOnClickListener(v -> {
            if (count > 0) {
                Log.w(TAG, "Tombol reeset ditekan. count sebelumnya (" + count + ") dikembailkan ke 0");
                count = 0;
                tvCounter.setText(String.valueOf(count));
                tvCounter.setTextColor(Color.BLACK);
                Toast.makeText(MainActivity.this, "Hitungan direset", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Sudah 0!", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnBukaInfo = findViewById(R.id.btnBukaInfo);
        btnBukaInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            intent.putExtra("EXTRA_COUNT", count);
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("STATE_COUNT", count);
        Log.d(TAG, "onSaveInstanceState Menyimpan count = " + count);
    }

    private void updateStatus(String namaMethod) {
        Log.d(TAG, "Method dipanggil: " + namaMethod);
        if (tvStatus != null) {
            tvStatus.setText("Status: " + namaMethod);
        }
    }
}