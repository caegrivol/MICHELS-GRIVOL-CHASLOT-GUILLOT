package tp3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Class Jeu : elle gère la partie en général, les tours de jeux, les joueurs,
 * etc.
 *
 * @author Olivier
 */
public class Jeu {

    private Goban goban;
    private Joueur noir;
    private Joueur blanc;
    private int handicap;
    private Coup dernierCoup;
    private Coup avantDernierCoup;
    private List<Pierre> pierresCapt;

    //Constantes
    
    private final static String rouge = "rouge";
     private final static String vide = "Vide";
    
    public Jeu() {
        this.noir = new Joueur();
        this.blanc = new Joueur();
        this.handicap = 0;
        this.dernierCoup = new Coup(vide , new Pierre(), rouge);
        this.avantDernierCoup = new Coup(vide, new Pierre(), rouge);
        pierresCapt = new ArrayList<>();
    }

    public Jeu(Goban goban, Joueur noir, Joueur blanc, int handicap) {
        this.goban = goban;
        this.noir = noir;
        this.blanc = blanc;
        this.handicap = handicap;
        this.dernierCoup = new Coup(vide, new Pierre(), rouge);
        this.avantDernierCoup = new Coup(vide, new Pierre(), rouge);
        pierresCapt = new ArrayList<>();
    }

    public Goban getGoban() {
        return goban;
    }

    public Joueur getNoir() {
        return noir;
    }

    public Joueur getBlanc() {
        return blanc;
    }

    public int getHandicap() {
        return handicap;
    }

    public Coup getDernierCoup() {
        return dernierCoup;
    }

    public void setGoban(Goban goban) {
        this.goban = goban;
    }

    public void setDernierCoup(Coup dernierCoup) {
        this.dernierCoup = dernierCoup;
    }

    public List<Pierre> getPierresCapt() {
        return pierresCapt;
    }

    public void setPierresCapt(List<Pierre> pierresCapt) {
        this.pierresCapt = pierresCapt;
    }

    /**
     * Méthode genereNom Cette méthode automatiquement génère un nom unique pour
     * un fichier de sauvegarde
     *
     * @return Nom généré
     */
    public static String genereNom() {
        String s;

        SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
        String heure = sdf.format(new Date());

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf1.format(new Date());

        s = "sauvegarde_GO_" + heure + "_" + date + ".txt";

        return s;
    }

    /**
     * sauve le jeu
     *
     * @param fichier Nom du fichier de sauvegarde
     */
    public void sauverJeu(String fichier) {
        System.out.println("La partie sera sauvegardée automatiquement dans un fichier : " + fichier);

        try {

            BufferedWriter write = new BufferedWriter(new FileWriter(fichier));

            // Ecriture des deux joueurs
            String s = "BLANC " + blanc.getNom();
            write.write(s);
            write.newLine();
            s = "NOIR " + noir.getNom();
            write.write(s);
            write.newLine();

            // Ecriture de la taille
            s = "" + Integer.toString(goban.getTaille());
            write.write(s);
            write.newLine();

            //Ecriture de l'handicap
            s = "" + Integer.toString(handicap);
            write.write(s);
            write.newLine();

            write.close();

        } catch (IOException ex) {
            Logger logger = Logger.getLogger("logg");
            logger.setLevel(Level.ALL);
            logger.log(Level.INFO, "Erreur lors de la sauvegarde du coup");
            ex.printStackTrace();
        }

    }

