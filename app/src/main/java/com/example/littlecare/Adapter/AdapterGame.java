package com.example.littlecare.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.littlecare.Model.Game.ModelGame;
import com.example.littlecare.R;

import java.util.List;

public class AdapterGame extends RecyclerView.Adapter<AdapterGame.VHGame> {

    private Context ctx;
    private List<ModelGame> listGame;

    public AdapterGame(Context ctx, List<ModelGame> listGame) {
        this.ctx = ctx;
        this.listGame = listGame;
    }

    @NonNull
    @Override
    public AdapterGame.VHGame onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_game, parent, false);
        return new VHGame(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGame.VHGame holder, int position) {
        ModelGame MG = listGame.get(position);
        holder.tvId.setText(MG.getId_game());
        holder.tvNama.setText(MG.getNama_game());
        holder.tvCreator.setText(MG.getCreator());
        holder.tvDeskripsi.setText(MG.getDeskripsi());
        holder.tvRating.setText(MG.getRating());
        holder.tvLinkFoto.setText(MG.getLink_foto());
        holder.tvLinkGame.setText(MG.getLink_game());
        Glide.with(holder.itemView.getContext()).load(MG.getLink_foto()).
                apply(new RequestOptions().override(1000, 1000)).
                into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        return listGame.size();
    }

    public class VHGame extends RecyclerView.ViewHolder {

        TextView tvId, tvNama, tvCreator, tvRating, tvLinkGame, tvDeskripsi, tvLinkFoto;
        ImageView ivFoto;

        public VHGame(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id2);
            tvNama = itemView.findViewById(R.id.tv_nama2);
            tvCreator = itemView.findViewById(R.id.tv_creators);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            tvLinkFoto = itemView.findViewById(R.id.tv_linkfoto);
            tvLinkGame = itemView.findViewById(R.id.tv_linkgame);
            ivFoto = itemView.findViewById(R.id.iv_foto2);
        }

    }
}
