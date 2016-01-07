/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Olivier
 */
public class JeuTest {

    public JeuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of genereNom method, of class Jeu.
     */
    @Ignore
    @Test
    public void testGenereNom() {
        System.out.println("genereNom");
        String result = Jeu.genereNom();
        System.out.println(result);

    }

    /**
     * Test of sauverJeu method, of class Jeu.
     */
    @Ignore
    @Test
    public void testSauverJeu() {
        System.out.println("sauverJeu");
        String fichier = "testSauverJeu.txt";

        /*Création du jeu*/
        //Création d'un goban
        Goban goban = new Goban(9);

        //Création des joueurs
        Joueur noir = new Joueur("JoueurNoir", "noir", 0);
        Joueur blanc = new Joueur("JoueurBlanc", "blanc", 0);

        Jeu instance = new Jeu(goban, noir, blanc, 0);

        instance.sauverJeu(fichier);
    }

    /**
     * Test of chargerPartie method, of class Jeu.
     */
     @Ignore
    @Test
    public void testChargerPartie() throws Exception {
        System.out.println("chargerPartie");
        String fichier = "test.txt";

        // Joueurs
        Joueur J1 = new Joueur();
        Joueur J2 = new Joueur();

        //Création d'un goban
        Goban goban = new Goban(9);

        //Jeu
        Jeu j = new Jeu(goban, J1, J2, 0);

        j.chargerPartie(fichier);

    }



    /**
     * Test of initialiser method, of class Jeu.
     */
    @Ignore
    @Test
    public void testInitialiser() {
        System.out.println("initialiser");
        Jeu instance = new Jeu();
        instance.initialiser();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
