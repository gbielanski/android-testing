package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.assertj.core.api.Assertions.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {
    private lateinit var tasksViewModel: TasksViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewMode() {
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
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