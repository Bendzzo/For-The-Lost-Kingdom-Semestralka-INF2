package hernySvet;

import fri.shapesge.Obrazok;

/**
 * Trieda Prekazka predstavuje prekážku v hre.
 * Stará sa o vizuálne zobrazenie, manipuláciu s jej polohou a veľkosťou.
 *
 * @author Benjamin Vyhnal
 */
public class Prekazka {
   //private Obdlznik prekazka;
    private Obrazok prekazka;
    private Poloha poloha;
    private int sirka;
    private int vyska;


    /**
     * Konštruktor triedy Prekazka inicializuje prekážku s danými rozmermi a farbou na zadaných súradniciach x a y.
     *
     *
     * @param x Súradnica x pre začiatočnú polohu prekážky.
     * @param y Súradnica y pre začiatočnú polohu prekážky.
     */
    public Prekazka(int x, int y) {
        this.sirka = 30;
        this.vyska = 50;

        this.prekazka = new Obrazok("assets/predmety/log.png", x, y);
        //this.prekazka = new Obdlznik(x, y);
        this.poloha = new Poloha(x, y);
        //this.prekazka.zmenStrany(this.sirka, this.vyska);
        //this.prekazka.zmenFarbu("#00000");
        this.prekazka.zobraz();
    }


    /**
     * Zmení veľkosť prekážky na zadané rozmery.
     *
     * @param sirka Nová šírka prekážky.
     * @param vyska Nová výška prekážky.
     */
    public void zmenVelkost(int sirka, int vyska) {
        //this.prekazka.zmenStrany(sirka, vyska);
        this.sirka = sirka;
        this.vyska = vyska;
    }


    /**
     * Posunie prekážku vodorovne o zadanú vzdialenosť.
     *
     * @param vzdialenost Vzdialenosť, o ktorú sa prekážka posunie vodorovne.
     */
    public void posunVodorovne(int vzdialenost) {
        this.prekazka.posunVodorovne(vzdialenost);
        this.poloha.setX(this.poloha.getX() + vzdialenost);
    }


    /**
     * Posunie prekážku zvisle o zadanú vzdialenosť.
     *
     * @param vzdialenost Vzdialenosť, o ktorú sa prekážka posunie zvisle.
     */
    public void posunZvisle(int vzdialenost) {
        this.prekazka.posunZvisle(vzdialenost);
        this.poloha.setY(this.poloha.getY() + vzdialenost);
    }


    /**
     * Zmení polohu prekážky na zadané súradnice x a y.
     *
     * @param x Nová súradnica x pre polohu prekážky.
     * @param y Nová súradnica y pre polohu prekážky.
     */
    public void zmenPolohu(int x, int y) {
        this.prekazka.zmenPolohu(x, y);
        this.poloha.zmenPolohu(x, y);
    }


    /**
     * Vráti šírku prekážky.
     *
     * @return Šírka prekážky.
     */
    public int getSirka() {
        return this.sirka;
    }


    /**
     * Vráti výšku prekážky.
     *
     * @return Výška prekážky.
     */
    public int getVyska() {
        return this.vyska;
    }


    /**
     * Vráti súradnicu x pravého horného rohu prekážky.
     *
     * @return Súradnica x pravého horného rohu prekážky.
     */
    public int getPravyHornyX() {
        return this.getX() + this.getSirka();
    }


    /**
     * Vráti súradnicu y ľavého dolného rohu prekážky.
     *
     * @return Súradnica y ľavého dolného rohu prekážky.
     */
    public int getLavyDolnyY() {
        return this.getY() + this.getVyska();
    }


    /**
     * Vráti súradnicu x pre polohu prekážky.
     *
     * @return Súradnica x pre polohu prekážky.
     */
    public int getX() {
        return this.poloha.getX();
    }


    /**
     * Vráti súradnicu y pre polohu prekážky.
     *
     * @return Súradnica y pre polohu prekážky.
     */
    public int getY() {
        return this.poloha.getY();
    }


    /**
     * Vráti inštanciu triedy Poloha pre polohu prekážky.
     *
     * @return Inštancia triedy Poloha pre polohu prekážky.
     */
    public Poloha getPoloha() {
        return this.poloha;
    }


    /**
     * Skryje vizuálnu reprezentáciu prekážky.
     */
    public void skry() {
        this.prekazka.skry();
    }
}
