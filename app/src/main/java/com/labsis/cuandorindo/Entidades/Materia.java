package com.labsis.cuandorindo.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Creada por Facu on 04/09/2015.
 */
public class Materia implements Parcelable {

    private int id;
    private String nombre;

    public Materia(){

    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //De aca para abajo, es para que la clase sea Parcelable, es codigo autogereado por http://www.parcelabler.com/
    protected Materia(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Materia> CREATOR = new Parcelable.Creator<Materia>() {
        @Override
        public Materia createFromParcel(Parcel in) {
            return new Materia(in);
        }

        @Override
        public Materia[] newArray(int size) {
            return new Materia[size];
        }
    };
}
