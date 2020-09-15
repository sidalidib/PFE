package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class qte_chgDAO extends DAOBase {
    public static final String TABLE_NAME = "qte_chg";
    public static final String CODE_BC = "code_bc";
    public static final String CODE_PR = "code_pr";
    public static final String QTE = "qte";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_BC + " int(11) NOT NULL, " + CODE_PR + " int(11) NOT NULL, " + QTE + " int(11) NOT NULL, FOREIGN KEY " + CODE_PR + " REFERENCES produit, FOREIGN KEY " + CODE_BC + " REFERENCES bon_chargement USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public qte_chgDAO(Context pContext) {
        super(pContext);}


    /* Sélectionner toutes les lignes d'un bon de chargement */
    public static ArrayList<qte_chg> select(SQLiteDatabase db, int code_bc) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_BC +"=?;" , new String[]{String.valueOf(code_bc)});
        int code_bonc = c.getColumnIndex(CODE_BC);
        int code_pr = c.getColumnIndex(CODE_PR);
        int qte = c.getColumnIndex(QTE);
        ArrayList<qte_chg> list = new ArrayList<qte_chg>();
        if (c.moveToFirst()) {
            do { qte_chg ql = new qte_chg(c.getInt(code_bonc),c.getInt(code_pr),c.getInt(qte));
                list.add(ql); }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner la ligne d'un produit dans un bon de chargement */
    public static ArrayList<qte_chg> selectByProduct(SQLiteDatabase db,int code_bc,int code_product) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_BC +"=? AND "+ CODE_PR +"=? ;" , new String[]{String.valueOf(code_bc),String.valueOf(code_product)});
        int code_bonc = c.getColumnIndex(CODE_BC);
        int code_pr = c.getColumnIndex(CODE_PR);
        int qte = c.getColumnIndex(QTE);
        ArrayList<qte_chg> list = new ArrayList<qte_chg>();
        if (c.moveToFirst()) {
            do { qte_chg ql = new qte_chg(c.getInt(code_bonc),c.getInt(code_pr),c.getInt(qte));
                list.add(ql); }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* ajouter une ligne dans un bon de chargement */
    public static void ajouterLigne(SQLiteDatabase db, int code_bc, int code_produit, int quantite) {
        db.execSQL("insert into "+ TABLE_NAME +" values("+ String.valueOf(code_bc) +","+ String.valueOf(code_produit) +","+ String.valueOf(quantite) +");");
    }

    /* supprimer une ligne d'un bon de chargement */
    public static void supprimerLigne(SQLiteDatabase db,int code_bc,int code_produit) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+ CODE_BC +"="+ String.valueOf(code_bc) +" AND "+ CODE_PR +"="+ String.valueOf(code_produit) +";");
    }

    /* modifier une ligne existante dans un bon de chargement */
    public static void modifierLigne(SQLiteDatabase db,int code_bc,int code_produit,int quantite) {
        db.execSQL("update "+ TABLE_NAME +" set "+ QTE +"="+ String.valueOf(quantite) +" where "+ CODE_BC +"="+ String.valueOf(code_bc) +" AND "+ CODE_PR +"="+ String.valueOf(code_produit) +";");
    }

    /* supprimer toutes les lignes d'un bon de chargement */
    public static void supprimerBonDeChargement(SQLiteDatabase db,int code_bc) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+ CODE_BC +"="+ String.valueOf(code_bc) +";");
    }


    /* Retourner la quantité chargée d'un produit chez un vendeur */
    public static int charge(SQLiteDatabase db,int code_product,int code_agent){
        Cursor c = db.rawQuery("select " + QTE + " from " + TABLE_NAME + " where (" + CODE_PR + "=?) AND (" + CODE_BC + " IN (select " + bon_chargementDAO.CODE_BC + " from " + bon_chargementDAO.TABLE_NAME + " where " + bon_chargementDAO.CODE_AG + "= ?));" , new String[]{String.valueOf(code_product),String.valueOf(code_agent)});
        int qte = c.getColumnIndex(QTE);
        int chg = 0;
        if (c.moveToFirst()) { chg = c.getInt(qte);}
        c.close();
        return chg;
    }


}
