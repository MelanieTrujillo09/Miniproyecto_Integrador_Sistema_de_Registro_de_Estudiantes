package com.example.miniproyecto01.data


import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("RegistroPreferences", Context.MODE_PRIVATE)

    companion object {
        const val KEY_MATRICULA = "key_matricula"
        const val KEY_NOMBRE = "key_nombre"
        const val KEY_CARRERA = "key_carrera"
        const val KEY_TURNO = "key_turno"
        const val KEY_ESTATUS = "key_estatus"
    }

    fun saveLastRegistro(matricula: String, nombre: String, carrera: String, turno: String, activo: Boolean) {
        sharedPreferences.edit().apply {
            putString(KEY_MATRICULA, matricula)
            putString(KEY_NOMBRE, nombre)
            putString(KEY_CARRERA, carrera)
            putString(KEY_TURNO, turno)
            putBoolean(KEY_ESTATUS, activo)
            apply()
        }
    }

    fun getLastMatricula(): String = sharedPreferences.getString(KEY_MATRICULA, "") ?: ""
    fun getLastNombre(): String = sharedPreferences.getString(KEY_NOMBRE, "") ?: ""
    fun getLastCarrera(): String = sharedPreferences.getString(KEY_CARRERA, "") ?: ""
    fun getLastTurno(): String = sharedPreferences.getString(KEY_TURNO, "Matutino") ?: "Matutino"
    fun getLastEstatus(): Boolean = sharedPreferences.getBoolean(KEY_ESTATUS, true)
    fun clear() {
        sharedPreferences.edit().clear().apply()}
}