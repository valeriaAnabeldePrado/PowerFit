package com.example.powerfit

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.powerfit.databinding.ActivityMainBinding
import com.example.powerfit.pages.navigation.Home
import com.example.powerfit.pages.navigation.ListaMorosos
import com.example.powerfit.pages.navigation.PagoActividad
import com.example.powerfit.pages.navigation.PagoCuotaMensual
import com.example.powerfit.pages.navigation.socioAddOk


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replaceFragment(Home())
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(Home())
                    true
                }
                R.id.miembros -> {
                    replaceFragment(socioAddOk())
                    true
                }
                R.id.pagocuota -> {
                    replaceFragment(PagoCuotaMensual())
                    true
                }
                R.id.pagoactivi -> {
                    replaceFragment(PagoActividad())
                    true
                }
                R.id.listamorosos ->{
                    replaceFragment(ListaMorosos())
                    true
                }
                else -> false
            }
        }
        //val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation);

    }
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
        Log.d("MainActivity", "Fragment replaced with: ${fragment.javaClass.simpleName}")
    }

}

