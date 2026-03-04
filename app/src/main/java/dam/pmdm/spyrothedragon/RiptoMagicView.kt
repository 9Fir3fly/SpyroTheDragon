package dam.pmdm.spyrothedragon

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader
import android.view.View
import android.widget.ImageView
//CLASE RIPTOMAGICVIEW PARA EL EFECTO "GLOW" DEL CETRO DEL PERSONAJE EN CUESTIÓN
@SuppressLint("ViewConstructor")
class RiptoMagicView(
    context: Context,
    private val targetImage: ImageView,
    private val xRatio: Float,
    private val yRatio: Float
) : View(context) {

    private val diamondPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val glowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val wavePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var intensity = 0f
    private var waveRadius = 0f
    private var animator: ValueAnimator? = null

    private val centerX get() = targetImage.width * xRatio
    private val centerY get() = targetImage.height * yRatio

    init {
        diamondPaint.style = Paint.Style.FILL
        glowPaint.style = Paint.Style.FILL
        wavePaint.style = Paint.Style.STROKE
        wavePaint.strokeWidth = 6f
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //Color rojizo
        val red = 255
        val green = (80 + intensity * 50).toInt()    // 80 → 130
        val blue = (80)                       // fijo
        val color = Color.rgb(red, green, blue)

        diamondPaint.color = color

        //Brillo radial
        val gradient = RadialGradient(
            centerX, centerY,
            120f + intensity * 100,
            color,
            Color.TRANSPARENT,
            Shader.TileMode.CLAMP
        )
        glowPaint.shader = gradient
        canvas.drawCircle(centerX, centerY, 200f, glowPaint)

    }

    // Iniciar la animación
    fun startMagic() {
        if (animator?.isRunning == true) return

        animator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            addUpdateListener {
                intensity = it.animatedValue as Float
                waveRadius = 80 + intensity * 100
                invalidate()
            }
            start()
        }
    }

    // Detener la animación
    fun stopMagic() {
        animator?.cancel()
        animator = null
    }
}