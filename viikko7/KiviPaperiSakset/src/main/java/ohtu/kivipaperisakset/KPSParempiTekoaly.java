package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSParempiTekoaly extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TekoalyParannettu tekoaly = new TekoalyParannettu(20);

    @Override
    protected String toisenSiirto() {
        String siirto = tekoaly.annaSiirto();
        
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }    
}