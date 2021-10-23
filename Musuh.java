public class Musuh{
    private KotakAngka kotak;
    int kotak2[][];
    int posisi;
    boolean play = false;
    
    /**
     * Musuh Constructor
     *
     * @param kotak A parameter
     */
    Musuh(KotakAngka kotak){
        this.kotak = kotak; 
        kotak2 = new int[kotak.getJumKotak()][kotak.getJumKotak()];
        buatKotak();
        this.play=true;    
    }

    /**
     * Method buatKotak
     *
     */
    private void buatKotak(){//menampilkan kotak
        int nomor = 1;
        for(int i = 0; i<this.kotak2.length;i++){  
            for(int j = 0; j<this.kotak2[i].length; j++){
                kotak2[i][j] = nomor++;
            } 
        }      
    }

    /**
     * Method jangkauan
     * jangkauan musuh yang secara acak menempati kotak
     * @return The return value
     */
    public int jangkauan(){
        int randomBaris = (int) Math.floor(Math.random() * (kotak.getJumKotak()));
        int randomKolom = (int) (Math.random()* kotak.getJumKotak());
        this.posisi = kotak2[randomBaris][randomKolom];
        return this.posisi;
        
    }
    /**
     * Method getPosisi
     *
     * @return The return value
     */
    public int getPosisi(){
        return this.posisi;       
    }

    /**
     * Method getPlay
     * 
     * @return The return value
     */
    public boolean getPlay(){
        return this.play;
    }
    
}