package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class vehiculeDAO extends DAOBase {
    public static final String TABLE_NAME = "vehicule";
    public static final String CODE_VEHICULE = "code_vehicule";
    public static final String MATRICULE = "matricule";
    public static final String ANNEE = "annee";
    public static final String ETAT = "etat";
    public static final String MARQUE = "marque";
    public static final String MODELE = "modele";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_VEHICULE + " int(11) PRIMARY KEY AUTO_INCREMENT, " + MATRICULE + " varchar(20) COLLATE utf8mb4_bin NOT NULL, " + ANNEE + " date DEFAULT NULL, " + ETAT + " int(11) NOT NULL, " + MARQUE + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + MODELE + " varchar(40) COLLATE utf8mb4_bin NOT NULL) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public vehiculeDAO(Context pContext) {
        super(pContext);}


    /* Rechercher un vehicule par son code */
    public static ArrayList<vehicule> selectByCode(SQLiteDatabase db, int code_vehicule) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_VEHICULE +"=? ;" , new String[]{String.valueOf(code_vehicule)});
        int code = c.getColumnIndex(CODE_VEHICULE);
        int matric = c.getColumnIndex(MATRICULE);
        int annee = c.getColumnIndex(ANNEE);
        int etat = c.getColumnIndex(ETAT);
        int marquee = c.getColumnIndex(MARQUE);
        int modele = c.getColumnIndex(MODELE);
        ArrayList<vehicule> list = new ArrayList<vehicule>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ vehicule cl = new vehicule(c.getInt(code),c.getString(matric),d.parse(c.getString(annee)).getYear(),c.getInt(etat),c.getString(marquee),c.getString(modele));
                       list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher les véhicules selon l'année */
    public static ArrayList<vehicule> selectByAnnee(SQLiteDatabase db, int année) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + ANNEE +"=? ;" , new String[]{String.valueOf(année)});
        int code = c.getColumnIndex(CODE_VEHICULE);
        int matric = c.getColumnIndex(MATRICULE);
        int annee = c.getColumnIndex(ANNEE);
        int etat = c.getColumnIndex(ETAT);
        int marquee = c.getColumnIndex(MARQUE);
        int modele = c.getColumnIndex(MODELE);
        ArrayList<vehicule> list = new ArrayList<vehicule>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ vehicule cl = new vehicule(c.getInt(code),c.getString(matric),d.parse(c.getString(annee)).getYear(),c.getInt(etat),c.getString(marquee),c.getString(modele));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher les véhicules selon l'état */
    public static ArrayList<vehicule> selectByEtat(SQLiteDatabase db, int état) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + ETAT +"=? ;" , new String[]{String.valueOf(état)});
        int code = c.getColumnIndex(CODE_VEHICULE);
        int matric = c.getColumnIndex(MATRICULE);
        int annee = c.getColumnIndex(ANNEE);
        int etat = c.getColumnIndex(ETAT);
        int marquee = c.getColumnIndex(MARQUE);
        int modele = c.getColumnIndex(MODELE);
        ArrayList<vehicule> list = new ArrayList<vehicule>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ vehicule cl = new vehicule(c.getInt(code),c.getString(matric),d.parse(c.getString(annee)).getYear(),c.getInt(etat),c.getString(marquee),c.getString(modele));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un vehicule par son matricule */
    public static ArrayList<vehicule> selectByMatricule(SQLiteDatabase db, String matricule) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + MATRICULE +"=? ;" , new String[]{matricule});
        int code = c.getColumnIndex(CODE_VEHICULE);
        int matric = c.getColumnIndex(MATRICULE);
        int annee = c.getColumnIndex(ANNEE);
        int etat = c.getColumnIndex(ETAT);
        int marquee = c.getColumnIndex(MARQUE);
        int modele = c.getColumnIndex(MODELE);
        ArrayList<vehicule> list = new ArrayList<vehicule>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ vehicule cl = new vehicule(c.getInt(code),c.getString(matric),d.parse(c.getString(annee)).getYear(),c.getInt(etat),c.getString(marquee),c.getString(modele));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher les véhicules selon la marque  */
    public static ArrayList<vehicule> selectByMarque(SQLiteDatabase db, String marque) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + MARQUE +"=? ;" , new String[]{marque});
        int code = c.getColumnIndex(CODE_VEHICULE);
        int matric = c.getColumnIndex(MATRICULE);
        int annee = c.getColumnIndex(ANNEE);
        int etat = c.getColumnIndex(ETAT);
        int marquee = c.getColumnIndex(MARQUE);
        int modele = c.getColumnIndex(MODELE);
        ArrayList<vehicule> list = new ArrayList<vehicule>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ vehicule cl = new vehicule(c.getInt(code),c.getString(matric),d.parse(c.getString(annee)).getYear(),c.getInt(etat),c.getString(marquee),c.getString(modele));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher les vehicules selon le modèle */
    public static ArrayList<vehicule> selectByModele(SQLiteDatabase db, String modèle) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + MODELE +"=? ;" , new String[]{modèle});
        int code = c.getColumnIndex(CODE_VEHICULE);
        int matric = c.getColumnIndex(MATRICULE);
        int annee = c.getColumnIndex(ANNEE);
        int etat = c.getColumnIndex(ETAT);
        int marquee = c.getColumnIndex(MARQUE);
        int modele = c.getColumnIndex(MODELE);
        ArrayList<vehicule> list = new ArrayList<vehicule>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ vehicule cl = new vehicule(c.getInt(code),c.getString(matric),d.parse(c.getString(annee)).getYear(),c.getInt(etat),c.getString(marquee),c.getString(modele));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un véhicule selon l'agent */
    public static ArrayList<vehicule> selectByAgent(SQLiteDatabase db, int code_agent) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_VEHICULE +" IN (SELECT "+CODE_VEHICULE+" from "+agentDAO.TABLE_NAME+" where "+agentDAO.CODE_AGENT+"=?) ;" , new String[]{String.valueOf(code_agent)});
        int code = c.getColumnIndex(CODE_VEHICULE);
        int matric = c.getColumnIndex(MATRICULE);
        int annee = c.getColumnIndex(ANNEE);
        int etat = c.getColumnIndex(ETAT);
        int marquee = c.getColumnIndex(MARQUE);
        int modele = c.getColumnIndex(MODELE);
        ArrayList<vehicule> list = new ArrayList<vehicule>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ vehicule cl = new vehicule(c.getInt(code),c.getString(matric),d.parse(c.getString(annee)).getYear(),c.getInt(etat),c.getString(marquee),c.getString(modele));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Modifier l'état d'un véhicule */
    public static void modifierEtat(SQLiteDatabase db,int code_vehicule,int etat) {
        db.execSQL("update "+TABLE_NAME+" set "+ETAT+"="+etat+" where "+CODE_VEHICULE+"="+code_vehicule+" ;");
    }



}
