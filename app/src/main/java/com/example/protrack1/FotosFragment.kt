package com.example.protrack1

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    }

    private fun showProjectDetailsDialog(
        title: String,
        description: String,
        technologies: List<String>
    ) {}

    override fun onDestroyView() {
        super.onDestroyView()
        detailsDialog?.dismiss()
        _binding = null
    }
}