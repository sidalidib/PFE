package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class communeDAO extends DAOBase {
    public static final String TABLE_NAME = "commune";
    public static final String NOM = "nom";
    public static final String VILLE = "ville";
    public static final String CODE_SEC = "code_sec";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + NOM + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + VILLE + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + CODE_SEC + " int(11) DEFAULT NULL, PRIMARY KEY " + NOM + ", FOREIGN KEY " + CODE_SEC + " REFERENCES secteur) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public communeDAO(Context pContext) {
        super(pContext);}


    /* Rechercher une commune par le nom  */
    public static ArrayList<commune> selectByNom(SQLiteDatabase db,String commune){
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ NOM +"=?;" , new String[]{commune});
        int nom=c.getColumnIndex(NOM);
        int ville=c.getColumnIndex(VILLE);
        int code=c.getColumnIndex(CODE_SEC);
        ArrayList<commune> list=new ArrayList<commune>();
        if (c.moveToFirst()) {
            do { commune com=new commune(c.getString(nom),c.getString(ville),c.getInt(code));
                  list.add(com);    }
             while (c.moveToNext()); }
        return list;
    }



    /* Rechercher toutes les communes situées dans une ville  */
    public static ArrayList<commune> selectByVille(SQLiteDatabase db,String ville){
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ VILLE +"=?;" , new String[]{ville});
        int nom=c.getColumnIndex(NOM);
        int ville1=c.getColumnIndex(VILLE);
        int code=c.getColumnIndex(CODE_SEC);
        ArrayList<commune> list=new ArrayList<commune>();
        if (c.moveToFirst()) {
            do { commune com=new commune(c.getString(nom),c.getString(ville1),c.getInt(code));
                list.add(com);    }
            while (c.moveToNext()); }
        return list;
    }



    /* Rechercher les communes situées dans un secteur  */
    public static ArrayList<commune> selectBySecteur(SQLiteDatabase db,int code_sec){
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_SEC +"=?;" , new String[]{String.valueOf(code_sec)});
        int nom=c.getColumnIndex(NOM);
        int ville=c.getColumnIndex(VILLE);
        int code=c.getColumnIndex(CODE_SEC);
        ArrayList<commune> list=new ArrayList<commune>();
        if (c.moveToFirst()) {
            do { commune com=new commune(c.getString(nom),c.getString(ville),c.getInt(code));
                list.add(com);    }
            while (c.moveToNext()); }
        return list;
    }


    /* Rechercher la commune d'un client */
    public static ArrayList<commune> selectByClient(SQLiteDatabase db,int code_client){
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ NOM +" IN ( select "+ clientDAO.COMMUNE +" from "+ clientDAO.TABLE_NAME +" where "+ clientDAO.CODE_CL +"=?);" , new String[]{String.valueOf(code_client)});
        int nom=c.getColumnIndex(NOM);
        int ville=c.getColumnIndex(VILLE);
        int code=c.getColumnIndex(CODE_SEC);
        ArrayList<commune> list=new ArrayList<commune>();
        if (c.moveToFirst()) {
            do { commune com=new commune(c.getString(nom),c.getString(ville),c.getInt(code));
                list.add(com);    }
            while (c.moveToNext()); }
        return list;
    }


}
