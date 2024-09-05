package predmety;

import postavy.hrdinovia.Hrdina;

/**
 * Trieda predstavuje liečivý lekársky elixír, ktorý môže hrdinovi obnoviť životy.
 * Implementuje metódu na použitie tohto predmetu, ktorá pridáva určitý počet bodov života hrdinovi.
 *
 * @author Benjamin Vyhnal
 */
public class HealingPotion extends Predmet {

    /**
     * Konštruktor triedy HealingPotion.
     * Vytvorí nový liečivý elixír s danými vlastnosťami na základe zadaných súradníc.
     *
     * @param x súradnica x pozície elixíru
     * @param y súradnica y pozície elixíru
     */
    public HealingPotion(int x, int y) {
        super("HealingPotion", "Tento predmet ti môže zachrániť život", "assets/Predmety/healing_potion.png", x, y);
    }

    /**
     * Metóda na použitie liečivého elixíru.
     * Pridáva určitý počet bodov života hrdinovi a skrýva elixír.
     *
     * @param hrdina hrdina, ktorý používa liečivý elixír
     */
    @Override
    public void pouzi(Hrdina hrdina) {
        hrdina.pridajHP(2);
        this.skry();
    }
}
