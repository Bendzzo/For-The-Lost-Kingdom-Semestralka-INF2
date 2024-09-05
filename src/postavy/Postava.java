package postavy;

import predmety.Predmet;
import fri.shapesge.Obrazok;

/**
 * Rozhranie, ktoré definuje základné metódy pre postavy v hre.
 * Obsahuje metódy na útok, obranu, používanie predmetov a pohyb.
 *
 * @author Benjamin Vyhnal
 */
public interface Postava {

    /**
     * Metóda na vykonanie útoku.
     */
    void utok();

    /**
     * Metóda na vykonanie obrany pred úderom.
     *
     * @param uder počet úderov, ktoré je potrebné odraziť
     */
    void obrana(int uder);

    /**
     * Metóda na použitie predmetu.
     *
     * @param predmet predmet, ktorý sa má použiť
     */
    void pouziPredmet(Predmet predmet);

    /**
     * Metóda na posun postavy hore.
     */
    void posunHore();

    /**
     * Metóda na posun postavy dole.
     */
    void posunDole();

    /**
     * Metóda na posun postavy vpravo.
     */
    void posunVpravo();

    /**
     * Metóda na posun postavy vľavo.
     */
    void posunVlavo();

    /**
     * Metóda na posun postavy zvisle.
     *
     * @param oKolko počet jednotiek, o ktoré sa má postava posunúť zvisle
     */
    void posunZvisle(int oKolko);

    /**
     * Metóda na posun postavy vodorovne.
     *
     * @param oKolko počet jednotiek, o ktoré sa má postava posunúť vodorovne
     */
    void posunVodorovne(int oKolko);

    /**
     * Metóda na získanie obrázka postavy.
     *
     * @return obrázok postavy
     */
    Obrazok getHrdina();
}
