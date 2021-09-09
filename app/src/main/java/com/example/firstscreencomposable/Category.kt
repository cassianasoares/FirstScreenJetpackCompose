package com.example.firstscreencomposable

data class Category(val icon:Int, val name: String)

val categoryList = arrayListOf(
    Category(R.drawable.ic_games, "Gamer"),
    Category(R.drawable.ic_work, "Work"),
    Category(R.drawable.ic_movie, "Movie"),
)