package com.example.powerfit.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabasePrueba(context: Context) : SQLiteOpenHelper(context, "powerfitDos", null, 1) {

    companion object {
        // Table names
        const val TABLA_USUARIO = "usuario"
        const val TABLA_MIEMBRO_PERSONA = "miembro"

        // Column names for usuario table
        const val COLUMNA_ID = "id_usuario"
        const val COLUMNA_NOMBRE = "nombre"
        const val COLUMNA_EMAIL = "mail"
        const val COLUMNA_PASS = "contrasena"

        // Column names for miembro table
        const val COLUMNA_ID_MIEMBRO = "IDMiembro"
        const val COLUMNA_NOMBRE_MIEMBRO = "Nombre"
        const val COLUMNA_APELLIDO = "Apellido"
        const val COLUMNA_DNI = "DNI"
        const val COLUMNA_TELEFONO = "Telefono"
        const val COLUMNA_ES_SOCIO = "EsSocio"
        const val COLUMNA_CORREO = "Correo"
        const val COLUMNA_DIRECCION = "Direccion"
        const val COLUMNA_FECHA_NAC = "FechaNac"
        const val COLUMNA_APTO_MEDICO = "AptoMedico"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableUsuario = """
            CREATE TABLE $TABLA_USUARIO (
                $COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMNA_NOMBRE TEXT,
                $COLUMNA_EMAIL TEXT,
                $COLUMNA_PASS TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableUsuario)
        Log.d("Database", "Table $TABLA_USUARIO created.")

        val adminValuesConst = ContentValues().apply {
            put(COLUMNA_NOMBRE, "McLovin")
            put(COLUMNA_EMAIL, "mcLovin24@gmail.com")
            put(COLUMNA_PASS, "alfajor24")
        }
        db?.insert(TABLA_USUARIO, null, adminValuesConst)
        Log.d("Database", "Admin user inserted.")

        val createTableMiembro = """
            CREATE TABLE $TABLA_MIEMBRO_PERSONA (
                $COLUMNA_ID_MIEMBRO INTEGER PRIMARY KEY,
                $COLUMNA_NOMBRE_MIEMBRO TEXT,
                $COLUMNA_APELLIDO TEXT,
                $COLUMNA_DNI TEXT,
                $COLUMNA_TELEFONO TEXT,
                $COLUMNA_ES_SOCIO INTEGER,
                $COLUMNA_CORREO TEXT,
                $COLUMNA_DIRECCION TEXT,
                $COLUMNA_FECHA_NAC TEXT,
                $COLUMNA_APTO_MEDICO INTEGER
            )
        """.trimIndent()
        db?.execSQL(createTableMiembro)
        Log.d("Database", "Table $TABLA_MIEMBRO_PERSONA created.")

        val miembroValues = arrayOf(
            ContentValues().apply {
                put(COLUMNA_ID_MIEMBRO, 1001)
                put(COLUMNA_NOMBRE_MIEMBRO, "Juan")
                put(COLUMNA_APELLIDO, "Perez")
                put(COLUMNA_DNI, "123456789")
                put(COLUMNA_TELEFONO, "3813456789")
                put(COLUMNA_ES_SOCIO, 1)
                put(COLUMNA_CORREO, "juan.perez@hotmail.com")
                put(COLUMNA_DIRECCION, "Calle D 123")
                put(COLUMNA_FECHA_NAC, "01/01/1990")
                put(COLUMNA_APTO_MEDICO, 1)
            },
            ContentValues().apply {
                put(COLUMNA_ID_MIEMBRO, 1002)
                put(COLUMNA_NOMBRE_MIEMBRO, "Maria")
                put(COLUMNA_APELLIDO, "Gomez")
                put(COLUMNA_DNI, "987654321")
                put(COLUMNA_TELEFONO, "3814456789")
                put(COLUMNA_ES_SOCIO, 1)
                put(COLUMNA_CORREO, "maria.gomez@gmail.com")
                put(COLUMNA_DIRECCION, "Avenida Sarmiento 456")
                put(COLUMNA_FECHA_NAC, "15/05/1985")
                put(COLUMNA_APTO_MEDICO, 1)
            },
            ContentValues().apply {
                put(COLUMNA_ID_MIEMBRO, 1003)
                put(COLUMNA_NOMBRE_MIEMBRO, "Carlos")
                put(COLUMNA_APELLIDO, "Rodriguez")
                put(COLUMNA_DNI, "456789123")
                put(COLUMNA_TELEFONO, "3815456789")
                put(COLUMNA_ES_SOCIO, 0)
                put(COLUMNA_CORREO, "carlos.rodriguez@gmail.com")
                put(COLUMNA_DIRECCION, "Plaza Principal 980")
                put(COLUMNA_FECHA_NAC, "30/11/1988")
                put(COLUMNA_APTO_MEDICO, 1)
            }
        )
        for (values in miembroValues) {
            db?.insert(TABLA_MIEMBRO_PERSONA, null, values)
        }
        Log.d("Database", "Miembros inserted.")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.apply {
            execSQL("DROP TABLE IF EXISTS $TABLA_USUARIO")
            execSQL("DROP TABLE IF EXISTS $TABLA_MIEMBRO_PERSONA")
            onCreate(this)
        }
    }

    fun obtenerNombresUsuario(): List<String> {
        val nombres = mutableListOf<String>()
        val databasePower = readableDatabase
        val cursor = databasePower.rawQuery("SELECT $COLUMNA_NOMBRE FROM $TABLA_USUARIO", null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_NOMBRE))
                nombres.add(nombre)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return nombres
    }
}

