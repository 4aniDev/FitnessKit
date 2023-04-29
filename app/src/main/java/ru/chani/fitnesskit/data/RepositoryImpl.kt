package ru.chani.fitnesskit.data

import ru.chani.fitnesskit.data.datasource.remote.RemoteDataSource
import ru.chani.fitnesskit.domain.Repository
import ru.chani.fitnesskit.domain.model.TrainingItem

class RepositoryImpl : Repository {

    override suspend fun getListOfTrainings(): List<TrainingItem> {
        return RemoteDataSource.getListOfTrainingItems()
    }

}