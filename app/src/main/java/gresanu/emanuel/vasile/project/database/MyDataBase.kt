package gresanu.emanuel.vasile.project.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import gresanu.emanuel.vasile.project.recyclers.models.Message


@Database(entities = [Message::class], version = 1)
abstract class MyDataBase: RoomDatabase() {
    abstract fun bind():MyDao
}