package com.ocr.livre.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * entité livre
 */
@Entity
@Table(name = "livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_livre")
    /**
     * id livre
     */
    private long id;
    /**
     * Nom de l auteur du livre
     */
    private String auteurName;
    /**
     * Prenom de l auteur du livre
     */
    private String auteurPrenom ;

    /**
     * titre du livre
     */
    @Column(name="titre_livre")
    private String titre ;
    /**
     * edition du livre
     */
    private String edition ;
    /**
     * url de l image de couverture du livre
     */
    private String image;
    /**
     * quantite disponible d un livre
     */
    private int quantiteDispo ;

    private int quantite;

    private boolean reservable;

    private boolean disponible;

    private int nombreResa;

    private Date prochainRetour;





    @JsonIgnore
    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private Set<Emprunt> emprunt;
    @JsonBackReference
    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER )
    private Set<Reservation> reservations;


    public Livre() {
    }

    /**
     * constructeur d un livre
     * @param auteurName
     * @param auteurPrenom
     * @param titre
     * @param edition
     * @param image
     */
    public Livre(String auteurName, String auteurPrenom, String titre, String edition, String image) {

        this.auteurName = auteurName;
        this.auteurPrenom = auteurPrenom;
        this.titre = titre;
        this.edition = edition;
        this.image = image;
        this.reservable= true;
        this.disponible= true;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<Emprunt> getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(Set<Emprunt> emprunt) {
        this.emprunt = emprunt;
    }

    public int getQuantiteDispo() {
        return quantiteDispo;
    }

    public void setQuantiteDispo(int quantiteDispo) {
        this.quantiteDispo = quantiteDispo;
    }

    public boolean isReservable() {
        return reservable;
    }

    public void setReservable(boolean reservable) {
        this.reservable = reservable;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Date getProchainRetour() {
        return prochainRetour;
    }

    public void setProchainRetour(Date prochainRetour) {
        this.prochainRetour = prochainRetour;
    }

    public int getNombreResa() {
        return nombreResa;
    }

    public void setNombreResa(int nombreResa) {
        this.nombreResa = nombreResa;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
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
                ", quantiteDispo=" + quantiteDispo +
                ", reservable=" + reservable +
                ", disponible=" + disponible +
                ", emprunt=" + emprunt +
                ", reservations=" + reservations +
                '}';
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}

