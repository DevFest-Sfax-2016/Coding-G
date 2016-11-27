package com.example.yessine.devfest16;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Tableau extends Activity {

    private Context context;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<CustomAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String pasword ;
    String email ;
    private DatabaseReference mDatabase;

    int  n=0;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tableau);
        Intent intent = getIntent();
        ArrayList<ListItemWrapper> objectListList = new ArrayList<ListItemWrapper>();
        for (int i = 1; i < 9; i++) {
            switch(i)
            {
                case 1:{  ListItemWrapper wrapper = new ListItemWrapper("Achraf Ben Alaya " ,
                        "Age : 24 ans , Crise Cardiaque ", R.drawable.home);
                    objectListList.add(wrapper);}
                break;
                case 2: { ListItemWrapper wrapper = new ListItemWrapper("Anis kriaa " ,
                        "Age : 56 ans ,Crise  Cardiaque ", R.drawable.profil);
                    objectListList.add(wrapper);}
                break;
                case 3: { ListItemWrapper wrapper = new ListItemWrapper("Nejia Rkhis ",
                        "Age : 64 ans ,Hemoragie interne", R.drawable.profil);
                    objectListList.add(wrapper);}
                break;
                case 4: { ListItemWrapper wrapper = new ListItemWrapper("Sadok Chiha " ,
                        "Age : 24 ans , Crise Cardiaque ", R.drawable.profil);
                    objectListList.add(wrapper);}
                break;
                case 5:{  ListItemWrapper wrapper = new ListItemWrapper("Amine Affes ",
                        "Age : 64 ans ,Hemoragie interne", R.drawable.profil);
                    objectListList.add(wrapper);}
                break;
                case 6: { ListItemWrapper wrapper = new ListItemWrapper("Yassine Abdessamad ",
                        "Age : 64 ans ,Hemoragie interne", R.drawable.profil);
                    objectListList.add(wrapper);}
                break;
                case 7:  {ListItemWrapper wrapper = new ListItemWrapper("Achraf Ben Alaya " ,
                        "Age : 24 ans , Crise Cardiaque ", R.drawable.profil);
                    objectListList.add(wrapper);}
                break;
                case 8:  {ListItemWrapper wrapper = new ListItemWrapper("Ahmed Ben Amor ",
                        "Age : 64 ans ,Hemoragie interne", R.drawable.profil);
                    objectListList.add(wrapper);}
                break;
            }



            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(context, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            // do whatever
                           // Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            //startActivity(intent);
                            /*try {
                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                r.play();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }*/
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            // do whatever
                        }
                    })
            );


        }


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);


        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new CustomAdapter(objectListList);
        mRecyclerView.setAdapter(mAdapter);



        Intent i = getIntent();
        email = i.getStringExtra("email");
        pasword = i.getStringExtra("password");

        Toast.makeText(Tableau.this,"email = "+email,
                Toast.LENGTH_SHORT).show();
        Toast.makeText(Tableau.this,"pass = "+pasword,
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

                        Toast.makeText(Tableau.this, "centre = " + interet,
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

