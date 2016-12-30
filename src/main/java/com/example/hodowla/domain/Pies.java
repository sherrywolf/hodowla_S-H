package com.example.hodowla.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Pies")

public class Pies {

    private int pies_id;
    private String imie;
    private int rok;
    private String dieta;
    private Rasa rasa;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pies_id")
    public int getpies_id() {
        return pies_id;
    }

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

    @ManyToOne(fetch = FetchType.LAZY)
    public Rasa getrasa() {
        return rasa;
    }

    public void setrasa(Rasa rasa) {
        this.rasa = rasa;
    }
}
