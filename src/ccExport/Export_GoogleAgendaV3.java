// 
// Decompiled by Procyon v0.5.36
// 

package ccExport;

import ccStructures.Participant;
import ccStructures.Presta;
import com.google.api.services.calendar.model.EventDateTime;
import ccStructures.Deg_Reco;
import ccStructures.Dest_Reco;
import ccStructures.Peq;
import java.util.SimpleTimeZone;
import java.text.SimpleDateFormat;
import java.util.List;
import com.google.api.services.calendar.model.Events;
import java.util.Map;
import com.google.api.client.googleapis.batch.BatchRequest;
import ccStructures.ActiviteSol;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.services.calendar.model.Event;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.calendar.Calendar;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.util.store.DataStoreFactory;
import java.util.Collection;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import java.util.Collections;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import java.io.File;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.util.Iterator;
import java.util.ArrayList;
import ccStructures.GCalOptions;
import java.io.IOException;
import com.google.api.services.calendar.model.CalendarListEntry;
import chopeCrew.ChopeCrew;
import com.google.api.services.calendar.model.CalendarList;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import ccEngine.AnalyseCrew;
import java.util.HashMap;
import com.google.api.client.util.DateTime;
import java.util.Observable;

public class Export_GoogleAgendaV3 extends Observable
{
    public static final String MSG_OK = "Planning Google envoy\u00e9 !";
    public static final String MSG_ERREUR = "Erreur d'identifiant ou de mot de passe !";
    public static final String MSG_EXCEPTION = "Erreur impr\u00e9vue !";
    public static final String MSG_CALENDRIER = "Erreur de calendrier !";
    public static final String MSG_CONNECTED = "Connect\u00e9 Google";
    private String login;
    private String nl;
    private DateTime debut;
    private DateTime fin;
    private int progressValue;
    private int nbCalendriers;
    private HashMap<String, String> mapCalendriers;
    
    public Export_GoogleAgendaV3() {
        this.login = null;
        this.nl = null;
        this.debut = null;
        this.fin = null;
        this.progressValue = 0;
        this.nbCalendriers = 0;
        this.mapCalendriers = null;
        this.mapCalendriers = new HashMap<String, String>();
        this.nl = "\r\n";
    }
    
