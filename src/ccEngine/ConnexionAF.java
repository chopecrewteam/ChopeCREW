package ccEngine;


import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import ccStructures.Rotation;
import ccStructures.ServiceVol;
import ccStructures.Troncon;
import ccUtils.Utils;
import chopeCrew.ChopeCrew;

public class ConnexionAF extends Observable {
	
	public static final String MSG_OK_NO_CHANGE = "Pas de mise à jour";
	public static final String MSG_OK_CHANGE = "Temps de vol mis à jour";
	public static final String MSG_ERR_SITE_AF = "Erreur du site AF";
	public static final String MSG_EXCEPTION = "Erreur imprévue";
	public static final String MSG_CONNECTED = "Connecté AF";
	
	boolean hasChanged = false;
	
	
	public ConnexionAF(AnalyseCrew analyse) {
	
		
	}
	
	
	public int chopeBlocAF(AnalyseCrew analyse) {
		
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
			// Erreur site AF (code http >= 400)
			setChanged();
			this.notifyObservers(MSG_ERR_SITE_AF);
			return -1;
		}
		
		else {
			setChanged();
			this.notifyObservers(MSG_CONNECTED);
		}
				
		importBlocAF(analyse);
		
		setChanged();
		if (hasChanged) {
			this.notifyObservers(MSG_OK_CHANGE);
		}
		else {
			this.notifyObservers(MSG_OK_NO_CHANGE);
		}
		
