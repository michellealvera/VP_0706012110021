package com.vp.week1_0706012110021

import Adapter.ListHewanRVAdapter
import Database.GlobalVar
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.GridLayoutManager
import com.vp.week1_0706012110021.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBind:ActivityMainBinding
    private val adapter = ListHewanRVAdapter(GlobalVar.listDataHewan)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        CheckPermissions()
        setupRecyclerView()
        checkIfEmpty()
        listener()
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun CheckPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), GlobalVar.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == GlobalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(baseContext, 2)
        viewBind.dataHewanRecyclerView.layoutManager = layoutManager
        viewBind.dataHewanRecyclerView.adapter = adapter
    }

    private fun checkIfEmpty(){
        if (viewBind.dataHewanRecyclerView.isEmpty()){
            viewBind.ifEmptyTextView.text = "Masukkan data hewanmu"
        } else{
            viewBind.ifEmptyTextView.text = ""
        }
    }

    private fun listener(){
        viewBind.addAnimalFAB.setOnClickListener {
            val myIntent = Intent(this, InputanimalActivity::class.java)
            startActivity(myIntent)
        }
    }
}