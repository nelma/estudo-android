package com.example.estudoandroid.api

import com.google.gson.annotations.SerializedName

data class GithubRepositoriesResult (

//        @field:Json(name = "items")
    @SerializedName("items")
    val repositories: List<Repository>
)
