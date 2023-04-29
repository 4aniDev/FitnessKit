package ru.chani.fitnesskit.data.datasource.remote.api

import ru.chani.fitnesskit.data.datasource.remote.api.dto.Club
import ru.chani.fitnesskit.domain.model.TrainingItem

object ApiMapper {
    fun clubToListOfTrainingItems(club: Club): List<TrainingItem> {
        val newTrainingItemSet = mutableSetOf<TrainingItem>()

        club.lessons.forEach { lesson ->
            val newTrainingItem = TrainingItem(
                date = lesson.date,
                statTime = lesson.startTime,
                endTime = lesson.endTime,
                typeOfTraining = lesson.name,
                coachName = getCoachFullNameById(club = club, id = lesson.coach_id),
                place = lesson.place
            )
            newTrainingItemSet.add(newTrainingItem)
        }

        val sortedList = newTrainingItemSet.sortedBy { it.date }

        return sortedList
    }

    private fun getCoachFullNameById(club: Club, id: String): String {
        val trainer = club.trainers.find { it.id == id }
        return trainer?.full_name ?: WITHOUT_TRAINER
    }

    private const val WITHOUT_TRAINER = "WITHOUT TRAINER"
}