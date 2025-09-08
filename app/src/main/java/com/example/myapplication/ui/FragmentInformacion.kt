package com.example.myapplication.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.SharedViewModel
import kotlinx.coroutines.launch

class FragmentInformacion : Fragment() {

    private val vm: SharedViewModel by activityViewModels()

    // cache local para calcular progreso y renderizar
    private var nombre = ""
    private var apellido = ""
    private var aficiones: Set<String> = emptySet()
    private var nivel = "Principiante"
    private var publico = false
    private var idiomas: Set<String> = emptySet()
    private var themeColor = 0xFF6750A4.toInt() // mismo default que en el VM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_informacion, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val header   = view.findViewById<LinearLayout>(R.id.header)
        val ivAvatar = view.findViewById<ImageView>(R.id.ivAvatar)
        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val tvPrefs  = view.findViewById<TextView>(R.id.tvPrefs)
        val tvIdiomas= view.findViewById<TextView>(R.id.tvIdiomas)
        val tvPct    = view.findViewById<TextView>(R.id.tvProgreso)
        val pb       = view.findViewById<ProgressBar>(R.id.pbProgreso)

        fun render() {
            val full = listOf(nombre, apellido).filter { it.isNotBlank() }.joinToString(" ")
            tvNombre.text = "Nombre completo: ${full.ifBlank { "—" }}"

            val afStr  = if (aficiones.isEmpty()) "—" else aficiones.joinToString(", ")
            val pubStr = if (publico) "Sí" else "No"
            tvPrefs.text = "Nivel: $nivel\nPúblico: $pubStr\nAficiones: $afStr"

            tvIdiomas.text = "Idiomas: ${ if (idiomas.isEmpty()) "—" else idiomas.joinToString(", ") }"

            var done = 0
            if (full.isNotBlank()) done++
            if (aficiones.isNotEmpty() || nivel != "Principiante" || publico) done++
            if (idiomas.isNotEmpty()) done++
            if (themeColor != 0xFF6750A4.toInt()) done++
            val pct = (done * 100) / 4
            pb.progress = pct
            tvPct.text = "Progreso: $pct %"
        }

        // Observa todos los estados del ViewModel y re-renderiza
        viewLifecycleOwner.lifecycleScope.launch { vm.nombre.collect { nombre = it; render() } }
        viewLifecycleOwner.lifecycleScope.launch { vm.apellido.collect { apellido = it; render() } }
        viewLifecycleOwner.lifecycleScope.launch { vm.aficiones.collect { aficiones = it; render() } }
        viewLifecycleOwner.lifecycleScope.launch { vm.nivel.collect { nivel = it; render() } }
        viewLifecycleOwner.lifecycleScope.launch { vm.publico.collect { publico = it; render() } }
        viewLifecycleOwner.lifecycleScope.launch { vm.idiomasSeleccionados.collect { idiomas = it; render() } }
        viewLifecycleOwner.lifecycleScope.launch {
            vm.themeColor.collect { c ->
                themeColor = c
                val soft = ColorUtils.setAlphaComponent(c, 32) // ~12% opacidad
                header.backgroundTintList = ColorStateList.valueOf(soft)
                ivAvatar.imageTintList = ColorStateList.valueOf(c)
                render()
            }
        }
    }
}
