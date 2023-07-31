package com.example.littlecare.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.littlecare.API.APIRequestData;
import com.example.littlecare.API.RetroServer;
import com.example.littlecare.Fragment.SettingFragment;
import com.example.littlecare.Model.User.ModelResponse;
import com.example.littlecare.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private Button btnEdit;
    String xID, xNama, xPassword;
    String nama, password, confirmpassword;
    private EditText etNama, etPassword, etConfirmPassword;
    KendaliLogin KL = new KendaliLogin(EditProfileActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etNama = findViewById(R.id.et_edit_nama);
        etPassword = findViewById(R.id.et_edit_password);
        etConfirmPassword = findViewById(R.id.et_edit_confirm_password);
        btnEdit = findViewById(R.id.btn_edit);

        xID = KL.getPref(KL.keySP_id);
        xNama = KL.getPref(KL.keySP_nama);
        xPassword = KL.getPref(KL.keySP_password);
        etNama.setText(xNama);
        etPassword.setText(xPassword);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nama = etNama.getText().toString();
                password = etPassword.getText().toString();
                confirmpassword = etConfirmPassword.getText().toString();

                if (nama.trim().isEmpty()) {
                    etNama.setError("Nama tidak boleh Kosong!");
                } else if (password.trim().isEmpty()) {
                    etPassword.setError("Password tidak boleh Kosong!");
                } else if (confirmpassword.trim().isEmpty()) {
                    etConfirmPassword.setError("Confirm Password tidak boleh Kosong!");
                } else if (!password.trim().equals(confirmpassword.trim())) {
                    Toast.makeText(EditProfileActivity.this, ("Password tidak sesuai. Mohon dicek kembali!"), Toast.LENGTH_SHORT).show();
                } else {
                    KL.setPref(KL.keySP_id, null);
                    KL.setPref(KL.keySP_nama, null);
                    KL.setPref(KL.keySP_email, null);
                    KL.setPref(KL.keySP_password, null);
                    KL.setPref(KL.keySP_status, null);
                    updateUser();
                }
            }
        });
    }

    public void updateUser() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardUpdateUser(xID, nama, password);
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String pesan = response.body().getPesan();
                String kode = response.body().getKode();
                Toast.makeText(EditProfileActivity.this, "Ubah Data Berhasil. Silahkan login kembali", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(EditProfileActivity.this , LoginActivity.class));
                finish();

            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}