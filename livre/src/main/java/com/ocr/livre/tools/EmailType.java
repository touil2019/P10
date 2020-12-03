package com.ocr.livre.tools;

public class EmailType {

    String email;
    String titre;
    String dateretour;
    String nom_Utilisateur;

    public EmailType() { super();
    }

    public EmailType(String email, String titre, String dateretour, String nom_Utilisateur) {
        this.email = email;
        this.titre = titre;
        this.dateretour = dateretour;
        this.nom_Utilisateur = nom_Utilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateretour() {
        return dateretour;
    }

    public void setDateretour(String dateretour) {
        this.dateretour = dateretour;
    }

    public String getNom_Utilisateur() {
        return nom_Utilisateur;
    }

    public void setNom_Utilisateur(String nomUtilisateur) {
        this.nom_Utilisateur = nomUtilisateur;
    }

}
