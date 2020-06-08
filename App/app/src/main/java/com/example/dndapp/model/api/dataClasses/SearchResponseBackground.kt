package com.example.dndapp.model.api.dataClasses

import com.google.gson.annotations.SerializedName

data class Background (
    @SerializedName("name") val name : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("slug") val slug : String,
    @SerializedName("skill_proficiencies") val skill_proficiencies : String,
    @SerializedName("tool_proficiencies") val tool_proficiencies : String,
    @SerializedName("languages") val languages : String,
    @SerializedName("equipment") val equipment : String,
    @SerializedName("feature") val feature : String,
    @SerializedName("feature_desc") val feature_desc : String,
    @SerializedName("suggested_characteristics") val suggested_characteristics : String,
    @SerializedName("document__slug") val document__slug : String,
    @SerializedName("document__title") val document__title : String,
    @SerializedName("document__license_url") val document__license_url : String
)

data class BackgroundResult (
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String,
    @SerializedName("results") val results : List<Background>
)