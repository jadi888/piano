package irrigazione_giardino.unibs.it;

import mylib.InputDati;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Utility {


     // è la lista generale in cui metto insieme specie di default e quelle inserite dall'utente;
    public static ArrayList<Specie> listaSpecie = new ArrayList<>();

    /**
     * mi serve questo array per poter permettere all'utente di inserire gli esemplari per le specie gia' esistenti anche se
     * non ne ha inseriti nuove.
     */
    public static ArrayList<Specie> listaSpecieUtente = new ArrayList<>();
    private static Giardino garden;



    public static void addSpecie(){
        listaSpecieUtente.add(Servizi.aggiungiSpecie());
    }

   public static ArrayList<Specie> listaSpecieDef(){
        ArrayList<Specie> specieDef = new ArrayList<>();
       specieDef.add(new Specie("Piante Tropicali", 2, 60));
       specieDef.add(new Specie("Latifoglie", 2, 70));
       specieDef.add(new Specie("Coniferi", 30, 40));
       specieDef.add(new Specie("Arbusti", 1, 40));
       specieDef.add(new Specie("Siepi", 1, 4));
       specieDef.add(new Specie("Alberi da frutto", 2, 65));
       return specieDef;
   }


    /**
     * Se l'utente ha inserite altre specie, allora aggiungo al giardino anche quelli, altrimenti gli permetto
     * di lavorare solo su quelli di default inseriti da me.
     */
    public static void aggiungiComposizione(){

        if(listaSpecieUtente.isEmpty()) {
            listaSpecie.addAll(listaSpecieDef());
            garden = new Giardino(Servizi.addEsemplare());
        }
            else{
                listaSpecie.addAll(listaSpecieDef());
                listaSpecie.addAll(listaSpecieUtente);
                garden = new Giardino(Servizi.addEsemplare());
            }
        }


    public static void stampaFabbisogno() {
        if (garden == null) {
            System.out.println("Inserisci prima la quantità di esemplari per ogni specie: ");
            aggiungiComposizione();
        } else {
            System.out.println("Il fabbisogno mensile del giardino è di: " + garden.getFabbisognoMensile() + " litri di acqua");
        }
    }

    public static void writer() throws IOException {
        BufferedWriter output = null;

        try {
            File file = new File("Piano_irrigazione.txt");
            output = new BufferedWriter(new FileWriter(file));
            output.write("Il giardino è composto da: ");
            output.write("\n");
            for(Map.Entry<Specie, Integer> entry : garden.getComposizioneGiardino().entrySet()){
                String nomePianta = entry.getKey().getNome();
                int nrEsemplari = entry.getValue();
                double fabbisognoMensilePerPianta = Specie.getFabbisogno(entry.getKey());
                output.write(nomePianta + " con " + nrEsemplari + " presenti con fabbisogno mensile per pianta" + fabbisognoMensilePerPianta);
            }

            output.write("\n");
            output.write("Il fabbisogno mensile TOTALE è di: " + garden.getFabbisognoMensile());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                output.close();
            }
        }

     }
}

