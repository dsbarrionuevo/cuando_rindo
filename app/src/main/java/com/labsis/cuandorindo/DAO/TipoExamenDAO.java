package com.labsis.cuandorindo.DAO;


import android.content.ContentValues;
import android.database.Cursor;

import com.labsis.cuandorindo.Entidades.TipoExamen;

/**
 * Creada por Fede on 07/09/2015
 */
public class TipoExamenDAO extends IdentificableDAO<TipoExamen> {

    private static TipoExamenDAO instance;

    String col_nombre = "nombre";
    String col_color = "color";

    public TipoExamenDAO(String tabla) {
        super(tabla);
    }

    public static TipoExamenDAO getInstance() {
        if (instance == null) {
            instance = new TipoExamenDAO("TipoExamen");
        }

        return instance;
    }

    @Override
    public String getSQLCreate() {
        return "CREATE TABLE TipoExamen (\n" +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    color  INTEGER,\n" +
                "    nombre TEXT    NOT NULL\n" +
                "                   UNIQUE\n" +
                ");\n";
    }

    @Override
    protected TipoExamen crearObjeto(Cursor cursor) {
        TipoExamen tipoExamen = new TipoExamen();
        tipoExamen.setId(cursor.getInt(cursor.getColumnIndex(col_id)));
        tipoExamen.setNombre(cursor.getString(cursor.getColumnIndex(col_nombre)));
        tipoExamen.setColor(cursor.getInt(cursor.getColumnIndex(col_color)));
        return tipoExamen;
    }

    @Override
    public ContentValues getContentValue(TipoExamen item) {
        ContentValues cv = new ContentValues();
        cv.put(col_color, item.getColor());
        cv.put(col_nombre, item.getNombre());
        return cv;
    }
}
