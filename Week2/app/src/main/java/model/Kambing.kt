package model

class Kambing(namaHewan: String, umurHewan: Int, fotoHewan: String) :
    Hewan(namaHewan, "Kambing", umurHewan, fotoHewan) {
    override val suara = "Mbeeek~"
    override val makan = "rerumputan"
}