package com.proclubstudio.camar.api;

import com.proclubstudio.camar.model.JSON;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("earthquakeList?limit=20&page=1")
    Call<JSONRespone> getJSON();

}
