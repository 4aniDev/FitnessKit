package ru.chani.fitnesskit.presentation.activities.model

import ru.chani.fitnesskit.domain.model.TrainingItem

sealed class ItemViewType() {

    class Date(val date: String) : ItemViewType() {
        companion object {
            const val VIEW_TYPE_ID = 1
        }
    }

    class Training(val trainingItem: TrainingItem) : ItemViewType(){
        companion object {
            const val VIEW_TYPE_ID = 2
        }
    }
}
