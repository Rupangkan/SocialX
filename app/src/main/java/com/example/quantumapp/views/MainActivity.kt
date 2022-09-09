package com.example.quantumapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.quantumapp.R
import com.example.quantumapp.databinding.ActivityMainBinding
import com.example.quantumapp.views.adapters.ViewPagerAdapter
import com.facebook.FacebookSdk

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setAdapters()
    }

    private fun setAdapters() {
        val adapter =  ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(), "Login")
        adapter.addFragment(SignInFragment(), "Sign UP")
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}