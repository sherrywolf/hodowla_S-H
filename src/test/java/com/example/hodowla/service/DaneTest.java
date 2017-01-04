package com.example.hodowla.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.hodowla.domain.Pies;
import com.example.hodowla.domain.Rasa;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@Transactional

public class DaneTest {

    @Autowired
    Dane dane;

    private static final boolean bool = true;

    private final static String IMIE_1 = "Puszek";
    private final static int ROK_1 = 2010;
    private final static String DIETA_1 = "Chrupki Chappy";

    private final static String IMIE_2 = "Strzala";
    private final static int ROK_2 = 2009;
    private final static String DIETA_2 = "Royal Canin";

    private final static String IMIE_3 = "Lola";
    private final static int ROK_3 = 2015;
    private final static String DIETA_3 = "Josera Knuspies";

    private final static String NAME_1 = "dalmatynczyk";
    private final static String OPIS_1 = "Jest to pies odwazny, czujny, zrownowazony. Wykazuje wysoki stopien przywiazania do czlonkow rodziny. Aktywny i towarzyski. Dalmatynczyk nie jest psem odpowiednim dla malo ruchliwych osob";

    private final static String NAME_2 = "husky";
    private final static String OPIS_2 = "Wspolczesnie husky syberyjski jest wykorzystywany, tak jak i dawniej, jako pies zaprzegowy, takze jako pies rodzinny.";

    Rasa rasa = new Rasa();
    Rasa rasa2 = new Rasa();
    Pies pies = new Pies();
    Pies pies2 = new Pies();
    Pies pies3 = new Pies();

    @Before
    public void setUp(){
        rasa.setnazwa(NAME_1);
        rasa.setopis(OPIS_1);

        rasa2.setnazwa(NAME_2);
        rasa2.setopis(OPIS_2);

        pies.setimie(IMIE_1);
        pies.setrok(ROK_1);
        pies.setdieta(DIETA_1);
        pies.setrasa(rasa);

        pies2.setimie(IMIE_2);
        pies2.setrok(ROK_2);
        pies2.setdieta(DIETA_2);
        pies2.setrasa(rasa);

        pies3.setimie(IMIE_3);
        pies3.setrok(ROK_3);
        pies3.setdieta(DIETA_3);
        pies3.setrasa(rasa2);

        if(dane.getAllRasa().indexOf(rasa) == -1) {
            dane.addRasa(rasa);
        }
        if(dane.getAllRasa().indexOf(rasa2) == -1) {
            dane.addRasa(rasa2);
        }
        if(dane.getAllPies().indexOf(pies) == -1) {
            dane.addPies(pies);
        }
        if(dane.getAllPies().indexOf(pies2) == -1) {
            dane.addPies(pies2);
        }
        if(dane.getAllPies().indexOf(pies3) == -1) {
            dane.addPies(pies3);
        }
    }

