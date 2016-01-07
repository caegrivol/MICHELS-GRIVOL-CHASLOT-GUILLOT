/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Laura
 */
public class GroupeTest {

    public GroupeTest() {
    }

    /**
     * Test of calculeLibertes method, of class Groupe.
     */
    @Test
    public void testCalculeLibertes() {

        System.out.println("calculeLibertes");

        //Création d'un goban
        Goban goban = new Goban(9);

        //Initialisation du plateau
        goban.getPlateau()[0][0] = 'N';
        goban.getPlateau()[0][1] = 'N';
        goban.getPlateau()[0][2] = 'N';
        goban.getPlateau()[2][0] = 'N';
        goban.getPlateau()[2][1] = 'N';
        goban.getPlateau()[2][2] = 'N';
        goban.getPlateau()[1][1] = 'B';

        //Initialisation des pierres
        Pierre p1 = new Pierre('N', false, new Point2D(0, 0));
        Pierre p2 = new Pierre('N', false, new Point2D(0, 1));
        Pierre p3 = new Pierre('N', false, new Point2D(0, 2));
        Pierre p4 = new Pierre('N', false, new Point2D(2, 0));
        Pierre p5 = new Pierre('N', false, new Point2D(2, 1));
        Pierre p6 = new Pierre('N', false, new Point2D(2, 2));

        Pierre p7 = new Pierre('B', false, new Point2D(1, 1));

        goban.getPierres().add(p1);
        goban.getPierres().add(p2);
        goban.getPierres().add(p3);
        goban.getPierres().add(p4);
        goban.getPierres().add(p5);
        goban.getPierres().add(p6);
        goban.getPierres().add(p7);

        //Création des groupes 
        ArrayList<Pierre> lp1 = new ArrayList<>();
        lp1.add(p1);
        lp1.add(p2);
        lp1.add(p3);
        Groupe g1 = new Groupe(lp1);

        ArrayList<Pierre> lp2 = new ArrayList<>();
        lp2.add(p3);
        lp2.add(p4);
        lp2.add(p5);
        Groupe g2 = new Groupe(lp2);

        ArrayList<Pierre> lp3 = new ArrayList<>();
        lp3.add(p7);
        Groupe g3 = new Groupe(lp3);

        goban.getGroupes().add(g1);
        goban.getGroupes().add(g2);
        goban.getGroupes().add(g3);

        
        //Test
        int result = goban.getGroupes().get(0).calculeLibertes(goban);
        assertEquals(3, result);
    }

}
