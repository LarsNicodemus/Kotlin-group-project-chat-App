package com.syntax_institut.whatssyntax.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.syntax_institut.whatssyntax.data.datamodel.Contact
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "http://81.169.201.230:8080"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WhatsSyntaxApiService {

    @GET("/group/{number}/contacts")
    suspend fun getContacts(@Path("number")number: Int, @Query ("key") key: String): List<Contact>

    @GET("/group/{number}/contacts/{id}")
    suspend fun getContact(@Path("number")number: Int, @Path("id")id: Int, @Query ("key") key: String): Contact

}

object WhatsSyntaxApi {
    val retrofitService: WhatsSyntaxApiService by lazy { retrofit.create(WhatsSyntaxApiService::class.java) }
}