package com.clientui.beans;

import java.util.Date;
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
    private boolean present;
    /**
     * quantite de livre
     */
    private int quantite;
    /**
     * relation avec la table
     */
    private Set<EmpruntBean> emprunt;

    private Date prochainRetour;

    private Set<ReservationBean> reservations;

    private boolean reservable;

    private int nombreResa;



    public LivreBean() {
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPresent(boolean present) {
        this.present = present;
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

    public int getNombreResa() {
        return nombreResa;
    }

    public void setNombreResa(int nombreResa) {
        this.nombreResa = nombreResa;
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


    public void setEmprunt(Set<EmpruntBean> emprunt) {
        this.emprunt = emprunt;
    }

    public Set<ReservationBean> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationBean> reservations) {
        this.reservations = reservations;
    }

    public boolean isReservable() {
        return reservable;
    }

    public void setReservable(boolean reservable) {
        this.reservable = reservable;
    }

    public Date getProchainRetour() {
        return prochainRetour;
    }

    public void setProchainRetour(Date prochainRetour) {
        this.prochainRetour = prochainRetour;
    }

    public boolean isPresent() {
        return present;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Set<EmpruntBean> getEmprunt() {
        return emprunt;
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
                ", Present=" + present +
                ", emprunt=" + emprunt +
                ", reservationBean=" + reservations +
                '}';

    }
}



