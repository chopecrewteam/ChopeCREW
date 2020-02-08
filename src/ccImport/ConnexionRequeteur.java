// 
// Decompiled by Procyon v0.5.36
// 

package ccImport;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ccUtils.Utils;
import org.apache.http.entity.ContentType;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.HttpEntity;
import java.util.List;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpUriRequest;
import chopeCrew.ChopeCrew;
import java.net.URI;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Observable;

public class ConnexionRequeteur extends Observable
{
    public static final int OK = 1;
    public static final int EXCEPTION = 0;
    public static final int ID_FIC_INVALIDE = -1;
    private String login;
    private String password;
    private String urlCrew;
    private String urlIpn;
    private ArrayList<String> listUrlRotations;
    private ArrayList<String> listPagesImpression;
    private Scanner scan;
    
    public ConnexionRequeteur() {
        this.listUrlRotations = new ArrayList<String>();
        this.listPagesImpression = new ArrayList<String>();
    }
    
    private int connectOFF(final String chemin_fichier) {
        System.out.println("Ouverture du fichier ...");
        this.setChanged();
        this.notifyObservers("Ouverture du fichier ...");
        try {
            this.scan = new Scanner(new File(chemin_fichier), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec ouverture du fichier");
            this.setChanged();
            this.notifyObservers("Echec ouverture du fichier");
            return 0;
        }
        if (this.scan.findWithinHorizon("--- ChopeCrew ", 0) != null) {
            System.out.println("Fichier reconnu !");
            this.setChanged();
            this.notifyObservers("Fichier reconnu !");
            return 1;
        }
        System.out.println("Fichier invalide !");
        this.setChanged();
        this.notifyObservers("Fichier invalide !");
        return -1;
    }
    
    private void importListPagesImpressionOFF() {
        final String nl = System.getProperty("line.separator");
        final String debut = "--- Page Impression ---";
        final String fin = "--- Fin Page Impression ---";
        while (this.scan.findWithinHorizon(debut, 0) != null) {
            final StringBuilder sb = new StringBuilder();
            String tmp;
            while (!(tmp = this.scan.nextLine()).equals(fin)) {
                sb.append(tmp).append(nl);
            }
            this.listPagesImpression.add(sb.toString());
        }
        System.out.println("Pages impression obtenues");
        this.setChanged();
        this.notifyObservers("Rotations obtenues !");
    }
    
    private int connect() {
        System.out.println("Connexion Crew en cours ...");
        this.setChanged();
        this.notifyObservers("Connexion Crew en cours ...");
        final HttpGet get = new HttpGet();
        final HttpPost post = new HttpPost();
        try {
            URI url = new URI("https://" + this.urlIpn);
            get.setURI(url);
            CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            if (response.getFirstHeader("Location") != null && response.getFirstHeader("Location").getValue().contains("siteminderagent")) {
                final String urlRedirection = response.getFirstHeader("Location").getValue();
                System.out.println("Authentification Habile ...");
                this.setChanged();
                this.notifyObservers("Authentification Habile ...");
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
                    this.setChanged();
                    this.notifyObservers("Acc\u00e8s refus\u00e9 !");
                    return -1;
                }
                System.out.println("Acc\u00e8s accord\u00e9 !");
                this.setChanged();
                this.notifyObservers("Acc\u00e8s accord\u00e9 !");
            }
            url = new URI("https://" + this.urlCrew + "/crew/main");
            post.setURI(url);
            final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("origine", "https://" + this.urlIpn));
            post.setEntity(new UrlEncodedFormEntity(nvps));
            ChopeCrew.httpClient.execute((HttpUriRequest)post);
            post.releaseConnection();
            System.out.println("Connect\u00e9 \u00e0 Crew !");
            this.setChanged();
            this.notifyObservers("Connect\u00e9 \u00e0 Crew !");
            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec de la connexion");
            this.setChanged();
            this.notifyObservers("Echec de la connexion");
            get.releaseConnection();
            post.releaseConnection();
            return 0;
        }
    }
    
    private void doRequest() {
        final String date = "25/07/2018";
        String avion = " 77";
        String specialite = "OPL";
        if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 0 || ChopeCrew.donneesPerso_PNT.qualif_pnt == 1) {
            avion = " A5";
        }
        else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 2) {
            avion = " 32";
        }
        else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 3) {
            avion = " A9";
        }
        else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 5) {
            avion = " 87";
        }
        else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 6) {
            avion = " 77";
        }
        else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 7) {
            avion = " 88";
        }
        if (ChopeCrew.donneesPerso_PNT.fonction_pnt == 1 || ChopeCrew.donneesPerso_PNT.fonction_pnt == 3) {
            specialite = "CDB";
        }
        else if (ChopeCrew.donneesPerso_PNT.fonction_pnt == 0 || ChopeCrew.donneesPerso_PNT.fonction_pnt == 2) {
            specialite = "OPL";
        }
        String courrier;
        if (avion.equals(" A5")) {
            courrier = "MOYEN";
        }
        else {
            courrier = "LONG";
        }
        final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("event", "executerequete"));
        nvps.add(new BasicNameValuePair("saisieDateDepart", date));
        nvps.add(new BasicNameValuePair("saisieDateDepartFin", date));
        nvps.add(new BasicNameValuePair("saisieDateRetour", ""));
        nvps.add(new BasicNameValuePair("saisieDateReengagement", ""));
        nvps.add(new BasicNameValuePair("saisieDateDepartTroncon", ""));
        nvps.add(new BasicNameValuePair("saisieDateArriveeTroncon", ""));
        nvps.add(new BasicNameValuePair("choixSpecialite", specialite));
        nvps.add(new BasicNameValuePair("choixCdl", ""));
        nvps.add(new BasicNameValuePair("choixAvion", avion));
        nvps.add(new BasicNameValuePair("champRot", "choixRotation"));
        nvps.add(new BasicNameValuePair("champDat", "dateRotation"));
        nvps.add(new BasicNameValuePair("choixFonction", "PNT"));
        nvps.add(new BasicNameValuePair("choixCourrier", courrier));
        nvps.add(new BasicNameValuePair("selectChoixAvion", avion));
        nvps.add(new BasicNameValuePair("selectChoixSpecialite", specialite));
        nvps.add(new BasicNameValuePair("choixBase", "vide"));
        nvps.add(new BasicNameValuePair("saisieDDepart", date));
        nvps.add(new BasicNameValuePair("saisieDDepartFin", date));
        nvps.add(new BasicNameValuePair("choixJourFonction", ""));
        nvps.add(new BasicNameValuePair("choixContrainteDateRetour", ""));
        nvps.add(new BasicNameValuePair("saisieDRetour", ""));
        nvps.add(new BasicNameValuePair("choixMEP", "V1"));
        nvps.add(new BasicNameValuePair("choixContrainteDateReengagement", ""));
        nvps.add(new BasicNameValuePair("saisieDReengagement", ""));
        nvps.add(new BasicNameValuePair("choixRotCom", "V1"));
        nvps.add(new BasicNameValuePair("choixContrainteNbreJourEngagement", ""));
        nvps.add(new BasicNameValuePair("choixNbrJourEngagement", ""));
        nvps.add(new BasicNameValuePair("choixEscale", ""));
        nvps.add(new BasicNameValuePair("choixContrainteNbreTroncon", ""));
        nvps.add(new BasicNameValuePair("choixNbrTroncon", ""));
        nvps.add(new BasicNameValuePair("choixContrainteDureeDecouche", ""));
        nvps.add(new BasicNameValuePair("choixDureeDecouche", ""));
        nvps.add(new BasicNameValuePair("choixNumeroVol", ""));
        nvps.add(new BasicNameValuePair("choixJourFonctionTroncon", ""));
        nvps.add(new BasicNameValuePair("choixEscaleDepartTroncon", ""));
        nvps.add(new BasicNameValuePair("choixContrainteDateDepartTroncon", ""));
        nvps.add(new BasicNameValuePair("saisieDDepartTroncon", ""));
        nvps.add(new BasicNameValuePair("choixHeureDepartTroncon", ""));
        nvps.add(new BasicNameValuePair("choixEscaleArriveeTroncon", ""));
        nvps.add(new BasicNameValuePair("choixContrainteDateArriveeTroncon", ""));
        nvps.add(new BasicNameValuePair("saisieDArriveeTroncon", ""));
        nvps.add(new BasicNameValuePair("choixHeureArriveeTroncon", ""));
        final HttpGet get = new HttpGet();
        final HttpPost post = new HttpPost();
        try {
            System.out.println("Recherche en cours");
            this.setChanged();
            this.notifyObservers("Recherche en cours ...");
            URI url = new URI("https://" + this.urlCrew + "/crew/main?event=requete&champRot=choixRotation&champDat=dateRotation&dateRot2=");
            get.setURI(url);
            CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            System.out.println("En attente des infos");
            this.setChanged();
            this.notifyObservers("En attente des infos ...");
            url = new URI("https://" + this.urlCrew + "/crew/main");
            post.setURI(url);
            post.setEntity(new UrlEncodedFormEntity(nvps));
            response = ChopeCrew.httpClient.execute((HttpUriRequest)post);
            final String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
            final String source = Utils.streamToString(response.getEntity().getContent(), charset);
            post.releaseConnection();
            System.out.println("Rotations obtenues");
            this.setChanged();
            this.notifyObservers("Rotations obtenues !");
            final String cible = "<a href=\"/crew/main(.*?)event=rotation&DETAIL_ROTATION_REQUETEUR=true&numrot=(.*?)&date=(.*?)\">";
            final Pattern regex = Pattern.compile(cible, 34);
            final Matcher result = regex.matcher(source);
            while (result.find()) {
                this.listUrlRotations.add("/crew/main?event=rotation&DETAIL_ROTATION_REQUETEUR=true&numrot=" + result.group(2).trim() + "&date=" + result.group(3).trim());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec recherche");
            this.setChanged();
            this.notifyObservers("Echec recherche");
            get.releaseConnection();
            post.releaseConnection();
        }
    }
    
    private void importPagesRotation() {
        final HttpGet get = new HttpGet();
        try {
            for (int i = 0; i < this.listUrlRotations.size(); ++i) {
                System.out.println("Requ\u00eate d\u00e9tails rotation " + Integer.toString(i + 1));
                this.setChanged();
                this.notifyObservers("D\u00e9tails rotation " + Integer.toString(i + 1) + " sur " + Integer.toString(this.listUrlRotations.size()) + " ...");
                String s = "https://" + this.urlCrew + this.listUrlRotations.get(i);
                URI url = new URI(s.replaceAll(" ", "%20"));
                get.setURI(url);
                ChopeCrew.httpClient.execute((HttpUriRequest)get);
                get.releaseConnection();
                s = "https://" + this.urlCrew + "/crew/main?event=impressionRotation";
                url = new URI(s.replaceAll(" ", "%20"));
                get.setURI(url);
                final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                final String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
                final InputStream is = response.getEntity().getContent();
                this.listPagesImpression.add(Utils.streamToString(is, charset));
                get.releaseConnection();
                System.out.println("D\u00e9tails rotation " + Integer.toString(i + 1) + " ok");
                this.setChanged();
                this.notifyObservers("D\u00e9tails rotation " + Integer.toString(i + 1) + " sur " + Integer.toString(this.listUrlRotations.size()) + " ok !");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec d\u00e9tails rotations");
            this.setChanged();
            this.notifyObservers("Echec d\u00e9tails rotations");
            get.releaseConnection();
        }
    }
    
    public int request(final String chemin_fichier, final String login, final String password, final String urlIpn) {
        this.login = login;
        this.password = password;
        this.urlIpn = urlIpn;
        this.urlCrew = urlIpn.replaceAll("ipn", "crew");
        final int z = this.connectOFF(chemin_fichier);
        if (z == 0) {
            System.out.println("->Erreur...(echec ouverture du fichier)");
            this.setChanged();
            this.notifyObservers(0);
            return 0;
        }
        if (z == -1) {
            System.out.println("->Erreur...(fichier invalide)");
            this.setChanged();
            this.notifyObservers(-1);
            return -1;
        }
        this.clear();
        this.importListPagesImpressionOFF();
        this.scan.close();
        this.scan = null;
        System.out.println("->Planning import\u00e9 !");
        this.setChanged();
        this.notifyObservers("Planning import\u00e9 !");
        this.setChanged();
        this.notifyObservers(1);
        return 1;
    }
    
    public int request(final String login, final String password, final String urlIpn) {
        this.login = login;
        this.password = password;
        this.urlIpn = urlIpn;
        this.urlCrew = urlIpn.replaceAll("ipn", "crew");
        if (ChopeCrew.httpClient == null) {
            final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            final ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
                @Override
                public long getKeepAliveDuration(final HttpResponse response, final HttpContext context) {
                    return 10800000L;
                }
            };
            ChopeCrew.httpClient = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(myStrategy).disableRedirectHandling().build();
        }
        final int z = this.connect();
        if (z == 0) {
            System.out.println("->Erreur...(impr\u00e9vue)");
            this.setChanged();
            this.notifyObservers(0);
            return 0;
        }
        if (z == -1) {
            System.out.println("->Erreur...(identifiants)");
            this.setChanged();
            this.notifyObservers(-1);
            return -1;
        }
        this.clear();
        this.doRequest();
        this.importPagesRotation();
        System.out.println("Recherche termin\u00e9e");
        this.setChanged();
        this.notifyObservers("Recherche termin\u00e9e !");
        this.setChanged();
        this.notifyObservers(1);
        ChopeCrew.isKeepAliving = true;
        return 1;
    }
    
    public String getSourceAsString() {
        final String nl = System.getProperty("line.separator");
        final StringBuilder sb = new StringBuilder();
        sb.append("--- ChopeCrew ---").append(nl);
        for (int i = 0; i < this.listPagesImpression.size(); ++i) {
            sb.append("--- Page Impression ---").append(nl);
            sb.append(this.listPagesImpression.get(i)).append(nl);
            sb.append("--- Fin Page Impression ---").append(nl);
            sb.append(nl).append(nl).append(nl);
        }
        sb.append("--- Fin ChopeCrew ---");
        return sb.toString();
    }
    
    public ArrayList<String> getListPagesImpression() {
        return this.listPagesImpression;
    }
    
    private void clear() {
        this.listPagesImpression.clear();
        this.listUrlRotations.clear();
    }
}
