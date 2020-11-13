package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;

    public Player(String name, String nationality, int assists, int goals, int penalties, String team, int games) {
        this.name = name;
        this.nationality = nationality;
        this.assists = assists;
        this.goals = goals;
        this.penalties = penalties;
        this.team = team;
        this.games = games;

    }

    public String getNationality() {
        return this.nationality;
    }

    public String getName() {
        return this.name;
    }

    public int getAssists() {
        return this.assists;
    }

    public String getTeam() {
        return this.team;
    }

    public int getGoals() {
        return this.goals;
    }

    public int getPenalties() {
        return this.penalties;
    }

    public int getGames() {
        return this.games;
    }

    @Override
    public int compareTo(Player p) {
        if (this.goals + this.assists > p.getGoals() + p.getAssists()) {
            return -1;
        } else if (this.goals + this.assists == p.getGoals() + p.getAssists()) {
            return 0;
        } else {
            return 1;
        }
    }
    
    @Override
    public String toString() {
        String alku = String.format("%-20s %-4s %-2s", name, team, goals);
        String keski = String.format("%-2s", assists);

        return alku + " + " + keski + " = " + (goals+assists);
    } 
}