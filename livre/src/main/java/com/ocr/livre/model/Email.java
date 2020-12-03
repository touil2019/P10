package com.ocr.livre.model;

import javax.persistence.*;

/**
 * entité email
 */
@Entity
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /**
     * id du mail
     */
    private Long idMail ;
    /**
     * nom du mail
     */
    private String nom;
    /**
     * objet du mail
     */
    private String objet ;
    /**
     * contenu du mail au format "TEXT"
     */
    @Column(columnDefinition = "TEXT")
    private String contenu ;

    public Email() {
    }

    /**
     * constructeyr de l email
     * @param idMail
     * @param nom
     * @param objet
     * @param contenu
     */
    public Email(Long idMail, String nom, String objet, String contenu) {
        this.idMail = idMail;
        this.nom = nom;
        this.objet = objet;
        this.contenu = contenu;
    }

    public Long getIdMail() {
        return idMail;
    }

    public void setIdMail(Long idMail) {
        this.idMail = idMail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "email{" +
                "idMail=" + idMail +
                ", nom='" + nom + '\'' +
                ", objet='" + objet + '\'' +
                ", contenu='" + contenu + '\'' +
                '}';
    }
}