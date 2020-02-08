// 
// Decompiled by Procyon v0.5.36
// 

package ccExport;

import java.io.InputStream;
import org.apache.http.client.methods.CloseableHttpResponse;
import ccUtils.Utils;
import org.apache.http.client.methods.HttpUriRequest;
import java.net.URI;
import org.apache.http.HttpEntity;
import java.util.List;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import chopeCrew.ChopeCrew;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import ccEngine.AnalyseCrew;
import java.util.ArrayList;
import java.util.Observable;

public class Export_NightStop extends Observable
{
    public static final String msg_OK = "Planning NightStop envoy\u00e9 !";
    public static final String msg_Erreur = "Erreur d'identifiant ou de mot de passe !";
    public static final String msg_Exception = "Erreur impr\u00e9vue !";
    private String login;
    private String password;
    private ArrayList<String> listEscalesDecouchers;
    private ArrayList<String> listJoursDecouchers;
    
    public Export_NightStop(final AnalyseCrew analyse) {
        this.login = null;
        this.password = null;
        this.listEscalesDecouchers = null;
        this.listJoursDecouchers = null;
        this.listEscalesDecouchers = new ArrayList<String>();
        this.listJoursDecouchers = new ArrayList<String>();
        final GregorianCalendar cal1 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar cal2 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calDeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calFin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calFinRot = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (int i = 0; i < analyse.listRotation.size(); ++i) {
            final Rotation rotation = analyse.listRotation.get(i);
            for (int j = 0; j < rotation.listSV.size(); ++j) {
                calFinRot.setTime(rotation.finUTCD);
                final ServiceVol sv = rotation.listSV.get(j);
                if (sv.isDecoucher) {
                    final Troncon lastTronconSv = sv.listTroncon.get(sv.listTroncon.size() - 1);
                    final ServiceVol nextSv = rotation.listSV.get(j + 1);
                    final Troncon firstTronconNextSv = nextSv.listTroncon.get(0);
                    cal1.setTime(lastTronconSv.finUTCD);
                    cal2.setTime(firstTronconNextSv.debutUTCD);
                    calDeb.setTime(lastTronconSv.finUTCD);
                    calFin.setTime(firstTronconNextSv.debutUTCD);
                    if (calDeb.get(2) + 1 == analyse.moisInt || calFin.get(2) + 1 == analyse.moisInt) {
                        if (calDeb.get(2) + 1 != analyse.moisInt && calFin.get(2) + 1 == analyse.moisInt) {
                            cal1.set(5, 1);
                            cal1.set(11, 0);
                            cal1.set(12, 0);
                            cal1.set(13, 0);
                            cal1.set(14, 0);
                            cal1.add(2, 1);
                            cal2.set(11, 23);
                            cal2.set(12, 59);
                            cal2.set(13, 59);
                            cal2.set(14, 1000);
                        }
                        else if (calDeb.get(2) + 1 == analyse.moisInt && calFin.get(2) + 1 != analyse.moisInt) {
                            cal2.set(5, 1);
                            cal2.set(11, 0);
                            cal2.set(12, 0);
                            cal2.set(13, 0);
                            cal2.set(14, 0);
                            cal1.set(11, 0);
                            cal1.set(12, 0);
                            cal1.set(13, 0);
                            cal1.set(14, 0);
                        }
                        else {
                            cal1.set(11, 0);
                            cal1.set(12, 0);
                            cal1.set(13, 0);
                            cal1.set(14, 0);
                            cal2.set(11, 23);
                            cal2.set(12, 59);
                            cal2.set(13, 59);
                            cal2.set(14, 1000);
                        }
                        while (cal1.before(cal2)) {
                            int duree = 0;
                            if (cal1.get(5) == calDeb.get(5)) {
                                duree = 1440 - (calDeb.get(11) * 60 + calDeb.get(12));
                            }
                            else if (cal1.get(5) == calFin.get(5)) {
                                duree = calFin.get(11) * 60 + calFin.get(12);
                            }
                            else {
                                duree = 1440;
                            }
                            if (duree >= 1) {
                                final int index = this.listJoursDecouchers.indexOf(String.valueOf(cal1.get(5)));
                                if (index >= 0) {
                                    this.listJoursDecouchers.remove(index);
                                    this.listEscalesDecouchers.remove(index);
                                }
                                if (cal1.get(5) != calFinRot.get(5)) {
                                    this.listJoursDecouchers.add(String.valueOf(cal1.get(5)));
                                    this.listEscalesDecouchers.add(lastTronconSv.to);
                                }
                            }
                            cal1.add(5, 1);
                        }
                    }
                }
            }
        }
    }
    
