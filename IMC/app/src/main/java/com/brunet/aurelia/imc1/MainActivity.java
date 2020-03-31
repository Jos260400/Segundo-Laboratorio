package com.brunet.aurelia.imc1;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {



    private final String defaut = "Debe hacer clic en el botón \"Calcular IMC\" para obtener un resultado.";
    private final String megaString = "¡Peso " +
            "perfecto!";
    Button envoyer = null;
    Button raz = null;
    EditText poids = null;
    EditText taille = null;
    RadioGroup group = null;
    TextView result = null;
    CheckBox mega = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        envoyer = findViewById(R.id.calcul);
        raz = findViewById(R.id.raz);
        poids = findViewById(R.id.poids);
        taille = findViewById(R.id.taille);
        group = findViewById(R.id.group);
        result = findViewById(R.id.result);
        mega = findViewById(R.id.mega);

        envoyer.setOnClickListener(envoyerListener);
        raz.setOnClickListener(razListener);

        taille.addTextChangedListener(textWatcher);
        poids.addTextChangedListener(textWatcher);
        mega.setOnClickListener(checkedListener);

    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            result.setText(defaut);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

    };


    private OnClickListener envoyerListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!mega.isChecked()) {


                String t = taille.getText().toString();

                String p = poids.getText().toString();

                float tValue = Float.valueOf(t);
                float pValue = Float.valueOf(p);



                if(tValue == 0)
                    Toast.makeText(MainActivity.this, "¿Eres un mini-inshout?", Toast.LENGTH_SHORT).show();


                else if(pValue == 0)
                    Toast.makeText(MainActivity.this, "Muy delgado", Toast.LENGTH_SHORT).show();

                else {



                    if(group.getCheckedRadioButtonId() == R.id.radio2)
                        tValue = tValue / 100;

                    tValue = (float)Math.pow(tValue, 2);
                    float imc = pValue / tValue;
                    result.setText("Su IMC es " + String.valueOf(imc));
                }

            } else
                result.setText(megaString);
        }
    };


    private View.OnClickListener razListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            poids.getText().clear();
            taille.getText().clear();
            result.setText(defaut);
        }
    };



    private View.OnClickListener checkedListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {


            if(!((CheckBox)v).isChecked() && result.getText().equals(megaString))
                result.setText(defaut);

        }
    };











}
