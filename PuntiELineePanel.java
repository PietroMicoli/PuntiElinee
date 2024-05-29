
import javax.swing.*;
import java.awt.*;

class PuntiELineePanel extends JPanel {
    private Gioco gioco;

    public PuntiELineePanel(Gioco g) {
        gioco = g; // Crea il gioco

        setPreferredSize(new Dimension(600, 600)); // Dimensione del pannello

        // Aggiungi un listener per intercettare l'input dell'utente
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int x = evt.getX();
                int y = evt.getY();

                // Trasforma le coordinate del click in una mossa valida nel gioco
                char label1 = getPointLabel(x, y); // Ottieni la lettera del punto cliccato
                char label2 = getNextPointLabel(x, y); // Ottieni la lettera del punto successivo

                if (label1 != '\0' && label2 != '\0') {
                    // Esegui la mossa nel gioco
                    Giocatore currentPlayer = gioco.getGiocatori()[gioco.getGiocatoriAttuali()];
                    boolean result = gioco.getBordo().drawLine(label1, label2, currentPlayer);

                    if (result) {
                        repaint(); // Ridisegna il pannello dopo la mossa
                    }
                }
            }
        });
    }

    // Metodo per ottenere la lettera del punto cliccato
    private char getPointLabel(int x, int y) {
        int row = y / 200; // La griglia è suddivisa in 3 righe
        int col = x / 200; // La griglia è suddivisa in 3 colonne

        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            return (char) ('A' + row * 3 + col);
        } else {
            return '\0'; // Carattere nullo se il punto non è valido
        }
    }

    // Metodo per ottenere la lettera del punto successivo
    private char getNextPointLabel(int x, int y) {
        int row = y / 200; // La griglia è suddivisa in 3 righe
        int col = x / 200; // La griglia è suddivisa in 3 colonne

        if (row >= 0 && row < 3 && col >= 0 && col < 2) {
            return (char) ('A' + row * 3 + col + 1);
        } else {
            return '\0'; // Carattere nullo se il punto successivo non è valido
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Disegna la griglia di punti e linee
        Punti[][] punti = gioco.getBordo().getPunti();
        Linee[] linee = gioco.getBordo().getLinee();

        for (int i = 0; i < punti.length; i++) {
            for (int j = 0; j < punti[i].length; j++) {
                g.setColor(Color.BLACK);
                g.fillOval(j * 200 + 50, i * 200 + 50, 20, 20); // Disegna il punto

                if (j < punti[i].length - 1) {
                    // Disegna la linea orizzontale
                    for (Linee l : linee) {
                        if (l.uguale(punti[i][j].getEtichetta(), punti[i][j + 1].getEtichetta())) {
                            if (l.getGiocatore() != null) {
                                if (l.getGiocatore().getNumero() == 1) {
                                    g.setColor(Color.RED);
                                } else {
                                    g.setColor(Color.BLUE);
                                }
                            } else {
                                g.setColor(Color.BLACK);
                            }
                            g.drawLine(j * 200 + 80, i * 200 + 60, (j + 1) * 200 + 20, i * 200 + 60);
                        }
                    }
                }

                if (i < punti.length - 1) {
                    // Disegna la linea verticale
                    for (Linee l : linee) {
                        if (l.uguale(punti[i][j].getEtichetta(), punti[i + 1][j].getEtichetta())) {
                            if (l.getGiocatore() != null) {
                                if (l.getGiocatore().getNumero() == 1) {
                                    g.setColor(Color.RED);
                                } else {
                                    g.setColor(Color.BLUE);
                                }
                            } else {
                                g.setColor(Color.BLACK);
                            }
                            g.drawLine(j * 200 + 60, i * 200 + 80, j * 200 + 60, (i + 1) * 200 + 20);
                        }
                    }
                }
            }
        }
        
        g.setColor(Color.BLACK);
        g.drawString("Giocatore 1 (Rosso): " + gioco.getGiocatori()[0].getPunteggio(), 20, 20);
        g.drawString("Giocatore 2 (Blu): " + gioco.getGiocatori()[1].getPunteggio(), 20, 40);
    }
    
    private void mostraVincitore() {
        Giocatore vincitore = gioco.getBordo().getVincitore();
        String messaggio;

        if (vincitore == null) {
            messaggio = "È un pareggio!";
        } else {
            messaggio = "Il vincitore è il giocatore " + vincitore.getNumero();
        }

        JOptionPane.showMessageDialog(this, messaggio, "Risultato del gioco", JOptionPane.INFORMATION_MESSAGE);
    }
}