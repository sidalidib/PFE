package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class clientDAO extends DAOBase {
    public static final String TABLE_NAME = "client";
    public static final String CODE_CL = "code_cl";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String ADRESSE = "adresse";
    public static final String TELEPHONE = "telephone";
    public static final String NRC = "nrc";
    public static final String NIF = "nif";
    public static final String VALIDE = "valide";
    public static final String CODE_SEC = "code_sec";
    public static final String COMMUNE = "commune";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_CL + " int(11) PRIMARY KEY AUTO_INCREMENT, " + NOM + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + PRENOM + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + ADRESSE + " varchar(255) COLLATE utf8mb4_bin NOT NULL, " + TELEPHONE + " varchar(10) COLLATE utf8mb4_bin NOT NULL, " + NRC + " int(11) NOT NULL, " + NIF + " int(11) NOT NULL, " + VALIDE + " int(1) NOT NULL, " + CODE_SEC + " int(11) NOT NULL, " + COMMUNE + " varchar(40) COLLATE utf8mb4_bin DEFAULT NULL, FOREIGN KEY " + CODE_SEC + " REFERENCES secteur) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public clientDAO(Context pContext) {
        super(pContext);}

    /* Rechercher un client selon le jour et le nom/prénom dans la barre de recherche */
    public static ArrayList<client> selectBySearch(SQLiteDatabase db,int code_agent, String day, String search) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where "+ CODE_CL +" IN ( select "+ CODE_CL +" from "+ itineraireDAO.TABLE_NAME +" where "+ itineraireDAO.CODE_AGENT +"=?) AND (CONCAT(nom,' ',prenom)=? OR CONCAT(prenom,' ',nom)=?) AND " + CODE_CL +" IN (select " + CODE_CL + " from " + itineraireDAO.TABLE_NAME + " where " + itineraireDAO.JOUR + "=?) ;",new String[]{String.valueOf(code_agent),search,search,day});
        int code_cl = c.getColumnIndex(CODE_CL);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int adresse = c.getColumnIndex(ADRESSE);
        int telephone = c.getColumnIndex(TELEPHONE);
        int nrc = c.getColumnIndex(NRC);
        int nif = c.getColumnIndex(NIF);
        int valide = c.getColumnIndex(VALIDE);
        int code_sec = c.getColumnIndex(CODE_SEC);
        int commune = c.getColumnIndex(COMMUNE);
        ArrayList<client> list = new ArrayList<client>();
        if (c.moveToFirst()) {
            do { client cl = new client(c.getInt(code_cl),c.getString(nom),c.getString(prenom),c.getString(adresse),c.getString(telephone),c.getInt(nrc),c.getInt(nif),c.getInt(valide),c.getInt(code_sec),c.getString(commune));
                 list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }

    /* Rechercher un client selon le jour seulement */
    public static ArrayList<client> select(SQLiteDatabase db,int code_agent, String day) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where "+ CODE_CL +" IN ( select "+ CODE_CL +" from "+ itineraireDAO.TABLE_NAME +" where "+ itineraireDAO.CODE_AGENT +"=?) AND " + CODE_CL +" IN (select " + CODE_CL + " from " + itineraireDAO.TABLE_NAME + " where " + itineraireDAO.JOUR + "=?) ;",new String[]{String.valueOf(code_agent),day});
        int code_cl = c.getColumnIndex(CODE_CL);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int adresse = c.getColumnIndex(ADRESSE);
        int telephone = c.getColumnIndex(TELEPHONE);
        int nrc = c.getColumnIndex(NRC);
        int nif = c.getColumnIndex(NIF);
        int valide=c.getColumnIndex(VALIDE);
        int code_sec = c.getColumnIndex(CODE_SEC);
        int commune = c.getColumnIndex(COMMUNE);
        ArrayList<client> list = new ArrayList<client>();
        if (c.moveToFirst()) {
            do { client cl = new client(c.getInt(code_cl),c.getString(nom),c.getString(prenom),c.getString(adresse),c.getString(telephone),c.getInt(nrc),c.getInt(nif),c.getInt(valide),c.getInt(code_sec),c.getString(commune));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }

    /* Rechercher un client selon le nom/prénom dans la barre de recherche */
    public static ArrayList<client> selectAllBySearch(SQLiteDatabase db,int code_agent,String search) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where "+ CODE_CL +" IN ( select "+ CODE_CL +" from "+ itineraireDAO.TABLE_NAME +" where "+ itineraireDAO.CODE_AGENT +"=?) AND (CONCAT(nom,' ',prenom)=? OR CONCAT(prenom,' ',nom)=?) ;",new String[]{String.valueOf(code_agent),search,search});
        int code_cl = c.getColumnIndex(CODE_CL);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int adresse = c.getColumnIndex(ADRESSE);
        int telephone = c.getColumnIndex(TELEPHONE);
        int nrc = c.getColumnIndex(NRC);
        int nif = c.getColumnIndex(NIF);
        int valide = c.getColumnIndex(VALIDE);
        int code_sec = c.getColumnIndex(CODE_SEC);
        int commune = c.getColumnIndex(COMMUNE);
        ArrayList<client> list = new ArrayList<client>();
        if (c.moveToFirst()) {
            do { client cl = new client(c.getInt(code_cl),c.getString(nom),c.getString(prenom),c.getString(adresse),c.getString(telephone),c.getInt(nrc),c.getInt(nif),c.getInt(valide),c.getInt(code_sec),c.getString(commune));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }

    /* Rechercher tous les clients existants */
    public static ArrayList<client> selectAll(SQLiteDatabase db,int code_agent) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where "+ CODE_CL +" IN ( select "+ CODE_CL +" from "+ itineraireDAO.TABLE_NAME +" where "+ itineraireDAO.CODE_AGENT +"=?) ;" , new String[]{String.valueOf(code_agent)});
        int code_cl = c.getColumnIndex(CODE_CL);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int adresse = c.getColumnIndex(ADRESSE);
        int telephone = c.getColumnIndex(TELEPHONE);
        int nrc = c.getColumnIndex(NRC);
        int nif = c.getColumnIndex(NIF);
        int valide = c.getColumnIndex(VALIDE);
        int code_sec = c.getColumnIndex(CODE_SEC);
        int commune = c.getColumnIndex(COMMUNE);
        ArrayList<client> list = new ArrayList<client>();
        if (c.moveToFirst()) {
            do { client cl = new client(c.getInt(code_cl),c.getString(nom),c.getString(prenom),c.getString(adresse),c.getString(telephone),c.getInt(nrc),c.getInt(nif),c.getInt(valide),c.getInt(code_sec),c.getString(commune));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }

    /* Insérer un nouveau client */
    public static void insert(SQLiteDatabase db,int code_cl,String nom,String prenom,String adresse,String telephone,int nrc,int nif,int code_sec,String commune) {
        db.execSQL("insert into " + TABLE_NAME + " values("+String.valueOf(code_cl)+",'"+nom+"','"+prenom+"','"+adresse+"','"+telephone+"',"+String.valueOf(nrc)+","+String.valueOf(nif)+",0,"+String.valueOf(code_sec)+",'"+commune+"');");
    }

    /* Modifier une information de type entier d'un client */
    public static void updateInt(SQLiteDatabase db,String columnName,int val,int code_client){
        db.execSQL("update "+ TABLE_NAME +" set "+ columnName +"="+String.valueOf(val)+" where "+CODE_CL+"="+String.valueOf(code_client)+";");
        db.execSQL("update "+ TABLE_NAME +" set "+ VALIDE +"=0 where "+CODE_CL+"="+String.valueOf(code_client)+";");
    }

    /* Modifier une information de type chaine de caractères d'un client */
    public static void updateString(SQLiteDatabase db,String columnName,String val,int code_client){
        db.execSQL("update "+ TABLE_NAME +" set "+ columnName +"="+val+" where "+CODE_CL+"="+String.valueOf(code_client)+";");
        db.execSQL("update "+ TABLE_NAME +" set "+ VALIDE +"=0 where "+CODE_CL+"="+String.valueOf(code_client)+";");
    }


}
