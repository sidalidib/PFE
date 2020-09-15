package com.example.salesapp;

public class vu {
    private int code,code_admin,vu;


    public vu(int code, int code_admin, int vu) {
        this.code = code;
        this.code_admin = code_admin;
        this.vu = vu;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode_admin() {
        return code_admin;
    }

    public void setCode_admin(int code_admin) {
        this.code_admin = code_admin;
    }

    public int getVu() {
        return vu;
    }

    public void setVu(int vu) {
        this.vu = vu;
    }
}
