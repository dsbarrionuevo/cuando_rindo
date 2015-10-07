package com.labsis.cuandorindo.DAO;

import android.content.ContentValues;
import android.database.Cursor;

import com.labsis.cuandorindo.Entidades.Materia;

/**
 * Creada por Fede on 07/09/2015
 */
public class MateriaDAO extends IdentificableDAO<Materia> {

    String col_nombre = "nombre";


    private static MateriaDAO instance;

    public MateriaDAO() {
        super("Materia");
    }

    public static MateriaDAO getInstance() {
        if (instance == null) {
            instance = new MateriaDAO();
        }

        return instance;
    }

    @Override
    public String getSQLCreate() {
        return "CREATE TABLE Materia (\n" +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    nombre TEXT    UNIQUE\n" +
                "                   NOT NULL\n" +
                ");\n";
    }

    @Override
    public ContentValues getContentValue(Materia item) {
        ContentValues cv = new ContentValues();
        cv.put(col_nombre, item.getNombre());
        return cv;
    }

    @Override
    protected Materia crearObjeto(Cursor cursor) {
        Materia materia = new Materia();
        materia.setId(cursor.getInt(cursor.getColumnIndex(col_id)));
        materia.setNombre(cursor.getString(cursor.getColumnIndex(col_nombre)));
        return materia;
    }

}
