// 
// Decompiled by Procyon v0.5.36
// 

package ccExport;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.net.URLEncoder;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.io.IOException;
import ccUtils.Utils;
import java.io.BufferedInputStream;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import java.util.ArrayList;
import java.net.URL;
import ccEngine.AnalyseCrew;
import java.net.CookieHandler;
import java.net.CookieManager;
import javax.net.ssl.HttpsURLConnection;
import java.util.Observable;

public class Export_NightStop3 extends Observable
{
    public static final String MSG_OK = "Planning NightStop envoy\u00e9 !";
    public static final String MSG_ERREUR = "Erreur d'identifiant ou de mot de passe !";
    public static final String MSG_EXCEPTION = "Erreur impr\u00e9vue !";
    public static final String MSG_CONNECTED = "Connect\u00e9 NightStop";
    private String login;
    private String password;
    private HttpsURLConnection huc;
    private CookieManager msCookieManager;
    private int progressValue;
    
    public Export_NightStop3() {
        this.login = null;
        this.password = null;
        this.huc = null;
        this.msCookieManager = null;
        this.progressValue = 0;
        CookieHandler.setDefault(this.msCookieManager = new CookieManager());
    }
    
    public int sendPlanning(final AnalyseCrew analyse, final String login, final String password) {
        this.login = login;
        this.password = password;
        final int z = this.connect();
        if (z == 0) {
            this.setChanged();
            this.notifyObservers("Erreur impr\u00e9vue !");
            this.huc.disconnect();
            return 0;
        }
        if (z == -1) {
            this.setChanged();
            this.notifyObservers("Erreur d'identifiant ou de mot de passe !");
            this.huc.disconnect();
            return -1;
        }
        this.setChanged();
        this.notifyObservers("Connect\u00e9 NightStop");
        this.progressValue = 20;
        this.setChanged();
        this.notifyObservers(this.progressValue);
        this.exportPlanningNightStop(analyse);
        System.out.println("Planning NightStop envoy\u00e9 !");
        this.setChanged();
        this.notifyObservers("Planning NightStop envoy\u00e9 !");
        return 1;
    }
    
    private int connect() {
        URL url = null;
        try {
            url = new URL("https://nightstop.top/FR/index.php");
            (this.huc = (HttpsURLConnection)url.openConnection()).setRequestMethod("POST");
            this.huc.setDoOutput(true);
            final List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("aem", this.login));
            params.add(new BasicNameValuePair("mdp", this.password));
            params.add(new BasicNameValuePair("ok", ""));
            params.add(new BasicNameValuePair("activation", ""));
            final OutputStream os = this.huc.getOutputStream();
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(this.getQuery(params));
            writer.flush();
            writer.close();
            os.close();
            this.huc.connect();
            final InputStream is = new BufferedInputStream(this.huc.getInputStream());
            final String body = Utils.streamToString(is, "ISO-8859-1");
            if (body.indexOf("erreur_identifiant") != -1 && this.huc.getResponseCode() == 200) {
                System.out.println("Acc\u00e8s refus\u00e9 !");
                return -1;
            }
            System.out.println("Connect\u00e9 NightStop");
            return 1;
        }
        catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    private void exportPlanningNightStop(final AnalyseCrew ac) {
        final Export_Ical ical = new Export_Ical();
        ical.createICal(ac);
        final String content = ical.getICal().replaceAll("\r\n", "\\\r\\\n");
        final String yearMonth = String.valueOf(ac.anneeInt) + "-" + ac.moisNum;
        this.progressValue = 40;
        this.setChanged();
        this.notifyObservers(this.progressValue);
        final ArrayList<NameValuePair> postDataArray = new ArrayList<NameValuePair>();
        postDataArray.add(new BasicNameValuePair("mois", yearMonth));
        postDataArray.add(new BasicNameValuePair("planning", content));
        postDataArray.add(new BasicNameValuePair("chope", "1"));
        postDataArray.add(new BasicNameValuePair("ok", ""));
        try {
            final URL url = new URL("https://nightstop.top/SRC/planning_importer.php");
            (this.huc = (HttpsURLConnection)url.openConnection()).setRequestMethod("POST");
            this.huc.setDoOutput(true);
            final OutputStream os = this.huc.getOutputStream();
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(this.getQuery(postDataArray));
            writer.flush();
            writer.close();
            os.close();
            this.huc.connect();
            this.progressValue = 60;
            this.setChanged();
            this.notifyObservers(this.progressValue);
            final InputStream is = new BufferedInputStream(this.huc.getInputStream());
            final String body = Utils.streamToString(is, "ISO-8859-1");
            if (body.indexOf("location.href =") < 0) {
                System.out.println("Erreur lors de l'envoi NightStop");
            }
            this.progressValue = 80;
            this.setChanged();
            this.notifyObservers(this.progressValue);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.huc.disconnect();
    }
    
    private String getQuery(final List<NameValuePair> params) throws UnsupportedEncodingException {
        final StringBuilder result = new StringBuilder();
        boolean first = true;
        for (final NameValuePair pair : params) {
            if (first) {
                first = false;
            }
            else {
                result.append("&");
            }
            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        return result.toString();
    }
    
    public int getProgressValue() {
        return this.progressValue;
    }
}
