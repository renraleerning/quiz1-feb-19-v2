/*
    Perisai berfungsi untuk mengurangi efektifitas serangan lawan.
    Skor kesehatan akan lebih kecil berkurang jika diserang lawan.


    dalam pembuatan perisai ada 2 tipe dampaknya saat digunakan,
    - yang pertama mengsurangi serangan langsung dengan kekuatannya
    - yang kedua perisai akan mengurangi serangan dengan kekuatannya/100 (berapa persen dari serangan lawan)
    durability =  hanya berapa kali perisai dapat digunakan
    - tipe ketiga (EX) = berpeluang mengembalikan serangan balik pada lawan (reflect) dan bertipe kedua
 */

public class Perisai {
    //lengkapi
    String nama;
    int kekuatan;
    int dura;
    int tipe;
    int EX=0;
}
