package ru.keepitlock.coreui

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setVisibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
