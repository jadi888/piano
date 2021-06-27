package irrigazione_giardino.unibs.it;
import mylib.*;

import java.util.HashMap;

public class Servizi {

    public static final String INPUT_NOME = "Inserisci il nome della specie che vuoi aggiungere: ";
    public static final String INPUT_PERIODICITA = "Inserisci periodicità annaffiatura: ";
    public static final String INPUT_QTA = "Inserisci il fabbisogno ad ogni annaffiatura: ";


    /**
     *
     * @return aggiunta di specie da parte dell'utente;
     */
    public static Specie aggiungiSpecie(){
        String nome = InputDati.leggiStringa(INPUT_NOME);
        int periodicità = 0;
        do{
            String maxPeriodicità = "La periodicità deve essere di massimo 30 giorni!";
            periodicità = InputDati.leggiIntero(INPUT_PERIODICITA);
        }while (30 < periodicità);
        double quantità = InputDati.leggiDouble(INPUT_QTA);

        return new Specie(nome, periodicità, quantità);
    }


    /**
     *
     * @return
     */
    public static HashMap<Specie, Integer> addEsemplare(){
        HashMap<Specie, Integer> composizione = new HashMap<>();

        for(Specie specie : Utility.listaSpecie){
            composizione.put(specie, InputDati.leggiIntero("Inserisci il numero di esemplari per la specie: "  + specie.getNome()+ " " ));
        }
        return composizione;
    }


}
