package com.ejemplo.usuariio.turismoabanquino;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DialogImagen extends DialogFragment {
    public final static String TAG="DialogImagen";

    private String url;

    public static DialogImagen newInstance(String url){
        Bundle bn=new Bundle();
        bn.putString("URL_IMG",url);
        DialogImagen dd=new DialogImagen();
        dd.setArguments(bn);
        return dd;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
        View view=getActivity().getLayoutInflater()
                .inflate(R.layout.activity_image,null);
        ImageView img=view.findViewById(R.id.imagen_full);

        url=getArguments().getString("URL_IMG");
        if(url!=null) {
            Glide.with(this)
                    .load(url)
                    .into(img);
        }
        alert.setView(view);
        return alert.create();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1d1d1d")));
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
