package com.example.rsschool2021TheCatsApi.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.thecatapi.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CatsApiService {
    @GET("v1/images/search")
    suspend fun getProperty(): List<CatsProperty>

    @GET("v1/images/search")
    suspend fun getProperties(
        @Query("limit") limit: Int,
        @Query("has_breeds") has_breeds: Boolean
    ): List<CatsProperty>

    @GET("v1/images/search")
    suspend fun getPropertiesWithPagination(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("has_breeds") has_breeds: Boolean,
        @Query("order") order: String = "desc"
    ): List<CatsProperty>
}

object CatsApi {
    val retrofitService: CatsApiService by lazy { retrofit.create(CatsApiService::class.java) }
}
