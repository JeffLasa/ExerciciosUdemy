package com.example.requisicoes.api;

import com.example.requisicoes.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepService {

    @GET("{cep}/json/")
    Call<CEP> recuperarCep(@Path("cep") String cep);

}
