package com.example.powerfit.dataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataBase(context : Context) : SQLiteOpenHelper( context, "powerfit",null, 1) {
   //cuando la bd no exista y se crea por primera vez ya sea por iniciacion propia o por rellenado de datos
    //objeto previo para pasar parametros simpre en mayuscula
    //USUARIO TABLA
    companion object{
        //Tabla admin
        internal const val TABLA_USUARIO = "usuario"
        internal const val COLUMNA_ID = "id_usuario"
       internal const val COLUMNA_NOMBRE = "nombre"
       internal const val COLUMNA_EMAIL = "mail"
       internal const val COLUMNA_PASS = "contrasena"

       //tabla miembro
       internal const val TABLA_MIEMBRO_PERSONA = "miembro"
       internal const val COLUMNA_ID_MIEMBRO = "IDMiembro"
       internal const val COLUMNA_NOMBRE_MIEMBRO = "Nombre"
       internal const val COLUMNA_APELLIDO = "Apellido"
       internal const val COLUMNA_DNI = "DNI"
       internal const val COLUMNA_TELEFONO = "Telefono"
       internal const val COLUMNA_ES_SOCIO = "EsSocio"
       internal const val COLUMNA_CORREO = "Correo"
       internal const val COLUMNA_DIRECCION = "Direccion"
       internal const val COLUMNA_FECHA_NAC = "FechaNac"
       internal const val COLUMNA_APTO_MEDICO = "AptoMedico"

       //tabla actividad
       private const val TABLA_ACTIVIDAD = "actividad"
       private const val COLUMNA_ID_ACTIV = "IDActiv"
       private const val COLUMNA_NOMBRE_ACTIV = "Nombre"
       private const val COLUMNA_HORARIO = "Horario"
       private const val COLUMNA_FECHA = "Fecha"
       private const val COLUMNA_CUPO_MAX = "CupoMax"
       private const val COLUMNA_COSTO = "Costo"

       //tabla inscripcion
       private const val TABLA_INSCRIPCION = "inscripcion"
       private const val COLUMNA_ID_INSCRIP = "IDInscrip"
       private const val COLUMNA_ID_MIEMBRO_INSCRIP = "IDMiembro"
       private const val COLUMNA_ID_ACTIV_INSCRIP = "IDActiv"
       private const val COLUMNA_FECHA_INSC = "FechaInsc"
       private const val COLUMNA_FECHA_VENC = "FechaVenc"

       //table cuota
       private const val TABLA_CUOTA = "cuota"
       private const val COLUMNA_ID_CUOTA = "IDCuota"
       private const val COLUMNA_MONTO = "Monto"
       private const val COLUMNA_FECHA_PAGO = "FechaPago"
       private const val COLUMNA_FECHA_VENC_CUOTA = "FechaVenc"
       private const val COLUMNA_ID_MIEMBRO_CUOTA = "IDMiembro"
    }
    override fun onCreate(db: SQLiteDatabase?) {


        val createTableUsuario = "CREATE TABLE $TABLA_USUARIO (" +
                "$COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMNA_NOMBRE TEXT, " +
                "$COLUMNA_EMAIL TEXT, " +
                "$COLUMNA_PASS TEXT)"
        db?.execSQL(createTableUsuario)
        Log.d("Database", "Table $TABLA_USUARIO created.")

        val adminValuesConst = ContentValues().apply {
            put(COLUMNA_NOMBRE, "McLovin")
            put(COLUMNA_EMAIL, "mcLovin24@gmail.com")
            put(COLUMNA_PASS, "alfajor24")
        }
        db?.insert(TABLA_USUARIO, null, adminValuesConst)
        Log.d("Database", "Admin user inserted.")

        val createTableMiembro = "CREATE TABLE $TABLA_MIEMBRO_PERSONA (" +
                "$COLUMNA_ID_MIEMBRO INTEGER PRIMARY KEY, " +
                "$COLUMNA_NOMBRE_MIEMBRO TEXT, " +
                "$COLUMNA_APELLIDO TEXT, " +
                "$COLUMNA_DNI TEXT, " +
                "$COLUMNA_TELEFONO INTEGER, " +
                "$COLUMNA_ES_SOCIO INTEGER, " +
                "$COLUMNA_CORREO TEXT, " +
                "$COLUMNA_DIRECCION TEXT, " +
                "$COLUMNA_FECHA_NAC TEXT, " +
                "$COLUMNA_APTO_MEDICO INTEGER)"
        db!!.execSQL(createTableMiembro)
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
            db.insert(TABLA_MIEMBRO_PERSONA, null, values)
        }
        Log.d("Database", "DATABASE Miembros inserted.")

        //crear tabla actividad
        val createTableActividad = "CREATE TABLE $TABLA_ACTIVIDAD (" +
                "$COLUMNA_ID_ACTIV INTEGER PRIMARY KEY, " +
                "$COLUMNA_NOMBRE_ACTIV TEXT, " +
                "$COLUMNA_HORARIO TEXT, " +
                "$COLUMNA_FECHA TEXT, " +
                "$COLUMNA_CUPO_MAX INTEGER, " +
                " $COLUMNA_COSTO INTEGER)"
        db.execSQL(createTableActividad)

        //insert actividad
        val actividadValues = arrayOf(
            ContentValues().apply {
                put(COLUMNA_ID_ACTIV, 101)
                put(COLUMNA_NOMBRE_ACTIV, "CrossFit")
                put(COLUMNA_CUPO_MAX, 30)
                put(COLUMNA_COSTO, 700)
            },
            ContentValues().apply {
                put(COLUMNA_ID_ACTIV, 102)
                put(COLUMNA_NOMBRE_ACTIV, "Natacion")
                put(COLUMNA_CUPO_MAX, 15)
                put(COLUMNA_COSTO, 1200)
            },
            ContentValues().apply {
                put(COLUMNA_ID_ACTIV, 103)
                put(COLUMNA_NOMBRE_ACTIV, "Funcional")
                put(COLUMNA_CUPO_MAX, 50)
                put(COLUMNA_COSTO, 500)
            },
            ContentValues().apply {
                put(COLUMNA_ID_ACTIV, 104)
                put(COLUMNA_NOMBRE_ACTIV, "Musculacion")
                put(COLUMNA_CUPO_MAX, 60)
                put(COLUMNA_COSTO, 800)
            }
        )
        for (values in actividadValues) {
            db.insert(TABLA_ACTIVIDAD, null, values)
        }

        //crear tabla inscripcion
        val createTableInscripcion = "CREATE TABLE $TABLA_INSCRIPCION (" +
                "$COLUMNA_ID_INSCRIP INTEGER PRIMARY KEY, " +
                "$COLUMNA_ID_MIEMBRO_INSCRIP INTEGER, " +
                "$COLUMNA_ID_ACTIV_INSCRIP INTEGER, " +
                "$COLUMNA_FECHA_INSC TEXT, " +
                "$COLUMNA_FECHA_VENC TEXT, " +
                "FOREIGN KEY($COLUMNA_ID_MIEMBRO_INSCRIP) REFERENCES $TABLA_MIEMBRO_PERSONA($COLUMNA_ID_MIEMBRO), " +
                "FOREIGN KEY($COLUMNA_ID_ACTIV_INSCRIP) REFERENCES $TABLA_ACTIVIDAD($COLUMNA_ID_ACTIV) " +
                ")"
        db.execSQL(createTableInscripcion)

        //crear tabla cuota
        val createTableCuota = "CREATE TABLE $TABLA_CUOTA (" +
                "$COLUMNA_ID_CUOTA INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMNA_MONTO REAL, " +
                "$COLUMNA_FECHA_PAGO TEXT, " +
                "$COLUMNA_FECHA_VENC_CUOTA TEXT, " +
                "$COLUMNA_ID_MIEMBRO_CUOTA INTEGER, " +
                "FOREIGN KEY($COLUMNA_ID_MIEMBRO_CUOTA) REFERENCES $TABLA_MIEMBRO_PERSONA($COLUMNA_ID_MIEMBRO) " +
                ")"
        db.execSQL(createTableCuota)

        //insert cuota
        val cuotaValues = arrayOf(
            ContentValues().apply {
                put(COLUMNA_MONTO, 7800.0)
                put(COLUMNA_FECHA_PAGO, "2023-10-29")
                put(COLUMNA_FECHA_VENC_CUOTA, "2023-11-28")
                put(COLUMNA_ID_MIEMBRO_CUOTA, 1001)
            },
            ContentValues().apply {
                put(COLUMNA_MONTO, 7800.0)
                put(COLUMNA_FECHA_PAGO, "2023-05-28")
                put(COLUMNA_FECHA_VENC_CUOTA, "2023-06-28")
                put(COLUMNA_ID_MIEMBRO_CUOTA, 1002)
            }
        )
        for (values in cuotaValues) {
            db.insert(TABLA_CUOTA, null, values)
        }


    }
    //Nombre, apellido, email, contrasena,id
  //Detecta una actualizacion nuemeros actuales de version o campos nuevos, solo para entorno de produccion xq borra datitos si no estan incorpora2
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.apply {
            execSQL("DROP TABLE IF EXISTS $TABLA_USUARIO")
            execSQL("DROP TABLE IF EXISTS $TABLA_MIEMBRO_PERSONA")
            execSQL("DROP TABLE IF EXISTS $TABLA_ACTIVIDAD")
            execSQL("DROP TABLE IF EXISTS $TABLA_INSCRIPCION")
            execSQL("DROP TABLE IF EXISTS $TABLA_CUOTA")
            onCreate(this)
        }
    }
    //--------METODOS Y CURSORES PARA CITAR EN EL CODIGO----------//
    fun validarUsuarioOk(username: String, userpassword: String): Boolean {
        val databasePower = readableDatabase
        //Este modo es solo para lectura solo busca datos, cambio el modo writableDatabase es para escribir datos para incertar con datos externos
        val cursor = databasePower.rawQuery("SELECT * FROM $TABLA_USUARIO WHERE $COLUMNA_NOMBRE = '$username' AND $COLUMNA_PASS = '$userpassword'", null)
        val isValid = cursor.count > 0
        cursor.close()

        return isValid
    }
    fun obtenerNombresMiembros(): List<String> {
        val nombres = mutableListOf<String>()
        val databasePower = readableDatabase
        val cursor = databasePower.rawQuery("SELECT ${DataBase.COLUMNA_NOMBRE_MIEMBRO} FROM ${DataBase.TABLA_MIEMBRO_PERSONA}", null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(DataBase.COLUMNA_NOMBRE_MIEMBRO))
                nombres.add(nombre)
            } while (cursor.moveToNext())
        }

        cursor.close()

        return nombres
    }

    @SuppressLint("Range")
    fun pagar(dniMiembro: String, monto: Double, fechaPago: String, tipoPago: Int): Int {
        val database = writableDatabase
        var respuesta = 0

        // Buscar el ID del miembro por DNI
        val queryBuscarMiembro = "SELECT $COLUMNA_ID_MIEMBRO, $COLUMNA_ES_SOCIO FROM $TABLA_MIEMBRO_PERSONA WHERE $COLUMNA_DNI = ?"
        var miembroId: Int? = null
        var esSocio: Int? = null

        database.rawQuery(queryBuscarMiembro, arrayOf(dniMiembro)).use { cursor ->
            if (cursor.moveToFirst()) {
                miembroId = cursor.getInt(cursor.getColumnIndex(COLUMNA_ID_MIEMBRO))
                esSocio = cursor.getInt(cursor.getColumnIndex(COLUMNA_ES_SOCIO))
            }
        }

        // Verificar si el miembro existe
        if (miembroId != null) {
            // Realizar la lógica de pago según el tipo de pago
            if (tipoPago == 1 && esSocio == 1) {
                // Verificar si ya pagó la cuota del mes actual
                val queryVerificarCuota = "SELECT 1 FROM $TABLA_CUOTA WHERE $COLUMNA_ID_MIEMBRO_CUOTA = ? " +
                        "AND strftime('%m', $COLUMNA_FECHA_PAGO) = strftime('%m', ?) " +
                        "AND strftime('%Y', $COLUMNA_FECHA_PAGO) = strftime('%Y', ?)"

                var cuotaPagada = false
                database.rawQuery(queryVerificarCuota, arrayOf(miembroId.toString(), fechaPago, fechaPago)).use { cursor ->
                    cuotaPagada = cursor.count > 0
                }

                if (!cuotaPagada) {
                    // Si no ha pagado, realizar el pago de la cuota mensual
                    val insertCuota = "INSERT INTO $TABLA_CUOTA ($COLUMNA_MONTO, $COLUMNA_FECHA_PAGO, " +
                            "$COLUMNA_FECHA_VENC_CUOTA, $COLUMNA_ID_MIEMBRO_CUOTA) VALUES (?, ?, DATE($COLUMNA_FECHA_PAGO, '+1 month'), ?)"

                    val argsCuota = arrayOf(monto.toString(), fechaPago, miembroId.toString())
                    database.execSQL(insertCuota, argsCuota)

                    respuesta = 1 // Pago realizado con éxito
                } else {
                    respuesta = 3 // Ya pagó la cuota del mes actual
                }
            } else if (tipoPago == 2 && esSocio == 0) {
                // El pago es de actividad y solo los no socios deben pagarlo
                val insertCuota = "INSERT INTO $TABLA_CUOTA ($COLUMNA_MONTO, $COLUMNA_FECHA_PAGO, " +
                        "$COLUMNA_FECHA_VENC_CUOTA, $COLUMNA_ID_MIEMBRO_CUOTA) VALUES (?, ?, DATE($COLUMNA_FECHA_PAGO, '+1 day'), ?)"

                val argsCuota = arrayOf(monto.toString(), fechaPago, miembroId.toString())
                database.execSQL(insertCuota, argsCuota)

                respuesta = 1 // Pago realizado con éxito
            } else {
                respuesta = 0 // No se realizó el pago según la lógica de tipo de pago y membresía
            }
        } else {
            respuesta = -1 // Miembro no encontrado en la base de datos
        }

        database.close()
        return respuesta
    }

    fun nuevoMiembro(
        nombre: String,
        apellido: String,
        dni: String,
        telefono: Int,
        esSocio: Boolean,
        correo: String,
        direccion: String,
        fechaNac: String,
        aptoMedico: Boolean
    ): Int {
        val database = writableDatabase
        var respuesta = 0

        // Verificar si el miembro ya existe por correo o DNI
        val queryExisteMiembro = "SELECT COUNT(*) FROM $TABLA_MIEMBRO_PERSONA WHERE $COLUMNA_CORREO = ? OR $COLUMNA_DNI = ?"
        var existe = 0

        database.rawQuery(queryExisteMiembro, arrayOf(correo, dni)).use { cursor ->
            if (cursor.moveToFirst()) {
                existe = cursor.getInt(0)
            }
        }

        if (existe == 0) {
            // Obtener el próximo ID de miembro disponible
            val queryNextID = "SELECT IFNULL(MAX($COLUMNA_ID_MIEMBRO), 0) + 1 FROM $TABLA_MIEMBRO_PERSONA"
            var nuevoID = 1001 // Si no hay miembros, se inicia en 1001

            database.rawQuery(queryNextID, null).use { cursor ->
                if (cursor.moveToFirst()) {
                    nuevoID = cursor.getInt(0)
                }
            }

            // Insertar el nuevo miembro
            val insertMiembro = "INSERT INTO $TABLA_MIEMBRO_PERSONA " +
                    "($COLUMNA_ID_MIEMBRO, $COLUMNA_NOMBRE_MIEMBRO, $COLUMNA_APELLIDO, $COLUMNA_DNI, $COLUMNA_TELEFONO, " +
                    "$COLUMNA_ES_SOCIO, $COLUMNA_CORREO, $COLUMNA_DIRECCION, $COLUMNA_FECHA_NAC, $COLUMNA_APTO_MEDICO) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"

            val argsMiembro = arrayOf(
                nuevoID.toString(),
                nombre,
                apellido,
                dni,
                telefono,
                if (esSocio) 1 else 0,
                correo,
                direccion,
                fechaNac,
                if (aptoMedico) 1 else 0
            )

            database.execSQL(insertMiembro, argsMiembro)

            respuesta = nuevoID // Devolver el ID del nuevo miembro insertado
        } else {
            respuesta = 1 // Devolver 1 si el miembro ya existe
        }

        database.close()
        return respuesta
    }

    data class MorososList(
        val nombreCompleto: String,
        val telefono: Int,
        val correo: String,
        val fechaVencimiento: String
    )

    fun mostrarSociosMorosos(): List<MorososList> {
        val database = readableDatabase
        val query = """
        SELECT m.$COLUMNA_NOMBRE, m.$COLUMNA_APELLIDO, m.$COLUMNA_DNI, m.$COLUMNA_CORREO, MAX(c.$COLUMNA_FECHA_VENC) AS 'Fecha de Vencimiento'
        FROM $TABLA_MIEMBRO_PERSONA m
        INNER JOIN $TABLA_CUOTA c ON c.$COLUMNA_ID_MIEMBRO = m.$COLUMNA_ID_MIEMBRO
        WHERE m.$COLUMNA_ES_SOCIO = 1
        GROUP BY m.$COLUMNA_ID_MIEMBRO
        HAVING MAX(c.$COLUMNA_FECHA_VENC) <= date('now')
        ORDER BY MAX(c.$COLUMNA_FECHA_VENC)
    """
        val cursor = database.rawQuery(query, null)
        val sociosMorosos = mutableListOf<MorososList>()

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_NOMBRE))
                val apellido = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_APELLIDO))
                val telefono = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_TELEFONO))
                val correo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMNA_CORREO))
                val fechaVencimiento = cursor.getString(cursor.getColumnIndexOrThrow("Fecha de Vencimiento"))

                val nombreCompleto = "$nombre $apellido"
                sociosMorosos.add(MorososList(nombreCompleto, telefono.toInt(), correo, fechaVencimiento))
            } while (cursor.moveToNext())
        }
        cursor.close()
        database.close()

        return sociosMorosos
    }

}