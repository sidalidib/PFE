package com.example.salesapp;

import java.util.Date;

public class agent {
    private int code_agent;
    private String nom;
    private String prenom;
    private Date date_nais;
    private String telephone;
    private char type_agent;
    private int code_sec;
    private int code_vehicule;
    private String username;
    private String mdp;

    public agent(int code_agent, String nom, String prenom, Date date_nais, String telephone, char type_agent, int code_sec, int code_vehicule, String username, String mdp)
    {   this.code_agent = code_agent;
        this.nom = nom;
        this.prenom = prenom;
        this.date_nais = date_nais;
        this.telephone = telephone;
        this.type_agent = type_agent;
        this.code_sec = code_sec;
        this.code_vehicule = code_vehicule;
        this.username = username;
        this.mdp = mdp;
    }

    public int getCode_agent() {
        return code_agent;
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

    public char getType_agent() {
        return type_agent;
    }

    public int getCode_sec() {
        return code_sec;
    }

    public int getCode_vehicule() {
        return code_vehicule;
    }

    public String getUsername() {
        return username;
    }

    public String getMdp() {
        return mdp;
    }



    public void setCode_agent(int code_agent) {
        this.code_agent = code_agent;
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

    public void setType_agent(char type_agent) {
        this.type_agent = type_agent;
    }

    public void setCode_sec(int code_sec) {
        this.code_sec = code_sec;
    }

    public void setCode_vehicule(int code_vehicule) {
        this.code_vehicule = code_vehicule;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
