package com.example.salesapp;

import java.util.Date;

public class facture {
    private int code_fact;
    private Date date_fact;
    private String mode_paiement;
    private int code_cl;
    private int code_ag;


    public facture(int code_fact, Date date_fact, String mode_paiement, int code_cl, int code_ag) {
        this.code_fact = code_fact;
        this.date_fact = date_fact;
        this.mode_paiement = mode_paiement;
        this.code_cl = code_cl;
        this.code_ag = code_ag;
    }


    public int getCode_fact() {
        return code_fact;
    }

    public Date getDate_fact() {
        return date_fact;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public int getCode_cl() {
        return code_cl;
    }

    public int getCode_ag() {
        return code_ag;
    }


    public void setCode_fact(int code_fact) {
        this.code_fact = code_fact;
    }

    public void setDate_fact(Date date_fact) {
        this.date_fact = date_fact;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

    public void setCode_cl(int code_cl) {
        this.code_cl = code_cl;
    }

    public void setCode_ag(int code_ag) {
        this.code_ag = code_ag;
    }
}
