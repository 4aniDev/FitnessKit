package ru.chani.fitnesskit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.chani.fitnesskit.databinding.ActivityMainBinding
import ru.chani.fitnesskit.databinding.FragmentActivitiesBinding

class ActivitiesFragment : Fragment() {

    private val binding by lazy { FragmentActivitiesBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    companion object {

        fun newInstance() = ActivitiesFragment()
    }
}