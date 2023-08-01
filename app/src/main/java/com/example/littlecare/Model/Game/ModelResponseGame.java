package com.example.littlecare.Model.Game;

import java.util.List;

public class ModelResponseGame {

    public String kode, pesan;
    public List<ModelGame> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelGame> getData() {
        return data;
    }
}