    /**
     * Méthode charger partie
     *
     * @param fichier Nom du fichier
     * @throws java.io.FileNotFoundException Exeption levée si il y a une erreur
     * de chargement
     */
    public void chargerPartie(String fichier) throws IOException {

        try {

            BufferedReader reader = new BufferedReader(new FileReader(fichier));
            String ligne;
            ligne = reader.readLine();

            // Création d'un tokenizer pour découper chaque ligne en morceau suivant les délimiteurs
            String delimiteurs = " ;,";
            StringTokenizer tok = new StringTokenizer(ligne, delimiteurs);

            // Joueurs
            tok.nextToken();
            // Nom du joueur blanc
            this.blanc.setNom(tok.nextToken());
            this.blanc.setNbPierresCapturees(0);

            ligne = reader.readLine();
            tok = new StringTokenizer(ligne, delimiteurs);
            tok.nextToken();
            // Nom du joueur noir
            this.noir.setNom(tok.nextToken());
            this.noir.setNbPierresCapturees(0);

            // Taille
            ligne = reader.readLine();
            tok = new StringTokenizer(ligne, delimiteurs);
            int t = Integer.parseInt(tok.nextToken());
            System.out.println(t);
            this.setGoban(new Goban(t));

            // Initialisation du plateau en fonction du handicap si la taille est 19
            ligne = reader.readLine();
            tok = new StringTokenizer(ligne, delimiteurs);
            int h = Integer.parseInt(tok.nextToken());
            this.setHandicap(h);

            if (t == 19) {

                this.initialiser();
            }

            System.out.println("Voici le jeu initial");
            this.getGoban().affiche();

            //******************
            //Lecture des coups
            //******************
            ligne = reader.readLine();
            int i = 0;
            while (ligne != null) {

                i++;

                System.out.println("*************************************");
                System.out.println("Tour de jeu :" + Integer.toString(i));

                //On récupère le type
                tok = new StringTokenizer(ligne, delimiteurs);
                String type = tok.nextToken();

                if ("pose".equals(type)) {
                    ligne = reader.readLine();
                    tok = new StringTokenizer(ligne, delimiteurs);
                    tok.nextToken();

                    //On récupère le booléen "capturée"
                    String temp = tok.nextToken();
                    Boolean capt = true;
                    if ("false".equals(temp)) {
                        capt = false;
                    }

                    //On récupère la couleur
                    String couleur = tok.nextToken();

                    //On récupère la position
                    Point2D p = new Point2D(Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()));

                    //Création de la pierre jouée
                    Pierre pierre;
                    if ("blanc".equals(couleur)) {
                        pierre = new Pierre('B', capt, p);
                    } else {
                        pierre = new Pierre('N', capt, p);
                    }

                    //Création du coup joué
                    Coup coup = new Coup(pierre);

                    //Mise à jour de l'avant dernier coup
                    avantDernierCoup = new Coup(dernierCoup);

                    //Mise à jour du dernier coup
                    dernierCoup = new Coup(coup);

                    //Ajout de la pierre à la liste des pierres
                    this.goban.getPierres().add(pierre);

                    //Ajout de la pierre au plateau
                    this.goban.getPlateau()[pierre.getPosition().getX()][pierre.getPosition().getY()] = pierre.getCouleur();

                    //Mise à jour des groupes
                    this.getGoban().majGroupe();

                    //Mise à jour des pierres mortes
                    this.getGoban().majPierreCapturee(this);

                    this.getGoban().affiche();
                } else {
                    System.out.println("Le joueur a passé");
                    if ("blanc".equals(dernierCoup.getCouleur())) {
                        dernierCoup = new Coup("noir");
                    } else {
                        dernierCoup = new Coup("blanc");
                    }
                }
                ligne = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Fichier erroné");
            e.printStackTrace();
        }
    }

    /**
     * Méthode tourDeJeu Gestion du jeu
     *
     * @throws java.io.IOException Erreur levée si erreur lors du chargement ou
     * de la sauvegarde de la partie
     */
    public static void gestionJeu() throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Jeu de GO");

        //***************************************
        // Initialisation du jeu
        //**************************************
        System.out.println("Taper 1 pour charger une partie et 2 pour repartir à 0 : ");
        int choix = sc.nextInt();
        System.out.println("");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("");

        while (choix != 1 && choix != 2) {
            System.out.println("Saisie incorrecte. Taper 1 pour charger une partie et 2 pour repartir à 0 : ");
            sc = new Scanner(System.in);
            choix = sc.nextInt();
            System.out.println("");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("");
        }

        Jeu jeu = new Jeu();

        //Chargement
        if (choix == 1) {
            System.out.println("Entrez le nom du fichier à charger");
            sc = new Scanner(System.in);
            String s = sc.nextLine();
            jeu.chargerPartie(s);
            System.out.println("");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("");
        }

        String nom;

