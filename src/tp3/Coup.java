/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;

/**
 *
 * @author Laura
 */
public class Coup {
    
    /**
     * Type du coup : passe ou pose
     */
    private String type;
    
    /**
     * Pierre jouée
     * Cette pierre est nulle si le joueur passe
     */
    private Pierre pierre;
    
    
    /**
     * Couleur du joueur qui a joué
     */
    private String couleur;
    
    //Constructeurs
    
    /**
     * Constructeur sans paramètre
     * Utilisé lorsque l'on passe
     * @param c Couleur du joueur
     */
    public Coup(String c){
        pierre = new Pierre();
        type="passe";
        couleur =c;
        
    }
    
    /**
     * Constructeur avec paramètres
     * @param t Type 
     * @param p Pierre
     * @param c Couleur
     */
    public Coup (String t, Pierre p, String c){
        type = t;
        pierre = p;
        couleur =c;
    }
    
    /**
     * Constructeur de recopie
     * @param c Coup à recopier
     */
    public Coup (Coup c){
        type = c.type;
        pierre = c.pierre;
        couleur = c.couleur;
    }
    
    /**
     * Constructeurs avec paramètre
     * @param c Couleur de la pierre jouée
     * @param cap La pierre jouée est elle capturée?
     * @param p Position de la pierre jouée
     */
    public Coup ( char c, boolean cap, Point2D p){
        pierre = new Pierre(c,cap,p);
        type = "pose";
        
        if (c=='B'){
            couleur = "blanc";
        }else{
            couleur="noir";
        }
    }

    /**
     * Constructeur avec une pierre
     * @param p Pierre jouée
     */
    
    public Coup (Pierre p){
        pierre =p;
        type="pose";
        
        if (p.getCouleur()=='B'){
            couleur = "blanc";
        }else{
            couleur="noir";
        }
    }
    
    
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Pierre getPierre() {
        return pierre;
    }

    public void setPierre(Pierre pierre) {
        this.pierre = pierre;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
    /**
     * Méthode sauverCoup
     * Enregistre le dernier coup joué
     * @param fichier Nom du fichier de sauvegarde
     * @param jeu Jeu 
     */
      public void sauverCoup (String fichier, Jeu jeu){
        try {

            BufferedWriter write = new BufferedWriter(new FileWriter(fichier));
            
            // Ecriture de la nature du coup
            String s = type;
            write.write(s);
            write.newLine();
            
            //Si le type est pose
            // Ecriture de la pierre jouée
            if ("pose".equals(s)){
             if (pierre.getCouleur()=='B'){
                 s= jeu.getBlanc().getNom()+" ";
             }else{
                s= jeu.getNoir().getNom()+" "; 
             }
             s= s+" "+pierre.getCouleur()+" "+pierre.isCapturee()+" "+pierre.getPosition().getX()+" "+pierre.getPosition().getY();
            write.write(s);
            }
            write.close();
            
        }catch (IOException ex){
           Logger logger = Logger.getLogger("logg");
          logger.setLevel(Level.ALL);
            logger.log(Level.INFO,"Erreur lors de la sauvegarde du coup");
            ex.printStackTrace();
        }
    }
      
      /**
       * Affichage d'un coup
       */
      public void afficheCoup(){
          Logger logger = Logger.getLogger("logg");
          logger.setLevel(Level.ALL);
            logger.log(Level.INFO,type+" - "+couleur+" - "+pierre.getPosition().getX()+" - "+pierre.getPosition().getY());
      }
    
}
