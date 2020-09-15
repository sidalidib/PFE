package com.example.salesapp;

import java.util.Date;

public class admin {
    private int code_admin;
    private String nom;
    private String prenom;
    private Date date_nais;
    private String telephone;
    private String email;
    private String username;
    private String mdp;
    private int temp;

    public admin (int code_admin,String nom,String prenom,Date date_nais,String telephone,String email,String username,String mdp,int temp)
    { this.code_admin=code_admin;
      this.nom=nom;
      this.prenom=prenom;
      this.date_nais=date_nais;
      this.telephone=telephone;
      this.email=email;
      this.username=username;
      this.mdp=mdp;
      this.temp=temp;
    }

    public int getCode_admin() {return code_admin;}
    public String getNom () {return nom;}
    public String getPrenom () {return prenom;}
    public Date getDate_nais() {return date_nais;}
    public String getTelephone () {return telephone;}
    public String getEmail () {return email;}
    public String getUsername () {return username;}
    public String getMdp () {return mdp;}
    public int getTemp () {return temp;}

    public void setCode_admin(int code_admin) {this.code_admin=code_admin;}
    public void setNom (String nom) {this.nom=nom;}
    public void setPrenom (String prenom) {this.prenom=prenom;}
    public void setDate_nais (Date date_nais) {this.date_nais=date_nais;}
    public void setTelephone (String telephone) {this.telephone=telephone;}
    public void setEmail (String email) {this.email=email;}
    public void setUsername (String username) {this.username=username;}
    public void setMdp (String mdp) {this.mdp=mdp;}
    public void setTemp(int temp) {this.temp=temp;}
}
