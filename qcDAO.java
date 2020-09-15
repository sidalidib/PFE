package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class qcDAO extends DAOBase {
    public static final String TABLE_NAME = "qc";
    public static final String CODE_QC = "code_qc";
    public static final String CODE_ADMIN = "code_admin";
    public static final String CODE_AGENT = "code_agent";
    public static final String DATE_QC = "date_qc";
    public static final String MONTANT = "montant";
    public static final String CODE_VH = "code_vh";
    public static final String SEC = "sec";



    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_QC + " int(11) PRIMARY KEY AUTO_INCREMENT, " + CODE_ADMIN + " int(11) NOT NULL, " + CODE_AGENT + " int(11) NOT NULL, " + DATE_QC + " date NOT NULL, " + MONTANT + " decimal(11,2) NOT NULL, " + CODE_VH + " int(11) NOT NULL, " + SEC + " int(11) NOT NULL, FOREIGN KEY " + CODE_AGENT + " REFERENCES agent USING BTREE, FOREIGN KEY " + CODE_ADMIN + " REFERENCES admin USING BTREE) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public qcDAO(Context pContext) {
        super(pContext);}



    /* Rechercher un quitus de caisse par son code */
    public static ArrayList<qc> selectByCode(SQLiteDatabase db, int code_qc) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_QC +"=? ;" , new String[]{String.valueOf(code_qc)});
        int code = c.getColumnIndex(CODE_QC);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int code_ag = c.getColumnIndex(CODE_AGENT);
        int date = c.getColumnIndex(DATE_QC);
        int mont = c.getColumnIndex(MONTANT);
        int code_vh = c.getColumnIndex(CODE_VH);
        int sec = c.getColumnIndex(SEC);
        ArrayList<qc> list = new ArrayList<qc>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ qc cl = new qc(c.getInt(code),c.getInt(code_ad),c.getInt(code_ag),d.parse(c.getString(date)),c.getFloat(mont),c.getInt(code_vh),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher un quitus de caisse selon l'agent */
    public static ArrayList<qc> selectByAgent(SQLiteDatabase db, int code_agent) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AGENT +"=? ;" , new String[]{String.valueOf(code_agent)});
        int code = c.getColumnIndex(CODE_QC);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int code_ag = c.getColumnIndex(CODE_AGENT);
        int date = c.getColumnIndex(DATE_QC);
        int mont = c.getColumnIndex(MONTANT);
        int code_vh = c.getColumnIndex(CODE_VH);
        int sec = c.getColumnIndex(SEC);
        ArrayList<qc> list = new ArrayList<qc>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ qc cl = new qc(c.getInt(code),c.getInt(code_ad),c.getInt(code_ag),d.parse(c.getString(date)),c.getFloat(mont),c.getInt(code_vh),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher les quitus de caisses selon le secteur */
    public static ArrayList<qc> selectBySecteur(SQLiteDatabase db, int code_secteur) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + SEC +"=? ;" , new String[]{String.valueOf(code_secteur)});
        int code = c.getColumnIndex(CODE_QC);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int code_ag = c.getColumnIndex(CODE_AGENT);
        int date = c.getColumnIndex(DATE_QC);
        int mont = c.getColumnIndex(MONTANT);
        int code_vh = c.getColumnIndex(CODE_VH);
        int sec = c.getColumnIndex(SEC);
        ArrayList<qc> list = new ArrayList<qc>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ qc cl = new qc(c.getInt(code),c.getInt(code_ad),c.getInt(code_ag),d.parse(c.getString(date)),c.getFloat(mont),c.getInt(code_vh),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher les quitus de caisses selon la date */
    public static ArrayList<qc> selectBySecteur(SQLiteDatabase db, Date date_qc) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + DATE_QC +"='?' ;" , new String[]{d.format(date_qc)});
        int code = c.getColumnIndex(CODE_QC);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int code_ag = c.getColumnIndex(CODE_AGENT);
        int date = c.getColumnIndex(DATE_QC);
        int mont = c.getColumnIndex(MONTANT);
        int code_vh = c.getColumnIndex(CODE_VH);
        int sec = c.getColumnIndex(SEC);
        ArrayList<qc> list = new ArrayList<qc>();
        if (c.moveToFirst()) {
            do {  try{ qc cl = new qc(c.getInt(code),c.getInt(code_ad),c.getInt(code_ag),d.parse(c.getString(date)),c.getFloat(mont),c.getInt(code_vh),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* calculer le montant d'un quitus de caisse pour un vendeur */
    public static float montantQuitus(SQLiteDatabase db,int code_agent) {
        ArrayList<facture> list = factureDAO.selectByDate(db,code_agent,Calendar.getInstance().getTime());
        float montant=0;
        for(facture f : list) {
            montant = montant + factureDAO.montantFacture(db,f.getCode_fact());
        }
        return montant;
    }



    /* calculer l'ecart dans un quitus de caisse pour un vendeur */
    public static float ecartQuitus(SQLiteDatabase db,int code_agent,float versement) {
        float ecart=0;
        ecart = montantQuitus(db,code_agent) - versement;
        return ecart;
    }



    /* calculer la somme des montants de tous les quitus de caisse dans un secteur */
    public static float montantSecteur(SQLiteDatabase db,int code_sec) {
        ArrayList<agent> list = agentDAO.selectBySecteur(db,code_sec);
        float montant=0;
        for(agent a : list) {
            montant = montant + montantQuitus(db,a.getCode_agent());
        }
        return montant;
    }




}
