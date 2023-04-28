package ru.chani.fitnesskit.domain.model

data class TrainingItem(
    val date: String,
    val statTime: String,
    val endTime: String,
    val typeOfTraining: String,
    val coachName: String,
    val place: String
)