    public int sendPlanning(final AnalyseCrew analyse, final String login, final String password) {
        this.login = login;
        this.password = password;
        if (ChopeCrew.httpClient == null) {
            final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            ChopeCrew.httpClient = HttpClients.custom().setConnectionManager(cm).disableRedirectHandling().build();
        }
        final int z = this.connect();
        if (z == 0) {
            this.setChanged();
            this.notifyObservers("Erreur impr\u00e9vue !");
            return 0;
        }
        if (z == -1) {
            this.setChanged();
            this.notifyObservers("Erreur d'identifiant ou de mot de passe !");
            return -1;
        }
        this.exportNightStop(analyse);
        System.out.println("Planning NightStop envoy\u00e9 !");
        this.setChanged();
        this.notifyObservers("Planning NightStop envoy\u00e9 !");
        return 1;
    }
    
    private int connect() {
        final HttpPost post = new HttpPost();
        final HttpGet get = new HttpGet();
        System.out.println("Connexion NightStop ...");
        this.setChanged();
        this.notifyObservers("Connexion NightStop ...");
        try {
            final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("pseudo", this.login));
            nvps.add(new BasicNameValuePair("mdp", this.password));
            nvps.add(new BasicNameValuePair("ok", "Ok"));
            post.setEntity(new UrlEncodedFormEntity(nvps));
            final URI url = new URI("http://nightstop.free.fr/FR/index.php?page=identification&chopecrew=1");
            post.setURI(url);
            ChopeCrew.httpClient.execute((HttpUriRequest)post);
            post.releaseConnection();
            get.setURI(url);
            final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            final InputStream is = response.getEntity().getContent();
            final String body = Utils.streamToString(is, "ISO-8859-1");
            get.releaseConnection();
            if (body.indexOf("Bienvenue") < 0) {
                System.out.println("Acc\u00e8s refus\u00e9 !");
                this.setChanged();
                this.notifyObservers("Acc\u00e8s refus\u00e9 !");
                return -1;
            }
            System.out.println("Connect\u00e9 NightStop");
            this.setChanged();
            this.notifyObservers("Connect\u00e9 NightStop");
            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec connexion");
            this.setChanged();
            this.notifyObservers("Echec connexion");
            post.releaseConnection();
            get.releaseConnection();
            return 0;
        }
    }
    
    private void exportNightStop(final AnalyseCrew analyse) {
        int nbEvenements = 0;
        final HttpPost post = new HttpPost();
        final HttpGet get = new HttpGet();
        this.setChanged();
        this.notifyObservers("Envoi en cours...");
        if (this.listJoursDecouchers.size() == 0) {
            try {
                URI url = new URI("http://nightstop.free.fr/FR/index.php?page=planning_effacer&mois=" + analyse.moisInt + "&annee=" + analyse.anneeInt + "&chopecrew=1");
                final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("mois", String.valueOf(analyse.moisInt)));
                nvps.add(new BasicNameValuePair("ok", "Effacer"));
                post.setEntity(new UrlEncodedFormEntity(nvps));
                post.setURI(url);
                ChopeCrew.httpClient.execute((HttpUriRequest)post);
                post.releaseConnection();
                url = new URI("http://nightstop.free.fr/FR/index.php?page=planning&sans_dec=" + analyse.moisInt + "&annee=" + analyse.anneeInt + "&chopecrew=1");
                get.setURI(url);
                ChopeCrew.httpClient.execute((HttpUriRequest)get);
                get.releaseConnection();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        final ArrayList<NameValuePair> postDataArray = new ArrayList<NameValuePair>();
        postDataArray.add(new BasicNameValuePair("ok", "Ok"));
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        cal.set(1, analyse.anneeInt);
        cal.set(2, analyse.moisInt - 1);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        while (cal.get(2) + 1 == analyse.moisInt) {
            final String jour = String.valueOf(cal.get(5));
            final int index = this.listJoursDecouchers.indexOf(jour);
            if (index < 0) {
                postDataArray.add(new BasicNameValuePair("j" + jour, ""));
            }
            else {
                ++nbEvenements;
                postDataArray.add(new BasicNameValuePair("j" + jour, this.listEscalesDecouchers.get(index)));
                System.out.println("nightstop n° " + nbEvenements + " cr\u00e9\u00e9");
                this.setChanged();
                this.notifyObservers(nbEvenements);
            }
            cal.add(5, 1);
        }
        try {
            final URI url = new URI("http://nightstop.free.fr/FR/index.php?page=planning_saisir&mois=" + analyse.moisInt + "&annee=" + analyse.anneeInt + "&chopecrew=1");
            post.setURI(url);
            post.setEntity(new UrlEncodedFormEntity(postDataArray));
            ChopeCrew.httpClient.execute((HttpUriRequest)post);
            post.releaseConnection();
        }
        catch (Exception e2) {
            e2.printStackTrace();
            this.setChanged();
            this.notifyObservers(e2);
            post.releaseConnection();
        }
    }
}
