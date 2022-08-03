package com.example.chap11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.example.chap11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        //업 버튼 보이게 하는 구문
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        //액션바의 내용을 툴바에 적용
        setSupportActionBar(binding.toolbar)
    }
    // 업 버튼 누를 때 실행되는 함수
    override fun onSupportNavigateUp(): Boolean {
        Log.d("chan", "onSupportNavigateUp")
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.menu3)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
                //검색어 변경 이벤트
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
                // 키보드 검색 버튼을 클릭한 순간의 이벤트
                return true
            }

        })
//        val menuItem1 : MenuItem? = menu?.add(0,0,0, "menu1")
//        val menuItem2 : MenuItem? = menu?.add(0,1,0, "menu2")
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            0 -> {
//                Log.d("chan", "menu1 click")
//                true
//            }
//
//            1->{
//                Log.d("chan", "menu2 click")
//                true
//            }
//
//        }
//        return super.onOptionsItemSelected(item)
//    }
}