        //Création d'une nouvelle partie
        if (choix == 2) {
            //Création du joueur noir
            System.out.println("Entrez le nom du joueur Noir");
            sc = new Scanner(System.in);
            nom = sc.nextLine();
            jeu.noir.setNom(nom);
            jeu.noir.setCouleur("noir");
            System.out.println("");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("");

            //Création du joueur blanc
            System.out.println("Entrez le nom du joueur Blanc");
            sc = new Scanner(System.in);
            nom = sc.nextLine();
            jeu.blanc.setNom(nom);
            jeu.blanc.setCouleur("blanc");
            System.out.println("");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("");

            //Ajout d'un handicap
            System.out.println("Entrer un handicap : un entier entre 0 et 9 : ");
            sc = new Scanner(System.in);
            int h = sc.nextInt();
            System.out.println("");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("");

            while (h < 0 || h > 9) {
                System.out.println("Saisie incorrecte. Entrer un handicap :un entier entre 0 et 9 : ");
                sc = new Scanner(System.in);
                h = sc.nextInt();
                System.out.println("");
                System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
                System.out.println("");
            }

            jeu.handicap = h;

            //Création du Goban
            //taille
            System.out.println("Entrez la taille du goban sur lequel vous voulez jouer : 9, 13 ou 19 : ");
            sc = new Scanner(System.in);
            int t = sc.nextInt();
            System.out.println("");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("");

            while ((t != 9) && (t != 13) && (t != 19)) {
                System.out.println("Saisie incorrecte. Entrez la taille du goban sur lequel vous voulez jouer : 9, 13 ou 19 : ");
                sc = new Scanner(System.in);
                t = sc.nextInt();
                System.out.println("");
                System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
                System.out.println("");
            }

            jeu.goban = new Goban(t);

            //Plateau et liste de pierres
            jeu.initialiser(); //Prise en compte du handicap    
        }

        //Affichage du jeu initial
        System.out.println("Voici le plateau de jeu intilial : ");
        jeu.goban.affiche();
        System.out.println("");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("");
        System.out.println("Les joueurs sont:");
        System.out.println("Joueur noir : " + jeu.noir.getNom());
        System.out.println("Joueur blanc : " + jeu.blanc.getNom());
        System.out.println("");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("");

        String joueurCourant;
        if (choix == 1) {
            if (jeu.dernierCoup.getCouleur().equals("blanc")) {
                System.out.println("C'est au joueur noir de commencer");
                joueurCourant = "noir";
            } else {
                System.out.println("C'est au joueur blanc de commencer");
                joueurCourant = "blanc";
            }
        } else {
            System.out.println("C'est au joueur noir de commencer");
            joueurCourant = "noir";
        }
        System.out.println("");
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("");

        //*****************************************************************
        // Sauvegarde de la partie
        //****************************************************************
        String fichier = Jeu.genereNom();
        jeu.sauverJeu(fichier);

        //*****************************************************************
        // Tours de jeu
        //*****************************************************************
        boolean finDePartie = false;
        int cpt = 1;

