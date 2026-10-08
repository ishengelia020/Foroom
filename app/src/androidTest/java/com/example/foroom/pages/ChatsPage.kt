package com.example.foroom.pages

import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.components.chat.ForoomChatCardView
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class ChatsPage {
    val navBar: Matcher<View> = withId(R.id.navBar)
    val chatsNavigationButton: Matcher<View> = withId(R.id.homeNavigationChats)
    val createChatNavigationButton: Matcher<View> = withId(R.id.homeNavigationCreateChat)
    val profileNavigationButton: Matcher<View> = withId(R.id.homeNavigationProfile)

    val chatsRecyclerView: Matcher<View> = withId(R.id.chatsRecyclerView)

    val searchField: Matcher<View> = allOf(
        isAssignableFrom(EditText::class.java),
        isDescendantOfA(withId(R.id.searchChatInput))
    )

    fun chatCard(chatName: String): Matcher<View> = allOf(
        withId(DesignR.id.chatTitleTextView),
        withText(chatName),
        isDescendantOfA(withId(R.id.chatsRecyclerView))
    )

    fun openChatButton(chatName: String): Matcher<View> = allOf(
        withId(DesignR.id.sendMessageButton),
        isDescendantOfA(
            allOf(
                isAssignableFrom(ForoomChatCardView::class.java),
                hasDescendant(chatCard(chatName))
            )
        )
    )
}