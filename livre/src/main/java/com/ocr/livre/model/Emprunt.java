package com.ocr.livre.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * entité emprunt
 */
@Entity
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    /**
     * id emprunt
     */
    private Long idEmprunt;

    /**
     * pseudoEmprunteur
     */
    private String pseudoEmprunteur;

    /**
     * date du début de l emprunt
     */
    private Date dateDebut;

    /**
     * date de début + 4 semaines
     */
    private Date dateFin;

    /**
     * emprunt prolongeable ou pas
     */
    private boolean prolongeable;
    /**
     * emprunt clôturer ou pas
     */
    private boolean cloturer;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_livre")
    private Livre livre;


    public Emprunt() {


        super();
    }

    /**
     * constructeur de l emprunt
     * @param pseudoEmprunteur
     * @param dateDebut
     * @param prolongeable
     * @param cloturer
     * @param livre
     */
    public Emprunt(String pseudoEmprunteur, Date dateDebut,  boolean prolongeable, boolean cloturer, Livre livre) {
        this.pseudoEmprunteur = pseudoEmprunteur;
        this.dateDebut = dateDebut;
        this.prolongeable = prolongeable;
        this.cloturer = cloturer;
        this.livre = livre;
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

    public boolean isCloturer() {
        return cloturer;
    }

    public void setCloturer(boolean enCours) {
        this.cloturer = enCours;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }


}
