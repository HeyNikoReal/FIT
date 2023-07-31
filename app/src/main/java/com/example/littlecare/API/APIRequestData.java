package com.example.littlecare.API;

import com.example.littlecare.Model.User.ModelResponse;;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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


}
