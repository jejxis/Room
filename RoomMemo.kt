package com.example.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_memo")//클래스를 테이블로 변환
class RoomMemo {
    @PrimaryKey(autoGenerate = true)//PK. 자동 증가 옵션.
    @ColumnInfo//테이블의 컬럼으로 사용하겠다.
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo
    var datetime: Long = 0

    constructor(no: Long?, content: String, datetime: Long){
        if(no != null) this.no = no
        this.content = content
        this.datetime = datetime
    }
}