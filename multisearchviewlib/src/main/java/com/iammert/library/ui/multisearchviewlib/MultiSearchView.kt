package com.iammert.library.ui.multisearchviewlib

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.iammert.library.ui.multisearchviewlib.databinding.ViewMultiSearchBinding
import com.iammert.library.ui.multisearchviewlib.extensions.inflate
import kotlinx.android.synthetic.main.view_multi_search.view.*

class MultiSearchView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    RelativeLayout(context, attrs, defStyleAttr) {

    interface MultiSearchViewListener {

        fun onTextChanged(index: Int, s: CharSequence)

        fun onSearchComplete(index: Int, s: CharSequence)

        fun onSearchItemRemoved(index: Int)

        fun onItemSelected(index: Int, s: CharSequence)
    }

    private val binding = inflate<ViewMultiSearchBinding>(R.layout.view_multi_search)

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.MultiSearchView, defStyleAttr, defStyleAttr)
        val searchTextStyle = typedArray.getResourceId(R.styleable.MultiSearchView_searchTextStyle, 0)

        binding.searchViewContainer.apply {
            this.searchTextStyle = searchTextStyle
        }

        binding.imageViewSearch.setOnClickListener {
            if (binding.searchViewContainer.isInSearchMode().not()) {
                binding.searchViewContainer.search()
            } else {
                binding.searchViewContainer.completeSearch()
            }
        }
    }

    fun setSearchViewListener(multiSearchViewListener: MultiSearchViewListener) {
        binding.searchViewContainer.setSearchViewListener(multiSearchViewListener)
    }
}