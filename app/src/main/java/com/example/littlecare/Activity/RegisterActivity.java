package com.example.littlecare.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.littlecare.API.APIRequestData;
import com.example.littlecare.API.RetroServer;
import com.example.littlecare.Model.User.ModelResponse;
import com.example.littlecare.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    private EditText etNama, etEmail, etPassword, etConfirmPassword;
    private String nama, email, password, confirm_password;
    private TextView tvSignUp;

    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.et_gmail);
        etNama = findViewById(R.id.et_nama);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        etPassword = findViewById(R.id.et_password);
        tvSignUp = findViewById(R.id.tv_signup);
        btnRegister = findViewById(R.id.btn_register);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = etNama.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                confirm_password = etConfirmPassword.getText().toString();

                if (nama.trim().isEmpty()) {
                    etNama.setError("Nama tidak boleh kosong !");
                } else if (email.trim().isEmpty()) {
                    etEmail.setError("Email tidak boleh kosong !");
                } else if (password.trim().isEmpty()) {
                    etPassword.setError("Password tidak boleh kosong !");
                } else if (confirm_password.trim().isEmpty()) {
                    etConfirmPassword.setError("Confirm Password tidak boleh kosong !");
                } else if (!password.trim().equals(confirm_password.trim())) {
                    Toast.makeText(RegisterActivity.this, ("Password tidak sesuai. Mohon dicek kembali!"), Toast.LENGTH_SHORT).show();
                } else {
                    createUser();
                }
            }
        });
    }


    private void createUser() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreateUser(nama, email, password);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(RegisterActivity.this, "Selamat, Anda telah berhasil melakukan pendaftaran." +
                        " Silahkan Login.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this , BerandaActivity.class));
        finish();
    }
}
