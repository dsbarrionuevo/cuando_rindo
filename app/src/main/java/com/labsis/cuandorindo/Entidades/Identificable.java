package com.labsis.cuandorindo.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Creado por by fedea on 07/10/2015.
 */
public class Identificable implements Parcelable {
    int id;

    public Identificable(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected Identificable(Parcel in) {
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
    }

    public static final Creator<Identificable> CREATOR = new Creator<Identificable>() {
        @Override
        public Identificable createFromParcel(Parcel in) {
            return new Identificable(in);
        }

        @Override
        public Identificable[] newArray(int size) {
            return new Identificable[size];
        }
    };
}
