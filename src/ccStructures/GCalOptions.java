// 
// Decompiled by Procyon v0.5.36
// 

package ccStructures;

public class GCalOptions
{
    private String name;
    private boolean entetes;
    private boolean troncons;
    private boolean sol;
    private boolean conges;
    private boolean repos;
    private boolean absences;
    private boolean dispersions;
    
    public GCalOptions() {
    }
    
    public GCalOptions(final String name, final boolean entetes, final boolean troncons, final boolean sol, final boolean conges, final boolean repos, final boolean absences, final boolean dispersions) {
        this.name = name;
        this.entetes = entetes;
        this.troncons = troncons;
        this.sol = sol;
        this.conges = conges;
        this.repos = repos;
        this.absences = absences;
        this.dispersions = dispersions;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public boolean isEntetes() {
        return this.entetes;
    }
    
    public void setEntetes(final boolean entetes) {
        this.entetes = entetes;
    }
    
    public boolean isTroncons() {
        return this.troncons;
    }
    
    public void setTroncons(final boolean troncons) {
        this.troncons = troncons;
    }
    
    public boolean isSol() {
        return this.sol;
    }
    
    public void setSol(final boolean sol) {
        this.sol = sol;
    }
    
    public boolean isConges() {
        return this.conges;
    }
    
    public void setConges(final boolean conges) {
        this.conges = conges;
    }
    
    public boolean isRepos() {
        return this.repos;
    }
    
    public void setRepos(final boolean repos) {
        this.repos = repos;
    }
    
    public boolean isAbsences() {
        return this.absences;
    }
    
    public void setAbsences(final boolean absences) {
        this.absences = absences;
    }
    
    public boolean isDispersions() {
        return this.dispersions;
    }
    
    public void setDispersions(final boolean dispersions) {
        this.dispersions = dispersions;
    }
}
