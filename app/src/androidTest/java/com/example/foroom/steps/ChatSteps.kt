package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps {
    private val chatsPage = ChatsPage()
    private val createChatPage = CreateChatPage()

    fun openCreateChat(): ChatSteps {
        onView(chatsPage.createChatNavigationButton).tap(10)
        onView(createChatPage.chatNameField).waitUntilVisible(10)
        return this
    }

    fun createChat(chatName: String, imageIndex: Int): ChatSteps {
        onView(createChatPage.chatNameField).input(chatName)
        onView(createChatPage.chatImageOption(imageIndex)).perform(click())
        onView(createChatPage.createChatButton).tap()
        return this
    }

    fun verifyCreatedChatIsOpened(chatName: String): ChatSteps {
        onView(createChatPage.createdChatTitle(chatName)).waitUntilVisible(10)
        return this
    }

    fun closeChat(): ChatSteps {
        onView(createChatPage.closeChatButton).tap(10)
        onView(chatsPage.chatsRecyclerView).waitUntilVisible(10)
        return this
    }

    fun searchChat(chatName: String): ChatSteps {
        onView(chatsPage.searchField).input(chatName)
        return this
    }

    fun verifyChatIsDisplayedInList(chatName: String): ChatSteps {
        onView(chatsPage.chatCard(chatName)).waitUntilVisible(10)
        return this
    }
}