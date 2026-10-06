package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import com.example.foroom.utils.ClearSessionRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule(order = 0)
    val clearSessionRule = ClearSessionRule()

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Test
    fun changePasswordAndVerifyIt() {
        loginSteps
            .logIn(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            .verifyHomeScreenIsDisplayed()

        profileSteps
            .openProfile()
            .changePassword(Constants.NEW_PASSWORD)

        loginSteps
            .verifyLoginScreenIsDisplayed()
            .logIn(Constants.TEST_USERNAME, Constants.NEW_PASSWORD)
            .verifyHomeScreenIsDisplayed()

        profileSteps
            .openProfile()
            .changePassword(Constants.TEST_PASSWORD)

        loginSteps.verifyLoginScreenIsDisplayed()
    }

    @Test
    fun changeLanguageFromGeorgianToEnglishAndBack() {
        loginSteps
            .logIn(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            .verifyHomeScreenIsDisplayed()

        profileSteps
            .openProfile()
            .selectGeorgianLanguage()
            .verifyChangeLanguageLabel(Constants.CHANGE_LANGUAGE_LABEL_GEO)
            .selectEnglishLanguage()
            .verifyChangeLanguageLabel(Constants.CHANGE_LANGUAGE_LABEL_ENG)
            .selectGeorgianLanguage()
            .verifyChangeLanguageLabel(Constants.CHANGE_LANGUAGE_LABEL_GEO)
    }

    @Test
    fun createChatAndFindItInChatList() {
        val chatName = "${Constants.CHAT_NAME} ${System.currentTimeMillis()}"

        loginSteps
            .logIn(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            .verifyHomeScreenIsDisplayed()

        chatSteps
            .openCreateChat()
            .createChat(chatName, Constants.CHAT_IMAGE_INDEX)
            .verifyCreatedChatIsOpened(chatName)
            .closeChat()
            .searchChat(chatName)
            .verifyChatIsDisplayedInList(chatName)
    }
}