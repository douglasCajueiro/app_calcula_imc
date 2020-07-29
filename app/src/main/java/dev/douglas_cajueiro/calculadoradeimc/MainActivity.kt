package dev.douglas_cajueiro.calculadoradeimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnCalcular.setOnClickListener {

            val peso = edtPeso.text.toString().toFloat()
            val altura = edtAltura.text.toString().toFloat()
            val imc = (peso / (altura * altura)) * 10000


            if (peso.toString().isEmpty()) {
                edtPeso.error = "Campo obrigatório"
            }
            else if(altura.toString().isEmpty()) {
                edtAltura.error = "Campo obrigatório"
            }
            else {

                val mIntent = Intent(this, ResultActivity::class.java)
                mIntent.putExtra("IMC", imc.toString())
                startActivity(mIntent)

            }
        }

    }
}
