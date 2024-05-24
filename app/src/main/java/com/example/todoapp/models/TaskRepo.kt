package com.example.todoapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TaskRepo {
    private var _taskList = MutableLiveData<MutableList<TaskModel>>(mutableListOf())
    private val taskList: LiveData<MutableList<TaskModel>> = _taskList

    fun getTaskList():LiveData<MutableList<TaskModel>>{
        return this.taskList
    }

    fun setTaskList(newTaskList:MutableList<TaskModel>){
        this._taskList.value=newTaskList
    }
}