package com.example.salesapp;

public class qte_ptg {
    private int code_bp,code_pr,qte;


    public qte_ptg(int code_bp, int code_pr, int qte) {
        this.code_bp = code_bp;
        this.code_pr = code_pr;
        this.qte = qte;
    }


    public int getCode_bp() {
        return code_bp;
    }

    public void setCode_bp(int code_bp) {
        this.code_bp = code_bp;
    }

    public int getCode_pr() {
        return code_pr;
    }

    public void setCode_pr(int code_pr) {
        this.code_pr = code_pr;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
