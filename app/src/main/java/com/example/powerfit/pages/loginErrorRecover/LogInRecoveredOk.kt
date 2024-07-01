package com.example.powerfit.pages.loginErrorRecover

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.powerfit.R

class LogInRecoveredOk : AppCompatActivity() {
    companion object {
        const val MY_CHANNEL_ID = "ElKanalxD"
        const val NOTIFICATION_PERMISSION_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in_recovered_ok)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val accessCode = intent.getStringExtra("ACCES_CODE") ?: return
        val btnPass : Button = findViewById(R.id.btn_pass_code)
        val inputCode : EditText = findViewById(R.id.value_code_in)
        val errorText : TextView = findViewById(R.id.error_text)


        createNotificationChannel(MY_CHANNEL_ID)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                Log.d("LogInRecoveredOk", "Acceso ok")
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), NOTIFICATION_PERMISSION_CODE)
            } else {
                showNotification(accessCode, this, MY_CHANNEL_ID)
            }
        } else {
            Log.d("LogInRecoveredOk", "version Nugaton")
            showNotification(accessCode, this, MY_CHANNEL_ID)
        }

        btnPass.setOnClickListener {
            val textCode = inputCode.text.toString()
            if(textCode == accessCode){
                laNavigation()
            }else{
                errorText.setText("Número incorrecto, vuelve a ingresar")
                Handler(Looper.getMainLooper()).postDelayed({
                    errorText.text = ""
                }, 4000)
            }
        }




    }
    fun laNavigation() {
        val intent = Intent(this, PassCodeOk::class.java)
        startActivity(intent)
    }
    private fun createNotificationChannel(channelId: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Password Recovery Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for password recovery notifications"
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(accessCode: String, context: Context, channelId: String) {
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.superhappy)
            .setContentTitle("Codigo de acceso")
            .setContentText("Ingresa el siguiente codigo de validacion: $accessCode")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                Log.d("LogInRecoveredOk", "Showing notification")
                notify(1, builder.build())
            } else {
                Log.d("LogInRecoveredOk", "Notification permission not granted")
            }
        }
    }
}
