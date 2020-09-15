package com.example.salesapp;

import java.util.Date;

public class bon_chargement {
    private int code_bc;
    private Date date_bc;
    private int code_admin;
    private int code_vehicule;
    private int code_ag;
    private int sec;

    public bon_chargement(int code_bc, Date date_bc, int code_admin, int code_vehicule, int code_ag, int sec) {
        this.code_bc = code_bc;
        this.date_bc = date_bc;
        this.code_admin = code_admin;
        this.code_vehicule = code_vehicule;
        this.code_ag = code_ag;
        this.sec = sec;
    }


    public int getCode_bc() {
        return code_bc;
    }

    public Date getDate_bc() {
        return date_bc;
    }

    public int getCode_admin() {
        return code_admin;
    }

    public int getCode_vehicule() {
        return code_vehicule;
    }

    public int getCode_ag() {
        return code_ag;
    }

    public int getSec() {
        return sec;
    }




    public void setCode_bc(int code_bc) {
        this.code_bc = code_bc;
    }

    public void setDate_bc(Date date_bc) {
        this.date_bc = date_bc;
    }

    public void setCode_admin(int code_admin) {
        this.code_admin = code_admin;
    }

    public void setCode_vehicule(int code_vehicule) {
        this.code_vehicule = code_vehicule;
    }

    public void setCode_ag(int code_ag) {
        this.code_ag = code_ag;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
}
