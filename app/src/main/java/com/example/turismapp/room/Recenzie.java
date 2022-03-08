package com.example.turismapp.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "recenzii")
public class Recenzie implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "recenzie")
    private String recenzie;

    @ColumnInfo(name = "nota")
    private Double nota;

    public Recenzie(long id, String recenzie, Double nota) {
        this.id = id;
        this.recenzie = recenzie;
        this.nota = nota;
    }
    @Ignore
    public Recenzie(String recenzie, Double nota) {
        this.recenzie = recenzie;
        this.nota = nota;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRecenzie() {
        return recenzie;
    }

    public void setRecenzie(String recenzie) {
        this.recenzie = recenzie;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Recenzie{" +
                "id=" + id +
                ", recenzie='" + recenzie + '\'' +
                ", nota=" + nota +
                '}';
    }
}