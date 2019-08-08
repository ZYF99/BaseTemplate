package com.example.basetemplate.main.manager

import com.squareup.moshi.Json
import java.io.Serializable

data class TitleModel(
    @Json(name = "titleTxt") val titleTxt:String?,
    @Json(name = "address") val address:String?,
    @Json(name = "level") val level:Int?
):Serializable