package com.example.salesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(adminDAO.TABLE_CREATE);
        db.execSQL(agentDAO.TABLE_CREATE);
        db.execSQL(bon_chargementDAO.TABLE_CREATE);
        db.execSQL(bon_pointageDAO.TABLE_CREATE);
        db.execSQL(clientDAO.TABLE_CREATE);
        db.execSQL(communeDAO.TABLE_CREATE);
        db.execSQL(factureDAO.TABLE_CREATE);
        db.execSQL(ligne_factureDAO.TABLE_CREATE);
        db.execSQL(notificationDAO.TABLE_CREATE);
        db.execSQL(pointeurDAO.TABLE_CREATE);
        db.execSQL(produitDAO.TABLE_CREATE);
        db.execSQL(qcDAO.TABLE_CREATE);
        db.execSQL(qte_chgDAO.TABLE_CREATE);
        db.execSQL(qte_ptgDAO.TABLE_CREATE);
        db.execSQL(secteurDAO.TABLE_CREATE);
        db.execSQL(vehiculeDAO.TABLE_CREATE);
        db.execSQL(itineraireDAO.TABLE_CREATE);
        db.execSQL(vuDAO.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("DatabaseHandler", "Mise à jour de la version " + oldVersion
                + " de la base de données vers la version " + newVersion
                + ", les anciennes données seront détruites ");
        db.execSQL(adminDAO.TABLE_DROP);
        db.execSQL(agentDAO.TABLE_DROP);
        db.execSQL(bon_chargementDAO.TABLE_DROP);
        db.execSQL(bon_pointageDAO.TABLE_DROP);
        db.execSQL(clientDAO.TABLE_DROP);
        db.execSQL(communeDAO.TABLE_DROP);
        db.execSQL(factureDAO.TABLE_DROP);
        db.execSQL(ligne_factureDAO.TABLE_DROP);
        db.execSQL(notificationDAO.TABLE_DROP);
        db.execSQL(pointeurDAO.TABLE_DROP);
        db.execSQL(produitDAO.TABLE_DROP);
        db.execSQL(qcDAO.TABLE_DROP);
        db.execSQL(qte_chgDAO.TABLE_DROP);
        db.execSQL(qte_ptgDAO.TABLE_DROP);
        db.execSQL(secteurDAO.TABLE_DROP);
        db.execSQL(vehiculeDAO.TABLE_DROP);
        db.execSQL(itineraireDAO.TABLE_DROP);
        db.execSQL(vuDAO.TABLE_DROP);

        onCreate(db);
    }
}
