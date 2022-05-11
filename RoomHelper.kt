package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
//entities: 룸 라이브러리가 사용할 엔티티 클래스 목록, version: 데이터 베이스의 버전, exprotSchema: true면 스키마 정보 파일 출력
@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun roomMemoDao(): RoomMemoDao
}