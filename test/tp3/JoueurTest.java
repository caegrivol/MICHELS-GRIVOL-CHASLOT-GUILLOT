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
 * @author caegrivol
 */
public class JoueurTest {
    
    public JoueurTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test of convert method, of class Joueur.
     */
    
    @Test
    public void testConvert() {
        System.out.println("convert");
        String s = "N";
        Joueur instance = new Joueur();
        int expResult = 14;
        int result = instance.convert(s);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of passer method, of class Joueur.
     */
    
    @Test
    public void testPasser() {
        System.out.println("passer");
        
        //Création d'un goban
        Goban goban = new Goban(9);
        
        // Création du jeu
        Jeu j = new Jeu(goban, new Joueur("Noir","noir",0), new Joueur("Blanc","blanc",0),0);
        
        //Coup
        Coup c = j.getNoir().passer(j);
        
        //Affichage du coup
        c.afficheCoup();
    }
    
     /**
     * Test of poserPierre method, of class Joueur.
     */

    @Test
    @Ignore
    public void testPoserPierre() {
        System.out.println("poser Pierre");
        
        //Création d'un goban
        Goban goban = new Goban(9);
        
        // Création du jeu
        Jeu j = new Jeu(goban, new Joueur("Noir","noir",0), new Joueur("Blanc","blanc",0),0);
        
        //Coup
        Coup c = j.getNoir().poserPierre(j);
        
        //Affichage du coup
        c.afficheCoup();
        
        //Affichage du goban
        goban.affiche();
    }
     
}
