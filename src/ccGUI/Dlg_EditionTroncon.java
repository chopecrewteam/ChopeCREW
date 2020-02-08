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
import ccStructures.ActiviteSol;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;
import chopeCrew.ChopeCrew;
import java.util.regex.Pattern;
import ccUtils.Utils;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.awt.Container;
import java.awt.Dimension;
import java.util.TimeZone;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.GregorianCalendar;
import ccGUI.EP4Table.EP4Table;
import ccStructures.Troncon;
import javax.swing.JDialog;

public class Dlg_EditionTroncon extends JDialog
{
    private static final long serialVersionUID = 1L;
    private Troncon troncon;
    private EP4Table ccwTable;
    private GregorianCalendar cal;
    private JPanel jContentPane;
    private JPanel pnl_Edition;
    private JPanel pnl_Edition_Bloc;
    private JLabel lab_OutReal;
    private JTextField tfld_OutReal;
    private JPanel pnl_Edition_Tv;
    private JLabel lab_TVReal;
    private JTextField tfld_TVReal;
    private JPanel pnl_Sortie;
    private JButton btn_Valider;
    private JButton btn_Quitter;
    private Component verticalStrut_North;
    private Component horizontalStrut_West;
    private Component horizontalStrut_East;
    private Component verticalStrut_Edition;
    private Component horizontalGlue_Edition_Bloc;
    private Component horizontalGlue_Edition_Tv;
    private Component horizontalStrut_Sortie_West;
    private Component horizontalStrut_Sortie_East;
    private Component horizontalGlue_Sortie;
    private long DEPrBak;
    private double TVrBak;
    private double MEPrBak;
    
    public Dlg_EditionTroncon(final Troncon troncon, final EP4Table ccwTable, final Frame owner) {
        super(owner);
        this.troncon = null;
        this.ccwTable = null;
        this.cal = null;
        this.jContentPane = null;
        this.pnl_Edition = null;
        this.lab_OutReal = null;
        this.tfld_OutReal = null;
        this.lab_TVReal = null;
        this.tfld_TVReal = null;
        this.pnl_Sortie = null;
        this.btn_Valider = null;
        this.btn_Quitter = null;
        this.troncon = troncon;
        this.ccwTable = ccwTable;
        this.cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        this.initialize();
    }
    
    private void initialize() {
        this.setModal(true);
        this.setSize(new Dimension(400, 260));
        this.setResizable(false);
        this.setContentPane(this.getJContentPane());
        this.afficheTroncon();
    }
    
