// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import javax.swing.border.EtchedBorder;
import java.awt.Rectangle;
import java.awt.FlowLayout;
import java.awt.event.KeyListener;
import java.awt.GridLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingWorker;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.Iterator;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.calendar.Calendar;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.util.store.DataStoreFactory;
import java.util.Collection;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import java.util.Collections;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.util.prefs.BackingStoreException;
import ccUtils.Utils;
import java.util.prefs.Preferences;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import java.io.File;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.awt.Container;
import chopeCrew.ChopeCrew;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JDialog;

public class Dlg_ExportOptions extends JDialog
{
    private static final long serialVersionUID = 1L;
    private boolean expRotBak;
    private boolean expVolBak;
    private boolean expActSolBak;
    private boolean expReposBak;
    private boolean expCongesBak;
    private boolean expAbsencesBak;
    private boolean expDispersionsBak;
    private String loginGoogleBak;
    private String calendarsEntetesBak;
    private String calendarsTronconsBak;
    private String calendarsActSolBak;
    private String calendarsCongesBak;
    private String calendarsReposBak;
    private String calendarsAbsencesBak;
    private String calendarsDispersionsBak;
    private String colorEntetesBak;
    private String colorTronconsBak;
    private String colorActSolBak;
    private String colorCongesBak;
    private String colorReposBak;
    private String colorAbsencesBak;
    private String colorDispersionBak;
    private boolean isGoogleParamUpdated;
    private boolean isFirstTextfieldUpdated;
    public boolean optionsExportChanged;
    private JPanel jContentPane;
    private JTabbedPane tabb_ExportOptions;
    private JPanel pnl_OptionsBasiques;
    private JPanel pnl_ListExport;
    private JCheckBox cbx_Rot;
    private JCheckBox cbx_Vol;
    private JCheckBox cbx_ActSol;
    private JCheckBox cbx_Repos;
    private JCheckBox cbx_Conges;
    private JCheckBox cbx_Absences;
    private JCheckBox cbx_Dispersions;
    private JComboBox<String> cobx_PositionRot;
    private JComboBox<String> cobx_PositionSol;
    private JTabbedPane tabb_Categorie;
    private JPanel pnl_CategorieRot;
    private JTextField tfld_CategRot;
    private JPanel pnl_CategorieVol;
    private JTextField tfld_CategVol;
    private JPanel pnl_CategorieActSol;
    private JTextField tfld_CategActSol;
    private JPanel pnl_CategorieConges;
    private JTextField tfld_CategConges;
    private JPanel pnl_CategorieRepos;
    private JTextField tfld_CategRepos;
    private JPanel pnl_CategorieAbsences;
    private JTextField tfld_CategAbsences;
    private JPanel pnl_CategorieDispersions;
    private JTextField tfld_CategDispersions;
    private JTabbedPane tabb_PersoAgenda;
    private JPanel pnl_PersoRot;
    private JTextField tfld_PersoRot;
    private JPanel pnl_PersoVol;
    private JTextField tfld_PersoVol;
    private JPanel pnl_PersoActSol;
    private JTextField tfld_PersoActSol;
    private JPanel pnl_PersoConges;
    private JTextField tfld_PersoConges;
    private JPanel pnl_PersoRepos;
    private JTextField tfld_PersoRepos;
    private JPanel pnl_PersoAbsences;
    private JTextField tfld_PersoAbsences;
    private JPanel pnl_PersoDispersions;
    private JTextField tfld_PersoDispersions;
    private JPanel pnl_OptionsAvancees;
    private JCheckBox cbx_geneTroncon;
    private JCheckBox cbx_peqTroncon;
    private JCheckBox cbx_recoTroncon;
    private JCheckBox cbx_indemTroncon;
    private JCheckBox cbx_prestaTroncon;
    private JCheckBox cbx_hotelTroncon;
    private JCheckBox cbx_payeTroncon;
    private JPanel pnl_DetailsRot;
    private JCheckBox cbx_geneRot;
    private JCheckBox cbx_peqRot;
    private JCheckBox cbx_recoRot;
    private JCheckBox cbx_payeRot;
    private JPanel pnl_DetailsSol;
    private JCheckBox cbx_detailsSol;
    private JCheckBox cbx_payeSol;
    private JTextField tfld_LibelleTroncon;
    private JPanel pnl_ChampLieu;
    private JCheckBox cbx_LieuMEP;
    private JCheckBox cbx_LieuSAB;
    private JCheckBox cbx_LieuReco;
    private JPanel pnl_Google;
    private JLabel lbl_LoginGoogle;
    private JTextField tfld_LoginGoogle;
    private JCheckBox cbox_GoogleAuto;
    private JLabel lbl_Google_Contenu_Calendars;
    private String[] agendasDispos;
    private JLabel lbl_Rotations;
    private JLabel lbl_Troncons;
    private JLabel lbl_ActSol;
    private JLabel lbl_Conges;
    private JLabel lbl_Repos;
    private JLabel lbl_Absences;
    private JLabel lbl_Dispersions;
    private JTextField tfld_CalendarRotations;
    private JTextField tfld_CalendarTroncons;
    private JTextField tfld_CalendarActSol;
    private JTextField tfld_CalendarConges;
    private JTextField tfld_CalendarRepos;
    private JTextField tfld_CalendarAbsences;
    private JTextField tfld_CalendarDispersions;
    private EventColorPicker btnColor_Rotations;
    private EventColorPicker btnColor_Troncons;
    private EventColorPicker btnColor_ActSol;
    private EventColorPicker btnColor_Conges;
    private EventColorPicker btnColor_Repos;
    private EventColorPicker btnColor_Absences;
    private EventColorPicker btnColor_Dispersions;
    private JPanel pnl_OptionsPDF;
    private JPanel pnl_OptTroncon;
    private JCheckBox cbx_TempsVol;
    private JCheckBox cbx_NumVol;
    private JCheckBox cbx_TempsEscale;
    private JCheckBox cbx_Presta;
    private JPanel pnl_OptSv;
    private JCheckBox cbx_TypeAvion;
    private JCheckBox cbx_DureeDecoucher;
    private JCheckBox cbx_Hotel;
    private JCheckBox cbx_Decalage;
    private JPanel pnl_OptRotation;
    private JCheckBox cbx_TempsVolSv;
    private JCheckBox cbx_TempsAbsence;
    private JCheckBox cbx_Rpc;
    private JCheckBox cbx_Reeng;
    private JPanel pnl_PDF_Middle_Couleurs;
    private JLabel lbl_CrewColor;
    private JLabel lbl_PastelColor;
    private JLabel lbl_BWColor;
    private JRadioButton rbtn_CrewColor;
    private JRadioButton rbtn_PastelColor;
    private JRadioButton rbtn_BWColor;
    private JPanel pnl_PDF_Middle_Polices;
    private JLabel lbl_Tahoma;
    private JLabel lbl_Serif;
    private JLabel lbl_Monospaced;
    private JRadioButton rbtn_Tahoma;
    private JRadioButton rbtn_Serif;
    private JRadioButton rbtn_Monospaced;
    private JPanel pnl_PDF_Bottom;
    private JCheckBox cbx_MoisComplet;
    private JPanel pnl_Notices;
    private JCheckBox cbx_NoticesAF_Auto;
    private JCheckBox cbx_NoticesAF_Total;
    private JTextField tfld_NoticesAF_RepSauvegarde;
    private JLabel lbl_NoticesAF_FormatNom;
    private JTextField tfld_LibelleNoticesAF;
    private JLabel lbl_PdfAF;
    private JLabel lbl_LoginCE;
    private JTextField tfld_LoginCE;
    private JLabel lbl_PasswordCE;
    private JPasswordField pwdf_PasswordCE;
    private JCheckBox cbx_NoticesCE_Auto;
    private JCheckBox cbx_NoticesCE_Total;
    private JTextField tfld_NoticesCE_RepSauvegarde;
    private JLabel lbl_NoticesCE_FormatNom;
    private JTextField tfld_LibelleNoticesCE;
    private JLabel lbl_PdfCE;
    private JPanel pnl_Auto;
    private JPanel pnl_Auto_Contenu;
    private JCheckBox cbx_Blocs_Auto;
    private JCheckBox cbx_Save_Auto;
    private JLabel lbl_Auto_RepSauvegarde;
    private JTextField tfld_Auto_RepSauvegarde;
    private Component horizontalStrut_Auto_Right;
    private Component horizontalStrut_Auto_Left;
    private Component verticalStrut_Auto_Contenu_1;
    private Component verticalStrut_Auto_Contenu_2;
    private Component verticalGlue_Auto_Contenu_Bottom;
    private JPanel pnl_Reset;
    private JLabel lbl_Reset;
    private JButton btn_Reset;
    private JComboBox<String> cobx_TimeRef;
    private JButton btn_Valider;
    private JButton btn_Annuler;
    private JPanel panel_Sortie;
    private JPanel panel_Boutons;
    private Component verticalStrut_Sortie_Bottom;
    private Component verticalStrut_Sortie_Top;
    private Component verticalStrut_North;
    private Component horizontalStrut_West;
    private Component horizontalStrut_East;
    private Component horizontalStrut_Sortie_Boutons_2;
    private Component horizontalStrut_Sortie_Boutons_1;
    private Component horizontalGlue_Sortie_Boutons_Left;
    private Component horizontalGlue_Sortie_Boutons_Right;
    private JPanel pnl_PositionRotation;
    private Component verticalStrut_PositionRotation_1;
    private Component verticalStrut_PositionRotation_2;
    private Component verticalStrut_PositionRotation_3;
    private JPanel pnl_OptionsAvancees_Left;
    private JPanel pnl_OptionsAvancees_Right;
    private Component verticalGlue_OptionsAvancees_Left_Botom;
    private Component horizontalStrut_OptionsAvancees;
    private Component verticalGlue_OptionsAvancees_Right_Bottom;
    private Component verticalStrut_OptionsAvancees_Right_1;
    private Component verticalStrut_OptionsAvancees_Right_2;
    private Component verticalStrut_OptionsAvancees_Left_1;
    private JPanel pnl_Google_Contenu_Login;
    private JPanel panel_Google_Contenu_Calendars;
    private Component horizontalStrut_Google_Login_1;
    private Component horizontalStrut_Google_Contenu_Login_2;
    private JPanel pnl_Google_Contenu;
    private Component horizontalStrut_Google_Left;
    private Component horizontalStrut_Google_Right;
    private JPanel pnl_PDF_Top;
    private JPanel pnl_PDF_Middle;
    private JPanel panel_NoticesAF_FormatNom;
    private JPanel panel_NoticesCE_FormatNom;
    private JPanel panel_NoticesCE_Password;
    private JPanel panel_NoticesCE_Login;
    private JPanel pnl_NoticesAF;
    private Component verticalStrut_Reset_1;
    private Component verticalStrut_Reset_2;
    private JPanel pnl_DetailsTroncons;
    private JPanel pnl_LibelleTroncons;
    private JPanel pnl_OptionsBasiques_Left;
    private Component verticalGlue_OptionsBasiques_Left_Bottom;
    private JPanel pnl_OptionsBasiques_Right;
    private Component verticalGlue_OptionsBasiques_Right_Bottom;
    private Component verticalStrut_OptionsBasiques_Left_Top;
    private Component verticalStrut_OptionsBasiques_Right_Top;
    private Component verticalStrut_OptionsAvancees_Left_Top;
    private Component verticalStrut_OptionsAvancees_Right_Top;
    private Component verticalStrut_PDF_Top;
    private Component verticalGlue_PDF_Bottom;
    private Component horizontalStrut_OptionsBasiques;
    private Component verticalStrut_Google_2;
    private Component verticalStrut_Google_1;
    private Component verticalStrut_Google_3;
    private JPanel panel_NoticesCE;
    private Component verticalStrut_NoticesAF_2;
    private Component verticalStrut_NoticesCE_2;
    private Component verticalStrut_NoticesCE_3;
    private JPanel pnl_Notices_Left;
    private Component verticalStrut_Notices_Left_Top;
    private Component verticalGlue_Notices_Left_Bottom;
    private JPanel pnl_Notices_Right;
    private Component verticalStrut_Notices_Right_Top;
    private Component verticalGlue_Notices_Right_Bottom;
    private JLabel lbl_NoticesAF_DossierSauvegarde;
    private Component verticalStrut_NoticesAF_1;
    private JLabel lbl_NoticesCE_DossierSauvegarde;
    private Component verticalStrut_NoticesCE_1;
    private Component verticalStrut_NoticesCE_4;
    
    public Dlg_ExportOptions(final Frame owner) {
        super(owner);
        this.isGoogleParamUpdated = false;
        this.isFirstTextfieldUpdated = false;
        this.optionsExportChanged = false;
        this.jContentPane = null;
        this.tabb_ExportOptions = null;
        this.pnl_OptionsBasiques = null;
        this.pnl_ListExport = null;
        this.cbx_Rot = null;
        this.cbx_Vol = null;
        this.cbx_ActSol = null;
        this.cbx_Repos = null;
        this.cbx_Conges = null;
        this.cbx_Absences = null;
        this.cbx_Dispersions = null;
        this.cobx_PositionRot = null;
        this.cobx_PositionSol = null;
        this.tabb_Categorie = null;
        this.pnl_CategorieRot = null;
        this.tfld_CategRot = null;
        this.pnl_CategorieVol = null;
        this.tfld_CategVol = null;
        this.pnl_CategorieActSol = null;
        this.tfld_CategActSol = null;
        this.pnl_CategorieConges = null;
        this.tfld_CategConges = null;
        this.pnl_CategorieRepos = null;
        this.tfld_CategRepos = null;
        this.pnl_CategorieAbsences = null;
        this.tfld_CategAbsences = null;
        this.pnl_CategorieDispersions = null;
        this.tfld_CategDispersions = null;
        this.tabb_PersoAgenda = null;
        this.pnl_PersoRot = null;
        this.tfld_PersoRot = null;
        this.pnl_PersoVol = null;
        this.tfld_PersoVol = null;
        this.pnl_PersoActSol = null;
        this.tfld_PersoActSol = null;
        this.pnl_PersoConges = null;
        this.tfld_PersoConges = null;
        this.pnl_PersoRepos = null;
        this.tfld_PersoRepos = null;
        this.pnl_PersoAbsences = null;
        this.tfld_PersoAbsences = null;
        this.pnl_PersoDispersions = null;
        this.tfld_PersoDispersions = null;
        this.pnl_OptionsAvancees = null;
        this.cbx_geneTroncon = null;
        this.cbx_peqTroncon = null;
        this.cbx_recoTroncon = null;
        this.cbx_indemTroncon = null;
        this.cbx_prestaTroncon = null;
        this.cbx_hotelTroncon = null;
        this.cbx_payeTroncon = null;
        this.pnl_DetailsRot = null;
        this.cbx_geneRot = null;
        this.cbx_peqRot = null;
        this.cbx_recoRot = null;
        this.cbx_payeRot = null;
        this.pnl_DetailsSol = null;
        this.cbx_detailsSol = null;
        this.cbx_payeSol = null;
        this.tfld_LibelleTroncon = null;
        this.pnl_ChampLieu = null;
        this.cbx_LieuMEP = null;
        this.cbx_LieuSAB = null;
        this.cbx_LieuReco = null;
        this.pnl_Google = null;
        this.lbl_LoginGoogle = null;
        this.tfld_LoginGoogle = null;
        this.cbox_GoogleAuto = null;
        this.lbl_Google_Contenu_Calendars = null;
        this.agendasDispos = null;
        this.lbl_Rotations = null;
        this.lbl_Troncons = null;
        this.lbl_ActSol = null;
        this.lbl_Conges = null;
        this.lbl_Repos = null;
        this.lbl_Absences = null;
        this.lbl_Dispersions = null;
        this.tfld_CalendarRotations = null;
        this.tfld_CalendarTroncons = null;
        this.tfld_CalendarActSol = null;
        this.tfld_CalendarConges = null;
        this.tfld_CalendarRepos = null;
        this.tfld_CalendarAbsences = null;
        this.tfld_CalendarDispersions = null;
        this.btnColor_Rotations = null;
        this.btnColor_Troncons = null;
        this.btnColor_ActSol = null;
        this.btnColor_Conges = null;
        this.btnColor_Repos = null;
        this.btnColor_Absences = null;
        this.btnColor_Dispersions = null;
        this.pnl_OptionsPDF = null;
        this.pnl_OptTroncon = null;
        this.cbx_TempsVol = null;
        this.cbx_NumVol = null;
        this.cbx_TempsEscale = null;
        this.cbx_Presta = null;
        this.pnl_OptSv = null;
        this.cbx_TypeAvion = null;
        this.cbx_DureeDecoucher = null;
        this.cbx_Hotel = null;
        this.cbx_Decalage = null;
        this.pnl_OptRotation = null;
        this.cbx_TempsVolSv = null;
        this.cbx_TempsAbsence = null;
        this.cbx_Rpc = null;
        this.cbx_Reeng = null;
        this.pnl_PDF_Middle_Couleurs = null;
        this.lbl_CrewColor = null;
        this.lbl_PastelColor = null;
        this.lbl_BWColor = null;
        this.rbtn_CrewColor = null;
        this.rbtn_PastelColor = null;
        this.rbtn_BWColor = null;
        this.pnl_PDF_Middle_Polices = null;
        this.lbl_Tahoma = null;
        this.lbl_Serif = null;
        this.lbl_Monospaced = null;
        this.rbtn_Tahoma = null;
        this.rbtn_Serif = null;
        this.rbtn_Monospaced = null;
        this.pnl_Notices = null;
        this.cbx_NoticesAF_Auto = null;
        this.cbx_NoticesAF_Total = null;
        this.tfld_NoticesAF_RepSauvegarde = null;
        this.lbl_NoticesAF_FormatNom = null;
        this.tfld_LibelleNoticesAF = null;
        this.lbl_PdfAF = null;
        this.lbl_LoginCE = null;
        this.tfld_LoginCE = null;
        this.lbl_PasswordCE = null;
        this.pwdf_PasswordCE = null;
        this.cbx_NoticesCE_Auto = null;
        this.cbx_NoticesCE_Total = null;
        this.tfld_NoticesCE_RepSauvegarde = null;
        this.lbl_NoticesCE_FormatNom = null;
        this.tfld_LibelleNoticesCE = null;
        this.lbl_PdfCE = null;
        this.pnl_Auto = null;
        this.pnl_Auto_Contenu = null;
        this.cbx_Blocs_Auto = null;
        this.cbx_Save_Auto = null;
        this.lbl_Auto_RepSauvegarde = null;
        this.tfld_Auto_RepSauvegarde = null;
        this.pnl_Reset = null;
        this.lbl_Reset = null;
        this.btn_Reset = null;
        this.cobx_TimeRef = null;
        this.btn_Valider = null;
        this.btn_Annuler = null;
        this.expRotBak = ChopeCrew.options.expRot;
        this.expVolBak = ChopeCrew.options.expVol;
        this.expActSolBak = ChopeCrew.options.expActSol;
        this.expCongesBak = ChopeCrew.options.expConges;
        this.expReposBak = ChopeCrew.options.expRepos;
        this.expAbsencesBak = ChopeCrew.options.expAbsences;
        this.expDispersionsBak = ChopeCrew.options.expDispersions;
        this.loginGoogleBak = ChopeCrew.options.loginGoogle;
        this.calendarsEntetesBak = ChopeCrew.options.calendarsRotations;
        this.calendarsTronconsBak = ChopeCrew.options.calendarsTroncons;
        this.calendarsActSolBak = ChopeCrew.options.calendarsActSol;
        this.calendarsCongesBak = ChopeCrew.options.calendarsConges;
        this.calendarsReposBak = ChopeCrew.options.calendarsRepos;
        this.calendarsAbsencesBak = ChopeCrew.options.calendarsAbsences;
        this.calendarsDispersionsBak = ChopeCrew.options.calendarsDispersions;
        this.colorEntetesBak = ChopeCrew.options.colorRotations;
        this.colorTronconsBak = ChopeCrew.options.colorTroncons;
        this.colorActSolBak = ChopeCrew.options.colorActSol;
        this.colorCongesBak = ChopeCrew.options.colorConges;
        this.colorReposBak = ChopeCrew.options.colorRepos;
        this.colorAbsencesBak = ChopeCrew.options.colorAbsences;
        this.colorDispersionBak = ChopeCrew.options.colorDispersions;
        this.initialize();
        this.loadDialogWithOptions();
    }
    
    private void initialize() {
        this.setModal(true);
        this.setSize(720, 580);
        this.setResizable(false);
        this.setTitle("Configuration");
        this.setContentPane(this.getJContentPane());
    }
    
