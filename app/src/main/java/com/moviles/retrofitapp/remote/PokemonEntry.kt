package com.moviles.retrofitapp.remote

import com.google.gson.annotations.SerializedName

data class PokemonEntry(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("types")
    val types: List<TypesEntry>,
    @SerializedName("stats")
    val stats: List<StatsEntry>,
    @SerializedName("sprites")
    val sprites: SpritesEntry
)

data class TypesEntry(
    @SerializedName("type")
    val type: TypeName
)

data class TypeName(
    @SerializedName("name")
    val name: String,
)

data class StatsEntry(
    @SerializedName("base_stat")
    val base_stat: Int,
    @SerializedName("stat")
    val stat: StatName
)

data class StatName(
    @SerializedName("name")
    val name: String,
)

data class SpritesEntry(
    @SerializedName("front_default")
    val front_default: String
)