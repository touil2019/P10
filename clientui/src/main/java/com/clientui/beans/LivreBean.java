package com.clientui.beans;

import java.util.Set;

/**
 * Bean pour l entite Livre
 */

public class LivreBean {
    /**
     * identifiant du livre
     */
    private long id;
    /**
     * Nom de l auteur
     */
    private String auteurName;
    /**
     * Prenom de l auteur
     */
    private String auteurPrenom;
    /**
     * titre du livre
     */
    private String titre;
    /**
     * edition du livre
     */
    private String edition;
    /**
     * url de l'image de couverture
     */
    private String image;
    /**
     * nombre de livres disponibles
     */
    private int quantiteDispo;
    /**
     * livre present ou non
     */
    private boolean Present;
    /**
     * relation avec la table
     */
    private Set<EmpruntBean> emprunt;


    public LivreBean() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPresent(boolean present) {
        Present = present;
    }

    public String getAuteurName() {
        return auteurName;
    }

    public void setAuteurName(String auteurName) {
        this.auteurName = auteurName;
    }

    public String getAuteurPrenom() {
        return auteurPrenom;
    }

    public void setAuteurPrenom(String auteurPrenom) {
        this.auteurPrenom = auteurPrenom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public int getQuantiteDispo() {
        return quantiteDispo;
    }

    public void setQuantiteDispo(int quantiteDispo) {
        this.quantiteDispo = quantiteDispo;
    }


    public boolean isPresent() {
        return Present;
    }

    public Set<EmpruntBean> getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(Set<EmpruntBean> emprunt) {
        this.emprunt = emprunt;
    }

    @Override
    public String toString() {
        return "LivreBean{" +
                "id=" + id +
                ", auteurName='" + auteurName + '\'' +
                ", auteurPrenom='" + auteurPrenom + '\'' +
                ", titre='" + titre + '\'' +
                ", edition='" + edition + '\'' +
                ", image='" + image + '\'' +
                ", quantiteDispo=" + quantiteDispo +
                ", Present=" + Present +
                ", emprunt=" + emprunt + '}';
    }
}



