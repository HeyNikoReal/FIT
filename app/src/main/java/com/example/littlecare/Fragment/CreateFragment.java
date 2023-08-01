package com.example.littlecare.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.littlecare.API.APIRequestData;
import com.example.littlecare.API.RetroServer;
import com.example.littlecare.Activity.LoginActivity;
import com.example.littlecare.Activity.MainActivity;
import com.example.littlecare.Model.Game.ModelResponseGame;
import com.example.littlecare.Model.User.ModelResponse;
import com.example.littlecare.Model.User.ModelUser;
import com.example.littlecare.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String nama, creator, rating, deskripsi, linkgame, linkfoto;

    public CreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateFragment newInstance(String param1, String param2) {
        CreateFragment fragment = new CreateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            EditText etNama, etCreator, etRating, etDeskripsi, etLinkFoto, etLinkGame;
            Button btnTambah;
            etNama = view.findViewById(R.id.et_nama_game);
            etCreator = view.findViewById(R.id.et_creator);
            etRating = view.findViewById(R.id.et_rating);
            etDeskripsi = view.findViewById(R.id.et_deskripsi);
            etLinkFoto = view.findViewById(R.id.et_link_foto);
            etLinkGame = view.findViewById(R.id.et_link_game);
            btnTambah = view.findViewById(R.id.btn_tambah);

            btnTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    nama = etNama.getText().toString();
                    creator = etCreator.getText().toString();
                    rating = etRating.getText().toString();
                    deskripsi = etDeskripsi.getText().toString();
                    linkfoto = etLinkFoto.getText().toString();
                    linkgame = etLinkGame.getText().toString();

                    if (nama.trim().isEmpty()) {
                        etNama.setError("Nama APK Harus diIsi!");
                    } else if (creator.trim().isEmpty()) {
                        etCreator.setError("Creator APK Harus diIsi!");
                    } else if (deskripsi.trim().isEmpty()) {
                        etDeskripsi.setError("Deskripsi APK Harus diIsi!");
                    } else if (rating.trim().isEmpty()) {
                        etRating.setError("Rating APK Harus diIsi!");
                    } else if (linkfoto.trim().isEmpty()) {
                        etLinkFoto.setError("URL/Link Gambar Foto APK Harus diIsi!");
                    } else if (linkgame.trim().isEmpty()) {
                        etLinkGame.setError("URL/LINK Game APK Harus diIsi!");
                    } else {
                        tambahGame();
                    }
                }
            });

        }
    }

    public void tambahGame() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponseGame> proses = ARD.ardCreateGame(nama, creator, deskripsi, rating, linkfoto, linkgame);
        proses.enqueue(new Callback<ModelResponseGame>() {
            @Override
            public void onResponse(Call<ModelResponseGame> call, Response<ModelResponseGame> response) {
                String pesan = response.body().getPesan();
                String kode = response.body().getKode();
                Toast.makeText(getActivity(), "Selamat! Data Aplikasi Anda telah ditambahkan ke List.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModelResponseGame> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

