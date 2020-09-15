package com.example.salesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class notificationDAO extends DAOBase {
    public static final String TABLE_NAME = "notification";
    public static final String CODE = "code";
    public static final String TYPE = "type";
    public static final String D = "d";
    public static final String CODE_USER = "code_user";
    public static final String CONTENU = "contenu";


    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + CODE + " int(11) PRIMARY KEY AUTO_INCREMENT, " + TYPE + " int(11) NOT NULL, " + D + " datetime NOT NULL, " + CODE_USER + " int(11) NOT NULL, " + CONTENU + " text COLLATE utf8mb4_bin NOT NULL) ENGINE=InnoDB AUTO_INCREMENT=1590705373 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public notificationDAO(Context pContext) {
        super(pContext);}

    /* Sélectionner une notification par son code */
    public static ArrayList<notification> selectByCode(SQLiteDatabase db, int code_notification) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE +"=?;" , new String[]{String.valueOf(code_notification)});
        int code = c.getColumnIndex(CODE);
        int type = c.getColumnIndex(TYPE);
        int d = c.getColumnIndex(D);
        int code_user = c.getColumnIndex(CODE_USER);
        int contenu = c.getColumnIndex(CONTENU);
        ArrayList<notification> list = new ArrayList<notification>();
        if (c.moveToFirst()) {
            do { notification ql = new notification(c.getInt(code),c.getInt(type),LocalDateTime.parse(c.getString(d)),c.getInt(code_user),c.getString(contenu));
                 list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner les notifications par type */
    public static ArrayList<notification> selectByType(SQLiteDatabase db, int type_notification) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ TYPE +"=?;" , new String[]{String.valueOf(type_notification)});
        int code = c.getColumnIndex(CODE);
        int type = c.getColumnIndex(TYPE);
        int d = c.getColumnIndex(D);
        int code_user = c.getColumnIndex(CODE_USER);
        int contenu = c.getColumnIndex(CONTENU);
        ArrayList<notification> list = new ArrayList<notification>();
        if (c.moveToFirst()) {
            do { notification ql = new notification(c.getInt(code),c.getInt(type),LocalDateTime.parse(c.getString(d)),c.getInt(code_user),c.getString(contenu));
                list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }



    /* Sélectionner les notifications par date */
    public static ArrayList<notification> selectByDate(SQLiteDatabase db,Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where DATE("+ D +")=?;" , new String[]{df.format(date)});
        int code = c.getColumnIndex(CODE);
        int type = c.getColumnIndex(TYPE);
        int d = c.getColumnIndex(D);
        int code_user = c.getColumnIndex(CODE_USER);
        int contenu = c.getColumnIndex(CONTENU);
        ArrayList<notification> list = new ArrayList<notification>();
        if (c.moveToFirst()) {
            do { notification ql = new notification(c.getInt(code),c.getInt(type),LocalDateTime.parse(c.getString(d)),c.getInt(code_user),c.getString(contenu));
                list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* Sélectionner les notifications par utilisateur */
    public static ArrayList<notification> selectByUser(SQLiteDatabase db, int code_utilisateur) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Cursor c=db.rawQuery("select * from "+ TABLE_NAME +" where "+ CODE_USER +"=?;" , new String[]{String.valueOf(code_utilisateur)});
        int code = c.getColumnIndex(CODE);
        int type = c.getColumnIndex(TYPE);
        int d = c.getColumnIndex(D);
        int code_user = c.getColumnIndex(CODE_USER);
        int contenu = c.getColumnIndex(CONTENU);
        ArrayList<notification> list = new ArrayList<notification>();
        if (c.moveToFirst()) {
            do { notification ql = new notification(c.getInt(code),c.getInt(type),LocalDateTime.parse(c.getString(d)),c.getInt(code_user),c.getString(contenu));
                list.add(ql);  }
            while(c.moveToNext());}
        c.close();
        return list;
    }


    /* insérer une nouvelle notification */
    public static void insert(SQLiteDatabase db,int code,int type,int code_user,String contenu) {
        db.execSQL("insert into "+ TABLE_NAME +" values("+String.valueOf(code)+","+String.valueOf(type)+","+ Calendar.getInstance() +","+String.valueOf(code_user)+",'"+contenu+"');");
    }


    /* supprimer une notification selon son code */
    public static void delete(SQLiteDatabase db,int code) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+CODE+"="+code+";");
    }


    /* supprimer toutes les notifications d'un utilisateur */
    public static void deleteByUser(SQLiteDatabase db,int code_user) {
        db.execSQL("delete from "+ TABLE_NAME +" where "+CODE_USER+"="+code_user+";");
    }



}
