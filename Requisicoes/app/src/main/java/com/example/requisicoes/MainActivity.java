package com.example.requisicoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.requisicoes.api.DataService;
import com.example.requisicoes.model.Fotos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView textoResultado;
    private  Retrofit retrofit;
    private List<Fotos> listaDeFotos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.button_recuperar);
        textoResultado = findViewById(R.id.textResultado);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recuperarListaRetrofit();
            }
        });

    }

    private void recuperarListaRetrofit() {

        DataService service = retrofit.create(DataService.class);
        Call<List<Fotos>> call = service.recuperarFotos();

        call.enqueue(new Callback<List<Fotos>>() {
            @Override
            public void onResponse(Call<List<Fotos>> call, Response<List<Fotos>> response) {
                if (response.isSuccessful()){
                    listaDeFotos = response.body();

                    for (int i=0; i<listaDeFotos.size();i++){
                        Fotos fotos = listaDeFotos.get(i);
                        Log.d("resultado","resultado"+ fotos.getId()+ " / " + fotos.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Fotos>> call, Throwable t) {

            }
        });

    }
}
