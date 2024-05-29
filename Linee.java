/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Objects;

/**
 *
 * @author Pietro Micoli
 */
public class Linee {
    private Punti start;
    private Punti end;
    Giocatore g;   // se null libera 

    public Linee(Punti start, Punti end) {
        this.start = start;
        this.end = end;
        g=null;
    }

    //se null false altrimenti true
    public boolean occupata() {
        return g!=null;
    }

    public void disegna(Giocatore player) {
        g=player;
    }

    public Punti getStart() {
        return start;
    }

    public Punti getEnd() {
        return end;
    }

    @Override
    public String toString() {
        if (g!=null) {
            return ""+g.getNumero();
        } else {
            return "+";
        }
    }

    public String getEttichetta(){
        return start.getEtichetta() + "" + end.getEtichetta();
    }
    
    //true se la linea corrisponde al segmento l1 l2
    boolean uguale(char l1,char l2){
        l1=Character.toUpperCase(l1);
        l2=Character.toUpperCase(l2);
        
        if ((start.getEtichetta()==l1 && end.getEtichetta()==l2)  ||
                (start.getEtichetta()==l2 && end.getEtichetta()==l1))
            return true;
       else
            return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Linee linee = (Linee) o;
        return Objects.equals(start, linee.start) && Objects.equals(end, linee.end) && Objects.equals(g, linee.g);
    }

    public Giocatore getGiocatore() {
        return g;
    }
}
