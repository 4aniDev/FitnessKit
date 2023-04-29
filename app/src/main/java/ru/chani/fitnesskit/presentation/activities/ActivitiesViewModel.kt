package ru.chani.fitnesskit.presentation.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.chani.fitnesskit.data.RepositoryImpl
import ru.chani.fitnesskit.domain.model.TrainingItem
import ru.chani.fitnesskit.domain.usecase.GetMapOfTrainingByDaysUseCase
import ru.chani.fitnesskit.presentation.activities.model.ItemViewType

class ActivitiesViewModel : ViewModel() {

    private val repository = RepositoryImpl()
    private val getMapOfTrainingByDaysUseCase = GetMapOfTrainingByDaysUseCase(repository)

    private val _listOfViewItems = MutableLiveData<List<ItemViewType>>()
    val listOfViewItems: LiveData<List<ItemViewType>> = _listOfViewItems

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val map = getMapOfTrainingByDaysUseCase.invoke(repository)
            mapToListOfItemViews(map)
        }
    }

    private fun mapToListOfItemViews(map: Map<String, List<TrainingItem>>) {
        val listOfItemsViewType = mutableListOf<ItemViewType>()

//        Inflate listOfItemsViewType
        map.forEach {
//            Key is a date
            listOfItemsViewType.add(ItemViewType.Date(date = it.key))
//            Value is a list of Training Items
            it.value.forEach { trainingItem ->
                listOfItemsViewType.add(ItemViewType.Training(trainingItem = trainingItem))
            }
        }
        _listOfViewItems.postValue(listOfItemsViewType)
    }
}