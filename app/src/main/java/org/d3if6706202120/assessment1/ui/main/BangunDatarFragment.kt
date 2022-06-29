package org.d3if6706202120.assessment1.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if6706202120.assessment1.R
import org.d3if6706202120.assessment1.databinding.FragmentBangundatarBinding
import org.d3if6706202120.assessment1.model.main.BangunDatar
import org.d3if6706202120.assessment1.ui.histori.HistoriPersegiPanjangAdapter

class BangunDatarFragment : Fragment() {

    private val viewModel: BangunDatarMainViewModel by lazy {
        ViewModelProvider(this).get(BangunDatarMainViewModel::class.java) }

    private lateinit var binding: FragmentBangundatarBinding
    private lateinit var myAdapter: BangunDatarAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentBangundatarBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner, {
            myAdapter.updateData(it)
        })

        myAdapter = BangunDatarAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
    }
}