    private void loadDialogWithOptions() {
        this.cbx_Rot.setSelected(ChopeCrew.options.expRot);
        this.cbx_Vol.setSelected(ChopeCrew.options.expVol);
        this.cbx_ActSol.setSelected(ChopeCrew.options.expActSol);
        this.cbx_Conges.setSelected(ChopeCrew.options.expConges);
        this.cbx_Repos.setSelected(ChopeCrew.options.expRepos);
        this.cbx_Absences.setSelected(ChopeCrew.options.expAbsences);
        this.cbx_Dispersions.setSelected(ChopeCrew.options.expDispersions);
        this.cobx_PositionRot.setSelectedIndex(ChopeCrew.options.idxPositionRot);
        this.cobx_PositionSol.setSelectedIndex(ChopeCrew.options.idxPositionSol);
        this.tfld_CategRot.setText(ChopeCrew.options.categRot);
        this.tfld_CategVol.setText(ChopeCrew.options.categVol);
        this.tfld_CategActSol.setText(ChopeCrew.options.categActSol);
        this.tfld_CategConges.setText(ChopeCrew.options.categConges);
        this.tfld_CategRepos.setText(ChopeCrew.options.categRepos);
        this.tfld_CategAbsences.setText(ChopeCrew.options.categAbsences);
        this.tfld_CategDispersions.setText(ChopeCrew.options.categDispersions);
        this.tfld_PersoRot.setText(ChopeCrew.options.persoRot);
        this.tfld_PersoVol.setText(ChopeCrew.options.persoVol);
        this.tfld_PersoActSol.setText(ChopeCrew.options.persoActSol);
        this.tfld_PersoConges.setText(ChopeCrew.options.persoConges);
        this.tfld_PersoRepos.setText(ChopeCrew.options.persoRepos);
        this.tfld_PersoAbsences.setText(ChopeCrew.options.persoAbsences);
        this.tfld_PersoDispersions.setText(ChopeCrew.options.persoDispersions);
        this.cbx_geneTroncon.setSelected(ChopeCrew.options.geneTroncon);
        this.cbx_peqTroncon.setSelected(ChopeCrew.options.peqTroncon);
        this.cbx_recoTroncon.setSelected(ChopeCrew.options.recoTroncon);
        this.cbx_indemTroncon.setSelected(ChopeCrew.options.indemTroncon);
        this.cbx_prestaTroncon.setSelected(ChopeCrew.options.prestaTroncon);
        this.cbx_hotelTroncon.setSelected(ChopeCrew.options.hotelTroncon);
        this.cbx_payeTroncon.setSelected(ChopeCrew.options.payeTroncon);
        this.cbx_geneRot.setSelected(ChopeCrew.options.geneRot);
        this.cbx_peqRot.setSelected(ChopeCrew.options.peqRot);
        this.cbx_recoRot.setSelected(ChopeCrew.options.recoRot);
        this.cbx_payeRot.setSelected(ChopeCrew.options.payeRot);
        this.cbx_detailsSol.setSelected(ChopeCrew.options.detailsSol);
        this.cbx_payeSol.setSelected(ChopeCrew.options.payeSol);
        this.tfld_LibelleTroncon.setText(ChopeCrew.options.libelleTroncon);
        this.cbx_LieuMEP.setSelected(ChopeCrew.options.lieuMEP);
        this.cbx_LieuSAB.setSelected(ChopeCrew.options.lieuSAB);
        this.cbx_LieuReco.setSelected(ChopeCrew.options.lieuReco);
        this.tfld_LoginGoogle.setText(ChopeCrew.options.loginGoogle);
        this.cbox_GoogleAuto.setSelected(ChopeCrew.options.isGoogleAuto);
        this.tfld_CalendarRotations.setText(ChopeCrew.options.calendarsRotations);
        this.tfld_CalendarTroncons.setText(ChopeCrew.options.calendarsTroncons);
        this.tfld_CalendarActSol.setText(ChopeCrew.options.calendarsActSol);
        this.tfld_CalendarConges.setText(ChopeCrew.options.calendarsConges);
        this.tfld_CalendarRepos.setText(ChopeCrew.options.calendarsRepos);
        this.tfld_CalendarAbsences.setText(ChopeCrew.options.calendarsAbsences);
        this.tfld_CalendarDispersions.setText(ChopeCrew.options.calendarsDispersions);
        this.btnColor_Rotations.setColorId(ChopeCrew.options.colorRotations);
        this.btnColor_Troncons.setColorId(ChopeCrew.options.colorTroncons);
        this.btnColor_ActSol.setColorId(ChopeCrew.options.colorActSol);
        this.btnColor_Conges.setColorId(ChopeCrew.options.colorConges);
        this.btnColor_Repos.setColorId(ChopeCrew.options.colorRepos);
        this.btnColor_Absences.setColorId(ChopeCrew.options.colorAbsences);
        this.btnColor_Dispersions.setColorId(ChopeCrew.options.colorDispersions);
        this.cbx_TempsVol.setSelected(ChopeCrew.options.webTempsVol);
        this.cbx_NumVol.setSelected(ChopeCrew.options.webNumVol);
        this.cbx_TempsEscale.setSelected(ChopeCrew.options.webTempsEscale);
        this.cbx_Presta.setSelected(ChopeCrew.options.webPresta);
        this.cbx_TypeAvion.setSelected(ChopeCrew.options.webTypeAvion);
        this.cbx_DureeDecoucher.setSelected(ChopeCrew.options.webDureeDecoucher);
        this.cbx_Hotel.setSelected(ChopeCrew.options.webHotel);
        this.cbx_Decalage.setSelected(ChopeCrew.options.webDecalage);
        this.cbx_TempsVolSv.setSelected(ChopeCrew.options.webTempsVolSv);
        this.cbx_TempsAbsence.setSelected(ChopeCrew.options.webTempsAbsence);
        this.cbx_Rpc.setSelected(ChopeCrew.options.webRpc);
        this.cbx_Reeng.setSelected(ChopeCrew.options.webReeng);
        if (ChopeCrew.options.webColor == 0) {
            this.rbtn_CrewColor.setSelected(true);
        }
        else if (ChopeCrew.options.webColor == 1) {
            this.rbtn_PastelColor.setSelected(true);
        }
        else if (ChopeCrew.options.webColor == 2) {
            this.rbtn_BWColor.setSelected(true);
        }
        else {
            this.rbtn_CrewColor.setSelected(true);
        }
        if (ChopeCrew.options.webFont == 0) {
            this.rbtn_Tahoma.setSelected(true);
        }
        else if (ChopeCrew.options.webFont == 1) {
            this.rbtn_Serif.setSelected(true);
        }
        else if (ChopeCrew.options.webFont == 2) {
            this.rbtn_Monospaced.setSelected(true);
        }
        else {
            this.rbtn_Tahoma.setSelected(true);
        }
        this.cbx_MoisComplet.setSelected(ChopeCrew.options.webMoisComplet);
        this.cbx_NoticesAF_Auto.setSelected(ChopeCrew.options.isNoticesAFAuto);
        this.cbx_NoticesAF_Total.setSelected(ChopeCrew.options.isNoticesAFTotal);
        if (ChopeCrew.options.isNoticesAFAuto) {
            this.tfld_NoticesAF_RepSauvegarde.setText(ChopeCrew.options.repNoticesAF);
        }
        else {
            this.tfld_NoticesAF_RepSauvegarde.setEnabled(false);
        }
        this.tfld_LibelleNoticesAF.setText(ChopeCrew.options.libelleNoticesAF);
        this.tfld_LoginCE.setText(ChopeCrew.options.ceLogin);
        this.pwdf_PasswordCE.setText(ChopeCrew.options.cePassword);
        this.cbx_NoticesCE_Auto.setSelected(ChopeCrew.options.isNoticesCEAuto);
        this.cbx_NoticesCE_Total.setSelected(ChopeCrew.options.isNoticesCETotal);
        if (ChopeCrew.options.isNoticesCEAuto) {
            this.tfld_NoticesCE_RepSauvegarde.setText(ChopeCrew.options.repNoticesCE);
        }
        else {
            this.tfld_NoticesCE_RepSauvegarde.setEnabled(false);
        }
        this.tfld_LibelleNoticesCE.setText(ChopeCrew.options.libelleNoticesCE);
        this.cbx_Blocs_Auto.setSelected(ChopeCrew.options.isMajAuto);
        this.cbx_Save_Auto.setSelected(ChopeCrew.options.isSaveAuto);
        if (ChopeCrew.options.isSaveAuto) {
            this.tfld_Auto_RepSauvegarde.setText(ChopeCrew.options.repSaveAuto);
        }
        else {
            this.tfld_Auto_RepSauvegarde.setEnabled(false);
        }
        this.cobx_TimeRef.setSelectedIndex(ChopeCrew.options.idxTimeRef);
    }
    
    private void saveOptionsFromDialog() {
        ChopeCrew.options.expRot = this.cbx_Rot.isSelected();
        ChopeCrew.options.expVol = this.cbx_Vol.isSelected();
        ChopeCrew.options.expActSol = this.cbx_ActSol.isSelected();
        ChopeCrew.options.expRepos = this.cbx_Repos.isSelected();
        ChopeCrew.options.expConges = this.cbx_Conges.isSelected();
        ChopeCrew.options.expAbsences = this.cbx_Absences.isSelected();
        ChopeCrew.options.expDispersions = this.cbx_Dispersions.isSelected();
        ChopeCrew.options.idxPositionRot = this.cobx_PositionRot.getSelectedIndex();
        ChopeCrew.options.idxPositionSol = this.cobx_PositionSol.getSelectedIndex();
        ChopeCrew.options.categRot = this.tfld_CategRot.getText();
        ChopeCrew.options.categVol = this.tfld_CategVol.getText();
        ChopeCrew.options.categActSol = this.tfld_CategActSol.getText();
        ChopeCrew.options.categConges = this.tfld_CategConges.getText();
        ChopeCrew.options.categRepos = this.tfld_CategRepos.getText();
        ChopeCrew.options.categAbsences = this.tfld_CategAbsences.getText();
        ChopeCrew.options.categDispersions = this.tfld_CategDispersions.getText();
        ChopeCrew.options.persoRot = this.tfld_PersoRot.getText();
        ChopeCrew.options.persoVol = this.tfld_PersoVol.getText();
        ChopeCrew.options.persoActSol = this.tfld_PersoActSol.getText();
        ChopeCrew.options.persoConges = this.tfld_PersoConges.getText();
        ChopeCrew.options.persoRepos = this.tfld_PersoRepos.getText();
        ChopeCrew.options.persoAbsences = this.tfld_PersoAbsences.getText();
        ChopeCrew.options.persoDispersions = this.tfld_PersoDispersions.getText();
        ChopeCrew.options.geneTroncon = this.cbx_geneTroncon.isSelected();
        ChopeCrew.options.peqTroncon = this.cbx_peqTroncon.isSelected();
        ChopeCrew.options.recoTroncon = this.cbx_recoTroncon.isSelected();
        ChopeCrew.options.indemTroncon = this.cbx_indemTroncon.isSelected();
        ChopeCrew.options.prestaTroncon = this.cbx_prestaTroncon.isSelected();
        ChopeCrew.options.hotelTroncon = this.cbx_hotelTroncon.isSelected();
        ChopeCrew.options.payeTroncon = this.cbx_payeTroncon.isSelected();
        ChopeCrew.options.geneRot = this.cbx_geneRot.isSelected();
        ChopeCrew.options.peqRot = this.cbx_peqRot.isSelected();
        ChopeCrew.options.recoRot = this.cbx_recoRot.isSelected();
        ChopeCrew.options.payeRot = this.cbx_payeRot.isSelected();
        ChopeCrew.options.detailsSol = this.cbx_detailsSol.isSelected();
        ChopeCrew.options.payeSol = this.cbx_payeSol.isSelected();
        ChopeCrew.options.libelleTroncon = this.tfld_LibelleTroncon.getText();
        if (ChopeCrew.options.libelleTroncon.equals("")) {
            ChopeCrew.options.libelleTroncon = "%n | %d - %a | %t";
        }
        ChopeCrew.options.lieuMEP = this.cbx_LieuMEP.isSelected();
        ChopeCrew.options.lieuSAB = this.cbx_LieuSAB.isSelected();
        ChopeCrew.options.lieuReco = this.cbx_LieuReco.isSelected();
        ChopeCrew.options.loginGoogle = this.tfld_LoginGoogle.getText().trim();
        if (!ChopeCrew.options.loginGoogle.contains("@")) {
            ChopeCrew.options.loginGoogle = String.valueOf(ChopeCrew.options.loginGoogle) + "@gmail.com";
        }
        ChopeCrew.options.isGoogleAuto = this.cbox_GoogleAuto.isSelected();
        ChopeCrew.options.calendarsRotations = this.tfld_CalendarRotations.getText();
        ChopeCrew.options.calendarsTroncons = this.tfld_CalendarTroncons.getText();
        ChopeCrew.options.calendarsActSol = this.tfld_CalendarActSol.getText();
        ChopeCrew.options.calendarsConges = this.tfld_CalendarConges.getText();
        ChopeCrew.options.calendarsRepos = this.tfld_CalendarRepos.getText();
        ChopeCrew.options.calendarsAbsences = this.tfld_CalendarAbsences.getText();
        ChopeCrew.options.calendarsDispersions = this.tfld_CalendarDispersions.getText();
        ChopeCrew.options.colorRotations = this.btnColor_Rotations.getColorId();
        ChopeCrew.options.colorTroncons = this.btnColor_Troncons.getColorId();
        ChopeCrew.options.colorActSol = this.btnColor_ActSol.getColorId();
        ChopeCrew.options.colorConges = this.btnColor_Conges.getColorId();
        ChopeCrew.options.colorRepos = this.btnColor_Repos.getColorId();
        ChopeCrew.options.colorAbsences = this.btnColor_Absences.getColorId();
        ChopeCrew.options.colorDispersions = this.btnColor_Dispersions.getColorId();
        ChopeCrew.options.webTempsVol = this.cbx_TempsVol.isSelected();
        ChopeCrew.options.webNumVol = this.cbx_NumVol.isSelected();
        ChopeCrew.options.webTempsEscale = this.cbx_TempsEscale.isSelected();
        ChopeCrew.options.webPresta = this.cbx_Presta.isSelected();
        ChopeCrew.options.webTypeAvion = this.cbx_TypeAvion.isSelected();
        ChopeCrew.options.webDureeDecoucher = this.cbx_DureeDecoucher.isSelected();
        ChopeCrew.options.webHotel = this.cbx_Hotel.isSelected();
        ChopeCrew.options.webDecalage = this.cbx_Decalage.isSelected();
        ChopeCrew.options.webTempsVolSv = this.cbx_TempsVolSv.isSelected();
        ChopeCrew.options.webTempsAbsence = this.cbx_TempsAbsence.isSelected();
        ChopeCrew.options.webRpc = this.cbx_Rpc.isSelected();
        ChopeCrew.options.webReeng = this.cbx_Reeng.isSelected();
        if (this.rbtn_CrewColor.isSelected()) {
            ChopeCrew.options.webColor = 0;
        }
        else if (this.rbtn_PastelColor.isSelected()) {
            ChopeCrew.options.webColor = 1;
        }
        else if (this.rbtn_BWColor.isSelected()) {
            ChopeCrew.options.webColor = 2;
        }
        else {
            ChopeCrew.options.webColor = 0;
        }
        if (this.rbtn_Tahoma.isSelected()) {
            ChopeCrew.options.webFont = 0;
        }
        else if (this.rbtn_Serif.isSelected()) {
            ChopeCrew.options.webFont = 1;
        }
        else if (this.rbtn_Monospaced.isSelected()) {
            ChopeCrew.options.webFont = 2;
        }
        else {
            ChopeCrew.options.webFont = 0;
        }
        ChopeCrew.options.webMoisComplet = this.cbx_MoisComplet.isSelected();
        ChopeCrew.options.isNoticesAFAuto = this.cbx_NoticesAF_Auto.isSelected();
        ChopeCrew.options.isNoticesAFTotal = this.cbx_NoticesAF_Total.isSelected();
        if (ChopeCrew.options.isNoticesAFAuto) {
            ChopeCrew.options.repNoticesAF = this.tfld_NoticesAF_RepSauvegarde.getText().trim();
        }
        ChopeCrew.options.libelleNoticesAF = this.tfld_LibelleNoticesAF.getText();
        if (ChopeCrew.options.libelleNoticesAF.equals("")) {
            ChopeCrew.options.libelleNoticesAF = "%c-%n_AF";
        }
        ChopeCrew.options.ceLogin = this.tfld_LoginCE.getText().trim();
        ChopeCrew.options.cePassword = String.valueOf(this.pwdf_PasswordCE.getPassword());
        ChopeCrew.options.isNoticesCEAuto = this.cbx_NoticesCE_Auto.isSelected();
        ChopeCrew.options.isNoticesCETotal = this.cbx_NoticesCE_Total.isSelected();
        if (ChopeCrew.options.isNoticesCEAuto) {
            ChopeCrew.options.repNoticesCE = this.tfld_NoticesCE_RepSauvegarde.getText().trim();
        }
        ChopeCrew.options.libelleNoticesCE = this.tfld_LibelleNoticesCE.getText();
        if (ChopeCrew.options.libelleNoticesCE.equals("")) {
            ChopeCrew.options.libelleNoticesCE = "%c-%n_CE";
        }
        ChopeCrew.options.isMajAuto = this.cbx_Blocs_Auto.isSelected();
        ChopeCrew.options.isSaveAuto = this.cbx_Save_Auto.isSelected();
        if (ChopeCrew.options.isSaveAuto) {
            ChopeCrew.options.repSaveAuto = this.tfld_Auto_RepSauvegarde.getText().trim();
        }
        ChopeCrew.options.idxTimeRef = this.cobx_TimeRef.getSelectedIndex();
    }
    
    private void resetGoogle() {
        this.agendasDispos = null;
        this.isGoogleParamUpdated = true;
        ChopeCrew.options.loginGoogle = this.tfld_LoginGoogle.getText().trim();
        if (!ChopeCrew.options.loginGoogle.contains("@")) {
            ChopeCrew.options.loginGoogle = String.valueOf(ChopeCrew.options.loginGoogle) + "@gmail.com";
        }
        ChopeCrew.options.calendarsRotations = "";
        ChopeCrew.options.calendarsTroncons = "";
        ChopeCrew.options.calendarsActSol = "";
        ChopeCrew.options.calendarsConges = "";
        ChopeCrew.options.calendarsRepos = "";
        ChopeCrew.options.calendarsAbsences = "";
        ChopeCrew.options.calendarsDispersions = "";
        this.tfld_CalendarRotations.setText(ChopeCrew.options.calendarsRotations);
        this.tfld_CalendarTroncons.setText(ChopeCrew.options.calendarsTroncons);
        this.tfld_CalendarActSol.setText(ChopeCrew.options.calendarsActSol);
        this.tfld_CalendarConges.setText(ChopeCrew.options.calendarsConges);
        this.tfld_CalendarRepos.setText(ChopeCrew.options.calendarsRepos);
        this.tfld_CalendarAbsences.setText(ChopeCrew.options.calendarsAbsences);
        this.tfld_CalendarDispersions.setText(ChopeCrew.options.calendarsDispersions);
    }
    
    private void restoreGoogle() {
        ChopeCrew.options.loginGoogle = this.loginGoogleBak;
        ChopeCrew.options.calendarsRotations = this.calendarsEntetesBak;
        ChopeCrew.options.calendarsTroncons = this.calendarsTronconsBak;
        ChopeCrew.options.calendarsActSol = this.calendarsActSolBak;
        ChopeCrew.options.calendarsConges = this.calendarsCongesBak;
        ChopeCrew.options.calendarsRepos = this.calendarsReposBak;
        ChopeCrew.options.calendarsAbsences = this.calendarsAbsencesBak;
        ChopeCrew.options.calendarsDispersions = this.calendarsDispersionsBak;
        ChopeCrew.options.colorRotations = this.colorEntetesBak;
        ChopeCrew.options.colorTroncons = this.colorTronconsBak;
        ChopeCrew.options.colorActSol = this.colorActSolBak;
        ChopeCrew.options.colorConges = this.colorCongesBak;
        ChopeCrew.options.colorRepos = this.colorReposBak;
        ChopeCrew.options.colorAbsences = this.colorAbsencesBak;
        ChopeCrew.options.colorDispersions = this.colorDispersionBak;
        this.tfld_CalendarRotations.setText(ChopeCrew.options.calendarsRotations);
        this.tfld_CalendarTroncons.setText(ChopeCrew.options.calendarsTroncons);
        this.tfld_CalendarActSol.setText(ChopeCrew.options.calendarsActSol);
        this.tfld_CalendarConges.setText(ChopeCrew.options.calendarsConges);
        this.tfld_CalendarRepos.setText(ChopeCrew.options.calendarsRepos);
        this.tfld_CalendarAbsences.setText(ChopeCrew.options.calendarsAbsences);
        this.tfld_CalendarDispersions.setText(ChopeCrew.options.calendarsDispersions);
        this.btnColor_Rotations.setColorId(ChopeCrew.options.colorRotations);
        this.btnColor_Troncons.setColorId(ChopeCrew.options.colorTroncons);
        this.btnColor_ActSol.setColorId(ChopeCrew.options.colorActSol);
        this.btnColor_Conges.setColorId(ChopeCrew.options.colorConges);
        this.btnColor_Repos.setColorId(ChopeCrew.options.colorRepos);
        this.btnColor_Absences.setColorId(ChopeCrew.options.colorAbsences);
        this.btnColor_Dispersions.setColorId(ChopeCrew.options.colorDispersions);
    }
    
    private int[] convertAgendasNamesToIndices(final String[] agendas) {
        final ArrayList<Integer> alIndices = new ArrayList<Integer>();
        for (final String str : agendas) {
            for (int i = 0; i < this.agendasDispos.length; ++i) {
                if (str.equals(this.agendasDispos[i])) {
                    alIndices.add(new Integer(i));
                }
            }
        }
        final int[] arrResult = new int[alIndices.size()];
        for (int j = 0; j < alIndices.size(); ++j) {
            arrResult[j] = alIndices.get(j);
        }
        return arrResult;
    }
    
    private void actionCbxNoticesCE(final boolean isSelected) {
        if (isSelected) {
            final JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Choix du r\u00e9pertoire de sauvegarde des notices CE");
            fc.setFileSelectionMode(1);
            fc.setCurrentDirectory(new File(this.tfld_NoticesCE_RepSauvegarde.getText().trim()));
            final int returnVal = fc.showSaveDialog(this);
            if (returnVal == 1) {
                this.cbx_NoticesCE_Auto.setSelected(false);
                return;
            }
            final String newRepCE = fc.getSelectedFile().getAbsolutePath();
            this.tfld_NoticesCE_RepSauvegarde.setEnabled(true);
            this.tfld_NoticesCE_RepSauvegarde.setText(newRepCE);
        }
        else {
            this.tfld_NoticesCE_RepSauvegarde.setText("");
            this.tfld_NoticesCE_RepSauvegarde.setEnabled(false);
        }
    }
    
