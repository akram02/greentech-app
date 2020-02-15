package tech.coderhub.basic.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64
import java.io.ByteArrayOutputStream
import kotlin.math.max


private val ICON = 0
private val IMAGE = 100

fun stringToBitmap(image: String): Bitmap {
    val decodedByteArray = Base64.decode(image, Base64.NO_WRAP)
    return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
}

fun bitmapToStringImage(bitmap: Bitmap, quality: Int = IMAGE): String {
    val baosImage = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baosImage)
    return Base64.encodeToString(baosImage.toByteArray(), Base64.NO_WRAP)
}

fun bitmapToStringImageWEBP(bitmap: Bitmap, quality: Int = IMAGE): String {
    val baosImage = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.WEBP, quality, baosImage)
    return Base64.encodeToString(baosImage.toByteArray(), Base64.NO_WRAP)
}

fun byteArrayToStringImage(byteArray: ByteArray): String {
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}

fun byteArrayOutputStreamToStringImage(byteArrayOutputStream: ByteArrayOutputStream): String {
    val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}

fun bitmapToStringIcon(bitmap: Bitmap): String {
    val baosImage = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.WEBP, ICON, baosImage)
    return Base64.encodeToString(baosImage.toByteArray(), Base64.NO_WRAP)
}

fun scaleTo1920Max(drawable: Drawable): Bitmap {
    val bitmap = (drawable as BitmapDrawable).bitmap
    return bitmap.scaleBitmap(1920)
}

fun Bitmap.scaleBitmap(maxHeightWidth: Int): Bitmap {
    val ratio: Float = width / height.toFloat()
    var width = width
    var height = height
    if (max(height, width)<=maxHeightWidth){}
    else if(height>width) {
        height = maxHeightWidth
        // ratio = width / height
        // ratio * height = width
        width = (height.toFloat() * ratio).toInt()
    }
    else {
        width = maxHeightWidth
        // ratio = width / height
        // ratio * height = width
        // height = width / ratio
        height = (width.toFloat()/ratio).toInt()
    }
    return Bitmap.createScaledBitmap(this, width, height, false)
}
fun Int.MB() = this * 1024 * 1024

