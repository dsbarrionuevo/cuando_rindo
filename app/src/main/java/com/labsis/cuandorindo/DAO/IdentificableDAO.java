package com.labsis.cuandorindo.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.labsis.cuandorindo.Entidades.Identificable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Creado by fedea on 07/10/2015.
 */
public abstract class IdentificableDAO<Id extends Identificable> {

    protected String tabla;

    public static final String col_id = "id";

    public IdentificableDAO(String tabla) {
        this.tabla = tabla;
    }

    protected HashMap<Integer, Id> items = new HashMap<>();

    public abstract ContentValues getContentValue(Id item);

    protected abstract Id crearObjeto(Cursor cursor);

    public abstract String getSQLCreate();

    public int insertar(Id item) {
        DBHelper db = DBHelper.getInstancia();
        ContentValues cv = getContentValue(item);

        int id = (int) db.getWritableDatabase().insert(tabla, null, cv);
        if (id != -1) {
            item.setId(id);
            items.put(id, item);
        }
        return id;
    }

    public int borrar(int id) {
        DBHelper db = DBHelper.getInstancia();

        String where = col_id + " = ?";
        String args[] = {"" + id};

        int cantidad = db.getWritableDatabase().delete(tabla, where, args);
        if (cantidad == 1) {
            items.remove(id);
        }

        return cantidad;
    }

    public int actualizar(Id item) {
        DBHelper db = DBHelper.getInstancia();

        String where = col_id + " = ?";
        String args[] = {"" + item.getId()};

        ContentValues cv = getContentValue(item);

        int cantidad = db.getWritableDatabase().update(tabla, cv, where, args);
        if (cantidad == 1) {
            items.put(item.getId(), item);
        }

        return cantidad;
    }

    public ArrayList<Id> leerTodo() {
        return leerTodo(null, null);
    }

    private ArrayList<Id> leerTodo(String select, String[] args) {
        DBHelper db = DBHelper.getInstancia();

        ArrayList<Id> listaItems = new ArrayList<>();

        String[] col = {col_id};

        Cursor cursor = db.getReadableDatabase().query(tabla, col, select, args, null, null, null);
        while (cursor.moveToNext()) {
            int idItem = cursor.getInt(0);
            Id item = items.get(idItem);
            if (item == null) {
                item = leer(idItem);
                items.put(item.getId(), item);
            }
            listaItems.add(item);
        }

        cursor.close();

        return listaItems;
    }

    public Id leer(int id) {
        Id item = items.get(id);
        if (item != null) {
            return item;
        }

        DBHelper db = DBHelper.getInstancia();

        String select = col_id + " = ?";
        String[] args = {"" + id};

        Cursor cursor = db.getReadableDatabase().query(tabla, null, select, args, null, null, null);
        if (cursor.moveToNext()) {
            item = crearObjeto(cursor);
            items.put(item.getId(), item);
        }

        cursor.close();

        return item;
    }
}
