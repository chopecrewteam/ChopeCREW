// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.JTableHeader;
import ccGUI.EP4Table.EP4TableHeader;
import javax.swing.table.TableModel;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.Box;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.event.TableModelEvent;
import java.beans.PropertyChangeEvent;
import ccUtils.Utils;
import ccGUI.EP4Table.EP4TableModel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingUtilities;
import java.awt.Container;
import chopeCrew.ChopeCrew;
import java.text.Format;
import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.awt.Frame;
import javax.swing.text.DefaultFormatterFactory;
import ccGUI.EP4Table.EP4Table;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import ccEngine.EP4_Pnc;
import ccEngine.EP4_Pnt;
import javax.swing.event.TableModelListener;
import java.beans.PropertyChangeListener;
import javax.swing.JDialog;

public class Dlg_Ep4 extends JDialog implements PropertyChangeListener, TableModelListener
{
    private static final long serialVersionUID = 1L;
    private EP4_Pnt ep4_pnt;
    private EP4_Pnc ep4_pnc;
    private JPanel jContentPane;
    private JTabbedPane jTabbedPane;
    private Component horizontalStrut_West;
    private Component horizontalStrut_East;
    private Component verticalStrut_South;
    private Component verticalStrut_North;
    private JPanel pnl_Paye;
    private JScrollPane spnl_Paye;
    private JTextArea txta_Paye;
    private JPanel pnl_Paye_Commandes;
    private JPanel pnl_Corrections;
    private JLabel lbl_cBF;
    private JLabel lbl_cIKV;
    private JLabel lbl_cIRimp;
    private JLabel lbl_cIRnimp;
    private JFormattedTextField tfld_cBF;
    private JFormattedTextField tfld_cIKV;
    private JFormattedTextField tfld_cIRimp;
    private JFormattedTextField tfld_cIRnimp;
    private JToggleButton tglbtn_ProgReal;
    private JScrollPane spnl_TempsVol;
    private EP4Table jTable_TempsVol;
    private JScrollPane spnl_HCp;
    private EP4Table jTable_HCp;
    private JScrollPane spnl_HCr;
    private EP4Table jTable_HCr;
    private JScrollPane spnl_HCRp;
    private EP4Table jTable_HCRp;
    private JScrollPane spnl_HCRr;
    private EP4Table jTable_HCRr;
    private JScrollPane spnl_IRp;
    private EP4Table jTable_IRp;
    private JScrollPane spnl_IRr;
    private EP4Table jTable_IRr;
    private JScrollPane spnl_DecompteEP4;
    private EP4Table jTable_DecompteEP4;
    private JScrollPane spnl_HoraireEP4;
    private EP4Table jTable_HoraireEP4;
    private Component horizontalStrut_PnlPaye;
    private Component verticalStrut_PnlCommandes;
    private Component verticalGlue_PnlCommandes;
    private DefaultFormatterFactory defaultFormatterFactory;
    public boolean tableChanged;
    
    public Dlg_Ep4(final Frame owner) {
        super(owner);
        this.ep4_pnt = null;
        this.ep4_pnc = null;
        this.jContentPane = null;
        this.jTabbedPane = null;
        this.pnl_Paye = null;
        this.spnl_Paye = null;
        this.txta_Paye = null;
        this.pnl_Paye_Commandes = null;
        this.pnl_Corrections = null;
        this.lbl_cBF = null;
        this.lbl_cIKV = null;
        this.lbl_cIRimp = null;
        this.lbl_cIRnimp = null;
        this.tfld_cBF = null;
        this.tfld_cIKV = null;
        this.tfld_cIRimp = null;
        this.tfld_cIRnimp = null;
        this.spnl_TempsVol = null;
        this.jTable_TempsVol = null;
        this.spnl_HCp = null;
        this.jTable_HCp = null;
        this.spnl_HCr = null;
        this.jTable_HCr = null;
        this.spnl_HCRp = null;
        this.jTable_HCRp = null;
        this.spnl_HCRr = null;
        this.jTable_HCRr = null;
        this.spnl_IRp = null;
        this.jTable_IRp = null;
        this.spnl_IRr = null;
        this.jTable_IRr = null;
        this.spnl_DecompteEP4 = null;
        this.jTable_DecompteEP4 = null;
        this.spnl_HoraireEP4 = null;
        this.jTable_HoraireEP4 = null;
        this.defaultFormatterFactory = null;
        final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        final DecimalFormat decimalFormat = new DecimalFormat();
        final NumberFormatter numberFormatter = new NumberFormatter();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setGroupingUsed(false);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(4);
        decimalFormat.setDecimalSeparatorAlwaysShown(true);
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        numberFormatter.setFormat(decimalFormat);
        (this.defaultFormatterFactory = new DefaultFormatterFactory()).setDefaultFormatter(numberFormatter);
        this.ep4_pnt = new EP4_Pnt();
        this.ep4_pnc = new EP4_Pnc();
        this.initialize();
        this.updatePaye();
        this.updateTables();
        this.loadDialogWithDonneesPerso();
    }
    
