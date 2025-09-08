package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.SharedViewModel
import kotlinx.coroutines.launch

class FragmentListas : Fragment() {

    private val vm: SharedViewModel by activityViewModels()
    private lateinit var adapter: IdiomasAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_listas, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv = view.findViewById<RecyclerView>(R.id.rvIdiomas)
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = IdiomasAdapter(vm.idiomasDisponibles) { name, checked ->
            vm.setIdioma(name, checked)
        }
        rv.adapter = adapter

        // Observa selección y refleja en la lista
        viewLifecycleOwner.lifecycleScope.launch {
            vm.idiomasSeleccionados.collect { set ->
                adapter.updateSelection(set)
            }
        }
    }
}
