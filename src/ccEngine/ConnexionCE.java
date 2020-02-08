package ccEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Observable;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import ccStructures.Escale;
import ccStructures.Rotation;
import ccStructures.ServiceVol;
import ccStructures.Troncon;
import ccUtils.Utils;
import chopeCrew.ChopeCrew;

public class ConnexionCE extends Observable {
	
	public static final String MSG_OK = "Notices CE exportées !";
	public static final String MSG_ERR_SITE_CE = "Erreur du site CE Lignes";
	public static final String MSG_ERR_LOGINPASSWORD = "Identifiant et/ou mot de passe CE Lignes\nnon renseigné(s) dans Options/Notices";
	public static final String MSG_EXCEPTION = "Erreur imprévue";
	public static final String MSG_CONNECTED = "Connecté CELignes !";
	
	private String repertoire = null;
	private ArrayList<Escale> listEscalesCE = null;
	private int progressValue = 0;
		
	
	public ConnexionCE() {
			
		listEscalesCE = new ArrayList<Escale>();
	}
	
	
	public int chopeNoticesCE(AnalyseCrew analyse, String repertoire, boolean isTotal) {
		
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
			String ficSource = "/res_databases/dbNOTICES.txt";
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
						listEscalesCE.add(escale);
					}
					else if (listDecouchers.contains(escale.code)) {
						listEscalesCE.add(escale);
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
			//ChopeCrew.httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
		}
		
		int z = connect();
		
		if (z == 0) {
			// Erreur imprevue (connexion internet hs)
			setChanged();
			this.notifyObservers(MSG_EXCEPTION);
			return 0;
		}
		
		else if (z == -1) {
			// Erreur site CE Lignes (code http != 200)
			setChanged();
			this.notifyObservers(MSG_ERR_SITE_CE);
			return -1;
		}
		
		else if (z == -2) {
			// pas d'identifiant ou de mot de passe
			setChanged();
			this.notifyObservers(MSG_ERR_LOGINPASSWORD);
			return -2;
		}
		
		else {
			setChanged();
			this.notifyObservers(MSG_CONNECTED);
		}
		
		importNoticesCE();
		
		setChanged();
		this.notifyObservers(MSG_OK);
		
		return 1;
	}
	
	
	
	private int connect() {
				
		HttpGet get = new HttpGet();
		HttpPost post = new HttpPost();
		CloseableHttpResponse response;
		
		// test sur le remplissage des champs identifiants et mdp
		if (ChopeCrew.options.ceLogin.equals("") || ChopeCrew.options.cePassword.equals("")) {
			return -2;
		}
		
		System.out.println("Connexion CE ...");

		try {
			// requête sur le site CE pour confirmer sa disponibilité
			get.setURI(new URI("https://www.celignes.com"));
			ChopeCrew.httpClient.execute(get);
			get.releaseConnection();
			
			// authentification
			post.setURI(new URI("https://www.celignes.com/_connect_celignes.asp"));
			
			ArrayList <NameValuePair> nvps = new ArrayList <NameValuePair>();
//			nvps.add(new BasicNameValuePair("r", ""));
			nvps.add(new BasicNameValuePair("matricul", ChopeCrew.options.ceLogin));
			nvps.add(new BasicNameValuePair("mdp", ChopeCrew.options.cePassword));
			
			post.setEntity(new UrlEncodedFormEntity(nvps));
						
			ChopeCrew.httpClient.execute(post);
			post.releaseConnection();
			
			get.setURI(new URI("https://www.celignes.com/index.asp"));
			response = ChopeCrew.httpClient.execute(get);
			
			get.releaseConnection();
			
			if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode() == 302) {
				System.out.println("Connecté CE Lignes");
				return 1;
			}
			else {
				System.out.println("Echec connexion");
				return -1;
			}
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
		
		progressValue = 0;
		
		HttpGet get = new HttpGet();
		CloseableHttpResponse response;
		
		URI url;

		int nbNoticesRecues = 0;
		
		for (Escale escale : listEscalesCE) {
			
			try {
				url = new URI("https://www.celignes.com/escales/telecharge-fiche.asp?f=" + escale.code + "-" + escale.nom);

				get.setURI(url);
				response = ChopeCrew.httpClient.execute(get);
				
				//String charset = ContentType.getOrDefault(response.getEntity()).getCharset().name();
				String body = Utils.streamToString(response.getEntity().getContent(), "ISO-8859-1");
				
				if ((response.getStatusLine().getStatusCode() == 200) && pdfOK(body)) {
					
					File f = new File(repertoire);
					f.mkdirs();
					String filename = ChopeCrew.options.libelleNoticesCE;
					filename = filename.replaceAll("%c", escale.code);
					filename = filename.replaceAll("%n", escale.nom);
					Utils.saveToFile(body, f.getAbsolutePath() + File.separator + filename + ".pdf", "ISO-8859-1");
					nbNoticesRecues++;
					progressValue = (nbNoticesRecues * 100) / listEscalesCE.size();
					
					System.out.println("notice CE " + escale.nom + " exportée");

					setChanged();
					this.notifyObservers(Integer.valueOf(nbNoticesRecues));
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
		return progressValue;
	}
	
	
	private boolean pdfOK(String s) {
		if (s.contains("The page cannot be found")) {
			return false;
		}
		return true;
	}
	
	
	
}
