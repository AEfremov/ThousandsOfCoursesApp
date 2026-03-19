package ru.keepitlock.thousandsofcoursesapp

import android.app.Application
import ru.keepitlock.coredi.ComponentHolder
import ru.keepitlock.thousandsofcoursesapp.di.AppComponent
import ru.keepitlock.thousandsofcoursesapp.di.DaggerAppComponent

class App : Application(), ComponentHolder<AppComponent> {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun getComponent(): AppComponent = appComponent

    companion object {
        lateinit var instance: App
            private set
    }

}