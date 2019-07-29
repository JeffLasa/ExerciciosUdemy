package com.example.requisicoes.api;

import com.example.requisicoes.model.Fotos;
import com.example.requisicoes.model.Postagens;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/photos")
    Call<List<Fotos>> recuperarFotos();

}
