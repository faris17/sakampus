package com.asadeveloper.sakampus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailMahasiswa extends AppCompatActivity {

    TextView vnama, valamat, vhobi, vgender;

    String nama, alamat, hobi, gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        vnama = findViewById(R.id.tv_mahasiswa);
        valamat = findViewById(R.id.tv_alamat);
        vhobi = findViewById(R.id.tv_hoby);
        vgender = findViewById(R.id.tv_gender);

        nama = getIntent().getStringExtra("NAMA_MAHASISWA");
        alamat = getIntent().getStringExtra("ALAMAT_MAHASISWA");
        hobi = getIntent().getStringExtra("HOBY_MAHASISWA");
        gender = getIntent().getStringExtra("GENDER_MAHASISWA");

        //set Text
        vnama.setText(nama);
        valamat.setText(alamat);
        vhobi.setText(hobi);
        vgender.setText(gender);
    }
}
