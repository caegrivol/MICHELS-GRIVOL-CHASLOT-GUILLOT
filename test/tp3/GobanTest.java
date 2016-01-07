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
public class GobanTest {
    
    public GobanTest() {
    }
    
   
    /**
     * Test of affiche method, of class Goban.
     */
    @Ignore
    @Test
    public void testAffiche() {
        System.out.println("affiche");
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
        goban.affiche();

    }

    /**
     * Test of renvoiePierre method, of class Goban.
     */
    @Ignore
    @Test
    public void testRenvoiePierre() {
        System.out.println("Test de la fonction renvoiePierre");
        Point2D position = new Point2D(12,11);
        Goban instance = new Goban(19);
        
        Pierre p = new Pierre('B',false,position);
        instance.getPierres().add(p);
        
        Pierre result = instance.renvoiePierre(position);
        assertEquals(result.getCouleur(), 'B');
        assertEquals(result.getPosition().getX(), position.getX());
        assertEquals(result.getPosition().getY(), position.getY());
    }

    /**
     * Test of estOccupee method, of class Goban.
     */
    @Ignore
    @Test
    public void testEstOccupee_int_int() {
        System.out.println("Test de la fonction estOccupee avec des int");
        int x = 12;
        int y = 13;
        Goban instance = new Goban(19);
        instance.getPlateau()[x][y] = 'B';
        assertEquals(true, instance.estOccupee(x, y));
        assertEquals(false, instance.estOccupee(x-1, y+1));
    }

    /**
     * Test of estOccupee method, of class Goban.
     */
    @Ignore
    @Test
    public void testEstOccupee_Point2D() {
        System.out.println("Test de la fonction estOccupee avec une position");
        Point2D p = new Point2D(12,13);
        Point2D p1 = new Point2D(1,2);
        Goban instance = new Goban(19);
        instance.getPlateau()[p.getX()][p.getY()] = 'B';
        assertEquals(true, instance.estOccupee(p));
        assertEquals(false, instance.estOccupee(p1));
    }
    

    
    /**
     * Test of majGroupe method, of class Goban.
     */
     @Ignore
    @Test
    public void testMajGroupe() {
        System.out.println("majGroupe");
        
        //Création d'un goban
        Goban goban = new Goban(9);

        //Initialisation du plateau
        goban.getPlateau()[0][0] = 'N';
        goban.getPlateau()[0][1] = 'N';
        goban.getPlateau()[0][2] = 'N';
        goban.getPlateau()[2][0] = 'N';
        goban.getPlateau()[2][1] = 'N';
        goban.getPlateau()[2][2] = 'N';
         goban.getPlateau()[1][0] = 'N';
        goban.getPlateau()[1][2] = 'B';
  

        //Initialisation des pierres
        Pierre p1 = new Pierre('N', false, new Point2D(0, 0));
        Pierre p2 = new Pierre('N', false, new Point2D(0, 1));
        Pierre p3 = new Pierre('N', false, new Point2D(0, 2));
        Pierre p4 = new Pierre('N', false, new Point2D(2, 0));
        Pierre p5 = new Pierre('N', false, new Point2D(2, 1));
        Pierre p6 = new Pierre('N', false, new Point2D(2, 2));
        Pierre p7 = new Pierre('B', false, new Point2D(1, 2));
        Pierre p8 = new Pierre('N', false, new Point2D(1, 0));

        goban.getPierres().add(p1);
        goban.getPierres().add(p2);
        goban.getPierres().add(p3);
        goban.getPierres().add(p4);
        goban.getPierres().add(p5);
        goban.getPierres().add(p6);
        goban.getPierres().add(p7);
        goban.getPierres().add(p8);

        //Création des groupes 
        ArrayList<Pierre> lp1 = new ArrayList<>();
        lp1.add(p1);
        lp1.add(p2);
        lp1.add(p3);
        lp1.add(p4);
        lp1.add(p5);
        lp1.add(p6);
        lp1.add(p8);
        Groupe g1 = new Groupe(lp1);

        ArrayList<Pierre> lp2 = new ArrayList<>();
        lp2.add(p7);
        Groupe g2 = new Groupe(lp2);
        
        goban.getGroupes().add(g1);
        goban.getGroupes().add(g2);

        
        //Ajout d'une pierre sur le goban 
         Pierre p9 = new Pierre('B', false, new Point2D(1, 1));
        goban.getPlateau()[1][1] = 'B';
          goban.getPierres().add(p9);
 
        //Mise à jour des groupes
          goban.majGroupe();

        //Affichage des groupes
          for (int i = 0; i<goban.getGroupes().size();i++){
              System.out.println("Groupe"+i);
              for (int j=0; j<goban.getGroupes().get(i).getListePierres().size();j++){
                  Pierre p = new Pierre(goban.getGroupes().get(i).getListePierres().get(j));
                  System.out.println(p.getCouleur()+"-"+p.getPosition().getX()+"-"+p.getPosition().getY());
              }
          }
             
          }

