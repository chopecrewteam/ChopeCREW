// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Box;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ccUtils.Utils;
import java.awt.Container;
import chopeCrew.ChopeCrew;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import ccEngine.EP4_Pnc;
import ccEngine.EP4_Pnt;
import javax.swing.JDialog;

public class Dlg_Resume extends JDialog
{
    private static final long serialVersionUID = 1L;
    private EP4_Pnt ep4_pnt;
    private EP4_Pnc ep4_pnc;
    private JPanel jContentPane;
    private JScrollPane scrollPane;
    private JEditorPane editorPane;
    private JPanel pnl_Fermeture;
    private JButton btn_Fermer;
    private Component horizontalStrut_East;
    private Component horizontalStrut_West;
    private Component verticalStrut_North;
    private Component verticalStrut_Fermeture1;
    private Component verticalStrut_Fermeture2;
    
    public Dlg_Resume(final Frame owner) {
        super(owner);
        this.ep4_pnt = null;
        this.ep4_pnc = null;
        this.jContentPane = null;
        this.scrollPane = null;
        this.editorPane = null;
        this.pnl_Fermeture = null;
        this.btn_Fermer = null;
        if (ChopeCrew.analyse.isPNT) {
            (this.ep4_pnt = new EP4_Pnt()).calculEP4(ChopeCrew.analyse.moisInt, ChopeCrew.donneesPerso_PNT);
        }
        if (!ChopeCrew.analyse.isPNT) {
            (this.ep4_pnc = new EP4_Pnc()).calculEP4(ChopeCrew.analyse.moisInt, ChopeCrew.donneesPerso_PNC);
        }
        this.initialize();
    }
    
    private void initialize() {
        this.setModal(true);
        this.setSize(640, 630);
        this.setResizable(false);
        this.setTitle("R\u00e9sum\u00e9 du planning");
        this.setContentPane(this.getJContentPane());
    }
    
