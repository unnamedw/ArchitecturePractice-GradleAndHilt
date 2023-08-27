package com.doachgosum.listsampleapp.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doachgosum.listsampleapp.R
import com.doachgosum.listsampleapp.databinding.ViewHolderPhotoBinding
import com.doachgosum.listsampleapp.domain.model.PhotoModel

class PhotoListViewHolder(
    private val binding: ViewHolderPhotoBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: PhotoModel) {
        binding.tvId.text = photo.id.toString()
        binding.tvTitle.text = photo.title

        Glide.with(binding.root.context)
            .load(photo.thumbnailUrl)
            .centerCrop()
            .placeholder(R.drawable.img_image_loading)
            .error(R.drawable.img_image_error)
            .into(binding.ivThumbnail)
    }

}