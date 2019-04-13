package gresanu.emanuel.vasile.project.network

import gresanu.emanuel.vasile.project.recyclers.models.Message
import io.reactivex.Observable
import retrofit2.http.GET

interface GeneralInformation {

    @GET("getAll")
    fun getAllMessages(): Observable<List<Message>>
}