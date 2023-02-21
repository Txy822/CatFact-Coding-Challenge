package com.tes.apps.development.catfact.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.tes.apps.development.catfact.data.local.entity.CatFactEntity
import com.tes.apps.development.catfact.domain.model.CatFactModel

data class CatFactsDtoItem(
    @SerializedName("breeds")
    val breeds: List<Breed> = listOf(),
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("width")
    val width: Int = 0
)

fun CatFactsDtoItem.toCatFactEntity(): CatFactEntity = CatFactEntity(
    height = height,
    width = width,
    url = url,
    name = breeds[0].name,
    origin = breeds[0].origin,
    adaptability = breeds[0].adaptability,
    catFriendly = breeds[0].catFriendly,
    dogFriendly = breeds[0].dogFriendly,
    desc = breeds[0].description,
    intelligence = breeds[0].intelligence,
    affectionLevel = breeds[0].affectionLevel,
    lifeSpan = breeds[0].lifeSpan


)

fun CatFactsDtoItem.toCatFactModel(): CatFactModel = CatFactModel(
    height = height,
    width = width,
    url = url,
    name = breeds[0].name,
    origin = breeds[0].origin,
    adaptability = breeds[0].adaptability,
    catFriendly = breeds[0].catFriendly,
    dogFriendly = breeds[0].dogFriendly,
    desc = breeds[0].description,
    intelligence = breeds[0].intelligence,
    affectionLevel = breeds[0].affectionLevel,
    lifeSpan = breeds[0].lifeSpan
)

data class Breed(
    @SerializedName("adaptability")
    val adaptability: Int = 0,
    @SerializedName("affection_level")
    val affectionLevel: Int = 0,
    @SerializedName("bidability")
    val bidability: Int = 0,
    @SerializedName("cat_friendly")
    val catFriendly: Int = 0,
    @SerializedName("cfa_url")
    val cfaUrl: String = "",
    @SerializedName("child_friendly")
    val childFriendly: Int = 0,
    @SerializedName("country_code")
    val countryCode: String = "",
    @SerializedName("country_codes")
    val countryCodes: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("dog_friendly")
    val dogFriendly: Int = 0,
    @SerializedName("energy_level")
    val energyLevel: Int = 0,
    @SerializedName("experimental")
    val experimental: Int = 0,
    @SerializedName("grooming")
    val grooming: Int = 0,
    @SerializedName("hairless")
    val hairless: Int = 0,
    @SerializedName("health_issues")
    val healthIssues: Int = 0,
    @SerializedName("hypoallergenic")
    val hypoallergenic: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("indoor")
    val indoor: Int = 0,
    @SerializedName("intelligence")
    val intelligence: Int = 0,
    @SerializedName("lap")
    val lap: Int = 0,
    @SerializedName("life_span")
    val lifeSpan: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("natural")
    val natural: Int = 0,
    @SerializedName("origin")
    val origin: String = "",
    @SerializedName("rare")
    val rare: Int = 0,
    @SerializedName("reference_image_id")
    val referenceImageId: String = "",
    @SerializedName("rex")
    val rex: Int = 0,
    @SerializedName("shedding_level")
    val sheddingLevel: Int = 0,
    @SerializedName("short_legs")
    val shortLegs: Int = 0,
    @SerializedName("social_needs")
    val socialNeeds: Int = 0,
    @SerializedName("stranger_friendly")
    val strangerFriendly: Int = 0,
    @SerializedName("suppressed_tail")
    val suppressedTail: Int = 0,
    @SerializedName("temperament")
    val temperament: String = "",
    @SerializedName("vcahospitals_url")
    val vcahospitalsUrl: String = "",
    @SerializedName("vetstreet_url")
    val vetstreetUrl: String = "",
    @SerializedName("vocalisation")
    val vocalisation: Int = 0,
    @SerializedName("weight")
    val weight: Weight = Weight(),
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String = ""
)

data class Weight(
    @SerializedName("imperial")
    val imperial: String = "",
    @SerializedName("metric")
    val metric: String = ""
)