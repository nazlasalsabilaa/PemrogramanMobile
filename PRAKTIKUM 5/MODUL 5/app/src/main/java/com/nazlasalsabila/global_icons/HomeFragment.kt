package com.nazlasalsabila.global_icons

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nazlasalsabila.global_icons.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListIconAdapter

    private val viewModel: GlobalIconViewModel by viewModels {
        val prefs = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)
        val apiLang = prefs.getString("api_lang", "en-US") ?: "en-US"
        val repository = Injection.provideRepository(requireContext(), apiLang)
        ViewModelFactory(repository = repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupRecycler()
        observeData()
        setupSearch()
    }

    private fun setupRecycler() {
        adapter = ListIconAdapter(arrayListOf())
        adapter.setOnItemClickCallback(object : ListIconAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GlobalIcon) {
                val bundle = Bundle().apply { putParcelable("extra_icon", data) }
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
        })
        binding.rvIcons.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIcons.adapter = adapter
    }

    private fun setupSearch() {
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return true
            }
        })
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.icons.collect { list ->
                adapter.updateData(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}