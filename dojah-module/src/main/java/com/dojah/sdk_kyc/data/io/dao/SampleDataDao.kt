package com.dojah.sdk_kyc.data.io.dao

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow

@Dao
interface SampleDataDao {

//    @Insert
//    suspend fun insertNotifications(notifications: List<Notification>)
//
//    @Update
//    suspend fun updateNotification(notifications: List<Notification>)
//
//    @RawQuery(observedEntities = [Notification::class])
//    fun allNotifications(query: SupportSQLiteQuery): Flow<List<Notification>>
//
//    @RawQuery(observedEntities = [Notification::class])
//    fun unseenCount(query: SupportSQLiteQuery): Flow<Int>
}