    public int sendPlanning(final AnalyseCrew analyse, final String login, final String calEntetes, final String calTroncons, final String calSol, final String calConges, final String calRepos, final String calAbsences, final String calDispersions) {
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        cal.set(1, analyse.anneeInt);
        cal.set(2, analyse.moisInt - 1);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(5, -10);
        this.debut = new DateTime(cal.getTime());
        cal.set(1, analyse.anneeInt);
        cal.set(2, analyse.moisInt);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(5, 10);
        this.fin = new DateTime(cal.getTime());
        this.login = login;
        if (!login.contains("@")) {
            this.login = String.valueOf(login) + "@gmail.com";
        }
        System.out.println("Connexion Google ...");
        final ArrayList<GCalOptions> listGCalOptions = this.buildListeOptions(calEntetes, calTroncons, calSol, calConges, calRepos, calAbsences, calDispersions);
        this.nbCalendriers = listGCalOptions.size();
        if (this.nbCalendriers == 0) {
            this.nbCalendriers = 1;
        }
        final int z = this.connect();
        if (z == 0) {
            this.setChanged();
            this.notifyObservers("Erreur impr\u00e9vue !");
            return 0;
        }
        this.setChanged();
        this.notifyObservers("Connect\u00e9 Google");
        this.progressValue = 20;
        this.setChanged();
        this.notifyObservers(this.progressValue);
        try {
            final CalendarList listCalendriers = ChopeCrew.client.calendarList().list().execute();
            for (final CalendarListEntry calendar : listCalendriers.getItems()) {
                this.mapCalendriers.put(calendar.getSummary(), calendar.getId());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (listGCalOptions.size() == 0) {
            this.exportGoogleAgendaPerso(analyse, null);
        }
        else {
            for (final GCalOptions gCalOptions : listGCalOptions) {
                this.exportGoogleAgendaPerso(analyse, gCalOptions);
            }
        }
        System.out.println("Planning envoy\u00e9 - Google");
        this.setChanged();
        this.notifyObservers("Planning Google envoy\u00e9 !");
        return 1;
    }
    
    private int connect() {
        try {
            if (ChopeCrew.json_factory == null) {
                ChopeCrew.json_factory = JacksonFactory.getDefaultInstance();
            }
            final File data_store_dir = new File(System.getProperty("user.home"), ".chopeCREW/" + ChopeCrew.options.loginGoogle);
            ChopeCrew.httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            ChopeCrew.dataStoreFactory = new FileDataStoreFactory(data_store_dir);
            final GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(ChopeCrew.httpTransport, ChopeCrew.json_factory, "1002555116964-fmmh79tuh5sukg5esg2tffc9p6u9u5le.apps.googleusercontent.com", "s-t931CHmC-1AvrWgO3bh9uP", Collections.singleton("https://www.googleapis.com/auth/calendar")).setDataStoreFactory(ChopeCrew.dataStoreFactory).build();
            final Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
            ChopeCrew.client = new Calendar.Builder(ChopeCrew.httpTransport, ChopeCrew.json_factory, credential).setApplicationName("ChopeCREW").build();
            System.out.println("Connect\u00e9 \u00e0 l'agenda Google");
            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    private void exportGoogleAgendaPerso(final AnalyseCrew analyse, final GCalOptions gCalOptions) {
        int nbEvenements = 0;
        String calendarID = this.login;
        if (gCalOptions != null) {
            calendarID = this.mapCalendriers.get(gCalOptions.getName());
        }
        try {
            this.deleteEvents(analyse, calendarID);
            this.progressValue += 80 / (2 * this.nbCalendriers);
            this.setChanged();
            this.notifyObservers(this.progressValue);
            final JsonBatchCallback<Event> callback = new JsonBatchCallback<Event>() {
                @Override
                public void onSuccess(final Event event, final HttpHeaders responseHeaders) {
                    System.out.println("\u00e9v\u00e8nements ajout\u00e9s");
                }
                
                @Override
                public void onFailure(final GoogleJsonError e, final HttpHeaders responseHeaders) {
                    System.out.println("Error Message: " + e.getMessage());
                }
            };
            final BatchRequest batch = ChopeCrew.client.batch();
            for (final Rotation rotation : analyse.listRotation) {
                int moisRotation = analyse.moisInt;
                if (rotation.numRot == 1) {
                    final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
                    cal.setTime(rotation.debutUTCD);
                    final int mois_depart = cal.get(2) + 1;
                    if (mois_depart != ChopeCrew.analyse.moisInt) {
                        moisRotation = analyse.moisInt - 1;
                        if (moisRotation == 0) {
                            moisRotation = 12;
                        }
                    }
                }
                final Event.ExtendedProperties extProperties = new Event.ExtendedProperties();
                final Map<String, String> properties = new HashMap<String, String>();
                properties.put("ChopeCrew", String.valueOf(moisRotation));
                extProperties.setPrivate(properties);
                if (rotation.isExportable() && (gCalOptions == null || (gCalOptions != null && gCalOptions.isEntetes()))) {
                    final ArrayList<Event> rotationEvents = this.createEvents(rotation);
                    for (final Event event : rotationEvents) {
                        event.setExtendedProperties(extProperties);
                        ChopeCrew.client.events().insert(calendarID, event).queue(batch, callback);
                    }
                    ++nbEvenements;
                    System.out.println("google n° " + nbEvenements + " cr\u00e9\u00e9 (rotation");
                }
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        if (troncon.isExportable() && (gCalOptions == null || (gCalOptions != null && gCalOptions.isTroncons()))) {
                            final ArrayList<Event> tronconEvents = this.createEvents(troncon, sv, rotation);
                            for (final Event event2 : tronconEvents) {
                                event2.setExtendedProperties(extProperties);
                                ChopeCrew.client.events().insert(calendarID, event2).queue(batch, callback);
                            }
                            ++nbEvenements;
                            System.out.println("google n° " + nbEvenements + " cr\u00e9\u00e9 (tron\u00e7on)");
                        }
                    }
                }
            }
            final Event.ExtendedProperties extProperties2 = new Event.ExtendedProperties();
            final Map<String, String> properties2 = new HashMap<String, String>();
            properties2.put("ChopeCrew", String.valueOf(analyse.moisInt));
            extProperties2.setPrivate(properties2);
            final ArrayList<Event> listEventSol = new ArrayList<Event>();
            for (int i = 0; i < analyse.listSol.size(); ++i) {
                final ActiviteSol activiteSol = analyse.listSol.get(i);
                if (this.eventForCalendarOK(gCalOptions, activiteSol)) {
                    ActiviteSol actBefore = new ActiviteSol();
                    if (i > 0) {
                        actBefore = analyse.listSol.get(i - 1);
                    }
                    if (activiteSol.isExportable() && (!activiteSol.debut.equals(actBefore.debut) || !activiteSol.fin.equals(actBefore.fin) || !activiteSol.code.equals(actBefore.code))) {
                        final ArrayList<Event> activiteSolEvents = this.createEvents(activiteSol);
                        for (final Event event3 : activiteSolEvents) {
                            event3.setExtendedProperties(extProperties2);
                            listEventSol.add(event3);
                        }
                        ++nbEvenements;
                        System.out.println("google n° " + nbEvenements + " cr\u00e9\u00e9 (sol)");
                    }
                }
            }
            if (ChopeCrew.options.idxPositionSol == 0) {
                for (int z = 0; z < listEventSol.size() - 1; ++z) {
                    final Event event4 = listEventSol.get(z);
                    final Event nextEvent = listEventSol.get(z + 1);
                    if (event4.getEnd().getDate() != null && nextEvent.getStart().getDate() != null) {
                        System.out.println(String.valueOf(event4.getSummary()) + "  " + event4.getStart().getDate().toString() + "  " + event4.getEnd().getDate().toString());
                        if (event4.getSummary().equals(nextEvent.getSummary()) && event4.getEnd().getDate().toString().equals(nextEvent.getStart().getDate().toString())) {
                            event4.setEnd(nextEvent.getEnd());
                            listEventSol.remove(z + 1);
                            --z;
                        }
                    }
                }
            }
            for (final Event event5 : listEventSol) {
                ChopeCrew.client.events().insert(calendarID, event5).queue(batch, callback);
            }
            if (nbEvenements > 0) {
                batch.execute();
            }
            this.progressValue += 80 / (2 * this.nbCalendriers);
            this.setChanged();
            this.notifyObservers(this.progressValue);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean eventForCalendarOK(final GCalOptions gco, final ActiviteSol act) {
        return gco == null || (gco.isConges() && (act.code.equals("MCA") || act.code.equals("MCE"))) || (gco.isRepos() && (act.code.equals("PRB") || act.code.equals("PAC") || act.code.equals("RPC") || act.code.equals("MAD"))) || (gco.isAbsences() && (act.code.equals("MAS") || act.code.equals("MDV"))) || (gco.isDispersions() && act.code.equals("DSP")) || (gco.isSol() && (act.code.equals("MCP") || act.code.equals("MIM") || act.code.equals("MVM") || act.code.equals("RBR") || act.code.equals("SST") || act.code.equals("SYN") || act.code.equals("MCI")));
    }
    
    private void deleteEvents(final AnalyseCrew analyse, final String calendarId) {
        final String property = "ChopeCrew=" + String.valueOf(analyse.moisInt);
        final ArrayList<String> extendedProperties = new ArrayList<String>();
        extendedProperties.add(property);
        try {
            final Events listEventsChopeCrew = ChopeCrew.client.events().list(calendarId).setMaxResults(250).setOrderBy("startTime").setSingleEvents(true).setPrivateExtendedProperty(extendedProperties).setTimeMin(this.debut).setTimeMax(this.fin).execute();
            if (listEventsChopeCrew.getItems().size() != 0) {
                final JsonBatchCallback<Void> callback = new JsonBatchCallback<Void>() {
                    @Override
                    public void onSuccess(final Void v, final HttpHeaders responseHeaders) {
                        System.out.println("\u00e9l\u00e9ment effac\u00e9");
                    }
                    
                    @Override
                    public void onFailure(final GoogleJsonError e, final HttpHeaders responseHeaders) {
                        System.out.println("Error Message: " + e.getMessage());
                    }
                };
                final BatchRequest batch = ChopeCrew.client.batch();
                for (final Event event : listEventsChopeCrew.getItems()) {
                    ChopeCrew.client.events().delete(calendarId, event.getId()).queue(batch, callback);
                }
                batch.execute();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private ArrayList<Event> createEvents(final Rotation rotation) {
        final ArrayList<Event> events = new ArrayList<Event>();
        final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM");
        final GregorianCalendar cal = new GregorianCalendar();
        final ArrayList<String> joursEntetesRotdeb = new ArrayList<String>();
        final ArrayList<String> joursEntetesRotfin = new ArrayList<String>();
        if (ChopeCrew.options.idxTimeRef == 0) {
            sdf1.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            sdf2.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        }
        else if (ChopeCrew.options.idxTimeRef == 1) {
            final ServiceVol firstSV = rotation.listSV.get(0);
            final Troncon firstTroncon = firstSV.listTroncon.get(0);
            final int lagOrigine = firstTroncon.LAGfromMillis;
            final SimpleTimeZone tzOrigine = new SimpleTimeZone(lagOrigine, "");
            sdf1.setTimeZone(tzOrigine);
            sdf2.setTimeZone(tzOrigine);
            cal.setTimeZone(tzOrigine);
        }
        cal.setTime(rotation.debutUTCD);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        joursEntetesRotdeb.add(sdf1.format(cal.getTime()));
        cal.add(5, 1);
        joursEntetesRotfin.add(sdf1.format(cal.getTime()));
        if (ChopeCrew.options.idxPositionRot == 0) {
            while (cal.getTimeInMillis() <= rotation.finUTCD.getTime()) {
                joursEntetesRotdeb.add(sdf1.format(cal.getTime()));
                cal.add(5, 1);
                joursEntetesRotfin.add(sdf1.format(cal.getTime()));
            }
        }
        else if (ChopeCrew.options.idxPositionRot == 4) {
            while (cal.getTimeInMillis() <= rotation.finUTCD.getTime()) {
                cal.add(5, 1);
                joursEntetesRotfin.set(0, sdf1.format(cal.getTime()));
            }
        }
        for (int z = 0; z < joursEntetesRotdeb.size(); ++z) {
            final Event rotEvent = new Event();
            rotEvent.setReminders(null);
            String affichage = " " + rotation.label + "   " + rotation.nON + "ON";
            for (final String decoucher : rotation.listDecouchers) {
                affichage = String.valueOf(affichage) + " " + decoucher;
            }
            if (rotation.sab != null && !rotation.sab.equals("")) {
                affichage = String.valueOf(affichage) + "   " + rotation.sab;
            }
            rotEvent.setSummary(affichage);
            final StringBuilder sbContent = new StringBuilder();
            if (ChopeCrew.options.geneRot) {
                sbContent.append("D\u00e9part UTC : ").append(rotation.departUTC).append(this.nl);
                sbContent.append("Arriv\u00e9e UTC : ").append(rotation.arriveeUTC).append(this.nl);
                sbContent.append(this.nl);
                sbContent.append("Temps de vol total  : ").append(rotation.tempsVolTotal).append(this.nl);
                sbContent.append("Temps de vol en MEP : ").append(rotation.tempsVolMep).append(this.nl);
                sbContent.append("Temps d'absence : ").append(rotation.tempsAbsence).append(this.nl);
                sbContent.append(this.nl);
                sbContent.append("Dur\u00e9e PAC : ").append(rotation.dureePac).append(this.nl);
                sbContent.append("Dur\u00e9e RPC : ").append(rotation.dureeRpc).append(this.nl);
                sbContent.append("R\u00e9eng. UTC : ").append(rotation.reengagementUTC).append(this.nl);
                boolean flag = false;
                if (rotation.particularite != null && !rotation.particularite.equals("")) {
                    flag = true;
                    sbContent.append(this.nl);
                    sbContent.append("Particularit\u00e9 : ").append(rotation.particularite).append(this.nl);
                }
                if (rotation.sab != null && !rotation.sab.equals("")) {
                    if (!flag) {
                        sbContent.append(this.nl);
                    }
                    sbContent.append("Situation \u00e0 bord : ").append(rotation.sab).append(this.nl);
                }
                if (rotation.nbCDB != null && rotation.nbOPL != null) {
                    sbContent.append(this.nl);
                    sbContent.append("CDB : ").append(rotation.nbCDB).append(", OPL : ").append(rotation.nbOPL).append(this.nl);
                }
            }
            if (ChopeCrew.options.peqRot && rotation.listPeqRot.size() > 0) {
                sbContent.append(this.nl);
                sbContent.append("Equipage de la rotation :").append(this.nl);
                for (final Peq peq : rotation.listPeqRot) {
                    sbContent.append(peq.fab).append(" : ").append(peq.nom).append(" ").append(peq.prenom).append(this.nl);
                }
            }
            if (ChopeCrew.options.recoRot) {
                if (rotation.listDestReco.size() > 0) {
                    sbContent.append(this.nl);
                    sbContent.append("Liste reco. destination :").append(this.nl);
                    for (final Dest_Reco reco : rotation.listDestReco) {
                        sbContent.append(reco.dest).append(" : cat.").append(reco.categorie).append(this.nl);
                    }
                }
                if (rotation.listDegReco.size() > 0) {
                    sbContent.append(this.nl);
                    sbContent.append("Liste reco. d\u00e9gagements :").append(this.nl);
                    for (final Deg_Reco reco2 : rotation.listDegReco) {
                        sbContent.append(reco2.deg).append(" : cat.").append(reco2.categorie).append(" (").append(reco2.dest).append(")").append(this.nl);
                    }
                }
            }
            if (ChopeCrew.options.payeRot) {
                sbContent.append(this.nl);
                sbContent.append("Heures d\u00e9compt\u00e9es / remun\u00e9r\u00e9es :").append(this.nl);
                sbContent.append("HCA : ").append(rotation.HCAp).append("  /  ").append(rotation.HCAp).append(this.nl);
                sbContent.append("\u03a3H1 : ").append(rotation.SH1p).append("  /  ").append(rotation.SH1Rp).append(this.nl);
                sbContent.append("H2 :  ").append(rotation.H2p).append("  /  ").append(rotation.H2Rp).append(this.nl);
            }
            rotEvent.setDescription(sbContent.toString());
            if (ChopeCrew.options.idxPositionRot == 0 || ChopeCrew.options.idxPositionRot == 1 || ChopeCrew.options.idxPositionRot == 4) {
                rotEvent.setStart(new EventDateTime().setDate(new DateTime(joursEntetesRotdeb.get(z))));
                rotEvent.setEnd(new EventDateTime().setDate(new DateTime(joursEntetesRotfin.get(z))));
            }
            else if (ChopeCrew.options.idxPositionRot == 2) {
                final DateTime start = new DateTime(rotation.debutUTCD);
                rotEvent.setStart(new EventDateTime().setDateTime(start));
                final DateTime end = new DateTime(rotation.debutUTCD);
                rotEvent.setEnd(new EventDateTime().setDateTime(end));
            }
            else if (ChopeCrew.options.idxPositionRot == 3) {
                final DateTime start = new DateTime(rotation.debutUTCD);
                rotEvent.setStart(new EventDateTime().setDateTime(start));
                final DateTime end = new DateTime(rotation.finUTCD);
                rotEvent.setEnd(new EventDateTime().setDateTime(end));
            }
            if (ChopeCrew.options.lieuSAB && rotation.sab != null && rotation.sab != "") {
                rotEvent.setLocation(rotation.sab);
            }
            if (ChopeCrew.options.colorRotations.equals("0")) {
                rotEvent.setColorId(null);
            }
            else {
                rotEvent.setColorId(ChopeCrew.options.colorRotations);
            }
            events.add(rotEvent);
        }
        return events;
    }
    
    private ArrayList<Event> createEvents(final Troncon troncon, final ServiceVol sv, final Rotation rotation) {
        final ArrayList<Event> events = new ArrayList<Event>();
        final Event tronconEvent = new Event();
        tronconEvent.setReminders(null);
        String titre = ChopeCrew.options.libelleTroncon;
        if (!titre.contains("%t") && troncon.isMep) {
            titre += " (MEP)";
        }
        titre = titre.replaceAll("%n", troncon.numVol).replaceAll("%d", troncon.from).replaceAll("%a", troncon.to).replaceAll("%D", troncon.fromClair).replaceAll("%A", troncon.toClair);
        if (!troncon.isMep) {
            titre = titre.replaceAll("%t", troncon.typeAvion);
        }
        else if (troncon.isMep && !rotation.isOD) {
            titre = titre.replaceAll("%t", "MEP");
        }
        else if (troncon.isMep && rotation.isOD) {
            titre = titre.replaceAll("%t", "OD");
        }
        tronconEvent.setSummary(titre);
        final StringBuilder sbContent = new StringBuilder();
        if (ChopeCrew.options.geneTroncon) {
            sbContent.append("Type avion : ").append(troncon.typeAvion).append(this.nl);
            sbContent.append("Version : ").append(troncon.versionExploitation).append(this.nl);
            sbContent.append(this.nl);
            sbContent.append("D\u00e9part UTC : ").append(troncon.departUTC).append(this.nl);
            sbContent.append("Arriv\u00e9e UTC : ").append(troncon.arriveeUTC).append(this.nl);
            sbContent.append(this.nl);
            sbContent.append("TVSV : ").append(sv.TVSV).append(this.nl);
            sbContent.append("TSV : ").append(sv.TSV).append(this.nl);
            sbContent.append(this.nl);
            if (troncon.LAGfromMillis >= 0) {
                sbContent.append(troncon.from).append(" : TU ").append("+").append(troncon.lagFrom).append(this.nl);
            }
            else {
                sbContent.append(troncon.from).append(" : TU ").append(troncon.lagFrom).append(this.nl);
            }
            if (troncon.LAGtoMillis >= 0) {
                sbContent.append(troncon.to).append(" : TU ").append("+").append(troncon.lagTo).append(this.nl);
            }
            else {
                sbContent.append(troncon.to).append(" : TU ").append(troncon.lagTo).append(this.nl);
            }
            if (rotation.sab != null && !rotation.sab.equals("")) {
                sbContent.append(this.nl);
                sbContent.append("Situation \u00e0 bord : ").append(rotation.sab).append(this.nl);
            }
        }
        if (ChopeCrew.options.recoTroncon && troncon.listRecoDest.size() > 0) {
            sbContent.append(this.nl);
            sbContent.append("Reco. destination :").append(this.nl);
            for (final Dest_Reco reco : troncon.listRecoDest) {
                sbContent.append(reco.dest).append(" : cat.").append(reco.categorie).append(this.nl);
            }
        }
        if (ChopeCrew.options.recoTroncon && troncon.listRecoDeg.size() > 0) {
            sbContent.append(this.nl);
            sbContent.append("Reco. d\u00e9gagements :").append(this.nl);
            for (final Deg_Reco reco2 : troncon.listRecoDeg) {
                sbContent.append(reco2.deg).append(" : cat.").append(reco2.categorie).append(this.nl);
            }
        }
        if (ChopeCrew.options.peqTroncon && troncon.listPeqTroncon.size() > 0) {
            sbContent.append(this.nl);
            sbContent.append("Equipage :").append(this.nl);
            for (final Peq peq : troncon.listPeqTroncon) {
                sbContent.append(peq.fab).append(" : ").append(peq.nom).append(" ").append(peq.prenom).append(this.nl);
            }
        }
        if (ChopeCrew.options.indemTroncon && troncon.indemTo.escale != null) {
            sbContent.append(this.nl);
            sbContent.append("Indemnit\u00e9s ").append(troncon.to).append(" :").append(this.nl);
            sbContent.append("IR : ").append(troncon.indemTo.irLoc).append(" ").append(troncon.indemTo.monnaieLoc).append(" (").append(troncon.indemTo.irEur).append(" \u20ac)").append(this.nl);
            sbContent.append("MF : ").append(troncon.indemTo.mfLoc).append(" ").append(troncon.indemTo.monnaieLoc).append(" (").append(troncon.indemTo.mfEur).append(" \u20ac)").append(this.nl);
            sbContent.append("Change AF : ").append(troncon.indemTo.change).append(this.nl);
            sbContent.append("Date effet : ").append(troncon.indemTo.dateEffet).append(this.nl);
        }
        if (ChopeCrew.options.prestaTroncon && troncon.listPresta.size() > 0) {
            sbContent.append(this.nl);
            sbContent.append("Prestations ").append(troncon.from).append(" :").append(this.nl);
            for (final Presta p : troncon.listPresta) {
                sbContent.append(p.type).append(this.nl);
            }
        }
        if (ChopeCrew.options.hotelTroncon && troncon.isDecoucher) {
            sbContent.append(this.nl);
            sbContent.append("Repos : ").append(sv.repos).append(this.nl);
            if (troncon.hotel != null) {
                sbContent.append("H\u00f4tel : ").append(troncon.hotel).append(this.nl);
            }
        }
        if (ChopeCrew.options.payeTroncon) {
            sbContent.append(this.nl);
            sbContent.append("Heures d\u00e9compt\u00e9es / r\u00e9mun\u00e9r\u00e9es :").append(this.nl);
            sbContent.append("HCV : ").append(sv.HCVp).append("  /  ").append(sv.HCVRp).append(this.nl);
            sbContent.append("HCT : ").append(sv.HCTp).append("  /  ").append(sv.HCTp).append(this.nl);
            sbContent.append("CMT : ").append(sv.CMTp).append("  /  ").append(sv.CMTp).append(this.nl);
            sbContent.append("H1 :  ").append(sv.H1p).append("  /  ").append(sv.H1Rp).append(this.nl);
            sbContent.append(this.nl);
            sbContent.append("TVref : ").append(troncon.TVref);
            if (!troncon.isTVref) {
                sbContent.append(" (non valid\u00e9: \u00e9gal \u00e0 TVp)").append(this.nl);
            }
            else {
                sbContent.append(this.nl);
            }
            sbContent.append("TVp : ").append(troncon.TVp).append(this.nl);
            sbContent.append("HV100% : ").append(troncon.HV100p).append("  /  ").append(troncon.HV100Rp).append(this.nl);
            sbContent.append("MEP : ").append(troncon.MEPp).append("  /  ").append(troncon.MEPp).append(this.nl);
            sbContent.append("TVN : ").append(troncon.TVNp).append("  /  ").append(troncon.TVNp).append(this.nl);
        }
        tronconEvent.setDescription(sbContent.toString());
        if (ChopeCrew.options.idxTimeRef == 0) {
            final DateTime start = new DateTime(troncon.debutUTCD);
            tronconEvent.setStart(new EventDateTime().setDateTime(start));
            final DateTime end = new DateTime(troncon.finUTCD);
            tronconEvent.setEnd(new EventDateTime().setDateTime(end));
        }
        else if (ChopeCrew.options.idxTimeRef == 1) {
            final GregorianCalendar calFrom = new GregorianCalendar();
            final GregorianCalendar calTo = new GregorianCalendar();
            calFrom.setTime(troncon.debutUTCD);
            final int dOffsetFrom = troncon.LAGfromMillis - TimeZone.getTimeZone("Europe/Paris").getOffset(troncon.debutUTCD.getTime());
            calFrom.add(14, dOffsetFrom);
            calTo.setTime(troncon.finUTCD);
            final int dOffsetTo = troncon.LAGtoMillis - TimeZone.getTimeZone("Europe/Paris").getOffset(troncon.finUTCD.getTime());
            calTo.add(14, dOffsetTo);
            final DateTime start2 = new DateTime(calFrom.getTime());
            tronconEvent.setStart(new EventDateTime().setDateTime(start2));
            final DateTime end2 = new DateTime(calTo.getTime());
            tronconEvent.setEnd(new EventDateTime().setDateTime(end2));
        }
        final StringBuilder sb = new StringBuilder();
        boolean hasChanged = false;
        if (ChopeCrew.options.lieuSAB && rotation.sab != null && rotation.sab != "") {
            sb.append(rotation.sab);
            hasChanged = true;
        }
        if (ChopeCrew.options.lieuMEP && troncon.isMep) {
            if (hasChanged) {
                sb.append(" / ");
            }
            sb.append("MISE EN PLACE");
            hasChanged = true;
        }
        if (ChopeCrew.options.lieuReco && (!troncon.listRecoDeg.isEmpty() || !troncon.listRecoDest.isEmpty())) {
            if (hasChanged) {
                sb.append(" / ");
            }
            sb.append("RECO. TERRAIN");
            hasChanged = true;
        }
        if (hasChanged) {
            tronconEvent.setLocation(sb.toString());
        }
        if (ChopeCrew.options.colorTroncons.equals("0")) {
            tronconEvent.setColorId(null);
        }
        else {
            tronconEvent.setColorId(ChopeCrew.options.colorTroncons);
        }
        events.add(tronconEvent);
        return events;
    }
    
    private ArrayList<Event> createEvents(final ActiviteSol act) {
        final ArrayList<Event> events = new ArrayList<Event>();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calStart = new GregorianCalendar();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        calStart.setTime(act.debutUTCD);
        final GregorianCalendar calEnd = new GregorianCalendar();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        calEnd.setTime(act.finUTCD);
        final String dstart = sdf.format(calStart.getTime());
        calEnd.add(5, 1);
        final String dend = sdf.format(calEnd.getTime());
        final Event solEvent = new Event();
        solEvent.setReminders(null);
        if (ChopeCrew.options.colorActSol.equals("0")) {
            solEvent.setColorId(null);
        }
        else {
            solEvent.setColorId(ChopeCrew.options.colorActSol);
        }
        final StringBuilder sbTitle = new StringBuilder();
        if (act.code.equals("PAC") || act.code.equals("RPC") || act.code.equals("PRB") || act.code.equals("MAD")) {
            sbTitle.append("Repos : ").append(act.label);
            if (ChopeCrew.options.colorRepos.equals("0")) {
                solEvent.setColorId(null);
            }
            else {
                solEvent.setColorId(ChopeCrew.options.colorRepos);
            }
        }
        else if (act.code.equals("MCA") || act.code.equals("MCE")) {
            sbTitle.append("Cong\u00e9s : ").append(act.label);
            if (ChopeCrew.options.colorConges.equals("0")) {
                solEvent.setColorId(null);
            }
            else {
                solEvent.setColorId(ChopeCrew.options.colorConges);
            }
        }
        else if (act.code.equals("MAS") || act.code.equals("MDV")) {
            sbTitle.append("Absence : ").append(act.label);
            if (ChopeCrew.options.colorAbsences.equals("0")) {
                solEvent.setColorId(null);
            }
            else {
                solEvent.setColorId(ChopeCrew.options.colorAbsences);
            }
        }
        else if (act.code.equals("DSP") && !act.isBlocActivite) {
            sbTitle.append("Dispersion");
            if (ChopeCrew.options.colorConges.equals("0")) {
                solEvent.setColorId(null);
            }
            else {
                solEvent.setColorId(ChopeCrew.options.colorDispersions);
            }
        }
        else if (act.code.equals("SST")) {
            sbTitle.append("ECP : ").append(act.label);
        }
        else if (act.code.equals("MCI")) {
            sbTitle.append("INS : ").append(act.label);
        }
        else if (act.code.equals("DSP") && act.isBlocActivite) {
            if (act.bloc.code.equals("MBR")) {
                sbTitle.append("Dispersion (Bloc r\u00e9serve)");
            }
            else if (act.bloc.code.equals("SST")) {
                sbTitle.append("Dispersion (Bloc stage)");
            }
            else {
                sbTitle.append("Dispersion (Bloc activit\u00e9s)");
            }
        }
        else if (!act.label.equals(null) && !act.label.equals("")) {
            sbTitle.append(act.label);
        }
        else {
            sbTitle.append(act.categorie);
        }
        solEvent.setSummary(sbTitle.toString());
        final StringBuilder sbContent = new StringBuilder();
        if (ChopeCrew.options.detailsSol) {
            sbContent.append(act.label).append(this.nl);
            sbContent.append("Cat\u00e9gorie (FAST): ").append(act.categorie).append(" (").append(act.code).append(")").append(this.nl);
            sbContent.append(this.nl);
            sbContent.append("D\u00e9but : ").append(act.debut).append(" LT").append(this.nl);
            sbContent.append("Fin : ").append(act.fin).append(" LT").append(this.nl);
            if (act.lieu != null || act.salle != null || act.commentaire != null) {
                sbContent.append(this.nl);
                sbContent.append("Lieu : ").append(act.lieu).append(this.nl);
                sbContent.append("Salle : ").append(act.salle).append(this.nl);
                sbContent.append("Commentaires : ").append(act.commentaire).append(this.nl);
            }
            if (act.listParticipant.size() > 0) {
                sbContent.append(this.nl);
                sbContent.append("Participants : ").append(this.nl);
                for (final Participant part : act.listParticipant) {
                    sbContent.append(part.nom).append(" ").append(part.prenom).append(" (").append(part.statut).append(")").append(this.nl);
                }
            }
        }
        if (ChopeCrew.options.payeSol) {
            sbContent.append(this.nl);
            sbContent.append("HCS : ").append(act.HCS).append(this.nl);
        }
        solEvent.setDescription(sbContent.toString().replaceAll("null", "NIL"));
        if (!act.isH24) {
            final DateTime start = new DateTime(act.debutUTCD);
            solEvent.setStart(new EventDateTime().setDateTime(start));
            final DateTime end = new DateTime(act.finUTCD);
            solEvent.setEnd(new EventDateTime().setDateTime(end));
        }
        else {
            final DateTime start = new DateTime(dstart);
            solEvent.setStart(new EventDateTime().setDate(start));
            final DateTime end = new DateTime(dend);
            solEvent.setEnd(new EventDateTime().setDate(end));
        }
        final StringBuilder sbLocation = new StringBuilder();
        if (act.lieu != null) {
            sbLocation.append(act.lieu);
        }
        if (act.salle != null) {
            if (act.lieu != null) {
                sbLocation.append(" / ");
            }
            sbLocation.append(act.salle);
        }
        final String location = sbLocation.toString();
        if (location != null && location != "") {
            solEvent.setLocation(location);
        }
        events.add(solEvent);
        return events;
    }
    
    private ArrayList<GCalOptions> buildListeOptions(final String calEntetes, final String calTroncons, final String calSol, final String calConges, final String calRepos, final String calAbsences, final String calDispersions) {
        final ArrayList<GCalOptions> alOptions = new ArrayList<GCalOptions>();
        final String[] arrEntetes = calEntetes.split(";");
        final String[] arrTroncons = calTroncons.split(";");
        final String[] arrSol = calSol.split(";");
        final String[] arrConges = calConges.split(";");
        final String[] arrRepos = calRepos.split(";");
        final String[] arrAbsences = calAbsences.split(";");
        final String[] arrDispersions = calDispersions.split(";");
        String[] array;
        for (int length = (array = arrEntetes).length, i = 0; i < length; ++i) {
            final String name = array[i];
            if (!name.equals("")) {
                final GCalOptions gco = new GCalOptions(name, false, false, false, false, false, false, false);
                if (!this.listHasDuplicate(alOptions, name)) {
                    alOptions.add(gco);
                }
            }
        }
        String[] array2;
        for (int length2 = (array2 = arrTroncons).length, j = 0; j < length2; ++j) {
            final String name = array2[j];
            if (!name.equals("")) {
                final GCalOptions gco = new GCalOptions(name, false, false, false, false, false, false, false);
                if (!this.listHasDuplicate(alOptions, name)) {
                    alOptions.add(gco);
                }
            }
        }
        String[] array3;
        for (int length3 = (array3 = arrSol).length, k = 0; k < length3; ++k) {
            final String name = array3[k];
            if (!name.equals("")) {
                final GCalOptions gco = new GCalOptions(name, false, false, false, false, false, false, false);
                if (!this.listHasDuplicate(alOptions, name)) {
                    alOptions.add(gco);
                }
            }
        }
        String[] array4;
        for (int length4 = (array4 = arrConges).length, l = 0; l < length4; ++l) {
            final String name = array4[l];
            if (!name.equals("")) {
                final GCalOptions gco = new GCalOptions(name, false, false, false, false, false, false, false);
                if (!this.listHasDuplicate(alOptions, name)) {
                    alOptions.add(gco);
                }
            }
        }
        String[] array5;
        for (int length5 = (array5 = arrRepos).length, n = 0; n < length5; ++n) {
            final String name = array5[n];
            if (!name.equals("")) {
                final GCalOptions gco = new GCalOptions(name, false, false, false, false, false, false, false);
                if (!this.listHasDuplicate(alOptions, name)) {
                    alOptions.add(gco);
                }
            }
        }
        String[] array6;
        for (int length6 = (array6 = arrAbsences).length, n2 = 0; n2 < length6; ++n2) {
            final String name = array6[n2];
            if (!name.equals("")) {
                final GCalOptions gco = new GCalOptions(name, false, false, false, false, false, false, false);
                if (!this.listHasDuplicate(alOptions, name)) {
                    alOptions.add(gco);
                }
            }
        }
        String[] array7;
        for (int length7 = (array7 = arrDispersions).length, n3 = 0; n3 < length7; ++n3) {
            final String name = array7[n3];
            if (!name.equals("")) {
                final GCalOptions gco = new GCalOptions(name, false, false, false, false, false, false, false);
                if (!this.listHasDuplicate(alOptions, name)) {
                    alOptions.add(gco);
                }
            }
        }
        for (final GCalOptions gCalOptions : alOptions) {
            final String name2 = gCalOptions.getName();
            String[] array8;
            for (int length8 = (array8 = arrEntetes).length, n4 = 0; n4 < length8; ++n4) {
                final String arrEntete = array8[n4];
                if (arrEntete.equals(name2)) {
                    gCalOptions.setEntetes(true);
                }
            }
            String[] array9;
            for (int length9 = (array9 = arrTroncons).length, n5 = 0; n5 < length9; ++n5) {
                final String arrTroncon = array9[n5];
                if (arrTroncon.equals(name2)) {
                    gCalOptions.setTroncons(true);
                }
            }
            String[] array10;
            for (int length10 = (array10 = arrSol).length, n6 = 0; n6 < length10; ++n6) {
                final String element = array10[n6];
                if (element.equals(name2)) {
                    gCalOptions.setSol(true);
                }
            }
            String[] array11;
            for (int length11 = (array11 = arrConges).length, n7 = 0; n7 < length11; ++n7) {
                final String arrConge = array11[n7];
                if (arrConge.equals(name2)) {
                    gCalOptions.setConges(true);
                }
            }
            String[] array12;
            for (int length12 = (array12 = arrRepos).length, n8 = 0; n8 < length12; ++n8) {
                final String arrRepo = array12[n8];
                if (arrRepo.equals(name2)) {
                    gCalOptions.setRepos(true);
                }
            }
            String[] array13;
            for (int length13 = (array13 = arrAbsences).length, n9 = 0; n9 < length13; ++n9) {
                final String arrAbsence = array13[n9];
                if (arrAbsence.equals(name2)) {
                    gCalOptions.setAbsences(true);
                }
            }
            String[] array14;
            for (int length14 = (array14 = arrDispersions).length, n10 = 0; n10 < length14; ++n10) {
                final String arrDispersion = array14[n10];
                if (arrDispersion.equals(name2)) {
                    gCalOptions.setDispersions(true);
                }
            }
        }
        return alOptions;
    }
    
    private boolean listHasDuplicate(final ArrayList<GCalOptions> listGco, final String str) {
        for (final GCalOptions gco : listGco) {
            if (gco.getName().equals(str)) {
                return true;
            }
        }
        return false;
    }
    
    public int getProgressValue() {
        return this.progressValue;
    }
}
