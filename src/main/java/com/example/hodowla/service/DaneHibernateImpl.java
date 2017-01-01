package com.example.hodowla.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.example.hodowla.domain.Pies;
import com.example.hodowla.domain.Rasa;



@Repository
@Transactional
public class DaneHibernateImpl implements Dane {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public int addRasa(Rasa rasa) {
        try{
            manager.persist(rasa);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public List<Rasa> getAllRasa() {
        return null;
    }

    @Override
    public void deleteRasa(Rasa rasa) {
        /*rasa = (Rasa) sessionFactory.getCurrentSession().get(Rasa.class,
                rasa.getrasa_id());

        for (Pies pies : rasa.getPsy()) {
            pies.setrasa(null);
            sessionFactory.getCurrentSession().update(pies);
        }
        sessionFactory.getCurrentSession().delete(rasa); */
    }

    @Override
    public Rasa getRasa_ID(Long id) {
        return null;
    }

    @Override
    public void deleteAllRasa() {

    }

    @Override
    public Long addPies(Pies pies) {
        return null;
    }

    @Override
    public List<Pies> getAllPies() {
        return null;
    }

    @Override
    public List<Pies> getAllPies_idRasa(Pies pies, Rasa rasa) {
        return null;
    }

    @Override
    public Pies getPies_ID(Long id) {
        return null;
    }

    @Override
    public void deleteAllPies() {

    }

    @Override
    public void deletePies(Pies pies) {

    }

    @Override
    public void deletePiesFromRasa(Rasa rasa) {

    }

}
