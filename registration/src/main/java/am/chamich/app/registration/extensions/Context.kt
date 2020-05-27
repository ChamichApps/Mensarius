package am.chamich.app.registration.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.createToast(@StringRes stringRes: Int, duration: Int = Toast.LENGTH_LONG) =
    Toast.makeText(this, stringRes, duration)

fun Context.createToast(text: String?, duration: Int = Toast.LENGTH_LONG) =
    Toast.makeText(this, text, duration)