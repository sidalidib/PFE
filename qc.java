package com.example.salesapp;

import java.util.Date;

public class qc {
    private int code_qc,code_admin,code_agent;
    private Date date_qc;
    private float montant;
    private int code_vh,sec;


    public qc(int code_qc, int code_admin, int code_agent, Date date_qc, float montant, int code_vh, int sec) {
        this.code_qc = code_qc;
        this.code_admin = code_admin;
        this.code_agent = code_agent;
        this.date_qc = date_qc;
        this.montant = montant;
        this.code_vh = code_vh;
        this.sec = sec;
    }


    public int getCode_qc() {
        return code_qc;
    }

    public int getCode_admin() {
        return code_admin;
    }

    public int getCode_agent() {
        return code_agent;
    }

    public Date getDate_qc() {
        return date_qc;
    }

    public float getMontant() {
        return montant;
    }

    public int getCode_vh() {
        return code_vh;
    }

    public int getSec() {
        return sec;
    }


    public void setCode_qc(int code_qc) {
        this.code_qc = code_qc;
    }

    public void setCode_admin(int code_admin) {
        this.code_admin = code_admin;
    }

    public void setCode_agent(int code_agent) {
        this.code_agent = code_agent;
    }

    public void setDate_qc(Date date_qc) {
        this.date_qc = date_qc;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public void setCode_vh(int code_vh) {
        this.code_vh = code_vh;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }


}
