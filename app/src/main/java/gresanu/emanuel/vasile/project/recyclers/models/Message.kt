package gresanu.emanuel.vasile.project.recyclers.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Message(@field:PrimaryKey var id: Int=-1, var title:String="", var message:String="", @Transient val timeStamp: Long = System.currentTimeMillis()) {

    companion object {
        const val STALE_MS = (5 * 1000).toLong() // Data is stale after 5 seconds
    }

    fun isUpToDate() = (System.currentTimeMillis() - timeStamp < STALE_MS)
}