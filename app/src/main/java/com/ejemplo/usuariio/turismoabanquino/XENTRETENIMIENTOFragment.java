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


public class XENTRETENIMIENTOFragment extends Fragment {

    private View view;

    ArrayList<LugaresVo> listaEntretenimiento;
    RecyclerView recyclerViewEntretenimiento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_xentretenimiento, container, false);



        listaEntretenimiento=new ArrayList<>();
        recyclerViewEntretenimiento=view.findViewById(R.id.recyclearViewEntretenimiento);
        recyclerViewEntretenimiento.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        llenarInforamcion();

        AdapterInformacion adaptadorInformacion=new AdapterInformacion(listaEntretenimiento);
        adaptadorInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (recyclerViewEntretenimiento.getChildAdapterPosition(v)){

                    case 0:
                        //direccion de ubicacion
                        Uri uri = Uri.parse("https://www.google.com/search?q=dircetur+turismo+abancay&npsic=0&rflfq=1&rlha=0&rllag=-13637850,-72879503,623&tbm=lcl&ved=2ahUKEwiJ59-H97PfAhVsrlkKHWs_CT4QtgN6BAgBEAQ&tbs=lrf:!2m4!1e17!4m2!17m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:1&rldoc=1#rlfi=hd:;si:,-13.635445255464859,-72.87388085396731;mv:!1m2!1d-13.63338080983039!2d-72.87181018860781!2m2!1d-13.638208247900835!2d-72.88177727730715!4m2!1d-13.635794541199166!2d-72.87679373295748!5i17");
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
                        startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + 921213190)));
                        break;

                    case 5:
                        //WhatsApp
                        Intent _intencion = new Intent("android.intent.action.MAIN");
                        _intencion.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
                        _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("51" + 921213190)+"@s.whatsapp.net");
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

                    case 8:
                        //horario

                        break;
                }

            }
        });

        recyclerViewEntretenimiento.setAdapter(adaptadorInformacion);



        return view;
    }

    private void llenarInforamcion() {
        listaEntretenimiento.add(new LugaresVo("Estadiones","",R.drawable.ubicacion2));
        listaEntretenimiento.add(new LugaresVo("Pagina web","Sitio turistico",R.drawable.www1));
        listaEntretenimiento.add(new LugaresVo("Correo Electronico"," Para mayor informacion",R.drawable.email));
        listaEntretenimiento.add(new LugaresVo("Instagrama"," Mas detalles",R.drawable.instagrama));
        listaEntretenimiento.add(new LugaresVo("Telefono"," Para comunicarse",R.drawable.ic_phone_black_24dp));
        listaEntretenimiento.add(new LugaresVo("WhatsApp"," Obtener informacion",R.drawable.whatsap));
        listaEntretenimiento.add(new LugaresVo("Facebook"," Para poder refernciarse",R.drawable.faceboook));
        listaEntretenimiento.add(new LugaresVo("Mesengeer"," Comunicarse",R.drawable.messenger));

    }
}
