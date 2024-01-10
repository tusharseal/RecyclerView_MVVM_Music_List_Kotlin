package com.example.rvmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rvmvvm.databinding.RowMusicItemBinding

import com.example.rvmvvm.model.MusicModel

class MusicAdapter(val onClickListener: OnClickInterface) :
    ListAdapter<MusicModel.Data, MusicAdapter.NotesViewHolder>(
        ComparatorDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding =
            RowMusicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val customer = getItem(position)
        customer?.let {
            holder.bind(it, position)
        }

    }

    inner class NotesViewHolder(private val binding: RowMusicItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: MusicModel.Data, position: Int) {

            binding.musicName.text = notification.title.toString()
            Glide.with(binding.root).load(notification.imageFile).into(binding.musicImg)
            binding.musicPaid.text = notification.paidFree
            binding.musicId.text = notification.id.toString()!!
            binding.musicSubtitile.text = notification.subTitle
            binding.musicTime.text = notification.minute

            binding.musicRootRl.setOnClickListener {
                notifyItemChanged(position)
                onClickListener.onItemClicked(notification)
            }

        }
    }

    interface OnClickInterface {
        fun onItemClicked(doc: MusicModel.Data)
    }


    class ComparatorDiffUtil : DiffUtil.ItemCallback<MusicModel.Data>() {
        override fun areItemsTheSame(
            oldItem: MusicModel.Data,
            newItem: MusicModel.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MusicModel.Data,
            newItem: MusicModel.Data
        ): Boolean {
            return oldItem == newItem
        }

    }
}