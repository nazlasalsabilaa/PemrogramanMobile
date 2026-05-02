package com.nazlasalsabila.global_icons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.nazlasalsabila.global_icons.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<Icon>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        list.clear()
        list.addAll(Icon_Data.list_icons)

        setupBanner()
        setupRecycler()
    }

    private fun setupBanner() {
        binding.rvBanner.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvBanner.adapter = BannerAdapter(ArrayList(list))

        if (binding.rvBanner.onFlingListener == null) {
            PagerSnapHelper().attachToRecyclerView(binding.rvBanner)
        }
    }

    private fun setupRecycler() {
        binding.rvIcons.layoutManager = LinearLayoutManager(requireContext())

        val adapter = ListIconAdapter(list)
        binding.rvIcons.adapter = adapter

        adapter.setOnItemClickCallback(object : ListIconAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Icon) {

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