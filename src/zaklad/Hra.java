package zaklad;

import fri.shapesge.Obrazok;
import hernySvet.Poloha;
import hernySvet.Pozadie;
import hernySvet.Prekazka;
import postavy.VyberPostavy;
import postavy.hrdinovia.Hrdina;
import postavy.hrdinovia.Mage;
import predmety.HealingPotion;
import predmety.ObrannyStit;
import predmety.Poison;
import predmety.Predmet;
import fri.shapesge.Manazer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Hlavná trieda. Spravuje všetky kolízie, pohyby predmetov a logiku hry.
 *
 * @author Benjamin Vyhnal
 */
public class Hra {
    private VyberPostavy vyberPostavy;
    private Manazer manazer;
    private Hrdina hrdina;
    private boolean uzStart;
    private boolean skok;
    private static final int RYCHLOST_SKOKU = 30;
    private int pocetTikov;
    private int animacia;
    private boolean koniecSkoku;
    private boolean uzUtok = false;
    private ArrayList<Prekazka> zoznamPrekazok;
    private int pocetPrePrekazku;
    private int pocetUderov;
    private int pocetTikovPredmet;
    private Predmet predmet;
    private boolean mozemVytvoritPredmet;
    private int casNaPredmet;
    private Obrazok canon;
    private Obrazok opakovat;
    private Poloha polohaOpakovat;

    /**
     * Konštruktor pre vytvorenie inštancie triedy Hra.
     * Inicializuje herný svet s daným hrdinom.
     *
     * @param hrdina hrdina, ktorý bude hrať hru
     */
    public Hra(Hrdina hrdina) {
        Pozadie pozadie = new Pozadie("assets/bg1.png");
        pozadie.zmenPolohu(0, 0);
        this.hrdina = hrdina;

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        this.animacia = 0;
        this.zoznamPrekazok = new ArrayList<>();
        this.vytvorPrekazky();
        this.pocetPrePrekazku = 0;
        this.mozemVytvoritPredmet = true;
        this.predmet = null;

        this.casNaPredmet = 25;
        this.canon = new Obrazok("assets/cannon.png", 1100, 650);
        this.canon.zobraz();

        this.opakovat = new Obrazok("assets/redo.png");
        this.opakovat.skry();
        this.opakovat.zmenPolohu(0, 0);
        this.polohaOpakovat = new Poloha(0, 0);

        this.vyberPostavy = new VyberPostavy();
        this.vyberPostavy.setVisible(false);
    }

    /**
     * Spustí skok hrdinu.
     */
    public void posunHore() {
        this.skok = true;
    }

    /**
     * Posunie hrdinu dole.
     */
    public void posunDole() {
        this.hrdina.posunDole();
    }

    /**
     * Posunie hrdinu vpravo.
     */
    public void posunVpravo() {
        this.hrdina.posunVpravo();
    }

    /**
     * Posunie hrdinu vľavo.
     */
    public void posunVlavo() {
        this.hrdina.posunVlavo();
    }

    /**
     * Hlavná metóda hry, ktorá sa vykonáva v každom tiku.
     * Spravuje skok, kolízie, pohyb prekážok a aktualizuje stav hry.
     */
    public void tik() {
        if (!this.uzStart) {
            this.start();
        }

        if (this.skok) {
            this.hrdina.posunZvisle(-RYCHLOST_SKOKU);
            if (!this.uzUtok) {
                this.hrdina.getUtok().posunZvisle(-RYCHLOST_SKOKU);
            }
            this.pocetTikov++;

            if (this.pocetTikov > 7) {
                this.pocetTikov = 0;
                this.skok = false;
                this.koniecSkoku = true;
            }
        }

        if (this.koniecSkoku) {
            this.pocetTikov++;
            this.hrdina.posunZvisle(RYCHLOST_SKOKU);
            if (!this.uzUtok) {
                this.hrdina.getUtok().posunZvisle(RYCHLOST_SKOKU);
            }
            if (this.pocetTikov > 7) {
                this.pocetTikov = 0;
                this.koniecSkoku = false;
            }
        }
        if (this.hrdina.getHp() != 0) {
            if (!this.hrdina.isZamrznutaHra() && this.zoznamPrekazok != null && this.pocetPrePrekazku >= this.casNaPredmet) {
                for (Prekazka prekazka : this.zoznamPrekazok) {
                    prekazka.posunVodorovne(-10);
                }
                if (this.jePrekazkaZa(this.zoznamPrekazok.get(0))) {
                    this.pocetPrePrekazku = 0;
                }
                this.aktualizujPrekazky();
            }
            this.pocetPrePrekazku++;
            this.kolizia();
            this.hrdina.aktualizujHp();

            if (this.hrdina.getSpecialnySpell() != null && this.hrdina.getSpecialnySpell().isJeStlaceny()) {
                this.hrdina.specialnySpell();
            }
        } else {
            this.opakovat.zmenPolohu(600, 550);
            this.polohaOpakovat.zmenPolohu(600, 550);
            this.opakovat.zobraz();
        }
    }

