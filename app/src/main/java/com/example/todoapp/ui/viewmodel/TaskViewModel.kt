package com.example.todoapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.todoapp.models.TaskModel
import com.example.todoapp.models.TaskRepo

class TaskViewModel : ViewModel() {
    var taskRepo=TaskRepo()
    var taskList=taskRepo.getTaskList()
    var position:Int=-1

    var title: String = ""
    var description: String = ""

    fun addTask() {
        val task = TaskModel(title, description)
        val updatedList = taskList.value?.toMutableList() ?: mutableListOf()
        if(position==-1){
            updatedList.add(task)
            taskRepo.setTaskList(updatedList)
            title = ""
            description = ""
        }
        else{
            updatedList[position]=task
            taskRepo.setTaskList(updatedList)
            title=""
            description=""
            position=-1
        }
    }

    fun setSelectedTask(newObject: TaskModel){
        title=newObject.title
        description=newObject.description
    }


    fun deleteTask(){
        Log.d("oreno",this.position.toString())
        val updatedList = taskList.value?.toMutableList() ?: mutableListOf()
        updatedList.removeAt(this.position)
        taskRepo.setTaskList(updatedList)
        title=""
        description=""
        this.position=-1
    }
}
