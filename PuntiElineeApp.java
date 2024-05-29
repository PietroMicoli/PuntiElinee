
import javax.swing.*;

public class PuntiElineeApp extends JFrame {

    public PuntiElineeApp() {
        super("test");
        Gioco gioco = new Gioco();
        PuntiELineePanel p = new PuntiELineePanel(gioco);
        add(p);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 450, 300);
        setVisible(true);

        gioco.play();


    }

    public static void main(String[] args) {
        new PuntiElineeApp();
    }
}