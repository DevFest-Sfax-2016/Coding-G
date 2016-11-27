package com.example.yessine.devfest16;

/**
 * Created by yessine on 26/11/2016.
 */


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Inscri extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "hhh";
    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;

    private String array_spinner[];

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    String  centreInteret="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentif);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        array_spinner=new String[5];

        array_spinner[0]="Charite";
        array_spinner[1]="Politique";
        array_spinner[2]="Foot";
        array_spinner[3]="Developper";
        array_spinner[4]="Photographe";
        Spinner s = (Spinner) findViewById(R.id.Spinner01);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_spinner);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
             //.   System.out.println();
                switch (position)
                {
                    case 0: {
                        centreInteret = "charite";
                        break;
                    }
                    case 1: {
                        centreInteret = "politique";
                        break;
                    }
                    case 2: {
                        centreInteret = "foot";
                        break;
                    }
                    case 3: {
                        centreInteret = "developper";
                        break;
                    }
                    case 4: {
                        centreInteret = "photographe";
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    private void registerUser(){

        //getting email and password from edit texts
     final   String email = editTextEmail.getText().toString().trim();
        final String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();





        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(Inscri.this,"Successfully registered", Toast.LENGTH_LONG).show();
                            writeNewUser(email,centreInteret,password);
                        }else{
                            //display some message here
                            Toast.makeText(Inscri.this,"error ", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {
        //calling register method on click
        registerUser();
    }
    private void writeNewUser(String id,String centreInteret, String nom) {
        user user = new user(id,centreInteret, nom);

        mDatabase.child("users").push().setValue(user);
        //    mDatabase.child("users").push().setValue(user);


    }
}