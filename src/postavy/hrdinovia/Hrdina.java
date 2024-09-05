package postavy.hrdinovia;

import hernySvet.Poloha;
import postavy.Postava;
import predmety.Predmet;
import skills.SpecialnySpell;
import skills.Utok;
import fri.shapesge.BlokTextu;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;

import java.util.ArrayList;

/**
 * Trieda Hrdina, reprezentuje postavu v hre, ktorú ovláda hráč.
 * @author Benjamin Vyhnal
 */
public abstract class Hrdina implements Postava {

    private String meno;
    private int hp;
    private Poloha poloha;
    private Obrazok hrdina;
    private String cesta;
    private Obrazok gameBar;
    private Utok utok;
    private ArrayList<Predmet> inventar;
    private int animacia;
    private BlokTextu zivotText;
    private BlokTextu koniecText;
    private BlokTextu obranaText;
    private int obrana;
    private SpecialnySpell specialnySpell;
    private boolean nesmrtelny;
    private boolean zamrznutaHra;

    /**
     * Konštruktor pre vytvorenie inštancie triedy Hrdina.
     *
     * @param meno          meno hrdinu
     * @param hp            životy hrdinu
     * @param cesta         cesta k obrázku hrdinu
     * @param gb            cesta k obrázku game baru
     * @param utok          útok hrdinu
     * @param obrana        obrana hrdinu
     * @param specSpell     špeciálny kúzelnícky útok hrdinu
     */
    public Hrdina(String meno, int hp, String cesta, String gb, Utok utok, int obrana, SpecialnySpell specSpell) {
        this.meno = meno;
        this.hp = hp;
        this.hrdina = new Obrazok(cesta);
        this.hrdina.zmenPolohu(100, 650);
        this.poloha = new Poloha(100, 650);
        this.hrdina.zobraz();

        this.cesta = cesta;
        this.specialnySpell = specSpell;

        this.gameBar = new Obrazok(gb);
        this.gameBar.zmenPolohu(200, 800);
        this.gameBar.zobraz();

        this.utok = utok;
        this.utok.zmenPolohu(this.poloha.getX(), this.poloha.getY());
        this.utok.getPoloha().zmenPolohu(this.poloha.getX(), this.poloha.getY());
        this.animacia = 0;

        this.vytvor();
        this.obrana = obrana;
        this.nesmrtelny = false;
    }


    /**
     * Metóda vráti špeciálny kúzelnícky útok hrdinu.
     *
     * @return špeciálny kúzelnícky útok hrdinu
     */
    public SpecialnySpell getSpecialnySpell() {
        return this.specialnySpell;
    }

    /**
     * Metóda pre útok hrdinu.
     * Implementácia závisí od konkrétnej triedy hrdinu.
     */
    @Override
    public abstract void utok();

    /**
     * Metóda pre použitie predmetu.
     *
     * @param predmet predmet, ktorý sa má použiť
     */

    @Override
    public void pouziPredmet(Predmet predmet) {
        predmet.pouzi(this);
    }


    /**
     * Metóda pre výpočet obrany hrdinu.
     *
     * @param uder počet úderov na obranu
     */
    @Override
    public void obrana(int uder) {
        if (uder > 0) {
            var zostatokPoUtoku = this.obrana - uder;
            if (zostatokPoUtoku <= 0) {
                this.obrana = 0;
                this.hp += zostatokPoUtoku;
            } else {
                this.obrana -= uder;
            }

            if (this.hp <= 0) {
                this.koniecText.zobraz();
                this.hp = 0;
            }
        }
    }

    public void setObrana(int obrana) {
        this.obrana += obrana;
    }

    /**
     * Metóda pre posun hrdinu hore.
     * Implementácia závisí od konkrétnej triedy hrdinu.
     */
    @Override
    public void posunHore() {

    }

    /**
     * Metóda pre posun hrdinu dole.
     * Implementácia závisí od konkrétnej triedy hrdinu.
     */
    @Override
    public void posunDole() {
        /*this.hrdina.posunDole();
        this.poloha.zmenPolohu(this.poloha.getX(), this.poloha.getY() + 20);
        this.utok.posunDole();
        this.zivotText.posunDole();
        this.obranaText.posunDole();*/
    }

