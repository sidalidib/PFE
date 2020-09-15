package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class qte_ptgDAO extends DAOBase {
    public static final String TABLE_NAME = "qte_ptg";
    public static final String CODE_BP = "code_bp";
    public static final String CODE_PR = "code_pr";
    public static final String QTE = "qte";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_BP + " int(11) NOT NULL, " + CODE_PR + " int(11) NOT NULL, " + QTE + " int(11) NOT NULL, FOREIGN KEY " + CODE_PR + " REFERENCES produit, FOREIGN KEY " + CODE_BP + " REFERENCES bon_pointage USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public qte_ptgDAO(Context pContext) {
        super(pContext);}


    /* Sélectionner toutes les lignes d'un bon de pointage */
    public static ArrayList<qte_ptg> select(SQLiteDatabase db,int code_bp) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_BP +"=?;" , new String[]{String.valueOf(code_bp)});
        int code_bonp = c.getColumnIndex(CODE_BP);
        int code_pr = c.getColumnIndex(CODE_PR);
        int qte = c.getColumnIndex(QTE);
        ArrayList<qte_ptg> list = new ArrayList<qte_ptg>();
        if (c.moveToFirst()) {
            do { qte_ptg ql = new qte_ptg(c.getInt(code_bonp),c.getInt(code_pr),c.getInt(qte));
                list.add(ql); }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Sélectionner la ligne d'un produit dans un bon de pointage */
    public static ArrayList<qte_ptg> selectByProduct(SQLiteDatabase db,int code_bp,int code_product) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_BP +"=? AND "+ CODE_PR +"=? ;" , new String[]{String.valueOf(code_bp),String.valueOf(code_product)});
        int code_bonp = c.getColumnIndex(CODE_BP);
        int code_pr = c.getColumnIndex(CODE_PR);
        int qte = c.getColumnIndex(QTE);
        ArrayList<qte_ptg> list = new ArrayList<qte_ptg>();
        if (c.moveToFirst()) {
            do { qte_ptg ql = new qte_ptg(c.getInt(code_bonp),c.getInt(code_pr),c.getInt(qte));
                list.add(ql); }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner toutes les lignes de pointage d'un agent */
    public static ArrayList<qte_ptg> selectByAgent(SQLiteDatabase db,int code_agent) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_BP +" IN (select " + CODE_BP + " from " + bon_pointageDAO.TABLE_NAME + " where " + bon_pointageDAO.CODE_AG + "= ?);" , new String[]{String.valueOf(code_agent)});
        int code_bonp = c.getColumnIndex(CODE_BP);
        int code_pr = c.getColumnIndex(CODE_PR);
        int qte = c.getColumnIndex(QTE);
        ArrayList<qte_ptg> list = new ArrayList<qte_ptg>();
        if (c.moveToFirst()) {
            do { qte_ptg ql = new qte_ptg(c.getInt(code_bonp),c.getInt(code_pr),c.getInt(qte));
                list.add(ql); }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner la ligne de pointage d'un produit chez un agent */
    public static ArrayList<qte_ptg> selectByAgentAndProduct(SQLiteDatabase db,int code_produit,int code_agent) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+CODE_PR+"=? AND "+ CODE_BP +" IN (select " + CODE_BP + " from " + bon_pointageDAO.TABLE_NAME + " where " + bon_pointageDAO.CODE_AG + "= ?);" , new String[]{String.valueOf(code_produit),String.valueOf(code_agent)});
        int code_bonp = c.getColumnIndex(CODE_BP);
        int code_pr = c.getColumnIndex(CODE_PR);
        int qte = c.getColumnIndex(QTE);
        ArrayList<qte_ptg> list = new ArrayList<qte_ptg>();
        if (c.moveToFirst()) {
            do { qte_ptg ql = new qte_ptg(c.getInt(code_bonp),c.getInt(code_pr),c.getInt(qte));
                list.add(ql); }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* ajouter une ligne dans un bon de pointage */
    public static void ajouterLigne(SQLiteDatabase db, int code_bp, int code_produit, int quantite) {
        db.execSQL("insert into "+ TABLE_NAME +" values("+ String.valueOf(code_bp) +","+ String.valueOf(code_produit) +","+ String.valueOf(quantite) +");");
    }

    /* supprimer une ligne d'un bon de pointage */
    public static void supprimerLigne(SQLiteDatabase db,int code_bp,int code_produit) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+ CODE_BP +"="+ String.valueOf(code_bp) +" AND "+ CODE_PR +"="+ String.valueOf(code_produit) +";");
    }

    /* modifier une ligne existante dans un bon de pointage */
    public static void modifierLigne(SQLiteDatabase db,int code_bp,int code_produit,int quantite) {
        db.execSQL("update "+ TABLE_NAME +" set "+ QTE +"="+ String.valueOf(quantite) +" where "+ CODE_BP +"="+ String.valueOf(code_bp) +" AND "+ CODE_PR +"="+ String.valueOf(code_produit) +";");
    }

    /* supprimer toutes les lignes d'un bon de pointage */
    public static void supprimerBonDePointage(SQLiteDatabase db,int code_bp) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+ CODE_BP +"="+ String.valueOf(code_bp) +";");
    }



    public static int ecartSortie(SQLiteDatabase db,int code_produit,int code_agent,int pointage) {
        int e=0;
        int c = qte_chgDAO.charge(db,code_produit,code_agent);
        e = pointage-c;
        return e;
    }

    public static int ecartRetour(SQLiteDatabase db,int code_produit,int code_agent,int pointage) {
        int e=0;
        int s = produitDAO.stockOfProduct(db,code_produit,code_agent);
        e = pointage-s;
        return e;
    }




}
