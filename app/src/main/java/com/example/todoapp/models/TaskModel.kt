package com.example.todoapp.models

data class TaskModel(var title: String, var description: String) {
    override fun toString(): String {
        return "TaskModel(title='$title', description='$description')"
    }
}