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
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.io.InputStream;
import ccUtils.Utils;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import chopeCrew.ChopeCrew;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JDialog;

public class Dlg_EditionDB extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane;
    private JPanel pnl_BDD;
    private JPanel pnl_TVRef;
    private JTextField tfld_TVRef;
    private JButton btn_TVRef;
    private JButton btn_ResetTVRef;
    private JButton btn_GetTVRef;
    private JPanel pnl_Baremes;
    private JTextField tfld_Baremes;
    private JButton btn_Baremes;
    private JButton btn_ResetBaremes;
    private JButton btn_GetBaremes;
    private JPanel pnl_Cotis;
    private JTextField tfld_Cotis;
    private JButton btn_Cotis;
    private JButton btn_ResetCotis;
    private JButton btn_GetCotis;
    private JPanel pnl_Escales;
    private JTextField tfld_Escales;
    private JButton btn_Escales;
    private JButton btn_ResetEscales;
    private JButton btn_GetEscales;
    private JPanel pnl_Sortie;
    private JPanel pnl_Sortie_Boutons;
    private JButton btn_Valider;
    private JButton btn_Annuler;
    private Component verticalStrut_BDD_North;
    private Component horizontalStrut_BDD_West;
    private Component horizontalStrut_BDD_East;
    private Component horizontalStrut_TVRef_1;
    private Component horizontalStrut_TVRef_2;
    private Component horizontalStrut_TVRef_3;
    private Component horizontalStrut_Baremes_1;
    private Component horizontalStrut_Baremes_2;
    private Component horizontalStrut_Baremes_3;
    private Component horizontalStrut_Cotis_1;
    private Component horizontalStrut_Cotis_2;
    private Component horizontalStrut_Cotis_3;
    private Component horizontalStrut_Escales_1;
    private Component horizontalStrut_Escales_2;
    private Component horizontalStrut_Escales_3;
    private Component verticalGlue_BDD1;
    private Component verticalGlue_BDD2;
    private Component verticalGlue_BDD3;
    private Component verticalStrut_BDD_Sortie_Top;
    private Component verticalStrut_BDD_Sortie_Bottom;
    private Component horizontalStrut_BDD_Sortie_Boutons;
    
    public Dlg_EditionDB(final Frame owner) {
        super(owner);
        this.jContentPane = null;
        this.pnl_TVRef = null;
        this.tfld_TVRef = null;
        this.btn_TVRef = null;
        this.btn_ResetTVRef = null;
        this.btn_GetTVRef = null;
        this.pnl_Baremes = null;
        this.tfld_Baremes = null;
        this.btn_Baremes = null;
        this.btn_ResetBaremes = null;
        this.btn_GetBaremes = null;
        this.pnl_Cotis = null;
        this.tfld_Cotis = null;
        this.btn_Cotis = null;
        this.btn_ResetCotis = null;
        this.btn_GetCotis = null;
        this.pnl_Escales = null;
        this.tfld_Escales = null;
        this.btn_Escales = null;
        this.btn_ResetEscales = null;
        this.btn_GetEscales = null;
        this.btn_Valider = null;
        this.btn_Annuler = null;
        this.initialize();
    }
    
    private void initialize() {
        this.setSize(640, 400);
        this.setResizable(false);
        this.setTitle("S\u00e9lection des bases de donn\u00e9es");
        this.setModal(true);
        this.setContentPane(this.getJContentPane());
    }
    
    private String openJFC(final String currentDb) {
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(currentDb));
        final int returnVal = fc.showOpenDialog(this);
        if (returnVal == 1) {
            return currentDb;
        }
        return fc.getSelectedFile().getAbsolutePath();
    }
    
    private void actionBtnTVRef() {
        final String newDb = this.openJFC(this.tfld_TVRef.getText());
        this.tfld_TVRef.setText(newDb);
    }
    
    private void actionBtnBaremes() {
        final String newDb = this.openJFC(this.tfld_Baremes.getText());
        this.tfld_Baremes.setText(newDb);
    }
    
    private void actionBtnCotis() {
        final String newDb = this.openJFC(this.tfld_Cotis.getText());
        this.tfld_Cotis.setText(newDb);
    }
    
    private void actionBtnEscales() {
        final String newDb = this.openJFC(this.tfld_Escales.getText());
        this.tfld_Escales.setText(newDb);
    }
    
    private void actionResetTVRef() {
        this.tfld_TVRef.setText("");
    }
    
    private void actionResetBaremes() {
        this.tfld_Baremes.setText("");
    }
    
    private void actionResetCotis() {
        this.tfld_Cotis.setText("");
    }
    
    private void actionResetEscales() {
        this.tfld_Escales.setText("");
    }
    
    private void actionGetTVRef() {
        final JFileChooser fc = new JFileChooser();
        if (ChopeCrew.analyse.isPNT) {
            fc.setSelectedFile(new File("TVRef_" + ChopeCrew.analyse.qualifPNT + ".txt"));
        }
        else if (!ChopeCrew.analyse.isPNT && ChopeCrew.analyse.isMC) {
            fc.setSelectedFile(new File("TVRef_MC.txt"));
        }
        else if (!ChopeCrew.analyse.isPNT && !ChopeCrew.analyse.isMC) {
            fc.setSelectedFile(new File("TVRef_LC.txt"));
        }
        final int returnVal = fc.showSaveDialog(this);
        if (returnVal == 1) {
            return;
        }
        if (fc.getSelectedFile().exists()) {
            final int i = JOptionPane.showConfirmDialog(ChopeCrew.mf, "Le fichier existe d\u00e9j\u00e0, \u00e9craser ?", "ChopeCREW vous informe...", 0, 2);
            if (i != 0) {
                return;
            }
        }
        final String nl = System.getProperty("line.separator");
        String ficSource = "";
        try {
            if (ChopeCrew.analyse.isPNT) {
                ficSource = "/res_databases/dbTVREF_" + ChopeCrew.analyse.qualifPNT + ".txt";
            }
            else if (!ChopeCrew.analyse.isPNT && ChopeCrew.analyse.isMC) {
                ficSource = "/res_databases/dbTVREF_MC.txt";
            }
            else if (!ChopeCrew.analyse.isPNT && !ChopeCrew.analyse.isMC) {
                ficSource = "/res_databases/dbTVREF_LC.txt";
            }
            final InputStream is = this.getClass().getResourceAsStream(ficSource);
            final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            final StringBuilder buffer = new StringBuilder();
            String tmp;
            while ((tmp = in.readLine()) != null) {
                buffer.append(tmp).append(nl);
            }
            in.close();
            is.close();
            Utils.saveToFile(buffer.toString(), fc.getSelectedFile().getAbsolutePath(), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void actionGetBaremes() {
        final JFileChooser fc = new JFileChooser();
        if (ChopeCrew.analyse.isPNT) {
            fc.setSelectedFile(new File("Baremes_PNT.txt"));
        }
        else if (!ChopeCrew.analyse.isPNT) {
            fc.setSelectedFile(new File("Baremes_PNC.txt"));
        }
        final int returnVal = fc.showSaveDialog(this);
        if (returnVal == 1) {
            return;
        }
        if (fc.getSelectedFile().exists()) {
            final int i = JOptionPane.showConfirmDialog(ChopeCrew.mf, "Le fichier existe d\u00e9j\u00e0, \u00e9craser ?", "ChopeCREW vous informe...", 0, 2);
            if (i != 0) {
                return;
            }
        }
        final String nl = System.getProperty("line.separator");
        String ficSource = "";
        try {
            if (ChopeCrew.analyse.isPNT) {
                ficSource = "/res_databases/dbBAREMES_PNT.txt";
            }
            else if (!ChopeCrew.analyse.isPNT) {
                ficSource = "/res_databases/dbBAREMES_PNC.txt";
            }
            final InputStream is = this.getClass().getResourceAsStream(ficSource);
            final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            final StringBuilder buffer = new StringBuilder();
            String tmp;
            while ((tmp = in.readLine()) != null) {
                buffer.append(tmp).append(nl);
            }
            in.close();
            is.close();
            Utils.saveToFile(buffer.toString(), fc.getSelectedFile().getAbsolutePath(), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void actionGetCotis() {
        final JFileChooser fc = new JFileChooser();
        if (ChopeCrew.analyse.isPNT) {
            fc.setSelectedFile(new File("Cotis_PNT.txt"));
        }
        else if (!ChopeCrew.analyse.isPNT) {
            fc.setSelectedFile(new File("Cotis_PNC.txt"));
        }
        final int returnVal = fc.showSaveDialog(this);
        if (returnVal == 1) {
            return;
        }
        if (fc.getSelectedFile().exists()) {
            final int i = JOptionPane.showConfirmDialog(ChopeCrew.mf, "Le fichier existe d\u00e9j\u00e0, \u00e9craser ?", "ChopeCREW vous informe...", 0, 2);
            if (i != 0) {
                return;
            }
        }
        final String nl = System.getProperty("line.separator");
        String ficSource = "";
        try {
            if (ChopeCrew.analyse.isPNT) {
                ficSource = "/res_databases/dbCOTIS_PNT.txt";
            }
            else if (!ChopeCrew.analyse.isPNT) {
                ficSource = "/res_databases/dbCOTIS_PNC.txt";
            }
            final InputStream is = this.getClass().getResourceAsStream(ficSource);
            final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            final StringBuilder buffer = new StringBuilder();
            String tmp;
            while ((tmp = in.readLine()) != null) {
                buffer.append(tmp).append(nl);
            }
            in.close();
            is.close();
            Utils.saveToFile(buffer.toString(), fc.getSelectedFile().getAbsolutePath(), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void actionGetEscales() {
        final JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("Escales.txt"));
        final int returnVal = fc.showSaveDialog(this);
        if (returnVal == 1) {
            return;
        }
        if (fc.getSelectedFile().exists()) {
            final int i = JOptionPane.showConfirmDialog(ChopeCrew.mf, "Le fichier existe d\u00e9j\u00e0, \u00e9craser ?", "ChopeCREW vous informe...", 0, 2);
            if (i != 0) {
                return;
            }
        }
        final String nl = System.getProperty("line.separator");
        try {
            final String ficSource = "/res_databases/dbESCALES.txt";
            final InputStream is = this.getClass().getResourceAsStream(ficSource);
            final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            final StringBuilder buffer = new StringBuilder();
            String tmp;
            while ((tmp = in.readLine()) != null) {
                buffer.append(tmp).append(nl);
            }
            in.close();
            is.close();
            Utils.saveToFile(buffer.toString(), fc.getSelectedFile().getAbsolutePath(), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void actionBtnValider() {
        if (ChopeCrew.analyse.isPNT) {
            ChopeCrew.options.dbTVRef_pnt = this.tfld_TVRef.getText();
            ChopeCrew.options.dbBaremes_pnt = this.tfld_Baremes.getText();
            ChopeCrew.options.dbCotis_pnt = this.tfld_Cotis.getText();
        }
        else if (!ChopeCrew.analyse.isPNT) {
            ChopeCrew.options.dbTVRef_pnc = this.tfld_TVRef.getText();
            ChopeCrew.options.dbBaremes_pnc = this.tfld_Baremes.getText();
            ChopeCrew.options.dbCotis_pnc = this.tfld_Cotis.getText();
        }
        ChopeCrew.options.dbEscales = this.tfld_Escales.getText();
        this.dispose();
    }
    
    private void actionBtnAnnuler() {
        this.dispose();
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BorderLayout());
            this.jContentPane.add(this.getVerticalStrut_BDD_North(), "North");
            this.jContentPane.add(this.getHorizontalStrut_BDD_West(), "West");
            this.jContentPane.add(this.getHorizontalStrut_BDD_East(), "East");
            this.jContentPane.add(this.getPnl_BDD(), "Center");
            this.jContentPane.add(this.getPnl_Sortie(), "South");
        }
        return this.jContentPane;
    }
    
    private JPanel getPnl_BDD() {
        if (this.pnl_BDD == null) {
            (this.pnl_BDD = new JPanel()).setLayout(new BoxLayout(this.pnl_BDD, 3));
            this.pnl_BDD.add(this.getPnl_TVRef());
            this.pnl_BDD.add(this.getVerticalGlue_BDD1());
            this.pnl_BDD.add(this.getPnl_Baremes());
            this.pnl_BDD.add(this.getVerticalGlue_BDD2());
            this.pnl_BDD.add(this.getPnl_Cotis());
            this.pnl_BDD.add(this.getVerticalGlue_BDD3());
            this.pnl_BDD.add(this.getPnl_Escales());
        }
        return this.pnl_BDD;
    }
    
    private JPanel getPnl_TVRef() {
        if (this.pnl_TVRef == null) {
            (this.pnl_TVRef = new JPanel()).setBorder(new TitledBorder(null, "Temps de vol de r\u00e9f\u00e9rence", 4, 2, null, null));
            this.pnl_TVRef.setLayout(new BoxLayout(this.pnl_TVRef, 0));
            this.pnl_TVRef.add(this.getTfld_TVRef());
            this.pnl_TVRef.add(this.getHorizontalStrut_TVRef_1());
            this.pnl_TVRef.add(this.getBtn_TVRef());
            this.pnl_TVRef.add(this.getHorizontalStrut_TVRef_2());
            this.pnl_TVRef.add(this.getBtn_ResetTVRef());
            this.pnl_TVRef.add(this.getHorizontalStrut_TVRef_3());
            this.pnl_TVRef.add(this.getBtn_GetTVRef());
        }
        return this.pnl_TVRef;
    }
    
    private JTextField getTfld_TVRef() {
        if (this.tfld_TVRef == null) {
            (this.tfld_TVRef = new JTextField()).setMaximumSize(new Dimension(256, 32));
            this.tfld_TVRef.setPreferredSize(new Dimension(256, 32));
            this.tfld_TVRef.setFont(new Font("Tahoma", 0, 16));
            if (ChopeCrew.analyse.isPNT) {
                this.tfld_TVRef.setText(ChopeCrew.options.dbTVRef_pnt);
            }
            else if (!ChopeCrew.analyse.isPNT) {
                this.tfld_TVRef.setText(ChopeCrew.options.dbTVRef_pnc);
            }
        }
        return this.tfld_TVRef;
    }
    
    private JButton getBtn_TVRef() {
        if (this.btn_TVRef == null) {
            (this.btn_TVRef = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_TVRef.setPreferredSize(new Dimension(104, 40));
            this.btn_TVRef.setMinimumSize(new Dimension(80, 40));
            this.btn_TVRef.setMaximumSize(new Dimension(104, 40));
            this.btn_TVRef.setFont(new Font("Tahoma", 0, 16));
            this.btn_TVRef.setText("Modifier");
            this.btn_TVRef.setToolTipText("S\u00e9lectionne une DB externe");
            this.btn_TVRef.addMouseListener(new MyMouseAdapter());
            this.btn_TVRef.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_TVRef;
    }
    
    private JButton getBtn_ResetTVRef() {
        if (this.btn_ResetTVRef == null) {
            (this.btn_ResetTVRef = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_ResetTVRef.setPreferredSize(new Dimension(104, 40));
            this.btn_ResetTVRef.setMinimumSize(new Dimension(80, 40));
            this.btn_ResetTVRef.setMaximumSize(new Dimension(104, 40));
            this.btn_ResetTVRef.setFont(new Font("Tahoma", 0, 16));
            this.btn_ResetTVRef.setText("Effacer");
            this.btn_ResetTVRef.setToolTipText("S\u00e9lectionne la DB interne");
            this.btn_ResetTVRef.addMouseListener(new MyMouseAdapter());
            this.btn_ResetTVRef.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ResetTVRef;
    }
    
    private JButton getBtn_GetTVRef() {
        if (this.btn_GetTVRef == null) {
            (this.btn_GetTVRef = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_GetTVRef.setPreferredSize(new Dimension(104, 40));
            this.btn_GetTVRef.setMinimumSize(new Dimension(80, 40));
            this.btn_GetTVRef.setMaximumSize(new Dimension(104, 40));
            this.btn_GetTVRef.setFont(new Font("Tahoma", 0, 16));
            this.btn_GetTVRef.setText("G\u00e9n\u00e9rer");
            this.btn_GetTVRef.setToolTipText("Exporte la DB interne");
            this.btn_GetTVRef.addMouseListener(new MyMouseAdapter());
            this.btn_GetTVRef.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_GetTVRef;
    }
    
    private JPanel getPnl_Baremes() {
        if (this.pnl_Baremes == null) {
            (this.pnl_Baremes = new JPanel()).setBorder(new TitledBorder(null, "Bar\u00e8mes", 4, 2, null, null));
            this.pnl_Baremes.setLayout(new BoxLayout(this.pnl_Baremes, 0));
            this.pnl_Baremes.add(this.getTfld_Baremes());
            this.pnl_Baremes.add(this.getHorizontalStrut_Baremes_1());
            this.pnl_Baremes.add(this.getBtn_Baremes());
            this.pnl_Baremes.add(this.getHorizontalStrut_Baremes_2());
            this.pnl_Baremes.add(this.getBtn_ResetBaremes());
            this.pnl_Baremes.add(this.getHorizontalStrut_Baremes_3());
            this.pnl_Baremes.add(this.getBtn_GetBaremes());
        }
        return this.pnl_Baremes;
    }
    
    private JTextField getTfld_Baremes() {
        if (this.tfld_Baremes == null) {
            (this.tfld_Baremes = new JTextField()).setMaximumSize(new Dimension(256, 32));
            this.tfld_Baremes.setPreferredSize(new Dimension(256, 32));
            this.tfld_Baremes.setFont(new Font("Tahoma", 0, 16));
            if (ChopeCrew.analyse.isPNT) {
                this.tfld_Baremes.setText(ChopeCrew.options.dbBaremes_pnt);
            }
            else if (!ChopeCrew.analyse.isPNT) {
                this.tfld_Baremes.setText(ChopeCrew.options.dbBaremes_pnc);
            }
        }
        return this.tfld_Baremes;
    }
    
    private JButton getBtn_Baremes() {
        if (this.btn_Baremes == null) {
            (this.btn_Baremes = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_Baremes.setPreferredSize(new Dimension(104, 40));
            this.btn_Baremes.setMinimumSize(new Dimension(80, 40));
            this.btn_Baremes.setMaximumSize(new Dimension(104, 40));
            this.btn_Baremes.setFont(new Font("Tahoma", 0, 16));
            this.btn_Baremes.setText("Modifier");
            this.btn_Baremes.setToolTipText("S\u00e9lectionne une DB externe");
            this.btn_Baremes.addMouseListener(new MyMouseAdapter());
            this.btn_Baremes.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Baremes;
    }
    
    private JButton getBtn_ResetBaremes() {
        if (this.btn_ResetBaremes == null) {
            (this.btn_ResetBaremes = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_ResetBaremes.setPreferredSize(new Dimension(104, 40));
            this.btn_ResetBaremes.setMinimumSize(new Dimension(80, 40));
            this.btn_ResetBaremes.setMaximumSize(new Dimension(104, 40));
            this.btn_ResetBaremes.setFont(new Font("Tahoma", 0, 16));
            this.btn_ResetBaremes.setText("Effacer");
            this.btn_ResetBaremes.setToolTipText("S\u00e9lectionne la DB interne");
            this.btn_ResetBaremes.addMouseListener(new MyMouseAdapter());
            this.btn_ResetBaremes.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ResetBaremes;
    }
    
    private JButton getBtn_GetBaremes() {
        if (this.btn_GetBaremes == null) {
            (this.btn_GetBaremes = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_GetBaremes.setPreferredSize(new Dimension(104, 40));
            this.btn_GetBaremes.setMinimumSize(new Dimension(80, 40));
            this.btn_GetBaremes.setMaximumSize(new Dimension(104, 40));
            this.btn_GetBaremes.setFont(new Font("Tahoma", 0, 16));
            this.btn_GetBaremes.setText("G\u00e9n\u00e9rer");
            this.btn_GetBaremes.setToolTipText("Exporte la DB interne");
            this.btn_GetBaremes.addMouseListener(new MyMouseAdapter());
            this.btn_GetBaremes.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_GetBaremes;
    }
    
    private JPanel getPnl_Cotis() {
        if (this.pnl_Cotis == null) {
            (this.pnl_Cotis = new JPanel()).setBorder(new TitledBorder(null, "Cotisations sociales", 4, 2, null, null));
            this.pnl_Cotis.setLayout(new BoxLayout(this.pnl_Cotis, 0));
            this.pnl_Cotis.add(this.getTfld_Cotis());
            this.pnl_Cotis.add(this.getHorizontalStrut_Cotis_1());
            this.pnl_Cotis.add(this.getBtn_Cotis());
            this.pnl_Cotis.add(this.getHorizontalStrut_Cotis_2());
            this.pnl_Cotis.add(this.getBtn_ResetCotis());
            this.pnl_Cotis.add(this.getHorizontalStrut_Cotis_3());
            this.pnl_Cotis.add(this.getBtn_GetCotis());
        }
        return this.pnl_Cotis;
    }
    
    private JTextField getTfld_Cotis() {
        if (this.tfld_Cotis == null) {
            (this.tfld_Cotis = new JTextField()).setMaximumSize(new Dimension(256, 32));
            this.tfld_Cotis.setPreferredSize(new Dimension(256, 32));
            this.tfld_Cotis.setFont(new Font("Tahoma", 0, 16));
            if (ChopeCrew.analyse.isPNT) {
                this.tfld_Cotis.setText(ChopeCrew.options.dbCotis_pnt);
            }
            else if (!ChopeCrew.analyse.isPNT) {
                this.tfld_Cotis.setText(ChopeCrew.options.dbCotis_pnc);
            }
        }
        return this.tfld_Cotis;
    }
    
    private JButton getBtn_Cotis() {
        if (this.btn_Cotis == null) {
            (this.btn_Cotis = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_Cotis.setPreferredSize(new Dimension(104, 40));
            this.btn_Cotis.setMinimumSize(new Dimension(80, 40));
            this.btn_Cotis.setMaximumSize(new Dimension(104, 40));
            this.btn_Cotis.setFont(new Font("Tahoma", 0, 16));
            this.btn_Cotis.setText("Modifier");
            this.btn_Cotis.setToolTipText("S\u00e9lectionne une DB externe");
            this.btn_Cotis.addMouseListener(new MyMouseAdapter());
            this.btn_Cotis.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Cotis;
    }
    
    private JButton getBtn_ResetCotis() {
        if (this.btn_ResetCotis == null) {
            (this.btn_ResetCotis = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_ResetCotis.setPreferredSize(new Dimension(104, 40));
            this.btn_ResetCotis.setMinimumSize(new Dimension(80, 40));
            this.btn_ResetCotis.setMaximumSize(new Dimension(104, 40));
            this.btn_ResetCotis.setFont(new Font("Tahoma", 0, 16));
            this.btn_ResetCotis.setText("Effacer");
            this.btn_ResetCotis.setToolTipText("S\u00e9lectionne la DB interne");
            this.btn_ResetCotis.addMouseListener(new MyMouseAdapter());
            this.btn_ResetCotis.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ResetCotis;
    }
    
    private JButton getBtn_GetCotis() {
        if (this.btn_GetCotis == null) {
            (this.btn_GetCotis = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_GetCotis.setPreferredSize(new Dimension(104, 40));
            this.btn_GetCotis.setMinimumSize(new Dimension(80, 40));
            this.btn_GetCotis.setMaximumSize(new Dimension(104, 40));
            this.btn_GetCotis.setFont(new Font("Tahoma", 0, 16));
            this.btn_GetCotis.setText("G\u00e9n\u00e9rer");
            this.btn_GetCotis.setToolTipText("Exporte la DB interne");
            this.btn_GetCotis.addMouseListener(new MyMouseAdapter());
            this.btn_GetCotis.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_GetCotis;
    }
    
    private JPanel getPnl_Escales() {
        if (this.pnl_Escales == null) {
            (this.pnl_Escales = new JPanel()).setBorder(new TitledBorder(null, "Escales", 4, 2, null, null));
            this.pnl_Escales.setLayout(new BoxLayout(this.pnl_Escales, 0));
            this.pnl_Escales.add(this.getTfld_Escales());
            this.pnl_Escales.add(this.getHorizontalStrut_Escales_1());
            this.pnl_Escales.add(this.getBtn_Escales());
            this.pnl_Escales.add(this.getHorizontalStrut_Escales_2());
            this.pnl_Escales.add(this.getBtn_ResetEscales());
            this.pnl_Escales.add(this.getHorizontalStrut_Escales_3());
            this.pnl_Escales.add(this.getBtn_GetEscales());
        }
        return this.pnl_Escales;
    }
    
    private JTextField getTfld_Escales() {
        if (this.tfld_Escales == null) {
            (this.tfld_Escales = new JTextField()).setMaximumSize(new Dimension(256, 32));
            this.tfld_Escales.setPreferredSize(new Dimension(256, 32));
            this.tfld_Escales.setFont(new Font("Tahoma", 0, 16));
            this.tfld_Escales.setText(ChopeCrew.options.dbEscales);
        }
        return this.tfld_Escales;
    }
    
    private JButton getBtn_Escales() {
        if (this.btn_Escales == null) {
            (this.btn_Escales = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_Escales.setPreferredSize(new Dimension(104, 40));
            this.btn_Escales.setMinimumSize(new Dimension(80, 40));
            this.btn_Escales.setMaximumSize(new Dimension(104, 40));
            this.btn_Escales.setFont(new Font("Tahoma", 0, 16));
            this.btn_Escales.setText("Modifier");
            this.btn_Escales.setToolTipText("S\u00e9lectionne une DB externe");
            this.btn_Escales.addMouseListener(new MyMouseAdapter());
            this.btn_Escales.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Escales;
    }
    
    private JButton getBtn_ResetEscales() {
        if (this.btn_ResetEscales == null) {
            (this.btn_ResetEscales = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_ResetEscales.setPreferredSize(new Dimension(104, 40));
            this.btn_ResetEscales.setMinimumSize(new Dimension(80, 40));
            this.btn_ResetEscales.setMaximumSize(new Dimension(104, 40));
            this.btn_ResetEscales.setFont(new Font("Tahoma", 0, 16));
            this.btn_ResetEscales.setText("Effacer");
            this.btn_ResetEscales.setToolTipText("S\u00e9lectionne la DB interne");
            this.btn_ResetEscales.addMouseListener(new MyMouseAdapter());
            this.btn_ResetEscales.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ResetEscales;
    }
    
    private JButton getBtn_GetEscales() {
        if (this.btn_GetEscales == null) {
            (this.btn_GetEscales = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_GetEscales.setPreferredSize(new Dimension(104, 40));
            this.btn_GetEscales.setMinimumSize(new Dimension(80, 40));
            this.btn_GetEscales.setMaximumSize(new Dimension(104, 40));
            this.btn_GetEscales.setFont(new Font("Tahoma", 0, 16));
            this.btn_GetEscales.setText("G\u00e9n\u00e9rer");
            this.btn_GetEscales.setToolTipText("Exporte la DB interne");
            this.btn_GetEscales.addMouseListener(new MyMouseAdapter());
            this.btn_GetEscales.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_GetEscales;
    }
    
    private JPanel getPnl_Sortie() {
        if (this.pnl_Sortie == null) {
            (this.pnl_Sortie = new JPanel()).setLayout(new BoxLayout(this.pnl_Sortie, 3));
            this.pnl_Sortie.add(this.getVerticalStrut_BDD_Sortie_Top());
            this.pnl_Sortie.add(this.getPnl_Sortie_Boutons());
            this.pnl_Sortie.add(this.getVerticalStrut_BDD_Sortie_Bottom());
        }
        return this.pnl_Sortie;
    }
    
    private JPanel getPnl_Sortie_Boutons() {
        if (this.pnl_Sortie_Boutons == null) {
            (this.pnl_Sortie_Boutons = new JPanel()).setLayout(new BoxLayout(this.pnl_Sortie_Boutons, 0));
            this.pnl_Sortie_Boutons.add(this.getBtn_Valider());
            this.pnl_Sortie_Boutons.add(this.getHorizontalStrut_BDD_Sortie_Boutons());
            this.pnl_Sortie_Boutons.add(this.getBtn_Annuler());
        }
        return this.pnl_Sortie_Boutons;
    }
    
    private JButton getBtn_Valider() {
        if (this.btn_Valider == null) {
            (this.btn_Valider = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_Valider.setPreferredSize(new Dimension(104, 40));
            this.btn_Valider.setMinimumSize(new Dimension(80, 40));
            this.btn_Valider.setMaximumSize(new Dimension(104, 40));
            this.btn_Valider.setFont(new Font("Tahoma", 0, 16));
            this.btn_Valider.setText("Valider");
            this.btn_Valider.addMouseListener(new MyMouseAdapter());
            this.btn_Valider.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Valider;
    }
    
    private JButton getBtn_Annuler() {
        if (this.btn_Annuler == null) {
            (this.btn_Annuler = new JButton()).setMargin(new Insets(2, 2, 2, 2));
            this.btn_Annuler.setPreferredSize(new Dimension(104, 40));
            this.btn_Annuler.setMinimumSize(new Dimension(80, 40));
            this.btn_Annuler.setMaximumSize(new Dimension(104, 40));
            this.btn_Annuler.setFont(new Font("Tahoma", 0, 16));
            this.btn_Annuler.setText("Annuler");
            this.btn_Annuler.addMouseListener(new MyMouseAdapter());
            this.btn_Annuler.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Annuler;
    }
    
    private Component getVerticalStrut_BDD_North() {
        if (this.verticalStrut_BDD_North == null) {
            this.verticalStrut_BDD_North = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_BDD_North;
    }
    
    private Component getHorizontalStrut_BDD_West() {
        if (this.horizontalStrut_BDD_West == null) {
            this.horizontalStrut_BDD_West = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_BDD_West;
    }
    
    private Component getHorizontalStrut_BDD_East() {
        if (this.horizontalStrut_BDD_East == null) {
            this.horizontalStrut_BDD_East = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_BDD_East;
    }
    
    private Component getHorizontalStrut_TVRef_1() {
        if (this.horizontalStrut_TVRef_1 == null) {
            this.horizontalStrut_TVRef_1 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_TVRef_1;
    }
    
    private Component getHorizontalStrut_TVRef_2() {
        if (this.horizontalStrut_TVRef_2 == null) {
            this.horizontalStrut_TVRef_2 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_TVRef_2;
    }
    
    private Component getHorizontalStrut_TVRef_3() {
        if (this.horizontalStrut_TVRef_3 == null) {
            this.horizontalStrut_TVRef_3 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_TVRef_3;
    }
    
    private Component getHorizontalStrut_Baremes_1() {
        if (this.horizontalStrut_Baremes_1 == null) {
            this.horizontalStrut_Baremes_1 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Baremes_1;
    }
    
    private Component getHorizontalStrut_Baremes_2() {
        if (this.horizontalStrut_Baremes_2 == null) {
            this.horizontalStrut_Baremes_2 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Baremes_2;
    }
    
    private Component getHorizontalStrut_Baremes_3() {
        if (this.horizontalStrut_Baremes_3 == null) {
            this.horizontalStrut_Baremes_3 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Baremes_3;
    }
    
    private Component getHorizontalStrut_Cotis_1() {
        if (this.horizontalStrut_Cotis_1 == null) {
            this.horizontalStrut_Cotis_1 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Cotis_1;
    }
    
    private Component getHorizontalStrut_Cotis_2() {
        if (this.horizontalStrut_Cotis_2 == null) {
            this.horizontalStrut_Cotis_2 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Cotis_2;
    }
    
    private Component getHorizontalStrut_Cotis_3() {
        if (this.horizontalStrut_Cotis_3 == null) {
            this.horizontalStrut_Cotis_3 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Cotis_3;
    }
    
    private Component getHorizontalStrut_Escales_1() {
        if (this.horizontalStrut_Escales_1 == null) {
            this.horizontalStrut_Escales_1 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Escales_1;
    }
    
    private Component getHorizontalStrut_Escales_2() {
        if (this.horizontalStrut_Escales_2 == null) {
            this.horizontalStrut_Escales_2 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Escales_2;
    }
    
    private Component getHorizontalStrut_Escales_3() {
        if (this.horizontalStrut_Escales_3 == null) {
            this.horizontalStrut_Escales_3 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Escales_3;
    }
    
    private Component getVerticalGlue_BDD1() {
        if (this.verticalGlue_BDD1 == null) {
            this.verticalGlue_BDD1 = Box.createVerticalGlue();
        }
        return this.verticalGlue_BDD1;
    }
    
    private Component getVerticalGlue_BDD2() {
        if (this.verticalGlue_BDD2 == null) {
            this.verticalGlue_BDD2 = Box.createVerticalGlue();
        }
        return this.verticalGlue_BDD2;
    }
    
    private Component getVerticalGlue_BDD3() {
        if (this.verticalGlue_BDD3 == null) {
            this.verticalGlue_BDD3 = Box.createVerticalGlue();
        }
        return this.verticalGlue_BDD3;
    }
    
    private Component getVerticalStrut_BDD_Sortie_Top() {
        if (this.verticalStrut_BDD_Sortie_Top == null) {
            this.verticalStrut_BDD_Sortie_Top = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_BDD_Sortie_Top;
    }
    
    private Component getVerticalStrut_BDD_Sortie_Bottom() {
        if (this.verticalStrut_BDD_Sortie_Bottom == null) {
            this.verticalStrut_BDD_Sortie_Bottom = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_BDD_Sortie_Bottom;
    }
    
    private Component getHorizontalStrut_BDD_Sortie_Boutons() {
        if (this.horizontalStrut_BDD_Sortie_Boutons == null) {
            this.horizontalStrut_BDD_Sortie_Boutons = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_BDD_Sortie_Boutons;
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_EditionDB.this.btn_TVRef) {
                Dlg_EditionDB.this.actionBtnTVRef();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_Baremes) {
                Dlg_EditionDB.this.actionBtnBaremes();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_Cotis) {
                Dlg_EditionDB.this.actionBtnCotis();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_Escales) {
                Dlg_EditionDB.this.actionBtnEscales();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_ResetTVRef) {
                Dlg_EditionDB.this.actionResetTVRef();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_ResetBaremes) {
                Dlg_EditionDB.this.actionResetBaremes();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_ResetCotis) {
                Dlg_EditionDB.this.actionResetCotis();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_ResetEscales) {
                Dlg_EditionDB.this.actionResetEscales();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_GetTVRef) {
                Dlg_EditionDB.this.actionGetTVRef();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_GetBaremes) {
                Dlg_EditionDB.this.actionGetBaremes();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_GetCotis) {
                Dlg_EditionDB.this.actionGetCotis();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_GetEscales) {
                Dlg_EditionDB.this.actionGetEscales();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_Valider) {
                Dlg_EditionDB.this.actionBtnValider();
            }
            else if (e.getSource() == Dlg_EditionDB.this.btn_Annuler) {
                Dlg_EditionDB.this.actionBtnAnnuler();
            }
        }
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10) {
                if (e.getSource() == Dlg_EditionDB.this.btn_TVRef) {
                    Dlg_EditionDB.this.actionBtnTVRef();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_Baremes) {
                    Dlg_EditionDB.this.actionBtnBaremes();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_Cotis) {
                    Dlg_EditionDB.this.actionBtnCotis();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_Escales) {
                    Dlg_EditionDB.this.actionBtnEscales();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_ResetTVRef) {
                    Dlg_EditionDB.this.actionResetTVRef();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_ResetBaremes) {
                    Dlg_EditionDB.this.actionResetBaremes();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_ResetCotis) {
                    Dlg_EditionDB.this.actionResetCotis();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_ResetEscales) {
                    Dlg_EditionDB.this.actionResetEscales();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_GetTVRef) {
                    Dlg_EditionDB.this.actionGetTVRef();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_GetBaremes) {
                    Dlg_EditionDB.this.actionGetBaremes();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_GetCotis) {
                    Dlg_EditionDB.this.actionGetCotis();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_GetEscales) {
                    Dlg_EditionDB.this.actionGetEscales();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_Valider) {
                    Dlg_EditionDB.this.actionBtnValider();
                }
                else if (e.getSource() == Dlg_EditionDB.this.btn_Annuler) {
                    Dlg_EditionDB.this.actionBtnAnnuler();
                }
            }
        }
    }
}
