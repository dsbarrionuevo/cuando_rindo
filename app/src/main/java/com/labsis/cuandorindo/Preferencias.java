package com.labsis.cuandorindo;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    private Context c;
    private String nombre;
    private int modo = Context.MODE_PRIVATE;

    public Preferencias(Context context, String nombre) {
        this.c = context;
        this.nombre = nombre;
    }

    public SharedPreferences.Editor getEditor() {
        SharedPreferences sp = c.getSharedPreferences(nombre, modo);
        return sp.edit();
    }

    public SharedPreferences getPreferencias() {
        return c.getSharedPreferences(nombre, modo);
    }

    public void guardar(String donde, String valor) {
        getEditor().putString(donde, valor).commit();
    }

    public void guardar(String donde, float valor) {
        getEditor().putFloat(donde, valor).commit();
    }

    public void guardar(String donde, int valor) {
        getEditor().putInt(donde, valor).commit();
    }

    public void guardar(String donde, boolean valor) {
        getEditor().putBoolean(donde, valor).commit();
    }

    public void guardar(String donde, long valor) {
        getEditor().putLong(donde, valor).commit();
    }

    public String recuperar(String donde, String defecto) {
        return getPreferencias().getString(donde, defecto);
    }

    public float recuperar(String donde, float defecto) {
        return getPreferencias().getFloat(donde, defecto);
    }

    public int recuperar(String donde, int defecto) {
        return getPreferencias().getInt(donde, defecto);
    }

    public boolean recuperar(String donde, boolean defecto) {
        return getPreferencias().getBoolean(donde, defecto);
    }

    public long recuperar(String donde, long defecto) {
        return getPreferencias().getLong(donde, defecto);
    }

    public void quitar(String donde) {
        getEditor().remove(donde).commit();
    }
}
