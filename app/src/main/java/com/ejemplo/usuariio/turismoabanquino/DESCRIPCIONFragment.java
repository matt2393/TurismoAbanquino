package com.ejemplo.usuariio.turismoabanquino;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class DESCRIPCIONFragment extends Fragment {

    private View view;
    private int numero;
    private PDFView pdfLugares;
    private LugaresVo lugaresVo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.fragment_descripcion, container, false);

        Bundle b = getArguments();
        numero=b.getInt("num2",0);
        lugaresVo=b.getParcelable("Lugar");

        pdfLugares=view.findViewById(R.id.pdfView);

        StorageReference reference=FirebaseStorage.getInstance()
                .getReferenceFromUrl(lugaresVo.getPdf());
        reference.getBytes(1024*1024)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        pdfLugares.fromBytes(bytes)
                                .load();
                        Log.e("PDF","cargado");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("PDF","error");
                    }
                });



/*
* pdfView.fromUri(Uri)
pdfView.fromFile(File)
pdfView.fromBytes(byte[])
pdfView.fromStream(InputStream)
pdfView.fromAsset(String)*/


       /* switch (numero){

            case 0:pdfLugares.fromAsset("1 Plaza de armas de Abancay.pdf").load();
                            pdf("Plaza_de_armas_de_Abancay");break;
            case 1:pdfLugares.fromAsset("2 Plaza Micaela Bastidas.pdf").load();
                    pdf("plaza_micaela_bastidas");break;
            case 2:pdfLugares.fromAsset("3 Catedral De Abancay.pdf").load();
                    pdf("Catedral_De_Abancay");break;
            case 3:pdfLugares.fromAsset("4 Casa De David Samanez Ocampo.pdf").load();
                    pdf("Casa_De_David_Samanez_Ocampo");break;
            case 4:pdfLugares.fromAsset("5 Casa Hacienda Illanya.pdf").load();
                    pdf("Casa_Hacienda_Illanya");break;
            case 5:pdfLugares.fromAsset("6 Puente colonial pachachaca.pdf").load();
                    pdf("Puente_colonial_pachachaca");break;
            case 6:pdfLugares.fromAsset("7 Santuario Nacional De Ampay.pdf").load();
                    pdf("Santuario_Nacional_De_Ampay");break;
            case 7:pdfLugares.fromAsset("8 Mirador de Taraccasa.pdf").load();
                    pdf("Mirador_de_Taraccasa");break;
            case 8:pdfLugares.fromAsset("9 Conjunto Arqueológico de Saywite.pdf").load();
                    pdf("conjunto_arqueologico_de_saywite");break;
            case 9:pdfLugares.fromAsset("10 Mirador Capitán Rumi.pdf").load();
                    pdf("Mirador_Capitan_Rumi");break;
            case 10:pdfLugares.fromAsset("11 Baños Termales De Cconoc.pdf").load();
                    pdf("Banos_Termales_De_Cconoc");break;
            case 11:pdfLugares.fromAsset("12 Cañón Del Apurímac.pdf").load();
                    pdf("Canon_Del_Apurimac");break;
            case 12:pdfLugares.fromAsset("13 Mirador De Capuliyoc.pdf").load();
                    pdf("Mirador_De_Capuliyoc");break;
            case 13:pdfLugares.fromAsset("14 Mirador De Kiuñalla.pdf").load();
                    pdf("mirador_de_kiunalla");break;
            case 14:pdfLugares.fromAsset("15 Camino Inca Huanipaca-Choqekiraw-Cachora.pdf").load();
                    pdf("Camino_Inca_Huanipaca_Choqekiraw_Cachora");break;
            case 15:pdfLugares.fromAsset("16 Carnaval Abanquino.pdf").load();
                    pdf("Carnaval_Abanquino");break;

        }*/





        return view;
    }



}
