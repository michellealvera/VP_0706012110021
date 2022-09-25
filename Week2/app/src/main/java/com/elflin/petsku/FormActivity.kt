package com.elflin.petsku

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.get
import com.elflin.petsku.databinding.ActivityFormBinding
import model.*
import java.lang.NumberFormatException
import java.util.*

class FormActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityFormBinding
    private lateinit var imageArray: ByteArray
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        GetIntent()
        SetupListener()
    }

    private fun GetIntent(){
        if (intent.getIntExtra("status", 0) == GlobalVar.StatusAdd){
            viewBinding.FormTittleEdit.visibility = View.INVISIBLE
        }else if (intent.getIntExtra("status", 0) == GlobalVar.StatusEdit){
            position = intent.getIntExtra("position", -1)
            viewBinding.FormTittleTambah.visibility = View.INVISIBLE
            viewBinding.FormInputNama.editText?.setText(GlobalVar.ListDataHewan[position].namaHewan)
            viewBinding.FormInputUmurHewan.editText?.setText(GlobalVar.ListDataHewan[position].umurHewan.toString())
            viewBinding.radioGroup.setOnCheckedChangeListener { radioGroup, i -> GlobalVar.ListDataHewan[position].jenisHewan }
            if(GlobalVar.ListDataHewan[position].fotoHewan != "null") {
                val bArray = GlobalVar.StringToByteArr(GlobalVar.ListDataHewan[position].fotoHewan)
                val options = BitmapFactory.Options()
                options.inSampleSize = 2
                options.inScaled = true
                val bitMap = BitmapFactory.decodeByteArray(
                    bArray,
                    0,
                    bArray.size,
                    options
                )
                viewBinding.FormPicture.setImageBitmap(bitMap)
            }
        }
    }

    private fun SetupListener(){
        viewBinding.FormPicture.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_PICK)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBinding.FormBackButton.setOnClickListener {
            finish()
        }
        viewBinding.FormInputButton.setOnClickListener{
            try{
                val radioButtonID = viewBinding.radioGroup.checkedRadioButtonId
                val radioButton = viewBinding.radioGroup[radioButtonID]
                val hewanID = viewBinding.radioGroup.indexOfChild(radioButton)
                val jenisHewannya = viewBinding.radioGroup.getChildAt(hewanID).toString()
                var animal: Hewan
                if (jenisHewannya == "Ayam"){
                    animal = Ayam(
                        viewBinding.FormInputNama.editText?.text.toString().trim(),
                        viewBinding.FormInputUmurHewan.editText?.text.toString().trim().toInt(),
                        GlobalVar.ByteArrToString(imageArray!!)
                    )
                } else if (jenisHewannya == "Sapi"){
                    animal = Sapi(
                        viewBinding.FormInputNama.editText?.text.toString().trim(),
                        viewBinding.FormInputUmurHewan.editText?.text.toString().trim().toInt(),
                        GlobalVar.ByteArrToString(imageArray!!)
                    )
                } else {
                    animal = Kambing(
                        viewBinding.FormInputNama.editText?.text.toString().trim(),
                        viewBinding.FormInputUmurHewan.editText?.text.toString().trim().toInt(),
                        GlobalVar.ByteArrToString(imageArray!!)
                    )
                }

                if (FormChecker(animal)){
                    if (intent.getIntExtra("status", 0) == GlobalVar.StatusAdd){
                        GlobalVar.ListDataHewan.add(animal)
                        if (animal.jenisHewan == "Ayam") {
                            GlobalVar.listDataAyam.add(animal as Ayam)
                        } else if (animal.jenisHewan == "Sapi") {
                            GlobalVar.listDataSapi.add(animal as Sapi)
                        } else {
                            GlobalVar.listDataKambing.add(animal as Kambing)
                        }
                    } else if (intent.getIntExtra("status", 0) == GlobalVar.StatusEdit){
                        GlobalVar.ListDataHewan[position] = animal
                        for ((index, animals) in GlobalVar.listDataAyam.withIndex()) {
                            if (animals.namaHewan == animal.namaHewan) {
                                if (animals.jenisHewan == animal.jenisHewan) {
                                    if (animals.umurHewan == animal.umurHewan) {
                                        GlobalVar.listDataAyam[index] = animal as Ayam
                                    }
                                }
                            }
                        }
                        for ((index, animals) in GlobalVar.listDataSapi.withIndex()) {
                            if (animals.namaHewan == animal.namaHewan) {
                                if (animals.jenisHewan == animal.jenisHewan) {
                                    if (animals.umurHewan == animal.umurHewan) {
                                        GlobalVar.listDataSapi[index] = animal as Sapi
                                    }
                                }
                            }
                        }
                        for ((index, animals) in GlobalVar.listDataKambing.withIndex()) {
                            if (animals.namaHewan == animal.namaHewan) {
                                if (animals.jenisHewan == animal.jenisHewan) {
                                    if (animals.umurHewan == animal.umurHewan) {
                                        GlobalVar.listDataKambing[index] = animal as Kambing
                                    }
                                }
                            }
                        }
                    }
                    Toast.makeText(baseContext, "Data berhasil di simpan", Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(baseContext, "Data gagal di simpan", Toast.LENGTH_LONG).show()
                }
            }catch (e: NumberFormatException){
                viewBinding.FormInputUmurHewan.error = "Umur hewan belum terisi"
            }catch (e: UninitializedPropertyAccessException){
                if (intent.getIntExtra("status", 0) == GlobalVar.StatusEdit){
                    val radioButtonID = viewBinding.radioGroup.checkedRadioButtonId
                    val radioButton = viewBinding.radioGroup[radioButtonID]
                    val hewanID = viewBinding.radioGroup.indexOfChild(radioButton)
                    val jenisHewannya = viewBinding.radioGroup.getChildAt(hewanID).toString()
                    val animal: Hewan
                    if (jenisHewannya == "Ayam"){
                        animal = Ayam(
                            viewBinding.FormInputNama.editText?.text.toString().trim(),
                            viewBinding.FormInputUmurHewan.editText?.text.toString().trim().toInt(),
                            GlobalVar.ByteArrToString(imageArray!!)
                        )
                    } else if (jenisHewannya == "Sapi"){
                        animal = Sapi(
                            viewBinding.FormInputNama.editText?.text.toString().trim(),
                            viewBinding.FormInputUmurHewan.editText?.text.toString().trim().toInt(),
                            GlobalVar.ByteArrToString(imageArray!!)
                        )
                    } else {
                        animal = Kambing(
                            viewBinding.FormInputNama.editText?.text.toString().trim(),
                            viewBinding.FormInputUmurHewan.editText?.text.toString().trim().toInt(),
                            GlobalVar.ByteArrToString(imageArray!!)
                        )
                    }

                    if (FormChecker(animal)){
                        GlobalVar.ListDataHewan[position] = animal
                        Toast.makeText(baseContext, "Data berhasil di simpan", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(baseContext, "Data gagal di simpan", Toast.LENGTH_LONG).show()
                    }
                }
                Toast.makeText(baseContext, "Foto Hewan belum di pilih", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun FormChecker(animal:Hewan): Boolean {

        var isCompleted = true

        if(animal.namaHewan.isEmpty()){
            viewBinding.FormInputNama.error = "Nama hewan belum terisi"
            isCompleted = false
        }else{
            viewBinding.FormInputNama.error = ""
        }

        if(animal.umurHewan == 0){
            viewBinding.FormInputUmurHewan.error = "Umur hewan belum terisi"
            isCompleted = false
        }else{
            viewBinding.FormInputUmurHewan.error = ""
        }

        return isCompleted
    }

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data                 // GET PATH TO IMAGE FROM GALLEY
            viewBinding.FormPicture.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
            if (uri != null) {
                val inputStream = contentResolver.openInputStream(uri)
                inputStream?.buffered()?.use {
                    imageArray = it.readBytes()
                }
            }
        }
    }
}