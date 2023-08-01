package com.example.littlecare.API;

import com.example.littlecare.Model.Game.ModelResponseGame;
import com.example.littlecare.Model.User.ModelResponse;;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {

    @FormUrlEncoded
    @POST("createUser.php")
    Call<ModelResponse> ardCreateUser(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("deleteUser.php")
    Call<ModelResponse> ardDeleteUser(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("updateUser.php")
    Call<ModelResponse> ardUpdateUser(
            @Field("id_user") String id_user,
            @Field("nama") String nama,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ModelResponse> ardLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("upgrade.php")
    Call<ModelResponse> ardUpgrade(
            @Field("id_user") String id_user,
            @Field("status") String tipe
    );

    @FormUrlEncoded
    @POST("createGame.php")
    Call<ModelResponseGame> ardCreateGame(
            @Field("nama_game") String nama_game,
            @Field("creator") String creator,
            @Field("deskripsi") String deskripsi,
            @Field("rating") String rating,
            @Field("link_foto") String link_foto,
            @Field("link_game") String link_game);

    @GET("retrieveGame.php")
    Call<ModelResponseGame> ardRetrieveGame();

}


