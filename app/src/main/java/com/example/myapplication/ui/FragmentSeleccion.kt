// app/src/main/java/com/example/myapplication/ui/FragmentSeleccion.kt
package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.SharedViewModel
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.coroutines.launch

class FragmentSeleccion : Fragment() {

    private val vm: SharedViewModel by activityViewModels()
    private var internal = false  // evita bucles al sincronizar UI↔VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_seleccion, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cbDeporte = view.findViewById<CheckBox>(R.id.cbDeporte)
        val cbMusica  = view.findViewById<CheckBox>(R.id.cbMusica)
        val cbLectura = view.findViewById<CheckBox>(R.id.cbLectura)

        val rgNivel = view.findViewById<RadioGroup>(R.id.rgNivel)
        val rbPrincipiante = view.findViewById<RadioButton>(R.id.rbPrincipiante)
        val rbIntermedio   = view.findViewById<RadioButton>(R.id.rbIntermedio)
        val rbAvanzado     = view.findViewById<RadioButton>(R.id.rbAvanzado)

        val swPublico = view.findViewById<SwitchMaterial>(R.id.swPublico)

        // Listeners -> VM
        val cbListener = { cb: CheckBox ->
            cb.setOnCheckedChangeListener { _, isChecked ->
                if (!internal) vm.setAficion(cb.tag.toString(), isChecked)
            }
        }
        cbListener(cbDeporte); cbListener(cbMusica); cbListener(cbLectura)

        rgNivel.setOnCheckedChangeListener { _, checkedId ->
            if (internal) return@setOnCheckedChangeListener
            val nivel = when (checkedId) {
                R.id.rbIntermedio -> "Intermedio"
                R.id.rbAvanzado   -> "Avanzado"
                else              -> "Principiante"
            }
            vm.setNivel(nivel)
        }

        swPublico.setOnCheckedChangeListener { _, isChecked ->
            if (!internal) vm.setPublico(isChecked)
        }

        // VM -> UI (sincroniza cuando vuelvas o tras clearAll)
        viewLifecycleOwner.lifecycleScope.launch {
            vm.aficiones.collect { set ->
                internal = true
                cbDeporte.isChecked = "Deporte" in set
                cbMusica.isChecked  = "Música"  in set
                cbLectura.isChecked = "Lectura" in set
                internal = false
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            vm.nivel.collect { n ->
                internal = true
                when (n) {
                    "Intermedio" -> rgNivel.check(R.id.rbIntermedio)
                    "Avanzado"   -> rgNivel.check(R.id.rbAvanzado)
                    else         -> rgNivel.check(R.id.rbPrincipiante)
                }
                internal = false
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            vm.publico.collect { p ->
                internal = true
                swPublico.isChecked = p
                internal = false
            }
        }
    }
}
