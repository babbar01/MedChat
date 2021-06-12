package com.babbarandrotech.medchat

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
//    Log.d(ChatFragment.TAG, "hideKeyboard: called")
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
//    Log.d(ChatFragment.TAG, "hideKeyboard: in activity called")
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {

//    Log.d(ChatFragment.TAG, "hideKeyboard: in context called")
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun ScrollView.scrollToBottomWithoutFocusChange() { // Kotlin extension to scrollView
    val lastChild = getChildAt(childCount - 1)
    val bottom = lastChild.bottom + paddingBottom
    val delta = bottom - (scrollY + height)
    smoothScrollBy(0, delta)
}

