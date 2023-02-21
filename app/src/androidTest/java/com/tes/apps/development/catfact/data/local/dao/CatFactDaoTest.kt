package com.tes.apps.development.catfact.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tes.apps.development.catfact.data.local.database.CatFactDatabase
import com.tes.apps.development.catfact.data.local.entity.CatFactEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CatFactDaoTest {

    private lateinit var database: CatFactDatabase
    private lateinit var dao: CatFactDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CatFactDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.catFactDao()
    }

    @Test
    fun insertCatFactsListToDBTest() = runBlocking {
       val  catFactEntityList: MutableList<CatFactEntity> = mutableListOf()
        val factOne = CatFactEntity(
            name = "name1",
            height = 0,
            width = 0,
            url = "",
            origin = "",
            adaptability = 4,
            intelligence = 2,
            catFriendly = 3,
            dogFriendly = 2,
            desc = "",
            affectionLevel = 6,
            lifeSpan = "",
            id = 1
        )
        val factTwo = CatFactEntity(
            name = "name2",
            height = 1,
            width = 1,
            url = "",
            origin = "",
            adaptability = 5,
            intelligence = 3,
            catFriendly = 4,
            dogFriendly = 3,
            desc = "",
            affectionLevel = 7,
            lifeSpan = "",
            id = 2
        )
        catFactEntityList.add(factOne)
        catFactEntityList.add(factTwo)

        dao.insertCatFactsList(catFactEntityList)
        assertEquals(dao.searchCatFacts(""),catFactEntityList)
    }

    @Test
    fun clearDatabaseTableTest()= runBlocking {
      val  catFactEntityList: MutableList<CatFactEntity> = mutableListOf()
        val factOne = CatFactEntity(
            name = "name1",
            height = 0,
            width = 0,
            url = "",
            origin = "",
            adaptability = 4,
            intelligence = 2,
            catFriendly = 3,
            dogFriendly = 2,
            desc = "",
            affectionLevel = 6,
            lifeSpan = "",
            id = 1
        )
        catFactEntityList.add(factOne)
        dao.insertCatFactsList(catFactEntityList)
        dao.clearCatFactTable()

        assertEquals(dao.searchCatFacts(""), emptyList<CatFactEntity>())
    }

    @After
    fun teardown() {
        database.close()
    }

}