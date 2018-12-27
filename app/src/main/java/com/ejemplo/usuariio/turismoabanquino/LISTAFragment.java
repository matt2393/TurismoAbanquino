package com.ejemplo.usuariio.turismoabanquino;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class LISTAFragment extends Fragment {

    private View v;


    ArrayList<LugaresVo> listaLugares;
    RecyclerView recyclerViewlugares;

    private int numero;

    private Query query;
    private ValueEventListener valueEventListener;
    private AdaptadorLugares adaptadorLugares;



    public static LISTAFragment newInstance(ArrayList<LugaresVo> lugares){
        Bundle bn=new Bundle();
        bn.putParcelableArrayList("LUGARES",lugares);
        LISTAFragment ff=new LISTAFragment();
        ff.setArguments(bn);
        return ff;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_lista, container, false);

        listaLugares=getArguments().getParcelableArrayList("LUGARES");

        recyclerViewlugares=v.findViewById(R.id.recyclearView);
        recyclerViewlugares.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));

        adaptadorLugares=new AdaptadorLugares(listaLugares,getActivity());

        adaptadorLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fM=getActivity().getSupportFragmentManager();
                GALERIAFragment fg=new GALERIAFragment();
                Bundle b = new Bundle();
                b.putParcelable("Lugar",listaLugares.get(recyclerViewlugares.getChildAdapterPosition(v)));
                b.putInt("num1",recyclerViewlugares.getChildAdapterPosition(v));
                fg.setArguments(b);
                fM.beginTransaction().replace(R.id.contenedor,fg).addToBackStack(null).commit();
            }
        });

        recyclerViewlugares.setAdapter(adaptadorLugares);

        return v;
    }





    private void llenarPersonajes() {


    /*    listaLugares.add(new LugaresVo("Plaza de Armas de Abancay"," Plaza en la Provincia Abancay ",R.drawable.perfil1));
        listaLugares.add(new LugaresVo("Plaza Micaela Bastidas"," Plaza en la Provincia Abancay",R.drawable.perfil2));
        listaLugares.add(new LugaresVo("Catedral de Abancay"," Inglesia, templo,catedral en la Provincia Abancay",R.drawable.perfil3));
        listaLugares.add(new LugaresVo("Casa de David Samanez Ocampo"," Casa hacienda en Provincia Abancay",R.drawable.perfil4));
        listaLugares.add(new LugaresVo("Casa HaciendadDe Illanya"," Casa hacienda decProvincia Abancay",R.drawable.perfil5));
        listaLugares.add(new LugaresVo("Puente colonial pachachaca"," Río en la Provincia Abancay",R.drawable.perfil6));
        listaLugares.add(new LugaresVo("Santuario Nacional de Ampay"," Santuarios Nacionales Provincia en Abancay",R.drawable.perfil7));
        listaLugares.add(new LugaresVo("Parque Recreacional el Mirador (Taraccasa)"," Parques en la Provincia Abancay",R.drawable.perfil8));
        listaLugares.add(new LugaresVo("Conjunto Arqueológico De Saywite"," Fortaleza, plaza, cementerio en la Provincia Abancay",R.drawable.perfil9));
        listaLugares.add(new LugaresVo("Mirador Capitán Rumi"," Mirador Natural en la Provincia Abancay",R.drawable.perfil10));
        listaLugares.add(new LugaresVo("Baños Termales De Cconoc"," Aguas Termales en la Provincia Abancay",R.drawable.perfil11));
        listaLugares.add(new LugaresVo("Cañón Del Apurímac"," Cañones en la Provincia Abancay",R.drawable.perfil12));
        listaLugares.add(new LugaresVo("Mirador De Capuliyoc"," Mirador Natural en la Provincia Abancay",R.drawable.perfil13));
        listaLugares.add(new LugaresVo("Mirador De Kiuñalla"," Mirador Natural en la Provincia Abancay",R.drawable.perfil14));
        listaLugares.add(new LugaresVo("Camino Inca Huanipaca-Choqekiraw-Cachora"," Provincia Abancay",R.drawable.perfil15));
        listaLugares.add(new LugaresVo("Carnabal Abanquino"," Provincia Abancay",R.drawable.perfil16));
*/

       /* StorageReference ref=FirebaseStorage.getInstance().getReference()
                .child("ListaInicial");
        final ArrayList<LugaresVo> llall=new ArrayList<>();

        for(LugaresVo ll:listaLugares){
            final LugaresVo l=new LugaresVo();
            l.setNombre(ll.getNombre());
            l.setInfo(ll.getInfo());
            final StorageReference ref2=ref.child(ll.getNombre()+".jpg");
            ref2.putFile(Uri.parse("android.resource://com.example.usuariio.turismoabanquino/" + ll.getFoto()))
                    .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                            if(!task.isSuccessful())
                                throw  task.getException();
                            return ref2.getDownloadUrl();
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()){
                                Uri uri=task.getResult();
                                l.setUrl_foto(uri.toString());
                                llall.add(l);
                                if(listaLugares.size()==llall.size()){
                                    for (LugaresVo l1l:llall) {
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("Lugares").push().setValue(l1l);
                                    }
                                }
                            }
                        }
                    });

        }*/

        //listaLugares.add(new LugaresVo("Comidas Típicas"," Provincia Abancay",R.drawable.perfil17));

    }
}
