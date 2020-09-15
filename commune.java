package com.example.salesapp;

public class commune {
    private String nom;
    private String ville;
    private int code_sec;


    public commune(String nom, String ville, int code_sec) {
        this.nom = nom;
        this.ville = ville;
        this.code_sec = code_sec;
    }


    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public int getCode_sec() {
        return code_sec;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCode_sec(int code_sec) {
        this.code_sec = code_sec;
    }
}
