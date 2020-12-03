package com.clientui.beans;

import java.util.Date;

/**
 * Bean pour l'emprunt
 */
public class EmpruntBean {

    /**
     * id
     */
    private Long idEmprunt;
    /**
     * pseudo de l'emprunteur
     */
    private String pseudoEmprunteur ;
    /**
     * Date du debut de l'emprunt
     */
    private Date dateDebut;
    /**
     * Date + 4 semaines
     */
    private Date dateFin;
    /**
     * emprunt prolongeable ou non
     */
    private boolean prolongeable;
    /**
     * emrpunt en cours ou non
     */
    private boolean enCours ;
    /**
     * relation avec la table livre
     */
    private LivreBean livre ;


    public EmpruntBean() {

    }


    public Long getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Long idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public String getPseudoEmprunteur() {
        return pseudoEmprunteur;
    }

    public void setPseudoEmprunteur(String pseudoEmprunteur) {
        this.pseudoEmprunteur = pseudoEmprunteur;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isProlongeable() {
        return prolongeable;
    }

    public void setProlongeable(boolean prolongeable) {
        this.prolongeable = prolongeable;
    }

    public boolean isEnCours() {
        return enCours;
    }

    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

    public LivreBean getLivre() {
        return livre;
    }

    public void setLivre(LivreBean livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "EmpruntBean{" +
                "idEmprunt=" + idEmprunt +
                ", pseudoEmprunteur='" + pseudoEmprunteur + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prolongeable=" + prolongeable +
                ", enCours=" + enCours +
                ", livre=" + livre +
                '}';
    }
}

