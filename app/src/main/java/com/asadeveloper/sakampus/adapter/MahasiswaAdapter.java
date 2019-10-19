package com.asadeveloper.sakampus.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asadeveloper.sakampus.DetailMahasiswa;
import com.asadeveloper.sakampus.R;
import com.asadeveloper.sakampus.model.DataMahasiswa;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<DataMahasiswa> dataMahasiswas;

    public MahasiswaAdapter(Context cont, ArrayList<DataMahasiswa> data){
        context= cont;
        dataMahasiswas = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.vnama.setText(dataMahasiswas.get(position).getNama());
        holder.valamat.setText(dataMahasiswas.get(position).getAlamat());

        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context.getApplicationContext(), DetailMahasiswa.class);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                detail.putExtra("NAMA_MAHASISWA", dataMahasiswas.get(position).getNama());
                detail.putExtra("ALAMAT_MAHASISWA", dataMahasiswas.get(position).getAlamat());
                detail.putExtra("GENDER_MAHASISWA", dataMahasiswas.get(position).getGender());
                detail.putExtra("HOBY_MAHASISWA", dataMahasiswas.get(position).getHobi());
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataMahasiswas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vnama, valamat;
        Button btndetail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vnama = itemView.findViewById(R.id.tv_nama);
            valamat = itemView.findViewById(R.id.tv_alamat);
            btndetail = itemView.findViewById(R.id.btn_detail);
        }
    }
}
