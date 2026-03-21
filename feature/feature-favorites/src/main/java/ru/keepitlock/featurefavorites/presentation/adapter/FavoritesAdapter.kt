package ru.keepitlock.featurefavorites.presentation.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.keepitlock.coreui.adapter.CourseDiffCallback
import ru.keepitlock.coreui.adapter.courseAdapterDelegate
import ru.keepitlock.coreui.model.CourseUi

class FavoritesAdapter(
    onItemClick: (CourseUi) -> Unit = {},
    onBookmarkClick: (CourseUi) -> Unit = {},
) : AsyncListDifferDelegationAdapter<CourseUi>(CourseDiffCallback()) {

    init {
        delegatesManager.addDelegate(
            courseAdapterDelegate(onItemClick, onBookmarkClick)
        )
    }
}