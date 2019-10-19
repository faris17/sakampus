package com.asadeveloper.sakampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.asadeveloper.sakampus.adapter.MahasiswaAdapter;
import com.asadeveloper.sakampus.model.DataMahasiswa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Mahasiswa extends AppCompatActivity {
    private DatabaseReference reference;

    ArrayList<DataMahasiswa> list;
    MahasiswaAdapter adapter;

    private RecyclerView mRecylcer;
    private LinearLayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        mRecylcer = findViewById(R.id.list_mahasiswa);
        mRecylcer.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecylcer.setLayoutManager(mManager);

        reference = FirebaseDatabase.getInstance().getReference().child("mahasiswa");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    DataMahasiswa mhs = dataSnapshot1.getValue(DataMahasiswa.class);
                    list.add(mhs);
                }
                adapter = new MahasiswaAdapter(getApplicationContext(), list);
                mRecylcer.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan",Toast.LENGTH_LONG).show();
            }
        });
    }
}
