package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class pointeurDAO extends DAOBase {
    public static final String TABLE_NAME = "pointeur";
    public static final String CODE_PT = "code_pt";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String DATE_NAIS = "date_nais";
    public static final String TELEPHONE = "telephone";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String MDP = "mdp";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_PT + " int(11) PRIMARY KEY AUTO_INCREMENT, " + NOM + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + PRENOM + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + DATE_NAIS + " date NOT NULL, " + TELEPHONE + " varchar(10) COLLATE utf8mb4_bin NOT NULL, " + EMAIL + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + USERNAME + " varchar(40) COLLATE utf8mb4_bin NOT NULL UNIQUE, " + MDP + " varchar(255) COLLATE utf8mb4_bin NOT NULL) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public pointeurDAO(Context pContext) {
        super(pContext);}



    /* Rechercher un pointeur par son code */
    public static ArrayList<pointeur> selectByCode(SQLiteDatabase db, int code_pointeur) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_PT +"=? ;" , new String[]{String.valueOf(code_pointeur)});
        int code = c.getColumnIndex(CODE_PT);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int email = c.getColumnIndex(EMAIL);
        int username = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        ArrayList<pointeur> list = new ArrayList<pointeur>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ pointeur cl = new pointeur(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(email),c.getString(username),c.getString(mdp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un pointeur par son secteur */
    public static ArrayList<pointeur> selectBySecteur(SQLiteDatabase db, int code_secteur) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_PT +" IN (SELECT "+CODE_PT+" from "+secteurDAO.TABLE_NAME+" where "+secteurDAO.CODE_SEC+"=?) ;" , new String[]{String.valueOf(code_secteur)});
        int code = c.getColumnIndex(CODE_PT);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int email = c.getColumnIndex(EMAIL);
        int username = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        ArrayList<pointeur> list = new ArrayList<pointeur>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ pointeur cl = new pointeur(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(email),c.getString(username),c.getString(mdp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un pointeur par son nom/prénom */
    public static ArrayList<pointeur> selectBySearch(SQLiteDatabase db, String search) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where CONCAT(nom,' ',prenom)=? OR CONCAT(prenom,' ',nom)=? ;" , new String[]{search,search});
        int code = c.getColumnIndex(CODE_PT);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int email = c.getColumnIndex(EMAIL);
        int usernamee = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        ArrayList<pointeur> list = new ArrayList<pointeur>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ pointeur cl = new pointeur(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(email),c.getString(usernamee),c.getString(mdp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un pointeur par son username */
    public static ArrayList<pointeur> selectByUsername(SQLiteDatabase db, String username) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_PT +"=? ;" , new String[]{username});
        int code = c.getColumnIndex(CODE_PT);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int email = c.getColumnIndex(EMAIL);
        int usernamee = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        ArrayList<pointeur> list = new ArrayList<pointeur>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ pointeur cl = new pointeur(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(email),c.getString(usernamee),c.getString(mdp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Vérifier si un username existe dans la base de données */
    public static boolean usernameExistant(SQLiteDatabase db, String username) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + USERNAME +"='?' ;" , new String[]{username});
        if (c.moveToFirst()) { return true;}
        c.close();
        return false; }



    /* Récupérer le mot de passe d'un pointeur */
    public static String selectMdp(SQLiteDatabase db,String username) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + USERNAME + "='?' ;", new String[]{username});
        int mdp = c.getColumnIndex(MDP);
        String s=new String();
        if (c.moveToFirst()) {  s=c.getString(mdp); }
        c.close();
        return s;
    }



    /* Modifier le mot de passe d'un pointeur */
    public static void modifierMdp(SQLiteDatabase db,int code_pointeur,String mdp) {
        db.execSQL("update " + TABLE_NAME + " set " + MDP + "='" + mdp + "' where " + CODE_PT + "="+ String.valueOf(code_pointeur) +" ;");
    }


    /* Modifier le numéro de téléphone d'un pointeur */
    public static void modifierTelephone(SQLiteDatabase db,int code_pointeur,String telephone) {
        db.execSQL("update " + TABLE_NAME + " set " + TELEPHONE + "='" + telephone + "' where " + CODE_PT + "="+ String.valueOf(code_pointeur) +" ;");
    }
}
