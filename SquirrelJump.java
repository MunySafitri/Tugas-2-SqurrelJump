import java.util.Scanner;
import java.lang.NullPointerException;
import java.util.InputMismatchException;

public class SquirrelJump{

    private static boolean play;

    static KotakAngka kotak;
    static Semut semut;
    static Manusia manusia;
    static Kobra kobra;

    static int angka;
    static String nama;
    static long waktuSelesai;
    static long waktuMulai;
    static int waktu;
    static int nyawa;
    
    /**
     * SquirrelJump Constructor
     * Akan mebuat variable play bernilai true ketika memmbuat objek
     */
    SquirrelJump(){
        play = true;
    }

    /**
     * Method mainkan
     * permainan dimulai
     */
    public void mainkan(){
        
        Scanner input = new Scanner(System.in);
        String jawab = "tidak";

        clearScreen();
        Pemain player1 = new Pemain(nama);
        
        //program rename
        do{  
            clearScreen(); 
            if("ya".equalsIgnoreCase(jawab)){
                System.out.print("Masukkan nama anda untuk memulai bermain: ");
                nama = input.nextLine();

                if(cekNama()){
                    Enter();
                    continue;
                }

                player1.setNama(nama);
            }else if(!("tidak".equalsIgnoreCase(jawab))){
                System.err.print("Masukkan input dengan benar\n");
            }             
            System.out.println("Apakah anda ingin mengubah nama? (ya/tidak)");
            jawab = input.nextLine();

        }while("ya".equalsIgnoreCase(jawab) || !("tidak".equalsIgnoreCase(jawab)));

        clearScreen();
        
        //program membuat kotak sesuai keinginan user
        System.out.println(player1 +" ingin bermain dengan jumlah luas persegi berapa?\n(a) Normal\t(b) atur luas sendiri");

        System.out.print("Pilihan anda: ");
        String jawaban = input.nextLine();

        if(jawaban.charAt(0) == 'b'){
            do{
                
                try{
                    System.out.print(player1+" ingin luas persegi berapa? ");
                    Scanner input2 = new Scanner(System.in);
                    angka = input2.nextInt();
                }catch(InputMismatchException e){
                    System.err.print("Anda tidak memasukkan angka\nNilai default luas persegi adalah 10");
                    angka = 10;
                }
                
                kotak = new KotakAngka(angka);
                
                if (angka > 15 || angka < 8) {
                    System.out.println(" Batas minimum adalah 8 dan Batas maksimum adalah 15\nEnter untuk lanjut");
                    Enter();
                }
                
            }while(angka > 15 || angka < 8);

            if (angka >= 10){
                System.out.println("Level:\n(1) Mudah\t(2) Sedang\t(3) Susah\t(4) Ekstrem\n(5) default");   
                System.out.print("level: ");
                String level = input.nextLine();

                pilihanLevel(level);
            }

            penentuanNyawa(kotak,angka,player1);

        }else{
            kotak = new KotakAngka();
            penentuanNyawa(kotak,kotak.getJumKotak(),player1);
        }
        Enter(); 

        System.out.println("GO!");
        Enter(); 

        waktuMulai = System.currentTimeMillis();
        
        //mulai permainan
        while (play){
            clearScreen();
            
            //akan masuk percabangan ini ketika nyawa sama dengan 0
            if (nyawa == 0){
                System.out.println("\nKesempatan Habis! ");
                Enter();
                System.out.println("Yahhh.. "+ player1 + " telah membuat tupai lelah karena banyak melompat..");
                break;
            }
            //akan masuk percabangan ini jika tupai berhasil ke garis finish
            if(kotak.getPosisiTupai() == kotak.getJumKotak()*kotak.getJumKotak()){
                waktuSelesai = System.currentTimeMillis();
                waktu = (int)(waktuSelesai - waktuMulai)/1000 ;
                System.out.println("\nyey..Tupai sudah mencapai garis finish\n ");
                System.out.println("Anda Bermain selama " +waktu+ " detik");
                player1.setNilai(Nilai());
                System.out.println("Skor anda adalah "+ player1.getNilai());    
                break;
            }

            System.out.println("(a) Kiri\t\t(d) Kanan\t\t(w) atas\t\t(s) bawah\n(q) Seberang Kiri atas\t(e) Seberang Kanan atas\t(z) Seberang kiri bawah\t(x) Seberang Kanan bawah\n");
            System.out.println("Kesempatan Melompat: "+(nyawa--)+"\t\tPower: "+player1.getPower());
            kotak.tampilanKotak();
            
            //arah tupai melompat
            System.out.print("\nLompat ke arah: ");
            String arah = input.next();
            int posisiTupaiSebelum = kotak.getPosisiTupai();

            player1.lompat(arah,kotak);
            
            //akan masuk percabangan ini jika tupai melompat diluar jangkauan
            if(kotak.getPosisiTupai() > kotak.getJumKotak()*kotak.getJumKotak()||kotak.getPosisiTupai() < 1 )  {
                System.out.print("\nDiluar Jangkauan! ");
                kotak.setPosisiTupai(posisiTupaiSebelum);
                nyawa++;
                Enter();
            }if(!(arah.charAt(0) == 'a' || arah.charAt(0) == 'w' || arah.charAt(0) == 'e' || arah.charAt(0) == 'q' || arah.charAt(0) == 's' || arah.charAt(0) == 'd' || arah.charAt(0) == 'z' || arah.charAt(0) == 'x')){
                nyawa++;
                continue;
            }
            //akan masuk percabangan ini jika tupai melompat diluar jangkauan
            if(((posisiTupaiSebelum+kotak.getJumKotak()+1 == kotak.getPosisiTupai() || posisiTupaiSebelum-kotak.getJumKotak()+1 == kotak.getPosisiTupai())
                || posisiTupaiSebelum - kotak.getJumKotak()+1 == kotak.getPosisiTupai()) && kotak.getPosisiTupai()%kotak.getJumKotak() == 1){
                batas(kotak, posisiTupaiSebelum);
                continue;
            }else if(((posisiTupaiSebelum-1 == kotak.getPosisiTupai() || posisiTupaiSebelum-kotak.getJumKotak()-1 == kotak.getPosisiTupai())
                || posisiTupaiSebelum + kotak.getJumKotak()-1 == kotak.getPosisiTupai()) && kotak.getPosisiTupai()%kotak.getJumKotak() == 0){
                batas(kotak, posisiTupaiSebelum);
                continue;
            }
            
            //perrulangan ada ketika pemain memiliki musuh
            for(int i = 1 ; i<=20; i++){

                try{
                    if(semut.getPlay()){
                        semut.jangkauan();

                        if(kotak.getPosisiTupai() == semut.jangkauan()){
                            semut.damage(player1);

                            if(player1.getPower() <=0){
                                player1.setPower(0);
                            }

                            System.out.println(player1 +" Kena damage dari semut\nPower anda bersisa " +player1.getPower());
                            Enter();
                        } 

                    }

                }catch(NullPointerException e){}

                try{
                    if(manusia.getPlay()){
                        manusia.jangkauan();
                        if(kotak.getPosisiTupai() == manusia.jangkauan()){
                            manusia.damage(player1);

                            if(player1.getPower() <=0){
                                player1.setPower(0);
                            }

                            System.out.println(player1 +" Kena damage dari manusia\nPower anda bersisa " +player1.getPower());
                            Enter();
                        }

                    } 

                }catch(NullPointerException e){}

                try{
                    kobra.jangkauan();
                    if(kobra.getPlay()){
                        if(kotak.getPosisiTupai()== kobra.jangkauan()){
                            kobra.damage(player1);

                            if(player1.getPower() <=0){
                                player1.setPower(0);
                            }

                            System.out.println(player1 +" Kena damage dari kobra\nPower anda bersisa " +player1.getPower());
                            Enter();
                        }                   
                    } 

                }catch(NullPointerException e){}
            }
            //akan masuk percabangan ini ketika nyawa tupai habis
            if(player1.getPower() <=0){
                System.out.println("Tupai Sudah Mati..\nAnda Gagal mencapai finish\n");
                waktuSelesai = System.currentTimeMillis();
                waktu = (int)(waktuSelesai - waktuMulai)/1000 ;
                System.out.println("Anda Bermain selama " + waktu + " detik");
                Enter();
                break;
            }  

        }
        input.close();         
    }

