package com.example.littlecare.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.littlecare.R;

public class BerandaActivity extends AppCompatActivity {

    private Button login,signUp;
    KendaliLogin KL = new KendaliLogin(BerandaActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!KL.isLogin(KL.keySP_email)==true) {
            setContentView(R.layout.activity_beranda);
            login = findViewById(R.id.btn_login2);
            signUp = findViewById(R.id.btn_sign_up);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(BerandaActivity.this, LoginActivity.class));
                }
            });

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(BerandaActivity.this, RegisterActivity.class));

                }
            });

        }

        else {
            startActivity(new Intent(BerandaActivity.this , MainActivity.class));
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}