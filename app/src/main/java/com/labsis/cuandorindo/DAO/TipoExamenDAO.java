package com.labsis.cuandorindo.DAO;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.labsis.cuandorindo.Entidades.TipoExamen;

import java.util.ArrayList;
import java.util.HashMap;

public class TipoExamenDAO {

    private static TipoExamenDAO instance;
    String tabla = "TipoExamen";
    String col_id = "id";
    String col_nombre = "nombre";
    String col_color = "color";

    private HashMap<Integer, TipoExamen> items = new HashMap<>();
    private Context context;

    public static TipoExamenDAO getInstance(Context context) {
        if (instance == null) {
            instance = new TipoExamenDAO();
            instance.context = context;
        }

        return instance;
    }

    public static String getSQLCreate() {
        return "CREATE TABLE TipoExamen (\n" +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    color  INTEGER,\n" +
                "    nombre TEXT    NOT NULL\n" +
                "                   UNIQUE\n" +
                ");\n";
    }

    private TipoExamen crearObjeto(Cursor cursor) {
        TipoExamen tipoExamen = new TipoExamen();
        tipoExamen.setId(cursor.getInt(cursor.getColumnIndex(col_id)));
        tipoExamen.setNombre(cursor.getString(cursor.getColumnIndex(col_nombre)));
        tipoExamen.setColor(cursor.getInt(cursor.getColumnIndex(col_color)));
        return tipoExamen;
    }

    public ArrayList<TipoExamen> leerTodo() {
        return leerTodo(null, null);
    }

    private ArrayList<TipoExamen> leerTodo(String select, String[] args) {
        DBHelper db = DBHelper.getInstancia(context);

        ArrayList<TipoExamen> tiposExamen = new ArrayList<>();

        String[] col = {col_id};

        Cursor cursor = db.getReadableDatabase().query(tabla, col, select, args, null, null, null);
        while (cursor.moveToNext()) {
            int idTipoExamen = cursor.getInt(0);

            TipoExamen tipoExamen = items.get(idTipoExamen);
            if (tipoExamen == null) {
                tipoExamen = leer(idTipoExamen);
                items.put(tipoExamen.getId(), tipoExamen);
            }
            tiposExamen.add(tipoExamen);
        }

        cursor.close();

        return tiposExamen;
    }

    public TipoExamen leer(int id) {
        TipoExamen tipoExamen = items.get(id);
        if (tipoExamen != null) {
            return tipoExamen;
        }

        DBHelper db = DBHelper.getInstancia(context);

        String select = col_id + " = ?";
        String[] args = {"" + id};

        Cursor cursor = db.getWritableDatabase().query(tabla, null, select, args, null, null, null);

        if (cursor.moveToFirst()) {
            tipoExamen = crearObjeto(cursor);
            items.put(tipoExamen.getId(), tipoExamen);
        }

        cursor.close();

        return tipoExamen;
    }

    public int insertar(TipoExamen tipoExamen) {
        DBHelper db = DBHelper.getInstancia(context);
        ContentValues cv = new ContentValues();
        cv.put(col_nombre, tipoExamen.getNombre());
        cv.put(col_color, tipoExamen.getColor());

        int id = (int) db.getWritableDatabase().insert(tabla, null, cv);
        if (id != -1) {
            tipoExamen.setId(id);
            items.put(id, tipoExamen);
        }
        return id;
    }

}
