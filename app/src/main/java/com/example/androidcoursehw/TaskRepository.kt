package com.example.androidcoursehw

import com.example.androidcoursehw.model.entity.Task

object TaskRepository {
    var tasks = arrayListOf(
        Task(0, "test1", "test1", "01.01.2001"),
        Task(1, "test2", "test2", "01.01.2001"),
        Task(2, "test3", "test3", "01.01.2001"),
        Task(3, "test4", "test4", "01.01.2001"),
    )
}