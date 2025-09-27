package com.example.protrack1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.protrack1.databinding.FragmentMenuBinding
import com.google.android.material.button.MaterialButton

class MenuFragment : Fragment() {
    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!
    private var selectedButton: MaterialButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenuButtons()
    }

    private fun setupMenuButtons() {
        // Configurar todos los botones del menú
        binding.btnPerfil.setOnClickListener {
            selectButton(binding.btnPerfil)
            showPerfil()
        }

        binding.btnFotos.setOnClickListener {
            selectButton(binding.btnFotos)
            showFotos()
        }

       binding.btnVideo.setOnClickListener {
            selectButton(binding.btnVideo)
            showVideo()
        }

       binding.btnWeb.setOnClickListener {
           selectButton(binding.btnWeb)
           showWeb()
       }
        binding.btnDescargar.setOnClickListener {
            selectButton(binding.btnDescargar)
            descargarCV()
        }

        binding.btnCerrarMenu.setOnClickListener {
            // Cerrar el menú
            (activity as? MainActivity)?.toggleMenu()
        }

        // Seleccionar el botón inicial (Perfil)
        selectButton(binding.btnPerfil)
    }

    private fun selectButton(button: MaterialButton) {
        // Deseleccionar el botón anterior
        selectedButton?.isSelected = false

        // Seleccionar el nuevo botón
        button.isSelected = true
        selectedButton = button
    }

    private fun showPerfil() {
        Toast.makeText(requireContext(), "Perfil seleccionado", Toast.LENGTH_SHORT).show()
        (activity as? MainActivity)?.cambiarFragment(PrincipalFragment())
    }

    private fun showFotos() {
        Toast.makeText(requireContext(), "Fotos seleccionadas", Toast.LENGTH_SHORT).show()
        (activity as? MainActivity)?.cambiarFragment(FotosFragment())
    }

    private fun showVideo() {
        Toast.makeText(requireContext(), "Video seleccionado", Toast.LENGTH_SHORT).show()
        (activity as? MainActivity)?.cambiarFragment(VideoFragment())
    }

        private fun showWeb() {
            Toast.makeText(requireContext(), "Web seleccionada", Toast.LENGTH_SHORT).show()
            (activity as? MainActivity)?.cambiarFragment(WebFragment())
        }

    private fun descargarCV() {
        Toast.makeText(requireContext(), "Descargando CV...", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        selectedButton = null
    }
}