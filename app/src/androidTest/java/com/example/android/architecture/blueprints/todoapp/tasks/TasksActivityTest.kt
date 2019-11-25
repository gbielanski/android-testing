package com.example.android.architecture.blueprints.todoapp.tasks


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TasksActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TasksActivity::class.java)

    @Test
    fun tasksActivityTest() {
        val floatingActionButton = onView(
                allOf(withId(R.id.add_task_fab),
                        childAtPosition(
                                allOf(withId(R.id.coordinator_layout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                1),
                        isDisplayed()))
        floatingActionButton.perform(click())

        val appCompatEditText = onView(
                allOf(withId(R.id.add_task_title_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                0)))
        appCompatEditText.perform(scrollTo(), replaceText("te"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
                allOf(withId(R.id.add_task_title_edit_text), withText("te"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                0)))
        appCompatEditText2.perform(scrollTo(), click())

        val appCompatEditText3 = onView(
                allOf(withId(R.id.add_task_title_edit_text), withText("te"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                0)))
        appCompatEditText3.perform(scrollTo(), replaceText("test"))

        val appCompatEditText4 = onView(
                allOf(withId(R.id.add_task_title_edit_text), withText("test"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                0),
                        isDisplayed()))
        appCompatEditText4.perform(closeSoftKeyboard())

        val appCompatEditText5 = onView(
                allOf(withId(R.id.add_task_description_edit_text),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                1)))
        appCompatEditText5.perform(scrollTo(), replaceText("tessst"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
                allOf(withId(R.id.add_task_description_edit_text), withText("tessst"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                1)))
        appCompatEditText6.perform(scrollTo(), click())

        val floatingActionButton2 = onView(
                allOf(withId(R.id.save_task_fab),
                        childAtPosition(
                                allOf(withId(R.id.coordinator_layout),
                                        childAtPosition(
                                                withId(R.id.nav_host_fragment),
                                                0)),
                                1),
                        isDisplayed()))
        floatingActionButton2.perform(click())

        val appCompatCheckBox = onView(
                allOf(withId(R.id.complete_checkbox),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tasks_list),
                                        2),
                                0),
                        isDisplayed()))
        appCompatCheckBox.perform(click())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
