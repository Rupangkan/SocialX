package com.example.quantumapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quantumapp.databinding.ActivityMainBinding
import com.example.quantumapp.views.fragments.LoginFragment
import com.example.quantumapp.views.fragments.SignInFragment
import com.example.quantumapp.views.adapters.ViewPagerAdapter

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