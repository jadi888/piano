package irrigazione_giardino.unibs.it;

import org.w3c.dom.ls.LSProgressEvent;

public class Specie {

    private String nome;
    private int periodicità;  //max 30 giorni;
    private double quantità; //espressa in litri;

    public Specie(String nome, int periodicità, double quantità){
        this.nome = nome;
        this.periodicità = periodicità;
        this.quantità = quantità;
    }

    public String getNome() {
        return nome;
    }

    public int getPeriodicità() {
        return periodicità;
    }

    public double getQuantità() {
        return quantità;
    }

    /**
     *
     * @param s parametro specie
     * @return il fabbisogno mensile per un'unità di ogni singola specie;
     */
    public static double getFabbisogno(Specie s){
        double fabbisogno = ((30/s.getPeriodicità()) * s.getQuantità());
        return fabbisogno;
    }

}
