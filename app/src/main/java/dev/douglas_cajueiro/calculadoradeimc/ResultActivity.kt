package dev.douglas_cajueiro.calculadoradeimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.activity_splash.*
import java.text.DecimalFormat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Recovering "IMC" from Intent.putExtra
        val imcResult = intent.getStringExtra("IMC")!!.toDouble()
        val df = DecimalFormat("#.0")

        // Formatting imcResult with a decimal pattern
        val imcfinal = df.format(imcResult).toFloat()

        // Showing IMC result in a TextView
        txtResult.text = imcfinal.toString()


        // Function that highlights the right group
        fun chooseGroup(btn: Button, txt : TextView, img: ImageView) {
            btn.setBackgroundColor(resources.getColor(R.color.colorTransparent))
            btn.setTextColor(resources.getColor(R.color.colorWhite))
            txt.setTextColor(resources.getColor(R.color.colorWhite))
            img.visibility=View.VISIBLE
        }

        // classifying the group
        val classify = if (imcfinal <= 18.4) {
            "Under"

        } else if (imcfinal > 18.5 && imcfinal<25 ){
            "Ideal"

        } else if (imcfinal >= 25 && imcfinal<=29.9 ){
            "Over"

        } else {
            "Obese"
        }

        // Setting pointer final position
        pointer.rotation = if (classify == "Under") {
            55f
        } else if (classify == "Ideal") {
            115f
        } else if (classify == "Over") {
            180f
        } else {
            235f
        }



        // Rotating the pointer

        Handler().postDelayed({
            var pointerPosition = pointer.rotation * -1
            val rotate = RotateAnimation(
                pointerPosition.toFloat(),
                0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            rotate.duration = 1200
            rotate.interpolator = LinearInterpolator()

            pointer.startAnimation(rotate)

        }, 150)


        // Setting Handle to wait for the pointer to spin
        Handler().postDelayed({

            // Picking the right group and applying a function to Highlight it
            if(imcfinal <= 18.4) {
                chooseGroup(btnUnder, txtUnder, bkgUnder)

            } else if(imcfinal > 18.5 && imcfinal<25 ){
                chooseGroup(btnIdeal, txtIdeal, bkgIdeal)

            } else if(imcfinal >= 25 && imcfinal<=29.9 ){
                chooseGroup(btnOver, txtOver, bkgOver)

            } else {
                chooseGroup(btnObese, txtObese, bkgObese)
            }

        }, 1350)

        // Setting up CalculateAgain button (come back to MainActivity)
        btnCalculateAgain.setOnClickListener {
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
            finishAffinity()
        }
    }

}
