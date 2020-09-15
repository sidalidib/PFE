package com.example.salesapp;

public class produit {
    private int code_pr;
    private String designation;
    private float prix_u;
    private int u_crt;
    private int stock;
    private String categorie;
    private String marque;


    public produit(int code_pr, String designation, float prix_u, int u_crt, int stock, String categorie, String marque) {
        this.code_pr = code_pr;
        this.designation = designation;
        this.prix_u = prix_u;
        this.u_crt = u_crt;
        this.stock = stock;
        this.categorie = categorie;
        this.marque = marque;

    }


    public int getCode_pr() {
        return code_pr;
    }

    public String getDesignation() {
        return designation;
    }

    public float getPrix_u() {
        return prix_u;
    }

    public int getU_crt() {
        return u_crt;
    }

    public int getStock() {
        return stock;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getMarque() {
        return marque;
    }


    public void setCode_pr(int code_pr) { this.code_pr = code_pr; }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrix_u(float prix_u) {
        this.prix_u = prix_u;
    }

    public void setU_crt(int u_crt) {
        this.u_crt = u_crt;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}
