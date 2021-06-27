package irrigazione_giardino.unibs.it;

import mylib.InputDati;
import mylib.*;

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
    public static ArrayList<Giardino> listaGiardini = new ArrayList<>();



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
     * Se l'utente ha inserito altre specie, allora aggiungo al giardino anche quelli, altrimenti gli permetto
     * di lavorare solo su quelli di default inseriti da me.
     */
    public static void aggiungiComposizione(){

        if(listaSpecieUtente.isEmpty()) {
            listaSpecie.addAll(listaSpecieDef());
            garden = new Giardino(Servizi.addEsemplare());
            listaGiardini.add(garden);
        }
            else{
                listaSpecie.addAll(listaSpecieDef());
                listaSpecie.addAll(listaSpecieUtente);
                garden = new Giardino(Servizi.addEsemplare());
                listaGiardini.add(garden);
            }
        }


    public static void stampaFabbisogno() throws IOException {
        if (garden == null) {
            System.out.println("Inserisci prima la quantità di esemplari per ogni specie: ");
            aggiungiComposizione();


        } else {
            System.out.println("Il fabbisogno mensile del giardino è di: " + garden.getFabbisognoMensile() + " litri di acqua");

        }
    }




     public static void stampaGiardini(){
        MyMenu scelta = null;
        if(listaGiardini.isEmpty()){
            scelta = new MyMenu("Non ci sono giardini al momento, vuoi aggiungerne uno?", new String[]{"SI", "NO"});
        while(scelta.scegli()==1){
            Utility.aggiungiComposizione();
        }
     }
     else {
            for (Giardino giardino : listaGiardini) {
                int i = 0;
                System.out.println("Il giardino " + i++ + "è composto da: ");
                for (Map.Entry<Specie, Integer> entry : giardino.getComposizioneGiardino().entrySet()) {
                    String nomePianta = entry.getKey().getNome();
                    int nrPiante = entry.getValue();
                    System.out.println(nomePianta + " con  " + nrPiante + " esemplari: ");
                }
            }
        }
    }


    public static void nuovoGiardino() {
        Utility.aggiungiComposizione();

        MyMenu scelta = new MyMenu("Vuoi aggiungere una nuova specie al giardino? ", new String[]{"Si", "No"});
        while (scelta.scegli() == 1) {
            Utility.addSpecie();
        }
    }

    public static void generaPiano() throws IOException {
        BufferedWriter output = null;
        MyMenu scelta;

        if(!listaGiardini.isEmpty()) {
            try {
                File file = new File("Piano_irrigazione.txt");
                output = new BufferedWriter(new FileWriter(file));
                output.flush();
                for(Giardino giardino : listaGiardini){
                    int i=0;
                output.write("Il giardino "+ i++ + " è composto da: ");
                output.flush();
                output.write("\n");
                output.flush();
                if (!giardino.getComposizioneGiardino().isEmpty()) {
                    for (Map.Entry<Specie, Integer> entry : giardino.getComposizioneGiardino().entrySet()) {
                        String nomePianta = entry.getKey().getNome();
                        int nrEsemplari = entry.getValue();
                        double fabbisognoMensilePerPianta = Specie.getFabbisogno(entry.getKey());
                        output.write("\n");
                        output.write(nomePianta + " con " + nrEsemplari + "esemplari presenti con fabbisogno mensile per pianta " + fabbisognoMensilePerPianta);
                        output.write("\n");
                        output.flush();
                    }
                }

                output.write("\n");
                output.flush();
                output.write("Il fabbisogno mensile TOTALE è di: " + giardino.getFabbisognoMensile());
                output.flush();}
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (output != null) {
                    output.close();
                }
            }
        }
        else{
            scelta = new MyMenu("Non è presente ancora nessun giardino di cui generare il piano di irrigazione, vuoi aggiungerne uno? ", new String[]{"SI", "NO"});
            if(scelta.scegli() == 0){
                Utility.nuovoGiardino();
            }
        }
    }
}

