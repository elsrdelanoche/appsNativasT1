package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.SharedViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar

class FragmentTextFields : Fragment() {

    // ViewModel compartido con la Activity
    private val vm: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_textfields, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etNombre   = view.findViewById<EditText>(R.id.etNombre)
        val etApellido = view.findViewById<EditText>(R.id.etApellido)
        val btnGuardar = view.findViewById<MaterialButton>(R.id.btnGuardar)

        // Prefill con lo que ya haya en el VM (por si vuelves a este fragment)
        etNombre.setText(vm.nombre.value)
        etApellido.setText(vm.apellido.value)

        btnGuardar.setOnClickListener {
            vm.setNombre(etNombre.text.toString())
            vm.setApellido(etApellido.text.toString())

            Log.d("M1", "Guardado: ${vm.nombre.value} ${vm.apellido.value}")
            Snackbar.make(view, "Información guardada", Snackbar.LENGTH_SHORT).show()
        }
    }
}
