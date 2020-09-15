package com.example.salesapp;

public class secteur {
    private int code_sec;
    private String ville;
    private int code_pointeur;


    public secteur(int code_sec, String ville, int code_pointeur) {
        this.code_sec = code_sec;
        this.ville = ville;
        this.code_pointeur = code_pointeur;
    }


    public int getCode_sec() {
        return code_sec;
    }

    public void setCode_sec(int code_sec) {
        this.code_sec = code_sec;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCode_pointeur() {
        return code_pointeur;
    }

    public void setCode_pointeur(int code_pointeur) {
        this.code_pointeur = code_pointeur;
    }
}
