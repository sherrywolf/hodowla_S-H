package com.example.hodowla.service;

import java.util.List;

import com.example.hodowla.domain.Rasa;
import com.example.hodowla.domain.Pies;

public interface Dane {

    int addRasa(Rasa rasa);
    List<Rasa> getAllRasa();
    int deleteRasa(Rasa rasa);
    Rasa getRasa_ID(Long id);
    int updateRasa(Rasa rasa);
    Rasa getRasa_Nazwa(String nazwa);

    int addPies(Pies pies);
    List<Pies> getAllPies();
    Pies getPies_ID(Long id);
    int deletePies(Pies pies);
    int updatePies(Pies pies);
    List<Pies> getPies_Imie(String imie);

    List<Pies> getAllPies_idRasa(Rasa rasa);
    int deletePiesFromRasa(Rasa rasa);

}