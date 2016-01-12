/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.*;

/**
 * Classe Goban
 *
 * @author thomasmichels
 */
public class Goban {

    /**
     * Taille du plateau de jeu
     */
    private int taille;

    /**
     * Plateau de jeu
     */
    private char[][] plateau;

    /**
     * Liste des groupes
     */
    private List<Groupe> groupes;

    /**
     * Liste de pierres
     */
    private List<Pierre> pierres;

    /**
     * Constrcuteurs avec paramètres
     *
     * @param size Taille du goban
     */
    public Goban(int size) {

        taille = size;
        plateau = new char[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                plateau[i][j] = '.';
            }
        }
        groupes = new ArrayList<Groupe>();
        pierres = new ArrayList<Pierre>();
    }

    /**
     * Methode affiche Affichage textuel du plateau de jeu
     */
    public void affiche() {
        
        switch (taille) {
            case 9:
               
            System.out.println( "  A B C D E F G H I");
                break;
            case 13:
               
            System.out.println( "  A B C D E F G H I J K L M");
                break;
            case 19:
               
           System.out.println( "  A B C D E F G H I J K L M N O P Q R S");
                break;
            default:
                break;
        }

        for (int i = 0; i < taille; i++) {
            
            if (i < 9) {
               System.out.println( " " + (i + 1) + " ");
            } else {
                System.out.println( " " + (i + 1));
            }
            for (int j = 0; j < taille; j++) {

              System.out.println( " " + plateau[i][j]);
            }
            System.out.println( "");

        }
    }

    /**
     * Renvoie la pierre occupée dans une position
     *
     * @param position position à tester
     * @return une pierre, ou null s'il n'y a pas de pierre
     */
    public Pierre renvoiePierre(Point2D position) {
        Pierre result = new Pierre();
        for (Pierre p : pierres) {
            if (p.getPosition().equals(position)) {
                result = new Pierre(p);
            }
        }
        return result; // retourne null si la position n'est pas occupée
    }

    /**
     * Methode estOccupee indique la position demandee en paramètre est occupee
     * ou non
     *
     * @param x ligne
     * @param y colonne
     * @return Booléen
     */
    public boolean estOccupee(int x, int y) {
        boolean res;
        if (plateau[x][y] != '.') {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    /**
     * Surcharge de la méthode estOccupee qui prend cette fois une position en
     * paramètre
     *
     * @param p Point2D
     * @return Booléen
     */
    public boolean estOccupee(Point2D p) {
        boolean res;
        int x = p.getX();
        int y = p.getY();
        if (plateau[x][y] != '.') {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    /**
     * Methode majGroupe() Met à jour le nombre et la position des groupes sur
     * le plateau de jeu. Cette méthode supprime les groupes qui n'existent
     * plus, crée les nouveau groupes qui apparaissent
     */
    public void majGroupe() {

        groupes = new ArrayList<>();

        double epsilon = 0.000000000000000000000001;
        for (int i = 0; i < pierres.size(); i++) {

            //Si la pierre i n'est pas dans un groupe 
            if (appartientA(pierres.get(i)).getListePierres().isEmpty()) {
                List<Pierre> a = new ArrayList<>();
                a.add(pierres.get(i));
                Groupe g = new Groupe(a);
                groupes.add(g);
            }

            //Pour toutes les pierres d'indice supérieur à celui de i, on regarde si elles sont dans le même groupe que la pierre i 
            for (int j = i + 1; j < pierres.size(); j++) {
                if (Math.abs(pierres.get(i).getPosition().distance(pierres.get(j).getPosition())-1)<epsilon && pierres.get(i).getCouleur() == pierres.get(j).getCouleur()) {
                    Groupe g1 = new Groupe(this.appartientA(pierres.get(i)));
                    int k = this.trouveGroupe(this.appartientA(pierres.get(i)));
                    groupes.remove(k);
                    g1.getListePierres().add(pierres.get(j));
                    groupes.add(g1);
                }
            }
        }

        // On fusionne tous les groupes qui ont une pierre en commun 
        this.fusion(0);

    }

    /**
     * Méthode trouveGroupe Renvoie l'indice d'un groupe dans la liste de groupe
     * (-1 s'il n'est pas dans la liste)
     *
     * @param g Groupe
     * @return Indice du groupe
     */
    public int trouveGroupe(Groupe g) {

        int k = -1;
        for (int i = 0; i < groupes.size(); i++) {
            if (groupes.get(i).getListePierres().get(0).getPosition().equals(g.getListePierres().get(0).getPosition())) {
                k = i;
            }
        }
        return k;
    }

    /**
     * Méthode fusion Fusionne tous les groupes d'indice supérieur à k avec le
     * groupe k si ils ont une pierre en commun
     */
    public void fusion(int k) {

        // Booleen qui sert à savoir si on a fait une fusion ou non
        boolean b = false;

        //Liste des groupes fusionnés
        List<Groupe> l = new ArrayList<>();

        //Fusion
        for (int i = k + 1; i < groupes.size(); i++) {
            if (this.commun(groupes.get(k), groupes.get(i)) != (-1)) {
                int a = this.commun(groupes.get(k), groupes.get(i));
                l.add(groupes.get(i));
                b = true;
                groupes.set(k, new Groupe(this.fusion2(a, groupes.get(k), groupes.get(i))));
            }
        }

        //On supprime les groupes fusionnés
        for (int z = 0; z < l.size(); z++) {
            groupes.remove(l.get(z));
        }

        if (b) {
            this.fusion(0);
        } else {
            if (k + 1 < groupes.size() - 1) {
                this.fusion(k + 1);
            }
        }

    }

    /**
     * Méthode fusion2 Cette méthode renvoie un groupe g qui correspond à la
     * fusion de deux groupes qui ont une pierre commune
     *
     * @param k indice de la pierre commune
     * @param g1 Groupe 1
     * @param g2 Groupe 2
     * @return Groupe issu de la fusion
     */
    public Groupe fusion2(int k, Groupe g1, Groupe g2) {

        for (Pierre p : g2.getListePierres()) {
            if (!p.getPosition().equals(g1.getListePierres().get(k).getPosition())) {
                g1.getListePierres().add(p);
            }
        }

        return g1;
    }

    /**
     * Méthode commun Teste si deux groupes ont une pierre en commun et si c'est
     * le cas elle renvoie la position de cette pierre dans le premier groupe
     * Sinon elle renvoie -1
     *
     * @param g1 Groupe 1
     * @param g2 Groupe 2
     * @return Indice de la pierre commune ou -1
     */
    public int commun(Groupe g1, Groupe g2) {

        int k = -1;

        for (int i = 0; i < g1.getListePierres().size(); i++) {
            for (int j = 0; j < g2.getListePierres().size(); j++) {
                if (g1.getListePierres().get(i).getPosition().equals(g2.getListePierres().get(j).getPosition())) {
                    k = i;
                }
            }
        }
        return k;
    }

    /**
     * Méthode estSuicidaire Teste si une intersection (x,y) est suicidaire pour
     * une couleur c
     *
     * @param x Ligne
     * @param y Colonne
     * @param c Couleur (blanc ou noir) celle qu'on pose
     * @return est suiscidaire ou pas
     */
  
   
    public boolean estSuicidaire(int x, int y, String c) {
        boolean ok = false;

        //On crée une pierre fictive
        Point2D posFictive = new Point2D(x, y);

        char coul;
        if ("blanc".equals(c)) {
            coul = 'B';
        } else {

            coul = 'N';
        }

        Pierre fictive = new Pierre(coul, false, posFictive);

        // On calcule son dégré de liberté
        if (this.calculeLiberte(fictive) == 0) {

            //On sauvegarde la liste de pierres ainsi que la liste de groupes
            List<Pierre> p = this.copie(pierres);
            List<Groupe> g = this.copie2(groupes);

            //On ajoute notre pierre fictive à la liste des pierres et on met à jour les groupes
            pierres.add(fictive);
            this.majGroupe();

            Groupe fict = new Groupe(appartientA(fictive));

            if (fict.calculeLibertes(this) == 0) {
                ok = true;
            }

            pierres = this.copie(p);
            groupes = this.copie2(g);
        }

        return ok;
    }

    /**
     * Méthode copie Renvoie la copie profonde d'une arrayliste de pierres
     *
     * @param l Liste à copier
     * @return copie
     */
    public List copie(List<Pierre> l) {
        List<Pierre> c = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {
            Pierre p = new Pierre(l.get(i));
            c.add(p);
        }

        return c;
    }

    /**
     * Méthode copie Renvoie la copie profonde d'une arrayliste de pierres
     *
     * @param l Liste à copier
     * @return copie
     */
    public List copie2(List<Groupe> l) {
        List<Groupe> c = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {
            Groupe p = new Groupe(l.get(i));
            c.add(p);
        }

        return c;
    }

    /**
     * Méthode estRepetee Teste si une intersection (x,y) est un ko
     *
     * @param x Ligne
     * @param y Colonne
     * @param j Jeu
     * @return Booléen
     */
    public boolean estRepetee(int x, int y, Jeu j) {
        boolean ok = false;
        
        if (this.calculeLiberte(j.getDernierCoup().getPierre()) == 1){
            
           for (Pierre p : j.getPierresCapt()){
               if (p.getPosition().equals(new Point2D(x,y))){
                   ok = true;
               }
           }
        }
        
        return ok;
    }

    /**
     * Calcule le nombre de libertés d'une pierre à partir du plateau de jeu
     *
     * @param p une pierre
     * @return le nombre de libertés
     */
    public int calculeLiberte(Pierre p) {
        int n = 0;
        int x = p.getPosition().getX();
        int y = p.getPosition().getY();
        if (y + 1 < taille - 1 && plateau[x][y + 1] == '.') {
            n++;
        }
        if (y - 1 >= 0 && plateau[x][y - 1] == '.') {
            n++;
        }
        if (x - 1 >= 0 && plateau[x - 1][y] == '.') {
            n++;
        }
        if (x + 1 < taille - 1 && plateau[x + 1][y] == '.') {
            n++;
        }
        return n;
    }

    /**
     * Méthode majPierreCapturee Méthode parcourant le plateau et pour chaque
     * pierre capturee : la supprime du plateau et de la liste de pierres et met
     * à jour le nombre de pierres capturées par les joueurs
     */
    public void majPierreCapturee(Jeu j) {
        
        //On vide la liste des pierres capturées au dernier coup
        j.setPierresCapt( new ArrayList<Pierre>());
        majGroupe();
        int nbCap = 0;
        char coul = 'N';

        List<Groupe> sup = new ArrayList<>();

        for (Groupe g : groupes) {
            if (g.calculeLibertes(this) == 0) {
                for (Pierre p : g.getListePierres()) {
                    //supprime les pierres du plateau et de la liste
                    p.setCapturee(true);
                    j.getPierresCapt().add(p);
                    pierres.remove(p);
                    int posX = p.getPosition().getX();
                    int posY = p.getPosition().getY();
                    char vide = '.';
                    coul = p.getCouleur();
                    plateau[posX][posY] = vide;
                    nbCap++;
                }
                sup.add(g);
                //incremente nb de pierres capturees
                if (coul == 'N') {
                    j.getBlanc().setNbPierresCapturees(j.getBlanc().getNbPierresCapturees() + nbCap);
                } else {
                    j.getNoir().setNbPierresCapturees(j.getNoir().getNbPierresCapturees() + nbCap);
                }

            }
        }

        for (Groupe g : sup) {
            groupes.remove(g);
        }
    }

    /**
     * retourne le groupe auquel une pierre appartient, null s'il y a une erreur
     *
     * @param p pierre à tester
     * @return le groupe auquel elle appartient
     */
    public Groupe appartientA(Pierre p) {
        Groupe result = new Groupe();
        for (Groupe g : groupes) {
            for (Pierre pierre : g.getListePierres()) {
                if (pierre.getPosition().equals(p.getPosition())) {
                    result = new Groupe(g);
                }
            }
        }
        return result;
    }

    /**
     * Retourne si une position est dans le Goban à partir de la taille du goban
     *
     * @param p position p
     * @return true ou false
     */
    public boolean estDansLeGoban(Point2D p) {
        return !(p.getX() < 0 || p.getX() > this.getTaille() - 1 || p.getY() < 0 || p.getY() > this.getTaille() - 1);
    }

    public char[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(char[][] plateau) {
        this.plateau = plateau;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }

    public List<Pierre> getPierres() {
        return pierres;
    }

    public void setPierres(List<Pierre> pierres) {
        this.pierres = pierres;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

}
