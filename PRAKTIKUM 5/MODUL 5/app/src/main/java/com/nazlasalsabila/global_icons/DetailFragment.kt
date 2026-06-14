package com.nazlasalsabila.global_icons

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.nazlasalsabila.global_icons.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("extra_icon", GlobalIcon::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("extra_icon")
        }

        data?.let {
            binding.apply {
                tvDetailName.text = it.name
                tvDetailLocation.text = "${getString(R.string.label_release)} ${it.releaseDate}"
                tvDetailDescription.text = it.overview.ifEmpty { "Sinopsis tidak tersedia." }

                Glide.with(this@DetailFragment)
                    .load("https://image.tmdb.org/t/p/w500${it.photoUrl}")
                    .into(imgDetailPhoto)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}