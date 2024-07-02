package com.example.powerfit.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabasePrueba(context : Context) : SQLiteOpenHelper( context, "powerfitDos",null, 1) {
    companion object{
        //Tabla admin
        const val TABLA_USUARIO = "usuario"
         const val COLUMNA_ID = "id_usuario"
      const val COLUMNA_NOMBRE = "nombre"
      const val COLUMNA_EMAIL = "mail"
      const val COLUMNA_PASS = "contrasena"

        //tabla miembro
        const val TABLA_MIEMBRO_PERSONA = "miembro"
        const val COLUMNA_ID_MIEMBRO = "IDMiembro"
         const val COLUMNA_NOMBRE_MIEMBRO = "Nombre"
        const val COLUMNA_APELLIDO = "Apellido"
         const val COLUMNA_DNI = "DNI"
       const val COLUMNA_TELEFONO = "Telefono"
        const val COLUMNA_ES_SOCIO = "EsSocio"
     const val COLUMNA_CORREO = "Correo"
      const val COLUMNA_DIRECCION = "Direccion"
       const val COLUMNA_FECHA_NAC = "FechaNac"
       const val COLUMNA_APTO_MEDICO = "AptoMedico"}

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUsuario = "CREATE TABLE ${DataBase.TABLA_USUARIO} (" +
                "${DataBase.COLUMNA_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${DataBase.COLUMNA_NOMBRE} TEXT, " +
                "${DataBase.COLUMNA_EMAIL} TEXT, " +
                "${DataBase.COLUMNA_PASS} TEXT)"
        db?.execSQL(createTableUsuario)
        Log.d("Database", "Table ${DataBase.TABLA_USUARIO} created.")

        val adminValuesConst = ContentValues().apply {
            put(DataBase.COLUMNA_NOMBRE, "McLovin")
            put(DataBase.COLUMNA_EMAIL, "mcLovin24@gmail.com")
            put(DataBase.COLUMNA_PASS, "alfajor24")
        }
        db?.insert(DataBase.TABLA_USUARIO, null, adminValuesConst)
        Log.d("Database", "Admin user inserted.")

        val createTableMiembro = "CREATE TABLE ${DataBase.TABLA_MIEMBRO_PERSONA} (" +
                "${DataBase.COLUMNA_ID_MIEMBRO} INTEGER PRIMARY KEY, " +
                "${DataBase.COLUMNA_NOMBRE_MIEMBRO} TEXT, " +
                "${DataBase.COLUMNA_APELLIDO} TEXT, " +
                "${DataBase.COLUMNA_DNI} TEXT, " +
                "${DataBase.COLUMNA_TELEFONO} INTEGER, " +
                "${DataBase.COLUMNA_ES_SOCIO} INTEGER, " +
                "${DataBase.COLUMNA_CORREO} TEXT, " +
                "${DataBase.COLUMNA_DIRECCION} TEXT, " +
                "${DataBase.COLUMNA_FECHA_NAC} TEXT, " +
                "${DataBase.COLUMNA_APTO_MEDICO} INTEGER)"
        db?.execSQL(createTableMiembro)
        Log.d("Database", "Table ${DataBase.TABLA_MIEMBRO_PERSONA} created.")

        val miembroValues = arrayOf(
            ContentValues().apply {
                put(DataBase.COLUMNA_ID_MIEMBRO, 1001)
                put(DataBase.COLUMNA_NOMBRE_MIEMBRO, "Juan")
                put(DataBase.COLUMNA_APELLIDO, "Perez")
                put(DataBase.COLUMNA_DNI, "123456789")
                put(DataBase.COLUMNA_TELEFONO, "3813456789")
                put(DataBase.COLUMNA_ES_SOCIO, 1)
                put(DataBase.COLUMNA_CORREO, "juan.perez@hotmail.com")
                put(DataBase.COLUMNA_DIRECCION, "Calle D 123")
                put(DataBase.COLUMNA_FECHA_NAC, "01/01/1990")
                put(DataBase.COLUMNA_APTO_MEDICO, 1)
            },
            ContentValues().apply {
                put(DataBase.COLUMNA_ID_MIEMBRO, 1002)
                put(DataBase.COLUMNA_NOMBRE_MIEMBRO, "Maria")
                put(DataBase.COLUMNA_APELLIDO, "Gomez")
                put(DataBase.COLUMNA_DNI, "987654321")
                put(DataBase.COLUMNA_TELEFONO, "3814456789")
                put(DataBase.COLUMNA_ES_SOCIO, 1)
                put(DataBase.COLUMNA_CORREO, "maria.gomez@gmail.com")
                put(DataBase.COLUMNA_DIRECCION, "Avenida Sarmiento 456")
                put(DataBase.COLUMNA_FECHA_NAC, "15/05/1985")
                put(DataBase.COLUMNA_APTO_MEDICO, 1)
            },
            ContentValues().apply {
                put(DataBase.COLUMNA_ID_MIEMBRO, 1003)
                put(DataBase.COLUMNA_NOMBRE_MIEMBRO, "Carlos")
                put(DataBase.COLUMNA_APELLIDO, "Rodriguez")
                put(DataBase.COLUMNA_DNI, "456789123")
                put(DataBase.COLUMNA_TELEFONO, "3815456789")
                put(DataBase.COLUMNA_ES_SOCIO, 0)
                put(DataBase.COLUMNA_CORREO, "carlos.rodriguez@gmail.com")
                put(DataBase.COLUMNA_DIRECCION, "Plaza Principal 980")
                put(DataBase.COLUMNA_FECHA_NAC, "30/11/1988")
                put(DataBase.COLUMNA_APTO_MEDICO, 1)
            }
        )
        for (values in miembroValues) {
            db?.insert(DataBase.TABLA_MIEMBRO_PERSONA, null, values)
        }

    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.apply {
            execSQL("DROP TABLE IF EXISTS ${DataBase.TABLA_MIEMBRO_PERSONA}")
            onCreate(this)
        }
    }


    fun obtenerNombresUsuario(): List<String> {
        val nombres = mutableListOf<String>()
        val databasePower = readableDatabase
        val cursor = databasePower.rawQuery("SELECT ${DataBase.COLUMNA_NOMBRE} FROM ${DataBase.TABLA_USUARIO}", null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(DataBase.COLUMNA_NOMBRE_MIEMBRO))
                nombres.add(nombre)
            } while (cursor.moveToNext())
        }

        cursor.close()

        return nombres
    }
}
