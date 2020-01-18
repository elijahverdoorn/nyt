package com.elijahverdoorn.nyt.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.elijahverdoorn.nyt.R

class HomeListItemComponent @JvmOverloads constructor(
    context: Context?, attrs: AttributeSet?, defStyleAttr: Int
): ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.component_home_list_item, this)
    }
}