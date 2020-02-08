// 
// Decompiled by Procyon v0.5.36
// 

package ccImport;

import java.util.Date;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.api.client.util.DateTime;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Iterator;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.CalendarList;
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
import chopeCrew.ChopeCrew;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnexionGoogle
{
    public static final int OK = 1;
    public static final int EXCEPTION = 0;
    private HashMap<String, String> mapCalendars;
    private ArrayList<String> listSourcesEvents;
    private int dMois;
    private int mois;
    private int annee;
    
    public ConnexionGoogle() {
        this.mapCalendars = new HashMap<String, String>();
        this.listSourcesEvents = new ArrayList<String>();
    }
    
    private ArrayList<String> getListeAgendas() {
        final ArrayList<String> listAgendas = new ArrayList<String>();
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
            final CalendarList feed = ChopeCrew.client.calendarList().list().execute();
            for (final CalendarListEntry calendar : feed.getItems()) {
                System.out.println(String.valueOf(calendar.getSummary()) + "  ZZZ   " + calendar.getId());
                listAgendas.add(calendar.getSummary());
                this.mapCalendars.put(calendar.getSummary(), calendar.getId());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listAgendas;
    }
    
    private void getEvents(final String calName) {
        final String calId = this.mapCalendars.get(calName);
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        if (this.dMois == 1) {
            cal.add(2, 1);
        }
        this.mois = cal.get(2) + 1;
        this.annee = cal.get(1);
        cal.add(5, -10);
        final DateTime debut = new DateTime(cal.getTime());
        cal.add(2, 2);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        cal.add(5, 10);
        final DateTime fin = new DateTime(cal.getTime());
        try {
            final Events listEvents = ChopeCrew.client.events().list(calId).setOrderBy("startTime").setSingleEvents(true).setTimeMin(debut).setTimeMax(fin).execute();
            long start = 0L;
            long end = 0L;
            for (final Event e : listEvents.getItems()) {
                String allday = "\n";
                if (e.getStart().getDateTime() != null && e.getEnd().getDateTime() != null) {
                    start = e.getStart().getDateTime().getValue();
                    end = e.getEnd().getDateTime().getValue();
                }
                else if (e.getStart().getDate() != null && e.getEnd().getDate() != null) {
                    start = e.getStart().getDate().getValue();
                    end = e.getEnd().getDate().getValue();
                    allday = "allday\n";
                }
                final String dep = new Date(start).toString();
                final String arr = new Date(end).toString();
                System.out.println(String.valueOf(e.getSummary()) + "  " + dep + "  " + e.getExtendedProperties() + "  " + arr + "   " + e.getDescription());
                String source = new String();
                if (e.getSummary() != null) {
                    source = String.valueOf(source) + e.getSummary() + "\n" + dep + "\n" + arr + "\n" + allday;
                }
                if (e.getDescription() != null) {
                    source = String.valueOf(source) + e.getDescription();
                }
                this.listSourcesEvents.add(source);
            }
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public int chope(final int dMois) {
        this.dMois = dMois;
        this.getListeAgendas();
        this.getEvents("Planning Crew Mobile");
        return 1;
    }
}