    private void actionCbxNoticesAF(final boolean isSelected) {
        if (isSelected) {
            final JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Choix du r\u00e9pertoire de sauvegarde des notices AF");
            fc.setFileSelectionMode(1);
            fc.setCurrentDirectory(new File(this.tfld_NoticesCE_RepSauvegarde.getText().trim()));
            final int returnVal = fc.showSaveDialog(this);
            if (returnVal == 1) {
                this.cbx_NoticesAF_Auto.setSelected(false);
                return;
            }
            final String newRepAF = fc.getSelectedFile().getAbsolutePath();
            this.tfld_NoticesAF_RepSauvegarde.setEnabled(true);
            this.tfld_NoticesAF_RepSauvegarde.setText(newRepAF);
        }
        else {
            this.tfld_NoticesAF_RepSauvegarde.setText("");
            this.tfld_NoticesAF_RepSauvegarde.setEnabled(false);
        }
    }
    
    private void actionCbxSaveAuto(final boolean isSelected) {
        if (isSelected) {
            final JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Choix du r\u00e9pertoire de sauvegarde des plannings");
            fc.setFileSelectionMode(1);
            fc.setCurrentDirectory(new File(this.tfld_Auto_RepSauvegarde.getText().trim()));
            final int returnVal = fc.showSaveDialog(this);
            if (returnVal == 1) {
                this.cbx_Save_Auto.setSelected(false);
                return;
            }
            final String newRepSaveAuto = fc.getSelectedFile().getAbsolutePath();
            this.tfld_Auto_RepSauvegarde.setEnabled(true);
            this.tfld_Auto_RepSauvegarde.setText(newRepSaveAuto);
        }
        else {
            this.tfld_Auto_RepSauvegarde.setText("");
            this.tfld_Auto_RepSauvegarde.setEnabled(false);
        }
    }
    
    private void actionCbxGoogleAuto() {
        if (!this.tfld_LoginGoogle.getText().equals(this.loginGoogleBak)) {
            this.resetGoogle();
        }
    }
    
