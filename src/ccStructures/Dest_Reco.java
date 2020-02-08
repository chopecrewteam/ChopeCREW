// 
// Decompiled by Procyon v0.5.36
// 

package ccStructures;

import java.io.Serializable;

public class Dest_Reco implements Serializable
{
    private static final long serialVersionUID = 1L;
    public String dest;
    public String categorie;
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Dest_Reco) {
            final Dest_Reco other = (Dest_Reco)obj;
            return this.dest.equals(other.dest) && this.categorie.equals(other.categorie);
        }
        return false;
    }
}
