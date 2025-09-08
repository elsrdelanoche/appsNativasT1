// app/src/main/java/com/example/myapplication/SharedViewModel.kt
package com.example.myapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {

    // --- M1
    private val _nombre = MutableStateFlow("")
    val nombre: StateFlow<String> = _nombre.asStateFlow()

    private val _apellido = MutableStateFlow("")
    val apellido: StateFlow<String> = _apellido.asStateFlow()

    fun setNombre(value: String) { _nombre.value = value.trim() }
    fun setApellido(value: String) { _apellido.value = value.trim() }

    // --- M2
    private val palette = intArrayOf(
        0xFF6750A4.toInt(), 0xFF386641.toInt(), 0xFF006494.toInt(), 0xFF9A031E.toInt()
    )
    private val _themeColor = MutableStateFlow(palette[0])
    val themeColor: StateFlow<Int> = _themeColor.asStateFlow()
    private var idx = 0
    fun cycleThemeColor() { idx = (idx + 1) % palette.size; _themeColor.value = palette[idx] }

    // --- M3
    private val _aficiones = MutableStateFlow<Set<String>>(emptySet())
    val aficiones: StateFlow<Set<String>> = _aficiones.asStateFlow()

    private val _nivel = MutableStateFlow("Principiante")
    val nivel: StateFlow<String> = _nivel.asStateFlow()

    private val _publico = MutableStateFlow(false)
    val publico: StateFlow<Boolean> = _publico.asStateFlow()

    fun setAficion(tag: String, on: Boolean) {
        _aficiones.value = if (on) _aficiones.value + tag else _aficiones.value - tag
    }
    fun setNivel(value: String) { _nivel.value = value }
    fun setPublico(value: Boolean) { _publico.value = value }

    // --- M4 (Idiomas)
    val idiomasDisponibles: List<String> =
        listOf("Español", "Inglés", "Francés", "Alemán")

    private val _idiomasSeleccionados = MutableStateFlow<Set<String>>(emptySet())
    val idiomasSeleccionados: StateFlow<Set<String>> = _idiomasSeleccionados.asStateFlow()

    fun setIdioma(name: String, checked: Boolean) {
        _idiomasSeleccionados.value =
            if (checked) _idiomasSeleccionados.value + name
            else _idiomasSeleccionados.value - name
    }

    fun toggleIdioma(name: String) {
        val s = _idiomasSeleccionados.value
        _idiomasSeleccionados.value = if (name in s) s - name else s + name
    }

    // --- Reset global
    fun clearAll() {
        _nombre.value = ""; _apellido.value = ""
        idx = 0; _themeColor.value = palette[idx]
        _aficiones.value = emptySet()
        _nivel.value = "Principiante"
        _publico.value = false
        _idiomasSeleccionados.value = emptySet()
    }
}
