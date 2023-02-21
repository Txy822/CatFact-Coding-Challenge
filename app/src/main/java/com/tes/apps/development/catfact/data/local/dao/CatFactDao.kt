package com.tes.apps.development.catfact.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tes.apps.development.catfact.data.local.entity.CatFactEntity

@Dao
interface CatFactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCatFactsList(showListingEntity: List<CatFactEntity>)

    @Query("DELETE FROM cat_facts_list_table")
    suspend fun clearCatFactTable()

    @Query(
        """
            SELECT * 
            FROM cat_facts_list_table
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == name
        """
    )
    suspend fun searchCatFacts(query: String): List<CatFactEntity>
}