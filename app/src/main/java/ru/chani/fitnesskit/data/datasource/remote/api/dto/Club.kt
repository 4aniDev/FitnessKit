package ru.chani.fitnesskit.data.datasource.remote.api.dto

data class Club(
    val lessons: List<Lesson>,
    val option: Option,
    val tabs: List<Tab>,
    val trainers: List<Trainer>
)