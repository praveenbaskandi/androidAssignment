package com.praveen.myapplication.dbUtil

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (mContext: Context) : SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "app_database_local"
        const val DATABASE_VERSION = 1
        const val LOCAL_ID = "local_id"
        const val LOCAL_TABLE = "local_table"
        const val LOCAL_FIRST_NAME = "local_first_name"
        const val LOCAL_LAST_NAME = "local_last_name"
        const val LOCAL_EMAIL = "local_email"
        const val LOCAL_AVATAR = "local_avatar"
        val LOCAL_COLUMN = arrayOf(LOCAL_ID, LOCAL_FIRST_NAME, LOCAL_LAST_NAME, LOCAL_EMAIL, LOCAL_AVATAR)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val LOCAL_TABLE_CREATE = ("create table if not exists " + LOCAL_TABLE + "("
                + LOCAL_ID + " integer primary key,"
                + LOCAL_FIRST_NAME + " text,"
                + LOCAL_LAST_NAME + " text,"
                + LOCAL_EMAIL + " INTEGER, "
                + LOCAL_AVATAR + " text);")

        db?.execSQL(LOCAL_TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}