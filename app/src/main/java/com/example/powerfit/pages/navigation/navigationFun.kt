package com.example.powerfit.pages.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
//Funcion de navegacion entre activitys solamente
class navigationActivity {
    fun navigationPages(context: Context, activity: Activity){
        val intent = Intent(context, activity::class.java)
        context.startActivity(intent)
    }

}