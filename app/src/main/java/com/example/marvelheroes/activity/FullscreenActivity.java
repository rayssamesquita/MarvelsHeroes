package com.example.marvelheroes.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.example.marvelheroes.Entity.Hero;
import com.example.marvelheroes.Entity.HeroesList;
import com.example.marvelheroes.R;
import com.example.marvelheroes.retrofit.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullscreenActivity extends AppCompatActivity {
    private int index = 0;
    private final Handler mHideHandler = new Handler();
    private List<Hero> heroes;
    private ImageButton back;
    private ImageButton next;
    private Button more;
    private TextView description;
    private long ts = System.currentTimeMillis();
    private String apiKey = "c71f10135daeca3c815e16b209ba0e78";
    private String privateKey = "e54bbb11d8c2048e1d4a44a78c753b08bf57e0d2";

    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
        }
    };
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        getInfo();

        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        more = findViewById(R.id.more);
        description = findViewById(R.id.description);

        back.setVisibility(View.INVISIBLE);

        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                index--;
                description.setVisibility(View.INVISIBLE);
                more.setVisibility(View.VISIBLE);
                setVisibilityButtons();
                setLayout();
            }
        });
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                index++;
                description.setVisibility(View.INVISIBLE);
                more.setVisibility(View.VISIBLE);
                setVisibilityButtons();
                setLayout();
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setVisibility(View.VISIBLE);
                more.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide(100);
    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mHideHandler.removeCallbacks(mShowPart2Runnable);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void getInfo(){
        heroes = new ArrayList<>();
        Call<HeroesList> call = new RetrofitInitializer().getHeroes().listar(ts, apiKey, hashMD5());
        call.enqueue(new Callback<HeroesList>() {
            @Override
            public void onResponse(Call<HeroesList> call, Response<HeroesList> response) {
                heroes = response.body().getData().getResults();
                setLayout();
            }

            @Override
            public void onFailure(Call<HeroesList> call, Throwable t) {
                Log.e("Lista de Heróis", "Erro ao listar os heróis.");
            }
        });
    }

    public void setLayout(){
        ImageView image = findViewById(R.id.image);
        String path = heroes.get(index).getThumbnail().getPath() + "/landscape_large." + heroes.get(index).getThumbnail().getExtension();
        Glide.with(this).load(path).placeholder(R.drawable.ic_placeholder).into(image);

        TextView name = findViewById(R.id.name);
        name.setText(heroes.get(index).getName());

        description.setText(heroes.get(index).getDescription());
    }

    public void setVisibilityButtons(){
        if(index == 0){
            back.setVisibility(View.INVISIBLE);
            next.setVisibility(View.VISIBLE);
        }else if(index == heroes.size() -1){
            back.setVisibility(View.VISIBLE);
            next.setVisibility(View.INVISIBLE);
        }else{
            back.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }
    }

    public String hashMD5(){
        String s = ts + privateKey + apiKey;
        final String MD5 = "MD5";
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
