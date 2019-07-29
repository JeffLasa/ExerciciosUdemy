package com.example.requisicoes;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView textoResultado;
    private TextView cepDigitado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        botaoRecuperar = findViewById(R.id.button_recuperar);
        textoResultado = findViewById(R.id.textResultado);
        cepDigitado = findViewById(R.id.cepDigitado_id);

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validarCep(cepDigitado);
            }

        });

    }

    public void validarCep(View view){

        String validaCep = cepDigitado.getText().toString();


        if (cepDigitado.equals("")){
            textoResultado.setText("Preecha o cep corretamente\n Exemplo 00000000\n não use caracteres nem espaços");

        }else{
            buscarCep(cepDigitado);
        }

    }

    private void buscarCep(TextView cepDigitado) {
        MyTask task = new MyTask();

        String UrlApi = "https://www.blockchain.com/ticker";
        String cep = cepDigitado.getText().toString();
        String urlCep = "https://viacep.com.br/ws/"+ cep +"/json";
        task.execute(urlCep);
    }

    class MyTask extends AsyncTask<String,Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream=null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {
                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                //Recupera os dados em Bytes
                inputStream = conexao.getInputStream();

                //Lê os dados em Bytes e decodifica para caracteree
                inputStreamReader = new InputStreamReader(inputStream);

                //Objeto utilizado para leitura dos caracteres di InputStreamReader
                BufferedReader reader = new BufferedReader(inputStreamReader);
                buffer = new StringBuffer();
                String linha = "";

                while ( (linha = reader.readLine()) !=null){
                    buffer.append(linha);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {

            }
            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            String logradouro =null;
            String cep =null;
            String complemento =null;
            String bairro =null;
            String localidade =null;
            String uf =null;



            try {
                JSONObject jsonObject = new JSONObject(resultado);
                logradouro = jsonObject.getString("logradouro");
                cep = jsonObject.getString("cep");
                complemento = jsonObject.getString("complemento");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");
                uf = jsonObject.getString("uf");


            } catch (JSONException e) {
                e.printStackTrace();
            }

            textoResultado.setText( logradouro+" / "+cep+" / "+complemento+" / "+bairro+" / "+localidade+" / "+uf);

        }
    }



}
