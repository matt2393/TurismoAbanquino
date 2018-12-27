package com.ejemplo.usuariio.turismoabanquino;

import android.os.Parcel;
import android.os.Parcelable;

public class Localizacion implements Parcelable {
    private double lat,lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.lat);
        dest.writeDouble(this.lng);
    }

    public Localizacion() {
    }

    protected Localizacion(Parcel in) {
        this.lat = in.readDouble();
        this.lng = in.readDouble();
    }

    public static final Parcelable.Creator<Localizacion> CREATOR = new Parcelable.Creator<Localizacion>() {
        @Override
        public Localizacion createFromParcel(Parcel source) {
            return new Localizacion(source);
        }

        @Override
        public Localizacion[] newArray(int size) {
            return new Localizacion[size];
        }
    };
}
