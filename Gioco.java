
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pietro Micoli
 */
public class Gioco {
    private Bordi bordo;
    private Giocatore[] giocatori;
    private int giocatoriattuali;

    public Gioco() {
        bordo = new Bordi(this);
        giocatori = new Giocatore[2];
        giocatori[0] = new Giocatore(1);
        giocatori[1] = new Giocatore(2);
        giocatoriattuali = 0;
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!bordo.seBordoFull()) {

            System.out.println("Punti: Giocatore 1: " + giocatori[0].getPunteggio() + ", Giocatore 2: " + giocatori[1].getPunteggio());
            bordo.display();
            Giocatore giocatoreAttuale = giocatori[giocatoriattuali];
            System.out.println("Giocatore " + giocatoreAttuale.getNumero() + ", e il tuo turno.");
            System.out.println("Inserisci due lettere (es: AB):");
            String input = scanner.nextLine();

            char point1 = Character.toUpperCase(input.charAt(0));
            char point2 = Character.toUpperCase(input.charAt(1));

            if (bordo.drawLine(point1, point2, giocatoreAttuale)) {
                giocatoreAttuale.incrementaPunti();
            } else {
                giocatoriattuali = (giocatoriattuali + 1) % 2;
            }

        }
        bordo.display();
        if (giocatori[0].getPunteggio() > giocatori[1].getPunteggio()) {
            System.out.println("Il giocatore " + giocatori[0] + " ha vinto!");
        } else if(giocatori[0].getPunteggio() < giocatori[1].getPunteggio()){
            System.out.println("Il giocatore " + giocatori[1] + " ha vinto!");
        } else {
            System.out.println("E' un pareggio!");
        }
    }
    
    public Giocatore[] getGiocatori() {
        return giocatori;
    }

    public int getGiocatoriAttuali() {
        return giocatoriattuali;
    }

    public Bordi getBordo() {
        return bordo;
    }
}
