package laskin;

public class Sovelluslogiikka {
 
    private int tulos;
    private Integer edellinenTulos;
 
    public void plus(int luku) {
        edellinenTulos = tulos;
        tulos += luku;
    }
     
    public void miinus(int luku) {
        edellinenTulos = tulos;
        tulos -= luku;
    }
 
    public void nollaa() {
        edellinenTulos = tulos;
        tulos = 0;
    }
    
    public void palauta() {
        tulos = edellinenTulos;
        edellinenTulos = null;
    }
 
    public int tulos() {
        return tulos;
    }
}