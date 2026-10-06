package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {
    private val chatsPage = ChatsPage()
    private val profilePage = ProfilePage()
    private val changePasswordPage = ChangePasswordPage()
    private val changeLanguagePage = ChangeLanguagePage()

    fun openProfile(): ProfileSteps {
        onView(chatsPage.profileNavigationButton).tap(10)
        onView(profilePage.changePasswordItem).waitUntilVisible(10)
        return this
    }

    fun changePassword(newPassword: String): ProfileSteps {
        onView(profilePage.changePasswordItem).tap()
        onView(changePasswordPage.passwordField).input(newPassword)
        onView(changePasswordPage.repeatPasswordField).input(newPassword)
        onView(changePasswordPage.confirmButton).tap()
        return this
    }

    fun selectGeorgianLanguage(): ProfileSteps {
        onView(profilePage.changeLanguageItem).tap(10)
        onView(changeLanguagePage.georgianButton).tap(10)
        return this
    }

    fun selectEnglishLanguage(): ProfileSteps {
        onView(profilePage.changeLanguageItem).tap(10)
        onView(changeLanguagePage.englishButton).tap(10)
        return this
    }

    fun verifyChangeLanguageLabel(expectedLabel: String): ProfileSteps {
        onView(profilePage.changeLanguageLabel(expectedLabel)).waitUntilVisible(10)
        return this
    }
}