package com.tes.apps.development.catfact.data.repository

import com.tes.apps.development.catfact.data.local.database.CatFactDatabase
import com.tes.apps.development.catfact.data.local.entity.CatFactEntity
import com.tes.apps.development.catfact.data.local.entity.toCatFactModel
import com.tes.apps.development.catfact.data.remote.api.CatFactApi
import com.tes.apps.development.catfact.data.remote.dto.toCatFactEntity
import com.tes.apps.development.catfact.domain.model.CatFactModel
import com.tes.apps.development.catfact.domain.repository.CatFactRepository
import com.tes.apps.development.catfact.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CatFactRepositoryImpl @Inject constructor(
    private val api: CatFactApi,
    db: CatFactDatabase
) : CatFactRepository {
    private val dao = db.catFactDao()
    override suspend fun getCatFactsList(
        query: String
    ): Flow<Resource<List<CatFactModel>>> {
        return flow {

            val remoteListings = try {
                api.getCatFactList(query)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null // flow{null}
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null // flow{null}
            }
            remoteListings?.let { listings ->
                //clearCatFactsFromDb()
                insertCatFactsListToDb(listings.map { it.toCatFactEntity() })
                emit(Resource.Success(
                    //data = listings.map { it.toCatFactModel()} //from remote
                    data = searchCatFactsFromDb(query).map { it.toCatFactModel() } //from local
                ))
                emit(Resource.Loading(false))
            }
            if (remoteListings.isNullOrEmpty() && searchCatFactsFromDb(query).isNotEmpty()) {
                emit(Resource.Success(
                    data = searchCatFactsFromDb(query).map { it.toCatFactModel() }
                ))
            }
        }
    }

    override suspend fun insertCatFactsListToDb(catFactList: List<CatFactEntity>) =
        dao.insertCatFactsList(catFactList)

    override suspend fun clearCatFactsFromDb() =
        dao.clearCatFactTable()

    override suspend fun searchCatFactsFromDb(query: String): List<CatFactEntity> =
        dao.searchCatFacts(query)
}
