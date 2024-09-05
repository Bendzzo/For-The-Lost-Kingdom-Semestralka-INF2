package postavy.hrdinovia;

import skills.SpecialnySpell;
import skills.Utok;
import fri.shapesge.Manazer;

/**
 * Trieda Rogue reprezentuje hrdinu zlodeja.
 * Rogue má špecifické schopnosti ako útok a špeciálny spell "Restoration".
 *
 * @author Benjamin Vyhnal
 */
public class Rogue extends Hrdina {
    private Manazer manazer;

    /**
     * Konštruktor pre vytvorenie inštancie triedy Rogue.
     * Inicializuje zlodeja s prednastavenými hodnotami a manažérom.
     */
    public Rogue() {
        super("Rogue", 10, "assets/Rogue/rogue.png", "assets/Rogue/rogue_gamebar.png", new Utok(9, "Rogue"), 2,
                new SpecialnySpell("Restoration", "Mas schopnost obnovit si HP", 10));
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    /**
     * Vykoná základný útok zlodeja.
     */
    @Override
    public void utok() {
        this.getUtok().krok();
    }

    /**
     * Spustí alebo zastaví animáciu zlodeja na základe stlačenia.
     *
     * @param stlacene true, ak je tlačidlo stlačené, inak false.
     */
    @Override
    public void animacia(boolean stlacene) {
        if (stlacene) {
            this.animaciaPlus();

            if (this.getAnimacia() % 3 == 0) {
                this.utok();
            }

            if (this.getAnimacia() > 23) {
                this.getUtok().zmenPolohu(this.getPoloha().getX(), this.getPoloha().getY());
                this.getUtok().skry();
                this.setAnimacia(0);
            }
        }
    }

    /**
     * Vykoná špeciálny spell "Restoration", ktorý obnovuje HP zlodeja.
     */
    @Override
    public void specialnySpell() {
        if (this.getSpecialnySpell().jeDokonceny() && this.getSpecialnySpell().jeRovnyZaciatku()) {
            this.getSpecialnySpell().setJeStlaceny(true);
            this.pridajHP(1);
        }
    }
}
