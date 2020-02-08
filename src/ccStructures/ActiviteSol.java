// 
// Decompiled by Procyon v0.5.36
// 

package ccStructures;

import java.util.Date;
import java.util.ArrayList;
import java.io.Serializable;

public class ActiviteSol extends CrewPlanningEvent implements Comparable<Object>, Serializable
{
    private static final long serialVersionUID = 1L;
    public String jour;
    public String code;
    public String debut;
    public String fin;
    public String categorie;
    public boolean isH24;
    public boolean isDispo;
    public boolean isCredite;
    public boolean isBlocActivite;
    public BlocActivite bloc;
    public boolean isReserveDeclenchee;
    public String maj;
    public String lieu;
    public String salle;
    public String commentaire;
    public ArrayList<Participant> listParticipant;
    public double HCS;
    public double HCRS;
    public String IKVar;
    public boolean isHCreal;
    public boolean isIKVreal;
    
    public ActiviteSol() {
        this.listParticipant = new ArrayList<Participant>();
    }
    
    @Override
    public int compareTo(final Object other) {
        Date otherDebut = new Date();
        if (other instanceof ActiviteSol) {
            otherDebut = ((ActiviteSol)other).debutUTCD;
        }
        else if (other instanceof Rotation) {
            otherDebut = ((Rotation)other).debutUTCD;
        }
        if (otherDebut.after(this.debutUTCD)) {
            return -1;
        }
        if (otherDebut.before(this.debutUTCD)) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ActiviteSol) {
            final ActiviteSol other = (ActiviteSol)obj;
            return this.debut.equals(other.debut) && this.fin.equals(other.fin) && this.code.equals(other.code) && this.label.equals(other.label);
        }
        return false;
    }
}
