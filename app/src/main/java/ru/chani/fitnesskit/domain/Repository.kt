package ru.chani.fitnesskit.domain

import ru.chani.fitnesskit.domain.model.TrainingItem

interface Repository {

    suspend fun getListOfTrainings(): List<TrainingItem>

}