package com.example.littlecare.Model.User;

import java.util.List;

public class ModelResponse {

    public String kode, pesan;
    public List<ModelUser> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelUser> getData() {
        return data;
    }
}
