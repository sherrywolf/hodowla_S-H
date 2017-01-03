package com.example.hodowla.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final static String IMIE_1 = "Puszek";
    private final static int ROK_1 = 2010;
    private final static String DIETA_1 = "Chrupki Chappy";

    private final static String NAME_1 = "dalmatynczyk";
    private final static String OPIS_1 = "Jest to pies odwazny, czujny, zrownowazony. Wykazuje wysoki stopien przywiazania do czlonkow rodziny. Aktywny i towarzyski. Dalmatynczyk nie jest psem odpowiednim dla malo ruchliwych osob";

    private final static String NAME_2 = "husky";
    private final static String OPIS_2 = "Wspolczesnie husky syberyjski jest wykorzystywany, tak jak i dawniej, jako pies zaprzegowy, takze jako pies rodzinny.";



    @Test
    public void checkAddRasa(){

        Rasa rasa = new Rasa();
        rasa.setnazwa(NAME_1);
        rasa.setopis(OPIS_1);
        Rasa rasa2 = new Rasa();
        rasa2.setnazwa(NAME_2);
        rasa2.setopis(OPIS_2);

        assertEquals(1,dane.addRasa(rasa));

        List<Rasa> AllRasa = dane.getAllRasa();
        Rasa rasadb = AllRasa.get(dane.getAllRasa().size()-1);

        assertEquals(NAME_1, rasadb.getnazwa());
        assertEquals(OPIS_1, rasadb.getopis());

        assertEquals(1,dane.addRasa(rasa2));

        List<Rasa> AllRasa2 = dane.getAllRasa();
        Rasa rasadb2 = AllRasa2.get(dane.getAllRasa().size()-1);

        assertEquals(NAME_2, rasadb2.getnazwa());
        assertEquals(OPIS_2, rasadb2.getopis());

        dane.deleteRasa(rasa);
        dane.deleteRasa(rasa2);
    }

    @Test
    public void checkAddPies(){

        Rasa rasa = new Rasa();
        rasa.setnazwa(NAME_1);
        rasa.setopis(OPIS_1);
        dane.addRasa(rasa);

        Pies pies = new Pies();
        pies.setimie(IMIE_1);
        pies.setrok(ROK_1);
        pies.setdieta(DIETA_1);
        pies.setrasa(rasa);
        dane.addPies(pies);

        assertEquals(rasa.getnazwa(),pies.getrasa().getnazwa());
        List<Pies> psy = rasa.getPsy();
        assertEquals(pies.getimie(),psy.get(psy.size()-1).getimie());

    }

    @Test
    public void checkupdatePies(){
        Rasa rasa = new Rasa();
        rasa.setnazwa(NAME_1);
        rasa.setopis(OPIS_1);
        dane.addRasa(rasa);

        Rasa rasa2 = new Rasa();
        rasa2.setnazwa(NAME_2);
        rasa2.setopis(OPIS_2);
        dane.addRasa(rasa2);

        Pies pies = new Pies();
        pies.setimie(IMIE_1);
        pies.setrok(ROK_1);
        pies.setdieta(DIETA_1);
        pies.setrasa(rasa);
        dane.addPies(pies);

        pies.setrasa(rasa2);
        dane.updatePies(pies);

        assertEquals(pies.getrasa(),rasa2);
        assertEquals(0,rasa2.getPsy().indexOf(pies));
        assertEquals(-1,rasa.getPsy().indexOf(pies));

    }

    @Test
    public void checkDelRasa(){

        Rasa rasa = new Rasa();
        rasa.setnazwa(NAME_1);
        rasa.setopis(OPIS_1);
        Rasa rasa2 = new Rasa();
        rasa2.setnazwa(NAME_2);
        rasa2.setopis(OPIS_2);

        dane.addRasa(rasa);
        dane.addRasa(rasa2);

        List<Rasa> AllRasa = dane.getAllRasa();
        Rasa rasadb = AllRasa.get(dane.getAllRasa().size()-1);
        assertEquals(1, dane.deleteRasa(rasadb));

        assertEquals(null, dane.getRasa_ID(rasadb.getrasa_id()));
    }
}
