//package com.example.littlecare.Adapter;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.littlecare.Model.Game.Standard.ModelGame;
//import com.example.littlecare.Model.Home.ModelHome;
//import com.example.littlecare.R;
//
//import java.util.List;
//
//
//public class AdapterHome extends RecyclerView.Adapter<AdapterHome.VHHome> {
//
//    private Context ctx;
//    private List<ModelGame> listHome;
//
//    public AdapterHome(Context ctx, List<ModelHome> listHome) {
//        this.ctx = ctx;
//        this.listHome = listHome;
//    }
//
//    @NonNull
//    @Override
//    public AdapterHome.VHGame onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false);
//        return new VHHome(varView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AdapterGame.VHHome holder, int position) {
//        ModelHome MH = listHome.get(position);
//        holder.tvId.setText(MA.getId());
//        holder.tvNama.setText(MA.getNama());
//        holder.tvTempatLahir.setText(MA.getTempat_lahir());
//        holder.tvTanggalLahir.setText(MA.getTanggal_lahir());
//        holder.tvTahunAktif.setText(MA.getTahun_aktif());
//        holder.tvPekerjaan.setText(MA.getPekerjaaan());
//        holder.tvFilm.setText(MA.getFilm());
//        holder.tvPenghargaan.setText(MA.getPenghargaan());
//        holder.tvFoto.setText(MA.getFoto());
//        Glide.with(holder.itemView.getContext()).load(MA.getFoto()).
//                apply(new RequestOptions().override(1000, 1000)).
//                into(holder.ivFoto);
//    }
//
//    @Override
//    public int getItemCount() {
//        return listHome.size();
//    }
//
//    public class VHHome extends RecyclerView.ViewHolder {
//
//        TextView tvId, tvNama, tvTempatLahir, tvTanggalLahir, tvTahunAktif, tvPekerjaan, tvFilm, tvPenghargaan, tvFoto;
//        ImageView ivFoto;
//        Button btnDetail, btnUbah, btnHapus;
//
//        public VHHome(@NonNull View itemView) {
//            super(itemView);
//
//            tvId = itemView.findViewById(R.id.tv_id);
//            tvNama = itemView.findViewById(R.id.tv_nama);
//            tvTempatLahir = itemView.findViewById(R.id.tv_tempat_lahir);
//            tvTanggalLahir = itemView.findViewById(R.id.tv_tanggal_lahir);
//            tvTahunAktif = itemView.findViewById(R.id.tv_tahun_aktif);
//            tvPekerjaan = itemView.findViewById(R.id.tv_pekerjaan);
//            tvFilm = itemView.findViewById(R.id.tv_film);
//            tvPenghargaan = itemView.findViewById(R.id.tv_penghargaan);
//            tvFoto = itemView.findViewById(R.id.tv_foto);
//
//            ivFoto = itemView.findViewById(R.id.iv_foto);
//            btnDetail = itemView.findViewById(R.id.btn_detail);
//            btnUbah = itemView.findViewById(R.id.btn_ubah);
//            btnHapus = itemView.findViewById(R.id.btn_hapus);
//
//            btnHapus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
//                    pesan.setTitle("Perhatian");
//                    pesan.setMessage("Apakah Anda yakin ingin menghapus data dari " + tvNama.getText().toString() + " ?");
//                    pesan.setCancelable(true);
//
//                    pesan.setNegativeButton("Ya", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            hapusAktor(tvId.getText().toString());
//                        }
//                    });
//
//                    pesan.setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//                    pesan.show();
//                }
//            });
//
//            btnUbah.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent pindah = new Intent(ctx, UbahActivity.class);
//                    pindah.putExtra("xId", tvId.getText().toString());
//                    pindah.putExtra("xNama", tvNama.getText().toString());
//                    pindah.putExtra("xTempatLahir", tvTempatLahir.getText().toString());
//                    pindah.putExtra("xTanggalLahir", tvTanggalLahir.getText().toString());
//                    pindah.putExtra("xTahunAktif", tvTahunAktif.getText().toString());
//                    pindah.putExtra("xPekerjaan", tvPekerjaan.getText().toString());
//                    pindah.putExtra("xFilm", tvFilm.getText().toString());
//                    pindah.putExtra("xPenghargaan", tvPenghargaan.getText().toString());
//                    pindah.putExtra("xFoto", tvFoto.getText().toString());
//                    ctx.startActivity(pindah);
//                }
//            });
//
//            btnDetail.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent pindah = new Intent(ctx, DetailActivity.class);
//                    pindah.putExtra("xNama", tvNama.getText().toString());
//                    pindah.putExtra("xTempatLahir", tvTempatLahir.getText().toString());
//                    pindah.putExtra("xTanggalLahir", tvTanggalLahir.getText().toString());
//                    pindah.putExtra("xTahunAktif", tvTahunAktif.getText().toString());
//                    pindah.putExtra("xPekerjaan", tvPekerjaan.getText().toString());
//                    pindah.putExtra("xFilm", tvFilm.getText().toString());
//                    pindah.putExtra("xPenghargaan", tvPenghargaan.getText().toString());
//                    pindah.putExtra("xFoto", tvFoto.getText().toString());
//                    ctx.startActivity(pindah);
//                }
//            });
//        }
//    }
//
//    public void hapusAktor(String id) {
//        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
//        Call<ModelResponse> proses = ARD.ardDelete(id);
//        proses.enqueue(new Callback<ModelResponse>() {
//            @Override
//            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
//                String pesan = response.body().getPesan();
//                String kode = response.body().getKode();
//                Toast.makeText(ctx, "Kode = " + kode + ", Pesan :  " + pesan, Toast.LENGTH_SHORT).show();
//                ((MainActivity) ctx).retrieveAktor();
//            }
//
//            @Override
//            public void onFailure(Call<ModelResponse> call, Throwable t) {
//                Toast.makeText(ctx, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
