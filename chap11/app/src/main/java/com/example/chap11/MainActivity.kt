package com.example.chap11

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.chap11.databinding.ActivityMainBinding
import com.example.chap11.databinding.FragmentOneBinding
import com.example.chap11.databinding.ItemMainBinding
import com.example.chap11.databinding.ItemPagerBinding


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


//아이템 데커레이션 구현
class MyDecoration(val context : Context) : RecyclerView.ItemDecoration() {

    // 항목이 배치되기 전에 호출
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.stadium), 0f, 0f, null)
    }

    //항목이 모두 배치된 후 호출
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        //뷰 크기 계산
        val width = parent.width
        val height = parent.height

        // 이미지 크기 계산
        val dr: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val drWidth = dr?.intrinsicWidth
        val drheight = dr?.intrinsicHeight

        //이미지가 그려질 위치 계산
        val left = width / 2 - drWidth?.div(2) as Int
        val top = height / 2 -drheight?.div(2) as Int
        c.drawBitmap(
            BitmapFactory.decodeResource(context.resources, R.drawable.kbo),
            left.toFloat(),
            top.toFloat(),
            null
        )

    }

    // 개별 항목을 꾸밀 때 호출
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val index = parent.getChildAdapterPosition(view) + 1
        if(index % 3 == 0){
            outRect.set(10, 10, 10, 60) //left, top, right, bottom 순
        }else{
            outRect.set(10,10,10,0)
        }
        view.setBackgroundColor(Color.LTGRAY)
        ViewCompat.setElevation(view, 20.0f)
    }
}



// 뷰 페이저2 구현 - 리사이클러 뷰 어댑터 이용
class MyPagerViewHolder(val binding : ItemPagerBinding) : RecyclerView.ViewHolder(binding.root)

class MyPagerAdapter(val datas : MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int =datas.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        MyPagerViewHolder(ItemPagerBinding.inflate(LayoutInflater.from(
                parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyPagerViewHolder).binding

        //뷰에 데이터 출력
        binding.itemPagerTextView.text =  datas[position]
        when (position % 3){
            0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
            1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
            2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
        }
    }


}

//뷰 페이저2 구현 - 프래그먼트 어댑터 이용
class MyFragmentPagerAdapter(activity : FragmentActivity) : FragmentStateAdapter(activity) {
    val fragments : List<Fragment>
    init {
        fragments = listOf(OneFragment() , TwoFragment(), ThreeFragment())
        Log.d("chan", "fragments size : ${fragments.size}")
    }
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment =fragments[position]
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
        for(i in 1..10) {
            data.add("Item $i")
        }

        // 뷰 페이저2 어댑터에 적용
        //binding.viewpager.adapter = MyPagerAdapter(data)

        // 뷰 페이저2 프래그먼트 적용
        binding.viewpager.adapter = MyFragmentPagerAdapter(this)


//        val data = mutableListOf<String>()
//        for(i in 1..10){
//            data.add("Item $i")
//        }
//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.VERTICAL
//        binding.recyclerView.layoutManager = layoutManager
        //GridLayoutManager(this, 3,
        //GridLayoutManager.HORIZONTAL, false)
//        binding.recyclerView.adapter = MyAdapter(data)
//        binding.recyclerView.addItemDecoration(
//            MyDecoration(this)
//            DividerItemDecoration(
//                this,
//                LinearLayoutManager.VERTICAL)
//        )

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