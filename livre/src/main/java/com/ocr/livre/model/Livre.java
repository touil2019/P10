package com.ocr.livre.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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





    @JsonIgnore
    @OneToMany(mappedBy = "livre", fetch = FetchType.EAGER)
    private Set<Emprunt> emprunt;


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

     @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", auteurName='" + auteurName + '\'' +
                ", auteurPrenom='" + auteurPrenom + '\'' +
                ", titre='" + titre + '\'' +
                ", edition='" + edition + '\'' +
                ", image='" + image + '\'' +
                ", quantiteDispo='" + quantiteDispo + '\''+
                ", emprunt='" + emprunt + '\''+
                '}';
    }


}

