package am.chamich.app.registration.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(@StringRes stringRes: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, stringRes, duration).show()
}