package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {
    private Matcher ehto;
    
    public Not(Matcher ehto) {
        this.ehto = ehto;
    }

    @Override
    public boolean matches(Player p) {
        return !(ehto.matches(p));
    }
}