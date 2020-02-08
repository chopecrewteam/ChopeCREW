// 
// Decompiled by Procyon v0.5.36
// 

package ccStructures;

import java.io.Serializable;

public class Deg_Reco implements Serializable
{
    private static final long serialVersionUID = 1L;
    public String deg;
    public String dest;
    public String categorie;
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Deg_Reco) {
            final Deg_Reco other = (Deg_Reco)obj;
            return this.deg.equals(other.deg) && this.dest.equals(other.dest) && this.categorie.equals(other.categorie);
        }
        return false;
    }
}
