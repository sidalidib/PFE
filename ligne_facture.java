package com.example.salesapp;

public class ligne_facture {

    public class prod_qte{
        private int code_pr,qte;
        public prod_qte(int code_pr, int qte) {
            this.code_pr = code_pr;
            this.qte = qte; }
        public int getCode_pr() { return code_pr; }
        public int getQte() { return qte; }
        public void setCode_pr(int code_pr) { this.code_pr = code_pr; }
        public void setQte(int qte) { this.qte = qte; }
    }


    private int code_fact;
    private int code_pr;
    private int qte;


    public ligne_facture(int code_fact, int code_pr, int qte) {
        this.code_fact = code_fact;
        this.code_pr = code_pr;
        this.qte = qte;
    }


    public int getCode_fact() {
        return code_fact;
    }

    public int getCode_pr() {
        return code_pr;
    }

    public int getQte() {
        return qte;
    }


    public void setCode_fact(int code_fact) {
        this.code_fact = code_fact;
    }

    public void setCode_pr(int code_pr) {
        this.code_pr = code_pr;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