    private void afficheTroncon() {
        this.DEPrBak = this.troncon.DEPr;
        this.TVrBak = this.troncon.TVr;
        this.MEPrBak = this.troncon.MEPr;
        String titre = String.valueOf(this.troncon.numVol) + " | " + this.troncon.from + "-" + this.troncon.to + " | " + this.troncon.departUTC;
        if (this.troncon.isMep) {
            titre = String.valueOf(titre) + " (M.E.P)";
        }
        this.setTitle(titre);
        final SimpleDateFormat sdf = new SimpleDateFormat("HH'h'mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.cal.setTimeInMillis(this.troncon.DEPr);
        final String out = sdf.format(this.cal.getTime());
        this.tfld_OutReal.setForeground(null);
        this.tfld_OutReal.setText(out);
        this.tfld_OutReal.selectAll();
        String tv;
        if (!this.troncon.isMep) {
            tv = Utils.timetoString(this.troncon.TVr);
        }
        else {
            tv = Utils.timetoString(this.troncon.MEPr);
        }
        this.tfld_TVReal.setForeground(null);
        this.tfld_TVReal.setText(tv);
        this.tfld_TVReal.selectAll();
        this.tfld_OutReal.requestFocusInWindow();
    }
    
    private long checkTronconOut() {
        final String input = this.tfld_OutReal.getText();
        final String motif = "\\A(\\d{1,2})([.,h]?)(\\d{2})\\z";
        final Pattern regex = Pattern.compile(motif);
        final Matcher result = regex.matcher(input);
        if (result.find()) {
            final int h = Integer.parseInt(result.group(1));
            int m = Integer.parseInt(result.group(3));
            boolean entreeValide = false;
            if (result.group(2).equals(".") || result.group(2).equals(",")) {
                if (h < 24 && m < 100) {
                    entreeValide = true;
                    this.tfld_OutReal.setForeground(Color.BLUE);
                    if (h < 10) {
                        if (m < 10) {
                            this.tfld_OutReal.setText("0" + h + ".0" + m);
                        }
                        else {
                            this.tfld_OutReal.setText("0" + h + "." + m);
                        }
                    }
                    else if (m < 10) {
                        this.tfld_OutReal.setText(String.valueOf(h) + ".0" + m);
                    }
                    else {
                        this.tfld_OutReal.setText(String.valueOf(h) + "." + m);
                    }
                    m = Math.round(m * 60.0f / 100.0f);
                }
            }
            else if (h < 24 && m < 60) {
                entreeValide = true;
                this.tfld_OutReal.setForeground(Color.BLUE);
                if (h < 10) {
                    if (m < 10) {
                        this.tfld_OutReal.setText("0" + h + "h0" + m);
                    }
                    else {
                        this.tfld_OutReal.setText("0" + h + "h" + m);
                    }
                }
                else if (m < 10) {
                    this.tfld_OutReal.setText(String.valueOf(h) + "h0" + m);
                }
                else {
                    this.tfld_OutReal.setText(String.valueOf(h) + "h" + m);
                }
            }
            if (entreeValide) {
                this.cal.setTimeInMillis(this.troncon.DEPp);
                this.cal.set(11, h);
                this.cal.set(12, m);
                this.cal.set(13, 0);
                final long sameDay = this.cal.getTimeInMillis();
                this.cal.add(5, 1);
                final long dayAfter = this.cal.getTimeInMillis();
                this.cal.add(5, -2);
                final long dayBefore = this.cal.getTimeInMillis();
                final long deltaBefore = Math.abs(this.troncon.DEPp - dayBefore);
                final long deltaAfter = Math.abs(this.troncon.DEPp - dayAfter);
                final long deltaSame = Math.abs(this.troncon.DEPp - sameDay);
                if (deltaBefore < deltaSame && deltaBefore < deltaAfter) {
                    this.cal.setTimeInMillis(dayBefore);
                }
                else if (deltaAfter < deltaSame && deltaAfter < deltaBefore) {
                    this.cal.setTimeInMillis(dayAfter);
                }
                else {
                    this.cal.setTimeInMillis(sameDay);
                }
                return this.cal.getTimeInMillis();
            }
        }
        JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur de saisie du bloc d\u00e9part", "ChopeCREW vous informe", -1);
        this.tfld_OutReal.setForeground(Color.RED);
        return -1L;
    }
    
    private double checkTronconTV() {
        final String input = this.tfld_TVReal.getText();
        final String motif = "\\A(\\d{1,2})([.,h]?)(\\d{2})\\z";
        final Pattern regex = Pattern.compile(motif);
        final Matcher result = regex.matcher(input);
        if (result.find()) {
            final int h = Integer.parseInt(result.group(1));
            final int m = Integer.parseInt(result.group(3));
            if (result.group(2).equals(".") || result.group(2).equals(",")) {
                if (h < 24 && m < 100) {
                    this.tfld_TVReal.setForeground(Color.BLUE);
                    if (m < 10) {
                        this.tfld_TVReal.setText(String.valueOf(h) + ".0" + m);
                        return Double.parseDouble(String.valueOf(h) + ".0" + m);
                    }
                    this.tfld_TVReal.setText(String.valueOf(h) + "." + m);
                    return Double.parseDouble(String.valueOf(h) + "." + m);
                }
            }
            else if (h < 24 && m < 60) {
                this.tfld_TVReal.setForeground(Color.BLUE);
                this.tfld_TVReal.setText(String.valueOf(h) + "h" + m);
                return Utils.arrondi(h + m / 60.0, 2);
            }
        }
        JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur de saisie du temps de vol", "ChopeCREW vous informe", -1);
        this.tfld_TVReal.setForeground(Color.RED);
        return -1.0;
    }
    
    private int getNextTroncon() {
        final int i = this.troncon.numRot;
        final int j = this.troncon.numSV;
        final int k = this.troncon.numTroncon;
        Rotation rot = ChopeCrew.analyse.listRotation.get(i - 1);
        ServiceVol sv = rot.listSV.get(j - 1);
        if (k < sv.listTroncon.size()) {
            this.troncon = sv.listTroncon.get(k);
            return 1;
        }
        if (j < rot.listSV.size()) {
            sv = rot.listSV.get(j);
            this.troncon = sv.listTroncon.get(0);
            return 1;
        }
        if (i < ChopeCrew.analyse.listRotation.size()) {
            rot = ChopeCrew.analyse.listRotation.get(i);
            sv = rot.listSV.get(0);
            this.troncon = sv.listTroncon.get(0);
            int nbSol = 0;
            for (final Object obj : ChopeCrew.analyse.listCrew) {
                if (obj instanceof Rotation) {
                    final Rotation r = (Rotation)obj;
                    if (r.numRot == i) {
                        nbSol = 0;
                    }
                    else {
                        if (r.numRot > i) {
                            break;
                        }
                        continue;
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
                    ++nbSol;
                }
            }
            return nbSol + 1;
        }
        return 0;
    }
    
    private void actionBtnValider() {
        final long checkOut = this.checkTronconOut();
        final double checkTv = this.checkTronconTV();
        if (checkOut != -1L && checkTv != -1.0) {
            this.troncon.DEPr = checkOut;
            if (!this.troncon.isMep) {
                this.troncon.TVr = checkTv;
                this.troncon.MEPr = 0.0;
            }
            else {
                this.troncon.TVr = 0.0;
                this.troncon.MEPr = checkTv;
            }
            this.troncon.isTVreal = true;
            final int r = this.ccwTable.getSelectedRow();
            if (this.troncon.DEPr != this.DEPrBak || this.troncon.TVr != this.TVrBak || this.troncon.MEPr != this.MEPrBak) {
                ((EP4TableModel)this.ccwTable.getModel()).fireTableDataChanged();
            }
            this.ccwTable.setRowSelectionInterval(r, r);
            this.dispose();
        }
        else if (checkOut == -1L) {
            this.tfld_OutReal.requestFocusInWindow();
        }
        else if (checkTv == -1.0) {
            this.tfld_TVReal.requestFocusInWindow();
        }
    }
    
    private void actionBtnQuitter() {
        this.dispose();
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BorderLayout(0, 0));
            this.jContentPane.add(this.getVerticalStrut_North(), "North");
            this.jContentPane.add(this.getHorizontalStrut_East(), "East");
            this.jContentPane.add(this.getHorizontalStrut_West(), "West");
            this.jContentPane.add(this.getPnl_Edition(), "Center");
            this.jContentPane.add(this.getPnl_Sortie(), "South");
        }
        return this.jContentPane;
    }
    
