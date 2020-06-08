package com.example.dndapp.model.api.dataClasses

import com.google.gson.annotations.SerializedName

data class Race (
    @SerializedName("name") val name : String,
    @SerializedName("slug") val slug : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("asi_desc") val asi_desc : String,
    @SerializedName("asi") val asi : List<Asi>,
    @SerializedName("age") val age : String,
    @SerializedName("alignment") val alignment : String,
    @SerializedName("size") val size : String,
    @SerializedName("speed") val speed : Speed,
    @SerializedName("speed_desc") val speed_desc : String,
    @SerializedName("languages") val languages : String,
    @SerializedName("vision") val vision : String,
    @SerializedName("traits") val traits : String,
    @SerializedName("subraces") val subraces : List<Subrace>,
    @SerializedName("document__slug") val document__slug : String,
    @SerializedName("document__title") val document__title : String,
    @SerializedName("document__license_url") val document__license_url : String

)

data class RaceResult (
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String,
    @SerializedName("results") val results : List<Race>
)

data class Speed (
    @SerializedName("walk") val walk : Int
)

data class Subrace (
    @SerializedName("name") val name : String,
    @SerializedName("slug") val slug : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("asi") val asi : List<Asi>,
    @SerializedName("traits") val traits : String,
    @SerializedName("asi_desc") val asi_desc : String,
    @SerializedName("document__slug") val document__slug : String,
    @SerializedName("document__title") val document__title : String
)

data class Asi (
    @SerializedName("attributes") val attributes : List<String>,
    @SerializedName("value") val value : Int
)