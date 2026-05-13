package com.nazlasalsabila.global_icons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.nazlasalsabila.global_icons.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: IconViewModel by viewModels {
        IconViewModelFactory("Nazla")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        observeData()
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.iconList.collect { list ->
                setupBanner(ArrayList(list))
                setupRecycler(ArrayList(list))
            }
        }
    }

    private fun setupBanner(list: ArrayList<Icon>) {
        binding.rvBanner.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvBanner.adapter = BannerAdapter(list)

        if (binding.rvBanner.onFlingListener == null) {
            PagerSnapHelper().attachToRecyclerView(binding.rvBanner)
        }
    }

    private fun setupRecycler(list: ArrayList<Icon>) {
        binding.rvIcons.layoutManager = LinearLayoutManager(requireContext())

        val adapter = ListIconAdapter(list)
        binding.rvIcons.adapter = adapter

        adapter.setOnItemClickCallback(object : ListIconAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Icon) {

                viewModel.selectItem(data)

                Timber.d("Tombol Detail ditekan: ${data.name}")

                val bundle = Bundle()
                bundle.putParcelable("extra_icon", data)

                findNavController().navigate(
                    R.id.detailFragment,
                    bundle
                )
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}