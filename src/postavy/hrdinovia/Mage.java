package postavy.hrdinovia;

import skills.SpecialnySpell;
import skills.Utok;
import fri.shapesge.Manazer;

/**
 * Trieda Mage reprezentuje hrdinu mága.
 * Mage má špecifické schopnosti ako útok a špeciálne spelly.
 *
 * @author Benjamin Vyhnal
 */
public class Mage extends Hrdina {
    private Manazer manazer;

    /**
     * Konštruktor pre vytvorenie inštancie triedy Mage.
     * Inicializuje mága s prednastavenými hodnotami a manažérom objektov.
     */
    public Mage() {
        super("Mag", 10, "assets/Mage/Mage.png", "assets/Mage/mage_gamebar.png", new Utok(9, "Mage"), 1,
                new SpecialnySpell("Frozen time", "Dokazes zastavit cas a zaroven vsetko chytat!", 30));
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.getUtok().zmenPolohu(this.getPoloha().getX(), this.getPoloha().getY() + 25);
    }

    /**
     * Vykoná základný útok mága.
     */
    @Override
    public void utok() {
        this.getUtok().krok();
    }

    /**
     * Spustí alebo zastaví animáciu mága na základe stlačenia.
     *
     * @param stlacene true, ak je tlačidlo stlačené, inak false.
     */
    @Override
    public void animacia(boolean stlacene) {
        if (stlacene) {
            this.animaciaPlus();
            var poloha = this.getPoloha();
            this.getUtok().posunVodorovne(20);

            if (this.getAnimacia() % 3 == 0) {
                this.getUtok().krok();
            }

            if (this.getAnimacia() > 23) {
                this.getUtok().zmenPolohu(poloha.getX(), poloha.getY() + 25);
                this.getUtok().skry();
                this.setAnimacia(0);
            }
        }
    }

    /**
     * Vykoná špeciálny spell mága.
     * Táto metóda je zatiaľ neimplementovaná.
     */
    @Override
    public void specialnySpell() {
        var spell = this.getSpecialnySpell();
        if (spell.getCasovac() <= spell.getPociatocnyCas() && spell.getCasovac() >= spell.getPociatocnyCas() - 5) {
            spell.setJeStlaceny(true);
            this.setZamrznutaHra(true);
            if (spell.getCasovac() <= spell.getPociatocnyCas() - 5) {
                this.setZamrznutaHra(false);
            }
        }
    }
}

