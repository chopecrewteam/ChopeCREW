
package ccImport;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;

import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ccUtils.Utils;
import chopeCrew.ChopeCrew;

public class ConnexionCrew extends Observable
{
    public static final int OK = 1;
    public static final int EXCEPTION = 0;
    public static final int ID_FIC_INVALIDE = -1;
    private String login;
    private String password;
    private String urlCrew;
    private String urlIpn;
    private int deltaMois;
    private Scanner scan;
    private String pageIntroPlanning;
    private String pageMensuelle;
    private String pageImpression;
    private String exportPda;
    private String exportPdaIcs;
    private ArrayList<String> pagesStage;
    private ArrayList<String> pagesRotation;
    private ArrayList<String> listUrlRotations;
    private ArrayList<String> listUrlStage;
    
    public ConnexionCrew() {
        this.pagesRotation = new ArrayList<String>();
        this.listUrlRotations = new ArrayList<String>();
        this.pagesStage = new ArrayList<String>();
        this.listUrlStage = new ArrayList<String>();
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
            this.deltaMois = this.scan.nextInt();
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
    
    private void importPageIntroPlanningOFF() {
        final String nl = System.getProperty("line.separator");
        final String debut = "--- Page IntroPlanning ---";
        final String fin = "--- Fin Page IntroPlanning ---";
        if (this.scan.findWithinHorizon(debut, 0) == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        String tmp;
        while (this.scan.hasNextLine() && !(tmp = this.scan.nextLine()).equals(fin)) {
            sb.append(tmp).append(nl);
        }
        System.out.println("Page intro planning obtenue");
        this.setChanged();
        this.notifyObservers("Type PN obtenu !");
        this.pageIntroPlanning = sb.toString();
    }
    
    private void importPageMensuelleOFF() {
        final String nl = System.getProperty("line.separator");
        final String debut = "--- Page Mensuelle ---";
        final String fin = "--- Fin Page Mensuelle ---";
        if (this.scan.findWithinHorizon(debut, 0) == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        String tmp;
        while (this.scan.hasNextLine() && !(tmp = this.scan.nextLine()).equals(fin)) {
            sb.append(tmp).append(nl);
        }
        System.out.println("Page mensuelle obtenue");
        this.setChanged();
        this.notifyObservers("Activit\u00e9s sol obtenues !");
        this.pageMensuelle = sb.toString();
    }
    
    private void importPageImpressionOFF() {
        final String nl = System.getProperty("line.separator");
        final String debut = "--- Page Impression ---";
        final String fin = "--- Fin Page Impression ---";
        if (this.scan.findWithinHorizon(debut, 0) == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        String tmp;
        while (this.scan.hasNextLine() && !(tmp = this.scan.nextLine()).equals(fin)) {
            sb.append(tmp).append(nl);
        }
        System.out.println("Page impression obtenue");
        this.setChanged();
        this.notifyObservers("Rotations obtenues !");
        this.pageImpression = sb.toString();
    }
    
    private void importExportPdaOFF() {
        final String nl = System.getProperty("line.separator");
        final String debut = "--- Export Pda ---";
        final String fin = "--- Fin Export Pda ---";
        if (this.scan.findWithinHorizon(debut, 0) == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        String tmp;
        while (this.scan.hasNextLine() && !(tmp = this.scan.nextLine()).equals(fin)) {
            sb.append(tmp).append(nl);
        }
        System.out.println("Export Pda obtenu");
        this.setChanged();
        this.notifyObservers("Infos obtenues !");
        this.exportPda = sb.toString();
    }
    
    private void importExportPdaIcsOFF() {
        final String nl = System.getProperty("line.separator");
        final String debut = "--- Export PdaIcs ---";
        final String fin = "--- Fin Export PdaIcs ---";
        if (this.scan.findWithinHorizon(debut, 0) == null) {
            return;
        }
        final StringBuilder sb = new StringBuilder();
        String tmp;
        while (this.scan.hasNextLine() && !(tmp = this.scan.nextLine()).equals(fin)) {
            sb.append(tmp).append(nl);
        }
        System.out.println("Export PdaIcs obtenu");
        this.setChanged();
        this.notifyObservers("Infos obtenues !");
        this.exportPdaIcs = sb.toString();
    }
    
    private void importPagesStageOFF() {
        final String nl = System.getProperty("line.separator");
        final String debut = "--- Stage ---";
        final String fin = "--- Fin Stage ---";
        if (this.scan.findWithinHorizon("--- Pages Stages ---", 0) == null) {
            return;
        }
        while (this.scan.findWithinHorizon(debut, 0) != null) {
            final StringBuilder sb = new StringBuilder();
            String tmp;
            while (!(tmp = this.scan.nextLine()).equals(fin)) {
                sb.append(tmp).append(nl);
            }
            this.pagesStage.add(sb.toString());
        }
        System.out.println("Pages stages obtenues");
        this.setChanged();
        this.notifyObservers("Stages obtenus !");
    }
    
    private void keepAlive() {
        final HttpGet get = new HttpGet();
        int i = 0;
        try {
            while (ChopeCrew.isKeepAliving) {
                final URI url = new URI("https://" + this.urlIpn);
                get.setURI(url);
                final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                if (response.getFirstHeader("Location") != null && response.getFirstHeader("Location").getValue().contains("siteminderagent")) {
                    System.out.println("Redirection Habile ...");
                }
                get.releaseConnection();
                ++i;
                System.out.println("Alive " + i);
                Thread.sleep(300000L);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	private int connectON(int deltamois) {

		System.out.println("Connexion Crew en cours ...");
		setChanged();
		this.notifyObservers("Connexion Crew en cours ...");

		HttpGet get = new HttpGet();
		HttpPost post = new HttpPost();
		CloseableHttpResponse response;
		ArrayList<NameValuePair> formParams;
		URI url;
		String urlRedirection;

		try {
			// Requête Ipn
			url = new URI("https://" + urlIpn + "/IPn");
			get.setURI(url);
			response = ChopeCrew.httpClient.execute(get);
			get.releaseConnection();

			// Si on est redirigé vers "HBLSP", on s'identifie sinon on est deja logué
			if ((response.getFirstHeader("Location") != null)
					&& (response.getFirstHeader("Location").getValue().contains("hblsp"))) {

				urlRedirection = response.getFirstHeader("Location").getValue();
				// System.out.println(urlRedirection);

				System.out.println("Authentification Habile ...");
				setChanged();
				this.notifyObservers("Authentification Habile ...");

				url = new URI(urlRedirection);
				get.setURI(url);
				response = ChopeCrew.httpClient.execute(get); // GET sur hblsp
				get.releaseConnection();
				urlRedirection = response.getFirstHeader("Location").getValue();
				// System.out.println(urlRedirection);

				url = new URI(urlRedirection);
				get.setURI(url);
				response = ChopeCrew.httpClient.execute(get); // GET sur fedhub

				String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
				InputStream is = response.getEntity().getContent();
				String s = Utils.streamToString(is, charset);
				get.releaseConnection();

				url = new URI("https://fedidp.airfrance.fr/idp/SSO.saml2");
				post.setURI(url);

				formParams = fetchParamsDom(s, null);
				post.setEntity(new UrlEncodedFormEntity(formParams)); // POST sur fedidp
				response = ChopeCrew.httpClient.execute(post);
				charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
				is = response.getEntity().getContent();
				s = Utils.streamToString(is, charset);
				post.releaseConnection();

				// post login
				formParams = fetchParamsDom(s, "nuxForm");
				replaceArrayListItem(formParams, "ok", "IDENTIFICATION");
				formParams.add(new BasicNameValuePair("username", login));
				formParams.add(new BasicNameValuePair("userIdInMemory", ""));
				url = new URI("https://fedidp.airfrance.fr" + fetchFormUrl(s, null));

				post.setURI(url);
				post.setEntity(new UrlEncodedFormEntity(formParams));
				response = ChopeCrew.httpClient.execute(post);
				charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
				is = response.getEntity().getContent();
				s = Utils.streamToString(is, charset);
				post.releaseConnection();

				// send token
				formParams = fetchParamsDom(s, null);
				replaceArrayListItem(formParams, "ok", "TOK");
				replaceArrayListItem(formParams, "displayFullUserName", login);
				replaceArrayListItem(formParams, "currentAuthMethod", "TOK");
				int index = findMfaIsAuthentIndex(formParams);
				if (index != -1) {
					formParams.add(index, new BasicNameValuePair("inputTokName", password));
				}
				formParams.add(new BasicNameValuePair("otpAuth", ""));

				post.setURI(url);
				post.setEntity(new UrlEncodedFormEntity(formParams));
				response = ChopeCrew.httpClient.execute(post);
				charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
				is = response.getEntity().getContent();
				s = Utils.streamToString(is, charset);
				post.releaseConnection();

				// redirect
				formParams = fetchParamsDom(s, null);
				url = new URI(fetchFormUrl(s, null));
				post.setURI(url);
				post.setEntity(new UrlEncodedFormEntity(formParams)); // POST sur fedHub
				response = ChopeCrew.httpClient.execute(post);
				charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
				is = response.getEntity().getContent();
				s = Utils.streamToString(is, charset);
				post.releaseConnection();

				// Redirect 2;
				formParams = fetchParamsDom(s, null);
				url = new URI(fetchFormUrl(s, null));
				post.setURI(url);
				post.setEntity(new UrlEncodedFormEntity(formParams)); // POST sur fedHub
				response = ChopeCrew.httpClient.execute(post);
				charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
				post.releaseConnection();
				
			}

			// Requête CREW
			// Cookie cookie = new Cookie(this.urlCrew, "crewid",
			// "kty4r5dy46yk4jtry5tf", "/", null, false);
			// ChopeCrew.httpClient.getState().addCookie(cookie);

			url = new URI("https://" + urlCrew + "/crew/main");
			post.setURI(url);

			ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("origine", "https://" + urlIpn));
			post.setEntity(new UrlEncodedFormEntity(nvps));

			ChopeCrew.httpClient.execute(post);
			post.releaseConnection();

			deltaMois = deltamois;

			System.out.println("Connecté à Crew !");
			setChanged();
			this.notifyObservers("Connecté à Crew !");

			return 1;
		}

		catch (Exception e) {
			e.printStackTrace();

			System.out.println("Echec de la connexion");
			setChanged();
			this.notifyObservers("Echec de la connexion");

			get.releaseConnection();
			post.releaseConnection();

			return 0;
		}
	}
    
    private int connectON_BAK(final int deltamois) {
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
            this.deltaMois = deltamois;
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
    
    private void importPageIntroPlanningON() {
        final HttpGet get = new HttpGet();
        try {
            System.out.println("Requ\u00eate menu pour type PN");
            this.setChanged();
            this.notifyObservers("Requ\u00eate type PN ...");
            final URI url = new URI("https://" + this.urlCrew + "/crew/main?event=IntroPlanning");
            get.setURI(url);
            final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            final String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
            final InputStream is = response.getEntity().getContent();
            this.pageIntroPlanning = Utils.streamToString(is, charset);
            get.releaseConnection();
            System.out.println("Page intro planning obtenue");
            this.setChanged();
            this.notifyObservers("Type PN obtenu !");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec requ\u00eate page intro planning");
            this.setChanged();
            this.notifyObservers("Echec requ\u00eate type PN");
            get.releaseConnection();
        }
    }
    
    private void importPageMensuelleON() {
        final HttpGet get = new HttpGet();
        try {
            System.out.println("Requ\u00eate page mensuelle");
            this.setChanged();
            this.notifyObservers("Requ\u00eate activit\u00e9s sol ...");
            final URI url = new URI("https://" + this.urlCrew + "/crew/main?event=planning&deltaMois=" + String.valueOf(this.deltaMois));
            get.setURI(url);
            final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            final String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
            final InputStream is = response.getEntity().getContent();
            this.pageMensuelle = Utils.streamToString(is, charset);
            get.releaseConnection();
            System.out.println("Page mensuelle obtenue");
            this.setChanged();
            this.notifyObservers("Activit\u00e9s sol obtenues !");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec requ\u00eate page mensuelle");
            this.setChanged();
            this.notifyObservers("Echec requ\u00eate activit\u00e9s sol");
            get.releaseConnection();
        }
    }
    
    private void importPageImpressionON() {
        final HttpGet get = new HttpGet();
        try {
            System.out.println("Requ\u00eate page impression");
            this.setChanged();
            this.notifyObservers("Requ\u00eate rotations ...");
            URI url = new URI("https://" + this.urlCrew + "/crew/main?event=printRotations");
            get.setURI(url);
            ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            url = new URI("https://" + this.urlCrew + "/crew/main?event=impressionRotations&deltaMois=" + String.valueOf(this.deltaMois));
            get.setURI(url);
            final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            final String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
            final InputStream is = response.getEntity().getContent();
            this.pageImpression = Utils.streamToString(is, charset);
            get.releaseConnection();
            System.out.println("Page impression obtenue");
            this.setChanged();
            this.notifyObservers("Rotations obtenues !");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec requ\u00eate page impression");
            this.setChanged();
            this.notifyObservers("Echec requ\u00eate rotations");
            get.releaseConnection();
        }
    }
    
    private void importExportPdaON() {
        final String nl = System.getProperty("line.separator");
        String str_deltaMois = "";
        switch (this.deltaMois) {
            case 0: {
                str_deltaMois = "moisM";
                break;
            }
            case 1: {
                str_deltaMois = "moisM1";
                break;
            }
            case 2: {
                str_deltaMois = "moisM2";
                break;
            }
        }
        final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("typeFichier", "csv"));
        nvps.add(new BasicNameValuePair("couperEvenementChevauchement", "false"));
        nvps.add(new BasicNameValuePair(str_deltaMois, String.valueOf(this.deltaMois)));
        nvps.add(new BasicNameValuePair("zoneTemps", "Europe/Paris"));
        nvps.add(new BasicNameValuePair("voirVol", "true"));
        nvps.add(new BasicNameValuePair("rotVoirTerrain", "true"));
        nvps.add(new BasicNameValuePair("volVoirTerrain", "true"));
        nvps.add(new BasicNameValuePair("volVoirEquip", "true"));
        nvps.add(new BasicNameValuePair("rotVoirEquip", "true"));
        nvps.add(new BasicNameValuePair("voirIdem", "true"));
        nvps.add(new BasicNameValuePair("voirPresta", "true"));
        nvps.add(new BasicNameValuePair("voirSol", "true"));
        nvps.add(new BasicNameValuePair("voirConge", "true"));
        nvps.add(new BasicNameValuePair("voirReserve", "true"));
        nvps.add(new BasicNameValuePair("voirRepos", "true"));
        nvps.add(new BasicNameValuePair("voirStage", "true"));
        nvps.add(new BasicNameValuePair("voirInstruct", "true"));
        final HttpGet get = new HttpGet();
        final HttpPost post = new HttpPost();
        try {
            System.out.println("Requ\u00eate export Pda");
            this.setChanged();
            this.notifyObservers("Requ\u00eate infos ...");
            URI url = new URI("https://" + this.urlCrew + "/crew/main?event=exportPDA");
            get.setURI(url);
            ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            this.setChanged();
            this.notifyObservers("En attente des infos ...");
            url = new URI("https://" + this.urlCrew + "/crew/main?event=exportPDAcsv");
            post.setURI(url);
            post.setEntity(new UrlEncodedFormEntity(nvps));
            ChopeCrew.httpClient.execute((HttpUriRequest)post);
            post.releaseConnection();
            url = new URI("https://" + this.urlCrew + "/crew/main?event=exportPDAzip");
            get.setURI(url);
            final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            final InputStream is = response.getEntity().getContent();
            final BufferedInputStream bis = new BufferedInputStream(is);
            final ZipInputStream zis = new ZipInputStream(bis);
            zis.getNextEntry();
            final int BUFFER = 1000;
            final byte[] data = new byte[BUFFER];
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BufferedOutputStream bos = new BufferedOutputStream(baos, BUFFER);
            int count;
            while ((count = zis.read(data, 0, BUFFER)) != -1) {
                bos.write(data, 0, count);
            }
            bos.close();
            baos.close();
            zis.close();
            bis.close();
            is.close();
            get.releaseConnection();
            System.out.println("Export Pda obtenu");
            this.setChanged();
            this.notifyObservers("Infos obtenues !");
            this.exportPda = baos.toString("ISO-8859-1").replaceAll("\r\n?|\n", nl);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec requ\u00eate export Pda");
            this.setChanged();
            this.notifyObservers("Echec requ\u00eate infos compl\u00e9mentaires");
            get.releaseConnection();
            post.releaseConnection();
        }
    }
    
    private void importExportPdaIcsON() {
        final String nl = System.getProperty("line.separator");
        String str_deltaMois = "";
        switch (this.deltaMois) {
            case 0: {
                str_deltaMois = "moisM";
                break;
            }
            case 1: {
                str_deltaMois = "moisM1";
                break;
            }
            case 2: {
                str_deltaMois = "moisM2";
                break;
            }
        }
        final ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("typeFichier", "ics"));
        nvps.add(new BasicNameValuePair("couperEvenementJourEntier", "false"));
        nvps.add(new BasicNameValuePair("couperEvenementChevauchement", "false"));
        nvps.add(new BasicNameValuePair(str_deltaMois, String.valueOf(this.deltaMois)));
        nvps.add(new BasicNameValuePair("zoneTemps", "UTC"));
        nvps.add(new BasicNameValuePair("voirVol", "true"));
        nvps.add(new BasicNameValuePair("rotVoirTerrain", "true"));
        nvps.add(new BasicNameValuePair("volVoirTerrain", "true"));
        nvps.add(new BasicNameValuePair("volVoirEquip", "true"));
        nvps.add(new BasicNameValuePair("rotVoirEquip", "true"));
        nvps.add(new BasicNameValuePair("voirIdem", "true"));
        nvps.add(new BasicNameValuePair("voirPresta", "true"));
        nvps.add(new BasicNameValuePair("voirSol", "false"));
        nvps.add(new BasicNameValuePair("voirConge", "false"));
        nvps.add(new BasicNameValuePair("voirReserve", "false"));
        nvps.add(new BasicNameValuePair("voirRepos", "false"));
        nvps.add(new BasicNameValuePair("voirStage", "false"));
        nvps.add(new BasicNameValuePair("voirInstruct", "false"));
        final HttpGet get = new HttpGet();
        final HttpPost post = new HttpPost();
        try {
            System.out.println("Requ\u00eate export PdaIcs");
            this.setChanged();
            this.notifyObservers("Requ\u00eate infos ...");
            URI url = new URI("https://" + this.urlCrew + "/crew/main?event=exportPDA");
            get.setURI(url);
            ChopeCrew.httpClient.execute((HttpUriRequest)get);
            get.releaseConnection();
            this.setChanged();
            this.notifyObservers("En attente des infos ...");
            url = new URI("https://" + this.urlCrew + "/crew/main?event=exportPDAics");
            post.setURI(url);
            post.setEntity(new UrlEncodedFormEntity(nvps));
            ChopeCrew.httpClient.execute((HttpUriRequest)post);
            post.releaseConnection();
            url = new URI("https://" + this.urlCrew + "/crew/main?event=exportPDAzip");
            get.setURI(url);
            final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
            final InputStream is = response.getEntity().getContent();
            final BufferedInputStream bis = new BufferedInputStream(is);
            final ZipInputStream zis = new ZipInputStream(bis);
            zis.getNextEntry();
            final int BUFFER = 1000;
            final byte[] data = new byte[BUFFER];
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final BufferedOutputStream bos = new BufferedOutputStream(baos, BUFFER);
            int count;
            while ((count = zis.read(data, 0, BUFFER)) != -1) {
                bos.write(data, 0, count);
            }
            bos.close();
            baos.close();
            zis.close();
            bis.close();
            is.close();
            get.releaseConnection();
            System.out.println("Export PdaIcs obtenu");
            this.setChanged();
            this.notifyObservers("Infos obtenues !");
            this.exportPdaIcs = baos.toString("UTF-8").replaceAll("\r\n?|\n", nl);
            System.out.println(this.exportPdaIcs);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec requ\u00eate export PdaIcs");
            this.setChanged();
            this.notifyObservers("Echec requ\u00eate infos compl\u00e9mentaires");
            get.releaseConnection();
            post.releaseConnection();
        }
    }
    
    private void importPagesRotationON() {
        final HttpGet get = new HttpGet();
        try {
            for (int i = 0; i < this.listUrlRotations.size(); ++i) {
                System.out.println("Requ\u00eate rotation");
                this.setChanged();
                this.notifyObservers("D\u00e9tails rotation " + Integer.toString(i + 1) + " sur " + Integer.toString(this.listUrlRotations.size()) + " ...");
                final String urlRotation = this.listUrlRotations.get(i);
                String s = "https://" + this.urlCrew + urlRotation;
                URI url = new URI(s.replaceAll(" ", "%20"));
                get.setURI(url);
                ChopeCrew.httpClient.execute((HttpUriRequest)get);
                get.releaseConnection();
                s = "https://crew.airfrance.fr/crew/main?event=impressionRotation";
                url = new URI(s.replaceAll(" ", "%20"));
                get.setURI(url);
                final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                final String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
                final InputStream is = response.getEntity().getContent();
                this.pagesRotation.add(Utils.streamToString(is, charset));
                get.releaseConnection();
                System.out.println("D\u00e9tails rotation obtenus");
                this.setChanged();
                this.notifyObservers("D\u00e9tails rotation " + Integer.toString(i + 1) + " sur " + Integer.toString(this.listUrlRotations.size()) + " obtenus !");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec requ\u00eate d\u00e9tails des rotations");
            this.setChanged();
            this.notifyObservers("Echec requ\u00eate d\u00e9tails des rotations");
            get.releaseConnection();
        }
    }
    
    private void importPagesStageON() {
        final HttpGet get = new HttpGet();
        try {
            for (int i = 0; i < this.listUrlStage.size(); ++i) {
                System.out.println("Requ\u00eate d\u00e9tails stage");
                this.setChanged();
                this.notifyObservers("D\u00e9tails du stage " + Integer.toString(i + 1) + " sur " + Integer.toString(this.listUrlStage.size()) + " ...");
                final String urlStage = this.listUrlStage.get(i);
                String s = "https://" + this.urlCrew + urlStage;
                URI url = new URI(s.replaceAll(" ", "%20"));
                get.setURI(url);
                ChopeCrew.httpClient.execute((HttpUriRequest)get);
                get.releaseConnection();
                final int index = urlStage.indexOf("stage") + "stage".length();
                s = "https://" + this.urlCrew + urlStage.substring(0, index) + "Retour" + urlStage.substring(index);
                url = new URI(s.replaceAll(" ", "%20"));
                get.setURI(url);
                final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                final String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
                final InputStream is = response.getEntity().getContent();
                this.pagesStage.add(Utils.streamToString(is, charset));
                get.releaseConnection();
                System.out.println("D\u00e9tails stage obtenus");
                this.setChanged();
                this.notifyObservers("D\u00e9tails du stage " + Integer.toString(i + 1) + " sur " + Integer.toString(this.listUrlStage.size()) + " obtenus !");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Echec requ\u00eate d\u00e9tails des stages");
            this.setChanged();
            this.notifyObservers("Echec requ\u00eate d\u00e9tails des stages");
            get.releaseConnection();
        }
    }
    
    private void checkVersion() {
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final HttpGet get = new HttpGet();
                try {
                    System.out.println("V\u00e9rification de la version");
                    get.setURI(new URI("http://chopecrew.free.fr/telecharger/build_number.txt"));
                    final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        final InputStream is = response.getEntity().getContent();
                        final int build = Integer.parseInt(Utils.streamToString(is, "ISO-8859-1").trim());
                        if (20200202 < build) {
                            System.out.println("Nouvelle version disponible");
                            JOptionPane.showMessageDialog(ChopeCrew.mf, "Une nouvelle version de ChopeCREW est disponible !\n            Rendez-vous sur http://chopecrew.free.fr", "ChopeCREW vous informe", -1);
                        }
                    }
                    else {
                        System.out.println("Probl\u00e8me r\u00e9cup\u00e9ration version");
                    }
                    get.releaseConnection();
                }
                catch (Exception e) {
                    System.out.println("Probl\u00e8me version");
                    e.printStackTrace();
                    get.releaseConnection();
                }
            }
        };
        thrCon.start();
    }
    
    private void checkCompteurs() {
        final Thread thrCon = new Thread() {
            @Override
            public void run() {
                final HttpGet get = new HttpGet();
                try {
                    if (ChopeCrew.premiereUtilisationVersion) {
                        System.out.println("Compteur utilisateurs...");
                        get.setURI(new URI("http://chopecrew.free.fr/compteur_jws/compteur_utilisateur.php"));
                        final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                        if (response.getStatusLine().getStatusCode() == 200) {
                            System.out.println("Compteur utilisateurs +1");
                            ChopeCrew.premiereUtilisationVersion = false;
                        }
                        else {
                            System.out.println("Probl\u00e8me compteur utilisateurs");
                        }
                        get.releaseConnection();
                    }
                    System.out.println("Compteur plannings...");
                    get.setURI(new URI("http://chopecrew.free.fr/compteur_jws/compteur_planning.php"));
                    final CloseableHttpResponse response = ChopeCrew.httpClient.execute((HttpUriRequest)get);
                    if (response.getStatusLine().getStatusCode() == 200) {
                        System.out.println("Compteur plannings +1");
                    }
                    else {
                        System.out.println("Probl\u00e8me compteur plannings");
                    }
                    get.releaseConnection();
                }
                catch (Exception e) {
                    System.out.println("Probl\u00e8me compteur (exception)");
                    get.releaseConnection();
                }
            }
        };
        thrCon.start();
    }
    
    public int chope(final String chemin_fichier, final boolean isFlash, final String login, final String password, final String urlIpn) {
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
        this.importPageIntroPlanningOFF();
        this.importPageMensuelleOFF();
        this.importPageImpressionOFF();
        if (!isFlash) {
            this.importExportPdaIcsOFF();
            this.importPagesStageOFF();
        }
        this.scan.close();
        this.scan = null;
        System.out.println("->Planning import\u00e9 !");
        this.setChanged();
        this.notifyObservers("Planning import\u00e9 !");
        this.setChanged();
        this.notifyObservers(1);
        return 1;
    }
    
    public int chope(final int dMois, final boolean isFlash, final String login, final String password, final String urlIpn) {
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
        this.checkVersion();
        final int z = this.connectON(dMois);
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
        this.checkCompteurs();
        this.clear();
        this.importPageIntroPlanningON();
        this.importPageMensuelleON();
        this.importPageImpressionON();
        if (!isFlash) {
            this.importExportPdaIcsON();
            this.chopeListUrlStage();
            this.importPagesStageON();
        }
        System.out.println("->Planning import\u00e9 !");
        this.setChanged();
        this.notifyObservers("Planning import\u00e9 !");
        this.setChanged();
        this.notifyObservers(1);
        ChopeCrew.isKeepAliving = true;
        this.keepAlive();
        return 1;
    }
    
    public String getPageIntroPlanning() {
        return this.pageIntroPlanning;
    }
    
    public String getPageMensuelle() {
        return this.pageMensuelle;
    }
    
    public String getPageImpression() {
        return this.pageImpression;
    }
    
    public String getExportPda() {
        return this.exportPda;
    }
    
    public String getExportPdaIcs() {
        return this.exportPdaIcs;
    }
    
    public ArrayList<String> getPagesStage() {
        return this.pagesStage;
    }
    
    public int getDeltaMois() {
        return this.deltaMois;
    }
    
    public String getSourceAsString() {
        final String nl = System.getProperty("line.separator");
        final StringBuilder sb = new StringBuilder();
        sb.append("--- ChopeCrew ").append(this.deltaMois).append(" ---").append(nl);
        if (this.pageIntroPlanning != null) {
            sb.append("--- Page IntroPlanning ---").append(nl).append(this.pageIntroPlanning).append(nl).append("--- Fin Page IntroPlanning ---").append(nl);
        }
        if (this.pageMensuelle != null) {
            sb.append("--- Page Mensuelle ---").append(nl).append(this.pageMensuelle).append(nl).append("--- Fin Page Mensuelle ---").append(nl);
        }
        if (this.pageImpression != null) {
            sb.append("--- Page Impression ---").append(nl).append(this.pageImpression).append(nl).append("--- Fin Page Impression ---").append(nl);
        }
        if (this.exportPda != null) {
            sb.append("--- Export Pda ---").append(nl).append(this.exportPda).append(nl).append("--- Fin Export Pda ---").append(nl);
        }
        if (this.exportPdaIcs != null) {
            sb.append("--- Export PdaIcs ---").append(nl).append(this.exportPdaIcs).append(nl).append("--- Fin Export PdaIcs ---").append(nl);
        }
        if (this.pagesStage.size() > 0) {
            sb.append("--- Pages Stages ---").append(nl);
            for (final String pageStage : this.pagesStage) {
                sb.append("--- Stage ---").append(nl);
                sb.append(pageStage).append(nl);
                sb.append("--- Fin Stage ---").append(nl);
            }
            sb.append("--- Fin Pages Stages ---").append(nl);
        }
        sb.append("--- Fin ChopeCrew ---");
        return sb.toString();
    }
    
    private void chopeListUrlRotations() {
        final String cible = "event=navigationActivite.*?(&numrot.*?)\"";
        final Pattern regex = Pattern.compile(cible);
        final Matcher result = regex.matcher(this.pageMensuelle);
        while (result.find()) {
            final String urlRotation = "/crew/main?event=rotation" + result.group(1);
            if (!this.listUrlRotations.contains(urlRotation)) {
                this.listUrlRotations.add(urlRotation);
            }
        }
    }
    
    private void chopeListUrlStage() {
        final String cible = "event=navigationActivite.*?(&dateBlocDeb.*?codestage.*?)\"";
        final Pattern regex = Pattern.compile(cible);
        final Matcher result = regex.matcher(this.pageMensuelle);
        while (result.find()) {
            final String urlStage = "/crew/main?event=stage" + result.group(1);
            if (!this.listUrlStage.contains(urlStage)) {
                this.listUrlStage.add(urlStage);
            }
        }
    }
    
    private void clear() {
        this.pageIntroPlanning = null;
        this.pageMensuelle = null;
        this.pageImpression = null;
        this.exportPda = null;
        this.exportPdaIcs = null;
        this.listUrlStage.clear();
        this.pagesStage.clear();
    }
    
	private ArrayList<NameValuePair> fetchParamsDom(String src, String formName) {
		ArrayList<NameValuePair> alParams = new ArrayList<NameValuePair>();
		org.jsoup.nodes.Document doc = Jsoup.parse(src);

		if (formName != null) {
			Elements elements = doc.select("form[id=nuxForm] > input[type=hidden]");
			for (Element elem : elements) {
				alParams.add(new BasicNameValuePair(elem.attr("name"), elem.attr("value")));
			}
			return alParams;
		}

		Elements elements = doc.select("input[type=hidden]");
		for (Element elem : elements) {
			alParams.add(new BasicNameValuePair(elem.attr("name"), elem.attr("value")));
		}
		return alParams;
	}

	private String fetchFedIdp(String src) {
		org.jsoup.nodes.Document doc = Jsoup.parse(src);
		Element element = doc.select("form[id=nuxForm]").first();

		Pattern pattern;
		Matcher result;
		pattern = Pattern.compile("/idp/(.*)/resumeSAML20/idp/SSO.ping");
		result = pattern.matcher(element.attr("action"));

		if (result.find()) {
			return result.group(1);
		}
		return null;
	}

	private String fetchFormUrl(String src, String formName) {
		org.jsoup.nodes.Document doc = Jsoup.parse(src);
		Element element;
		if (formName != null) {
			element = doc.select("form[id=" + formName + "]").first();
		} else {
			element = doc.select("form").first();
		}

		return element.attr("action");
	}

	private void replaceArrayListItem(ArrayList<NameValuePair> list, String itemName, String itemValue) {
		BasicNameValuePair nvp;
		// get item index
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			nvp = (BasicNameValuePair) list.get(i);
			if (nvp.getName().equals(itemName)) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			list.set(index, new BasicNameValuePair(itemName, itemValue));
		}
	}

	private int findMfaIsAuthentIndex(ArrayList<NameValuePair> list) {
		BasicNameValuePair nvp;
		for (int i = 0; i < list.size(); i++) {
			nvp = (BasicNameValuePair) list.get(i);
			if (nvp.getName().equals("mfaIsAuthent")) {
				return i;
			}
		}
		return -1;
	}
}
