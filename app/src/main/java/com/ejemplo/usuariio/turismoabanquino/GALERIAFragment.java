package com.ejemplo.usuariio.turismoabanquino;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class GALERIAFragment extends Fragment {

    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    private int numeroPos;
    private ImageView Imagenlugar;
    private TextView NobreLugar;

    private LugaresVo lugaresVo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);

        Imagenlugar =view.findViewById(R.id.IMAGENLUGAR);
        NobreLugar =view.findViewById(R.id.NOMBRELUGAR);

        //Imagenlugar.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        //Imagenlugar.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

        View contenedor = (View) container.getParent();
        appBar = (AppBarLayout) contenedor.findViewById(R.id.appBar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"));
        appBar.addView(pestanas);

        viewPager = (ViewPager) view.findViewById(R.id.pager1);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        pestanas.setupWithViewPager(viewPager);



        Bundle b = getArguments();
        numeroPos=b.getInt("num1");
        lugaresVo=b.getParcelable("Lugar");

        if(lugaresVo!=null) {
            NobreLugar.setText(lugaresVo.getNombre());
            Glide.with(getActivity())
                    .load(lugaresVo.getUrl_foto())
                    .into(Imagenlugar);
        }

        //mostrarLugarImagen(numeroPos);

        return view;
    }

    public void mostrarLugarImagen(int num){
        String Lugares[]={"Plaza de Armas de Abancay",
                "Plaza Micaela Bastidas",
                "Catedral de Abancay",
                "Casa de David Samanez Ocampo",
                "Casa HaciendadDe Illanya",
                "Puente colonial pachachaca",
                "Santuario Nacional de Ampay",
                "Parque Recreacional el Mirador (Taraccasa)",
                "Conjunto Arqueológico De Saywite",
                "Mirador Capitán Rumi",
                "Baños Termales De Cconoc",
                "Cañón Del Apurímac",
                "Mirador De Capuliyoc",
                "Mirador De Kiuñalla",
                "Camino Inca Huanipaca-Choqekiraw-Cachora",
                "Carnabal Abanquino"}   ;
     /*   switch(num){
            case 0: NobreLugar.setText(Lugares[0]);
            Imagenlugar.setImageResource(R.drawable.perfil1);
                    break;
            case 1: NobreLugar.setText(Lugares[1]);
                Imagenlugar.setImageResource(R.drawable.perfil2);
                break;

            case 2: NobreLugar.setText(Lugares[2]);
                Imagenlugar.setImageResource(R.drawable.perfil3);
                break;
            case 3: NobreLugar.setText(Lugares[3]);
                Imagenlugar.setImageResource(R.drawable.perfil4);
                break;
            case 4: NobreLugar.setText(Lugares[4]);
                Imagenlugar.setImageResource(R.drawable.perfil5);
                break;
            case 5: NobreLugar.setText(Lugares[5]);
                Imagenlugar.setImageResource(R.drawable.perfil6);
                break;
            case 6: NobreLugar.setText(Lugares[6]);
                Imagenlugar.setImageResource(R.drawable.perfil7);
                break;
            case 7: NobreLugar.setText(Lugares[7]);
                Imagenlugar.setImageResource(R.drawable.perfil8);
                break;
            case 8: NobreLugar.setText(Lugares[8]);
                Imagenlugar.setImageResource(R.drawable.perfil9);
                break;
            case 9: NobreLugar.setText(Lugares[9]);
                Imagenlugar.setImageResource(R.drawable.perfil10);
                break;
            case 10: NobreLugar.setText(Lugares[10]);
                Imagenlugar.setImageResource(R.drawable.perfil11);
                break;
            case 11: NobreLugar.setText(Lugares[11]);
                Imagenlugar.setImageResource(R.drawable.perfil12);
                break;
            case 12: NobreLugar.setText(Lugares[12]);
                Imagenlugar.setImageResource(R.drawable.perfil13);
                break;
            case 13: NobreLugar.setText(Lugares[13]);
                Imagenlugar.setImageResource(R.drawable.perfil14);
                break;

            case 14: NobreLugar.setText(Lugares[14]);
                Imagenlugar.setImageResource(R.drawable.perfil15);
                break;
            case 15: NobreLugar.setText(Lugares[15]);
                Imagenlugar.setImageResource(R.drawable.perfil16);
                break;

        }
*/

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(pestanas);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }



        String[] tituloPestana = {"INFORMACIÓN", "DESCRIPCIÓN", "FOTOS"};



        @Override
        public Fragment getItem(int posecion) {



            switch (posecion) {
                case 0:
                    INFORMACIONFragment inf=new INFORMACIONFragment();
                    Bundle b0 = new Bundle();
                    b0.putInt("num2",numeroPos);
                    b0.putParcelable("LUGAR",lugaresVo);
                    inf.setArguments(b0);
                    return inf;

                case 1:
                    DESCRIPCIONFragment des=new DESCRIPCIONFragment();
                    Bundle b1 = new Bundle();
                    b1.putInt("num2",numeroPos);
                    b1.putParcelable("Lugar",lugaresVo);
                    des.setArguments(b1);
                    return des;

                case 2:
                    FOTOSFragment ff=new FOTOSFragment();
                    Bundle b2 = new Bundle();
                    b2.putInt("num2",numeroPos);
                    ArrayList<String> urls=new ArrayList<>();
                    for (Imagen ii:lugaresVo.getImagenes().values()) {
                        urls.add(ii.getUrl());
                    }
                    b2.putStringArrayList("IMAGENES",urls);
                    b2.putString("NOMBRE",lugaresVo.getNombre());
                    ff.setArguments(b2);
                    return ff;

            }

            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tituloPestana[position];

        }

        @Override
        public int getCount() {
            return 3;
        }


    }

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                    finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
}
