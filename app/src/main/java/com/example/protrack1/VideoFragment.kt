package com.example.protrack1

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment

class VideoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)

        // 🎬 Configuración del VideoView
        val videoView = view.findViewById<VideoView>(R.id.videoViewCV)

        val videoUri = Uri.parse("android.resource://${requireContext().packageName}/${R.raw.presentacion}")
        videoView.setVideoURI(videoUri)

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.start()

        // 🍔 Botón menú hamburguesa (misma lógica que en otros fragments)
        val btnMenu = view.findViewById<ImageButton>(R.id.btnMenu)
        btnMenu?.setOnClickListener {
            val menu = requireActivity().findViewById<View>(R.id.contenedorMenuLateral)
            val overlay = requireActivity().findViewById<View>(R.id.overlay)

            if (menu.translationX == 0f) {
                // Ocultar menú
                menu.animate().translationX(-menu.width.toFloat()).setDuration(300).start()
                overlay.visibility = View.GONE
                overlay.animate().alpha(0f).setDuration(300).start()
            } else {
                // Mostrar menú
                menu.animate().translationX(0f).setDuration(300).start()
                overlay.visibility = View.VISIBLE
                overlay.animate().alpha(1f).setDuration(300).start()
            }
        }

        return view
    }
}