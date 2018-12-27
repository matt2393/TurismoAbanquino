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


public class INFORMACIONFragment extends Fragment {

    private View view;

    ArrayList<LugaresVo> listaInformaciones;
    RecyclerView recyclerViewInformacion;

    private int numero,telefono;
    private String facebook, messenger,watsap,correo, telegrama,uralUbicacion, uralPagina,horario,precio;

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

    public String getWatsap() {
        return watsap;
    }

    public void setWatsap(String watsap) {
        this.watsap = watsap;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelegrama() {
        return telegrama;
    }

    public void setTelegrama(String telegrama) {
        this.telegrama = telegrama;
    }

    public String getUralUbicacion() {
        return uralUbicacion;
    }

    public void setUralUbicacion(String uralUbicacion) {
        this.uralUbicacion = uralUbicacion;
    }

    public String getUralPagina() {
        return uralPagina;
    }

    public void setUralPagina(String uralPagina) {
        this.uralPagina = uralPagina;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }


    private LugaresVo lugar;

    public static INFORMACIONFragment newInstance(LugaresVo ll){
        Bundle bn=new Bundle();
        bn.putParcelable("LUGAR",ll);
        INFORMACIONFragment ff=new INFORMACIONFragment();
        ff.setArguments(bn);
        return ff;
    }


    private String []UrlLugares={
            "https://www.google.com/search?q=dircetur+turismo+abancay&npsic=0&rflfq=1&rlha=0&rllag=-13637850,-72879503,623&tbm=lcl&ved=2ahUKEwiJ59-H97PfAhVsrlkKHWs_CT4QtgN6BAgBEAQ&tbs=lrf:!2m4!1e17!4m2!17m1!1e2!2m1!1e3!3sIAE,lf:1,lf_ui:1&rldoc=1#rlfi=hd:;si:,-13.635445255464859,-72.87388085396731;mv:!1m2!1d-13.63338080983039!2d-72.87181018860781!2m2!1d-13.638208247900835!2d-72.88177727730715!4m2!1d-13.635794541199166!2d-72.87679373295748!5i17",
            "http://dirceturapurimac.gob.pe/web/",
            "fuentesedge20@gmail.com",
            "http://instagram.com/_u/sanjosedeoruro",
            "921213190",



    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_informacion, container, false);

        Bundle b = getArguments();
        numero=b.getInt("num2",0);
        lugar=b.getParcelable("LUGAR");


        /*ImageView lineColorCode = (ImageView)convertView.findViewById(R.id.line_color_code);
        int color = Color.parseColor("#AE6118"); //The color u want lineColorCode.setColorFilter(color);
*/
        listaInformaciones=new ArrayList<>();
        recyclerViewInformacion=view.findViewById(R.id.recyclearViewInfomacion);
        recyclerViewInformacion.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        llenarInforamcion();

        AdapterInformacion adaptadorInformacion=new AdapterInformacion(listaInformaciones);
        adaptadorInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (recyclerViewInformacion.getChildAdapterPosition(v)){

                    case 0:
                        //direccion de ubicacion
                        /*Uri uri = Uri.parse("https://www.google.com/maps/place/DIRCETUR+Apur%C3%ADmac/@-13.6361334,-72.8761983,17z/data=!3m1!4b1!4m5!3m4!1s0x916d037c0c1771b5:0x4fc4c2a4bf871bb2!8m2!3d-13.6361334!4d-72.8740096");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);*/
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.contenedor,FragmentMap.newInstance(1,lugar,null))
                                .addToBackStack(null)
                                .commit();
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

                    case 8:
                        //horario

                        break;
                }

            }
        });

        recyclerViewInformacion.setAdapter(adaptadorInformacion);

        return view;

    }


    private void llenarInforamcion() {

        listaInformaciones.add(new LugaresVo("Ubicacion del sitio","En el mapa",R.drawable.ubicacion2));
        listaInformaciones.add(new LugaresVo("Pagina web","Sitio turistico",R.drawable.www1));
        listaInformaciones.add(new LugaresVo("Correo Electronico"," Para mayor informacion",R.drawable.email));
        listaInformaciones.add(new LugaresVo("Instagrama"," Mas detalles",R.drawable.instagrama));
        listaInformaciones.add(new LugaresVo("Telefono"," Para comunicarse",R.drawable.ic_phone_black_24dp));
        listaInformaciones.add(new LugaresVo("WhatsApp"," Obtener informacion",R.drawable.whatsap));
        listaInformaciones.add(new LugaresVo("Facebook"," Para poder refernciarse",R.drawable.faceboook));
        listaInformaciones.add(new LugaresVo("Mesengeer"," Comunicarse",R.drawable.messenger));
        listaInformaciones.add(new LugaresVo("Horario",":Lunes a Domingo",R.drawable.horario));
        //listaInformaciones.add(new LugaresVo("Precio","20 soles",R.drawable.precio));
    }


}

