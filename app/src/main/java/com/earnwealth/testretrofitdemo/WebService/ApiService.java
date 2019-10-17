package com.earnwealth.testretrofitdemo.WebService;

import com.earnwealth.testretrofitdemo.Pojo.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiService {
    @Headers({
            "Accept:application/json",
            "Content-Type: application/json"
    })

    @GET(ApiConstants.GENDER_URL)
    Call<List<UserResponse>> gender_type();

}
