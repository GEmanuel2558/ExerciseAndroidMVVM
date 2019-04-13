package gresanu.emanuel.vasile.project.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import gresanu.emanuel.vasile.project.recyclers.models.Message

@Dao
interface MyDao {

    @get:Query("SELECT * FROM Message")
    val getAll: List<Message>

    @Insert
    fun insertAll(vararg users: Message)

    @Query("DELETE FROM Message")
    fun deleteAll()
}