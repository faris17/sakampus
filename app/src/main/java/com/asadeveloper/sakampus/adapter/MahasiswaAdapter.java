package com.asadeveloper.sakampus.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asadeveloper.sakampus.DetailMahasiswa;
import com.asadeveloper.sakampus.R;
import com.asadeveloper.sakampus.model.DataMahasiswa;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<DataMahasiswa> dataMahasiswas;

    private DatabaseReference ref;

    //variable untuk firebase storage
    private StorageReference reference;

    public MahasiswaAdapter(Context cont, ArrayList<DataMahasiswa> data){
        context= cont;
        dataMahasiswas = data;
        ref = FirebaseDatabase.getInstance().getReference().child("mahasiswa");
        reference = FirebaseStorage.getInstance().getReference();

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

        getImage("images/"+dataMahasiswas.get(position).getNim()+".jpg",holder.foto);

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

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hapus data
                ref.child(dataMahasiswas.get(position).getNim()).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataMahasiswas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vnama, valamat;
        Button btndetail, btndelete;
        ImageView foto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vnama = itemView.findViewById(R.id.tv_nama);
            valamat = itemView.findViewById(R.id.tv_alamat);
            btndetail = itemView.findViewById(R.id.btn_detail);
            btndelete = itemView.findViewById(R.id.btn_delete);
            foto = itemView.findViewById(R.id.foto_alumni);

        }
    }

    public void getImage(String data, final ImageView foto){
        reference.child(data).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //tampilkan gambar dengan glide
                Glide.with(context)
                        .load(uri)
                        .into(foto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


}
