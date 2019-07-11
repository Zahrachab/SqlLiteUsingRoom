package com.example.myappplombiers;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Plombier implements Serializable {

    public Plombier(String nom, String numero, String type) {
        this.nom = nom;
        this.numero = numero;
        this.type = type;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }


    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "numero")
    private String numero;

    @ColumnInfo(name = "type")
    private String type;

    /*
     * Getters and Setters
     *
     * */

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}