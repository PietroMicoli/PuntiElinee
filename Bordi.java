/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Pietro Micoli
 */
public class Bordi {

    private static final int SIZE = 3; 
    private Punti[][] punti; //3x3
    private Linee[] linee; //12
    private Giocatore[] giocatori; 
    private Quadrato[] quadrati; 
    
    public Bordi(Gioco gioco) {
        giocatori = gioco.getGiocatori();
        punti = new Punti[SIZE][SIZE];
        linee = new Linee[(SIZE - 1) * SIZE + (SIZE - 1) * SIZE];     // (numr-1)*numc  + (numc-1) *numr (12)
        quadrati = new Quadrato[(SIZE - 1) * (SIZE - 1)];
        initializePoints(); //A, B, ...
        initializeLines(); // AB, BC, ...
    }

    //crea i punti
    private void initializePoints() {
        char label = 'A';
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                punti[i][j] = new Punti(label); //A, B, C, D, E, F, G, H, I
                label++;
            }
        }
    }

    //crea tutte le linee e le associa ai quadrati
    private void initializeLines() {
        int p = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                linee[p] = new Linee(punti[i][j], punti[i][j + 1]);
                p++;
            }
        }

        for (int i = 0; i < SIZE - 1; i++) {
            for (int j = 0; j < SIZE; j++) {
                linee[p] = new Linee(punti[i][j], punti[i + 1][j]);
                p++;

            }
        }
        for (int i = 0; i < linee.length; i++) {
            System.out.print(linee[i].getEttichetta()+" "); // serve per stampare le linee
            if(i%10==0 && i!=0){
                System.out.println();
            }
        }
        System.out.println();


        for (int i = 0; i < quadrati.length; i++) {
            quadrati[i] = new Quadrato(); //crea i quadrati
        }

        //aggiunge ai quadrati line orrizzontali
        for (int i = 0; i < (SIZE-1)*SIZE; i++) {
            int numero_quadrato1=i;
            int numero_quadrato2=i-(SIZE-1);

            //                      (5-1)*(5-1)-1 = 4*4-1= 15
            if(numero_quadrato1<=(SIZE-1)*(SIZE-1)-1){
                quadrati[numero_quadrato1].aggiungilinea(linee[i]);
            }
            if(numero_quadrato2>=0){ //eliminare -4 in prima linea
                quadrati[numero_quadrato2].aggiungilinea(linee[i]);
            }
        }

        //aggiunge ai quadrati line verticali
        // da 20 a 40 --> 20 - 39 compresi
        int riga=-1;
        for (int i = (SIZE-1)*SIZE; i < (SIZE-1)*SIZE+(SIZE-1)*SIZE; i++) {
            int numero_quadrato1=i-(SIZE-1)*SIZE;

            if((i-(SIZE-1)*SIZE)%SIZE==0){ //se è la prima riga
                riga++;
            }

            if((numero_quadrato1+1)%SIZE!=0){ //se non è la prima colonna
                quadrati[numero_quadrato1-riga].aggiungilinea(linee[i]);
            }

            if(numero_quadrato1%SIZE!=0){ //se non è la prima riga
                quadrati[numero_quadrato1-1-riga].aggiungilinea(linee[i]);
            }
        }

        for (int i = 0; i < quadrati.length; i++) {
            System.out.println(quadrati[i]);
        }
    }

    // il giocatore traccia una linea fra l1 l2
    public boolean drawLine(char label1, char label2, Giocatore player) {

        boolean ridare=false; //se il giocatore fa un quadrato la mosa dopo e sua
        boolean corretta=false;
        for (Linee l : linee) {
            if (l.uguale(label1, label2)) {//trova linea da label1 a label2 e viceversa


                if (l.occupata() == false) { //se non è gia occupata
                    l.disegna(player); //disegna con il numero del giocatore
                    corretta=true;


                    for (int i = 0; i < quadrati.length; i++) { //controlla se la linea è contenuta in un quadrato
                        if(quadrati[i].contieneLinea(l)==true){ //se è contenuta

                            quadrati[i].decrementaLati();

                            if(quadrati[i].completo()==true){
                                quadrati[i].setGiocatore(player); //associa il giocatore al quadrato
                                ridare=true; //stesso giocatore
                            }
                        }
                    }
                }
            }
        }

        if(corretta==false){
            System.out.println("mossa non corretta ");
        }

        return ridare;
    }

    public boolean checkForSquare(Giocatore player) { //controlla se il giocatore ha fatto un quadrato
        return false;
    }

    // true se tutti i bordi sono disegnati
    public boolean seBordoFull() {
        for (Linee l : linee) {
            if (l.occupata() == false) {
                return false;
            }
        }
        return true;
    }
    
     public void display() {
        for (int i = 0; i < SIZE; i++) {// Inizia un nuovo ciclo for per le righe
            for (int j = 0; j < SIZE; j++) { // Inizia un nuovo ciclo for per le colonne
                System.out.print(punti[i][j]); 
                if (j < SIZE - 1) { // Se non è l'ultima colonna
                    //disegna linea punti[i][j] e punti[i][j+1]
                    for (Linee l : linee) { 
                        if (l.uguale(punti[i][j].getEtichetta(), punti[i][j + 1].getEtichetta())) {
                            System.out.print(" " + l + " ");
                        }
                    }
                }

            }
            System.out.println();
            // righe verticali
            if (i < SIZE - 1) { // Se non è l'ultima riga
                for (int j = 0; j < SIZE; j++) { // Inizia un nuovo ciclo for per le righe verticali
                    for (Linee l : linee) { //stampa le linee verticali
                        if (l.uguale(punti[i][j].getEtichetta(), punti[i + 1][j].getEtichetta())) {
                            System.out.print(l + "   ");
                        }
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public Giocatore getVincitore() {
        if (giocatori[0].getPunteggio() > giocatori[1].getPunteggio()) {
            return giocatori[0];
        } else if (giocatori[0].getPunteggio() < giocatori[1].getPunteggio()) {
            return giocatori[1];
        } else {
            return null;
        }
    }

    public Punti[][] getPunti() {
        return punti;
    }

    public Linee[] getLinee() {
        return linee;
    }

    public Giocatore[] getGiocatori() {
        return giocatori;
    }

    public Quadrato[] getQuadrati() {
        return quadrati;
    }
}
