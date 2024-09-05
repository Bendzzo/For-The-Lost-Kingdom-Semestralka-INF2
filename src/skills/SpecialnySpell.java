package skills;

import fri.shapesge.BlokTextu;
import fri.shapesge.Manazer;
import fri.shapesge.StylFontu;

/**
 * Trieda SpecialnySpell reprezentuje špeciálne kúzlo hrdinu.
 * SpecialnySpell má názov, popis a časovač.
 *
 * @author Benjamin Vyhnal
 */
public class SpecialnySpell {

    private String nazov;
    private String popis;
    private BlokTextu odpocitavac;
    private int casovac;
    private int pociatocnyCas;
    private boolean jeDokonceny;
    private boolean jeStlaceny;
    private Manazer manazer;

    /**
     * Konštruktor pre vytvorenie inštancie triedy SpecialnySpell.
     * Inicializuje spell s názvom, popisom a časovačom.
     *
     * @param nazov názov spellu
     * @param popis popis spellu
     * @param casovac počiatočná hodnota časovača
     */
    public SpecialnySpell(String nazov, String popis, int casovac) {
        this.nazov = nazov;
        this.popis = popis;
        this.casovac = casovac + 1;
        this.pociatocnyCas = casovac + 1;
        this.jeDokonceny = true;

        this.odpocitavac = new BlokTextu(String.valueOf(this.casovac));
        this.odpocitavac.zmenFarbu("red");
        this.odpocitavac.zmenPolohu(555, 860);
        this.odpocitavac.zmenFont("Impact", StylFontu.PLAIN, 35);
        this.odpocitavac.skry();

        this.jeStlaceny = false;

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    /**
     * Vráti aktuálnu hodnotu časovača.
     *
     * @return hodnota časovača
     */
    public int getCasovac() {
        return this.casovac;
    }

    /**
     * Vráti počiatočnú hodnotu časovača.
     *
     * @return počiatočná hodnota časovača
     */
    public int getPociatocnyCas() {
        return this.pociatocnyCas;
    }

    /**
     * Vráti, či je spell stlačený.
     *
     * @return true, ak je spell stlačený, inak false
     */
    public boolean isJeStlaceny() {
        return this.jeStlaceny;
    }

    /**
     * Vráti názov spellu.
     *
     * @return názov spellu
     */
    public String getNazov() {
        return this.nazov;
    }

    /**
     * Nastaví názov spellu.
     *
     * @param nazov nový názov spellu
     */
    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    /**
     * Vráti popis spellu.
     *
     * @return popis spellu
     */
    public String getPopis() {
        return this.popis;
    }

    /**
     * Nastaví popis spellu.
     *
     * @param popis nový popis spellu
     */
    public void setPopis(String popis) {
        this.popis = popis;
    }

    /**
     * Nastaví stav spellu na stlačený a resetuje dokončenie.
     *
     * @param jeStlaceny nový stav spellu
     */
    public void setJeStlaceny(boolean jeStlaceny) {
        this.jeStlaceny = jeStlaceny;
        this.jeDokonceny = false;
    }

    /**
     * Aktualizuje časovač o jednu sekundu.
     * Ak je spell stlačený a nie je dokončený, zníži časovač a skryje text.
     */
    public void sekunda() {
        if (this.jeStlaceny && !this.jeDokonceny) {
            this.odpocitavac.zobraz();
            this.casovac--;
            this.odpocitavac.zmenText(String.valueOf(this.casovac));
            if (this.casovac == 0) {
                this.jeStlaceny = false;
                this.casovac = this.pociatocnyCas;
                this.odpocitavac.skry();
                this.odpocitavac.zmenText(String.valueOf(this.casovac));
                this.jeDokonceny = true;
            }
        }
    }

    /**
     * Vráti, či je spell dokončený.
     *
     * @return true, ak je spell dokončený, inak false
     */
    public boolean jeDokonceny() {
        return this.jeDokonceny;
    }

    /**
     * Vráti, či je časovač rovný počiatočnej hodnote.
     *
     * @return true, ak je časovač rovný počiatočnej hodnote, inak false
     */
    public boolean jeRovnyZaciatku() {
        return this.casovac == this.pociatocnyCas;
    }

    /**
     * Zobrazí odpočítavanie spellu.
     */
    public void zobraz() {
        this.odpocitavac.zobraz();
    }

    /**
     * Skryje odpočítavanie spellu.
     */
    public void skry() {
        this.odpocitavac.skry();
        this.casovac = this.pociatocnyCas;
        this.jeDokonceny = true;
    }
}
