package postavy;

import postavy.hrdinovia.Hrdina;
import postavy.hrdinovia.Mage;
import postavy.hrdinovia.Rogue;
import postavy.hrdinovia.Warrior;
import zaklad.Hra;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * GUI pre výber postavy, s ktorou sa následne hraje Hra.
 *
 * @author Benjamin Vyhnal
 */
public class VyberPostavy extends JFrame implements KeyListener {
    private JPanel vyberPanel;
    private JLabel magueLabel;
    private JLabel warriorLabel;
    private JLabel rogueLabel;
    private int indexVybranejPostavy;
    private boolean uzVybrany;

    /**
     * Konštruktor triedy, inicializuje okno pre výber postavy.
     */
    public VyberPostavy() {
        this.start();
    }

    /**
     * Metóda reagujúca na stlačenie klávesy.
     * Implementuje pohyb výberu medzi postavami a vytvorenie hry pri stlačení Enter.
     *
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int vyber = e.getKeyCode();

        if (vyber == KeyEvent.VK_LEFT) {
            this.indexVybranejPostavy = (this.indexVybranejPostavy - 1 + 3) % 3;
            this.vyberovyOkraj();
        } else if (vyber == KeyEvent.VK_RIGHT) {
            this.indexVybranejPostavy = (this.indexVybranejPostavy + 1) % 3;
            this.vyberovyOkraj();
        } else if (vyber == KeyEvent.VK_ENTER) {
            this.setVisible(false);
            this.uzVybrany = true;
            Hra hra = new Hra(this.getHrdina());
            switch (this.indexVybranejPostavy) {
                case 0:
                    System.out.println("Mage");

                    break;
                case 1:
                    System.out.println("Warrior");

                    break;
                case 2:
                    System.out.println("Rogue");

                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Metóda pre získanie vybranej postavy.
     *
     * @return inštancia vybranej postavy
     */
    public Hrdina getHrdina() {
        if (this.isUzVybrany()) {
            switch (this.indexVybranejPostavy) {
                case 0: return new Mage();
                case 1: return new Warrior();
                default: return new Rogue();
            }
        }
        return null;
    }

    /**
     * Metóda pre zistenie, či už bola postava vybraná.
     *
     * @return true, ak bola postava vybraná, false inak
     */
    public boolean isUzVybrany() {
        return this.uzVybrany;
    }

    private void vyberovyOkraj() {
        this.magueLabel.setBorder(null);
        this.warriorLabel.setBorder(null);
        this.rogueLabel.setBorder(null);

        switch (this.indexVybranejPostavy) {
            case 0:
                this.magueLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                break;
            case 1:
                this.warriorLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                break;
            case 2:
                this.rogueLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                break;
        }
    }

    private void start() {
        this.setTitle("Vyber si postavu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.vyberPanel = new JPanel(new GridLayout(1, 3));
        this.add(this.vyberPanel);

        this.magueLabel = new JLabel(new ImageIcon("assets/Mage/mage_vyber.png"));
        this.warriorLabel = new JLabel(new ImageIcon("assets/Warrior/warrior_vyber.png"));
        this.rogueLabel = new JLabel(new ImageIcon("assets/Rogue/rogue_vyber.png"));

        this.vyberPanel.add(this.magueLabel);
        this.vyberPanel.add(this.warriorLabel);
        this.vyberPanel.add(this.rogueLabel);

        this.magueLabel.setToolTipText("Mag ma magicke schopnosti. Sustredi sa na boj z dialky. Najdolezitejsia vlastnost je intellect");
        this.warriorLabel.setToolTipText("Warrior je silny bojovnik, ktory ma velmi silnu obranu. Sustredi sa na boj z blizka. Najdolezitejsia vlastnost je strength");
        this.rogueLabel.setToolTipText("Rogue je velmi tichy a rychly bojovnik, ktory sa dokaze zneviditelnit. Sustredi sa na boj z blizka. Najdolezitejsia vlastnost je agility");

        this.indexVybranejPostavy = 0;
        this.uzVybrany = false;
        this.vyberovyOkraj();

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
    }

}