package gresanu.emanuel.vasile.project.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import gresanu.emanuel.vasile.project.recyclers.models.Message


@Database(entities = [Message::class], version = 1)
abstract class MyDataBase: RoomDatabase() {
    abstract fun bind():MyDao


    companion object {
        private var dbInstance: MyDataBase? = null

        fun getCurrentDb(context: Context): MyDataBase? {
            if(null == dbInstance) {
                synchronized(MyDataBase::class.java) {
                    if(null == dbInstance) {
                        dbInstance =
                            Room.databaseBuilder(context.applicationContext, MyDataBase::class.java, "my_disk").build()
                    }
                }
            }
            return dbInstance
        }
    }
}