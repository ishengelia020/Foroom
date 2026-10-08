package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.foroom.Helper.withIndex
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import com.example.design_system.R as DesignR

class CreateChatPage {
    val chatNameField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(withId(R.id.chatNameInput))
    )
    val createChatButton: Matcher<View> = withId(R.id.createChatButton)

    fun chatImageOption(index: Int): Matcher<View> = withIndex(
        allOf(
            isAssignableFrom(ImageChooserItemView::class.java),
            isDescendantOfA(withId(R.id.chatImageChooser))
        ),
        index
    )

    val closeChatButton: Matcher<View> = allOf(
        withId(R.id.closeButton),
        hasSibling(withId(R.id.messagesRecyclerView))
    )

    fun createdChatTitle(chatName: String): Matcher<View> = allOf(
        withText(chatName),
        isDescendantOfA(
            allOf(
                withId(R.id.chatHeaderView),
                hasSibling(withId(R.id.messagesRecyclerView))
            )
        )
    )
}