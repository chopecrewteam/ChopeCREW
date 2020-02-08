// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.Box;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.text.Format;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemEvent;
import chopeCrew.ChopeCrew;
import java.awt.Container;
import java.text.DecimalFormatSymbols;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.text.DecimalFormat;
import java.beans.PropertyChangeListener;
import java.awt.event.ItemListener;
import javax.swing.JDialog;

public class Dlg_DonneesPerso_PNC extends JDialog implements ItemListener, PropertyChangeListener
{
    private static final long serialVersionUID = 1L;
    private DecimalFormat decimalFormat;
    private JPanel jContentPane;
    private JPanel pnl_DonneesPNC;
    private JPanel pnl_Carriere;
    private JPanel pnl_Carriere_Contenu;
    private JComboBox<String> cbx_Fonction;
    private JComboBox<String> cbx_Ech;
    private JComboBox<String> cbx_Classe;
    private JComboBox<String> cbx_Reseau;
    private JPanel pnl_FixePv;
    private JLabel lbl_Fixe;
    private JLabel lbl_Pv;
    private JLabel lbl_PrimeFonction;
    private JLabel lbl_PrimeHabillement;
    private JLabel lbl_Ikv;
    private JFormattedTextField tfld_Fixe;
    private JFormattedTextField tfld_Pv;
    private JFormattedTextField tfld_PrimeFonction;
    private JFormattedTextField tfld_PrimeHabillement;
    private JFormattedTextField tfld_Ikv;
    private JPanel panel_KmConges;
    private JPanel pnl_Conges;
    private JLabel lbl_CongePV;
    private JFormattedTextField tfld_CongePV;
    private JCheckBox cbox_CoefSecu;
    private JPanel pnl_Kilometres;
    private JLabel lbl_KmCDG;
    private JLabel lbl_KmORY;
    private JLabel lbl_KmBASE;
    private JFormattedTextField tfld_KmCDG;
    private JFormattedTextField tfld_KmORY;
    private JFormattedTextField tfld_KmBASE;
    private JPanel pnl_Sortie;
    private JPanel pnl_Buttons;
    private JButton btn_Bdd;
    private JButton btn_Valider;
    private JButton btn_Annuler;
    private Component verticalStrut_North;
    private Component horizontalStrut_West;
    private Component horizontalStrut_East;
    private Component horizontalStrut_DonneesPNC_2;
    private Component horizontalStrut_DonneesPNC_1;
    private Component horizontalStrut_DonneesPNC_Carriere_1;
    private Component horizontalStrut_DonneesPNC_Carriere_2;
    private Component verticalGlue_Carriere_1;
    private Component verticalGlue_Carriere_2;
    private Component verticalGlue_Carriere_3;
    private Component verticalGlue_Carriere_4;
    private Component verticalGlue_Carriere_5;
    private Component verticalStrut_KmConges_1;
    private Component verticalStrut_Sortie_Top;
    private Component verticalStrut_Sortie_Bottom;
    private Component horizontalStrut_Buttons_1;
    private Component horizontalStrut_Buttons_2;
    
    public Dlg_DonneesPerso_PNC(final Frame owner) {
        super(owner);
        this.decimalFormat = null;
        this.jContentPane = null;
        this.pnl_Carriere = null;
        this.cbx_Fonction = null;
        this.cbx_Ech = null;
        this.cbx_Classe = null;
        this.cbx_Reseau = null;
        this.pnl_FixePv = null;
        this.lbl_Fixe = null;
        this.lbl_Pv = null;
        this.lbl_PrimeFonction = null;
        this.lbl_PrimeHabillement = null;
        this.lbl_Ikv = null;
        this.tfld_Fixe = null;
        this.tfld_Pv = null;
        this.tfld_PrimeFonction = null;
        this.tfld_PrimeHabillement = null;
        this.tfld_Ikv = null;
        this.pnl_Conges = null;
        this.lbl_CongePV = null;
        this.tfld_CongePV = null;
        this.cbox_CoefSecu = null;
        this.pnl_Kilometres = null;
        this.lbl_KmCDG = null;
        this.lbl_KmORY = null;
        this.lbl_KmBASE = null;
        this.tfld_KmCDG = null;
        this.tfld_KmORY = null;
        this.tfld_KmBASE = null;
        this.pnl_Sortie = null;
        this.btn_Bdd = null;
        this.btn_Valider = null;
        this.btn_Annuler = null;
        final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        (this.decimalFormat = new DecimalFormat()).setGroupingUsed(false);
        this.decimalFormat.setMinimumFractionDigits(2);
        this.decimalFormat.setMaximumFractionDigits(4);
        this.decimalFormat.setDecimalSeparatorAlwaysShown(true);
        this.decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        this.initialize();
        this.loadDialogWithDonneesPerso();
        this.cbx_Fonction.addItemListener(this);
        this.cbx_Ech.addItemListener(this);
        this.cbx_Classe.addItemListener(this);
        this.cbx_Reseau.addItemListener(this);
    }
    
    private void initialize() {
        this.setModal(true);
        this.setSize(768, 400);
        this.setResizable(false);
        this.setTitle("Donn\u00e9es personnelles");
        this.setContentPane(this.getJContentPane());
    }
    
