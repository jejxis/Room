package com.example.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao//Data Access Object. DB에 접근해서 DML쿼리(select, insert, updaate, delete)를 실행하는 메서드의 모음.
interface RoomMemoDao {//Room이 나머지 코드를 자동 생성함.
    @Query("select * from room_memo")
    fun getAll(): List<RoomMemo>

    @Insert(onConflict = REPLACE)//이미 가지고 있는 키를 가진 값이 입력되었을 때 update로 실행됨.
    fun insert(memo: RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)
}