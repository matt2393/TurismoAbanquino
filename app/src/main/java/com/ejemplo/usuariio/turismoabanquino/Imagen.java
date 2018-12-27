package com.ejemplo.usuariio.turismoabanquino;

import android.os.Parcel;
import android.os.Parcelable;

public class Imagen implements Parcelable {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
    }

    public Imagen() {
    }

    protected Imagen(Parcel in) {
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Imagen> CREATOR = new Parcelable.Creator<Imagen>() {
        @Override
        public Imagen createFromParcel(Parcel source) {
            return new Imagen(source);
        }

        @Override
        public Imagen[] newArray(int size) {
            return new Imagen[size];
        }
    };
}
