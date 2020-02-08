package ccEngine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.print.attribute.Size2DSyntax;
import javax.sound.sampled.spi.FormatConversionProvider;
import javax.swing.JOptionPane;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import ccUtils.Utils;
import chopeCrew.ChopeCrew;

public class ConnexionCrew extends Observable {

	public static final int OK = 1; // Planning import�
	public static final int EXCEPTION = 0; // Erreur impr�vue
	public static final int ID_FIC_INVALIDE = -1; // Identifiants (online) ou Fichier invalide (offline)

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:72.0) Gecko/20100101 Firefox/72.0";
	// Donn�es par m�thodes chope (OFF/ON)
	private String login;
	private String password;
	private String urlCrew;
	private String urlIpn;

	// Donn�es par m�thodes connect (OFF/ON)
	private int deltaMois;
	private Scanner scan;

	// Donn�es par m�thodes importXXX (OFF/ON)
	private String pageIntroPlanning;
	private String pageMensuelle;
	private String pageImpression;
	private String exportPda;
	private String exportPdaIcs;
	private ArrayList<String> pagesStage;

	// Donn�es par m�thode chopeListUrlStage
	private ArrayList<String> listUrlStage;

	public ConnexionCrew() {
		pagesStage = new ArrayList<String>();
		listUrlStage = new ArrayList<String>();
	}

	/**
	 * "Connexion" au fichier
	 * 
	 * @return 1=ok , 0=exception , -1=fichier invalide
	 */
	private int connectOFF(String chemin_fichier) {

		System.out.println("Ouverture du fichier ...");
		setChanged();
		this.notifyObservers("Ouverture du fichier ...");

		try {
			scan = new Scanner(new File(chemin_fichier), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Echec ouverture du fichier");
			setChanged();
			this.notifyObservers("Echec ouverture du fichier");
			return 0;
		}

		if (scan.findWithinHorizon("--- ChopeCrew ", 0) != null) {
			deltaMois = scan.nextInt();
			System.out.println("Fichier reconnu !");
			setChanged();
			this.notifyObservers("Fichier reconnu !");
			return 1;
		} else {
			System.out.println("Fichier invalide !");
			setChanged();
			this.notifyObservers("Fichier invalide !");
			return -1;
		}
	}

	private void importPageIntroPlanningOFF() {

		String nl = System.getProperty("line.separator");
		StringBuilder sb;
		String tmp;

		String debut = "--- Page IntroPlanning ---";
		String fin = "--- Fin Page IntroPlanning ---";

		if (scan.findWithinHorizon(debut, 0) == null) {
			return;
		}

		sb = new StringBuilder();
		while (scan.hasNextLine()) {
			if ((tmp = scan.nextLine()).equals(fin)) {
				break;
			}
			sb.append(tmp).append(nl);
		}

		System.out.println("Page intro planning obtenue");
		setChanged();
		this.notifyObservers("Type PN obtenu !");

		pageIntroPlanning = sb.toString();
	}

	private void importPageMensuelleOFF() {

		String nl = System.getProperty("line.separator");
		StringBuilder sb;
		String tmp;

		String debut = "--- Page Mensuelle ---";
		String fin = "--- Fin Page Mensuelle ---";

		if (scan.findWithinHorizon(debut, 0) == null) {
			return;
		}

		sb = new StringBuilder();
		while (scan.hasNextLine()) {
			if ((tmp = scan.nextLine()).equals(fin)) {
				break;
			}
			sb.append(tmp).append(nl);
		}

		System.out.println("Page mensuelle obtenue");
		setChanged();
		this.notifyObservers("Activit�s sol obtenues !");

		pageMensuelle = sb.toString();
	}

	private void importPageImpressionOFF() {

		String nl = System.getProperty("line.separator");
		StringBuilder sb;
		String tmp;

		String debut = "--- Page Impression ---";
		String fin = "--- Fin Page Impression ---";

		if (scan.findWithinHorizon(debut, 0) == null) {
			return;
		}

		sb = new StringBuilder();
		while (scan.hasNextLine()) {
			if ((tmp = scan.nextLine()).equals(fin)) {
				break;
			}
			sb.append(tmp).append(nl);
		}

		System.out.println("Page impression obtenue");
		setChanged();
		this.notifyObservers("Rotations obtenues !");

		pageImpression = sb.toString();
	}

	private void importExportPdaOFF() {

		String nl = System.getProperty("line.separator");
		StringBuilder sb;
		String tmp;

		String debut = "--- Export Pda ---";
		String fin = "--- Fin Export Pda ---";

		if (scan.findWithinHorizon(debut, 0) == null) {
			return;
		}

		sb = new StringBuilder();
		while (scan.hasNextLine()) {
			if ((tmp = scan.nextLine()).equals(fin)) {
				break;
			}
			sb.append(tmp).append(nl);
		}

		System.out.println("Export Pda obtenu");
		setChanged();
		this.notifyObservers("Infos obtenues !");

		exportPda = sb.toString();
	}

	private void importExportPdaIcsOFF() {

		String nl = System.getProperty("line.separator");
		StringBuilder sb;
		String tmp;

		String debut = "--- Export PdaIcs ---";
		String fin = "--- Fin Export PdaIcs ---";

		if (scan.findWithinHorizon(debut, 0) == null) {
			return;
		}

		sb = new StringBuilder();
		while (scan.hasNextLine()) {
			if ((tmp = scan.nextLine()).equals(fin)) {
				break;
			}
			sb.append(tmp).append(nl);
		}

		System.out.println("Export PdaIcs obtenu");
		setChanged();
		this.notifyObservers("Infos obtenues !");

		exportPdaIcs = sb.toString();
	}

	private void importPagesStageOFF() {

		String nl = System.getProperty("line.separator");
		StringBuilder sb;
		String tmp;

		String debut = "--- Stage ---";
		String fin = "--- Fin Stage ---";

		if (scan.findWithinHorizon("--- Pages Stages ---", 0) == null) {
			return;
		}

		while (scan.findWithinHorizon(debut, 0) != null) {
			sb = new StringBuilder();
			while (!(tmp = scan.nextLine()).equals(fin)) {
				sb.append(tmp).append(nl);
			}
			pagesStage.add(sb.toString());
		}

		System.out.println("Pages stages obtenues");
		setChanged();
		this.notifyObservers("Stages obtenus !");
	}

	private void keepAlive() {

		HttpGet get = new HttpGet();
		CloseableHttpResponse response;

		URI url;
		int i = 0;

		try {
			while (ChopeCrew.isKeepAliving == true) {
				// Requ�te Ipn
				url = new URI("https://" + urlIpn);
				get.setURI(url);
				response = ChopeCrew.httpClient.execute(get);
				if ((response.getFirstHeader("Location") != null)
						&& (response.getFirstHeader("Location").getValue().contains("siteminderagent"))) {
					System.out.println("Redirection Habile ...");

				}

				get.releaseConnection();
				i = i + 1;
				System.out.println("Alive " + i);
				Thread.sleep(300000);// 5 minutes
			}

		} catch (Exception e) {
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

	/**
	 * Connexion � Crew
	 * 
	 * @return 1=ok , 0=exception, -1=erreur login/pass
	 */
	private int connectON_CR(int deltamois) {

		System.out.println("Connexion Crew en cours ...");
		setChanged();
		this.notifyObservers("Connexion Crew en cours ...");

		HttpGet get = new HttpGet();
		HttpPost post = new HttpPost();
		CloseableHttpResponse response;

		HttpResponse httpResponse;
		HttpGet httpGet;
		HttpPost httpPost;

		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		CookieStore cookieStore = new BasicCookieStore();
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
				.setDefaultCookieStore(cookieStore).build();

		URI url;
		String urlRedirection;
		String location;
		String content;
		ArrayList<NameValuePair> formParams;

		try {
			httpGet = new HttpGet("https://intralignes.airfrance.fr/");
			httpResponse = httpClient.execute(httpGet);
			content = scanResponse(httpResponse.getEntity().getContent());
			formParams = fetchParamsDom(content, null);

			//
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
			httpPost = new HttpPost("https://fedidp.airfrance.fr/idp/SSO.saml2");
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			content = scanResponse(httpResponse.getEntity().getContent());
			formParams = fetchParamsDom(content, "nuxForm");

			// send login
			replaceArrayListItem(formParams, "ok", "IDENTIFICATION");
			formParams.add(new BasicNameValuePair("username", login));
			formParams.add(new BasicNameValuePair("userIdInMemory", ""));

			entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
			url = new URI("https://fedidp.airfrance.fr" + fetchFormUrl(content, null));
			httpPost = new HttpPost(url);
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			content = scanResponse(httpResponse.getEntity().getContent());
			formParams = fetchParamsDom(content, null);

			// send token
			replaceArrayListItem(formParams, "ok", "TOK");
			replaceArrayListItem(formParams, "displayFullUserName", login);
			replaceArrayListItem(formParams, "currentAuthMethod", "TOK");
			int index = findMfaIsAuthentIndex(formParams);
			if (index != -1) {
				formParams.add(index, new BasicNameValuePair("inputTokName", password));
			}
			formParams.add(new BasicNameValuePair("otpAuth", ""));

			entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
			httpPost = new HttpPost(url);
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			content = scanResponse(httpResponse.getEntity().getContent());

			// redirect
			formParams = fetchParamsDom(content, null);
			entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
			url = new URI(fetchFormUrl(content, null));
			httpPost = new HttpPost(url);
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			content = scanResponse(httpResponse.getEntity().getContent());

			// redirect
			formParams = fetchParamsDom(content, null);
			entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
			url = new URI(fetchFormUrl(content, null));
			httpPost = new HttpPost(url);
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			content = scanResponse(httpResponse.getEntity().getContent());

			// connexion intralignes
			httpGet = new HttpGet("https://intralignes.airfrance.fr/group/guest");
			httpResponse = httpClient.execute(httpGet);
			content = scanResponse(httpResponse.getEntity().getContent());

			// connexion Crew
			httpGet = new HttpGet("https://crew.airfrance.fr");
			httpResponse = httpClient.execute(httpGet);
			content = scanResponse(httpResponse.getEntity().getContent());

			// connexion Crew main
			formParams = new ArrayList<>();
			formParams.add(new BasicNameValuePair("origine", "https://intralignes.airfrance.fr/group/guest"));
			entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
			url = new URI("https://crew.airfrance.fr/crew/main");
			httpPost = new HttpPost(url);
			httpPost.setEntity(entity);
			httpResponse = ChopeCrew.httpClient.execute(httpPost);
			content = scanResponse(httpResponse.getEntity().getContent());

			System.out.println("Connect� � Crew !");
			setChanged();
			this.notifyObservers("Connect� � Crew !");

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

	/**
	 * Connexion � Crew
	 * 
	 * @return 1=ok , 0=exception, -1=erreur login/pass
	 */
	private int connectON_OLD(int deltamois) {

		System.out.println("Connexion Crew en cours ...");
		setChanged();
		this.notifyObservers("Connexion Crew en cours ...");

		HttpGet get = new HttpGet();
		HttpPost post = new HttpPost();
		CloseableHttpResponse response;

		URI url;
		String urlRedirection;
		String location;

		try {
			// Requ�te Ipn
			url = new URI("https://intralignes.airfrance.fr");
			get.setURI(url);
			response = ChopeCrew.httpClient.execute(get);
			location = response.getFirstHeader("Location").getValue();

			get.releaseConnection();
			// Si on est redirig� vers la mire Habile, on s'identifie
			if ((response.getFirstHeader("Location") != null)
					&& (response.getFirstHeader("Location").getValue().contains("siteminderagent"))) {
				urlRedirection = response.getFirstHeader("Location").getValue();

				System.out.println("Authentification Habile ...");
				setChanged();
				this.notifyObservers("Authentification Habile ...");

				url = new URI(urlRedirection);
				post.setURI(url);

				ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("USERNAME", login));
				nvps.add(new BasicNameValuePair("PASSWORD", password));
				nvps.add(new BasicNameValuePair("target", "https://" + urlIpn));
				nvps.add(new BasicNameValuePair("smagentname", "agt_" + urlIpn + "_dmz-internet"));
				post.setEntity(new UrlEncodedFormEntity(nvps));

				response = ChopeCrew.httpClient.execute(post);
				post.releaseConnection();

				// Test redirection (r�ussite mot de passe)
				if ((response.getFirstHeader("Location") == null)
						|| (response.getFirstHeader("Location").getValue().contains("siteminderagent"))) {
					System.out.println("Acc�s refus� !");
					setChanged();
					this.notifyObservers("Acc�s refus� !");
					return -1;
				} else {
					System.out.println("Acc�s accord� !");
					setChanged();
					this.notifyObservers("Acc�s accord� !");
				}
			}

			// Requ�te CREW
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

			System.out.println("Connect� � Crew !");
			setChanged();
			this.notifyObservers("Connect� � Crew !");

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

	private void importPageIntroPlanningON() {

		HttpGet get = new HttpGet();
		CloseableHttpResponse response;
		URI url;

		try {
			System.out.println("Requ�te menu pour type PN");
			setChanged();
			this.notifyObservers("Requ�te type PN ...");

			url = new URI("https://" + urlCrew + "/crew/main?event=IntroPlanning");
			get.setURI(url);
			response = ChopeCrew.httpClient.execute(get);

			String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
			InputStream is = response.getEntity().getContent();
			pageIntroPlanning = Utils.streamToString(is, charset);
			get.releaseConnection();

			System.out.println("Page intro planning obtenue");
			setChanged();
			this.notifyObservers("Type PN obtenu !");
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Echec requ�te page intro planning");
			setChanged();
			this.notifyObservers("Echec requ�te type PN");

			get.releaseConnection();
		}
	}

	private void importPageMensuelleON() {

		HttpGet get = new HttpGet();
		CloseableHttpResponse response;
		URI url;

		try {
			System.out.println("Requ�te page mensuelle");
			setChanged();
			this.notifyObservers("Requ�te activit�s sol ...");

			// url = new URI("https://" + urlCrew + "/crew/main?event=planning&deltaMois=" +
			// String.valueOf(deltaMois));
			url = new URI(
					"https://" + urlCrew + "/crew/main?event=plannings&selOnglet=" + String.valueOf(deltaMois + 3));

			get.setURI(url);
			get.addHeader("Referer",
					"https://crew.airfrance.fr/crew/main?event=menu&version_browser=;5.0%20(Windows;&autorise=true");
			response = ChopeCrew.httpClient.execute(get);

			String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
			InputStream is = response.getEntity().getContent();
			pageMensuelle = Utils.streamToString(is, charset);
			get.releaseConnection();

			System.out.println("Page mensuelle obtenue");
			setChanged();
			this.notifyObservers("Activit�s sol obtenues !");
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Echec requ�te page mensuelle");
			setChanged();
			this.notifyObservers("Echec requ�te activit�s sol");

			get.releaseConnection();
		}
	}

	private void importPageImpressionON() {

		HttpGet get = new HttpGet();
		CloseableHttpResponse response;
		URI url;

		try {
			System.out.println("Requ�te page impression");
			setChanged();
			this.notifyObservers("Requ�te rotations ...");

			url = new URI("https://" + urlCrew + "/crew/main?event=printRotations");
			get.setURI(url);
			ChopeCrew.httpClient.execute(get);
			get.releaseConnection();

			url = new URI("https://" + urlCrew + "/crew/main?event=impressionRotations&deltaMois="
					+ String.valueOf(deltaMois));
			get.setURI(url);
			response = ChopeCrew.httpClient.execute(get);

			String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
			InputStream is = response.getEntity().getContent();
			pageImpression = Utils.streamToString(is, charset);
			get.releaseConnection();

			System.out.println("Page impression obtenue");
			setChanged();
			this.notifyObservers("Rotations obtenues !");
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Echec requ�te page impression");
			setChanged();
			this.notifyObservers("Echec requ�te rotations");

			get.releaseConnection();
		}
	}

	private void importExportPdaON() {

		String nl = System.getProperty("line.separator");
		String str_deltaMois = "";

		switch (deltaMois) {
		case 0:
			str_deltaMois = "moisM";
			break;
		case 1:
			str_deltaMois = "moisM1";
			break;
		case 2:
			str_deltaMois = "moisM2";
			break;
		}

		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("typeFichier", "csv"));
		nvps.add(new BasicNameValuePair("couperEvenementChevauchement", "false"));
		nvps.add(new BasicNameValuePair(str_deltaMois, String.valueOf(deltaMois)));
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

		HttpGet get = new HttpGet();
		HttpPost post = new HttpPost();
		CloseableHttpResponse response;
		URI url;

		try {
			System.out.println("Requ�te export Pda");
			setChanged();
			this.notifyObservers("Requ�te infos ...");

			// requ�tes sur exportPDA
			url = new URI("https://" + urlCrew + "/crew/main?event=exportPDA");
			get.setURI(url);
			ChopeCrew.httpClient.execute(get);
			get.releaseConnection();

			setChanged();
			this.notifyObservers("En attente des infos ...");

			url = new URI("https://" + urlCrew + "/crew/main?event=exportPDAcsv");
			post.setURI(url);
			post.setEntity(new UrlEncodedFormEntity(nvps));
			ChopeCrew.httpClient.execute(post);
			post.releaseConnection();

			url = new URI("https://" + urlCrew + "/crew/main?event=exportPDAzip");
			get.setURI(url);
			response = ChopeCrew.httpClient.execute(get);

			// String charset =
			// ContentType.getOrDefault(response.getEntity()).getCharset().name();
			InputStream is = response.getEntity().getContent();
			BufferedInputStream bis = new BufferedInputStream(is);
			ZipInputStream zis = new ZipInputStream(bis);

			zis.getNextEntry();
			int BUFFER = 1000;
			int count;
			byte data[] = new byte[BUFFER];

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(baos, BUFFER);

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
			setChanged();
			this.notifyObservers("Infos obtenues !");

			// Le txt de Crew utilise parfois \n, parfois \r, parfois \r\n
			exportPda = baos.toString("ISO-8859-1").replaceAll("\r\n?|\n", nl);
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Echec requ�te export Pda");
			setChanged();
			this.notifyObservers("Echec requ�te infos compl�mentaires");

			get.releaseConnection();
			post.releaseConnection();
		}
	}

	private void importExportPdaIcsON() {

		String nl = System.getProperty("line.separator");
		String str_deltaMois = "";

		switch (deltaMois) {
		case 0:
			str_deltaMois = "moisM";
			break;
		case 1:
			str_deltaMois = "moisM1";
			break;
		case 2:
			str_deltaMois = "moisM2";
			break;
		}

		ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("typeFichier", "ics"));
		nvps.add(new BasicNameValuePair("couperEvenementJourEntier", "false"));
		nvps.add(new BasicNameValuePair("couperEvenementChevauchement", "false"));
		nvps.add(new BasicNameValuePair(str_deltaMois, String.valueOf(deltaMois)));
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

		HttpGet get = new HttpGet();
		HttpPost post = new HttpPost();
		CloseableHttpResponse response;
		URI url;

		try {
			System.out.println("Requ�te export PdaIcs");
			setChanged();
			this.notifyObservers("Requ�te infos ...");

			// requ�tes sur exportPDA
			url = new URI("https://" + urlCrew + "/crew/main?event=exportPDA");
			get.setURI(url);
			ChopeCrew.httpClient.execute(get);
			get.releaseConnection();

			setChanged();
			this.notifyObservers("En attente des infos ...");

			url = new URI("https://" + urlCrew + "/crew/main?event=exportPDAics");
			post.setURI(url);
			post.setEntity(new UrlEncodedFormEntity(nvps));
			ChopeCrew.httpClient.execute(post);
			post.releaseConnection();

			url = new URI("https://" + urlCrew + "/crew/main?event=exportPDAzip");
			get.setURI(url);
			response = ChopeCrew.httpClient.execute(get);

			// String charset =
			// ContentType.getOrDefault(response.getEntity()).getCharset().name();
			InputStream is = response.getEntity().getContent();
			BufferedInputStream bis = new BufferedInputStream(is);
			ZipInputStream zis = new ZipInputStream(bis);

			zis.getNextEntry();
			int BUFFER = 1000;
			int count;
			byte data[] = new byte[BUFFER];

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(baos, BUFFER);

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
			setChanged();
			this.notifyObservers("Infos obtenues !");

			// Le txt de Crew utilise parfois \n, parfois \r, parfois \r\n
			exportPdaIcs = baos.toString("UTF-8").replaceAll("\r\n?|\n", nl);
			System.out.println(exportPdaIcs);
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Echec requ�te export PdaIcs");
			setChanged();
			this.notifyObservers("Echec requ�te infos compl�mentaires");

			get.releaseConnection();
			post.releaseConnection();
		}
	}

	private void importPagesStageON() {

		HttpGet get = new HttpGet();
		CloseableHttpResponse response;
		URI url;
		String s, urlStage;
		int index;

		try {
			for (int i = 0; i < listUrlStage.size(); i++) {

				System.out.println("Requ�te d�tails stage");
				setChanged();
				this.notifyObservers("D�tails du stage " + Integer.toString(i + 1) + " sur "
						+ Integer.toString(listUrlStage.size()) + " ...");

				urlStage = listUrlStage.get(i);
				s = "https://" + urlCrew + urlStage;
				url = new URI(s.replaceAll(" ", "%20"));
				get.setURI(url);
				ChopeCrew.httpClient.execute(get);
				get.releaseConnection();

				index = urlStage.indexOf("stage") + "stage".length();
				s = "https://" + urlCrew + urlStage.substring(0, index) + "Retour" + urlStage.substring(index);
				url = new URI(s.replaceAll(" ", "%20"));
				get.setURI(url);
				response = ChopeCrew.httpClient.execute(get);

				String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
				InputStream is = response.getEntity().getContent();
				pagesStage.add(Utils.streamToString(is, charset));
				get.releaseConnection();

				System.out.println("D�tails stage obtenus");
				setChanged();
				this.notifyObservers("D�tails du stage " + Integer.toString(i + 1) + " sur "
						+ Integer.toString(listUrlStage.size()) + " obtenus !");
			}
		} catch (Exception e) {
			e.printStackTrace();

			System.out.println("Echec requ�te d�tails des stages");
			setChanged();
			this.notifyObservers("Echec requ�te d�tails des stages");

			get.releaseConnection();
		}
	}

	private void checkVersion() {
		Thread thrCon = new Thread() {

			@Override
			public void run() {

				HttpGet get = new HttpGet();
				CloseableHttpResponse response;
				try {
					System.out.println("V�rification de la version");

					get.setURI(new URI("http://chopecrew.free.fr/telecharger/build_number.txt"));
					response = ChopeCrew.httpClient.execute(get);

					if (response.getStatusLine().getStatusCode() == 200) {

						// String charset =
						// ContentType.getOrDefault(response.getEntity()).getCharset().name();
						InputStream is = response.getEntity().getContent();
						int build = Integer.parseInt(Utils.streamToString(is, "ISO-8859-1").trim());

						if (ChopeCrew.buildNumber < build) {
							System.out.println("Nouvelle version disponible");
							JOptionPane.showMessageDialog(ChopeCrew.mf,
									"Une nouvelle version de ChopeCREW est disponible !\n"
											+ "            Rendez-vous sur http://chopecrew.free.fr",
									"ChopeCREW vous informe", JOptionPane.PLAIN_MESSAGE);
						}
					} else {
						System.out.println("Probl�me r�cup�ration version");
					}
					get.releaseConnection();
				} catch (Exception e) {
					System.out.println("Probl�me version");
					e.printStackTrace();
					get.releaseConnection();
				}
			}
		};
		thrCon.start();
	}

	private void checkCompteurs() {
		Thread thrCon = new Thread() {

			@Override
			public void run() {

				HttpGet get = new HttpGet();
				CloseableHttpResponse response;
				try {
					// Compteur utilisateurs
					if (ChopeCrew.premiereUtilisationVersion == true) {
						System.out.println("Compteur utilisateurs...");

						get.setURI(new URI("http://chopecrew.free.fr/compteur_jws/compteur_utilisateur.php"));
						response = ChopeCrew.httpClient.execute(get);

						if (response.getStatusLine().getStatusCode() == 200) {
							System.out.println("Compteur utilisateurs +1");
							ChopeCrew.premiereUtilisationVersion = false;
						} else {
							System.out.println("Probl�me compteur utilisateurs");
						}
						get.releaseConnection();
					}

					// Compteur plannings
					System.out.println("Compteur plannings...");

					get.setURI(new URI("http://chopecrew.free.fr/compteur_jws/compteur_planning.php"));
					response = ChopeCrew.httpClient.execute(get);

					if (response.getStatusLine().getStatusCode() == 200) {
						System.out.println("Compteur plannings +1");
					} else {
						System.out.println("Probl�me compteur plannings");
					}
					get.releaseConnection();
				} catch (Exception e) {
					System.out.println("Probl�me compteur (exception)");
					get.releaseConnection();
				}
			}
		};
		thrCon.start();
	}

	/**
	 * Chope Offline
	 * 
	 * @return 1=ok ("Planning import� !") , 0=erreur impr�vue ("Erreur...") ,
	 *         -1=fichier invalide ("Erreur...")
	 */
	public int chope(String chemin_fichier, boolean isFlash, String login, String password, String urlIpn) {

		this.login = login;
		this.password = password;
		this.urlIpn = urlIpn;
		urlCrew = urlIpn.replaceAll("ipn", "crew");

		int z = connectOFF(chemin_fichier);

		if (z == 0) {
			// Echec ouverture fichier
			System.out.println("->Erreur...(echec ouverture du fichier)");
			setChanged();
			this.notifyObservers(Integer.valueOf(EXCEPTION));
			return EXCEPTION;
		}

		else if (z == -1) {
			// Fichier invalide
			System.out.println("->Erreur...(fichier invalide)");
			setChanged();
			this.notifyObservers(Integer.valueOf(ID_FIC_INVALIDE));
			return ID_FIC_INVALIDE;
		}

		clear();
		importPageIntroPlanningOFF();
		importPageMensuelleOFF();

		ChopeData cd = new ChopeData(pageMensuelle);

		importPageImpressionOFF();
		if (isFlash == false) {
			// importExportPdaOFF();
			importExportPdaIcsOFF();
			importPagesStageOFF();
		}

		scan.close();
		scan = null;

		// Ce message d�clenche l'analyse des pages et le chargement de l'arbre
		System.out.println("->Planning import� !");
		setChanged();
		this.notifyObservers("Planning import� !");
		setChanged();
		this.notifyObservers(Integer.valueOf(OK));

		return OK;
	}

	/**
	 * Chope Online
	 * 
	 * @return 1=ok ("Planning import� !") , 0=erreur impr�vue ("Erreur...") ,
	 *         -1=erreur login/pass ("Erreur...")
	 */
	public int chope(int dMois, boolean isFlash, String login, String password, String urlIpn) {

		this.login = login;
		this.password = password;
		this.urlIpn = urlIpn;
		urlCrew = urlIpn.replaceAll("ipn", "crew");

		if (ChopeCrew.httpClient == null) {
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();// MultiThreaded (check
																								// version/comteur)

			ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {

				public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
					return 3 * 60 * 60 * 1000;// 3h

				}

			};

			ChopeCrew.httpClient = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(myStrategy)
					.disableRedirectHandling().build();

			// ChopeCrew.httpClient =
			// HttpClientBuilder.create().disableRedirectHandling().build();
		}

		checkVersion();

		int z = connectON(dMois);

		if (z == 0) {
			// Erreur imprevue (connexion internet hs)
			System.out.println("->Erreur...(impr�vue)");
			setChanged();
			this.notifyObservers(Integer.valueOf(EXCEPTION));
			return EXCEPTION;
		}

		else if (z == -1) {
			// Identifiants
			System.out.println("->Erreur...(identifiants)");
			setChanged();
			this.notifyObservers(Integer.valueOf(ID_FIC_INVALIDE));
			return ID_FIC_INVALIDE;
		}

		checkCompteurs();

		clear();
		// importPageIntroPlanningON();
		importPageMensuelleON();
		importPageImpressionON();
		if (isFlash == false) {
			// importExportPdaON();
			importExportPdaIcsON();
			chopeListUrlStage();
			importPagesStageON();
		}

		// Ce message d�clenche l'analyse des pages et le chargement de l'arbre
		System.out.println("->Planning import� !");
		setChanged();
		this.notifyObservers("Planning import� !");
		setChanged();
		this.notifyObservers(Integer.valueOf(OK));

		ChopeCrew.isKeepAliving = true;
		keepAlive();

		return OK;
	}

	public String getPageIntroPlanning() {
		return pageIntroPlanning;
	}

	public String getPageMensuelle() {
		return pageMensuelle;
	}

	public String getPageImpression() {
		return pageImpression;
	}

	public String getExportPda() {
		return exportPda;
	}

	public String getExportPdaIcs() {
		return exportPdaIcs;
	}

	public ArrayList<String> getPagesStage() {
		return pagesStage;
	}

	public int getDeltaMois() {
		return deltaMois;
	}

	// Renvoie le contenu des sources sous forme de String
	public String getSourceAsString() {

		String nl = System.getProperty("line.separator");
		StringBuilder sb = new StringBuilder();

		sb.append("--- ChopeCrew ").append(deltaMois).append(" ---").append(nl);

		if (pageIntroPlanning != null) {
			sb.append("--- Page IntroPlanning ---").append(nl).append(pageIntroPlanning).append(nl)
					.append("--- Fin Page IntroPlanning ---").append(nl);
		}
		if (pageMensuelle != null) {
			sb.append("--- Page Mensuelle ---").append(nl).append(pageMensuelle).append(nl)
					.append("--- Fin Page Mensuelle ---").append(nl);
		}
		if (pageImpression != null) {
			sb.append("--- Page Impression ---").append(nl).append(pageImpression).append(nl)
					.append("--- Fin Page Impression ---").append(nl);
		}
		if (exportPda != null) {
			sb.append("--- Export Pda ---").append(nl).append(exportPda).append(nl).append("--- Fin Export Pda ---")
					.append(nl);
		}
		if (exportPdaIcs != null) {
			sb.append("--- Export PdaIcs ---").append(nl).append(exportPdaIcs).append(nl)
					.append("--- Fin Export PdaIcs ---").append(nl);
		}
		if (pagesStage.size() > 0) {
			sb.append("--- Pages Stages ---").append(nl);
			for (String pageStage : pagesStage) {
				sb.append("--- Stage ---").append(nl);
				sb.append(pageStage).append(nl);
				sb.append("--- Fin Stage ---").append(nl);
			}
			sb.append("--- Fin Pages Stages ---").append(nl);
		}

		sb.append("--- Fin ChopeCrew ---");

		return sb.toString();
	}

	// A partir de la page mensuelle, d�termination des url stages
	private void chopeListUrlStage() {

		String cible = "event=navigationActivite.*?(&dateBlocDeb.*?codestage.*?)\"";
		Pattern regex = Pattern.compile(cible);
		Matcher result = regex.matcher(pageMensuelle);
		String urlStage;

		while (result.find()) {
			urlStage = "/crew/main?event=stage" + result.group(1);
			if (!listUrlStage.contains(urlStage)) {
				listUrlStage.add(urlStage);
			}
		}
	}

	private void clear() {
		pageIntroPlanning = null;
		pageMensuelle = null;
		pageImpression = null;
		exportPda = null;
		exportPdaIcs = null;
		listUrlStage.clear();
		pagesStage.clear();
	}

//	private LinkedHashMap<String, String> fetchParamsDom(String src, String formName) {
//		LinkedHashMap<String, String> hmap = new LinkedHashMap<>();
//		org.jsoup.nodes.Document doc = Jsoup.parse(src);
//
//		if (formName != null) {
//			Elements elements = doc.select("form[id=nuxForm] > input[type=hidden]");
//			for (Element elem : elements) {
//				hmap.put(elem.attr("name"), elem.attr("value"));
//			}
//			return hmap;
//		}
//
//		Elements elements = doc.select("input[type=hidden]");
//		for (Element elem : elements) {
//			hmap.put(elem.attr("name"), elem.attr("value"));
//		}
//		return hmap;
//	}

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

//	private void injectCookies(HttpsURLConnection huc, CookieManager cm) {
//		if (cm.getCookieStore().getCookies().size() > 0) {
//			StringBuilder sb = new StringBuilder();
//
//			List<HttpCookie> cookies = cm.getCookieStore().getCookies();
//			for (HttpCookie cookie : cookies) {
//				sb.append(";").append(cookie.toString());
//			}
//			huc.addRequestProperty("Cookie", sb.substring(1));
//		}
//	}

	private String scanResponse(InputStream is) {
		Scanner sc = new Scanner(is);
		StringBuilder sb = new StringBuilder();

		while (sc.hasNext()) {
			sb.append(sc.nextLine());
		}
		sc.close();
		return sb.toString();
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
