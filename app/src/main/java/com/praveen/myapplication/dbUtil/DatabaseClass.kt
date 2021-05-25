package com.praveen.myapplication.dbUtil

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.praveen.myapplication.appUI.DataModelClass
import java.util.ArrayList

object DatabaseClass {

    /**
     * insert note in database
     */
    fun insertLocalData(localDataList: List<DataModelClass.Data>, mContext: Context): Long {
        val database: SQLiteDatabase = DBHelper(mContext).writableDatabase
        val values = ContentValues()
        var result = 1L
        localDataList.forEach { obj ->
            values.put(DBHelper.LOCAL_ID, obj.id)
            values.put(DBHelper.LOCAL_EMAIL, obj.email)
            values.put(DBHelper.LOCAL_FIRST_NAME, obj.first_name)
            values.put(DBHelper.LOCAL_LAST_NAME, obj.last_name)
            values.put(DBHelper.LOCAL_AVATAR, obj.avatar)
            result = database.insert(DBHelper.LOCAL_TABLE, null, values)
        }
        database.close()
        return result
    }


    fun getLocalList(mContext: Context): List<DataModelClass.Data> {
        val database: SQLiteDatabase = DBHelper(mContext).writableDatabase
        val mList = ArrayList<DataModelClass.Data>()
        val cursor: Cursor =
            database.query(
                DBHelper.LOCAL_TABLE,
                DBHelper.LOCAL_COLUMN,
                null,
                null,
                null,
                null,
                null
            )

        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val model = DataModelClass.Data()
            model.id = cursor.getInt(cursor.getColumnIndex(DBHelper.LOCAL_ID))
            model.email = cursor.getString(cursor.getColumnIndex(DBHelper.LOCAL_EMAIL))
            model.first_name = cursor.getString(cursor.getColumnIndex(DBHelper.LOCAL_FIRST_NAME))
            model.last_name = cursor.getString(cursor.getColumnIndex(DBHelper.LOCAL_LAST_NAME))
            model.avatar = cursor.getString(cursor.getColumnIndex(DBHelper.LOCAL_AVATAR))
            mList.add(model)
            cursor.moveToNext()
        }
        cursor.close()
        database.close()
        return mList
    }
}