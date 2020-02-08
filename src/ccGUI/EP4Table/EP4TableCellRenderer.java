// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI.EP4Table;

import java.util.Iterator;
import ccStructures.ActiviteSol;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import chopeCrew.ChopeCrew;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

public class EP4TableCellRenderer extends JLabel implements TableCellRenderer
{
    private static final long serialVersionUID = 1L;
    
    EP4TableCellRenderer() {
        this.setHorizontalTextPosition(0);
        this.setHorizontalAlignment(0);
        this.setVerticalTextPosition(3);
        this.setVerticalAlignment(3);
        this.setFont(new Font("Courier New", 0, 16));
        this.setOpaque(true);
        this.setVisible(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        int index = 0;
        int indexSV = 0;
        boolean isPrecedentRotation = false;
    Label_0383:
        for (final Object obj : ChopeCrew.analyse.listCrew) {
            if (obj instanceof Rotation) {
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        if (index == row) {
                            if (troncon.numTroncon == 1 && sv.numSV == 1 && !isPrecedentRotation) {
                                this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(3, 128, 255, 128)));
                            }
                            if (troncon.numTroncon == sv.listTroncon.size() && sv.numSV == rotation.listSV.size()) {
                                this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(3, 128, 255, 128)));
                            }
                            if (indexSV % 2 == 0) {
                                this.setBackground(new Color(185, 217, 255, 64));
                                break Label_0383;
                            }
                            if (indexSV % 2 == 1) {
                                this.setBackground(new Color(185, 217, 255, 32));
                                break Label_0383;
                            }
                            break Label_0383;
                        }
                        else {
                            ++index;
                        }
                    }
                    ++indexSV;
                }
                isPrecedentRotation = true;
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                if (index == row) {
                    this.setBackground(new Color(185, 217, 255, 8));
                    break;
                }
                ++index;
                isPrecedentRotation = false;
            }
        }
        if (table.getColumnName(column).equals("MEP") && value != null) {
            this.setForeground(Color.BLUE);
        }
        if (row == table.getSelectedRow()) {
            this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(130, 210, 255)));
            this.setBackground(new Color(185, 217, 255, 128));
        }
        if (hasFocus) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        }
        boolean isPreviousRowSameDate = false;
        if (table.getColumnName(column).equals("Date") && row > 0) {
            final String s = (String)table.getValueAt(row - 1, column);
            if (s.equals(value)) {
                isPreviousRowSameDate = true;
            }
        }
        if (value != null && !value.equals("0") && !value.equals("0.0") && !isPreviousRowSameDate) {
            this.setText(value.toString());
        }
        return this;
    }
}
