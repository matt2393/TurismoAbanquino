package com.ejemplo.usuariio.turismoabanquino;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


public class FOTOSFragment extends Fragment {

    private View view;
    private static final String TAG = "MainActivity";
    private static final int NUM_COLUMNS = 2;

    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private int numero;

    private LugaresVo lugaresVo;
    private String nombre;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_foto, container, false);

        Bundle b = getArguments();
        numero=b.getInt("num2",0);
        lugaresVo=b.getParcelable("Lugar");

        nombre=b.getString("NOMBRE");
        mImageUrls=b.getStringArrayList("IMAGENES");

        /*mImageUrls=new ArrayList<>();
        for (Imagen im:lugaresVo.getImagenes().values()) {
            mImageUrls.add(im.getUrl());
        }*/


        Toast.makeText(getActivity(),"Bienvenido a Abancay . . . ", Toast.LENGTH_SHORT).show();


        initImageBitmaps(numero);


        return view;
    }


    private void initImageBitmaps(int num){

        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");



        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initializing staggered recyclerview.");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter =
                new StaggeredRecyclerViewAdapter(getActivity(),
                        nombre,
                        mImageUrls);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }

}
