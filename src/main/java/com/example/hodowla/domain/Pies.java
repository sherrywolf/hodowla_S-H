package com.example.hodowla.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Pies")

public class Pies {

    private int pies_id;
    private String imie;
    private int rok;
    private String dieta;
    private int rasa_id;

    public Pies() {
        super();
    }

    public Pies(String imie, int rok, String dieta) {
        super();

        this.pies_id = pies_id;
        this.imie = imie;
        this.rok = rok;
        this.dieta = dieta;
        this.rasa_id = rasa_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pies_id")
    public int getpies_id() { return pies_id; }

    public void setpies_id(int pies_id) {
        this.pies_id = pies_id;
    }

    @Column(name = "nazwa")
    public String getimie() {
        return imie;
    }

    public void setimie(String imie) {
        this.imie = imie;
    }

    @Column(name = "rok_ur")
    public int getrok() {
        return rok;
    }

    public void setrok(int rok) {
        this.rok = rok;
    }

    @Column(name = "dieta")
    public String getdieta() {
        return dieta;
    }

    public void setdieta(String dieta) {
        this.dieta = dieta;
    }

    @Column(name = "rasa_id")
    public int getrasa_id() {
        return rasa_id;
    }

    public void setrasa_id(int rasa_id) {
        this.rasa_id = rasa_id;
    }
}
