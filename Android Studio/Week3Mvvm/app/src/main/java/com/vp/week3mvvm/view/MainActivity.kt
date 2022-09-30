package com.vp.week3mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vp.week3mvvm.R
import com.vp.week3mvvm.databinding.ActivityMainBinding
import com.vp.week3mvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.currentResult.observe(this, Observer {
            binding.tvHasil.text = it.toString()
        })
        binding.btnHitung.setOnClickListener {
            val angka1 = binding.etAngka1.text.toString()
            val angka2 = binding.etAngka2.text.toString()

            when{
                angka1.isEmpty()->binding.etAngka1.error = "Angka 1 harus diisi!"
                angka2.isEmpty()->binding.etAngka2.error = "Angka 2 harus diisi!"
                else -> {
                    mainViewModel.hasilPenjumlahan(angka1.toInt(), angka2.toInt())
                    //binding.tvHasil.text = mainViewModel.result.toString()
                    mainViewModel.currentResult.value = mainViewModel.result
                }
            }
        }
    }
}