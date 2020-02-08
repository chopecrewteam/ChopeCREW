// 
// Decompiled by Procyon v0.5.36
// 

package ccImport;

import java.io.File;
import ccUtils.Utils;
import org.apache.http.entity.ContentType;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.HttpEntity;
import java.util.List;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import java.net.URI;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import chopeCrew.ChopeCrew;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import ccEngine.AnalyseCrew;
import ccStructures.Escale;
import java.util.ArrayList;
import java.util.Observable;

public class ConnexionEdito extends Observable
{
    public static final String MSG_OK = "Notices AF export\u00e9es !";
    public static final String MSG_ERREUR = "Erreur d'identifiant ou de mot de passe";
    public static final String MSG_EXCEPTION = "Erreur impr\u00e9vue";
    public static final String MSG_CONNECTED = "Connect\u00e9 Edito !";
    private String login;
    private String password;
    private String urlEdito;
    private String urlIpn;
    private String repertoire;
    private ArrayList<Escale> listEscalesAF;
    private int progressValue;
    
    public ConnexionEdito() {
        this.login = null;
        this.password = null;
        this.urlEdito = null;
        this.urlIpn = null;
        this.repertoire = null;
        this.listEscalesAF = null;
        this.progressValue = 0;
        this.listEscalesAF = new ArrayList<Escale>();
    }
    
    public int chopeNoticesAF(final AnalyseCrew analyse, final String login, final String password, final String urlIpn, final String repertoire, final boolean isTotal) {
        this.login = login;
        this.password = password;
        this.urlIpn = urlIpn;
        this.urlEdito = urlIpn.replaceAll("ipn", "edito");
        this.repertoire = repertoire;
        final ArrayList<String> listDecouchers = new ArrayList<String>();
        for (final Rotation rotation : analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                if (sv.isDecoucher) {
                    final Troncon lastTronconSv = sv.listTroncon.get(sv.listTroncon.size() - 1);
                    if (listDecouchers.contains(lastTronconSv.to)) {
                        continue;
                    }
                    listDecouchers.add(lastTronconSv.to);
                }
            }
        }
        try {
            final String ficSource = "/res_databases/dbESCALES.txt";
            final InputStream is = this.getClass().getResourceAsStream(ficSource);
            final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String tmp;
            while ((tmp = in.readLine()) != null) {
                if (!tmp.equals("") && !tmp.contains("//")) {
                    final Escale escale = new Escale();
                    escale.code = tmp.split(";")[0].trim();
                    escale.nom = tmp.split(";")[1].trim();
                    if (isTotal) {
                        this.listEscalesAF.add(escale);
                    }
                    else {
                        if (!listDecouchers.contains(escale.code)) {
                            continue;
                        }
                        this.listEscalesAF.add(escale);
                    }
                }
            }
            in.close();
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (ChopeCrew.httpClient == null) {
            final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            ChopeCrew.httpClient = HttpClients.custom().setConnectionManager(cm).disableRedirectHandling().build();
        }
        final int z = this.connect();
        if (z == 0) {
            this.setChanged();
            this.notifyObservers("Erreur impr\u00e9vue");
            return 0;
        }
        if (z == -1) {
            this.setChanged();
            this.notifyObservers("Erreur d'identifiant ou de mot de passe");
            return -1;
        }
        this.setChanged();
        this.notifyObservers("Connect\u00e9 Edito !");
        this.importNoticesAF();
        this.setChanged();
        this.notifyObservers("Notices AF export\u00e9es !");
        return 1;
    }
    
