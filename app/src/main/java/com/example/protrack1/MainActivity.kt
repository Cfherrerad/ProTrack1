package com.example.protrack1

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.protrack1.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private var isMenuVisible = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar el click del overlay para cerrar el menú
        binding.overlay.setOnClickListener {
            if (isMenuVisible) {
                toggleMenu()
            }
        }

        // Cargar los fragments
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorMenuLateral, MenuFragment())
            .replace(R.id.contenedorPrincipal, PrincipalFragment())
            .commit()

    }

    fun toggleMenu() {
        val menuWidthPx = (250 * resources.displayMetrics.density)
        val translationX = if (isMenuVisible) -menuWidthPx else 0f
        val overlayAlpha = if (isMenuVisible) 0f else 0.6f

        // Animar el menú lateral
        binding.contenedorMenuLateral.animate()
            .translationX(translationX)
            .setDuration(300)
            .start()

        // Animar el overlay
        if (isMenuVisible) {
            // Si vamos a ocultar el menú
            binding.overlay.animate()
                .alpha(overlayAlpha)
                .setDuration(300)
                .withEndAction {
                    binding.overlay.visibility = View.GONE
                }
                .start()
        } else {
            // Si vamos a mostrar el menú
            binding.overlay.visibility = View.VISIBLE
            binding.overlay.animate()
                .alpha(overlayAlpha)
                .setDuration(300)
                .start()
        }

        // Configurar click para cerrar menú
        binding.overlay.isClickable = !isMenuVisible

        isMenuVisible = !isMenuVisible
    }

    // Función para cambiar el fragment principal
    fun cambiarFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contenedorPrincipal, fragment)
            .addToBackStack(null)
            .commit()

        // Cerrar el menú después de seleccionar una opción
        toggleMenu()
    }

    override fun onBackPressed() {
        if (isMenuVisible) {
            toggleMenu()
        } else {
            super.onBackPressed()
        }
    }

}