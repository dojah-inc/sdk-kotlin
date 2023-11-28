package com.dojah.sdk_kyc.data.io

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dojah.sdk_kyc.data.io.dao.SampleDataDao
import com.dojah.sdk_kyc.domain.Beneficiary
import com.dojah.sdk_kyc.domain.UserProfile

@Database(
    entities = [Beneficiary::class],
    version = 2
)
abstract class DojahDatabase : RoomDatabase() {
    companion object {
        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS notification_id (notificationId INTEGER PRIMARY KEY NOT NULL, user_number TEXT NOT NULL)")
            }
        }


        const val DB_NAME = "dojah_base"
    }

//    abstract val sampleDataDao: SampleDataDao


}