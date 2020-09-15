package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class secteurDAO extends DAOBase {
    public static final String TABLE_NAME = "secteur";
    public static final String CODE_SEC = "code_sec";
    public static final String VILLE = "ville";
    public static final String CODE_POINTEUR = "code_pointeur";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_SEC + " int(11) PRIMARY KEY AUTO_INCREMENT, " + VILLE + " varchar(40) COLLATE utf8mb4_bin DEFAULT NULL, " + CODE_POINTEUR + " int(11) DEFAULT NULL, FOREIGN KEY " + CODE_POINTEUR + " REFERENCES pointeur) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public secteurDAO(Context pContext) {
        super(pContext);}


    /* Rechercher un secteur par son code */
    public static ArrayList<secteur> selectByCode(SQLiteDatabase db,int code_secteur){
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_SEC +"=?;" , new String[]{String.valueOf(code_secteur)});
        int code=c.getColumnIndex(CODE_SEC);
        int ville1=c.getColumnIndex(VILLE);
        int code_pt=c.getColumnIndex(CODE_POINTEUR);
        ArrayList<secteur> list=new ArrayList<secteur>();
        if (c.moveToFirst()) {
            do { secteur com=new secteur(c.getInt(code),c.getString(ville1),c.getInt(code_pt));
                list.add(com);    }
            while (c.moveToNext()); }
        return list;
    }


    /* Rechercher les secteurs selon le pointeur */
    public static ArrayList<secteur> selectByPointeur(SQLiteDatabase db,int code_pointeur){
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_POINTEUR +"=?;" , new String[]{String.valueOf(code_pointeur)});
        int code=c.getColumnIndex(CODE_SEC);
        int ville1=c.getColumnIndex(VILLE);
        int code_pt=c.getColumnIndex(CODE_POINTEUR);
        ArrayList<secteur> list=new ArrayList<secteur>();
        if (c.moveToFirst()) {
            do { secteur com=new secteur(c.getInt(code),c.getString(ville1),c.getInt(code_pt));
                list.add(com);    }
            while (c.moveToNext()); }
        return list;
    }


    /* Rechercher toutes les secteurs situés dans une ville  */
    public static ArrayList<secteur> selectByVille(SQLiteDatabase db, String ville){
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ VILLE +"=?;" , new String[]{ville});
        int code=c.getColumnIndex(CODE_SEC);
        int ville1=c.getColumnIndex(VILLE);
        int code_pt=c.getColumnIndex(CODE_POINTEUR);
        ArrayList<secteur> list=new ArrayList<secteur>();
        if (c.moveToFirst()) {
            do { secteur com=new secteur(c.getInt(code),c.getString(ville1),c.getInt(code_pt));
                list.add(com);    }
            while (c.moveToNext()); }
        return list;
    }


}
