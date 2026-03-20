package ru.keepitlock.featurecourses.presentation.adapter

import androidx.core.content.ContextCompat
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.keepitlock.featurecourses.R
import ru.keepitlock.coreui.R as CoreUiR
import ru.keepitlock.featurecourses.databinding.ItemCourseBinding
import ru.keepitlock.featurecourses.presentation.model.CourseUi

fun courseAdapterDelegate(
    onItemClick: (CourseUi) -> Unit = {},
    onBookmarkClick: (CourseUi) -> Unit = {},
) = adapterDelegateViewBinding<CourseUi, CourseUi, ItemCourseBinding>(
    { layoutInflater, parent -> ItemCourseBinding.inflate(layoutInflater, parent, false) }
) {

    binding.root.setOnClickListener {
        onItemClick(item)
    }

    binding.btnBookmark.setOnClickListener {
        onBookmarkClick(item)
    }

    bind { payloads ->
        if (payloads.isEmpty()) {
            binding.tvCourseTitle.text = item.title
            binding.tvCourseDescription.text = item.description
            binding.tvCoursePrice.text = item.price
            binding.tvRating.text = item.rating
            binding.tvCourseDate.text = item.startDate
            binding.updateBookmarkIcon(item)
        } else {
            if (CourseDiffCallback.PAYLOAD_BOOKMARK in payloads) {
                binding.updateBookmarkIcon(item)
            }
        }
    }
}
private fun ItemCourseBinding.updateBookmarkIcon(item: CourseUi) {
    val context = btnBookmark.context
    val (iconRes, colorRes) = if (item.hasLike) {
        R.drawable.ic_bookmark_selected to CoreUiR.color.color_primary_green
    } else {
        R.drawable.ic_bookmark to CoreUiR.color.text_primary
    }

    btnBookmark.setImageResource(iconRes)
    btnBookmark.setColorFilter(
        ContextCompat.getColor(context, colorRes),
        android.graphics.PorterDuff.Mode.SRC_IN
    )
}
