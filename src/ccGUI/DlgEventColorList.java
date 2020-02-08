// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.Color;
import ccUtils.Utils;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.ListModel;
import ccUtils.Constantes;
import javax.swing.ListCellRenderer;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

public class DlgEventColorList extends JDialog implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private JPanel pnl_Content;
    private JPanel pnl_Buttons;
    private JPanel panel;
    private JButton btn_Valider;
    private JButton btn_Cancel;
    public String colorId;
    private JList<String> list;
    private DefaultListModel<String> model;
    private Component horizontalStrut_West;
    private Component horizontalStrut_East;
    private Component verticalStrut_North;
    private Component verticalStrut_Buttons_1;
    private Component verticalStrut_Buttons_2;
    private Component horizontalStrut_Buttons_1;
    private Component horizontalStrut_Buttons_2;
    private Component horizontalStrut_Buttons_3;
    
    public DlgEventColorList(final Component parent, final String colorId) {
        this.pnl_Content = new JPanel();
        this.setBounds(0, 0, 304, 192);
        this.setModal(true);
        this.colorId = colorId;
        this.initComponents();
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    private void initComponents() {
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.verticalStrut_North = Box.createVerticalStrut(20);
        this.getContentPane().add(this.verticalStrut_North, "North");
        this.horizontalStrut_West = Box.createHorizontalStrut(20);
        this.getContentPane().add(this.horizontalStrut_West, "West");
        this.horizontalStrut_East = Box.createHorizontalStrut(20);
        this.getContentPane().add(this.horizontalStrut_East, "East");
        this.pnl_Content.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.pnl_Content.setLayout(new GridLayout(0, 1, 0, 0));
        this.getContentPane().add(this.pnl_Content, "Center");
        (this.list = new JList<String>()).setFixedCellHeight(24);
        this.list.setSelectionMode(0);
        this.list.setCellRenderer(new CustomListCellRenderer());
        this.model = new DefaultListModel<String>();
        for (int i = 0; i < Constantes.GOOGLE_EVENT_COLOR_NAMES.length; ++i) {
            this.model.addElement(Constantes.GOOGLE_EVENT_COLOR_NAMES[i]);
        }
        this.list.setModel(this.model);
        try {
            this.list.setSelectedIndex(Integer.parseInt(this.colorId));
        }
        catch (Exception e) {
            this.list.setSelectedIndex(0);
        }
        this.pnl_Content.add(this.list);
        (this.pnl_Buttons = new JPanel()).setLayout(new BoxLayout(this.pnl_Buttons, 3));
        this.getContentPane().add(this.pnl_Buttons, "South");
        this.verticalStrut_Buttons_1 = Box.createVerticalStrut(20);
        this.pnl_Buttons.add(this.verticalStrut_Buttons_1);
        (this.panel = new JPanel()).setLayout(new BoxLayout(this.panel, 0));
        this.pnl_Buttons.add(this.panel);
        this.horizontalStrut_Buttons_1 = Box.createHorizontalStrut(20);
        this.panel.add(this.horizontalStrut_Buttons_1);
        (this.btn_Cancel = new JButton("Annuler")).setPreferredSize(new Dimension(104, 40));
        this.btn_Cancel.setMinimumSize(new Dimension(104, 40));
        this.btn_Cancel.setMaximumSize(new Dimension(104, 40));
        this.btn_Cancel.setFont(new Font("Tahoma", 0, 16));
        this.btn_Cancel.addActionListener(this);
        this.btn_Cancel.setAlignmentX(0.5f);
        this.btn_Cancel.setActionCommand("Cancel");
        this.panel.add(this.btn_Cancel);
        this.horizontalStrut_Buttons_2 = Box.createHorizontalStrut(20);
        this.panel.add(this.horizontalStrut_Buttons_2);
        (this.btn_Valider = new JButton("Valider")).setPreferredSize(new Dimension(104, 40));
        this.btn_Valider.setMinimumSize(new Dimension(104, 40));
        this.btn_Valider.setMaximumSize(new Dimension(104, 40));
        this.btn_Valider.setFont(new Font("Tahoma", 0, 16));
        this.btn_Valider.addActionListener(this);
        this.btn_Valider.setAlignmentX(0.5f);
        this.btn_Valider.setActionCommand("OK");
        this.panel.add(this.btn_Valider);
        this.horizontalStrut_Buttons_3 = Box.createHorizontalStrut(20);
        this.panel.add(this.horizontalStrut_Buttons_3);
        this.verticalStrut_Buttons_2 = Box.createVerticalStrut(20);
        this.pnl_Buttons.add(this.verticalStrut_Buttons_2);
        this.getRootPane().setDefaultButton(this.btn_Valider);
        this.pack();
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.btn_Cancel) {
            this.setVisible(false);
        }
        else if (e.getSource() == this.btn_Valider) {
            this.colorId = String.valueOf(this.list.getSelectedIndex());
            this.setVisible(false);
        }
    }
    
    class CustomListCellRenderer extends JLabel implements ListCellRenderer<Object>
    {
        private static final long serialVersionUID = 1L;
        
        public CustomListCellRenderer() {
            this.setOpaque(true);
            this.setHorizontalAlignment(0);
            this.setVerticalAlignment(0);
            this.setFont(new Font("Tahoma", 0, 16));
        }
        
        @Override
        public Component getListCellRendererComponent(final JList<?> list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
            final String name = Constantes.GOOGLE_EVENT_COLOR_NAMES[index];
            final Color color = Utils.hex2rgb(Constantes.GOOGLE_EVENT_COLORS_VALUES[index]);
            this.setText(name);
            if (color != null) {
                this.setBackground(color);
            }
            else {
                this.setBackground(list.getBackground());
            }
            if (isSelected) {
                this.setFont(this.getFont().deriveFont(1));
                this.setText("> " + this.getText() + " <");
            }
            else {
                this.setFont(this.getFont().deriveFont(0));
            }
            return this;
        }
    }
}
