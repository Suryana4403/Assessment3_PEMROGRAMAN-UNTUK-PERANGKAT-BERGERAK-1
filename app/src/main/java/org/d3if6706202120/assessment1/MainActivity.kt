package org.d3if6706202120.assessment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if6706202120.assessment1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hitung.setOnClickListener { hitung() }

    }

    private fun hitung() {
        val panjang = binding.valuePanjang.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(this, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val lebar = binding.valueLebar.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(this, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val keliling = (2 * (panjang.toInt() + lebar.toInt()))
        val luas = panjang.toInt() * lebar.toInt()
        binding.keliling.text = getString(R.string.keliling_x, keliling)
        binding.luas.text = getString(R.string.luas_x, luas)
    }
}