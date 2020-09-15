package com.example.salesapp;

public class itineraire {

    public enum dayOfWeek {DIMANCHE,LUNDI,MARDI,MERCREDI,JEUDI};

    private dayOfWeek jour;
    private int code_cl;
    private int code_agent;


    public itineraire(dayOfWeek jour, int code_cl, int code_agent) {
        this.jour = jour;
        this.code_cl = code_cl;
        this.code_agent = code_agent;
    }


    public dayOfWeek getJour() {
        return jour;
    }

    public void setJour(dayOfWeek jour) {
        this.jour = jour;
    }

    public int getCode_cl() {
        return code_cl;
    }

    public void setCode_cl(int code_cl) {
        this.code_cl = code_cl;
    }

    public int getCode_agent() {
        return code_agent;
    }

    public void setCode_agent(int code_agent) {
        this.code_agent = code_agent;
    }
}