    /**
     * Metóda pre posun hrdinu doprava.
     * Implementácia závisí od konkrétnej triedy hrdinu.
     */
    @Override
    public void posunVpravo() {
        this.hrdina.posunVpravo();
        this.poloha.zmenPolohu(this.poloha.getX()  + 20, this.poloha.getY());
        if (!(this instanceof Mage)) {
            this.utok.posunVpravo();
        } else if (this.getUtok().getAnimacia() % 8 == 0 && (this instanceof Mage)) {
            this.utok.posunVpravo();
        }
        this.zivotText.posunVpravo();
        this.obranaText.posunVpravo();
    }

    /**
     * Metóda pre posun hrdinu doľava.
     * Implementácia závisí od konkrétnej triedy hrdinu.
     */
    @Override
    public void posunVlavo() {
        this.hrdina.posunVlavo();
        this.poloha.zmenPolohu(this.poloha.getX() - 20, this.poloha.getY());
        if (!(this instanceof Mage)) {
            this.utok.posunVlavo();
        } else if (this.getUtok().getAnimacia() % 8 == 0 && (this instanceof Mage)) {
            this.utok.posunVlavo();
        }
        this.zivotText.posunVlavo();
        this.obranaText.posunVlavo();
    }

    /**
     * Metóda pre posun hrdinu zvisle.
     * Implementácia závisí od konkrétnej triedy hrdinu.
     *
     * @param oKolko o koľko jednotiek sa má posunúť hrdina zvisle
     */
    @Override
    public void posunZvisle(int oKolko) {
        this.hrdina.posunZvisle(oKolko);
        this.poloha.zmenPolohu(this.poloha.getX(), this.poloha.getY() + oKolko);
        if (!(this instanceof Mage)) {
            this.utok.posunZvisle(oKolko);
        } else if (this.getUtok().getAnimacia() % 8 == 0 && (this instanceof Mage)) {
            this.utok.posunZvisle(oKolko);
        }
        this.zivotText.posunZvisle(oKolko);
        this.obranaText.posunZvisle(oKolko);
    }

    /**
     * Metóda pre posun hrdinu vodorovne.
     * Implementácia závisí od konkrétnej triedy hrdinu.
     *
     * @param i o koľko jednotiek sa má posunúť hrdina vodorovne
     */
    @Override
    public void posunVodorovne(int i) {
        this.hrdina.posunVodorovne(i);
        this.poloha.zmenPolohu(this.poloha.getX() + i, this.poloha.getY());
        if (!(this instanceof Mage)) {
            this.utok.posunVodorovne(i);
        } else if (this.getUtok().getAnimacia() % 8 == 0 && (this instanceof Mage)) {
            this.utok.posunVodorovne(i);
        }
        this.zivotText.posunVodorovne(i);
        this.obranaText.posunVodorovne(i);
    }

    /**
     * Metóda pre získanie obrázku hrdinu.
     *
     * @return obrázok hrdinu
     */
    @Override
    public Obrazok getHrdina() {
        return this.hrdina;
    }

    /**
     * Metóda pre získanie game baru.
     *
     * @return obrázok game baru
     */
    public Obrazok getGameBar() {
        return this.gameBar;
    }

    /**
     * Metóda pre získanie útoku hrdinu.
     *
     * @return útok hrdinu
     */
    public Utok getUtok() {
        return this.utok;
    }

    /**
     * Metóda pre získanie polohy hrdinu.
     *
     * @return poloha hrdinu
     */
    public Poloha getPoloha() {
        return this.poloha;
    }

    /**
     * Metóda pre animáciu hrdinu.
     */
    public abstract void animacia(boolean stlacene);

    /**
     * Metóda pre získanie aktuálneho poradia snímky animácie hrdinu.
     *
     * @return aktuálna snímka animácie hrdinu
     */
    public int getAnimacia() {
        return this.animacia;
    }

    /**
     * Metóda pre pridanie k aktuálnej animácii hrdinu.
     */
    public void animaciaPlus() {
        this.animacia++;
    }

