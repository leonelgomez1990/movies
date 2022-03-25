package com.lgomez.movies.core.utils

import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    Log.w("Snackbar", message)
    Snackbar.make(this, message, duration).show()
}