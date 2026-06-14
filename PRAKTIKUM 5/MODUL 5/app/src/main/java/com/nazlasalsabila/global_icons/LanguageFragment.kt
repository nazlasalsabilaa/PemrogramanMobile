package com.nazlasalsabila.global_icons

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nazlasalsabila.global_icons.databinding.FragmentLanguageBinding

class LanguageFragment : Fragment(R.layout.fragment_language) {

    private var _binding: FragmentLanguageBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLanguageBinding.bind(view)

        binding.btnLangId.setOnClickListener {
            changeLanguage("id")
        }

        binding.btnLangEn.setOnClickListener {
            changeLanguage("en")
        }
    }

    private fun changeLanguage(langCode: String) {
        val prefs = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        prefs.edit().putString("lang", langCode).apply()

        val intent = requireActivity().packageManager.getLaunchIntentForPackage(requireActivity().packageName)
        intent?.let {
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(it)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}