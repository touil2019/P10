package com.ocr.livre.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class

Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String pseudoEmprunteur;

    private Date dateReservation;

    private Date dateDeRetour;

    private Date dateNotification;

    private boolean notified;

    private Integer position;

    private boolean enCours;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_livre")
    @JsonManagedReference
    private Livre livre;

    public Reservation() {
        super();
    }

    public Reservation( Livre livre ,String pseudoEmprunteur, Date dateReservation, Date dateDeRetour) {

        this.livre = livre;
        this.pseudoEmprunteur = pseudoEmprunteur;
        this.dateReservation = dateReservation;
        this.dateDeRetour = dateDeRetour;
        this.enCours = true;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudoEmprunteur() {  return pseudoEmprunteur; }

    public void setPseudoEmprunteur(String pseudoEmprunteur) {
        this.pseudoEmprunteur = pseudoEmprunteur;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Date getDateDeRetour() {
        return dateDeRetour;
    }

    public void setDateDeRetour(Date dateDeRetour) {
        this.dateDeRetour = dateDeRetour;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public boolean isEnCours() {
        return enCours;
    }

    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public boolean isNotified() { return notified; }

    public void setNotified(boolean notified) { this.notified = notified; }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", pseudoEmprunteur='" + pseudoEmprunteur + '\'' +
                ", dateReservation=" + dateReservation +
                ", dateDeRetour=" + dateDeRetour +
                ", dateNotification=" + dateNotification +
                ", notified=" + notified +
                ", position=" + position +
                ", enCours=" + enCours +
                ", livre=" + livre +
                '}';
    }
}
