package model

import android.util.Base64

class GlobalVar {
    companion object{
        val ListDataHewan = ArrayList<Hewan>()
        val listDataAyam = ArrayList<Ayam>()
        val listDataSapi = ArrayList<Sapi>()
        val listDataKambing = ArrayList<Kambing>()
        val StatusAdd = 1
        val StatusEdit = 2

        fun ByteArrToString(bArray: ByteArray): String {
            return Base64.encodeToString(bArray, Base64.DEFAULT)
        }

        fun StringToByteArr(raw: String):ByteArray{
            return Base64.decode(raw, Base64.DEFAULT)
        }
    }
}