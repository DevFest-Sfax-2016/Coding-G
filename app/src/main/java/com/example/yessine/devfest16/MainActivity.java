package com.example.yessine.devfest16;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    String Tnom[]=new String[50];
    String Tid[]=new String[50];
    int  n=0;
    int i=0;
    ArrayList<String> AN = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mDatabase = FirebaseDatabase.getInstance().getReference();



       //writeNewUser("2", "felecity");
        //writeNewUser("3", "yessine");

        DatabaseReference databaseRef1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://test-9aa71.firebaseio.com/");

        databaseRef1.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {


            AN=new ArrayList<String>();

                for( DataSnapshot snapshot : dataSnapshot.child("users").getChildren() ) {
                    String[] parts = snapshot.getValue().toString().split("nom=");

                          String[] part=     parts[1].split(",");

                    AN.add(part[0]);
                           //  n++;

                 //  System.out.println(snapshot.getValue());
                }



            }

            @Override

            public void onCancelled(DatabaseError databaseError) {

            }

        });



        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://test-9aa71.firebaseio.com/");

//Query is only being used because of onChildMoved.

//Can call addChildEventListener on databaseRef

        Query queryOrderByChild = databaseRef.orderByChild("test-9aa71");

        queryOrderByChild.addChildEventListener(new ChildEventListener() {

            @Override

            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

             //   Log.e(TAG, "onChildAdded");
                System.out.println("add");

            }

            @Override

            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

               // Log.e(TAG, "onChildChanged");
                System.out.println("changed");
                for(int j=0;j<AN.size();j++)
                    System.out.println(AN.get(j));

            }

            @Override

            public void onChildRemoved(DataSnapshot dataSnapshot) {

            //    Log.e(TAG, "onChildRemoved");

            }

            @Override

            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

              //  Log.e(TAG, "onChildMoved");
                System.out.println("removed");

            }

            @Override

            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
    private void writeNewUser(String id, String nom) {
       user user = new user(id, nom);

        mDatabase.child("users").push().setValue(user);
    //    mDatabase.child("users").push().setValue(user);


    }
}