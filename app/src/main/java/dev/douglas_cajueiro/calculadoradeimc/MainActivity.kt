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

        val emptyMessage = "Campo obrigatório"

        btnCalcular.setOnClickListener {

            if (edtPeso.text.toString().isNullOrEmpty() || edtPeso.text.toString().toInt() == 0) {
                edtPeso.error = emptyMessage
            }
            else if (edtAltura.text.toString().isNullOrEmpty() || edtAltura.text.toString().toInt() == 0) {
                edtAltura.error = emptyMessage
            } else {

                val peso = edtPeso.text.toString().toFloat()
                val altura = edtAltura.text.toString().toFloat()
                val imc = (peso / (altura * altura)) * 10000

                val mIntent = Intent(this, ResultActivity::class.java)
                mIntent.putExtra("IMC", imc.toString())
                startActivity(mIntent)

            }
        }

    }
}
