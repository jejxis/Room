package com.example.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var helper: RoomHelper? = null
    var listData = mutableListOf<RoomMemo>()

    private val main = MainActivity.getInstance()//https://juahnpop.tistory.com/231

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setRoomMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
    inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root){
        var mRoomMemo: RoomMemo? = null//클릭시점에 어떤 데이터가 있는지 알려준다.
        var changed: String = ""
        init{
            binding.buttonDelete.setOnClickListener {
                helper?.roomMemoDao()?.delete(mRoomMemo!!)//데이터 삭제
                listData.remove(mRoomMemo)//listData의 데이터도 삭제
                notifyDataSetChanged()//어댑터 갱신
            }

            binding.root.setOnClickListener {
                main?.changeContent(mRoomMemo!!)
            }
        }
        fun setRoomMemo(memo: RoomMemo){
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")//날짜 포맷은 SimpleDateFormat으로 설정
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"
            this.mRoomMemo = memo
        }
    }
}
