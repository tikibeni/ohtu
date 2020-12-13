package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KiviPaperiSakset {
    private static final Scanner scanner = new Scanner(System.in);
    private static Tuomari tuomari;
    private static String ekanSiirto;
    private static String tokanSiirto;
    
    public void pelaa() {
        tuomari = new Tuomari();
        ekanSiirto = ensimmaisenSiirto();
        tokanSiirto = toisenSiirto();
        
        peli();
        
        System.out.println("");
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }    
    
    protected String ensimmaisenSiirto() {
        System.out.println("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }
    
    abstract protected String toisenSiirto();
    
    protected void peli() {
        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();
            
            ekanSiirto = ensimmaisenSiirto();
            tokanSiirto = toisenSiirto();
        }
    }
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}