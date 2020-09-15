package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class agentDAO extends DAOBase {
    public static final String TABLE_NAME = "agent";
    public static final String CODE_AGENT = "code_agent";
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String DATE_NAIS = "date_nais";
    public static final String TELEPHONE = "telephone";
    public static final String TYPE_AGENT = "type_agent";
    public static final String CODE_SEC = "code_sec";
    public static final String CODE_VEHICULE = "code_vehicule";
    public static final String USERNAME = "username";
    public static final String MDP = "mdp";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_AGENT + " int(11) PRIMARY KEY AUTO_INCREMENT, " + NOM + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + PRENOM + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + DATE_NAIS + " date NOT NULL, " + TELEPHONE + " varchar(10) COLLATE utf8mb4_bin NOT NULL, " + TYPE_AGENT + " char(1) COLLATE utf8mb4_bin NOT NULL, " + CODE_SEC + " int(11) DEFAULT NULL, " + CODE_VEHICULE + " int(11) DEFAULT NULL, " + USERNAME + " varchar(40) COLLATE utf8mb4_bin NOT NULL UNIQUE, " + MDP + " varchar(255) COLLATE utf8mb4_bin NOT NULL, FOREIGN KEY " + CODE_SEC + " REFERENCES secteur, FOREIGN KEY " + CODE_VEHICULE + " REFERENCES vehicule) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public agentDAO(Context pContext) {
        super(pContext);}




    /* Rechercher un agent par son code */
    public static ArrayList<agent> selectByCode(SQLiteDatabase db, int code_agent) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AGENT +"=? ;" , new String[]{String.valueOf(code_agent)});
        int code = c.getColumnIndex(CODE_AGENT);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int type_agent = c.getColumnIndex(TYPE_AGENT);
        int code_sec = c.getColumnIndex(CODE_SEC);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int username = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        ArrayList<agent> list = new ArrayList<agent>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ agent cl = new agent(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(type_agent).charAt(0),c.getInt(code_sec),c.getInt(code_vehicule),c.getString(username),c.getString(mdp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un agent par son nom/prénom */
    public static ArrayList<agent> selectBySearch(SQLiteDatabase db, String search) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where CONCAT(nom,' ',prenom)=? OR CONCAT(prenom,' ',nom)=? ;" , new String[]{search,search});
        int code = c.getColumnIndex(CODE_AGENT);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int type_agent = c.getColumnIndex(TYPE_AGENT);
        int code_sec = c.getColumnIndex(CODE_SEC);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int username = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        ArrayList<agent> list = new ArrayList<agent>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ agent cl = new agent(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(type_agent).charAt(0),c.getInt(code_sec),c.getInt(code_vehicule),c.getString(username),c.getString(mdp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un agent par son username */
    public static ArrayList<agent> selectByUsername(SQLiteDatabase db, String username) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + USERNAME +"=? ;" , new String[]{username});
        int code = c.getColumnIndex(CODE_AGENT);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int type_agent = c.getColumnIndex(TYPE_AGENT);
        int code_sec = c.getColumnIndex(CODE_SEC);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int usernamee = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        ArrayList<agent> list = new ArrayList<agent>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ agent cl = new agent(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(type_agent).charAt(0),c.getInt(code_sec),c.getInt(code_vehicule),c.getString(usernamee),c.getString(mdp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un agent par son secteur */
    public static ArrayList<agent> selectBySecteur(SQLiteDatabase db, int code_secteur) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_SEC +"=? ;" , new String[]{String.valueOf(code_secteur)});
        int code = c.getColumnIndex(CODE_AGENT);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int type_agent = c.getColumnIndex(TYPE_AGENT);
        int code_sec = c.getColumnIndex(CODE_SEC);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int username = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        ArrayList<agent> list = new ArrayList<agent>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ agent cl = new agent(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(type_agent).charAt(0),c.getInt(code_sec),c.getInt(code_vehicule),c.getString(username),c.getString(mdp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un agent par son vehicule */
    public static ArrayList<agent> selectByVehicule(SQLiteDatabase db, int code_vehicule) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_VEHICULE +"=? ;" , new String[]{String.valueOf(code_vehicule)});
        int code = c.getColumnIndex(CODE_AGENT);
        int nom = c.getColumnIndex(NOM);
        int prenom = c.getColumnIndex(PRENOM);
        int date_nais = c.getColumnIndex(DATE_NAIS);
        int telephone = c.getColumnIndex(TELEPHONE);
        int type_agent = c.getColumnIndex(TYPE_AGENT);
        int code_sec = c.getColumnIndex(CODE_SEC);
        int code_veh = c.getColumnIndex(CODE_VEHICULE);
        int username = c.getColumnIndex(USERNAME);
        int mdp=c.getColumnIndex(MDP);
        ArrayList<agent> list = new ArrayList<agent>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ agent cl = new agent(c.getInt(code),c.getString(nom),c.getString(prenom),d.parse(c.getString(date_nais)),c.getString(telephone),c.getString(type_agent).charAt(0),c.getInt(code_sec),c.getInt(code_veh),c.getString(username),c.getString(mdp));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher le métier d'un personnel (admin, pointeur, vendeur ou chauffeur) par son code */
    public static String selectFonction(SQLiteDatabase db, int code) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AGENT + "=? ;", new String[]{String.valueOf(code)});
        int type = c.getColumnIndex(TYPE_AGENT);
        if (c.moveToFirst()) { if (c.getString(type).charAt(0)=='v') { return "vendeur";}
                                  else if(c.getString(type).charAt(0)=='c') { return "chauffeur";}   }
        c.close();
        c = db.rawQuery("select * from " + adminDAO.TABLE_NAME + " where " + adminDAO.CODE_ADMIN + "=? ;", new String[]{String.valueOf(code)});
        if (c.moveToFirst()) { return "admin";}
        c.close();
        c = db.rawQuery("select * from " + pointeurDAO.TABLE_NAME + " where " + pointeurDAO.CODE_PT + "=? ;", new String[]{String.valueOf(code)});
        if (c.moveToFirst()) { return "pointeur";}
        c.close();
        return "none";
    }




    /* Vérifier si un username existe dans la base de données */
    public static boolean usernameExistant(SQLiteDatabase db,String username) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + USERNAME +"='?' ;" , new String[]{username});
        if (c.moveToFirst()) { return true;}
        c.close();
        return false; }



    /* Récupérer le type d'un agent (vendeur ou chauffeur) */
    public static char selectType(SQLiteDatabase db,int code_agent) {
        Cursor c=db.rawQuery("select " + TYPE_AGENT + " from "+ TABLE_NAME +" where "+ CODE_AGENT +"=?;" , new String[]{String.valueOf(code_agent)});
        int type = c.getColumnIndex(TYPE_AGENT);
        char t ='x';
        if (c.moveToFirst()) {String s=c.getString(type); t=s.charAt(0);}
        c.close();
        return t;
    }


    /* Récupérer le mot de passe d'un agent */
    public static String selectMdp(SQLiteDatabase db,String username) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + USERNAME + "='?' ;", new String[]{username});
        int mdp = c.getColumnIndex(MDP);
        String s=new String();
        if (c.moveToFirst()) {  s=c.getString(mdp); }
        c.close();
        return s;
    }



    /* Modifier le mot de passe d'un agent */
    public static void modifierMdp(SQLiteDatabase db,int code_agent,String mdp) {
        db.execSQL("update " + TABLE_NAME + " set " + MDP + "='" + mdp + "' where " + CODE_AGENT + "="+ String.valueOf(code_agent) +" ;");
    }


    /* Modifier le numéro de téléphone d'un agent */
    public static void modifierTelephone(SQLiteDatabase db,int code_agent,String telephone) {
        db.execSQL("update " + TABLE_NAME + " set " + TELEPHONE + "='" + telephone + "' where " + CODE_AGENT + "="+ String.valueOf(code_agent) +" ;");
    }



    /* Retourner le pourcentage de vente d'un vendeur */
    public static double PourcentageVente(SQLiteDatabase db,int code_agent) {
        ArrayList<produit> list = produitDAO.selectByAgent(db,code_agent);
        int chg=0,vnd=0;
        for (produit p : list) {
                    chg= chg + qte_chgDAO.charge(db,p.getCode_pr(),code_agent);
                    vnd= vnd + produitDAO.vendu(db,p.getCode_pr(),code_agent); }
        double p=0;
        if (chg != 0) { p = (vnd*100)/chg ;}
        return p;
    }




}
