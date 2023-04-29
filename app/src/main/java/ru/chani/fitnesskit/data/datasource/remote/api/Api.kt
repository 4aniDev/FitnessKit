package ru.chani.fitnesskit.data.datasource.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.chani.fitnesskit.data.datasource.remote.api.dto.Club

interface Api {

    @GET("schedule/get_v3/")
    suspend fun getClubScheduleById(
        @Query("club_id") clubId: Int
    ): Response<Club>

}