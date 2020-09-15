package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class vuDAO extends DAOBase {
    public static final String TABLE_NAME = "vu";
    public static final String CODE = "code";
    public static final String CODE_ADMIN = "code_admin";
    public static final String VU = "vu";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE + " int(11) NOT NULL, " + CODE_ADMIN + " int(11) NOT NULL, " + VU + " int(11) NOT NULL, FOREIGN KEY " + CODE_ADMIN + " REFERENCES admin, FOREIGN KEY " + CODE + " REFERENCES notification) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public vuDAO(Context pContext) {
        super(pContext);}



    /* Sélectionner des vus par le code */
    public static ArrayList<vu> selectByCode(SQLiteDatabase db, int code) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE +"=?;" , new String[]{String.valueOf(code)});
        int cod = c.getColumnIndex(CODE);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int v = c.getColumnIndex(VU);
        ArrayList<vu> list = new ArrayList<vu>();
        if (c.moveToFirst()) {
            do { vu ql = new vu(c.getInt(cod),c.getInt(code_ad),c.getInt(v));
                list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner des vus par l'admin */
    public static ArrayList<vu> selectByAdmin(SQLiteDatabase db, int code_admin) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_ADMIN +"=?;" , new String[]{String.valueOf(code_admin)});
        int cod = c.getColumnIndex(CODE);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int v = c.getColumnIndex(VU);
        ArrayList<vu> list = new ArrayList<vu>();
        if (c.moveToFirst()) {
            do { vu ql = new vu(c.getInt(cod),c.getInt(code_ad),c.getInt(v));
                list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner des vus par la valeur vu */
    public static ArrayList<vu> selectByVu(SQLiteDatabase db, int vu) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ VU +"=?;" , new String[]{String.valueOf(vu)});
        int cod = c.getColumnIndex(CODE);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int v = c.getColumnIndex(VU);
        ArrayList<vu> list = new ArrayList<vu>();
        if (c.moveToFirst()) {
            do { vu ql = new vu(c.getInt(cod),c.getInt(code_ad),c.getInt(v));
                list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner un vu par le code et l'admin */
    public static ArrayList<vu> selectByCodeAndAdmin(SQLiteDatabase db, int code,int code_admin) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE +"=? AND "+CODE_ADMIN+"=? ;" , new String[]{String.valueOf(code),String.valueOf(code_admin)});
        int cod = c.getColumnIndex(CODE);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int v = c.getColumnIndex(VU);
        ArrayList<vu> list = new ArrayList<vu>();
        if (c.moveToFirst()) {
            do { vu ql = new vu(c.getInt(cod),c.getInt(code_ad),c.getInt(v));
                list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner un vu par la valeur vu et l'admin */
    public static ArrayList<vu> selectByAdminAndVu(SQLiteDatabase db,int code_admin,int vu) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ VU +"=? AND "+CODE_ADMIN+"=? ;" , new String[]{String.valueOf(vu),String.valueOf(code_admin)});
        int cod = c.getColumnIndex(CODE);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int v = c.getColumnIndex(VU);
        ArrayList<vu> list = new ArrayList<vu>();
        if (c.moveToFirst()) {
            do { vu ql = new vu(c.getInt(cod),c.getInt(code_ad),c.getInt(v));
                list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner un vu par le code et la valeur vu */
    public static ArrayList<vu> selectByCodeAndVu(SQLiteDatabase db,int code,int vu) {
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ VU +"=? AND "+CODE+"=? ;" , new String[]{String.valueOf(vu),String.valueOf(code)});
        int cod = c.getColumnIndex(CODE);
        int code_ad = c.getColumnIndex(CODE_ADMIN);
        int v = c.getColumnIndex(VU);
        ArrayList<vu> list = new ArrayList<vu>();
        if (c.moveToFirst()) {
            do { vu ql = new vu(c.getInt(cod),c.getInt(code_ad),c.getInt(v));
                list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }




    /* insérer un nouveau vu */
    public static void insert(SQLiteDatabase db, int code, int code_admin) {
        db.execSQL("insert into "+ TABLE_NAME +" values("+String.valueOf(code)+","+String.valueOf(code_admin)+",0);");
    }


    /* supprimer des vus selon le code */
    public static void deleteByCode(SQLiteDatabase db,int code) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+CODE+"="+code+";");
    }


    /* supprimer un vu selon le code et l'admin */
    public static void delete(SQLiteDatabase db,int code,int code_admin) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+CODE+"="+code+" AND "+CODE_ADMIN+"="+code_admin+";");
    }


    /* supprimer tous les vus d'un admin */
    public static void deleteByAdmin(SQLiteDatabase db,int code_admin) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+CODE_ADMIN+"="+code_admin+";");
    }


    /* supprimer tous les vus déja vus */
    public static void deleteByVu(SQLiteDatabase db) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+VU+"=1;");
    }


    /* modifier l'attribut vu */
    public static void modifierVu(SQLiteDatabase db,int code,int code_admin,int vu) {
        db.execSQL("update "+ TABLE_NAME +" set "+VU+"="+vu+" where "+CODE+"="+code+" AND "+CODE_ADMIN+"="+code_admin+";");
    }


    /* modifier l'attribut vu */
    public static void modifierVu(SQLiteDatabase db,int code,int vu) {
        db.execSQL("update "+ TABLE_NAME +" set "+VU+"="+vu+" where "+CODE+"="+code+";");
    }


}
