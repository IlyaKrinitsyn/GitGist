package com.krinitsyn.git_gist.impl.picasso

import android.graphics.*
import com.squareup.picasso.Transformation

object CircleTransformation : Transformation {

    override fun transform(source: Bitmap): Bitmap {
        val minSide = Math.min(source.width, source.height)

        val width = (source.width - minSide) / 2
        val height = (source.height - minSide) / 2

        val bitmap = Bitmap.createBitmap(minSide, minSide, source.config)

        val canvas = Canvas(bitmap)
        val paint = Paint()
        val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        if (width != 0 || height != 0) {
            val matrix = Matrix()
            matrix.setTranslate(-width.toFloat(), -height.toFloat())
            shader.setLocalMatrix(matrix)
        }
        paint.shader = shader
        paint.isAntiAlias = true

        val r = minSide / 2f
        canvas.drawCircle(r, r, r, paint)

        source.recycle()

        return bitmap
    }

    override fun key(): String = "com.krinitsyn.git_gist.impl.picasso"

}