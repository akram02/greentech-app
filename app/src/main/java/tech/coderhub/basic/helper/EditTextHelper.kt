package tech.coderhub.basic.helper

import android.widget.TextView

fun checkEmptyTextView(view: TextView, errorMessage: String): Boolean {
    if (view.text.toString().trim().isEmpty()) {
        view.error = errorMessage
        return true
    }
    return false
}