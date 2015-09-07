package com.labsis.cuandorindo.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.labsis.cuandorindo.Entidades.Materia;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by fedea on 07/09/2015.
 */
public class MateriaDAO {

    String tabla = "Materia";
    String col_id = "id";
    String col_nombre = "nombre";

    private HashMap<Integer, Materia> items = new HashMap<>();

    private static MateriaDAO instance;
    private Context context;

    public static MateriaDAO getInstance(Context context) {
        if (instance == null) {
            instance = new MateriaDAO();
            instance.context = context;
        }

        return instance;
    }

    public static String getSQLCreate() {
        return "CREATE TABLE Materia (\n" +
                "    id     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    nombre TEXT    UNIQUE\n" +
                "                   NOT NULL\n" +
                ");\n";
    }

    private Materia crearObjeto(Cursor cursor) {
        Materia materia = new Materia();
        materia.setId(cursor.getInt(cursor.getColumnIndex(col_id)));
        materia.setNombre(cursor.getString(cursor.getColumnIndex(col_nombre)));
        return materia;
    }

    public ArrayList<Materia> leerTodo(){
        return leerTodo(null, null);
    }

    private ArrayList<Materia> leerTodo(String select, String[] args) {
        DBHelper db = DBHelper.getInstancia(context);

        ArrayList<Materia> materias = new ArrayList<>();

        String[] col = {col_id};

        Cursor cursor = db.getReadableDatabase().query(tabla, col, select, args, null, null, null);
        while (cursor.moveToNext()) {
            int idMateria = cursor.getInt(0);
            Materia materia = items.get(idMateria);
            if(materia==null){
                materia = leer(idMateria);
                items.put(materia.getId(), materia);
            }
            materias.add(materia);
        }

        cursor.close();

        return materias;
    }


    public Materia leer(int id) {
        Materia materia = items.get(id);
        if(materia!=null){
            return materia;
        }

        DBHelper db = DBHelper.getInstancia(context);

        String select = col_id + " = ?";
        String[] args = {"" + id};

        Cursor cursor = db.getReadableDatabase().query(tabla, null, select, args, null, null, null);
        if (cursor.moveToNext()) {
            materia = crearObjeto(cursor);
            items.put(materia.getId(), materia);
        }

        cursor.close();

        return materia;
    }

    public int insertar(Materia materia) {
        DBHelper db = DBHelper.getInstancia(context);
        ContentValues cv = new ContentValues();
        cv.put("nombre", materia.getNombre());

        int id = (int) db.getWritableDatabase().insert(tabla, null, cv);
        if(id!=-1){
            materia.setId(id);
            items.put(id, materia);
        }
        return id;
    }

}
