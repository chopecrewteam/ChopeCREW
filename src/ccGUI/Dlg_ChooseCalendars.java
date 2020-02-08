// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JList;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class Dlg_ChooseCalendars extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private static Dlg_ChooseCalendars dialog;
    private static String[] values;
    private JList<String> list;
    
    static {
        Dlg_ChooseCalendars.values = null;
    }
    
    public static String[] showDialog(final Component frameComp, final Component locationComp, final String labelText, final String title, final String[] possibleValues, final int[] selection, final String longValue) {
        final Frame frame = JOptionPane.getFrameForComponent(frameComp);
        (Dlg_ChooseCalendars.dialog = new Dlg_ChooseCalendars(frame, locationComp, labelText, title, possibleValues, selection, longValue)).setVisible(true);
        return Dlg_ChooseCalendars.values;
    }
    
    private void setSelection(final int[] selection) {
        this.list.setSelectedIndices(selection);
    }
    
    private Dlg_ChooseCalendars(final Frame frame, final Component locationComp, final String labelText, final String title, final String[] data, final int[] selection, final String longValue) {
        super(frame, title, true);
        this.setPreferredSize(new Dimension(400, 360));
        final Container contentPane = this.getContentPane();
        final Component verticalStrut_North = Box.createVerticalStrut(20);
        this.getContentPane().add(verticalStrut_North, "North");
        final Component horizontalStrut_West = Box.createHorizontalStrut(20);
        this.getContentPane().add(horizontalStrut_West, "West");
        final Component horizontalStrut_East = Box.createHorizontalStrut(20);
        this.getContentPane().add(horizontalStrut_East, "East");
        (this.list = new JList<String>()).setListData(data);
        this.list.setSelectionMode(2);
        this.list.setVisibleRowCount(-1);
        this.list.setFont(new Font("Tahoma", 0, 16));
        this.list.setFixedCellHeight(24);
        final JPanel pnl_Central = new JPanel();
        pnl_Central.setLayout(new BoxLayout(pnl_Central, 3));
        pnl_Central.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        final JPanel pnl_Central_Contenu = new JPanel();
        pnl_Central_Contenu.setLayout(new BoxLayout(pnl_Central_Contenu, 3));
        pnl_Central.add(pnl_Central_Contenu);
        final JLabel lbl_SelectAgendas = new JLabel("S\u00e9lectionner le(s) agenda(s) destinataire(s)");
        lbl_SelectAgendas.setAlignmentX(0.5f);
        lbl_SelectAgendas.setLabelFor(this.list);
        lbl_SelectAgendas.setFont(new Font("Tahoma", 0, 16));
        pnl_Central_Contenu.add(lbl_SelectAgendas);
        final Component verticalStrut_Central = Box.createVerticalStrut(20);
        pnl_Central_Contenu.add(verticalStrut_Central);
        final JScrollPane listScroller = new JScrollPane(this.list);
        listScroller.setPreferredSize(new Dimension(360, 360));
        pnl_Central_Contenu.add(listScroller);
        contentPane.add(pnl_Central, "Center");
        final JPanel pnl_Buttons = new JPanel();
        pnl_Buttons.setLayout(new BoxLayout(pnl_Buttons, 3));
        pnl_Buttons.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        this.getContentPane().add(pnl_Buttons, "South");
        final Component verticalStrut_Buttons_1 = Box.createVerticalStrut(20);
        pnl_Buttons.add(verticalStrut_Buttons_1);
        final JPanel panel = new JPanel();
        pnl_Buttons.add(panel);
        panel.setLayout(new BoxLayout(panel, 0));
        final JButton btn_Annuler = new JButton("Annuler");
        panel.add(btn_Annuler);
        btn_Annuler.setAlignmentX(0.5f);
        btn_Annuler.setMaximumSize(new Dimension(128, 40));
        btn_Annuler.setPreferredSize(new Dimension(104, 40));
        btn_Annuler.setMargin(new Insets(2, 2, 2, 2));
        btn_Annuler.setActionCommand("Annuler");
        btn_Annuler.addActionListener(this);
        btn_Annuler.setFont(new Font("Tahoma", 0, 16));
        final Component horizontalStrut_Buttons_1 = Box.createHorizontalStrut(20);
        panel.add(horizontalStrut_Buttons_1);
        final JButton btn_Valider = new JButton("Valider");
        panel.add(btn_Valider);
        btn_Valider.setAlignmentX(0.5f);
        btn_Valider.setMaximumSize(new Dimension(128, 40));
        btn_Valider.setPreferredSize(new Dimension(104, 40));
        btn_Valider.setMargin(new Insets(2, 2, 2, 2));
        btn_Valider.setActionCommand("Valider");
        btn_Valider.addActionListener(this);
        btn_Valider.setFont(new Font("Tahoma", 0, 16));
        final Component verticalStrut_Buttons_2 = Box.createVerticalStrut(20);
        pnl_Buttons.add(verticalStrut_Buttons_2);
        this.getRootPane().setDefaultButton(btn_Valider);
        this.setSelection(selection);
        this.pack();
        this.setLocationRelativeTo(locationComp);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("Valider")) {
            Dlg_ChooseCalendars.values = this.list.getSelectedValuesList().toArray(new String[this.list.getSelectedValuesList().size()]);
        }
        else if (e.getActionCommand().equals("Annuler")) {
            Dlg_ChooseCalendars.values = null;
        }
        this.dispose();
    }
}
