package com.kienht.bubblepicker

import android.graphics.Color
import android.support.annotation.ColorInt

fun getTextColorByBackground(@ColorInt backGroundColor: Int, @ColorInt lightTextColor: Int?, @ColorInt darkTextColor: Int?): Int {
	return if ((Color.red(backGroundColor) * 0.299 + Color.green(backGroundColor) * 0.587 + Color.blue(backGroundColor) * 0.114) > 186) {
		adjustAlpha(lightTextColor ?: Color.parseColor("#ffffff"), 0.6f)
	} else {
		adjustAlpha(darkTextColor ?: Color.parseColor("#000000"), 0.6f)
	}
}

fun adjustAlpha(@ColorInt color: Int, factor: Float): Int {
	val alpha = Math.round((Color.alpha(color) * factor))
	val red = Color.red(color)
	val green = Color.green(color)
	val blue = Color.blue(color)
	return Color.argb(alpha, red, green, blue)
}