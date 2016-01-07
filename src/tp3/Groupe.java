/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Olivier
 * 
 * 
 */
public class Groupe {

    private List<Pierre> listePierres;

 
  public Groupe() {
        this.listePierres = new ArrayList<>();
    }
    
    public Groupe(List<Pierre> listePierres) {
        this.listePierres = listePierres;
    }
    
    /**
     * Constructeur de recopie
     * @param g Groupe à recopier
     */
     public Groupe(Groupe g) {
        this.listePierres = g.listePierres;
    }
    
    
    
    
    
    
    //Méthodes

    public List<Pierre> getListePierres() {
        return listePierres;
    }

    public void setListePierres(List<Pierre> listePierres) {
        this.listePierres = listePierres;
    }

    public boolean contient(List<Point2D> l, Point2D p) {
        boolean ok = false;

        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getX() == p.getX() && l.get(i).getY() == p.getY()) {
                ok = true;
            }
        }
        return ok;
    }

    /**
     * Calcule le nombre de libertés d'un groupe (liste de pierres)
     *
     * @param goban goban
     * @return le nombre de libertés
     */
    public int calculeLibertes(Goban goban) {
        int libertes = 0;
        List<Point2D> redondance = new ArrayList<>(); //pour éviter les redondances de case libre qui ne doivent pas être comptées 2 fois

        for (Pierre p : listePierres) {
            Point2D hor1 = new Point2D(p.getPosition().getX() + 1, p.getPosition().getY());
            if (goban.estDansLeGoban(hor1) && !goban.estOccupee(hor1) && !this.contient(redondance, hor1)) {
                libertes++;
                redondance.add(hor1);
            }

            Point2D hor2 = new Point2D(p.getPosition().getX() - 1, p.getPosition().getY());
            if (goban.estDansLeGoban(hor2) && !goban.estOccupee(hor2) && !this.contient(redondance, hor2)) {
                libertes++;
                redondance.add(hor2);
            }

            Point2D ver1 = new Point2D(p.getPosition().getX(), p.getPosition().getY() + 1);
            if (goban.estDansLeGoban(ver1) && !goban.estOccupee(ver1) && !this.contient(redondance, ver1)) {
                libertes++;
                redondance.add(ver1);
            }

            Point2D ver2 = new Point2D(p.getPosition().getX(), p.getPosition().getY() - 1);
            if (goban.estDansLeGoban(ver2) && !goban.estOccupee(ver2) && !this.contient(redondance, ver2)) {
                libertes++;
                redondance.add(ver2);
            }
        }
        return libertes;
    }
}
