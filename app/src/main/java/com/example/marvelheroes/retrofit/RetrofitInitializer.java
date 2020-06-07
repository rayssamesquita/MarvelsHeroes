package com.example.marvelheroes.retrofit;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitInitializer {

    private String apiKey = "";
    private String apiKeyPrivate = "";
    private int ts = 1;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/v1/public/characters?ts=1&apikey=c71f10135daeca3c815e16b209ba0e78&hash=67c36e8f56a5c14650cfc746f12b8090")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);

/*    public static String hash(int ts, String apiKey, String apiKeyPrivate){
        String hashString = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        String value = ts + apiKeyPrivate + apiKey;
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        hashString = hash.toString();
        return hashString;
    }*/

}