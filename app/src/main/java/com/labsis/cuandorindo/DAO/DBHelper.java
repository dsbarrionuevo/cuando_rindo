package com.labsis.cuandorindo.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.labsis.cuandorindo.Entidades.Materia;
import com.labsis.cuandorindo.Entidades.TipoExamen;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "CuandoRindoDB";

    private static DBHelper instancia;
    private Context context;
    public static DBHelper getInstancia(Context context) {
        if (instancia == null) {
            instancia = new DBHelper(context, DBNAME, null, 1);
            instancia.context = context;
        }

        return instancia;
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MateriaDAO.getSQLCreate());
        db.execSQL(TipoExamenDAO.getSQLCreate());
        db.execSQL(ExamenDAO.getSQLCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