    private JPanel getPnl_Edition() {
        if (this.pnl_Edition == null) {
            (this.pnl_Edition = new JPanel()).setBorder(null);
            this.pnl_Edition.setLayout(new BoxLayout(this.pnl_Edition, 3));
            this.pnl_Edition.add(this.getPnl_Edition_Bloc());
            this.pnl_Edition.add(this.getVerticalStrut_Edition());
            this.pnl_Edition.add(this.getPnl_Edition_Tv());
        }
        return this.pnl_Edition;
    }
    
    private JPanel getPnl_Edition_Bloc() {
        if (this.pnl_Edition_Bloc == null) {
            (this.pnl_Edition_Bloc = new JPanel()).setLayout(new BoxLayout(this.pnl_Edition_Bloc, 0));
            this.pnl_Edition_Bloc.add(this.getLab_OutReal());
            this.pnl_Edition_Bloc.add(this.getHorizontalGlue_Edition_Bloc());
            this.pnl_Edition_Bloc.add(this.getTfld_OutReal());
        }
        return this.pnl_Edition_Bloc;
    }
    
    private JLabel getLab_OutReal() {
        if (this.lab_OutReal == null) {
            (this.lab_OutReal = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.lab_OutReal.setText("Bloc d\u00e9part :");
        }
        return this.lab_OutReal;
    }
    
    private JTextField getTfld_OutReal() {
        if (this.tfld_OutReal == null) {
            (this.tfld_OutReal = new JTextField()).setPreferredSize(new Dimension(64, 32));
            this.tfld_OutReal.setMaximumSize(new Dimension(96, 40));
            this.tfld_OutReal.setMinimumSize(new Dimension(64, 32));
            this.tfld_OutReal.setHorizontalAlignment(11);
            this.tfld_OutReal.setFont(new Font("Tahoma", 0, 16));
            this.tfld_OutReal.setToolTipText("Sous la forme (H)Hmm (notation horaire), ou (H)H.xx (notation d\u00e9cimale)");
            this.tfld_OutReal.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_OutReal;
    }
    
    private JPanel getPnl_Edition_Tv() {
        if (this.pnl_Edition_Tv == null) {
            (this.pnl_Edition_Tv = new JPanel()).setLayout(new BoxLayout(this.pnl_Edition_Tv, 0));
            this.pnl_Edition_Tv.add(this.getLab_TVReal());
            this.pnl_Edition_Tv.add(this.getHorizontalGlue_Edition_Tv());
            this.pnl_Edition_Tv.add(this.getTfld_TVReal());
        }
        return this.pnl_Edition_Tv;
    }
    
    private JLabel getLab_TVReal() {
        if (this.lab_TVReal == null) {
            (this.lab_TVReal = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.lab_TVReal.setText("Temps de vol :");
        }
        return this.lab_TVReal;
    }
    
    private JTextField getTfld_TVReal() {
        if (this.tfld_TVReal == null) {
            (this.tfld_TVReal = new JTextField()).setPreferredSize(new Dimension(64, 32));
            this.tfld_TVReal.setMaximumSize(new Dimension(96, 40));
            this.tfld_TVReal.setMinimumSize(new Dimension(64, 32));
            this.tfld_TVReal.setHorizontalAlignment(11);
            this.tfld_TVReal.setFont(new Font("Tahoma", 0, 16));
            this.tfld_TVReal.setToolTipText("Sous la forme (H)Hmm (notation horaire), ou (H)H.xx (notation d\u00e9cimale)");
            this.tfld_TVReal.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_TVReal;
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
    
    private Component getHorizontalGlue_Edition_Bloc() {
        if (this.horizontalGlue_Edition_Bloc == null) {
            this.horizontalGlue_Edition_Bloc = Box.createHorizontalGlue();
        }
        return this.horizontalGlue_Edition_Bloc;
    }
    
    private Component getHorizontalGlue_Edition_Tv() {
        if (this.horizontalGlue_Edition_Tv == null) {
            this.horizontalGlue_Edition_Tv = Box.createHorizontalGlue();
        }
        return this.horizontalGlue_Edition_Tv;
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
                if (e.getSource() == Dlg_EditionTroncon.this.tfld_OutReal) {
                    final long checkOut = Dlg_EditionTroncon.this.checkTronconOut();
                    if (checkOut != -1L) {
                        Dlg_EditionTroncon.this.tfld_TVReal.requestFocusInWindow();
                    }
                    else {
                        Dlg_EditionTroncon.this.tfld_OutReal.requestFocusInWindow();
                    }
                }
                else if (e.getSource() == Dlg_EditionTroncon.this.tfld_TVReal) {
                    final long checkOut = Dlg_EditionTroncon.this.checkTronconOut();
                    final double checkTv = Dlg_EditionTroncon.this.checkTronconTV();
                    if (checkOut != -1L && checkTv != -1.0) {
                        Dlg_EditionTroncon.this.troncon.DEPr = checkOut;
                        if (!Dlg_EditionTroncon.this.troncon.isMep) {
                            Dlg_EditionTroncon.this.troncon.TVr = checkTv;
                            Dlg_EditionTroncon.this.troncon.MEPr = 0.0;
                        }
                        else {
                            Dlg_EditionTroncon.this.troncon.TVr = 0.0;
                            Dlg_EditionTroncon.this.troncon.MEPr = checkTv;
                        }
                        Dlg_EditionTroncon.this.troncon.isTVreal = true;
                        final int r = Dlg_EditionTroncon.this.ccwTable.getSelectedRow();
                        if (Dlg_EditionTroncon.this.troncon.DEPr != Dlg_EditionTroncon.this.DEPrBak || Dlg_EditionTroncon.this.troncon.TVr != Dlg_EditionTroncon.this.TVrBak || Dlg_EditionTroncon.this.troncon.MEPr != Dlg_EditionTroncon.this.MEPrBak) {
                            ((EP4TableModel)Dlg_EditionTroncon.this.ccwTable.getModel()).fireTableDataChanged();
                        }
                        final int z = Dlg_EditionTroncon.this.getNextTroncon();
                        if (z == 0) {
                            Dlg_EditionTroncon.this.ccwTable.setRowSelectionInterval(r, r);
                            Dlg_EditionTroncon.this.dispose();
                        }
                        else {
                            Dlg_EditionTroncon.this.ccwTable.setRowSelectionInterval(r + z, r + z);
                            Dlg_EditionTroncon.this.afficheTroncon();
                        }
                    }
                    else if (checkOut == -1L) {
                        Dlg_EditionTroncon.this.tfld_OutReal.requestFocusInWindow();
                    }
                    else if (checkTv == -1.0) {
                        Dlg_EditionTroncon.this.tfld_TVReal.requestFocusInWindow();
                    }
                }
                else if (e.getSource() == Dlg_EditionTroncon.this.btn_Valider) {
                    Dlg_EditionTroncon.this.actionBtnValider();
                }
                else if (e.getSource() == Dlg_EditionTroncon.this.btn_Quitter) {
                    Dlg_EditionTroncon.this.actionBtnQuitter();
                }
            }
        }
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_EditionTroncon.this.btn_Valider) {
                Dlg_EditionTroncon.this.actionBtnValider();
            }
            else if (e.getSource() == Dlg_EditionTroncon.this.btn_Quitter) {
                Dlg_EditionTroncon.this.actionBtnQuitter();
            }
        }
    }
}
