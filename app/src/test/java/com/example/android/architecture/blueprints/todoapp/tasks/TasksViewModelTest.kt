package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.junit.Rule
import org.junit.Test
import org.assertj.core.api.Assertions.*
import org.junit.Before

class TasksViewModelTest {
    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var tasksRepository: FakeTestRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)
        tasksViewModel = TasksViewModel(tasksRepository)

    }
    @Test
    fun addNewTask_setsNewTaskEvent() {
        //given
        //when
        tasksViewModel.addNewTask()
        //then
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value).isNotNull
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {
        //given
        //when
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)
        //then
        val value = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value).isTrue()
    }
}