package ru.chani.fitnesskit.data.datasource.remote

import ru.chani.fitnesskit.data.datasource.DataSource
import ru.chani.fitnesskit.data.datasource.remote.api.ApiMapper
import ru.chani.fitnesskit.data.datasource.remote.api.RetrofitInstance
import ru.chani.fitnesskit.domain.model.TrainingItem

object RemoteDataSource : DataSource {

    override suspend fun getListOfTrainingItems(): List<TrainingItem> {
        val responseClub = RetrofitInstance.api.getClubScheduleById(CLUB_ID_FOR_TESTING)
        var listOfTrainingItems = listOf<TrainingItem>()

        if (responseClub.isSuccessful) {
            val club = responseClub.body()!!
            listOfTrainingItems = ApiMapper.clubToListOfTrainingItems(club = club)
        }
        return listOfTrainingItems
    }

    private const val CLUB_ID_FOR_TESTING = 2
}