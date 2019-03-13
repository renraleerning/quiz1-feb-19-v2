/*
     Robot yang akan bertarung. Robot punya senjata, perisai dan skor kesehatan.
 */
public class Robot {
    Senjata[] oSenjata= new Senjata[2];
    Perisai[] oPerisai= new Perisai[2];
    int kesehatan;
    String nama;


    public void printSenjata(){
        for (int i=0;i<2;i++){
                System.out.println("["+(i+1)+"]"+oSenjata[i].nama+" Kekuatan : "+oSenjata[i].kekuatan+" Reflect : "+oSenjata[i].reflect);
        }
    }

    public void printPerisai(){
        for (int i=0;i<2;i++){
            System.out.println("["+(i+1)+"]"+oPerisai[i].nama+" Kekuatan : "+oPerisai[i].kekuatan);
        }
    }

    //isi senjata milik robot
    public void  tambahSenjata(Senjata s, int ke) {
        oSenjata[ke] = s;
    }

    //isi perisai milik robot
    public void  tambahPerisai(Perisai p, int ke) {
        oPerisai[ke] = p;
    }

    //print kesehatan dsb
    public void printStatistik() {
        System.out.println("Nama Robot: "+nama);
        System.out.println("Kesehatan:"+kesehatan);
    }

    //constructor
    public Robot(String vNama) {
        nama = vNama;
        kesehatan = 150; //default
    }

    /* menyerang robot lain, skor kesehatan robot lain akan berkurang
    */
    public void serang(Robot rLawan, int senjatake, int perisaike) {
       //skor kesehatan robot lawan akan dikurangi
        int deff=0;
        System.out.println("Menyerang dengan senjata "+oSenjata[senjatake].nama+" dmg : "+oSenjata[senjatake].kekuatan);
        if(rLawan.oPerisai[perisaike].dura>0){
            System.out.print("Ditahan dengan perisai "+rLawan.oPerisai[perisaike].nama);
            rLawan.oPerisai[perisaike].dura--;
            switch (rLawan.oPerisai[perisaike].tipe){ //pengurangan kesehatan robot berdasarkan tipe perisai lawan
                case 1:
                    deff = oSenjata[senjatake].kekuatan - rLawan.oPerisai[perisaike].kekuatan;
                    rLawan.kesehatan =  rLawan.kesehatan - deff;
                    break;
                case 2:
                    deff = (oSenjata[senjatake].kekuatan*(100-rLawan.oPerisai[perisaike].kekuatan)/100);
                    rLawan.kesehatan = rLawan.kesehatan - deff;
                    break;
                case 3:
                    deff = (oSenjata[senjatake].kekuatan*(100-rLawan.oPerisai[perisaike].kekuatan)/100);
                    rLawan.kesehatan = rLawan.kesehatan - deff;
                    int reflect = rLawan.oPerisai[perisaike].EX*oSenjata[senjatake].kekuatan/100;
                    System.out.print(" reflect : " +reflect);
                    kesehatan=kesehatan-reflect;
                    break;
            }
            System.out.print(" deff : "+(oSenjata[senjatake].kekuatan-deff));
        }else{
            System.out.print("durabiliti perisai habis!!");
            rLawan.kesehatan = rLawan.kesehatan - (oSenjata[senjatake].kekuatan);
        }
        kesehatan=kesehatan-oSenjata[senjatake].reflect;
        System.out.println("\nterkena reflect dari senjata sebanyak "+oSenjata[senjatake].reflect);
    }
}
