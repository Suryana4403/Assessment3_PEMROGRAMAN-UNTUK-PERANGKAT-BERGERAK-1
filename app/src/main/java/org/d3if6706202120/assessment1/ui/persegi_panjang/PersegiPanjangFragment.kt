package org.d3if6706202120.assessment1.ui.persegi_panjang

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import org.d3if6706202120.assessment1.R
import org.d3if6706202120.assessment1.databinding.FragmentPersegipanjangBinding
import org.d3if6706202120.assessment1.db.PersegiPanjangDb
import org.d3if6706202120.assessment1.model.persegi_panjang.HasilPersegiPanjang

class PersegiPanjangFragment : Fragment() {
    private lateinit var binding: FragmentPersegipanjangBinding

    private val viewModel: PersegiPanjangViewModel by lazy {
        val db = PersegiPanjangDb.getInstance(requireContext())
        val factory = PersegiPanjangViewModelFactory(db.dao)

        ViewModelProvider(this, factory)[PersegiPanjangViewModel::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentPersegipanjangBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.hitung.setOnClickListener { hitung() }
        binding.reset.setOnClickListener { reset() }
        binding.bagikan.setOnClickListener { shareData() }
        viewModel.getHasilHitung().observe(requireActivity(), { showResult(it) })
        binding.lihatrumus.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_persegiPanjangFragment_to_rumusPersegiPanjangFragment
            )
        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                R.id.action_persegiPanjangFragment_to_historiFragmentPersegiPanjang
                )
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_persegiPanjangFragment_to_aboutPersegiPanjangFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hitung() {
        val panjang = binding.valuePanjang.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val lebar = binding.valueLebar.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitung(
            panjang.toInt(),
            lebar.toInt()
        )
    }
    private fun reset() {
        binding.valuePanjang.text = (null)
        binding.valueLebar.text = (null)
        binding.keliling.text = "Hasil Keliling ="
        binding.luas.text = "Hasil Luas ="
    }
    private fun showResult(result: HasilPersegiPanjang?){
        if(result == null) return
        binding.keliling.text = getString(R.string.keliling_x,result.keliling)
        binding.luas.text = getString(R.string.luas_x, result.luas)
        binding.bagikan.visibility = View.VISIBLE
    }
    private fun shareData() {

        val message = getString(R.string.bagikan_template,
            binding.valuePanjang.text,
            binding.valueLebar.text,
            binding.keliling.text,
            binding.luas.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        }
    }
}