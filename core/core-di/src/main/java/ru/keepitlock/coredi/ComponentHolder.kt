package ru.keepitlock.coredi

interface ComponentHolder<T> {
    fun getComponent(): T
}
