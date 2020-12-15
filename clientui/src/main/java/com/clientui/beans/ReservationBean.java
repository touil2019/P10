package com.clientui.beans;

import java.io.Serializable;
import java.util.Date;

public class ReservationBean {


        private Long id;

        private String pseudoEmprunteur;

        private Date dateReservation;

        private Date dateDeRetour;

        private Date dateNotification;

        private Integer position;

        private boolean enCours;

        private LivreBean livre ;



        public ReservationBean() {
            super();
        }


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getPseudoEmprunteur() {
            return pseudoEmprunteur;
        }

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

        public LivreBean getLivre() {
            return livre;
        }

        public void setLivre(LivreBean livre) {
            this.livre = livre;
        }

        @Override
        public String toString() {
            return "Reservation{" +
                    "id=" + id +
                    ", pseudoEmprunteur='" + pseudoEmprunteur + '\'' +
                    ", dateReservation=" + dateReservation +
                    ", dateDeRetour=" + dateDeRetour +
                    ", dateNotification=" + dateNotification +
                    ", position=" + position +
                    ", enCours=" + enCours +
                    ",livre=" + livre +
                    '}';
        }
    }


