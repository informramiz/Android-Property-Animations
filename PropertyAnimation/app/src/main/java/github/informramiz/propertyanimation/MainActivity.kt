package github.informramiz.propertyanimation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
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
            showerStars()
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
        //because there is no propertyValue holder already defined for this so
        //we specify only name and type (color, int) and objectAnimator tries to find matching
        //property name and type automatically.
        //Note: In case object animator can't find the property then nothing will happen (no crash as well)
        val animator = ObjectAnimator.ofArgb(star.parent, "backgroundColor", Color.BLACK, Color.RED)
        //reverse it
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.duration = 500
        animator.disableViewDuringAnimation(backgroundButton)
        animator.start()
    }

    private fun showerStars() {
        val container = star.parent as ViewGroup
        val newStartView = createView(container.width, container.height, star.width, star.height)

        //add this new view to container
        container.addView(newStartView)

        //animate star in rotation, randomly perform rotations [1, 3]
        val rotator = ObjectAnimator.ofFloat(newStartView, View.ROTATION, (Math.random()* (3*360)).toFloat())
        //we want the rotation to be linear
        rotator.interpolator = LinearInterpolator()

        //also, animate the start in y-axis using translation
        val translator = ObjectAnimator.ofFloat(newStartView, View.TRANSLATION_Y, -newStartView.height.toFloat(), container.height + newStartView.height.toFloat())
        //we want the fall animation to accelerate
        translator.interpolator = AccelerateInterpolator()

        //group these animators to run simultaneously
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(rotator, translator)
        //set duration randomly in range [500, 2000]
        animatorSet.duration = Math.random().toLong() * 1500 + 500
        animatorSet.doOnEnd {
            //when animation ends, we don't need the newStarView any longer so remove it
            container.removeView(newStartView)
        }
        animatorSet.start()
    }

    private fun createView(containerWidth: Int, containerHeight: Int, starWidth: Int, startHeight: Int): View {
        //create a new view for each click, with random scale and random position in container
        val randomScale = Math.random().toFloat() * 1.5f + 0.1f
        val view = AppCompatImageView(this).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            //scale the star in x direction in range [0.1*width, 1.5*width]
            scaleX = randomScale
            //scale the star in y direction in range [0.1*height, 1.5*height]
            scaleY = randomScale

            //also translate it randomly in container x-axis
            translationX = Math.random().toFloat() * containerWidth
            setImageResource(R.drawable.ic_star)
        }
        return view
    }

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        doOnStart { view.isEnabled = false }
        doOnEnd { view.isEnabled = true }
    }
}
