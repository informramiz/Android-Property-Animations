package github.informramiz.propertyanimation

import android.animation.ObjectAnimator
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
    lateinit var colorizeButton: Button
    lateinit var showerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        star = findViewById(R.id.star)
        rotateButton = findViewById(R.id.rotateButton)
        translateButton = findViewById(R.id.translateButton)
        scaleButton = findViewById(R.id.scaleButton)
        fadeButton = findViewById(R.id.fadeButton)
        colorizeButton = findViewById(R.id.backgroundButton)
        showerButton = findViewById(R.id.showerButton)

        rotateButton.setOnClickListener {
            rotateStar()
        }

        translateButton.setOnClickListener {

        }

        scaleButton.setOnClickListener {

        }

        fadeButton.setOnClickListener {

        }

        colorizeButton.setOnClickListener {

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

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        doOnStart { view.isEnabled = false }
        doOnEnd { view.isEnabled = true }
    }
}
