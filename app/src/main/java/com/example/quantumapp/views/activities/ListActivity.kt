package com.example.quantumapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quantumapp.R
import com.example.quantumapp.databinding.ActivityListBinding
import com.example.quantumapp.viewmodels.AppViewModel
import com.example.quantumapp.views.adapters.NewsAdapter

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private val searchText = "bitcoin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.adapter = NewsAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
    }
}