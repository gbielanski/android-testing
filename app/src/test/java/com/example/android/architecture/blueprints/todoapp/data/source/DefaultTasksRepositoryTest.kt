package com.example.android.architecture.blueprints.todoapp.data.source

import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.assertj.core.api.Assertions.*

class DefaultTasksRepositoryTest{
    private val task1 = Task("Title1", "Description1")
    private val task2 = Task("Title2", "Description2")
    private val task3 = Task("Title3", "Description3")
    private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }
    private lateinit var taskRemoteDataSource: FakeDataSource
    private lateinit var taskLocalDataSource: FakeDataSource
    private lateinit var taskRepository: DefaultTasksRepository

    @Before
    fun createRepository(){
        taskRemoteDataSource = FakeDataSource(remoteTasks.toMutableList())
        taskLocalDataSource = FakeDataSource(localTasks.toMutableList())
        taskRepository= DefaultTasksRepository(taskRemoteDataSource, taskLocalDataSource, Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getTasks_requestAllTasksFromRemoteDataSource() = runBlockingTest{
        //given
        //when
        val tasks = taskRepository.getTasks(true) as Result.Success
        //then
        assertThat(remoteTasks).containsExactly(task1, task2)
    }
}