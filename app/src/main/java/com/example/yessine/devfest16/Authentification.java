package com.example.yessine.devfest16;

/**
 * Created by yessine on 27/11/2016.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sadok on 15/10/2016.
 */
public class Authentification extends Activity {

    private Button btn;
    private EditText pass;
    private TextView ins;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentificationn);
        init();
    }

    public void init() {

        pass=(EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.button);
        ins=(TextView)findViewById(R.id.textView);
        pass.setText("0000");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pass.getText().toString().equals("0000")) {
                    Intent intent = new Intent(getApplicationContext(), MeuG.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Authentification.this, "Mot de passe incorrect !!! " , Toast.LENGTH_SHORT).show();
                }
            }
        });

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inscri.class);
                startActivity(intent);
            }
        });

    }
}