    private void loadDialogWithDonneesPerso() {
        this.cbx_Fonction.setSelectedIndex(ChopeCrew.donneesPerso_PNC.fonction_pnc);
        this.cbx_Ech.setSelectedIndex(ChopeCrew.donneesPerso_PNC.echelon_pnc);
        this.cbx_Classe.setSelectedIndex(ChopeCrew.donneesPerso_PNC.classe_pnc);
        this.cbx_Reseau.setSelectedIndex(ChopeCrew.donneesPerso_PNC.reseau_pnc);
        this.tfld_Fixe.setValue(new Double(ChopeCrew.donneesPerso_PNC.fixe_pnc));
        this.tfld_Pv.setValue(new Double(ChopeCrew.donneesPerso_PNC.pv_pnc));
        this.tfld_PrimeFonction.setValue(new Double(ChopeCrew.donneesPerso_PNC.pfonction_pnc));
        this.tfld_PrimeHabillement.setValue(new Double(ChopeCrew.donneesPerso_PNC.phabillement_pnc));
        this.tfld_Ikv.setValue(new Double(ChopeCrew.donneesPerso_PNC.ikv_pnc));
        this.cbox_CoefSecu.setSelected(ChopeCrew.donneesPerso_PNC.abattementSecu_pnc);
        this.tfld_KmCDG.setValue(new Double(ChopeCrew.donneesPerso_PNC.kmCdg_pnc));
        this.tfld_KmORY.setValue(new Double(ChopeCrew.donneesPerso_PNC.kmOry_pnc));
        this.tfld_KmBASE.setValue(new Double(ChopeCrew.donneesPerso_PNC.kmBase_pnc));
        this.tfld_CongePV.setValue(new Double(ChopeCrew.donneesPerso_PNC.congePv_pnc));
    }
    
    private void saveDonneesPersoFromDialog() {
        ChopeCrew.donneesPerso_PNC.fonction_pnc = this.cbx_Fonction.getSelectedIndex();
        ChopeCrew.donneesPerso_PNC.echelon_pnc = this.cbx_Ech.getSelectedIndex();
        ChopeCrew.donneesPerso_PNC.classe_pnc = this.cbx_Classe.getSelectedIndex();
        ChopeCrew.donneesPerso_PNC.reseau_pnc = this.cbx_Reseau.getSelectedIndex();
        ChopeCrew.donneesPerso_PNC.fixe_pnc = ((Number)this.tfld_Fixe.getValue()).doubleValue();
        ChopeCrew.donneesPerso_PNC.pv_pnc = ((Number)this.tfld_Pv.getValue()).doubleValue();
        ChopeCrew.donneesPerso_PNC.pfonction_pnc = ((Number)this.tfld_PrimeFonction.getValue()).doubleValue();
        ChopeCrew.donneesPerso_PNC.phabillement_pnc = ((Number)this.tfld_PrimeHabillement.getValue()).doubleValue();
        ChopeCrew.donneesPerso_PNC.ikv_pnc = ((Number)this.tfld_Ikv.getValue()).doubleValue();
        ChopeCrew.donneesPerso_PNC.abattementSecu_pnc = this.cbox_CoefSecu.isSelected();
        ChopeCrew.donneesPerso_PNC.kmCdg_pnc = ((Number)this.tfld_KmCDG.getValue()).doubleValue();
        ChopeCrew.donneesPerso_PNC.kmOry_pnc = ((Number)this.tfld_KmORY.getValue()).doubleValue();
        ChopeCrew.donneesPerso_PNC.kmBase_pnc = ((Number)this.tfld_KmBASE.getValue()).doubleValue();
        ChopeCrew.donneesPerso_PNC.congePv_pnc = ((Number)this.tfld_CongePV.getValue()).doubleValue();
    }
    
    private void actionBtnBdd() {
        final Dlg_EditionDB dlgEditionDb = new Dlg_EditionDB(ChopeCrew.mf);
        dlgEditionDb.setLocationRelativeTo(this);
        dlgEditionDb.setVisible(true);
    }
    
    private void actionBtnValider() {
        this.saveDonneesPersoFromDialog();
        this.dispose();
    }
    
    private void actionBtnAnnuler() {
        this.dispose();
    }
    
