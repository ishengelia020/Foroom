package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.ConversationPage
import com.example.foroom.utils.waitUntilDisplayed
import com.example.foroom.utils.waitUntilEnabled

class ConversationSteps {
    private val conversationPage = ConversationPage()
    private val chatsPage = ChatsPage()

    fun verifyConversationIsOpened(chatName: String): ConversationSteps {
        onView(conversationPage.chatTitle(chatName)).waitUntilVisible(10)
        return this
    }

    fun sendMessage(text: String): ConversationSteps {
        onView(conversationPage.sendMessageButton).waitUntilEnabled(10)
        onView(conversationPage.messageField).input(text)
        onView(conversationPage.sendMessageButton).tap()
        onView(conversationPage.sendMessageButton).waitUntilEnabled(10)
        return this
    }

    fun sendMessages(text: String, count: Int): ConversationSteps {
        repeat(count) { index ->
            sendMessage("$text ${index + 1}")
        }
        return this
    }

    fun verifyMessageIsDisplayed(text: String): ConversationSteps {
        onView(conversationPage.message(text)).waitUntilDisplayed(10)
        return this
    }

    fun verifyMessageFromSender(text: String, senderName: String): ConversationSteps {
        onView(conversationPage.messageFromSender(text, senderName)).waitUntilDisplayed(10)
        return this
    }

    fun swipeToOlderMessage(text: String): ConversationSteps {
        repeat(Constants.MAX_SWIPE_ATTEMPTS) {
            if (isMessageDisplayed(text)) return this
            swipeTowardsOlderMessages()
        }

        if (!isMessageDisplayed(text)) {
            throw AssertionError(
                "Message '$text' was not found after ${Constants.MAX_SWIPE_ATTEMPTS} swipes"
            )
        }
        return this
    }

    fun closeConversation(): ConversationSteps {
        onView(conversationPage.closeButton).tap(10)
        onView(chatsPage.chatsRecyclerView).waitUntilVisible(10)
        return this
    }

    private fun isMessageDisplayed(text: String): Boolean {
        return try {
            onView(conversationPage.message(text)).check(matches(isDisplayed()))
            true
        } catch (_: Exception) {
            false
        } catch (_: AssertionError) {
            false
        }
    }

    private fun swipeTowardsOlderMessages() {
        val location = IntArray(2)
        var listHeight = 0

        onView(conversationPage.messagesRecyclerView).check { view, noViewFoundException ->
            if (noViewFoundException != null) throw noViewFoundException
            view.getLocationOnScreen(location)
            listHeight = view.height
        }

        val startY = location[1] + (listHeight * 0.3).toInt()
        val endY = location[1] + (listHeight * 0.7).toInt()

        swiper(startY, endY, 1000)
    }
}