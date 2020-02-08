// 
// Decompiled by Procyon v0.5.36
// 

package ccStructures;

import java.util.Date;

public class CrewPlanningEvent implements Comparable<Object>
{
    public Date debutUTCD;
    public Date finUTCD;
    public String label;
    protected boolean isExportable;
    protected boolean isExpanded;
    
    public CrewPlanningEvent() {
        this.isExportable = true;
        this.isExpanded = false;
    }
    
    public boolean isExportable() {
        return this.isExportable;
    }
    
    public void setExportable(final boolean exportable) {
        this.isExportable = exportable;
    }
    
    public void switchExportableState() {
        if (this.isExportable()) {
            this.setExportable(false);
        }
        else {
            this.setExportable(true);
        }
    }
    
    public boolean isExpanded() {
        return this.isExpanded;
    }
    
    public void setExpanded(final boolean expanded) {
        this.isExpanded = expanded;
    }
    
    public void switchExpandedState() {
        if (this.isExpanded()) {
            this.setExpanded(false);
        }
        else {
            this.setExpanded(true);
        }
    }
    
    @Override
    public int compareTo(final Object other) {
        if (((CrewPlanningEvent)other).debutUTCD.after(this.debutUTCD)) {
            return -1;
        }
        if (((CrewPlanningEvent)other).debutUTCD.equals(this.debutUTCD)) {
            return 0;
        }
        return 1;
    }
    
    @Override
    public boolean equals(final Object obj) {
        return obj == this || (this.debutUTCD == ((CrewPlanningEvent)obj).debutUTCD && this.label == ((CrewPlanningEvent)obj).label && this.finUTCD == ((CrewPlanningEvent)obj).finUTCD);
    }
}
