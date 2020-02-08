// 
// Decompiled by Procyon v0.5.36
// 

package ccStructures;

import java.util.ArrayList;
import java.io.Serializable;

public class Troncon extends CrewPlanningEvent implements Serializable
{
    private static final long serialVersionUID = 1L;
    public int numRot;
    public int numSV;
    public int numTroncon;
    public String numVol;
    public String from;
    public String to;
    public String fromClair;
    public String toClair;
    public String departUTC;
    public String arriveeUTC;
    public String lagFrom;
    public String lagTo;
    public int LAGfromMillis;
    public int LAGtoMillis;
    public String TVT;
    public boolean isMep;
    public String tempsEscale;
    public String typeAvion;
    public String versionExploitation;
    public ArrayList<Peq> listPeqTroncon;
    public ArrayList<Presta> listPresta;
    public ArrayList<Dest_Reco> listRecoDest;
    public ArrayList<Deg_Reco> listRecoDeg;
    public Indem indemFrom;
    public Indem indemTo;
    public boolean isDecoucher;
    public String repos;
    public String hotel;
    public long DEPp;
    public long DEPr;
    public long ARRp;
    public long ARRr;
    public double DEBp;
    public double DEBr;
    public double FINp;
    public double FINr;
    public double TVp;
    public double TVr;
    public double TVref;
    public double MEPp;
    public double MEPr;
    public double HV100p;
    public double HV100r;
    public double HV100Rp;
    public double HV100Rr;
    public double TVNp;
    public double TVNr;
    public double HCNp;
    public double HCNr;
    public boolean isTVref;
    public boolean isSaisonEte;
    public boolean isTronconAChevalP;
    public boolean isTronconAChevalR;
    public double TVp1;
    public double TVp2;
    public double TVr1;
    public double TVr2;
    public double HCNp1;
    public double HCNp2;
    public double HCNr1;
    public double HCNr2;
    public int nDecP;
    public int nDecR;
    public boolean isTVreal;
    
    public Troncon() {
        this.listPeqTroncon = new ArrayList<Peq>();
        this.listPresta = new ArrayList<Presta>();
        this.listRecoDeg = new ArrayList<Deg_Reco>();
        this.listRecoDest = new ArrayList<Dest_Reco>();
        this.indemFrom = new Indem();
        this.indemTo = new Indem();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Troncon) {
            final Troncon other = (Troncon)obj;
            return this.from.equals(other.from) && this.to.equals(other.to) && this.departUTC.equals(other.departUTC) && this.numVol.equals(other.numVol);
        }
        return false;
    }
}
