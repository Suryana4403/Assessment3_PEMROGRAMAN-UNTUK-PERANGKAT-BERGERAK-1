package org.d3if6706202120.assessment1.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if6706202120.assessment1.R
import org.d3if6706202120.assessment1.databinding.ListItemBinding
import org.d3if6706202120.assessment1.model.main.BangunDatar
import org.d3if6706202120.assessment1.network.BangunDatarApi

class BangunDatarAdapter : RecyclerView.Adapter<BangunDatarAdapter.ViewHolder>() {

    private val data = mutableListOf<BangunDatar>()

    fun updateData(newData: List<BangunDatar>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bangunDatar: BangunDatar) = with(binding) {
            namaTextView.text = bangunDatar.nama
            Glide.with(imageView.context)
                .load(BangunDatarApi.getBangunDatarUrl(bangunDatar.image))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)


            root.setOnClickListener {
                val message = root.context.getString(R.string.message, bangunDatar.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener(
           Navigation.createNavigateOnClickListener(R.id.action_bangunDatarFragment_to_persegiPanjangFragment)
        )

    }

    override fun getItemCount(): Int {
        return data.size
    }
}