package com.vp.week1_0706012110021

import Database.GlobalVar
import Model.Hewan
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.vp.week1_0706012110021.databinding.ActivityInputanimalBinding

class InputanimalActivity : AppCompatActivity() {
    private lateinit var viewBind:ActivityInputanimalBinding
    private lateinit var hewan: Hewan
    private var position = -1

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data                 // GET PATH TO IMAGE FROM GALLEY
            viewBind.editImageButton.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
            GlobalVar.listDataHewan[position].imageUri = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBind.root)
        GetIntent()
        Listener()
    }

    private fun GetIntent(){
        position = intent.getIntExtra("position", -1)
        if (position != -1){
            val hewan = GlobalVar.listDataHewan[position]
            Display(hewan)
        }
    }

    private fun Listener(){
        viewBind.saveButton.setOnClickListener {
            var nama = viewBind.namaTextInputLayout.editText?.text.toString().trim()
            var jenis = viewBind.jenisTextInputLayout.editText?.text.toString().trim()
            var usia = viewBind.usiaTextInputLayout.editText?.text.toString().trim()

            var hewann = Hewan(nama,jenis,usia)

            checker()
        }

        viewBind.editImageButton.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_PICK)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }
    }

    private fun Display(hewan: Hewan){
        viewBind.namaTextInputLayout.editText?.setText(hewan.namanya)
        viewBind.jenisTextInputLayout.editText?.setText(hewan.jenisnya)
        hewan.usianya?.let { viewBind.usiaTextInputLayout.editText?.setText(it) }
        if (hewan.imageUri.isNotEmpty()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                baseContext.contentResolver.takePersistableUriPermission(
                    Uri.parse(hewan.imageUri),
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
            viewBind.editImageButton.setImageURI(Uri.parse(hewan.imageUri))
            }
        }

    private fun checker(){
        var isCompleted:Boolean = true

        if (hewan.namanya!!.isEmpty()){
            viewBind.namaTextInputLayout.error = "Mohon isi kolom nama hewan"
            isCompleted = false
        } else{
            viewBind.namaTextInputLayout.error = ""
        }

        if (hewan.jenisnya!!.isEmpty()){
            viewBind.jenisTextInputLayout.error = "Mohon isi kolom jenis hewan"
            isCompleted = false
        } else{
            viewBind.jenisTextInputLayout.error = ""
        }

        if (hewan.usianya == null) {
            viewBind.usiaTextInputLayout.error = "Mohon isi kolom usia hewan"
            isCompleted = false
        } else if (hewan.usianya!!.toInt() <= 0) {
            viewBind.usiaTextInputLayout.error = "Mohon isi usia hewan >0 tahun"
            isCompleted = false
        } else{
            viewBind.usiaTextInputLayout.error = ""
        }

        if (isCompleted){
            if (position==-1){
                GlobalVar.listDataHewan.add(hewan)
                Toast.makeText(this, "${hewan.namanya} berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            } else {
                GlobalVar.listDataHewan[position] = hewan
                Toast.makeText(this, "Data ${hewan.namanya} berhasil diubah", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }
}