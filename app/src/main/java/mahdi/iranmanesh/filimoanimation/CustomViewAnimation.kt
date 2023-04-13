package mahdi.iranmanesh.filimoanimation

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Shader.TileMode
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.view.View
import androidx.core.graphics.ColorUtils

class CustomViewAnimation(context: Context): View(context) {

    private var textX = 0f
    private val textHeight = AndroidUtilities.dp(70)
    private var imageTopPadding = textHeight/2

    private val imageAnimator: ValueAnimator
    private val gradiantAnimator: ValueAnimator

    private val textPaint: TextPaint = TextPaint()
    private val paint: Paint = Paint()
    private val camera = Camera()
    private val cameraMatrix = Matrix()

    lateinit var image: Drawable
    lateinit var text: String

    init {
        textPaint.textSize = AndroidUtilities.dp(64).toFloat()
        textPaint.color = Color.WHITE

        imageAnimator = ValueAnimator.ofFloat(0f , 360f).apply {
            duration = 1000
            addUpdateListener { invalidate() }
        }

        gradiantAnimator = ValueAnimator.ofInt(0 , 255).apply {
            startDelay = 1000
            duration = 500
            addUpdateListener { invalidate() }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    fun startAnimation(){
        imageAnimator.start()
        gradiantAnimator.start()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            canvas.save()

            textX =  measuredWidth/2 - textPaint.measureText(text)/2
            canvas.drawText(text ,textX , measuredHeight.toFloat() , textPaint )

            paint.shader = RadialGradient(
                measuredWidth / 2f, measuredHeight / 2f,
                measuredHeight / 2f ,
                ColorUtils.setAlphaComponent(Color.WHITE ,  if (gradiantAnimator.isRunning) gradiantAnimator.animatedValue as Int else 255 - if (imageAnimator.isRunning) 255 else 0) ,
                Color.TRANSPARENT, TileMode.MIRROR
            )

            canvas.drawCircle(
                measuredWidth/2f, measuredHeight/2f,
                measuredHeight/2f, paint
            )

            camera.save()
            camera.rotateY(if (imageAnimator.isRunning) imageAnimator.animatedValue as Float else 0f)
            camera.getMatrix(cameraMatrix)
            cameraMatrix.preTranslate(-measuredHeight/2f, 0f);
            cameraMatrix.postTranslate(measuredHeight/2f, 0f);
            camera.restore();

            canvas.concat(cameraMatrix)

            image.setBounds(textHeight/2 + imageTopPadding/2 , imageTopPadding , measuredWidth - (textHeight/2) , measuredHeight - textHeight)
            image.draw(canvas)

            canvas.restore()
        }
    }
}