package com.labsis.cuandorindo.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.labsis.cuandorindo.Entidades.Materia;
import com.labsis.cuandorindo.Entidades.TipoExamen;
import com.labsis.cuandorindo.MiApp;

/**
    Creada por Fede on 07/09/2015
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "CuandoRindoDB";

    private static DBHelper instancia;

    public static DBHelper getInstancia() {
        if (instancia == null) {
            instancia = new DBHelper(MiApp.getContext(), DBNAME, null, 1);
        }

        return instancia;
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    /**
     * Este metodo solo es llamado la primera vez que se crea la base de datos.
     * Android maneja de crearla cuando no existe
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MateriaDAO.getInstance().getSQLCreate());
        db.execSQL(TipoExamenDAO.getInstance().getSQLCreate());
        db.execSQL(ExamenDAO.getInstance().getSQLCreate());
    }

    @Override
    /**
     * Este mentodo es llamado al cambiar el versionado de la DB (es un atributo del contrsuctor de esta misma clase)
     * Se deberia realizar la reestructuracion del esquema
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
