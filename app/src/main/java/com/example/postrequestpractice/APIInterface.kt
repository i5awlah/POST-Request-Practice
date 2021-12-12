package com.example.postrequestpractice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIInterface {
    @GET("test/")
    fun getPerson(): Call<Person>
    @POST("test/")
    fun addPerson(@Body person: PersonItem): Call<PersonItem>
}