    /**
     * Test of estSuicidaire method, of class Goban.
     */
    @Ignore
    @Test
    public void testEstSuicidaire() {
        System.out.println("estSuicidaire");

        //Création d'un goban
        Goban goban = new Goban(9);

        //Initialisation du plateau
        goban.getPlateau()[0][0] = 'N';
        goban.getPlateau()[0][1] = 'N';
        goban.getPlateau()[0][2] = 'N';
        goban.getPlateau()[2][0] = 'N';
        goban.getPlateau()[2][1] = 'N';
        goban.getPlateau()[2][2] = 'N';
        goban.getPlateau()[1][0] = 'N';
        goban.getPlateau()[1][2] = 'N';

        //Initialisation des pierres
        Pierre p1 = new Pierre('N', false, new Point2D(0, 0));
        Pierre p2 = new Pierre('N', false, new Point2D(0, 1));
        Pierre p3 = new Pierre('N', false, new Point2D(0, 2));
        Pierre p4 = new Pierre('N', false, new Point2D(2, 0));
        Pierre p5 = new Pierre('N', false, new Point2D(2, 1));
        Pierre p6 = new Pierre('N', false, new Point2D(2, 2));
        Pierre p7 = new Pierre('N', false, new Point2D(1, 0));
        Pierre p8 = new Pierre('N', false, new Point2D(1, 2));

        goban.getPierres().add(p1);
        goban.getPierres().add(p2);
        goban.getPierres().add(p3);
        goban.getPierres().add(p4);
        goban.getPierres().add(p5);
        goban.getPierres().add(p6);
        goban.getPierres().add(p7);
        goban.getPierres().add(p8);

        //Création des groupes 
        ArrayList<Pierre> lp1 = new ArrayList<>();
        lp1.add(p1);
        lp1.add(p2);
        lp1.add(p3);
        lp1.add(p4);
        lp1.add(p5);
        lp1.add(p6);
        lp1.add(p8);
        lp1.add(p7);
        Groupe g1 = new Groupe(lp1);
 
        goban.getGroupes().add(g1);
  

        boolean result = goban.estSuicidaire(1,1,"blanc");
        assertEquals(true, result);
    }

    
     /**
     * Test of calculeLiberte method, of class Goban.
     */
    @Ignore
    @Test
    public void testCalculeLiberte() {
        System.out.println("Test de la fonction calculeLiberte");
        Goban instance = new Goban(9);
        Pierre p1 = new Pierre('B',2,3);
        Pierre p2 = new Pierre('B',2,4);
        Pierre p3 = new Pierre('B',0,0);
        

        instance.getPlateau()[2][3]='B';
        instance.getPlateau()[2][4]='B';
        instance.getPlateau()[0][0]='B';
        
        assertEquals(3, instance.calculeLiberte(p1));
        assertEquals(2, instance.calculeLiberte(p3));
    }
    
    
/****************************** A FAIRE******************************************/
    /**
     * Test of estRepetee method, of class Goban.
     */
    @Ignore
    @Test
    public void testEstRepetee() {
        System.out.println("estRepetee");
        
        
        
    }


    /**
     * Test of majPierreCapturee method, of class Goban.
     */

