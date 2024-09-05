package hernySvet;

import fri.shapesge.Obrazok;

/**
 * Obrazok, ktorý sa zobrazí na najnižšej vrsvte.
 * @author Benjamin Vyhnal
 */
public class Pozadie {
    private Obrazok pozadie;

    /**
     * Konštruktor triedy Pozadie.
     * Vytvorí nový objekt Pozadia na základe zadaného obrázku.
     *
     * @param cesta cesta k súboru s obrázkom pozadia
     */
    public Pozadie(String cesta) {
        this.pozadie = new Obrazok(cesta);
        this.pozadie.zobraz();
    }

    /**
     * Metóda pre získanie obrázku pozadia.
     *
     * @return obrázok pozadia
     */
    public Obrazok getPozadie() {
        return this.pozadie;
    }

    /**
     * Metóda pre zmenu polohy pozadia na základe zadaných súradníc.
     *
     * @param x nová súradnica x
     * @param y nová súradnica y
     */
    public void zmenPolohu(int x, int y) {
        this.pozadie.zmenPolohu(x, y);
    }
}
