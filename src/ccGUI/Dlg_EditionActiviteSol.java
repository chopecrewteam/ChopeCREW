// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.Box;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import java.awt.event.KeyListener;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ccGUI.EP4Table.EP4TableModel;
import java.util.Iterator;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import chopeCrew.ChopeCrew;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ccGUI.EP4Table.EP4Table;
import ccStructures.ActiviteSol;
import javax.swing.JDialog;

public class Dlg_EditionActiviteSol extends JDialog
{
    private static final long serialVersionUID = 1L;
    private ActiviteSol actSol;
    private EP4Table ccwTable;
    private JPanel jContentPane;
    private JPanel pnl_Edition;
    private JPanel pnl_Edition_Lieu;
    private JLabel lab_IKV;
    private JTextField tfld_IKV;
    private JPanel pnl_Edition_HC;
    private JLabel lab_HC;
    private JTextField tfld_HC;
    private JTextField tfld_HCR;
    private JPanel pnl_Sortie;
    private JButton btn_Valider;
    private JButton btn_Quitter;
    private Component verticalStrut_North;
    private Component horizontalStrut_West;
    private Component horizontalStrut_East;
    private Component verticalStrut_Edition;
    private Component horizontalGlue_Edition_Lieu;
    private Component horizontalGlue_Edition_HC;
    private Component horizontalStrut_Sortie_West;
    private Component horizontalStrut_Sortie_East;
    private Component horizontalGlue_Sortie;
    private String IKVarBak;
    private double HCBak;
    private double HCRBak;
    
    public Dlg_EditionActiviteSol(final ActiviteSol act, final EP4Table ccwTable, final Frame owner) {
        super(owner);
        this.actSol = null;
        this.ccwTable = null;
        this.jContentPane = null;
        this.lab_IKV = null;
        this.tfld_IKV = null;
        this.lab_HC = null;
        this.tfld_HC = null;
        this.tfld_HCR = null;
        this.pnl_Sortie = null;
        this.btn_Valider = null;
        this.btn_Quitter = null;
        this.actSol = act;
        this.ccwTable = ccwTable;
        this.initialize();
    }
    
    private void initialize() {
        this.setModal(true);
        this.setSize(new Dimension(400, 260));
        this.setResizable(false);
        this.setContentPane(this.getJContentPane());
        this.afficheActiviteSol();
    }
    
    private void afficheActiviteSol() {
        this.IKVarBak = this.actSol.IKVar;
        this.HCBak = this.actSol.HCS;
        this.HCRBak = this.actSol.HCRS;
        final String titre = String.valueOf(this.actSol.label) + " du " + this.actSol.debut + " LT";
        this.setTitle(titre);
        this.tfld_IKV.setForeground(null);
        this.tfld_IKV.setText(this.actSol.IKVar);
        this.tfld_IKV.selectAll();
        this.tfld_HC.setForeground(null);
        this.tfld_HC.setText(String.valueOf(this.actSol.HCS));
        this.tfld_HC.selectAll();
        this.tfld_HCR.setForeground(null);
        this.tfld_HCR.setText(String.valueOf(this.actSol.HCRS));
        this.tfld_HCR.selectAll();
        this.tfld_IKV.requestFocusInWindow();
    }
    
    private String checkActiviteIKV() {
        final String input = this.tfld_IKV.getText().toUpperCase();
        if (input.equals("") || input.equals("NIL")) {
            this.tfld_IKV.setForeground(Color.BLUE);
            this.tfld_IKV.setText("NIL");
            return "NIL";
        }
        if (input.equals("CDG") || input.equals("ORY")) {
            this.tfld_IKV.setForeground(Color.BLUE);
            this.tfld_IKV.setText(input);
            return input;
        }
        JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur de saisie du lieu (CDG, ORY, ou vide)", "ChopeCREW vous informe", -1);
        this.tfld_IKV.setForeground(Color.RED);
        return null;
    }
    
