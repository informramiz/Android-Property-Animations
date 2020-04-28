package github.informramiz.propertyanimation

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

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
}