    /**
     * Method main
     *
     * @param args A parameter
     */
    public static void main(String[] args) throws Exception { 
        
        SquirrelJump tupai = new SquirrelJump();
        
        Scanner input; 
        Scanner input2;
        input = new Scanner(System.in);
        //program meminta mainkan
        System.out.print("Apakah Anda ingin bermain?(ya/tidak)\nJawaban: ");
        String jawaban = input.nextLine();

        if("tidak".equalsIgnoreCase(jawaban)){
            System.exit(1);
        }else if(!"ya".equalsIgnoreCase(jawaban)){
            System.err.print("Input anda masukkan salah ");
            System.exit(0);
        }

        System.out.println("Halooo..\nSelamat Datang Di Permainan Squirrel Jump\n");
        //program memasukkan nama
        do{
            System.out.print("Masukkan nama anda untuk memulai bermain: ");
            input = new Scanner(System.in);
            nama = input.nextLine();
            if(nama.isEmpty()){
                System.err.print( "Nama tidak boleh kosong\n");
            }
        }while(nama.isEmpty() || cekNama() == true);
        input2 = new Scanner(System.in);
        
        //program mainkan
        while(play){
            tupai.mainkan();
            System.out.print("Apakah anda ingin bermain lagi (ya/tidak)? ");
            String jawaban2 = input2.nextLine();
            if("tidak".equalsIgnoreCase(jawaban2) || !("ya".equalsIgnoreCase(jawaban2))){
                break;
            }
        }
        System.out.print("Terima Kasih Telah Bermain \\^w^/");
        input.close();
        input2.close();

    }