    @Override
    public void itemStateChanged(final ItemEvent e) {
        if (e.getStateChange() == 1) {
            this.saveDonneesPersoFromDialog();
            ChopeCrew.donneesPerso_PNC.calculFixePv();
            this.loadDialogWithDonneesPerso();
        }
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent e) {
        this.cbx_Fonction.removeItemListener(this);
        this.cbx_Ech.removeItemListener(this);
        this.cbx_Classe.removeItemListener(this);
        this.cbx_Reseau.removeItemListener(this);
        this.saveDonneesPersoFromDialog();
        ChopeCrew.donneesPerso_PNC.calculFixePv();
        this.loadDialogWithDonneesPerso();
        this.cbx_Fonction.addItemListener(this);
        this.cbx_Ech.addItemListener(this);
        this.cbx_Classe.addItemListener(this);
        this.cbx_Reseau.addItemListener(this);
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BorderLayout(0, 0));
            this.jContentPane.add(this.getVerticalStrut_North(), "North");
            this.jContentPane.add(this.getHorizontalStrut_East(), "East");
            this.jContentPane.add(this.getHorizontalStrut_West(), "West");
            this.jContentPane.add(this.getPnl_DonneesPNC(), "Center");
            this.jContentPane.add(this.getPnl_Sortie(), "South");
        }
        return this.jContentPane;
    }
    
    private JPanel getPnl_DonneesPNC() {
        if (this.pnl_DonneesPNC == null) {
            (this.pnl_DonneesPNC = new JPanel()).setLayout(new BoxLayout(this.pnl_DonneesPNC, 2));
            this.pnl_DonneesPNC.add(this.getPnl_Carriere());
            this.pnl_DonneesPNC.add(this.getHorizontalStrut_DonneesPNC_1());
            this.pnl_DonneesPNC.add(this.getPnl_FixePv());
            this.pnl_DonneesPNC.add(this.getHorizontalStrut_DonneesPNC_2());
            this.pnl_DonneesPNC.add(this.getPanel_KmConges());
        }
        return this.pnl_DonneesPNC;
    }
    
    private JPanel getPnl_Carriere() {
        if (this.pnl_Carriere == null) {
            (this.pnl_Carriere = new JPanel()).setPreferredSize(new Dimension(240, 10));
            this.pnl_Carriere.setBorder(new LineBorder(null));
            this.pnl_Carriere.setLayout(new BoxLayout(this.pnl_Carriere, 2));
            this.pnl_Carriere.add(this.getHorizontalStrut_DonneesPNC_Carriere_1());
            this.pnl_Carriere.add(this.getPnl_Carriere_Contenu());
            this.pnl_Carriere.add(this.getHorizontalStrut_DonneesPNC_Carriere_2());
        }
        return this.pnl_Carriere;
    }
    
    private JPanel getPnl_Carriere_Contenu() {
        if (this.pnl_Carriere_Contenu == null) {
            (this.pnl_Carriere_Contenu = new JPanel()).setLayout(new BoxLayout(this.pnl_Carriere_Contenu, 3));
            this.pnl_Carriere_Contenu.add(this.getVerticalGlue_Carriere_1());
            this.pnl_Carriere_Contenu.add(this.getCbx_Fonction());
            this.pnl_Carriere_Contenu.add(this.getVerticalGlue_Carriere_2());
            this.pnl_Carriere_Contenu.add(this.getCbx_Ech());
            this.pnl_Carriere_Contenu.add(this.getVerticalGlue_Carriere_3());
            this.pnl_Carriere_Contenu.add(this.getCbx_Classe());
            this.pnl_Carriere_Contenu.add(this.getVerticalGlue_Carriere_4());
            this.pnl_Carriere_Contenu.add(this.getCbx_Reseau());
            this.pnl_Carriere_Contenu.add(this.getVerticalGlue_Carriere_5());
        }
        return this.pnl_Carriere_Contenu;
    }
    
    private JComboBox<String> getCbx_Fonction() {
        if (this.cbx_Fonction == null) {
            final String[] fonctions = { "HOT/STW", "CC", "CCP" };
            (this.cbx_Fonction = new JComboBox<String>()).setPreferredSize(new Dimension(192, 32));
            this.cbx_Fonction.setMinimumSize(new Dimension(192, 64));
            this.cbx_Fonction.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Fonction", 4, 2, null, new Color(0, 0, 0)));
            this.cbx_Fonction.setFont(new Font("Tahoma", 0, 16));
            String[] array;
            for (int length = (array = fonctions).length, i = 0; i < length; ++i) {
                final String s = array[i];
                this.cbx_Fonction.addItem(s);
            }
        }
        return this.cbx_Fonction;
    }
    
    private JComboBox<String> getCbx_Ech() {
        if (this.cbx_Ech == null) {
            final String[] echelons = { "Ech. 1", "Ech. 2", "Ech. 3", "Ech. 4", "Ech. 5", "Ech. 6", "Ech. 7", "Ech. 8", "Ech. 9", "Ech. 10" };
            (this.cbx_Ech = new JComboBox<String>()).setPreferredSize(new Dimension(192, 32));
            this.cbx_Ech.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Echelon", 4, 2, null, new Color(0, 0, 0)));
            this.cbx_Ech.setMinimumSize(new Dimension(192, 64));
            this.cbx_Ech.setFont(new Font("Tahoma", 0, 16));
            String[] array;
            for (int length = (array = echelons).length, i = 0; i < length; ++i) {
                final String s = array[i];
                this.cbx_Ech.addItem(s);
            }
        }
        return this.cbx_Ech;
    }
    
    private JComboBox<String> getCbx_Classe() {
        if (this.cbx_Classe == null) {
            final String[] classes = { "Classe Adaptation", "Classe 4", "Classe 3", "Classe 2", "Classe 1", "Hors Classe" };
            (this.cbx_Classe = new JComboBox<String>()).setPreferredSize(new Dimension(192, 32));
            this.cbx_Classe.setMinimumSize(new Dimension(192, 64));
            this.cbx_Classe.setBorder(new TitledBorder(null, "Classe", 4, 2, null, null));
            this.cbx_Classe.setFont(new Font("Tahoma", 0, 16));
            String[] array;
            for (int length = (array = classes).length, i = 0; i < length; ++i) {
                final String s = array[i];
                this.cbx_Classe.addItem(s);
            }
        }
        return this.cbx_Classe;
    }
    
    private JComboBox<String> getCbx_Reseau() {
        if (this.cbx_Reseau == null) {
            final String[] reseaux = { "MC", "MC BP", "LC" };
            (this.cbx_Reseau = new JComboBox<String>()).setPreferredSize(new Dimension(192, 32));
            this.cbx_Reseau.setMinimumSize(new Dimension(192, 64));
            this.cbx_Reseau.setBorder(new TitledBorder(null, "R\u00e9seau", 4, 2, null, null));
            this.cbx_Reseau.setFont(new Font("Tahoma", 0, 16));
            String[] array;
            for (int length = (array = reseaux).length, i = 0; i < length; ++i) {
                final String s = array[i];
                this.cbx_Reseau.addItem(s);
            }
        }
        return this.cbx_Reseau;
    }
    
    private JPanel getPnl_FixePv() {
        if (this.pnl_FixePv == null) {
            (this.pnl_FixePv = new JPanel()).setPreferredSize(new Dimension(96, 160));
            this.pnl_FixePv.setBorder(new LineBorder(null));
            final GridBagLayout gbl_pnl_FixePv = new GridBagLayout();
            gbl_pnl_FixePv.columnWidths = new int[] { 128, 96, 0 };
            gbl_pnl_FixePv.rowHeights = new int[] { 53, 53, 53, 53, 53, 0 };
            gbl_pnl_FixePv.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
            gbl_pnl_FixePv.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
            this.pnl_FixePv.setLayout(gbl_pnl_FixePv);
            final GridBagConstraints gbc_lbl_Fixe = new GridBagConstraints();
            gbc_lbl_Fixe.fill = 1;
            gbc_lbl_Fixe.insets = new Insets(5, 5, 5, 5);
            gbc_lbl_Fixe.gridx = 0;
            gbc_lbl_Fixe.gridy = 0;
            this.pnl_FixePv.add(this.getLbl_Fixe(), gbc_lbl_Fixe);
            final GridBagConstraints gbc_tfld_Fixe = new GridBagConstraints();
            gbc_tfld_Fixe.fill = 2;
            gbc_tfld_Fixe.insets = new Insets(5, 5, 5, 5);
            gbc_tfld_Fixe.gridx = 1;
            gbc_tfld_Fixe.gridy = 0;
            this.pnl_FixePv.add(this.getTfld_Fixe(), gbc_tfld_Fixe);
            final GridBagConstraints gbc_lbl_Pv = new GridBagConstraints();
            gbc_lbl_Pv.fill = 1;
            gbc_lbl_Pv.insets = new Insets(5, 5, 5, 5);
            gbc_lbl_Pv.gridx = 0;
            gbc_lbl_Pv.gridy = 1;
            this.pnl_FixePv.add(this.getLbl_Pv(), gbc_lbl_Pv);
            final GridBagConstraints gbc_tfld_Pv = new GridBagConstraints();
            gbc_tfld_Pv.fill = 2;
            gbc_tfld_Pv.insets = new Insets(5, 5, 5, 5);
            gbc_tfld_Pv.gridx = 1;
            gbc_tfld_Pv.gridy = 1;
            this.pnl_FixePv.add(this.getTfld_Pv(), gbc_tfld_Pv);
            final GridBagConstraints gbc_lbl_PrimeFonction = new GridBagConstraints();
            gbc_lbl_PrimeFonction.fill = 1;
            gbc_lbl_PrimeFonction.insets = new Insets(5, 5, 5, 5);
            gbc_lbl_PrimeFonction.gridx = 0;
            gbc_lbl_PrimeFonction.gridy = 2;
            this.pnl_FixePv.add(this.getLbl_PrimeFonction(), gbc_lbl_PrimeFonction);
            final GridBagConstraints gbc_tfld_PrimeFonction = new GridBagConstraints();
            gbc_tfld_PrimeFonction.fill = 2;
            gbc_tfld_PrimeFonction.insets = new Insets(5, 5, 5, 5);
            gbc_tfld_PrimeFonction.gridx = 1;
            gbc_tfld_PrimeFonction.gridy = 2;
            this.pnl_FixePv.add(this.getTfld_PrimeFonction(), gbc_tfld_PrimeFonction);
            final GridBagConstraints gbc_lbl_PrimeHabillement = new GridBagConstraints();
            gbc_lbl_PrimeHabillement.fill = 1;
            gbc_lbl_PrimeHabillement.insets = new Insets(5, 5, 5, 5);
            gbc_lbl_PrimeHabillement.gridx = 0;
            gbc_lbl_PrimeHabillement.gridy = 3;
            this.pnl_FixePv.add(this.getLbl_PrimeHabillement(), gbc_lbl_PrimeHabillement);
            final GridBagConstraints gbc_tfld_PrimeHabillement = new GridBagConstraints();
            gbc_tfld_PrimeHabillement.fill = 2;
            gbc_tfld_PrimeHabillement.insets = new Insets(5, 5, 5, 5);
            gbc_tfld_PrimeHabillement.gridx = 1;
            gbc_tfld_PrimeHabillement.gridy = 3;
            this.pnl_FixePv.add(this.getTfld_PrimeHabillement(), gbc_tfld_PrimeHabillement);
            final GridBagConstraints gbc_lbl_Ikv = new GridBagConstraints();
            gbc_lbl_Ikv.fill = 1;
            gbc_lbl_Ikv.insets = new Insets(5, 5, 0, 5);
            gbc_lbl_Ikv.gridx = 0;
            gbc_lbl_Ikv.gridy = 4;
            this.pnl_FixePv.add(this.getLbl_Ikv(), gbc_lbl_Ikv);
            final GridBagConstraints gbc_tfld_Ikv = new GridBagConstraints();
            gbc_tfld_Ikv.insets = new Insets(5, 5, 5, 5);
            gbc_tfld_Ikv.fill = 2;
            gbc_tfld_Ikv.gridx = 1;
            gbc_tfld_Ikv.gridy = 4;
            this.pnl_FixePv.add(this.getTfld_Ikv(), gbc_tfld_Ikv);
        }
        return this.pnl_FixePv;
    }
    
    private JLabel getLbl_Fixe() {
        if (this.lbl_Fixe == null) {
            (this.lbl_Fixe = new JLabel()).setMaximumSize(new Dimension(32767, 32767));
            this.lbl_Fixe.setFont(new Font("Tahoma", 0, 16));
            this.lbl_Fixe.setText("Fixe :");
        }
        return this.lbl_Fixe;
    }
    
    private JLabel getLbl_Pv() {
        if (this.lbl_Pv == null) {
            (this.lbl_Pv = new JLabel()).setMaximumSize(new Dimension(32767, 32767));
            this.lbl_Pv.setFont(new Font("Tahoma", 0, 16));
            this.lbl_Pv.setText("PVI :");
        }
        return this.lbl_Pv;
    }
    
    private JLabel getLbl_PrimeFonction() {
        if (this.lbl_PrimeFonction == null) {
            (this.lbl_PrimeFonction = new JLabel()).setMaximumSize(new Dimension(32767, 32767));
            this.lbl_PrimeFonction.setFont(new Font("Tahoma", 0, 16));
            this.lbl_PrimeFonction.setText("Pr. fonction :");
        }
        return this.lbl_PrimeFonction;
    }
    
    private JLabel getLbl_PrimeHabillement() {
        if (this.lbl_PrimeHabillement == null) {
            (this.lbl_PrimeHabillement = new JLabel()).setMaximumSize(new Dimension(32767, 32767));
            this.lbl_PrimeHabillement.setFont(new Font("Tahoma", 0, 16));
            this.lbl_PrimeHabillement.setText("Pr. habillement :");
        }
        return this.lbl_PrimeHabillement;
    }
    
    private JLabel getLbl_Ikv() {
        if (this.lbl_Ikv == null) {
            (this.lbl_Ikv = new JLabel()).setMaximumSize(new Dimension(32767, 32767));
            this.lbl_Ikv.setFont(new Font("Tahoma", 0, 16));
            this.lbl_Ikv.setText("IKV :");
        }
        return this.lbl_Ikv;
    }
    
    private JFormattedTextField getTfld_Fixe() {
        if (this.tfld_Fixe == null) {
            (this.tfld_Fixe = new JFormattedTextField(this.decimalFormat)).setHorizontalAlignment(4);
            this.tfld_Fixe.setText("12345,45");
            this.tfld_Fixe.setBackground(new Color(200, 230, 235));
            this.tfld_Fixe.setFont(new Font("Tahoma", 0, 16));
            this.tfld_Fixe.setToolTipText("Vous pouvez forcer une valeur pour le fixe");
        }
        return this.tfld_Fixe;
    }
    
    private JFormattedTextField getTfld_Pv() {
        if (this.tfld_Pv == null) {
            (this.tfld_Pv = new JFormattedTextField(this.decimalFormat)).setHorizontalAlignment(4);
            this.tfld_Pv.setBackground(new Color(200, 230, 235));
            this.tfld_Pv.setFont(new Font("Tahoma", 0, 16));
            this.tfld_Pv.setToolTipText("Vous pouvez forcer une valeur pour la prime de vol");
        }
        return this.tfld_Pv;
    }
    
    private JFormattedTextField getTfld_PrimeFonction() {
        if (this.tfld_PrimeFonction == null) {
            (this.tfld_PrimeFonction = new JFormattedTextField(this.decimalFormat)).setHorizontalAlignment(4);
            this.tfld_PrimeFonction.setBackground(new Color(200, 230, 235));
            this.tfld_PrimeFonction.setFont(new Font("Tahoma", 0, 16));
            this.tfld_PrimeFonction.setToolTipText("Vous pouvez forcer une valeur pour la prime de fonction");
        }
        return this.tfld_PrimeFonction;
    }
    
    private JFormattedTextField getTfld_PrimeHabillement() {
        if (this.tfld_PrimeHabillement == null) {
            (this.tfld_PrimeHabillement = new JFormattedTextField(this.decimalFormat)).setHorizontalAlignment(4);
            this.tfld_PrimeHabillement.setBackground(new Color(200, 230, 235));
            this.tfld_PrimeHabillement.setFont(new Font("Tahoma", 0, 16));
            this.tfld_PrimeHabillement.setToolTipText("Vous pouvez forcer une valeur pour la prime d'habillement");
        }
        return this.tfld_PrimeHabillement;
    }
    
    private JFormattedTextField getTfld_Ikv() {
        if (this.tfld_Ikv == null) {
            (this.tfld_Ikv = new JFormattedTextField(this.decimalFormat)).setHorizontalAlignment(4);
            this.tfld_Ikv.setBackground(new Color(200, 230, 235));
            this.tfld_Ikv.setFont(new Font("Tahoma", 0, 16));
            this.tfld_Ikv.setToolTipText("Vous pouvez forcer une valeur pour l'indemnit\u00e9 kilom\u00e9trique");
        }
        return this.tfld_Ikv;
    }
    
    private JPanel getPanel_KmConges() {
        if (this.panel_KmConges == null) {
            (this.panel_KmConges = new JPanel()).setLayout(new BoxLayout(this.panel_KmConges, 3));
            this.panel_KmConges.add(this.getPnl_Kilometres());
            this.panel_KmConges.add(this.getVerticalStrut_KmConges_1());
            this.panel_KmConges.add(this.getPnl_Conges());
        }
        return this.panel_KmConges;
    }
    
    private JPanel getPnl_Conges() {
        if (this.pnl_Conges == null) {
            (this.pnl_Conges = new JPanel()).setPreferredSize(new Dimension(64, 0));
            this.pnl_Conges.setBorder(new LineBorder(null));
            final GridBagLayout gbl_pnl_Conges = new GridBagLayout();
            gbl_pnl_Conges.columnWidths = new int[] { 128, 64, 0 };
            gbl_pnl_Conges.rowHeights = new int[] { 20, 20, 0 };
            gbl_pnl_Conges.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
            gbl_pnl_Conges.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
            this.pnl_Conges.setLayout(gbl_pnl_Conges);
            final GridBagConstraints gbc_lbl_CongePV = new GridBagConstraints();
            gbc_lbl_CongePV.fill = 1;
            gbc_lbl_CongePV.insets = new Insets(5, 5, 5, 5);
            gbc_lbl_CongePV.gridx = 0;
            gbc_lbl_CongePV.gridy = 0;
            this.pnl_Conges.add(this.getLbl_CongePV(), gbc_lbl_CongePV);
            final GridBagConstraints gbc_tfld_CongePV = new GridBagConstraints();
            gbc_tfld_CongePV.fill = 1;
            gbc_tfld_CongePV.insets = new Insets(5, 5, 5, 5);
            gbc_tfld_CongePV.gridx = 1;
            gbc_tfld_CongePV.gridy = 0;
            this.pnl_Conges.add(this.getTfld_CongePV(), gbc_tfld_CongePV);
            final GridBagConstraints gbc_cbox_CoefSecu = new GridBagConstraints();
            gbc_cbox_CoefSecu.insets = new Insets(5, 5, 5, 5);
            gbc_cbox_CoefSecu.fill = 1;
            gbc_cbox_CoefSecu.gridwidth = 2;
            gbc_cbox_CoefSecu.gridx = 0;
            gbc_cbox_CoefSecu.gridy = 1;
            this.pnl_Conges.add(this.getCbox_CoefSecu(), gbc_cbox_CoefSecu);
        }
        return this.pnl_Conges;
    }
    
    private JLabel getLbl_CongePV() {
        if (this.lbl_CongePV == null) {
            (this.lbl_CongePV = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.lbl_CongePV.setText("CPLT SGMM :");
        }
        return this.lbl_CongePV;
    }
    
    private JFormattedTextField getTfld_CongePV() {
        if (this.tfld_CongePV == null) {
            (this.tfld_CongePV = new JFormattedTextField(this.decimalFormat)).setFont(new Font("Tahoma", 0, 16));
            this.tfld_CongePV.setToolTipText("Traitement des cong\u00e9s");
        }
        return this.tfld_CongePV;
    }
    
    private JCheckBox getCbox_CoefSecu() {
        if (this.cbox_CoefSecu == null) {
            (this.cbox_CoefSecu = new CustomCheckBox()).setHorizontalTextPosition(2);
            this.cbox_CoefSecu.setFont(new Font("Tahoma", 0, 16));
            this.cbox_CoefSecu.setText("Abattement S.S.     ");
            this.cbox_CoefSecu.setToolTipText("Applique l'abattement de 30% sur la base de calcul des cotisations sociales (plafond \u00e0 7600 \u20ac par an)");
        }
        return this.cbox_CoefSecu;
    }
    
    private JPanel getPnl_Kilometres() {
        if (this.pnl_Kilometres == null) {
            (this.pnl_Kilometres = new JPanel()).setPreferredSize(new Dimension(64, 0));
            this.pnl_Kilometres.setBorder(new LineBorder(null));
            final GridBagLayout gbl_pnl_Kilometres = new GridBagLayout();
            gbl_pnl_Kilometres.columnWidths = new int[] { 128, 64, 0 };
            gbl_pnl_Kilometres.rowHeights = new int[] { 25, 25, 25, 0 };
            gbl_pnl_Kilometres.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
            gbl_pnl_Kilometres.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
            this.pnl_Kilometres.setLayout(gbl_pnl_Kilometres);
            final GridBagConstraints gbc_lbl_KmCDG = new GridBagConstraints();
            gbc_lbl_KmCDG.fill = 1;
            gbc_lbl_KmCDG.insets = new Insets(5, 5, 5, 5);
            gbc_lbl_KmCDG.gridx = 0;
            gbc_lbl_KmCDG.gridy = 0;
            this.pnl_Kilometres.add(this.getLbl_KmCDG(), gbc_lbl_KmCDG);
            final GridBagConstraints gbc_tfld_KmCDG = new GridBagConstraints();
            gbc_tfld_KmCDG.fill = 1;
            gbc_tfld_KmCDG.insets = new Insets(5, 5, 5, 5);
            gbc_tfld_KmCDG.gridx = 1;
            gbc_tfld_KmCDG.gridy = 0;
            this.pnl_Kilometres.add(this.getTfld_KmCDG(), gbc_tfld_KmCDG);
            final GridBagConstraints gbc_lbl_KmORY = new GridBagConstraints();
            gbc_lbl_KmORY.fill = 1;
            gbc_lbl_KmORY.insets = new Insets(5, 5, 5, 5);
            gbc_lbl_KmORY.gridx = 0;
            gbc_lbl_KmORY.gridy = 1;
            this.pnl_Kilometres.add(this.getLbl_KmORY(), gbc_lbl_KmORY);
            final GridBagConstraints gbc_tfld_KmORY = new GridBagConstraints();
            gbc_tfld_KmORY.fill = 1;
            gbc_tfld_KmORY.insets = new Insets(5, 5, 5, 5);
            gbc_tfld_KmORY.gridx = 1;
            gbc_tfld_KmORY.gridy = 1;
            this.pnl_Kilometres.add(this.getTfld_KmORY(), gbc_tfld_KmORY);
            final GridBagConstraints gbc_lbl_KmBASE = new GridBagConstraints();
            gbc_lbl_KmBASE.fill = 1;
            gbc_lbl_KmBASE.insets = new Insets(5, 5, 5, 5);
            gbc_lbl_KmBASE.gridx = 0;
            gbc_lbl_KmBASE.gridy = 2;
            this.pnl_Kilometres.add(this.getLbl_KmBASE(), gbc_lbl_KmBASE);
            final GridBagConstraints gbc_tfld_KmBASE = new GridBagConstraints();
            gbc_tfld_KmBASE.insets = new Insets(5, 5, 5, 5);
            gbc_tfld_KmBASE.fill = 1;
            gbc_tfld_KmBASE.gridx = 1;
            gbc_tfld_KmBASE.gridy = 2;
            this.pnl_Kilometres.add(this.getTfld_KmBASE(), gbc_tfld_KmBASE);
        }
        return this.pnl_Kilometres;
    }
    
    private JLabel getLbl_KmCDG() {
        if (this.lbl_KmCDG == null) {
            (this.lbl_KmCDG = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.lbl_KmCDG.setText("Kms CDG :");
        }
        return this.lbl_KmCDG;
    }
    
    private JLabel getLbl_KmORY() {
        if (this.lbl_KmORY == null) {
            (this.lbl_KmORY = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.lbl_KmORY.setText("Kms ORY :");
        }
        return this.lbl_KmORY;
    }
    
    private JLabel getLbl_KmBASE() {
        if (this.lbl_KmBASE == null) {
            (this.lbl_KmBASE = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.lbl_KmBASE.setText("Kms Base :");
        }
        return this.lbl_KmBASE;
    }
    
    private JFormattedTextField getTfld_KmCDG() {
        if (this.tfld_KmCDG == null) {
            (this.tfld_KmCDG = new JFormattedTextField(this.decimalFormat)).setHorizontalAlignment(4);
            this.tfld_KmCDG.setText("\r\n");
            this.tfld_KmCDG.setFont(new Font("Tahoma", 0, 16));
            this.tfld_KmCDG.setToolTipText("Distance domicile-CDG");
        }
        return this.tfld_KmCDG;
    }
    
    private JFormattedTextField getTfld_KmORY() {
        if (this.tfld_KmORY == null) {
            (this.tfld_KmORY = new JFormattedTextField(this.decimalFormat)).setHorizontalAlignment(4);
            this.tfld_KmORY.setFont(new Font("Tahoma", 0, 16));
            this.tfld_KmORY.setToolTipText("Distance domicile-ORY");
        }
        return this.tfld_KmORY;
    }
    
    private JFormattedTextField getTfld_KmBASE() {
        if (this.tfld_KmBASE == null) {
            (this.tfld_KmBASE = new JFormattedTextField(this.decimalFormat)).setHorizontalAlignment(4);
            this.tfld_KmBASE.setFont(new Font("Tahoma", 0, 16));
            this.tfld_KmBASE.setToolTipText("Distance domicile-Base provinciale");
        }
        return this.tfld_KmBASE;
    }
    
    private JPanel getPnl_Sortie() {
        if (this.pnl_Sortie == null) {
            (this.pnl_Sortie = new JPanel()).setLayout(new BoxLayout(this.pnl_Sortie, 3));
            this.pnl_Sortie.add(this.getVerticalStrut_Sortie_Top());
            this.pnl_Sortie.add(this.getPnl_Buttons());
            this.pnl_Sortie.add(this.getVerticalStrut_Sortie_Bottom());
        }
        return this.pnl_Sortie;
    }
    
    private JPanel getPnl_Buttons() {
        if (this.pnl_Buttons == null) {
            (this.pnl_Buttons = new JPanel()).setLayout(new BoxLayout(this.pnl_Buttons, 0));
            this.pnl_Buttons.add(this.getBtn_Bdd());
            this.pnl_Buttons.add(this.getHorizontalStrut_Buttons_1());
            this.pnl_Buttons.add(this.getBtn_Valider());
            this.pnl_Buttons.add(this.getHorizontalStrut_Buttons_2());
            this.pnl_Buttons.add(this.getBtn_Annuler());
        }
        return this.pnl_Buttons;
    }
    
    private JButton getBtn_Bdd() {
        if (this.btn_Bdd == null) {
            (this.btn_Bdd = new JButton()).setMargin(new Insets(2, 4, 2, 4));
            this.btn_Bdd.setPreferredSize(new Dimension(160, 40));
            this.btn_Bdd.setFont(new Font("Tahoma", 0, 16));
            this.btn_Bdd.setText("Bases de donn\u00e9es");
            this.btn_Bdd.addMouseListener(new MyMouseAdapter());
            this.btn_Bdd.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Bdd;
    }
    
    private JButton getBtn_Valider() {
        if (this.btn_Valider == null) {
            (this.btn_Valider = new JButton()).setMargin(new Insets(2, 4, 2, 4));
            this.btn_Valider.setPreferredSize(new Dimension(160, 40));
            this.btn_Valider.setMinimumSize(new Dimension(128, 40));
            this.btn_Valider.setMaximumSize(new Dimension(128, 40));
            this.btn_Valider.setFont(new Font("Tahoma", 0, 16));
            this.btn_Valider.setText("Valider");
            this.btn_Valider.addMouseListener(new MyMouseAdapter());
            this.btn_Valider.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Valider;
    }
    
    private JButton getBtn_Annuler() {
        if (this.btn_Annuler == null) {
            (this.btn_Annuler = new JButton()).setMargin(new Insets(2, 4, 2, 4));
            this.btn_Annuler.setPreferredSize(new Dimension(160, 40));
            this.btn_Annuler.setMinimumSize(new Dimension(128, 40));
            this.btn_Annuler.setMaximumSize(new Dimension(128, 40));
            this.btn_Annuler.setFont(new Font("Tahoma", 0, 16));
            this.btn_Annuler.setText("Annuler");
            this.btn_Annuler.addMouseListener(new MyMouseAdapter());
            this.btn_Annuler.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Annuler;
    }
    
    private Component getVerticalStrut_North() {
        if (this.verticalStrut_North == null) {
            this.verticalStrut_North = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_North;
    }
    
    private Component getHorizontalStrut_West() {
        if (this.horizontalStrut_West == null) {
            this.horizontalStrut_West = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_West;
    }
    
    private Component getHorizontalStrut_East() {
        if (this.horizontalStrut_East == null) {
            this.horizontalStrut_East = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_East;
    }
    
    private Component getHorizontalStrut_DonneesPNC_1() {
        if (this.horizontalStrut_DonneesPNC_1 == null) {
            this.horizontalStrut_DonneesPNC_1 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_DonneesPNC_1;
    }
    
    private Component getHorizontalStrut_DonneesPNC_2() {
        if (this.horizontalStrut_DonneesPNC_2 == null) {
            this.horizontalStrut_DonneesPNC_2 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_DonneesPNC_2;
    }
    
    private Component getHorizontalStrut_DonneesPNC_Carriere_1() {
        if (this.horizontalStrut_DonneesPNC_Carriere_1 == null) {
            this.horizontalStrut_DonneesPNC_Carriere_1 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_DonneesPNC_Carriere_1;
    }
    
    private Component getHorizontalStrut_DonneesPNC_Carriere_2() {
        if (this.horizontalStrut_DonneesPNC_Carriere_2 == null) {
            this.horizontalStrut_DonneesPNC_Carriere_2 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_DonneesPNC_Carriere_2;
    }
    
    private Component getVerticalGlue_Carriere_1() {
        if (this.verticalGlue_Carriere_1 == null) {
            this.verticalGlue_Carriere_1 = Box.createVerticalGlue();
        }
        return this.verticalGlue_Carriere_1;
    }
    
    private Component getVerticalGlue_Carriere_2() {
        if (this.verticalGlue_Carriere_2 == null) {
            this.verticalGlue_Carriere_2 = Box.createVerticalGlue();
        }
        return this.verticalGlue_Carriere_2;
    }
    
    private Component getVerticalGlue_Carriere_3() {
        if (this.verticalGlue_Carriere_3 == null) {
            this.verticalGlue_Carriere_3 = Box.createVerticalGlue();
        }
        return this.verticalGlue_Carriere_3;
    }
    
    private Component getVerticalGlue_Carriere_4() {
        if (this.verticalGlue_Carriere_4 == null) {
            this.verticalGlue_Carriere_4 = Box.createVerticalGlue();
        }
        return this.verticalGlue_Carriere_4;
    }
    
    private Component getVerticalGlue_Carriere_5() {
        if (this.verticalGlue_Carriere_5 == null) {
            this.verticalGlue_Carriere_5 = Box.createVerticalGlue();
        }
        return this.verticalGlue_Carriere_5;
    }
    
    private Component getVerticalStrut_KmConges_1() {
        if (this.verticalStrut_KmConges_1 == null) {
            this.verticalStrut_KmConges_1 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_KmConges_1;
    }
    
    private Component getVerticalStrut_Sortie_Top() {
        if (this.verticalStrut_Sortie_Top == null) {
            this.verticalStrut_Sortie_Top = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Sortie_Top;
    }
    
    private Component getVerticalStrut_Sortie_Bottom() {
        if (this.verticalStrut_Sortie_Bottom == null) {
            this.verticalStrut_Sortie_Bottom = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Sortie_Bottom;
    }
    
    private Component getHorizontalStrut_Buttons_1() {
        if (this.horizontalStrut_Buttons_1 == null) {
            this.horizontalStrut_Buttons_1 = Box.createHorizontalStrut(140);
        }
        return this.horizontalStrut_Buttons_1;
    }
    
    private Component getHorizontalStrut_Buttons_2() {
        if (this.horizontalStrut_Buttons_2 == null) {
            this.horizontalStrut_Buttons_2 = Box.createHorizontalStrut(10);
        }
        return this.horizontalStrut_Buttons_2;
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10) {
                if (e.getSource() == Dlg_DonneesPerso_PNC.this.btn_Valider) {
                    Dlg_DonneesPerso_PNC.this.actionBtnValider();
                }
                else if (e.getSource() == Dlg_DonneesPerso_PNC.this.btn_Annuler) {
                    Dlg_DonneesPerso_PNC.this.actionBtnAnnuler();
                }
                else if (e.getSource() == Dlg_DonneesPerso_PNC.this.btn_Bdd) {
                    Dlg_DonneesPerso_PNC.this.actionBtnBdd();
                }
            }
        }
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_DonneesPerso_PNC.this.btn_Valider) {
                Dlg_DonneesPerso_PNC.this.actionBtnValider();
            }
            else if (e.getSource() == Dlg_DonneesPerso_PNC.this.btn_Annuler) {
                Dlg_DonneesPerso_PNC.this.actionBtnAnnuler();
            }
            else if (e.getSource() == Dlg_DonneesPerso_PNC.this.btn_Bdd) {
                Dlg_DonneesPerso_PNC.this.actionBtnBdd();
            }
        }
    }
}
