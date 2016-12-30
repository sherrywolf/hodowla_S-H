package com.example.hodowla.service;

import java.util.List;

import com.example.hodowla.domain.Rasa;
import com.example.hodowla.domain.Pies;

public interface Dane {

    void addRasa(Rasa rasa);
    List<Rasa> getAllRasa();
    void deleteRasa(Rasa rasa);
    Rasa getRasa_ID(Long id);
    void deleteAllRasa();

    Long addPies(Pies pies);
    List<Pies> getAllPies();
    List<Pies> getAllPies_idRasa(Pies pies, Rasa rasa);
    Pies getPies_ID(Long id);
    void deleteAllPies();
    void deletePies(Pies pies);
    void deletePiesFromRasa(Rasa rasa);

}