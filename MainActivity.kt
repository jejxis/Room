package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    var helper:RoomHelper? = null
    var mynull: Long? = null

    companion object{//출처: https://juahnpop.tistory.com/231
        private  var instance:MainActivity? = null
        fun getInstance():MainActivity?{
            return instance
        }
    }
    init{
        instance = this
    }//->RecyclerAdapter.kt에서 홀더(리스트 목록 중 하나) 클릭했을 때 editText에 값을 가져오기 위함임.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_memo")//생성되는 DB파일 이름: room_memo
            .allowMainThreadQueries()//실제 프로젝트에서는 이 옵션 사용하지 않기를 권함.
            .build()

        val adapter = RecyclerAdapter()
        adapter.helper = helper//helper를 어댑터에 연결
        adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())//어댑터의 listData에 디비에서 가져온 데이터 세팅

        binding.recyclerMemo.adapter = adapter//어댑터 연결
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)//레이아웃 매니지 설정

        binding.buttonSave.setOnClickListener {
            if(binding.editMemo.text.toString().isNotEmpty()){//플레인 텍스트에 값이 있으면
                val memo = RoomMemo(mynull, binding.editMemo.text.toString(), System.currentTimeMillis())
                helper?.roomMemoDao()?.insert(memo)//생성한 메모를 데이터베이스에 저장
                mynull = null
                adapter.listData.clear()//어댑터의 데이터 모두 초기화

                adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())//데이터베이스에서 새로운 목록을 읽어와 세팅하고 갱신
                adapter.notifyDataSetChanged()

                binding.editMemo.setText("")
            }
        }

    }
    fun changeContent(memo: RoomMemo){//RecyclerAdapter에서 리스트 목록 중 하나를 클릭했을 때 content 값을 보여주고, 해당 memo객체의 no를 저장한다.
        binding.editMemo.setText(memo.content)
        mynull = memo.no
    }
}