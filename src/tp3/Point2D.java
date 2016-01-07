/*
 * MEDEV
 *
 * Carlos Eduardo GRIVOL JÚNIOR
 */
package tp3;

/**
 * Point2D est une classe utilisée pour définir les coordonnés (x et y) des
 * élements du jeu
 *
 * @author Carlos Eduardo GRIVOL JÚNIOR
 * @version 1.0
 * @since 2015-12-03
 */
public class Point2D {

    //Coordonnees x et y
    /**
     * Coordonée Horizontale
     */
    private int x;

    /**
     * Coordonée Verticale
     */
    private int y;

    //Contructeur 2 parametres
    /**
     * Constructeur avec les coordonées x et y du Point2D
     * <p>
     *
     * @param x Coordonée horizontale
     * @param y Coordonée verticale
     *
     */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Constructeur sans parametres
    public Point2D() {
        this.x = 0;
        this.y = 0;
    }

    //Constructeur de recopie
    public Point2D(Point2D p) {
        this.x = p.x;
        this.y = p.y;
    }

    //Affichage
    public void affiche() {
        System.out.println("[" + x + " ; " + y + "]");
    }

    //Getters et Setters pour x et y
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Set Position
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Translate
    public void translate(int dx, int dy) {
        x = x + dx;
        y = y + dy;
    }

    // Distance
    /**
     * Méthode de calcul de la distance du Point2D
     * <p>
     *
     * @param p Position
     * @return dist Distance entre les 2 points
     *
     */
    public double distance(Point2D p) {

        double d = Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y) * (this.y - p.y));

        return d;
    }

}
