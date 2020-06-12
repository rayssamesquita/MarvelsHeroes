package com.example.marvelheroes.retrofit;

import com.example.marvelheroes.Entity.HeroesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("characters?")
    Call<HeroesList> listar(@Query("ts") long ts, @Query("apikey") String apiKey, @Query("hash") String hash);
}