package com.example.salesapp;

import java.util.Date;

public class pointeur {
    private int code_pt;
    private String nom;
    private String prenom;
    private Date date_nais;
    private  String telephone;
    private String email;
    private String username;
    private String mdp;


    public pointeur(int code_pt, String nom, String prenom, Date date_nais, String telephone, String email, String username, String mdp) {
        this.code_pt = code_pt;
        this.nom = nom;
        this.prenom = prenom;
        this.date_nais = date_nais;
        this.telephone = telephone;
        this.email = email;
        this.username = username;
        this.mdp = mdp;
    }


    public int getCode_pt() {
        return code_pt;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate_nais() {
        return date_nais;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getMdp() {
        return mdp;
    }


    public void setCode_pt(int code_pt) {
        this.code_pt = code_pt;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_nais(Date date_nais) {
        this.date_nais = date_nais;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
