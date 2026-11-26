package com.example.sugengsfirstproject;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.TextView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;

public class InfoActivity extends AppCompatActivity {

    private final ActivityResultLauncher<String> izinKameraLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            Toast.makeText(InfoActivity.this, "Izin Kamera DIBERIKAN!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(InfoActivity.this, "Izin Kamera DITOLAK!", Toast.LENGTH_SHORT).show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainInfo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvInfoJudul = findViewById(R.id.tvInfoJudul);

        // mengambil data titipan dari intent (0 adalah default jika data tidak ditemukan)
        int nilaiDariMain = getIntent().getIntExtra("EXTRA_COUNT", 0);

        //memanfaatkan context untuk mengambil teks dari res/value/strings.xml
        String namaAplikasi = getString(R.string.app_name);

        tvInfoJudul.setText(namaAplikasi + "\nnilai count terakhir: " + nilaiDariMain);

        Button btnKembali = findViewById(R.id.btnKembali);
        btnKembali.setOnClickListener(v -> finish());

        //tombol kamera
        Button btnCekKamera = findViewById(R.id.btnCekKamera);
        btnCekKamera.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(InfoActivity.this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(InfoActivity.this, "Kamera sudah diizinkan sebelumnya!", Toast.LENGTH_SHORT).show();
            } else {
                izinKameraLauncher.launch(Manifest.permission.CAMERA);
            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new CatatanFragment())
                    .commit();
        }
    }
}