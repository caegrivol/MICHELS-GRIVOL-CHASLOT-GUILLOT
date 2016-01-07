/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laura
 */
public class CoupTest {
    
    public CoupTest() {
    }
    

    /**
     * Test construction d'un coup de type "passe"
     */
    @Test
    public void testPasse() {
        System.out.println("Constructeur pour un coup de type passe");
        Coup instance = new Coup("noir");
        Coup result = new Coup("passe",new Pierre(),"noir");
        assertEquals(instance.getType(), result.getType());
        assertEquals(instance.getCouleur(), result.getCouleur());
    }

    /**
     * Test construction d'un coup de type "pose"
     */
    @Test
    public void testPose() {
        System.out.println("Constructeur pour un coup de type pose");
       Pierre p = new Pierre ('N',false, new Point2D(0,0));
        Coup instance = new Coup(p);
        Coup result = new Coup("pose",p,"noir");
        assertEquals(instance.getType(), result.getType());
        assertEquals(instance.getCouleur(), result.getCouleur());
    }

    /**
     * Test of sauverCoup method, of class Coup.
     */
    @Test
    public void testSauverCoup() {
        System.out.println("sauverCoup");
        String fichier = "testSauverCoup";
        Pierre p = new Pierre ('N',false, new Point2D(0,0));
        Coup instance = new Coup(p);
        
        Goban goban = new Goban(9);
        Joueur noir = new Joueur("JoueurNoir");
        Joueur blanc = new Joueur("JoueurBlanc");
        Jeu jeu = new Jeu(goban, noir, blanc,0);
        instance.sauverCoup(fichier, jeu);
    }
    
}
