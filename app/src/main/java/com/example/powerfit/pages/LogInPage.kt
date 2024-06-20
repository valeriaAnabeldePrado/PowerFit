package com.example.powerfit.pages

import android.content.Intent
import android.os.Bundle

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.powerfit.MainActivity
import com.example.powerfit.R
import com.example.powerfit.dataBase.DataBase
import com.example.powerfit.pages.loginErrorRecover.logIn_recoverPage
import com.google.android.material.textfield.TextInputEditText

class LogInPage : AppCompatActivity() {
    private lateinit var dataBase: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in_page2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnIn = findViewById<AppCompatButton>(R.id.btn_sign_in)
        val nameEditText = findViewById<TextInputEditText>(R.id.nameuser_value)
        val passwordEditText = findViewById<TextInputEditText>(R.id.pasword_value)

        //Base de datos en contexto
        dataBase = DataBase(this)

        //Otras variables para eventos
        var appCounter = 3
        //eventos
        btnIn.setOnClickListener{
            val userName = nameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

                if (dataBase.validarUsuarioOk(userName, password)) {
                    navigateToHome()
                } else {
                    appCounter -= 1
                    if (appCounter > 0){
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Te quedaste sin intentos kpo", Toast.LENGTH_SHORT).show()
                        btnIn.isEnabled = false
                        recoverPage()
                    }
                }
    }

}
    //Navegación recuperar la contraseña
    private fun recoverPage() {
       val intent = Intent(this, logIn_recoverPage::class.java)
        startActivity(intent)
    }

    //Navegación OK
    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }}