package com.example.todoapp.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentCreateTaskBinding
import com.example.todoapp.ui.viewmodel.TaskViewModel

class CreateTask : Fragment() {

    private lateinit var binding: FragmentCreateTaskBinding
    private val viewModel: TaskViewModel by activityViewModels()
    public var position:Int=-1;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.titleEditText.setText(viewModel.title)
        binding.descriptionEditText.setText(viewModel.description)

        binding.saveButton.setOnClickListener {
            viewModel.title = binding.titleEditText.text.toString()
            viewModel.description = binding.descriptionEditText.text.toString()

            if(viewModel.title.isNotEmpty() && viewModel.description.isNotEmpty()){

                viewModel.addTask()


                viewModel.taskList.value?.let { tasks ->
                    Log.d("TaskList", "Updated task list: $tasks")
                }

                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment1, TodoList())
                    .commit()
            }
            else{
                Toast.makeText(requireContext(),"fill all the fields",Toast.LENGTH_SHORT).show()
                binding.titleEditText.error = "fill the field"
                binding.descriptionEditText.error = "fill the field"
            }
        }

        binding.backButton.setOnClickListener{
            position=-1
            viewModel.position=-1
            viewModel.title=""
            viewModel.description=""
            Toast.makeText(requireContext(), "Changes Not Saved", Toast.LENGTH_SHORT).show()
            parentFragmentManager.beginTransaction().replace(R.id.fragment1,TodoList()).commit()
        }


    }
}
