package com.example.todoapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.models.TaskModel
import java.text.FieldPosition

class TaskViewModel : ViewModel() {
    private val _taskList = MutableLiveData<MutableList<TaskModel>>(mutableListOf())
    val taskList: LiveData<MutableList<TaskModel>> get() = _taskList
    var position:Int=-1

    var title: String = ""
    var description: String = ""

    fun addTask() {
        val task = TaskModel(title, description)
        val updatedList = _taskList.value?.toMutableList() ?: mutableListOf()
        if(position==-1){
            updatedList.add(task)
            _taskList.value = updatedList
            title = ""
            description = ""
        }
        else{
            updatedList[position]=task
            _taskList.value=updatedList
            title=""
            description=""
            position=-1
        }
    }

    fun setSelectedTask(newObject:TaskModel,position: Int){

        title=newObject.title
        description=newObject.description
    }
}
