package ru.chani.fitnesskit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.chani.fitnesskit.data.RepositoryImpl
import ru.chani.fitnesskit.data.datasource.remote.api.RetrofitInstance
import ru.chani.fitnesskit.databinding.ActivityMainBinding
import ru.chani.fitnesskit.domain.usecase.GetMapOfTrainingByDaysUseCase

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




        GlobalScope.launch(Dispatchers.IO) {
            val repository = RepositoryImpl()
            val getMapUseCase = GetMapOfTrainingByDaysUseCase(repository)
            val list = getMapUseCase()
            list.forEach { day ->
                Log.i("MEG", day.key)

                day.value.forEach { trainingItem ->
                    Log.i("MEG", "$trainingItem")
                }
            }
        }

        loadFragment(ActivitiesFragment.newInstance())

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }
}