    private void actionBtnReset() {
        final Object[] options = { "Oui, je confirme !", "NON !!!" };
        final int result = JOptionPane.showOptionDialog(this.getOwner(), "Etes vous certain de vouloir r\u00e9initialiser ChopeCREW ?", "Attention...", 0, 2, null, options, options[1]);
        if (result == 0) {
            try {
                Preferences.userRoot().node("ChopeCREW").removeNode();
                Utils.deleteDirectory(new File(System.getProperty("user.home"), ".chopeCREW/"));
                System.exit(0);
            }
            catch (BackingStoreException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void actionBtnValider() {
        if (!this.tfld_LoginGoogle.getText().equals(this.loginGoogleBak) && !this.isGoogleParamUpdated) {
            this.resetGoogle();
        }
        this.saveOptionsFromDialog();
        if (ChopeCrew.options.expRot != this.expRotBak || ChopeCrew.options.expVol != this.expVolBak || ChopeCrew.options.expActSol != this.expActSolBak || ChopeCrew.options.expRepos != this.expReposBak || ChopeCrew.options.expConges != this.expCongesBak || ChopeCrew.options.expAbsences != this.expAbsencesBak || ChopeCrew.options.expDispersions != this.expDispersionsBak) {
            this.optionsExportChanged = true;
        }
        this.dispose();
    }
    
    private void actionBtnAnnuler() {
        if (!this.tfld_LoginGoogle.getText().equals(this.loginGoogleBak)) {
            this.restoreGoogle();
        }
        this.dispose();
    }
    
    private void actionTfldCalendar(final JTextField tfld) {
        if (!this.tfld_LoginGoogle.getText().equals(this.loginGoogleBak) && !this.isFirstTextfieldUpdated) {
            this.resetGoogle();
            this.isFirstTextfieldUpdated = true;
        }
        if (this.agendasDispos == null) {
            this.listageAgendasDispos();
        }
        this.actualiseChampAgendas(tfld);
    }
    
    private void actionBtnColor(final Component parent, final EventColorPicker btn) {
        final DlgEventColorList dialog = new DlgEventColorList((Component)this, btn.getColorId());
        btn.setColorId(dialog.colorId);
        btn.updateBackground();
        dialog.dispose();
    }
    
    private ArrayList<String> getListeAgendas() {
        final ArrayList<String> alAgendas = new ArrayList<String>();
        try {
            if (ChopeCrew.json_factory == null) {
                ChopeCrew.json_factory = JacksonFactory.getDefaultInstance();
            }
            final File data_store_dir = new File(System.getProperty("user.home"), ".chopeCREW/" + ChopeCrew.options.loginGoogle);
            ChopeCrew.httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            ChopeCrew.dataStoreFactory = new FileDataStoreFactory(data_store_dir);
            final GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(ChopeCrew.httpTransport, ChopeCrew.json_factory, "1002555116964-fmmh79tuh5sukg5esg2tffc9p6u9u5le.apps.googleusercontent.com", "s-t931CHmC-1AvrWgO3bh9uP", Collections.singleton("https://www.googleapis.com/auth/calendar")).setDataStoreFactory(ChopeCrew.dataStoreFactory).build();
            final Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
            ChopeCrew.client = new Calendar.Builder(ChopeCrew.httpTransport, ChopeCrew.json_factory, credential).setApplicationName("ChopeCREW").build();
            final CalendarList feed = ChopeCrew.client.calendarList().list().execute();
            for (final CalendarListEntry calendar : feed.getItems()) {
                alAgendas.add(calendar.getSummary());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "<html>Probl\u00e8me de connexion au compte Google.<br>V\u00e9rifier identifiant, mot de passe et connexion Internet</html>", "ChopeCREW vous informe", -1);
        }
        return alAgendas;
    }
    
    private void actualiseChampAgendas(final JTextField field) {
        final String[] array = field.getText().split(";");
        for (int i = 0; i < array.length; ++i) {
            array[i] = array[i].trim();
        }
        final int[] selection = this.convertAgendasNamesToIndices(array);
        final String[] result = Dlg_ChooseCalendars.showDialog(this.pnl_Google, field, "Selectionner le(s) agenda(s) destinataires ", "Gestion des agendas", this.agendasDispos, selection, "truc");
        if (result == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        for (int j = 0; j < result.length; ++j) {
            sb.append(result[j]);
            if (j < result.length - 1) {
                sb.append(";");
            }
        }
        field.setText(sb.toString());
    }
    
    private void listageAgendasDispos() {
        final JDialog dialog = new JDialog(this);
        final JPanel p1 = new JPanel(new BorderLayout());
        final JLabel label = new JLabel("<html>Veuillez patienter, recherche des<br>agendas disponibles en cours...</html>");
        label.setFont(new Font("Tahoma", 0, 16));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p1.add(label, "Center");
        dialog.setUndecorated(true);
        dialog.getContentPane().add(p1);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setDefaultCloseOperation(0);
        dialog.setModal(true);
        final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
                Dlg_ExportOptions.access$31(Dlg_ExportOptions.this, Dlg_ExportOptions.this.getListeAgendas().toArray(new String[Dlg_ExportOptions.this.getListeAgendas().size()]));
                return null;
            }
            
            @Override
            protected void done() {
                dialog.dispose();
            }
        };
        worker.execute();
        dialog.setVisible(true);
        try {
            worker.get();
        }
        catch (Exception e1) {
            e1.printStackTrace();
            dialog.dispose();
        }
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BorderLayout(0, 0));
            this.jContentPane.add(this.getVerticalStrut_North(), "North");
            this.jContentPane.add(this.getHorizontalStrut_West(), "West");
            this.jContentPane.add(this.getHorizontalStrut_East(), "East");
            this.jContentPane.add(this.getTabb_ExportOptions());
            this.panel_Sortie = new JPanel();
            this.jContentPane.add(this.panel_Sortie, "South");
            this.panel_Sortie.setLayout(new BoxLayout(this.panel_Sortie, 3));
            this.panel_Sortie.add(this.getVerticalStrut_Sortie_Top());
            this.panel_Boutons = new JPanel();
            this.panel_Sortie.add(this.panel_Boutons);
            this.panel_Boutons.setLayout(new BoxLayout(this.panel_Boutons, 0));
            this.horizontalGlue_Sortie_Boutons_Left = Box.createHorizontalGlue();
            this.panel_Boutons.add(this.horizontalGlue_Sortie_Boutons_Left);
            this.panel_Boutons.add(this.getCobx_TimeRef());
            this.panel_Boutons.add(this.getHorizontalStrut_Sortie_Boutons_1());
            this.panel_Boutons.add(this.getBtn_Valider());
            this.panel_Boutons.add(this.getHorizontalStrut_Sortie_Boutons_2());
            this.panel_Boutons.add(this.getBtn_Annuler());
            this.horizontalGlue_Sortie_Boutons_Right = Box.createHorizontalGlue();
            this.panel_Boutons.add(this.horizontalGlue_Sortie_Boutons_Right);
            this.verticalStrut_Sortie_Bottom = Box.createVerticalStrut(20);
            this.panel_Sortie.add(this.verticalStrut_Sortie_Bottom);
        }
        return this.jContentPane;
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
    
    private JTabbedPane getTabb_ExportOptions() {
        if (this.tabb_ExportOptions == null) {
            (this.tabb_ExportOptions = new JTabbedPane()).setPreferredSize(new Dimension(10, 10));
            this.tabb_ExportOptions.setFont(new Font("Tahoma", 0, 14));
            this.tabb_ExportOptions.addTab("Basiques", null, this.getPnl_OptionsBasiques(), null);
            this.tabb_ExportOptions.addTab("Avanc\u00e9es", null, this.getPnl_OptionsAvancees(), null);
            this.tabb_ExportOptions.addTab("Google", null, this.getPnl_Google(), null);
            this.tabb_ExportOptions.addTab("PDF", null, this.getPnl_OptionsPDF(), null);
            this.tabb_ExportOptions.addTab("Notices", null, this.getPnl_Notices(), null);
            this.tabb_ExportOptions.addTab("Auto.", null, this.getPnl_Auto(), null);
            this.tabb_ExportOptions.addTab("R\u00e9init.", null, this.getPnl_Reset(), null);
        }
        return this.tabb_ExportOptions;
    }
    
    private JPanel getPnl_OptionsBasiques() {
        if (this.pnl_OptionsBasiques == null) {
            (this.pnl_OptionsBasiques = new JPanel()).setLayout(new BoxLayout(this.pnl_OptionsBasiques, 0));
            this.pnl_OptionsBasiques.add(this.getPnl_OptionsBasiques_Left());
            this.pnl_OptionsBasiques.add(this.getHorizontalStrut_OptionsBasiques());
            this.pnl_OptionsBasiques.add(this.getPnl_OptionsBasiques_Right());
        }
        return this.pnl_OptionsBasiques;
    }
    
    private JPanel getPnl_OptionsBasiques_Left() {
        if (this.pnl_OptionsBasiques_Left == null) {
            (this.pnl_OptionsBasiques_Left = new JPanel()).setPreferredSize(new Dimension(400, 10));
            this.pnl_OptionsBasiques_Left.setLayout(new BoxLayout(this.pnl_OptionsBasiques_Left, 3));
            this.pnl_OptionsBasiques_Left.add(this.getVerticalStrut_OptionsBasiques_Left_Top());
            this.pnl_OptionsBasiques_Left.add(this.getPnl_ListExport());
            this.pnl_OptionsBasiques_Left.add(this.getVerticalGlue_OptionsBasiques_Left_Bottom());
        }
        return this.pnl_OptionsBasiques_Left;
    }
    
    private JPanel getPnl_ListExport() {
        if (this.pnl_ListExport == null) {
            (this.pnl_ListExport = new JPanel()).setMinimumSize(new Dimension(0, 0));
            this.pnl_ListExport.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "El\u00e9ments \u00e0 exporter", 4, 2, null, new Color(0, 0, 0)));
            this.pnl_ListExport.setLayout(new GridLayout(0, 1, 0, 0));
            this.pnl_ListExport.add(this.getCbx_Rot());
            this.pnl_ListExport.add(this.getCbx_Vol());
            this.pnl_ListExport.add(this.getCbx_ActSol());
            this.pnl_ListExport.add(this.getCbx_Conges());
            this.pnl_ListExport.add(this.getCbx_Repos());
            this.pnl_ListExport.add(this.getCbx_Absences());
            this.pnl_ListExport.add(this.getCbx_Dispersions());
        }
        return this.pnl_ListExport;
    }
    
    private JCheckBox getCbx_Rot() {
        if (this.cbx_Rot == null) {
            (this.cbx_Rot = new CustomCheckBox()).setMaximumSize(new Dimension(320, 32));
            this.cbx_Rot.setPreferredSize(new Dimension(21, 32));
            this.cbx_Rot.setIconTextGap(12);
            this.cbx_Rot.setFont(new Font("Tahoma", 0, 16));
            this.cbx_Rot.setText("Ent\u00eates de rotations");
            this.cbx_Rot.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Rot;
    }
    
    private JCheckBox getCbx_Vol() {
        if (this.cbx_Vol == null) {
            (this.cbx_Vol = new CustomCheckBox()).setMaximumSize(new Dimension(320, 32));
            this.cbx_Vol.setPreferredSize(new Dimension(21, 32));
            this.cbx_Vol.setIconTextGap(12);
            this.cbx_Vol.setFont(new Font("Tahoma", 0, 16));
            this.cbx_Vol.setText("Tron\u00e7ons");
            this.cbx_Vol.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Vol;
    }
    
    private JCheckBox getCbx_ActSol() {
        if (this.cbx_ActSol == null) {
            (this.cbx_ActSol = new CustomCheckBox()).setMaximumSize(new Dimension(320, 32));
            this.cbx_ActSol.setPreferredSize(new Dimension(21, 32));
            this.cbx_ActSol.setIconTextGap(12);
            this.cbx_ActSol.setFont(new Font("Tahoma", 0, 16));
            this.cbx_ActSol.setText("Activit\u00e9s sol");
            this.cbx_ActSol.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_ActSol;
    }
    
    private JCheckBox getCbx_Conges() {
        if (this.cbx_Conges == null) {
            (this.cbx_Conges = new CustomCheckBox()).setMaximumSize(new Dimension(320, 32));
            this.cbx_Conges.setPreferredSize(new Dimension(21, 32));
            this.cbx_Conges.setIconTextGap(12);
            this.cbx_Conges.setFont(new Font("Tahoma", 0, 16));
            this.cbx_Conges.setText("Cong\u00e9s");
            this.cbx_Conges.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Conges;
    }
    
    private JCheckBox getCbx_Repos() {
        if (this.cbx_Repos == null) {
            (this.cbx_Repos = new CustomCheckBox()).setMaximumSize(new Dimension(320, 32));
            this.cbx_Repos.setPreferredSize(new Dimension(21, 32));
            this.cbx_Repos.setIconTextGap(12);
            this.cbx_Repos.setFont(new Font("Tahoma", 0, 16));
            this.cbx_Repos.setText("Repos");
            this.cbx_Repos.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Repos;
    }
    
    private JCheckBox getCbx_Absences() {
        if (this.cbx_Absences == null) {
            (this.cbx_Absences = new CustomCheckBox()).setMaximumSize(new Dimension(320, 32));
            this.cbx_Absences.setPreferredSize(new Dimension(21, 32));
            this.cbx_Absences.setIconTextGap(12);
            this.cbx_Absences.setFont(new Font("Tahoma", 0, 16));
            this.cbx_Absences.setText("Absences");
            this.cbx_Absences.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Absences;
    }
    
    private JCheckBox getCbx_Dispersions() {
        if (this.cbx_Dispersions == null) {
            (this.cbx_Dispersions = new CustomCheckBox()).setMaximumSize(new Dimension(320, 32));
            this.cbx_Dispersions.setPreferredSize(new Dimension(21, 32));
            this.cbx_Dispersions.setIconTextGap(12);
            this.cbx_Dispersions.setFont(new Font("Tahoma", 0, 16));
            this.cbx_Dispersions.setText("Dispersions");
            this.cbx_Dispersions.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Dispersions;
    }
    
    private JPanel getPnl_OptionsBasiques_Right() {
        if (this.pnl_OptionsBasiques_Right == null) {
            (this.pnl_OptionsBasiques_Right = new JPanel()).setPreferredSize(new Dimension(400, 10));
            this.pnl_OptionsBasiques_Right.setLayout(new BoxLayout(this.pnl_OptionsBasiques_Right, 3));
            this.pnl_OptionsBasiques_Right.add(this.getVerticalStrut_OptionsBasiques_Right_Top());
            this.pnl_OptionsBasiques_Right.add(this.getPnl_PositionRotation());
            this.pnl_OptionsBasiques_Right.add(this.getVerticalGlue_OptionsBasiques_Right_Bottom());
        }
        return this.pnl_OptionsBasiques_Right;
    }
    
    private JPanel getPnl_PositionRotation() {
        if (this.pnl_PositionRotation == null) {
            (this.pnl_PositionRotation = new JPanel()).setLayout(new BoxLayout(this.pnl_PositionRotation, 3));
            this.pnl_PositionRotation.add(this.getCobx_PositionRot());
            this.pnl_PositionRotation.add(this.getVerticalStrut_PositionRotation_1());
            this.pnl_PositionRotation.add(this.getCobx_PositionSol());
            this.pnl_PositionRotation.add(this.getVerticalStrut_PositionRotation_2());
            this.pnl_PositionRotation.add(this.getTabb_Categorie());
            this.pnl_PositionRotation.add(this.getVerticalStrut_PositionRotation_3());
            this.pnl_PositionRotation.add(this.getTabb_PersoAgenda());
        }
        return this.pnl_PositionRotation;
    }
    
    private JComboBox<String> getCobx_PositionRot() {
        if (this.cobx_PositionRot == null) {
            (this.cobx_PositionRot = new JComboBox<String>()).setMaximumSize(new Dimension(32767, 48));
            this.cobx_PositionRot.setMinimumSize(new Dimension(10, 10));
            this.cobx_PositionRot.setPreferredSize(new Dimension(400, 48));
            this.cobx_PositionRot.setBorder(new TitledBorder(null, "Position des ent\u00eates de rotation", 4, 2, null, null));
            this.cobx_PositionRot.setFont(new Font("Tahoma", 0, 16));
            String[] positionRotItems;
            for (int length = (positionRotItems = ChopeCrew.options.positionRotItems).length, i = 0; i < length; ++i) {
                final String positionRotItem = positionRotItems[i];
                this.cobx_PositionRot.addItem(positionRotItem);
            }
        }
        return this.cobx_PositionRot;
    }
    
    private JComboBox<String> getCobx_PositionSol() {
        if (this.cobx_PositionSol == null) {
            (this.cobx_PositionSol = new JComboBox<String>()).setMaximumSize(new Dimension(32767, 48));
            this.cobx_PositionSol.setMinimumSize(new Dimension(10, 10));
            this.cobx_PositionSol.setPreferredSize(new Dimension(400, 48));
            this.cobx_PositionSol.setBorder(new TitledBorder(null, "Position des activit\u00e9s sol", 4, 2, null, null));
            this.cobx_PositionSol.setFont(new Font("Tahoma", 0, 16));
            String[] positionSolItems2;
            for (int length = (positionSolItems2 = ChopeCrew.options.positionSolItems).length, i = 0; i < length; ++i) {
                final String positionSolItems = positionSolItems2[i];
                this.cobx_PositionSol.addItem(positionSolItems);
            }
        }
        return this.cobx_PositionSol;
    }
    
    private JTabbedPane getTabb_Categorie() {
        if (this.tabb_Categorie == null) {
            (this.tabb_Categorie = new JTabbedPane()).setPreferredSize(new Dimension(10, 30));
            this.tabb_Categorie.setBorder(new TitledBorder(null, "Cat\u00e9gorie associ\u00e9e au type d'\u00e9v\u00e8nement", 4, 2, null, null));
            this.tabb_Categorie.setFont(new Font("Tahoma", 0, 14));
            this.tabb_Categorie.addTab("Rot.", null, this.getPnl_CategorieRot(), null);
            this.tabb_Categorie.addTab("Vols", null, this.getPnl_CategorieVol(), null);
            this.tabb_Categorie.addTab("Sol", null, this.getPnl_CategorieActSol(), null);
            this.tabb_Categorie.addTab("Cong.", null, this.getPnl_CategorieConges(), null);
            this.tabb_Categorie.addTab("Rep.", null, this.getPnl_CategorieRepos(), null);
            this.tabb_Categorie.addTab("Abs.", null, this.getPnl_CategorieAbsences(), null);
            this.tabb_Categorie.addTab("Disp.", null, this.getPnl_CategorieDispersions(), null);
        }
        return this.tabb_Categorie;
    }
    
    private JPanel getPnl_CategorieRot() {
        if (this.pnl_CategorieRot == null) {
            (this.pnl_CategorieRot = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_CategorieRot.setPreferredSize(new Dimension(320, 40));
            this.pnl_CategorieRot.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_CategorieRot.add(this.getTfld_CategRot());
        }
        return this.pnl_CategorieRot;
    }
    
    private JTextField getTfld_CategRot() {
        if (this.tfld_CategRot == null) {
            (this.tfld_CategRot = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_CategRot.setPreferredSize(new Dimension(296, 30));
            this.tfld_CategRot.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_CategRot;
    }
    
    private JPanel getPnl_CategorieVol() {
        if (this.pnl_CategorieVol == null) {
            (this.pnl_CategorieVol = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_CategorieVol.setPreferredSize(new Dimension(320, 40));
            this.pnl_CategorieVol.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_CategorieVol.add(this.getTfld_CategVol());
        }
        return this.pnl_CategorieVol;
    }
    
    private JTextField getTfld_CategVol() {
        if (this.tfld_CategVol == null) {
            (this.tfld_CategVol = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_CategVol.setPreferredSize(new Dimension(296, 30));
            this.tfld_CategVol.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_CategVol;
    }
    
    private JPanel getPnl_CategorieActSol() {
        if (this.pnl_CategorieActSol == null) {
            (this.pnl_CategorieActSol = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_CategorieActSol.setPreferredSize(new Dimension(320, 40));
            this.pnl_CategorieActSol.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_CategorieActSol.add(this.getTfld_CategActSol());
        }
        return this.pnl_CategorieActSol;
    }
    
    private JTextField getTfld_CategActSol() {
        if (this.tfld_CategActSol == null) {
            (this.tfld_CategActSol = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_CategActSol.setPreferredSize(new Dimension(296, 30));
            this.tfld_CategActSol.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_CategActSol;
    }
    
    private JPanel getPnl_CategorieConges() {
        if (this.pnl_CategorieConges == null) {
            (this.pnl_CategorieConges = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_CategorieConges.setPreferredSize(new Dimension(320, 40));
            this.pnl_CategorieConges.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_CategorieConges.add(this.getTfld_CategConges());
        }
        return this.pnl_CategorieConges;
    }
    
    private JTextField getTfld_CategConges() {
        if (this.tfld_CategConges == null) {
            (this.tfld_CategConges = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_CategConges.setPreferredSize(new Dimension(296, 30));
            this.tfld_CategConges.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_CategConges;
    }
    
    private JPanel getPnl_CategorieRepos() {
        if (this.pnl_CategorieRepos == null) {
            (this.pnl_CategorieRepos = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_CategorieRepos.setPreferredSize(new Dimension(320, 40));
            this.pnl_CategorieRepos.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_CategorieRepos.add(this.getTfld_CategRepos());
        }
        return this.pnl_CategorieRepos;
    }
    
    private JTextField getTfld_CategRepos() {
        if (this.tfld_CategRepos == null) {
            (this.tfld_CategRepos = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_CategRepos.setPreferredSize(new Dimension(296, 30));
            this.tfld_CategRepos.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_CategRepos;
    }
    
    private JPanel getPnl_CategorieAbsences() {
        if (this.pnl_CategorieAbsences == null) {
            (this.pnl_CategorieAbsences = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_CategorieAbsences.setPreferredSize(new Dimension(320, 40));
            this.pnl_CategorieAbsences.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_CategorieAbsences.add(this.getTfld_CategAbsences());
        }
        return this.pnl_CategorieAbsences;
    }
    
    private JTextField getTfld_CategAbsences() {
        if (this.tfld_CategAbsences == null) {
            (this.tfld_CategAbsences = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_CategAbsences.setPreferredSize(new Dimension(296, 30));
            this.tfld_CategAbsences.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_CategAbsences;
    }
    
    private JPanel getPnl_CategorieDispersions() {
        if (this.pnl_CategorieDispersions == null) {
            (this.pnl_CategorieDispersions = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_CategorieDispersions.setPreferredSize(new Dimension(320, 40));
            this.pnl_CategorieDispersions.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_CategorieDispersions.add(this.getTfld_CategDispersions());
        }
        return this.pnl_CategorieDispersions;
    }
    
    private JTextField getTfld_CategDispersions() {
        if (this.tfld_CategDispersions == null) {
            (this.tfld_CategDispersions = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_CategDispersions.setPreferredSize(new Dimension(296, 30));
            this.tfld_CategDispersions.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_CategDispersions;
    }
    
    private JTabbedPane getTabb_PersoAgenda() {
        if (this.tabb_PersoAgenda == null) {
            (this.tabb_PersoAgenda = new JTabbedPane()).setPreferredSize(new Dimension(10, 30));
            this.tabb_PersoAgenda.setBorder(new TitledBorder(null, "Param\u00e8tres optionnels", 4, 2, null, null));
            this.tabb_PersoAgenda.setFont(new Font("Tahoma", 0, 14));
            this.tabb_PersoAgenda.addTab("Rot.", null, this.getPnl_PersoRot(), null);
            this.tabb_PersoAgenda.addTab("Vols", null, this.getPnl_PersoVol(), null);
            this.tabb_PersoAgenda.addTab("Sol", null, this.getPnl_PersoActSol(), null);
            this.tabb_PersoAgenda.addTab("Cong.", null, this.getPnl_PersoConges(), null);
            this.tabb_PersoAgenda.addTab("Rep.", null, this.getPnl_PersoRepos(), null);
            this.tabb_PersoAgenda.addTab("Abs.", null, this.getPnl_PersoAbsences(), null);
            this.tabb_PersoAgenda.addTab("Disp.", null, this.getPnl_PersoDispersions(), null);
        }
        return this.tabb_PersoAgenda;
    }
    
    private JPanel getPnl_PersoRot() {
        if (this.pnl_PersoRot == null) {
            (this.pnl_PersoRot = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_PersoRot.setPreferredSize(new Dimension(320, 40));
            this.pnl_PersoRot.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_PersoRot.add(this.getTfld_PersoRot());
        }
        return this.pnl_PersoRot;
    }
    
    private JTextField getTfld_PersoRot() {
        if (this.tfld_PersoRot == null) {
            (this.tfld_PersoRot = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_PersoRot.setPreferredSize(new Dimension(296, 30));
            this.tfld_PersoRot.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_PersoRot;
    }
    
    private JPanel getPnl_PersoVol() {
        if (this.pnl_PersoVol == null) {
            (this.pnl_PersoVol = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_PersoVol.setPreferredSize(new Dimension(320, 40));
            this.pnl_PersoVol.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_PersoVol.add(this.getTfld_PersoVol());
        }
        return this.pnl_PersoVol;
    }
    
    private JTextField getTfld_PersoVol() {
        if (this.tfld_PersoVol == null) {
            (this.tfld_PersoVol = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_PersoVol.setPreferredSize(new Dimension(296, 30));
            this.tfld_PersoVol.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_PersoVol;
    }
    
    private JPanel getPnl_PersoActSol() {
        if (this.pnl_PersoActSol == null) {
            (this.pnl_PersoActSol = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_PersoActSol.setPreferredSize(new Dimension(320, 40));
            this.pnl_PersoActSol.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_PersoActSol.add(this.getTfld_PersoActSol());
        }
        return this.pnl_PersoActSol;
    }
    
    private JTextField getTfld_PersoActSol() {
        if (this.tfld_PersoActSol == null) {
            (this.tfld_PersoActSol = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_PersoActSol.setPreferredSize(new Dimension(296, 30));
            this.tfld_PersoActSol.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_PersoActSol;
    }
    
    private JPanel getPnl_PersoConges() {
        if (this.pnl_PersoConges == null) {
            (this.pnl_PersoConges = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_PersoConges.setPreferredSize(new Dimension(320, 40));
            this.pnl_PersoConges.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_PersoConges.add(this.getTfld_PersoConges());
        }
        return this.pnl_PersoConges;
    }
    
    private JTextField getTfld_PersoConges() {
        if (this.tfld_PersoConges == null) {
            (this.tfld_PersoConges = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_PersoConges.setPreferredSize(new Dimension(296, 30));
            this.tfld_PersoConges.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_PersoConges;
    }
    
    private JPanel getPnl_PersoRepos() {
        if (this.pnl_PersoRepos == null) {
            (this.pnl_PersoRepos = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_PersoRepos.setPreferredSize(new Dimension(320, 40));
            this.pnl_PersoRepos.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_PersoRepos.add(this.getTfld_PersoRepos());
        }
        return this.pnl_PersoRepos;
    }
    
    private JTextField getTfld_PersoRepos() {
        if (this.tfld_PersoRepos == null) {
            (this.tfld_PersoRepos = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_PersoRepos.setPreferredSize(new Dimension(296, 30));
            this.tfld_PersoRepos.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_PersoRepos;
    }
    
    private JPanel getPnl_PersoAbsences() {
        if (this.pnl_PersoAbsences == null) {
            (this.pnl_PersoAbsences = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_PersoAbsences.setPreferredSize(new Dimension(320, 40));
            this.pnl_PersoAbsences.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_PersoAbsences.add(this.getTfld_PersoAbsences());
        }
        return this.pnl_PersoAbsences;
    }
    
    private JTextField getTfld_PersoAbsences() {
        if (this.tfld_PersoAbsences == null) {
            (this.tfld_PersoAbsences = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_PersoAbsences.setPreferredSize(new Dimension(296, 30));
            this.tfld_PersoAbsences.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_PersoAbsences;
    }
    
    private JPanel getPnl_PersoDispersions() {
        if (this.pnl_PersoDispersions == null) {
            (this.pnl_PersoDispersions = new JPanel()).setMaximumSize(new Dimension(320, 50));
            this.pnl_PersoDispersions.setPreferredSize(new Dimension(320, 40));
            this.pnl_PersoDispersions.setLayout(new FlowLayout(1, 5, 5));
            this.pnl_PersoDispersions.add(this.getTfld_PersoDispersions());
        }
        return this.pnl_PersoDispersions;
    }
    
    private JTextField getTfld_PersoDispersions() {
        if (this.tfld_PersoDispersions == null) {
            (this.tfld_PersoDispersions = new JTextField()).setMaximumSize(new Dimension(304, 50));
            this.tfld_PersoDispersions.setPreferredSize(new Dimension(296, 30));
            this.tfld_PersoDispersions.setFont(new Font("Tahoma", 0, 16));
        }
        return this.tfld_PersoDispersions;
    }
    
    private JPanel getPnl_OptionsAvancees() {
        if (this.pnl_OptionsAvancees == null) {
            (this.pnl_OptionsAvancees = new JPanel()).setLayout(new BoxLayout(this.pnl_OptionsAvancees, 0));
            this.pnl_OptionsAvancees.add(this.getPnl_OptionsAvancees_Left());
            this.pnl_OptionsAvancees.add(this.getHorizontalStrut_OptionsAvancees());
            this.pnl_OptionsAvancees.add(this.getPnl_OptionsAvancees_Right());
        }
        return this.pnl_OptionsAvancees;
    }
    
    private JPanel getPnl_OptionsAvancees_Left() {
        if (this.pnl_OptionsAvancees_Left == null) {
            (this.pnl_OptionsAvancees_Left = new JPanel()).setMaximumSize(new Dimension(400, 480));
            this.pnl_OptionsAvancees_Left.setMinimumSize(new Dimension(256, 320));
            this.pnl_OptionsAvancees_Left.setPreferredSize(new Dimension(400, 480));
            this.pnl_OptionsAvancees_Left.setLayout(new BoxLayout(this.pnl_OptionsAvancees_Left, 3));
            this.pnl_OptionsAvancees_Left.add(this.getVerticalStrut_OptionsAvancees_Left_Top());
            this.pnl_OptionsAvancees_Left.add(this.getPnl_DetailsTroncons());
            this.pnl_OptionsAvancees_Left.add(this.getVerticalStrut_OptionsAvancees_Left_1());
            this.pnl_OptionsAvancees_Left.add(this.getPnl_LibelleTroncons());
            this.pnl_OptionsAvancees_Left.add(this.getVerticalGlue_OptionsAvancees_Left_Botom());
        }
        return this.pnl_OptionsAvancees_Left;
    }
    
    private JPanel getPnl_DetailsTroncons() {
        if (this.pnl_DetailsTroncons == null) {
            (this.pnl_DetailsTroncons = new JPanel()).setBorder(new TitledBorder(null, "Notes associ\u00e9es aux tron\u00e7ons", 4, 2, null, null));
            this.pnl_DetailsTroncons.setLayout(new GridLayout(0, 1, 0, 0));
            this.pnl_DetailsTroncons.add(this.getCbx_geneTroncon());
            this.pnl_DetailsTroncons.add(this.getCbx_peqTroncon());
            this.pnl_DetailsTroncons.add(this.getCbx_recoTroncon());
            this.pnl_DetailsTroncons.add(this.getCbx_indemTroncon());
            this.pnl_DetailsTroncons.add(this.getCbx_prestaTroncon());
            this.pnl_DetailsTroncons.add(this.getCbx_hotelTroncon());
            this.pnl_DetailsTroncons.add(this.getCbx_payeTroncon());
        }
        return this.pnl_DetailsTroncons;
    }
    
    private JCheckBox getCbx_geneTroncon() {
        if (this.cbx_geneTroncon == null) {
            (this.cbx_geneTroncon = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_geneTroncon.setFont(new Font("Tahoma", 0, 16));
            this.cbx_geneTroncon.setText("Infos g\u00e9n\u00e9rales");
            this.cbx_geneTroncon.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_geneTroncon;
    }
    
    private JCheckBox getCbx_peqTroncon() {
        if (this.cbx_peqTroncon == null) {
            (this.cbx_peqTroncon = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_peqTroncon.setFont(new Font("Tahoma", 0, 16));
            this.cbx_peqTroncon.setText("Equipage du tron\u00e7on");
            this.cbx_peqTroncon.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_peqTroncon;
    }
    
    private JCheckBox getCbx_recoTroncon() {
        if (this.cbx_recoTroncon == null) {
            (this.cbx_recoTroncon = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_recoTroncon.setFont(new Font("Tahoma", 0, 16));
            this.cbx_recoTroncon.setText("Reconnaissances terrains (PNT)");
            this.cbx_recoTroncon.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_recoTroncon;
    }
    
    private JCheckBox getCbx_indemTroncon() {
        if (this.cbx_indemTroncon == null) {
            (this.cbx_indemTroncon = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_indemTroncon.setFont(new Font("Tahoma", 0, 16));
            this.cbx_indemTroncon.setText("Indemnit\u00e9s");
            this.cbx_indemTroncon.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_indemTroncon;
    }
    
    private JCheckBox getCbx_prestaTroncon() {
        if (this.cbx_prestaTroncon == null) {
            (this.cbx_prestaTroncon = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_prestaTroncon.setFont(new Font("Tahoma", 0, 16));
            this.cbx_prestaTroncon.setText("Prestations moyen courrier");
            this.cbx_prestaTroncon.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_prestaTroncon;
    }
    
    private JCheckBox getCbx_hotelTroncon() {
        if (this.cbx_hotelTroncon == null) {
            (this.cbx_hotelTroncon = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_hotelTroncon.setFont(new Font("Tahoma", 0, 16));
            this.cbx_hotelTroncon.setText("Repos & h\u00f4tel");
            this.cbx_hotelTroncon.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_hotelTroncon;
    }
    
    private JCheckBox getCbx_payeTroncon() {
        if (this.cbx_payeTroncon == null) {
            (this.cbx_payeTroncon = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_payeTroncon.setFont(new Font("Tahoma", 0, 16));
            this.cbx_payeTroncon.setText("Heures cr\u00e9dit\u00e9es");
            this.cbx_payeTroncon.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_payeTroncon;
    }
    
    private JPanel getPnl_LibelleTroncons() {
        if (this.pnl_LibelleTroncons == null) {
            (this.pnl_LibelleTroncons = new JPanel()).setFont(new Font("Tahoma", 0, 11));
            this.pnl_LibelleTroncons.setMaximumSize(new Dimension(32767, 64));
            this.pnl_LibelleTroncons.setBorder(new TitledBorder(null, "Format du libell\u00e9 des tron\u00e7ons", 4, 2, null, null));
            this.pnl_LibelleTroncons.setLayout(new GridLayout(0, 1, 0, 0));
            this.pnl_LibelleTroncons.add(this.getTfld_LibelleTroncon());
        }
        return this.pnl_LibelleTroncons;
    }
    
    private JTextField getTfld_LibelleTroncon() {
        if (this.tfld_LibelleTroncon == null) {
            (this.tfld_LibelleTroncon = new JTextField()).setMinimumSize(new Dimension(16, 20));
            this.tfld_LibelleTroncon.setMaximumSize(new Dimension(320, 32));
            this.tfld_LibelleTroncon.setPreferredSize(new Dimension(304, 32));
            this.tfld_LibelleTroncon.setFont(new Font("Tahoma", 0, 16));
            this.tfld_LibelleTroncon.setToolTipText("<html>%n : num\u00e9ro de vol<br>%d : d\u00e9part iata<br>%a : arriv\u00e9e iata<br>%t : type avion<br>%D : d\u00e9part<br>%A : arriv\u00e9e</html>");
        }
        return this.tfld_LibelleTroncon;
    }
    
    private JPanel getPnl_OptionsAvancees_Right() {
        if (this.pnl_OptionsAvancees_Right == null) {
            (this.pnl_OptionsAvancees_Right = new JPanel()).setPreferredSize(new Dimension(400, 480));
            this.pnl_OptionsAvancees_Right.setMinimumSize(new Dimension(256, 320));
            this.pnl_OptionsAvancees_Right.setMaximumSize(new Dimension(400, 480));
            this.pnl_OptionsAvancees_Right.setLayout(new BoxLayout(this.pnl_OptionsAvancees_Right, 3));
            this.pnl_OptionsAvancees_Right.add(this.getVerticalStrut_OptionsAvancees_Right_Top());
            this.pnl_OptionsAvancees_Right.add(this.getPnl_DetailsRot());
            this.pnl_OptionsAvancees_Right.add(this.getVerticalStrut_OptionsAvancees_Right_1());
            this.pnl_OptionsAvancees_Right.add(this.getPnl_DetailsSol());
            this.pnl_OptionsAvancees_Right.add(this.getVerticalStrut_OptionsAvancees_Right_2());
            this.pnl_OptionsAvancees_Right.add(this.getPnl_ChampLieu());
            this.pnl_OptionsAvancees_Right.add(this.getVerticalGlue_OptionsAvancees_Right_Bottom());
        }
        return this.pnl_OptionsAvancees_Right;
    }
    
    private JPanel getPnl_DetailsRot() {
        if (this.pnl_DetailsRot == null) {
            (this.pnl_DetailsRot = new JPanel()).setBorder(new TitledBorder(null, "Notes associ\u00e9es aux ent\u00eates des rotations", 4, 2, null, null));
            this.pnl_DetailsRot.setLayout(new GridLayout(0, 1, 0, 0));
            this.pnl_DetailsRot.add(this.getCbx_geneRot());
            this.pnl_DetailsRot.add(this.getCbx_peqRot());
            this.pnl_DetailsRot.add(this.getCbx_recoRot());
            this.pnl_DetailsRot.add(this.getCbx_payeRot());
        }
        return this.pnl_DetailsRot;
    }
    
    private JCheckBox getCbx_geneRot() {
        if (this.cbx_geneRot == null) {
            (this.cbx_geneRot = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_geneRot.setFont(new Font("Tahoma", 0, 16));
            this.cbx_geneRot.setText("Infos g\u00e9n\u00e9rales");
            this.cbx_geneRot.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_geneRot;
    }
    
    private JCheckBox getCbx_peqRot() {
        if (this.cbx_peqRot == null) {
            (this.cbx_peqRot = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_peqRot.setFont(new Font("Tahoma", 0, 16));
            this.cbx_peqRot.setText("Equipage de la rotation");
            this.cbx_peqRot.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_peqRot;
    }
    
    private JCheckBox getCbx_recoRot() {
        if (this.cbx_recoRot == null) {
            (this.cbx_recoRot = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_recoRot.setFont(new Font("Tahoma", 0, 16));
            this.cbx_recoRot.setText("Reconnaissances terrains (PNT)");
            this.cbx_recoRot.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_recoRot;
    }
    
    private JCheckBox getCbx_payeRot() {
        if (this.cbx_payeRot == null) {
            (this.cbx_payeRot = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_payeRot.setFont(new Font("Tahoma", 0, 16));
            this.cbx_payeRot.setText("Heures cr\u00e9dit\u00e9es");
            this.cbx_payeRot.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_payeRot;
    }
    
    private JPanel getPnl_DetailsSol() {
        if (this.pnl_DetailsSol == null) {
            (this.pnl_DetailsSol = new JPanel()).setBorder(new TitledBorder(null, "Notes associ\u00e9es aux activit\u00e9s sol", 4, 2, null, null));
            this.pnl_DetailsSol.setLayout(new GridLayout(0, 1, 0, 0));
            this.pnl_DetailsSol.add(this.getCbx_detailsSol());
            this.pnl_DetailsSol.add(this.getCbx_payeSol());
        }
        return this.pnl_DetailsSol;
    }
    
    private JCheckBox getCbx_detailsSol() {
        if (this.cbx_detailsSol == null) {
            (this.cbx_detailsSol = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_detailsSol.setFont(new Font("Tahoma", 0, 16));
            this.cbx_detailsSol.setText("D\u00e9tails");
            this.cbx_detailsSol.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_detailsSol;
    }
    
    private JCheckBox getCbx_payeSol() {
        if (this.cbx_payeSol == null) {
            (this.cbx_payeSol = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_payeSol.setFont(new Font("Tahoma", 0, 16));
            this.cbx_payeSol.setText("Heures cr\u00e9dit\u00e9es");
            this.cbx_payeSol.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_payeSol;
    }
    
    private JPanel getPnl_ChampLieu() {
        if (this.pnl_ChampLieu == null) {
            (this.pnl_ChampLieu = new JPanel()).setBorder(new TitledBorder(null, "Inclure dans le \"Lieu\" des tron\u00e7ons", 4, 2, null, null));
            this.pnl_ChampLieu.setLayout(new GridLayout(0, 1, 0, 0));
            this.pnl_ChampLieu.add(this.getCbx_LieuMEP());
            this.pnl_ChampLieu.add(this.getCbx_LieuSAB());
            this.pnl_ChampLieu.add(this.getCbx_LieuReco());
        }
        return this.pnl_ChampLieu;
    }
    
    private JCheckBox getCbx_LieuMEP() {
        if (this.cbx_LieuMEP == null) {
            (this.cbx_LieuMEP = new CustomCheckBox("Mise en place")).setIconTextGap(8);
            this.cbx_LieuMEP.setToolTipText("Les mises en place seront notifi\u00e9es dans le champ \"Lieu\" de l'\u00e9venement");
            this.cbx_LieuMEP.setFont(new Font("Tahoma", 0, 16));
        }
        return this.cbx_LieuMEP;
    }
    
    private JCheckBox getCbx_LieuSAB() {
        if (this.cbx_LieuSAB == null) {
            (this.cbx_LieuSAB = new CustomCheckBox("Situation \u00e0 bord")).setIconTextGap(8);
            this.cbx_LieuSAB.setToolTipText("Les situations \u00e0 bord (Controle etc.) seront notifi\u00e9es dans le champ \"Lieu\" de l'\u00e9venement");
            this.cbx_LieuSAB.setFont(new Font("Tahoma", 0, 16));
        }
        return this.cbx_LieuSAB;
    }
    
    private JCheckBox getCbx_LieuReco() {
        if (this.cbx_LieuReco == null) {
            (this.cbx_LieuReco = new CustomCheckBox("Reconnaissances terrains")).setIconTextGap(8);
            this.cbx_LieuReco.setToolTipText("Les reconnaissances terrain seront notifi\u00e9es dans le champ \"Lieu\" de l'\u00e9venement");
            this.cbx_LieuReco.setFont(new Font("Tahoma", 0, 16));
        }
        return this.cbx_LieuReco;
    }
    
    private JPanel getPnl_Google() {
        if (this.pnl_Google == null) {
            (this.pnl_Google = new JPanel()).setBounds(new Rectangle(4, 4, 236, 211));
            this.pnl_Google.setBorder(new EtchedBorder(1, null, null));
            this.pnl_Google.setLayout(new BoxLayout(this.pnl_Google, 2));
            this.pnl_Google.add(this.getHorizontalStrut_Google_Left());
            this.pnl_Google.add(this.getPnl_Google_Contenu());
            this.pnl_Google.add(this.getHorizontalStrut_Google_Right());
        }
        return this.pnl_Google;
    }
    
    private JLabel getLbl_LoginGoogle() {
        if (this.lbl_LoginGoogle == null) {
            (this.lbl_LoginGoogle = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.lbl_LoginGoogle.setText("Nom d'utilisateur :");
        }
        return this.lbl_LoginGoogle;
    }
    
    private JTextField getTfld_LoginGoogle() {
        if (this.tfld_LoginGoogle == null) {
            (this.tfld_LoginGoogle = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_LoginGoogle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            this.tfld_LoginGoogle.setFont(new Font("Tahoma", 0, 16));
            this.tfld_LoginGoogle.setToolTipText("Adresse e-mail de votre compte Google");
        }
        return this.tfld_LoginGoogle;
    }
    
    private JCheckBox getCbox_GoogleAuto() {
        if (this.cbox_GoogleAuto == null) {
            (this.cbox_GoogleAuto = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbox_GoogleAuto.setToolTipText("Mettre \u00e0 jour automatiquement votre agenda Google \u00e0 l'importation d'un planning");
            this.cbox_GoogleAuto.setText("Auto.");
            this.cbox_GoogleAuto.addMouseListener(new MyMouseAdapter());
            this.cbox_GoogleAuto.addKeyListener(new MyKeyAdapter());
        }
        return this.cbox_GoogleAuto;
    }
    
    private JLabel getLbl_Google_Contenu_Calendars() {
        if (this.lbl_Google_Contenu_Calendars == null) {
            (this.lbl_Google_Contenu_Calendars = new JLabel("<html>Par d\u00e9faut, le planning est export\u00e9 dans l'agenda principal du compte Google.<br>Pour chaque type d'\u00e9v\u00e8nement, vous pouvez \u00e9galement choisir une couleur, ainsi que les agendas secondaires vers lesquels ils seront export\u00e9s.</html>")).setAlignmentX(0.5f);
            this.lbl_Google_Contenu_Calendars.setForeground(Color.BLUE);
            this.lbl_Google_Contenu_Calendars.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_Google_Contenu_Calendars;
    }
    
    private JLabel getLbl_Rotations() {
        if (this.lbl_Rotations == null) {
            (this.lbl_Rotations = new JLabel("Rotations :")).setPreferredSize(new Dimension(128, 32));
            this.lbl_Rotations.setMaximumSize(new Dimension(128, 32));
            this.lbl_Rotations.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_Rotations;
    }
    
    private JLabel getLbl_Troncons() {
        if (this.lbl_Troncons == null) {
            (this.lbl_Troncons = new JLabel("Tron\u00e7ons :")).setPreferredSize(new Dimension(128, 32));
            this.lbl_Troncons.setMaximumSize(new Dimension(128, 32));
            this.lbl_Troncons.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_Troncons;
    }
    
    private JLabel getLbl_ActSol() {
        if (this.lbl_ActSol == null) {
            (this.lbl_ActSol = new JLabel("Activit\u00e9s sol :")).setPreferredSize(new Dimension(128, 32));
            this.lbl_ActSol.setMaximumSize(new Dimension(128, 32));
            this.lbl_ActSol.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_ActSol;
    }
    
    private JLabel getLbl_Conges() {
        if (this.lbl_Conges == null) {
            (this.lbl_Conges = new JLabel("Cong\u00e9s :")).setPreferredSize(new Dimension(128, 32));
            this.lbl_Conges.setMaximumSize(new Dimension(128, 32));
            this.lbl_Conges.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_Conges;
    }
    
    private JLabel getLbl_Repos() {
        if (this.lbl_Repos == null) {
            (this.lbl_Repos = new JLabel("Repos :")).setPreferredSize(new Dimension(128, 32));
            this.lbl_Repos.setMaximumSize(new Dimension(128, 32));
            this.lbl_Repos.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_Repos;
    }
    
    private JLabel getLbl_Absences() {
        if (this.lbl_Absences == null) {
            (this.lbl_Absences = new JLabel("Absences :")).setPreferredSize(new Dimension(128, 32));
            this.lbl_Absences.setMaximumSize(new Dimension(128, 32));
            this.lbl_Absences.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_Absences;
    }
    
    private JLabel getLbl_Dispersions() {
        if (this.lbl_Dispersions == null) {
            (this.lbl_Dispersions = new JLabel("Dispersions :")).setPreferredSize(new Dimension(128, 32));
            this.lbl_Dispersions.setMaximumSize(new Dimension(128, 32));
            this.lbl_Dispersions.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_Dispersions;
    }
    
    private JTextField getTfld_CalendarRotations() {
        if (this.tfld_CalendarRotations == null) {
            (this.tfld_CalendarRotations = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_CalendarRotations.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            this.tfld_CalendarRotations.setFont(new Font("Tahoma", 0, 16));
            this.tfld_CalendarRotations.setColumns(10);
            this.tfld_CalendarRotations.addMouseListener(new MyMouseAdapter());
            this.tfld_CalendarRotations.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_CalendarRotations;
    }
    
    private JTextField getTfld_CalendarTroncons() {
        if (this.tfld_CalendarTroncons == null) {
            (this.tfld_CalendarTroncons = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_CalendarTroncons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            this.tfld_CalendarTroncons.setFont(new Font("Tahoma", 0, 16));
            this.tfld_CalendarTroncons.setColumns(10);
            this.tfld_CalendarTroncons.addMouseListener(new MyMouseAdapter());
            this.tfld_CalendarTroncons.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_CalendarTroncons;
    }
    
    private JTextField getTfld_CalendarActSol() {
        if (this.tfld_CalendarActSol == null) {
            (this.tfld_CalendarActSol = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_CalendarActSol.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            this.tfld_CalendarActSol.setFont(new Font("Tahoma", 0, 16));
            this.tfld_CalendarActSol.setColumns(10);
            this.tfld_CalendarActSol.addMouseListener(new MyMouseAdapter());
            this.tfld_CalendarActSol.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_CalendarActSol;
    }
    
    private JTextField getTfld_CalendarConges() {
        if (this.tfld_CalendarConges == null) {
            (this.tfld_CalendarConges = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_CalendarConges.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            this.tfld_CalendarConges.setFont(new Font("Tahoma", 0, 16));
            this.tfld_CalendarConges.setColumns(10);
            this.tfld_CalendarConges.addMouseListener(new MyMouseAdapter());
            this.tfld_CalendarConges.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_CalendarConges;
    }
    
    private JTextField getTfld_CalendarRepos() {
        if (this.tfld_CalendarRepos == null) {
            (this.tfld_CalendarRepos = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_CalendarRepos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            this.tfld_CalendarRepos.setFont(new Font("Tahoma", 0, 16));
            this.tfld_CalendarRepos.setColumns(10);
            this.tfld_CalendarRepos.addMouseListener(new MyMouseAdapter());
            this.tfld_CalendarRepos.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_CalendarRepos;
    }
    
    private JTextField getTfld_CalendarAbsences() {
        if (this.tfld_CalendarAbsences == null) {
            (this.tfld_CalendarAbsences = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_CalendarAbsences.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            this.tfld_CalendarAbsences.setFont(new Font("Tahoma", 0, 16));
            this.tfld_CalendarAbsences.setColumns(10);
            this.tfld_CalendarAbsences.addMouseListener(new MyMouseAdapter());
            this.tfld_CalendarAbsences.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_CalendarAbsences;
    }
    
    private JTextField getTfld_CalendarDispersions() {
        if (this.tfld_CalendarDispersions == null) {
            (this.tfld_CalendarDispersions = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_CalendarDispersions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
            this.tfld_CalendarDispersions.setFont(new Font("Tahoma", 0, 16));
            this.tfld_CalendarDispersions.setColumns(10);
            this.tfld_CalendarDispersions.addMouseListener(new MyMouseAdapter());
            this.tfld_CalendarDispersions.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_CalendarDispersions;
    }
    
    private EventColorPicker getBtnColor_Rotations() {
        if (this.btnColor_Rotations == null) {
            (this.btnColor_Rotations = new EventColorPicker()).setAlignmentX(0.5f);
            this.btnColor_Rotations.setMinimumSize(new Dimension(32, 32));
            this.btnColor_Rotations.setPreferredSize(new Dimension(32, 32));
            this.btnColor_Rotations.setMaximumSize(new Dimension(40, 40));
            this.btnColor_Rotations.setToolTipText("<html>Cliquer sur ce bouton pour changer la<br> couleur du type d'\u00e9venement concern\u00e9.<br>Si aucune couleur n'est choisie, ce sera<br>celle de l'agenda qui sera associ\u00e9e</html>");
            this.btnColor_Rotations.setFont(new Font("Tahoma", 0, 16));
            this.btnColor_Rotations.addMouseListener(new MyMouseAdapter());
            this.btnColor_Rotations.addKeyListener(new MyKeyAdapter());
        }
        return this.btnColor_Rotations;
    }
    
    private EventColorPicker getBtnColor_Troncons() {
        if (this.btnColor_Troncons == null) {
            (this.btnColor_Troncons = new EventColorPicker()).setAlignmentX(0.5f);
            this.btnColor_Troncons.setMinimumSize(new Dimension(32, 32));
            this.btnColor_Troncons.setPreferredSize(new Dimension(32, 32));
            this.btnColor_Troncons.setMaximumSize(new Dimension(40, 40));
            this.btnColor_Troncons.setToolTipText("<html>Cliquer sur ce bouton pour changer la<br> couleur du type d'\u00e9venement concern\u00e9.<br>Si aucune couleur n'est choisie, ce sera<br>celle de l'agenda qui sera associ\u00e9e</html>");
            this.btnColor_Troncons.setFont(new Font("Tahoma", 0, 16));
            this.btnColor_Troncons.addMouseListener(new MyMouseAdapter());
            this.btnColor_Troncons.addKeyListener(new MyKeyAdapter());
        }
        return this.btnColor_Troncons;
    }
    
    private EventColorPicker getBtnColor_ActSol() {
        if (this.btnColor_ActSol == null) {
            (this.btnColor_ActSol = new EventColorPicker()).setAlignmentX(0.5f);
            this.btnColor_ActSol.setMinimumSize(new Dimension(32, 32));
            this.btnColor_ActSol.setPreferredSize(new Dimension(32, 32));
            this.btnColor_ActSol.setMaximumSize(new Dimension(40, 40));
            this.btnColor_ActSol.setToolTipText("<html>Cliquer sur ce bouton pour changer la<br> couleur du type d'\u00e9venement concern\u00e9.<br>Si aucune couleur n'est choisie, ce sera<br>celle de l'agenda qui sera associ\u00e9e</html>");
            this.btnColor_ActSol.setFont(new Font("Tahoma", 0, 16));
            this.btnColor_ActSol.addMouseListener(new MyMouseAdapter());
            this.btnColor_ActSol.addKeyListener(new MyKeyAdapter());
        }
        return this.btnColor_ActSol;
    }
    
    private EventColorPicker getBtnColor_Conges() {
        if (this.btnColor_Conges == null) {
            (this.btnColor_Conges = new EventColorPicker()).setAlignmentX(0.5f);
            this.btnColor_Conges.setMinimumSize(new Dimension(32, 32));
            this.btnColor_Conges.setPreferredSize(new Dimension(32, 32));
            this.btnColor_Conges.setMaximumSize(new Dimension(40, 40));
            this.btnColor_Conges.setToolTipText("<html>Cliquer sur ce bouton pour changer la<br> couleur du type d'\u00e9venement concern\u00e9.<br>Si aucune couleur n'est choisie, ce sera<br>celle de l'agenda qui sera associ\u00e9e</html>");
            this.btnColor_Conges.setFont(new Font("Tahoma", 0, 16));
            this.btnColor_Conges.addMouseListener(new MyMouseAdapter());
            this.btnColor_Conges.addKeyListener(new MyKeyAdapter());
        }
        return this.btnColor_Conges;
    }
    
    private EventColorPicker getBtnColor_Repos() {
        if (this.btnColor_Repos == null) {
            (this.btnColor_Repos = new EventColorPicker()).setAlignmentX(0.5f);
            this.btnColor_Repos.setMinimumSize(new Dimension(32, 32));
            this.btnColor_Repos.setPreferredSize(new Dimension(32, 32));
            this.btnColor_Repos.setMaximumSize(new Dimension(40, 40));
            this.btnColor_Repos.setToolTipText("<html>Cliquer sur ce bouton pour changer la<br> couleur du type d'\u00e9venement concern\u00e9.<br>Si aucune couleur n'est choisie, ce sera<br>celle de l'agenda qui sera associ\u00e9e</html>");
            this.btnColor_Repos.setFont(new Font("Tahoma", 0, 16));
            this.btnColor_Repos.addMouseListener(new MyMouseAdapter());
            this.btnColor_Repos.addKeyListener(new MyKeyAdapter());
        }
        return this.btnColor_Repos;
    }
    
    private EventColorPicker getBtnColor_Absences() {
        if (this.btnColor_Absences == null) {
            (this.btnColor_Absences = new EventColorPicker()).setAlignmentX(0.5f);
            this.btnColor_Absences.setMinimumSize(new Dimension(32, 32));
            this.btnColor_Absences.setPreferredSize(new Dimension(32, 32));
            this.btnColor_Absences.setMaximumSize(new Dimension(40, 40));
            this.btnColor_Absences.setToolTipText("<html>Cliquer sur ce bouton pour changer la<br> couleur du type d'\u00e9venement concern\u00e9.<br>Si aucune couleur n'est choisie, ce sera<br>celle de l'agenda qui sera associ\u00e9e</html>");
            this.btnColor_Absences.setFont(new Font("Tahoma", 0, 16));
            this.btnColor_Absences.addMouseListener(new MyMouseAdapter());
            this.btnColor_Absences.addKeyListener(new MyKeyAdapter());
        }
        return this.btnColor_Absences;
    }
    
    private EventColorPicker getBtnColor_Dispersions() {
        if (this.btnColor_Dispersions == null) {
            (this.btnColor_Dispersions = new EventColorPicker()).setAlignmentX(0.5f);
            this.btnColor_Dispersions.setMinimumSize(new Dimension(32, 32));
            this.btnColor_Dispersions.setPreferredSize(new Dimension(32, 32));
            this.btnColor_Dispersions.setMaximumSize(new Dimension(40, 40));
            this.btnColor_Dispersions.setToolTipText("<html>Cliquer sur ce bouton pour changer la<br> couleur du type d'\u00e9venement concern\u00e9.<br>Si aucune couleur n'est choisie, ce sera<br>celle de l'agenda qui sera associ\u00e9e</html>");
            this.btnColor_Dispersions.setFont(new Font("Tahoma", 0, 16));
            this.btnColor_Dispersions.addMouseListener(new MyMouseAdapter());
            this.btnColor_Dispersions.addKeyListener(new MyKeyAdapter());
        }
        return this.btnColor_Dispersions;
    }
    
    private JPanel getPnl_OptionsPDF() {
        if (this.pnl_OptionsPDF == null) {
            (this.pnl_OptionsPDF = new JPanel()).setLayout(new BoxLayout(this.pnl_OptionsPDF, 3));
            this.pnl_OptionsPDF.add(this.getVerticalStrut_PDF_Top());
            this.pnl_OptionsPDF.add(this.getPnl_PDF_Top());
            this.pnl_OptionsPDF.add(this.getPnl_PDF_Middle());
            this.pnl_OptionsPDF.add(this.getPnl_PDF_Bottom());
            this.pnl_OptionsPDF.add(this.getVerticalGlue_PDF_Bottom());
        }
        return this.pnl_OptionsPDF;
    }
    
    private JPanel getPnl_PDF_Top() {
        if (this.pnl_PDF_Top == null) {
            (this.pnl_PDF_Top = new JPanel()).setLayout(new GridLayout(0, 3, 0, 0));
            this.pnl_PDF_Top.add(this.getPnl_OptTroncon());
            this.pnl_PDF_Top.add(this.getPnl_OptSv());
            this.pnl_PDF_Top.add(this.getPnl_OptRotation());
        }
        return this.pnl_PDF_Top;
    }
    
    private JPanel getPnl_OptTroncon() {
        if (this.pnl_OptTroncon == null) {
            (this.pnl_OptTroncon = new JPanel()).setBorder(new TitledBorder(null, "Tron\u00e7ons", 4, 2, null, null));
            this.pnl_OptTroncon.setLayout(new GridLayout(4, 1, 0, 0));
            this.pnl_OptTroncon.add(this.getCbx_TempsVol());
            this.pnl_OptTroncon.add(this.getCbx_NumVol());
            this.pnl_OptTroncon.add(this.getCbx_TempsEscale());
            this.pnl_OptTroncon.add(this.getCbx_Presta());
        }
        return this.pnl_OptTroncon;
    }
    
    private JCheckBox getCbx_TempsVol() {
        if (this.cbx_TempsVol == null) {
            (this.cbx_TempsVol = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_TempsVol.setText("  Temps de vol");
            this.cbx_TempsVol.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_TempsVol;
    }
    
    private JCheckBox getCbx_NumVol() {
        if (this.cbx_NumVol == null) {
            (this.cbx_NumVol = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_NumVol.setText("  N° de vol");
            this.cbx_NumVol.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_NumVol;
    }
    
    private JCheckBox getCbx_TempsEscale() {
        if (this.cbx_TempsEscale == null) {
            (this.cbx_TempsEscale = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_TempsEscale.setText("  Temps d'escale");
            this.cbx_TempsEscale.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_TempsEscale;
    }
    
    private JCheckBox getCbx_Presta() {
        if (this.cbx_Presta == null) {
            (this.cbx_Presta = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_Presta.setText("  Prestations");
            this.cbx_Presta.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Presta;
    }
    
    private JPanel getPnl_OptSv() {
        if (this.pnl_OptSv == null) {
            (this.pnl_OptSv = new JPanel()).setBorder(new TitledBorder(null, "Services de vol", 4, 2, null, null));
            this.pnl_OptSv.setLayout(new GridLayout(4, 1, 0, 0));
            this.pnl_OptSv.add(this.getCbx_TypeAvion());
            this.pnl_OptSv.add(this.getCbx_DureeDecoucher());
            this.pnl_OptSv.add(this.getCbx_Hotel());
            this.pnl_OptSv.add(this.getCbx_Decalage());
        }
        return this.pnl_OptSv;
    }
    
    private JCheckBox getCbx_TypeAvion() {
        if (this.cbx_TypeAvion == null) {
            (this.cbx_TypeAvion = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_TypeAvion.setText("  Type avion");
            this.cbx_TypeAvion.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_TypeAvion;
    }
    
    private JCheckBox getCbx_DureeDecoucher() {
        if (this.cbx_DureeDecoucher == null) {
            (this.cbx_DureeDecoucher = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_DureeDecoucher.setText("  Dur\u00e9e d\u00e9coucher");
            this.cbx_DureeDecoucher.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_DureeDecoucher;
    }
    
    private JCheckBox getCbx_Hotel() {
        if (this.cbx_Hotel == null) {
            (this.cbx_Hotel = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_Hotel.setText("  H\u00f4tel");
            this.cbx_Hotel.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Hotel;
    }
    
    private JCheckBox getCbx_Decalage() {
        if (this.cbx_Decalage == null) {
            (this.cbx_Decalage = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_Decalage.setText("  D\u00e9calage horaire");
            this.cbx_Decalage.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Decalage;
    }
    
    private JPanel getPnl_OptRotation() {
        if (this.pnl_OptRotation == null) {
            (this.pnl_OptRotation = new JPanel()).setBorder(new TitledBorder(null, "Rotations", 4, 2, null, null));
            this.pnl_OptRotation.setLayout(new GridLayout(4, 1, 0, 0));
            this.pnl_OptRotation.add(this.getCbx_TempsVolSv());
            this.pnl_OptRotation.add(this.getCbx_TempsAbsence());
            this.pnl_OptRotation.add(this.getCbx_Rpc());
            this.pnl_OptRotation.add(this.getCbx_Reeng());
        }
        return this.pnl_OptRotation;
    }
    
    private JCheckBox getCbx_TempsVolSv() {
        if (this.cbx_TempsVolSv == null) {
            (this.cbx_TempsVolSv = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_TempsVolSv.setText("  Temps de vol SV");
            this.cbx_TempsVolSv.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_TempsVolSv;
    }
    
    private JCheckBox getCbx_TempsAbsence() {
        if (this.cbx_TempsAbsence == null) {
            (this.cbx_TempsAbsence = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_TempsAbsence.setText("  Temps d'absence");
            this.cbx_TempsAbsence.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_TempsAbsence;
    }
    
    private JCheckBox getCbx_Rpc() {
        if (this.cbx_Rpc == null) {
            (this.cbx_Rpc = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_Rpc.setText("  Dur\u00e9e RPC");
            this.cbx_Rpc.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Rpc;
    }
    
    private JCheckBox getCbx_Reeng() {
        if (this.cbx_Reeng == null) {
            (this.cbx_Reeng = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_Reeng.setText("  R\u00e9engagement");
            this.cbx_Reeng.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Reeng;
    }
    
    private JPanel getPnl_PDF_Middle() {
        if (this.pnl_PDF_Middle == null) {
            (this.pnl_PDF_Middle = new JPanel()).setLayout(new GridLayout(1, 2, 0, 0));
            this.pnl_PDF_Middle.add(this.getPnl_PDF_Middle_Couleurs());
            this.pnl_PDF_Middle.add(this.getPnl_PDF_Middle_Polices());
        }
        return this.pnl_PDF_Middle;
    }
    
    private JPanel getPnl_PDF_Middle_Couleurs() {
        if (this.pnl_PDF_Middle_Couleurs == null) {
            (this.pnl_PDF_Middle_Couleurs = new JPanel()).setBorder(new TitledBorder(null, "Couleurs", 4, 2, null, null));
            final GridBagLayout gbl_pnl_PDF_Bottom_Couleurs = new GridBagLayout();
            gbl_pnl_PDF_Bottom_Couleurs.columnWidths = new int[] { 200, 120, 0 };
            gbl_pnl_PDF_Bottom_Couleurs.rowHeights = new int[] { 48, 48, 48 };
            gbl_pnl_PDF_Bottom_Couleurs.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
            gbl_pnl_PDF_Bottom_Couleurs.rowWeights = new double[] { 0.0, 0.0, 0.0 };
            this.pnl_PDF_Middle_Couleurs.setLayout(gbl_pnl_PDF_Bottom_Couleurs);
            final GridBagConstraints gbc_rbtn_CrewColor = new GridBagConstraints();
            gbc_rbtn_CrewColor.weighty = 1.0;
            gbc_rbtn_CrewColor.fill = 1;
            gbc_rbtn_CrewColor.insets = new Insets(0, 0, 5, 5);
            gbc_rbtn_CrewColor.gridx = 0;
            gbc_rbtn_CrewColor.gridy = 0;
            this.pnl_PDF_Middle_Couleurs.add(this.getRbtn_CrewColor(), gbc_rbtn_CrewColor);
            final GridBagConstraints gbc_lbl_CrewColor = new GridBagConstraints();
            gbc_lbl_CrewColor.fill = 1;
            gbc_lbl_CrewColor.insets = new Insets(0, 0, 5, 0);
            gbc_lbl_CrewColor.gridx = 1;
            gbc_lbl_CrewColor.gridy = 0;
            this.pnl_PDF_Middle_Couleurs.add(this.getLbl_CrewColor(), gbc_lbl_CrewColor);
            final GridBagConstraints gbc_rbtn_PastelColor = new GridBagConstraints();
            gbc_rbtn_PastelColor.weighty = 1.0;
            gbc_rbtn_PastelColor.fill = 1;
            gbc_rbtn_PastelColor.insets = new Insets(0, 0, 5, 5);
            gbc_rbtn_PastelColor.gridx = 0;
            gbc_rbtn_PastelColor.gridy = 1;
            this.pnl_PDF_Middle_Couleurs.add(this.getRbtn_PastelColor(), gbc_rbtn_PastelColor);
            final GridBagConstraints gbc_lbl_PastelColor = new GridBagConstraints();
            gbc_lbl_PastelColor.fill = 1;
            gbc_lbl_PastelColor.insets = new Insets(0, 0, 5, 0);
            gbc_lbl_PastelColor.gridx = 1;
            gbc_lbl_PastelColor.gridy = 1;
            this.pnl_PDF_Middle_Couleurs.add(this.getLbl_PastelColor(), gbc_lbl_PastelColor);
            final GridBagConstraints gbc_rbtn_BWColor = new GridBagConstraints();
            gbc_rbtn_BWColor.weighty = 1.0;
            gbc_rbtn_BWColor.fill = 1;
            gbc_rbtn_BWColor.insets = new Insets(0, 0, 0, 5);
            gbc_rbtn_BWColor.gridx = 0;
            gbc_rbtn_BWColor.gridy = 2;
            this.pnl_PDF_Middle_Couleurs.add(this.getRbtn_BWColor(), gbc_rbtn_BWColor);
            final GridBagConstraints gbc_lbl_BWColor = new GridBagConstraints();
            gbc_lbl_BWColor.fill = 1;
            gbc_lbl_BWColor.gridx = 1;
            gbc_lbl_BWColor.gridy = 2;
            this.pnl_PDF_Middle_Couleurs.add(this.getLbl_BWColor(), gbc_lbl_BWColor);
            final ButtonGroup btng = new ButtonGroup();
            btng.add(this.getRbtn_CrewColor());
            btng.add(this.getRbtn_PastelColor());
            btng.add(this.getRbtn_BWColor());
        }
        return this.pnl_PDF_Middle_Couleurs;
    }
    
    private JLabel getLbl_CrewColor() {
        if (this.lbl_CrewColor == null) {
            final ImageIcon img = new ImageIcon(this.getClass().getResource("/res_images/ImgColorCrew.png"));
            this.lbl_CrewColor = new JLabel(img);
        }
        return this.lbl_CrewColor;
    }
    
    private JLabel getLbl_PastelColor() {
        if (this.lbl_PastelColor == null) {
            final ImageIcon img = new ImageIcon(this.getClass().getResource("/res_images/ImgColorPastel.png"));
            this.lbl_PastelColor = new JLabel(img);
        }
        return this.lbl_PastelColor;
    }
    
    private JLabel getLbl_BWColor() {
        if (this.lbl_BWColor == null) {
            final ImageIcon img = new ImageIcon(this.getClass().getResource("/res_images/ImgColorBW.png"));
            this.lbl_BWColor = new JLabel(img);
        }
        return this.lbl_BWColor;
    }
    
    private JRadioButton getRbtn_CrewColor() {
        if (this.rbtn_CrewColor == null) {
            (this.rbtn_CrewColor = new JRadioButton()).setIconTextGap(8);
            this.rbtn_CrewColor.setFont(new Font("Tahoma", 0, 16));
            this.rbtn_CrewColor.setText("Crew");
            this.rbtn_CrewColor.addKeyListener(new MyKeyAdapter());
        }
        return this.rbtn_CrewColor;
    }
    
    private JRadioButton getRbtn_PastelColor() {
        if (this.rbtn_PastelColor == null) {
            (this.rbtn_PastelColor = new JRadioButton()).setIconTextGap(8);
            this.rbtn_PastelColor.setFont(new Font("Tahoma", 0, 16));
            this.rbtn_PastelColor.setText("Pastel");
            this.rbtn_PastelColor.addKeyListener(new MyKeyAdapter());
        }
        return this.rbtn_PastelColor;
    }
    
    private JRadioButton getRbtn_BWColor() {
        if (this.rbtn_BWColor == null) {
            (this.rbtn_BWColor = new JRadioButton()).setIconTextGap(8);
            this.rbtn_BWColor.setFont(new Font("Tahoma", 0, 16));
            this.rbtn_BWColor.setText("Noir et Blanc");
            this.rbtn_BWColor.addKeyListener(new MyKeyAdapter());
        }
        return this.rbtn_BWColor;
    }
    
    private JPanel getPnl_PDF_Middle_Polices() {
        if (this.pnl_PDF_Middle_Polices == null) {
            (this.pnl_PDF_Middle_Polices = new JPanel()).setBorder(new TitledBorder(null, "Polices", 4, 2, null, null));
            final GridBagLayout gbl_pnl_PDF_Bottom_Polices = new GridBagLayout();
            gbl_pnl_PDF_Bottom_Polices.columnWidths = new int[] { 240, 80, 0 };
            gbl_pnl_PDF_Bottom_Polices.rowHeights = new int[] { 48, 48, 48 };
            gbl_pnl_PDF_Bottom_Polices.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
            gbl_pnl_PDF_Bottom_Polices.rowWeights = new double[] { 0.0, 0.0, 0.0 };
            this.pnl_PDF_Middle_Polices.setLayout(gbl_pnl_PDF_Bottom_Polices);
            final GridBagConstraints gbc_rbtn_Tahoma = new GridBagConstraints();
            gbc_rbtn_Tahoma.weighty = 1.0;
            gbc_rbtn_Tahoma.fill = 1;
            gbc_rbtn_Tahoma.insets = new Insets(0, 0, 5, 5);
            gbc_rbtn_Tahoma.gridx = 0;
            gbc_rbtn_Tahoma.gridy = 0;
            this.pnl_PDF_Middle_Polices.add(this.getRbtn_Tahoma(), gbc_rbtn_Tahoma);
            final GridBagConstraints gbc_lbl_Tahoma = new GridBagConstraints();
            gbc_lbl_Tahoma.fill = 1;
            gbc_lbl_Tahoma.insets = new Insets(0, 0, 5, 0);
            gbc_lbl_Tahoma.gridx = 1;
            gbc_lbl_Tahoma.gridy = 0;
            this.pnl_PDF_Middle_Polices.add(this.getLbl_Tahoma(), gbc_lbl_Tahoma);
            final GridBagConstraints gbc_rbtn_Serif = new GridBagConstraints();
            gbc_rbtn_Serif.weighty = 1.0;
            gbc_rbtn_Serif.fill = 1;
            gbc_rbtn_Serif.insets = new Insets(0, 0, 5, 5);
            gbc_rbtn_Serif.gridx = 0;
            gbc_rbtn_Serif.gridy = 1;
            this.pnl_PDF_Middle_Polices.add(this.getRbtn_Serif(), gbc_rbtn_Serif);
            final GridBagConstraints gbc_lbl_Serif = new GridBagConstraints();
            gbc_lbl_Serif.fill = 1;
            gbc_lbl_Serif.insets = new Insets(0, 0, 5, 0);
            gbc_lbl_Serif.gridx = 1;
            gbc_lbl_Serif.gridy = 1;
            this.pnl_PDF_Middle_Polices.add(this.getLbl_Serif(), gbc_lbl_Serif);
            final GridBagConstraints gbc_rbtn_Monospaced = new GridBagConstraints();
            gbc_rbtn_Monospaced.weighty = 1.0;
            gbc_rbtn_Monospaced.fill = 1;
            gbc_rbtn_Monospaced.insets = new Insets(0, 0, 0, 5);
            gbc_rbtn_Monospaced.gridx = 0;
            gbc_rbtn_Monospaced.gridy = 2;
            this.pnl_PDF_Middle_Polices.add(this.getRbtn_Monospaced(), gbc_rbtn_Monospaced);
            final GridBagConstraints gbc_lbl_Monospaced = new GridBagConstraints();
            gbc_lbl_Monospaced.fill = 1;
            gbc_lbl_Monospaced.gridx = 1;
            gbc_lbl_Monospaced.gridy = 2;
            this.pnl_PDF_Middle_Polices.add(this.getLbl_Monospaced(), gbc_lbl_Monospaced);
            final ButtonGroup btng = new ButtonGroup();
            btng.add(this.getRbtn_Tahoma());
            btng.add(this.getRbtn_Serif());
            btng.add(this.getRbtn_Monospaced());
        }
        return this.pnl_PDF_Middle_Polices;
    }
    
    private JLabel getLbl_Tahoma() {
        if (this.lbl_Tahoma == null) {
            final ImageIcon img = new ImageIcon(this.getClass().getResource("/res_images/ImgFontArial.png"));
            this.lbl_Tahoma = new JLabel(img);
        }
        return this.lbl_Tahoma;
    }
    
    private JLabel getLbl_Serif() {
        if (this.lbl_Serif == null) {
            final ImageIcon img = new ImageIcon(this.getClass().getResource("/res_images/ImgFontTimes.png"));
            this.lbl_Serif = new JLabel(img);
        }
        return this.lbl_Serif;
    }
    
    private JLabel getLbl_Monospaced() {
        if (this.lbl_Monospaced == null) {
            final ImageIcon img = new ImageIcon(this.getClass().getResource("/res_images/ImgFontCourier.png"));
            this.lbl_Monospaced = new JLabel(img);
        }
        return this.lbl_Monospaced;
    }
    
    private JRadioButton getRbtn_Tahoma() {
        if (this.rbtn_Tahoma == null) {
            (this.rbtn_Tahoma = new JRadioButton()).setIconTextGap(8);
            this.rbtn_Tahoma.setFont(new Font("Tahoma", 0, 16));
            this.rbtn_Tahoma.setText("Sans empattement");
            this.rbtn_Tahoma.addKeyListener(new MyKeyAdapter());
        }
        return this.rbtn_Tahoma;
    }
    
    private JRadioButton getRbtn_Serif() {
        if (this.rbtn_Serif == null) {
            (this.rbtn_Serif = new JRadioButton()).setIconTextGap(8);
            this.rbtn_Serif.setFont(new Font("Tahoma", 0, 16));
            this.rbtn_Serif.setText("Avec empattement");
            this.rbtn_Serif.addKeyListener(new MyKeyAdapter());
        }
        return this.rbtn_Serif;
    }
    
    private JRadioButton getRbtn_Monospaced() {
        if (this.rbtn_Monospaced == null) {
            (this.rbtn_Monospaced = new JRadioButton()).setIconTextGap(8);
            this.rbtn_Monospaced.setFont(new Font("Tahoma", 0, 16));
            this.rbtn_Monospaced.setText("Chasse fixe");
            this.rbtn_Monospaced.addKeyListener(new MyKeyAdapter());
        }
        return this.rbtn_Monospaced;
    }
    
    private JPanel getPnl_PDF_Bottom() {
        if (this.pnl_PDF_Bottom == null) {
            (this.pnl_PDF_Bottom = new JPanel()).setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Exportation", 4, 2, null, new Color(0, 0, 0)));
            this.pnl_PDF_Bottom.setLayout(new GridLayout(0, 2, 0, 0));
            this.pnl_PDF_Bottom.add(this.getCbx_MoisComplet());
        }
        return this.pnl_PDF_Bottom;
    }
    
    private JCheckBox getCbx_MoisComplet() {
        if (this.cbx_MoisComplet == null) {
            (this.cbx_MoisComplet = new CustomCheckBox()).setFont(new Font("Tahoma", 0, 16));
            this.cbx_MoisComplet.setText("  Mois complet syst\u00e9matique");
            this.cbx_MoisComplet.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_MoisComplet;
    }
    
    private JPanel getPnl_Notices() {
        if (this.pnl_Notices == null) {
            (this.pnl_Notices = new JPanel()).setLayout(new GridLayout(1, 2, 16, 8));
            this.pnl_Notices.add(this.getPnl_Notices_Left());
            this.pnl_Notices.add(this.getPnl_Notices_Right());
        }
        return this.pnl_Notices;
    }
    
    private JPanel getPnl_Notices_Left() {
        if (this.pnl_Notices_Left == null) {
            (this.pnl_Notices_Left = new JPanel()).setLayout(new BoxLayout(this.pnl_Notices_Left, 3));
            this.pnl_Notices_Left.add(this.getVerticalStrut_Notices_Left_Top());
            this.pnl_Notices_Left.add(this.getPnl_NoticesAF());
            this.pnl_Notices_Left.add(this.getVerticalGlue_Notices_Left_Bottom());
        }
        return this.pnl_Notices_Left;
    }
    
    private JPanel getPnl_NoticesAF() {
        if (this.pnl_NoticesAF == null) {
            (this.pnl_NoticesAF = new JPanel()).setBorder(new TitledBorder(null, "Notices Escales AF", 4, 2, null, null));
            this.pnl_NoticesAF.setLayout(new BoxLayout(this.pnl_NoticesAF, 3));
            this.pnl_NoticesAF.add(this.getCbx_NoticesAF_Auto());
            this.pnl_NoticesAF.add(this.getCbx_NoticesAF_Total());
            this.pnl_NoticesAF.add(this.getVerticalStrut_NoticesAF_1());
            this.pnl_NoticesAF.add(this.getLbl_NoticesAF_DossierSauvegarde());
            this.pnl_NoticesAF.add(this.getTfld_NoticesAF_RepSauvegarde());
            this.pnl_NoticesAF.add(this.getVerticalStrut_NoticesAF_2());
            this.pnl_NoticesAF.add(this.getLbl_NoticesAF_FormatNom());
            this.pnl_NoticesAF.add(this.getPanel_NoticesAF_FormatNom());
        }
        return this.pnl_NoticesAF;
    }
    
    private JCheckBox getCbx_NoticesAF_Auto() {
        if (this.cbx_NoticesAF_Auto == null) {
            (this.cbx_NoticesAF_Auto = new CustomCheckBox()).setMaximumSize(new Dimension(320, 32));
            this.cbx_NoticesAF_Auto.setIconTextGap(8);
            this.cbx_NoticesAF_Auto.setFont(new Font("Tahoma", 0, 16));
            this.cbx_NoticesAF_Auto.setText("T\u00e9l\u00e9chargement automatique");
            this.cbx_NoticesAF_Auto.setToolTipText("Importer automatiquement les notices AF \u00e0 l'importation d'un planning, \u00e0 l'emplacement choisi");
            this.cbx_NoticesAF_Auto.addMouseListener(new MyMouseAdapter());
            this.cbx_NoticesAF_Auto.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_NoticesAF_Auto;
    }
    
    private JCheckBox getCbx_NoticesAF_Total() {
        if (this.cbx_NoticesAF_Total == null) {
            (this.cbx_NoticesAF_Total = new CustomCheckBox("Collection compl\u00e8te")).setMaximumSize(new Dimension(320, 32));
            this.cbx_NoticesAF_Total.setIconTextGap(8);
            this.cbx_NoticesAF_Total.setFont(new Font("Tahoma", 0, 16));
            this.cbx_NoticesAF_Total.setToolTipText("R\u00e9cupere l'int\u00e9gralit\u00e9 des notices escales et non uniquement celles des d\u00e9couchers");
        }
        return this.cbx_NoticesAF_Total;
    }
    
    private JLabel getLbl_NoticesAF_DossierSauvegarde() {
        if (this.lbl_NoticesAF_DossierSauvegarde == null) {
            (this.lbl_NoticesAF_DossierSauvegarde = new JLabel("Dossier de sauvegarde :")).setMaximumSize(new Dimension(32767, 24));
            this.lbl_NoticesAF_DossierSauvegarde.setPreferredSize(new Dimension(117, 24));
            this.lbl_NoticesAF_DossierSauvegarde.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_NoticesAF_DossierSauvegarde;
    }
    
    private JTextField getTfld_NoticesAF_RepSauvegarde() {
        if (this.tfld_NoticesAF_RepSauvegarde == null) {
            (this.tfld_NoticesAF_RepSauvegarde = new JTextField()).setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            this.tfld_NoticesAF_RepSauvegarde.setAlignmentX(0.0f);
            this.tfld_NoticesAF_RepSauvegarde.setPreferredSize(new Dimension(6, 32));
            this.tfld_NoticesAF_RepSauvegarde.setFont(new Font("Tahoma", 0, 16));
            this.tfld_NoticesAF_RepSauvegarde.setToolTipText("Pour modifier l'emplacement, vous pouvez d\u00e9cocher puis recocher la case 'Auto'");
        }
        return this.tfld_NoticesAF_RepSauvegarde;
    }
    
    private JLabel getLbl_NoticesAF_FormatNom() {
        if (this.lbl_NoticesAF_FormatNom == null) {
            (this.lbl_NoticesAF_FormatNom = new JLabel("Format du nom :")).setPreferredSize(new Dimension(72, 24));
            this.lbl_NoticesAF_FormatNom.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_NoticesAF_FormatNom;
    }
    
    private JPanel getPanel_NoticesAF_FormatNom() {
        if (this.panel_NoticesAF_FormatNom == null) {
            (this.panel_NoticesAF_FormatNom = new JPanel()).setAlignmentX(0.0f);
            this.panel_NoticesAF_FormatNom.setLayout(new BoxLayout(this.panel_NoticesAF_FormatNom, 0));
            this.panel_NoticesAF_FormatNom.add(this.getTfld_LibelleNoticesAF());
            this.panel_NoticesAF_FormatNom.add(this.getLbl_PdfAF());
        }
        return this.panel_NoticesAF_FormatNom;
    }
    
    private JTextField getTfld_LibelleNoticesAF() {
        if (this.tfld_LibelleNoticesAF == null) {
            (this.tfld_LibelleNoticesAF = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_LibelleNoticesAF.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            this.tfld_LibelleNoticesAF.setFont(new Font("Tahoma", 0, 16));
            this.tfld_LibelleNoticesAF.setToolTipText("<html>%c : code IATA de l'escale<br>%n : nom de l'escale</html>");
            this.tfld_LibelleNoticesAF.setColumns(10);
        }
        return this.tfld_LibelleNoticesAF;
    }
    
    private JLabel getLbl_PdfAF() {
        if (this.lbl_PdfAF == null) {
            (this.lbl_PdfAF = new JLabel(".pdf")).setAlignmentX(0.5f);
            this.lbl_PdfAF.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_PdfAF;
    }
    
    private JPanel getPnl_Notices_Right() {
        if (this.pnl_Notices_Right == null) {
            (this.pnl_Notices_Right = new JPanel()).setLayout(new BoxLayout(this.pnl_Notices_Right, 3));
            this.pnl_Notices_Right.add(this.getVerticalStrut_Notices_Right_Top());
            this.pnl_Notices_Right.add(this.getPanel_NoticesCE());
            this.pnl_Notices_Right.add(this.getVerticalGlue_Notices_Right_Bottom());
        }
        return this.pnl_Notices_Right;
    }
    
    private JPanel getPanel_NoticesCE() {
        if (this.panel_NoticesCE == null) {
            (this.panel_NoticesCE = new JPanel()).setBorder(new TitledBorder(null, "Notices Escales CE", 4, 2, null, null));
            this.panel_NoticesCE.setLayout(new BoxLayout(this.panel_NoticesCE, 3));
            this.panel_NoticesCE.add(this.getCbx_NoticesCE_Auto());
            this.panel_NoticesCE.add(this.getCbx_NoticesCE_Total());
            this.panel_NoticesCE.add(this.getVerticalStrut_NoticesCE_1());
            this.panel_NoticesCE.add(this.getLbl_NoticesCE_DossierSauvegarde());
            this.panel_NoticesCE.add(this.getTfld_NoticesCE_RepSauvegarde());
            this.panel_NoticesCE.add(this.getVerticalStrut_NoticesCE_2());
            this.panel_NoticesCE.add(this.getLbl_NoticesCE_FormatNom());
            this.panel_NoticesCE.add(this.getPanel_NoticesCE_FormatNom());
            this.panel_NoticesCE.add(this.getVerticalStrut_NoticesCE_3());
            this.panel_NoticesCE.add(this.getPanel_NoticesCE_Login());
            this.panel_NoticesCE.add(this.getVerticalStrut_NoticesCE_4());
            this.panel_NoticesCE.add(this.getPanel_NoticesCE_Password());
        }
        return this.panel_NoticesCE;
    }
    
    private JCheckBox getCbx_NoticesCE_Auto() {
        if (this.cbx_NoticesCE_Auto == null) {
            (this.cbx_NoticesCE_Auto = new CustomCheckBox()).setMaximumSize(new Dimension(320, 32));
            this.cbx_NoticesCE_Auto.setIconTextGap(8);
            this.cbx_NoticesCE_Auto.setFont(new Font("Tahoma", 0, 16));
            this.cbx_NoticesCE_Auto.setText("T\u00e9l\u00e9chargement automatique");
            this.cbx_NoticesCE_Auto.setToolTipText("Importer automatiquement les notices CE \u00e0 l'importation d'un planning, \u00e0 l'emplacement choisi");
            this.cbx_NoticesCE_Auto.addMouseListener(new MyMouseAdapter());
            this.cbx_NoticesCE_Auto.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_NoticesCE_Auto;
    }
    
    private JCheckBox getCbx_NoticesCE_Total() {
        if (this.cbx_NoticesCE_Total == null) {
            (this.cbx_NoticesCE_Total = new CustomCheckBox("Collection compl\u00e8te")).setMaximumSize(new Dimension(320, 32));
            this.cbx_NoticesCE_Total.setIconTextGap(8);
            this.cbx_NoticesCE_Total.setFont(new Font("Tahoma", 0, 16));
            this.cbx_NoticesCE_Total.setToolTipText("R\u00e9cupere l'int\u00e9gralit\u00e9 des notices escales et non uniquement celles des d\u00e9couchers");
        }
        return this.cbx_NoticesCE_Total;
    }
    
    private JLabel getLbl_NoticesCE_DossierSauvegarde() {
        if (this.lbl_NoticesCE_DossierSauvegarde == null) {
            (this.lbl_NoticesCE_DossierSauvegarde = new JLabel("Dossier de sauvegarde :")).setMaximumSize(new Dimension(32767, 24));
            this.lbl_NoticesCE_DossierSauvegarde.setPreferredSize(new Dimension(117, 24));
            this.lbl_NoticesCE_DossierSauvegarde.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_NoticesCE_DossierSauvegarde;
    }
    
    private JTextField getTfld_NoticesCE_RepSauvegarde() {
        if (this.tfld_NoticesCE_RepSauvegarde == null) {
            (this.tfld_NoticesCE_RepSauvegarde = new JTextField()).setAlignmentX(0.0f);
            this.tfld_NoticesCE_RepSauvegarde.setPreferredSize(new Dimension(6, 32));
            this.tfld_NoticesCE_RepSauvegarde.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            this.tfld_NoticesCE_RepSauvegarde.setFont(new Font("Tahoma", 0, 16));
            this.tfld_NoticesCE_RepSauvegarde.setToolTipText("Pour modifier l'emplacement, vous pouvez d\u00e9cocher puis recocher la case 'Auto'");
        }
        return this.tfld_NoticesCE_RepSauvegarde;
    }
    
    private JLabel getLbl_NoticesCE_FormatNom() {
        if (this.lbl_NoticesCE_FormatNom == null) {
            (this.lbl_NoticesCE_FormatNom = new JLabel("Format du nom :")).setPreferredSize(new Dimension(72, 24));
            this.lbl_NoticesCE_FormatNom.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_NoticesCE_FormatNom;
    }
    
    private JPanel getPanel_NoticesCE_FormatNom() {
        if (this.panel_NoticesCE_FormatNom == null) {
            (this.panel_NoticesCE_FormatNom = new JPanel()).setAlignmentX(0.0f);
            this.panel_NoticesCE_FormatNom.setLayout(new BoxLayout(this.panel_NoticesCE_FormatNom, 0));
            this.panel_NoticesCE_FormatNom.add(this.getTfld_LibelleNoticesCE());
            this.panel_NoticesCE_FormatNom.add(this.getLbl_PdfCE());
        }
        return this.panel_NoticesCE_FormatNom;
    }
    
    private JTextField getTfld_LibelleNoticesCE() {
        if (this.tfld_LibelleNoticesCE == null) {
            (this.tfld_LibelleNoticesCE = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_LibelleNoticesCE.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            this.tfld_LibelleNoticesCE.setFont(new Font("Tahoma", 0, 16));
            this.tfld_LibelleNoticesCE.setToolTipText("<html>%c : code IATA de l'escale<br>%n : nom de l'escale</html>");
            this.tfld_LibelleNoticesCE.setColumns(10);
        }
        return this.tfld_LibelleNoticesCE;
    }
    
    private JLabel getLbl_PdfCE() {
        if (this.lbl_PdfCE == null) {
            (this.lbl_PdfCE = new JLabel(".pdf")).setAlignmentX(0.5f);
            this.lbl_PdfCE.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_PdfCE;
    }
    
    private JPanel getPanel_NoticesCE_Login() {
        if (this.panel_NoticesCE_Login == null) {
            (this.panel_NoticesCE_Login = new JPanel()).setAlignmentX(0.0f);
            this.panel_NoticesCE_Login.setLayout(new BoxLayout(this.panel_NoticesCE_Login, 0));
            this.panel_NoticesCE_Login.add(this.getLbl_LoginCE());
            this.panel_NoticesCE_Login.add(this.getTfld_LoginCE());
        }
        return this.panel_NoticesCE_Login;
    }
    
    private JLabel getLbl_LoginCE() {
        if (this.lbl_LoginCE == null) {
            (this.lbl_LoginCE = new JLabel("Matricule :")).setPreferredSize(new Dimension(128, 14));
            this.lbl_LoginCE.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_LoginCE;
    }
    
    private JTextField getTfld_LoginCE() {
        if (this.tfld_LoginCE == null) {
            (this.tfld_LoginCE = new JTextField()).setAlignmentX(0.0f);
            this.tfld_LoginCE.setPreferredSize(new Dimension(6, 32));
            this.tfld_LoginCE.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            this.tfld_LoginCE.setFont(new Font("Tahoma", 0, 16));
            this.tfld_LoginCE.setColumns(10);
        }
        return this.tfld_LoginCE;
    }
    
    private JPanel getPanel_NoticesCE_Password() {
        if (this.panel_NoticesCE_Password == null) {
            (this.panel_NoticesCE_Password = new JPanel()).setAlignmentX(0.0f);
            this.panel_NoticesCE_Password.setLayout(new BoxLayout(this.panel_NoticesCE_Password, 0));
            this.panel_NoticesCE_Password.add(this.getLbl_PasswordCE());
            this.panel_NoticesCE_Password.add(this.getPwdf_PasswordCE());
        }
        return this.panel_NoticesCE_Password;
    }
    
    private JLabel getLbl_PasswordCE() {
        if (this.lbl_PasswordCE == null) {
            (this.lbl_PasswordCE = new JLabel("Mot de passe :")).setPreferredSize(new Dimension(128, 14));
            this.lbl_PasswordCE.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_PasswordCE;
    }
    
    private JPasswordField getPwdf_PasswordCE() {
        if (this.pwdf_PasswordCE == null) {
            (this.pwdf_PasswordCE = new JPasswordField()).setAlignmentX(0.0f);
            this.pwdf_PasswordCE.setPreferredSize(new Dimension(6, 32));
            this.pwdf_PasswordCE.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            this.pwdf_PasswordCE.setFont(new Font("Tahoma", 0, 16));
            this.pwdf_PasswordCE.setColumns(10);
        }
        return this.pwdf_PasswordCE;
    }
    
    private JPanel getPnl_Auto() {
        if (this.pnl_Auto == null) {
            (this.pnl_Auto = new JPanel()).setBounds(new Rectangle(4, 4, 236, 211));
            this.pnl_Auto.setBorder(new EtchedBorder(1, null, null));
            this.pnl_Auto.setLayout(new BoxLayout(this.pnl_Auto, 0));
            this.pnl_Auto.add(this.getHorizontalStrut_Auto_Left());
            this.pnl_Auto.add(this.getPnl_Auto_Contenu());
            this.pnl_Auto.add(this.getHorizontalStrut_Auto_Right());
        }
        return this.pnl_Auto;
    }
    
    private Component getHorizontalStrut_Auto_Left() {
        if (this.horizontalStrut_Auto_Left == null) {
            this.horizontalStrut_Auto_Left = Box.createHorizontalStrut(8);
        }
        return this.horizontalStrut_Auto_Left;
    }
    
    private Component getHorizontalStrut_Auto_Right() {
        if (this.horizontalStrut_Auto_Right == null) {
            this.horizontalStrut_Auto_Right = Box.createHorizontalStrut(8);
        }
        return this.horizontalStrut_Auto_Right;
    }
    
    private JPanel getPnl_Auto_Contenu() {
        if (this.pnl_Auto_Contenu == null) {
            (this.pnl_Auto_Contenu = new JPanel()).setLayout(new BoxLayout(this.pnl_Auto_Contenu, 3));
            this.pnl_Auto_Contenu.add(this.getVerticalStrut_Auto_Contenu_1());
            this.pnl_Auto_Contenu.add(this.getCbx_Blocs_Auto());
            this.pnl_Auto_Contenu.add(this.getCbx_Save_Auto());
            this.pnl_Auto_Contenu.add(this.getVerticalStrut_Auto_Contenu_2());
            this.pnl_Auto_Contenu.add(this.getLbl_Auto_RepSauvegarde());
            this.pnl_Auto_Contenu.add(this.getTfld_Auto_RepSauvegarde());
            this.pnl_Auto_Contenu.add(this.getVerticalGlue_Auto_Contenu_Bottom());
        }
        return this.pnl_Auto_Contenu;
    }
    
    private Component getVerticalStrut_Auto_Contenu_1() {
        if (this.verticalStrut_Auto_Contenu_1 == null) {
            this.verticalStrut_Auto_Contenu_1 = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_Auto_Contenu_1;
    }
    
    private JCheckBox getCbx_Blocs_Auto() {
        if (this.cbx_Blocs_Auto == null) {
            (this.cbx_Blocs_Auto = new CustomCheckBox()).setIconTextGap(8);
            this.cbx_Blocs_Auto.setFont(new Font("Tahoma", 0, 16));
            this.cbx_Blocs_Auto.setText("R\u00e9cup\u00e9ration automatique des blocs r\u00e9els");
            this.cbx_Blocs_Auto.setToolTipText("Importer automatiquement les blocs r\u00e9els \u00e0 l'importation d'un planning");
        }
        return this.cbx_Blocs_Auto;
    }
    
    private JCheckBox getCbx_Save_Auto() {
        if (this.cbx_Save_Auto == null) {
            (this.cbx_Save_Auto = new CustomCheckBox("Sauvegarde automatique des plannings .xml")).setIconTextGap(8);
            this.cbx_Save_Auto.setFont(new Font("Tahoma", 0, 16));
            this.cbx_Save_Auto.setToolTipText("Sauvegarder automatiquement le planning mis \u00e0 jour");
            this.cbx_Save_Auto.addMouseListener(new MyMouseAdapter());
            this.cbx_Save_Auto.addKeyListener(new MyKeyAdapter());
        }
        return this.cbx_Save_Auto;
    }
    
    private Component getVerticalStrut_Auto_Contenu_2() {
        if (this.verticalStrut_Auto_Contenu_2 == null) {
            this.verticalStrut_Auto_Contenu_2 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Auto_Contenu_2;
    }
    
    private JLabel getLbl_Auto_RepSauvegarde() {
        if (this.lbl_Auto_RepSauvegarde == null) {
            (this.lbl_Auto_RepSauvegarde = new JLabel("Dossier de sauvegarde :")).setMaximumSize(new Dimension(32767, 24));
            this.lbl_Auto_RepSauvegarde.setPreferredSize(new Dimension(117, 24));
            this.lbl_Auto_RepSauvegarde.setFont(new Font("Tahoma", 0, 16));
        }
        return this.lbl_Auto_RepSauvegarde;
    }
    
    private JTextField getTfld_Auto_RepSauvegarde() {
        if (this.tfld_Auto_RepSauvegarde == null) {
            (this.tfld_Auto_RepSauvegarde = new JTextField()).setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            this.tfld_Auto_RepSauvegarde.setAlignmentX(0.0f);
            this.tfld_Auto_RepSauvegarde.setPreferredSize(new Dimension(6, 32));
            this.tfld_Auto_RepSauvegarde.setFont(new Font("Tahoma", 0, 16));
            this.tfld_Auto_RepSauvegarde.setToolTipText("Pour modifier l'emplacement, vous pouvez d\u00e9cocher puis recocher la case 'Auto'");
        }
        return this.tfld_Auto_RepSauvegarde;
    }
    
    private Component getVerticalGlue_Auto_Contenu_Bottom() {
        if (this.verticalGlue_Auto_Contenu_Bottom == null) {
            this.verticalGlue_Auto_Contenu_Bottom = Box.createVerticalGlue();
        }
        return this.verticalGlue_Auto_Contenu_Bottom;
    }
    
    private JPanel getPnl_Reset() {
        if (this.pnl_Reset == null) {
            (this.pnl_Reset = new JPanel()).setLayout(new BoxLayout(this.pnl_Reset, 1));
            this.pnl_Reset.add(this.getVerticalStrut_Reset_1());
            this.pnl_Reset.add(this.getLbl_Reset());
            this.pnl_Reset.add(this.getVerticalStrut_Reset_2());
            this.pnl_Reset.add(this.getBtn_Reset());
        }
        return this.pnl_Reset;
    }
    
    private JLabel getLbl_Reset() {
        if (this.lbl_Reset == null) {
            (this.lbl_Reset = new JLabel("<html>Cliquer sur bouton \"R\u00e9initialisation\" effacera toutes les traces de<br>ChopeCREW sur l'ordinateur et r\u00e9initialisera toutes les options</html>")).setFont(new Font("Tahoma", 0, 16));
            this.lbl_Reset.setHorizontalAlignment(0);
            this.lbl_Reset.setAlignmentX(0.5f);
        }
        return this.lbl_Reset;
    }
    
    private JButton getBtn_Reset() {
        if (this.btn_Reset == null) {
            (this.btn_Reset = new JButton("New button")).setPreferredSize(new Dimension(128, 40));
            this.btn_Reset.setMaximumSize(new Dimension(128, 40));
            this.btn_Reset.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Reset.setAlignmentX(0.5f);
            this.btn_Reset.setFont(new Font("Tahoma", 0, 16));
            this.btn_Reset.setText("R\u00e9initialisation");
            this.btn_Reset.addMouseListener(new MyMouseAdapter());
            this.btn_Reset.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Reset;
    }
    
    private JComboBox<String> getCobx_TimeRef() {
        if (this.cobx_TimeRef == null) {
            (this.cobx_TimeRef = new JComboBox<String>()).setMinimumSize(new Dimension(10, 10));
            this.cobx_TimeRef.setPreferredSize(new Dimension(200, 48));
            this.cobx_TimeRef.setBorder(new TitledBorder(null, "R\u00e9f\u00e9rence horaire", 4, 2, null, null));
            this.cobx_TimeRef.setFont(new Font("Tahoma", 0, 16));
            String[] timeRefItems;
            for (int length = (timeRefItems = ChopeCrew.options.timeRefItems).length, i = 0; i < length; ++i) {
                final String timeRefItem = timeRefItems[i];
                this.cobx_TimeRef.addItem(timeRefItem);
            }
        }
        return this.cobx_TimeRef;
    }
    
    private JButton getBtn_Valider() {
        if (this.btn_Valider == null) {
            (this.btn_Valider = new JButton()).setMaximumSize(new Dimension(128, 40));
            this.btn_Valider.setPreferredSize(new Dimension(128, 40));
            this.btn_Valider.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Valider.setFont(new Font("Tahoma", 0, 16));
            this.btn_Valider.setText("Valider");
            this.btn_Valider.addMouseListener(new MyMouseAdapter());
            this.btn_Valider.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Valider;
    }
    
    private JButton getBtn_Annuler() {
        if (this.btn_Annuler == null) {
            (this.btn_Annuler = new JButton()).setMaximumSize(new Dimension(128, 40));
            this.btn_Annuler.setPreferredSize(new Dimension(128, 40));
            this.btn_Annuler.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Annuler.setFont(new Font("Tahoma", 0, 16));
            this.btn_Annuler.setText("Annuler");
            this.btn_Annuler.addMouseListener(new MyMouseAdapter());
            this.btn_Annuler.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Annuler;
    }
    
    private Component getVerticalStrut_Sortie_Top() {
        if (this.verticalStrut_Sortie_Top == null) {
            this.verticalStrut_Sortie_Top = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Sortie_Top;
    }
    
    private Component getHorizontalStrut_Sortie_Boutons_2() {
        if (this.horizontalStrut_Sortie_Boutons_2 == null) {
            this.horizontalStrut_Sortie_Boutons_2 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Sortie_Boutons_2;
    }
    
    private Component getHorizontalStrut_Sortie_Boutons_1() {
        if (this.horizontalStrut_Sortie_Boutons_1 == null) {
            this.horizontalStrut_Sortie_Boutons_1 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Sortie_Boutons_1;
    }
    
    private Component getVerticalStrut_PositionRotation_1() {
        if (this.verticalStrut_PositionRotation_1 == null) {
            this.verticalStrut_PositionRotation_1 = Box.createVerticalStrut(16);
        }
        return this.verticalStrut_PositionRotation_1;
    }
    
    private Component getVerticalStrut_PositionRotation_2() {
        if (this.verticalStrut_PositionRotation_2 == null) {
            this.verticalStrut_PositionRotation_2 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_PositionRotation_2;
    }
    
    private Component getVerticalStrut_PositionRotation_3() {
        if (this.verticalStrut_PositionRotation_3 == null) {
            this.verticalStrut_PositionRotation_3 = Box.createVerticalStrut(16);
        }
        return this.verticalStrut_PositionRotation_3;
    }
    
    private Component getVerticalGlue_OptionsAvancees_Left_Botom() {
        if (this.verticalGlue_OptionsAvancees_Left_Botom == null) {
            this.verticalGlue_OptionsAvancees_Left_Botom = Box.createVerticalGlue();
        }
        return this.verticalGlue_OptionsAvancees_Left_Botom;
    }
    
    private Component getHorizontalStrut_OptionsAvancees() {
        if (this.horizontalStrut_OptionsAvancees == null) {
            this.horizontalStrut_OptionsAvancees = Box.createHorizontalStrut(16);
        }
        return this.horizontalStrut_OptionsAvancees;
    }
    
    private Component getVerticalGlue_OptionsAvancees_Right_Bottom() {
        if (this.verticalGlue_OptionsAvancees_Right_Bottom == null) {
            this.verticalGlue_OptionsAvancees_Right_Bottom = Box.createVerticalGlue();
        }
        return this.verticalGlue_OptionsAvancees_Right_Bottom;
    }
    
    private Component getVerticalStrut_OptionsAvancees_Right_1() {
        if (this.verticalStrut_OptionsAvancees_Right_1 == null) {
            this.verticalStrut_OptionsAvancees_Right_1 = Box.createVerticalStrut(16);
        }
        return this.verticalStrut_OptionsAvancees_Right_1;
    }
    
    private Component getVerticalStrut_OptionsAvancees_Right_2() {
        if (this.verticalStrut_OptionsAvancees_Right_2 == null) {
            this.verticalStrut_OptionsAvancees_Right_2 = Box.createVerticalStrut(16);
        }
        return this.verticalStrut_OptionsAvancees_Right_2;
    }
    
    private Component getVerticalStrut_OptionsAvancees_Left_1() {
        if (this.verticalStrut_OptionsAvancees_Left_1 == null) {
            this.verticalStrut_OptionsAvancees_Left_1 = Box.createVerticalStrut(16);
        }
        return this.verticalStrut_OptionsAvancees_Left_1;
    }
    
    private JPanel getPnl_Google_Contenu_Login() {
        if (this.pnl_Google_Contenu_Login == null) {
            (this.pnl_Google_Contenu_Login = new JPanel()).setLayout(new BoxLayout(this.pnl_Google_Contenu_Login, 0));
            this.pnl_Google_Contenu_Login.add(this.getLbl_LoginGoogle());
            this.pnl_Google_Contenu_Login.add(this.getHorizontalStrut_Google_Login_1());
            this.pnl_Google_Contenu_Login.add(this.getTfld_LoginGoogle());
            this.pnl_Google_Contenu_Login.add(this.getHorizontalStrut_Google_Contenu_Login_2());
            this.pnl_Google_Contenu_Login.add(this.getCbox_GoogleAuto());
        }
        return this.pnl_Google_Contenu_Login;
    }
    
    private JPanel getPanel_Google_Contenu_Calendars() {
        if (this.panel_Google_Contenu_Calendars == null) {
            this.panel_Google_Contenu_Calendars = new JPanel();
            final GridBagLayout gbl_panel_Google_Contenu_Calendars = new GridBagLayout();
            gbl_panel_Google_Contenu_Calendars.columnWidths = new int[] { 128, 464, 40 };
            gbl_panel_Google_Contenu_Calendars.rowHeights = new int[] { 32, 32, 32, 32, 32, 32 };
            gbl_panel_Google_Contenu_Calendars.columnWeights = new double[] { 1.0, 1.0, 0.0 };
            gbl_panel_Google_Contenu_Calendars.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
            this.panel_Google_Contenu_Calendars.setLayout(gbl_panel_Google_Contenu_Calendars);
            final GridBagConstraints gbc_lbl_Rotations = new GridBagConstraints();
            gbc_lbl_Rotations.weighty = 1.0;
            gbc_lbl_Rotations.fill = 2;
            gbc_lbl_Rotations.insets = new Insets(0, 0, 0, 5);
            gbc_lbl_Rotations.gridx = 0;
            gbc_lbl_Rotations.gridy = 0;
            this.panel_Google_Contenu_Calendars.add(this.getLbl_Rotations(), gbc_lbl_Rotations);
            final GridBagConstraints gbc_lbl_Troncons = new GridBagConstraints();
            gbc_lbl_Troncons.weighty = 1.0;
            gbc_lbl_Troncons.anchor = 17;
            gbc_lbl_Troncons.insets = new Insets(0, 0, 0, 5);
            gbc_lbl_Troncons.gridx = 0;
            gbc_lbl_Troncons.gridy = 1;
            this.panel_Google_Contenu_Calendars.add(this.getLbl_Troncons(), gbc_lbl_Troncons);
            final GridBagConstraints gbc_lbl_ActSol = new GridBagConstraints();
            gbc_lbl_ActSol.weighty = 1.0;
            gbc_lbl_ActSol.anchor = 17;
            gbc_lbl_ActSol.insets = new Insets(0, 0, 0, 5);
            gbc_lbl_ActSol.gridx = 0;
            gbc_lbl_ActSol.gridy = 2;
            this.panel_Google_Contenu_Calendars.add(this.getLbl_ActSol(), gbc_lbl_ActSol);
            final GridBagConstraints gbc_lbl_Conges = new GridBagConstraints();
            gbc_lbl_Conges.weighty = 1.0;
            gbc_lbl_Conges.anchor = 17;
            gbc_lbl_Conges.insets = new Insets(0, 0, 0, 5);
            gbc_lbl_Conges.gridx = 0;
            gbc_lbl_Conges.gridy = 3;
            this.panel_Google_Contenu_Calendars.add(this.getLbl_Conges(), gbc_lbl_Conges);
            final GridBagConstraints gbc_lbl_Repos = new GridBagConstraints();
            gbc_lbl_Repos.weighty = 1.0;
            gbc_lbl_Repos.anchor = 17;
            gbc_lbl_Repos.insets = new Insets(0, 0, 0, 5);
            gbc_lbl_Repos.gridx = 0;
            gbc_lbl_Repos.gridy = 4;
            this.panel_Google_Contenu_Calendars.add(this.getLbl_Repos(), gbc_lbl_Repos);
            final GridBagConstraints gbc_lbl_Absences = new GridBagConstraints();
            gbc_lbl_Absences.weighty = 1.0;
            gbc_lbl_Absences.anchor = 17;
            gbc_lbl_Absences.insets = new Insets(0, 0, 0, 5);
            gbc_lbl_Absences.gridx = 0;
            gbc_lbl_Absences.gridy = 5;
            this.panel_Google_Contenu_Calendars.add(this.getLbl_Absences(), gbc_lbl_Absences);
            final GridBagConstraints gbc_lbl_Dispersions = new GridBagConstraints();
            gbc_lbl_Dispersions.weighty = 1.0;
            gbc_lbl_Dispersions.anchor = 17;
            gbc_lbl_Dispersions.insets = new Insets(0, 0, 0, 5);
            gbc_lbl_Dispersions.gridx = 0;
            gbc_lbl_Dispersions.gridy = 6;
            this.panel_Google_Contenu_Calendars.add(this.getLbl_Dispersions(), gbc_lbl_Dispersions);
            final GridBagConstraints gbc_tfld_CalendarRotations = new GridBagConstraints();
            gbc_tfld_CalendarRotations.fill = 2;
            gbc_tfld_CalendarRotations.insets = new Insets(0, 0, 0, 5);
            gbc_tfld_CalendarRotations.gridx = 1;
            gbc_tfld_CalendarRotations.gridy = 0;
            this.panel_Google_Contenu_Calendars.add(this.getTfld_CalendarRotations(), gbc_tfld_CalendarRotations);
            final GridBagConstraints gbc_tfld_CalendarTroncons = new GridBagConstraints();
            gbc_tfld_CalendarTroncons.fill = 2;
            gbc_tfld_CalendarTroncons.insets = new Insets(0, 0, 0, 5);
            gbc_tfld_CalendarTroncons.gridx = 1;
            gbc_tfld_CalendarTroncons.gridy = 1;
            this.panel_Google_Contenu_Calendars.add(this.getTfld_CalendarTroncons(), gbc_tfld_CalendarTroncons);
            final GridBagConstraints gbc_tfld_CalendarActSol = new GridBagConstraints();
            gbc_tfld_CalendarActSol.fill = 2;
            gbc_tfld_CalendarActSol.insets = new Insets(0, 0, 0, 5);
            gbc_tfld_CalendarActSol.gridx = 1;
            gbc_tfld_CalendarActSol.gridy = 2;
            this.panel_Google_Contenu_Calendars.add(this.getTfld_CalendarActSol(), gbc_tfld_CalendarActSol);
            final GridBagConstraints gbc_tfld_CalendarConges = new GridBagConstraints();
            gbc_tfld_CalendarConges.fill = 2;
            gbc_tfld_CalendarConges.insets = new Insets(0, 0, 0, 5);
            gbc_tfld_CalendarConges.gridx = 1;
            gbc_tfld_CalendarConges.gridy = 3;
            this.panel_Google_Contenu_Calendars.add(this.getTfld_CalendarConges(), gbc_tfld_CalendarConges);
            final GridBagConstraints gbc_tfld_CalendarRepos = new GridBagConstraints();
            gbc_tfld_CalendarRepos.fill = 2;
            gbc_tfld_CalendarRepos.insets = new Insets(0, 0, 0, 5);
            gbc_tfld_CalendarRepos.gridx = 1;
            gbc_tfld_CalendarRepos.gridy = 4;
            this.panel_Google_Contenu_Calendars.add(this.getTfld_CalendarRepos(), gbc_tfld_CalendarRepos);
            final GridBagConstraints gbc_tfld_CalendarAbsences = new GridBagConstraints();
            gbc_tfld_CalendarAbsences.fill = 2;
            gbc_tfld_CalendarAbsences.insets = new Insets(0, 0, 0, 5);
            gbc_tfld_CalendarAbsences.gridx = 1;
            gbc_tfld_CalendarAbsences.gridy = 5;
            this.panel_Google_Contenu_Calendars.add(this.getTfld_CalendarAbsences(), gbc_tfld_CalendarAbsences);
            final GridBagConstraints gbc_tfld_CalendarDispersions = new GridBagConstraints();
            gbc_tfld_CalendarDispersions.fill = 2;
            gbc_tfld_CalendarDispersions.insets = new Insets(0, 0, 0, 5);
            gbc_tfld_CalendarDispersions.gridx = 1;
            gbc_tfld_CalendarDispersions.gridy = 6;
            this.panel_Google_Contenu_Calendars.add(this.getTfld_CalendarDispersions(), gbc_tfld_CalendarDispersions);
            final GridBagConstraints gbc_btnColor_Rotations = new GridBagConstraints();
            gbc_btnColor_Rotations.gridx = 2;
            gbc_btnColor_Rotations.gridy = 0;
            this.panel_Google_Contenu_Calendars.add(this.getBtnColor_Rotations(), gbc_btnColor_Rotations);
            final GridBagConstraints gbc_btnColor_Troncons = new GridBagConstraints();
            gbc_btnColor_Troncons.gridx = 2;
            gbc_btnColor_Troncons.gridy = 1;
            this.panel_Google_Contenu_Calendars.add(this.getBtnColor_Troncons(), gbc_btnColor_Troncons);
            final GridBagConstraints gbc_btnColor_ActSol = new GridBagConstraints();
            gbc_btnColor_ActSol.gridx = 2;
            gbc_btnColor_ActSol.gridy = 2;
            this.panel_Google_Contenu_Calendars.add(this.getBtnColor_ActSol(), gbc_btnColor_ActSol);
            final GridBagConstraints gbc_btnColor_Conges = new GridBagConstraints();
            gbc_btnColor_Conges.gridx = 2;
            gbc_btnColor_Conges.gridy = 3;
            this.panel_Google_Contenu_Calendars.add(this.getBtnColor_Conges(), gbc_btnColor_Conges);
            final GridBagConstraints gbc_btnColor_Repos = new GridBagConstraints();
            gbc_btnColor_Repos.gridx = 2;
            gbc_btnColor_Repos.gridy = 4;
            this.panel_Google_Contenu_Calendars.add(this.getBtnColor_Repos(), gbc_btnColor_Repos);
            final GridBagConstraints gbc_btnColor_Absences = new GridBagConstraints();
            gbc_btnColor_Absences.gridx = 2;
            gbc_btnColor_Absences.gridy = 5;
            this.panel_Google_Contenu_Calendars.add(this.getBtnColor_Absences(), gbc_btnColor_Absences);
            final GridBagConstraints gbc_btnColor_Dispersions = new GridBagConstraints();
            gbc_btnColor_Dispersions.gridx = 2;
            gbc_btnColor_Dispersions.gridy = 6;
            this.panel_Google_Contenu_Calendars.add(this.getBtnColor_Dispersions(), gbc_btnColor_Dispersions);
        }
        return this.panel_Google_Contenu_Calendars;
    }
    
    private Component getHorizontalStrut_Google_Login_1() {
        if (this.horizontalStrut_Google_Login_1 == null) {
            this.horizontalStrut_Google_Login_1 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Google_Login_1;
    }
    
    private Component getHorizontalStrut_Google_Contenu_Login_2() {
        if (this.horizontalStrut_Google_Contenu_Login_2 == null) {
            this.horizontalStrut_Google_Contenu_Login_2 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Google_Contenu_Login_2;
    }
    
    private JPanel getPnl_Google_Contenu() {
        if (this.pnl_Google_Contenu == null) {
            (this.pnl_Google_Contenu = new JPanel()).setLayout(new BoxLayout(this.pnl_Google_Contenu, 3));
            this.pnl_Google_Contenu.add(this.getVerticalStrut_Google_1());
            this.pnl_Google_Contenu.add(this.getPnl_Google_Contenu_Login());
            this.pnl_Google_Contenu.add(this.getVerticalStrut_Google_2());
            this.pnl_Google_Contenu.add(this.getLbl_Google_Contenu_Calendars());
            this.pnl_Google_Contenu.add(this.getVerticalStrut_Google_3());
            this.pnl_Google_Contenu.add(this.getPanel_Google_Contenu_Calendars());
        }
        return this.pnl_Google_Contenu;
    }
    
    private Component getHorizontalStrut_Google_Left() {
        if (this.horizontalStrut_Google_Left == null) {
            this.horizontalStrut_Google_Left = Box.createHorizontalStrut(8);
        }
        return this.horizontalStrut_Google_Left;
    }
    
    private Component getHorizontalStrut_Google_Right() {
        if (this.horizontalStrut_Google_Right == null) {
            this.horizontalStrut_Google_Right = Box.createHorizontalStrut(8);
        }
        return this.horizontalStrut_Google_Right;
    }
    
    private Component getVerticalStrut_Reset_1() {
        if (this.verticalStrut_Reset_1 == null) {
            this.verticalStrut_Reset_1 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Reset_1;
    }
    
    private Component getVerticalStrut_Reset_2() {
        if (this.verticalStrut_Reset_2 == null) {
            this.verticalStrut_Reset_2 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Reset_2;
    }
    
    private Component getVerticalGlue_OptionsBasiques_Left_Bottom() {
        if (this.verticalGlue_OptionsBasiques_Left_Bottom == null) {
            this.verticalGlue_OptionsBasiques_Left_Bottom = Box.createVerticalGlue();
        }
        return this.verticalGlue_OptionsBasiques_Left_Bottom;
    }
    
    private Component getVerticalGlue_OptionsBasiques_Right_Bottom() {
        if (this.verticalGlue_OptionsBasiques_Right_Bottom == null) {
            this.verticalGlue_OptionsBasiques_Right_Bottom = Box.createVerticalGlue();
        }
        return this.verticalGlue_OptionsBasiques_Right_Bottom;
    }
    
    private Component getVerticalStrut_OptionsBasiques_Left_Top() {
        if (this.verticalStrut_OptionsBasiques_Left_Top == null) {
            this.verticalStrut_OptionsBasiques_Left_Top = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_OptionsBasiques_Left_Top;
    }
    
    private Component getVerticalStrut_OptionsBasiques_Right_Top() {
        if (this.verticalStrut_OptionsBasiques_Right_Top == null) {
            this.verticalStrut_OptionsBasiques_Right_Top = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_OptionsBasiques_Right_Top;
    }
    
    private Component getVerticalStrut_OptionsAvancees_Left_Top() {
        if (this.verticalStrut_OptionsAvancees_Left_Top == null) {
            this.verticalStrut_OptionsAvancees_Left_Top = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_OptionsAvancees_Left_Top;
    }
    
    private Component getVerticalStrut_OptionsAvancees_Right_Top() {
        if (this.verticalStrut_OptionsAvancees_Right_Top == null) {
            this.verticalStrut_OptionsAvancees_Right_Top = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_OptionsAvancees_Right_Top;
    }
    
    private Component getVerticalStrut_PDF_Top() {
        if (this.verticalStrut_PDF_Top == null) {
            this.verticalStrut_PDF_Top = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_PDF_Top;
    }
    
    private Component getVerticalGlue_PDF_Bottom() {
        if (this.verticalGlue_PDF_Bottom == null) {
            this.verticalGlue_PDF_Bottom = Box.createVerticalGlue();
        }
        return this.verticalGlue_PDF_Bottom;
    }
    
    private Component getHorizontalStrut_OptionsBasiques() {
        if (this.horizontalStrut_OptionsBasiques == null) {
            this.horizontalStrut_OptionsBasiques = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_OptionsBasiques;
    }
    
    private Component getVerticalStrut_Google_2() {
        if (this.verticalStrut_Google_2 == null) {
            this.verticalStrut_Google_2 = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_Google_2;
    }
    
    private Component getVerticalStrut_Google_1() {
        if (this.verticalStrut_Google_1 == null) {
            this.verticalStrut_Google_1 = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_Google_1;
    }
    
    private Component getVerticalStrut_Google_3() {
        if (this.verticalStrut_Google_3 == null) {
            this.verticalStrut_Google_3 = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_Google_3;
    }
    
    private Component getVerticalStrut_NoticesAF_2() {
        if (this.verticalStrut_NoticesAF_2 == null) {
            this.verticalStrut_NoticesAF_2 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_NoticesAF_2;
    }
    
    private Component getVerticalStrut_NoticesCE_2() {
        if (this.verticalStrut_NoticesCE_2 == null) {
            this.verticalStrut_NoticesCE_2 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_NoticesCE_2;
    }
    
    private Component getVerticalStrut_NoticesCE_3() {
        if (this.verticalStrut_NoticesCE_3 == null) {
            this.verticalStrut_NoticesCE_3 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_NoticesCE_3;
    }
    
    private Component getVerticalStrut_Notices_Left_Top() {
        if (this.verticalStrut_Notices_Left_Top == null) {
            this.verticalStrut_Notices_Left_Top = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_Notices_Left_Top;
    }
    
    private Component getVerticalGlue_Notices_Left_Bottom() {
        if (this.verticalGlue_Notices_Left_Bottom == null) {
            this.verticalGlue_Notices_Left_Bottom = Box.createVerticalGlue();
        }
        return this.verticalGlue_Notices_Left_Bottom;
    }
    
    private Component getVerticalStrut_Notices_Right_Top() {
        if (this.verticalStrut_Notices_Right_Top == null) {
            this.verticalStrut_Notices_Right_Top = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_Notices_Right_Top;
    }
    
    private Component getVerticalGlue_Notices_Right_Bottom() {
        if (this.verticalGlue_Notices_Right_Bottom == null) {
            this.verticalGlue_Notices_Right_Bottom = Box.createVerticalGlue();
        }
        return this.verticalGlue_Notices_Right_Bottom;
    }
    
    private Component getVerticalStrut_NoticesAF_1() {
        if (this.verticalStrut_NoticesAF_1 == null) {
            this.verticalStrut_NoticesAF_1 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_NoticesAF_1;
    }
    
    private Component getVerticalStrut_NoticesCE_1() {
        if (this.verticalStrut_NoticesCE_1 == null) {
            this.verticalStrut_NoticesCE_1 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_NoticesCE_1;
    }
    
    private Component getVerticalStrut_NoticesCE_4() {
        if (this.verticalStrut_NoticesCE_4 == null) {
            this.verticalStrut_NoticesCE_4 = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_NoticesCE_4;
    }
    
    static /* synthetic */ void access$31(final Dlg_ExportOptions dlg_ExportOptions, final String[] agendasDispos) {
        dlg_ExportOptions.agendasDispos = agendasDispos;
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_ExportOptions.this.btn_Valider) {
                Dlg_ExportOptions.this.actionBtnValider();
            }
            else if (e.getSource() == Dlg_ExportOptions.this.btn_Annuler) {
                Dlg_ExportOptions.this.actionBtnAnnuler();
            }
            else if (e.getSource() == Dlg_ExportOptions.this.btn_Reset) {
                Dlg_ExportOptions.this.actionBtnReset();
            }
            else if (e.getSource() == Dlg_ExportOptions.this.cbox_GoogleAuto) {
                Dlg_ExportOptions.this.actionCbxGoogleAuto();
            }
            else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarRotations) {
                Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarRotations);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarTroncons) {
                Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarTroncons);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarActSol) {
                Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarActSol);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarConges) {
                Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarConges);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarRepos) {
                Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarRepos);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarAbsences) {
                Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarAbsences);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarDispersions) {
                Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarDispersions);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Rotations) {
                Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Rotations);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Troncons) {
                Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Troncons);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.btnColor_ActSol) {
                Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_ActSol);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Conges) {
                Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Conges);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Repos) {
                Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Repos);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Absences) {
                Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Absences);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Dispersions) {
                Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Dispersions);
            }
            else if (e.getSource() == Dlg_ExportOptions.this.cbx_NoticesAF_Auto) {
                Dlg_ExportOptions.this.actionCbxNoticesAF(Dlg_ExportOptions.this.cbx_NoticesAF_Auto.isSelected());
            }
            else if (e.getSource() == Dlg_ExportOptions.this.cbx_NoticesCE_Auto) {
                Dlg_ExportOptions.this.actionCbxNoticesCE(Dlg_ExportOptions.this.cbx_NoticesCE_Auto.isSelected());
            }
            else if (e.getSource() == Dlg_ExportOptions.this.cbx_Save_Auto) {
                Dlg_ExportOptions.this.actionCbxSaveAuto(Dlg_ExportOptions.this.cbx_Save_Auto.isSelected());
            }
        }
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10) {
                if (e.getSource() == Dlg_ExportOptions.this.btn_Valider) {
                    Dlg_ExportOptions.this.actionBtnValider();
                }
                else if (e.getSource() == Dlg_ExportOptions.this.btn_Annuler) {
                    Dlg_ExportOptions.this.actionBtnAnnuler();
                }
                else if (e.getSource() == Dlg_ExportOptions.this.btn_Reset) {
                    Dlg_ExportOptions.this.actionBtnReset();
                }
                else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarRotations) {
                    Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarRotations);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarTroncons) {
                    Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarTroncons);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarActSol) {
                    Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarActSol);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarConges) {
                    Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarConges);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarRepos) {
                    Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarRepos);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarAbsences) {
                    Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarAbsences);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.tfld_CalendarDispersions) {
                    Dlg_ExportOptions.this.actionTfldCalendar(Dlg_ExportOptions.this.tfld_CalendarDispersions);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Rotations) {
                    Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Rotations);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Troncons) {
                    Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Troncons);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.btnColor_ActSol) {
                    Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_ActSol);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Conges) {
                    Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Conges);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Repos) {
                    Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Repos);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Absences) {
                    Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Absences);
                }
                else if (e.getSource() == Dlg_ExportOptions.this.btnColor_Dispersions) {
                    Dlg_ExportOptions.this.actionBtnColor(Dlg_ExportOptions.this, Dlg_ExportOptions.this.btnColor_Dispersions);
                }
                else if (e.getSource() instanceof JCheckBox) {
                    ((JCheckBox)e.getSource()).doClick();
                    if (e.getSource() == Dlg_ExportOptions.this.cbox_GoogleAuto) {
                        Dlg_ExportOptions.this.actionCbxGoogleAuto();
                    }
                    else if (e.getSource() == Dlg_ExportOptions.this.cbx_NoticesAF_Auto) {
                        Dlg_ExportOptions.this.actionCbxNoticesAF(Dlg_ExportOptions.this.cbx_NoticesAF_Auto.isSelected());
                    }
                    else if (e.getSource() == Dlg_ExportOptions.this.cbx_NoticesCE_Auto) {
                        Dlg_ExportOptions.this.actionCbxNoticesCE(Dlg_ExportOptions.this.cbx_NoticesCE_Auto.isSelected());
                    }
                    else if (e.getSource() == Dlg_ExportOptions.this.cbx_Save_Auto) {
                        Dlg_ExportOptions.this.actionCbxSaveAuto(Dlg_ExportOptions.this.cbx_Save_Auto.isSelected());
                    }
                }
                else if (e.getSource() instanceof JRadioButton) {
                    ((JRadioButton)e.getSource()).doClick();
                }
            }
        }
    }
}
