package ru.chani.fitnesskit.domain.usecase

import ru.chani.fitnesskit.domain.Repository
import ru.chani.fitnesskit.domain.model.TrainingItem

class GetMapOfTrainingByDaysUseCase(private val repository: Repository) {
    suspend operator fun invoke(repository: Repository): Map<String, List<TrainingItem>> {

        val listOfTrainingItems = repository.getListOfTrainings()
        val map = listTrainingsToMapTrainings(listOfTrainingItems)

        /**
         * Sort trainings by startTime in Map
         */
        map.forEach { trainingDay ->
            trainingDay.value.sortBy { it.statTime }
        }

        return map
    }


    private fun listTrainingsToMapTrainings(list: List<TrainingItem>): Map<String, MutableList<TrainingItem>>  {
        val map = mutableMapOf<String, MutableList<TrainingItem>>()
        list.forEach { trainingItem ->
            if (map[trainingItem.date].isNullOrEmpty()) {
                map[trainingItem.date] = mutableListOf<TrainingItem>()
                map[trainingItem.date]!!.add(trainingItem)

            } else {
                map[trainingItem.date]!!.add(trainingItem)
            }
        }
        return map
    }

}