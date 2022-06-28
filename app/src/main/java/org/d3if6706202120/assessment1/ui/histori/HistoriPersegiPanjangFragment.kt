package org.d3if6706202120.assessment1.ui.histori

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if6706202120.assessment1.R
import org.d3if6706202120.assessment1.databinding.FragmentHistoriPersegipanjangBinding
import org.d3if6706202120.assessment1.db.PersegiPanjangDb


class HistoriPersegiPanjangFragment : Fragment() {

    private val viewModel: HistoriPersegiPanjangViewModel by lazy {
        val db = PersegiPanjangDb.getInstance(requireContext())
        val factory = HistoriPersegiPanjangViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HistoriPersegiPanjangViewModel::class.java]
    }

    private lateinit var binding: FragmentHistoriPersegipanjangBinding
    private lateinit var myAdapter: HistoriPersegiPanjangAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHistoriPersegipanjangBinding.inflate(layoutInflater,
            container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoriPersegiPanjangAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }

        viewModel.data.observe(viewLifecycleOwner, {
            binding.emptyView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            myAdapter.submitList(it)
        })
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.histori_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_hapus) {
            hapusData()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun hapusData() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.konfirmasi_hapus)
            .setPositiveButton(getString(R.string.hapus)) { _, _ ->
                viewModel.hapusData()
            }
            .setNegativeButton(getString(R.string.batal)) { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

}