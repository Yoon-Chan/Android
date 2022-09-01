package com.example.chap18

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.example.chap18.databinding.ActivityMain2Binding
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

data class UserModel(
    var id: String,
    @SerializedName("first_name")
    var firstName :String,
    var lastName : String,
    var avatar : String,
    var avatarBitmap : Bitmap
)
//모델 클래스 분리 이용
data class UserListModel(
    var page : String,
    @SerializedName("per_page")
    var perPage : String,
    var total : String,
    @SerializedName("total_pages")
    var totalPages : String,
    var data : List<UserModel>?
)




//서비스 인터페이스 정의
interface INetworkService {
    @GET("api/users")
    fun doGetUserList(@Query("page") page: String): Call<UserListModel>
    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

    //동적 경로 애너테이션 예시
    @GET("group/{id}/users/{name}")
    fun test2(@Path("id") userId : String, @Path("name") arg2 : String): Call<UserModel>
}

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = "https://www.google.com"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> {
                Log.d("chan", "server data : $it")
            },
            Response.ErrorListener { error ->
                Log.d("chan", "error...... $error")
            })

        val queue = Volley.newRequestQueue(this)
        val imgMap = HashMap<String?, Bitmap?>()
        val imageLoader = ImageLoader(queue, object : ImageLoader.ImageCache{
            override fun getBitmap(url: String?): Bitmap? {
                return imgMap[url]
            }

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                imgMap[url] = bitmap
            }
        })
        //화면출력용 이미ㅣ 데이터 요청하기
        binding.networkImageView.setImageUrl(url, imageLoader)


        queue.add(stringRequest)

        val stringRequest2 = object : StringRequest(
            Request.Method.POST,
            url,
            Response.Listener<String> {
                Log.d("chan", "server data : $it")
            },
            Response.ErrorListener { error ->
                Log.d("chan", "error......$error")
            }){
            override fun getParams(): MutableMap<String, String>? {
                return mutableMapOf<String, String>("one" to "hello", "two" to "world")
            }
        }

        val imageRequest = ImageRequest(
            url,
            Response.Listener { response -> binding.imageView.setImageBitmap(response)
            },
            0, 0, ImageView.ScaleType.CENTER_CROP, null,
            Response.ErrorListener { error -> Log.d("chan", "error..... $error")  })

        val queue2 = Volley.newRequestQueue(this)
        queue2.add(imageRequest)


        val jsonRequest =
            JsonObjectRequest(Request.Method.GET, url, null, Response.Listener<JSONObject> { response ->
                val title = response.getString("title")
                val data = response.getString("data")
                Log.d("chan", "$title, $data")
            },
            Response.ErrorListener { error -> Log.d("chan", "error....$error")  })

        //JSON 배열 요청하기
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener<JSONArray> { response ->
                for(i in 0 until response.length()){
                    val jsonObject = response[i] as JSONObject
                    val title = jsonObject.getString("title")
                    val date = jsonObject.getString("date")
                    Log.d("chan", "$title, $date")
                }
            },
            Response.ErrorListener { error ->
                Log.d("chan", "error....$error")
            })


        //Retrofit 객체 생성
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        //서비스 객체 얻기
        var networkService : INetworkService = retrofit.create(INetworkService::class.java)

        //네트워크 통신 시도
        val userListCall = networkService.doGetUserList("1")
        //네트워크 통신 수행
        userListCall.enqueue(object : Callback<UserListModel> {
            override fun onResponse(
                call: Call<UserListModel>,
                response: retrofit2.Response<UserListModel>
            ) {
                val userList = response.body()

            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                call.cancel()
            }
        })
    }


}