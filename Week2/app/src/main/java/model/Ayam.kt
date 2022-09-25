package model

class Ayam(namaHewan: String, umurHewan: Int, fotoHewan: String) :
    Hewan(namaHewan, "Ayam", umurHewan, fotoHewan) {
    override val suara = "Cock-a-doodle-doo"
    override val makan = "biji-bijian"
}