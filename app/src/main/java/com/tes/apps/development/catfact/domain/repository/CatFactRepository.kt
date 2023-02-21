package com.tes.apps.development.catfact.domain.repository

import com.tes.apps.development.catfact.data.local.entity.CatFactEntity
import com.tes.apps.development.catfact.domain.model.CatFactModel
import com.tes.apps.development.catfact.util.Resource
import kotlinx.coroutines.flow.Flow

interface CatFactRepository    {
suspend fun getCatFactsList(
    query: String
): Flow<Resource<List<CatFactModel>>>
    suspend fun insertCatFactsListToDb(catFactList: List<CatFactEntity>)
    suspend fun clearCatFactsFromDb()
    suspend fun searchCatFactsFromDb(query: String): List<CatFactEntity>
}