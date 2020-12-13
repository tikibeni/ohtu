package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperiSakset {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Tekoaly tekoaly = new Tekoaly();

    @Override
    protected String toisenSiirto() {
        String siirto = tekoaly.annaSiirto();
        
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }
}