    private double checkActiviteHC() {
        final String input = this.tfld_HC.getText();
        final String motif = "\\A([0-9]{1})([\\.,][0-9]{1,2})?\\z";
        final Pattern regex = Pattern.compile(motif);
        final Matcher result = regex.matcher(input);
        if (result.find()) {
            this.tfld_HC.setForeground(Color.BLUE);
            this.tfld_HC.setText(input);
            return Double.parseDouble(input.replaceAll(",", "."));
        }
        JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur de saisie", "ChopeCREW vous informe", -1);
        this.tfld_HC.setForeground(Color.RED);
        return -1.0;
    }
    
    private double checkActiviteHCR() {
        final String input = this.tfld_HCR.getText();
        final String motif = "\\A([0-9]{1})([\\.,][0-9]{1,2})?\\z";
        final Pattern regex = Pattern.compile(motif);
        final Matcher result = regex.matcher(input);
        if (result.find()) {
            this.tfld_HCR.setForeground(Color.BLUE);
            this.tfld_HCR.setText(input);
            return Double.parseDouble(input.replaceAll(",", "."));
        }
        JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur de saisie", "ChopeCREW vous informe", -1);
        this.tfld_HCR.setForeground(Color.RED);
        return -1.0;
    }
    
    private int getNextActSol() {
        int nbVol = 0;
        boolean flag = false;
        for (final Object obj : ChopeCrew.analyse.listCrew) {
            if (obj instanceof ActiviteSol) {
                final ActiviteSol act = (ActiviteSol)obj;
                if (act.equals(this.actSol)) {
                    flag = true;
                    nbVol = 0;
                }
                else {
                    if (act.isCredite && flag) {
                        this.actSol = act;
                        return nbVol + 1;
                    }
                    continue;
                }
            }
            else {
                if (!(obj instanceof Rotation)) {
                    continue;
                }
                final Rotation rot = (Rotation)obj;
                for (final ServiceVol sv : rot.listSV) {
                    nbVol += sv.listTroncon.size();
                }
            }
        }
        return 0;
    }
    
    private void actionBtnValider() {
        final String checkIkv = this.checkActiviteIKV();
        final double checkHc = this.checkActiviteHC();
        final double checkHcR = this.checkActiviteHCR();
        if (checkIkv != null && checkHc != -1.0 && checkHcR != -1.0) {
            this.actSol.IKVar = checkIkv;
            this.actSol.HCS = checkHc;
            this.actSol.HCRS = checkHcR;
            this.actSol.isIKVreal = true;
            this.actSol.isHCreal = true;
            final int r = this.ccwTable.getSelectedRow();
            if (!this.actSol.IKVar.equals(this.IKVarBak) || this.actSol.HCS != this.HCBak || this.actSol.HCRS != this.HCRBak) {
                ((EP4TableModel)this.ccwTable.getModel()).fireTableDataChanged();
            }
            this.ccwTable.setRowSelectionInterval(r, r);
            this.dispose();
        }
        else if (checkIkv == null) {
            this.tfld_IKV.requestFocusInWindow();
        }
        else if (checkHc == -1.0) {
            this.tfld_HC.requestFocusInWindow();
        }
        else if (checkHcR == -1.0) {
            this.tfld_HCR.requestFocusInWindow();
        }
    }
    
