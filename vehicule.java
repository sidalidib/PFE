package com.example.salesapp;

import java.time.Year;

public class vehicule {
    private int code_vehicule;
    private String matricule;
    private int annee;
    private int etat;
    private String marque;
    private String modele;


    public vehicule(int code_vehicule, String matricule, int annee, int etat, String marque, String modele) {
        this.code_vehicule = code_vehicule;
        this.matricule = matricule;
        this.annee = annee;
        this.etat = etat;
        this.marque = marque;
        this.modele = modele;
    }


    public int getCode_vehicule() {
        return code_vehicule;
    }

    public void setCode_vehicule(int code_vehicule) {
        this.code_vehicule = code_vehicule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
}
