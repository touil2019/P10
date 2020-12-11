package com.ocr.livre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private Long idUtilisateur;

    private Date dateReservation;

    private Date dateDeRetour;

    private Date dateNotification;

    private Integer position;

    private boolean enCours;

    public Reservation() {
    }

    public Reservation(Long id, Long idUtilisateur, Date dateReservation, Date dateDeRetour, Date dateNotification, Integer position, boolean enCours) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.dateReservation = dateReservation;
        this.dateDeRetour = dateDeRetour;
        this.dateNotification = dateNotification;
        this.position = position;
        this.enCours = enCours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", idUtilisateur=" + idUtilisateur +
                ", dateReservation=" + dateReservation +
                ", dateDeRetour=" + dateDeRetour +
                ", dateNotification=" + dateNotification +
                ", position=" + position +
                ", enCours=" + enCours +
                '}';
    }
}
