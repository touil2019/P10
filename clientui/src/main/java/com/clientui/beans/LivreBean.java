package com.clientui.beans;

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
    private EmpruntBean emprunt;


    public LivreBean() {
    }

    /**
     *Instanciation du livre
     * @param auteurName
     * @param auteurPrenom
     * @param titre
     * @param edition
     * @param image
     * @param quantiteDispo
     * @param emprunt
     */
    public LivreBean(String auteurName, String auteurPrenom, String titre, String edition, String image, int quantiteDispo, EmpruntBean emprunt) {

        this.auteurName = auteurName;
        this.auteurPrenom = auteurPrenom;
        this.titre = titre;
        this.edition = edition;
        this.quantiteDispo = quantiteDispo;
        this.image = image;
        this.emprunt = emprunt;

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

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", auteurName='" + auteurName + '\'' +
                ", auteurPrenom='" + auteurPrenom + '\'' +
                ", titre='" + titre + '\'' +
                ", edition='" + edition + '\'' +
                ", image='" + image + '\'' +
                ", quantiteDispo='" + quantiteDispo + '\'' +
                ", emprunt='" + emprunt + '\'' +
                '}';
    }

    public boolean isPresent() {
        return Present;
    }

    public EmpruntBean getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(EmpruntBean emprunt) {
        this.emprunt = emprunt;
    }
}