    private String generateResume() {
        final StringBuilder sb = new StringBuilder();
        final String nl = System.getProperty("line.separator");
        int nJEvol;
        int nJEsol;
        int nConges;
        int nMaladies;
        int nSansSolde;
        int nDispersions;
        int nLeversTot;
        double volSTVp;
        double volSTVr;
        double volHCp;
        double volHCr;
        double solHC;
        double seuilHS;
        double volHSp;
        double volHSr;
        double volHCRp;
        double volHCRr;
        double solHCR;
        double volHCNp;
        double volHCNr;
        double seuilMGA;
        if (ChopeCrew.analyse.isPNT) {
            nJEvol = this.ep4_pnt.nJEvol;
            nJEsol = this.ep4_pnt.nJEsol;
            nConges = this.ep4_pnt.nConges;
            nMaladies = this.ep4_pnt.nMaladies;
            nSansSolde = this.ep4_pnt.nSansSolde;
            nDispersions = this.ep4_pnt.nDispersions;
            nLeversTot = this.ep4_pnt.nLeversTot;
            volSTVp = this.ep4_pnt.volSTVp;
            volSTVr = this.ep4_pnt.volSTVr;
            volHCp = this.ep4_pnt.volHCp;
            volHCr = this.ep4_pnt.volHCr;
            solHC = this.ep4_pnt.solHC;
            seuilHS = this.ep4_pnt.seuilHS;
            volHSp = this.ep4_pnt.volHSp;
            volHSr = this.ep4_pnt.volHSr;
            volHCRp = this.ep4_pnt.volHCRp;
            volHCRr = this.ep4_pnt.volHCRr;
            solHCR = this.ep4_pnt.solHCR;
            volHCNp = this.ep4_pnt.volHCNp;
            volHCNr = this.ep4_pnt.volHCNr;
            seuilMGA = this.ep4_pnt.seuilMGA;
        }
        else {
            nJEvol = this.ep4_pnc.nJEvol;
            nJEsol = this.ep4_pnc.nJEsol;
            nConges = this.ep4_pnc.nConges;
            nMaladies = this.ep4_pnc.nMaladies;
            nSansSolde = this.ep4_pnc.nSansSolde;
            nDispersions = this.ep4_pnc.nDispersions;
            nLeversTot = this.ep4_pnc.nLeversTot;
            volSTVp = this.ep4_pnc.volSTVp;
            volSTVr = this.ep4_pnc.volSTVr;
            volHCp = this.ep4_pnc.volHCp;
            volHCr = this.ep4_pnc.volHCr;
            solHC = this.ep4_pnc.solHC;
            seuilHS = this.ep4_pnc.seuilHS;
            volHSp = this.ep4_pnc.volHSp;
            volHSr = this.ep4_pnc.volHSr;
            volHCRp = this.ep4_pnc.volHCRp;
            volHCRr = this.ep4_pnc.volHCRr;
            solHCR = this.ep4_pnc.solHCR;
            volHCNp = this.ep4_pnc.volHCNp;
            volHCNr = this.ep4_pnc.volHCNr;
            seuilMGA = this.ep4_pnc.seuilMGA;
        }
        sb.append(" Jours vol :\t\t").append(nJEvol).append(nl);
        sb.append(" Jours sol :\t\t").append(nJEsol).append(nl);
        sb.append(" Jours cong\u00e9s :\t\t").append(nConges).append(nl);
        sb.append(" Jours maladies :\t").append(nMaladies).append(nl);
        sb.append(" Jours sans solde :\t").append(nSansSolde).append(nl);
        sb.append(" Dispersions :\t\t").append(nDispersions).append(nl);
        sb.append(" Levers t\u00f4t :\t\t").append(nLeversTot).append(nl);
        sb.append(" \t\t\tPROG.\tREAL.").append(nl);
        sb.append(nl);
        sb.append(" Temps de vol en fonction :\t\t").append(volSTVp).append("\t").append(volSTVr).append(nl);
        sb.append(nl);
        sb.append(" HC d\u00e9compt\u00e9es VOL :\t\t").append(volHCp).append("\t").append(volHCr).append(nl);
        sb.append(" HC d\u00e9compt\u00e9es SOL :\t\t").append(solHC).append("\t").append(solHC).append(nl);
        sb.append(" HC d\u00e9compt\u00e9es TOTAL :\t\t").append(Utils.arrondi(volHCp + solHC, 2)).append("\t").append(Utils.arrondi(volHCr + solHC, 2)).append(nl);
        sb.append(nl);
        sb.append(" Seuil HS :\t\t\t").append(seuilHS).append("\t").append(seuilHS).append(nl);
        sb.append(" HS :\t\t\t").append(volHSp).append("\t").append(volHSr).append(nl);
        sb.append(nl);
        sb.append(" HC r\u00e9mun\u00e9r\u00e9es VOL :\t\t").append(volHCRp).append("\t").append(volHCRr).append(nl);
        sb.append(" HC r\u00e9mun\u00e9r\u00e9es SOL :\t\t").append(solHCR).append("\t").append(solHCR).append(nl);
        sb.append(" HC r\u00e9mun\u00e9r\u00e9es Nuit :\t\t").append(volHCNp).append("\t").append(volHCNr).append(nl);
        sb.append(" HC r\u00e9mun\u00e9r\u00e9es TOTAL :\t\t").append(Utils.arrondi(volHCRp + solHCR + volHCNp, 2)).append("\t").append(Utils.arrondi(volHCRr + solHCR + volHCNr, 2)).append(nl);
        sb.append(nl);
        sb.append(" Seuil MGA :\t\t\t").append(seuilMGA).append("\t").append(seuilMGA);
        return sb.toString();
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BorderLayout(0, 0));
            this.jContentPane.add(this.getHorizontalStrut_East(), "East");
            this.jContentPane.add(this.getHorizontalStrut_West(), "West");
            this.jContentPane.add(this.getVerticalStrut_North(), "North");
            this.jContentPane.add(this.getPnl_Fermeture(), "South");
            this.jContentPane.add(this.getScrollPane());
        }
        return this.jContentPane;
    }
    
    private JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setHorizontalScrollBarPolicy(31);
            this.scrollPane.setVerticalScrollBarPolicy(20);
            this.scrollPane.setViewportView(this.getEditorPane());
        }
        return this.scrollPane;
    }
    
    private JEditorPane getEditorPane() {
        if (this.editorPane == null) {
            (this.editorPane = new JEditorPane()).setFont(new Font("Tahoma", 0, 16));
            this.editorPane.setEditable(false);
            this.editorPane.setOpaque(true);
            this.editorPane.setFocusable(false);
            this.editorPane.setText(this.generateResume());
            this.editorPane.setCaretPosition(0);
        }
        return this.editorPane;
    }
    
    private JPanel getPnl_Fermeture() {
        if (this.pnl_Fermeture == null) {
            (this.pnl_Fermeture = new JPanel()).setLayout(new BoxLayout(this.pnl_Fermeture, 1));
            this.pnl_Fermeture.add(this.getVerticalStrut_Fermeture1());
            this.pnl_Fermeture.add(this.getBtn_Fermer());
            this.pnl_Fermeture.add(this.getVerticalStrut_Fermeture2());
        }
        return this.pnl_Fermeture;
    }
    
    private JButton getBtn_Fermer() {
        if (this.btn_Fermer == null) {
            (this.btn_Fermer = new JButton()).setPreferredSize(new Dimension(128, 40));
            this.btn_Fermer.setMinimumSize(new Dimension(128, 40));
            this.btn_Fermer.setMaximumSize(new Dimension(128, 40));
            this.btn_Fermer.setAlignmentX(0.5f);
            this.btn_Fermer.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Fermer.setFont(new Font("Tahoma", 0, 16));
            this.btn_Fermer.setText("Fermer");
            this.btn_Fermer.addMouseListener(new MyMouseAdapter());
            this.btn_Fermer.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Fermer;
    }
    
    private Component getHorizontalStrut_East() {
        if (this.horizontalStrut_East == null) {
            this.horizontalStrut_East = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_East;
    }
    
    private Component getHorizontalStrut_West() {
        if (this.horizontalStrut_West == null) {
            this.horizontalStrut_West = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_West;
    }
    
    private Component getVerticalStrut_North() {
        if (this.verticalStrut_North == null) {
            this.verticalStrut_North = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_North;
    }
    
    private Component getVerticalStrut_Fermeture1() {
        if (this.verticalStrut_Fermeture1 == null) {
            this.verticalStrut_Fermeture1 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Fermeture1;
    }
    
    private Component getVerticalStrut_Fermeture2() {
        if (this.verticalStrut_Fermeture2 == null) {
            this.verticalStrut_Fermeture2 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Fermeture2;
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_Resume.this.btn_Fermer) {
                Dlg_Resume.this.dispose();
            }
        }
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10 && e.getSource() == Dlg_Resume.this.btn_Fermer) {
                Dlg_Resume.this.dispose();
            }
        }
    }
}
