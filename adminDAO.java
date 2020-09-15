package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class adminDAO extends DAOBase {
    public static final String TABLE_NAME = "admin";
    public static final String CODE_ADMIN = "code_admin";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String DATE_NAIS = "date_nais";
    public static final String TELEPHONE = "telephone";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String MDP = "mdp";
    public static final String TEMP = "temp";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_ADMIN + " int(11) PRIMARY KEY AUTO_INCREMENT, " + NOM + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + PRENOM + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + DATE_NAIS + " date NOT NULL, " + TELEPHONE + " varchar(10) COLLATE utf8mb4_bin NOT NULL, " + EMAIL + " varchar(40) COLLATE utf8mb4_bin NOT NULL UNIQUE, " + USERNAME + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + MDP + " varchar(255) COLLATE utf8mb4_bin NOT NULL," + TEMP + " int(11) NOT NULL) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public adminDAO(Context pContext) {
        super(pContext);}


    /* Rechercher un admin par son code */
    public static ArrayList<admin> select(SQLiteDatabase db, int code_admin) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_ADMIN +"=? ;" , new String[]{String.valueOf(code_admin)});
        int code = c.getColumnIndex(CODE_ADMIN);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int email = c.getColumnIndex(EMAIL);
        int username = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        int temp = c.getColumnIndex(TEMP);
        ArrayList<admin> list = new ArrayList<admin>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ admin cl = new admin(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(email),c.getString(username),c.getString(mdp),c.getInt(temp));
                       list.add(cl); }
                  catch (ParseException e) {
                  e.printStackTrace(); }
               }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher un admin par son nom prénom */
    public static ArrayList<admin> select(SQLiteDatabase db, String search) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where CONCAT(" + NOM + ",' ',"+ PRENOM +")=? OR CONCAT("+ PRENOM +",' '," + NOM + ")=? ;" , new String[]{search,search});
        int code = c.getColumnIndex(CODE_ADMIN);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int email = c.getColumnIndex(EMAIL);
        int username = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        int temp = c.getColumnIndex(TEMP);
        ArrayList<admin> list = new ArrayList<admin>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ admin cl = new admin(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(email),c.getString(username),c.getString(mdp),c.getInt(temp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }

}
