// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI.RosterTable;

import java.util.Iterator;
import ccStructures.ActiviteSol;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import ccEngine.AnalyseCrew;
import ccStructures.CrewPlanningEvent;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class RosterTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;
    public ArrayList<CrewPlanningEvent> events;
    private String[] columnNames;
    
    public RosterTableModel() {
        this.columnNames = new String[] { "Date", "Activit\u00e9" };
        this.events = new ArrayList<CrewPlanningEvent>();
    }
    
    public void loadRosterTable(final AnalyseCrew analyse) {
        this.events.clear();
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                final Rotation rotation = (Rotation)obj;
                this.events.add(rotation);
                if (!rotation.isExpanded()) {
                    continue;
                }
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        this.events.add(troncon);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                final ActiviteSol activiteSol = (ActiviteSol)obj;
                this.events.add(activiteSol);
            }
        }
        this.fireTableDataChanged();
    }
    
    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }
    
    @Override
    public int getRowCount() {
        return this.events.size();
    }
    
    @Override
    public Object getValueAt(final int row, final int col) {
        return this.events.get(row);
    }
    
    @Override
    public Class<?> getColumnClass(final int c) {
        return this.getValueAt(0, c).getClass();
    }
}
