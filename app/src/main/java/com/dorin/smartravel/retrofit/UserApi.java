package com.dorin.smartravel.retrofit;

import com.dorin.smartravel.serverObjects.ActivityBoundary;
import com.dorin.smartravel.serverObjects.InstanceBoundary;
import com.dorin.smartravel.serverObjects.UserBoundary;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {



    @POST("iob/users")
    Call<UserBoundary> createUser(@Body UserBoundary user);

    @POST("iob/instances")
    Call<InstanceBoundary> createInstance(@Body InstanceBoundary instanceBoundary);


    // TODO: 5/20/2022 need to do all below
    @POST("iob/activities")
    Call<ActivityBoundary> createActivity(@Body ActivityBoundary activityBoundary);

    @GET("iob/instances/{instanceDomain}/{instanceId}")
    Call<InstanceBoundary> getInstanceById(
    @Path("instanceDomain") String instanceDomain,
    @Path("instanceId") String instanceId,
    @Query("userDomain") String userDomain,
    @Query("userEmail") String userEmail);

    @GET("iob/instances/search/byName/{name}")
    Call<InstanceBoundary> getAllInstancesByName(
            @Path("name") String name,
            @Query("userDomain") String userDomain,
            @Query("userEmail") String userEmail,
            @Query("size") String size,
            @Query("page") String page);


    @GET("iob/instances/search/byType/{type}")
    Call<InstanceBoundary> getAllInstancesByType(
            @Path("type") String type,
            @Query("userDomain") String userDomain,
            @Query("userEmail") String userEmail,
            @Query("size") String size,
            @Query("page") String page);


    @PUT("iob/users/{userDomain}/{userEmail}")
    Call<Void> updateUserDetails(@Body UserBoundary userBoundary,
            @Path("userDomain") String userDomain,
            @Path("userEmail") String userEmail);

    @PUT("iob/instances/{instanceDomain}/{instanceId}")
    Call<InstanceBoundary> updateInstanceById(@Body InstanceBoundary instanceBoundary,
            @Path("instanceDomain") String instanceDomain,
            @Path("instanceId") String instanceId,
            @Query("userDomain") String userDomain,
            @Query("userEmail") String userEmail);


}
