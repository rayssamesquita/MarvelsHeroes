package com.example.marvelheroes.retrofit;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {

    private final Retrofit retrofit;

    public RetrofitInitializer() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://gateway.marvel.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public RetrofitService getHeroes(){
        return retrofit.create(RetrofitService.class);
    }

}