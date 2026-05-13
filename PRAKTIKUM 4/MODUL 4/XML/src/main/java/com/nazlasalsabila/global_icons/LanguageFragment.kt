package com.nazlasalsabila.global_icons

import android.content.Context
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
            setLocale("id")
        }

        binding.btnLangEn.setOnClickListener {
            setLocale("en")
        }
    }

    private fun setLocale(langCode: String) {
        val prefs = requireActivity()
            .getSharedPreferences("settings", Context.MODE_PRIVATE)

        prefs.edit().putString("lang", langCode).apply()

        requireActivity().recreate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}