    /**
     * Spravuje kolízie hrdinu s prekážkami a predmetmi.
     */
    public void kolizia() {
        if (this.hrdina.isNesmrtelny()) {
            this.doNothing();
        } else {
            for (Prekazka prekazka: this.zoznamPrekazok) {
                if (this.hrdina.getLavyHornyX() < prekazka.getPravyHornyX() && this.hrdina.getPravyHornyX() > prekazka.getX() &&
                        this.hrdina.getLavyHornyY() < prekazka.getLavyDolnyY() && this.hrdina.getPravyDolnyY() > prekazka.getY()) {
                    this.pocetUderov++;
                    this.hrdina.posunVodorovne(-3);
                } else {
                    this.hrdina.obrana(this.pocetUderov / 4);
                    this.pocetUderov = 0;
                }
            }

            if (this.predmet != null) {
                if (this.hrdina.getLavyHornyX() < this.predmet.getPravyHornyX() && this.hrdina.getPravyHornyX() > this.predmet.getPoloha().getX() &&
                        this.hrdina.getLavyHornyY() < this.predmet.getLavyDolnyY() && this.hrdina.getPravyDolnyY() > this.predmet.getPoloha().getY()) {
                    this.predmet.pouzi(this.hrdina);
                    this.predmet = null;
                    this.pocetTikovPredmet = 0;
                    this.mozemVytvoritPredmet = true;
                }
            }

            var prekazka = this.zoznamPrekazok.get(0);
            var utokPoloha = this.hrdina.getUtok().getPoloha();

            if (this.hrdina instanceof Mage && this.uzUtok && prekazka.getX() < utokPoloha.getX() + 175 && prekazka.getPravyHornyX() > utokPoloha.getPoloha().getX() &&
                    prekazka.getY() < utokPoloha.getY() + 60 && prekazka.getLavyDolnyY() > utokPoloha.getY()) {
                this.hrdina.getUtok().skry();
                this.uzUtok = false;
                this.hrdina.getUtok().ukonciAnimaciu();
                this.hrdina.setAnimacia(0);
                this.hrdina.getUtok().zmenPolohu(this.hrdina.getPoloha().getX(), this.hrdina.getPoloha().getY() + 25);
                this.hrdina.getUtok().skry();
                this.zoznamPrekazok.get(0).zmenPolohu(-30, this.zoznamPrekazok.get(0).getY());
                System.out.println("TREFA");
            }
        }
    }

    /**
     * Vykoná krok útoku hrdinu.
     */
    public void krok() {
        this.hrdina.getUtok().krok();
        this.uzUtok = true;
        if (!(this.hrdina instanceof Mage)) {
            this.hrdina.skry();
        }
    }

    /**
     * Ukončí hru.
     */
    public void zrus() {
        System.exit(0);
    }

    /**
     * Spravuje animáciu útoku hrdinu. Opakuje sa kazdych 115 milisekund.
     */
    public void animacia() {
        if (this.uzUtok) {
            var poloha = this.hrdina.getPoloha();
            this.hrdina.animacia(true);

            if (this.hrdina.getAnimacia() > 22) {
                this.uzUtok = false;
                this.hrdina.animacia(true);
                this.hrdina.zobraz();
            }
        }
    }

