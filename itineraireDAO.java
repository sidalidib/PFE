package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class itineraireDAO extends DAOBase {
    public static final String TABLE_NAME = "itineraire";
    public static final String JOUR = "jour";
    public static final String CODE_CL = "code_cl";
    public static final String CODE_AGENT = "code_agent";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + JOUR + " varchar(10) NOT NULL CHECK( " + JOUR + " IN ('DIMANCHE','LUNDI','MARDI','MERCREDI','JEUDI') ), " + CODE_CL + " int(11) NOT NULL, " + CODE_AGENT + " int(11) NOT NULL, PRIMARY KEY (" + JOUR + "," + CODE_CL + "), FOREIGN KEY " + CODE_CL + " REFERENCES client, FOREIGN KEY " + CODE_AGENT + " REFERENCES agent USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public itineraireDAO(Context pContext) {
        super(pContext);}


    /* Rechercher les itineraires par client pour un vendeur */
    public static ArrayList<itineraire> selectByClient(SQLiteDatabase db, int code_agent, int code_client) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AGENT +"=? AND "+ CODE_CL +"=? ;" , new String[]{String.valueOf(code_agent),String.valueOf(code_client)});
        int jour = c.getColumnIndex(JOUR);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AGENT);
        ArrayList<itineraire> list = new ArrayList<itineraire>();
        if (c.moveToFirst()) {
            do {   itineraire cl = new itineraire(itineraire.dayOfWeek.valueOf(c.getString(jour)) , c.getInt(code_cl) , c.getInt(code_ag));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher les itineraires par jour pour un vendeur */
    public static ArrayList<itineraire> selectByDay(SQLiteDatabase db, int code_agent, itineraire.dayOfWeek day) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AGENT +"=? AND "+ JOUR +"=? ;" , new String[]{String.valueOf(code_agent),String.valueOf(day)});
        int jour = c.getColumnIndex(JOUR);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AGENT);
        ArrayList<itineraire> list = new ArrayList<itineraire>();
        if (c.moveToFirst()) {
            do {   itineraire cl = new itineraire(itineraire.dayOfWeek.valueOf(c.getString(jour)) , c.getInt(code_cl) , c.getInt(code_ag));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher les itineraires pendant la semaine pour un vendeur */
    public static ArrayList<itineraire> selectAll(SQLiteDatabase db, int code_agent) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AGENT +"=? ;" , new String[]{String.valueOf(code_agent)});
        int jour = c.getColumnIndex(JOUR);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AGENT);
        ArrayList<itineraire> list = new ArrayList<itineraire>();
        if (c.moveToFirst()) {
            do {   itineraire cl = new itineraire(itineraire.dayOfWeek.valueOf(c.getString(jour)) , c.getInt(code_cl) , c.getInt(code_ag));
                list.add(cl); }
            while(c.moveToNext());}
        c.close();
        return list;
    }




    /* Ajouter un nouveau client dans un itineraire pour un vendeur */
    public static void insert(SQLiteDatabase db,int code_agent,int code_client,itineraire.dayOfWeek jour) {
       db.execSQL("insert into "+TABLE_NAME+" values('"+String.valueOf(jour)+"',"+String.valueOf(code_client)+","+String.valueOf(code_agent)+") ;");
    }




}
