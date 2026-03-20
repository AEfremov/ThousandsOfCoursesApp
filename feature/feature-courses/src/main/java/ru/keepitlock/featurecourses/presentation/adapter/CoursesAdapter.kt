package ru.keepitlock.featurecourses.presentation.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.keepitlock.featurecourses.presentation.model.CourseUi

class CoursesAdapter(
    onItemClick: (CourseUi) -> Unit = {},
    onBookmarkClick: (CourseUi) -> Unit = {},
) : AsyncListDifferDelegationAdapter<CourseUi>(CourseDiffCallback()) {

    init {
        delegatesManager.addDelegate(
            courseAdapterDelegate(onItemClick, onBookmarkClick)
        )
    }
}
class CourseDiffCallback : DiffUtil.ItemCallback<CourseUi>() {
    override fun areItemsTheSame(oldItem: CourseUi, newItem: CourseUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CourseUi, newItem: CourseUi): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: CourseUi, newItem: CourseUi): Any? {
        if (oldItem.copy(hasLike = newItem.hasLike) == oldItem) {
            return null
        }

        val oldCopy = oldItem.copy(hasLike = newItem.hasLike)
        if (oldCopy == newItem) {
            return PAYLOAD_BOOKMARK
        }

        return null
    }

    companion object {
        const val PAYLOAD_BOOKMARK = "bookmark_changed"
    }
}
