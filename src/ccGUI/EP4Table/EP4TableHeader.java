// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI.EP4Table;

import javax.swing.JTable;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.table.TableColumnModel;
import javax.swing.table.JTableHeader;

public class EP4TableHeader extends JTableHeader
{
    private static final long serialVersionUID = 1L;
    private String[] allToolTipsText;
    
    public EP4TableHeader(final TableColumnModel tcm) {
        super(tcm);
        this.setFont(new Font("Tahoma", 0, 14));
    }
    
    @Override
    public String getToolTipText(final MouseEvent e) {
        final Point p = e.getPoint();
        final int viewColumnIndex = this.columnAtPoint(p);
        final JTable jt = this.getTable();
        final int modelColumnIndex = jt.convertColumnIndexToModel(viewColumnIndex);
        if (this.allToolTipsText[modelColumnIndex].equals("")) {
            return super.getToolTipText(e);
        }
        return this.allToolTipsText[modelColumnIndex];
    }
    
    public void setToolTipsText(final String[] myToolTipsText) {
        this.allToolTipsText = myToolTipsText;
    }
}
