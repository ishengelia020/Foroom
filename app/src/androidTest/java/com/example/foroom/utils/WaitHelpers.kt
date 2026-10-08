package com.example.foroom.utils

import android.view.View
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun ViewInteraction.waitUntilDisplayed(timeoutSec: Long): ViewInteraction {
    return waitUntilMatches(isDisplayed(), timeoutSec)
}

fun ViewInteraction.waitUntilEnabled(timeoutSec: Long): ViewInteraction {
    return waitUntilMatches(allOf(isDisplayed(), isEnabled()), timeoutSec)
}

private fun ViewInteraction.waitUntilMatches(
    condition: Matcher<View>,
    timeoutSec: Long
): ViewInteraction {
    val endTime = System.currentTimeMillis() + timeoutSec * 1000
    var lastError: Throwable? = null

    do {
        try {
            check(matches(condition))
            return this
        } catch (error: Exception) {
            lastError = error
        } catch (error: AssertionError) {
            lastError = error
        }
        Thread.sleep(50)
    } while (System.currentTimeMillis() < endTime)

    throw AssertionError("Condition was not met within $timeoutSec seconds", lastError)
}