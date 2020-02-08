// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Dimension;
import chopeCrew.ChopeCrew;
import java.awt.event.WindowListener;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Dlg_AdresseIpn extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JButton btn_Valider;
    private Box.Filler filler_North;
    private Box.Filler filler_West;
    private Box.Filler filler_East;
    private Box.Filler filler_South;
    private Box.Filler filler_AdresseIpn_1;
    private Box.Filler filler_AdresseIpn_2;
    private JLabel lbl_AdresseIpn;
    private JPanel pnl_AdresseIpn;
    private JTextField tfld_AdresseIpn;
    
    public Dlg_AdresseIpn(final Frame parent) {
        super(parent);
        this.initComponents();
        this.setModal(true);
        this.setTitle("Validation iPN");
        this.addWindowListener(new MyWindowAdapter());
    }
    
    private void actionBtnValider() {
        String s = this.tfld_AdresseIpn.getText().trim().toLowerCase();
        if (s.contains("http://")) {
            s = s.replaceAll("http://", "");
        }
        else if (s.contains("https://")) {
            s = s.replaceAll("https://", "");
        }
        if (s.contains("/")) {
            s = s.replaceAll("/", "");
        }
        if (s.contains("crew")) {
            s = s.replaceAll("crew", "ipn");
        }
        if (s.contains("habile")) {
            s = s.replaceAll("habile", "ipn");
        }
        if (s.contains("intralignes")) {
            s = s.replaceAll("intralignes", "ipn");
        }
        ChopeCrew.options.urlIPN = s;
        this.dispose();
    }
    
    private void initComponents() {
        this.filler_West = new Box.Filler(new Dimension(20, 0), new Dimension(20, 0), new Dimension(20, 32767));
        this.filler_East = new Box.Filler(new Dimension(20, 0), new Dimension(20, 0), new Dimension(20, 32767));
        this.filler_North = new Box.Filler(new Dimension(0, 20), new Dimension(0, 20), new Dimension(32767, 20));
        this.filler_South = new Box.Filler(new Dimension(0, 20), new Dimension(0, 20), new Dimension(32767, 20));
        this.pnl_AdresseIpn = new JPanel();
        this.lbl_AdresseIpn = new JLabel();
        this.filler_AdresseIpn_1 = new Box.Filler(new Dimension(0, 20), new Dimension(0, 20), new Dimension(32767, 20));
        this.tfld_AdresseIpn = new JTextField();
        this.filler_AdresseIpn_2 = new Box.Filler(new Dimension(0, 20), new Dimension(0, 20), new Dimension(32767, 20));
        this.btn_Valider = new JButton();
        this.setDefaultCloseOperation(2);
        this.lbl_AdresseIpn.setFont(new Font("Tahoma", 0, 16));
        this.lbl_AdresseIpn.setText("Veuillez entrer l'adresse d'iPn");
        this.lbl_AdresseIpn.setAlignmentX(0.5f);
        this.tfld_AdresseIpn.setFont(new Font("Tahoma", 0, 16));
        this.tfld_AdresseIpn.setPreferredSize(new Dimension(256, 32));
        this.tfld_AdresseIpn.addKeyListener(new MyKeyAdapter());
        this.btn_Valider.setFont(new Font("Tahoma", 0, 16));
        this.btn_Valider.setText("Valider");
        this.btn_Valider.setAlignmentX(0.5f);
        this.btn_Valider.addMouseListener(new MyMouseAdapter());
        this.btn_Valider.addKeyListener(new MyKeyAdapter());
        this.pnl_AdresseIpn.setLayout(new BoxLayout(this.pnl_AdresseIpn, 3));
        this.pnl_AdresseIpn.add(this.lbl_AdresseIpn);
        this.pnl_AdresseIpn.add(this.filler_AdresseIpn_1);
        this.pnl_AdresseIpn.add(this.tfld_AdresseIpn);
        this.pnl_AdresseIpn.add(this.filler_AdresseIpn_2);
        this.pnl_AdresseIpn.add(this.btn_Valider);
        this.getContentPane().add(this.filler_West, "West");
        this.getContentPane().add(this.filler_East, "East");
        this.getContentPane().add(this.filler_North, "North");
        this.getContentPane().add(this.filler_South, "South");
        this.getContentPane().add(this.pnl_AdresseIpn, "Center");
        this.pack();
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10) {
                if (e.getSource() == Dlg_AdresseIpn.this.tfld_AdresseIpn) {
                    Dlg_AdresseIpn.this.actionBtnValider();
                }
                else if (e.getSource() == Dlg_AdresseIpn.this.btn_Valider) {
                    Dlg_AdresseIpn.this.actionBtnValider();
                }
            }
        }
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_AdresseIpn.this.btn_Valider) {
                Dlg_AdresseIpn.this.actionBtnValider();
            }
        }
    }
    
    class MyWindowAdapter extends WindowAdapter
    {
        @Override
        public void windowClosing(final WindowEvent e) {
            System.exit(0);
        }
    }
}
