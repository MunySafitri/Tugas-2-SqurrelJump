import java.util.Scanner;

public class Pemain{
        private String nama;
        private int nilai;
        static Scanner input = new Scanner(System.in);
        private int power;
       
        /**
         * Pemain Constructor
         *
         * @param nama A parameter
         */
        Pemain(String nama){
            this.nama = nama;
            this.nilai = 0;
            this.power = 120;
        }

        /**
         * Method getNama
         *
         * @return The return value
         */
        public String getNama(){
            return this.nama;
        }

        /**
         * Method setNama
         *
         * @param nama A parameter
         */
        public void setNama(String nama){
            this.nama = nama;
        }

        /**
         * Method getNilai
         *
         * @return The return value
         */
        public int getNilai(){
            return this.nilai;
        }
        /**
         * Method setNilai
         *
         * @param nilai A parameter
         */
        public void setNilai(int nilai){
            this.nilai = nilai;
        }

        /**
         * Method buatSquirrel
         *
         */
        public void buatSquirrel(){
            System.out.println("Selamat datang "+ this.nama + "!\nBantu tupai untuk sampai ke garis finish");
            System.out.println("Tupai akan diwakili dengan angka 0");           
        }

        /**
         * Method lompat
         *
         * @param arah A parameter
         * @param kotak A parameter
         */
        public void lompat (String arah, KotakAngka kotak){
            switch (arah) {
                case "a":
                    kotak.setPosisiTupai(kotak.getPosisiTupai()-1);
                    break;
                case "s":
                    kotak.setPosisiTupai(kotak.getPosisiTupai()+kotak.getJumKotak());              
                    break;
                case "d":
                    kotak.setPosisiTupai(kotak.getPosisiTupai()+1);       
                    break;
                case "w":
                    kotak.setPosisiTupai(kotak.getPosisiTupai()-kotak.getJumKotak());      
                    break;
                case "q":
                    kotak.setPosisiTupai(kotak.getPosisiTupai()-kotak.getJumKotak()-1); 
                    break;    
                case "e":
                    kotak.setPosisiTupai(kotak.getPosisiTupai()-kotak.getJumKotak()+1); 
                    break;
                case "z": 
                    kotak.setPosisiTupai(kotak.getPosisiTupai()+kotak.getJumKotak()-1); 
                    break;
                case "x":
                    kotak.setPosisiTupai(kotak.getPosisiTupai()+kotak.getJumKotak()+1);
                    break;               
                default:
                    System.out.println("Input yang anda masukkan salah\nPilihan a,q,w,s,d,z dan x\nEnter untuk lanjut");
                    input.nextLine();
                    break;
            }
        }

        /**
         * Method toString
         *
         * @return The return value
         */
        public String toString(){
            return this.nama;
        }
        
        /**
         * Method getPower
         *
         * @return The return value
         */
        public int getPower(){
            return this.power;
        }
        
        /**
         * Method setPower
         *
         * @param power A parameter
         */
        public void setPower(int power){
            this.power = power;
        }
      
}