package com.example.yessine.devfest16;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by yessine on 26/11/2016.
 */

public class MeuG extends Activity {
    String pasword ;
    String email ;
    private DatabaseReference mDatabase;

    int  n=0;
    int i=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hhh);
        Intent i = getIntent();
        email = i.getStringExtra("email");
        pasword = i.getStringExtra("password");

        Toast.makeText(MeuG.this,"email = "+email,
                Toast.LENGTH_SHORT).show();
        Toast.makeText(MeuG.this,"pass = "+pasword,
                Toast.LENGTH_SHORT).show();
        mDatabase = FirebaseDatabase.getInstance().getReference();



        //writeNewUser("2", "felecity");
        //writeNewUser("3", "yessine");

        DatabaseReference databaseRef1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://yassska-f41df.firebaseio.com/");

        databaseRef1.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {


                for( DataSnapshot snapshot : dataSnapshot.child("users").getChildren() ) {

                    String emaile ;
                    String interet ;
                    String passwords ;
                    String  ch=snapshot.getValue().toString();
System.out.println(ch);

                    String T[] =ch.split("=");
                    System.out.println(T[1]);
                    String mail[]=T[1].split(",");
                    System.out.println(mail[0]);
                    emaile=mail[0];



                    String centre[] =T[2].split(",");
                    System.out.println(centre[0]);
                    interet= centre[0];

                   // String pass[]= T[3].split("}");
                 //   System.out.println(pass[0]);
               //     passwords=pass[0];
                    System.out.println(T[3]);
                   // String coord=email+"/"+interet+"/"+password;
String  mp = pasword+"}";
System.out.println(mp);


                    if((emaile.equalsIgnoreCase(email)))
                    {

                            Toast.makeText(MeuG.this, "centre = " + interet,
                                    Toast.LENGTH_SHORT).show();
                            System.out.println("voilaa" + interet);


                    }

               System.out.println(snapshot.getValue());
                }



            }

            @Override

            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }

}