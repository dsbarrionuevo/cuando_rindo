package com.labsis.cuandorindo.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import com.labsis.cuandorindo.DAO.MateriaDAO;
import com.labsis.cuandorindo.DAO.TipoExamenDAO;

import java.util.Date;

/**
 * Creada por Facu on 04/09/2015.
 */
public class Examen extends Identificable {

    private String descripcion;
    private int prioridad;
    private float nota;
    private Date fechaRecordatorio;
    private Date fechaExamen;

    private int idMateria;
    private int idTipo;

    public Examen() {
    }

    public String getTitulo() {
        if (getTipoExamen() != null && getMateria() != null) {
            return getTipoExamen().getNombre() + ": " + getMateria().getNombre();
        }

        if (getMateria() != null) {
            return getMateria().getNombre();
        }

        if (getTipoExamen() != null) {
            return getTipoExamen().getNombre();
        }

        return "";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public Date getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public void setFechaRecordatorio(Date fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public Materia getMateria() {
        if (idMateria != 0) {
            return MateriaDAO.getInstance().leer(idMateria);
        }
        return null;
    }

    public TipoExamen getTipoExamen() {
        if (idTipo != 0) {
            return TipoExamenDAO.getInstance().leer(idTipo);
        }
        return null;
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    /**
     * Seteo la fecha examen
     *
     * @param fechaExamen
     */
    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    //De aca para abajo, es para que la clase sea Parcelable, es codigo autogereado por http://www.parcelabler.com/
    protected Examen(Parcel in) {
        super(in);
        descripcion = in.readString();
        prioridad = in.readInt();
        nota = in.readFloat();
        long tmpFechaRecordatorio = in.readLong();
        fechaRecordatorio = tmpFechaRecordatorio != -1 ? new Date(tmpFechaRecordatorio) : null;
        long tmpFechaExamen = in.readLong();
        fechaExamen = tmpFechaExamen != -1 ? new Date(tmpFechaExamen) : null;

        idMateria = in.readInt();
        idTipo = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeString(descripcion);
        dest.writeInt(prioridad);
        dest.writeFloat(nota);
        dest.writeLong(fechaRecordatorio != null ? fechaRecordatorio.getTime() : -1L);
        dest.writeLong(fechaExamen != null ? fechaExamen.getTime() : -1L);
        dest.writeInt(idMateria);
        dest.writeInt(idTipo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Examen> CREATOR = new Parcelable.Creator<Examen>() {
        @Override
        public Examen createFromParcel(Parcel in) {
            return new Examen(in);
        }

        @Override
        public Examen[] newArray(int size) {
            return new Examen[size];
        }
    };
}