// 
// Decompiled by Procyon v0.5.36
// 

package ccImport;

import java.io.File;
import ccUtils.Utils;
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

public class ConnexionCE extends Observable
{
    public static final String MSG_OK = "Notices CE export\u00e9es !";
    public static final String MSG_ERR_SITE_CE = "Erreur du site CE Lignes";
    public static final String MSG_ERR_LOGINPASSWORD = "Identifiant et/ou mot de passe CE Lignes\nnon renseign\u00e9(s) dans Options/Notices";
    public static final String MSG_EXCEPTION = "Erreur impr\u00e9vue";
    public static final String MSG_CONNECTED = "Connect\u00e9 CELignes !";
    private String repertoire;
    private ArrayList<Escale> listEscalesCE;
    private int progressValue;
    
    public ConnexionCE() {
        this.repertoire = null;
        this.listEscalesCE = null;
        this.progressValue = 0;
        this.listEscalesCE = new ArrayList<Escale>();
    }
    
    public int chopeNoticesCE(final AnalyseCrew analyse, final String repertoire, final boolean isTotal) {
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
            final String ficSource = "/res_databases/dbNOTICES.txt";
            final InputStream is = this.getClass().getResourceAsStream(ficSource);
            final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String tmp;
            while ((tmp = in.readLine()) != null) {
                if (!tmp.equals("") && !tmp.contains("//")) {
                    final Escale escale = new Escale();
                    escale.code = tmp.split(";")[0].trim();
                    escale.nom = tmp.split(";")[1].trim();
                    if (isTotal) {
                        this.listEscalesCE.add(escale);
                    }
                    else {
                        if (!listDecouchers.contains(escale.code)) {
                            continue;
                        }
                        this.listEscalesCE.add(escale);
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
            this.notifyObservers("Erreur du site CE Lignes");
            return -1;
        }
        if (z == -2) {
            this.setChanged();
            this.notifyObservers("Identifiant et/ou mot de passe CE Lignes\nnon renseign\u00e9(s) dans Options/Notices");
            return -2;
        }
        this.setChanged();
        this.notifyObservers("Connect\u00e9 CELignes !");
        this.importNoticesCE();
        this.setChanged();
        this.notifyObservers("Notices CE export\u00e9es !");
        return 1;
    }
    
    private int connect() {
        final HttpGet get = new HttpGet();
        final HttpPost post = new HttpPost();
        if (ChopeCrew.options.ceLogin.equals("") || ChopeCrew.options.cePassword.equals("")) {
            return -2;
        }
        System.out.println("Connexion CE ...");
        try {
            get.setURI(new URI("https://www.celignes.com"));
            ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            post.setURI(new URI("https://www.celignes.com/_connect_celignes.asp"));
            final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("r", ""));
            nvps.add(new BasicNameValuePair("matricul", ChopeCrew.options.ceLogin));
            nvps.add(new BasicNameValuePair("mdp", ChopeCrew.options.cePassword));
            post.setEntity(new UrlEncodedFormEntity(nvps));
            ChopeCrew.httpClient.execute((HttpUriRequest)post);
            post.releaseConnection();
            get.setURI(new URI("https://www.celignes.com/index.asp"));
            final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("Connect\u00e9 CE Lignes");
                return 1;
            }
            System.out.println("Echec connexion");
            return -1;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec connexion");
            get.releaseConnection();
            post.releaseConnection();
            return 0;
        }
    }
    
    private void importNoticesCE() {
        this.progressValue = 0;
        final HttpGet get = new HttpGet();
        int nbNoticesRecues = 0;
        for (final Escale escale : this.listEscalesCE) {
            try {
                final URI url = new URI("https://www.celignes.com/escales/telecharge-fiche.asp?f=" + escale.code + "-" + escale.nom);
                get.setURI(url);
                final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                final String body = Utils.streamToString(response.getEntity().getContent(), "ISO-8859-1");
                if (response.getStatusLine().getStatusCode() == 200 && this.pdfOK(body)) {
                    final File f = new File(this.repertoire);
                    f.mkdirs();
                    String filename = ChopeCrew.options.libelleNoticesCE;
                    filename = filename.replaceAll("%c", escale.code);
                    filename = filename.replaceAll("%n", escale.nom);
                    Utils.saveToFile(body, String.valueOf(f.getAbsolutePath()) + File.separator + filename + ".pdf", "ISO-8859-1");
                    ++nbNoticesRecues;
                    this.progressValue = nbNoticesRecues * 100 / this.listEscalesCE.size();
                    System.out.println("notice CE " + escale.nom + " export\u00e9e");
                    this.setChanged();
                    this.notifyObservers(nbNoticesRecues);
                }
                get.releaseConnection();
            }
            catch (Exception e) {
                e.printStackTrace();
                get.releaseConnection();
            }
        }
    }
    
    public int getProgressValue() {
        return this.progressValue;
    }
    
    private boolean pdfOK(final String s) {
        return !s.contains("The page cannot be found");
    }
}
