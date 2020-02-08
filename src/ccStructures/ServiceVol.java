// 
// Decompiled by Procyon v0.5.36
// 

package ccStructures;

import java.util.ArrayList;
import java.io.Serializable;

public class ServiceVol implements Serializable
{
    private static final long serialVersionUID = 1L;
    public int numRot;
    public int numSV;
    public String dateSV;
    public String TVSV;
    public String TSV;
    public String repos;
    public boolean isDecoucher;
    public long debTSVp;
    public long debTSVr;
    public long finTSVp;
    public long finTSVr;
    public double NETAPEp;
    public double NETAPEr;
    public double TMEp;
    public double TMEr;
    public double CMTp;
    public double CMTr;
    public double STVp;
    public double STVr;
    public double SHV100p;
    public double SHV100r;
    public double SHV100Rp;
    public double SHV100Rr;
    public double SMEPp;
    public double SMEPr;
    public double HCVp;
    public double HCVr;
    public double HCVRp;
    public double HCVRr;
    public double TSVp;
    public double TSVr;
    public double TSVNp;
    public double TSVNr;
    public double HCTp;
    public double HCTr;
    public double H1p;
    public double H1r;
    public double H1Rp;
    public double H1Rr;
    public boolean isDCdepMidi;
    public boolean isDCdepSoir;
    public boolean isDCarrMidi;
    public boolean isDCarrSoir;
    public ArrayList<Troncon> listTroncon;
    
    public ServiceVol() {
        this.listTroncon = new ArrayList<Troncon>();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ServiceVol) {
            final ServiceVol other = (ServiceVol)obj;
            return this.dateSV.equals(other.dateSV) && this.TVSV.equals(other.TVSV) && this.TSV.equals(other.TSV);
        }
        return false;
    }
}
