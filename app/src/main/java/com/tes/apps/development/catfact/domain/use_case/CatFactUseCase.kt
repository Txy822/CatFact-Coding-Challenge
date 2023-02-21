package com.tes.apps.development.catfact.domain.use_case

import com.tes.apps.development.catfact.domain.model.CatFactModel
import com.tes.apps.development.catfact.domain.repository.CatFactRepository
import com.tes.apps.development.catfact.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatFactUseCase @Inject constructor(
    private val repository:CatFactRepository
){
    suspend fun getCatFactList(query: String): Flow<Resource<List<CatFactModel>>> {
      return  repository.getCatFactsList(query)
    }
}