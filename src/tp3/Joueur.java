/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.Scanner;

/**
 * Classe joueur
 *
 * @author Laura
 */
public class Joueur {

    /**
     * Nom du joueur
     */
    private String nom;

    /**
     * Couleur du joueur
     */
    private String couleur;

    /**
     * Nombre de pierres capturées par le joueur
     */
    private int nbPierresCapturees;

    //Constructeur
    /**
     * Constructeur avec trois paramètres
     *
     * @param n Nom du joueur
     * @param c Couleur du joueur
     * @param nb Nombre de pierres capturées par le joueur
     */
    public Joueur(String n, String c, int nb) {
        nom = n;
        nbPierresCapturees = nb;
        couleur = c;
    }

    /**
     * Constructeur d'initialisation qui prend juste le nom du joueur
     *
     * @param n Nom du joueur
     */
    public Joueur(String n) {
        nom = n;
        nbPierresCapturees = 0;
    }

    /**
     * Constructeur sans paramètre
     */
    public Joueur() {
        nom = "";
        nbPierresCapturees = 0;
        couleur = "noir";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void setCouleur(String coul){
        this.couleur=coul;
    }

    public int getNbPierresCapturees() {
        return nbPierresCapturees;
    }

    public void setNbPierresCapturees(int nbPierresCapturees) {
        this.nbPierresCapturees = nbPierresCapturees;
    }

    /**
     * Demande au joueur où il veut poser sa pierre, fait les verif et pose la
     * pierre sur le Goban
     *
     * @param j jeu
     * @return Coup joué
     */
    public Coup poserPierre(Jeu j) {
        System.out.println("----------- POSE DE PIERRE -----------");
        System.out.println("Où voulez-vous poser votre pierre ?");
        System.out.println("Entrez une ligne (entier entre 1 et " + j.getGoban().getTaille()+")");

        //Saisie de la ligne
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        while (x < 1 || x > j.getGoban().getTaille()) {
            System.out.println("Votre saisie est invalide ! Merci de recommencer");
            x = sc.nextInt();
        }

        //Saisie de la colonne
        System.out.println("Entrez une colonne (lettre majuscule)");
        Scanner sc2 = new Scanner(System.in);
        String c = sc2.nextLine();

        switch (j.getGoban().getTaille()) {
            case 9:
                while (!("A".equals(c) || "B".equals(c) || "C".equals(c) || "D".equals(c) || "E".equals(c) || "F".equals(c) || "G".equals(c) || "H".equals(c) || "I".equals(c))) {
                    System.out.println("Votre saisie est invalide ! Merci de recommencer");
                    c = sc2.nextLine();
                }
                break;
            case 13:
                while (!("A".equals(c) || "B".equals(c) || "C".equals(c) || "D".equals(c) || "E".equals(c) || "F".equals(c) || "G".equals(c) || "H".equals(c) || "I".equals(c) || "J".equals(c) || "K".equals(c) || "L".equals(c) || "M".equals(c))) {
                    System.out.println("Votre saisie est invalide ! Merci de recommencer");
                    c = sc2.nextLine();
                }
                break;

            case 19:
                while (!("A".equals(c) || "B".equals(c) || "C".equals(c) || "D".equals(c) || "E".equals(c) || "F".equals(c) || "G".equals(c) || "H".equals(c) || "I".equals(c) || "J".equals(c) || "K".equals(c) || "L".equals(c) || "M".equals(c) || "N".equals(c) || "O".equals(c) || "P".equals(c) || "Q".equals(c) || "R".equals(c) || "S".equals(c))) {
                    System.out.println("Votre saisie est invalide ! Merci de recommencer");
                    c = sc2.nextLine();
                }
                break;
            default : break;
        }     
        

        int y = this.convert(c);
        x --;
        y--;

        //************************************************************************************************
        //Tant que la position est occupée, qu'il y a suicide ou qu'il y a répétition, on redemande une case 
        //**************************************************************************************************
        while (j.getGoban().estOccupee(x, y) || j.getGoban().estSuicidaire(x, y, couleur) || j.getGoban().estRepetee(x, y,j)) {

            System.out.println("Vous ne pouvez pas jouer sur cette case. Ce coup est interdit!");

            System.out.println("Entrez une ligne (entier entre 1 et " + j.getGoban().getTaille()+")");

            //Saisie de la ligne
            x = sc.nextInt();

            while (x < 1 || x > j.getGoban().getTaille()) {
                System.out.println("Votre saisie est invalide ! Merci de recommencer");
                x = sc.nextInt();
            }

            //Saisie de la colonne
            System.out.println("Entrez une colonne (lettre majuscule)");
            c = sc2.nextLine();

            switch (j.getGoban().getTaille()) {
               case 9:
                while (!("A".equals(c) || "B".equals(c) || "C".equals(c) || "D".equals(c) || "E".equals(c) || "F".equals(c) || "G".equals(c) || "H".equals(c) || "I".equals(c))) {
                    System.out.println("Votre saisie est invalide ! Merci de recommencer");
                    c = sc2.nextLine();
                }
                break;
            case 13:
                while (!("A".equals(c) || "B".equals(c) || "C".equals(c) || "D".equals(c) || "E".equals(c) || "F".equals(c) || "G".equals(c) || "H".equals(c) || "I".equals(c) || "J".equals(c) || "K".equals(c) || "L".equals(c) || "M".equals(c))) {
                    System.out.println("Votre saisie est invalide ! Merci de recommencer");
                    c = sc2.nextLine();
                }
                break;

            case 19:
                while (!("A".equals(c) || "B".equals(c) || "C".equals(c) || "D".equals(c) || "E".equals(c) || "F".equals(c) || "G".equals(c) || "H".equals(c) || "I".equals(c) || "J".equals(c) || "K".equals(c) || "L".equals(c) || "M".equals(c) || "N".equals(c) || "O".equals(c) || "P".equals(c) || "Q".equals(c) || "R".equals(c) || "S".equals(c))) {
                    System.out.println("Votre saisie est invalide ! Merci de recommencer");
                    c = sc2.nextLine();
                }break;
            default : break;
            }

            y = this.convert(c);
        }

        // Création d'une nouvelle pierre
        Point2D pos = new Point2D(x, y);
        char coul;
        if ("blanc".equals(couleur)) {
            coul = 'B';
        } else {
            coul = 'N';
        }
        Pierre p = new Pierre(coul, false, pos);

        //Ajout de la pierre sur le plateau
        j.getGoban().getPlateau()[x][y] = coul;
        j.getGoban().getPierres().add(p);
        
        //Mise à jour de l'avant dernier coup
        j.setAvantDernierCoup(j.getDernierCoup());
        
        // Mise à jour du dernier coup
        Coup coup = new Coup(p);
        j.setDernierCoup(coup);

        //Mise à jour des groupes
        j.getGoban().majGroupe();

        //Mise à jour des pierres capturees
        j.getGoban().majPierreCapturee(j);

        return coup;
    }

    /**
     * Méthode convert : convertit un caractère en un entier entre 1 et 19
     *
     * @param s Chaine à convertir
     * @return Entier correspondant au caractère
     */
    public int convert(String s) {
        int n = 0;

        if ("A".equals(s)) {
            n = 1;
        }
        if ("B".equals(s)) {
            n = 2;
        }
        if ("C".equals(s)) {
            n = 3;
        }
        if ("D".equals(s)) {
            n = 4;
        }
        if ("E".equals(s)) {
            n = 5;
        }
        if ("F".equals(s)) {
            n = 6;
        }
        if ("G".equals(s)) {
            n = 7;
        }
        if ("H".equals(s)) {
            n = 8;
        }
        if ("I".equals(s)) {
            n = 9;
        }
        if ("J".equals(s)) {
            n = 10;
        }
        if ("K".equals(s)) {
            n = 11;
        }
        if ("L".equals(s)) {
            n = 12;
        }
        if ("M".equals(s)) {
            n = 13;
        }
        if ("N".equals(s)) {
            n = 14;
        }
        if ("O".equals(s)) {
            n = 15;
        }
        if ("P".equals(s)) {
            n = 16;
        }
        if ("Q".equals(s)) {
            n = 17;
        }
        if ("R".equals(s)) {
            n = 18;
        }
        if ("S".equals(s)) {
            n = 19;
        }
        return n;
    }

    /**
     * Méthode passer qui permet de créer un nouveau qui sera donc considéré
     * comme étant le dernier coup joué mais sans avoir d'influence sur le Goban
     * actuel
     *
     * @param j Jeu
     * @return Coup joué
     */
    public Coup passer(Jeu j) {
        Coup c = new Coup(couleur);
        j.setAvantDernierCoup(j.getDernierCoup());
        j.setDernierCoup(c);
        
        return c;
    }

    public boolean tourDeJeu(Jeu j, String fichier) {
        boolean finDePartie = false;
        Scanner sc = new Scanner(System.in);
        System.out.println(" Taper 1 pour passer et 2 pour poser une pierre");
        int n = sc.nextInt();

        while (!(n == 1 || n == 2)) {
            System.out.println("Saisie incorrecte. Taper 1 pour passer et 2 pour poser une pierre");
            n = sc.nextInt();
        }
              

        if (n == 1) {
            //On regarde si la partie est finie
            finDePartie = "passe".equals(j.getDernierCoup().getType());

            Coup coup = this.passer(j);
           
            //On sauvegarde le coup
            coup.sauverCoup (fichier, j);
        }
        if (n == 2) {
            Coup coup = this.poserPierre(j);
            //On sauvegarde le coup
            coup.sauverCoup(fichier, j);
        }
        return finDePartie;
    }
}
