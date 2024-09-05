package predmety;

import hernySvet.Poloha;
import postavy.hrdinovia.Hrdina;
import fri.shapesge.Obrazok;

/**
 * Trieda Predmet reprezentuje predmet v hre.
 * Predmet má názov, popis, cestu k obrázku, polohu a metódy na manipuláciu.
 *
 * @author Benjamin Vyhnal
 */
public abstract class Predmet {

    private String meno;
    private String popis;
    private String cesta;
    private Poloha poloha;
    private Obrazok predmet;

    /**
     * Konštruktor pre vytvorenie inštancie triedy Predmet.
     * Inicializuje predmet s daným názvom, popisom, cestou k obrázku a počiatočnou polohou.
     *
     * @param meno názov predmetu
     * @param popis popis predmetu
     * @param cesta cesta k obrázku predmetu
     * @param x počiatočná x-ová súradnica predmetu
     * @param y počiatočná y-ová súradnica predmetu
     */
    public Predmet(String meno, String popis, String cesta, int x, int y) {
        this.meno = meno;
        this.popis = popis;
        this.cesta = cesta;
        this.poloha = new Poloha(x, y);
        this.predmet = new Obrazok(cesta, x, y);
        this.predmet.zobraz();
    }

    /**
     * Abstraktná metóda pre použitie predmetu na hrdinu.
     * Táto metóda musí byť implementovaná v podtriedach.
     *
     * @param hrdina hrdina, na ktorého sa predmet použije
     */
    public abstract void pouzi(Hrdina hrdina);

    /**
     * Vráti aktuálnu polohu predmetu.
     *
     * @return poloha predmetu
     */
    public Poloha getPoloha() {
        return this.poloha;
    }

    /**
     * Vráti cestu k obrázku predmetu.
     *
     * @return cesta k obrázku predmetu
     */
    public String getCesta() {
        return this.cesta;
    }

    /**
     * Posunie predmet smerom dole o 50 pixelov.
     */
    public void posunDole() {
        this.predmet.posunZvisle(50);
        this.poloha.zmenPolohu(this.poloha.getX(), this.poloha.getY() + 50);
    }

    /**
     * Vráti obrázok predmetu.
     *
     * @return obrázok predmetu
     */
    public Obrazok getPredmet() {
        return this.predmet;
    }

    /**
     * Zmení polohu predmetu na zadané súradnice.
     *
     * @param x nová x-ová súradnica
     * @param y nová y-ová súradnica
     */
    public void zmenPolohu(int x, int y) {
        this.predmet.zmenPolohu(x, y);
        this.poloha.zmenPolohu(x, y);
    }

    /**
     * Skryje predmet.
     */
    public void skry() {
        this.predmet.skry();
    }

    /**
     * Zobrazí predmet.
     */
    public void zobraz() {
        this.predmet.zobraz();
    }

    /**
     * Vráti x-ovú súradnicu pravého horného rohu predmetu.
     *
     * @return x-ová súradnica pravého horného rohu predmetu
     */
    public int getPravyHornyX() {
        return this.poloha.getX() + 30;
    }

    /**
     * Vráti y-ovú súradnicu ľavého dolného rohu predmetu.
     *
     * @return y-ová súradnica ľavého dolného rohu predmetu
     */
    public int getLavyDolnyY() {
        return this.poloha.getY() + 30;
    }
}
