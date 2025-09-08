package com.example.myapplication.ui

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.SharedViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class FragmentBotones : Fragment() {

    private val vm: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_botones, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val root     = view.findViewById<ConstraintLayout>(R.id.rootBotones)
        val btnTema  = view.findViewById<MaterialButton>(R.id.btnCambiarTema)
        val btnSend  = view.findViewById<ImageButton>(R.id.btnEnviar)
        val rowEnviar= view.findViewById<LinearLayout>(R.id.rowEnviar)
        val fabNuevo = view.findViewById<FloatingActionButton>(R.id.fabNuevo)

        // --- Animación “latido” del icono Enviar ---
        ObjectAnimator.ofFloat(btnSend, View.SCALE_X, 1f, 1.08f, 1f).apply {
            duration = 700
            repeatCount = ObjectAnimator.INFINITE
        }.start()
        ObjectAnimator.ofFloat(btnSend, View.SCALE_Y, 1f, 1.08f, 1f).apply {
            duration = 700
            repeatCount = ObjectAnimator.INFINITE
        }.start()

        // --- Acciones ---
        btnTema.setOnClickListener {
            vm.cycleThemeColor()
            Toast.makeText(requireContext(), "Tema cambiado", Toast.LENGTH_SHORT).show()
        }

        btnSend.setOnClickListener {
            val nombre   = vm.nombre.value.ifBlank { "(sin nombre)" }
            val apellido = vm.apellido.value.ifBlank { "(sin apellido)" }
            Toast.makeText(requireContext(), "Enviando: $nombre $apellido", Toast.LENGTH_SHORT).show()
        }

        fabNuevo.setOnClickListener {
            vm.clearAll()
            Toast.makeText(requireContext(), "Perfil reiniciado", Toast.LENGTH_SHORT).show()
        }

        // --- Observa el color de tema y aplícalo visualmente ---
        viewLifecycleOwner.lifecycleScope.launch {
            vm.themeColor.collect { color ->
                // Fondo suave del fragment
                val soft = ColorUtils.setAlphaComponent(color, 28) // ~11% opacidad
                root.setBackgroundColor(soft)

                // Botón con tinte sólido
                btnTema.backgroundTintList = ColorStateList.valueOf(color)
                btnTema.setTextColor(resources.getColor(android.R.color.white, null))

                // (Opcional) resaltar fila de enviar
                rowEnviar.backgroundTintList = ColorStateList.valueOf(
                    ColorUtils.setAlphaComponent(color, 18)
                )
            }
        }
    }
}
