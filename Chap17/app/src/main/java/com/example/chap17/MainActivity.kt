package com.example.chap17

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE, null)
//        db.execSQL("create table USER_TB ("
//        +"_id integer primary key autoincrement,"+
//        "name not null ,"+
//                " phone)")
//
//        //데이터 삽입
//        db.execSQL("insert into USER_TB (name, phone) values (?,?)",
//        arrayOf<String>("chan", "0101111"))
//
//        //데이터 조회
//        val cursor = db.rawQuery("select * from USER_TB", null)
//
//        while(cursor.moveToNext()){
//            val name = cursor.getString(0)
//            val phone = cursor.getString(1)
//        }
//
//        val values = ContentValues()
//        values.put("name", "chan")
//        values.put("phone", "0101111")
//        db.insert("USER_TB", null, values)
//
//        val cursor2 = db.query("USER_TB", arrayOf<String>("name", "phone"), "phone=?",
//        arrayOf<String>("0101111"), null, null, null)
//
//
//
//        val db2 : SQLiteDatabase = DBHelper(this).writableDatabase
    }
}

class DBHelper(context: Context) : SQLiteOpenHelper(context, "testdb", null, 1){
    override fun onCreate(p0: SQLiteDatabase?) {
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}