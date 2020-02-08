// 
// Decompiled by Procyon v0.5.36
// 

package ccStructures;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class Rotation extends CrewPlanningEvent implements Comparable<Object>, Serializable
{
    private static final long serialVersionUID = 1L;
    public int numRot;
    public String departUTC;
    public String arriveeUTC;
    public String nbCDB;
    public String nbOPL;
    public String reengagementUTC;
    public String presentationUTC;
    public String tempsVolTotal;
    public String tempsVolMep;
    public String tempsAbsence;
    public String dureePac;
    public String dureeRpc;
    public boolean isCourrierCroise;
    public boolean isRotationACheval;
    public boolean isOD;
    public Date reengagementUTCD;
    public Date presentationUTCD;
    public String particularite;
    public String sab;
    public int nON;
    public ArrayList<String> listDecouchers;
    public long debTAp;
    public long debTAr;
    public long finTAp;
    public long finTAr;
    public long DEPp;
    public long DEPr;
    public long ARRp;
    public long ARRr;
    public double NJEp;
    public double NJEr;
    public double TAp;
    public double TAr;
    public double HCAp;
    public double HCAr;
    public double SH1p;
    public double SH1r;
    public double H2p;
    public double H2r;
    public double SH1Rp;
    public double SH1Rr;
    public double H2Rp;
    public double H2Rr;
    public double STVp;
    public double STVr;
    public double STVp1;
    public double STVr1;
    public double STVp2;
    public double STVr2;
    public boolean isRotAChevalP;
    public boolean isRotAChevalR;
    public String IKVallerP;
    public String IKVretourP;
    public String IKVallerR;
    public String IKVretourR;
    public ArrayList<ServiceVol> listSV;
    public ArrayList<Dest_Reco> listDestReco;
    public ArrayList<Deg_Reco> listDegReco;
    public ArrayList<Peq> listPeqRot;
    
    public Rotation() {
        this.listSV = new ArrayList<ServiceVol>();
        this.listDestReco = new ArrayList<Dest_Reco>();
        this.listDegReco = new ArrayList<Deg_Reco>();
        this.listPeqRot = new ArrayList<Peq>();
        this.listDecouchers = new ArrayList<String>();
    }
    
    @Override
    public int compareTo(final Object other) {
        Date otherDepart = new Date();
        if (other instanceof ActiviteSol) {
            otherDepart = ((ActiviteSol)other).debutUTCD;
        }
        else if (other instanceof Rotation) {
            otherDepart = ((Rotation)other).debutUTCD;
        }
        if (otherDepart.after(this.debutUTCD)) {
            return -1;
        }
        if (otherDepart.equals(this.debutUTCD)) {
            return 0;
        }
        return 1;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Rotation) {
            final Rotation other = (Rotation)obj;
            return this.debutUTCD.equals(other.debutUTCD) || this.finUTCD.equals(other.finUTCD);
        }
        return false;
    }
}
