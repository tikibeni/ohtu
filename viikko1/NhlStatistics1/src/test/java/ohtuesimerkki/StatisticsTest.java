package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            
            return players;
        }                    
    };
            
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void findExistingPlayer() {
        Player p = stats.search("Semenko");
        
        assertEquals(4, p.getGoals());
    }
    
    @Test
    public void nonExistingPlayerReturnsNull() {
        Player p = stats.search("Tikibeni");
        
        assertEquals(null, p);
    }
    
    @Test
    public void certainTeamContainsTrueAmount() {
        List<Player> team = stats.team("EDM");
        
        assertEquals(3, team.size());
    }
    
    @Test
    public void returnsCorrectTop3() {
        List<Player> topScorers = stats.topScorers(3);
        
        assertEquals("Yzerman", topScorers.get(2).getName());
    }
    
    
}
