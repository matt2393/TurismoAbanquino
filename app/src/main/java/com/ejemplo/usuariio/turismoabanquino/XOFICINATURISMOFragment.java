package com.ejemplo.usuariio.turismoabanquino;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


public class XOFICINATURISMOFragment extends Fragment {


    private View view;

    ArrayList<LugaresVo> listaInformacion;
    RecyclerView recyclerViewInformation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_xoficinaturismo, container, false);

        listaInformacion=new ArrayList<>();
        recyclerViewInformation=view.findViewById(R.id.recyclearViewInfomacionTurismo);
        recyclerViewInformation.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        llenarInforamcion();

        AdapterInformacion adaptadorInformacion=new AdapterInformacion(listaInformacion);
        adaptadorInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (recyclerViewInformation.getChildAdapterPosition(v)){

                    case 0:
                        //direccion de ubicacion
                        Uri uri = Uri.parse("https://www.google.com/maps/place/DIRCETUR+Apur%C3%ADmac/@-13.6361334,-72.8761983,17z/data=!3m1!4b1!4m5!3m4!1s0x916d037c0c1771b5:0x4fc4c2a4bf871bb2!8m2!3d-13.6361334!4d-72.8740096");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case 1:
                        //direccion de la pagina
                        Uri uris = Uri.parse("http://dirceturapurimac.gob.pe/web/");
                        Intent intent1 = new Intent(Intent.ACTION_VIEW, uris);
                        startActivity(intent1);
                        break;
                    case 2:
                        //direccion de correo
                        startActivity(new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","fuentesedge20@gmail.com", null)));
                        break;
                    case 3:
                        //Instegram
                        Uri uril = Uri.parse("http://instagram.com/_u/sanjosedeoruro");
                        Intent likeIng = new Intent(Intent.ACTION_VIEW, uril);

                        likeIng.setPackage("com.instagram.android");

                        try {
                            startActivity(likeIng);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("http://instagram.com/sanjosedeoruro")));
                        }
                        break;

                    case 4:
                        //4telefono
                        startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + 936889627)));
                        break;

                    case 5:
                        //WhatsApp
                        Intent _intencion = new Intent("android.intent.action.MAIN");
                        _intencion.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
                        _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("51" + 936889627)+"@s.whatsapp.net");
                        startActivity(_intencion);
                        break;
                    case 6:
                        //facebook
                        String facebookId = "fb://page/<Facebook Page ID>";
                        String urlPage = "https://www.facebook.com/Oficial.UTEA.THG/";
                        String face="fb://facewebmodal/f?href=" + urlPage;

                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(face)));
                        } catch (Exception e) {
                            Log.e(TAG, "Aplicación no instalada.");
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlPage)));
                        }
                        break;

                    case 7:
                        //messenger
                        //Uri ural = Uri.parse("fb-messenger://user/");
                        Uri ural = Uri.parse("https://www.facebook.com/Oficial.UTEA.THG/");
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "hola");
                        sendIntent.setType("text/plain");
                        sendIntent.setPackage("com.facebook.orca");
                        try {
                            startActivity(sendIntent);
                        }
                        catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getActivity(),"Instale Facebook Messenger", Toast.LENGTH_LONG).show();
                        }
                        break;

                }

            }
        });

        recyclerViewInformation.setAdapter(adaptadorInformacion);



        return view;
    }


    private void llenarInforamcion() {
        listaInformacion.add(new LugaresVo("Ubicacion en gps","Lugar donde te encuentres",R.drawable.ubicacion1));
        listaInformacion.add(new LugaresVo("Pagina web","Sitio turistico",R.drawable.www1));
        listaInformacion.add(new LugaresVo("Correo Electronico"," Para mayor informacion",R.drawable.email));
        listaInformacion.add(new LugaresVo("Instagrama"," Mas detalles",R.drawable.instagrama));
        listaInformacion.add(new LugaresVo("Telefono"," Para comunicarse",R.drawable.ic_phone_black_24dp));
        listaInformacion.add(new LugaresVo("WhatsApp"," Obtener informacion",R.drawable.whatsap));
        listaInformacion.add(new LugaresVo("Facebook"," Para poder refernciarse",R.drawable.faceboook));
        listaInformacion.add(new LugaresVo("Mesengeer"," Comunicarse",R.drawable.messenger));

    }


}
