package ccExport;

// class contenant un nom d'agenda et les elements que l'on doit y envoyer
public class GCalOptions {
	
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
	
	public GCalOptions(String name, boolean entetes, boolean troncons, boolean sol, boolean conges, boolean repos, boolean absences, boolean dispersions) {
		this.name = name;
		this.entetes = entetes;
		this.troncons = troncons;
		this.sol = sol;
		this.conges = conges;
		this.repos = repos;
		this.absences = absences;
		this.dispersions = dispersions;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the entetes
	 */
	public boolean isEntetes() {
		return entetes;
	}
	
	/**
	 * @param entetes
	 *            the entetes to set
	 */
	public void setEntetes(boolean entetes) {
		this.entetes = entetes;
	}
	
	/**
	 * @return the troncons
	 */
	public boolean isTroncons() {
		return troncons;
	}
	
	/**
	 * @param troncons
	 *            the troncons to set
	 */
	public void setTroncons(boolean troncons) {
		this.troncons = troncons;
	}
	
	/**
	 * @return the sol
	 */
	public boolean isSol() {
		return sol;
	}
	
	/**
	 * @param sol
	 *            the sol to set
	 */
	public void setSol(boolean sol) {
		this.sol = sol;
	}
	
	/**
	 * @return the conges
	 */
	public boolean isConges() {
		return conges;
	}
	
	/**
	 * @param conges
	 *            the conges to set
	 */
	public void setConges(boolean conges) {
		this.conges = conges;
	}
	
	/**
	 * @return the repos
	 */
	public boolean isRepos() {
		return repos;
	}
	
	/**
	 * @param repos
	 *            the repos to set
	 */
	public void setRepos(boolean repos) {
		this.repos = repos;
	}
	
	
	/**
	 * @return the absences
	 */
	public boolean isAbsences() {
		return absences;
	}
	
	/**
	 * @param absences
	 *            the absences to set
	 */
	public void setAbsences(boolean absences) {
		this.absences = absences;
	}
	
	
	/**
	 * @return the dispersions
	 */
	public boolean isDispersions() {
		return dispersions;
	}
	
	/**
	 * @param dispersions
	 *            the dispersions to set
	 */
	public void setDispersions(boolean dispersions) {
		this.dispersions = dispersions;
	}
}
