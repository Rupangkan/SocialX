package com.example.quantumapp.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quantumapp.common.Constants.RECYCLERVIEW_SIZE
import com.example.quantumapp.databinding.SingleCardViewBinding
import com.example.quantumapp.models.News

class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(binding: SingleCardViewBinding): RecyclerView.ViewHolder(binding.root) {
        // Create the View Holder here
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val binding = SingleCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        // Do nothing for now as we have given default values
    }

    override fun getItemCount(): Int {
        return RECYCLERVIEW_SIZE
    }
}