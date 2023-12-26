package com.dicoding.fishify

import com.dicoding.fishify.model.StoryResponseItem

object DataDummy {

    fun generateDummyStories() : List<StoryResponseItem> {
        val newList = ArrayList<StoryResponseItem>()
        for (i in 0..10) {
            val ingredientsList = mutableListOf<String>()
            ingredientsList.add("Ingredient $i")
            // Tambahkan item ke dalam list ingredients

            val instructorsList = mutableListOf<String>()
            instructorsList.add("Instructor $i")
            // Tambahkan item ke dalam list instructors
            val stories = StoryResponseItem(
                photoUrl = "photo_url ",
                name = "Story $i",
                description = "Description $i",
                lon = 1.0,
                id = i,
                lat = 2.0,
                ingredients = ingredientsList.toList(),
                instructors = instructorsList.toList()
            )
            newList.add(stories)
        }
        return newList
    }
}