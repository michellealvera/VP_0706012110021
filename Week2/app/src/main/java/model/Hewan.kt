package model

abstract class Hewan (
    val namaHewan: String,
    val jenisHewan: String,
    val umurHewan: Int,
    val fotoHewan: String
) {
    abstract val suara: String
    abstract val makan: String
}