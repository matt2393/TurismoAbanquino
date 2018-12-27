package com.ejemplo.usuariio.turismoabanquino;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;


import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import  java.util.ArrayList ;
public class AdaptadorLugares extends RecyclerView.Adapter<AdaptadorLugares.ViewHolderLugares> implements View.OnClickListener {


    ArrayList<LugaresVo> listaLugares;
    private View.OnClickListener listener;
    private FragmentActivity fragmentActivity;

    public AdaptadorLugares(ArrayList<LugaresVo> listaLugares, FragmentActivity fragmentActivity) {
        this.listaLugares = listaLugares;
        this.fragmentActivity=fragmentActivity;
    }

    @NonNull
    @Override
    public ViewHolderLugares onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lugares,null,false);

        view.setOnClickListener(this );
        return new ViewHolderLugares(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderLugares viewHolderLugares, int i) {

        viewHolderLugares.etiNombre.setText(listaLugares.get(i).getNombre());
        viewHolderLugares.etiInformaion.setText(listaLugares.get(i).getInfo());

        Glide.with(fragmentActivity)
                .load(listaLugares.get(i).getUrl_foto())
                .into(viewHolderLugares.foto);

    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public void setListaLugares(ArrayList<LugaresVo> listaLugares) {
        this.listaLugares = listaLugares;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public class ViewHolderLugares extends RecyclerView.ViewHolder {
        TextView etiNombre,etiInformaion;
        ImageView foto;

        public ViewHolderLugares(@NonNull View itemView) {
            super(itemView);
            etiNombre=(TextView)itemView.findViewById(R.id.idNombre);
            etiInformaion=(TextView)itemView.findViewById(R.id.idInfo);
            foto=(ImageView)itemView.findViewById(R.id.idImagen);
        }

    }
}