    @After
    public void tearDown(){
        if(dane.getPies_ID(pies.getpies_id()) != null)
        dane.deletePies(pies);
        if(dane.getPies_ID(pies2.getpies_id()) != null)
        dane.deletePies(pies2);
        if(dane.getPies_ID(pies3.getpies_id()) != null)
        dane.deletePies(pies3);
        if(dane.getRasa_ID(rasa.getrasa_id()) != null)
        dane.deleteRasa(rasa);
        if(dane.getRasa_ID(rasa2.getrasa_id()) != null)
        dane.deleteRasa(rasa2);
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkAddRasa(){
        Rasa rasadb = dane.getRasa_ID(rasa.getrasa_id());
        Rasa rasadb2 = dane.getRasa_ID(rasa2.getrasa_id());

        assertEquals(NAME_1, rasadb.getnazwa());
        assertEquals(OPIS_1, rasadb.getopis());

        assertEquals(NAME_2, rasadb2.getnazwa());
        assertEquals(OPIS_2, rasadb2.getopis());
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkAddPies(){
        Rasa rasadb = dane.getRasa_ID(rasa.getrasa_id());
        Pies piesdb = dane.getPies_ID(pies.getpies_id());

        assertEquals(rasadb.getnazwa(),piesdb.getrasa().getnazwa());
        List<Pies> psy = rasadb.getPsy();
        assertEquals(piesdb.getimie(),psy.get(psy.indexOf(piesdb)).getimie());
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkupdateRasa(){
        rasa.setnazwa("Zmiana");
        dane.updateRasa(rasa);

        Rasa rasadb = dane.getRasa_ID(rasa.getrasa_id());
        assertEquals("Zmiana",rasadb.getnazwa());
        rasadb = dane.getRasa_ID(rasa2.getrasa_id());
        assertEquals(NAME_2,rasadb.getnazwa());
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkupdatePies(){
        pies.setrasa(rasa2);
        dane.updatePies(pies);

        Pies piesdb = dane.getPies_ID(pies.getpies_id());

        assertEquals(piesdb.getrasa(),rasa2);

        if(rasa2.getPsy().indexOf(piesdb)>-1) {
            assertEquals(1, 1);
        }else assertEquals(1,0);
        
        assertEquals(-1,rasa.getPsy().indexOf(piesdb));
        assertEquals(rasa,pies2.getrasa());
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkDelRasa(){
        Rasa rasadb = dane.getRasa_ID(rasa.getrasa_id());
        assertEquals(1, dane.deleteRasa(rasadb));

        assertEquals(null, dane.getRasa_ID(rasadb.getrasa_id()));
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkDelPies(){
        assertEquals(1,dane.deletePies(pies));

        Rasa rasadb = dane.getRasa_ID(rasa.getrasa_id());

        assertEquals(null,dane.getPies_ID(pies.getpies_id()));
        assertEquals(-1,rasadb.getPsy().indexOf(pies));

    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkGetRasa_ID(){
        assertEquals(rasa,dane.getRasa_ID(rasa.getrasa_id()));
        assertEquals(NAME_1,dane.getRasa_ID(rasa.getrasa_id()).getnazwa());
        assertEquals(OPIS_1,dane.getRasa_ID(rasa.getrasa_id()).getopis());
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkGetPies_ID(){
        assertEquals(pies,dane.getPies_ID(pies.getpies_id()));
        assertEquals(IMIE_1,dane.getPies_ID(pies.getpies_id()).getimie());
        assertEquals(ROK_1,dane.getPies_ID(pies.getpies_id()).getrok());
        assertEquals(DIETA_1,dane.getPies_ID(pies.getpies_id()).getdieta());
        assertEquals(rasa,dane.getPies_ID(pies.getpies_id()).getrasa());
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkGetRasa_Nazwa(){
        assertEquals(rasa,dane.getRasa_Nazwa(rasa.getnazwa()));
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkGetPies_Imie(){
        int sizebefore = dane.getPies_Imie(pies.getimie()).size();

        pies3.setimie(pies.getimie());
        dane.updatePies(pies3);

        assertEquals(sizebefore + 1,dane.getPies_Imie(pies.getimie()).size());
        assertEquals(pies.getimie(),pies3.getimie());
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkGetAllPies_idRasa(){
        List<Pies> psy = new ArrayList<Pies>();

        for(Pies pies : dane.getAllPies()){
            if(pies.getrasa() == rasa)
                psy.add(pies);
        }
        assertEquals(dane.getAllPies_idRasa(rasa).size(),psy.size());
    }

    @Transactional
    @Rollback(value = bool)
    @Test
    public void checkDelPiesFromRasa(){
        dane.deletePiesFromRasa(rasa);

        assertEquals(0,dane.getAllPies_idRasa(rasa).size());
        assertEquals(null,dane.getPies_ID(pies.getpies_id()));
        assertEquals(null,dane.getPies_ID(pies2.getpies_id()));
        assertEquals(pies3,dane.getPies_ID(pies3.getpies_id()));
    }
}
