package com.asadeveloper.sakampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements View.OnClickListener  {
    ImageView logout;

    private  FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = findViewById(R.id.btn_logout);

        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_logout:
                //kode disini
                mAuth.signOut();
                Intent keluar = new Intent(this, MainActivity.class);
                keluar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(keluar);
                break;
        }

    }
}
