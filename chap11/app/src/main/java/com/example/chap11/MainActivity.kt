package com.example.chap11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chap11.databinding.ActivityMainBinding
import com.example.chap11.databinding.FragmentOneBinding
import com.example.chap11.databinding.ItemMainBinding


//뷰 홀더 준비
class MyViewHolder(val binding : ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

//어댑터 준비
class MyAdapter(val datas : MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //항목 개수를 판단하려고 자동으로 호출된다.
    override fun getItemCount(): Int {
        Log.d("chan", "init datas size: ${datas.size}")
        return datas.size
    }

    // 뷰 홀더의 뷰에 데이터를 출력하고 자동으로 호출된다.
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("chan", "onBindViewHolder : $position")
        val binding = (holder as MyViewHolder).binding

        //뷰에 데이터 출력
        binding.itemData.text = datas[position]

        //뷰에 이벤트 추가
        binding.itemRoot.setOnClickListener {
            Log.d("chan", "item root click : $position")
        }
    }

    // 항목의 뷰를 가지는 뷰 홀더를 준비하려고 자동으로 호출한다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

}

//프래그먼트 상속 클래스
//class OneFragment : Fragment() {
//    //XML 뷰 바인딩 아직 XML 파일을 생성하지 않아 오류가 발생.
//    lateinit var binding: FragmentOneBinding
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
//        return binding.root
//    }
//}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = mutableListOf<String>()
        for(i in 1..10){
            data.add("Item $i")
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(data)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL)
        )

//        val fragmentManager : FragmentManager = supportFragmentManager
//        val transaction : FragmentTransaction = fragmentManager.beginTransaction()
//        val fragment = OneFragment()
//         fragement_content인 id에 fragment를 넣는다.
//        transaction.add(R.id.fragment_content, fragment)
//        //화면에 적응하기
//        transaction.commit()


        //업 버튼 보이게 하는 구문
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        //액션바의 내용을 툴바에 적용
        //setSupportActionBar(binding.toolbar)
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