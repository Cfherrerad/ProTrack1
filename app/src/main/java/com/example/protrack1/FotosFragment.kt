package com.example.protrack1

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import com.example.protrack1.databinding.DialogProjectDetailsBinding
import com.example.protrack1.databinding.FragmentFotosBinding

class FotosFragment : Fragment() {
    private var _binding: FragmentFotosBinding? = null
    private val binding get() = _binding!!
    private var detailsDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnMenu.setOnClickListener {
            (activity as? MainActivity)?.toggleMenu()
        }

        binding.btnDetalles1.setOnClickListener {
            showProjectDetailsDialog(
                title = "E-commerce Platform",
                description = "Desarrollé una plataforma completa de e-commerce utilizando React y Node.js. Incluye funcionalidades como gestión de productos, carrito de compras, sistema de pagos con Stripe, y un panel de administración para gestionar pedidos e inventario. La aplicación maneja más de 10,000 productos y procesa cientos de transacciones diarias.",
                technologies = listOf("React", "Node.js", "MongoDB", "Stripe")
            )
        }

        binding.btnDetalles2?.setOnClickListener {
            showProjectDetailsDialog(
                title = "Mobile App Development",
                description = "Desarrollé una aplicación móvil nativa para Android utilizando Kotlin. La aplicación incluye funciones avanzadas de geolocalización, notificaciones push en tiempo real, y sincronización offline. Maneja más de 50,000 usuarios activos mensuales.",
                technologies = listOf("Kotlin", "Android", "Firebase", "Google Maps")
            )
        }
    }

    private fun showProjectDetailsDialog(
        title: String,
        description: String,
        technologies: List<String>
    ) {
        val dialog = Dialog(requireContext())
        val dialogBinding = DialogProjectDetailsBinding.inflate(layoutInflater)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(dialogBinding.root)
        dialog.setCancelable(true)

        // Configurar el estilo del dialog
        dialog.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                (resources.displayMetrics.widthPixels * 0.9).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        // Configurar el contenido
        dialogBinding.textTitle.text = title
        dialogBinding.textDescription.text = description

        // Configurar el botón cerrar
        dialogBinding.btnCerrarDialog.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        detailsDialog = dialog
    }


    override fun onDestroyView() {
        super.onDestroyView()
        detailsDialog?.dismiss()
        _binding = null
    }
}