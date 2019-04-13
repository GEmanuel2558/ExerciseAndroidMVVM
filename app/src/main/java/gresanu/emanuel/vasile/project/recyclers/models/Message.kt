package gresanu.emanuel.vasile.project.recyclers.models

data class Message(val id: Int=-1, val title:String="", val message:String="", @Transient val timeStamp: Long = System.currentTimeMillis()) {

    private val STALE_MS = (5 * 1000).toLong() // Data is stale after 5 seconds

    fun isUpToDate() = (System.currentTimeMillis() - timeStamp < STALE_MS)
}