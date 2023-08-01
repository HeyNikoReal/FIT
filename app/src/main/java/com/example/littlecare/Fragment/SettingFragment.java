package com.example.littlecare.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.littlecare.API.APIRequestData;
import com.example.littlecare.API.RetroServer;
import com.example.littlecare.Activity.BerandaActivity;
import com.example.littlecare.Activity.EditProfileActivity;
import com.example.littlecare.Activity.KendaliLogin;
import com.example.littlecare.Activity.MainActivity;
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
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            KendaliLogin KL = new KendaliLogin(getActivity());
            Button btnEdit, btnUpgrade, btnHapus, btnLogout;
            btnUpgrade = view.findViewById(R.id.btn_upgrade);
            if(KL.getPref(KL.keySP_status).equals("Premium")){
                btnUpgrade.setVisibility(view.GONE);
            }
            TextView tvNama, tvStatus, tvEmail;
            String yNama, yEmail, yStatus, yID;
            TextView tvID;

            yID = KL.getPref(KL.keySP_id);
            yNama = KL.getPref(KL.keySP_nama);
            yEmail = KL.getPref(KL.keySP_email);
            yStatus = KL.getPref(KL.keySP_status);
            tvID = view.findViewById(R.id.tv_id);
            tvNama = view.findViewById(R.id.tv_nama);
            tvStatus = view.findViewById(R.id.tv_status);
            tvEmail = view.findViewById(R.id.tv_email);
            tvID.setText(yID);
            tvNama.setText(yNama);
            tvStatus.setText(yStatus);
            tvEmail.setText(yEmail);

            btnEdit = view.findViewById(R.id.btn_edit);
            btnHapus = view.findViewById(R.id.btn_hapus);
            btnLogout = view.findViewById(R.id.btn_logout);

            btnUpgrade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Konfirmasi");
                    dialog.setMessage("Apakah Anda yakin ingin upgrade ke Status Premium(Rp. 25.000,00/bulan)?");

                    dialog.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                        }
                    });

                    dialog.setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            upgradePremium();
                            Toast.makeText(getActivity(), "Upgrade Berhasil! Silahkan Login Kembali.", Toast.LENGTH_SHORT).show();
                            getActivity().finish();

                        }
                    });
                    dialog.show();
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                    startActivity(intent);
                }
            });

            btnHapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Konfirmasi");
                    dialog.setMessage("Apakah Anda yakin ingin menghapus akun?");

                    dialog.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                        }
                    });

                    dialog.setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            deleteUser(yID);
                        }
                    });
                    dialog.show();
                }
            });

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("Konfirmasi");
                    dialog.setMessage("Apakah Anda yakin ingin keluar dari akun Anda?");

                    dialog.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                        }
                    });

                    dialog.setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            KL.setPref(KL.keySP_id, null);
                            KL.setPref(KL.keySP_nama, null);
                            KL.setPref(KL.keySP_email, null);
                            KL.setPref(KL.keySP_password, null);
                            KL.setPref(KL.keySP_status, null);
                            Toast.makeText(getActivity(), "Anda berhasil Logout!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), BerandaActivity.class));
                            getActivity().finish();
                        }

                    });
                    dialog.show();
                }
            });

        }
    }

    public void upgradePremium() {
        final List<ModelUser>[] listUser = new List[]{new ArrayList<>()};
        KendaliLogin KL = new KendaliLogin(getActivity());
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardUpgrade(KL.getPref(KL.keySP_id), KL.getPref(KL.keySP_status));

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listUser[0] = response.body().getData();

                if (kode.equals("0")) {
                    Toast.makeText(getActivity(), "Error! Ada Kesalahan saat Upgrade ke Premium",Toast.LENGTH_SHORT).show();
                } else {
                    KL.setPref(KL.keySP_status, listUser[0].get(0).getStatus());
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error! Gagal Terhubung ke Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteUser(String id) {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardDeleteUser(id);
        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String pesan = response.body().getPesan();
                String kode = response.body().getKode();
                Toast.makeText(getActivity(), "Akun Berhasil dihapus", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}