    /**
     * Method batas
     * method ketika tupai berada diluar jangkauam
     *
     * @param kotak A parameter
     * @param posisiTupaiSebelum A parameter
     */
    static void batas(KotakAngka kotak,int posisiTupaiSebelum){
        nyawa++;
        System.out.println("Diluar Jangkauan");
        kotak.setPosisiTupai(posisiTupaiSebelum);
        Enter();
    }

    /**
     * Method clearScreen
     * menghupus layar terminal
     */
    static void clearScreen(){
        try{
            if(System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            }else{
                System.out.print("\033\143");
            }
        }catch (Exception e){
            System.err.println("Tidak bisa clear screen");
        }
    }

    /**
     * Method Enter
     *
     */
    static void Enter(){
        Scanner Enter = new Scanner(System.in);
        Enter.nextLine();
    } 

    /**
     * Method NyawaPlayer
     * penentuan kesempatan melompat sesuai dengan jumlah kotak 
     * 
     * @param jumlahKotak A parameter
     * @param kotak A parameter
     */
    public static void NyawaPlayer(int jumlahKotak, KotakAngka kotak){
        if(jumlahKotak >= 8 && jumlahKotak <10){
            if(kotak.getPosisiTupai() <= (kotak.getJumKotak()*kotak.getJumKotak()/2))  {nyawa = 15;}
            else nyawa = 12;
        }else if(jumlahKotak == 10){
            if(kotak.getPosisiTupai() <= 50) nyawa = 20;
            else if(kotak.getPosisiTupai() > 50) nyawa = 10;
        }else if(jumlahKotak > 10 && jumlahKotak <=13){
            if(kotak.getPosisiTupai() >= (kotak.getJumKotak()*kotak.getJumKotak()/2)) {nyawa = 12;}
            else nyawa = 20;         
        }else if(jumlahKotak <= 15 && jumlahKotak >13){
            if(kotak.getPosisiTupai() >= (kotak.getJumKotak()*kotak.getJumKotak()/2)) nyawa = 12;
            else nyawa = 22;
        }
    }

    /**
     * Method Nilai
     * penentuan nilai sesuai dengan lamanya waktu pemain mainkan
     *
     * @return The return value
     */
    
    public static int Nilai(){
        if(waktu<=25){
            return 100;
        }else if(waktu <=30){
            return 80;
        }else if(waktu <=35){
            return 70;
        }else if(waktu <=45){
            return 60;
        }else if(waktu<=50){
            return 50;
        }else{
            return 40;
        }
    }

    /**
     * Method penentuanNyawa
     *
     * @param kotak A parameter
     * @param angka A parameter
     * @param player1 A parameter
     */
    public static void penentuanNyawa(KotakAngka kotak,int angka, Pemain player1){
        clearScreen();
        player1.buatSquirrel();
        kotak.tambahSquirrel();
        NyawaPlayer(angka,kotak);
        System.out.println("Anda memiliki kesempatan melompat sebanyak " + nyawa);
    }

    /**
     * Method pilihanLevel
     *
     * @param level A parameter
     */
    static void pilihanLevel(String level){
        switch(level){
            case "1":
                semut = new Semut(kotak);
                System.out.println("Musuh anda adalah Semut");
                break;
            case "2":
                semut = new Semut(kotak);
                kobra = new Kobra (kotak);
                System.out.println("Musuh anda adalah Semut dan Kobra");
                break;
            case "3":
                kobra = new Kobra (kotak);
                manusia = new Manusia(kotak);
                System.out.println("Musuh anda adalah Kobra dan Manusia");
                break;
            case "4":
                kobra = new Kobra (kotak);
                manusia = new Manusia(kotak);
                semut = new Semut(kotak);
                System.out.println("Musuh anda adalah Semut, Kobra, dan Manusia");
                break;
            default:

        }
        Enter();
    }

    /**
     * Method cekNama
     *
     * @return The return value
     */
    public static boolean cekNama(){
        for(int i= 0; i<nama.length(); i++){
            if((nama.charAt(i) < 'A' || nama.charAt(i) > 'Z') && (nama.charAt(i) < 'a' || nama.charAt(i) > 'z')){
                System.out.println("Masukkan nama dengan benar, nama tidak boleh menggunakan angka atau karakter lain");
                return true;
            }
        }
        return false;
    }

}       
