package com.ejemplo.usuariio.turismoabanquino;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener,MAPAFragment.OnFragmentInteractionListener{


    private Query query;
    private ValueEventListener valueEventListener;

    private Button btn;

    ArrayList<LugaresVo> listaLugares;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}
                , 12345);

        FloatingActionButton camara = (FloatingActionButton) findViewById(R.id.camara);
        camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Tomate una foto", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                    //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //startActivity(intent);

            }
        });
        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        int pantalla=preferences.getInt("P_E",0);
        if(pantalla==1)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        else
            if(pantalla==0)
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        listaLugares=new ArrayList<>();
        query=FirebaseDatabase.getInstance().getReference().child("Lugares");
        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    LugaresVo ll=snapshot.getValue(LugaresVo.class);
                    if(ll!=null) {
                        ll.setKey(snapshot.getKey());
                        listaLugares.add(ll);
                    }
                }
                Log.e("LLL","lugares");
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contenedor,GalleryFragment.newInstance(listaLugares)).commit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("LLL","error "+databaseError.getMessage());
            }
        };





        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

/*      btn=findViewById(R.id.btnMapa);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                Intent startIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(startIntent);
            }
        });

*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.ajustesMenu) {
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,new AJUSTEFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fM=getSupportFragmentManager();


        if (id == R.id.oficiturism) {
            fM.beginTransaction().replace(R.id.contenedor,new XOFICINATURISMOFragment()).commit();

        } else if (id == R.id.emergencia) {

            fM.beginTransaction().replace(R.id.contenedor,new XEMERGENCIASFragment()).commit();

        } else if (id == R.id.hoteles) {

            fM.beginTransaction().replace(R.id.contenedor,new HOTELESYMOTELESFragment()).commit();

        } else if (id == R.id.restaurantes) {
            fM.beginTransaction().replace(R.id.contenedor,new RESTAURANTESFragment()).commit();

        } else if (id == R.id.entretenimiento) {
            fM.beginTransaction().replace(R.id.contenedor,new XENTRETENIMIENTOFragment()).commit();

        } else if (id == R.id.compartir) {
            Compartir1();

        } else if (id == R.id.Valorar) {
            vApp();

        }else if (id == R.id.contacto) {

            startActivity(new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","fuentesedge20@gmail.com", null)));

        } else if (id == R.id.ajuste) {
            fM.beginTransaction().replace(R.id.contenedor,new AJUSTEFragment()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Compartir1(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        //intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"fuentesedge20@gmail.com"});
        intent.putExtra(Intent.EXTRA_TEXT, "Correo electronico: fuentesedge20@gmail.com/");
        startActivity(Intent.createChooser(intent, "Share with"));
    }



    private void vApp(){
        Uri uri = Uri.parse("market://details?id="+getBaseContext().getPackageName());

        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        try{
            startActivity(intent);
        }catch (Exception e ){
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onStart() {
        super.onStart();
        if(query!=null && valueEventListener!=null)
            query.addValueEventListener(valueEventListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(query!=null && valueEventListener!=null)
            query.removeEventListener(valueEventListener);
    }


}
