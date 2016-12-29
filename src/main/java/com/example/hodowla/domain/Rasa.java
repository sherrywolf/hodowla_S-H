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
@Table(name="Rasa")
@NamedQueries({
        @NamedQuery(name = "rasa.all", query = "Select r from Rasa r"),
        @NamedQuery(name = "rasa.byID", query = "Select r from Rasa r where r.id = :rasa_id")
})

public class Rasa {

    private int rasa_id;
    private String nazwa;
    private String opis;

    private List<Pies> psy = new ArrayList<Pies>();

    public Rasa() { super(); }

    public Rasa(String nazwa, String opis) {
        super();

        this.rasa_id = rasa_id;
        this.nazwa = nazwa;
        this.opis = opis;

        private List<Pies> psy = new ArrayList<Pies>();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rasa_id")
    public int getrasa_id() { return rasa_id; }
    public void setrasa_id(int rasa_id) { this.rasa_id = rasa_id; }

    @Column(name = "nazwa", nullable = false, unique = true)
    public String getnazwa() { return  nazwa; }
    public void setnazwa(String nazwa) { this.nazwa = nazwa; }

    @Column(name = "opis")
    public String getopis() { return opis; }
    public void setopis(String opis) { this.opis = opis; }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Pies> getPsy() {
        return psy;
    }
    public void setPsy(List<Pies> psy) {
        this.psy = psy;
    }
}