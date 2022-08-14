package com.example.chap12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chap12.databinding.ActivityMain6Binding
import com.example.test12.OneFragment
import com.example.test12.ThreeFragment
import com.example.test12.TwoFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity6 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain6Binding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        val tablayout = binding.tabs
        val viewpager = binding.viewpager

        viewpager.adapter = MyFragmentPagerAdapter(this)

        TabLayoutMediator(tablayout, viewpager) {
            tab, position -> tab.text = "Tab${position+1}"
        }.attach()

        //탭 레이아웃을 이용한 탭 이벤트 활성 시키기
//        supportFragmentManager.beginTransaction().add(R.id.tabContent, OneFragment()).commit()
//        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            //선택된 탭 버튼을 다시 선택할 때 이벤트
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//
//            //탭 버튼을 선택할 때 이벤트
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                val transaction = supportFragmentManager.beginTransaction()
//                when(tab?.text){
//                    "Tab1" -> transaction.replace(R.id.tabContent, OneFragment() )
//                    "Tab2" -> transaction.replace(R.id.tabContent, TwoFragment() )
//                    "Tab3" -> transaction.replace(R.id.tabContent, ThreeFragment() )
//                }
//
//                transaction.commit()
//            }
//
//            // 다른 탭 버튼을 눌러 선택된 탭 버튼이 해지될 때 이벤트
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//        })




//        //탭 레이아웃 사용하기
//        val tablayout = binding.tabs
//        val tab1 : TabLayout.Tab = tablayout.newTab()
//        tab1.text = "Tab1"
//        tablayout.addTab(tab1)
//
//        val tab2 : TabLayout.Tab = tablayout.newTab()
//        tab2.text = "Tab2"
//        tablayout.addTab(tab2)
//
//        val tab3 : TabLayout.Tab = tablayout.newTab()
//        tab3.text = "Tab3"
//        tablayout.addTab(tab3)



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