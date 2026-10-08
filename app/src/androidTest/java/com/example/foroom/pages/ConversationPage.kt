package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class ConversationPage {
    val messagesRecyclerView: Matcher<View> = withId(R.id.messagesRecyclerView)

    val messageField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(withId(R.id.messageInput))
    )

    val sendMessageButton: Matcher<View> = allOf(
        withId(R.id.sendMessageButton),
        isDescendantOfA(withId(R.id.messageInput))
    )

    val closeButton: Matcher<View> = allOf(
        withId(R.id.closeButton),
        hasSibling(withId(R.id.messagesRecyclerView))
    )

    fun chatTitle(chatName: String): Matcher<View> = allOf(
        withText(chatName),
        isDescendantOfA(
            allOf(
                withId(R.id.chatHeaderView),
                hasSibling(withId(R.id.messagesRecyclerView))
            )
        )
    )

    fun message(text: String): Matcher<View> = allOf(
        withId(DesignR.id.messageTextView),
        withText(text),
        isDescendantOfA(withId(R.id.messagesRecyclerView))
    )

    fun messageFromSender(text: String, senderName: String): Matcher<View> = allOf(
        withId(DesignR.id.messageTextView),
        withText(text),
        isDescendantOfA(
            allOf(
                withId(DesignR.id.contentLinearLayout),
                hasDescendant(
                    allOf(
                        withId(DesignR.id.userNameTextView),
                        withText(senderName)
                    )
                )
            )
        )
    )
}