    @Test
    public void testMajPierreCapturee() {
        System.out.println("majPierreCapturee");
        
        //Création d'un goban
        Goban goban = new Goban(9);

        //Initialisation du plateau
        goban.getPlateau()[0][0] = 'N';
        goban.getPlateau()[0][1] = 'N';
        goban.getPlateau()[0][2] = 'N';
        goban.getPlateau()[2][0] = 'N';
        goban.getPlateau()[2][1] = 'N';
        goban.getPlateau()[2][2] = 'N';
        goban.getPlateau()[1][0] = 'N';
        goban.getPlateau()[1][2] = 'N';

        //Initialisation des pierres
        Pierre p1 = new Pierre('N', false, new Point2D(0, 0));
        Pierre p2 = new Pierre('N', false, new Point2D(0, 1));
        Pierre p3 = new Pierre('N', false, new Point2D(0, 2));
        Pierre p4 = new Pierre('N', false, new Point2D(2, 0));
        Pierre p5 = new Pierre('N', false, new Point2D(2, 1));
        Pierre p6 = new Pierre('N', false, new Point2D(2, 2));
        Pierre p7 = new Pierre('N', false, new Point2D(1, 0));
        Pierre p8 = new Pierre('N', false, new Point2D(1, 2));

        goban.getPierres().add(p1);
        goban.getPierres().add(p2);
        goban.getPierres().add(p3);
        goban.getPierres().add(p4);
        goban.getPierres().add(p5);
        goban.getPierres().add(p6);
        goban.getPierres().add(p7);
        goban.getPierres().add(p8);

        //Création des groupes 
        ArrayList<Pierre> lp1 = new ArrayList<>();
        lp1.add(p1);
        lp1.add(p2);
        lp1.add(p3);
        lp1.add(p4);
        lp1.add(p5);
        lp1.add(p6);
        lp1.add(p8);
        lp1.add(p7);
        Groupe g1 = new Groupe(lp1);
 
        goban.getGroupes().add(g1);
        
        // Création du jeu
        Jeu j = new Jeu(goban, new Joueur("Noir","noir",0), new Joueur("Blanc","blanc",0),0);
        
       //Ajout d'une pierre sur le goban 
       Pierre p9 = new Pierre('B', false, new Point2D(1, 1));
       goban.getPlateau()[1][1] = 'B';
       goban.getPierres().add(p9);
       
       //Mise à jour des pierres capturées
       goban.majPierreCapturee(j);
       
       //Affichage du goban
       goban.affiche();
       
       //Tests
       assertEquals(1, j.getNoir().getNbPierresCapturees());
        
    }

    
    /**
     * Test of appartientA method, of class Goban.
     */
    @Ignore
    @Test
    public void testAppartientA() {
        System.out.println("Test de la fonction appartientA");
        Goban instance = new Goban(9);
        
        //1er groupe
        Pierre p1 = new Pierre('B',1,2);
        Pierre p2 = new Pierre('B',2,2);
        Pierre p3 = new Pierre('B',2,3);
        
        //2ème groupe
        Pierre p4 = new Pierre('B',6,4);
        Pierre p5 = new Pierre('B',6,5);
        Pierre p6 = new Pierre('B',6,6);
        
        instance.getPierres().add(p1);
        instance.getPierres().add(p2);
        instance.getPierres().add(p3);
        instance.getPierres().add(p4);
        instance.getPierres().add(p5);
        instance.getPierres().add(p6);
        
        ArrayList<Pierre> groupe1 = new ArrayList();
        groupe1.add(p1);
        groupe1.add(p2);
        groupe1.add(p3);
        Groupe g1 = new Groupe(groupe1);
        
        ArrayList<Pierre> groupe2 = new ArrayList();
        groupe2.add(p4);
        groupe2.add(p4);
        groupe2.add(p6);
        Groupe g2 = new Groupe(groupe2);

        instance.getGroupes().add(g1);
        instance.getGroupes().add(g2);
        Groupe result = instance.appartientA(p1);
        assertEquals(1, result.getListePierres().get(0).getPosition().getX());
        assertEquals(2, result.getListePierres().get(0).getPosition().getY());
        assertEquals(2, result.getListePierres().get(2).getPosition().getX());
        assertEquals(3, result.getListePierres().get(2).getPosition().getY());   
    }   
}
