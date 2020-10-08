package com.kienht.bubble_picker

import android.content.res.TypedArray
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat.setTint
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kienht.bubblepicker.BubblePickerListener
import com.kienht.bubblepicker.adapter.BubblePickerAdapter
import com.kienht.bubblepicker.model.BubbleGradient
import com.kienht.bubblepicker.model.PickerItem
import kotlinx.android.synthetic.main.sync_activity.*

/**
 * @author kienht
 * @since 20/07/2018
 */
class SyncActivity : AppCompatActivity() {

    lateinit var images: TypedArray
    lateinit var colors: TypedArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sync_activity)

        val titles = resources.getStringArray(R.array.countries)
        colors = resources.obtainTypedArray(R.array.colors)

        picker.adapter = object : BubblePickerAdapter {
            override val totalCount = titles.size

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getItem(position: Int): PickerItem {
                val currentColor = colors.getColor((position * 2) % 8 + 1, 0)
                return PickerItem().apply {
                    title = titles[position]
                    //gradient = BubbleGradient(colors.getColor((position * 2) % 8, 0), currentColor, BubbleGradient.VERTICAL)
                    //imgUrl = "http://sohanews.sohacdn.com/2018/4/11/hat9-1523392964439195574255.jpg"
                    imgDrawable = ContextCompat.getDrawable(this@SyncActivity, /*images.getResourceId(position, 0)*/R.drawable.ic_aura_bubbles)?.apply { setTint(currentColor) }
                    bgColor = currentColor
                }
            }
        }

        picker.bubbleSize = 10
        picker.swipeMoveSpeed = 2f
        picker.isAlwaysSelected = false
        picker.centerImmediately = true
        picker.listener = object : BubblePickerListener {
            override fun onBubbleDeselected(item: PickerItem) {
                toast("Unselected: " + item.title!!)
            }

            override fun onBubbleSelected(item: PickerItem) {
                toast("Selected: " + item.title!!)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        picker.onResume()
    }

    override fun onPause() {
        picker.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        colors.resources
    }

    private fun toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

}