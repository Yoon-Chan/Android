package com.example.ch12_material

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ch12_material.databinding.ActivityMainBinding
import com.example.ch12_material.databinding.ItemRecyclerviewBinding
import com.example.test12.OneFragment
import com.example.test12.ThreeFragment
import com.example.test12.TwoFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()


                //탭 레이아웃 사용하기
        val tablayout = binding.tabs
        val tab1 : TabLayout.Tab = tablayout.newTab()
        tab1.text = "Tab1"
        tablayout.addTab(tab1)

        val tab2 : TabLayout.Tab = tablayout.newTab()
        tab2.text = "Tab2"
        tablayout.addTab(tab2)

        val tab3 : TabLayout.Tab = tablayout.newTab()
        tab3.text = "Tab3"
        tablayout.addTab(tab3)


        //뷰 페이저에 어댑터 적용
        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tabs, binding.viewpager) {
            tab, position -> tab.text = "Tab${position+1}"
        }.attach()

        //네비게이션 바 메뉴 클릭 이벤트
        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("chan", "navigation Item Click ... ${it.title}")
            true
        }

        //확장된 플로팅 액션 버튼 이벤트
        binding.fab.setOnClickListener {
            when(binding.fab.isExtended) {
                true -> binding.fab.shrink()
                false -> binding.fab.extend()
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 이벤트가 토글 버튼에서 발생하면
        if(toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_378, menu)

        return super.onCreateOptionsMenu(menu)
    }

    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments : List<Fragment>
        init {
            fragments = listOf( OneFragment(), TwoFragment(), ThreeFragment() )
        }
        override fun createFragment(position: Int): Fragment = fragments[position]

        override fun getItemCount(): Int = fragments.size

    }


}