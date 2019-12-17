package com.asadeveloper.sakampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class DetailMahasiswa extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView;
    Button pilih, simpan;

    TextView vnama, valamat, vhobi, vgender;
    String nama, alamat, hobi, gender;

    private final int PICK_IMAGE_REQUEST = 12;
    private Uri filepath;

    //Firebase
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mahasiswa);

        vnama = findViewById(R.id.tv_mahasiswa);
        valamat = findViewById(R.id.tv_alamat);
        vhobi = findViewById(R.id.tv_hoby);
        vgender = findViewById(R.id.tv_gender);
        pilih = findViewById(R.id.btnPilih);
        simpan = findViewById(R.id.btnSave);
        imageView = findViewById(R.id.vImage);

        nama = getIntent().getStringExtra("NAMA_MAHASISWA");
        alamat = getIntent().getStringExtra("ALAMAT_MAHASISWA");
        hobi = getIntent().getStringExtra("HOBY_MAHASISWA");
        gender = getIntent().getStringExtra("GENDER_MAHASISWA");

        //set Text
        vnama.setText(nama);
        valamat.setText(alamat);
        vhobi.setText(hobi);
        vgender.setText(gender);

        storageReference = FirebaseStorage.getInstance().getReference();

        pilih.setOnClickListener(this);
        simpan.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPilih:
                pilihImage();
                break;

            case R.id.btnSave:
                uploadFile();
                break;
        }
    }

    private void uploadFile() {
        if(filepath != null){
             //buat progress upload
            Context context;
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            //buat angka random untuk nama file
            String random = UUID.randomUUID().toString();

            StorageReference ref= storageReference.child("images/"+random+".jpg");
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Berhasil upload", Toast.LENGTH_SHORT).show();
                    imageView.setImageBitmap(null);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Failed upload", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressDialog.setMessage("Uploaded "+ (int)progress+"%");
                }
            });
        }
    }

    private void pilihImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
