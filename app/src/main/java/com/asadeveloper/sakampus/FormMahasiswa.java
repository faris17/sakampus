package com.asadeveloper.sakampus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.asadeveloper.sakampus.model.DataMahasiswa;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormMahasiswa extends AppCompatActivity {
    EditText namamahasiswa, nimmahasiswa, alamat, hobi;
    RadioGroup kategorigendermhs;
    RadioButton pria, wanita;
    Button simpan;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_mahasiswa);

        namamahasiswa = findViewById(R.id.inputmahasiswa);
        nimmahasiswa = findViewById(R.id.inputnim);
        alamat = findViewById(R.id.inputalamat);
        hobi = findViewById(R.id.inputhobi);
        pria = findViewById(R.id.male);
        wanita = findViewById(R.id.female);
        simpan = findViewById(R.id.btnsave);
        kategorigendermhs = findViewById(R.id.kategorigender);

        reference = FirebaseDatabase.getInstance().getReference().child("mahasiswa");

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = namamahasiswa.getText().toString().trim();
                String nim = nimmahasiswa.getText().toString().trim();
                String alamatmhs = alamat.getText().toString().trim();
                String hobimhs = hobi.getText().toString().trim();
                int dgender = kategorigendermhs.getCheckedRadioButtonId();
                String kateggender;

                if(dgender == pria.getId()){
                    kateggender = "pria";
                }
                else {
                    kateggender = "wanita";
                }

                DataMahasiswa insert = new DataMahasiswa(nama,alamatmhs, kateggender, hobimhs);
                //insert ke firebase
                reference.child(nim).setValue(insert);
                hapusField();
                Toast.makeText(getApplicationContext(),"Berhasil disimpan",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void hapusField(){
        namamahasiswa.getText().clear();
        nimmahasiswa.getText().clear();
        alamat.getText().clear();
        hobi.getText().clear();
    }
}
