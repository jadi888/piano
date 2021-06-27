package irrigazione_giardino.unibs.it;

import mylib.MyMenu;

import javax.swing.*;

public class Main {

    public static final String[] MENU_OPTIONS = {
            "Aggiungi nuova specie.  ",
            "Aggiungi esemplari per ogni specie presente nel giardino. ",
            "Visualizza il fabbisogno mensile di acqua per il giardino. "
    };

    public static void main(String[] args){

            int scelta;
            MyMenu menu = new MyMenu("Scegli un'opzione dal menu?", MENU_OPTIONS);

            do {
                scelta = menu.scegli();
                switch (scelta) {
                    case 1 -> Utility.addSpecie();
                    case 2 -> Utility.aggiungiComposizione();
                    case 3 -> Utility.stampaFabbisogno();
                }
            }
            while (scelta != 0) ;

    }
}
