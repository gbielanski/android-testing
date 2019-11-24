package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Test
import org.assertj.core.api.Assertions.*

private const val TEST_TITLE = "title"
private const val TEST_DESC = "description"

class StatisticsUtilsTest {
    @Test
    fun getActiveAndCompletedTasks_noCompleted_returnHundredZero() {
        //given
        val tasks = listOf(Task(TEST_TITLE, TEST_DESC, false))
        //when
        val stats = getActiveAndCompletedStats(tasks)
        //then
        assertThat(stats.activeTasksPercent).isEqualTo(100f)
        assertThat(stats.completedTasksPercent).isEqualTo(0f)
    }

    @Test
    fun getActiveAndCompletedTasks_twoCompletedThreeActive_returnSixtyForty() {
        //given
        val tasks = listOf(
                Task(TEST_TITLE, TEST_DESC, true),
                Task(TEST_TITLE, TEST_DESC, true),
                Task(TEST_TITLE, TEST_DESC, false),
                Task(TEST_TITLE, TEST_DESC, false),
                Task(TEST_TITLE, TEST_DESC, false)
        )
        //when
        val stats = getActiveAndCompletedStats(tasks)
        //then
        assertThat(stats.activeTasksPercent).isEqualTo(60f)
        assertThat(stats.completedTasksPercent).isEqualTo(40f)
    }

    @Test
    fun getActiveAndCompletedTasks_loadProblem_returnZeros() {
        //give
        val tasks = null
        //when
        val stats = getActiveAndCompletedStats(tasks)
        //then
        assertThat(stats.completedTasksPercent).isEqualTo(0f)
        assertThat(stats.activeTasksPercent).isEqualTo(0f)
    }

    @Test
    fun getActiveAndCompletedTasks_emptyList_returnZeros() {
        //give
        val tasks = emptyList<Task>()
        //when
        val stats = getActiveAndCompletedStats(tasks)
        //then
        assertThat(stats.completedTasksPercent).isEqualTo(0f)
        assertThat(stats.activeTasksPercent).isEqualTo(0f)
    }
}