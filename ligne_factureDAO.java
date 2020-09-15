package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ligne_factureDAO extends DAOBase {

    public static final String TABLE_NAME = "ligne_facture";
    public static final String CODE_FACT = "code_fact";
    public static final String CODE_PR = "code_pr";
    public static final String QTE = "qte";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_FACT + " int(11) NOT NULL, " + CODE_PR + " int(11) NOT NULL, " + QTE + " int(11) NOT NULL, FOREIGN KEY " + CODE_FACT + " REFERENCES facture, FOREIGN KEY " + CODE_PR + " REFERENCES produit) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public ligne_factureDAO(Context pContext) {
        super(pContext);}


    /* Sélectionner toutes les lignes d'une facture */
    public static ArrayList<ligne_facture> select(SQLiteDatabase db, int code_facture) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_FACT +"=?;" , new String[]{String.valueOf(code_facture)});
        int code_fact = c.getColumnIndex(CODE_FACT);
        int code_pr = c.getColumnIndex(CODE_PR);
        int qte = c.getColumnIndex(QTE);
        ArrayList<ligne_facture> list = new ArrayList<ligne_facture>();
        if (c.moveToFirst()) {
            do { ligne_facture ql = new ligne_facture(c.getInt(code_fact),c.getInt(code_pr),c.getInt(qte));
                list.add(ql); }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner la ligne d'un produit dans une facture */
    public static ArrayList<ligne_facture> selectByProduct(SQLiteDatabase db,int code_facture,int code_product) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_FACT +"=? AND "+ CODE_PR +"=? ;" , new String[]{String.valueOf(code_facture),String.valueOf(code_product)});
        int code_fact = c.getColumnIndex(CODE_FACT);
        int code_pr = c.getColumnIndex(CODE_PR);
        int qte = c.getColumnIndex(QTE);
        ArrayList<ligne_facture> list = new ArrayList<ligne_facture>();
        if (c.moveToFirst()) {
            do { ligne_facture ql = new ligne_facture(c.getInt(code_fact),c.getInt(code_pr),c.getInt(qte));
                list.add(ql); }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* ajouter une ligne dans une facture */
    public static void ajouterAuPalier(SQLiteDatabase db,int code_facture,int code_produit,int quantite) {
        db.execSQL("insert into "+ TABLE_NAME +" values("+ String.valueOf(code_facture) +","+ String.valueOf(code_produit) +","+ String.valueOf(quantite) +");");
    }

    /* supprimer une ligne d'une facture */
    public static void supprimerLigne(SQLiteDatabase db,int code_facture,int code_produit) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+ CODE_FACT +"="+ String.valueOf(code_facture) +" AND "+ CODE_PR +"="+ String.valueOf(code_produit) +";");
    }

    /* modifier une ligne existante dans une facture */
    public static void modifierLigne(SQLiteDatabase db,int code_facture,int code_produit,int quantite) {
        db.execSQL("update "+ TABLE_NAME +" set "+ QTE +"="+ String.valueOf(quantite) +" where "+ CODE_FACT +"="+ String.valueOf(code_facture) +" AND "+ CODE_PR +"="+ String.valueOf(code_produit) +";");
    }

    /* supprimer toutes les lignes d'une facture */
    public static void supprimerFacture(SQLiteDatabase db,int code_facture) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+ CODE_FACT +"="+ String.valueOf(code_facture) +";");
    }


    /* calculer le montant d'une ligne dans une facture */
    public static float montantLigne(SQLiteDatabase db,int code_facture,int code_produit) {
        ArrayList<ligne_facture> list = selectByProduct(db,code_facture,code_produit);
        ArrayList<produit> list1 = produitDAO.selectByCode(db,code_produit);
        return (list1.get(0).getPrix_u())*(list.get(0).getQte());
    }




}
