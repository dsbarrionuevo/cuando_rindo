package com.labsis.cuandorindo.Entidades;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Creada por Facu on 04/09/2015.
 */
public class Examen implements Parcelable {

    private int id;
    private String descripcion;
    private int prioridad;
    private float nota;
    private Date fechaRecordatorio;
    private Date fechaExamen;

    private Materia materia;
    private TipoExamen tipo;

    public Examen(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return tipo.getNombre() + ": " + materia.getNombre();
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

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public TipoExamen getTipoExamen() {
        return tipo;
    }

    public void setTipoExamen(TipoExamen tipo) {
        this.tipo = tipo;
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    /**
     * Seteo la fecha examen
     * @param fechaExamen
     */
    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    //De aca para abajo, es para que la clase sea Parcelable, es codigo autogereado por http://www.parcelabler.com/
    protected Examen(Parcel in) {
        id = in.readInt();
        descripcion = in.readString();
        prioridad = in.readInt();
        nota = in.readFloat();
        long tmpFechaRecordatorio = in.readLong();
        fechaRecordatorio = tmpFechaRecordatorio != -1 ? new Date(tmpFechaRecordatorio) : null;
        long tmpFechaExamen = in.readLong();
        fechaExamen = tmpFechaExamen != -1 ? new Date(tmpFechaExamen) : null;
        materia = (Materia) in.readValue(Materia.class.getClassLoader());
        tipo = (TipoExamen) in.readValue(TipoExamen.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(descripcion);
        dest.writeInt(prioridad);
        dest.writeFloat(nota);
        dest.writeLong(fechaRecordatorio != null ? fechaRecordatorio.getTime() : -1L);
        dest.writeLong(fechaExamen != null ? fechaExamen.getTime() : -1L);
        dest.writeValue(materia);
        dest.writeValue(tipo);
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