package com.example.littlecare.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.littlecare.API.APIRequestData;
import com.example.littlecare.API.RetroServer;
import com.example.littlecare.Model.User.ModelResponse;
import com.example.littlecare.Model.User.ModelUser;
import com.example.littlecare.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextView tvLogin;
    private Button btnLogin;
    private EditText etEmail, etPassword;
    private List<ModelUser> listPengguna = new ArrayList<>();
    KendaliLogin KL = new KendaliLogin(LoginActivity.this);
    private String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvLogin = findViewById(R.id.tv_login2);
        btnLogin = findViewById(R.id.btn_login2);
        etEmail = findViewById(R.id.et_gmail);
        etPassword = findViewById(R.id.et_password);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                if(email.trim().isEmpty()){
                    etEmail.setError("Username Tidak Boleh Kosong");
                }
                else if(password.trim().isEmpty()){
                    etPassword.setError("Password Tidak Boleh Kosong");
                }
                else{
                    login();
                }
            }
        });

    }
    private void login() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardLogin(email, password);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listPengguna = response.body().getData();

                if (kode.equals("0")) {
                    Toast.makeText(LoginActivity.this, "Email atau Password Salah !", Toast.LENGTH_SHORT).show();
                } else {
                    KL.setPref(KL.keySP_id, listPengguna.get(0).getId_user());
                    KL.setPref(KL.keySP_nama, listPengguna.get(0).getNama());
                    KL.setPref(KL.keySP_email, listPengguna.get(0).getEmail());
                    KL.setPref(KL.keySP_password, listPengguna.get(0).getPassword());
                    KL.setPref(KL.keySP_status, listPengguna.get(0).getStatus());

                    Toast.makeText(LoginActivity.this, "Login Berhasil.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();

                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error! Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this , BerandaActivity.class));
        finish();
    }
}