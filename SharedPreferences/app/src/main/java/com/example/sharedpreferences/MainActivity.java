package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharedpreferences.R;

public class MainActivity extends AppCompatActivity {

    private Button buttonSalvar;
    private TextInputEditText editNome;
    private TextView textResultado;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSalvar = findViewById(R.id.button);
        editNome = findViewById(R.id.nome_input_text_id);
        textResultado = findViewById(R.id.text_resultado_id);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if (editNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Preencha o nome",Toast.LENGTH_LONG).show();
                } else {
                    String nome = editNome.getText().toString();
                    editor.putString("nome",nome);
                    editor.commit();
                    textResultado.setText("Olá, "+ nome);
                }
            }
        });

        //Recuperar os dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        // Validar se temos o nome preferencias

        if (preferences.contains("nome")){

            String nome = preferences.getString("nome","usuário");
            textResultado.setText("Olá, "+nome);

        }else {
            textResultado.setText("olá, usuário não definido");
        }
    }
}


