package com.example.xqtest.util

/**
 * @author: hepan
 * @date: 2024/4/24
 * @desc:
 */
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import com.example.xqtest.R
import java.io.FileOutputStream

object ImageUtil {

    fun changeGrayToWhite(ctx:Context, outputFilePath: String) {
        try {


            // Load the input image
            val bitmap = BitmapFactory.decodeResource(ctx.resources,R.drawable.src)

            // Get image dimensions
            val width = bitmap.width
            val height = bitmap.height

            // Create a mutable copy of the bitmap
            val mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)

            // Iterate over each pixel in the image
            for (y in 0 until height) {
                for (x in 0 until width) {
                    // Get the color of the pixel
                    val color = mutableBitmap.getPixel(x, y)

                    // Extract red, green, and blue components
                    val red = Color.red(color)
                    val green = Color.green(color)
                    val blue = Color.blue(color)

                    // Check if the pixel is gray
                    if (Math.abs(red - green) < 20 && Math.abs(green - blue) < 20) {
                        // Change the pixel to white
                        mutableBitmap.setPixel(x, y, Color.WHITE)
                    }
                }
            }

            // Save the modified bitmap to the output file
            FileOutputStream(outputFilePath).use { out ->
                mutableBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }
        } catch (e: Exception) {
            println("hepan 异常 + "+e.message)
        }
    }
}