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
import ccStructures.Escale;
import ccUtils.Utils;
import java.awt.Container;
import java.io.File;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import ccExport.Export_Stats;
import javax.swing.JDialog;

public class Dlg_Stats extends JDialog
{
    private static final long serialVersionUID = 1L;
    Export_Stats exp;
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
    
    public Dlg_Stats(final Frame owner, final File f) {
        super(owner);
        this.jContentPane = null;
        this.scrollPane = null;
        this.editorPane = null;
        this.pnl_Fermeture = null;
        this.btn_Fermer = null;
        (this.exp = new Export_Stats(f)).genereFR();
        this.initialize();
    }
    
    private void initialize() {
        this.setModal(true);
        this.setSize(640, 630);
        this.setResizable(false);
        this.setTitle("Statistiques");
        this.setContentPane(this.getJContentPane());
    }
    
    private String generateResume() {
        final StringBuilder sb = new StringBuilder();
        final String nl = System.getProperty("line.separator");
        final int pcD0d = (int)Utils.arrondi(this.exp.compteurPonctuDepartD0 * 100.0 / this.exp.compteurEtapes, 0);
        final int pcD0a = (int)Utils.arrondi(this.exp.compteurPonctuArriveeD0 * 100.0 / this.exp.compteurEtapes, 0);
        final int pcD3d = (int)Utils.arrondi(this.exp.compteurPonctuDepartD3 * 100.0 / this.exp.compteurEtapes, 0);
        final int pcD3a = (int)Utils.arrondi(this.exp.compteurPonctuArriveeD3 * 100.0 / this.exp.compteurEtapes, 0);
        final int pcD15d = (int)Utils.arrondi(this.exp.compteurPonctuDepartD15 * 100.0 / this.exp.compteurEtapes, 0);
        final int pcD15a = (int)Utils.arrondi(this.exp.compteurPonctuArriveeD15 * 100.0 / this.exp.compteurEtapes, 0);
        final int pcD60d = (int)Utils.arrondi(this.exp.compteurPonctuDepartD60 * 100.0 / this.exp.compteurEtapes, 0);
        final int pcD60a = (int)Utils.arrondi(this.exp.compteurPonctuArriveeD60 * 100.0 / this.exp.compteurEtapes, 0);
        sb.append("\u03a3 HDV\tPROG. = " + this.exp.compteurHDVp).append("    \tREAL. = ").append(this.exp.compteurHDVr).append(nl);
        sb.append("\u03a3 MEP\tPROG. = " + this.exp.compteurMEPp).append("    \tREAL. = ").append(this.exp.compteurMEPr).append(nl);
        sb.append("\u03a3 TSV\tPROG. = " + this.exp.compteurTSVp).append("    \tREAL. = ").append(this.exp.compteurTSVr).append(nl);
        sb.append("\u03a3 TA\tPROG. = " + this.exp.compteurTAp).append("    \tREAL. = ").append(this.exp.compteurTAr).append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append("NBRE. ETAPES\t = " + this.exp.compteurEtapes).append(nl);
        sb.append(nl);
        sb.append("D0\tDEP. = " + this.exp.compteurPonctuDepartD0 + " (" + pcD0d + "%)" + "\tARR. = " + this.exp.compteurPonctuArriveeD0 + " (" + pcD0a + "%)").append(nl);
        sb.append("D3\tDEP. = " + this.exp.compteurPonctuDepartD3 + " (" + pcD3d + "%)" + "\tARR. = " + this.exp.compteurPonctuArriveeD3 + " (" + pcD3a + "%)").append(nl);
        sb.append("D15\tDEP. = " + this.exp.compteurPonctuDepartD15 + " (" + pcD15d + "%)" + "\tARR. = " + this.exp.compteurPonctuArriveeD15 + " (" + pcD15a + "%)").append(nl);
        sb.append("D60\tDEP. = " + this.exp.compteurPonctuDepartD60 + " (" + pcD60d + "%)" + "\tARR. = " + this.exp.compteurPonctuArriveeD60 + " (" + pcD60a + "%)").append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append("NBRE. DEST.\t = " + this.exp.listEscale.size()).append(nl);
        sb.append(nl);
        for (int i = 0; i < this.exp.listEscale.size(); ++i) {
            final Escale escale = this.exp.listEscale.get(i);
            sb.append(escale.code).append("  ").append(escale.compteurNE);
            if ((i + 1) % 5 == 0 || i == this.exp.listEscale.size() - 1) {
                sb.append(nl);
            }
            else {
                sb.append("\t");
            }
        }
        sb.append(nl);
        sb.append(nl);
        if (this.exp.compteurJE != 0) {
            sb.append("J. VOL\t = " + this.exp.compteurJE).append(nl);
        }
        if (this.exp.compteurJActSol != 0) {
            sb.append("J. SOL\t = " + this.exp.compteurJActSol).append(nl);
        }
        if (this.exp.compteurJConges != 0) {
            sb.append("J. CONGES\t = " + this.exp.compteurJConges).append(nl);
        }
        if (this.exp.compteurJSansSolde != 0) {
            sb.append("J. SANS SOLDE\t = " + this.exp.compteurJSansSolde).append(nl);
        }
        if (this.exp.compteurJMaladie != 0) {
            sb.append("J. MALADIE\t = " + this.exp.compteurJMaladie).append(nl);
        }
        if (this.exp.compteurJSyndicat != 0) {
            sb.append("J. SYNDICAT\t = " + this.exp.compteurJSyndicat).append(nl);
        }
        if (this.exp.compteurJRepos != 0) {
            sb.append("J. REPOS\t = " + this.exp.compteurJRepos).append(nl);
        }
        if (this.exp.compteurJRpcPac != 0) {
            sb.append("J. RPC / PAC\t = " + this.exp.compteurJRpcPac).append(nl);
        }
        if (this.exp.compteurJDisp != 0) {
            sb.append("J. DISP.\t = " + this.exp.compteurJDisp).append(nl);
        }
        sb.append(nl);
        final double nJeTotal = this.exp.compteurJE + this.exp.compteurJActSol + this.exp.compteurJDisp + this.exp.compteurJRpcPac + this.exp.compteurJConges + this.exp.compteurJMaladie + this.exp.compteurJSansSolde + this.exp.compteurJRepos + this.exp.compteurJSyndicat;
        sb.append("TOTAL J.\t = " + nJeTotal).append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append("FRAIS REELS").append(nl);
        sb.append(nl);
        for (int j = 0; j < this.exp.listFrais.size(); ++j) {
            final Escale escale2 = this.exp.listFrais.get(j);
            sb.append(escale2.code).append("  ").append(escale2.compteurFR);
            if ((j + 1) % 5 == 0 || j == this.exp.listFrais.size() - 1) {
                sb.append(nl);
            }
            else {
                sb.append("\t");
            }
        }
        sb.append(nl);
        sb.append(nl);
        sb.append((CharSequence)this.exp.sbFRstats);
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
            if (e.getSource() == Dlg_Stats.this.btn_Fermer) {
                Dlg_Stats.this.dispose();
            }
        }
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10 && e.getSource() == Dlg_Stats.this.btn_Fermer) {
                Dlg_Stats.this.dispose();
            }
        }
    }
}
