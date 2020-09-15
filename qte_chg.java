package com.example.salesapp;

public class qte_chg {
    private int code_pr,code_bc,qte;

    public qte_chg(int code_pr, int code_bc, int qte) {
        this.code_pr = code_pr;
        this.code_bc = code_bc;
        this.qte = qte;
    }


    public int getCode_pr() {
        return code_pr;
    }

    public void setCode_pr(int code_pr) {
        this.code_pr = code_pr;
    }

    public int getCode_bc() {
        return code_bc;
    }

    public void setCode_bc(int code_bc) {
        this.code_bc = code_bc;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
