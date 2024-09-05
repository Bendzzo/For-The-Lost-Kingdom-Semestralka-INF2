package hernySvet;

/**
 * Reprezentuje polohu objektu v 2D priestore.
 * @author Benjamin Vyhnal
 */
public class Poloha {

    private int x;
    private int y;

    /**
     * Konštruktor triedy Poloha.
     *
     * @param x súradnica x
     * @param y súradnica y
     */
    public Poloha(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Metóda pre získanie súradnice x.
     *
     * @return súradnica x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Metóda pre získanie súradnice y.
     *
     * @return súradnica y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Metóda pre nastavenie súradnice x.
     *
     * @param inyX nová hodnota súradnice x
     */
    public void setX(int inyX) {
        this.x = inyX;
    }

    /**
     * Metóda pre nastavenie súradnice y.
     *
     * @param inyY nová hodnota súradnice y
     */
    public void setY(int inyY) {
        this.y = inyY;
    }

    /**
     * Metóda pre získanie aktuálnej polohy.
     *
     * @return aktuálna poloha
     */
    public Poloha getPoloha() {
        return this;
    }

    /**
     * Metóda pre zmenu polohy na nové súradnice.
     *
     * @param x nová súradnica x
     * @param y nová súradnica y
     */
    public void zmenPolohu(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