    private void actionBtnQuitter() {
        this.dispose();
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BorderLayout(0, 0));
            this.jContentPane.add(this.getVerticalStrut_North(), "North");
            this.jContentPane.add(this.getHorizontalStrut_West(), "West");
            this.jContentPane.add(this.getHorizontalStrut_East(), "East");
            this.jContentPane.add(this.getPnl_Edition(), "Center");
            this.jContentPane.add(this.getPnl_Sortie(), "South");
        }
        return this.jContentPane;
    }
    
    private JPanel getPnl_Edition() {
        if (this.pnl_Edition == null) {
            (this.pnl_Edition = new JPanel()).setBorder(null);
            this.pnl_Edition.setLayout(new BoxLayout(this.pnl_Edition, 3));
            this.pnl_Edition.add(this.getPnl_Edition_Lieu());
            this.pnl_Edition.add(this.getVerticalStrut_Edition());
            this.pnl_Edition.add(this.getPnl_Edition_HC());
        }
        return this.pnl_Edition;
    }
    
    private JPanel getPnl_Edition_Lieu() {
        if (this.pnl_Edition_Lieu == null) {
            (this.pnl_Edition_Lieu = new JPanel()).setLayout(new BoxLayout(this.pnl_Edition_Lieu, 0));
            this.pnl_Edition_Lieu.add(this.getLab_IKV());
            this.pnl_Edition_Lieu.add(this.getHorizontalGlue_Edition_Lieu());
            this.pnl_Edition_Lieu.add(this.getTfld_IKV());
        }
        return this.pnl_Edition_Lieu;
    }
    
    private JLabel getLab_IKV() {
        if (this.lab_IKV == null) {
            (this.lab_IKV = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.lab_IKV.setText("IKV (lieu) :");
        }
        return this.lab_IKV;
    }
    
    private JTextField getTfld_IKV() {
        if (this.tfld_IKV == null) {
            (this.tfld_IKV = new JTextField()).setPreferredSize(new Dimension(64, 32));
            this.tfld_IKV.setMaximumSize(new Dimension(96, 40));
            this.tfld_IKV.setMinimumSize(new Dimension(64, 32));
            this.tfld_IKV.setHorizontalAlignment(11);
            this.tfld_IKV.setFont(new Font("Tahoma", 0, 16));
            this.tfld_IKV.setToolTipText("Code 3 lettres (CDG, ORY) ou vide");
            this.tfld_IKV.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_IKV;
    }
    
    private JPanel getPnl_Edition_HC() {
        if (this.pnl_Edition_HC == null) {
            (this.pnl_Edition_HC = new JPanel()).setLayout(new BoxLayout(this.pnl_Edition_HC, 0));
            this.pnl_Edition_HC.add(this.getLab_HC());
            this.pnl_Edition_HC.add(this.getHorizontalGlue_Edition_HC());
            this.pnl_Edition_HC.add(this.getTfld_HC());
            this.pnl_Edition_HC.add(this.getTfld_HCR());
        }
        return this.pnl_Edition_HC;
    }
    
    private JLabel getLab_HC() {
        if (this.lab_HC == null) {
            (this.lab_HC = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.lab_HC.setText("HC / HCR :");
        }
        return this.lab_HC;
    }
    
    private JTextField getTfld_HC() {
        if (this.tfld_HC == null) {
            (this.tfld_HC = new JTextField()).setPreferredSize(new Dimension(64, 32));
            this.tfld_HC.setMaximumSize(new Dimension(96, 40));
            this.tfld_HC.setMinimumSize(new Dimension(64, 32));
            this.tfld_HC.setHorizontalAlignment(11);
            this.tfld_HC.setFont(new Font("Tahoma", 0, 16));
            this.tfld_HC.setEnabled(true);
            this.tfld_HC.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_HC;
    }
    
    private JTextField getTfld_HCR() {
        if (this.tfld_HCR == null) {
            (this.tfld_HCR = new JTextField()).setPreferredSize(new Dimension(64, 32));
            this.tfld_HCR.setMaximumSize(new Dimension(96, 40));
            this.tfld_HCR.setMinimumSize(new Dimension(64, 32));
            this.tfld_HCR.setHorizontalAlignment(11);
            this.tfld_HCR.setFont(new Font("Tahoma", 0, 16));
            this.tfld_HCR.setEnabled(true);
            this.tfld_HCR.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_HCR;
    }
    
    private JPanel getPnl_Sortie() {
        if (this.pnl_Sortie == null) {
            (this.pnl_Sortie = new JPanel()).setBorder(BorderFactory.createTitledBorder(null, null, 0, 0, new Font("Tahoma", 0, 11), Color.darkGray));
            this.pnl_Sortie.setLayout(new BoxLayout(this.pnl_Sortie, 0));
            this.pnl_Sortie.add(this.getHorizontalStrut_Sortie_West());
            this.pnl_Sortie.add(this.getBtn_Valider());
            this.pnl_Sortie.add(this.getHorizontalGlue_Sortie());
            this.pnl_Sortie.add(this.getBtn_Quitter());
            this.pnl_Sortie.add(this.getHorizontalStrut_Sortie_East());
        }
        return this.pnl_Sortie;
    }
    
    private JButton getBtn_Valider() {
        if (this.btn_Valider == null) {
            (this.btn_Valider = new JButton()).setAlignmentX(0.5f);
            this.btn_Valider.setPreferredSize(new Dimension(104, 40));
            this.btn_Valider.setFont(new Font("Tahoma", 0, 16));
            this.btn_Valider.setText("Valider");
            this.btn_Valider.addMouseListener(new MyMouseAdapter());
            this.btn_Valider.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Valider;
    }
    
    private JButton getBtn_Quitter() {
        if (this.btn_Quitter == null) {
            (this.btn_Quitter = new JButton()).setAlignmentX(0.5f);
            this.btn_Quitter.setPreferredSize(new Dimension(104, 40));
            this.btn_Quitter.setFont(new Font("Tahoma", 0, 16));
            this.btn_Quitter.setText("Quitter");
            this.btn_Quitter.addMouseListener(new MyMouseAdapter());
            this.btn_Quitter.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Quitter;
    }
    
    private Component getVerticalStrut_North() {
        if (this.verticalStrut_North == null) {
            this.verticalStrut_North = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_North;
    }
    
    private Component getHorizontalStrut_West() {
        if (this.horizontalStrut_West == null) {
            this.horizontalStrut_West = Box.createHorizontalStrut(64);
        }
        return this.horizontalStrut_West;
    }
    
    private Component getHorizontalStrut_East() {
        if (this.horizontalStrut_East == null) {
            this.horizontalStrut_East = Box.createHorizontalStrut(64);
        }
        return this.horizontalStrut_East;
    }
    
    private Component getVerticalStrut_Edition() {
        if (this.verticalStrut_Edition == null) {
            this.verticalStrut_Edition = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Edition;
    }
    
    private Component getHorizontalGlue_Edition_Lieu() {
        if (this.horizontalGlue_Edition_Lieu == null) {
            this.horizontalGlue_Edition_Lieu = Box.createHorizontalGlue();
        }
        return this.horizontalGlue_Edition_Lieu;
    }
    
    private Component getHorizontalGlue_Edition_HC() {
        if (this.horizontalGlue_Edition_HC == null) {
            this.horizontalGlue_Edition_HC = Box.createHorizontalGlue();
        }
        return this.horizontalGlue_Edition_HC;
    }
    
    private Component getHorizontalStrut_Sortie_West() {
        if (this.horizontalStrut_Sortie_West == null) {
            this.horizontalStrut_Sortie_West = Box.createHorizontalStrut(64);
        }
        return this.horizontalStrut_Sortie_West;
    }
    
    private Component getHorizontalStrut_Sortie_East() {
        if (this.horizontalStrut_Sortie_East == null) {
            this.horizontalStrut_Sortie_East = Box.createHorizontalStrut(64);
        }
        return this.horizontalStrut_Sortie_East;
    }
    
    private Component getHorizontalGlue_Sortie() {
        if (this.horizontalGlue_Sortie == null) {
            this.horizontalGlue_Sortie = Box.createHorizontalGlue();
        }
        return this.horizontalGlue_Sortie;
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10) {
                if (e.getSource() == Dlg_EditionActiviteSol.this.tfld_IKV) {
                    final String checkIkv = Dlg_EditionActiviteSol.this.checkActiviteIKV();
                    if (checkIkv != null) {
                        Dlg_EditionActiviteSol.this.tfld_HC.requestFocusInWindow();
                    }
                    else {
                        Dlg_EditionActiviteSol.this.tfld_IKV.requestFocusInWindow();
                    }
                }
                else if (e.getSource() == Dlg_EditionActiviteSol.this.tfld_HC) {
                    final double checkHc = Dlg_EditionActiviteSol.this.checkActiviteHC();
                    if (checkHc != -1.0) {
                        Dlg_EditionActiviteSol.this.tfld_HCR.requestFocusInWindow();
                    }
                    else {
                        Dlg_EditionActiviteSol.this.tfld_HC.requestFocusInWindow();
                    }
                }
                else if (e.getSource() == Dlg_EditionActiviteSol.this.tfld_HCR) {
                    final String checkIkv = Dlg_EditionActiviteSol.this.checkActiviteIKV();
                    final double checkHc2 = Dlg_EditionActiviteSol.this.checkActiviteHC();
                    final double checkHcR = Dlg_EditionActiviteSol.this.checkActiviteHCR();
                    if (checkIkv != null && checkHc2 != -1.0 && checkHcR != -1.0) {
                        Dlg_EditionActiviteSol.this.actSol.IKVar = checkIkv;
                        Dlg_EditionActiviteSol.this.actSol.HCS = checkHc2;
                        Dlg_EditionActiviteSol.this.actSol.HCRS = checkHcR;
                        Dlg_EditionActiviteSol.this.actSol.isIKVreal = true;
                        Dlg_EditionActiviteSol.this.actSol.isHCreal = true;
                        final int r = Dlg_EditionActiviteSol.this.ccwTable.getSelectedRow();
                        if (!Dlg_EditionActiviteSol.this.actSol.IKVar.equals(Dlg_EditionActiviteSol.this.IKVarBak) || Dlg_EditionActiviteSol.this.actSol.HCS != Dlg_EditionActiviteSol.this.HCBak || Dlg_EditionActiviteSol.this.actSol.HCRS != Dlg_EditionActiviteSol.this.HCRBak) {
                            ((EP4TableModel)Dlg_EditionActiviteSol.this.ccwTable.getModel()).fireTableDataChanged();
                        }
                        final int z = Dlg_EditionActiviteSol.this.getNextActSol();
                        if (z == 0) {
                            Dlg_EditionActiviteSol.this.ccwTable.setRowSelectionInterval(r, r);
                            Dlg_EditionActiviteSol.this.dispose();
                        }
                        else {
                            Dlg_EditionActiviteSol.this.ccwTable.setRowSelectionInterval(r + z, r + z);
                            Dlg_EditionActiviteSol.this.afficheActiviteSol();
                        }
                    }
                    else if (checkIkv == null) {
                        Dlg_EditionActiviteSol.this.tfld_IKV.requestFocusInWindow();
                    }
                    else if (checkHc2 == -1.0) {
                        Dlg_EditionActiviteSol.this.tfld_HC.requestFocusInWindow();
                    }
                    else if (checkHcR == -1.0) {
                        Dlg_EditionActiviteSol.this.tfld_HCR.requestFocusInWindow();
                    }
                }
                else if (e.getSource() == Dlg_EditionActiviteSol.this.btn_Valider) {
                    Dlg_EditionActiviteSol.this.actionBtnValider();
                }
                else if (e.getSource() == Dlg_EditionActiviteSol.this.btn_Quitter) {
                    Dlg_EditionActiviteSol.this.actionBtnQuitter();
                }
            }
        }
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_EditionActiviteSol.this.btn_Valider) {
                Dlg_EditionActiviteSol.this.actionBtnValider();
            }
            else if (e.getSource() == Dlg_EditionActiviteSol.this.btn_Quitter) {
                Dlg_EditionActiviteSol.this.actionBtnQuitter();
            }
        }
    }
}
