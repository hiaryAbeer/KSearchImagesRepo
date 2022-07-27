package com.abeerapps.ishowimagesapp.images.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.abeerapps.ishowimagesapp.databinding.ImageRowBinding
import com.abeerapps.ishowimagesapp.images.domain.models.Result
import com.abeerapps.ishowimagesapp.images.ui.viewmodel.ImagesViewModel


class ImagesAdapter(val list: List<Result>, val viewModel: ImagesViewModel) :
    RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {

    inner class ImagesViewHolder(val binding: ImageRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.textView.text = list[position].description ?: "Car"
            binding.imageView.load(list[position].urls.small)
            binding.textViewPhotographer.text = list[position].user.name

            list[position].user.social.portfolio_url?.let {link ->
                binding.textViewPhotographer.setTextColor(Color.BLUE)
                binding.textViewPhotographer.setOnClickListener {
                    viewModel.openPhotographerPortfolio(link)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ImageRowBinding.inflate(LayoutInflater.from(parent.context))
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size
}