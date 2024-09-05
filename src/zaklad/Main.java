package zaklad;

import postavy.VyberPostavy;

import javax.swing.SwingUtilities;

/**
 * Trieda, ktorá spusti celú hru.
 *
 * @author Benjamin Vyhnal
 */
public class Main {
    public static void main(String[] args) {
        //Zdroj Internet
        SwingUtilities.invokeLater(() -> {
            VyberPostavy vp = new VyberPostavy();
            vp.setVisible(true);
        });
    }
}