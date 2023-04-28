package ru.chani.fitnesskit.data.datasource

import ru.chani.fitnesskit.domain.model.TrainingItem

interface DataSource {

    suspend fun getListOfTrainingItems(): List<TrainingItem>

}