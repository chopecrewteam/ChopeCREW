package ccEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import ccStructures.Escale;
import ccStructures.Rotation;
import ccStructures.ServiceVol;
import ccStructures.Troncon;
import ccUtils.Utils;
import chopeCrew.ChopeCrew;

public class ConnexionEdito extends Observable {

	public static final String MSG_OK = "Notices AF exportées !";
	public static final String MSG_ERREUR = "Erreur d'identifiant ou de mot de passe";
	public static final String MSG_EXCEPTION = "Erreur imprévue";
	public static final String MSG_CONNECTED = "Connecté Edito !";
	
	private String login = null;
	private String password = null;
	private String urlEdito = null;
	private String urlIpn = null;

	private String repertoire = null;
	private ArrayList<Escale> listEscalesAF = null;
	private int progressValue = 0;

	public ConnexionEdito() {

		listEscalesAF = new ArrayList<Escale>();
	
	}

	
	public int chopeNoticesAF(AnalyseCrew analyse, String login, String password, String urlIpn, String repertoire, boolean isTotal) {
		
		this.login = login;
		this.password = password;
		this.urlIpn = urlIpn;
		this.urlEdito = urlIpn.replaceAll("ipn", "edito");
		this.repertoire = repertoire;
		
		// Liste des découchers du planning
		ArrayList<String> listDecouchers = new ArrayList<String>();
		for (Rotation rotation : analyse.listRotation) {
			for (ServiceVol sv : rotation.listSV) {
				if (sv.isDecoucher) {
					Troncon lastTronconSv = sv.listTroncon.get(sv.listTroncon.size() - 1);
					if (!listDecouchers.contains(lastTronconSv.to)) {
						listDecouchers.add(lastTronconSv.to);
					}
				}
			}
		}
			
		Escale escale;
		try {
			String ficSource = "/res_databases/dbESCALES.txt";
			InputStream is = getClass().getResourceAsStream(ficSource);
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String tmp;
			while ((tmp = in.readLine()) != null) {
				// on ne récupère que le nom (et on gere les lignes blanches de separation des secteurs ou les lignes de comentaires //)
				if ((tmp.equals("") == false) && (tmp.contains("//") == false)) {
					escale = new Escale();
					escale.code = tmp.split(";")[0].trim();
					escale.nom = tmp.split(";")[1].trim();
					if (isTotal) {
						listEscalesAF.add(escale);
					}
					else if (listDecouchers.contains(escale.code)) {
						listEscalesAF.add(escale);
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
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();// MultiThreaded (check version/comteur)
			ChopeCrew.httpClient = HttpClients.custom().setConnectionManager(cm).disableRedirectHandling().build();
			// ChopeCrew.httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
		}

		int z = connect();

		if (z == 0) {
			// Erreur imprevue (connexion internet hs)
			setChanged();
			this.notifyObservers(MSG_EXCEPTION);
			return 0;
		}

		else if (z == -1) {
			// Identifiants
			setChanged();
			this.notifyObservers(MSG_ERREUR);
			return -1;
		}
		
		else {
			setChanged();
			this.notifyObservers(MSG_CONNECTED);
		}

		importNoticesAF();

		setChanged();
		this.notifyObservers(MSG_OK);

		return 1;
	}

	
	private int connect() {

		HttpGet get = new HttpGet();
		HttpPost post = new HttpPost();
		CloseableHttpResponse response;
		URI url;
		String urlRedirection;

		System.out.println("Connexion Edito ...");
		
		try {
			// 1ere requête sur Ipn afin d'obtenir la redirection vers la mire
			// habile, le cas échéant
			url = new URI("https://" + urlIpn);
			get.setURI(url);
			response = ChopeCrew.httpClient.execute(get);
			get.releaseConnection();

			// On est redirigé vers la mire Habile
			if ((response.getFirstHeader("Location") != null)
					&& (response.getFirstHeader("Location").getValue().contains("siteminderagent"))) {
				urlRedirection = response.getFirstHeader("Location").getValue();

				System.out.println("Autorisation ...");
				
				// ChopeCrew.httpClient.getState().clear();

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

				// Test redirection (réussite mot de passe)
				if ((response.getFirstHeader("Location") == null)
						|| (response.getFirstHeader("Location").getValue().contains("siteminderagent"))) {
					System.out.println("Accès refusé !");
					return -1;
				}
				else {
					System.out.println("Accès accordé !");
				}
			}

			// Connexion Edito
			url = new URI("https://" + urlEdito);
			get.setURI(url);
			ChopeCrew.httpClient.execute(get);
			get.releaseConnection();

			// POST d'identification
			ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("mode", ""));

			// url = new URI("https://" + urlEdito + "/authentifier.do");
			url = new URI("https://" + urlEdito + "/editoWeb/docsVol.do?action=init");

			post.setURI(url);
			post.setEntity(new UrlEncodedFormEntity(nvps));
			ChopeCrew.httpClient.execute(post);
			post.releaseConnection();

			System.out.println("Connecté Edito");
			
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

		progressValue = 0;

		HttpGet get = new HttpGet();
		HttpPost post = new HttpPost();
		CloseableHttpResponse response;
		URI url;
		
		int nbNoticesRecues = 0;
		
		for (Escale escaleAF : listEscalesAF) {

			ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("typeVoletList[15].selected", "on"));
//			nvps.add(new BasicNameValuePair("critereCodeCourrier", ""));
//			nvps.add(new BasicNameValuePair("critereCodeCourrier", ""));
//			nvps.add(new BasicNameValuePair("criterePays", ""));
			nvps.add(new BasicNameValuePair("critereEscale", escaleAF.code));
//			nvps.add(new BasicNameValuePair("critereTypeAvion", ""));
//			nvps.add(new BasicNameValuePair("critereNumeroVol", ""));
//			nvps.add(new BasicNameValuePair("critereCDL", ""));
//			nvps.add(new BasicNameValuePair("critereBase", ""));
//			nvps.add(new BasicNameValuePair("critereMatriculeAvion", ""));
//			nvps.add(new BasicNameValuePair("critereClasse", ""));
//			nvps.add(new BasicNameValuePair("dateDebutValidite", ""));
//			nvps.add(new BasicNameValuePair("dateFinValidite", ""));
//			nvps.add(new BasicNameValuePair("dernierRedacteur", ""));
			nvps.add(new BasicNameValuePair("action", "search"));
//			nvps.add(new BasicNameValuePair("wUnSelectBox", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[0].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[1].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[2].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[3].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[4].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[5].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[6].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[7].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[8].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[9].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[10].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[11].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[12].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[13].selected", ""));
//			nvps.add(new BasicNameValuePair("typeVoletList[14].selected", ""));

			try {

				url = new URI("https://" + urlEdito + "/editoWeb" + "/docsVol.do");

				post.setURI(url);
				post.setEntity(new UrlEncodedFormEntity(nvps));
				response = ChopeCrew.httpClient.execute(post);

				String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
				String source = Utils.streamToString(response.getEntity().getContent(), charset);
				post.releaseConnection();

				// Recherche du idVolet
				String idVolet = "";
				String motif = "action=viewPDF&idVolet=";

				int idx = source.indexOf(motif);
				// jump to next code if doc does not exists
				if (idx == -1) {
					continue;
				}
				idVolet = source.substring(idx + motif.length(), source.indexOf("'", idx));

				if (idVolet != null) {

					// Requête PDF connaissant le idVolet
					url = new URI("https://" + urlEdito + "/editoWeb" + "/afficherVolets.do?action=viewPDF&idVolet="
							+ idVolet);
					get.setURI(url);
					response = ChopeCrew.httpClient.execute(get);

					if (response.getStatusLine().getStatusCode() == 200) {

						File f = new File(repertoire);
						f.mkdirs();
						String filename = ChopeCrew.options.libelleNoticesAF;
						filename = filename.replaceAll("%c", escaleAF.code);
						filename = filename.replaceAll("%n", escaleAF.nom);

						// charset =
						// ContentType.getOrDefault(response.getEntity()).getCharset().name();
						String body = Utils.streamToString(response.getEntity().getContent(), "ISO-8859-1");
						Utils.saveToFile(body, f.getAbsolutePath() + File.separator + filename + ".pdf", "ISO-8859-1");
						nbNoticesRecues++;
						progressValue = (nbNoticesRecues * 100) / listEscalesAF.size();
						
						System.out.println("notice AF " + escaleAF.nom + " exportée");
						
						setChanged();
						this.notifyObservers(Integer.valueOf(nbNoticesRecues));
					}

					get.releaseConnection();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				get.releaseConnection();
				post.releaseConnection();
			}
		}
	}

	
	public int getProgressValue() {
		return progressValue;
	}

	

	

}
