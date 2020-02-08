// 
// Decompiled by Procyon v0.5.36
// 

package chopeCrew;

import java.awt.Component;
import java.awt.Frame;
import ccGUI.Dlg_AdresseIpn;
import ccUtils.Utils;
import java.util.prefs.Preferences;
import javax.swing.ToolTipManager;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import ccGUI.OverridingLookAndFeel;
import java.util.Locale;
import com.google.api.services.calendar.Calendar;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import ccGUI.MainFrame;
import org.apache.http.impl.client.CloseableHttpClient;
import ccImport.ConnexionGoogle;
import ccImport.ConnexionRequeteur;
import ccImport.ConnexionCrew;
import ccEngine.AnalyseCrew;
import ccOptions.DonneesPerso_PNC;
import ccOptions.DonneesPerso_PNT;
import ccOptions.Options;

public class ChopeCrew
{
    public static Options options;
    public static DonneesPerso_PNT donneesPerso_PNT;
    public static DonneesPerso_PNC donneesPerso_PNC;
    public static AnalyseCrew analyse;
    public static ConnexionCrew conCrew;
    public static ConnexionRequeteur conRequeteur;
    public static ConnexionGoogle conGoogle;
    public static CloseableHttpClient httpClient;
    public static MainFrame mf;
    public static boolean isKeepAliving;
    public static final String APPLICATION_NAME = "ChopeCREW";
    public static final String CLIENT_ID = "1002555116964-fmmh79tuh5sukg5esg2tffc9p6u9u5le.apps.googleusercontent.com";
    public static final String CLIENT_SECRET = "s-t931CHmC-1AvrWgO3bh9uP";
    public static JsonFactory json_factory;
    public static HttpTransport httpTransport;
    public static FileDataStoreFactory dataStoreFactory;
    public static Calendar client;
    public static final String version = "3.8";
    public static final int buildNumber = 20200208;
    public static boolean premiereUtilisationVersion;
    
    static {
        ChopeCrew.options = null;
        ChopeCrew.donneesPerso_PNT = null;
        ChopeCrew.donneesPerso_PNC = null;
        ChopeCrew.analyse = null;
        ChopeCrew.conCrew = null;
        ChopeCrew.conRequeteur = null;
        ChopeCrew.conGoogle = null;
        ChopeCrew.httpClient = null;
        ChopeCrew.mf = null;
        ChopeCrew.isKeepAliving = false;
        ChopeCrew.json_factory = null;
        ChopeCrew.httpTransport = null;
        ChopeCrew.dataStoreFactory = null;
        ChopeCrew.client = null;
        ChopeCrew.premiereUtilisationVersion = false;
    }
    
    public static void main(final String[] args) {
        try {
            Locale.setDefault(Locale.FRANCE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            UIManager.setLookAndFeel(new OverridingLookAndFeel());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ToolTipManager.sharedInstance().setDismissDelay(15000);
        final String lastVersionUsed = Preferences.userRoot().node("ChopeCREW").get("version", "");
        if (!lastVersionUsed.equals("3.8") && !"3.8".contains("BETA")) {
            try {
                Preferences.userRoot().node("ChopeCREW").put("version", "3.8");
                ChopeCrew.premiereUtilisationVersion = true;
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        (ChopeCrew.options = new Options()).initOptions();
        ChopeCrew.options.loadPreferences();
        (ChopeCrew.donneesPerso_PNT = new DonneesPerso_PNT()).initDonneesPerso();
        ChopeCrew.donneesPerso_PNT.loadPreferences();
        (ChopeCrew.donneesPerso_PNC = new DonneesPerso_PNC()).initDonneesPerso();
        ChopeCrew.donneesPerso_PNC.loadPreferences();
        ChopeCrew.analyse = new AnalyseCrew();
        ChopeCrew.conCrew = new ConnexionCrew();
        ChopeCrew.conRequeteur = new ConnexionRequeteur();
        ChopeCrew.conGoogle = new ConnexionGoogle();
        ChopeCrew.mf = new MainFrame();
        final String md5UrlIpn = "f2a44c759979aca396091ad49af2d650";
        while (!Utils.getMD5(ChopeCrew.options.urlIPN).equals(md5UrlIpn)) {
            final Dlg_AdresseIpn dlgAdresseIpn = new Dlg_AdresseIpn(ChopeCrew.mf);
            dlgAdresseIpn.setLocationRelativeTo(null);
            dlgAdresseIpn.setVisible(true);
        }
    }
}
