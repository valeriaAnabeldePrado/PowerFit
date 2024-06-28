package com.example.powerfit.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context : Context) : SQLiteOpenHelper( context, "powerfit",null, 1) {
   //cuando la bd no exista y se crea por primera vez ya sea por iniciacion propia o por rellenado de datos
    //objeto previo para pasar parametros simpre en mayuscula
    //USUARIO TABLA
    companion object{
        //Tabla admin
        private const val TABLA_USUARIO = "usuario"
        private const val COLUMNA_ID = "id_usuario"
       private const val COLUMNA_NOMBRE = "nombre"
       private const val COLUMNA_EMAIL = "mail"
       private const val COLUMNA_PASS = "contrasena"
       //Table persona

    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUsuario = "CREATE TABLE $TABLA_USUARIO ($COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMNA_NOMBRE TEXT, $COLUMNA_EMAIL TEXT, $COLUMNA_PASS TEXT)"
       //lanzar orden de creacion
       db!!.execSQL(createTableUsuario)
       val adminValuesConst = ContentValues().apply {
           put(COLUMNA_NOMBRE, "McLovin")
           put(COLUMNA_EMAIL, "mcLovin24@gmail.com")
           put(COLUMNA_PASS, "alfajor24")
       }
       db.insert(TABLA_USUARIO, null, adminValuesConst)
    }
    //Nombre, apellido, email, contrasena,id
  //Detecta una actualizacion nuemeros actuales de version o campos nuevos, solo para entorno de produccion xq borra datitos si no estan incorpora2
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       val dropTable = "DROP TABLE IF EXISTS $TABLA_USUARIO"
        db!!.execSQL(dropTable)
        onCreate(db)
    }
    //--------METODOS Y CURSORES PARA CITAR EN EL CODIGO----------//
    fun validarUsuarioOk(username: String, userpassword: String): Boolean {
        val databasePower = readableDatabase
        //Este modo es solo para lectura solo busca datos, cambio el modo writableDatabase es para escribir datos para incertar con datos externos
        val cursor = databasePower.rawQuery("SELECT * FROM $TABLA_USUARIO WHERE $COLUMNA_NOMBRE = '$username' AND $COLUMNA_PASS = '$userpassword'", null)
        val isValid = cursor.count > 0
        cursor.close()
        databasePower.close()
        return isValid
    }


}