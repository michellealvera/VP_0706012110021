package model

class Sapi(namaHewan: String, umurHewan: Int, fotoHewan: String) :
    Hewan(namaHewan, "Sapi", umurHewan, fotoHewan) {
    override val suara = "Mooo~"
    override val makan = "rerumputan"
}