package com.example.sugengsfirstproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CatatanFragment extends Fragment {

    public CatatanFragment() {
        super(R.layout.fragment_catatan);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvCatatan = view.findViewById(R.id.tvCatatan);
        Button btnUbahCatatan = view.findViewById(R.id.btnUbahCatatan);

        btnUbahCatatan.setOnClickListener(v -> {
            tvCatatan.setText("Teks ini diubah dari dalam Fragment!");
            Toast.makeText(requireContext(), "Tombol Fragment diklik!", Toast.LENGTH_SHORT).show();
        });
    }
}