package predmety;

import postavy.hrdinovia.Hrdina;

/**
 * Trieda predstavuje jed, ktorý môže spôsobiť hrdinovi škodu.
 * Implementuje metódu na použitie tohto predmetu, ktorá znižuje obranu hrdinovi o 3.
 *
 * @author Benjamin Vyhnal
 */
public class Poison extends Predmet {

    /**
     * Konštruktor triedy Poison.
     * Vytvorí nový jed s danými vlastnosťami na základe zadaných súradníc.
     *
     * @param x súradnica x pozície jedu
     * @param y súradnica y pozície jedu
     */
    public Poison(int x, int y) {
        super("Poison", "Tomuto sa radšej vyhni!", "assets/Predmety/poison.png", x, y);
    }

    /**
     * Metóda na použitie jedu.
     * Zníži obranu hrdinovi o 3 a skryje jed.
     *
     * @param hrdina hrdina, ktorý používa jed
     */
    @Override
    public void pouzi(Hrdina hrdina) {
        hrdina.obrana(3);
        this.skry();
    }
}
