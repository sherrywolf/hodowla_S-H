package com.example.hodowla.service;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

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
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
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

        System.out.println("********** TEST DODAWANIA **********\n");

        Rasa rasa = new Rasa();
        rasa.setnazwa(NAME_1);
        rasa.setopis(OPIS_1);
        Rasa rasa2 = new Rasa();
        rasa2.setnazwa(NAME_2);
        rasa2.setopis(OPIS_2);

        assertEquals(1,dane.addRasa(rasa));
        System.out.println("Rasa " + rasa.getnazwa() + " zostala dodana do bazy.");

        List<Rasa> AllRasa = dane.getAllRasa();
        Rasa rasadb = AllRasa.get(dane.getAllRasa().size()-1);

        assertEquals(NAME_1, rasadb.getnazwa());
        assertEquals(OPIS_1, rasadb.getopis());
        System.out.println("Otrzymana rasa z bazy o nazwie: " + rasadb.getnazwa() + " jest poprawna.");

        assertEquals(1,dane.addRasa(rasa2));
        System.out.println("Rasa " + rasa2.getnazwa() + " zostala dodana do bazy.");

        List<Rasa> AllRasa2 = dane.getAllRasa();
        Rasa rasadb2 = AllRasa2.get(dane.getAllRasa().size()-1);

        assertEquals(NAME_2, rasadb2.getnazwa());
        assertEquals(OPIS_2, rasadb2.getopis());
        System.out.println("Otrzymana rasa z bazy o nazwie: " + rasadb2.getnazwa() + " jest poprawna.");


        System.out.println("****** KONIEC TESTU DODAWANIA ******\n");
    }
/*
    @Test
    public void checkDelRasa(){

        System.out.println("********** TEST DELETE **********\n");

        dane.clearPies();
        dane.clearRasa();

        Rasa rasa = new Rasa(NAME_1,OPIS_1);
        Rasa rasa2 = new Rasa(NAME_2,OPIS_2);

        assertEquals(1,dane.addRasa(rasa));
        assertEquals(1,dane.addRasa(rasa2));

        List<Rasa> AllRasa = dane.getAllRasa();
        Rasa rasadb = AllRasa.get(dane.getAllRasa().size()-1);
        assertEquals(1, dane.deleteRasa(rasadb.getrasa_id()));

        assertEquals(0, dane.deleteRasa(rasadb.getrasa_id()));

        System.out.println("Rasa o id: " + rasadb.getrasa_id() + " i nazwie: " + rasadb.getnazwa() + " zostala usunieta.");
        System.out.println("****** KONIEC TESTU DELETE ******\n");
    } */
}
