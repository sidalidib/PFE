package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Printer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class bon_chargementDAO extends DAOBase {
    public static final String TABLE_NAME = "bon_chargement";
    public static final String CODE_BC = "code_bc";
    public static final String DATE_BC = "date_bc";
    public static final String CODE_ADMIN = "code_admin";
    public static final String CODE_VEHICULE = "code_vehicule";
    public static final String CODE_AG = "code_ag";
    public static final String SEC = "sec";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_BC + " int(11) PRIMARY KEY AUTO_INCREMENT, " + DATE_BC + " date NOT NULL, " + CODE_ADMIN + " int(11) NOT NULL, " + CODE_VEHICULE + " int(11) NOT NULL, " + CODE_AG + " int(11) NOT NULL, " + SEC + " int(11) DEFAULT NULL, FOREIGN KEY " + CODE_ADMIN + " REFERENCES admin USING BTREE, FOREIGN KEY " + CODE_VEHICULE + " REFERENCES vehicule, FOREIGN KEY " + CODE_AG + " REFERENCES agent, FOREIGN KEY " + SEC + " REFERENCES secteur) ENGINE=InnoDB AUTO_INCREMENT=1589781063 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public bon_chargementDAO(Context pContext) {
        super(pContext);}




    /* Rechercher un bon de chargement par son code */
    public static ArrayList<bon_chargement> select(SQLiteDatabase db, int code_bc) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_BC +"=? ;" , new String[]{String.valueOf(code_bc)});
        int code = c.getColumnIndex(CODE_BC);
        int date_bc = c.getColumnIndex(DATE_BC);
        int code_admin = c.getColumnIndex(CODE_ADMIN);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date_bc)),c.getInt(code_admin),c.getInt(code_vehicule),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de chargement par le code de l'agent et la date */
    public static ArrayList<bon_chargement> selectByAgent(SQLiteDatabase db, int code_ag, Date date_bc) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"=? AND "+ DATE_BC +"='?' ;" , new String[]{String.valueOf(code_ag),d.format(date_bc)});
        int code = c.getColumnIndex(CODE_BC);
        int date = c.getColumnIndex(DATE_BC);
        int code_admin = c.getColumnIndex(CODE_ADMIN);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_agent = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date)),c.getInt(code_admin),c.getInt(code_vehicule),c.getInt(code_agent),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de chargement par le code du véhicule et la date */
    public static ArrayList<bon_chargement> selectByVehicule(SQLiteDatabase db, int code_vehicule, Date date_bc) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_VEHICULE +"=? AND "+ DATE_BC +"='?' ;" , new String[]{String.valueOf(code_vehicule),d.format(date_bc)});
        int code = c.getColumnIndex(CODE_BC);
        int date = c.getColumnIndex(DATE_BC);
        int code_admin = c.getColumnIndex(CODE_ADMIN);
        int code_veh = c.getColumnIndex(CODE_VEHICULE);
        int code_agent = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date)),c.getInt(code_admin),c.getInt(code_veh),c.getInt(code_agent),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de chargement par le code de l'admin et la date */
    public static ArrayList<bon_chargement> selectByAdmin(SQLiteDatabase db, int code_admin, Date date_bc) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_ADMIN +"=? AND "+ DATE_BC +"='?' ;" , new String[]{String.valueOf(code_admin),d.format(date_bc)});
        int code = c.getColumnIndex(CODE_BC);
        int date = c.getColumnIndex(DATE_BC);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_agent = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date)),c.getInt(code_ad),c.getInt(code_vehicule),c.getInt(code_agent),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de chargement par le secteur et la date */
    public static ArrayList<bon_chargement> selectBySecteur(SQLiteDatabase db, int sec, Date date_bc) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + SEC +"=? AND "+ DATE_BC +"='?' ;" , new String[]{String.valueOf(sec),d.format(date_bc)});
        int code = c.getColumnIndex(CODE_BC);
        int date = c.getColumnIndex(DATE_BC);
        int code_admin = c.getColumnIndex(CODE_ADMIN);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_agent = c.getColumnIndex(CODE_AG);
        int secteur = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date)),c.getInt(code_admin),c.getInt(code_vehicule),c.getInt(code_agent),c.getInt(secteur));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de chargement par la date seulement */
    public static ArrayList<bon_chargement> selectByDate(SQLiteDatabase db,Date date_bc) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where "+ DATE_BC +"='?' ;" , new String[]{d.format(date_bc)});
        int code = c.getColumnIndex(CODE_BC);
        int date = c.getColumnIndex(DATE_BC);
        int code_admin = c.getColumnIndex(CODE_ADMIN);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_agent = c.getColumnIndex(CODE_AG);
        int secteur = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date)),c.getInt(code_admin),c.getInt(code_vehicule),c.getInt(code_agent),c.getInt(secteur));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de chargement par le secteur seulement */
    public static ArrayList<bon_chargement> selectBySecteurOnly(SQLiteDatabase db, int sec) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + SEC +"=? ;" , new String[]{String.valueOf(sec)});
        int code = c.getColumnIndex(CODE_BC);
        int date = c.getColumnIndex(DATE_BC);
        int code_admin = c.getColumnIndex(CODE_ADMIN);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_agent = c.getColumnIndex(CODE_AG);
        int secteur = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date)),c.getInt(code_admin),c.getInt(code_vehicule),c.getInt(code_agent),c.getInt(secteur));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de chargement par le code de l'admin seulement */
    public static ArrayList<bon_chargement> selectByAdminOnly(SQLiteDatabase db, int code_admin) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_ADMIN +"=? ;" , new String[]{String.valueOf(code_admin)});
        int code = c.getColumnIndex(CODE_BC);
        int date = c.getColumnIndex(DATE_BC);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_agent = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date)),c.getInt(code_ad),c.getInt(code_vehicule),c.getInt(code_agent),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de chargement par le code de l'agent seulement */
    public static ArrayList<bon_chargement> selectByAgentOnly(SQLiteDatabase db, int code_ag) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"=? ;" , new String[]{String.valueOf(code_ag)});
        int code = c.getColumnIndex(CODE_BC);
        int date = c.getColumnIndex(DATE_BC);
        int code_admin = c.getColumnIndex(CODE_ADMIN);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_agent = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date)),c.getInt(code_admin),c.getInt(code_vehicule),c.getInt(code_agent),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de chargement par le code du véhicule seulement */
    public static ArrayList<bon_chargement> selectByVehiculeOnly(SQLiteDatabase db, int code_vehicule) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_VEHICULE +"=? ;" , new String[]{String.valueOf(code_vehicule)});
        int code = c.getColumnIndex(CODE_BC);
        int date = c.getColumnIndex(DATE_BC);
        int code_admin = c.getColumnIndex(CODE_ADMIN);
        int code_veh = c.getColumnIndex(CODE_VEHICULE);
        int code_agent = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_chargement> list = new ArrayList<bon_chargement>();
        if (c.moveToFirst()) {
            do {  try{ bon_chargement cl = new bon_chargement(c.getInt(code),d.parse(c.getString(date)),c.getInt(code_admin),c.getInt(code_veh),c.getInt(code_agent),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }










}
