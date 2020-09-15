package com.example.salesapp;

import java.time.LocalDateTime;

public class notification {
    private int code;
    private int type;
    private LocalDateTime d;
    private int code_user;
    private String contenu;


    public notification(int code, int type, LocalDateTime d, int code_user, String contenu) {
        this.code = code;
        this.type = type;
        this.d = d;
        this.code_user = code_user;
        this.contenu = contenu;
    }


    public int getCode() {
        return code;
    }

    public int getType() {
        return type;
    }

    public LocalDateTime getD() {
        return d;
    }

    public int getCode_user() {
        return code_user;
    }

    public String getContenu() {
        return contenu;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setD(LocalDateTime d) {
        this.d = d;
    }

    public void setCode_user(int code_user) {
        this.code_user = code_user;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
