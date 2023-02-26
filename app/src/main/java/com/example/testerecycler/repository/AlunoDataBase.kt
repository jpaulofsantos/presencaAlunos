package com.example.testerecycler.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testerecycler.model.AlunoModel

@Database(entities = [AlunoModel::class], version = 1)
abstract class AlunoDataBase: RoomDatabase() {

    abstract fun alunoDAO(): AlunoDAO

    companion object {

        private lateinit var INSTANCE: AlunoDataBase

        fun getDataBase(context: Context): AlunoDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(AlunoDataBase::class) {
                    INSTANCE =Room.databaseBuilder(context, AlunoDataBase::class.java, "alunodb")
                        .addMigrations(MIGRATION)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
        private val MIGRATION = object: Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Aluno")
            }
        }
    }
}