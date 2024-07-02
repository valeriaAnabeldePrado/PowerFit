package com.example.powerfit.pages

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
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
import com.example.powerfit.pages.navigation.navigationActivity
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
        val navigator = navigationActivity()
        val btnIn = findViewById<AppCompatButton>(R.id.btn_sign_in)
        val nameEditText = findViewById<TextInputEditText>(R.id.nameuser_value)
        val passwordEditText = findViewById<TextInputEditText>(R.id.pasword_value)
        val recoveredP = findViewById<TextView>(R.id.recupera_pass)
        val errorPass = findViewById<TextView>(R.id.pass_error_r)
        val errorName : TextView = findViewById(R.id.name_errorR)


        //Base de datos en contexto
        dataBase = DataBase(this)

        //Otras variables para eventos
        var appCounter = 3
        //eventos
        btnIn.setOnClickListener{
            val userName = nameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

                if (dataBase.validarUsuarioOk(userName, password)) {
                   navigator.navigationPages(this, MainActivity())
                } else {
                    appCounter -= 1
                    if (appCounter > 0){
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                        errorName.setText("Correo incorrecto, vuelve a ingresar")
                        errorPass.setText("Contraseña incorrecta, vuelve a ingresar")
                        Handler(Looper.getMainLooper()).postDelayed({
                            errorName.text = ""
                            errorPass.text = ""
                        }, 4000)
                    } else {
                        btnIn.isEnabled = false
                        navigator.navigationPages(this,logIn_recoverPage())
                    }
                }
        }
        recoveredP.setOnClickListener{
            navigator.navigationPages(this,logIn_recoverPage())
        }

    }
}

