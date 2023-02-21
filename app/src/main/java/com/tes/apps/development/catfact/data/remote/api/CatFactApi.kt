package com.tes.apps.development.catfact.data.remote.api

import com.tes.apps.development.catfact.BuildConfig
import com.tes.apps.development.catfact.data.remote.dto.CatFactsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactApi {

    @GET(END)
    suspend fun getCatFactList(
        @Query("breed_ids")breed_ids:String,
        @Query("api_key") api_key:String= BuildConfig.API_KEY

    ):CatFactsDto

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/"
        const val END = "v1/images/search?limit=30&breed_ids=abys,beng,aege,bali,bamb,amer,aust,birm,brit,burm,calif,chan,char,chau,chee,cypr,drag,egyp,japa,java,khao,kora,mala,turk,toyg,sphy,york,soma,sing,scot"
    }

}
