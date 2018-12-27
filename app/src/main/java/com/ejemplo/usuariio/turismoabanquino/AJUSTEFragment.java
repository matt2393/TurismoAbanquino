package com.ejemplo.usuariio.turismoabanquino;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class AJUSTEFragment extends Fragment {
    private View view;
    private Switch sw1,sw2,sw3,sw4;
    private SharedPreferences preferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_ajuste, container, false);

        sw1=view.findViewById(R.id.switch1);
        sw2=view.findViewById(R.id.switch2);
        sw3=view.findViewById(R.id.switch3);
        sw4=view.findViewById(R.id.switch4);

        preferences=PreferenceManager.getDefaultSharedPreferences(getContext().getApplicationContext());



        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferences.edit().putInt("P_E",isChecked?1:0).apply();
            }
        });

        //Switch1();
       // Switch2();
        return view;
    }


    public void Switch1(){

        if (!sw1.isChecked()) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            //btnaceptar.setEnabled(false);
        } else {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            Toast.makeText(getActivity(), "Activado", Toast.LENGTH_SHORT).show();
            //btnaceptar.setEnabled(false);
        }
    }

    public void Switch2(){
        if (!sw1.isChecked()) {
            Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getActivity(), "Activado Edgar", Toast.LENGTH_SHORT).show();

        }
    }



}
