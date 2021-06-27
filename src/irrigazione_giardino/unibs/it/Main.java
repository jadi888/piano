package irrigazione_giardino.unibs.it;

import mylib.MyMenu;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static final String[] MENU_OPTIONS = {
            "Aggiungi nuova specie.  ",
            "Aggiungi esemplari per ogni specie presente nel giardino. ",
            "Visualizza il fabbisogno mensile di acqua per il giardino. ",
            "Aggiungere un nuovo giardino. ",
            "Visualizzare elenco giardini:"
    };

    public static void main(String[] args) throws IOException {

            int scelta;
            MyMenu menu = new MyMenu("Scegli un'opzione dal menu?", MENU_OPTIONS);

            do {
                scelta = menu.scegli();
                switch (scelta) {
                    case 1 -> Utility.addSpecie();
                    case 2 -> Utility.aggiungiComposizione();
                    case 3 -> Utility.stampaFabbisogno();
                    case 4 -> Utility.nuovoGiardino();
                    case 5 -> Utility.stampaGiardini();


                }
            }
            while (scelta != 0) ;

    }
}
