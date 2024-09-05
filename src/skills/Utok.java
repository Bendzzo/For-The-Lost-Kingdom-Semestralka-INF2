package skills;

import hernySvet.Poloha;
import fri.shapesge.Obrazok;

/**
 * Trieda Utok reprezentuje animáciu útoku v hre.
 * Utok obsahuje metódy pre ovládanie a animáciu útoku.
 *
 * @author Benjamin Vyhnal
 */
public class Utok {
    private int animacia;
    private int pocetObrazkov;
    private Obrazok utok;
    private String nazov;
    private Poloha poloha;
    private boolean ukoncenaAnimacia;

    /**
     * Konštruktor pre vytvorenie inštancie triedy Utok.
     * Inicializuje útok s daným počtom obrázkov a názvom.
     *
     * @param pocetObrazkov počet obrázkov v animácii útoku
     * @param nazov názov útoku
     */
    public Utok(int pocetObrazkov, String nazov) {
        this.pocetObrazkov = pocetObrazkov;
        this.nazov = nazov;
        this.utok = new Obrazok("assets/Animacie/" + this.nazov + "/0.png");
        this.ukoncenaAnimacia = true;
    }

    /**
     * Posunie animáciu útoku o jeden krok dopredu.
     * Zmení obrázok útoku na ďalší v poradí.
     */
    public void krok() {
        this.utok.skry();
        this.animacia++;
        this.utok.zmenObrazok("assets/Animacie/" + this.nazov + "/" + (this.animacia % this.pocetObrazkov) + ".png");
        this.utok.zobraz();
    }

    /**
     * Nastaví, či je animácia útoku ukončená.
     *
     * @param ukoncenaAnimacia nový stav ukončenia animácie
     */
    public void setUkoncenaAnimacia(boolean ukoncenaAnimacia) {
        this.ukoncenaAnimacia = ukoncenaAnimacia;
    }

    /**
     * Ukončí animáciu útoku a nastaví ju na počiatočný stav.
     */
    public void ukonciAnimaciu() {
        this.animacia = 0;
        this.utok.zmenObrazok("assets/Animacie/" + this.nazov + "/0.png");
        this.ukoncenaAnimacia = true;
    }

    /**
     * Vráti aktuálny krok animácie útoku.
     *
     * @return aktuálny krok animácie
     */
    public int getAnimacia() {
        return this.animacia;
    }

    /**
     * Zmení polohu útoku na zadané súradnice.
     *
     * @param x nová x-ová súradnica
     * @param y nová y-ová súradnica
     */
    public void zmenPolohu(int x, int y) {
        this.utok.skry();
        this.utok.zmenPolohu(x, y);
        this.poloha = new Poloha(x, y);
        this.utok.zobraz();
    }

    /**
     * Zobrazí útok.
     */
    public void zobraz() {
        this.utok.zobraz();
    }

    /**
     * Skryje útok.
     */
    public void skry() {
        this.utok.skry();
    }

    /**
     * Posunie útok vodorovne o zadaný počet pixelov.
     *
     * @param oKolko počet pixelov, o ktorý sa útok posunie vodorovne
     */
    public void posunVodorovne(int oKolko) {
        this.utok.posunVodorovne(oKolko);
        this.poloha.zmenPolohu(this.poloha.getX() + oKolko, this.poloha.getY());
    }

    /**
     * Nastaví nový objekt obrázka útoku.
     *
     * @param utok nový objekt obrázka
     */
    public void setUtok(Obrazok utok) {
        this.utok = utok;
    }

    /**
     * Vráti aktuálnu polohu útoku.
     *
     * @return aktuálna poloha útoku
     */
    public Poloha getPoloha() {
        return this.poloha;
    }

    /**
     * Posunie útok doprava o 20 pixelov.
     */
    public void posunVpravo() {
        this.utok.posunVpravo();
        this.poloha.zmenPolohu(this.poloha.getX() + 20, this.poloha.getY());
    }

    /**
     * Posunie útok doľava o 20 pixelov.
     */
    public void posunVlavo() {
        this.utok.posunVlavo();
        this.poloha.zmenPolohu(this.poloha.getX() - 20, this.poloha.getY());
    }

    /**
     * Posunie útok hore o 20 pixelov.
     */
    public void posunHore() {
        this.utok.posunHore();
        this.poloha.zmenPolohu(this.poloha.getX(), this.poloha.getY() - 20);
    }

    /**
     * Posunie útok dole o 20 pixelov.
     */
    public void posunDole() {
        this.utok.posunDole();
        this.poloha.zmenPolohu(this.poloha.getX(), this.poloha.getY() + 20);
    }

    /**
     * Posunie útok zvisle o zadaný počet pixelov.
     *
     * @param oKolko počet pixelov, o ktorý sa útok posunie zvisle
     */
    public void posunZvisle(int oKolko) {
        this.utok.posunZvisle(oKolko);
        this.poloha.zmenPolohu(this.poloha.getX(), this.poloha.getY() + oKolko);
    }
}
