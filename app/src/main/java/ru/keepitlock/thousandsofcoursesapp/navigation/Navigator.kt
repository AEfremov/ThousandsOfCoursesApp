package ru.keepitlock.thousandsofcoursesapp.navigation

import android.content.Context
import android.content.Intent
import ru.keepitlock.featureauth.presentation.AuthNavigator
import ru.keepitlock.featurecourses.presentation.MainActivity

class AppNavigator(private val context: Context) : AuthNavigator {

    override fun navigateToMain() {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }
}