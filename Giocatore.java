/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pietro Micoli
 */
public class Giocatore {
    private int numero;
    private int punti;

    public Giocatore(int numero) {
        this.numero = numero;
        punti = 0;
    }

    public int getNumero() {
        return numero;
    }

    public int getPunteggio() {
        return punti;
    }

    public void incrementaPunti() {
        punti++;
    }

    @Override
    public String toString() {
        return "Giocatore{" +
                "numero=" + numero +
                ", punti=" + punti +
                '}';
    }
}
