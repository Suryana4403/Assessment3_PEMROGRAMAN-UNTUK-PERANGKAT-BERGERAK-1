package org.d3if6706202120.assessment1.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if6706202120.assessment1.R
import org.d3if6706202120.assessment1.databinding.FragmentBangundatarBinding
import org.d3if6706202120.assessment1.model.main.BangunDatar
import org.d3if6706202120.assessment1.network.ApiStatus


class BangunDatarFragment : Fragment() {
    
    private val viewModel: BangunDatarMainViewModel by lazy {
        ViewModelProvider(this).get(BangunDatarMainViewModel::class.java)
    }

    private lateinit var binding: FragmentBangundatarBinding
    private lateinit var myAdapter: BangunDatarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBangundatarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })

        viewModel.getStatus().observe(viewLifecycleOwner, {
            updateProgress(it)
        })
        viewModel.scheduleUpdater(requireActivity().application)

        binding.recyclerView.setOnClickListener {
                it.findNavController().navigate(
                    R.id.action_bangunDatarFragment_to_persegiPanjangFragment
                )
        }

        myAdapter = BangunDatarAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE

            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }

}