    private void initialize() {
        this.setModal(true);
        this.setSize(1024, 630);
        this.setResizable(true);
        this.setTitle("Paye & Activit\u00e9 - " + ChopeCrew.analyse.moisLit + " " + ChopeCrew.analyse.anneeInt);
        this.setContentPane(this.getJContentPane());
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                Dlg_Ep4.this.tglbtn_ProgReal.requestFocusInWindow();
            }
        };
        SwingUtilities.invokeLater(run);
    }
    
    private void loadDialogWithDonneesPerso() {
        if (ChopeCrew.analyse.isPNT) {
            this.tfld_cBF.setValue(new Double(ChopeCrew.donneesPerso_PNT.cBF_pnt));
            this.tfld_cIKV.setValue(new Double(ChopeCrew.donneesPerso_PNT.cIKV_pnt));
            this.tfld_cIRimp.setValue(new Double(ChopeCrew.donneesPerso_PNT.cIRimp_pnt));
            this.tfld_cIRnimp.setValue(new Double(ChopeCrew.donneesPerso_PNT.cIRnimp_pnt));
            this.tglbtn_ProgReal.setSelected(ChopeCrew.options.ep4_progreal);
        }
        else if (!ChopeCrew.analyse.isPNT) {
            this.tfld_cBF.setValue(new Double(ChopeCrew.donneesPerso_PNC.cBF_pnc));
            this.tfld_cIKV.setValue(new Double(ChopeCrew.donneesPerso_PNC.cIKV_pnc));
            this.tfld_cIRimp.setValue(new Double(ChopeCrew.donneesPerso_PNC.cIRimp_pnc));
            this.tfld_cIRnimp.setValue(new Double(ChopeCrew.donneesPerso_PNC.cIRnimp_pnc));
            this.tglbtn_ProgReal.setSelected(ChopeCrew.options.ep4_progreal);
        }
        this.tfld_cBF.addPropertyChangeListener("value", this);
        this.tfld_cIKV.addPropertyChangeListener("value", this);
        this.tfld_cIRimp.addPropertyChangeListener("value", this);
        this.tfld_cIRnimp.addPropertyChangeListener("value", this);
    }
    
    private void saveDonneesPersoFromDialog() {
        if (ChopeCrew.analyse.isPNT) {
            ChopeCrew.donneesPerso_PNT.cBF_pnt = ((Number)this.tfld_cBF.getValue()).doubleValue();
            ChopeCrew.donneesPerso_PNT.cIKV_pnt = ((Number)this.tfld_cIKV.getValue()).doubleValue();
            ChopeCrew.donneesPerso_PNT.cIRimp_pnt = ((Number)this.tfld_cIRimp.getValue()).doubleValue();
            ChopeCrew.donneesPerso_PNT.cIRnimp_pnt = ((Number)this.tfld_cIRnimp.getValue()).doubleValue();
            ChopeCrew.options.ep4_progreal = this.tglbtn_ProgReal.isSelected();
        }
        else if (!ChopeCrew.analyse.isPNT) {
            ChopeCrew.donneesPerso_PNC.cBF_pnc = ((Number)this.tfld_cBF.getValue()).doubleValue();
            ChopeCrew.donneesPerso_PNC.cIKV_pnc = ((Number)this.tfld_cIKV.getValue()).doubleValue();
            ChopeCrew.donneesPerso_PNC.cIRimp_pnc = ((Number)this.tfld_cIRimp.getValue()).doubleValue();
            ChopeCrew.donneesPerso_PNC.cIRnimp_pnc = ((Number)this.tfld_cIRnimp.getValue()).doubleValue();
            ChopeCrew.options.ep4_progreal = this.tglbtn_ProgReal.isSelected();
        }
    }
    
    private void updatePaye() {
        if (ChopeCrew.analyse.isPNT) {
            this.ep4_pnt.calculEP4(ChopeCrew.analyse.moisInt, ChopeCrew.donneesPerso_PNT);
            if (!ChopeCrew.options.ep4_progreal) {
                this.spnl_Paye.setBorder(BorderFactory.createTitledBorder(null, " Programm\u00e9 ", 2, 1, new Font("Tahoma", 0, 16), new Color(51, 51, 51)));
                this.txta_Paye.setText(this.generatePayePntNewP());
                this.txta_Paye.setCaretPosition(0);
            }
            else {
                this.spnl_Paye.setBorder(BorderFactory.createTitledBorder(null, " R\u00e9alis\u00e9 ", 2, 1, new Font("Tahoma", 0, 16), new Color(51, 51, 51)));
                this.txta_Paye.setText(this.generatePayePntNewR());
                this.txta_Paye.setCaretPosition(0);
            }
        }
        else if (!ChopeCrew.analyse.isPNT) {
            this.ep4_pnc.calculEP4(ChopeCrew.analyse.moisInt, ChopeCrew.donneesPerso_PNC);
            if (!ChopeCrew.options.ep4_progreal) {
                this.spnl_Paye.setBorder(BorderFactory.createTitledBorder(null, "----  Programm\u00e9  ----", 2, 1, new Font("Tahoma", 0, 11), new Color(51, 51, 51)));
                this.txta_Paye.setText(this.generatePayePncP());
                this.txta_Paye.setCaretPosition(0);
            }
            else {
                this.spnl_Paye.setBorder(BorderFactory.createTitledBorder(null, "----  R\u00e9alis\u00e9  ----", 2, 1, new Font("Tahoma", 0, 11), new Color(51, 51, 51)));
                this.txta_Paye.setText(this.generatePayePncR());
                this.txta_Paye.setCaretPosition(0);
            }
        }
    }
    
    private void updateTables() {
        ((EP4TableModel)this.jTable_TempsVol.getModel()).loadTableTempsVol(ChopeCrew.analyse);
        ((EP4TableModel)this.jTable_HCp.getModel()).loadTableHCp(ChopeCrew.analyse);
        ((EP4TableModel)this.jTable_HCr.getModel()).loadTableHCr(ChopeCrew.analyse);
        ((EP4TableModel)this.jTable_HCRp.getModel()).loadTableHCRp(ChopeCrew.analyse);
        ((EP4TableModel)this.jTable_HCRr.getModel()).loadTableHCRr(ChopeCrew.analyse);
        ((EP4TableModel)this.jTable_IRp.getModel()).loadTableIRp(ChopeCrew.analyse);
        ((EP4TableModel)this.jTable_IRr.getModel()).loadTableIRr(ChopeCrew.analyse);
        ((EP4TableModel)this.jTable_DecompteEP4.getModel()).loadTableDecompteEP4(ChopeCrew.analyse);
        ((EP4TableModel)this.jTable_HoraireEP4.getModel()).loadTableHoraireEP4(ChopeCrew.analyse);
    }
    
    private String generatePayePntP() {
        final StringBuilder sb = new StringBuilder();
        final String nl = System.getProperty("line.separator");
        sb.append(nl);
        sb.append("TRAITEMENT FIXE :\t").append(this.ep4_pnt.fppBaseFixe).append("\t").append(Math.max(0, 30 - this.ep4_pnt.nSansSolde)).append("/30\t").append(this.ep4_pnt.fppFixe).append(nl);
        if (this.ep4_pnt.fppPrimeFonction != 0.0) {
            sb.append("PRIME DE FONCTION :\t").append(this.ep4_pnt.fppBasePrimeFonction).append("\t").append(30 - this.ep4_pnt.nSansSolde).append("/30\t").append(this.ep4_pnt.fppPrimeFonction).append(nl);
        }
        sb.append(nl);
        sb.append("P.VOL ET MAJO.NUIT :\t\t\t").append(this.ep4_pnt.fppPv).append(nl);
        if (this.ep4_pnt.fppHsFixe != 0.0 || this.ep4_pnt.fppHsPv != 0.0) {
            sb.append("MAJ.HRES SUP/FIXE :\t\t\t").append(this.ep4_pnt.fppHsFixe).append(nl);
            sb.append("MAJ.HRES SUP/P.VOL :\t\t\t").append(this.ep4_pnt.fppHsPv).append(nl);
        }
        if (this.ep4_pnt.fppMaladie != 0.0 || this.ep4_pnt.fppCongePv != 0.0 || this.ep4_pnt.fppCongeHs != 0.0 || this.ep4_pnt.fppCongeAs != 0.0) {
            sb.append(nl);
        }
        if (this.ep4_pnt.fppMaladie != 0.0) {
            sb.append("MINI.GARANTI :\t").append(this.ep4_pnt.nMaladies).append("\t").append(this.ep4_pnt.fppBaseMaladie).append("\t").append(this.ep4_pnt.fppMaladie).append(nl);
        }
        if (this.ep4_pnt.fppCongePv != 0.0) {
            sb.append("CNG ANNUEL/FAMI.PV :\t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fppBaseCongePv).append("\t").append(this.ep4_pnt.fppCongePv).append(nl);
        }
        if (this.ep4_pnt.fppCongeHs != 0.0) {
            sb.append("CNG ANNUEL/FAMI.HS :\t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fppBaseCongeHs).append("\t").append(this.ep4_pnt.fppCongeHs).append(nl);
        }
        if (this.ep4_pnt.fppCongeAs != 0.0) {
            sb.append("CNG ANNUEL/FAMI.AS :\t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fppBaseCongeAs).append("\t").append(this.ep4_pnt.fppCongeAs).append(nl);
        }
        if (this.ep4_pnt.fppCompMga != 0.0) {
            sb.append(nl);
            sb.append("COMPL.MG ACTIVITE :\t").append(this.ep4_pnt.fppMga).append("\t\t").append(this.ep4_pnt.fppCompMga).append(nl);
        }
        sb.append(nl);
        sb.append("====> BRUT FISCAL (inclus correction) :\t\t").append(this.ep4_pnt.fppBrutFiscal).append(nl);
        sb.append(nl);
        sb.append("IND.REPAS (inclus correction) :\t\t").append(this.ep4_pnt.fppRepasImp).append(nl);
        sb.append(nl);
        sb.append("I.VOITURE/COURRIER (inclus correction) :\t\t").append(this.ep4_pnt.fppIkv).append(nl);
        sb.append(nl).append(nl);
        sb.append("SEC.SOC.MALADIE :\t").append(this.ep4_pnt.fppBaseSSM).append("\t").append(this.ep4_pnt.ssm).append("\t\t").append(this.ep4_pnt.fppCotisSSM).append(nl);
        sb.append("SEC.SOC.VIEILLESSE :\t").append(this.ep4_pnt.fppBaseSSV).append("\t").append(this.ep4_pnt.ssv).append("\t\t").append(this.ep4_pnt.fppCotisSSV).append(nl);
        sb.append("SS VIEILLESSE CPLT :\t").append(this.ep4_pnt.fppBaseSSVC).append("\t").append(this.ep4_pnt.ssvc).append("\t\t").append(this.ep4_pnt.fppCotisSSVC).append(nl);
        sb.append("COTISATION ASSEDIC :\t").append(this.ep4_pnt.fppBaseASS).append("\t").append(this.ep4_pnt.ass).append("\t\t").append(this.ep4_pnt.fppCotisASS).append(nl);
        sb.append("CRPN F.RETRAITE :\t").append(this.ep4_pnt.fppBaseCRPNr).append("\t").append(this.ep4_pnt.crpnr).append("\t\t").append(this.ep4_pnt.fppCotisCRPNr).append(nl);
        sb.append("CRPN F.ASSURANCE :\t").append(this.ep4_pnt.fppBaseCRPNa).append("\t").append(this.ep4_pnt.crpna).append("\t\t").append(this.ep4_pnt.fppCotisCRPNa).append(nl);
        sb.append("CRPN F.MAJORATION :\t").append(this.ep4_pnt.fppBaseCRPNm).append("\t").append(this.ep4_pnt.crpnm).append("\t\t").append(this.ep4_pnt.fppCotisCRPNm).append(nl);
        sb.append("COTISATION MNPAF :\t").append(this.ep4_pnt.fppBaseMNPAF).append("\t").append(this.ep4_pnt.mnpaf).append("\t\t").append(this.ep4_pnt.fppCotisMNPAF).append(nl);
        sb.append("PREV.INAPT.DEFINIT :\t").append(this.ep4_pnt.fppBasePIAD).append("\t").append(this.ep4_pnt.iad).append("\t\t").append(this.ep4_pnt.fppCotisPIAD).append(nl);
        sb.append("PREV.DECES T1 :\t").append(this.ep4_pnt.fppBasePDT1).append("\t").append(this.ep4_pnt.pdt1).append("\t\t").append(this.ep4_pnt.fppCotisPDT1).append(nl);
        if (this.ep4_pnt.fppCotisPDT1B != 0.0) {
            sb.append("PREV.DECES T1B :\t").append(this.ep4_pnt.fppBasePDT1B).append("\t").append(this.ep4_pnt.pdt1B).append("\t\t").append(this.ep4_pnt.fppCotisPDT1B).append(nl);
        }
        if (this.ep4_pnt.fppCotisPDT2 != 0.0) {
            sb.append("PREV.DECES T2 :\t").append(this.ep4_pnt.fppBasePDT2).append("\t").append(this.ep4_pnt.pdt2).append("\t\t").append(this.ep4_pnt.fppCotisPDT2).append(nl);
        }
        if (this.ep4_pnt.fppCotisPDT3 != 0.0) {
            sb.append("PREV.DECES T3 :\t").append(this.ep4_pnt.fppBasePDT3).append("\t").append(this.ep4_pnt.pdt3).append("\t\t").append(this.ep4_pnt.fppCotisPDT3).append(nl);
        }
        if (this.ep4_pnt.fppCotisPDT4 != 0.0) {
            sb.append("PREV.DECES T4 :\t").append(this.ep4_pnt.fppBasePDT4).append("\t").append(this.ep4_pnt.pdt4).append("\t\t").append(this.ep4_pnt.fppCotisPDT4).append(nl);
        }
        if (this.ep4_pnt.fppCotisPDT5 != 0.0) {
            sb.append("PREV.DECES T5 :\t").append(this.ep4_pnt.fppBasePDT5).append("\t").append(this.ep4_pnt.pdt5).append("\t\t").append(this.ep4_pnt.fppCotisPDT5).append(nl);
        }
        sb.append("PREV.LONG.MAL TA :\t").append(this.ep4_pnt.fppBasePLMTA).append("\t").append(this.ep4_pnt.plmta).append("\t\t").append(this.ep4_pnt.fppCotisPLMTA).append(nl);
        if (this.ep4_pnt.fppCotisPLMTB != 0.0) {
            sb.append("PREV.LONG.MAL TB :\t").append(this.ep4_pnt.fppBasePLMTB).append("\t").append(this.ep4_pnt.plmtb).append("\t\t").append(this.ep4_pnt.fppCotisPLMTB).append(nl);
        }
        if (this.ep4_pnt.fppCotisPLMTC != 0.0) {
            sb.append("PREV.LONG.MAL TC :\t").append(this.ep4_pnt.fppBasePLMTC).append("\t").append(this.ep4_pnt.plmtc).append("\t\t").append(this.ep4_pnt.fppCotisPLMTC).append(nl);
        }
        sb.append("CSG DEDUCTIBLE :\t").append(this.ep4_pnt.fppBaseCSGd).append("\t").append(this.ep4_pnt.csgd).append("\t\t").append(this.ep4_pnt.fppCotisCSGd).append(nl);
        sb.append("CSG + CRDS N/DEDUC :\t").append(this.ep4_pnt.fppBaseCSGnd).append("\t").append(this.ep4_pnt.csgnd).append("\t\t").append(this.ep4_pnt.fppCotisCSGnd).append(nl);
        sb.append(nl);
        sb.append("INDEMNITE REPAS (inclus correction) :\t\t").append(this.ep4_pnt.fppRepasNonImp).append(nl);
        sb.append(nl);
        sb.append("====> NET BULLETIN :\t\t\t").append(this.ep4_pnt.fppNetBulletin).append(nl);
        sb.append(nl).append(nl);
        sb.append("COTISATIONS EMPLOYE :\t").append(this.ep4_pnt.fppCotisEmploye).append(nl);
        sb.append("RETRAITE SUP :\t").append(Utils.arrondi(this.ep4_pnt.fppCotisArt83_1 + this.ep4_pnt.fppCotisArt83_2 + this.ep4_pnt.fppCotisArt83Vif, 2)).append(nl);
        sb.append("I.DECOUCHERS F.PRO :\t").append(this.ep4_pnt.fppDEC).append(nl);
        sb.append("FRAIS PROFESS. :\t").append(this.ep4_pnt.fppFraisPro).append(nl);
        sb.append("NET IMPOSABLE :\t").append(this.ep4_pnt.fppNetImposable).append(nl);
        return sb.toString();
    }
    
    private String generatePayePntNewP() {
        final StringBuilder sb = new StringBuilder();
        final String nl = System.getProperty("line.separator");
        sb.append(nl);
        sb.append("TRAITEMENT FIXE \t").append(this.ep4_pnt.fppBaseFixe).append("\t").append(Math.max(0, 30 - this.ep4_pnt.nSansSolde)).append("/30\t").append(this.ep4_pnt.fppFixe).append(nl);
        if (this.ep4_pnt.fppPrimeFonction != 0.0) {
            sb.append("PRIME DE FONCTION \t").append(this.ep4_pnt.fppBasePrimeFonction).append("\t").append(30 - this.ep4_pnt.nSansSolde).append("/30\t").append(this.ep4_pnt.fppPrimeFonction).append(nl);
        }
        sb.append(nl);
        sb.append("P.VOL ET MAJO.NUIT \t\t\t").append(this.ep4_pnt.fppPv).append(nl);
        if (this.ep4_pnt.fppHsFixe != 0.0 || this.ep4_pnt.fppHsPv != 0.0) {
            sb.append("MAJ.HRES SUP/FIXE \t\t\t").append(this.ep4_pnt.fppHsFixe).append(nl);
            sb.append("MAJ.HRES SUP/P.VOL \t\t\t").append(this.ep4_pnt.fppHsPv).append(nl);
        }
        if (this.ep4_pnt.fppMaladie != 0.0 || this.ep4_pnt.fppCongePv != 0.0 || this.ep4_pnt.fppCongeHs != 0.0 || this.ep4_pnt.fppCongeAs != 0.0) {
            sb.append(nl);
        }
        if (this.ep4_pnt.fppMaladie != 0.0) {
            sb.append("MINI.GARANTI \t").append(this.ep4_pnt.nMaladies).append("\t").append(this.ep4_pnt.fppBaseMaladie).append("\t").append(this.ep4_pnt.fppMaladie).append(nl);
        }
        if (this.ep4_pnt.fppCongePv != 0.0) {
            sb.append("CNG ANNUEL/FAMI.PV \t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fppBaseCongePv).append("\t").append(this.ep4_pnt.fppCongePv).append(nl);
        }
        if (this.ep4_pnt.fppCongeHs != 0.0) {
            sb.append("CNG ANNUEL/FAMI.HS \t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fppBaseCongeHs).append("\t").append(this.ep4_pnt.fppCongeHs).append(nl);
        }
        if (this.ep4_pnt.fppCongeAs != 0.0) {
            sb.append("CNG ANNUEL/FAMI.AS \t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fppBaseCongeAs).append("\t").append(this.ep4_pnt.fppCongeAs).append(nl);
        }
        if (this.ep4_pnt.fppCompMga != 0.0) {
            sb.append(nl);
            sb.append("COMPL.MG ACTIVITE \t").append(this.ep4_pnt.fppMga).append("\t\t").append(this.ep4_pnt.fppCompMga).append(nl);
        }
        sb.append(nl);
        sb.append("IND.REPAS (inclus correction) \t\t\t").append(this.ep4_pnt.fppRepasImp).append(nl);
        sb.append("IND.TRANSPORT (inclus correction) \t\t").append(this.ep4_pnt.fppIkv).append(nl);
        sb.append(nl);
        sb.append("INDEMNITE REPAS (inclus correction) \t\t").append(this.ep4_pnt.fppRepasNonImp).append(nl);
        sb.append(nl);
        sb.append("TOTAL BRUT \t\t\t\t").append(Utils.arrondi(this.ep4_pnt.fppBrutFiscal + this.ep4_pnt.fppRepasImp + this.ep4_pnt.fppIkv + this.ep4_pnt.fppRepasNonImp, 2)).append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append("CRPN F.Assurance \t").append(this.ep4_pnt.fppBaseCRPNa).append("\t").append(this.ep4_pnt.crpna).append("\t\t").append(this.ep4_pnt.fppCotisCRPNa).append(nl);
        sb.append("CRPN F.Majoration \t").append(this.ep4_pnt.fppBaseCRPNm).append("\t").append(this.ep4_pnt.crpnm).append("\t\t").append(this.ep4_pnt.fppCotisCRPNm).append(nl);
        sb.append("Compl. Inapt.d\u00e9finitive \t").append(this.ep4_pnt.fppBasePIAD).append("\t").append(this.ep4_pnt.iad).append("\t\t").append(this.ep4_pnt.fppCotisPIAD).append(nl);
        sb.append("Compl. D\u00e9c\u00e8s T1 \t").append(this.ep4_pnt.fppBasePDT1).append("\t").append(this.ep4_pnt.pdt1).append("\t\t").append(this.ep4_pnt.fppCotisPDT1).append(nl);
        if (this.ep4_pnt.fppCotisPDT1B != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T1B \t").append(this.ep4_pnt.fppBasePDT1B).append("\t").append(this.ep4_pnt.pdt1B).append("\t\t").append(this.ep4_pnt.fppCotisPDT1B).append(nl);
        }
        if (this.ep4_pnt.fppCotisPDT2 != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T2 \t").append(this.ep4_pnt.fppBasePDT2).append("\t").append(this.ep4_pnt.pdt2).append("\t\t").append(this.ep4_pnt.fppCotisPDT2).append(nl);
        }
        if (this.ep4_pnt.fppCotisPDT3 != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T3 \t").append(this.ep4_pnt.fppBasePDT3).append("\t").append(this.ep4_pnt.pdt3).append("\t\t").append(this.ep4_pnt.fppCotisPDT3).append(nl);
        }
        if (this.ep4_pnt.fppCotisPDT4 != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T4 \t").append(this.ep4_pnt.fppBasePDT4).append("\t").append(this.ep4_pnt.pdt4).append("\t\t").append(this.ep4_pnt.fppCotisPDT4).append(nl);
        }
        if (this.ep4_pnt.fppCotisPDT5 != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T5 \t").append(this.ep4_pnt.fppBasePDT5).append("\t").append(this.ep4_pnt.pdt5).append("\t\t").append(this.ep4_pnt.fppCotisPDT5).append(nl);
        }
        sb.append("Compl. Longue Maladie TA \t").append(this.ep4_pnt.fppBasePLMTA).append("\t").append(this.ep4_pnt.plmta).append("\t\t").append(this.ep4_pnt.fppCotisPLMTA).append(nl);
        if (this.ep4_pnt.fppCotisPLMTB != 0.0) {
            sb.append("Compl. Longue Maladie TB \t").append(this.ep4_pnt.fppBasePLMTB).append("\t").append(this.ep4_pnt.plmtb).append("\t\t").append(this.ep4_pnt.fppCotisPLMTB).append(nl);
        }
        if (this.ep4_pnt.fppCotisPLMTC != 0.0) {
            sb.append("Compl. Longue Maladie TC \t").append(this.ep4_pnt.fppBasePLMTC).append("\t").append(this.ep4_pnt.plmtc).append("\t\t").append(this.ep4_pnt.fppCotisPLMTC).append(nl);
        }
        sb.append("Compl\u00e9mentaire Sant\u00e9 \t").append(this.ep4_pnt.fppBaseMNPAF).append("\t").append(this.ep4_pnt.mnpaf).append("\t\t").append(this.ep4_pnt.fppCotisMNPAF).append(nl);
        sb.append(nl);
        sb.append("S\u00e9curit\u00e9 sociale plafonn\u00e9e \t").append(this.ep4_pnt.fppBaseSSV).append("\t").append(this.ep4_pnt.ssv).append("\t\t").append(this.ep4_pnt.fppCotisSSV).append(nl);
        sb.append("S\u00e9curit\u00e9 sociale d\u00e9plafonn\u00e9e \t").append(this.ep4_pnt.fppBaseSSVC).append("\t").append(this.ep4_pnt.ssvc).append("\t\t").append(this.ep4_pnt.fppCotisSSVC).append(nl);
        sb.append("Compl\u00e9mentaire tranche A \t").append(this.ep4_pnt.fppBaseCRPNr).append("\t").append(this.ep4_pnt.crpnr).append("\t\t").append(this.ep4_pnt.fppCotisCRPNr).append(nl);
        sb.append("Suppl\u00e9mentaire \t").append(this.ep4_pnt.fppBaseART83T1).append("\t").append(Utils.arrondi(this.ep4_pnt.art83t1p + this.ep4_pnt.art83vifp, 2)).append("\t\t").append(Utils.arrondi(this.ep4_pnt.fppCotisArt83_1 + this.ep4_pnt.fppCotisArt83Vif, 2)).append(nl);
        sb.append("Suppl\u00e9mentaire \t").append(this.ep4_pnt.fppBaseART83T2).append("\t").append(this.ep4_pnt.art83t2p).append("\t\t").append(this.ep4_pnt.fppCotisArt83_2).append(nl);
        sb.append(nl);
        sb.append("CSG d\u00e9ductible \t").append(this.ep4_pnt.fppBaseCSGd).append("\t").append(this.ep4_pnt.csgd).append("\t\t").append(this.ep4_pnt.fppCotisCSGd).append(nl);
        sb.append("CSG/CRDS non d\u00e9ductible \t").append(this.ep4_pnt.fppBaseCSGnd).append("\t").append(this.ep4_pnt.csgnd).append("\t\t").append(this.ep4_pnt.fppCotisCSGnd).append(nl);
        sb.append(nl);
        sb.append("Total des cotisations \t\t\t\t").append(this.ep4_pnt.fppCotisEmploye).append(nl);
        sb.append(nl);
        sb.append("====> NET BULLETIN \t\t\t").append(this.ep4_pnt.fppNetBulletin).append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append("I.DECOUCHERS F.PRO \t").append(this.ep4_pnt.fppDEC).append(nl);
        sb.append("RETRAITE SUP \t").append(Utils.arrondi(this.ep4_pnt.fppCotisArt83_1 + this.ep4_pnt.fppCotisArt83_2 + this.ep4_pnt.fppCotisArt83Vif, 2)).append(nl);
        sb.append("FRAIS PROFESS. \t").append(this.ep4_pnt.fppFraisPro).append(nl);
        sb.append("BRUT FISCAL \t\t").append(this.ep4_pnt.fppBrutFiscal).append(nl);
        sb.append("BRUT SEC.SOC. \t").append(this.ep4_pnt.fppBaseSSM).append(nl);
        sb.append("NET IMPOSABLE \t").append(this.ep4_pnt.fppNetImposable).append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append("Montant Vol \t\t").append(this.ep4_pnt.montantVolHCRp).append(nl);
        sb.append("Montant Sol \t\t").append(this.ep4_pnt.montantSolHCRS).append(nl);
        sb.append("Montant Prime CDB \t").append(this.ep4_pnt.montantVolPrimeCdbp).append(nl);
        sb.append("Montant Nuit \t\t").append(this.ep4_pnt.montantVolHCSVNp).append(nl);
        return sb.toString();
    }
    
    private String generatePayePntR() {
        final StringBuilder sb = new StringBuilder();
        final String nl = System.getProperty("line.separator");
        sb.append(nl);
        sb.append("TRAITEMENT FIXE :\t").append(this.ep4_pnt.fprBaseFixe).append("\t").append(Math.max(0, 30 - this.ep4_pnt.nSansSolde)).append("/30\t").append(this.ep4_pnt.fprFixe).append(nl);
        if (this.ep4_pnt.fprPrimeFonction != 0.0) {
            sb.append("PRIME DE FONCTION :\t").append(this.ep4_pnt.fprBasePrimeFonction).append("\t").append(30 - this.ep4_pnt.nSansSolde).append("/30\t").append(this.ep4_pnt.fprPrimeFonction).append(nl);
        }
        sb.append(nl);
        sb.append("P.VOL ET MAJO.NUIT :\t\t\t").append(this.ep4_pnt.fprPv).append(nl);
        if (this.ep4_pnt.fprHsFixe != 0.0 || this.ep4_pnt.fprHsPv != 0.0) {
            sb.append("MAJ.HRES SUP/FIXE :\t\t\t").append(this.ep4_pnt.fprHsFixe).append(nl);
            sb.append("MAJ.HRES SUP/P.VOL :\t\t\t").append(this.ep4_pnt.fprHsPv).append(nl);
        }
        if (this.ep4_pnt.fprMaladie != 0.0 || this.ep4_pnt.fprCongePv != 0.0 || this.ep4_pnt.fprCongeHs != 0.0 || this.ep4_pnt.fprCongeAs != 0.0) {
            sb.append(nl);
        }
        if (this.ep4_pnt.fprMaladie != 0.0) {
            sb.append("MINI.GARANTI :\t").append(this.ep4_pnt.nMaladies).append("\t").append(this.ep4_pnt.fprBaseMaladie).append("\t").append(this.ep4_pnt.fprMaladie).append(nl);
        }
        if (this.ep4_pnt.fprCongePv != 0.0) {
            sb.append("CNG ANNUEL/FAMI.PV :\t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fprBaseCongePv).append("\t").append(this.ep4_pnt.fprCongePv).append(nl);
        }
        if (this.ep4_pnt.fprCongeHs != 0.0) {
            sb.append("CNG ANNUEL/FAMI.HS :\t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fprBaseCongeHs).append("\t").append(this.ep4_pnt.fprCongeHs).append(nl);
        }
        if (this.ep4_pnt.fprCongeAs != 0.0) {
            sb.append("CNG ANNUEL/FAMI.AS :\t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fprBaseCongeAs).append("\t").append(this.ep4_pnt.fprCongeAs).append(nl);
        }
        if (this.ep4_pnt.fprCompMga != 0.0) {
            sb.append(nl);
            sb.append("COMPL.MG ACTIVITE :\t").append(this.ep4_pnt.fprMga).append("\t\t").append(this.ep4_pnt.fprCompMga).append(nl);
        }
        sb.append(nl);
        sb.append("====> BRUT FISCAL (inclus correction) :\t\t").append(this.ep4_pnt.fprBrutFiscal).append(nl);
        sb.append(nl);
        sb.append("IND.REPAS (inclus correction) :\t\t").append(this.ep4_pnt.fprRepasImp).append(nl);
        sb.append(nl);
        sb.append("I.VOITURE/COURRIER (inclus correction) :\t\t").append(this.ep4_pnt.fprIkv).append(nl);
        sb.append(nl).append(nl);
        sb.append("SEC.SOC.MALADIE :\t").append(this.ep4_pnt.fprBaseSSM).append("\t").append(this.ep4_pnt.ssm).append("\t\t").append(this.ep4_pnt.fprCotisSSM).append(nl);
        sb.append("SEC.SOC.VIEILLESSE :\t").append(this.ep4_pnt.fprBaseSSV).append("\t").append(this.ep4_pnt.ssv).append("\t\t").append(this.ep4_pnt.fprCotisSSV).append(nl);
        sb.append("SS VIEILLESSE CPLT :\t").append(this.ep4_pnt.fprBaseSSVC).append("\t").append(this.ep4_pnt.ssvc).append("\t\t").append(this.ep4_pnt.fprCotisSSVC).append(nl);
        sb.append("COTISATION ASSEDIC :\t").append(this.ep4_pnt.fprBaseASS).append("\t").append(this.ep4_pnt.ass).append("\t\t").append(this.ep4_pnt.fprCotisASS).append(nl);
        sb.append("CRPN F.RETRAITE :\t").append(this.ep4_pnt.fprBaseCRPNr).append("\t").append(this.ep4_pnt.crpnr).append("\t\t").append(this.ep4_pnt.fprCotisCRPNr).append(nl);
        sb.append("CRPN F.ASSURANCE :\t").append(this.ep4_pnt.fprBaseCRPNa).append("\t").append(this.ep4_pnt.crpna).append("\t\t").append(this.ep4_pnt.fprCotisCRPNa).append(nl);
        sb.append("CRPN F.MAJORATION :\t").append(this.ep4_pnt.fprBaseCRPNm).append("\t").append(this.ep4_pnt.crpnm).append("\t\t").append(this.ep4_pnt.fprCotisCRPNm).append(nl);
        sb.append("COTISATION MNPAF :\t").append(this.ep4_pnt.fprBaseMNPAF).append("\t").append(this.ep4_pnt.mnpaf).append("\t\t").append(this.ep4_pnt.fprCotisMNPAF).append(nl);
        sb.append("PREV.INAPT.DEFINIT :\t").append(this.ep4_pnt.fprBasePIAD).append("\t").append(this.ep4_pnt.iad).append("\t\t").append(this.ep4_pnt.fprCotisPIAD).append(nl);
        sb.append("PREV.DECES T1 :\t").append(this.ep4_pnt.fprBasePDT1).append("\t").append(this.ep4_pnt.pdt1).append("\t\t").append(this.ep4_pnt.fprCotisPDT1).append(nl);
        if (this.ep4_pnt.fprCotisPDT1B != 0.0) {
            sb.append("PREV.DECES T1B :\t").append(this.ep4_pnt.fprBasePDT1B).append("\t").append(this.ep4_pnt.pdt1B).append("\t\t").append(this.ep4_pnt.fprCotisPDT1B).append(nl);
        }
        if (this.ep4_pnt.fprCotisPDT2 != 0.0) {
            sb.append("PREV.DECES T2 :\t").append(this.ep4_pnt.fprBasePDT2).append("\t").append(this.ep4_pnt.pdt2).append("\t\t").append(this.ep4_pnt.fprCotisPDT2).append(nl);
        }
        if (this.ep4_pnt.fprCotisPDT3 != 0.0) {
            sb.append("PREV.DECES T3 :\t").append(this.ep4_pnt.fprBasePDT3).append("\t").append(this.ep4_pnt.pdt3).append("\t\t").append(this.ep4_pnt.fprCotisPDT3).append(nl);
        }
        if (this.ep4_pnt.fprCotisPDT4 != 0.0) {
            sb.append("PREV.DECES T4 :\t").append(this.ep4_pnt.fprBasePDT4).append("\t").append(this.ep4_pnt.pdt4).append("\t\t").append(this.ep4_pnt.fprCotisPDT4).append(nl);
        }
        if (this.ep4_pnt.fprCotisPDT5 != 0.0) {
            sb.append("PREV.DECES T5 :\t").append(this.ep4_pnt.fprBasePDT5).append("\t").append(this.ep4_pnt.pdt5).append("\t\t").append(this.ep4_pnt.fprCotisPDT5).append(nl);
        }
        sb.append("PREV.LONG.MAL TA :\t").append(this.ep4_pnt.fprBasePLMTA).append("\t").append(this.ep4_pnt.plmta).append("\t\t").append(this.ep4_pnt.fprCotisPLMTA).append(nl);
        if (this.ep4_pnt.fprCotisPLMTB != 0.0) {
            sb.append("PREV.LONG.MAL TB :\t").append(this.ep4_pnt.fprBasePLMTB).append("\t").append(this.ep4_pnt.plmtb).append("\t\t").append(this.ep4_pnt.fprCotisPLMTB).append(nl);
        }
        if (this.ep4_pnt.fprCotisPLMTC != 0.0) {
            sb.append("PREV.LONG.MAL TC :\t").append(this.ep4_pnt.fprBasePLMTC).append("\t").append(this.ep4_pnt.plmtc).append("\t\t").append(this.ep4_pnt.fprCotisPLMTC).append(nl);
        }
        sb.append("CSG DEDUCTIBLE :\t").append(this.ep4_pnt.fprBaseCSGd).append("\t").append(this.ep4_pnt.csgd).append("\t\t").append(this.ep4_pnt.fprCotisCSGd).append(nl);
        sb.append("CSG + CRDS N/DEDUC :\t").append(this.ep4_pnt.fprBaseCSGnd).append("\t").append(this.ep4_pnt.csgnd).append("\t\t").append(this.ep4_pnt.fprCotisCSGnd).append(nl);
        sb.append(nl);
        sb.append("INDEMNITE REPAS (inclus correction) :\t\t").append(this.ep4_pnt.fprRepasNonImp).append(nl);
        sb.append(nl);
        sb.append("====> NET BULLETIN :\t\t\t").append(this.ep4_pnt.fprNetBulletin).append(nl);
        sb.append(nl).append(nl);
        sb.append("COTISATIONS EMPLOYE :\t").append(this.ep4_pnt.fprCotisEmploye).append(nl);
        sb.append("RETRAITE SUP :\t").append(Utils.arrondi(this.ep4_pnt.fprCotisArt83_1 + this.ep4_pnt.fprCotisArt83_2 + this.ep4_pnt.fprCotisArt83Vif, 2)).append(nl);
        sb.append("I.DECOUCHERS F.PRO :\t").append(this.ep4_pnt.fprDEC).append(nl);
        sb.append("FRAIS PROFESS. :\t").append(this.ep4_pnt.fprFraisPro).append(nl);
        sb.append("NET IMPOSABLE :\t").append(this.ep4_pnt.fprNetImposable).append(nl);
        return sb.toString();
    }
    
    private String generatePayePntNewR() {
        final StringBuilder sb = new StringBuilder();
        final String nl = System.getProperty("line.separator");
        sb.append(nl);
        sb.append("TRAITEMENT FIXE \t").append(this.ep4_pnt.fprBaseFixe).append("\t").append(Math.max(0, 30 - this.ep4_pnt.nSansSolde)).append("/30\t").append(this.ep4_pnt.fprFixe).append(nl);
        if (this.ep4_pnt.fprPrimeFonction != 0.0) {
            sb.append("PRIME DE FONCTION \t").append(this.ep4_pnt.fprBasePrimeFonction).append("\t").append(30 - this.ep4_pnt.nSansSolde).append("/30\t").append(this.ep4_pnt.fprPrimeFonction).append(nl);
        }
        sb.append(nl);
        sb.append("P.VOL ET MAJO.NUIT \t\t\t").append(this.ep4_pnt.fprPv).append(nl);
        if (this.ep4_pnt.fprHsFixe != 0.0 || this.ep4_pnt.fprHsPv != 0.0) {
            sb.append("MAJ.HRES SUP/FIXE \t\t\t").append(this.ep4_pnt.fprHsFixe).append(nl);
            sb.append("MAJ.HRES SUP/P.VOL \t\t\t").append(this.ep4_pnt.fprHsPv).append(nl);
        }
        if (this.ep4_pnt.fprMaladie != 0.0 || this.ep4_pnt.fprCongePv != 0.0 || this.ep4_pnt.fprCongeHs != 0.0 || this.ep4_pnt.fprCongeAs != 0.0) {
            sb.append(nl);
        }
        if (this.ep4_pnt.fprMaladie != 0.0) {
            sb.append("MINI.GARANTI \t").append(this.ep4_pnt.nMaladies).append("\t").append(this.ep4_pnt.fprBaseMaladie).append("\t").append(this.ep4_pnt.fprMaladie).append(nl);
        }
        if (this.ep4_pnt.fprCongePv != 0.0) {
            sb.append("CNG ANNUEL/FAMI.PV \t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fprBaseCongePv).append("\t").append(this.ep4_pnt.fprCongePv).append(nl);
        }
        if (this.ep4_pnt.fprCongeHs != 0.0) {
            sb.append("CNG ANNUEL/FAMI.HS \t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fprBaseCongeHs).append("\t").append(this.ep4_pnt.fprCongeHs).append(nl);
        }
        if (this.ep4_pnt.fprCongeAs != 0.0) {
            sb.append("CNG ANNUEL/FAMI.AS \t").append(this.ep4_pnt.nConges).append("\t").append(this.ep4_pnt.fprBaseCongeAs).append("\t").append(this.ep4_pnt.fprCongeAs).append(nl);
        }
        if (this.ep4_pnt.fprCompMga != 0.0) {
            sb.append(nl);
            sb.append("COMPL.MG ACTIVITE \t").append(this.ep4_pnt.fprMga).append("\t\t").append(this.ep4_pnt.fprCompMga).append(nl);
        }
        sb.append(nl);
        sb.append("IND.REPAS (inclus correction) \t\t\t").append(this.ep4_pnt.fprRepasImp).append(nl);
        sb.append("IND.TRANSPORT (inclus correction) \t\t").append(this.ep4_pnt.fprIkv).append(nl);
        sb.append(nl);
        sb.append("INDEMNITE REPAS (inclus correction) \t\t").append(this.ep4_pnt.fprRepasNonImp).append(nl);
        sb.append(nl);
        sb.append("TOTAL BRUT \t\t\t\t").append(Utils.arrondi(this.ep4_pnt.fprBrutFiscal + this.ep4_pnt.fprRepasImp + this.ep4_pnt.fprIkv + this.ep4_pnt.fprRepasNonImp, 2)).append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append("CRPN F.Assurance \t").append(this.ep4_pnt.fprBaseCRPNa).append("\t").append(this.ep4_pnt.crpna).append("\t\t").append(this.ep4_pnt.fprCotisCRPNa).append(nl);
        sb.append("CRPN F.Majoration \t").append(this.ep4_pnt.fprBaseCRPNm).append("\t").append(this.ep4_pnt.crpnm).append("\t\t").append(this.ep4_pnt.fprCotisCRPNm).append(nl);
        sb.append("Compl. Inapt.d\u00e9finitive \t").append(this.ep4_pnt.fprBasePIAD).append("\t").append(this.ep4_pnt.iad).append("\t\t").append(this.ep4_pnt.fprCotisPIAD).append(nl);
        sb.append("Compl. D\u00e9c\u00e8s T1 \t").append(this.ep4_pnt.fprBasePDT1).append("\t").append(this.ep4_pnt.pdt1).append("\t\t").append(this.ep4_pnt.fprCotisPDT1).append(nl);
        if (this.ep4_pnt.fprCotisPDT1B != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T1B \t").append(this.ep4_pnt.fprBasePDT1B).append("\t").append(this.ep4_pnt.pdt1B).append("\t\t").append(this.ep4_pnt.fprCotisPDT1B).append(nl);
        }
        if (this.ep4_pnt.fprCotisPDT2 != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T2 \t").append(this.ep4_pnt.fprBasePDT2).append("\t").append(this.ep4_pnt.pdt2).append("\t\t").append(this.ep4_pnt.fprCotisPDT2).append(nl);
        }
        if (this.ep4_pnt.fprCotisPDT3 != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T3 \t").append(this.ep4_pnt.fprBasePDT3).append("\t").append(this.ep4_pnt.pdt3).append("\t\t").append(this.ep4_pnt.fprCotisPDT3).append(nl);
        }
        if (this.ep4_pnt.fprCotisPDT4 != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T4 \t").append(this.ep4_pnt.fprBasePDT4).append("\t").append(this.ep4_pnt.pdt4).append("\t\t").append(this.ep4_pnt.fprCotisPDT4).append(nl);
        }
        if (this.ep4_pnt.fprCotisPDT5 != 0.0) {
            sb.append("Compl. D\u00e9c\u00e8s T5 \t").append(this.ep4_pnt.fprBasePDT5).append("\t").append(this.ep4_pnt.pdt5).append("\t\t").append(this.ep4_pnt.fprCotisPDT5).append(nl);
        }
        sb.append("Compl. Longue Maladie TA \t").append(this.ep4_pnt.fprBasePLMTA).append("\t").append(this.ep4_pnt.plmta).append("\t\t").append(this.ep4_pnt.fprCotisPLMTA).append(nl);
        if (this.ep4_pnt.fprCotisPLMTB != 0.0) {
            sb.append("Compl. Longue Maladie TB \t").append(this.ep4_pnt.fprBasePLMTB).append("\t").append(this.ep4_pnt.plmtb).append("\t\t").append(this.ep4_pnt.fprCotisPLMTB).append(nl);
        }
        if (this.ep4_pnt.fprCotisPLMTC != 0.0) {
            sb.append("Compl. Longue Maladie TC \t").append(this.ep4_pnt.fprBasePLMTC).append("\t").append(this.ep4_pnt.plmtc).append("\t\t").append(this.ep4_pnt.fprCotisPLMTC).append(nl);
        }
        sb.append("Compl\u00e9mentaire Sant\u00e9 \t").append(this.ep4_pnt.fprBaseMNPAF).append("\t").append(this.ep4_pnt.mnpaf).append("\t\t").append(this.ep4_pnt.fprCotisMNPAF).append(nl);
        sb.append(nl);
        sb.append("S\u00e9curit\u00e9 sociale plafonn\u00e9e \t").append(this.ep4_pnt.fprBaseSSV).append("\t").append(this.ep4_pnt.ssv).append("\t\t").append(this.ep4_pnt.fprCotisSSV).append(nl);
        sb.append("S\u00e9curit\u00e9 sociale d\u00e9plafonn\u00e9e \t").append(this.ep4_pnt.fprBaseSSVC).append("\t").append(this.ep4_pnt.ssvc).append("\t\t").append(this.ep4_pnt.fprCotisSSVC).append(nl);
        sb.append("Compl\u00e9mentaire tranche A \t").append(this.ep4_pnt.fprBaseCRPNr).append("\t").append(this.ep4_pnt.crpnr).append("\t\t").append(this.ep4_pnt.fprCotisCRPNr).append(nl);
        sb.append("Suppl\u00e9mentaire \t").append(this.ep4_pnt.fprBaseART83T1).append("\t").append(Utils.arrondi(this.ep4_pnt.art83t1p + this.ep4_pnt.art83vifp, 2)).append("\t\t").append(Utils.arrondi(this.ep4_pnt.fprCotisArt83_1 + this.ep4_pnt.fprCotisArt83Vif, 2)).append(nl);
        sb.append("Suppl\u00e9mentaire \t").append(this.ep4_pnt.fprBaseART83T2).append("\t").append(this.ep4_pnt.art83t2p).append("\t\t").append(this.ep4_pnt.fprCotisArt83_2).append(nl);
        sb.append(nl);
        sb.append("CSG d\u00e9ductible \t").append(this.ep4_pnt.fprBaseCSGd).append("\t").append(this.ep4_pnt.csgd).append("\t\t").append(this.ep4_pnt.fprCotisCSGd).append(nl);
        sb.append("CSG/CRDS non d\u00e9ductible \t").append(this.ep4_pnt.fprBaseCSGnd).append("\t").append(this.ep4_pnt.csgnd).append("\t\t").append(this.ep4_pnt.fprCotisCSGnd).append(nl);
        sb.append(nl);
        sb.append("Total des cotisations \t\t\t\t").append(this.ep4_pnt.fprCotisEmploye).append(nl);
        sb.append(nl);
        sb.append("====> NET BULLETIN \t\t\t").append(this.ep4_pnt.fprNetBulletin).append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append("I.DECOUCHERS F.PRO \t").append(this.ep4_pnt.fprDEC).append(nl);
        sb.append("RETRAITE SUP \t").append(Utils.arrondi(this.ep4_pnt.fprCotisArt83_1 + this.ep4_pnt.fprCotisArt83_2 + this.ep4_pnt.fprCotisArt83Vif, 2)).append(nl);
        sb.append("FRAIS PROFESS. \t").append(this.ep4_pnt.fprFraisPro).append(nl);
        sb.append("BRUT FISCAL \t\t").append(this.ep4_pnt.fprBrutFiscal).append(nl);
        sb.append("BRUT SEC.SOC. \t").append(this.ep4_pnt.fprBaseSSM).append(nl);
        sb.append("NET IMPOSABLE \t").append(this.ep4_pnt.fprNetImposable).append(nl);
        sb.append(nl);
        sb.append(nl);
        sb.append("Montant Vol \t\t").append(this.ep4_pnt.montantVolHCRr).append(nl);
        sb.append("Montant Sol \t\t").append(this.ep4_pnt.montantSolHCRS).append(nl);
        sb.append("Montant Prime CDB \t").append(this.ep4_pnt.montantVolPrimeCdbr).append(nl);
        sb.append("Montant Nuit \t\t").append(this.ep4_pnt.montantVolHCSVNr).append(nl);
        return sb.toString();
    }
    
    private String generatePayePncP() {
        final StringBuilder sb = new StringBuilder();
        final String nl = System.getProperty("line.separator");
        sb.append(nl);
        sb.append("TRAITEMENT FIXE :\t").append(this.ep4_pnc.fppBaseFixe).append("\t").append(Math.max(0, 30 - this.ep4_pnc.nSansSolde)).append("/30\t").append(this.ep4_pnc.fppFixe).append(nl);
        if (this.ep4_pnc.fppPrimeFonction != 0.0) {
            sb.append("PR.FONCT. :\t\t").append(this.ep4_pnc.fppBasePrimeFonction).append("\t").append(30 - this.ep4_pnc.nSansSolde).append("/30\t").append(this.ep4_pnc.fppPrimeFonction).append(nl);
        }
        if (this.ep4_pnc.fppHsFixe != 0.0) {
            sb.append("H.S/FIXE+PR.FONCT. :\t").append(this.ep4_pnc.volHSp).append("\t").append(this.ep4_pnc.tauxHSFixe).append("\t").append(this.ep4_pnc.fppHsFixe).append(nl);
        }
        sb.append(nl);
        if (this.ep4_pnc.fppCompMga != 0.0) {
            sb.append("CPLT SAL.MINI.GARA :\t").append(this.ep4_pnc.fppMga).append("\t\t").append(this.ep4_pnc.fppCompMga).append(nl);
        }
        sb.append("P.VOL+MAJ.NUIT+VLD :\t\t\t").append(this.ep4_pnc.fppPv).append(nl);
        if (this.ep4_pnc.fppHsPv != 0.0) {
            sb.append("H.S/P.V+NUIT+VLD :\t").append(this.ep4_pnc.volHSp).append("\t").append(this.ep4_pnc.tauxHSVolp).append("\t").append(this.ep4_pnc.fppHsPv).append(nl);
        }
        if (this.ep4_pnc.fppMaladie != 0.0 || this.ep4_pnc.fppCongePv != 0.0) {
            sb.append(nl);
        }
        if (this.ep4_pnc.fppMaladie != 0.0) {
            sb.append("GARANTIE REMU 80PV :\t").append(this.ep4_pnc.nMaladies).append("\t").append(this.ep4_pnc.fppBaseMaladie).append("\t").append(this.ep4_pnc.fppMaladie).append(nl);
        }
        if (this.ep4_pnc.fppCongePv != 0.0) {
            sb.append("CPLT SGMM :\t\t").append(this.ep4_pnc.nConges).append("\t").append(this.ep4_pnc.fppBaseCongePv).append("\t").append(this.ep4_pnc.fppCongePv).append(nl);
        }
        sb.append(nl);
        sb.append("====> BRUT FISCAL (inclus correction) :\t\t").append(this.ep4_pnc.fppBrutFiscal).append(nl);
        sb.append(nl);
        sb.append("I.ENT.HABILLEMENT :\t\t\t").append(this.ep4_pnc.fppPrimeHabillement).append(nl);
        sb.append(nl);
        sb.append("IND.REPAS (inclus correction) :\t\t").append(this.ep4_pnc.fppRepasImp).append(nl);
        sb.append("I.VOITURE/COURRIER (inclus correction) :\t\t").append(this.ep4_pnc.fppIkv).append(nl);
        sb.append(nl).append(nl);
        sb.append("SEC.SOC.MALADIE :\t").append(this.ep4_pnc.fppBaseSSM).append("\t").append(this.ep4_pnc.ssm).append("\t\t").append(this.ep4_pnc.fppCotisSSM).append(nl);
        sb.append("SEC.SOC.VIEILLESSE :\t").append(this.ep4_pnc.fppBaseSSV).append("\t").append(this.ep4_pnc.ssv).append("\t\t").append(this.ep4_pnc.fppCotisSSV).append(nl);
        sb.append("SS VIEILLESSE CPLT :\t").append(this.ep4_pnc.fppBaseSSVC).append("\t").append(this.ep4_pnc.ssvc).append("\t\t").append(this.ep4_pnc.fppCotisSSVC).append(nl);
        sb.append("COTISATION ASSEDIC :\t").append(this.ep4_pnc.fppBaseASS).append("\t").append(this.ep4_pnc.ass).append("\t\t").append(this.ep4_pnc.fppCotisASS).append(nl);
        sb.append("CRPN F.RETRAITE :\t").append(this.ep4_pnc.fppBaseCRPNr).append("\t").append(this.ep4_pnc.crpnr).append("\t\t").append(this.ep4_pnc.fppCotisCRPNr).append(nl);
        sb.append("CRPN F.ASSURANCE :\t").append(this.ep4_pnc.fppBaseCRPNa).append("\t").append(this.ep4_pnc.crpna).append("\t\t").append(this.ep4_pnc.fppCotisCRPNa).append(nl);
        sb.append("CRPN F.SPECIAL :\t").append(this.ep4_pnc.fppBaseCRPNs).append("\t").append(this.ep4_pnc.crpns).append("\t\t").append(this.ep4_pnc.fppCotisCRPNs).append(nl);
        sb.append("COTISATION MNPAF :\t").append(this.ep4_pnc.fppBaseMNPAF).append("\t").append(this.ep4_pnc.mnpaf).append("\t\t").append(this.ep4_pnc.fppCotisMNPAF).append(nl);
        sb.append("PREV.INAPT.DEFINIT :\t").append(this.ep4_pnc.fppBasePIAD).append("\t").append(this.ep4_pnc.iad).append("\t\t").append(this.ep4_pnc.fppCotisPIAD).append(nl);
        sb.append("PREV.DECES T1 :\t").append(this.ep4_pnc.fppBasePDT1).append("\t").append(this.ep4_pnc.pdt1).append("\t\t").append(this.ep4_pnc.fppCotisPDT1).append(nl);
        if (this.ep4_pnc.fppCotisPDT1B != 0.0) {
            sb.append("PREV.DECES T1B :\t").append(this.ep4_pnc.fppBasePDT1B).append("\t").append(this.ep4_pnc.pdt1B).append("\t\t").append(this.ep4_pnc.fppCotisPDT1B).append(nl);
        }
        if (this.ep4_pnc.fppCotisPDT2 != 0.0) {
            sb.append("PREV.DECES T2 :\t").append(this.ep4_pnc.fppBasePDT2).append("\t").append(this.ep4_pnc.pdt2).append("\t\t").append(this.ep4_pnc.fppCotisPDT2).append(nl);
        }
        if (this.ep4_pnc.fppCotisPDT3 != 0.0) {
            sb.append("PREV.DECES T3 :\t").append(this.ep4_pnc.fppBasePDT3).append("\t").append(this.ep4_pnc.pdt3).append("\t\t").append(this.ep4_pnc.fppCotisPDT3).append(nl);
        }
        if (this.ep4_pnc.fppCotisPDT4 != 0.0) {
            sb.append("PREV.DECES T4 :\t").append(this.ep4_pnc.fppBasePDT4).append("\t").append(this.ep4_pnc.pdt4).append("\t\t").append(this.ep4_pnc.fppCotisPDT4).append(nl);
        }
        if (this.ep4_pnc.fppCotisPDT5 != 0.0) {
            sb.append("PREV.DECES T5 :\t").append(this.ep4_pnc.fppBasePDT5).append("\t").append(this.ep4_pnc.pdt5).append("\t\t").append(this.ep4_pnc.fppCotisPDT5).append(nl);
        }
        sb.append("PREV.LONG.MAL TA :\t").append(this.ep4_pnc.fppBasePLMTA).append("\t").append(this.ep4_pnc.plmta).append("\t\t").append(this.ep4_pnc.fppCotisPLMTA).append(nl);
        if (this.ep4_pnc.fppCotisPLMTB != 0.0) {
            sb.append("PREV.LONG.MAL TB :\t").append(this.ep4_pnc.fppBasePLMTB).append("\t").append(this.ep4_pnc.plmtb).append("\t\t").append(this.ep4_pnc.fppCotisPLMTB).append(nl);
        }
        if (this.ep4_pnc.fppCotisPLMTC != 0.0) {
            sb.append("PREV.LONG.MAL TC :\t").append(this.ep4_pnc.fppBasePLMTC).append("\t").append(this.ep4_pnc.plmtc).append("\t\t").append(this.ep4_pnc.fppCotisPLMTC).append(nl);
        }
        sb.append("CSG DEDUCTIBLE :\t").append(this.ep4_pnc.fppBaseCSGd).append("\t").append(this.ep4_pnc.csgd).append("\t\t").append(this.ep4_pnc.fppCotisCSGd).append(nl);
        sb.append("CSG + CRDS N/DEDUC :\t").append(this.ep4_pnc.fppBaseCSGnd).append("\t").append(this.ep4_pnc.csgnd).append("\t\t").append(this.ep4_pnc.fppCotisCSGnd).append(nl);
        sb.append(nl);
        sb.append("INDEMNITE REPAS (inclus correction) :\t\t").append(this.ep4_pnc.fppRepasNonImp).append(nl);
        sb.append(nl);
        sb.append("====> NET BULLETIN :\t\t\t").append(this.ep4_pnc.fppNetBulletin).append(nl);
        sb.append(nl).append(nl);
        sb.append("COTISATIONS EMPLOYE :\t").append(this.ep4_pnc.fppCotisEmploye).append(nl);
        sb.append("I.DECOUCHERS F.PRO :\t").append(this.ep4_pnc.fppDEC).append(nl);
        sb.append("FRAIS PROFESS. :\t").append(this.ep4_pnc.fppFraisPro).append(nl);
        sb.append("NET IMPOSABLE :\t").append(this.ep4_pnc.fppNetImposable).append(nl);
        return sb.toString();
    }
    
    private String generatePayePncR() {
        final StringBuilder sb = new StringBuilder();
        final String nl = System.getProperty("line.separator");
        sb.append(nl);
        sb.append("TRAITEMENT FIXE :\t").append(this.ep4_pnc.fprBaseFixe).append("\t").append(Math.max(0, 30 - this.ep4_pnc.nSansSolde)).append("/30\t").append(this.ep4_pnc.fprFixe).append(nl);
        if (this.ep4_pnc.fprPrimeFonction != 0.0) {
            sb.append("PR.FONCT. :\t\t").append(this.ep4_pnc.fprBasePrimeFonction).append("\t").append(30 - this.ep4_pnc.nSansSolde).append("/30\t").append(this.ep4_pnc.fprPrimeFonction).append(nl);
        }
        if (this.ep4_pnc.fprHsFixe != 0.0) {
            sb.append("H.S/FIXE+PR.FONCT. :\t").append(this.ep4_pnc.volHSr).append("\t").append(this.ep4_pnc.tauxHSFixe).append("\t").append(this.ep4_pnc.fprHsFixe).append(nl);
        }
        sb.append(nl);
        if (this.ep4_pnc.fprCompMga != 0.0) {
            sb.append("CPLT SAL.MINI.GARA :\t").append(this.ep4_pnc.fprMga).append("\t\t").append(this.ep4_pnc.fprCompMga).append(nl);
        }
        sb.append("P.VOL+MAJ.NUIT+VLD :\t\t\t").append(this.ep4_pnc.fprPv).append(nl);
        if (this.ep4_pnc.fprHsPv != 0.0) {
            sb.append("H.S/P.V+NUIT+VLD :\t").append(this.ep4_pnc.volHSr).append("\t").append(this.ep4_pnc.tauxHSVolr).append("\t").append(this.ep4_pnc.fprHsPv).append(nl);
        }
        if (this.ep4_pnc.fprMaladie != 0.0 || this.ep4_pnc.fprCongePv != 0.0) {
            sb.append(nl);
        }
        if (this.ep4_pnc.fprMaladie != 0.0) {
            sb.append("GARANTIE REMU 80PV :\t").append(this.ep4_pnc.nMaladies).append("\t").append(this.ep4_pnc.fprBaseMaladie).append("\t").append(this.ep4_pnc.fprMaladie).append(nl);
        }
        if (this.ep4_pnc.fprCongePv != 0.0) {
            sb.append("CPLT SGMM :\t\t").append(this.ep4_pnc.nConges).append("\t").append(this.ep4_pnc.fprBaseCongePv).append("\t").append(this.ep4_pnc.fprCongePv).append(nl);
        }
        sb.append(nl);
        sb.append("====> BRUT FISCAL (inclus correction) :\t\t").append(this.ep4_pnc.fprBrutFiscal).append(nl);
        sb.append(nl);
        sb.append("I.ENT.HABILLEMENT :\t\t\t").append(this.ep4_pnc.fprPrimeHabillement).append(nl);
        sb.append(nl);
        sb.append("IND.REPAS (inclus correction) :\t\t").append(this.ep4_pnc.fprRepasImp).append(nl);
        sb.append("I.VOITURE/COURRIER (inclus correction) :\t\t").append(this.ep4_pnc.fprIkv).append(nl);
        sb.append(nl).append(nl);
        sb.append("SEC.SOC.MALADIE :\t").append(this.ep4_pnc.fprBaseSSM).append("\t").append(this.ep4_pnc.ssm).append("\t\t").append(this.ep4_pnc.fprCotisSSM).append(nl);
        sb.append("SEC.SOC.VIEILLESSE :\t").append(this.ep4_pnc.fprBaseSSV).append("\t").append(this.ep4_pnc.ssv).append("\t\t").append(this.ep4_pnc.fprCotisSSV).append(nl);
        sb.append("SS VIEILLESSE CPLT :\t").append(this.ep4_pnc.fprBaseSSVC).append("\t").append(this.ep4_pnc.ssvc).append("\t\t").append(this.ep4_pnc.fprCotisSSVC).append(nl);
        sb.append("COTISATION ASSEDIC :\t").append(this.ep4_pnc.fprBaseASS).append("\t").append(this.ep4_pnc.ass).append("\t\t").append(this.ep4_pnc.fprCotisASS).append(nl);
        sb.append("CRPN F.RETRAITE :\t").append(this.ep4_pnc.fprBaseCRPNr).append("\t").append(this.ep4_pnc.crpnr).append("\t\t").append(this.ep4_pnc.fprCotisCRPNr).append(nl);
        sb.append("CRPN F.ASSURANCE :\t").append(this.ep4_pnc.fprBaseCRPNa).append("\t").append(this.ep4_pnc.crpna).append("\t\t").append(this.ep4_pnc.fprCotisCRPNa).append(nl);
        sb.append("CRPN F.SPECIAL :\t").append(this.ep4_pnc.fprBaseCRPNs).append("\t").append(this.ep4_pnc.crpns).append("\t\t").append(this.ep4_pnc.fprCotisCRPNs).append(nl);
        sb.append("COTISATION MNPAF :\t").append(this.ep4_pnc.fprBaseMNPAF).append("\t").append(this.ep4_pnc.mnpaf).append("\t\t").append(this.ep4_pnc.fprCotisMNPAF).append(nl);
        sb.append("PREV.INAPT.DEFINIT :\t").append(this.ep4_pnc.fprBasePIAD).append("\t").append(this.ep4_pnc.iad).append("\t\t").append(this.ep4_pnc.fprCotisPIAD).append(nl);
        sb.append("PREV.DECES T1 :\t").append(this.ep4_pnc.fprBasePDT1).append("\t").append(this.ep4_pnc.pdt1).append("\t\t").append(this.ep4_pnc.fprCotisPDT1).append(nl);
        if (this.ep4_pnc.fprCotisPDT1B != 0.0) {
            sb.append("PREV.DECES T1B :\t").append(this.ep4_pnc.fprBasePDT1B).append("\t").append(this.ep4_pnc.pdt1B).append("\t\t").append(this.ep4_pnc.fprCotisPDT1B).append(nl);
        }
        if (this.ep4_pnc.fprCotisPDT2 != 0.0) {
            sb.append("PREV.DECES T2 :\t").append(this.ep4_pnc.fprBasePDT2).append("\t").append(this.ep4_pnc.pdt2).append("\t\t").append(this.ep4_pnc.fprCotisPDT2).append(nl);
        }
        if (this.ep4_pnc.fprCotisPDT3 != 0.0) {
            sb.append("PREV.DECES T3 :\t").append(this.ep4_pnc.fprBasePDT3).append("\t").append(this.ep4_pnc.pdt3).append("\t\t").append(this.ep4_pnc.fprCotisPDT3).append(nl);
        }
        if (this.ep4_pnc.fprCotisPDT4 != 0.0) {
            sb.append("PREV.DECES T4 :\t").append(this.ep4_pnc.fprBasePDT4).append("\t").append(this.ep4_pnc.pdt4).append("\t\t").append(this.ep4_pnc.fprCotisPDT4).append(nl);
        }
        if (this.ep4_pnc.fprCotisPDT5 != 0.0) {
            sb.append("PREV.DECES T5 :\t").append(this.ep4_pnc.fprBasePDT5).append("\t").append(this.ep4_pnc.pdt5).append("\t\t").append(this.ep4_pnc.fprCotisPDT5).append(nl);
        }
        sb.append("PREV.LONG.MAL TA :\t").append(this.ep4_pnc.fprBasePLMTA).append("\t").append(this.ep4_pnc.plmta).append("\t\t").append(this.ep4_pnc.fprCotisPLMTA).append(nl);
        if (this.ep4_pnc.fprCotisPLMTB != 0.0) {
            sb.append("PREV.LONG.MAL TB :\t").append(this.ep4_pnc.fprBasePLMTB).append("\t").append(this.ep4_pnc.plmtb).append("\t\t").append(this.ep4_pnc.fprCotisPLMTB).append(nl);
        }
        if (this.ep4_pnc.fprCotisPLMTC != 0.0) {
            sb.append("PREV.LONG.MAL TC :\t").append(this.ep4_pnc.fprBasePLMTC).append("\t").append(this.ep4_pnc.plmtc).append("\t\t").append(this.ep4_pnc.fprCotisPLMTC).append(nl);
        }
        sb.append("CSG DEDUCTIBLE :\t").append(this.ep4_pnc.fprBaseCSGd).append("\t").append(this.ep4_pnc.csgd).append("\t\t").append(this.ep4_pnc.fprCotisCSGd).append(nl);
        sb.append("CSG + CRDS N/DEDUC :\t").append(this.ep4_pnc.fprBaseCSGnd).append("\t").append(this.ep4_pnc.csgnd).append("\t\t").append(this.ep4_pnc.fprCotisCSGnd).append(nl);
        sb.append(nl);
        sb.append("INDEMNITE REPAS (inclus correction) :\t\t").append(this.ep4_pnc.fprRepasNonImp).append(nl);
        sb.append(nl);
        sb.append("====> NET BULLETIN :\t\t\t").append(this.ep4_pnc.fprNetBulletin).append(nl);
        sb.append(nl).append(nl);
        sb.append("COTISATIONS EMPLOYE :\t").append(this.ep4_pnc.fprCotisEmploye).append(nl);
        sb.append("I.DECOUCHERS F.PRO :\t").append(this.ep4_pnc.fprDEC).append(nl);
        sb.append("FRAIS PROFESS. :\t").append(this.ep4_pnc.fprFraisPro).append(nl);
        sb.append("NET IMPOSABLE :\t").append(this.ep4_pnc.fprNetImposable).append(nl);
        return sb.toString();
    }
    
    private void actionTglBtnProgReal(final boolean isSelected) {
        if (isSelected) {
            ChopeCrew.options.ep4_progreal = true;
        }
        else {
            ChopeCrew.options.ep4_progreal = false;
        }
        this.updatePaye();
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent e) {
        this.saveDonneesPersoFromDialog();
        this.updatePaye();
    }
    
    @Override
    public void tableChanged(final TableModelEvent e) {
        ChopeCrew.analyse.calculHCIRrAnalyseCrew();
        this.updatePaye();
        this.updateTables();
        this.tableChanged = true;
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BorderLayout(0, 0));
            this.jContentPane.add(this.getJTabbedPane());
            this.horizontalStrut_West = Box.createHorizontalStrut(6);
            this.jContentPane.add(this.horizontalStrut_West, "West");
            this.horizontalStrut_East = Box.createHorizontalStrut(6);
            this.jContentPane.add(this.horizontalStrut_East, "East");
            this.verticalStrut_South = Box.createVerticalStrut(10);
            this.jContentPane.add(this.verticalStrut_South, "South");
            this.verticalStrut_North = Box.createVerticalStrut(10);
            this.jContentPane.add(this.verticalStrut_North, "North");
        }
        return this.jContentPane;
    }
    
    private JTabbedPane getJTabbedPane() {
        if (this.jTabbedPane == null) {
            (this.jTabbedPane = new JTabbedPane()).setTabPlacement(1);
            this.jTabbedPane.setTabLayoutPolicy(1);
            this.jTabbedPane.setFont(new Font("Tahoma", 0, 16));
            this.jTabbedPane.addTab("Paye", null, this.getPnl_Paye(), "Feuille de paie");
            this.jTabbedPane.addTab("Temps de vol", null, this.getSpnl_TempsVol(), "Temps de vol programm\u00e9s et r\u00e9alis\u00e9s");
            this.jTabbedPane.addTab("D\u00e9compte prog.", null, this.getSpnl_HCp(), "HC d\u00e9compt\u00e9es programm\u00e9es");
            this.jTabbedPane.addTab("D\u00e9compte r\u00e9al.", null, this.getSpnl_HCr(), "HC d\u00e9compt\u00e9es r\u00e9alis\u00e9es");
            this.jTabbedPane.addTab("R\u00e9mu prog.", null, this.getSpnl_HCRp(), "HC r\u00e9mun\u00e9r\u00e9es programm\u00e9es");
            this.jTabbedPane.addTab("R\u00e9mu r\u00e9al.", null, this.getSpnl_HCRr(), "HC r\u00e9mun\u00e9r\u00e9es r\u00e9alis\u00e9es");
            this.jTabbedPane.addTab("Indem prog.", null, this.getSpnl_IRp(), "Indemnit\u00e9s programm\u00e9es");
            this.jTabbedPane.addTab("Indem r\u00e9al.", null, this.getSpnl_IRr(), "Indemnit\u00e9s r\u00e9alis\u00e9es");
            this.jTabbedPane.addTab("D\u00e9c. EP4", null, this.getSpnl_DecompteEP4(), "D\u00e9compte d'activit\u00e9 EP4");
            this.jTabbedPane.addTab("Hor. EP4", null, this.getSpnl_HoraireEP4(), "Horaires EP4");
        }
        return this.jTabbedPane;
    }
    
    private JPanel getPnl_Paye() {
        if (this.pnl_Paye == null) {
            (this.pnl_Paye = new JPanel()).setLayout(new BoxLayout(this.pnl_Paye, 0));
            this.pnl_Paye.add(this.getSpnl_Paye());
            this.pnl_Paye.add(this.getHorizontalStrut_PnlPaye());
            this.pnl_Paye.add(this.getPnl_Paye_Commandes());
        }
        return this.pnl_Paye;
    }
    
    private JScrollPane getSpnl_Paye() {
        if (this.spnl_Paye == null) {
            (this.spnl_Paye = new JScrollPane()).setPreferredSize(new Dimension(2, 600));
            this.spnl_Paye.setBorder(BorderFactory.createTitledBorder(null, "----  Programm\u00e9  ----", 2, 1, new Font("Tahoma", 0, 11), new Color(51, 51, 51)));
            this.spnl_Paye.setViewportView(this.getTxta_Paye());
        }
        return this.spnl_Paye;
    }
    
    private JTextArea getTxta_Paye() {
        if (this.txta_Paye == null) {
            (this.txta_Paye = new JTextArea()).setFont(new Font("Tahoma", 0, 16));
            this.txta_Paye.setLineWrap(true);
            this.txta_Paye.setWrapStyleWord(true);
            this.txta_Paye.setEditable(false);
            this.txta_Paye.setCaretPosition(0);
        }
        return this.txta_Paye;
    }
    
    private JPanel getPnl_Paye_Commandes() {
        if (this.pnl_Paye_Commandes == null) {
            (this.pnl_Paye_Commandes = new JPanel()).setLayout(new BoxLayout(this.pnl_Paye_Commandes, 1));
            this.pnl_Paye_Commandes.setMaximumSize(new Dimension(180, 32767));
            this.pnl_Paye_Commandes.add(this.getVerticalStrut_PnlCommandes());
            this.pnl_Paye_Commandes.add(this.getPnl_Corrections());
            this.pnl_Paye_Commandes.add(this.getVerticalGlue_PnlCommandes());
            this.pnl_Paye_Commandes.add(this.getTglbtn_ProgReal());
        }
        return this.pnl_Paye_Commandes;
    }
    
    private JPanel getPnl_Corrections() {
        if (this.pnl_Corrections == null) {
            (this.pnl_Corrections = new JPanel()).setBorder(BorderFactory.createTitledBorder(null, "Corrections", 0, 0, new Font("Tahoma", 0, 10), new Color(51, 51, 51)));
            this.pnl_Corrections.setLayout(new BoxLayout(this.pnl_Corrections, 1));
            this.pnl_Corrections.add(this.getLbl_cBF());
            this.pnl_Corrections.add(this.getTfld_cBF());
            this.pnl_Corrections.add(this.getLbl_cIKV());
            this.pnl_Corrections.add(this.getTfld_cIKV());
            this.pnl_Corrections.add(this.getLbl_cIRimp());
            this.pnl_Corrections.add(this.getTfld_cIRimp());
            this.pnl_Corrections.add(this.getLbl_cIRnimp());
            this.pnl_Corrections.add(this.getTfld_cIRnimp());
        }
        return this.pnl_Corrections;
    }
    
    private JLabel getLbl_cBF() {
        if (this.lbl_cBF == null) {
            (this.lbl_cBF = new JLabel()).setAlignmentX(0.5f);
            this.lbl_cBF.setFont(new Font("Tahoma", 0, 16));
            this.lbl_cBF.setText("Brut fiscal :");
        }
        return this.lbl_cBF;
    }
    
    private JLabel getLbl_cIKV() {
        if (this.lbl_cIKV == null) {
            (this.lbl_cIKV = new JLabel()).setAlignmentX(0.5f);
            this.lbl_cIKV.setFont(new Font("Tahoma", 0, 16));
            this.lbl_cIKV.setText("IKV :");
        }
        return this.lbl_cIKV;
    }
    
    private JLabel getLbl_cIRimp() {
        if (this.lbl_cIRimp == null) {
            (this.lbl_cIRimp = new JLabel()).setAlignmentX(0.5f);
            this.lbl_cIRimp.setFont(new Font("Tahoma", 0, 16));
            this.lbl_cIRimp.setText("IR imp. :");
        }
        return this.lbl_cIRimp;
    }
    
    private JLabel getLbl_cIRnimp() {
        if (this.lbl_cIRnimp == null) {
            (this.lbl_cIRnimp = new JLabel()).setAlignmentX(0.5f);
            this.lbl_cIRnimp.setFont(new Font("Tahoma", 0, 16));
            this.lbl_cIRnimp.setText("IR non imp. :");
        }
        return this.lbl_cIRnimp;
    }
    
    private JFormattedTextField getTfld_cBF() {
        if (this.tfld_cBF == null) {
            (this.tfld_cBF = new JFormattedTextField()).setHorizontalAlignment(11);
            this.tfld_cBF.setMaximumSize(new Dimension(140, 32));
            this.tfld_cBF.setFont(new Font("Tahoma", 0, 16));
            this.tfld_cBF.setFormatterFactory(this.defaultFormatterFactory);
            this.tfld_cBF.setToolTipText("Correction sur brut fiscal");
        }
        return this.tfld_cBF;
    }
    
    private JFormattedTextField getTfld_cIKV() {
        if (this.tfld_cIKV == null) {
            (this.tfld_cIKV = new JFormattedTextField()).setHorizontalAlignment(11);
            this.tfld_cIKV.setMaximumSize(new Dimension(140, 32));
            this.tfld_cIKV.setFont(new Font("Tahoma", 0, 16));
            this.tfld_cIKV.setFormatterFactory(this.defaultFormatterFactory);
            this.tfld_cIKV.setToolTipText("Correction sur indemnit\u00e9s kilom\u00e9triques");
        }
        return this.tfld_cIKV;
    }
    
    private JFormattedTextField getTfld_cIRimp() {
        if (this.tfld_cIRimp == null) {
            (this.tfld_cIRimp = new JFormattedTextField()).setHorizontalAlignment(11);
            this.tfld_cIRimp.setMaximumSize(new Dimension(140, 32));
            this.tfld_cIRimp.setFont(new Font("Tahoma", 0, 16));
            this.tfld_cIRimp.setFormatterFactory(this.defaultFormatterFactory);
            this.tfld_cIRimp.setToolTipText("Correction sur indemnit\u00e9s repas imposables");
        }
        return this.tfld_cIRimp;
    }
    
    private JFormattedTextField getTfld_cIRnimp() {
        if (this.tfld_cIRnimp == null) {
            (this.tfld_cIRnimp = new JFormattedTextField()).setHorizontalAlignment(11);
            this.tfld_cIRnimp.setMaximumSize(new Dimension(140, 32));
            this.tfld_cIRnimp.setFont(new Font("Tahoma", 0, 16));
            this.tfld_cIRnimp.setFormatterFactory(this.defaultFormatterFactory);
            this.tfld_cIRnimp.setToolTipText("Correction sur indemnit\u00e9s repas non imposables");
        }
        return this.tfld_cIRnimp;
    }
    
    private JToggleButton getTglbtn_ProgReal() {
        if (this.tglbtn_ProgReal == null) {
            (this.tglbtn_ProgReal = new JToggleButton()).setAlignmentX(0.5f);
            this.tglbtn_ProgReal.setPreferredSize(new Dimension(140, 40));
            this.tglbtn_ProgReal.setMaximumSize(new Dimension(140, 40));
            this.tglbtn_ProgReal.setMargin(new Insets(2, 2, 2, 2));
            this.tglbtn_ProgReal.setFont(new Font("Tahoma", 0, 16));
            this.tglbtn_ProgReal.setText("Prog. / R\u00e9al.");
            this.tglbtn_ProgReal.setToolTipText("Alterne entre programm\u00e9 et r\u00e9alis\u00e9");
            this.tglbtn_ProgReal.addMouseListener(new MyMouseAdapter());
            this.tglbtn_ProgReal.addKeyListener(new MyKeyAdapter());
        }
        return this.tglbtn_ProgReal;
    }
    
    private JScrollPane getSpnl_TempsVol() {
        if (this.spnl_TempsVol == null) {
            (this.spnl_TempsVol = new JScrollPane()).setViewportView(this.getJTable_TempsVol());
        }
        return this.spnl_TempsVol;
    }
    
    private EP4Table getJTable_TempsVol() {
        if (this.jTable_TempsVol == null) {
            (this.jTable_TempsVol = new EP4Table()).setRowHeight(24);
            final EP4TableModel model = new EP4TableModel();
            model.loadTableTempsVol(ChopeCrew.analyse);
            model.addTableModelListener(this);
            this.jTable_TempsVol.setModel(model);
            final EP4TableHeader ccth = new EP4TableHeader(this.jTable_TempsVol.getColumnModel());
            final String[] myToolTipsText = { "<html>Date du service de vol</html>", "<html>N° de vol</html>", "<html>Mise en place</html>", "<html>Origine</html>", "<html>Destination</html>", "<html>Bloc d\u00e9part programm\u00e9 [DEPp]</html>", "<html>Bloc arriv\u00e9e programm\u00e9 [ARRp]</html>", "<html>Temps de vol ou de mise en place programm\u00e9 [TVp / MEPp]</html>", "<html>Bloc d\u00e9part r\u00e9alis\u00e9 [DEPr]</html>", "<html>Bloc arriv\u00e9e r\u00e9alis\u00e9 [ARRr]</html>", "<html>Temps de vol ou de mise en place r\u00e9alis\u00e9 [TVr / MEPr]</html>" };
            ccth.setToolTipsText(myToolTipsText);
            this.jTable_TempsVol.setTableHeader(ccth);
        }
        return this.jTable_TempsVol;
    }
    
    private JScrollPane getSpnl_HCp() {
        if (this.spnl_HCp == null) {
            (this.spnl_HCp = new JScrollPane()).setViewportView(this.getJTable_HCp());
        }
        return this.spnl_HCp;
    }
    
    private EP4Table getJTable_HCp() {
        if (this.jTable_HCp == null) {
            (this.jTable_HCp = new EP4Table()).setRowHeight(24);
            final EP4TableModel model = new EP4TableModel();
            model.loadTableHCp(ChopeCrew.analyse);
            model.addTableModelListener(this);
            this.jTable_HCp.setModel(model);
            final EP4TableHeader ccth = new EP4TableHeader(this.jTable_HCp.getColumnModel());
            final String[] myToolTipsText = { "<html>Date du service de vol</html>", "<html>Origine</html>", "<html>Destination</html>", "<html>Temps de vol programm\u00e9 [TVp]</html>", "<html>Temps de  vol de r\u00e9f\u00e9rence [TVref]</html>", "<html>Heures de vol 100% [HV100%p] :<br> = Max (TVp, TVref)</html>", "<html>Temps de vol en MEP [MEPp]", "<html>Temps moyen d'\u00e9tape [TMEp] :<br> = \u03a3TVp / Nb.\u00e9tapes ; mini. 1</html>", "<html>Coefficient majorateur de tron\u00e7on [CMTp] :<br> = 70 / (21*TMEp + 30) ; mini.1</html>", "<html>Heures d\u00e9compt\u00e9es au titre des heures de vol [HCVp] :<br> = \u03a3HV100%p * CMTp + \u03a3MEPp / 2</html>", "<html>Heures d\u00e9compt\u00e9es au titre du temps de service de vol [HCTp] :<br> MC = TSVp / 1.64 ; mini. 3.5<br> LC = TSVp / 1.75 ; mini. 4</html>", "<html>[\u03a3H1p] :<br> = \u03a3(Max (HCVp, HCTp))</html>", "<html>Heures d\u00e9compt\u00e9es au titre du temps d'absence [HCAp] :<br> MC = 4 * Nb.jours d'engagement<br> LC = TAp * 5 / 24</html>", "<html>Temps de vol de nuit [TVNp] :<br> MC = p\u00e9riode [DEPp -> TVref + 10'] comprise entre 21h00 et 09h00 locales de Paris<br> LC = p\u00e9riode [DEPp -> TVref + 30'] comprise entre 18h00 et 06h00 locales de l'origine du service de vol</html>" };
            ccth.setToolTipsText(myToolTipsText);
            this.jTable_HCp.setTableHeader(ccth);
        }
        return this.jTable_HCp;
    }
    
    private JScrollPane getSpnl_HCr() {
        if (this.spnl_HCr == null) {
            (this.spnl_HCr = new JScrollPane()).setViewportView(this.getJTable_HCr());
        }
        return this.spnl_HCr;
    }
    
    private EP4Table getJTable_HCr() {
        if (this.jTable_HCr == null) {
            (this.jTable_HCr = new EP4Table()).setRowHeight(24);
            final EP4TableModel model = new EP4TableModel();
            model.loadTableHCr(ChopeCrew.analyse);
            model.addTableModelListener(this);
            this.jTable_HCr.setModel(model);
            final EP4TableHeader ccth = new EP4TableHeader(this.jTable_HCr.getColumnModel());
            final String[] myToolTipsText = { "<html>Date du service de vol</html>", "<html>Origine</html>", "<html>Destination</html>", "<html>Temps de vol r\u00e9alis\u00e9 [TVr]</html>", "<html>Temps de  vol de r\u00e9f\u00e9rence [TVref]</html>", "<html>Heures de vol 100% [HV100%r] :<br> = Max (TVr, TVref)</html>", "<html>Temps de vol en MEP [MEPr]", "<html>Temps moyen d'\u00e9tape [TMEr] :<br> = \u03a3TVr / Nb.\u00e9tapes ; mini. 1</html>", "<html>Coefficient majorateur de tron\u00e7on [CMTr] :<br> = 70 / (21*TMEr + 30) ; mini.1</html>", "<html>Heures d\u00e9compt\u00e9es au titre des heures de vol [HCVr] :<br> = \u03a3HV100%r * CMTr + \u03a3MEPr / 2</html>", "<html>Heures d\u00e9compt\u00e9es au titre du temps de service de vol [HCTp] :<br> MC = TSVr / 1.64 ; mini. 3.5<br> LC = TSVr / 1.75 ; mini. 4</html>", "<html>[\u03a3H1r] :<br> = \u03a3(Max (HCVr, HCTr))</html>", "<html>Heures d\u00e9compt\u00e9es au titre du temps d'absence [HCAr] :<br> MC = 4 * Nb.jours d'engagement<br> LC = TAr * 5 / 24</html>", "<html>Temps de vol de nuit [TVNr] :<br> MC = p\u00e9riode [DEPr -> TVref + 10'] comprise entre 21h00 et 09h00 locales de Paris<br> LC = p\u00e9riode [DEPr -> TVref + 30'] comprise entre 18h00 et 06h00 locales de l'origine du service de vol</html>" };
            ccth.setToolTipsText(myToolTipsText);
            this.jTable_HCr.setTableHeader(ccth);
        }
        return this.jTable_HCr;
    }
    
    private JScrollPane getSpnl_HCRp() {
        if (this.spnl_HCRp == null) {
            (this.spnl_HCRp = new JScrollPane()).setViewportView(this.getJTable_HCRp());
        }
        return this.spnl_HCRp;
    }
    
    private EP4Table getJTable_HCRp() {
        if (this.jTable_HCRp == null) {
            (this.jTable_HCRp = new EP4Table()).setRowHeight(24);
            final EP4TableModel model = new EP4TableModel();
            model.loadTableHCRp(ChopeCrew.analyse);
            model.addTableModelListener(this);
            this.jTable_HCRp.setModel(model);
            final EP4TableHeader ccth = new EP4TableHeader(this.jTable_HCRp.getColumnModel());
            final String[] myToolTipsText = { "<html>Date du service de vol</html>", "<html>Origine</html>", "<html>Destination</html>", "<html>Temps de vol programm\u00e9 [TVp]</html>", "<html>Temps de  vol de r\u00e9f\u00e9rence [TVref]</html>", "<html>Heures de vol 100% r\u00e9mun\u00e9r\u00e9es [HV100%Rp] :<br> MC = TVref + 10'<br> LC = TVref + 30'</html>", "<html>Temps de vol en MEP [MEPp]", "<html>Temps moyen d'\u00e9tape [TMEp] :<br> = \u03a3TVp / Nb.\u00e9tapes ; mini. 1</html>", "<html>Coefficient majorateur de tron\u00e7on [CMTp] :<br> = 70 / (21*TMEp + 30) ; mini.1</html>", "<html>Heures r\u00e9mun\u00e9r\u00e9es au titre des heures de vol [HCVRp] :<br> = \u03a3HV100%Rp * CMTp + \u03a3MEPp / 2</html>", "<html>Heures r\u00e9mun\u00e9r\u00e9es au titre du temps de service de vol [HCTp] :<br> MC = TSVp / 1.64 ; mini. 3.5<br> LC = TSVp / 1.75 ; mini. 4</html>", "<html>[\u03a3H1Rp] :<br> = \u03a3(Max (HCVRp, HCTp))</html>", "<html>Heures r\u00e9mun\u00e9r\u00e9es au titre du temps d'absence [HCAp] :<br> MC = 4 * Nb.jours d'engagement<br> LC = TAp * 5 / 24</html>", "<html>Temps de vol de nuit [TVNp] :<br> MC = p\u00e9riode [DEPp -> TVref + 10'] comprise entre 21h00 et 09h00 locales de Paris<br> LC = p\u00e9riode [DEPp -> TVref + 30'] comprise entre 18h00 et 06h00 locales de l'origine du service de vol</html>" };
            ccth.setToolTipsText(myToolTipsText);
            this.jTable_HCRp.setTableHeader(ccth);
        }
        return this.jTable_HCRp;
    }
    
    private JScrollPane getSpnl_HCRr() {
        if (this.spnl_HCRr == null) {
            (this.spnl_HCRr = new JScrollPane()).setViewportView(this.getJTable_HCRr());
        }
        return this.spnl_HCRr;
    }
    
    private EP4Table getJTable_HCRr() {
        if (this.jTable_HCRr == null) {
            (this.jTable_HCRr = new EP4Table()).setRowHeight(24);
            final EP4TableModel model = new EP4TableModel();
            model.loadTableHCRr(ChopeCrew.analyse);
            model.addTableModelListener(this);
            this.jTable_HCRr.setModel(model);
            final EP4TableHeader ccth = new EP4TableHeader(this.jTable_HCRr.getColumnModel());
            final String[] myToolTipsText = { "<html>Date du service de vol</html>", "<html>Origine</html>", "<html>Destination</html>", "<html>Temps de vol r\u00e9alis\u00e9 [TVr]</html>", "<html>Temps de  vol de r\u00e9f\u00e9rence [TVref]</html>", "<html>Heures de vol 100% r\u00e9mun\u00e9r\u00e9es [HV100%Rr] :<br> MC = TVref + 10'<br> LC = TVref + 30'</html>", "<html>Temps de vol en MEP [MEPr]", "<html>Temps moyen d'\u00e9tape [TMEr] :<br> = \u03a3TVr / Nb.\u00e9tapes ; mini. 1</html>", "<html>Coefficient majorateur de tron\u00e7on [CMTr] :<br> = 70 / (21*TMEr + 30) ; mini.1</html>", "<html>Heures r\u00e9mun\u00e9r\u00e9es au titre des heures de vol [HCVRr] :<br> = \u03a3HV100%Rr * CMTr + \u03a3MEPr / 2</html>", "<html>Heures r\u00e9mun\u00e9r\u00e9es au titre du temps de service de vol [HCTr] :<br> MC = TSVr / 1.64 ; mini. 3.5<br> LC = TSVr / 1.75 ; mini. 4</html>", "<html>[\u03a3H1Rr] :<br> = \u03a3(Max (HCVRr, HCTr))</html>", "<html>Heures r\u00e9mun\u00e9r\u00e9es au titre du temps d'absence [HCAr] :<br> MC = 4 * Nb.jours d'engagement<br> LC = TAr * 5 / 24</html>", "<html>Temps de vol de nuit [TVNr] :<br> MC = p\u00e9riode [DEPr -> TVref + 10'] comprise entre 21h00 et 09h00 locales de Paris<br> LC = p\u00e9riode [DEPr -> TVref + 30'] comprise entre 18h00 et 06h00 locales de l'origine du service de vol</html>" };
            ccth.setToolTipsText(myToolTipsText);
            this.jTable_HCRr.setTableHeader(ccth);
        }
        return this.jTable_HCRr;
    }
    
    private JScrollPane getSpnl_IRp() {
        if (this.spnl_IRp == null) {
            (this.spnl_IRp = new JScrollPane()).setViewportView(this.getJTable_IRp());
        }
        return this.spnl_IRp;
    }
    
    private EP4Table getJTable_IRp() {
        if (this.jTable_IRp == null) {
            (this.jTable_IRp = new EP4Table()).setRowHeight(24);
            final EP4TableModel model = new EP4TableModel();
            model.loadTableIRp(ChopeCrew.analyse);
            model.addTableModelListener(this);
            this.jTable_IRp.setModel(model);
            final EP4TableHeader ccth = new EP4TableHeader(this.jTable_IRp.getColumnModel());
            final String[] myToolTipsText = { "<html>Date du service de vol</html>", "<html>N° de vol</html>", "<html>Bloc d\u00e9part programmm\u00e9 [DEPp]</html>", "<html>Origine</html>", "<html>Destination</html>", "<html>Bloc arriv\u00e9e programm\u00e9 [ARRp]</html>", "<html>Nombre d'indemnit\u00e9s repas vers\u00e9es \u00e0 l'origine</html>", "<html>Nombre de menu frais vers\u00e9s \u00e0 l'origine</html>", "<html>Nombre de d\u00e9couchers</html>", "<html>Nombre d'indemnit\u00e9s repas vers\u00e9es \u00e0 la destination</html>", "<html>Nombre de menu frais vers\u00e9s \u00e0 la destination</html>", "<html>Indemnit\u00e9 de d\u00e9placement \u00e0 l'aller</html>", "<html>Indemnit\u00e9 de d\u00e9placement au retour</html>" };
            ccth.setToolTipsText(myToolTipsText);
            this.jTable_IRp.setTableHeader(ccth);
        }
        return this.jTable_IRp;
    }
    
    private JScrollPane getSpnl_IRr() {
        if (this.spnl_IRr == null) {
            (this.spnl_IRr = new JScrollPane()).setViewportView(this.getJTable_IRr());
        }
        return this.spnl_IRr;
    }
    
    private EP4Table getJTable_IRr() {
        if (this.jTable_IRr == null) {
            (this.jTable_IRr = new EP4Table()).setRowHeight(24);
            final EP4TableModel model = new EP4TableModel();
            model.loadTableIRr(ChopeCrew.analyse);
            model.addTableModelListener(this);
            this.jTable_IRr.setModel(model);
            final EP4TableHeader ccth = new EP4TableHeader(this.jTable_IRr.getColumnModel());
            final String[] myToolTipsText = { "<html>Date du service de vol</html>", "<html>N° de vol</html>", "<html>Bloc d\u00e9part r\u00e9alis\u00e9 [DEPr]</html>", "<html>Origine</html>", "<html>Destination</html>", "<html>Bloc arriv\u00e9e r\u00e9alis\u00e9 [ARRr]</html>", "<html>Nombre d'indemnit\u00e9s repas vers\u00e9es \u00e0 l'origine</html>", "<html>Nombre de menu frais vers\u00e9s \u00e0 l'origine</html>", "<html>Nombre de d\u00e9couchers</html>", "<html>Nombre d'indemnit\u00e9s repas vers\u00e9es \u00e0 la destination</html>", "<html>Nombre de menu frais vers\u00e9s \u00e0 la destination</html>", "<html>Indemnit\u00e9 de d\u00e9placement \u00e0 l'aller</html>", "<html>Indemnit\u00e9 de d\u00e9placement au retour</html>" };
            ccth.setToolTipsText(myToolTipsText);
            this.jTable_IRr.setTableHeader(ccth);
        }
        return this.jTable_IRr;
    }
    
    private JScrollPane getSpnl_DecompteEP4() {
        if (this.spnl_DecompteEP4 == null) {
            (this.spnl_DecompteEP4 = new JScrollPane()).setViewportView(this.getJTable_DecompteEP4());
        }
        return this.spnl_DecompteEP4;
    }
    
    private EP4Table getJTable_DecompteEP4() {
        if (this.jTable_DecompteEP4 == null) {
            (this.jTable_DecompteEP4 = new EP4Table()).setRowHeight(24);
            final EP4TableModel model = new EP4TableModel();
            model.loadTableDecompteEP4(ChopeCrew.analyse);
            model.addTableModelListener(this);
            this.jTable_DecompteEP4.setModel(model);
            final EP4TableHeader ccth = new EP4TableHeader(this.jTable_DecompteEP4.getColumnModel());
            final String[] myToolTipsText = { "<html>Date du service de vol</html>", "<html>Origine</html>", "<html>Destination</html>", "<html>Temps de vol r\u00e9alis\u00e9 [TVr]</html>", "<html>Temps moyen d'\u00e9tape [TMEr] :<br> = \u03a3TVr / Nb.\u00e9tapes ; mini. 1</html>", "<html>Coefficient majorateur de tron\u00e7on [CMTr] :<br> = 70 / (21*TMEr + 30) ; mini.1</html>", "<html>Heures de vol 100% [HV100%r] :<br> = Max (TVr, TVref)</html>", "<html>Heures d\u00e9compt\u00e9es au titre des heures de vol [HCVr] :<br> = \u03a3HV100%r * CMTr + \u03a3MEPr / 2</html>", "<html>Heures d\u00e9compt\u00e9es au titre du temps de service de vol [HCTr] :<br> MC = TSVr / 1.64 ; mini. 3.5<br> LC = TSVr / 1.75 ; mini. 4</html>", "<html>Heures d\u00e9compt\u00e9es au titre du temps d'absence [HCAr] :<br> MC = 4 * Nb.jours d'engagement<br> LC = TAr * 5 / 24</html>", "<html>[H1r] :<br> = Max (HCVr, HCTr)</html>", "<html>[H2r] :<br> = Max (HCAr, \u03a3H1)</html>", "<html>Heures de vol 100% r\u00e9mun\u00e9r\u00e9es [HV100%Rr] :<br> MC = TVref + 10 min.<br> LC = TVref + 30 min.</html>", "<html>Heures r\u00e9mun\u00e9r\u00e9es au titre des heures de vol [HCVRr] :<br> = \u03a3HV100%Rr * CMTr + \u03a3MEPr / 2</html>", "<html>[H1Rr] :<br> = Max (HCVRr, HCTr)</html>", "<html>[H2Rr] :<br> = Max (HCAr, \u03a3H1R)</html>", "<html>Majoration de nuit :<br> = 50% TVNr</html>" };
            ccth.setToolTipsText(myToolTipsText);
            this.jTable_DecompteEP4.setTableHeader(ccth);
        }
        return this.jTable_DecompteEP4;
    }
    
    private JScrollPane getSpnl_HoraireEP4() {
        if (this.spnl_HoraireEP4 == null) {
            (this.spnl_HoraireEP4 = new JScrollPane()).setViewportView(this.getJTable_HoraireEP4());
        }
        return this.spnl_HoraireEP4;
    }
    
    private EP4Table getJTable_HoraireEP4() {
        if (this.jTable_HoraireEP4 == null) {
            (this.jTable_HoraireEP4 = new EP4Table()).setRowHeight(24);
            final EP4TableModel model = new EP4TableModel();
            model.loadTableHoraireEP4(ChopeCrew.analyse);
            model.addTableModelListener(this);
            this.jTable_HoraireEP4.setModel(model);
            final EP4TableHeader ccth = new EP4TableHeader(this.jTable_HoraireEP4.getColumnModel());
            final String[] myToolTipsText = { "<html>Date du service de vol</html>", "<html>N° de vol</html>", "<html>Bloc d\u00e9part programm\u00e9 [DEPp]</html>", "<html>Bloc d\u00e9part r\u00e9alis\u00e9 [DEPr]</html>", "<html>Origine</html>", "<html>Destination</html>", "<html>Bloc arriv\u00e9e r\u00e9alis\u00e9 [ARRr]</html>", "<html>Bloc arriv\u00e9e programm\u00e9 [ARRp]</html>", "<html>Temps de vol r\u00e9alis\u00e9 [TVr]</html>", "<html>Temps de vol programm\u00e9 [TVp]</html>", "<html>Temps de vol de r\u00e9f\u00e9rence [TVref]</html>", "<html>Temps de service de vol [TSVr]</html>", "<html>Temps d'absence [TAr]</html>", "<html>Temps de vol de nuit [TVNr] :<br> MC = p\u00e9riode [DEPr -> TVref + 10'] comprise entre 21h00 et 09h00 locales de Paris<br> LC = p\u00e9riode [DEPr -> TVref + 30'] comprise entre 18h00 et 06h00 locales de l'origine du service de vol</html>" };
            ccth.setToolTipsText(myToolTipsText);
            this.jTable_HoraireEP4.setTableHeader(ccth);
        }
        return this.jTable_HoraireEP4;
    }
    
    private Component getHorizontalStrut_PnlPaye() {
        if (this.horizontalStrut_PnlPaye == null) {
            this.horizontalStrut_PnlPaye = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_PnlPaye;
    }
    
    private Component getVerticalStrut_PnlCommandes() {
        if (this.verticalStrut_PnlCommandes == null) {
            this.verticalStrut_PnlCommandes = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_PnlCommandes;
    }
    
    private Component getVerticalGlue_PnlCommandes() {
        if (this.verticalGlue_PnlCommandes == null) {
            this.verticalGlue_PnlCommandes = Box.createVerticalGlue();
        }
        return this.verticalGlue_PnlCommandes;
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_Ep4.this.tglbtn_ProgReal) {
                Dlg_Ep4.this.actionTglBtnProgReal(Dlg_Ep4.this.tglbtn_ProgReal.isSelected());
            }
        }
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10 && e.getSource() == Dlg_Ep4.this.tglbtn_ProgReal) {
                Dlg_Ep4.this.tglbtn_ProgReal.doClick();
                Dlg_Ep4.this.actionTglBtnProgReal(Dlg_Ep4.this.tglbtn_ProgReal.isSelected());
            }
        }
    }
}
