package com.ejemplo.usuariio.turismoabanquino;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.HashMap;

public class LugaresVo implements Parcelable, Serializable {

    private String nombre;
    private String info;
    private int foto;
    private String url_foto;
    private String key;
    private String pdf;
    private HashMap<String,Imagen> imagenes;
    private Localizacion localizacion;

    public LatLng toLatLng(){
        return new LatLng(localizacion.getLat(),localizacion.getLng());
    }
    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }

    public HashMap<String, Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(HashMap<String, Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public LugaresVo(){

    }

    public LugaresVo(String nombre, String info, int foto) {
        this.nombre = nombre;
        this.info = info;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.info);
        dest.writeInt(this.foto);
        dest.writeString(this.url_foto);
        dest.writeString(this.key);
        dest.writeString(this.pdf);
        dest.writeSerializable(this.imagenes);
        dest.writeParcelable(this.localizacion, flags);
    }

    protected LugaresVo(Parcel in) {
        this.nombre = in.readString();
        this.info = in.readString();
        this.foto = in.readInt();
        this.url_foto = in.readString();
        this.key = in.readString();
        this.pdf = in.readString();
        this.imagenes = (HashMap<String, Imagen>) in.readSerializable();
        this.localizacion = in.readParcelable(Localizacion.class.getClassLoader());
    }

    public static final Creator<LugaresVo> CREATOR = new Creator<LugaresVo>() {
        @Override
        public LugaresVo createFromParcel(Parcel source) {
            return new LugaresVo(source);
        }

        @Override
        public LugaresVo[] newArray(int size) {
            return new LugaresVo[size];
        }
    };
}
