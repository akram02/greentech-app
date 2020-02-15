package tech.coderhub.greenapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.popup_select.view.*
import org.jetbrains.anko.makeCall
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onTouch
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

fun populateDatePicker(context: Context, dateEditText: EditText) {
    val calendar = Calendar.getInstance()
    val datePicker = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        dateEditText.setText(SimpleDateFormat("yyyy-MM-dd", Locale.US).format(calendar.time))
    }
    DatePickerDialog(
        context, datePicker,
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
fun getFirstDateOfMonth(): String {
    val date = Calendar.getInstance()
    date.set(Calendar.DAY_OF_MONTH, 1)
    return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date.time)+"T00:00:00.000Z"
}
fun getLastDateOfMonth(): String {
    val date = Calendar.getInstance()
    date.add(Calendar.MONTH, 1)
    date.set(Calendar.DAY_OF_MONTH, 1)
    date.add(Calendar.DATE, -1)
    return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date.time)+"T00:00:00.000Z"
}

fun clearEditText(editText: EditText) {
    editText.onTouch { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            val drawableRight = 2
            if (event.rawX >= (editText.right - editText.compoundDrawables[drawableRight].bounds.width())) {
                editText.setText("")
            }
        }
    }
}

@Suppress("DEPRECATION")
fun getPopupWindow(rootLayout: View, view: View): PopupWindow? {
    val popupWindow = PopupWindow(
        view,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT,
        true
    )
    popupWindow.setBackgroundDrawable(object : BitmapDrawable() {})
    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)
    popupWindow.setOnDismissListener { rootLayout.setBackgroundColor(Color.WHITE) }
    return popupWindow
}

fun callPhoneNumber(activity: AppCompatActivity, phoneNumber: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CALL_PHONE), 100)
        }
    }
    activity.makeCall(phoneNumber)
}