    /**
     * Spravuje vytváranie a pohyb predmetov.
     */
    public void predmety() {
        this.pocetTikovPredmet++;

        if (this.pocetTikovPredmet >= 5 && this.mozemVytvoritPredmet && this.predmet == null) {
            Random random = new Random();
            int cislo;
            int x;
            cislo = random.nextInt(3) + 1;
            x = random.nextInt(1000) + 100;
            this.mozemVytvoritPredmet = false;
            switch (cislo) {
                case 1:
                    this.predmet = new HealingPotion(x, -50);
                    this.predmet.zobraz();
                    break;
                case 2:
                    this.predmet = new ObrannyStit(x, -50);
                    this.predmet.zobraz();
                    break;
                case 3:
                    this.predmet = new Poison(x, -50);
                    this.predmet.zobraz();
                    break;
            }
        }

        if (this.predmet != null && this.jePredmetZa()) {
            this.pocetTikovPredmet = 0;
            this.mozemVytvoritPredmet = true;
            this.predmet = null;
        }
    }

    /**
     * Posunie všetky prekážky o zadanú vzdialenosť.
     *
     * @param vzdialenost vzdialenosť, o ktorú sa prekážky posunú
     */
    public void posunPrekazky(int vzdialenost) {
        for (Prekazka prekazka : this.zoznamPrekazok) {
            prekazka.posunVodorovne(vzdialenost);
        }
    }

    /**
     * Skontroluje, či je prekážka mimo obrazovky.
     *
     * @param prekazka prekážka, ktorá sa kontroluje
     * @return true, ak je prekážka mimo obrazovky, inak false
     */
    public boolean jePrekazkaZa(Prekazka prekazka) {
        return prekazka.getX() < -29;
    }

    /**
     * Aktualizuje polohu prekážok, ktoré sú mimo obrazovky.
     */
    public void aktualizujPrekazky() {
        for (Prekazka prekazka : this.zoznamPrekazok) {
            if (this.jePrekazkaZa(prekazka)) {
                prekazka.zmenPolohu(prekazka.getX() + 1130, prekazka.getY());
            }
        }
    }

    /**
     * Spravuje vytváranie a pohyb predmetov. Opakuje sa kazdych 115ms.
     */
    public void droppy() {
        if (this.hrdina.getHp() != 0) {
            if (!this.hrdina.isZamrznutaHra()) {
                this.predmety();
                if (this.predmet != null) {
                    this.predmet.posunDole();
                }
            }
        }
    }

    /**
     * Spracuje výber súradníc a reštartuje hru v prípade stlačenia tlačítka na reštart.
     *
     * @param x X-ová súradnica výberu.
     * @param y Y-ová súradnica výberu.
     */
    public void vyberSuradnice(int x, int y) {
        if (x < this.polohaOpakovat.getX() + 64 && y < this.polohaOpakovat.getY() + 64) {

            this.opakovat.zmenPolohu(0, 0);
            this.polohaOpakovat.zmenPolohu(0, 0);
            this.opakovat.skry();

            this.manazer.prestanSpravovatObjekt(this);
            this.vyberPostavy.setVisible(true);
            this.hrdina.getSpecialnySpell().skry();
        }
    }

    private void start() {
        if (!this.uzStart) {
            this.hrdina.getHrdina().skry();
            this.hrdina.getHrdina().zobraz();
            this.hrdina.getGameBar().skry();
            this.hrdina.getGameBar().zobraz();
            this.hrdina.textZobraz();
            if (this.hrdina.getSpecialnySpell() != null) {
                this.hrdina.getSpecialnySpell().zobraz();
                this.hrdina.getSpecialnySpell().skry();
            }
            this.uzStart = true;
        }
    }


    private void vytvorPrekazky() {
        this.zoznamPrekazok.add(new Prekazka(1110, 680));
    }

    private boolean jePredmetZa() {
        return this.predmet != null && this.predmet.getPoloha().getY() > 900;
    }

    private void doNothing() {
    }
}
