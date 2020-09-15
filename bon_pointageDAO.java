package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class bon_pointageDAO extends DAOBase {
    public static final String TABLE_NAME = "bon_pointage";
    public static final String CODE_BP = "code_bp";
    public static final String DATE_PT = "date_pt";
    public static final String TYPE = "type";
    public static final String CODE_PT = "code_pt";
    public static final String CODE_VEHICULE = "code_vehicule";
    public static final String CODE_AG = "code_ag";
    public static final String SEC = "sec";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_BP + " int(11) PRIMARY KEY AUTO_INCREMENT, " + DATE_PT + " date NOT NULL, " + TYPE + " char(1) COLLATE utf8mb4_bin NOT NULL, " + CODE_PT + " int(11) NOT NULL, " + CODE_VEHICULE + " int(11) NOT NULL, " + CODE_AG + " int(11) NOT NULL, " + SEC + " int(11) DEFAULT NULL, FOREIGN KEY " + CODE_PT + " REFERENCES pointeur, FOREIGN KEY " + CODE_VEHICULE + " REFERENCES vehicule, FOREIGN KEY " + CODE_AG + " REFERENCES agent, FOREIGN KEY " + SEC + " REFERENCES secteur) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public bon_pointageDAO(Context pContext) {
        super(pContext);}


    /* Effectuer un pointage et créer le bon de pointage */
    public static void pointer(SQLiteDatabase db, int code_bp, Date date_pt, char type, int code_pointeur, int code_vehicule,int code_agent,int sec, ligne_facture.prod_qte[] lignes) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        db.execSQL("insert into "+ TABLE_NAME +" values("+ String.valueOf(code_bp) +",'"+ d.format(date_pt) +"','"+ type +"',"+ String.valueOf(code_pointeur) +","+ String.valueOf(code_vehicule) +","+ String.valueOf(code_agent) +","+ String.valueOf(sec) +");");
        int n=lignes.length;
        for (int i=0;i<n;i++) {
            qte_ptgDAO.ajouterLigne(db,code_bp,lignes[i].getCode_pr(),lignes[i].getQte());
        }
    }


    /* Annuler un pointage et supprimer le bon de pointage */
    public static int annulerPointage(SQLiteDatabase db,Date date_pt,int code_pointeur,int code_agent){
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select "+ CODE_BP +" from "+ TABLE_NAME +" where "+ CODE_PT +"=? AND "+ CODE_AG +"=? AND "+ DATE_PT +"='?' ;" , new String[]{String.valueOf(code_pointeur),String.valueOf(code_agent),d.format(date_pt)});
        int p=-1,code_p=c.getColumnIndex(CODE_BP);
        if (c.moveToFirst()) { p = c.getInt(code_p);}
        if (p != -1) {
            qte_ptgDAO.supprimerBonDePointage(db,p);
            db.execSQL("delete from "+ TABLE_NAME +" where "+ CODE_BP +"="+ p +";");
        }
        c.close();
        return p;
    }


    /* Modifier un pointage et le bon de pointage */
    public static int modifierPointage(SQLiteDatabase db,Date date_pt,int code_pointeur,int code_agent,ligne_facture.prod_qte[] lignes){
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select "+ CODE_BP +" from "+ TABLE_NAME +" where "+ CODE_PT +"=? AND "+ CODE_AG +"=? AND "+ DATE_PT +"='?' ;" , new String[]{String.valueOf(code_pointeur),String.valueOf(code_agent),d.format(date_pt)});
        int p=-1,code_p=c.getColumnIndex(CODE_BP);
        if (c.moveToFirst()) { p = c.getInt(code_p);}
        if (p != -1) {
            qte_ptgDAO.supprimerBonDePointage(db,p);
            int n=lignes.length;
            for (int i=0;i<n;i++) {
                qte_ptgDAO.ajouterLigne(db,p,lignes[i].getCode_pr(),lignes[i].getQte());
            }   }
        c.close();
        return p;
    }



    public static char selectType(SQLiteDatabase db,int code_bp) {
        Cursor c=db.rawQuery("select " + TYPE + " from "+ TABLE_NAME +" where "+ CODE_BP +"=?;" , new String[]{String.valueOf(code_bp)});
        int type = c.getColumnIndex(TYPE);
        char t ='x';
        if (c.moveToFirst()) {String s=c.getString(type); t=s.charAt(0);}
        c.close();
        return t;
    }

    public static int selectAgent(SQLiteDatabase db,int code_bp) {
        Cursor c=db.rawQuery("select " + CODE_AG + " from "+ TABLE_NAME +" where "+ CODE_BP +"=?;" , new String[]{String.valueOf(code_bp)});
        int code = c.getColumnIndex(CODE_AG);
        int code_agent = 0;
        if (c.moveToFirst()) { code_agent = c.getInt(code);}
        c.close();
        return code_agent;
    }


    /* Vérifier s'il y a un écart dans un bon de pointage */
    public static boolean ecart(SQLiteDatabase db,int code_bp) {
        ArrayList<qte_ptg> list = qte_ptgDAO.select(db,code_bp);
        char type=selectType(db,code_bp);

        if (type=='s') {
            for (qte_ptg q : list) {
                if( qte_ptgDAO.ecartSortie(db,q.getCode_pr(),selectAgent(db,code_bp)) != 0)   return true;
            }
        }

        else if (type=='r') {
            for (qte_ptg q : list) {
                if( qte_ptgDAO.ecartRetour(db,q.getCode_pr(),selectAgent(db,code_bp)) != 0)   return true;
            }
        }

        return false;
    }


    /* Rechercher un bon de pointage par son code */
    public static ArrayList<bon_pointage> select(SQLiteDatabase db, int code_bp) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_BP +"=? ;" , new String[]{String.valueOf(code_bp)});
        int code = c.getColumnIndex(CODE_BP);
        int date_pt = c.getColumnIndex(DATE_PT);
        int type = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date_pt)),c.getString(type).charAt(0),c.getInt(code_pt),c.getInt(code_vehicule),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de pointage par la date */
    public static ArrayList<bon_pointage> selectByDate(SQLiteDatabase db,Date date_pt) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where "+ DATE_PT +"='?' ;" , new String[]{d.format(date_pt)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_vehicule),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de pointage par le type seulement */
    public static ArrayList<bon_pointage> selectByTypeOnly(SQLiteDatabase db,char type) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + TYPE +"='?' ;" , new String[]{String.valueOf(type)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_vehicule),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de pointage par le pointeur seulement */
    public static ArrayList<bon_pointage> selectByPointeurOnly(SQLiteDatabase db,int code_pointeur) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_PT +"='?' ;" , new String[]{String.valueOf(code_pointeur)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_vehicule),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de pointage par le vehicule seulement */
    public static ArrayList<bon_pointage> selectByVehiculeOnly(SQLiteDatabase db,int code_vehicule) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_VEHICULE +"='?' ;" , new String[]{String.valueOf(code_vehicule)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_veh = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_veh),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de pointage par l'agent seulement */
    public static ArrayList<bon_pointage> selectByAgentOnly(SQLiteDatabase db,int code_agent) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"='?' ;" , new String[]{String.valueOf(code_agent)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_veh = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_veh),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher un bon de pointage par l'agent seulement */
    public static ArrayList<bon_pointage> selectBySecteurOnly(SQLiteDatabase db,int secteur) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + SEC +"='?' ;" , new String[]{String.valueOf(secteur)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_veh = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_veh),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher un bon de pointage par le type et la date */
    public static ArrayList<bon_pointage> selectByType(SQLiteDatabase db,char type,Date date_pt) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + TYPE +"='?' AND "+ DATE_PT +"='?' ;" , new String[]{String.valueOf(type),d.format(date_pt)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_vehicule),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de pointage par le pointeur et la date */
    public static ArrayList<bon_pointage> selectByPointeur(SQLiteDatabase db,int code_pointeur,Date date_pt) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_PT +"='?' AND "+ DATE_PT +"='?' ;" , new String[]{String.valueOf(code_pointeur),d.format(date_pt)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_vehicule = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_vehicule),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de pointage par le vehicule et la date */
    public static ArrayList<bon_pointage> selectByVehicule(SQLiteDatabase db,int code_vehicule,Date date_pt) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_VEHICULE +"='?' AND "+ DATE_PT +"='?' ;" , new String[]{String.valueOf(code_vehicule),d.format(date_pt)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_veh = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_veh),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher un bon de pointage par l'agent et la date */
    public static ArrayList<bon_pointage> selectByAgent(SQLiteDatabase db,int code_agent,Date date_pt) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"='?' AND "+ DATE_PT +"='?' ;" , new String[]{String.valueOf(code_agent),d.format(date_pt)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_veh = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_veh),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher un bon de pointage par le secteur et la date */
    public static ArrayList<bon_pointage> selectBySecteur(SQLiteDatabase db,int secteur,Date date_pt) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + SEC +"='?' AND "+ DATE_PT +"='?' ;" , new String[]{String.valueOf(secteur),d.format(date_pt)});
        int code = c.getColumnIndex(CODE_BP);
        int date = c.getColumnIndex(DATE_PT);
        int type_bp = c.getColumnIndex(TYPE);
        int code_pt = c.getColumnIndex(CODE_PT);
        int code_veh = c.getColumnIndex(CODE_VEHICULE);
        int code_ag = c.getColumnIndex(CODE_AG);
        int sec = c.getColumnIndex(SEC);
        ArrayList<bon_pointage> list = new ArrayList<bon_pointage>();
        if (c.moveToFirst()) {
            do {  try{ bon_pointage cl = new bon_pointage(c.getInt(code),d.parse(c.getString(date)),c.getString(type_bp).charAt(0),c.getInt(code_pt),c.getInt(code_veh),c.getInt(code_ag),c.getInt(sec));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }




}
