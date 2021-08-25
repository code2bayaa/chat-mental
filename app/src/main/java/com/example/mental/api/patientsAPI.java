package com.example.mental.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface patientsAPI {
    String BASE_URL = "http://192.168.0.22/mental/";
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("getPatients.php")
    Call<String> getStrings(
            @Field("permit") String permit,
            @Field("user") String user
    );
}