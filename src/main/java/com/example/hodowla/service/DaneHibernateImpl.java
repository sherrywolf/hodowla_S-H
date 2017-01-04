package com.example.hodowla.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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
        List<Rasa> rasy = manager.createQuery("Select r From Rasa r", Rasa.class).getResultList();
        return rasy;
    }

    @Override
    public int deleteRasa(Rasa rasa) {
        try{
            for (Pies pies : rasa.getPsy()) {
                pies.setrasa(null);
                manager.merge(pies);
            }
            manager.remove(rasa);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public Rasa getRasa_ID(Long id) {
        return manager.find(Rasa.class, id);
    }


    @Override
    public int updateRasa(Rasa rasa) {
        try{
            manager.merge(rasa);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int addPies(Pies pies) {
        try{
            manager.persist(pies);
            Rasa rasa = pies.getrasa();
            rasa.getPsy().add(pies);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;

    }

    @Override
    public List<Pies> getAllPies() {
        List<Pies> psy = manager.createQuery("Select p From Pies p", Pies.class).getResultList();
        return psy;
    }

    @Override
    public List<Pies> getAllPies_idRasa(Rasa rasa) {
        return rasa.getPsy();
    }

    @Override
    public Pies getPies_ID(Long id) {
        return manager.find(Pies.class, id);
    }

    @Override
    public int deletePies(Pies pies) {
        try{
            Rasa rasa = pies.getrasa();
            if(rasa != null)
            rasa.getPsy().remove(pies);
            manager.remove(pies);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int updatePies(Pies pies) {
        try{
            Rasa rasa = pies.getrasa();

            for(Rasa r : getAllRasa()){
            if(rasa != r){
                int i = r.getPsy().indexOf(pies);
                if(i != -1) r.getPsy().remove(pies);
            }
        }
            if(rasa.getPsy().indexOf(pies) == -1)
            rasa.getPsy().add(pies);
            manager.merge(pies);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int deletePiesFromRasa(Rasa rasa) {
        try{
            for(Pies pies : rasa.getPsy() ){
                rasa.getPsy().remove(pies);
                manager.remove(pies);
            }
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

}