		return 1;
	}
	
	
	
	private int connect() {
				
		HttpGet get = new HttpGet();
		CloseableHttpResponse response;
					
		System.out.println("Connexion AF ...");
		
		try {
			// requête sur le site AF pour confirmer sa disponibilité et l'accès internet
			get.setURI(new URI("http://www.airfrance.fr"));
			response = ChopeCrew.httpClient.execute(get);
			get.releaseConnection();
						
			if (response.getStatusLine().getStatusCode() <= 400) {
				System.out.println("Connecté AF");
				
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
					
			return 0;
		}
	}
	
	
	private void importBlocAF(AnalyseCrew analyse) {
		
		
		// ATTENTION AUX VOLS CARGO ET VOLS HOP ?
				
		HttpGet get = new HttpGet();
		CloseableHttpResponse response;
		
		URI url;
		
		int nbVolRecus = 0;
				
		DateFormat dayFormatFrom = new SimpleDateFormat("dd");
		DateFormat monthYearFormatFrom = new SimpleDateFormat("yyyyMM");
				
		
		for (Rotation rotation : analyse.listRotation) {
			for (ServiceVol sv : rotation.listSV) {
				for (Troncon tro : sv.listTroncon) {
					if (tro.numVol.contains("AF") || tro.numVol.contains("A5")) { // Ne compte pas les MEP sur autres cies
						
						String codeCie = "";
						
						if (tro.numVol.contains("AF")) {
							codeCie = "AF";
						}
						
						if (tro.numVol.contains("A5")) {
							codeCie = "A5";
						}
						
						String numVol = tro.numVol.substring(2).trim();//enlève le AF
												
						TimeZone tzFrom = new SimpleTimeZone(tro.LAGfromMillis, "");
						dayFormatFrom.setTimeZone(tzFrom);
						monthYearFormatFrom.setTimeZone(tzFrom);
						
						String dateFrom = dayFormatFrom.format(tro.debutUTCD);
						String anneemoisFrom = monthYearFormatFrom.format(tro.debutUTCD);
						
						TimeZone tzTo = new SimpleTimeZone(tro.LAGtoMillis, "");
						DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
						hourFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
						
						try {
							
							url = new URI("https://www.airfrance.fr/FR/fr/local/resainfovol/infovols/detailsVol.do"
									+ "?companyCode=" + codeCie
									+ "&flightNumber=" + numVol
									+ "&dayDate=" + dateFrom
									+ "&yearMonthDate=" + anneemoisFrom
									+ "&flightDate=" + anneemoisFrom + dateFrom
									+ "&queryFromEntryPoint=true");
														
							get.setURI(url);
							response = ChopeCrew.httpClient.execute(get);
							String body = Utils.streamToString(response.getEntity().getContent(), "ISO-8859-1");
							
							if (response.getStatusLine().getStatusCode() == 200) {
																
								nbVolRecus++;
								System.out.println(body);
								
								String blocD="", blocA="";
								Pattern regex;
								Matcher result;
																
								String cible = "<section class=\"flight__item\">"
										+ "(.*?)"
										+ "<span class=\"flight__item__detail__stopover__airport\">(.*?)</span>" // group 2 contient depart
										+ "(.*?)"
										+ "<span class=\"flight__item__detail__scheduling__value\">(.*?)</span>" // 4 dep prog
										+ "(.*?)"
										+ "<span class=\"flight__item__detail__scheduling__value\">(.*?)</span>" // 6 dep real
										+ "(.*?)"
										+ "<span class=\"flight__item__detail__stopover__airport\">(.*?)</span>" // group 8 contient arrivee
										+ "(.*?)"
										+ "<span class=\"flight__item__detail__scheduling__value\">(.*?)</span>" // 10 arr prog
										+ "(.*?)"
										+ "<span class=\"flight__item__detail__scheduling__value\">(.*?)</span>" // 12 arr real
										+ "(.*?)"
										+ "</section>";
							
							//	String cible = "<span class=\"flight__item__detail__scheduling__value\">(.*?)</span>";
								regex = Pattern.compile(cible, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
								result = regex.matcher(body);
							//	int compteur = 0;
								while (result.find()) {
							//		compteur += 1;
									if (result.group(2).trim().contains("(" + tro.from + ")") && result.group(8).contains("(" + tro.to + ")")) {
										blocD = result.group(6).trim();
										blocA = result.group(12).trim();
									}
									
//									if (compteur == 2) {
//										blocD = result.group(1).trim();
//									}
//									else if (compteur == 4) {
//										blocA = result.group(1).trim();
//									}
								}
								
								System.out.println(tro.numVol + " " + tro.from + "  " + tro.to + " " + blocD + " " + blocA);
								
								
								// Infos non disponible
								if (blocD.equals("") || blocA.equals("")) {
									continue;
								}
								
								// Bloc départ
								GregorianCalendar calFrom = new GregorianCalendar(tzFrom);
								int hFrom = Integer.parseInt(blocD.substring(0, 2));
								int mFrom = Integer.parseInt(blocD.substring(3, 5));
								//System.out.println(hFrom + " " + mFrom);
								
								calFrom.setTimeInMillis(tro.DEPp);
								calFrom.set(Calendar.HOUR_OF_DAY, hFrom);
								calFrom.set(Calendar.MINUTE, mFrom);
								calFrom.set(Calendar.SECOND, 0);
								
								long sameDayFrom = calFrom.getTimeInMillis();
								calFrom.add(Calendar.DATE, 1);
								long dayAfterFrom = calFrom.getTimeInMillis();
								calFrom.add(Calendar.DATE, -2);
								long dayBeforeFrom = calFrom.getTimeInMillis();
								
								long deltaBeforeFrom = Math.abs(tro.DEPp - dayBeforeFrom);
								long deltaAfterFrom = Math.abs(tro.DEPp - dayAfterFrom);
								long deltaSameFrom = Math.abs(tro.DEPp - sameDayFrom);
								
								if ((deltaBeforeFrom < deltaSameFrom) && (deltaBeforeFrom < deltaAfterFrom)) {
									calFrom.setTimeInMillis(dayBeforeFrom);
								}
								else if ((deltaAfterFrom < deltaSameFrom) && (deltaAfterFrom < deltaBeforeFrom)) {
									calFrom.setTimeInMillis(dayAfterFrom);
								}
								else {
									calFrom.setTimeInMillis(sameDayFrom);
								}
								
								//System.out.println(hourFormat.format(calFrom.getTime()));
								
								// Bloc arrivée
								
								GregorianCalendar calTo = new GregorianCalendar(tzTo);
								int hTo = Integer.parseInt(blocA.substring(0, 2));
								int mTo = Integer.parseInt(blocA.substring(3, 5));
								//System.out.println(hTo + " " + mTo);
								
								calTo.setTimeInMillis(tro.ARRp);
								calTo.set(Calendar.HOUR_OF_DAY, hTo);
								calTo.set(Calendar.MINUTE, mTo);
								calTo.set(Calendar.SECOND, 0);
								
								long sameDayTo = calTo.getTimeInMillis();
								calTo.add(Calendar.DATE, 1);
								long dayAfterTo = calTo.getTimeInMillis();
								calTo.add(Calendar.DATE, -2);
								long dayBeforeTo = calTo.getTimeInMillis();
								
								long deltaBeforeTo = Math.abs(tro.ARRp - dayBeforeTo);
								long deltaAfterTo = Math.abs(tro.ARRp - dayAfterTo);
								long deltaSameTo = Math.abs(tro.ARRp - sameDayTo);
								
								if ((deltaBeforeTo < deltaSameTo) && (deltaBeforeTo < deltaAfterTo)) {
									calTo.setTimeInMillis(dayBeforeTo);
								}
								else if ((deltaAfterTo < deltaSameTo) && (deltaAfterTo < deltaBeforeTo)) {
									calTo.setTimeInMillis(dayAfterTo);
								}
								else {
									calTo.setTimeInMillis(sameDayTo);
								}
								
								
								//System.out.println(hourFormat.format(calTo.getTime()));
								
								
								
								// DEP et TV
								double TVr = Utils.arrondi(((double)(calTo.getTimeInMillis() - calFrom.getTimeInMillis())) / 1000 / 60 / 60, 2);
								
								//System.out.println(tro.TVr);
								//System.out.println(Utils.timetoString(tro.TVr));
								
								// Remaniement du bloc
//								if ((calFrom.get(Calendar.MINUTE) % 3) == 1) {
//									calFrom.add(Calendar.SECOND, 12);
//								}
//								else if ((calFrom.get(Calendar.MINUTE) % 3) == 2) {
//									calFrom.add(Calendar.SECOND, -12);
//								}
								
								
								// Si le troncon a déjà été renseigné, on ne modifie pas (i.e. on ne surpasse pas une entrée manuelle)
								if (tro.isTVreal == false) {
									if (tro.isMep == false) {
										if (tro.DEPr != calFrom.getTimeInMillis() || tro.TVr != TVr) {
											tro.DEPr = calFrom.getTimeInMillis();
											tro.TVr = TVr;
											tro.MEPr = 0;
											tro.isTVreal = true;
											hasChanged = true;
										}
									}
									else {
										if (tro.DEPr != calFrom.getTimeInMillis() || tro.MEPr != TVr) {
											tro.DEPr = calFrom.getTimeInMillis();
											tro.TVr = 0;
											tro.MEPr = TVr;
											tro.isTVreal = true;
											hasChanged = true;
										}
									}
								}
																

								setChanged();
								this.notifyObservers(Integer.valueOf(nbVolRecus));
							}
							
							get.releaseConnection();
						}
						catch (Exception e) {
							e.printStackTrace();
							continue;
						}
					}
				}
			}
		}
	}
	
}
