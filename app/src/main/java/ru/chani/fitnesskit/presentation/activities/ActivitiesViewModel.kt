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
import java.text.SimpleDateFormat
import java.util.*

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
            listOfItemsViewType.add(ItemViewType.Date(date = convertDate(it.key)))
//            Value is a list of Training Items
            it.value.forEach { trainingItem ->
                listOfItemsViewType.add(ItemViewType.Training(trainingItem = trainingItem))
            }
        }
        _listOfViewItems.postValue(listOfItemsViewType)
    }

    private fun convertDate(date: String): String {
        val dateList = date.split("-")
        val year = dateList[0].toInt()
        val month = dateList[1].toInt()
        val day = dateList[2].toInt()

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
//        val simpleDateFormat = SimpleDateFormat("EEEE.LLLL.yyyy KK:mm:ss aaa z")
        val simpleDateFormat = SimpleDateFormat("EEEE, dd MMMM")
        val convertedDate = simpleDateFormat.format(calendar.time).toString()

        return convertedDate
    }
}