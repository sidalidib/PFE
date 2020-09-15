package com.example.salesapp;

public class client {
    private int code_cl;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private int nrc;
    private int nif;
    private int valide;
    private int code_sec;
    private String commune;

    public client(int code_cl,String nom,String prenom,String adresse,String telephone,int nrc,int nif,int valide,int code_sec,String commune)
    {   this.code_cl=code_cl;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.telephone=telephone;
        this.nrc=nrc;
        this.nif=nif;
        this.valide=valide;
        this.code_sec=code_sec;
        this.commune=commune; }

    public int getCode_cl () { return code_cl;}
    public String getNom () {return nom;}
    public String getPrenom () {return prenom;}
    public String getAdresse () {return adresse;}
    public String getTelephone () {return telephone;}
    public int getNrc () { return nrc;}
    public int getNif () { return nif;}
    public int getValide() { return valide; }
    public int getCode_sec () { return code_sec;}
    public String getCommune () {return commune;}

    public void setCode_cl (int code_cl) {this.code_cl=code_cl;}
    public void setNom (String nom) {this.nom=nom;}
    public void setPrenom (String prenom) {this.prenom=prenom;}
    public void setAdresse (String adresse) {this.adresse=adresse;}
    public void setTelephone (String telephone) {this.telephone=telephone;}
    public void setNrc (int nrc) {this.nrc=nrc;}
    public void setNif (int nif) {this.nif=nif;}
    public void setValide (int valide) {this.valide=valide;}
    public void setCode_sec (int code_sec) {this.code_sec=code_sec;}
    public void setCommune (String commune) {this.commune=commune;}

}
