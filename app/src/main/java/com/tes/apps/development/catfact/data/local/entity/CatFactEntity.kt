package com.tes.apps.development.catfact.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tes.apps.development.catfact.domain.model.CatFactModel

@Entity(tableName = "cat_facts_list_table")
data class  CatFactEntity(
    val name: String?,
    val height: Int,
    val width: Int,
    val url: String,
    @PrimaryKey val id: Int? = null,
    val origin: String,
    val adaptability: Int,
    val catFriendly: Int,
    val dogFriendly: Int,
    val desc: String,
    val intelligence: Int,
    val affectionLevel: Int,
    val lifeSpan: String
)

fun CatFactEntity.toCatFactModel():CatFactModel= CatFactModel(
    height = height,
    width=width,
    url=url,
    name=name,
    origin=origin,
    adaptability=adaptability,
    catFriendly=catFriendly,
    dogFriendly=dogFriendly,
    desc=desc,
    intelligence=intelligence,
    affectionLevel=affectionLevel,
    lifeSpan=lifeSpan
)