    /**
     * Metóda pre nastavenie animácie hrdinu na konkrétnu hodnotu.
     *
     * @param animacia animácia hrdinu
     */
    public void setAnimacia(int animacia) {
        this.animacia = animacia;
    }

    /**
     * Metóda pre skrytie obrázku hrdinu.
     */
    public void skry() {
        this.hrdina.skry();
    }

    /**
     * Metóda pre zobrazenie obrázku hrdinu.
     */
    public void zobraz() {
        this.hrdina.zobraz();
    }

    /**
     * Metóda pre pridanie životov hrdinu.
     *
     * @param kolko koľko životov sa má pridať
     */
    public void pridajHP(int kolko) {
        this.hp += kolko;
        this.zivotText.zmenText(String.valueOf(this.hp));
    }

    /**
     * Metóda pre zobrazenie textov života a obrany hrdinu.
     */
    public void textZobraz() {
        this.zivotText.zobraz();
        this.obranaText.zobraz();
    }

    /**
     * Metóda pre získanie ľavého horného X súradnice hrdinu.
     *
     * @return ľavá horná X súradnica
     */
    public int getLavyHornyX() {
        return this.getPoloha().getX();
    }

    /**
     * Metóda pre získanie pravého horného X súradnice hrdinu.
     *
     * @return pravá horná X súradnica
     */
    public int getPravyHornyX() {
        return this.getPoloha().getX() + 79;
    }

    /**
     * Metóda pre získanie ľavého horného Y súradnice hrdinu.
     *
     * @return ľavá horná Y súradnica
     */
    public int getLavyHornyY() {
        return this.poloha.getY();
    }

    /**
     * Metóda pre získanie pravého dolného Y súradnice hrdinu.
     *
     * @return pravá dolná Y súradnica
     */
    public int getPravyDolnyY() {
        return this.poloha.getY() + 89;
    }

    /**
     * Metóda pre aktualizáciu počtu životov hrdinu na obrazovke.
     */
    public void aktualizujHp() {
        this.zivotText.zmenText(String.valueOf(this.hp));
        this.obranaText.zmenText(String.valueOf(this.obrana));
    }

    /**
     * Abstraktná metóda pre špeciálny útok hrdinu.
     * Implementácia závisí od konkrétnej triedy hrdinu.
     */
    public abstract void specialnySpell();

    /**
     * Metóda pre nastavenie hrdinu ako nesmrteľného.
     *
     * @param nesmrtelny true, ak má byť hrdina nesmrteľný, false inak
     */
    public void setNesmrtelny(boolean nesmrtelny) {
        this.nesmrtelny = nesmrtelny;
    }

    /**
     * Metóda pre získanie informácie o tom, či je hrdina nesmrteľný.
     *
     * @return true ak je hrdina nesmrteľný, inak false.
     */
    public boolean isNesmrtelny() {
        return this.nesmrtelny;
    }

    public boolean isZamrznutaHra() {
        return this.zamrznutaHra;
    }

    public void setZamrznutaHra(boolean zamrznutaHra) {
        this.zamrznutaHra = zamrznutaHra;
    }

    public int getHp() {
        return this.hp;
    }

    private void vytvor() {
        this.zivotText = new BlokTextu(String.valueOf(this.hp));
        this.zivotText.zmenFont("Impact", StylFontu.PLAIN, 50);
        this.zivotText.zmenFarbu("black");
        this.zivotText.zmenPolohu(this.getPoloha().getX() + 10, this.getPoloha().getY() - 30);

        this.koniecText = new BlokTextu("GAME OVER");
        this.koniecText.zmenPolohu(275, 500);
        this.koniecText.zmenFont("Impact", StylFontu.PLAIN, 150);
        this.koniecText.zmenFarbu("red");
        this.koniecText.skry();

        this.obranaText = new BlokTextu(String.valueOf(this.obrana));
        this.obranaText.zmenPolohu(this.getPoloha().getX() - 15, this.getPoloha().getY() + 20);
        this.obranaText.zmenFont("Impact", StylFontu.PLAIN, 35);
        this.obranaText.zmenFarbu("yellow");
    }
}
