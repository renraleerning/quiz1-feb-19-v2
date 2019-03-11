/*
     Menjalankan pertarungan antar dua robot
 */
import java.util.Scanner;

public class Arena {
    private Robot robot1;
    private Robot robot2;
    String pemenang;

    public void tambahRobot(Robot r1,Robot r2) {
        robot1 = r1;
        robot2 = r2;
    }
    //pakai senjata
    public void pakai_Senjata(Robot rob, int sen, int i){
        switch (sen){
            case 1:
                Senjata oSenjataPukulan = new SenjataPukulan();
                rob.tambahSenjata(oSenjataPukulan, i);
                break;
            case 2:
                Senjata oSenjataKilat = new SenjataKilat();
                rob.tambahSenjata(oSenjataKilat, i);
                break;
            case 3 :
                Senjata oSenjataBeam = new SenjataBeam();
                rob.tambahSenjata(oSenjataBeam, i);
                break;
        }
    }

    public void pakai_Perisai(Robot rob, int sen, int i){
        switch (sen){
            case 1:
                Perisai oPerisaiInfinity = new PerisaiInfinity();
                rob.tambahPerisai(oPerisaiInfinity, i);
                break;
            case 2:
                Perisai oPerisaiImortal = new PerisaiImortal();
                rob.tambahPerisai(oPerisaiImortal, i);
                break;
            case 3 :
                Perisai oPerisaiGatot = new PerisaiGatot();
                rob.tambahPerisai(oPerisaiGatot, i);
                break;
        }
    }

    public void bertanding() {

        int[] Eq1= new int[4]; //senjata player
        int sen = 0;//pilihan senjata/perisai
        int pil; //pilihan untuk UI serang/bertahan
        Scanner in = new Scanner(System.in);

        //UI sederhana untuk pertandingan


        //loop sampai salah satu robot habis skor kesehatannya
        boolean isSelesai = false;



        //tambahkan senjata ke robot
        System.out.println("Silahkan pilih senjata ");
        for (int i=0;i<2;i++){
            System.out.println("Senjata ke"+(i+1)+" : ");
            System.out.println("[1] Senjata Pukulan (kekuatan = 40 reflect = 10");
            System.out.println("[2] Senjata Kilat (kekuatan = 70 reflect = 20");
            System.out.println("[3] Senjata Bean (kekuatan = 50 reflect = 15");
            System.out.print("Masukkan Pilihan : ");
            sen = in.nextInt();
            pakai_Senjata(robot1,sen,i); //pakai senjata player
            sen = (int) (Math.random()*2); //random senjata bot
            sen++;//karena pilihan dari 1-3 jadi (random 0-2)+1
            pakai_Senjata(robot2,sen,i); //pakai senjata bot
        }

        for (int i=0;i<2;i++){
            System.out.println("Perisai ke-"+(i+1)+" : ");
            System.out.println("[1] Perisai infinity (tipe1)");
            System.out.println("[2] Perisai Imortal (tipe2)");
            System.out.println("[3] Perisai Gatot (tipe3 EX)");
            System.out.print("Masukkan Pilihan : ");
            sen = in.nextInt();
            pakai_Perisai(robot1,sen,i); //pakai perisai player
            sen = (int) (Math.random()*2);//random perisai bot
            sen++;//karena pilihan dari 1-3 jadi (random 0-2)+1
            pakai_Perisai(robot2,sen,i);//pakai perisai bot
        }


        //LENGKAPI dengan NIM dan NAMA
        System.out.println("\nSaya berjanji tdk berbuat curang dan/atau membantu orang lain berbuat curang");
        System.out.println(" Quiz 1 27 Feb ");
        System.out.println(" 1601330 Renra Noviana ");

        System.out.print("Pertandingan dimulai =====\n");
        //print kesehatan
        System.out.println("\n<<<<STATUS>>>>");
        robot1.printStatistik();
        robot2.printStatistik();

        Robot robotAktif = robot1;
        Robot robotPasif = robot2;

        int j=0;//variabel untuk pengecekan giliran

        while (!isSelesai) {
            System.out.println("");
            System.out.println("Giliran robot:"+robotAktif.nama);
            if (j % 2==0){//untuk memeriksa apakah giliran player/bot sehingga sistem UI disesuaikan
                System.out.println("Serang dengan menggunakan : ");
                robotAktif.printSenjata();
                System.out.print("Pilihan : ");
                sen = in.nextInt();
                pil = (int) (Math.random()*1);//random perisai bot untuk bertahan;
                pil++;
            }else{
                System.out.println("Robot"+robotAktif.nama+"akan menyerang!");
                sen = (int) (Math.random()*1);//random senjata bot untuk menyerang
                sen++;
                System.out.println("tahan menggunakan : ");
                robotPasif.printPerisai();
                System.out.print("Pilihan : ");
                pil = in.nextInt();
            }
            robotAktif.serang(robotPasif,sen-1,pil-1);

            //print kesehatan
            System.out.println("\n<<<<STATUS>>>>");
            robot1.printStatistik();
            robot2.printStatistik();
            //tukar peran

            Robot temp = robotAktif; //supaya tdk tertimpa
            robotAktif = robotPasif;
            robotPasif = temp;

            //stop jika salah satu robot skor kesehatanya nol
            isSelesai = (robotAktif.kesehatan<=0 || robotPasif.kesehatan<=0);

            //pemenang
            if (robotAktif.kesehatan>robotPasif.kesehatan){
                pemenang=robotAktif.nama;
            }else {
                pemenang=robotPasif.nama;
            }
            j++; //increment J untuk giliran
        }
        System.out.println("Pertandingan Selesai dan dimenangkan oleh " + pemenang);
    }



    public static void main(String [] args) {
        //buat arena
        Arena oArena = new Arena();

        //menu awal
        Scanner in = new Scanner(System.in);
        System.out.println("Selamat datang di Pertarungan Gundom FFXIP !");
        System.out.print("Masukkan nama robot : ");
        String vnama = in.nextLine();

        //siapkan robot
        Robot robot1 = new Robot(vnama);
        Robot robot2 = new Robot("Chop");

        //tambahkan robot ke arena
        oArena.tambahRobot(robot1,robot2);

        //mainkan
        oArena.bertanding();

    }

}
