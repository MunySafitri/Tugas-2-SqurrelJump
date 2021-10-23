class Kobra extends Musuh{
    private int damage; 
    private boolean play = false;  

    /**
     * Kobra Constructor
     *
     * @param kotak A parameter
     */
    Kobra(KotakAngka kotak){
        super(kotak);
        this.damage = 40;
        this.play = true;      
    }

    /**
     * Method damage
     *
     * @param player A parameter
     */
    public void damage(Pemain player){
        player.setPower(player.getPower()-this.damage);
    }

    /**
     * Method getDamage
     *
     * @return The return value
     */
    public int getDamage(){
        return this.damage;
    }
    
    /**
     * Method getPlay
     *
     * @return The return value
     */
    @Override
    public boolean getPlay(){
        return this.play;
    }
    
}