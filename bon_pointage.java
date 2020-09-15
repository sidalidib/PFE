package com.example.salesapp;

import java.util.Date;

public class bon_pointage {
    private int code_bp;
    private Date date_pt;
    private char type;
    private int code_pt;
    private int code_vehicule;
    private int code_ag;
    private int sec;


    public bon_pointage(int code_bp, Date date_pt, char type, int code_pt, int code_vehicule, int code_ag, int sec)
    {   this.code_bp = code_bp;
        this.date_pt = date_pt;
        this.type = type;
        this.code_pt = code_pt;
        this.code_vehicule = code_vehicule;
        this.code_ag = code_ag;
        this.sec = sec;
    }


    public int getCode_bp() {
        return code_bp;
    }

    public Date getDate_pt() {
        return date_pt;
    }

    public char getType() {
        return type;
    }

    public int getCode_pt() {
        return code_pt;
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


    public void setCode_bp(int code_bp) {
        this.code_bp = code_bp;
    }

    public void setDate_pt(Date date_pt) {
        this.date_pt = date_pt;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setCode_pt(int code_pt) {
        this.code_pt = code_pt;
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
