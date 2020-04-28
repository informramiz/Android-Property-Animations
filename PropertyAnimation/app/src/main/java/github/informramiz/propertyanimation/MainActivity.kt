package github.informramiz.propertyanimation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart

class MainActivity : AppCompatActivity() {

    lateinit var star: ImageView
    lateinit var rotateButton: Button
    lateinit var translateButton: Button
    lateinit var scaleButton: Button
    lateinit var fadeButton: Button
    lateinit var backgroundButton: Button
    lateinit var showerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        star = findViewById(R.id.star)
        rotateButton = findViewById(R.id.rotateButton)
        translateButton = findViewById(R.id.translateButton)
        scaleButton = findViewById(R.id.scaleButton)
        fadeButton = findViewById(R.id.fadeButton)
        backgroundButton = findViewById(R.id.backgroundButton)
        showerButton = findViewById(R.id.showerButton)

        rotateButton.setOnClickListener {
            rotateStar()
        }

        translateButton.setOnClickListener {
            translateStar()
        }

        scaleButton.setOnClickListener {
            scaleStar()
        }

        fadeButton.setOnClickListener {
            fadeStar()
        }

        backgroundButton.setOnClickListener {
            animateBackground()
        }

        showerButton.setOnClickListener {

        }
    }

    private fun rotateStar() {
        val animator = ObjectAnimator.ofFloat(star, View.ROTATION, -360f, 0f)
        animator.duration = 1000
        animator.disableViewDuringAnimation(rotateButton)
        animator.start()
    }

    private fun translateStar() {
        val animator = ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f)
        //after first animation is done, repeat it but this time in reverse direction
        //so that the start comes back to its original position
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(translateButton)
        animator.start()
    }

    private fun scaleStar() {
        //we need to scale start in both X and Y-axis at the SAME TIME but there
        //is no single property that can do that so we have to create it ourselves
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        //now combine these 2 properties into a single object animator
        val animator = ObjectAnimator.ofPropertyValuesHolder(star, scaleX, scaleY)
        //we want the animation to reverse itself so that at the end star is in its original
        //shape and size
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(scaleButton)
        animator.start()
    }

    private fun fadeStar() {
        val animator = ObjectAnimator.ofFloat(star, View.ALPHA, 0f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.disableViewDuringAnimation(fadeButton)
        animator.start()
    }

    private fun animateBackground() {
        val animator = ObjectAnimator.ofArgb(star.parent, "backgroundColor", Color.BLACK, Color.RED)
        //reverse it
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.duration = 500
        animator.disableViewDuringAnimation(backgroundButton)
        animator.start()
    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        doOnStart { view.isEnabled = false }
        doOnEnd { view.isEnabled = true }
    }
}
