package postavy.hrdinovia;

import skills.SpecialnySpell;
import skills.Utok;
import fri.shapesge.Manazer;

/**
 * Trieda Warrior reprezentuje hrdinu.
 * Warrior má špecifické schopnosti ako útok a špeciálny spell "Blood shield".
 *
 * @author Benjamin Vyhnal
 */
public class Warrior extends Hrdina {
    private boolean skryty;
    private final Manazer manazer;

    /**
     * Konštruktor pre vytvorenie inštancie triedy Warrior.
     * Inicializuje bojovníka s prednastavenými hodnotami a manažérom.
     */
    public Warrior() {
        super("Warrior", 10, "assets/Warrior/warrior.png", "assets/Warrior/warrior_gamebar.png", new Utok(9, "Warrior"), 3,
                new SpecialnySpell("Blood shield", "Dokazes byt nesmrtelny, ale zaroven nemozes nic ine robit", 20));
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    /**
     * Vykoná základný útok bojovníka.
     */
    @Override
    public void utok() {
        this.getUtok().krok();
    }

    /**
     * Vráti stav skrytosti bojovníka.
     *
     * @return true, ak je bojovník skrytý, inak false.
     */
    public boolean skryty() {
        return this.skryty;
    }

    /**
     * Spustí alebo zastaví animáciu bojovníka na základe stlačenia.
     *
     * @param stlacene true, ak je tlačidlo stlačené, inak false.
     */
    @Override
    public void animacia(boolean stlacene) {
        if (stlacene) {
            this.animaciaPlus();

            if (this.getAnimacia() % 3 == 0) {
                this.getUtok().krok();
            }

            if (this.getAnimacia() > 23) {
                this.getUtok().zmenPolohu(this.getPoloha().getX(), this.getPoloha().getY());
                this.getUtok().skry();
                this.setAnimacia(0);
            }
        }
    }

    /**
     * Vykoná špeciálny spell "Blood shield", ktorý robí bojovníka nesmrteľným na určitý čas.
     */
    @Override
    public void specialnySpell() {
        var spell = this.getSpecialnySpell();
        if (spell.getCasovac() <= spell.getPociatocnyCas() && spell.getCasovac() >= spell.getPociatocnyCas() - 5) {
            spell.setJeStlaceny(true);
            this.setNesmrtelny(true);
            this.getHrdina().zmenObrazok("assets/Warrior/Warrior_nesmrtelny.png");
            if (spell.getCasovac() <= 16) {
                this.setNesmrtelny(false);
                this.getHrdina().zmenObrazok("assets/Warrior/Warrior.png");
            }
        }
    }
}
