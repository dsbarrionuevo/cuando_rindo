package com.labsis.cuandorindo.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Creada por Facu on 04/09/2015.
 */
public class TipoExamen extends Identificable {

    private int color;
    private String nombre;

    public TipoExamen(){

    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //De aca para abajo, es para que la clase sea Parcelable, es codigo autogereado por http://www.parcelabler.com/
    protected TipoExamen(Parcel in) {
        super(in);
        color = in.readInt();
        nombre = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(color);
        dest.writeString(nombre);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<TipoExamen> CREATOR = new Parcelable.Creator<TipoExamen>() {
        @Override
        public TipoExamen createFromParcel(Parcel in) {
            return new TipoExamen(in);
        }

        @Override
        public TipoExamen[] newArray(int size) {
            return new TipoExamen[size];
        }
    };
}