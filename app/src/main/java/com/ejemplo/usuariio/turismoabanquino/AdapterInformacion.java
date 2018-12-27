package com.ejemplo.usuariio.turismoabanquino;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterInformacion extends RecyclerView.Adapter<AdapterInformacion.ViewHolderInformacion> implements View.OnClickListener {

    ArrayList<LugaresVo> listaInformacion;
    private View.OnClickListener listener;

    public AdapterInformacion(ArrayList<LugaresVo> listaInformacio) {
        this.listaInformacion = listaInformacio;
    }

    @NonNull
    @Override
    public ViewHolderInformacion onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_informacion,null,false);

        view.setOnClickListener(this );
        return new ViewHolderInformacion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInformacion viewHolderInformacion, int i) {

        viewHolderInformacion.etiNombre.setText(listaInformacion.get(i).getNombre());
        viewHolderInformacion.etiInformaion.setText(listaInformacion.get(i).getInfo());
        viewHolderInformacion.foto.setImageResource(listaInformacion.get(i).getFoto());
    }

    @Override
    public int getItemCount() {
        return listaInformacion.size();
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


    public class ViewHolderInformacion extends RecyclerView.ViewHolder{

        TextView etiNombre,etiInformaion;
        ImageView foto;

        public ViewHolderInformacion(@NonNull View itemView) {
            super(itemView);
            etiNombre=(TextView)itemView.findViewById(R.id.idNombreInfo);
            etiInformaion=(TextView)itemView.findViewById(R.id.idInforInf);
            foto=(ImageView)itemView.findViewById(R.id.idImagenInfo);
        }

    }
}


