package statistics;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {
    private Matcher m;    
    
    public QueryBuilder playsIn(String team) {
        if (m != null) {
            this.m = new And(m, new PlaysIn(team));
            return this;
        }
        
        this.m = new PlaysIn(team);
        return this;
    }

    public QueryBuilder hasAtLeast(int amount, String category) {
        if (m != null) {
            this.m = new And(m, new HasAtLeast(amount, category));
            return this;
        }
        
        this.m = new HasAtLeast(amount, category);
        return this;
    }
    
    public QueryBuilder hasFewerThan(int amount, String category) {
        if (m != null) {
            this.m = new And(m, new HasFewerThan(amount, category));
            return this;
        }
        
        this.m = new HasFewerThan(amount, category);
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.m = new Or(matchers);
        return this;
    }
    
    public Matcher build() {
        if (m == null) {
            return new All();
        } 
        
        Matcher temp = m;
        m = null;
                  
        return temp;
    }
}