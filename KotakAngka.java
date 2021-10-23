public class KotakAngka{
    private int jumlahKotak = 10 ;
    private int kotak[][]  ;
    public static int nomor = 1;
    private int posisiTupai;
    private int randomKolom;
    private int randomBaris;
    
    /**
     * KotakAngka Constructor
     *
     */
    KotakAngka(){
        this.jumlahKotak = 10;
        this.kotak = new int[10][10];
        this.posisiTupai = 0;          
    }

    /**
     * KotakAngka Constructor
     *
     * @param jumKotak A parameter
     */
    KotakAngka(int jumKotak){
        this.jumlahKotak = jumKotak;
        this.kotak = new int[jumKotak][jumKotak];
        this.posisiTupai = 0;    
    }

    /**
     * Method getJumKotak
     *
     * @return The return value
     */
    public int getJumKotak(){
        return this.jumlahKotak;
    }

    /**
     * Method buatKotak
     *
     */
    private void buatKotak(){
        nomor = 1;
        for(int i = 0; i<this.kotak.length;i++){  
            for(int j = 0; j<this.kotak[i].length; j++){
                kotak[i][j] = this.nomor++;
            } 
        }      
    }

    /**
     * Method tampilanKotak
     *
     */
    public void tampilanKotak(){
        buatKotak();
        for(int i = 0; i<this.kotak.length;i++){  
            for(int j = 0; j<this.kotak[i].length; j++){
                if(kotak[i][j] == this.posisiTupai) kotak[i][j] = 0;
                System.out.printf("|| %-3d",kotak[i][j]);     
            }
             System.out.println("||"); 
        } 
        System.out.println("Tupai berada pada posisi ke-" + getPosisiTupai());
    }
    
    /**
     * Method tambahSquirrel
     *
     */
    public void tambahSquirrel(){
        buatKotak();

        if(this.posisiTupai == 0) {
            randomBaris = (int) Math.floor(Math.random() * this.jumlahKotak);
            randomKolom = (int) (Math.random()* this.jumlahKotak);
            this.posisiTupai = this.kotak[randomBaris][randomKolom];
        }      
    }
    
    /**
     * Method getRandomBaris
     *
     * @return The return value
     */
    public int getRandomBaris(){
        return this.randomBaris;
    }
    
    /**
     * Method getRandomKolom
     *
     * @return The return value
     */
    public int getRandomKolom(){
        return this.randomKolom;
    }
    
    /**
     * Method getPosisiTupai
     *
     * @return The return value
     */
    public int getPosisiTupai(){
        return this.posisiTupai;
    }
    
    /**
     * Method setPosisiTupai
     *
     * @param posisiTupai A parameter
     */
    public void setPosisiTupai(int posisiTupai){
        this.posisiTupai = posisiTupai;
    }
}