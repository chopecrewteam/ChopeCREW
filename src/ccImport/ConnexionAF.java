// 
// Decompiled by Procyon v0.5.36
// 

package ccImport;

import java.util.regex.Matcher;
import java.util.TimeZone;
import java.util.Iterator;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;
import ccUtils.Utils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import java.util.ArrayList;
import java.util.SimpleTimeZone;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import java.text.SimpleDateFormat;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import java.net.URI;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import chopeCrew.ChopeCrew;
import ccEngine.AnalyseCrew;
import java.util.Observable;

public class ConnexionAF extends Observable
{
    public static final String MSG_OK_NO_CHANGE = "Pas de mise \u00e0 jour";
    public static final String MSG_OK_CHANGE = "Temps de vol mis \u00e0 jour";
    public static final String MSG_ERR_SITE_AF = "Erreur du site AF";
    public static final String MSG_EXCEPTION = "Erreur impr\u00e9vue";
    public static final String MSG_CONNECTED = "Connect\u00e9 AF";
    boolean hasChanged;
    
    public ConnexionAF(final AnalyseCrew analyse) {
        this.hasChanged = false;
    }
    
    public int chopeBlocAF(final AnalyseCrew analyse) {
        if (ChopeCrew.httpClient == null) {
            final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
            ChopeCrew.httpClient = HttpClients.custom().setConnectionManager(cm).build();
        }
        final int z = this.connect();
        if (z == 0) {
            this.setChanged();
            this.notifyObservers("Erreur impr\u00e9vue");
            return 0;
        }
        if (z == -1) {
            this.setChanged();
            this.notifyObservers("Erreur du site AF");
            return -1;
        }
        this.setChanged();
        this.notifyObservers("Connect\u00e9 AF");
        this.importBlocAF(analyse);
        this.setChanged();
        if (this.hasChanged) {
            this.notifyObservers("Temps de vol mis \u00e0 jour");
        }
        else {
            this.notifyObservers("Pas de mise \u00e0 jour");
        }
        return 1;
    }
    
    private int connect() {
        final HttpGet get = new HttpGet();
        System.out.println("Connexion AF ...");
        try {
            get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
            get.setURI(new URI("https://www.airfrance.fr"));
            final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            if (response.getStatusLine().getStatusCode() <= 400) {
                System.out.println("Connect\u00e9 AF");
                return 1;
            }
            System.out.println("Echec connexion");
            return -1;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec connexion");
            get.releaseConnection();
            return 0;
        }
    }
    
