package com.example.alcoolgasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editPrecoAlcool;
    private EditText editPrecoGasolina;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPrecoAlcool = findViewById(R.id.alcool_editText);
        editPrecoGasolina = findViewById(R.id.gasolina_editText);
        textResultado = findViewById(R.id.resultado_id);

    }

    public void calcularPreco(View view){

        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();

        Boolean camposValidados = this.validarCampos(precoAlcool,precoGasolina);

        if (camposValidados){
            this.calcularMelhorPreco(precoAlcool,precoGasolina);

        }else{
            textResultado.setText("Preencha os camppos primeiros");
        }

    }

    public void calcularMelhorPreco(String pAlcool , String pGasolina){
        Double precoAlcool  = Double.parseDouble(pAlcool);
        Double precoGasolina = Double.parseDouble(pGasolina);
        Double resultado = precoAlcool / precoGasolina;

        if (resultado>=0.7){
            textResultado.setText("Melhor utilizar Gasolina "+resultado);
        } else {
            textResultado.setText("Melhor utilizar Álcool "+resultado);
        }

    }

    public Boolean validarCampos(String pAlcool, String pGasolina){

        Boolean camposValidados = true;

        if (pAlcool==null || pAlcool.equals("")){
            camposValidados = false;
        } else if (pGasolina == null || pGasolina.equals("")) {
            camposValidados = false;
        }

        return camposValidados;
    }

}
