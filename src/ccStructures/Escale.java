// 
// Decompiled by Procyon v0.5.36
// 

package ccStructures;

import java.io.Serializable;

public class Escale implements Serializable
{
    private static final long serialVersionUID = 1L;
    public String code;
    public String nom;
    public String pays;
    public String tz;
    public String zone;
    public String bigramme;
    public double compteurFR;
    public int compteurNE;
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Escale) {
            final Escale other = (Escale)obj;
            return this.code.equals(other.code);
        }
        return false;
    }
}
