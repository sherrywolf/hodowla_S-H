package com.example.hodowla.service;

import java.util.List;

import com.example.hodowla.domain.Rasa;
import com.example.hodowla.domain.Pies;

public interface Dane {

    int addRasa(Rasa rasa);
    List<Rasa> getAllRasa();
    int deleteRasa(Rasa rasa);
    Rasa getRasa_ID(Long id);
    int deleteAllRasa();

    int addPies(Pies pies);
    List<Pies> getAllPies();
    Pies getPies_ID(Long id);
    int deleteAllPies();
    int deletePies(Pies pies);

    List<Pies> getAllPies_idRasa(Rasa rasa);
    int deletePiesFromRasa(Rasa rasa);

}