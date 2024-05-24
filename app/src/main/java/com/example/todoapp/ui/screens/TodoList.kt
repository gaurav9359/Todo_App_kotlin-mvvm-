package com.example.todoapp.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.example.todoapp.ui.adapters.TaskAdapter
import com.example.todoapp.ui.viewmodel.TaskViewModel


class TodoList : Fragment(), TaskAdapter.OnItemClickListener {

    private lateinit var binding: FragmentTodoListBinding
    private val viewModel: TaskViewModel by activityViewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(viewModel.taskList.value!!.size==0){
            binding.emptyList.visibility=View.VISIBLE
        }

        binding.openDialogueButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment1, CreateTask())
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        setupRecyclerView()
        observeTaskList()
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(mutableListOf(),this)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.adapter = adapter
    }

    private fun observeTaskList() {
        viewModel.taskList.observe(viewLifecycleOwner, Observer { tasks ->
            viewModel.taskList.value?.let { tasks1 ->
                Log.d("TaskList22", "Updated task list: $tasks1")
            }
            adapter.updateTasks(tasks.toMutableList())
        })
    }

    override fun onItemClick(position: Int) {
        viewModel.position=position;
        val clickedTask = adapter.taskList[position]
        Log.d("TaskClick", "Clicked task: $clickedTask")
        viewModel.setSelectedTask(clickedTask)

        parentFragmentManager.beginTransaction().replace(R.id.fragment1,CreateTask()).commit()
    }
}