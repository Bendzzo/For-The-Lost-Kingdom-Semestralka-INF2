package predmety;

import postavy.hrdinovia.Hrdina;

/**
 * Trieda predstavuje obranný štít, ktorý môže hrdinovi poskytnúť dodatočnú obranu.
 * Implementuje metódu na použitie tohto predmetu, ktorá zvyšuje obranu hrdinovi o 1.
 *
 * @author Benjamin Vyhnal
 */
public class ObrannyStit extends Predmet {

    /**
     * Konštruktor triedy ObrannyStit.
     * Vytvorí nový obranný štít s danými vlastnosťami na základe zadaných súradníc.
     *
     * @param x súradnica x pozície štítu
     * @param y súradnica y pozície štítu
     */
    public ObrannyStit(int x, int y) {
        super("Stit", "Dokáže ti zachrániť život!", "assets/Predmety/shield.png", x , y);
    }

    /**
     * Metóda na použitie obranného štítu.
     * Zvyšuje obranu hrdinovi o 1 a skrýva štít.
     *
     * @param hrdina hrdina, ktorý používa obranný štít
     */
    @Override
    public void pouzi(Hrdina hrdina) {
        hrdina.setObrana(1);
        this.skry();
    }
}
