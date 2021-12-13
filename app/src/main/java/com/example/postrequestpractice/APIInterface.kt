package com.example.postrequestpractice

import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @GET("test/")
    fun getPerson(): Call<Person>
    @POST("test/")
    fun addPerson(@Body person: PersonItem): Call<PersonItem>
    @PUT("test/{id}")
    fun updatePerson(@Path("id") id: Int, @Body person: PersonItem): Call<PersonItem>
    @DELETE("test/{id}")
    fun deletePerson(@Path("id") id: Int): Call<Void>
}