package com.example.olimpiafitness.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.olimpiafitness.data.api.RetrofitBuilder
import com.example.olimpiafitness.data.model.Lesson
import com.example.olimpiafitness.databinding.FragmentMainBinding
import com.example.olimpiafitness.ui.viewmodel.LessonsAdapter
import com.example.olimpiafitness.ui.viewmodel.MainViewModel
import com.example.olimpiafitness.ui.viewmodel.ResponseState
import com.example.olimpiafitness.ui.viewmodel.ViewModelFactory

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels {
        ViewModelFactory(RetrofitBuilder.apiService)
    }
    private lateinit var adapter: LessonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
        viewModel.getSchedule()

    }

    private fun setupObservers() {
        viewModel.listLessons.observe(viewLifecycleOwner, Observer {
            with(binding) {
                it?.let { resource ->
                    when (resource) {
                        is ResponseState.Success -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            resource.data?.let { lessons -> retrieveList(lessons) }
                        }
                        is ResponseState.Error -> {
                            recyclerView.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@MainFragment.context,
                                resource.error.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        is ResponseState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            recyclerView.visibility = View.GONE
                        }
                    }
                }
            }
        })
    }

    private fun setupUI() =
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(this@MainFragment.context)
            adapter = LessonsAdapter(arrayListOf())
            recyclerView.adapter = adapter
        }

    private fun retrieveList(lessons: List<Lesson>) {
        adapter.apply {
            addLessons(lessons)
            notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}