        while (!finDePartie) {

            System.out.println("Appuyez sur entrée pour continuer : ");
            sc = new Scanner(System.in);
            sc.nextLine();

            System.out.println("");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("");
            System.out.println("******************");
            System.out.println("TOUR DE JEU " + Integer.toString(cpt));
            System.out.println("******************");

            cpt++;

            if ("noir".equals(joueurCourant)) {
                System.out.println("C'est au joueur: " + jeu.noir.getNom());
                finDePartie = jeu.noir.tourDeJeu(jeu, fichier);
                joueurCourant = "blanc";

            } else {
                System.out.println("C'est au joueur: " + jeu.blanc.getNom());
                finDePartie = jeu.blanc.tourDeJeu(jeu, fichier);
                joueurCourant = "noir";
            }

            System.out.println("Voici le nouveau plateau : ");
            jeu.goban.affiche();
            System.out.println("");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("");

            if (finDePartie) {
                System.out.println("Vous avez passé deux fois de suite, la partie se termine");
            } else {
                System.out.println("Voulez-vous continuer? o/n");
                String a = sc.nextLine();
                finDePartie = ("n".equals(a));
            }
            System.out.println("");
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////");
            System.out.println("");
        }
    }

    /**
     * Méthode d'initialisation du goban
     */
    public void initialiser() {
        int taille = goban.getTaille();
        Point2D[] pos = new Point2D[10];

        pos[1] = new Point2D(taille / 4, taille / 4);
        pos[2] = new Point2D(taille / 2, taille / 4);
        pos[3] = new Point2D(3 * (taille / 4), taille / 4);
        pos[4] = new Point2D(taille / 4, taille / 2);
        pos[5] = new Point2D(taille / 2, taille / 2);
        pos[6] = new Point2D(3 * (taille / 4), taille / 2);
        pos[7] = new Point2D(taille / 4, 3 * (taille / 4));
        pos[8] = new Point2D(taille / 2, 3 * (taille / 4));
        pos[9] = new Point2D(3 * (taille / 4), 3 * (taille / 4));
        switch (handicap) {
            case 2:
                goban.getPierres().add(new Pierre('N', false, pos[1]));
                goban.getPierres().add(new Pierre('N', false, pos[9]));
                goban.getPlateau()[pos[1].getX()][pos[1].getY()] = 'N';
                goban.getPlateau()[pos[9].getX()][pos[9].getY()] = 'N';
                break;
            case 3:
                goban.getPierres().add(new Pierre('N', false, pos[1]));
                goban.getPierres().add(new Pierre('N', false, pos[9]));
                goban.getPierres().add(new Pierre('N', false, pos[5]));
                goban.getPlateau()[pos[1].getX()][pos[1].getY()] = 'N';
                goban.getPlateau()[pos[9].getX()][pos[9].getY()] = 'N';
                goban.getPlateau()[pos[5].getX()][pos[5].getY()] = 'N';
                break;
            case 4:
                goban.getPierres().add(new Pierre('N', false, pos[1]));
                goban.getPierres().add(new Pierre('N', false, pos[9]));
                goban.getPierres().add(new Pierre('N', false, pos[3]));
                goban.getPierres().add(new Pierre('N', false, pos[7]));
                goban.getPlateau()[pos[1].getX()][pos[1].getY()] = 'N';
                goban.getPlateau()[pos[9].getX()][pos[9].getY()] = 'N';
                goban.getPlateau()[pos[3].getX()][pos[3].getY()] = 'N';
                goban.getPlateau()[pos[7].getX()][pos[7].getY()] = 'N';
                break;
            case 5:
                goban.getPierres().add(new Pierre('N', false, pos[1]));
                goban.getPierres().add(new Pierre('N', false, pos[9]));
                goban.getPierres().add(new Pierre('N', false, pos[3]));
                goban.getPierres().add(new Pierre('N', false, pos[7]));
                goban.getPierres().add(new Pierre('N', false, pos[5]));
                goban.getPlateau()[pos[1].getX()][pos[1].getY()] = 'N';
                goban.getPlateau()[pos[9].getX()][pos[9].getY()] = 'N';
                goban.getPlateau()[pos[3].getX()][pos[3].getY()] = 'N';
                goban.getPlateau()[pos[7].getX()][pos[7].getY()] = 'N';
                goban.getPlateau()[pos[5].getX()][pos[5].getY()] = 'N';
                break;
            case 6:
                goban.getPierres().add(new Pierre('N', false, pos[1]));
                goban.getPierres().add(new Pierre('N', false, pos[9]));
                goban.getPierres().add(new Pierre('N', false, pos[3]));
                goban.getPierres().add(new Pierre('N', false, pos[7]));
                goban.getPierres().add(new Pierre('N', false, pos[4]));
                goban.getPierres().add(new Pierre('N', false, pos[6]));
                goban.getPlateau()[pos[1].getX()][pos[1].getY()] = 'N';
                goban.getPlateau()[pos[9].getX()][pos[9].getY()] = 'N';
                goban.getPlateau()[pos[3].getX()][pos[3].getY()] = 'N';
                goban.getPlateau()[pos[7].getX()][pos[7].getY()] = 'N';
                goban.getPlateau()[pos[4].getX()][pos[4].getY()] = 'N';
                goban.getPlateau()[pos[6].getX()][pos[6].getY()] = 'N';
                break;
            case 7:
                goban.getPierres().add(new Pierre('N', false, pos[1]));
                goban.getPierres().add(new Pierre('N', false, pos[9]));
                goban.getPierres().add(new Pierre('N', false, pos[3]));
                goban.getPierres().add(new Pierre('N', false, pos[7]));
                goban.getPierres().add(new Pierre('N', false, pos[4]));
                goban.getPierres().add(new Pierre('N', false, pos[6]));
                goban.getPierres().add(new Pierre('N', false, pos[5]));
                goban.getPlateau()[pos[1].getX()][pos[1].getY()] = 'N';
                goban.getPlateau()[pos[9].getX()][pos[9].getY()] = 'N';
                goban.getPlateau()[pos[3].getX()][pos[3].getY()] = 'N';
                goban.getPlateau()[pos[7].getX()][pos[7].getY()] = 'N';
                goban.getPlateau()[pos[4].getX()][pos[4].getY()] = 'N';
                goban.getPlateau()[pos[6].getX()][pos[6].getY()] = 'N';
                goban.getPlateau()[pos[5].getX()][pos[5].getY()] = 'N';
                break;
            case 8:
                goban.getPierres().add(new Pierre('N', false, pos[1]));
                goban.getPierres().add(new Pierre('N', false, pos[9]));
                goban.getPierres().add(new Pierre('N', false, pos[3]));
                goban.getPierres().add(new Pierre('N', false, pos[7]));
                goban.getPierres().add(new Pierre('N', false, pos[4]));
                goban.getPierres().add(new Pierre('N', false, pos[6]));
                goban.getPierres().add(new Pierre('N', false, pos[2]));
                goban.getPierres().add(new Pierre('N', false, pos[8]));
                goban.getPlateau()[pos[1].getX()][pos[1].getY()] = 'N';
                goban.getPlateau()[pos[9].getX()][pos[9].getY()] = 'N';
                goban.getPlateau()[pos[3].getX()][pos[3].getY()] = 'N';
                goban.getPlateau()[pos[7].getX()][pos[7].getY()] = 'N';
                goban.getPlateau()[pos[4].getX()][pos[4].getY()] = 'N';
                goban.getPlateau()[pos[6].getX()][pos[6].getY()] = 'N';
                goban.getPlateau()[pos[2].getX()][pos[2].getY()] = 'N';
                goban.getPlateau()[pos[8].getX()][pos[8].getY()] = 'N';
                break;
            case 9:
                goban.getPierres().add(new Pierre('N', false, pos[1]));
                goban.getPierres().add(new Pierre('N', false, pos[9]));
                goban.getPierres().add(new Pierre('N', false, pos[3]));
                goban.getPierres().add(new Pierre('N', false, pos[7]));
                goban.getPierres().add(new Pierre('N', false, pos[4]));
                goban.getPierres().add(new Pierre('N', false, pos[6]));
                goban.getPierres().add(new Pierre('N', false, pos[2]));
                goban.getPierres().add(new Pierre('N', false, pos[8]));
                goban.getPierres().add(new Pierre('N', false, pos[5]));
                goban.getPlateau()[pos[1].getX()][pos[1].getY()] = 'N';
                goban.getPlateau()[pos[9].getX()][pos[9].getY()] = 'N';
                goban.getPlateau()[pos[3].getX()][pos[3].getY()] = 'N';
                goban.getPlateau()[pos[7].getX()][pos[7].getY()] = 'N';
                goban.getPlateau()[pos[4].getX()][pos[4].getY()] = 'N';
                goban.getPlateau()[pos[6].getX()][pos[6].getY()] = 'N';
                goban.getPlateau()[pos[2].getX()][pos[2].getY()] = 'N';
                goban.getPlateau()[pos[8].getX()][pos[8].getY()] = 'N';
                goban.getPlateau()[pos[5].getX()][pos[5].getY()] = 'N';
                break;
            default:
                break;
        }

    }

    public void setNoir(Joueur noir) {
        this.noir = noir;
    }

    public void setBlanc(Joueur blanc) {
        this.blanc = blanc;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }

    public Coup getAvantDernierCoup() {
        return avantDernierCoup;
    }

    public void setAvantDernierCoup(Coup avantDernierCoup) {
        this.avantDernierCoup = avantDernierCoup;
    }

}
