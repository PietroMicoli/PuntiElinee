/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 19908
 */
public class Quadrato {
    private Linee[] linee;// ognuno ne ha 4
    private Giocatore giocatore;

    public Quadrato() {
        this.linee = new Linee[4];
    }

    public void aggiungilinea(Linee linea) {
        for (int i = 0; i < linee.length; i++) {
            if (linee[i] == null) {
                linee[i] = linea;
                break;
            }
        }
    }

    public boolean contieneLinea(Linee l) {
        for (int i = 0; i < linee.length; i++) {
            if(linee[i].equals(l)) return true;
        }
        return false;
    }

    public Linee[] getLinee() {
        return linee;
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }
    
    private int latiDisponibili = 4;
    
    public void decrementaLati() {
        latiDisponibili--;
    }
    
    public boolean completo() {
        if (latiDisponibili == 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String s="";
        for (int i = 0; i < linee.length; i++) {
            if(linee[i]==null) continue;
            s+=linee[i].getEttichetta()+" ";
        }
        return s;
    }
}

