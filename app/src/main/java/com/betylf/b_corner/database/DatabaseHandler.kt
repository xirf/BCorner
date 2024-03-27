package com.betylf.b_corner.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.lang.Exception

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "AccountManager"
        private const val TABLE_ACCOUNTS = "accounts"
        private const val TABLE_LOGGED_IN = "logged_in"
        private const val TABLE_PROBLEM = "problem"
        private const val KEY_ID = "id"
        private const val KEY_USERNAME = "username"
        private const val KEY_PASSWORD = "password"
        private const val KEY_NAME = "name"
        private const val KEY_INDEX = "Qindex"
        private const val KEY_ANSWER_INDEX = "answer_index"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createAccountsTableQuery = """
        CREATE TABLE $TABLE_ACCOUNTS (
            $KEY_ID INTEGER PRIMARY KEY,
            $KEY_USERNAME TEXT UNIQUE,
            $KEY_PASSWORD TEXT,
            $KEY_NAME TEXT
        )
    """
        db.execSQL(createAccountsTableQuery)

        val createLoggedInTableQuery = """
        CREATE TABLE $TABLE_LOGGED_IN (
            $KEY_ID INTEGER PRIMARY KEY,
            $KEY_USERNAME TEXT,
            $KEY_NAME TEXT
        )
    """
        db.execSQL(createLoggedInTableQuery)

        val createProblemsTableQuery = """
        CREATE TABLE $TABLE_PROBLEM (
            $KEY_ID INTEGER PRIMARY KEY,
            $KEY_ANSWER_INDEX INTEGER
        )
        """

        db.execSQL(createProblemsTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ACCOUNTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_LOGGED_IN")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PROBLEM")
        onCreate(db)
    }

    fun addAccount(username: String, password: String, name: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USERNAME, username)
        values.put(KEY_PASSWORD, password)
        values.put(KEY_NAME, name)
        db.insert(TABLE_ACCOUNTS, null, values)
        db.close()
    }

    fun getAccount(username: String): String? {
        val db = this.readableDatabase
        val query = "SELECT $KEY_NAME FROM $TABLE_ACCOUNTS WHERE $KEY_USERNAME = ?"
        val cursor = db.rawQuery(query, arrayOf(username))
        cursor.moveToFirst()
        var name: String? = null
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(KEY_NAME)
            name = if (columnIndex != -1) {
                cursor.getString(columnIndex)
            } else {
                null
            }
        }
        cursor.close()
        db.close()
        return name
    }

    fun getUsername(name: String): String {
        val db = this.readableDatabase
        val query = "SELECT $KEY_NAME FROM $TABLE_ACCOUNTS WHERE $KEY_USERNAME = ?"
        val cursor = db.rawQuery(query, arrayOf(name))
        cursor.moveToFirst()
        var username: String? = null
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(KEY_NAME)
            username = if (columnIndex != -1) {
                cursor.getString(columnIndex)
            } else {
                null
            }
        }
        cursor.close()
        db.close()
        return username ?: "Admin"
    }

    fun isLoggedIn(): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_LOGGED_IN"
        val cursor = db.rawQuery(query, null)
        val result = cursor.count > 0
        cursor.close()
        db.close()
        return result
    }

    fun getLoggedInUsername(): String? {
        val db = this.readableDatabase
        val query = "SELECT $KEY_USERNAME FROM $TABLE_LOGGED_IN"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        var username: String? = null
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(KEY_USERNAME)
            username = if (columnIndex != -1) {
                cursor.getString(columnIndex)
            } else {
                null
            }
        }
        cursor.close()
        db.close()
        return username
    }

    fun getLoggedInUser(): String? {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_LOGGED_IN"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()
        var name: String? = null
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(KEY_USERNAME)
            name = if (columnIndex != -1) {
                cursor.getString(columnIndex)
            } else {
                null
            }
        }
        cursor.close()
        db.close()
        return name
    }


    fun setLoggedIn(username: String, name: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_USERNAME, username)
        values.put(KEY_NAME, name)
        db.insert(TABLE_LOGGED_IN, null, values)
        db.close()
    }

    fun clearLoggedIn() {
        val db = this.writableDatabase
        db.delete(TABLE_LOGGED_IN, null, null)
        db.close()
    }

    fun getProblemAnswer(index: Int): Int {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_PROBLEM WHERE $KEY_ID = ?"
        val cursor = db.rawQuery(query, arrayOf(index.toString()))
        cursor.moveToFirst()
        var questionIndex: Int? = null
        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(KEY_ANSWER_INDEX)
            questionIndex = if (columnIndex != -1) {
                cursor.getInt(columnIndex)
            } else {
                null
            }
        }
        cursor.close()
        db.close()

        return questionIndex ?: -1

    }

    fun setProblemAnswer(index: Int, selected: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_ID, index)
        values.put(KEY_ANSWER_INDEX, selected)
        try {
            db.insertOrThrow(TABLE_PROBLEM, null, values)
        } catch (e: Exception) {
            db.update(TABLE_PROBLEM, values, "$KEY_ID = ?", arrayOf(index.toString()))
        }
        db.close()
    }

    fun dropProblemTable() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_PROBLEM")
    }

    fun createProblemTable() {
        val db = this.writableDatabase
        val createProblemsTableQuery = """
        CREATE TABLE $TABLE_PROBLEM (
            $KEY_ID INTEGER PRIMARY KEY,
            $KEY_ANSWER_INDEX INTEGER
        )
        """

        db.execSQL(createProblemsTableQuery)
        db.close()
    }

    fun getAllProblem(): ArrayList<Int> {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_PROBLEM"
        val cursor = db.rawQuery(query, null)
        val result = ArrayList<Int>()
        while (cursor.moveToNext()) {
            val columnIndex = cursor.getColumnIndex(KEY_ANSWER_INDEX)
            val answerIndex = if (columnIndex != -1) {
                cursor.getInt(columnIndex)
            } else {
                null
            }
            if (answerIndex != null) {
                result.add(answerIndex)
            }
        }
        cursor.close()
        db.close()
        return result
    }


}