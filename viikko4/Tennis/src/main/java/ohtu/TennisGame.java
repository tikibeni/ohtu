package ohtu;

public class TennisGame {
    
    private int player1score = 0;
    private int player2score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1")) {
            player1score += 1;
        } else {
            player2score += 1;
        }
    }

    public String getScore() {
        String score = "";
        int temporaryScore = 0;
        
        if (player1score == player2score) {
            score = tasapistetilanne(score);
        } else if (player1score >= 4 || player2score >= 4) {
            score = normaalitilanne(score);            
        } else {
            score = kaksiYksiTilanne(score, temporaryScore);
        }
        
        return score;
    }
    
    private String tasapistetilanne(String score) {
        switch (player1score) {
            case 0:
                score = "Love-All";
                break;
            case 1:
                score = "Fifteen-All";
                break;
            case 2:
                score = "Thirty-All";
                break;
            case 3:
                score = "Forty-All";
                break;
            default:
                score = "Deuce";
                break;
        }
        
        return score;
    }
    
    private String normaalitilanne(String score) {        
        int pisteErotus = player1score - player2score;
        if (pisteErotus == 1) {
            score = "Advantage player1";
        }
        else if (pisteErotus == -1) {
            score = "Advantage player2";
        }
        else if (pisteErotus >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
            
        return score;
    }
    
    private String kaksiYksiTilanne(String score, int temporaryScore) {
        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                temporaryScore = player1score;
            } else { 
                score += "-"; 
                temporaryScore = player2score;
            }
            switch(temporaryScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
                }
            }
        
        return score;
    }
}