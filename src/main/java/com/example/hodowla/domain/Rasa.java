package com.example.hodowla.domain;

import java.util.ArrayList;
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
import javax.persistence.Table;

@Entity
@Table(name="Rasa")

public class Rasa {

    private Long rasa_id;
    private String nazwa;
    private String opis;

    private List<Pies> psy = new ArrayList<Pies>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rasa_id")
    public Long getrasa_id() { return rasa_id; }
    public void setrasa_id(Long rasa_id) { this.rasa_id = rasa_id; }

    @Column(name = "nazwa", nullable = false, unique = true)
    public String getnazwa() { return  nazwa; }
    public void setnazwa(String nazwa) { this.nazwa = nazwa; }

    @Column(name = "opis")
    public String getopis() { return opis; }
    public void setopis(String opis) { this.opis = opis; }

    @OneToMany(targetEntity = Pies.class)
    public List<Pies> getPsy() {
        return psy;
    }
    public void setPsy(List<Pies> psy) {
        this.psy = psy;
    }
}