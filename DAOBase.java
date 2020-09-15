package com.example.salesapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class DAOBase {

        protected final static int VERSION = 1;
        protected final static String NOM = "localdb.db";
        protected SQLiteDatabase mDb = null;
        protected DatabaseHandler mHandler = null;

        public DAOBase(Context pContext) {
            this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
        }

        public SQLiteDatabase open() {
            mDb = mHandler.getWritableDatabase();
            return mDb;
        }

        public void close() {
            mDb.close();
        }

        public SQLiteDatabase getDb() {
            return mDb;
        }
    }

