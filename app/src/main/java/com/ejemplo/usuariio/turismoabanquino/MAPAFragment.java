package com.ejemplo.usuariio.turismoabanquino;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MAPAFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private OnFragmentInteractionListener mListener;

    public MAPAFragment() {

    }

    public static MAPAFragment newInstance(String param1, String param2) {
        MAPAFragment fragment = new MAPAFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Spinner lugares;
    List<String> listaLugares;
    ArrayAdapter<String> adapterSpinner;
    private String []LugaresTuristicos={
            "Plaza de Armas de Abancay",
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
            "Carnabal Abanquino",
    };

    private String LugarBuscado;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        /*Intent intent=new Intent(getActivity(),MapsActivity.class);
        getActivity().startActivity(intent);

*/
        //Intent startIntent = new Intent(this, MapsActivity.class);
        //startActivity(startIntent);


        view= inflater.inflate(R.layout.fragment_mapa, container, false);

        WebView myWebView = (WebView)view.findViewById(R.id.myWebView);
        myWebView.loadUrl("https://www.google.com/maps/dir/-13.6323411,-72.8853735/Mirador+de+Taraccasa/@-13.6271215,-72.8860412,14z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x916d031b2f137a2b:0xf2e47655e0628413!2m2!1d-72.8640279!2d-13.6243621");
        myWebView.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);




        lugares=view.findViewById(R.id.spinnerLugares);


        listaLugares = new ArrayList<>();
        Collections.addAll(listaLugares, LugaresTuristicos);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, LugaresTuristicos);
        lugares.setAdapter(adapter);

        proceso();

        return view;
    }

    public void proceso()
    { lugares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    { @Override
    public void onItemSelected(AdapterView av, View v, int p, long id)
    {

        switch(p)
        {   case 0: Toast.makeText(getActivity(), "FNI ES MI FACU 111 : ", Toast.LENGTH_SHORT).show(); break;
            case 1: Toast.makeText(getActivity(), "FNI ES MI FACU 222 : ", Toast.LENGTH_SHORT).show(); break;
            case 2: Toast.makeText(getActivity(), "FNI ES MI FACU 333 : ", Toast.LENGTH_SHORT).show(); break;
            case 3: Toast.makeText(getActivity(), "FNI ES MI FACU 444 : ", Toast.LENGTH_SHORT).show(); break;
        }

    }

        @Override
        public void onNothingSelected(AdapterView p)
        { }
    });
    }

    private class MyWebViewClient extends WebViewClient {

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView mWebView;
        mWebView = (WebView) view.findViewById(R.id.myWebView);
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWebView.canGoBack()) {
                        mWebView.goBack();
                    } else {

                    }
                    return true;
            }
        }
        return onKeyDown(keyCode, event);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

