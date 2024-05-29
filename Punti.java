/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pietro Micoli
 */
public class Punti {
    private char lettera;
    private Giocatore numgioc;
    
    public Punti(char etichetta) {    
        this.lettera = Character.toUpperCase(etichetta);
    }

    public char getEtichetta() {
        return lettera;
    }

    public void setOwner(Giocatore giocatore) {
        numgioc = giocatore;
    }

    @Override
    public String toString() {
        if (numgioc == null) {
            return String.valueOf(lettera);
        } else {
            return String.valueOf(numgioc.getNumero());
        }
    }
}
