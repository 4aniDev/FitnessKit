package ru.chani.fitnesskit.presentation.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.chani.fitnesskit.databinding.FragmentActivitiesBinding

class ActivitiesFragment : Fragment() {

    private val binding by lazy { FragmentActivitiesBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<ActivitiesViewModel>()
    private val activitiesListRvAdapter = ActivitiesRvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setObservers()
    }

    private fun setupRecyclerView() {
        binding.rv.adapter = activitiesListRvAdapter
        binding.rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setObservers() {
        viewModel.listOfViewItems.observe(viewLifecycleOwner) { newListOfItemsViewType ->
            activitiesListRvAdapter.setNewList(newListOfItemsViewType)
        }
    }


    companion object {
        fun newInstance() = ActivitiesFragment()
    }
}