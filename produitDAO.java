package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class produitDAO extends DAOBase {
    public static final String TABLE_NAME = "produit";
    public static final String CODE_PR = "code_pr";
    public static final String DESIGNATION = "designation";
    public static final String PRIX_U = "prix_u";
    public static final String U_CRT = "u_crt";
    public static final String STOCK = "stock";
    public static final String CATEGORIE = "categorie";
    public static final String MARQUE = "marque";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_PR + " int(11) PRIMARY KEY AUTO_INCREMENT, " + DESIGNATION + " varchar(255) COLLATE utf8mb4_bin NOT NULL, " + PRIX_U + " decimal(6,2) NOT NULL, " + U_CRT + " int(11) NOT NULL, " + STOCK + " int(11) NOT NULL, " + CATEGORIE + " varchar(100) COLLATE utf8mb4_bin NOT NULL, " + MARQUE + " varchar(40) COLLATE utf8mb4_bin DEFAULT NULL) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public produitDAO(Context pContext) {
        super(pContext);}


    /* Sélectionner un produit selon son code  */
    public static ArrayList<produit> selectByCode(SQLiteDatabase db,int code_produit) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_PR + "=?;" , new String[]{String.valueOf(code_produit)});
        int code_pr = c.getColumnIndex(CODE_PR);
        int designation = c.getColumnIndex(DESIGNATION);
        int prix_u = c.getColumnIndex(PRIX_U);
        int u_crt = c.getColumnIndex(U_CRT);
        int stock = c.getColumnIndex(STOCK);
        int categorie = c.getColumnIndex(CATEGORIE);
        int marque = c.getColumnIndex(MARQUE);
        ArrayList<produit> list = new ArrayList<produit>();
        if (c.moveToFirst()) {
            do { produit cl = new produit(c.getInt(code_pr),c.getString(designation),c.getFloat(prix_u),c.getInt(u_crt),c.getInt(stock),c.getString(categorie),c.getString(marque));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner des produits selon la catégorie  */
    public static ArrayList<produit> selectByCategorie(SQLiteDatabase db,String catégorie) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CATEGORIE + "='?';" , new String[]{catégorie});
        int code_pr = c.getColumnIndex(CODE_PR);
        int designation = c.getColumnIndex(DESIGNATION);
        int prix_u = c.getColumnIndex(PRIX_U);
        int u_crt = c.getColumnIndex(U_CRT);
        int stock = c.getColumnIndex(STOCK);
        int categorie = c.getColumnIndex(CATEGORIE);
        int marque = c.getColumnIndex(MARQUE);
        ArrayList<produit> list = new ArrayList<produit>();
        if (c.moveToFirst()) {
            do { produit cl = new produit(c.getInt(code_pr),c.getString(designation),c.getFloat(prix_u),c.getInt(u_crt),c.getInt(stock),c.getString(categorie),c.getString(marque));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Sélectionner un produit par sa marque  */
    public static ArrayList<produit> selectByMarque(SQLiteDatabase db,String marque) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + MARQUE + "='?';" , new String[]{marque});
        int code_pr = c.getColumnIndex(CODE_PR);
        int designation = c.getColumnIndex(DESIGNATION);
        int prix_u = c.getColumnIndex(PRIX_U);
        int u_crt = c.getColumnIndex(U_CRT);
        int stock = c.getColumnIndex(STOCK);
        int categorie = c.getColumnIndex(CATEGORIE);
        int marquee = c.getColumnIndex(MARQUE);
        ArrayList<produit> list = new ArrayList<produit>();
        if (c.moveToFirst()) {
            do { produit cl = new produit(c.getInt(code_pr),c.getString(designation),c.getFloat(prix_u),c.getInt(u_crt),c.getInt(stock),c.getString(categorie),c.getString(marquee));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner un produit par sa désignation  */
    public static ArrayList<produit> selectBySearch(SQLiteDatabase db,String search) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + DESIGNATION + " LIKE '?%' ;" , new String[]{search});
        int code_pr = c.getColumnIndex(CODE_PR);
        int designation = c.getColumnIndex(DESIGNATION);
        int prix_u = c.getColumnIndex(PRIX_U);
        int u_crt = c.getColumnIndex(U_CRT);
        int stock = c.getColumnIndex(STOCK);
        int categorie = c.getColumnIndex(CATEGORIE);
        int marque = c.getColumnIndex(MARQUE);
        ArrayList<produit> list = new ArrayList<produit>();
        if (c.moveToFirst()) {
            do { produit cl = new produit(c.getInt(code_pr),c.getString(designation),c.getFloat(prix_u),c.getInt(u_crt),c.getInt(stock),c.getString(categorie),c.getString(marque));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Sélectionner tous les produits disponibles selon le vendeur */
    public static ArrayList<produit> selectByAgent(SQLiteDatabase db,int code_agent) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_PR + " IN (select " + CODE_PR + " from " + qte_chgDAO.TABLE_NAME + " where " + qte_chgDAO.CODE_BC + " IN (select " + qte_chgDAO.CODE_BC + " from " + bon_chargementDAO.TABLE_NAME + " where " + bon_chargementDAO.CODE_AG + "= ?));" , new String[]{String.valueOf(code_agent)});
        int code_pr = c.getColumnIndex(CODE_PR);
        int designation = c.getColumnIndex(DESIGNATION);
        int prix_u = c.getColumnIndex(PRIX_U);
        int u_crt = c.getColumnIndex(U_CRT);
        int stock = c.getColumnIndex(STOCK);
        int categorie = c.getColumnIndex(CATEGORIE);
        int marque = c.getColumnIndex(MARQUE);
        ArrayList<produit> list = new ArrayList<produit>();
        if (c.moveToFirst()) {
            do { produit cl = new produit(c.getInt(code_pr),c.getString(designation),c.getFloat(prix_u),c.getInt(u_crt),c.getInt(stock),c.getString(categorie),c.getString(marque));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Retourner la quantité vendue d'un produit par un vendeur */
    public static int vendu(SQLiteDatabase db,int code_product,int code_agent){
        Cursor b = db.rawQuery("select SUM(" + ligne_factureDAO.QTE + ") AS somme from "+ ligne_factureDAO.TABLE_NAME +" where (" + ligne_factureDAO.CODE_PR + "=?) AND (" + ligne_factureDAO.CODE_FACT + " IN (select " + factureDAO.CODE_FACT + " from " + factureDAO.TABLE_NAME + " where " + factureDAO.CODE_AG + "= ?));" , new String[]{String.valueOf(code_product),String.valueOf(code_agent)});
        int qte1 = b.getColumnIndex("somme");
        int vnd = 0;
        if (b.moveToFirst()) { vnd = b.getInt(qte1);}
        b.close();
        return vnd;
    }

    /* Retourner la quantité restante en stock d'un produit dans le véhicule d'un vendeur */
    public static int stockOfProduct(SQLiteDatabase db,int code_product,int code_agent) {
        int chg,vnd,stock=0;
        chg = qte_chgDAO.charge(db,code_product,code_agent);
        vnd = vendu(db,code_product,code_agent);
        stock = (chg-vnd) ;
        return stock;
    }


    /* Retourner le pourcentage de vente d'un produit par un vendeur */
    public static double PourcentageVente(SQLiteDatabase db,int code_product,int code_agent) {
        int chg,vnd;
        double p=0;
        chg = qte_chgDAO.charge(db,code_product,code_agent);
        vnd = vendu(db,code_product,code_agent);
        if (chg != 0) { p = (vnd*100)/chg ;}
        return p;
    }








}
