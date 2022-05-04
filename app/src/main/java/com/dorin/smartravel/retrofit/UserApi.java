package com.dorin.smartravel.retrofit;

import com.dorin.smartravel.Objects.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("iob/admin/users")
    Call<List<User>> getAllUser();

    @POST("iob/users")
    Call<User> createUser(@Body User user);




}
