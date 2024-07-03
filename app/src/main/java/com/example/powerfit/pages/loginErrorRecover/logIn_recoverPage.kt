package com.example.powerfit.pages.loginErrorRecover

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.powerfit.R
import com.example.powerfit.dataBase.DataBase
import com.example.powerfit.pages.LogInPage
import com.example.powerfit.pages.navigation.navigationActivity
import com.google.android.material.textfield.TextInputEditText

class logIn_recoverPage : AppCompatActivity() {
    private lateinit var dataBase: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in_recover_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val navigator = navigationActivity()
        val inputText :TextInputEditText = findViewById(R.id.mailuser_value)
        val btnSend : Button = findViewById(R.id.btn_send_mail)
        val errorText : TextView = findViewById(R.id.error_text)
        val navigationArrow : ImageView = findViewById(R.id.arrow_navigation)

        //fake bd mail
        val mailOk = "mcLovin24@gmail.com"

        btnSend.setOnClickListener {
            val valueInputTextMail = inputText.text.toString().trim()
            if(valueInputTextMail == mailOk){
                val accessCode = "123456"
                navegationActivitys(this, accessCode)
            } else {
                errorText.setText("Correo incorrecto, vuelve a ingresar")
                Handler(Looper.getMainLooper()).postDelayed({
                    errorText.text = ""
                }, 4000)
            }
        }
        navigationArrow.setOnClickListener{
            navigator.navigationPages(this,LogInPage())
        }

    }

}
private fun navegationActivitys( context: Context, accessCode: String) {
    val intent = Intent(context, LogInRecoveredOk::class.java).apply {
        putExtra("ACCES_CODE", accessCode)
    }
    context.startActivity(intent)

}