    private int connect() {
        final HttpGet get = new HttpGet();
        final HttpPost post = new HttpPost();
        System.out.println("Connexion Edito ...");
        try {
            URI url = new URI("https://" + this.urlIpn);
            get.setURI(url);
            CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            if (response.getFirstHeader("Location") != null && response.getFirstHeader("Location").getValue().contains("siteminderagent")) {
                final String urlRedirection = response.getFirstHeader("Location").getValue();
                System.out.println("Autorisation ...");
                url = new URI(urlRedirection);
                post.setURI(url);
                final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("USERNAME", this.login));
                nvps.add(new BasicNameValuePair("PASSWORD", this.password));
                nvps.add(new BasicNameValuePair("target", "https://" + this.urlIpn));
                nvps.add(new BasicNameValuePair("smagentname", "agt_" + this.urlIpn + "_dmz-internet"));
                post.setEntity(new UrlEncodedFormEntity(nvps));
                response = ChopeCrew.httpClient.execute((HttpUriRequest)post);
                post.releaseConnection();
                if (response.getFirstHeader("Location") == null || response.getFirstHeader("Location").getValue().contains("siteminderagent")) {
                    System.out.println("Acc\u00e8s refus\u00e9 !");
                    return -1;
                }
                System.out.println("Acc\u00e8s accord\u00e9 !");
            }
            url = new URI("https://" + this.urlEdito);
            get.setURI(url);
            ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("mode", ""));
            url = new URI("https://" + this.urlEdito + "/editoWeb/docsVol.do?action=init");
            post.setURI(url);
            post.setEntity(new UrlEncodedFormEntity(nvps));
            ChopeCrew.httpClient.execute((HttpUriRequest)post);
            post.releaseConnection();
            System.out.println("Connect\u00e9 Edito");
            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec connexion");
            get.releaseConnection();
            post.releaseConnection();
            return 0;
        }
    }
    
    private void importNoticesAF() {
        this.progressValue = 0;
        final HttpGet get = new HttpGet();
        final HttpPost post = new HttpPost();
        int nbNoticesRecues = 0;
        for (final Escale escaleAF : this.listEscalesAF) {
            final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("typeVoletList[15].selected", "on"));
            nvps.add(new BasicNameValuePair("critereEscale", escaleAF.code));
            nvps.add(new BasicNameValuePair("action", "search"));
            try {
                URI url = new URI("https://" + this.urlEdito + "/editoWeb" + "/docsVol.do");
                post.setURI(url);
                post.setEntity(new UrlEncodedFormEntity(nvps));
                CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)post);
                final String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
                final String source = Utils.streamToString(response.getEntity().getContent(), charset);
                post.releaseConnection();
                String idVolet = "";
                final String motif = "action=viewPDF&idVolet=";
                final int idx = source.indexOf(motif);
                if (idx == -1) {
                    continue;
                }
                idVolet = source.substring(idx + motif.length(), source.indexOf("'", idx));
                if (idVolet == null) {
                    continue;
                }
                url = new URI("https://" + this.urlEdito + "/editoWeb" + "/afficherVolets.do?action=viewPDF&idVolet=" + idVolet);
                get.setURI(url);
                response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                if (response.getStatusLine().getStatusCode() == 200) {
                    final File f = new File(this.repertoire);
                    f.mkdirs();
                    String filename = ChopeCrew.options.libelleNoticesAF;
                    filename = filename.replaceAll("%c", escaleAF.code);
                    filename = filename.replaceAll("%n", escaleAF.nom);
                    final String body = Utils.streamToString(response.getEntity().getContent(), "ISO-8859-1");
                    Utils.saveToFile(body, String.valueOf(f.getAbsolutePath()) + File.separator + filename + ".pdf", "ISO-8859-1");
                    ++nbNoticesRecues;
                    this.progressValue = nbNoticesRecues * 100 / this.listEscalesAF.size();
                    System.out.println("notice AF " + escaleAF.nom + " export\u00e9e");
                    this.setChanged();
                    this.notifyObservers(nbNoticesRecues);
                }
                get.releaseConnection();
            }
            catch (Exception e) {
                e.printStackTrace();
                get.releaseConnection();
                post.releaseConnection();
            }
        }
    }
    
    public int getProgressValue() {
        return this.progressValue;
    }
}
