package com.example.marvelheroes.retrofit;

import com.example.marvelheroes.Entity.HeroesList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("5d1b4f0f34000074000006dd")
    Call<HeroesList> listar();

/*    @GET("5d1b4fd23400004c000006e1")
    fun detalhes() : Call<Detalhes>*/
}