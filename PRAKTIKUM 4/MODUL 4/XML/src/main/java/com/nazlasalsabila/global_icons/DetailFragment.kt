package com.nazlasalsabila.global_icons

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.nazlasalsabila.global_icons.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("extra_icon", Icon::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("extra_icon")
        }

        data?.let {
            binding.apply {
                imgDetailPhoto.setImageResource(it.photo)
                tvDetailName.text = it.name
                tvDetailLocation.text = it.location
                tvDetailDescription.text = getString(it.descriptionRes)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}