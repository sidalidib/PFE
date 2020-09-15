package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class factureDAO extends DAOBase {
    public static final String TABLE_NAME = "facture";
    public static final String CODE_FACT = "code_fact";
    public static final String DATE_FACT = "date_fact";
    public static final String MODE_PAIEMENT = "mode_paiement";
    public static final String CODE_CL = "code_cl";
    public static final String CODE_AG = "code_ag";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE_FACT + " int(11) PRIMARY KEY AUTO_INCREMENT, " + DATE_FACT + " date NOT NULL, " + MODE_PAIEMENT + " varchar(40) COLLATE utf8mb4_bin NOT NULL, " + CODE_CL + " int(11) NOT NULL, " + CODE_AG + " int(11) NOT NULL, FOREIGN KEY " + CODE_CL + " REFERENCES client) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public factureDAO(Context pContext) {
        super(pContext);}


    /* Effectuer une vente et créer la facture */
    public static void vendre(SQLiteDatabase db, int code_facture, Date date_facture,String mode,int code_client,int code_agent,ligne_facture.prod_qte[] lignes) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        db.execSQL("insert into "+ TABLE_NAME +" values("+ String.valueOf(code_facture) +",'"+ d.format(date_facture) +"','"+ mode +"',"+ String.valueOf(code_client) +","+ String.valueOf(code_agent) +");");
        int n=lignes.length;
        for (int i=0;i<n;i++) {
            ligne_factureDAO.ajouterAuPalier(db,code_facture,lignes[i].getCode_pr(),lignes[i].getQte());
        }
    }


    /* Annuler une vente et supprimer la facture */
    public static int annulerVente(SQLiteDatabase db,Date date_facture,int code_client,int code_agent){
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select "+ CODE_FACT +" from "+ TABLE_NAME +" where "+ CODE_CL +"=? AND "+ CODE_AG +"=? AND "+ DATE_FACT +"='?' ;" , new String[]{String.valueOf(code_client),String.valueOf(code_agent),d.format(date_facture)});
        int f=-1,code_f=c.getColumnIndex(CODE_FACT);
        if (c.moveToFirst()) { f = c.getInt(code_f);}
        if (f != -1) {
            ligne_factureDAO.supprimerFacture(db,f);
            db.execSQL("delete from "+ TABLE_NAME +" where "+ CODE_FACT +"="+ f +";");
        }
        c.close();
        return f;
    }


    /* Modifier une vente et sa facture en cas d'erreur/mal entendu */
    public static int modifierVente(SQLiteDatabase db,Date date_facture,int code_client,int code_agent,ligne_facture.prod_qte[] lignes) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select " + CODE_FACT + " from " + TABLE_NAME + " where " + CODE_CL + "=? AND " + CODE_AG + "=? AND " + DATE_FACT + "='?' ;", new String[]{String.valueOf(code_client), String.valueOf(code_agent), d.format(date_facture)});
        int f = -1, code_f = c.getColumnIndex(CODE_FACT);
        if (c.moveToFirst()) { f = c.getInt(code_f); }
        if (f != -1) {
            ligne_factureDAO.supprimerFacture(db, f);
            int n=lignes.length;
            for (int i=0;i<n;i++) {
                ligne_factureDAO.ajouterAuPalier(db,f,lignes[i].getCode_pr(),lignes[i].getQte());
            } }
        c.close();
        return f;
    }



    /* Modifier le mode de paiement d'une facture */
    public static void modifierMode(SQLiteDatabase db,int code_fact,String mode) {
        db.execSQL("update "+TABLE_NAME+" set "+MODE_PAIEMENT+"='"+mode+"' where "+CODE_FACT+"="+String.valueOf(code_fact)+";");
    }


    /* calculer le montant d'une facture */
    public static float montantFacture(SQLiteDatabase db,int code_facture) {
        ArrayList<ligne_facture> list = ligne_factureDAO.select(db,code_facture);
        float montant=0;
        for(ligne_facture ligne : list) {
             montant = montant + ligne_factureDAO.montantLigne(db,code_facture,ligne.getCode_pr());
        }
        return montant;
    }


    /* Rechercher une facture par son code */
    public static ArrayList<facture> selectByCode(SQLiteDatabase db, int code_fact) {
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_FACT +"=? ;" , new String[]{String.valueOf(code_fact)});
        int code = c.getColumnIndex(CODE_FACT);
        int date = c.getColumnIndex(DATE_FACT);
        int mode = c.getColumnIndex(MODE_PAIEMENT);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AG);
        ArrayList<facture> list = new ArrayList<facture>();
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        if (c.moveToFirst()) {
            do {  try{ facture cl = new facture(c.getInt(code),d.parse(c.getString(date)),c.getString(mode),c.getInt(code_cl),c.getInt(code_ag));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher des factures par date pour un vendeur */
    public static ArrayList<facture> selectByDate(SQLiteDatabase db, int code_agent,Date date_facture) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"=? AND "+ DATE_FACT +"='?' ;" , new String[]{String.valueOf(code_agent),d.format(date_facture)});
        int code = c.getColumnIndex(CODE_FACT);
        int date = c.getColumnIndex(DATE_FACT);
        int mode = c.getColumnIndex(MODE_PAIEMENT);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AG);
        ArrayList<facture> list = new ArrayList<facture>();
        if (c.moveToFirst()) {
            do {  try{ facture cl = new facture(c.getInt(code),d.parse(c.getString(date)),c.getString(mode),c.getInt(code_cl),c.getInt(code_ag));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher des factures par date et mode de paiement pour un vendeur */
    public static ArrayList<facture> selectByDateAndMode(SQLiteDatabase db, int code_agent,Date date_facture, String mode_paiement) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"=? AND "+ DATE_FACT +"='?' AND "+ MODE_PAIEMENT +"='?' ;" , new String[]{String.valueOf(code_agent),d.format(date_facture),mode_paiement});
        int code = c.getColumnIndex(CODE_FACT);
        int date = c.getColumnIndex(DATE_FACT);
        int mode = c.getColumnIndex(MODE_PAIEMENT);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AG);
        ArrayList<facture> list = new ArrayList<facture>();
        if (c.moveToFirst()) {
            do {  try{ facture cl = new facture(c.getInt(code),d.parse(c.getString(date)),c.getString(mode),c.getInt(code_cl),c.getInt(code_ag));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher des factures par date et client pour un vendeur */
    public static ArrayList<facture> selectByDateAndClient(SQLiteDatabase db, int code_agent,Date date_facture,int code_client) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"=? AND "+ DATE_FACT +"='?' AND "+ CODE_CL +"=? ;" , new String[]{String.valueOf(code_agent),d.format(date_facture),String.valueOf(code_client)});
        int code = c.getColumnIndex(CODE_FACT);
        int date = c.getColumnIndex(DATE_FACT);
        int mode = c.getColumnIndex(MODE_PAIEMENT);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AG);
        ArrayList<facture> list = new ArrayList<facture>();
        if (c.moveToFirst()) {
            do {  try{ facture cl = new facture(c.getInt(code),d.parse(c.getString(date)),c.getString(mode),c.getInt(code_cl),c.getInt(code_ag));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher des factures par mode de paiement pour un vendeur */
    public static ArrayList<facture> selectByMode(SQLiteDatabase db, int code_agent,String mode_paiement) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"=? AND "+ MODE_PAIEMENT +"='?' ;" , new String[]{String.valueOf(code_agent),mode_paiement});
        int code = c.getColumnIndex(CODE_FACT);
        int date = c.getColumnIndex(DATE_FACT);
        int mode = c.getColumnIndex(MODE_PAIEMENT);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AG);
        ArrayList<facture> list = new ArrayList<facture>();
        if (c.moveToFirst()) {
            do {  try{ facture cl = new facture(c.getInt(code),d.parse(c.getString(date)),c.getString(mode),c.getInt(code_cl),c.getInt(code_ag));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Rechercher des factures par client pour un vendeur */
    public static ArrayList<facture> selectByClient(SQLiteDatabase db, int code_agent,int code_client) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"=? AND "+ CODE_CL +"=? ;" , new String[]{String.valueOf(code_agent),String.valueOf(code_client)});
        int code = c.getColumnIndex(CODE_FACT);
        int date = c.getColumnIndex(DATE_FACT);
        int mode = c.getColumnIndex(MODE_PAIEMENT);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AG);
        ArrayList<facture> list = new ArrayList<facture>();
        if (c.moveToFirst()) {
            do {  try{ facture cl = new facture(c.getInt(code),d.parse(c.getString(date)),c.getString(mode),c.getInt(code_cl),c.getInt(code_ag));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Rechercher toutes les factures de tous les jours pour un vendeur */
    public static ArrayList<facture> selectAllTime(SQLiteDatabase db, int code_agent) {
        DateFormat d = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where " + CODE_AG +"=? ;" , new String[]{String.valueOf(code_agent)});
        int code = c.getColumnIndex(CODE_FACT);
        int date = c.getColumnIndex(DATE_FACT);
        int mode = c.getColumnIndex(MODE_PAIEMENT);
        int code_cl = c.getColumnIndex(CODE_CL);
        int code_ag = c.getColumnIndex(CODE_AG);
        ArrayList<facture> list = new ArrayList<facture>();
        if (c.moveToFirst()) {
            do {  try{ facture cl = new facture(c.getInt(code),d.parse(c.getString(date)),c.getString(mode),c.getInt(code_cl),c.getInt(code_ag));
                list.add(cl); }
            catch (ParseException e) {
                e.printStackTrace(); }
            }
            while(c.moveToNext());}
        c.close();
        return list;
    }




}