    private void importBlocAF(final AnalyseCrew analyse) {
        final HttpGet get = new HttpGet();
        final HttpPost post = new HttpPost();
        int nbVolRecus = 0;
        final DateFormat dayFormatFrom = new SimpleDateFormat("dd");
        final DateFormat monthYearFormatFrom = new SimpleDateFormat("yyyyMM");
        for (final Rotation rotation : analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon tro : sv.listTroncon) {
                    if (tro.numVol.contains("AF") || tro.numVol.contains("A5")) {
                        String codeCie = "";
                        if (tro.numVol.contains("AF")) {
                            codeCie = "AF";
                        }
                        if (tro.numVol.contains("A5")) {
                            codeCie = "A5";
                        }
                        final String numVol = tro.numVol.substring(2).trim();
                        final TimeZone tzFrom = new SimpleTimeZone(tro.LAGfromMillis, "");
                        dayFormatFrom.setTimeZone(tzFrom);
                        monthYearFormatFrom.setTimeZone(tzFrom);
                        String dateFrom = dayFormatFrom.format(tro.debutUTCD);
                        String anneemoisFrom = monthYearFormatFrom.format(tro.debutUTCD);
                        final TimeZone tzTo = new SimpleTimeZone(tro.LAGtoMillis, "");
                        if (sv.listTroncon.size() > 1 && tro.numTroncon >= 2) {
                            final Troncon troPre = sv.listTroncon.get(tro.numTroncon - 2);
                            if (troPre.numVol.equals(tro.numVol)) {
                                final TimeZone tzFromPre = new SimpleTimeZone(troPre.LAGfromMillis, "");
                                dayFormatFrom.setTimeZone(tzFromPre);
                                monthYearFormatFrom.setTimeZone(tzFromPre);
                                dateFrom = dayFormatFrom.format(troPre.debutUTCD);
                                anneemoisFrom = monthYearFormatFrom.format(troPre.debutUTCD);
                            }
                        }
                        try {
                            final URI url = new URI("https://www.airfrance.fr/FR/fr/local/resainfovol/infovols/detailsVol.do?companyCode=" + codeCie + "&flightNumber=" + numVol + "&dayDate=" + dateFrom + "&yearMonthDate=" + anneemoisFrom + "&flightDate=" + anneemoisFrom + dateFrom + "&queryFromEntryPoint=true");
                            final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
                            nvps.add(new BasicNameValuePair("companyCode", codeCie));
                            nvps.add(new BasicNameValuePair("flightNumber", numVol));
                            nvps.add(new BasicNameValuePair("dayDate", dateFrom));
                            nvps.add(new BasicNameValuePair("yearMonthDate", anneemoisFrom));
                            nvps.add(new BasicNameValuePair("flightDate", String.valueOf(anneemoisFrom) + dateFrom));
                            nvps.add(new BasicNameValuePair("queryFromEntryPoint", "true"));
                            System.out.println(String.valueOf(numVol) + anneemoisFrom + dateFrom);
                            get.setURI(url);
                            get.setHeader("origin", "https://www.airfrance.fr");
                            get.setHeader("referer", "https://www.airfrance.fr/FR/fr/local/resainfovol/infovols/actualiteDesVols.do");
                            get.setHeader("upgrade-insecure-requests", "1");
                            get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
                            final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                            final String body = Utils.streamToString(response.getEntity().getContent(), "ISO-8859-1");
                            if (response.getStatusLine().getStatusCode() == 200) {
                                ++nbVolRecus;
                                System.out.println(body);
                                String blocD = "";
                                String blocA = "";
                                final String cible = "<section class=\"flight__item\">(.*?)<span class=\"flight__item__detail__stopover__airport\">(.*?)</span>(.*?)<span class=\"flight__item__detail__scheduling__value\">(.*?)</span>(.*?)<span class=\"flight__item__detail__scheduling__value\">(.*?)</span>(.*?)<span class=\"flight__item__detail__stopover__airport\">(.*?)</span>(.*?)<span class=\"flight__item__detail__scheduling__value\">(.*?)</span>(.*?)<span class=\"flight__item__detail__scheduling__value\">(.*?)</span>(.*?)</section>";
                                final Pattern regex = Pattern.compile(cible, 34);
                                final Matcher result = regex.matcher(body);
                                while (result.find()) {
                                    if (result.group(2).trim().contains("(" + tro.from + ")") && result.group(8).contains("(" + tro.to + ")")) {
                                        blocD = result.group(6).trim();
                                        blocA = result.group(12).trim();
                                    }
                                }
                                System.out.println(String.valueOf(tro.numVol) + " " + tro.from + "  " + tro.to + " " + blocD + " " + blocA);
                                if (blocD.equals("")) {
                                    continue;
                                }
                                if (blocA.equals("")) {
                                    continue;
                                }
                                final GregorianCalendar calFrom = new GregorianCalendar(tzFrom);
                                final int hFrom = Integer.parseInt(blocD.substring(0, 2));
                                final int mFrom = Integer.parseInt(blocD.substring(3, 5));
                                calFrom.setTimeInMillis(tro.DEPp);
                                calFrom.set(11, hFrom);
                                calFrom.set(12, mFrom);
                                calFrom.set(13, 0);
                                final long sameDayFrom = calFrom.getTimeInMillis();
                                calFrom.add(5, 1);
                                final long dayAfterFrom = calFrom.getTimeInMillis();
                                calFrom.add(5, -2);
                                final long dayBeforeFrom = calFrom.getTimeInMillis();
                                final long deltaBeforeFrom = Math.abs(tro.DEPp - dayBeforeFrom);
                                final long deltaAfterFrom = Math.abs(tro.DEPp - dayAfterFrom);
                                final long deltaSameFrom = Math.abs(tro.DEPp - sameDayFrom);
                                if (deltaBeforeFrom < deltaSameFrom && deltaBeforeFrom < deltaAfterFrom) {
                                    calFrom.setTimeInMillis(dayBeforeFrom);
                                }
                                else if (deltaAfterFrom < deltaSameFrom && deltaAfterFrom < deltaBeforeFrom) {
                                    calFrom.setTimeInMillis(dayAfterFrom);
                                }
                                else {
                                    calFrom.setTimeInMillis(sameDayFrom);
                                }
                                final GregorianCalendar calTo = new GregorianCalendar(tzTo);
                                final int hTo = Integer.parseInt(blocA.substring(0, 2));
                                final int mTo = Integer.parseInt(blocA.substring(3, 5));
                                calTo.setTimeInMillis(tro.ARRp);
                                calTo.set(11, hTo);
                                calTo.set(12, mTo);
                                calTo.set(13, 0);
                                final long sameDayTo = calTo.getTimeInMillis();
                                calTo.add(5, 1);
                                final long dayAfterTo = calTo.getTimeInMillis();
                                calTo.add(5, -2);
                                final long dayBeforeTo = calTo.getTimeInMillis();
                                final long deltaBeforeTo = Math.abs(tro.ARRp - dayBeforeTo);
                                final long deltaAfterTo = Math.abs(tro.ARRp - dayAfterTo);
                                final long deltaSameTo = Math.abs(tro.ARRp - sameDayTo);
                                if (deltaBeforeTo < deltaSameTo && deltaBeforeTo < deltaAfterTo) {
                                    calTo.setTimeInMillis(dayBeforeTo);
                                }
                                else if (deltaAfterTo < deltaSameTo && deltaAfterTo < deltaBeforeTo) {
                                    calTo.setTimeInMillis(dayAfterTo);
                                }
                                else {
                                    calTo.setTimeInMillis(sameDayTo);
                                }
                                final double TVr = Utils.arrondi((calTo.getTimeInMillis() - calFrom.getTimeInMillis()) / 1000.0 / 60.0 / 60.0, 2);
                                if (!tro.isTVreal) {
                                    if (!tro.isMep) {
                                        if (tro.DEPr != calFrom.getTimeInMillis() || tro.TVr != TVr) {
                                            tro.DEPr = calFrom.getTimeInMillis();
                                            tro.TVr = TVr;
                                            tro.MEPr = 0.0;
                                            tro.isTVreal = true;
                                            this.hasChanged = true;
                                        }
                                    }
                                    else if (tro.DEPr != calFrom.getTimeInMillis() || tro.MEPr != TVr) {
                                        tro.DEPr = calFrom.getTimeInMillis();
                                        tro.TVr = 0.0;
                                        tro.MEPr = TVr;
                                        tro.isTVreal = true;
                                        this.hasChanged = true;
                                    }
                                }
                                this.setChanged();
                                this.notifyObservers(nbVolRecus);
                            }
                            get.releaseConnection();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
