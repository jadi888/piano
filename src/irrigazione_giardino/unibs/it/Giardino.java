package irrigazione_giardino.unibs.it;

import mylib.InputDati;
import java.util.HashMap;
import java.util.Map;


public class Giardino {

    private HashMap<Specie, Integer> composizioneGiardino;
    private double fabbisognoMensile;


    /*public Giardino addEsemplare(Specie s, int esemplari){
        this.composizioneGiardino.put(s, esemplari);
        return this;
    }*/


    public Giardino(HashMap<Specie, Integer> comp){
        this.composizioneGiardino = comp;
        for(Map.Entry<Specie, Integer> entry : composizioneGiardino.entrySet()){
            this.fabbisognoMensile += Specie.getFabbisogno(entry.getKey())*entry.getValue();
        }
    }

    public double getFabbisognoMensile(){
        return fabbisognoMensile;
    }

    public HashMap<Specie, Integer> getComposizioneGiardino(){
        return composizioneGiardino;
    }

}
