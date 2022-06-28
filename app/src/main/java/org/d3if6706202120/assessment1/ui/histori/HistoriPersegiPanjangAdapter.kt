package org.d3if6706202120.assessment1.ui.histori


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if6706202120.assessment1.R
import org.d3if6706202120.assessment1.databinding.ItemHistoriBinding
import org.d3if6706202120.assessment1.db.PersegiPanjangEntity
import org.d3if6706202120.assessment1.model.hitungPersegiPanjang
import java.text.SimpleDateFormat
import java.util.*


class HistoriPersegiPanjangAdapter : ListAdapter<PersegiPanjangEntity, HistoriPersegiPanjangAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<PersegiPanjangEntity>() {
                override fun areItemsTheSame(
                    oldData: PersegiPanjangEntity, newData: PersegiPanjangEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: PersegiPanjangEntity, newData: PersegiPanjangEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        fun bind(item: PersegiPanjangEntity) = with(binding) {
            val hasilPersegiPanjang = item.hitungPersegiPanjang()

            tanggal.text = dateFormatter.format(Date(item.tanggal))
            luast.text = root.context.getString(
                R.string.luasx,
                hasilPersegiPanjang.luas.toString()
            )
            kelilingt.text = root.context.getString(
                R.string.kelilingx,
                hasilPersegiPanjang.keliling.toString()
            )
            data.text = root.context.getString(R.string.datax,
                item.panjang.toString(), item.lebar.toString())
        }
    }
}