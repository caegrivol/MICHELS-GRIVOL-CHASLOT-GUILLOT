/*
 * MEDEV
 *
 * Carlos Eduardo GRIVOL JÚNIOR
 */
package tp3;

/**
 *
 * @author caegrivol
 */
public class Pierre {
    /**
     * Couleur de la Pierre
     */
    private char couleur;
    /**
     * Piece capturee ou pas
     */
    private boolean capturee;
    /**
     * Position 2D de la Pierre
     */
    private Point2D position;
    
    //Constructeurs

    public Pierre(char couleur, boolean capturee, Point2D position) {
        this.couleur = couleur;
        this.capturee = capturee;
        this.position = position;
    }

    public Pierre(char couleur, Point2D position) {
        this.couleur = couleur;
        this.position = position;
    }
    
    public Pierre(char couleur, int x, int y) {
        this.couleur = couleur;
        Point2D p = new Point2D(x,y);
        this.position = p;
    }
    
    public Pierre(){
     this.position = new Point2D();
    }
   
    /**
     * Constructeur de recopie 
     * @param p Pierre à recopier
     */
    public Pierre (Pierre p){
        couleur = p.couleur;
        capturee = p.capturee;
        position = new Point2D(p.position);
    }

    //Getters et Setters
    
    public char getCouleur() {
        return couleur;
    }

    public boolean isCapturee() {
        return capturee;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setCouleur(char couleur) {
        this.couleur = couleur;
    }

    public void setCapturee(boolean capturee) {
        this.capturee = capturee;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
    
}
