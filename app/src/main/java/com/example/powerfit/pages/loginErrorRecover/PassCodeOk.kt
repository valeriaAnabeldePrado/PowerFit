package com.example.powerfit.pages.loginErrorRecover

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.powerfit.R
import com.example.powerfit.pages.LogInPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PassCodeOk : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pass_code_ok)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputPass: EditText = findViewById(R.id.pasword_new_value)
        val inputPassOk: EditText = findViewById(R.id.pasword_ok_value)
        val btnSavePass: Button = findViewById(R.id.btn_save_new_pass)
        val responseText: TextView = findViewById(R.id.response_text)

        btnSavePass.setOnClickListener {
            val passNew = inputPass.text.toString()
            val passNewOk = inputPassOk.text.toString()
            if (passNew == passNewOk) {
                responseText.text = "La contraseña fue restablecida correctamente"
                responseText.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray))

                launch {
                    delay(3000)
                    volver()
                }
            } else {
                responseText.text = "Las contraseñas no coinciden, vuelve a ingresar"
                Handler(Looper.getMainLooper()).postDelayed({
                    responseText.text = ""
                }, 4000)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    private fun volver() {
        val intent = Intent(this, LogInPage::class.java)
        startActivity(intent)
        finish()
    }
}
