// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.Box;
import java.net.URISyntaxException;
import java.io.IOException;
import java.awt.Desktop;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ccImport.ConnexionRequeteur;
import ccImport.ConnexionCrew;
import java.util.Observable;
import ccStructures.ActiviteSol;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import java.util.Iterator;
import ccStructures.Rotation;
import javax.swing.JFileChooser;
import ccExport.Export_Ical;
import java.awt.Frame;
import ccExport.Export_XML;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import ccUtils.Utils;
import javax.swing.JOptionPane;
import java.io.File;
import ccUtils.Constantes;
import java.awt.Color;
import ccUtils.CustomFileChooser;
import ccImport.ConnexionCE;
import ccImport.ConnexionEdito;
import ccExport.Export_NightStop3;
import ccExport.Export_GoogleAgendaV3;
import ccImport.ConnexionAF;
import ccGUI.RosterTable.RosterTableModel;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.awt.CardLayout;
import javax.swing.SwingUtilities;
import chopeCrew.ChopeCrew;
import java.awt.event.WindowListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JToggleButton;
import ccGUI.RosterTable.RosterTable;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JPanel;
import java.util.Observer;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements Observer
{
    private static final long serialVersionUID = 1L;
    private boolean isOnline;
    private JPanel jContentPane;
    private JPanel panel_west;
    private JPanel panel_east;
    private JPanel panel_center;
    private Component verticalStrut_North;
    private Component verticalStrut_South;
    private Component horizontalStrut_WestLeft;
    private JPanel pnl_West;
    private Component horizontalStrut_WestRight;
    private Component horizontalStrut_EastLeft;
    private JPanel pnl_East;
    private Component horizontalStrut_EastRight;
    private JLabel lab_Banner;
    private JPanel pnl_Authentification;
    private JLabel lab_Login;
    private JLabel lab_Password;
    private JTextField tfld_Login;
    private JPasswordField pwdf_Password;
    private JPanel pnl_Importation;
    private JPanel pnl_Connect;
    private JPanel pnl_Connect_100;
    private JPanel pnl_Connect_Ins;
    private JButton btn_ChopeM0_100;
    private JButton btn_ChopeM1_100;
    private JButton btn_ChopeM0_Ins;
    private JButton btn_ChopeM1_Ins;
    private JButton btn_ChopeM2_Ins;
    private JPanel pnl_Status;
    private JProgressBar prb_ProgressStatus;
    private JPanel pnl_NormFlash;
    private JLabel lab_Norm;
    private JLabel lab_Flash;
    private JPanel pnl_HorsLigne;
    private JButton btn_Charger;
    private JButton btn_Sauver;
    private JPanel pnl_Footer;
    private JButton btn_Aide;
    private JButton btn_Profil;
    private JButton btn_Configuration;
    private JPanel pnl_Exportation;
    private JButton btn_ExportGoogle;
    private JProgressBar prb_ProgressGoogle;
    private JButton btn_ExportNightStop;
    private JProgressBar prb_ProgressNightStop;
    private JButton btn_ExportPDA;
    private JButton btn_ExportPDF;
    private JPanel pnl_Notices;
    private JButton btn_ExportNoticesAF;
    private JProgressBar prb_ProgressNoticesAF;
    private JButton btn_ExportNoticesCE;
    private JProgressBar prb_ProgressNoticesCE;
    private JPanel pnl_ActivitePaye;
    private JButton btn_Resume;
    private JButton btn_Ep4;
    private JButton btn_Stats;
    private JPanel pnl_RosterAndDetails;
    private JPanel pnl_Details;
    private JLabel lab_DetailsHeader;
    private JScrollPane scrollPane_Details;
    private JTextPane txtp_Details;
    private JPanel pnl_Roster;
    private JLabel lab_RosterHeader;
    private JScrollPane scrollPane_Roster;
    private RosterTable rosterTable;
    private JPanel pnl_Filtres;
    private JButton btn_Blocs;
    private JButton btn_Req;
    private JButton btn_GoogleM0;
    private JButton btn_GoogleM1;
    private JToggleButton tbtn_RosterTableRetracted;
    private JToggleButton tbtn_RosterTableReal;
    private JToggleButton tbtn_RosterTableLocal;
    private Component verticalGlue_W1;
    private Component verticalGlue_W2;
    private Component verticalGlue_W3;
    private Component verticalGlue_W4;
    private Component verticalGlue_E1;
    private Component verticalGlue_E2;
    private Component horizontalStrut_Footer1;
    private Component horizontalStrut_Footer2;
    private Component verticalStrut_Exportation1;
    private Component verticalStrut_Exportation2;
    private Component verticalStrut_Notices1;
    private Component horizontalGlue_Filtres1;
    private Component verticalStrut;
    private Component verticalStrut_1;
    private Component verticalStrut_2;
    private Component verticalStrut_3;
    private Component verticalStrut_4;
    private Component verticalStrut_5;
    private Component verticalStrut_6;
    private Component verticalStrut_7;
    private Component verticalStrut_8;
    private Component verticalStrut_9;
    private Component verticalStrut_10;
    private Component verticalStrut_11;
    private Component verticalStrut_12;
    
    public MainFrame() {
        this.isOnline = false;
        this.jContentPane = null;
        this.pnl_Authentification = null;
        this.lab_Login = null;
        this.lab_Password = null;
        this.tfld_Login = null;
        this.pwdf_Password = null;
        this.pnl_Importation = null;
        this.btn_ChopeM0_100 = null;
        this.btn_ChopeM1_100 = null;
        this.btn_ChopeM0_Ins = null;
        this.btn_ChopeM1_Ins = null;
        this.btn_ChopeM2_Ins = null;
        this.lab_Norm = null;
        this.lab_Flash = null;
        this.pnl_HorsLigne = null;
        this.btn_Charger = null;
        this.btn_Sauver = null;
        this.btn_Aide = null;
        this.btn_Profil = null;
        this.btn_Configuration = null;
        this.pnl_Exportation = null;
        this.btn_ExportGoogle = null;
        this.prb_ProgressGoogle = null;
        this.btn_ExportNightStop = null;
        this.prb_ProgressNightStop = null;
        this.btn_ExportPDA = null;
        this.btn_ExportPDF = null;
        this.btn_ExportNoticesAF = null;
        this.prb_ProgressNoticesAF = null;
        this.btn_ExportNoticesCE = null;
        this.prb_ProgressNoticesCE = null;
        this.pnl_ActivitePaye = null;
        this.btn_Resume = null;
        this.btn_Ep4 = null;
        this.btn_Stats = null;
        this.pnl_Filtres = null;
        this.btn_Blocs = null;
        this.btn_Req = null;
        this.btn_GoogleM0 = null;
        this.btn_GoogleM1 = null;
        this.tbtn_RosterTableRetracted = null;
        this.tbtn_RosterTableReal = null;
        this.tbtn_RosterTableLocal = null;
        this.initialize();
    }
    
    private void initialize() {
        final Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        final int hauteur = (int)tailleEcran.getHeight();
        final int largeur = (int)tailleEcran.getWidth();
        System.out.println(String.valueOf(largeur) + "  " + hauteur);
        this.setSize(960, 640);
        this.setPreferredSize(new Dimension(960, 640));
        this.setResizable(false);
        this.setTitle("C h o p e C R E W");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/res_images/ImgIconeChopeCrew.png")));
        this.setLocationRelativeTo(null);
        this.setContentPane(this.getJContentPane());
        this.addWindowListener(new MyWindowAdapter());
        this.updatePanelImportation();
        this.updateMonthLabels();
        this.updateToggleButtons();
        this.setVisible(true);
        final Runnable run = new Runnable() {
            @Override
            public void run() {
                if (!ChopeCrew.options.loginIpn.equals("")) {
                    MainFrame.this.tfld_Login.setText(ChopeCrew.options.loginIpn);
                    MainFrame.this.pwdf_Password.requestFocusInWindow();
                }
                else {
                    MainFrame.this.tfld_Login.requestFocusInWindow();
                }
            }
        };
        SwingUtilities.invokeLater(run);
    }
    
    private void updatePanelImportation() {
        final CardLayout cl = (CardLayout)this.getPnl_Connect().getLayout();
        switch (ChopeCrew.donneesPerso_PNT.fonction_pnt) {
            case 2: {
                cl.show(this.getPnl_Connect(), "CONNECT_LAYOUT_INS");
                break;
            }
            case 3: {
                cl.show(this.getPnl_Connect(), "CONNECT_LAYOUT_INS");
                break;
            }
            default: {
                cl.show(this.getPnl_Connect(), "CONNECT_LAYOUT_100");
                break;
            }
        }
        this.pnl_Connect.repaint();
    }
    
    private void updateMonthLabels() {
        final TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        final Calendar cal = new GregorianCalendar(timeZone);
        final DateFormatSymbols dfsMois = new DateFormatSymbols();
        final String[] mois = dfsMois.getMonths();
        for (int i = 0; i < mois.length; ++i) {
            if (mois[i].length() != 0) {
                final String result = String.valueOf(mois[i].substring(0, 1).toUpperCase()) + mois[i].substring(1);
                mois[i] = result;
            }
        }
        dfsMois.setMonths(mois);
        final SimpleDateFormat sdf = new SimpleDateFormat("MMMM", dfsMois);
        this.btn_ChopeM0_100.setText(sdf.format(cal.getTime()));
        this.btn_ChopeM0_Ins.setText(sdf.format(cal.getTime()));
        cal.add(2, 1);
        this.btn_ChopeM1_100.setText(sdf.format(cal.getTime()));
        this.btn_ChopeM1_Ins.setText(sdf.format(cal.getTime()));
        cal.add(2, 1);
        this.btn_ChopeM2_Ins.setText(sdf.format(cal.getTime()));
    }
    
    private void updateToggleButtons() {
        if (ChopeCrew.options.isRosterTableReal) {
            this.tbtn_RosterTableReal.setSelected(true);
            this.tbtn_RosterTableReal.setText("REAL.");
        }
        else {
            this.tbtn_RosterTableReal.setSelected(false);
            this.tbtn_RosterTableReal.setText("PROG.");
        }
        if (ChopeCrew.options.isRosterTableLocal) {
            this.tbtn_RosterTableLocal.setSelected(true);
            this.tbtn_RosterTableLocal.setText("LOC.");
        }
        else {
            this.tbtn_RosterTableLocal.setSelected(false);
            this.tbtn_RosterTableLocal.setText("PAR.");
        }
        if (ChopeCrew.options.isRosterTableRetracted) {
            this.tbtn_RosterTableRetracted.setSelected(true);
            this.tbtn_RosterTableRetracted.setText("-");
        }
        else {
            this.tbtn_RosterTableRetracted.setSelected(false);
            this.tbtn_RosterTableRetracted.setText("+");
        }
    }
    
    public void showDetails(final String title, final String content) {
        this.lab_DetailsHeader.setText(title);
        this.txtp_Details.setText(content);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame.this.scrollPane_Details.getVerticalScrollBar().setValue(0);
            }
        });
        final CardLayout cl = (CardLayout)this.pnl_RosterAndDetails.getLayout();
        cl.show(this.pnl_RosterAndDetails, "card_details");
    }
    
    public void showRoster() {
        this.lab_RosterHeader.setText(String.valueOf(ChopeCrew.analyse.moisLit) + " " + ChopeCrew.analyse.anneeInt);
        ((RosterTableModel)this.rosterTable.getModel()).loadRosterTable(ChopeCrew.analyse);
        final CardLayout cl = (CardLayout)this.pnl_RosterAndDetails.getLayout();
        cl.show(this.pnl_RosterAndDetails, "card_roster");
    }
    
    private void importBlocs() {
        if (!ChopeCrew.options.isMajAuto) {
            return;
        }
        this.btn_Blocs.setEnabled(false);
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final ConnexionAF connexionAF = new ConnexionAF(ChopeCrew.analyse);
                connexionAF.addObserver(MainFrame.this);
                connexionAF.chopeBlocAF(ChopeCrew.analyse);
            }
        };
        thrCon.start();
    }
    
    private void exportGoogleAgenda() {
        if (!ChopeCrew.options.isGoogleAuto) {
            return;
        }
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final Export_GoogleAgendaV3 gCal = new Export_GoogleAgendaV3();
                gCal.addObserver(MainFrame.this);
                gCal.sendPlanning(ChopeCrew.analyse, ChopeCrew.options.loginGoogle, ChopeCrew.options.calendarsRotations, ChopeCrew.options.calendarsTroncons, ChopeCrew.options.calendarsActSol, ChopeCrew.options.calendarsConges, ChopeCrew.options.calendarsRepos, ChopeCrew.options.calendarsAbsences, ChopeCrew.options.calendarsDispersions);
            }
        };
        thrCon.start();
    }
    
    private void exportNightStop() {
        if (!ChopeCrew.options.isNightStopAuto) {
            return;
        }
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final Export_NightStop3 nsCal = new Export_NightStop3();
                nsCal.addObserver(MainFrame.this);
                nsCal.sendPlanning(ChopeCrew.analyse, ChopeCrew.options.loginNightStop, ChopeCrew.options.passNightStop);
            }
        };
        thrCon.start();
    }
    
    private void exportNoticesAF() {
        if (!ChopeCrew.options.isNoticesAFAuto) {
            return;
        }
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final ConnexionEdito connexionEdito = new ConnexionEdito();
                connexionEdito.addObserver(MainFrame.this);
                connexionEdito.chopeNoticesAF(ChopeCrew.analyse, ChopeCrew.options.loginIpn, ChopeCrew.options.passIpn, ChopeCrew.options.urlIPN, ChopeCrew.options.repNoticesAF, ChopeCrew.options.isNoticesAFTotal);
            }
        };
        thrCon.start();
    }
    
    private void exportNoticesCE() {
        if (!ChopeCrew.options.isNoticesCEAuto) {
            return;
        }
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final ConnexionCE connexionCE = new ConnexionCE();
                connexionCE.addObserver(MainFrame.this);
                connexionCE.chopeNoticesCE(ChopeCrew.analyse, ChopeCrew.options.repNoticesCE, ChopeCrew.options.isNoticesCETotal);
            }
        };
        thrCon.start();
    }
    
    private int actionLogoClicGauche(final boolean modeFlash) {
        final CustomFileChooser fc = new CustomFileChooser("txt", false);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return -1;
        }
        this.btn_ChopeM0_100.setEnabled(false);
        this.btn_ChopeM1_100.setEnabled(false);
        this.btn_ChopeM0_Ins.setEnabled(false);
        this.btn_ChopeM1_Ins.setEnabled(false);
        this.btn_ChopeM2_Ins.setEnabled(false);
        this.lab_Norm.setForeground(Color.ORANGE);
        this.lab_Flash.setForeground(Color.ORANGE);
        this.prb_ProgressStatus.setForeground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
        this.prb_ProgressStatus.setValue(0);
        ChopeCrew.options.isModeFlash = modeFlash;
        ChopeCrew.options.repTXT = returnFile.getParent();
        this.isOnline = false;
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                ChopeCrew.conCrew.addObserver(MainFrame.this);
                ChopeCrew.conCrew.chope(returnFile.getAbsolutePath(), ChopeCrew.options.isModeFlash, ChopeCrew.options.loginIpn, ChopeCrew.options.passIpn, ChopeCrew.options.urlIPN);
            }
        };
        thrCon.start();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionLogoClicDroit() {
        if (ChopeCrew.conCrew.getPageMensuelle() == null) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune source planning \u00e0 sauvegarder", "ChopeCREW vous informe", -1);
            return 0;
        }
        final CustomFileChooser fc = new CustomFileChooser("txt", true);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return -1;
        }
        ChopeCrew.options.repTXT = returnFile.getParent();
        Utils.saveToFile(ChopeCrew.conCrew.getSourceAsString(), returnFile.getAbsolutePath(), "UTF-8");
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnChope(final int deltaMois, final boolean modeFlash) {
        String login = this.tfld_Login.getText().trim().toLowerCase();
        if (!login.contains("m")) {
            login = "m" + login;
        }
        if (login.length() > 7) {
            login = login.substring(0, 7);
        }
        this.tfld_Login.setText(login);
        this.btn_ChopeM0_100.setEnabled(false);
        this.btn_ChopeM1_100.setEnabled(false);
        this.btn_ChopeM0_Ins.setEnabled(false);
        this.btn_ChopeM1_Ins.setEnabled(false);
        this.btn_ChopeM2_Ins.setEnabled(false);
        this.lab_Norm.setForeground(Color.ORANGE);
        this.lab_Flash.setForeground(Color.ORANGE);
        this.prb_ProgressStatus.setForeground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
        this.prb_ProgressStatus.setValue(0);
        ChopeCrew.options.loginIpn = login;
        ChopeCrew.options.passIpn = String.valueOf(this.pwdf_Password.getPassword());
        ChopeCrew.options.periodImport = deltaMois;
        ChopeCrew.options.isModeFlash = modeFlash;
        ChopeCrew.isKeepAliving = false;
        this.isOnline = true;
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                ChopeCrew.conCrew.addObserver(MainFrame.this);
                ChopeCrew.conCrew.chope(ChopeCrew.options.periodImport, ChopeCrew.options.isModeFlash, ChopeCrew.options.loginIpn, ChopeCrew.options.passIpn, ChopeCrew.options.urlIPN);
            }
        };
        thrCon.start();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnChargeXML() {
        final CustomFileChooser fc = new CustomFileChooser("xml", false);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return -1;
        }
        ChopeCrew.options.repXML = returnFile.getParent();
        try {
            final InputStream is = new FileInputStream(returnFile);
            final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            final StringBuilder sb = new StringBuilder();
            String tmp;
            while ((tmp = in.readLine()) != null) {
                sb.append(tmp).append(System.getProperty("line.separator"));
            }
            in.close();
            is.close();
            ChopeCrew.analyse.chargeAnalyseCrewFromXml(sb.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            this.lab_Norm.setForeground(Color.GRAY);
            this.lab_Flash.setForeground(Color.GRAY);
            this.prb_ProgressStatus.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
            this.prb_ProgressStatus.setValue(0);
            this.prb_ProgressStatus.setString("Recommencez");
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Fichier invalide", "ChopeCREW vous informe", -1);
            return 0;
        }
        this.updateExportability();
        this.updateRotationsExpandState();
        this.showRoster();
        this.importBlocs();
        if (ChopeCrew.analyse.isFlash) {
            this.lab_Norm.setForeground(Color.GRAY);
            this.lab_Flash.setForeground(Color.BLUE);
        }
        else {
            this.lab_Norm.setForeground(Color.GREEN);
            this.lab_Flash.setForeground(Color.GRAY);
        }
        this.btn_Sauver.setForeground(null);
        this.prb_ProgressStatus.setForeground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
        this.prb_ProgressStatus.setValue(this.prb_ProgressStatus.getMaximum());
        this.prb_ProgressStatus.setString("Planning import\u00e9 !");
        this.btn_Blocs.setText("M\u00e0J");
        this.prb_ProgressGoogle.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
        this.prb_ProgressNoticesCE.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
        this.prb_ProgressGoogle.setValue(0);
        this.prb_ProgressNoticesCE.setValue(0);
        this.prb_ProgressGoogle.setString(" ");
        this.prb_ProgressNoticesCE.setString(" ");
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnSauveXML() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucun planning \u00e0 sauvegarder", "ChopeCREW vous informe", -1);
            return 0;
        }
        final CustomFileChooser fc = new CustomFileChooser("xml", true);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return -1;
        }
        ChopeCrew.options.repXML = returnFile.getParent();
        final Export_XML xml = new Export_XML();
        xml.createXML(ChopeCrew.analyse);
        Utils.saveToFile(xml.getXml(), returnFile.getAbsolutePath(), "UTF8");
        this.btn_Sauver.setForeground(null);
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnAide() {
        final Dlg_Aide dlgAide = new Dlg_Aide(ChopeCrew.mf);
        dlgAide.setLocationRelativeTo(this);
        dlgAide.setVisible(true);
        return 1;
    }
    
    private int actionBtnProfil() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucun planning charg\u00e9 : op\u00e9ration impossible", "ChopeCREW vous informe", -1);
            return 0;
        }
        if (ChopeCrew.analyse.isPNT) {
            final Dlg_DonneesPerso_PNT dlgDonneesPersoPNT = new Dlg_DonneesPerso_PNT(ChopeCrew.mf);
            dlgDonneesPersoPNT.setLocationRelativeTo(this);
            dlgDonneesPersoPNT.setVisible(true);
        }
        else if (!ChopeCrew.analyse.isPNT) {
            final Dlg_DonneesPerso_PNC dlgDonneesPersoPNC = new Dlg_DonneesPerso_PNC(ChopeCrew.mf);
            dlgDonneesPersoPNC.setLocationRelativeTo(this);
            dlgDonneesPersoPNC.setVisible(true);
        }
        ChopeCrew.analyse.updateAnalyseCrew();
        this.updatePanelImportation();
        this.showRoster();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnConfiguration() {
        final Dlg_ExportOptions dlgExpOpt = new Dlg_ExportOptions(ChopeCrew.mf);
        dlgExpOpt.setLocationRelativeTo(this);
        dlgExpOpt.setVisible(true);
        if (dlgExpOpt.optionsExportChanged) {
            this.updateExportability();
            this.showRoster();
        }
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnExportGoogleAgenda() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune donn\u00e9e \u00e0 exporter", "ChopeCREW vous informe", -1);
            return 0;
        }
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                MainFrame.this.prb_ProgressGoogle.setString(" ");
                MainFrame.this.prb_ProgressGoogle.setForeground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
                MainFrame.this.prb_ProgressGoogle.setValue(0);
                final Export_GoogleAgendaV3 gCal = new Export_GoogleAgendaV3();
                gCal.addObserver(MainFrame.this);
                gCal.sendPlanning(ChopeCrew.analyse, ChopeCrew.options.loginGoogle, ChopeCrew.options.calendarsRotations, ChopeCrew.options.calendarsTroncons, ChopeCrew.options.calendarsActSol, ChopeCrew.options.calendarsConges, ChopeCrew.options.calendarsRepos, ChopeCrew.options.calendarsAbsences, ChopeCrew.options.calendarsDispersions);
            }
        };
        thrCon.start();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnExportNightStop() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune donn\u00e9e \u00e0 exporter", "ChopeCREW vous informe", -1);
            return 0;
        }
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                MainFrame.this.prb_ProgressNightStop.setString(" ");
                MainFrame.this.prb_ProgressNightStop.setForeground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
                MainFrame.this.prb_ProgressNightStop.setValue(0);
                final Export_NightStop3 nsCal = new Export_NightStop3();
                nsCal.addObserver(MainFrame.this);
                nsCal.sendPlanning(ChopeCrew.analyse, ChopeCrew.options.loginNightStop, ChopeCrew.options.passNightStop);
            }
        };
        thrCon.start();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnPDA() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune donn\u00e9e \u00e0 exporter", "ChopeCREW vous informe", -1);
            return 0;
        }
        final CustomFileChooser fc = new CustomFileChooser("ics", true);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return -1;
        }
        ChopeCrew.options.repICS = returnFile.getParent();
        final Export_Ical ical = new Export_Ical();
        ical.createICal(ChopeCrew.analyse);
        Utils.saveToFile(ical.getICal(), returnFile.getAbsolutePath(), "UTF8");
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnWEB() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune donn\u00e9e \u00e0 exporter", "ChopeCREW vous informe", -1);
            return 0;
        }
        final Dlg_ApercuPlanning dlgPlanning = new Dlg_ApercuPlanning(ChopeCrew.mf);
        dlgPlanning.setLocationRelativeTo(this);
        dlgPlanning.setVisible(true);
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnExportNoticesAF() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune donn\u00e9e \u00e0 exporter", "ChopeCREW vous informe", -1);
            return 0;
        }
        String login = this.tfld_Login.getText().trim().toLowerCase();
        if (!login.contains("m")) {
            login = "m" + login;
        }
        if (login.length() > 7) {
            login = login.substring(0, 7);
        }
        ChopeCrew.options.loginIpn = login;
        ChopeCrew.options.passIpn = String.valueOf(this.pwdf_Password.getPassword());
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Choix du r\u00e9pertoire de sauvegarde des notices AF");
                fc.setFileSelectionMode(1);
                fc.setCurrentDirectory(new File(ChopeCrew.options.repAF));
                final int returnVal = fc.showSaveDialog(MainFrame.this);
                if (returnVal == 1) {
                    return;
                }
                ChopeCrew.options.repAF = fc.getSelectedFile().getAbsolutePath();
                final ConnexionEdito connexionEdito = new ConnexionEdito();
                connexionEdito.addObserver(MainFrame.this);
                MainFrame.this.prb_ProgressNoticesAF.setString(" ");
                MainFrame.this.prb_ProgressNoticesAF.setForeground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
                MainFrame.this.prb_ProgressNoticesAF.setValue(0);
                connexionEdito.chopeNoticesAF(ChopeCrew.analyse, ChopeCrew.options.loginIpn, ChopeCrew.options.passIpn, ChopeCrew.options.urlIPN, ChopeCrew.options.repAF, ChopeCrew.options.isNoticesAFTotal);
            }
        };
        thrCon.start();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnExportNoticesCE() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune donn\u00e9e \u00e0 exporter", "ChopeCREW vous informe", -1);
            return 0;
        }
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("Choix du r\u00e9pertoire de sauvegarde des notices CE");
                fc.setFileSelectionMode(1);
                fc.setCurrentDirectory(new File(ChopeCrew.options.repCE));
                final int returnVal = fc.showSaveDialog(MainFrame.this);
                if (returnVal == 1) {
                    return;
                }
                ChopeCrew.options.repCE = fc.getSelectedFile().getAbsolutePath();
                final ConnexionCE connexionCE = new ConnexionCE();
                connexionCE.addObserver(MainFrame.this);
                MainFrame.this.prb_ProgressNoticesCE.setString(" ");
                MainFrame.this.prb_ProgressNoticesCE.setForeground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
                MainFrame.this.prb_ProgressNoticesCE.setValue(0);
                connexionCE.chopeNoticesCE(ChopeCrew.analyse, ChopeCrew.options.repCE, ChopeCrew.options.isNoticesCETotal);
            }
        };
        thrCon.start();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnResume() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune donn\u00e9e \u00e0 afficher", "ChopeCREW vous informe", -1);
            return 0;
        }
        final Dlg_Resume dlgResume = new Dlg_Resume(ChopeCrew.mf);
        dlgResume.setLocationRelativeTo(this);
        dlgResume.setVisible(true);
        ChopeCrew.options.savePreferences();
        ChopeCrew.donneesPerso_PNT.savePreferences();
        ChopeCrew.donneesPerso_PNC.savePreferences();
        return 1;
    }
    
    private int actionBtnEp4() {
        if (ChopeCrew.analyse.listCrew.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune donn\u00e9e \u00e0 afficher", "ChopeCREW vous informe", -1);
            return 0;
        }
        final Dlg_Ep4 dlgEp4 = new Dlg_Ep4(ChopeCrew.mf);
        dlgEp4.setLocationRelativeTo(this);
        dlgEp4.setVisible(true);
        if (dlgEp4.tableChanged) {
            this.btn_Sauver.setForeground(Color.RED);
        }
        ChopeCrew.options.savePreferences();
        ChopeCrew.donneesPerso_PNT.savePreferences();
        ChopeCrew.donneesPerso_PNC.savePreferences();
        return 1;
    }
    
    private int actionBtnStats() {
        final JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("R\u00e9pertoire contenant les plannings");
        fc.setFileSelectionMode(1);
        fc.setCurrentDirectory(new File(ChopeCrew.options.repXML));
        final int returnVal = fc.showOpenDialog(this);
        if (returnVal == 1) {
            return -1;
        }
        ChopeCrew.options.repXML = fc.getSelectedFile().getAbsolutePath();
        final Dlg_Stats dlgStats = new Dlg_Stats(ChopeCrew.mf, fc.getSelectedFile());
        dlgStats.setLocationRelativeTo(this);
        dlgStats.setVisible(true);
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnBlocs() {
        if (ChopeCrew.analyse.listRotation.size() == 0) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucun vol \u00e0 mettre \u00e0 jour", "ChopeCREW vous informe", -1);
            return 0;
        }
        this.btn_Blocs.setEnabled(false);
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final ConnexionAF connexionAF = new ConnexionAF(ChopeCrew.analyse);
                connexionAF.addObserver(MainFrame.this);
                connexionAF.chopeBlocAF(ChopeCrew.analyse);
            }
        };
        thrCon.start();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnGoogleM0() {
        System.out.println("Bouton GoogleM0");
        ChopeCrew.conGoogle.chope(0);
        return 1;
    }
    
    private int actionBtnGoogleM1() {
        System.out.println("Bouton GoogleM1");
        return 1;
    }
    
    private int actionTbtnRosterTableRetracted(final boolean isSelected) {
        if (isSelected) {
            ChopeCrew.options.isRosterTableRetracted = true;
            this.tbtn_RosterTableRetracted.setText("-");
            for (final Object obj : ChopeCrew.analyse.listCrew) {
                if (obj instanceof Rotation) {
                    ((Rotation)obj).setExpanded(false);
                }
            }
        }
        else if (!isSelected) {
            ChopeCrew.options.isRosterTableRetracted = false;
            this.tbtn_RosterTableRetracted.setText("+");
            for (final Object obj : ChopeCrew.analyse.listCrew) {
                if (obj instanceof Rotation) {
                    ((Rotation)obj).setExpanded(true);
                }
            }
        }
        this.showRoster();
        return 1;
    }
    
    private int actionTbtnRosterTableReal(final boolean isSelected) {
        if (isSelected) {
            ChopeCrew.options.isRosterTableReal = true;
            this.tbtn_RosterTableReal.setText("REAL.");
        }
        else if (!isSelected) {
            ChopeCrew.options.isRosterTableReal = false;
            this.tbtn_RosterTableReal.setText("PROG.");
        }
        this.showRoster();
        return 1;
    }
    
    private int actionTbtnRosterTableLocal(final boolean isSelected) {
        if (isSelected) {
            ChopeCrew.options.isRosterTableLocal = true;
            this.tbtn_RosterTableLocal.setText("LOC.");
        }
        else if (!isSelected) {
            ChopeCrew.options.isRosterTableLocal = false;
            this.tbtn_RosterTableLocal.setText("PAR.");
        }
        this.showRoster();
        return 1;
    }
    
    private int actionBtnRequest() {
        String login = this.tfld_Login.getText().trim().toLowerCase();
        if (!login.contains("m")) {
            login = "m" + login;
        }
        if (login.length() > 7) {
            login = login.substring(0, 7);
        }
        this.tfld_Login.setText(login);
        this.btn_ChopeM0_100.setEnabled(false);
        this.btn_ChopeM1_100.setEnabled(false);
        this.btn_ChopeM0_Ins.setEnabled(false);
        this.btn_ChopeM1_Ins.setEnabled(false);
        this.btn_ChopeM2_Ins.setEnabled(false);
        this.lab_Norm.setForeground(Color.ORANGE);
        this.lab_Flash.setForeground(Color.ORANGE);
        this.prb_ProgressStatus.setForeground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
        this.prb_ProgressStatus.setValue(0);
        ChopeCrew.options.loginIpn = login;
        ChopeCrew.options.passIpn = String.valueOf(this.pwdf_Password.getPassword());
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                ChopeCrew.conRequeteur.addObserver(MainFrame.this);
                ChopeCrew.conRequeteur.request(ChopeCrew.options.loginIpn, ChopeCrew.options.passIpn, ChopeCrew.options.urlIPN);
            }
        };
        thrCon.start();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnRequestClicGauche() {
        final CustomFileChooser fc = new CustomFileChooser("txt", false);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return -1;
        }
        this.btn_ChopeM0_100.setEnabled(false);
        this.btn_ChopeM1_100.setEnabled(false);
        this.btn_ChopeM0_Ins.setEnabled(false);
        this.btn_ChopeM1_Ins.setEnabled(false);
        this.btn_ChopeM2_Ins.setEnabled(false);
        this.lab_Norm.setForeground(Color.ORANGE);
        this.lab_Flash.setForeground(Color.ORANGE);
        this.prb_ProgressStatus.setForeground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
        this.prb_ProgressStatus.setValue(0);
        this.isOnline = false;
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                ChopeCrew.conRequeteur.addObserver(MainFrame.this);
                ChopeCrew.conRequeteur.request(returnFile.getAbsolutePath(), ChopeCrew.options.loginIpn, ChopeCrew.options.passIpn, ChopeCrew.options.urlIPN);
            }
        };
        thrCon.start();
        ChopeCrew.options.savePreferences();
        return 1;
    }
    
    private int actionBtnRequestClicDroit() {
        if (ChopeCrew.conRequeteur.getListPagesImpression().isEmpty()) {
            JOptionPane.showMessageDialog(ChopeCrew.mf, "Aucune source requ\u00eateur \u00e0 sauvegarder", "ChopeCREW vous informe", -1);
            return 0;
        }
        final CustomFileChooser fc = new CustomFileChooser("txt", true);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return -1;
        }
        Utils.saveToFile(ChopeCrew.conRequeteur.getSourceAsString(), returnFile.getAbsolutePath(), "UTF-8");
        return 1;
    }
    
    private void updateRotationsExpandState() {
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            rotation.setExpanded(!ChopeCrew.options.isRosterTableRetracted);
        }
    }
    
    private void updateExportability() {
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            rotation.setExportable(ChopeCrew.options.expRot);
        }
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    troncon.setExportable(ChopeCrew.options.expVol);
                }
            }
        }
        for (final ActiviteSol actSol : ChopeCrew.analyse.listSol) {
            if (actSol.code.equals("PRB") || actSol.code.equals("MAD") || actSol.code.equals("PAC") || actSol.code.equals("RPC")) {
                actSol.setExportable(ChopeCrew.options.expRepos);
            }
            else if (actSol.code.equals("MCA") || actSol.code.equals("MCE")) {
                actSol.setExportable(ChopeCrew.options.expConges);
            }
            else if (actSol.code.equals("MDV") || actSol.code.equals("MAS")) {
                actSol.setExportable(ChopeCrew.options.expAbsences);
            }
            else if (actSol.code.equals("DSP")) {
                actSol.setExportable(ChopeCrew.options.expDispersions);
            }
            else {
                actSol.setExportable(ChopeCrew.options.expActSol);
            }
        }
        if (ChopeCrew.analyse.listRotation.size() > 0) {
            final Rotation rotation = ChopeCrew.analyse.listRotation.get(0);
            final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(rotation.debutUTCD);
            if (cal.get(2) + 1 != ChopeCrew.analyse.moisInt) {
                rotation.setExportable(false);
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        troncon.setExportable(false);
                    }
                }
            }
        }
        if (ChopeCrew.analyse.listSol.size() > 0) {
            final ActiviteSol act = ChopeCrew.analyse.listSol.get(0);
            final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTime(act.debutUTCD);
            if (cal.get(2) + 1 != ChopeCrew.analyse.moisInt) {
                act.setExportable(false);
            }
        }
    }
    
    @Override
    public void update(final Observable obs, final Object msg) {
        if (obs instanceof ConnexionCrew && msg instanceof String) {
            this.prb_ProgressStatus.setValue(this.prb_ProgressStatus.getValue() + 1);
            this.prb_ProgressStatus.setString(msg.toString());
        }
        else if (obs instanceof ConnexionCrew && msg instanceof Integer) {
            if ((int)msg == 1) {
                this.prb_ProgressStatus.setValue(this.prb_ProgressStatus.getMaximum());
                this.prb_ProgressStatus.setForeground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
                ChopeCrew.analyse.chargeAnalyseCrewFromConnexion(ChopeCrew.conCrew);
                this.updateExportability();
                this.btn_Blocs.setText("M\u00e0J");
                this.prb_ProgressGoogle.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
                this.prb_ProgressNoticesCE.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
                this.prb_ProgressGoogle.setValue(0);
                this.prb_ProgressNoticesCE.setValue(0);
                this.prb_ProgressGoogle.setString(" ");
                this.prb_ProgressNoticesCE.setString(" ");
                this.importBlocs();
                this.exportNoticesCE();
                this.exportGoogleAgenda();
                this.btn_ChopeM0_100.setEnabled(true);
                this.btn_ChopeM1_100.setEnabled(true);
                this.btn_ChopeM0_Ins.setEnabled(true);
                this.btn_ChopeM1_Ins.setEnabled(true);
                this.btn_ChopeM2_Ins.setEnabled(true);
                this.btn_Sauver.setForeground(null);
                if (ChopeCrew.analyse.isFlash) {
                    this.lab_Norm.setForeground(Color.GRAY);
                    this.lab_Flash.setForeground(Color.BLUE);
                }
                else if (!ChopeCrew.analyse.isFlash) {
                    this.lab_Norm.setForeground(Color.GREEN);
                    this.lab_Flash.setForeground(Color.GRAY);
                }
                this.updateRotationsExpandState();
                this.showRoster();
            }
            else if ((int)msg == 0) {
                this.btn_ChopeM0_100.setEnabled(true);
                this.btn_ChopeM1_100.setEnabled(true);
                this.btn_ChopeM0_Ins.setEnabled(true);
                this.btn_ChopeM1_Ins.setEnabled(true);
                this.btn_ChopeM2_Ins.setEnabled(true);
                this.lab_Norm.setForeground(Color.GRAY);
                this.lab_Flash.setForeground(Color.GRAY);
                this.prb_ProgressStatus.setValue(0);
                this.prb_ProgressStatus.setString("Recommencez");
                this.showRoster();
                if (this.isOnline) {
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur impr\u00e9vue (v\u00e9rifiez le fonctionnement de Crew)", "ChopeCREW vous informe", -1);
                }
                else if (!this.isOnline) {
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur impr\u00e9vue", "ChopeCREW vous informe", -1);
                }
            }
            else if ((int)msg == -1) {
                this.btn_ChopeM0_100.setEnabled(true);
                this.btn_ChopeM1_100.setEnabled(true);
                this.btn_ChopeM0_Ins.setEnabled(true);
                this.btn_ChopeM1_Ins.setEnabled(true);
                this.btn_ChopeM2_Ins.setEnabled(true);
                this.lab_Norm.setForeground(Color.GRAY);
                this.lab_Flash.setForeground(Color.GRAY);
                this.prb_ProgressStatus.setValue(0);
                this.prb_ProgressStatus.setString("Recommencez");
                this.showRoster();
                if (this.isOnline) {
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Mauvais matricule et/ou mot de passe", "ChopeCREW vous informe", -1);
                }
                else if (!this.isOnline) {
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Fichier invalide", "ChopeCREW vous informe", -1);
                }
            }
        }
        if (obs instanceof ConnexionRequeteur && msg instanceof String) {
            this.prb_ProgressStatus.setValue(this.prb_ProgressStatus.getValue() + 1);
            this.prb_ProgressStatus.setString(msg.toString());
        }
        else if (obs instanceof ConnexionRequeteur && msg instanceof Integer) {
            if ((int)msg == 1) {
                this.prb_ProgressStatus.setValue(this.prb_ProgressStatus.getMaximum());
                this.prb_ProgressStatus.setForeground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
                ChopeCrew.analyse.chargeAnalyseCrewFromRequeteur(ChopeCrew.conRequeteur);
                this.updateExportability();
                this.btn_Blocs.setText("M\u00e0J");
                this.prb_ProgressGoogle.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
                this.prb_ProgressNightStop.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
                this.prb_ProgressNoticesAF.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
                this.prb_ProgressNoticesCE.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
                this.prb_ProgressGoogle.setValue(0);
                this.prb_ProgressNightStop.setValue(0);
                this.prb_ProgressNoticesAF.setValue(0);
                this.prb_ProgressNoticesCE.setValue(0);
                this.prb_ProgressGoogle.setString(" ");
                this.prb_ProgressNightStop.setString(" ");
                this.prb_ProgressNoticesAF.setString(" ");
                this.prb_ProgressNoticesCE.setString(" ");
                this.btn_ChopeM0_100.setEnabled(true);
                this.btn_ChopeM1_100.setEnabled(true);
                this.btn_ChopeM0_Ins.setEnabled(true);
                this.btn_ChopeM1_Ins.setEnabled(true);
                this.btn_ChopeM2_Ins.setEnabled(true);
                this.btn_Sauver.setForeground(null);
                if (ChopeCrew.analyse.isFlash) {
                    this.lab_Norm.setForeground(Color.GRAY);
                    this.lab_Flash.setForeground(Color.BLUE);
                }
                else if (!ChopeCrew.analyse.isFlash) {
                    this.lab_Norm.setForeground(Color.GREEN);
                    this.lab_Flash.setForeground(Color.GRAY);
                }
                this.updateRotationsExpandState();
                this.showRoster();
            }
            else if ((int)msg == 0) {
                this.btn_ChopeM0_100.setEnabled(true);
                this.btn_ChopeM1_100.setEnabled(true);
                this.btn_ChopeM0_Ins.setEnabled(true);
                this.btn_ChopeM1_Ins.setEnabled(true);
                this.btn_ChopeM2_Ins.setEnabled(true);
                this.lab_Norm.setForeground(Color.GRAY);
                this.lab_Flash.setForeground(Color.GRAY);
                this.prb_ProgressStatus.setValue(0);
                this.prb_ProgressStatus.setString("Recommencez");
                this.showRoster();
                if (this.isOnline) {
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur impr\u00e9vue (v\u00e9rifiez le fonctionnement de Crew)", "ChopeCREW vous informe", -1);
                }
                else if (!this.isOnline) {
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur impr\u00e9vue", "ChopeCREW vous informe", -1);
                }
            }
            else if ((int)msg == -1) {
                this.btn_ChopeM0_100.setEnabled(true);
                this.btn_ChopeM1_100.setEnabled(true);
                this.btn_ChopeM0_Ins.setEnabled(true);
                this.btn_ChopeM1_Ins.setEnabled(true);
                this.btn_ChopeM2_Ins.setEnabled(true);
                this.lab_Norm.setForeground(Color.GRAY);
                this.lab_Flash.setForeground(Color.GRAY);
                this.prb_ProgressStatus.setValue(0);
                this.prb_ProgressStatus.setString("Recommencez");
                this.showRoster();
                if (this.isOnline) {
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Mauvais matricule et/ou mot de passe", "ChopeCREW vous informe", -1);
                }
                else if (!this.isOnline) {
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Fichier invalide", "ChopeCREW vous informe", -1);
                }
            }
        }
        else if (obs instanceof Export_GoogleAgendaV3) {
            if (msg instanceof String) {
                if (msg.equals("Planning Google envoy\u00e9 !")) {
                    this.prb_ProgressGoogle.setString("OK");
                    this.prb_ProgressGoogle.setValue(this.prb_ProgressGoogle.getMaximum());
                    this.prb_ProgressGoogle.setForeground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
                }
                else if (msg.equals("Erreur d'identifiant ou de mot de passe !")) {
                    this.prb_ProgressGoogle.setString("ERR");
                    this.prb_ProgressGoogle.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "V\u00e9rifiez vos identifiants Google", "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Erreur impr\u00e9vue !")) {
                    this.prb_ProgressGoogle.setString("ERR");
                    this.prb_ProgressGoogle.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur impr\u00e9vue de Google", "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Erreur de calendrier !")) {
                    this.prb_ProgressGoogle.setString("ERR");
                    this.prb_ProgressGoogle.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Calendrier sp\u00e9cifi\u00e9 inconnu", "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Connect\u00e9 Google")) {
                    this.prb_ProgressGoogle.setString("@");
                }
            }
            else if (msg instanceof Integer) {
                this.prb_ProgressGoogle.setString(msg.toString());
                this.prb_ProgressGoogle.setValue(((Export_GoogleAgendaV3)obs).getProgressValue());
            }
        }
        else if (obs instanceof Export_NightStop3) {
            if (msg instanceof String) {
                if (msg.equals("Planning NightStop envoy\u00e9 !")) {
                    this.prb_ProgressNightStop.setString("OK");
                    this.prb_ProgressNightStop.setValue(this.prb_ProgressNightStop.getMaximum());
                    this.prb_ProgressNightStop.setForeground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
                }
                else if (msg.equals("Erreur d'identifiant ou de mot de passe !")) {
                    this.prb_ProgressNightStop.setString("ERR");
                    this.prb_ProgressNightStop.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "V\u00e9rifiez vos identifiants NightStop", "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Erreur impr\u00e9vue !")) {
                    this.prb_ProgressNightStop.setString("ERR");
                    this.prb_ProgressNightStop.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, "Erreur impr\u00e9vue de NightStop", "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Connect\u00e9 NightStop")) {
                    this.prb_ProgressNightStop.setString("@");
                }
            }
            else if (msg instanceof Integer) {
                this.prb_ProgressNightStop.setString(msg.toString());
                this.prb_ProgressNightStop.setValue(((Export_NightStop3)obs).getProgressValue());
            }
        }
        else if (obs instanceof ConnexionEdito) {
            if (msg instanceof String) {
                if (msg.equals("Notices AF export\u00e9es !")) {
                    this.prb_ProgressNoticesAF.setString("OK");
                    this.prb_ProgressNoticesAF.setValue(this.prb_ProgressNoticesAF.getMaximum());
                    this.prb_ProgressNoticesAF.setForeground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
                }
                else if (msg.equals("Erreur d'identifiant ou de mot de passe")) {
                    this.prb_ProgressNoticesAF.setString("ERR");
                    this.prb_ProgressNoticesAF.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, msg, "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Erreur impr\u00e9vue")) {
                    this.prb_ProgressNoticesAF.setString("ERR");
                    this.prb_ProgressNoticesAF.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, msg, "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Connect\u00e9 Edito !")) {
                    this.prb_ProgressNoticesAF.setString("@");
                }
            }
            else if (msg instanceof Integer) {
                this.prb_ProgressNoticesAF.setString(msg.toString());
                this.prb_ProgressNoticesAF.setValue(((ConnexionEdito)obs).getProgressValue());
            }
        }
        else if (obs instanceof ConnexionCE) {
            if (msg instanceof String) {
                if (msg.equals("Notices CE export\u00e9es !")) {
                    this.prb_ProgressNoticesCE.setString("OK");
                    this.prb_ProgressNoticesCE.setValue(this.prb_ProgressNoticesCE.getMaximum());
                    this.prb_ProgressNoticesCE.setForeground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
                }
                else if (msg.equals("Erreur du site CE Lignes")) {
                    this.prb_ProgressNoticesCE.setString("ERR");
                    this.prb_ProgressNoticesCE.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, msg, "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Erreur impr\u00e9vue")) {
                    this.prb_ProgressNoticesCE.setString("ERR");
                    this.prb_ProgressNoticesCE.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, msg, "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Identifiant et/ou mot de passe CE Lignes\nnon renseign\u00e9(s) dans Options/Notices")) {
                    this.prb_ProgressNoticesCE.setString("ERR");
                    this.prb_ProgressNoticesCE.setValue(0);
                    JOptionPane.showMessageDialog(ChopeCrew.mf, msg, "ChopeCREW vous informe", -1);
                }
                else if (msg.equals("Connect\u00e9 CELignes !")) {
                    this.prb_ProgressNoticesCE.setString("@");
                }
            }
            else if (msg instanceof Integer) {
                this.prb_ProgressNoticesCE.setString(msg.toString());
                this.prb_ProgressNoticesCE.setValue(((ConnexionCE)obs).getProgressValue());
            }
        }
        else if (obs instanceof ConnexionAF) {
            if (msg instanceof String) {
                if (msg.equals("Connect\u00e9 AF")) {
                    this.btn_Blocs.setText("@");
                }
                else if (msg.equals("Erreur du site AF") || msg.equals("Erreur impr\u00e9vue")) {
                    this.btn_Blocs.setEnabled(true);
                    this.btn_Blocs.setText("ERR");
                }
                else if (msg.equals("Pas de mise \u00e0 jour")) {
                    System.out.println("Blocs OK NO CHANGE");
                    this.btn_Blocs.setEnabled(true);
                    this.btn_Blocs.setText("OK");
                }
                else if (msg.equals("Temps de vol mis \u00e0 jour")) {
                    System.out.println("Blocs OK CHANGE");
                    ChopeCrew.analyse.calculHCIRrAnalyseCrew();
                    this.showRoster();
                    this.btn_Blocs.setEnabled(true);
                    this.btn_Blocs.setText("OK");
                    this.btn_Sauver.setForeground(Color.RED);
                    if (ChopeCrew.options.isSaveAuto) {
                        String filename = "";
                        if (ChopeCrew.analyse.deltaMois == 0) {
                            filename = String.valueOf(ChopeCrew.analyse.matricule) + "_Plng_" + ChopeCrew.analyse.anneeInt + "_" + ChopeCrew.analyse.moisNum;
                        }
                        else if (ChopeCrew.analyse.deltaMois == 1) {
                            filename = String.valueOf(ChopeCrew.analyse.matricule) + "_Prev_" + ChopeCrew.analyse.anneeInt + "_" + ChopeCrew.analyse.moisNum;
                        }
                        else if (ChopeCrew.analyse.deltaMois == 2) {
                            filename = String.valueOf(ChopeCrew.analyse.matricule) + "_Prev_" + ChopeCrew.analyse.anneeInt + "_" + ChopeCrew.analyse.moisNum;
                        }
                        final Export_XML xml = new Export_XML();
                        xml.createXML(ChopeCrew.analyse);
                        Utils.saveToFile(xml.getXml(), String.valueOf(ChopeCrew.options.repSaveAuto) + File.separator + filename + ".xml", "UTF8");
                        this.btn_Sauver.setForeground(null);
                    }
                }
            }
            else if (msg instanceof Integer) {
                this.btn_Blocs.setText(msg.toString());
            }
        }
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BorderLayout(0, 0));
            this.jContentPane.add(this.getPanel_west(), "West");
            this.jContentPane.add(this.getPanel_center(), "Center");
            this.jContentPane.add(this.getPanel_east(), "East");
            this.jContentPane.add(this.getVerticalStrut_North(), "North");
            this.jContentPane.add(this.getVerticalStrut_South(), "South");
            this.jContentPane.addMouseListener(new MyMouseAdapter());
        }
        return this.jContentPane;
    }
    
    private JPanel getPanel_west() {
        if (this.panel_west == null) {
            (this.panel_west = new JPanel()).setPreferredSize(new Dimension(200, 32767));
            this.panel_west.setLayout(new BoxLayout(this.panel_west, 0));
            this.panel_west.add(this.getHorizontalStrut_WestLeft());
            this.panel_west.add(this.getPnl_West());
            this.panel_west.add(this.getHorizontalStrut_WestRight());
        }
        return this.panel_west;
    }
    
    private JPanel getPnl_West() {
        if (this.pnl_West == null) {
            (this.pnl_West = new JPanel()).setLayout(new BoxLayout(this.pnl_West, 1));
            this.pnl_West.add(this.getLab_Banner());
            this.pnl_West.add(this.getVerticalGlue_W1());
            this.pnl_West.add(this.getPnl_Authentification());
            this.pnl_West.add(this.getVerticalGlue_W2());
            this.pnl_West.add(this.getPnl_Importation());
            this.pnl_West.add(this.getVerticalGlue_W3());
            this.pnl_West.add(this.getPnl_HorsLigne());
            this.pnl_West.add(this.getVerticalGlue_W4());
            this.pnl_West.add(this.getPnl_Footer());
        }
        return this.pnl_West;
    }
    
    private JLabel getLab_Banner() {
        if (this.lab_Banner == null) {
            (this.lab_Banner = new JLabel("")).setIconTextGap(0);
            this.lab_Banner.setIcon(new ImageIcon(MainFrame.class.getResource("/res_images/img_banner_chopecrew.png")));
            this.lab_Banner.setHorizontalAlignment(0);
            this.lab_Banner.setPreferredSize(new Dimension(160, 40));
            this.lab_Banner.setMinimumSize(new Dimension(160, 40));
            this.lab_Banner.setMaximumSize(new Dimension(32767, 32767));
            this.lab_Banner.setAlignmentX(0.5f);
            this.lab_Banner.addMouseListener(new MyMouseAdapter());
            this.lab_Banner.addKeyListener(new MyKeyAdapter());
        }
        return this.lab_Banner;
    }
    
    private JPanel getPnl_Authentification() {
        if (this.pnl_Authentification == null) {
            (this.pnl_Authentification = new JPanel()).setBorder(new TitledBorder(null, "Authentification", 4, 2, null, null));
            final Border border = this.pnl_Authentification.getBorder();
            final Border margin = new EmptyBorder(4, 4, 4, 4);
            this.pnl_Authentification.setBorder(new CompoundBorder(border, margin));
            this.pnl_Authentification.setLayout(new BoxLayout(this.pnl_Authentification, 1));
            this.pnl_Authentification.add(this.getLab_Login());
            this.pnl_Authentification.add(this.getTfld_Login());
            this.pnl_Authentification.add(this.getVerticalStrut_1());
            this.pnl_Authentification.add(this.getLab_Passw());
            this.pnl_Authentification.add(this.getPwdf_Password());
        }
        return this.pnl_Authentification;
    }
    
    private JLabel getLab_Login() {
        if (this.lab_Login == null) {
            (this.lab_Login = new JLabel("Identifiant :")).setMaximumSize(new Dimension(32767, 40));
            this.lab_Login.setAlignmentX(0.5f);
            this.lab_Login.setFont(new Font("Tahoma", 0, 14));
        }
        return this.lab_Login;
    }
    
    private JTextField getTfld_Login() {
        if (this.tfld_Login == null) {
            (this.tfld_Login = new JTextField()).setPreferredSize(new Dimension(6, 32));
            this.tfld_Login.setMaximumSize(new Dimension(32767, 40));
            this.tfld_Login.setColumns(7);
            this.tfld_Login.setFont(new Font("Tahoma", 0, 16));
            this.tfld_Login.setToolTipText("m + les 6 premiers chiffres du matricule");
            this.tfld_Login.addKeyListener(new MyKeyAdapter());
        }
        return this.tfld_Login;
    }
    
    private JLabel getLab_Passw() {
        if (this.lab_Password == null) {
            (this.lab_Password = new JLabel("Code d'entr\u00e9e :")).setMaximumSize(new Dimension(32767, 40));
            this.lab_Password.setAlignmentX(0.5f);
            this.lab_Password.setFont(new Font("Tahoma", 0, 14));
        }
        return this.lab_Password;
    }
    
    private JPasswordField getPwdf_Password() {
        if (this.pwdf_Password == null) {
            (this.pwdf_Password = new JPasswordField()).setPreferredSize(new Dimension(6, 32));
            this.pwdf_Password.setMaximumSize(new Dimension(32767, 40));
            this.pwdf_Password.setColumns(10);
            this.pwdf_Password.setFont(new Font("Tahoma", 0, 16));
            this.pwdf_Password.setToolTipText("Code PIN + code Token");
            this.pwdf_Password.addKeyListener(new MyKeyAdapter());
        }
        return this.pwdf_Password;
    }
    
    private JPanel getPnl_Importation() {
        if (this.pnl_Importation == null) {
            (this.pnl_Importation = new JPanel()).setBorder(new TitledBorder(null, "Importation", 4, 2, null, null));
            this.pnl_Importation.setLayout(new BoxLayout(this.pnl_Importation, 1));
            this.pnl_Importation.add(this.getPnl_Connect());
            this.pnl_Importation.add(this.getPnl_Status());
        }
        return this.pnl_Importation;
    }
    
    private JPanel getPnl_Connect() {
        if (this.pnl_Connect == null) {
            (this.pnl_Connect = new JPanel()).setMaximumSize(new Dimension(32767, 128));
            this.pnl_Connect.setLayout(new CardLayout(0, 0));
            this.pnl_Connect.add(this.getPnl_Connect_100(), "CONNECT_LAYOUT_100");
            this.pnl_Connect.add(this.getPnl_Connect_Ins(), "CONNECT_LAYOUT_INS");
        }
        return this.pnl_Connect;
    }
    
    private JPanel getPnl_Connect_100() {
        if (this.pnl_Connect_100 == null) {
            (this.pnl_Connect_100 = new JPanel()).setMaximumSize(new Dimension(32767, 128));
            this.pnl_Connect_100.setLayout(new BoxLayout(this.pnl_Connect_100, 1));
            this.pnl_Connect_100.add(this.getBtn_ChopeM0_100());
            this.pnl_Connect_100.add(this.getVerticalStrut_2());
            this.pnl_Connect_100.add(this.getBtn_ChopeM1_100());
        }
        return this.pnl_Connect_100;
    }
    
    private JPanel getPnl_Connect_Ins() {
        if (this.pnl_Connect_Ins == null) {
            (this.pnl_Connect_Ins = new JPanel()).setMaximumSize(new Dimension(32767, 128));
            this.pnl_Connect_Ins.setLayout(new BoxLayout(this.pnl_Connect_Ins, 1));
            this.pnl_Connect_Ins.add(this.getBtn_ChopeM0_Ins());
            this.pnl_Connect_Ins.add(this.getVerticalStrut_3());
            this.pnl_Connect_Ins.add(this.getBtn_ChopeM1_Ins());
            this.pnl_Connect_Ins.add(this.getVerticalStrut_4());
            this.pnl_Connect_Ins.add(this.getBtn_ChopeM2_Ins());
        }
        return this.pnl_Connect_Ins;
    }
    
    private JButton getBtn_ChopeM0_100() {
        if (this.btn_ChopeM0_100 == null) {
            (this.btn_ChopeM0_100 = new JButton()).setPreferredSize(new Dimension(33, 40));
            this.btn_ChopeM0_100.setMinimumSize(new Dimension(128, 40));
            this.btn_ChopeM0_100.setAlignmentX(0.5f);
            this.btn_ChopeM0_100.setMaximumSize(new Dimension(32767, 40));
            this.btn_ChopeM0_100.setMargin(new Insets(0, 2, 0, 2));
            this.btn_ChopeM0_100.setFont(new Font("Tahoma", 0, 16));
            this.btn_ChopeM0_100.setToolTipText("Importation du planning du mois en cours (clic gauche = mode normal, clic droit = mode flash) ");
            this.btn_ChopeM0_100.addMouseListener(new MyMouseAdapter());
            this.btn_ChopeM0_100.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ChopeM0_100;
    }
    
    private JButton getBtn_ChopeM1_100() {
        if (this.btn_ChopeM1_100 == null) {
            (this.btn_ChopeM1_100 = new JButton()).setPreferredSize(new Dimension(33, 40));
            this.btn_ChopeM1_100.setMinimumSize(new Dimension(128, 40));
            this.btn_ChopeM1_100.setAlignmentX(0.5f);
            this.btn_ChopeM1_100.setMaximumSize(new Dimension(32767, 40));
            this.btn_ChopeM1_100.setMargin(new Insets(0, 2, 0, 2));
            this.btn_ChopeM1_100.setFont(new Font("Tahoma", 0, 16));
            this.btn_ChopeM1_100.setToolTipText("Importation du planning du mois prochain (clic gauche = mode normal, clic droit = mode flash) ");
            this.btn_ChopeM1_100.addMouseListener(new MyMouseAdapter());
            this.btn_ChopeM1_100.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ChopeM1_100;
    }
    
    private JButton getBtn_ChopeM0_Ins() {
        if (this.btn_ChopeM0_Ins == null) {
            (this.btn_ChopeM0_Ins = new JButton()).setPreferredSize(new Dimension(33, 40));
            this.btn_ChopeM0_Ins.setMinimumSize(new Dimension(128, 40));
            this.btn_ChopeM0_Ins.setAlignmentX(0.5f);
            this.btn_ChopeM0_Ins.setMaximumSize(new Dimension(32767, 40));
            this.btn_ChopeM0_Ins.setMargin(new Insets(0, 2, 0, 2));
            this.btn_ChopeM0_Ins.setFont(new Font("Tahoma", 0, 16));
            this.btn_ChopeM0_Ins.setToolTipText("Importation du planning du mois en cours (clic gauche = mode normal, clic droit = mode flash) ");
            this.btn_ChopeM0_Ins.addMouseListener(new MyMouseAdapter());
            this.btn_ChopeM0_Ins.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ChopeM0_Ins;
    }
    
    private JButton getBtn_ChopeM1_Ins() {
        if (this.btn_ChopeM1_Ins == null) {
            (this.btn_ChopeM1_Ins = new JButton()).setPreferredSize(new Dimension(33, 40));
            this.btn_ChopeM1_Ins.setMinimumSize(new Dimension(128, 40));
            this.btn_ChopeM1_Ins.setAlignmentX(0.5f);
            this.btn_ChopeM1_Ins.setMaximumSize(new Dimension(32767, 40));
            this.btn_ChopeM1_Ins.setMargin(new Insets(0, 2, 0, 2));
            this.btn_ChopeM1_Ins.setFont(new Font("Tahoma", 0, 16));
            this.btn_ChopeM1_Ins.setToolTipText("Importation du planning du mois prochain (clic gauche = mode normal, clic droit = mode flash) ");
            this.btn_ChopeM1_Ins.addMouseListener(new MyMouseAdapter());
            this.btn_ChopeM1_Ins.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ChopeM1_Ins;
    }
    
    private JButton getBtn_ChopeM2_Ins() {
        if (this.btn_ChopeM2_Ins == null) {
            (this.btn_ChopeM2_Ins = new JButton()).setPreferredSize(new Dimension(33, 40));
            this.btn_ChopeM2_Ins.setMinimumSize(new Dimension(128, 40));
            this.btn_ChopeM2_Ins.setAlignmentX(0.5f);
            this.btn_ChopeM2_Ins.setMaximumSize(new Dimension(32767, 40));
            this.btn_ChopeM2_Ins.setFont(new Font("Tahoma", 0, 16));
            this.btn_ChopeM2_Ins.setMargin(new Insets(0, 2, 0, 2));
            this.btn_ChopeM2_Ins.setToolTipText("Importation du planning du mois M+2 (instructeurs seulement)");
            this.btn_ChopeM2_Ins.addMouseListener(new MyMouseAdapter());
            this.btn_ChopeM2_Ins.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ChopeM2_Ins;
    }
    
    private JPanel getPnl_Status() {
        if (this.pnl_Status == null) {
            (this.pnl_Status = new JPanel()).setMaximumSize(new Dimension(256, 96));
            this.pnl_Status.setLayout(new BoxLayout(this.pnl_Status, 1));
            this.pnl_Status.add(this.getPrb_ProgressStatus());
            this.pnl_Status.add(this.getPnl_NormFlash());
        }
        return this.pnl_Status;
    }
    
    private JProgressBar getPrb_ProgressStatus() {
        if (this.prb_ProgressStatus == null) {
            (this.prb_ProgressStatus = new JProgressBar()).setFont(new Font("Tahoma", 0, 12));
            this.prb_ProgressStatus.setMinimumSize(new Dimension(128, 24));
            this.prb_ProgressStatus.setMaximumSize(new Dimension(32767, 48));
            this.prb_ProgressStatus.setPreferredSize(new Dimension(128, 24));
            this.prb_ProgressStatus.setOpaque(false);
            this.prb_ProgressStatus.setMaximum(14);
            this.prb_ProgressStatus.setStringPainted(true);
            this.prb_ProgressStatus.setString("Pr\u00eat");
            this.prb_ProgressStatus.setForeground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
        }
        return this.prb_ProgressStatus;
    }
    
    private JPanel getPnl_NormFlash() {
        if (this.pnl_NormFlash == null) {
            (this.pnl_NormFlash = new JPanel()).setLayout(new GridLayout(0, 2, 0, 0));
            this.pnl_NormFlash.add(this.getLab_Norm());
            this.pnl_NormFlash.add(this.getLab_Flash());
        }
        return this.pnl_NormFlash;
    }
    
    private JLabel getLab_Norm() {
        if (this.lab_Norm == null) {
            (this.lab_Norm = new JLabel()).setAlignmentX(0.5f);
            this.lab_Norm.setFont(new Font("Tahoma", 0, 12));
            this.lab_Norm.setForeground(Color.gray);
            this.lab_Norm.setHorizontalAlignment(0);
            this.lab_Norm.setHorizontalTextPosition(0);
            this.lab_Norm.setText("norm");
        }
        return this.lab_Norm;
    }
    
    private JLabel getLab_Flash() {
        if (this.lab_Flash == null) {
            (this.lab_Flash = new JLabel()).setAlignmentX(0.5f);
            this.lab_Flash.setFont(new Font("Tahoma", 0, 12));
            this.lab_Flash.setForeground(Color.gray);
            this.lab_Flash.setHorizontalAlignment(0);
            this.lab_Flash.setHorizontalTextPosition(0);
            this.lab_Flash.setText("flash");
        }
        return this.lab_Flash;
    }
    
    private JPanel getPnl_HorsLigne() {
        if (this.pnl_HorsLigne == null) {
            (this.pnl_HorsLigne = new JPanel()).setBorder(new TitledBorder(null, "Hors-ligne", 4, 2, null, null));
            this.pnl_HorsLigne.setLayout(new BoxLayout(this.pnl_HorsLigne, 1));
            this.pnl_HorsLigne.add(this.getBtn_Charger());
            this.pnl_HorsLigne.add(this.getVerticalStrut_5());
            this.pnl_HorsLigne.add(this.getBtn_Sauver());
        }
        return this.pnl_HorsLigne;
    }
    
    private JButton getBtn_Charger() {
        if (this.btn_Charger == null) {
            (this.btn_Charger = new JButton()).setMinimumSize(new Dimension(128, 40));
            this.btn_Charger.setPreferredSize(new Dimension(32767, 40));
            this.btn_Charger.setAlignmentX(0.5f);
            this.btn_Charger.setMaximumSize(new Dimension(32767, 40));
            this.btn_Charger.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Charger.setFont(new Font("Tahoma", 0, 16));
            this.btn_Charger.setText("Charger");
            this.btn_Charger.setToolTipText("Chargement d'un fichier planning pour consultation hors ligne");
            this.btn_Charger.addMouseListener(new MyMouseAdapter());
            this.btn_Charger.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Charger;
    }
    
    private JButton getBtn_Sauver() {
        if (this.btn_Sauver == null) {
            (this.btn_Sauver = new JButton()).setMinimumSize(new Dimension(128, 40));
            this.btn_Sauver.setPreferredSize(new Dimension(32767, 40));
            this.btn_Sauver.setAlignmentX(0.5f);
            this.btn_Sauver.setMaximumSize(new Dimension(32767, 40));
            this.btn_Sauver.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Sauver.setFont(new Font("Tahoma", 0, 16));
            this.btn_Sauver.setText("Sauver");
            this.btn_Sauver.setToolTipText("Sauvegarde d'un fichier planning pour consultation hors ligne");
            this.btn_Sauver.addMouseListener(new MyMouseAdapter());
            this.btn_Sauver.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Sauver;
    }
    
    private JPanel getPnl_Footer() {
        if (this.pnl_Footer == null) {
            (this.pnl_Footer = new JPanel()).setLayout(new BoxLayout(this.pnl_Footer, 0));
            this.pnl_Footer.add(this.getBtn_Aide());
            this.pnl_Footer.add(this.getHorizontalStrut_Footer1());
            this.pnl_Footer.add(this.getBtn_Profil());
            this.pnl_Footer.add(this.getHorizontalStrut_Footer2());
            this.pnl_Footer.add(this.getBtn_Configuration());
        }
        return this.pnl_Footer;
    }
    
    private JButton getBtn_Aide() {
        if (this.btn_Aide == null) {
            (this.btn_Aide = new JButton()).setPreferredSize(new Dimension(40, 40));
            this.btn_Aide.setMinimumSize(new Dimension(40, 40));
            this.btn_Aide.setMaximumSize(new Dimension(40, 40));
            this.btn_Aide.setIcon(new ImageIcon(MainFrame.class.getResource("/res_images/icon_infos.png")));
            this.btn_Aide.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Aide.setFont(new Font("Tahoma", 0, 11));
            this.btn_Aide.setToolTipText("Aide et \u00e0 propos ...");
            this.btn_Aide.addMouseListener(new MyMouseAdapter());
            this.btn_Aide.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Aide;
    }
    
    private JButton getBtn_Profil() {
        if (this.btn_Profil == null) {
            (this.btn_Profil = new JButton()).setPreferredSize(new Dimension(40, 40));
            this.btn_Profil.setMinimumSize(new Dimension(40, 40));
            this.btn_Profil.setMaximumSize(new Dimension(40, 40));
            this.btn_Profil.setIcon(new ImageIcon(MainFrame.class.getResource("/res_images/icon_profil.png")));
            this.btn_Profil.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Profil.setFont(new Font("Tahoma", 0, 11));
            this.btn_Profil.setToolTipText("Profil");
            this.btn_Profil.addMouseListener(new MyMouseAdapter());
            this.btn_Profil.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Profil;
    }
    
    private JButton getBtn_Configuration() {
        if (this.btn_Configuration == null) {
            (this.btn_Configuration = new JButton()).setPreferredSize(new Dimension(40, 40));
            this.btn_Configuration.setMinimumSize(new Dimension(40, 40));
            this.btn_Configuration.setMaximumSize(new Dimension(40, 40));
            this.btn_Configuration.setIcon(new ImageIcon(MainFrame.class.getResource("/res_images/icon_settings.png")));
            this.btn_Configuration.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Configuration.setFont(new Font("Tahoma", 0, 11));
            this.btn_Configuration.setToolTipText("Configuration");
            this.btn_Configuration.addMouseListener(new MyMouseAdapter());
            this.btn_Configuration.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Configuration;
    }
    
    private JPanel getPanel_east() {
        if (this.panel_east == null) {
            (this.panel_east = new JPanel()).setPreferredSize(new Dimension(200, 32767));
            this.panel_east.setLayout(new BoxLayout(this.panel_east, 0));
            this.panel_east.add(this.getHorizontalStrut_EastLeft());
            this.panel_east.add(this.getPnl_East());
            this.panel_east.add(this.getHorizontalStrut_EastRight());
        }
        return this.panel_east;
    }
    
    private JPanel getPnl_East() {
        if (this.pnl_East == null) {
            (this.pnl_East = new JPanel()).setLayout(new BoxLayout(this.pnl_East, 1));
            this.pnl_East.add(this.getPnl_Exportation());
            this.pnl_East.add(this.getVerticalGlue_E1());
            this.pnl_East.add(this.getPnl_Notices());
            this.pnl_East.add(this.getVerticalGlue_E2());
            this.pnl_East.add(this.getPnl_ActivitePaye());
        }
        return this.pnl_East;
    }
    
    private JPanel getPnl_Exportation() {
        if (this.pnl_Exportation == null) {
            (this.pnl_Exportation = new JPanel()).setBorder(new TitledBorder(null, "Exportation", 4, 2, null, null));
            this.pnl_Exportation.setLayout(new BoxLayout(this.pnl_Exportation, 1));
            this.pnl_Exportation.add(this.getBtn_ExportGoogle());
            this.pnl_Exportation.add(this.getVerticalStrut_6());
            this.pnl_Exportation.add(this.getPrb_ProgressGoogle());
            this.pnl_Exportation.add(this.getVerticalStrut_Exportation1());
            this.pnl_Exportation.add(this.getVerticalStrut_7());
            this.pnl_Exportation.add(this.getVerticalStrut_Exportation2());
            this.pnl_Exportation.add(this.getBtn_ExportPDA());
            this.pnl_Exportation.add(this.getVerticalStrut_8());
            this.pnl_Exportation.add(this.getBtn_ExportPDF());
        }
        return this.pnl_Exportation;
    }
    
    private JButton getBtn_ExportGoogle() {
        if (this.btn_ExportGoogle == null) {
            (this.btn_ExportGoogle = new JButton()).setPreferredSize(new Dimension(128, 40));
            this.btn_ExportGoogle.setMinimumSize(new Dimension(128, 40));
            this.btn_ExportGoogle.setMaximumSize(new Dimension(32767, 40));
            this.btn_ExportGoogle.setAlignmentX(0.5f);
            this.btn_ExportGoogle.setText("Google Agenda");
            this.btn_ExportGoogle.setIcon(null);
            this.btn_ExportGoogle.setMargin(new Insets(2, 2, 2, 2));
            this.btn_ExportGoogle.setFont(new Font("Tahoma", 0, 16));
            this.btn_ExportGoogle.setToolTipText("Export du planning vers Google Agenda");
            this.btn_ExportGoogle.addMouseListener(new MyMouseAdapter());
            this.btn_ExportGoogle.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ExportGoogle;
    }
    
    private JProgressBar getPrb_ProgressGoogle() {
        if (this.prb_ProgressGoogle == null) {
            (this.prb_ProgressGoogle = new JProgressBar()).setMinimumSize(new Dimension(10, 20));
            this.prb_ProgressGoogle.setPreferredSize(new Dimension(128, 20));
            this.prb_ProgressGoogle.setMaximumSize(new Dimension(32767, 20));
            this.prb_ProgressGoogle.setString(" ");
            this.prb_ProgressGoogle.setStringPainted(true);
            this.prb_ProgressGoogle.setFont(new Font("Tahoma", 0, 14));
            this.prb_ProgressGoogle.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
            this.prb_ProgressGoogle.setMinimum(0);
            this.prb_ProgressGoogle.setMaximum(100);
        }
        return this.prb_ProgressGoogle;
    }
    
    private JButton getBtn_ExportNightStop() {
        if (this.btn_ExportNightStop == null) {
            (this.btn_ExportNightStop = new JButton()).setPreferredSize(new Dimension(128, 40));
            this.btn_ExportNightStop.setMinimumSize(new Dimension(128, 40));
            this.btn_ExportNightStop.setMaximumSize(new Dimension(32767, 40));
            this.btn_ExportNightStop.setAlignmentX(0.5f);
            this.btn_ExportNightStop.setText("NightStop");
            this.btn_ExportNightStop.setIcon(null);
            this.btn_ExportNightStop.setMargin(new Insets(2, 2, 2, 2));
            this.btn_ExportNightStop.setFont(new Font("Tahoma", 0, 16));
            this.btn_ExportNightStop.setToolTipText("Export du planning vers NightStop");
            this.btn_ExportNightStop.addMouseListener(new MyMouseAdapter());
            this.btn_ExportNightStop.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ExportNightStop;
    }
    
    private JProgressBar getPrb_ProgressNightStop() {
        if (this.prb_ProgressNightStop == null) {
            (this.prb_ProgressNightStop = new JProgressBar()).setMinimumSize(new Dimension(10, 20));
            this.prb_ProgressNightStop.setPreferredSize(new Dimension(128, 20));
            this.prb_ProgressNightStop.setMaximumSize(new Dimension(32767, 20));
            this.prb_ProgressNightStop.setString(" ");
            this.prb_ProgressNightStop.setStringPainted(true);
            this.prb_ProgressNightStop.setFont(new Font("Tahoma", 0, 14));
            this.prb_ProgressNightStop.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
            this.prb_ProgressNightStop.setMinimum(0);
            this.prb_ProgressNightStop.setMaximum(100);
        }
        return this.prb_ProgressNightStop;
    }
    
    private JButton getBtn_ExportPDA() {
        if (this.btn_ExportPDA == null) {
            (this.btn_ExportPDA = new JButton()).setPreferredSize(new Dimension(128, 40));
            this.btn_ExportPDA.setMinimumSize(new Dimension(128, 40));
            this.btn_ExportPDA.setMaximumSize(new Dimension(32767, 40));
            this.btn_ExportPDA.setAlignmentX(0.5f);
            this.btn_ExportPDA.setMargin(new Insets(2, 2, 2, 2));
            this.btn_ExportPDA.setFont(new Font("Tahoma", 0, 16));
            this.btn_ExportPDA.setText("iCal / Outlook");
            this.btn_ExportPDA.setToolTipText("<html>G\u00e9n\u00e8re un fichier de format calendrier (.ics)<br> pour iCal, Outlook, Palm Desktop, etc...</html>");
            this.btn_ExportPDA.addMouseListener(new MyMouseAdapter());
            this.btn_ExportPDA.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ExportPDA;
    }
    
    private JButton getBtn_ExportPDF() {
        if (this.btn_ExportPDF == null) {
            (this.btn_ExportPDF = new JButton()).setPreferredSize(new Dimension(128, 40));
            this.btn_ExportPDF.setMinimumSize(new Dimension(128, 40));
            this.btn_ExportPDF.setMaximumSize(new Dimension(32767, 40));
            this.btn_ExportPDF.setAlignmentX(0.5f);
            this.btn_ExportPDF.setMargin(new Insets(2, 2, 2, 2));
            this.btn_ExportPDF.setFont(new Font("Tahoma", 0, 16));
            this.btn_ExportPDF.setText("PDF");
            this.btn_ExportPDF.setToolTipText("G\u00e9n\u00e8re un fichier de format Pdf, Html ou Png");
            this.btn_ExportPDF.addMouseListener(new MyMouseAdapter());
            this.btn_ExportPDF.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ExportPDF;
    }
    
    private JPanel getPnl_Notices() {
        if (this.pnl_Notices == null) {
            (this.pnl_Notices = new JPanel()).setBorder(new TitledBorder(null, "Notices escales", 4, 2, null, null));
            this.pnl_Notices.setLayout(new BoxLayout(this.pnl_Notices, 1));
            this.pnl_Notices.add(this.getVerticalStrut_9());
            this.pnl_Notices.add(this.getVerticalStrut_Notices1());
            this.pnl_Notices.add(this.getBtn_ExportNoticesCE());
            this.pnl_Notices.add(this.getVerticalStrut_10());
            this.pnl_Notices.add(this.getPrb_ProgressNoticesCE());
        }
        return this.pnl_Notices;
    }
    
    private JButton getBtn_ExportNoticesAF() {
        if (this.btn_ExportNoticesAF == null) {
            (this.btn_ExportNoticesAF = new JButton("Notices AF")).setPreferredSize(new Dimension(128, 40));
            this.btn_ExportNoticesAF.setMinimumSize(new Dimension(128, 40));
            this.btn_ExportNoticesAF.setMaximumSize(new Dimension(32767, 40));
            this.btn_ExportNoticesAF.setAlignmentX(0.5f);
            this.btn_ExportNoticesAF.setIcon(null);
            this.btn_ExportNoticesAF.setToolTipText("<html>R\u00e9cup\u00e8re les notices escales Air France</html>");
            this.btn_ExportNoticesAF.setMargin(new Insets(2, 2, 2, 2));
            this.btn_ExportNoticesAF.setFont(new Font("Tahoma", 0, 16));
            this.btn_ExportNoticesAF.addMouseListener(new MyMouseAdapter());
            this.btn_ExportNoticesAF.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ExportNoticesAF;
    }
    
    private JProgressBar getPrb_ProgressNoticesAF() {
        if (this.prb_ProgressNoticesAF == null) {
            (this.prb_ProgressNoticesAF = new JProgressBar()).setMinimumSize(new Dimension(10, 20));
            this.prb_ProgressNoticesAF.setPreferredSize(new Dimension(128, 20));
            this.prb_ProgressNoticesAF.setMaximumSize(new Dimension(32767, 20));
            this.prb_ProgressNoticesAF.setStringPainted(true);
            this.prb_ProgressNoticesAF.setString(" ");
            this.prb_ProgressNoticesAF.setFont(new Font("Tahoma", 0, 14));
            this.prb_ProgressNoticesAF.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
            this.prb_ProgressNoticesAF.setMinimum(0);
            this.prb_ProgressNoticesAF.setMaximum(100);
        }
        return this.prb_ProgressNoticesAF;
    }
    
    private JButton getBtn_ExportNoticesCE() {
        if (this.btn_ExportNoticesCE == null) {
            (this.btn_ExportNoticesCE = new JButton("Notices CE")).setPreferredSize(new Dimension(128, 40));
            this.btn_ExportNoticesCE.setMinimumSize(new Dimension(128, 40));
            this.btn_ExportNoticesCE.setMaximumSize(new Dimension(32767, 40));
            this.btn_ExportNoticesCE.setAlignmentX(0.5f);
            this.btn_ExportNoticesCE.setIcon(null);
            this.btn_ExportNoticesCE.setToolTipText("<html>R\u00e9cup\u00e8re les notices escales CE Lignes</html>");
            this.btn_ExportNoticesCE.setMargin(new Insets(2, 2, 2, 2));
            this.btn_ExportNoticesCE.setFont(new Font("Tahoma", 0, 16));
            this.btn_ExportNoticesCE.addMouseListener(new MyMouseAdapter());
            this.btn_ExportNoticesCE.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_ExportNoticesCE;
    }
    
    private JProgressBar getPrb_ProgressNoticesCE() {
        if (this.prb_ProgressNoticesCE == null) {
            (this.prb_ProgressNoticesCE = new JProgressBar()).setMinimumSize(new Dimension(10, 20));
            this.prb_ProgressNoticesCE.setPreferredSize(new Dimension(128, 20));
            this.prb_ProgressNoticesCE.setMaximumSize(new Dimension(32767, 20));
            this.prb_ProgressNoticesCE.setStringPainted(true);
            this.prb_ProgressNoticesCE.setString(" ");
            this.prb_ProgressNoticesCE.setFont(new Font("Tahoma", 0, 14));
            this.prb_ProgressNoticesCE.setForeground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
            this.prb_ProgressNoticesCE.setMinimum(0);
            this.prb_ProgressNoticesCE.setMaximum(100);
        }
        return this.prb_ProgressNoticesCE;
    }
    
    private JPanel getPnl_ActivitePaye() {
        if (this.pnl_ActivitePaye == null) {
            (this.pnl_ActivitePaye = new JPanel()).setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Activit\u00e9 & Paye", 4, 2, null, null));
            this.pnl_ActivitePaye.setLayout(new BoxLayout(this.pnl_ActivitePaye, 1));
            this.pnl_ActivitePaye.add(this.getBtn_Resume());
            this.pnl_ActivitePaye.add(this.getVerticalStrut_11());
            this.pnl_ActivitePaye.add(this.getBtn_Ep4());
            this.pnl_ActivitePaye.add(this.getVerticalStrut_12());
            this.pnl_ActivitePaye.add(this.getBtn_Stats());
        }
        return this.pnl_ActivitePaye;
    }
    
    private JButton getBtn_Resume() {
        if (this.btn_Resume == null) {
            (this.btn_Resume = new JButton()).setMinimumSize(new Dimension(128, 40));
            this.btn_Resume.setMaximumSize(new Dimension(32767, 40));
            this.btn_Resume.setPreferredSize(new Dimension(128, 40));
            this.btn_Resume.setAlignmentX(0.5f);
            this.btn_Resume.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Resume.setFont(new Font("Tahoma", 0, 16));
            this.btn_Resume.setText("R\u00e9sum\u00e9");
            this.btn_Resume.setToolTipText("Affiche un r\u00e9sum\u00e9 du mois");
            this.btn_Resume.addMouseListener(new MyMouseAdapter());
            this.btn_Resume.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Resume;
    }
    
    private JButton getBtn_Ep4() {
        if (this.btn_Ep4 == null) {
            (this.btn_Ep4 = new JButton()).setMinimumSize(new Dimension(128, 40));
            this.btn_Ep4.setMaximumSize(new Dimension(32767, 40));
            this.btn_Ep4.setPreferredSize(new Dimension(128, 40));
            this.btn_Ep4.setAlignmentX(0.5f);
            this.btn_Ep4.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Ep4.setFont(new Font("Tahoma", 0, 16));
            this.btn_Ep4.setText("EP4");
            this.btn_Ep4.setToolTipText("Affiche les EP4 du mois");
            this.btn_Ep4.addMouseListener(new MyMouseAdapter());
            this.btn_Ep4.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Ep4;
    }
    
    private JButton getBtn_Stats() {
        if (this.btn_Stats == null) {
            (this.btn_Stats = new JButton()).setMinimumSize(new Dimension(128, 40));
            this.btn_Stats.setMaximumSize(new Dimension(32767, 40));
            this.btn_Stats.setPreferredSize(new Dimension(128, 40));
            this.btn_Stats.setAlignmentX(0.5f);
            this.btn_Stats.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Stats.setFont(new Font("Tahoma", 0, 16));
            this.btn_Stats.setText("Statistiques");
            this.btn_Stats.setToolTipText("Affiche les statistiques des plannings fournis");
            this.btn_Stats.addMouseListener(new MyMouseAdapter());
            this.btn_Stats.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Stats;
    }
    
    private JPanel getPanel_center() {
        if (this.panel_center == null) {
            (this.panel_center = new JPanel()).setPreferredSize(new Dimension(512, 512));
            this.panel_center.setMinimumSize(new Dimension(512, 512));
            this.panel_center.setLayout(new BoxLayout(this.panel_center, 1));
            this.panel_center.add(this.getPnl_RosterAndDetails());
        }
        return this.panel_center;
    }
    
    private JPanel getPnl_RosterAndDetails() {
        if (this.pnl_RosterAndDetails == null) {
            (this.pnl_RosterAndDetails = new JPanel()).setLayout(new CardLayout(0, 0));
            this.pnl_RosterAndDetails.add(this.getPnl_Details(), "card_details");
            this.pnl_RosterAndDetails.add(this.getPnl_Roster(), "card_roster");
        }
        return this.pnl_RosterAndDetails;
    }
    
    private JPanel getPnl_Details() {
        if (this.pnl_Details == null) {
            (this.pnl_Details = new JPanel()).setLayout(new BoxLayout(this.pnl_Details, 3));
            this.pnl_Details.add(this.getLab_DetailsHeader());
            this.pnl_Details.add(this.getScrollPane_Details());
        }
        return this.pnl_Details;
    }
    
    private JLabel getLab_DetailsHeader() {
        if (this.lab_DetailsHeader == null) {
            (this.lab_DetailsHeader = new JLabel("Aide rapide")).setAlignmentX(0.5f);
            this.lab_DetailsHeader.setFont(new Font("SansSerif", 0, 16));
        }
        return this.lab_DetailsHeader;
    }
    
    private JScrollPane getScrollPane_Details() {
        if (this.scrollPane_Details == null) {
            (this.scrollPane_Details = new JScrollPane()).setViewportView(this.getTxtp_Details());
        }
        return this.scrollPane_Details;
    }
    
    private JTextPane getTxtp_Details() {
        if (this.txtp_Details == null) {
            (this.txtp_Details = new JTextPane()).setContentType("text/html");
            this.txtp_Details.setFont(new Font("SansSerif", 0, 16));
            this.txtp_Details.setText("<html>\r\n<style type=\"text/css\">\r\nbody {\r\n  font-family: sans-serif;\r\n  font-size: medium;\r\n  color : gray}\r\n \r\nh3 {\r\n  font-family: sans-serif;  \r\n  font-size: large; }\r\n  \r\nul{list-style-type: disc;}\r\n  </style>\r\n<body>\r\n<font face=\"verdana,arial,sans-serif\">\r\n<h3>Import du planning</h3>\r\n<ul>\r\n<li>Entrez le matricule (m + 6 premiers chiffres) et votre code (Pin + token)</li>\r\n<li>Cliquez sur le mois \u00e0 importer, ou choisissez un planning pr\u00e9alablement sauv\u00e9 avec le bouton 'Charger'</li>\r\n</ul>\r\n</font>\r\n\r\n<h3>Gestion de l'affichage</h3>\r\n<ul>\r\n<li>Clic gauche sur un \u00e9v\u00e8nement : affichage des d\u00e9tails</li>\r\n<li>Clic gauche dans la colonne des dates : masque / affiche les tron\u00e7ons de la rotation correspondante</li>\r\n<li>Clic droit  : active / d\u00e9sactive l'export de l'\u00e9v\u00e8nement</li>\r\n</ul>\r\n</font>\r\n\r\n<h3>Export</h3>\r\n<ul>\r\n<li>Choisissez une des m\u00e9thodes d'envoi du planning (Google, iCal etc.)</li>\r\n</ul>\r\n</br/>\r\n<p>Consultez l'aide compl\u00e8te sur le <font color=\"blue\"><a href=\"http://chopecrew.free.fr/?page_id=211\">site de ChopeCREW</a></p>\r\n</font></font>\r\n</body>\r\n</html>");
            this.txtp_Details.setEditable(false);
            this.txtp_Details.addMouseListener(new MyMouseAdapter());
            this.txtp_Details.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(final HyperlinkEvent e) {
                    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED && Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        catch (URISyntaxException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            });
        }
        return this.txtp_Details;
    }
    
    private JPanel getPnl_Roster() {
        if (this.pnl_Roster == null) {
            (this.pnl_Roster = new JPanel()).setLayout(new BoxLayout(this.pnl_Roster, 1));
            this.pnl_Roster.add(this.getLab_RosterHeader());
            this.pnl_Roster.add(this.getScrollPane_Roster());
            this.pnl_Roster.add(this.getVerticalStrut());
            this.pnl_Roster.add(this.getPnl_Filtres());
        }
        return this.pnl_Roster;
    }
    
    private JLabel getLab_RosterHeader() {
        if (this.lab_RosterHeader == null) {
            (this.lab_RosterHeader = new JLabel("Aucun planning")).setAlignmentX(0.5f);
            this.lab_RosterHeader.setHorizontalAlignment(0);
            this.lab_RosterHeader.setFont(new Font("SansSerif", 0, 16));
        }
        return this.lab_RosterHeader;
    }
    
    private JScrollPane getScrollPane_Roster() {
        if (this.scrollPane_Roster == null) {
            (this.scrollPane_Roster = new JScrollPane()).setVerticalScrollBarPolicy(22);
            this.scrollPane_Roster.setHorizontalScrollBarPolicy(31);
            this.scrollPane_Roster.setViewportView(this.getRosterTable());
        }
        return this.scrollPane_Roster;
    }
    
    private RosterTable getRosterTable() {
        if (this.rosterTable == null) {
            (this.rosterTable = new RosterTable(this)).setSelectionMode(0);
            this.rosterTable.setFocusable(false);
        }
        return this.rosterTable;
    }
    
    private JPanel getPnl_Filtres() {
        if (this.pnl_Filtres == null) {
            (this.pnl_Filtres = new JPanel()).setLayout(new BoxLayout(this.pnl_Filtres, 0));
            this.pnl_Filtres.add(this.getBtn_Blocs());
            this.pnl_Filtres.add(this.getHorizontalGlue_Filtres1());
            this.pnl_Filtres.add(this.getTbtn_RosterTableRetracted());
            this.pnl_Filtres.add(this.getTbtn_RosterTableReal());
            this.pnl_Filtres.add(this.getTbtn_RosterTableLocal());
        }
        return this.pnl_Filtres;
    }
    
    private JButton getBtn_Blocs() {
        if (this.btn_Blocs == null) {
            (this.btn_Blocs = new JButton()).setPreferredSize(new Dimension(56, 24));
            this.btn_Blocs.setMaximumSize(new Dimension(64, 24));
            this.btn_Blocs.setMargin(new Insets(1, 1, 1, 1));
            this.btn_Blocs.setFont(new Font("Tahoma", 0, 14));
            this.btn_Blocs.setText("M\u00e0J");
            this.btn_Blocs.setToolTipText("M\u00e0J des blocs r\u00e9alis\u00e9s");
            this.btn_Blocs.addMouseListener(new MyMouseAdapter());
            this.btn_Blocs.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Blocs;
    }
    
    private JButton getBtn_Req() {
        if (this.btn_Req == null) {
            (this.btn_Req = new JButton()).setPreferredSize(new Dimension(56, 24));
            this.btn_Req.setMaximumSize(new Dimension(64, 24));
            this.btn_Req.setMargin(new Insets(1, 1, 1, 1));
            this.btn_Req.setFont(new Font("Tahoma", 0, 14));
            this.btn_Req.setText("REQ.");
            this.btn_Req.setToolTipText("Requ\u00eateur");
            this.btn_Req.addMouseListener(new MyMouseAdapter());
            this.btn_Req.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Req;
    }
    
    private JButton getBtn_GoogleM0() {
        if (this.btn_GoogleM0 == null) {
            (this.btn_GoogleM0 = new JButton()).setPreferredSize(new Dimension(56, 24));
            this.btn_GoogleM0.setMaximumSize(new Dimension(64, 24));
            this.btn_GoogleM0.setMargin(new Insets(1, 1, 1, 1));
            this.btn_GoogleM0.setFont(new Font("Tahoma", 0, 14));
            this.btn_GoogleM0.setText("G.M0");
            this.btn_GoogleM0.setToolTipText("Google M0");
            this.btn_GoogleM0.addMouseListener(new MyMouseAdapter());
            this.btn_GoogleM0.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_GoogleM0;
    }
    
    private JButton getBtn_GoogleM1() {
        if (this.btn_GoogleM1 == null) {
            (this.btn_GoogleM1 = new JButton()).setPreferredSize(new Dimension(56, 24));
            this.btn_GoogleM1.setMaximumSize(new Dimension(64, 24));
            this.btn_GoogleM1.setMargin(new Insets(1, 1, 1, 1));
            this.btn_GoogleM1.setFont(new Font("Tahoma", 0, 14));
            this.btn_GoogleM1.setText("G.M1");
            this.btn_GoogleM1.setToolTipText("Google M1");
            this.btn_GoogleM1.addMouseListener(new MyMouseAdapter());
            this.btn_GoogleM1.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_GoogleM1;
    }
    
    private JToggleButton getTbtn_RosterTableRetracted() {
        if (this.tbtn_RosterTableRetracted == null) {
            (this.tbtn_RosterTableRetracted = new JToggleButton()).setPreferredSize(new Dimension(56, 24));
            this.tbtn_RosterTableRetracted.setMaximumSize(new Dimension(64, 24));
            this.tbtn_RosterTableRetracted.setMargin(new Insets(1, 1, 1, 1));
            this.tbtn_RosterTableRetracted.setFont(new Font("Tahoma", 0, 14));
            this.tbtn_RosterTableRetracted.setToolTipText("Affiche / masque les tron\u00e7ons de toutes les rotations");
            this.tbtn_RosterTableRetracted.addMouseListener(new MyMouseAdapter());
            this.tbtn_RosterTableRetracted.addKeyListener(new MyKeyAdapter());
        }
        return this.tbtn_RosterTableRetracted;
    }
    
    private JToggleButton getTbtn_RosterTableReal() {
        if (this.tbtn_RosterTableReal == null) {
            (this.tbtn_RosterTableReal = new JToggleButton()).setPreferredSize(new Dimension(56, 24));
            this.tbtn_RosterTableReal.setMaximumSize(new Dimension(64, 24));
            this.tbtn_RosterTableReal.setMargin(new Insets(1, 1, 1, 1));
            this.tbtn_RosterTableReal.setFont(new Font("Tahoma", 0, 14));
            this.tbtn_RosterTableReal.setToolTipText("Heures programm\u00e9es / Heures r\u00e9alis\u00e9es");
            this.tbtn_RosterTableReal.addMouseListener(new MyMouseAdapter());
            this.tbtn_RosterTableReal.addKeyListener(new MyKeyAdapter());
        }
        return this.tbtn_RosterTableReal;
    }
    
    private JToggleButton getTbtn_RosterTableLocal() {
        if (this.tbtn_RosterTableLocal == null) {
            (this.tbtn_RosterTableLocal = new JToggleButton()).setPreferredSize(new Dimension(56, 24));
            this.tbtn_RosterTableLocal.setMaximumSize(new Dimension(64, 24));
            this.tbtn_RosterTableLocal.setMargin(new Insets(1, 1, 1, 1));
            this.tbtn_RosterTableLocal.setFont(new Font("Tahoma", 0, 14));
            this.tbtn_RosterTableLocal.setToolTipText("Heures de Paris / Heures locales");
            this.tbtn_RosterTableLocal.addMouseListener(new MyMouseAdapter());
            this.tbtn_RosterTableLocal.addKeyListener(new MyKeyAdapter());
        }
        return this.tbtn_RosterTableLocal;
    }
    
    private Component getVerticalStrut_North() {
        if (this.verticalStrut_North == null) {
            (this.verticalStrut_North = Box.createVerticalStrut(63)).setPreferredSize(new Dimension(0, 16));
            this.verticalStrut_North.setMinimumSize(new Dimension(0, 16));
        }
        return this.verticalStrut_North;
    }
    
    private Component getVerticalStrut_South() {
        if (this.verticalStrut_South == null) {
            (this.verticalStrut_South = Box.createVerticalStrut(63)).setMinimumSize(new Dimension(0, 16));
            this.verticalStrut_South.setPreferredSize(new Dimension(0, 16));
        }
        return this.verticalStrut_South;
    }
    
    private Component getHorizontalStrut_WestLeft() {
        if (this.horizontalStrut_WestLeft == null) {
            (this.horizontalStrut_WestLeft = Box.createHorizontalStrut(20)).setPreferredSize(new Dimension(16, 0));
            this.horizontalStrut_WestLeft.setMinimumSize(new Dimension(16, 0));
        }
        return this.horizontalStrut_WestLeft;
    }
    
    private Component getHorizontalStrut_WestRight() {
        if (this.horizontalStrut_WestRight == null) {
            (this.horizontalStrut_WestRight = Box.createHorizontalStrut(20)).setMinimumSize(new Dimension(16, 0));
            this.horizontalStrut_WestRight.setPreferredSize(new Dimension(16, 0));
        }
        return this.horizontalStrut_WestRight;
    }
    
    private Component getHorizontalStrut_EastLeft() {
        if (this.horizontalStrut_EastLeft == null) {
            (this.horizontalStrut_EastLeft = Box.createHorizontalStrut(20)).setPreferredSize(new Dimension(16, 0));
            this.horizontalStrut_EastLeft.setMinimumSize(new Dimension(16, 0));
        }
        return this.horizontalStrut_EastLeft;
    }
    
    private Component getHorizontalStrut_EastRight() {
        if (this.horizontalStrut_EastRight == null) {
            (this.horizontalStrut_EastRight = Box.createHorizontalStrut(20)).setPreferredSize(new Dimension(16, 0));
            this.horizontalStrut_EastRight.setMinimumSize(new Dimension(16, 0));
        }
        return this.horizontalStrut_EastRight;
    }
    
    private Component getVerticalGlue_W1() {
        if (this.verticalGlue_W1 == null) {
            this.verticalGlue_W1 = Box.createVerticalGlue();
        }
        return this.verticalGlue_W1;
    }
    
    private Component getVerticalGlue_W2() {
        if (this.verticalGlue_W2 == null) {
            this.verticalGlue_W2 = Box.createVerticalGlue();
        }
        return this.verticalGlue_W2;
    }
    
    private Component getVerticalGlue_W3() {
        if (this.verticalGlue_W3 == null) {
            this.verticalGlue_W3 = Box.createVerticalGlue();
        }
        return this.verticalGlue_W3;
    }
    
    private Component getVerticalGlue_W4() {
        if (this.verticalGlue_W4 == null) {
            this.verticalGlue_W4 = Box.createVerticalGlue();
        }
        return this.verticalGlue_W4;
    }
    
    private Component getVerticalGlue_E1() {
        if (this.verticalGlue_E1 == null) {
            this.verticalGlue_E1 = Box.createVerticalGlue();
        }
        return this.verticalGlue_E1;
    }
    
    private Component getVerticalGlue_E2() {
        if (this.verticalGlue_E2 == null) {
            this.verticalGlue_E2 = Box.createVerticalGlue();
        }
        return this.verticalGlue_E2;
    }
    
    private Component getHorizontalStrut_Footer1() {
        if (this.horizontalStrut_Footer1 == null) {
            (this.horizontalStrut_Footer1 = Box.createHorizontalStrut(20)).setMaximumSize(new Dimension(20, 20));
        }
        return this.horizontalStrut_Footer1;
    }
    
    private Component getHorizontalStrut_Footer2() {
        if (this.horizontalStrut_Footer2 == null) {
            (this.horizontalStrut_Footer2 = Box.createHorizontalStrut(20)).setMaximumSize(new Dimension(20, 20));
        }
        return this.horizontalStrut_Footer2;
    }
    
    private Component getVerticalStrut_Exportation1() {
        if (this.verticalStrut_Exportation1 == null) {
            (this.verticalStrut_Exportation1 = Box.createVerticalStrut(14)).setMaximumSize(new Dimension(32767, 12));
        }
        return this.verticalStrut_Exportation1;
    }
    
    private Component getVerticalStrut_Exportation2() {
        if (this.verticalStrut_Exportation2 == null) {
            (this.verticalStrut_Exportation2 = Box.createVerticalStrut(14)).setMaximumSize(new Dimension(32767, 12));
        }
        return this.verticalStrut_Exportation2;
    }
    
    private Component getVerticalStrut_Notices1() {
        if (this.verticalStrut_Notices1 == null) {
            (this.verticalStrut_Notices1 = Box.createVerticalStrut(14)).setMaximumSize(new Dimension(32767, 12));
        }
        return this.verticalStrut_Notices1;
    }
    
    private Component getHorizontalGlue_Filtres1() {
        if (this.horizontalGlue_Filtres1 == null) {
            this.horizontalGlue_Filtres1 = Box.createHorizontalGlue();
        }
        return this.horizontalGlue_Filtres1;
    }
    
    private Component getVerticalStrut() {
        if (this.verticalStrut == null) {
            this.verticalStrut = Box.createVerticalStrut(5);
        }
        return this.verticalStrut;
    }
    
    private Component getVerticalStrut_1() {
        if (this.verticalStrut_1 == null) {
            this.verticalStrut_1 = Box.createVerticalStrut(8);
        }
        return this.verticalStrut_1;
    }
    
    private Component getVerticalStrut_2() {
        if (this.verticalStrut_2 == null) {
            this.verticalStrut_2 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_2;
    }
    
    private Component getVerticalStrut_3() {
        if (this.verticalStrut_3 == null) {
            this.verticalStrut_3 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_3;
    }
    
    private Component getVerticalStrut_4() {
        if (this.verticalStrut_4 == null) {
            this.verticalStrut_4 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_4;
    }
    
    private Component getVerticalStrut_5() {
        if (this.verticalStrut_5 == null) {
            this.verticalStrut_5 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_5;
    }
    
    private Component getVerticalStrut_6() {
        if (this.verticalStrut_6 == null) {
            this.verticalStrut_6 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_6;
    }
    
    private Component getVerticalStrut_7() {
        if (this.verticalStrut_7 == null) {
            this.verticalStrut_7 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_7;
    }
    
    private Component getVerticalStrut_8() {
        if (this.verticalStrut_8 == null) {
            this.verticalStrut_8 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_8;
    }
    
    private Component getVerticalStrut_9() {
        if (this.verticalStrut_9 == null) {
            this.verticalStrut_9 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_9;
    }
    
    private Component getVerticalStrut_10() {
        if (this.verticalStrut_10 == null) {
            this.verticalStrut_10 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_10;
    }
    
    private Component getVerticalStrut_11() {
        if (this.verticalStrut_11 == null) {
            this.verticalStrut_11 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_11;
    }
    
    private Component getVerticalStrut_12() {
        if (this.verticalStrut_12 == null) {
            this.verticalStrut_12 = Box.createVerticalStrut(2);
        }
        return this.verticalStrut_12;
    }
    
    class MyWindowAdapter extends WindowAdapter
    {
        @Override
        public void windowClosing(final WindowEvent e) {
            ChopeCrew.options.savePreferences();
            ChopeCrew.donneesPerso_PNT.savePreferences();
            ChopeCrew.donneesPerso_PNC.savePreferences();
            System.exit(0);
        }
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10) {
                if (e.getSource() == MainFrame.this.tfld_Login) {
                    MainFrame.this.pwdf_Password.requestFocusInWindow();
                }
                else if (e.getSource() == MainFrame.this.pwdf_Password) {
                    MainFrame.this.actionBtnChope(0, false);
                }
                else if (e.getSource() == MainFrame.this.btn_ChopeM0_Ins || e.getSource() == MainFrame.this.btn_ChopeM0_100) {
                    MainFrame.this.actionBtnChope(0, false);
                }
                else if (e.getSource() == MainFrame.this.btn_ChopeM1_Ins || e.getSource() == MainFrame.this.btn_ChopeM1_100) {
                    MainFrame.this.actionBtnChope(1, false);
                }
                else if (e.getSource() == MainFrame.this.btn_ChopeM2_Ins) {
                    MainFrame.this.actionBtnChope(2, false);
                }
                else if (e.getSource() == MainFrame.this.btn_Charger) {
                    MainFrame.this.actionBtnChargeXML();
                }
                else if (e.getSource() == MainFrame.this.btn_Sauver) {
                    MainFrame.this.actionBtnSauveXML();
                }
                else if (e.getSource() == MainFrame.this.btn_Aide) {
                    MainFrame.this.actionBtnAide();
                }
                else if (e.getSource() == MainFrame.this.btn_Profil) {
                    MainFrame.this.actionBtnProfil();
                }
                else if (e.getSource() == MainFrame.this.btn_Configuration) {
                    MainFrame.this.actionBtnConfiguration();
                }
                else if (e.getSource() == MainFrame.this.btn_ExportGoogle) {
                    MainFrame.this.actionBtnExportGoogleAgenda();
                }
                else if (e.getSource() == MainFrame.this.btn_ExportNightStop) {
                    MainFrame.this.actionBtnExportNightStop();
                }
                else if (e.getSource() == MainFrame.this.btn_ExportPDA) {
                    MainFrame.this.actionBtnPDA();
                }
                else if (e.getSource() == MainFrame.this.btn_ExportPDF) {
                    MainFrame.this.actionBtnWEB();
                }
                else if (e.getSource() == MainFrame.this.btn_ExportNoticesAF) {
                    MainFrame.this.actionBtnExportNoticesAF();
                }
                else if (e.getSource() == MainFrame.this.btn_ExportNoticesCE) {
                    MainFrame.this.actionBtnExportNoticesCE();
                }
                else if (e.getSource() == MainFrame.this.btn_Resume) {
                    MainFrame.this.actionBtnResume();
                }
                else if (e.getSource() == MainFrame.this.btn_Ep4) {
                    MainFrame.this.actionBtnEp4();
                }
                else if (e.getSource() == MainFrame.this.btn_Stats) {
                    MainFrame.this.actionBtnStats();
                }
                else if (e.getSource() == MainFrame.this.btn_Blocs) {
                    MainFrame.this.actionBtnBlocs();
                }
                else if (e.getSource() == MainFrame.this.btn_Req) {
                    MainFrame.this.actionBtnRequest();
                }
                else if (e.getSource() == MainFrame.this.btn_GoogleM0) {
                    MainFrame.this.actionBtnGoogleM0();
                }
                else if (e.getSource() == MainFrame.this.btn_GoogleM1) {
                    MainFrame.this.actionBtnGoogleM1();
                }
                else if (e.getSource() == MainFrame.this.tbtn_RosterTableRetracted) {
                    MainFrame.this.tbtn_RosterTableRetracted.doClick();
                    MainFrame.this.actionTbtnRosterTableRetracted(MainFrame.this.tbtn_RosterTableRetracted.isSelected());
                }
                else if (e.getSource() == MainFrame.this.tbtn_RosterTableReal) {
                    MainFrame.this.tbtn_RosterTableReal.doClick();
                    MainFrame.this.actionTbtnRosterTableReal(MainFrame.this.tbtn_RosterTableReal.isSelected());
                }
                else if (e.getSource() == MainFrame.this.tbtn_RosterTableLocal) {
                    MainFrame.this.tbtn_RosterTableLocal.doClick();
                    MainFrame.this.actionTbtnRosterTableLocal(MainFrame.this.tbtn_RosterTableLocal.isSelected());
                }
            }
        }
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseEntered(final MouseEvent e) {
            if (e.getSource() == MainFrame.this.jContentPane && ChopeCrew.analyse.listCrew.size() > 0) {
                MainFrame.this.showRoster();
            }
            super.mouseEntered(e);
        }
        
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == MainFrame.this.lab_Banner) {
                if (!e.isControlDown() && e.getButton() == 1 && e.getClickCount() == 2) {
                    MainFrame.this.actionLogoClicGauche(false);
                }
                else if (e.isControlDown() && e.getButton() == 1 && e.getClickCount() == 2) {
                    MainFrame.this.actionLogoClicGauche(true);
                }
                else if (e.getButton() == 3 && e.getClickCount() == 2) {
                    MainFrame.this.actionLogoClicDroit();
                }
            }
            else if (e.getSource() == MainFrame.this.btn_ChopeM0_100 || e.getSource() == MainFrame.this.btn_ChopeM0_Ins) {
                if (e.getButton() == 1) {
                    System.out.println("M NORM");
                    MainFrame.this.actionBtnChope(0, false);
                }
                else if (e.getButton() == 3) {
                    System.out.println("M FLASH");
                    MainFrame.this.actionBtnChope(0, true);
                }
            }
            else if (e.getSource() == MainFrame.this.btn_ChopeM1_100 || e.getSource() == MainFrame.this.btn_ChopeM1_Ins) {
                if (e.getButton() == 1) {
                    System.out.println("M+1 NORM");
                    MainFrame.this.actionBtnChope(1, false);
                }
                else if (e.getButton() == 3) {
                    System.out.println("M+1 FLASH");
                    MainFrame.this.actionBtnChope(1, true);
                }
            }
            else if (e.getSource() == MainFrame.this.btn_ChopeM2_Ins) {
                if (e.getButton() == 1) {
                    System.out.println("M+2 NORM");
                    MainFrame.this.actionBtnChope(2, false);
                }
                else if (e.getButton() == 3) {
                    System.out.println("M+2 FLASH");
                    MainFrame.this.actionBtnChope(2, true);
                }
            }
            else if (e.getSource() == MainFrame.this.btn_Charger) {
                MainFrame.this.actionBtnChargeXML();
            }
            else if (e.getSource() == MainFrame.this.btn_Sauver) {
                MainFrame.this.actionBtnSauveXML();
            }
            else if (e.getSource() == MainFrame.this.btn_Aide) {
                MainFrame.this.actionBtnAide();
            }
            else if (e.getSource() == MainFrame.this.btn_Profil) {
                MainFrame.this.actionBtnProfil();
            }
            else if (e.getSource() == MainFrame.this.btn_Configuration) {
                MainFrame.this.actionBtnConfiguration();
            }
            else if (e.getSource() == MainFrame.this.btn_ExportGoogle) {
                MainFrame.this.actionBtnExportGoogleAgenda();
            }
            else if (e.getSource() == MainFrame.this.btn_ExportNightStop) {
                MainFrame.this.actionBtnExportNightStop();
            }
            else if (e.getSource() == MainFrame.this.btn_ExportPDA) {
                MainFrame.this.actionBtnPDA();
            }
            else if (e.getSource() == MainFrame.this.btn_ExportPDF) {
                MainFrame.this.actionBtnWEB();
            }
            else if (e.getSource() == MainFrame.this.btn_ExportNoticesAF) {
                MainFrame.this.actionBtnExportNoticesAF();
            }
            else if (e.getSource() == MainFrame.this.btn_ExportNoticesCE) {
                MainFrame.this.actionBtnExportNoticesCE();
            }
            else if (e.getSource() == MainFrame.this.btn_Resume) {
                MainFrame.this.actionBtnResume();
            }
            else if (e.getSource() == MainFrame.this.btn_Ep4) {
                MainFrame.this.actionBtnEp4();
            }
            else if (e.getSource() == MainFrame.this.btn_Stats) {
                MainFrame.this.actionBtnStats();
            }
            else if (e.getSource() == MainFrame.this.txtp_Details) {
                if (ChopeCrew.analyse.listCrew.size() > 0) {
                    MainFrame.this.showRoster();
                }
            }
            else if (e.getSource() == MainFrame.this.btn_Blocs) {
                MainFrame.this.actionBtnBlocs();
            }
            else if (e.getSource() == MainFrame.this.btn_Req) {
                if (e.isControlDown()) {
                    MainFrame.this.actionBtnRequest();
                }
                else if (e.getButton() == 1 && e.getClickCount() == 2) {
                    MainFrame.this.actionBtnRequestClicGauche();
                }
                else if (e.getButton() == 3 && e.getClickCount() == 2) {
                    MainFrame.this.actionBtnRequestClicDroit();
                }
            }
            else if (e.getSource() == MainFrame.this.btn_GoogleM0) {
                MainFrame.this.actionBtnGoogleM0();
            }
            else if (e.getSource() == MainFrame.this.btn_GoogleM1) {
                MainFrame.this.actionBtnGoogleM1();
            }
            else if (e.getSource() == MainFrame.this.tbtn_RosterTableRetracted) {
                MainFrame.this.actionTbtnRosterTableRetracted(MainFrame.this.tbtn_RosterTableRetracted.isSelected());
            }
            else if (e.getSource() == MainFrame.this.tbtn_RosterTableReal) {
                MainFrame.this.actionTbtnRosterTableReal(MainFrame.this.tbtn_RosterTableReal.isSelected());
            }
            else if (e.getSource() == MainFrame.this.tbtn_RosterTableLocal) {
                MainFrame.this.actionTbtnRosterTableLocal(MainFrame.this.tbtn_RosterTableLocal.isSelected());
            }
        }
    }
}
