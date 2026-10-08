package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import com.example.foroom.utils.ClearSessionRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ConversationTests {

    @get:Rule(order = 0)
    val clearSessionRule = ClearSessionRule()

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val chatSteps = ChatSteps()
    private val conversationSteps = ConversationSteps()
    private val profileSteps = ProfileSteps()

    @Test
    fun sendMessageInJohnWeekChatAndVerifyItAfterReopening() {
        val message = "${Constants.DRINK_MESSAGE} ${System.currentTimeMillis()}"

        loginSteps
            .logIn(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            .verifyHomeScreenIsDisplayed()

        chatSteps.openChat(Constants.JOHN_WEEK_CHAT)

        conversationSteps
            .verifyConversationIsOpened(Constants.JOHN_WEEK_CHAT)
            .sendMessage(message)
            .verifyMessageIsDisplayed(message)
            .closeConversation()

        chatSteps.openChat(Constants.JOHN_WEEK_CHAT)

        conversationSteps
            .verifyConversationIsOpened(Constants.JOHN_WEEK_CHAT)
            .verifyMessageIsDisplayed(message)
    }

    @Test
    fun sendQuestionInOwnChat() {
        val question = "${Constants.MODULE_QUESTION} ${System.currentTimeMillis()}"

        loginSteps
            .logIn(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            .verifyHomeScreenIsDisplayed()

        chatSteps.openChat(Constants.CHAT_NAME)

        conversationSteps
            .verifyConversationIsOpened(Constants.CHAT_NAME)
            .sendMessage(question)
            .verifyMessageIsDisplayed(question)
    }

    @Test
    fun continueConversationUsingAnotherAccount() {
        val suffix = System.currentTimeMillis()
        val greeting = "${Constants.GREETING_MESSAGE} $suffix"
        val reply = "${Constants.REPLY_MESSAGE} $suffix"

        loginSteps
            .logIn(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            .verifyHomeScreenIsDisplayed()

        chatSteps.openChat(Constants.SHARED_CHAT)

        conversationSteps
            .verifyConversationIsOpened(Constants.SHARED_CHAT)
            .sendMessage(greeting)
            .verifyMessageIsDisplayed(greeting)
            .sendMessages(Constants.FILLER_MESSAGE, Constants.FILLER_MESSAGES_COUNT)
            .closeConversation()

        profileSteps.signOut()

        loginSteps
            .logIn(Constants.SECOND_USERNAME, Constants.SECOND_PASSWORD)
            .verifyHomeScreenIsDisplayed()

        chatSteps.openChat(Constants.SHARED_CHAT)

        conversationSteps
            .verifyConversationIsOpened(Constants.SHARED_CHAT)
            .swipeToOlderMessage(greeting)
            .verifyMessageFromSender(greeting, Constants.TEST_USERNAME)
            .sendMessage(reply)
            .verifyMessageIsDisplayed(reply)
            .closeConversation()

        profileSteps.signOut()

        loginSteps
            .logIn(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            .verifyHomeScreenIsDisplayed()

        chatSteps.openChat(Constants.SHARED_CHAT)

        conversationSteps
            .verifyConversationIsOpened(Constants.SHARED_CHAT)
            .verifyMessageFromSender(reply, Constants.SECOND_USERNAME)
    }
}