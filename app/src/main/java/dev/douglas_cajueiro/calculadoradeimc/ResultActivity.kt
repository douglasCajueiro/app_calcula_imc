package dev.douglas_cajueiro.calculadoradeimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*
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


        if(imcfinal <= 18.4) {
            btnUnder.setBackgroundColor(resources.getColor(R.color.colorTransparent))
            btnUnder.setTextColor(resources.getColor(R.color.colorWhite))
            txtUnder.setTextColor(resources.getColor(R.color.colorWhite))
            bkgUnder.visibility=View.VISIBLE
            pointer.rotation = -55f

        } else if(imcfinal > 18.5 && imcfinal<25 ){
            btnIdeal.setBackgroundColor(resources.getColor(R.color.colorTransparent))
            btnIdeal.setTextColor((resources.getColor(R.color.colorWhite)))
            txtIdeal.setTextColor(resources.getColor(R.color.colorWhite))
            bkgIdeal.visibility = View.VISIBLE
            pointer.rotation = 0f

        } else if(imcfinal >= 25 && imcfinal<=29.9 ){
            btnOver.setBackgroundColor(resources.getColor(R.color.colorTransparent))
            btnOver.setTextColor((resources.getColor(R.color.colorWhite)))
            txtOver.setTextColor(resources.getColor(R.color.colorWhite))
            bkgOver.visibility = View.VISIBLE
            pointer.rotation = 60f

        } else {
            btnObese.setBackgroundColor(resources.getColor(R.color.colorTransparent))
            btnObese.setTextColor((resources.getColor(R.color.colorWhite)))
            txtObese.setTextColor(resources.getColor(R.color.colorWhite))
            bkgObese.visibility = View.VISIBLE
            pointer.rotation = 120f

        }

        // Setting up CalculateAgain button (come back to MainActivity)
        btnCalculateAgain.setOnClickListener {
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
            finishAffinity()
        }
    }

}
