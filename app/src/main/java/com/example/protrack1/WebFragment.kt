package com.example.protrack1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.protrack1.databinding.FragmentWebBinding

class WebFragment : Fragment() {
    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupWebView()
        setupListeners()

        // Cargar página inicial
        loadUrl("https://github.com/Johansst/HerramientasProgramacionMovilGrupo32025")
    }

    private fun setupViews() {
        webView = binding.webView
        progressBar = binding.progressBar
    }

    private fun setupWebView() {
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.isVisible = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.isVisible = false
                // Actualizar la URL en el campo de texto
                if (!url.isNullOrEmpty()) {
                    binding.etUrl.setText(url)
                }
            }
        }

        // Configuración básica del WebView
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
    }

    private fun setupListeners() {
        // Botón del menú lateral
        binding.btnMenu.setOnClickListener {
            (activity as? MainActivity)?.toggleMenu()
        }
        // Botón de búsqueda
        binding.btnGo.setOnClickListener {
            loadWebPage()
        }
        // Tecla Enter/Go del teclado
        binding.etUrl.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                loadWebPage()
                true
            } else {
                false
            }
        }
        // Ocultar teclado al hacer clic fuera
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }
    private fun loadWebPage() {
        val url = binding.etUrl.text.toString().trim()
        if (url.isNotEmpty()) {
            hideKeyboard()
            loadUrl(url)
        }
    }
    private fun loadUrl(url: String) {
        var finalUrl = url
        when {
            url.startsWith("http://") || url.startsWith("https://") -> {
                // URL ya tiene protocolo
            }
            url.contains(".") -> {
                finalUrl = "https://$url"
            }
            else -> {
                finalUrl = "https://www.google.com/search?q=${url.replace(" ", "+")}"
            }
        }
        webView.loadUrl(finalUrl)
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
        imm.hideSoftInputFromWindow(binding.etUrl.windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}