// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI.EP4Table;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Iterator;
import ccGUI.Dlg_EditionActiviteSol;
import ccStructures.ActiviteSol;
import java.awt.Component;
import java.awt.Frame;
import ccGUI.Dlg_EditionTroncon;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import chopeCrew.ChopeCrew;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.TableCellRenderer;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.JTable;

public class EP4Table extends JTable
{
    private static final long serialVersionUID = 1L;
    
    public EP4Table() {
        this.addMouseListener(new MyMouseAdapter());
        this.addKeyListener(new MyKeyAdapter());
    }
    
    @Override
    public TableCellRenderer getCellRenderer(final int row, final int column) {
        return new EP4TableCellRenderer();
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            Label_0262: {
                if (e.getClickCount() == 2 && e.getButton() == 1) {
                    final int ligne = EP4Table.this.rowAtPoint(e.getPoint());
                    int index = 0;
                    for (final Object obj : ChopeCrew.analyse.listCrew) {
                        if (obj instanceof Rotation) {
                            final Rotation rotation = (Rotation)obj;
                            for (final ServiceVol sv : rotation.listSV) {
                                for (final Troncon troncon : sv.listTroncon) {
                                    if (index == ligne) {
                                        final Dlg_EditionTroncon dlg_EditionTroncon = new Dlg_EditionTroncon(troncon, EP4Table.this, ChopeCrew.mf);
                                        dlg_EditionTroncon.setLocationRelativeTo(EP4Table.this);
                                        dlg_EditionTroncon.setVisible(true);
                                        break Label_0262;
                                    }
                                    ++index;
                                }
                            }
                        }
                        else {
                            if (!(obj instanceof ActiviteSol)) {
                                continue;
                            }
                            final ActiviteSol act = (ActiviteSol)obj;
                            if (!act.isCredite) {
                                continue;
                            }
                            if (index == ligne) {
                                final Dlg_EditionActiviteSol dlg_EditionActiviteSol = new Dlg_EditionActiviteSol(act, EP4Table.this, ChopeCrew.mf);
                                dlg_EditionActiviteSol.setLocationRelativeTo(EP4Table.this);
                                dlg_EditionActiviteSol.setVisible(true);
                                break;
                            }
                            ++index;
                        }
                    }
                }
            }
        }
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            Label_0251: {
                if (e.getKeyCode() == 10) {
                    final int ligne = EP4Table.this.getSelectedRow();
                    int index = 0;
                    for (final Object obj : ChopeCrew.analyse.listCrew) {
                        if (obj instanceof Rotation) {
                            final Rotation rotation = (Rotation)obj;
                            for (final ServiceVol sv : rotation.listSV) {
                                for (final Troncon troncon : sv.listTroncon) {
                                    if (index == ligne) {
                                        final Dlg_EditionTroncon dlg_EditionTroncon = new Dlg_EditionTroncon(troncon, EP4Table.this, ChopeCrew.mf);
                                        dlg_EditionTroncon.setLocationRelativeTo(EP4Table.this);
                                        dlg_EditionTroncon.setVisible(true);
                                        break Label_0251;
                                    }
                                    ++index;
                                }
                            }
                        }
                        else {
                            if (!(obj instanceof ActiviteSol)) {
                                continue;
                            }
                            final ActiviteSol act = (ActiviteSol)obj;
                            if (!act.isCredite) {
                                continue;
                            }
                            if (index == ligne) {
                                final Dlg_EditionActiviteSol dlg_EditionActiviteSol = new Dlg_EditionActiviteSol(act, EP4Table.this, ChopeCrew.mf);
                                dlg_EditionActiviteSol.setLocationRelativeTo(EP4Table.this);
                                dlg_EditionActiviteSol.setVisible(true);
                                break;
                            }
                            ++index;
                        }
                    }
                }
            }
        }
    }
}
