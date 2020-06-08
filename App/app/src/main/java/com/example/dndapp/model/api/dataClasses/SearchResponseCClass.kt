package com.example.dndapp.model.api.dataClasses

import com.google.gson.annotations.SerializedName

data class CClass (
    @SerializedName("name") val name : String,
    @SerializedName("slug") val slug : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("hit_dice") val hit_dice : String,
    @SerializedName("hp_at_1st_level") val hp_at_1st_level : String,
    @SerializedName("hp_at_higher_levels") val hp_at_higher_levels : String,
    @SerializedName("prof_armor") val prof_armor : String,
    @SerializedName("prof_weapons") val prof_weapons : String,
    @SerializedName("prof_tools") val prof_tools : String,
    @SerializedName("prof_saving_throws") val prof_saving_throws : String,
    @SerializedName("prof_skills") val prof_skills : String,
    @SerializedName("equipment") val equipment : String,
    @SerializedName("table") val table : String,
    @SerializedName("spellcasting_ability") val spellcasting_ability : String,
    @SerializedName("subtypes_name") val subtypes_name : String,
    @SerializedName("archetypes") val archetypes : List<Archetypes>,
    @SerializedName("document__slug") val document__slug : String,
    @SerializedName("document__title") val document__title : String,
    @SerializedName("document__license_url") val document__license_url : String
)

data class CClassResult (
    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String,
    @SerializedName("results") val results : List<CClass>
)

data class Archetypes (
    @SerializedName("name") val name : String,
    @SerializedName("slug") val slug : String,
    @SerializedName("desc") val desc : String,
    @SerializedName("document__slug") val document__slug : String,
    @SerializedName("document__title") val document__title : String,
    @SerializedName("document__license_url") val document__license_url : String
)