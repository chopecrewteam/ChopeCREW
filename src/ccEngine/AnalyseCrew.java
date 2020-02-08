// 
// Decompiled by Procyon v0.5.36
// 

package ccEngine;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import ccImport.ConnexionRequeteur;
import ccImport.ConnexionCrew;
import ccImport.ConnexionGoogle;
import java.util.SimpleTimeZone;
import ccStructures.Participant;
import java.util.List;
import java.util.Collections;
import java.text.ParseException;
import ccStructures.BlocActivite;
import ccStructures.Presta;
import ccStructures.Deg_Reco;
import ccStructures.Dest_Reco;
import java.util.HashMap;
import ccStructures.Escale;
import ccStructures.Indem;
import ccStructures.Peq;
import java.util.Date;
import java.util.Iterator;
import ccUtils.Utils;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import chopeCrew.ChopeCrew;
import ccStructures.ActiviteSol;
import ccStructures.Rotation;
import java.util.ArrayList;
import java.io.Serializable;

public class AnalyseCrew implements Serializable
{
    private static final long serialVersionUID = 1L;
    public String matricule;
    public int deltaMois;
    public boolean isFlash;
    public int moisInt;
    public int anneeInt;
    public String moisNum;
    public String moisLit;
    public boolean isPNT;
    public boolean isMC;
    public boolean isBP;
    public String qualifPNT;
    public ArrayList<Comparable<Object>> listCrew;
    public ArrayList<Rotation> listRotation;
    public ArrayList<ActiviteSol> listSol;
    
    public AnalyseCrew() {
        this.listCrew = new ArrayList<Comparable<Object>>();
        this.listRotation = new ArrayList<Rotation>();
        this.listSol = new ArrayList<ActiviteSol>();
    }
    
    private void chope_TypePN_PageIntroPlanning(final String pageIntroPlanning) {
        if (pageIntroPlanning != null && pageIntroPlanning.contains("Exp r&eacute;cente")) {
            this.isPNT = true;
        }
        else {
            this.isPNT = false;
        }
    }
    
    private void chope_isMC_isBP_qualifPNT() {
        if (this.isPNT) {
            if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 0) {
                this.isMC = true;
                this.isBP = false;
                this.qualifPNT = "320";
            }
            else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 1) {
                this.isMC = true;
                this.isBP = true;
                this.qualifPNT = "320";
            }
            else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 2) {
                this.isMC = false;
                this.isBP = false;
                this.qualifPNT = "340";
            }
            else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 3) {
                this.isMC = false;
                this.isBP = false;
                this.qualifPNT = "340";
            }
            else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 4) {
                this.isMC = false;
                this.isBP = false;
                this.qualifPNT = "340";
            }
            else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 5) {
                this.isMC = false;
                this.isBP = false;
                this.qualifPNT = "787";
            }
            else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 6) {
                this.isMC = false;
                this.isBP = false;
                this.qualifPNT = "777";
            }
            else if (ChopeCrew.donneesPerso_PNT.qualif_pnt == 7) {
                this.isMC = false;
                this.isBP = false;
                this.qualifPNT = "380";
            }
        }
        else if (!this.isPNT) {
            if (ChopeCrew.donneesPerso_PNC.reseau_pnc == 0) {
                this.isMC = true;
                this.isBP = false;
            }
            else if (ChopeCrew.donneesPerso_PNC.reseau_pnc == 1) {
                this.isMC = true;
                this.isBP = true;
            }
            else if (ChopeCrew.donneesPerso_PNC.reseau_pnc == 2) {
                this.isMC = false;
                this.isBP = false;
            }
        }
    }
    
    private void chope_MoisAnneeMatricule_PageMensuelle(final String pageMensuelle) {
        String cible = "<title>Planning\\s+(.*?)\\s+(\\d{4})\\s+de.*?\\((\\d{6})\\d{2}\\)";
        final Matcher result = Pattern.compile(cible).matcher(pageMensuelle);
        if (result.find()) {
            this.moisLit = result.group(1).toLowerCase();
            this.anneeInt = Integer.parseInt(result.group(2));
            this.matricule = "m" + result.group(3).trim();
            if (this.moisLit.equals("janvier")) {
                this.moisLit = "Janvier";
                this.moisNum = "01";
                this.moisInt = 1;
            }
            else if (this.moisLit.equals("fevrier") || this.moisLit.equals("f\u00e9vrier") || this.moisLit.contains("vrier")) {
                this.moisLit = "F\u00e9vrier";
                this.moisNum = "02";
                this.moisInt = 2;
            }
            else if (this.moisLit.equals("mars")) {
                this.moisLit = "Mars";
                this.moisNum = "03";
                this.moisInt = 3;
            }
            else if (this.moisLit.equals("avril")) {
                this.moisLit = "Avril";
                this.moisNum = "04";
                this.moisInt = 4;
            }
            else if (this.moisLit.equals("mai")) {
                this.moisLit = "Mai";
                this.moisNum = "05";
                this.moisInt = 5;
            }
            else if (this.moisLit.equals("juin")) {
                this.moisLit = "Juin";
                this.moisNum = "06";
                this.moisInt = 6;
            }
            else if (this.moisLit.equals("juillet")) {
                this.moisLit = "Juillet";
                this.moisNum = "07";
                this.moisInt = 7;
            }
            else if (this.moisLit.equals("aout") || this.moisLit.equals("ao\u00fbt") || this.moisLit.contains("ao")) {
                this.moisLit = "Ao\u00fbt";
                this.moisNum = "08";
                this.moisInt = 8;
            }
            else if (this.moisLit.equals("septembre")) {
                this.moisLit = "Septembre";
                this.moisNum = "09";
                this.moisInt = 9;
            }
            else if (this.moisLit.equals("octobre")) {
                this.moisLit = "Octobre";
                this.moisNum = "10";
                this.moisInt = 10;
            }
            else if (this.moisLit.equals("novembre")) {
                this.moisLit = "Novembre";
                this.moisNum = "11";
                this.moisInt = 11;
            }
            else if (this.moisLit.equals("decembre") || this.moisLit.equals("d\u00e9cembre") || this.moisLit.contains("cembre")) {
                this.moisLit = "D\u00e9cembre";
                this.moisNum = "12";
                this.moisInt = 12;
            }
        }
        cible = "var IS_PNT=";
        final int index = pageMensuelle.indexOf(cible) + cible.length();
        if (index >= cible.length()) {
            cible = pageMensuelle.substring(index, index + 4);
            if (cible.equals("true")) {
                this.isPNT = true;
            }
            else if (cible.equals("false")) {
                this.isPNT = false;
            }
        }
    }
    
    private void chope_ActivitesVol_PageImpression(final String pageImpression) {
        final GregorianCalendar calUtc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final SimpleDateFormat sdfUtc = new SimpleDateFormat("dd/MM/yyyy '\u00e0' HH'h'mm");
        sdfUtc.setTimeZone(TimeZone.getTimeZone("UTC"));
        final ArrayList<String> pageImpressionDEC = this.decomposePageImpression(pageImpression);
        for (final String source : pageImpressionDEC) {
            final Rotation rotation = new Rotation();
            String cible = "Rotation ([\\w\\s]+) du (\\d{2}/\\d{2}/(\\d{4}))";
            Pattern regex1 = Pattern.compile(cible);
            Matcher result1 = regex1.matcher(source);
            String anneeRotation = "";
            String dateRotation = "";
            if (result1.find()) {
                rotation.label = result1.group(1).trim();
                dateRotation = result1.group(2);
                anneeRotation = result1.group(3);
            }
            cible = "Dur\u00e9e RPC(.*?)<strong>(.*?)</strong>";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            if (result1.find()) {
                rotation.dureeRpc = result1.group(2).trim();
            }
            cible = "Dur\u00e9e PAV(.*?)<strong>(.*?)</strong>";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            if (result1.find()) {
                rotation.dureePac = result1.group(2).trim();
            }
            cible = "<td class=ligneSelectionnable1 vAlign=top align=middle rowSpan=\".\">\\w{2} (\\d{1,2}) (\\w{3})</td>";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            while (result1.find()) {
                final ServiceVol sv = new ServiceVol();
                String jourSV = result1.group(1);
                String moisSV = result1.group(2);
                if (jourSV.length() == 1) {
                    jourSV = "0" + jourSV;
                }
                if (moisSV.equals("jan")) {
                    moisSV = "01";
                }
                else if (moisSV.equals("feb")) {
                    moisSV = "02";
                }
                else if (moisSV.equals("mar")) {
                    moisSV = "03";
                }
                else if (moisSV.equals("apr")) {
                    moisSV = "04";
                }
                else if (moisSV.equals("may")) {
                    moisSV = "05";
                }
                else if (moisSV.equals("jun")) {
                    moisSV = "06";
                }
                else if (moisSV.equals("jul")) {
                    moisSV = "07";
                }
                else if (moisSV.equals("aug")) {
                    moisSV = "08";
                }
                else if (moisSV.equals("sep")) {
                    moisSV = "09";
                }
                else if (moisSV.equals("oct")) {
                    moisSV = "10";
                }
                else if (moisSV.equals("nov")) {
                    moisSV = "11";
                }
                else if (moisSV.equals("dec")) {
                    moisSV = "12";
                }
                sv.dateSV = String.valueOf(jourSV) + "/" + moisSV + "/" + anneeRotation;
                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                try {
                    calUtc.setTime(sdf.parse(dateRotation));
                    final Date a = calUtc.getTime();
                    calUtc.setTime(sdf.parse(sv.dateSV));
                    final Date b = calUtc.getTime();
                    if (b.before(a)) {
                        calUtc.add(1, 1);
                        sv.dateSV = sdf.format(calUtc.getTime());
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                final int indexdeb = result1.end();
                cible = "<td class=ligneSelectionnable1 vAlign=top align=middle rowSpan=";
                int indexfin = source.indexOf(cible, indexdeb);
                if (indexfin < 0) {
                    cible = "<TD class=ligneSelectionnable1 vAlign=top align=middle rowSpan=";
                    indexfin = source.indexOf(cible, indexdeb);
                }
                if (indexfin < 0) {
                    indexfin = source.length();
                }
                cible = "noWrap>(.*?)</td>";
                final Pattern regex2 = Pattern.compile(cible, 34);
                final Matcher result2 = regex2.matcher(source);
                result2.region(indexdeb, indexfin);
                int compteur = 0;
                while (result2.find()) {
                    if (++compteur == 2) {
                        sv.TVSV = result2.group(1).trim();
                    }
                    else if (compteur == 4) {
                        sv.TSV = result2.group(1).trim();
                    }
                    else {
                        if (compteur != 6) {
                            continue;
                        }
                        sv.repos = result2.group(1).trim();
                    }
                }
                cible = "<td.[^<]*?valign.[^<]*?top.[^<]*?>(.*?)</td>";
                final Pattern regex3 = Pattern.compile(cible, 34);
                final Matcher result3 = regex3.matcher(source);
                result3.region(indexdeb, indexfin);
                compteur = 0;
                String numvol = "";
                String from = "";
                String to = "";
                String ismep = "";
                String depart = "";
                String arrivee = "";
                String type = "";
                while (result3.find()) {
                    if (++compteur == 1) {
                        numvol = result3.group(1).trim();
                    }
                    else if (compteur == 2) {
                        from = result3.group(1).trim();
                    }
                    else if (compteur == 3) {
                        to = result3.group(1).trim();
                    }
                    else if (compteur == 4) {
                        ismep = result3.group(1).trim();
                    }
                    else if (compteur == 5) {
                        depart = result3.group(1).trim();
                    }
                    else if (compteur == 6) {
                        arrivee = result3.group(1).trim();
                    }
                    else {
                        if (compteur != 7) {
                            continue;
                        }
                        type = result3.group(1).trim();
                        compteur = 0;
                        final Troncon troncon = new Troncon();
                        troncon.numVol = numvol;
                        troncon.from = from.substring(0, 3);
                        troncon.to = to;
                        troncon.lagFrom = from.substring(5, from.length() - 1).replaceAll(",", ".");
                        troncon.LAGfromMillis = (int)(Float.parseFloat(troncon.lagFrom) * 60.0f * 60.0f * 1000.0f);
                        if (ismep.equals("X")) {
                            troncon.isMep = true;
                        }
                        else {
                            troncon.isMep = false;
                        }
                        troncon.typeAvion = type;
                        try {
                            final Date dep = sdfUtc.parse(String.valueOf(sv.dateSV) + " \u00e0 " + depart);
                            Date arr = sdfUtc.parse(String.valueOf(sv.dateSV) + " \u00e0 " + arrivee);
                            if (arr.before(dep)) {
                                calUtc.setTime(arr);
                                calUtc.add(5, 1);
                                arr = calUtc.getTime();
                            }
                            troncon.departUTC = sdfUtc.format(dep);
                            troncon.arriveeUTC = sdfUtc.format(arr);
                            troncon.debutUTCD = dep;
                            troncon.finUTCD = arr;
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        sv.listTroncon.add(troncon);
                    }
                }
                for (int k = 0; k < sv.listTroncon.size() - 1; ++k) {
                    final Troncon t1 = sv.listTroncon.get(k);
                    final Troncon t2 = sv.listTroncon.get(k + 1);
                    if (t2.debutUTCD.before(t1.finUTCD)) {
                        calUtc.setTime(t2.debutUTCD);
                        calUtc.add(5, 1);
                        t2.debutUTCD = calUtc.getTime();
                        t2.departUTC = sdfUtc.format(t2.debutUTCD);
                        calUtc.setTime(t2.finUTCD);
                        calUtc.add(5, 1);
                        t2.finUTCD = calUtc.getTime();
                        t2.arriveeUTC = sdfUtc.format(t2.finUTCD);
                    }
                }
                final Iterator<Troncon> iterator2 = sv.listTroncon.iterator();
                while (iterator2.hasNext()) {
                    final Troncon troncon = iterator2.next();
                    double tvt = (troncon.finUTCD.getTime() - troncon.debutUTCD.getTime()) / 3600000.0;
                    tvt = Utils.arrondi(tvt, 2);
                    troncon.TVT = Utils.timetoString(tvt);
                }
                rotation.listSV.add(sv);
            }
            rotation.debutUTCD = rotation.listSV.get(0).listTroncon.get(0).debutUTCD;
            final ServiceVol lastSv = rotation.listSV.get(rotation.listSV.size() - 1);
            final Troncon lastTroncon = lastSv.listTroncon.get(lastSv.listTroncon.size() - 1);
            rotation.finUTCD = lastTroncon.finUTCD;
            if (!this.listRotation.contains(rotation)) {
                this.listRotation.add(rotation);
            }
        }
    }
    
    private void chope_ActivitesVol_ListPagesImpression(final ArrayList<String> listPagesImpression) {
        final GregorianCalendar calUtc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final SimpleDateFormat sdfUtc = new SimpleDateFormat("dd/MM/yyyy '\u00e0' HH'h'mm");
        sdfUtc.setTimeZone(TimeZone.getTimeZone("UTC"));
        for (final String source : listPagesImpression) {
            final Rotation rotation = new Rotation();
            String cible = "Rotation ([\\w\\s]+) du [\\w]+ (\\d{1,2}) ([\\w]+) (\\d{4})";
            Pattern regex1 = Pattern.compile(cible);
            Matcher result1 = regex1.matcher(source);
            String anneeRotation = "";
            String dateRotation = "";
            if (result1.find()) {
                rotation.label = result1.group(1).trim();
                anneeRotation = result1.group(4);
                String mois = "";
                if (result1.group(3).equals("Janvier")) {
                    mois = "01";
                }
                else if (result1.group(3).equals("F\u00e9vrier") || result1.group(3).contains("vrier")) {
                    mois = "02";
                }
                else if (result1.group(3).equals("Mars")) {
                    mois = "03";
                }
                else if (result1.group(3).equals("Avril")) {
                    mois = "04";
                }
                else if (result1.group(3).equals("Mai")) {
                    mois = "05";
                }
                else if (result1.group(3).equals("Juin")) {
                    mois = "06";
                }
                else if (result1.group(3).equals("Juillet")) {
                    mois = "07";
                }
                else if (result1.group(3).equals("Ao\u00fbt") || result1.group(3).contains("Ao")) {
                    mois = "08";
                }
                else if (result1.group(3).equals("Septembre")) {
                    mois = "09";
                }
                else if (result1.group(3).equals("Octobre")) {
                    mois = "10";
                }
                else if (result1.group(3).equals("Novembre")) {
                    mois = "11";
                }
                else if (result1.group(3).equals("D\u00e9cembre") || result1.group(3).contains("cembre")) {
                    mois = "12";
                }
                dateRotation = String.valueOf(result1.group(2)) + "/" + mois + "/" + result1.group(4);
            }
            System.out.println(String.valueOf(rotation.label) + "  " + dateRotation);
            cible = "CDB :(.*?)<strong>(.*?)</strong>";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            if (result1.find()) {
                rotation.nbCDB = result1.group(2).trim();
            }
            cible = "OPL :(.*?)<strong>(.*?)</strong>";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            if (result1.find()) {
                rotation.nbOPL = result1.group(2).trim();
            }
            System.out.println("CDB : " + rotation.nbCDB + "   OPL : " + rotation.nbOPL);
            cible = "Dur\u00e9e RPC(.*?)<strong>(.*?)</strong>";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            if (result1.find()) {
                rotation.dureeRpc = result1.group(2).trim();
            }
            cible = "Dur\u00e9e PAV(.*?)<strong>(.*?)</strong>";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            if (result1.find()) {
                rotation.dureePac = result1.group(2).trim();
            }
            cible = "<td Class=\"ligneSelectionnable.\" onmouseover=\"\" valign=\"top\" align=\"center\" rowspan=\".\">\\w{2} (\\d{1,2}) (\\w{3})</td>";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            while (result1.find()) {
                final ServiceVol sv = new ServiceVol();
                String jourSV = result1.group(1);
                String moisSV = result1.group(2);
                if (jourSV.length() == 1) {
                    jourSV = "0" + jourSV;
                }
                if (moisSV.equals("jan")) {
                    moisSV = "01";
                }
                else if (moisSV.equals("feb")) {
                    moisSV = "02";
                }
                else if (moisSV.equals("mar")) {
                    moisSV = "03";
                }
                else if (moisSV.equals("apr")) {
                    moisSV = "04";
                }
                else if (moisSV.equals("may")) {
                    moisSV = "05";
                }
                else if (moisSV.equals("jun")) {
                    moisSV = "06";
                }
                else if (moisSV.equals("jul")) {
                    moisSV = "07";
                }
                else if (moisSV.equals("aug")) {
                    moisSV = "08";
                }
                else if (moisSV.equals("sep")) {
                    moisSV = "09";
                }
                else if (moisSV.equals("oct")) {
                    moisSV = "10";
                }
                else if (moisSV.equals("nov")) {
                    moisSV = "11";
                }
                else if (moisSV.equals("dec")) {
                    moisSV = "12";
                }
                sv.dateSV = String.valueOf(jourSV) + "/" + moisSV + "/" + anneeRotation;
                final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                try {
                    calUtc.setTime(sdf.parse(dateRotation));
                    final Date a = calUtc.getTime();
                    calUtc.setTime(sdf.parse(sv.dateSV));
                    final Date b = calUtc.getTime();
                    if (b.before(a)) {
                        calUtc.add(1, 1);
                        sv.dateSV = sdf.format(calUtc.getTime());
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                final int indexdeb = result1.end();
                final String cibleZ = "<td Class=\"ligneSelectionnable.\" onmouseover=\"\" valign=\"top\" align=\"center\" rowspan=\".\">\\w{2} (\\d{1,2}) (\\w{3})</td>";
                final Pattern regexZ = Pattern.compile(cibleZ, 34);
                final Matcher resultZ = regexZ.matcher(source);
                resultZ.region(indexdeb, source.length());
                int indexfin = source.length();
                if (resultZ.find()) {
                    indexfin = resultZ.start();
                }
                cible = "<td nowrap (style=\"border:0 none\")?>(.*?)</td>";
                final Pattern regex2 = Pattern.compile(cible, 34);
                final Matcher result2 = regex2.matcher(source);
                result2.region(indexdeb, indexfin);
                int compteur = 0;
                while (result2.find()) {
                    if (++compteur == 2) {
                        sv.TVSV = result2.group(2).trim();
                    }
                    else if (compteur == 4) {
                        sv.TSV = result2.group(2).trim();
                    }
                    else {
                        if (compteur != 6) {
                            continue;
                        }
                        sv.repos = result2.group(2).trim();
                    }
                }
                System.out.println("TVSV = " + sv.TVSV + "   TSV = " + sv.TSV + "   Repos = " + sv.repos);
                cible = "<td.[^<]*?valign.[^<]*?top.[^<]*?>(.*?)</td>";
                final Pattern regex3 = Pattern.compile(cible, 34);
                final Matcher result3 = regex3.matcher(source);
                result3.region(indexdeb, indexfin);
                compteur = 0;
                String numvol = "";
                String from = "";
                String to = "";
                String ismep = "";
                String depart = "";
                String arrivee = "";
                String type = "";
                while (result3.find()) {
                    if (++compteur == 1) {
                        numvol = result3.group(1).trim();
                    }
                    else if (compteur == 2) {
                        from = result3.group(1).trim();
                    }
                    else if (compteur == 3) {
                        to = result3.group(1).trim();
                    }
                    else if (compteur == 4) {
                        ismep = result3.group(1).trim();
                    }
                    else if (compteur == 5) {
                        depart = result3.group(1).trim();
                    }
                    else if (compteur == 6) {
                        arrivee = result3.group(1).trim();
                    }
                    else {
                        if (compteur != 7) {
                            continue;
                        }
                        type = result3.group(1).trim();
                        System.out.println(String.valueOf(numvol) + "  " + from + "  " + to + "  " + ismep + "  " + depart + "  " + arrivee + "  " + type);
                        compteur = 0;
                        final Troncon troncon = new Troncon();
                        troncon.numVol = numvol;
                        troncon.from = from.substring(0, 3);
                        troncon.to = to;
                        troncon.lagFrom = from.substring(5, from.length() - 1).replaceAll(",", ".");
                        troncon.LAGfromMillis = (int)(Float.parseFloat(troncon.lagFrom) * 60.0f * 60.0f * 1000.0f);
                        if (ismep.equals("X")) {
                            troncon.isMep = true;
                        }
                        else {
                            troncon.isMep = false;
                        }
                        troncon.typeAvion = type;
                        try {
                            final Date dep = sdfUtc.parse(String.valueOf(sv.dateSV) + " \u00e0 " + depart);
                            Date arr = sdfUtc.parse(String.valueOf(sv.dateSV) + " \u00e0 " + arrivee);
                            if (arr.before(dep)) {
                                calUtc.setTime(arr);
                                calUtc.add(5, 1);
                                arr = calUtc.getTime();
                            }
                            troncon.departUTC = sdfUtc.format(dep);
                            troncon.arriveeUTC = sdfUtc.format(arr);
                            troncon.debutUTCD = dep;
                            troncon.finUTCD = arr;
                        }
                        catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        sv.listTroncon.add(troncon);
                    }
                }
                for (int k = 0; k < sv.listTroncon.size() - 1; ++k) {
                    final Troncon t1 = sv.listTroncon.get(k);
                    final Troncon t2 = sv.listTroncon.get(k + 1);
                    if (t2.debutUTCD.before(t1.finUTCD)) {
                        calUtc.setTime(t2.debutUTCD);
                        calUtc.add(5, 1);
                        t2.debutUTCD = calUtc.getTime();
                        t2.departUTC = sdfUtc.format(t2.debutUTCD);
                        calUtc.setTime(t2.finUTCD);
                        calUtc.add(5, 1);
                        t2.finUTCD = calUtc.getTime();
                        t2.arriveeUTC = sdfUtc.format(t2.finUTCD);
                    }
                }
                final Iterator<Troncon> iterator2 = sv.listTroncon.iterator();
                while (iterator2.hasNext()) {
                    final Troncon troncon = iterator2.next();
                    double tvt = (troncon.finUTCD.getTime() - troncon.debutUTCD.getTime()) / 3600000.0;
                    tvt = Utils.arrondi(tvt, 2);
                    troncon.TVT = Utils.timetoString(tvt);
                }
                rotation.listSV.add(sv);
            }
            cible = "Equipage de la rotation";
            int indexdeb2 = source.indexOf(cible);
            cible = "</table>";
            int indexfin2 = source.indexOf(cible, indexdeb2);
            cible = "<td align=\"left\">(.*?)</td>";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            result1.region(indexdeb2, indexfin2);
            int compteur = 0;
            String nom = "";
            String prenom = "";
            String fab = "";
            while (result1.find()) {
                if (++compteur == 1) {
                    nom = result1.group(1).trim();
                }
                else if (compteur == 2) {
                    prenom = result1.group(1).trim();
                }
                else if (compteur == 4) {
                    fab = result1.group(1).trim();
                }
                else {
                    if (compteur != 7) {
                        continue;
                    }
                    compteur = 0;
                    final Peq peq = new Peq();
                    peq.nom = nom;
                    peq.prenom = prenom;
                    peq.fab = fab;
                    System.out.println(String.valueOf(peq.nom) + " " + peq.prenom + " " + peq.fab);
                    rotation.listPeqRot.add(peq);
                }
            }
            final ArrayList<Indem> listIndem = new ArrayList<Indem>();
            cible = "Indemnit\u00e9s de l'ensemble des escales";
            indexdeb2 = source.indexOf(cible);
            cible = "</table>";
            indexfin2 = source.indexOf(cible, indexdeb2);
            cible = "<td align=\"left\"(.*?)>(.*?)<";
            regex1 = Pattern.compile(cible, 34);
            result1 = regex1.matcher(source);
            result1.region(indexdeb2, indexfin2);
            compteur = 0;
            String escale = "";
            String indemLoc = "";
            String indemEuro = "";
            String mfLoc = "";
            String mfEuro = "";
            String taux = "";
            String dateEffet = "";
            while (result1.find()) {
                if (++compteur == 1) {
                    escale = result1.group(2).trim();
                }
                else if (compteur == 2) {
                    indemLoc = result1.group(2).trim();
                }
                else if (compteur == 3) {
                    indemEuro = result1.group(2).trim();
                }
                else if (compteur == 4) {
                    mfLoc = result1.group(2).trim();
                }
                else if (compteur == 5) {
                    mfEuro = result1.group(2).trim();
                }
                else if (compteur == 6) {
                    taux = result1.group(2).trim();
                }
                else if (compteur == 7) {
                    dateEffet = result1.group(2).trim();
                }
                else {
                    if (compteur != 8) {
                        continue;
                    }
                    compteur = 0;
                    System.out.println(String.valueOf(escale) + " " + indemLoc + " " + indemEuro + " " + mfLoc + " " + mfEuro + " " + taux + " " + dateEffet);
                    final Indem indem = new Indem();
                    cible = "\\(([A-Z]{3})\\)";
                    Pattern regex2 = Pattern.compile(cible, 34);
                    Matcher result2 = regex2.matcher(escale);
                    if (result2.find()) {
                        indem.escale = result2.group(1);
                    }
                    cible = "(.*?) [A-Z]{3}";
                    regex2 = Pattern.compile(cible, 34);
                    result2 = regex2.matcher(indemLoc);
                    if (result2.find()) {
                        indem.irLoc = result2.group(1);
                    }
                    cible = "(.*?) EUR";
                    regex2 = Pattern.compile(cible, 34);
                    result2 = regex2.matcher(indemEuro);
                    if (result2.find()) {
                        indem.irEur = result2.group(1);
                    }
                    cible = "(.*?) [A-Z]{3}";
                    regex2 = Pattern.compile(cible, 34);
                    result2 = regex2.matcher(mfLoc);
                    if (result2.find()) {
                        indem.mfLoc = result2.group(1);
                    }
                    cible = "(.*?) EUR";
                    regex2 = Pattern.compile(cible, 34);
                    result2 = regex2.matcher(mfEuro);
                    if (result2.find()) {
                        indem.mfEur = result2.group(1);
                    }
                    cible = "1 ([A-Z]{3}(.*?)) = (.*?) EUR";
                    regex2 = Pattern.compile(cible, 34);
                    result2 = regex2.matcher(taux);
                    if (result2.find()) {
                        indem.monnaieLoc = result2.group(1);
                        indem.change = result2.group(3);
                    }
                    else {
                        indem.monnaieLoc = "EUR (EURO)";
                        indem.change = "1";
                    }
                    indem.dateEffet = dateEffet;
                    try {
                        indem.ir = Double.parseDouble(indem.irEur.replaceAll(",", "."));
                        indem.mf = Double.parseDouble(indem.mfEur.replaceAll(",", "."));
                    }
                    catch (Exception e3) {
                        e3.printStackTrace();
                    }
                    System.out.println(String.valueOf(indem.escale) + " " + indem.irLoc + " " + indem.irEur + " " + indem.mfLoc + " " + indem.mfEur + " " + indem.change + " " + indem.dateEffet);
                    listIndem.add(indem);
                }
            }
            for (final ServiceVol sv2 : rotation.listSV) {
                for (final Troncon troncon2 : sv2.listTroncon) {
                    for (final Indem indem2 : listIndem) {
                        if (indem2.escale.equals(troncon2.from)) {
                            troncon2.indemFrom.change = indem2.change;
                            troncon2.indemFrom.dateEffet = indem2.dateEffet;
                            troncon2.indemFrom.escale = indem2.escale;
                            troncon2.indemFrom.irEur = indem2.irEur;
                            troncon2.indemFrom.irLoc = indem2.irLoc;
                            troncon2.indemFrom.mfEur = indem2.mfEur;
                            troncon2.indemFrom.mfLoc = indem2.mfLoc;
                            troncon2.indemFrom.monnaieLoc = indem2.monnaieLoc;
                            troncon2.indemFrom.ir = indem2.ir;
                            troncon2.indemFrom.mf = indem2.mf;
                        }
                        if (indem2.escale.equals(troncon2.to)) {
                            troncon2.indemTo.change = indem2.change;
                            troncon2.indemTo.dateEffet = indem2.dateEffet;
                            troncon2.indemTo.escale = indem2.escale;
                            troncon2.indemTo.irEur = indem2.irEur;
                            troncon2.indemTo.irLoc = indem2.irLoc;
                            troncon2.indemTo.mfEur = indem2.mfEur;
                            troncon2.indemTo.mfLoc = indem2.mfLoc;
                            troncon2.indemTo.monnaieLoc = indem2.monnaieLoc;
                            troncon2.indemTo.ir = indem2.ir;
                            troncon2.indemTo.mf = indem2.mf;
                        }
                    }
                }
            }
            final int nbPeqRequis = Integer.parseInt(rotation.nbCDB) + Integer.parseInt(rotation.nbOPL);
            final int nbPeqPresents = rotation.listPeqRot.size();
            if (nbPeqRequis > nbPeqPresents) {
                rotation.label = String.valueOf(rotation.label) + "    BULLE";
            }
            this.listRotation.add(rotation);
        }
    }
    
    private void chope_ActivitesVol_ExportPda(final String exportPda) {
        final SimpleDateFormat sdfUtc = new SimpleDateFormat("dd/MM/yyyy '\u00e0' HH'h'mm");
        sdfUtc.setTimeZone(TimeZone.getTimeZone("UTC"));
        final ArrayList<String> exportPdaDEC = this.decomposeExportPda(exportPda);
        for (final String source : exportPdaDEC) {
            final Rotation rot = new Rotation();
            String cible = "\"";
            if (source.indexOf(cible) >= 0) {
                final int idebut = source.indexOf(cible) + cible.length();
                final int ifin = source.indexOf("\"", idebut);
                rot.label = source.substring(idebut, ifin).trim();
            }
            cible = "Dur\u00e9e RPC :";
            if (source.indexOf(cible) >= 0) {
                final int idebut = source.indexOf(cible) + cible.length();
                final int ifin = source.indexOf("\n", idebut);
                rot.dureeRpc = source.substring(idebut, ifin).trim();
            }
            else {
                rot.dureeRpc = "0h00";
            }
            cible = "Dur\u00e9e PAV :";
            if (source.indexOf(cible) >= 0) {
                final int idebut = source.indexOf(cible) + cible.length();
                final int ifin = source.indexOf("\n", idebut);
                rot.dureePac = source.substring(idebut, ifin).trim();
            }
            else {
                rot.dureePac = "0h00";
            }
            String mois = null;
            boolean flagLastSV = false;
            String lastHit = "";
            final String motif = "[A-Za-z]{2}\\s*(\\d{2})\\s*([a-z]{3})\\s*(\\d{4}),\\s*TVSV\\s*:\\s*(\\d+h\\d{2}),\\s*TSV\\s*:\\s*(\\d+h\\d{2})(,\\s*Repos\\s*:\\s*(\\d+h\\d{2}))?";
            final Pattern regex = Pattern.compile(motif);
            final Matcher result = regex.matcher(source);
            while (result.find() && !flagLastSV) {
                final ServiceVol sv = new ServiceVol();
                lastHit = source.substring(result.start(), result.end());
                if (result.group(2).equals("jan")) {
                    mois = "01";
                }
                else if (result.group(2).equals("feb")) {
                    mois = "02";
                }
                else if (result.group(2).equals("mar")) {
                    mois = "03";
                }
                else if (result.group(2).equals("apr")) {
                    mois = "04";
                }
                else if (result.group(2).equals("may")) {
                    mois = "05";
                }
                else if (result.group(2).equals("jun")) {
                    mois = "06";
                }
                else if (result.group(2).equals("jul")) {
                    mois = "07";
                }
                else if (result.group(2).equals("aug")) {
                    mois = "08";
                }
                else if (result.group(2).equals("sep")) {
                    mois = "09";
                }
                else if (result.group(2).equals("oct")) {
                    mois = "10";
                }
                else if (result.group(2).equals("nov")) {
                    mois = "11";
                }
                else if (result.group(2).equals("dec")) {
                    mois = "12";
                }
                sv.dateSV = String.valueOf(result.group(1)) + "/" + mois + "/" + result.group(3);
                sv.TVSV = result.group(4);
                sv.TSV = result.group(5);
                sv.repos = result.group(7);
                if (sv.repos == null) {
                    flagLastSV = true;
                }
                String sourceTroncons = source.substring(result.end());
                final String motif2 = "[A-Za-z]{2}\\s*(\\d{2})\\s*([a-z]{3})\\s*(\\d{4}),\\s*TVSV\\s*:\\s*(\\d+h\\d{2}),\\s*TSV\\s*:\\s*(\\d+h\\d{2})(,\\s*Repos\\s*:\\s*(\\d+h\\d{2}))?";
                final Pattern regex2 = Pattern.compile(motif2);
                final Matcher result2 = regex2.matcher(source);
                result2.region(result.end(), source.length());
                while (result2.find()) {
                    if (!flagLastSV && !lastHit.equals(source.substring(result2.start(), result2.end()))) {
                        sourceTroncons = source.substring(result.end(), result2.start());
                        break;
                    }
                }
                final String motifT = "\"\\s*([A-Z]{2}\\s?[0-9A-Z]+)?\\s*([A-Z]{3})\\s*\u00e0\\s*([A-Z]{3})( \\(MEP\\))?\",\"\\d{2}/\\d{2}/\\d{4}\",\"\\d{2}:\\d{2}:\\d{2}\",\"\\d{2}/\\d{2}/\\d{4}\",\"\\d{2}:\\d{2}:\\d{2}\",\"Faux\",\"\",\"";
                final Pattern regexT = Pattern.compile(motifT);
                final Matcher resultT = regexT.matcher(sourceTroncons);
                while (resultT.find()) {
                    final Troncon troncon = new Troncon();
                    troncon.numVol = resultT.group(1);
                    troncon.from = resultT.group(2);
                    troncon.to = resultT.group(3);
                    if (resultT.group(4) == null) {
                        troncon.isMep = false;
                    }
                    else {
                        troncon.isMep = true;
                    }
                    final int indexdebut = resultT.end();
                    final int indexfin = sourceTroncons.indexOf("\"", indexdebut);
                    final String motifA = "\\s*Date d\u00e9part UTC\\s*:\\s*(\\d{2}/\\d{2}/\\d{4} \u00e0 \\d{2}h\\d{2})?";
                    final String motifB = "\\s*D\u00e9calage horaire d\u00e9part\\s*:\\s*(-?\\d+\\.\\d)?";
                    final String motifC = "\\s*Date arriv\u00e9e UTC\\s*:\\s*(\\d{2}/\\d{2}/\\d{4} \u00e0 \\d{2}h\\d{2})?";
                    final String motifD = "\\s*D\u00e9calage horaire arriv\u00e9e\\s*:\\s*(-?\\d+\\.\\d)?";
                    final String motifE = "\\s*TVT\\s*:\\s*(\\d+h\\d{2})?";
                    final String motifF = "\\s*Date th\u00e9orique de vol\\s*:\\s*[A-Za-z]{2} \\d{2} [a-z]{3} \\d{4}";
                    final String motifG = "\\s*Type avion\\s*:\\s*(\\w*)";
                    final String motifZ = String.valueOf(motifA) + motifB + motifC + motifD + motifE + motifF + motifG;
                    final Pattern regexZ = Pattern.compile(motifZ);
                    final Matcher resultZ = regexZ.matcher(sourceTroncons);
                    resultZ.region(indexdebut, indexfin);
                    if (resultZ.find()) {
                        troncon.departUTC = resultZ.group(1);
                        troncon.lagFrom = resultZ.group(2);
                        troncon.LAGfromMillis = (int)(Float.parseFloat(troncon.lagFrom) * 60.0f * 60.0f * 1000.0f);
                        troncon.arriveeUTC = resultZ.group(3);
                        troncon.lagTo = resultZ.group(4);
                        troncon.LAGtoMillis = (int)(Float.parseFloat(troncon.lagTo) * 60.0f * 60.0f * 1000.0f);
                        troncon.TVT = resultZ.group(5);
                        troncon.typeAvion = resultZ.group(6);
                        try {
                            troncon.debutUTCD = sdfUtc.parse(troncon.departUTC);
                            troncon.finUTCD = sdfUtc.parse(troncon.arriveeUTC);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (troncon.from.equals(troncon.to)) {
                        troncon.numVol = "SOL";
                    }
                    if (!sv.listTroncon.contains(troncon)) {
                        boolean flag = false;
                        for (final ServiceVol svtemp : rot.listSV) {
                            if (svtemp.listTroncon.contains(troncon)) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            continue;
                        }
                        sv.listTroncon.add(troncon);
                    }
                }
                if (!rot.listSV.contains(sv)) {
                    rot.listSV.add(sv);
                }
            }
            rot.debutUTCD = rot.listSV.get(0).listTroncon.get(0).debutUTCD;
            final ServiceVol lastSv = rot.listSV.get(rot.listSV.size() - 1);
            final Troncon lastTroncon = lastSv.listTroncon.get(lastSv.listTroncon.size() - 1);
            rot.finUTCD = lastTroncon.finUTCD;
            if (!this.listRotation.contains(rot)) {
                this.listRotation.add(rot);
            }
        }
    }
    
    private void chope_ActivitesVol_Complement() {
        final GregorianCalendar calUtc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final SimpleDateFormat sdfUtc = new SimpleDateFormat("dd/MM/yyyy '\u00e0' HH'h'mm");
        sdfUtc.setTimeZone(TimeZone.getTimeZone("UTC"));
        final HashMap<String, Escale> escales = this.importBaseEscales();
        for (int i = 0; i < this.listRotation.size(); ++i) {
            final Rotation rotation = this.listRotation.get(i);
            int indexSVODa = -1;
            int indexTROODa = -1;
            int indexSVODr = -1;
            int indexTROODr = -1;
            boolean flagLastSol = false;
            for (int j = 0; j < rotation.listSV.size(); ++j) {
                final ServiceVol sv = rotation.listSV.get(j);
                for (int k = 0; k < sv.listTroncon.size(); ++k) {
                    final Troncon tro = sv.listTroncon.get(k);
                    if (tro.numVol.equals("SOL") && !flagLastSol) {
                        flagLastSol = true;
                        if (k > 0) {
                            indexSVODa = j;
                            indexTROODa = k - 1;
                        }
                        else if (k == 0 && j > 0) {
                            indexSVODa = j - 1;
                            indexTROODa = rotation.listSV.get(j - 1).listTroncon.size() - 1;
                        }
                    }
                    if (!tro.numVol.equals("SOL") && flagLastSol) {
                        flagLastSol = false;
                        indexSVODr = j;
                        indexTROODr = k;
                    }
                }
            }
            if (indexSVODa != -1) {
                final Rotation rot1 = new Rotation();
                final Rotation rot2 = new Rotation();
                final Rotation rot3 = new Rotation();
                final Rotation rot4 = new Rotation();
                rot1.label = rotation.label;
                rot1.dureePac = rotation.dureePac;
                rot2.label = rotation.label;
                rot2.dureeRpc = rotation.dureeRpc;
                rot3.label = String.valueOf(rotation.label) + "  OD ALLER";
                rot3.isOD = true;
                rot4.label = String.valueOf(rotation.label) + "  OD RETOUR";
                rot4.isOD = true;
                for (int l = 0; l < rotation.listSV.size(); ++l) {
                    final ServiceVol sv2 = rotation.listSV.get(l);
                    if (l < indexSVODa) {
                        rot1.listSV.add(sv2);
                    }
                    if (l == indexSVODa) {
                        final ServiceVol sv3 = new ServiceVol();
                        final ServiceVol sv4 = new ServiceVol();
                        sv3.dateSV = sv2.dateSV;
                        sv3.TVSV = sv2.TVSV;
                        sv3.TSV = sv2.TSV;
                        sv3.repos = sv2.repos;
                        sv4.dateSV = sv2.dateSV;
                        sv4.TVSV = "0h00";
                        sv4.TSV = "0h00";
                        sv4.repos = null;
                        for (int m = 0; m < sv2.listTroncon.size(); ++m) {
                            final Troncon tro2 = sv2.listTroncon.get(m);
                            if (m < indexTROODa) {
                                sv3.listTroncon.add(tro2);
                            }
                            if (m == indexTROODa) {
                                tro2.isMep = true;
                                tro2.typeAvion = "OD";
                                sv4.listTroncon.add(tro2);
                            }
                        }
                        if (sv3.listTroncon.size() > 0) {
                            rot1.listSV.add(sv3);
                        }
                        if (sv4.listTroncon.size() > 0) {
                            rot3.listSV.add(sv4);
                        }
                    }
                    if (l == indexSVODr) {
                        final ServiceVol sv3 = new ServiceVol();
                        final ServiceVol sv4 = new ServiceVol();
                        sv3.dateSV = sv2.dateSV;
                        sv3.TVSV = "0h00";
                        sv3.TSV = "0h00";
                        sv3.repos = null;
                        sv4.dateSV = sv2.dateSV;
                        sv4.TVSV = sv2.TVSV;
                        sv4.TSV = sv2.TSV;
                        sv4.repos = sv2.repos;
                        for (int m = 0; m < sv2.listTroncon.size(); ++m) {
                            final Troncon tro2 = sv2.listTroncon.get(m);
                            if (m == indexTROODr) {
                                tro2.isMep = true;
                                tro2.typeAvion = "OD";
                                sv3.listTroncon.add(tro2);
                            }
                            if (m > indexTROODr) {
                                sv4.listTroncon.add(tro2);
                            }
                        }
                        if (sv3.listTroncon.size() > 0) {
                            rot4.listSV.add(sv3);
                        }
                        if (sv4.listTroncon.size() > 0) {
                            rot2.listSV.add(sv4);
                        }
                    }
                    if (l > indexSVODr) {
                        rot2.listSV.add(sv2);
                    }
                }
                if (rot1.listSV.size() > 0) {
                    this.listRotation.add(i, rot1);
                    ++i;
                }
                if (rot3.listSV.size() > 0) {
                    this.listRotation.add(i, rot3);
                    ++i;
                }
                if (rot4.listSV.size() > 0) {
                    this.listRotation.add(i, rot4);
                    ++i;
                }
                if (rot2.listSV.size() > 0) {
                    this.listRotation.add(i, rot2);
                    ++i;
                }
                this.listRotation.remove(i);
                --i;
            }
        }
        for (int i = 0; i < this.listRotation.size(); ++i) {
            final Rotation rotation = this.listRotation.get(i);
            rotation.numRot = i + 1;
            final ServiceVol firstSV = rotation.listSV.get(0);
            final ServiceVol lastSV = rotation.listSV.get(rotation.listSV.size() - 1);
            final Troncon firstTronconRotation = firstSV.listTroncon.get(0);
            final Troncon lastTronconRotation = lastSV.listTroncon.get(lastSV.listTroncon.size() - 1);
            if (!firstTronconRotation.from.equals(lastTronconRotation.to) && !rotation.isOD) {
                rotation.isCourrierCroise = true;
            }
            else {
                rotation.isCourrierCroise = false;
            }
            rotation.departUTC = firstTronconRotation.departUTC;
            rotation.debutUTCD = firstTronconRotation.debutUTCD;
            rotation.arriveeUTC = lastTronconRotation.arriveeUTC;
            rotation.finUTCD = lastTronconRotation.finUTCD;
            final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
            final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
            caldeb.setTime(rotation.debutUTCD);
            calfin.setTime(rotation.finUTCD);
            caldeb.set(11, 0);
            caldeb.set(12, 0);
            caldeb.set(13, 0);
            caldeb.set(14, 0);
            calfin.set(11, 23);
            calfin.set(12, 59);
            calfin.set(13, 59);
            calfin.set(14, 1000);
            final long deltaOffset = calfin.get(15) + calfin.get(16) - caldeb.get(15) - caldeb.get(16);
            rotation.nON = (int)Math.round((calfin.getTimeInMillis() - caldeb.getTimeInMillis() + deltaOffset) / 8.64E7);
            double rotTVT = 0.0;
            double rotTVMep = 0.0;
            for (final ServiceVol sv5 : rotation.listSV) {
                rotTVT += Utils.timeStringtoMs(sv5.TVSV);
                for (final Troncon troncon : sv5.listTroncon) {
                    if (troncon.isMep) {
                        rotTVMep += Utils.timeStringtoMs(troncon.TVT);
                    }
                }
            }
            rotTVT /= 3600000.0;
            rotTVT = Math.round(rotTVT * 100.0) / 100.0;
            if (!rotation.isOD) {
                rotation.tempsVolTotal = Utils.timetoString(rotTVT);
            }
            else {
                rotation.tempsVolTotal = "0h00";
            }
            rotTVMep /= 3600000.0;
            rotTVMep = Math.round(rotTVMep * 100.0) / 100.0;
            if (!rotation.isOD) {
                rotation.tempsVolMep = Utils.timetoString(rotTVMep);
            }
            else {
                rotation.tempsVolMep = "0h00";
            }
            calUtc.setTime(rotation.finUTCD);
            calUtc.add(14, 1800000);
            calUtc.add(14, (int)Utils.timeStringtoMs(rotation.dureeRpc));
            if (!rotation.isOD) {
                rotation.reengagementUTC = sdfUtc.format(calUtc.getTime());
                rotation.reengagementUTCD = calUtc.getTime();
            }
            calUtc.setTime(rotation.debutUTCD);
            if (!this.isPNT) {
                if (firstTronconRotation.isMep && firstSV.listTroncon.size() == 1) {
                    calUtc.add(11, 0);
                }
                else {
                    calUtc.add(11, -1);
                }
            }
            else if (this.isBP) {
                calUtc.add(12, -60);
            }
            else if (firstTronconRotation.isMep) {
                calUtc.add(12, -75);
            }
            else if (firstTronconRotation.typeAvion.equals("318") || firstTronconRotation.typeAvion.equals("319") || firstTronconRotation.typeAvion.equals("320") || firstTronconRotation.typeAvion.equals("321")) {
                calUtc.add(12, -90);
            }
            else {
                calUtc.add(12, -105);
            }
            if (!rotation.isOD) {
                rotation.presentationUTCD = calUtc.getTime();
                rotation.presentationUTC = sdfUtc.format(rotation.presentationUTCD);
            }
            double TA = (lastTronconRotation.finUTCD.getTime() - firstTronconRotation.debutUTCD.getTime()) / 3600000.0;
            if (!this.isPNT) {
                if (firstTronconRotation.isMep && firstSV.listTroncon.size() == 1) {
                    TA += 0.0;
                }
                else {
                    ++TA;
                }
                if (lastTronconRotation.isMep && lastSV.listTroncon.size() == 1) {
                    TA += 0.0;
                }
                else {
                    TA += 0.5;
                }
                TA = Math.round(TA * 100.0) / 100.0;
                if (!rotation.isOD) {
                    rotation.tempsAbsence = Utils.timetoString(TA);
                }
                else {
                    rotation.tempsAbsence = Utils.timetoString(0.0);
                }
            }
            else if (this.isPNT) {
                if (firstTronconRotation.isMep) {
                    ++TA;
                }
                else if (firstTronconRotation.typeAvion.equals("318") || firstTronconRotation.typeAvion.equals("319") || firstTronconRotation.typeAvion.equals("320") || firstTronconRotation.typeAvion.equals("321")) {
                    TA += 1.25;
                }
                else {
                    TA += 1.5;
                }
                TA += 0.25;
                TA = Math.round(TA * 100.0) / 100.0;
                if (!rotation.isOD) {
                    rotation.tempsAbsence = Utils.timetoString(TA);
                }
                else {
                    rotation.tempsAbsence = Utils.timetoString(0.0);
                }
            }
            calUtc.setTime(rotation.debutUTCD);
            final int mois_depart = calUtc.get(2) + 1;
            calUtc.setTime(rotation.finUTCD);
            final int mois_arrivee = calUtc.get(2) + 1;
            if (mois_arrivee != mois_depart) {
                rotation.isRotationACheval = true;
            }
            else {
                rotation.isRotationACheval = false;
            }
            for (int j2 = 0; j2 < rotation.listSV.size(); ++j2) {
                final ServiceVol sv6 = rotation.listSV.get(j2);
                sv6.numSV = j2 + 1;
                sv6.numRot = rotation.numRot;
                if (j2 < rotation.listSV.size() - 1) {
                    sv6.isDecoucher = true;
                }
                else {
                    sv6.isDecoucher = false;
                }
                for (int k2 = 0; k2 < sv6.listTroncon.size(); ++k2) {
                    final Troncon troncon2 = sv6.listTroncon.get(k2);
                    troncon2.numTroncon = k2 + 1;
                    troncon2.numSV = sv6.numSV;
                    troncon2.numRot = sv6.numRot;
                    if (k2 == sv6.listTroncon.size() - 1 && sv6.isDecoucher) {
                        troncon2.isDecoucher = true;
                        troncon2.repos = sv6.repos;
                        rotation.listDecouchers.add(troncon2.to);
                    }
                    else {
                        troncon2.isDecoucher = false;
                    }
                    troncon2.fromClair = troncon2.from;
                    troncon2.toClair = troncon2.to;
                    if (escales.containsKey(troncon2.from)) {
                        troncon2.fromClair = escales.get(troncon2.from).nom;
                    }
                    if (escales.containsKey(troncon2.to)) {
                        troncon2.toClair = escales.get(troncon2.to).nom;
                    }
                    if (j2 == rotation.listSV.size() - 1 && k2 == sv6.listTroncon.size() - 1) {
                        troncon2.lagTo = Float.toString(TimeZone.getTimeZone("Europe/Paris").getOffset(troncon2.finUTCD.getTime()) / 3600000.0f);
                    }
                    else if (k2 == sv6.listTroncon.size() - 1) {
                        troncon2.lagTo = rotation.listSV.get(j2 + 1).listTroncon.get(0).lagFrom;
                    }
                    else {
                        troncon2.lagTo = sv6.listTroncon.get(k2 + 1).lagFrom;
                    }
                    troncon2.LAGtoMillis = (int)(Float.parseFloat(troncon2.lagTo) * 60.0f * 60.0f * 1000.0f);
                    if (escales.containsKey(troncon2.to)) {
                        final TimeZone tzTo = TimeZone.getTimeZone(escales.get(troncon2.to).tz);
                        calUtc.setTime(troncon2.finUTCD);
                        troncon2.LAGtoMillis = tzTo.getOffset(calUtc.getTimeInMillis());
                        troncon2.lagTo = String.valueOf(Utils.arrondi(troncon2.LAGtoMillis / 1000.0 / 60.0 / 60.0, 1));
                    }
                    if (k2 != sv6.listTroncon.size() - 1) {
                        final Troncon nextTroncon = sv6.listTroncon.get(k2 + 1);
                        troncon2.tempsEscale = Utils.timetoString((nextTroncon.debutUTCD.getTime() - troncon2.finUTCD.getTime()) / 3600000.0);
                    }
                }
            }
        }
    }
    
    private void chope_DetailsVol_ExportPdaIcs(final String exportPdaIcs) {
        final SimpleDateFormat sdfJour = new SimpleDateFormat("dd/MM/yyyy");
        final SimpleDateFormat sdfHeure = new SimpleDateFormat("HH'h'mm");
        sdfJour.setTimeZone(TimeZone.getTimeZone("UTC"));
        sdfHeure.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String[] icsDecompose = exportPdaIcs.split("BEGIN:VEVENT");
        for (final Rotation rotation : this.listRotation) {
            if (rotation.isOD) {
                continue;
            }
            String cible = "SUMMARY:" + rotation.label + "(.*?)" + "DESCRIPTION(.*?)Date d\u00e9part UTC : " + sdfJour.format(rotation.debutUTCD) + "(.*?)" + sdfHeure.format(rotation.debutUTCD) + "(.*?)END:VEVENT";
            Pattern regex = Pattern.compile(cible, 32);
            String sourceRotation = "";
            String[] array;
            for (int length = (array = icsDecompose).length, i = 0; i < length; ++i) {
                final String source = array[i];
                final Matcher result = regex.matcher(source);
                if (result.find()) {
                    sourceRotation = source;
                    break;
                }
            }
            cible = "CDB : (\\d), OPL : (\\d)";
            regex = Pattern.compile(cible);
            Matcher result = regex.matcher(sourceRotation);
            if (result.find()) {
                rotation.nbCDB = result.group(1);
                rotation.nbOPL = result.group(2);
            }
            cible = "Liste \u00e9quipage de la rotation";
            int indexdebut = sourceRotation.indexOf(cible);
            int indexfin = sourceRotation.length();
            if (indexdebut >= 0) {
                cible = "([A-Z-*]*), ([A-Z-*]*), ([A-Z/*]*)";
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final Peq peq = new Peq();
                    peq.nom = result.group(1);
                    if (!result.group(2).equals("")) {
                        peq.prenom = String.valueOf(result.group(2).substring(0, 1)) + result.group(2).substring(1).toLowerCase();
                    }
                    peq.fab = result.group(3);
                    rotation.listPeqRot.add(peq);
                }
            }
            cible = "Terrain \u00e0 particularit\u00e9";
            indexdebut = sourceRotation.indexOf(cible);
            indexfin = sourceRotation.length();
            if (indexdebut >= 0) {
                cible = "([A-Z]{3}),\\s*Cat\u00e9gorie\\s*:\\s*([A-Z]{1})";
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final Dest_Reco dest = new Dest_Reco();
                    dest.dest = result.group(1);
                    dest.categorie = result.group(2);
                    if (!rotation.listDestReco.contains(dest)) {
                        rotation.listDestReco.add(dest);
                    }
                }
            }
            cible = "D\u00e9gagement \u00e0 particularit\u00e9";
            indexdebut = sourceRotation.indexOf(cible);
            indexfin = sourceRotation.length();
            if (indexdebut >= 0) {
                cible = "([A-Z]{3})\\s*->((\\s*([A-Z]{3})\\s*\\(Cat. ([A-Z]{1})\\))+)";
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final String cibleListRecoDeg = "\\s*([A-Z]{3})\\s*\\(Cat. ([A-Z]{1})\\)";
                    final Pattern regexListRecoDeg = Pattern.compile(cibleListRecoDeg);
                    final Matcher resultListRecoDeg = regexListRecoDeg.matcher(result.group(2));
                    while (resultListRecoDeg.find()) {
                        final Deg_Reco deg = new Deg_Reco();
                        deg.dest = result.group(1);
                        deg.deg = resultListRecoDeg.group(1);
                        deg.categorie = resultListRecoDeg.group(2);
                        if (!rotation.listDegReco.contains(deg)) {
                            rotation.listDegReco.add(deg);
                        }
                    }
                }
            }
            final ArrayList<Indem> listIndem = new ArrayList<Indem>();
            cible = "Indemnit\u00e9s de l'ensemble des escales touch\u00e9es par la rotation";
            indexdebut = sourceRotation.indexOf(cible);
            indexfin = sourceRotation.length();
            if (indexdebut > 0) {
                final String motifA1 = "Escale : [A-Z- ]*\\s\\(([A-Z]{3})\\)";
                final String motifA2 = "\\\\nIndemnit\u00e9 menus frais : (\\d+,?\\d*) EUR";
                final String motifA3 = "\\\\nIndemnit\u00e9 repas : (\\d+,?\\d*) EUR";
                final String motifA3b = "(\\\\nComplement : ESCALE SURETE [A-Z]{3})?";
                final String motifA4 = "\\\\nDate Effet : (\\d\\d/\\d\\d/\\d\\d)";
                final String motifB1 = "Escale : [A-Z- ]*\\s\\(([A-Z]{3})\\)";
                final String motifB2 = "\\\\nChange : 1 [A-Z]{3} \\([A-Z ]*\\) - (\\d+,?\\d*) EUR";
                final String motifB3 = "\\\\nMonnaie locale : ([A-Z]{3} \\([A-Z ]*\\))";
                final String motifB4 = "\\\\nIndemnit\u00e9 menus frais : (\\d+,?\\d*) [A-Z]{3} -> (\\d+,?\\d*) EUR";
                final String motifB5 = "\\\\nIndemnit\u00e9 repas : (\\d+,?\\d*) -> (\\d+,?\\d*) EUR";
                final String motifB5b = "(\\\\nComplement : ESCALE SURETE [A-Z]{3})?";
                final String motifB6 = "\\\\nDate Effet : (\\d\\d/\\d\\d/\\d\\d)";
                cible = String.valueOf(motifA1) + motifA2 + motifA3 + motifA3b + motifA4;
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final Indem indem = new Indem();
                    indem.escale = result.group(1).trim();
                    indem.change = "1";
                    indem.monnaieLoc = "EUR (EURO)";
                    indem.mfLoc = result.group(2);
                    indem.irLoc = result.group(3);
                    indem.mfEur = result.group(2);
                    indem.irEur = result.group(3);
                    indem.dateEffet = result.group(5);
                    try {
                        indem.ir = Double.parseDouble(indem.irEur.replaceAll(",", "."));
                        indem.mf = Double.parseDouble(indem.mfEur.replaceAll(",", "."));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    listIndem.add(indem);
                }
                cible = String.valueOf(motifB1) + motifB2 + motifB3 + motifB4 + motifB5 + motifB5b + motifB6;
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final Indem indem = new Indem();
                    indem.escale = result.group(1);
                    indem.change = result.group(2);
                    indem.monnaieLoc = result.group(3);
                    indem.mfLoc = result.group(4);
                    indem.mfEur = result.group(5);
                    indem.irLoc = result.group(6);
                    indem.irEur = result.group(7);
                    indem.dateEffet = result.group(9);
                    try {
                        indem.ir = Double.parseDouble(indem.irEur.replaceAll(",", "."));
                        indem.mf = Double.parseDouble(indem.mfEur.replaceAll(",", "."));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    listIndem.add(indem);
                }
            }
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    cible = "SUMMARY:" + troncon.numVol + " " + troncon.from + "(.*?)" + troncon.to + "(.*?)" + "Date d\u00e9part UTC : " + sdfJour.format(troncon.debutUTCD) + "(.*?)" + sdfHeure.format(troncon.debutUTCD) + "(.*?)END:VEVENT";
                    regex = Pattern.compile(cible, 32);
                    String sourceTroncon = "";
                    String[] array2;
                    for (int length2 = (array2 = icsDecompose).length, j = 0; j < length2; ++j) {
                        final String source2 = array2[j];
                        result = regex.matcher(source2);
                        if (result.find()) {
                            sourceTroncon = source2;
                            break;
                        }
                    }
                    cible = "Version d'exploitation :(.*?)000";
                    regex = Pattern.compile(cible);
                    result = regex.matcher(sourceTroncon);
                    if (result.find()) {
                        troncon.versionExploitation = result.group(1).trim();
                    }
                    cible = "Liste \u00e9quipage";
                    indexdebut = sourceTroncon.indexOf(cible);
                    indexfin = sourceTroncon.length();
                    if (indexdebut >= 0) {
                        cible = "([A-Z-*]*), ([A-Z-*]*), ([A-Z/]*)";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(sourceTroncon);
                        result.region(indexdebut, indexfin);
                        while (result.find()) {
                            final Peq peq2 = new Peq();
                            peq2.nom = result.group(1);
                            if (!result.group(2).equals("")) {
                                peq2.prenom = String.valueOf(result.group(2).substring(0, 1)) + result.group(2).substring(1).toLowerCase();
                            }
                            peq2.fab = result.group(3);
                            troncon.listPeqTroncon.add(peq2);
                        }
                    }
                    cible = "Embarquement prestations";
                    indexdebut = sourceTroncon.indexOf(cible);
                    indexfin = sourceTroncon.length();
                    if (indexdebut >= 0) {
                        cible = "NumSDV : \\d+, Description : ([\\w '\u00e9\u00e0\u00e8-]+)";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(sourceTroncon);
                        result.region(indexdebut, indexfin);
                        while (result.find()) {
                            final Presta presta = new Presta();
                            presta.type = result.group(1).trim();
                            presta.escaleEmb = troncon.from;
                            troncon.listPresta.add(presta);
                        }
                    }
                    cible = "Escale d\u00e9couch\u00e9e";
                    indexdebut = sourceTroncon.indexOf(cible);
                    indexfin = sourceTroncon.length();
                    if (indexdebut >= 0) {
                        cible = "Nom de l'hotel : ([\\w '\u00e9\u00e0\u00e8-]+)";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(sourceTroncon);
                        result.region(indexdebut, indexfin);
                        if (result.find()) {
                            troncon.hotel = result.group(1).trim();
                        }
                    }
                    for (final Indem indem2 : listIndem) {
                        if (indem2.escale.equals(troncon.from)) {
                            troncon.indemFrom.change = indem2.change;
                            troncon.indemFrom.dateEffet = indem2.dateEffet;
                            troncon.indemFrom.escale = indem2.escale;
                            troncon.indemFrom.irEur = indem2.irEur;
                            troncon.indemFrom.irLoc = indem2.irLoc;
                            troncon.indemFrom.mfEur = indem2.mfEur;
                            troncon.indemFrom.mfLoc = indem2.mfLoc;
                            troncon.indemFrom.monnaieLoc = indem2.monnaieLoc;
                            troncon.indemFrom.ir = indem2.ir;
                            troncon.indemFrom.mf = indem2.mf;
                        }
                        if (indem2.escale.equals(troncon.to)) {
                            troncon.indemTo.change = indem2.change;
                            troncon.indemTo.dateEffet = indem2.dateEffet;
                            troncon.indemTo.escale = indem2.escale;
                            troncon.indemTo.irEur = indem2.irEur;
                            troncon.indemTo.irLoc = indem2.irLoc;
                            troncon.indemTo.mfEur = indem2.mfEur;
                            troncon.indemTo.mfLoc = indem2.mfLoc;
                            troncon.indemTo.monnaieLoc = indem2.monnaieLoc;
                            troncon.indemTo.ir = indem2.ir;
                            troncon.indemTo.mf = indem2.mf;
                        }
                    }
                    for (final Dest_Reco reco : rotation.listDestReco) {
                        if (reco.dest.equals(troncon.to) && !troncon.isMep) {
                            troncon.listRecoDest.add(reco);
                            break;
                        }
                    }
                    for (final Deg_Reco reco2 : rotation.listDegReco) {
                        if (reco2.dest.equals(troncon.to) && !troncon.isMep) {
                            troncon.listRecoDeg.add(reco2);
                        }
                    }
                }
            }
        }
    }
    
    private void chope_DetailsVol_ExportPda(final String exportPda) {
        for (final Rotation rotation : this.listRotation) {
            if (rotation.isOD) {
                continue;
            }
            int indexdebutRot = 0;
            int indexfinRot = 0;
            String cible = "\"" + rotation.label + "([^\"]*?\"){14}" + "[^\"]*?Date d\u00e9part UTC : " + rotation.departUTC + "([^\"]*?\"){1}";
            Pattern regex = Pattern.compile(cible, 32);
            Matcher result = regex.matcher(exportPda);
            if (result.find()) {
                indexdebutRot = result.start();
                indexfinRot = result.end();
            }
            final String sourceRotation = exportPda.substring(indexdebutRot, indexfinRot);
            cible = "CDB : (\\d), OPL : (\\d)";
            regex = Pattern.compile(cible);
            result = regex.matcher(sourceRotation);
            if (result.find()) {
                rotation.nbCDB = result.group(1);
                rotation.nbOPL = result.group(2);
            }
            cible = "Liste \u00e9quipage de la rotation";
            int indexdebut = sourceRotation.indexOf(cible);
            int indexfin = sourceRotation.length();
            if (indexdebut >= 0) {
                cible = "([A-Z-*]*), ([A-Z-*]*), ([A-Z/*]*)";
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final Peq peq = new Peq();
                    peq.nom = result.group(1);
                    if (!result.group(2).equals("")) {
                        peq.prenom = String.valueOf(result.group(2).substring(0, 1)) + result.group(2).substring(1).toLowerCase();
                    }
                    peq.fab = result.group(3);
                    rotation.listPeqRot.add(peq);
                }
            }
            cible = "Terrain \u00e0 particularit\u00e9";
            indexdebut = sourceRotation.indexOf(cible);
            indexfin = sourceRotation.length();
            if (indexdebut >= 0) {
                cible = "([A-Z]{3}),\\s*Cat\u00e9gorie\\s*:\\s*([A-Z]{1})";
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final Dest_Reco dest = new Dest_Reco();
                    dest.dest = result.group(1);
                    dest.categorie = result.group(2);
                    if (!rotation.listDestReco.contains(dest)) {
                        rotation.listDestReco.add(dest);
                    }
                }
            }
            cible = "D\u00e9gagement \u00e0 particularit\u00e9";
            indexdebut = sourceRotation.indexOf(cible);
            indexfin = sourceRotation.length();
            if (indexdebut >= 0) {
                cible = "([A-Z]{3})\\s*->((\\s*([A-Z]{3})\\s*\\(Cat. ([A-Z]{1})\\))+)";
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final String cibleListRecoDeg = "\\s*([A-Z]{3})\\s*\\(Cat. ([A-Z]{1})\\)";
                    final Pattern regexListRecoDeg = Pattern.compile(cibleListRecoDeg);
                    final Matcher resultListRecoDeg = regexListRecoDeg.matcher(result.group(2));
                    while (resultListRecoDeg.find()) {
                        final Deg_Reco deg = new Deg_Reco();
                        deg.dest = result.group(1);
                        deg.deg = resultListRecoDeg.group(1);
                        deg.categorie = resultListRecoDeg.group(2);
                        if (!rotation.listDegReco.contains(deg)) {
                            rotation.listDegReco.add(deg);
                        }
                    }
                }
            }
            final ArrayList<Indem> listIndem = new ArrayList<Indem>();
            cible = "Indemnit\u00e9s de l'ensemble des escales touch\u00e9es par la rotation";
            indexdebut = sourceRotation.indexOf(cible);
            indexfin = sourceRotation.length();
            if (indexdebut > 0) {
                final String motifA1 = "Escale : [A-Z- ]*\\s\\(([A-Z]{3})\\)";
                final String motifA2 = "\\s*Indemnit\u00e9 menus frais : (\\d+,?\\d*) EUR";
                final String motifA3 = "\\s*Indemnit\u00e9 repas : (\\d+,?\\d*) EUR";
                final String motifA3b = "(\\s*Complement : ESCALE SURETE [A-Z]{3})?";
                final String motifA4 = "\\s*Date Effet : (\\d\\d/\\d\\d/\\d\\d)";
                final String motifB1 = "Escale : [A-Z- ]*\\s\\(([A-Z]{3})\\)";
                final String motifB2 = "\\s*Change : 1 [A-Z]{3} \\([A-Z ]*\\) - (\\d+,?\\d*) EUR";
                final String motifB3 = "\\s*Monnaie locale : ([A-Z]{3} \\([A-Z ]*\\))";
                final String motifB4 = "\\s*Indemnit\u00e9 menus frais : (\\d+,?\\d*) [A-Z]{3} -> (\\d+,?\\d*) EUR";
                final String motifB5 = "\\s*Indemnit\u00e9 repas : (\\d+,?\\d*) -> (\\d+,?\\d*) EUR";
                final String motifB5b = "(\\s*Complement : ESCALE SURETE [A-Z]{3})?";
                final String motifB6 = "\\s*Date Effet : (\\d\\d/\\d\\d/\\d\\d)";
                cible = String.valueOf(motifA1) + motifA2 + motifA3 + motifA3b + motifA4;
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final Indem indem = new Indem();
                    indem.escale = result.group(1).trim();
                    indem.change = "1";
                    indem.monnaieLoc = "EUR (EURO)";
                    indem.mfLoc = result.group(2);
                    indem.irLoc = result.group(3);
                    indem.mfEur = result.group(2);
                    indem.irEur = result.group(3);
                    indem.dateEffet = result.group(5);
                    try {
                        indem.ir = Double.parseDouble(indem.irEur.replaceAll(",", "."));
                        indem.mf = Double.parseDouble(indem.mfEur.replaceAll(",", "."));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    listIndem.add(indem);
                }
                cible = String.valueOf(motifB1) + motifB2 + motifB3 + motifB4 + motifB5 + motifB5b + motifB6;
                regex = Pattern.compile(cible);
                result = regex.matcher(sourceRotation);
                result.region(indexdebut, indexfin);
                while (result.find()) {
                    final Indem indem = new Indem();
                    indem.escale = result.group(1);
                    indem.change = result.group(2);
                    indem.monnaieLoc = result.group(3);
                    indem.mfLoc = result.group(4);
                    indem.mfEur = result.group(5);
                    indem.irLoc = result.group(6);
                    indem.irEur = result.group(7);
                    indem.dateEffet = result.group(9);
                    try {
                        indem.ir = Double.parseDouble(indem.irEur.replaceAll(",", "."));
                        indem.mf = Double.parseDouble(indem.mfEur.replaceAll(",", "."));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    listIndem.add(indem);
                }
            }
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    int indexdebutTroncon = 0;
                    int indexfinTroncon = 0;
                    cible = "\"" + troncon.numVol + "([^\"]*?\"){14}" + "[^\"]*?Date d\u00e9part UTC : " + troncon.departUTC + "([^\"]*?\"){1}";
                    regex = Pattern.compile(cible, 32);
                    result = regex.matcher(exportPda);
                    if (result.find()) {
                        indexdebutTroncon = result.start();
                        indexfinTroncon = result.end();
                    }
                    final String sourceTroncon = exportPda.substring(indexdebutTroncon, indexfinTroncon);
                    cible = "Version d'exploitation :(.*?)000";
                    regex = Pattern.compile(cible);
                    result = regex.matcher(sourceTroncon);
                    if (result.find()) {
                        troncon.versionExploitation = result.group(1).trim();
                    }
                    cible = "Liste \u00e9quipage";
                    indexdebut = sourceTroncon.indexOf(cible);
                    indexfin = sourceTroncon.length();
                    if (indexdebut >= 0) {
                        cible = "([A-Z-*]*), ([A-Z-*]*), ([A-Z/]*)";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(sourceTroncon);
                        result.region(indexdebut, indexfin);
                        while (result.find()) {
                            final Peq peq2 = new Peq();
                            peq2.nom = result.group(1);
                            if (!result.group(2).equals("")) {
                                peq2.prenom = String.valueOf(result.group(2).substring(0, 1)) + result.group(2).substring(1).toLowerCase();
                            }
                            peq2.fab = result.group(3);
                            troncon.listPeqTroncon.add(peq2);
                        }
                    }
                    cible = "Embarquement prestations";
                    indexdebut = sourceTroncon.indexOf(cible);
                    indexfin = sourceTroncon.length();
                    if (indexdebut >= 0) {
                        cible = "NumSDV : \\d+, Description : ([\\w '\u00e9\u00e0\u00e8-]+)";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(sourceTroncon);
                        result.region(indexdebut, indexfin);
                        while (result.find()) {
                            final Presta presta = new Presta();
                            presta.type = result.group(1).trim();
                            presta.escaleEmb = troncon.from;
                            troncon.listPresta.add(presta);
                        }
                    }
                    cible = "Escale d\u00e9couch\u00e9e";
                    indexdebut = sourceTroncon.indexOf(cible);
                    indexfin = sourceTroncon.length();
                    if (indexdebut >= 0) {
                        cible = "Nom de l'hotel : ([\\w '\u00e9\u00e0\u00e8-]+)";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(sourceTroncon);
                        result.region(indexdebut, indexfin);
                        if (result.find()) {
                            troncon.hotel = result.group(1).trim();
                        }
                    }
                    for (final Indem indem2 : listIndem) {
                        if (indem2.escale.equals(troncon.from)) {
                            troncon.indemFrom.change = indem2.change;
                            troncon.indemFrom.dateEffet = indem2.dateEffet;
                            troncon.indemFrom.escale = indem2.escale;
                            troncon.indemFrom.irEur = indem2.irEur;
                            troncon.indemFrom.irLoc = indem2.irLoc;
                            troncon.indemFrom.mfEur = indem2.mfEur;
                            troncon.indemFrom.mfLoc = indem2.mfLoc;
                            troncon.indemFrom.monnaieLoc = indem2.monnaieLoc;
                            troncon.indemFrom.ir = indem2.ir;
                            troncon.indemFrom.mf = indem2.mf;
                        }
                        if (indem2.escale.equals(troncon.to)) {
                            troncon.indemTo.change = indem2.change;
                            troncon.indemTo.dateEffet = indem2.dateEffet;
                            troncon.indemTo.escale = indem2.escale;
                            troncon.indemTo.irEur = indem2.irEur;
                            troncon.indemTo.irLoc = indem2.irLoc;
                            troncon.indemTo.mfEur = indem2.mfEur;
                            troncon.indemTo.mfLoc = indem2.mfLoc;
                            troncon.indemTo.monnaieLoc = indem2.monnaieLoc;
                            troncon.indemTo.ir = indem2.ir;
                            troncon.indemTo.mf = indem2.mf;
                        }
                    }
                    for (final Dest_Reco reco : rotation.listDestReco) {
                        if (reco.dest.equals(troncon.to) && !troncon.isMep) {
                            troncon.listRecoDest.add(reco);
                            break;
                        }
                    }
                    for (final Deg_Reco reco2 : rotation.listDegReco) {
                        if (reco2.dest.equals(troncon.to) && !troncon.isMep) {
                            troncon.listRecoDeg.add(reco2);
                        }
                    }
                }
            }
        }
    }
    
    private void chope_PartSabVol_PageMensuelle(final String pageMensuelle) {
        final ArrayList<ArrayList<ArrayList<String>>> pageMensuelleDEC = this.decomposePageMensuelle(pageMensuelle);
        for (final ArrayList<ArrayList<String>> journee : pageMensuelleDEC) {
            for (final ArrayList<String> ligne : journee) {
                boolean flag = false;
                String numRot = null;
                String dateRot = null;
                String part = null;
                String sab = null;
                String cible = "numrot=(.*?)&date=(.*?)&";
                Pattern regex = Pattern.compile(cible);
                Matcher result = regex.matcher(ligne.get(3));
                if (result.find()) {
                    numRot = result.group(1).trim();
                    dateRot = result.group(2).trim();
                    cible = "title=\"(.*?)\"";
                    regex = Pattern.compile(cible);
                    result = regex.matcher(ligne.get(4));
                    if (result.find()) {
                        part = result.group(1).trim();
                        flag = true;
                    }
                    result = regex.matcher(ligne.get(5));
                    if (result.find()) {
                        sab = result.group(1).trim();
                        flag = true;
                    }
                }
                if (flag) {
                    for (final Rotation rot : this.listRotation) {
                        if (rot.label.equals(numRot) && rot.departUTC.contains(dateRot)) {
                            rot.particularite = part;
                            rot.sab = sab;
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void chope_ActivitesSol_PageMensuelle(final String pageMensuelle) {
        final SimpleDateFormat sdfParis1 = new SimpleDateFormat("dd/MM/yyyy '\u00e0' HH'h'mm");
        sdfParis1.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final SimpleDateFormat sdfParis2 = new SimpleDateFormat("dd/MM/yyyy");
        sdfParis2.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calDeb = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calFin = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final ArrayList<ArrayList<ArrayList<String>>> pageMensuelleDEC = this.decomposePageMensuelle(pageMensuelle);
        for (final ArrayList<ArrayList<String>> journee : pageMensuelleDEC) {
            int compteurLignesVides = 0;
            BlocActivite lastBlocJournee = null;
            String jour = "";
            String mois = "";
            String annee = "";
            for (final ArrayList<String> ligne : journee) {
                ActiviteSol act = null;
                BlocActivite bloc = null;
                boolean isActRot = false;
                String colonne = ligne.get(0);
                final String cibleJour = "\\w{2} (\\d{2})(/\\d{2})?";
                final Pattern regexJour = Pattern.compile(cibleJour);
                final Matcher resultJour = regexJour.matcher(colonne);
                if (resultJour.find()) {
                    if (resultJour.group(2) != null) {
                        jour = resultJour.group(1);
                        mois = resultJour.group(2).substring(1);
                        if (this.moisInt == 12 && mois.equals("01")) {
                            annee = String.valueOf(this.anneeInt + 1);
                        }
                        else if (this.moisInt == 1 && mois.equals("12")) {
                            annee = String.valueOf(this.anneeInt - 1);
                        }
                        else {
                            annee = String.valueOf(this.anneeInt);
                        }
                    }
                    else {
                        jour = resultJour.group(1);
                        mois = this.moisNum;
                        annee = String.valueOf(this.anneeInt);
                    }
                }
                colonne = ligne.get(1);
                final String cibleBloc = "event=.*?dateBlocDeb=\\w{2} (\\d{2}/\\d{2})(\\d{2}h\\d{2})&dateBlocFin=\\w{2} (\\d{2}/\\d{2})(\\d{2}h\\d{2})&codestage=(\\w{3})&libelstage=(.*?)&codeconfstage=.*?&numact=.*?&dateDebCompl=\\d{2}/\\d{2}/(\\d{4})&dateFinCompl=\\d{2}/\\d{2}/(\\d{4})";
                final Pattern regexBloc = Pattern.compile(cibleBloc);
                final Matcher resultBloc = regexBloc.matcher(colonne);
                if (resultBloc.find()) {
                    bloc = new BlocActivite();
                    bloc.code = resultBloc.group(5);
                    bloc.label = resultBloc.group(6).trim();
                    bloc.debut = String.valueOf(resultBloc.group(1)) + "/" + resultBloc.group(7) + " \u00e0 " + resultBloc.group(2);
                    bloc.fin = String.valueOf(resultBloc.group(3)) + "/" + resultBloc.group(8) + " \u00e0 " + resultBloc.group(4);
                    if (bloc.code.equals("SST")) {
                        bloc.categorie = "Bloc stage";
                        if (bloc.label.equals("")) {
                            bloc.label = "BLOC STAGE";
                        }
                    }
                    else if (bloc.code.equals("MBR")) {
                        bloc.categorie = "Bloc r\u00e9serve";
                        if (bloc.label.equals("")) {
                            bloc.label = "BLOC RESERVE";
                        }
                    }
                    else {
                        bloc.categorie = "Bloc activit\u00e9s";
                        if (bloc.label.equals("")) {
                            bloc.label = "BLOC ACTIVITES";
                        }
                    }
                    lastBlocJournee = bloc;
                }
                colonne = ligne.get(2);
                int typeAct = 0;
                final String cibleActivite1 = "event=.*?dateBlocDeb=\\w{2} (\\d{2}/\\d{2})(\\d{2}h\\d{2})&dateBlocFin=\\w{2} (\\d{2}/\\d{2})(\\d{2}h\\d{2})&codestage=(\\w{3})&libelstage=(.*?)&codeconfstage=.*?&numact=.*?&dateDebCompl=\\d{2}/\\d{2}/(\\d{4})&dateFinCompl=\\d{2}/\\d{2}/(\\d{4})(&lieuReserve=(.*?))?\"";
                final Pattern regexActivite1 = Pattern.compile(cibleActivite1);
                final Matcher resultActivite1 = regexActivite1.matcher(colonne);
                final String cibleActivite2 = "title=\"Code activit\u00e9 : (\\w{3})\"";
                final Pattern regexActivite2 = Pattern.compile(cibleActivite2);
                final Matcher resultActivite2 = regexActivite2.matcher(colonne);
                final String cibleRotation = "event=.*?&numrot=";
                final Pattern regexRotation = Pattern.compile(cibleRotation);
                final Matcher resultRotation = regexRotation.matcher(colonne);
                if (resultActivite1.find()) {
                    typeAct = 1;
                    act = new ActiviteSol();
                    act.code = resultActivite1.group(5);
                    act.jour = String.valueOf(jour) + "/" + mois + "/" + annee;
                    act.debut = String.valueOf(resultActivite1.group(1)) + "/" + resultActivite1.group(7) + " \u00e0 " + resultActivite1.group(2);
                    act.fin = String.valueOf(resultActivite1.group(3)) + "/" + resultActivite1.group(8) + " \u00e0 " + resultActivite1.group(4);
                    act.label = resultActivite1.group(6).trim();
                    if (resultActivite1.group(10) != null && !resultActivite1.group(10).trim().equals("")) {
                        act.lieu = resultActivite1.group(10).trim();
                        if (act.lieu.equals("ROISSY")) {
                            act.lieu = "CDG";
                        }
                        else if (act.lieu.equals("ORLY")) {
                            act.lieu = "ORY";
                        }
                    }
                    try {
                        act.debutUTCD = sdfParis1.parse(act.debut);
                        act.finUTCD = sdfParis1.parse(act.fin);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (ligne.size() == 12) {
                        String deb = "&nbsp;";
                        colonne = ligne.get(3);
                        String cible = ">(.*?)(\\d{2}h\\d{2})(.*?)</";
                        Pattern regex = Pattern.compile(cible, 32);
                        Matcher result = regex.matcher(colonne);
                        if (result.find()) {
                            deb = result.group(2).trim();
                        }
                        if (deb.equals("&nbsp;") || deb.equals("")) {
                            deb = String.valueOf(act.jour) + " \u00e0 00h00";
                        }
                        else {
                            deb = String.valueOf(act.jour) + " \u00e0 " + deb;
                        }
                        String fin = "&nbsp;";
                        colonne = ligne.get(5);
                        cible = ">(.*?)(\\d{2}h\\d{2})(.*?)</";
                        regex = Pattern.compile(cible, 32);
                        result = regex.matcher(colonne);
                        if (result.find()) {
                            fin = result.group(2).trim();
                        }
                        if (fin.equals("&nbsp;") || fin.equals("")) {
                            fin = String.valueOf(act.jour) + " \u00e0 23h59";
                        }
                        else {
                            fin = String.valueOf(act.jour) + " \u00e0 " + fin;
                        }
                        String label = "";
                        colonne = ligne.get(4);
                        cible = ">(.*?)</";
                        regex = Pattern.compile(cible, 32);
                        result = regex.matcher(colonne);
                        if (result.find()) {
                            label = result.group(1).trim();
                        }
                        try {
                            calDeb.setTime(sdfParis1.parse(deb));
                            calFin.setTime(sdfParis1.parse(fin));
                            if (calDeb.after(calFin)) {
                                calFin.add(5, 1);
                            }
                            if (calDeb.getTimeInMillis() < act.debutUTCD.getTime() || calFin.getTimeInMillis() > act.finUTCD.getTime()) {
                                act.label = label;
                                act.lieu = null;
                                act.debutUTCD = calDeb.getTime();
                                act.finUTCD = calFin.getTime();
                                act.debut = sdfParis1.format(act.debutUTCD);
                                act.fin = sdfParis1.format(act.finUTCD);
                            }
                        }
                        catch (ParseException e2) {
                            e2.printStackTrace();
                        }
                    }
                    else {
                        ligne.size();
                    }
                }
                else if (resultActivite2.find()) {
                    if (ligne.size() == 10) {
                        typeAct = 2;
                        act = new ActiviteSol();
                        act.code = resultActivite2.group(1);
                        act.jour = String.valueOf(jour) + "/" + mois + "/" + annee;
                        act.debut = String.valueOf(act.jour) + " \u00e0 00h00";
                        act.fin = String.valueOf(act.jour) + " \u00e0 23h59";
                        colonne = ligne.get(3);
                        final String cible = "colspan=\"3\">(.*?)</";
                        final Pattern regex = Pattern.compile(cible, 32);
                        final Matcher result = regex.matcher(colonne);
                        if (result.find()) {
                            act.label = result.group(1).trim();
                        }
                        try {
                            act.debutUTCD = sdfParis1.parse(act.debut);
                            act.finUTCD = sdfParis1.parse(act.fin);
                        }
                        catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    else if (ligne.size() == 12) {
                        typeAct = 3;
                        act = new ActiviteSol();
                        act.code = resultActivite2.group(1);
                        act.jour = String.valueOf(jour) + "/" + mois + "/" + annee;
                        String deb = "&nbsp;";
                        colonne = ligne.get(3);
                        String cible = ">(.*?)(\\d{2}h\\d{2})(.*?)</";
                        Pattern regex = Pattern.compile(cible, 32);
                        Matcher result = regex.matcher(colonne);
                        if (result.find()) {
                            deb = result.group(2).trim();
                        }
                        if (deb.equals("&nbsp;") || deb.equals("")) {
                            deb = String.valueOf(act.jour) + " \u00e0 00h00";
                        }
                        else {
                            deb = String.valueOf(act.jour) + " \u00e0 " + deb;
                        }
                        String fin = "&nbsp;";
                        colonne = ligne.get(5);
                        cible = ">(.*?)(\\d{2}h\\d{2})(.*?)</";
                        regex = Pattern.compile(cible, 32);
                        result = regex.matcher(colonne);
                        if (result.find()) {
                            fin = result.group(2).trim();
                        }
                        if (fin.equals("&nbsp;") || fin.equals("")) {
                            fin = String.valueOf(act.jour) + " \u00e0 23h59";
                        }
                        else {
                            fin = String.valueOf(act.jour) + " \u00e0 " + fin;
                        }
                        String label = "";
                        colonne = ligne.get(4);
                        cible = ">(.*?)</";
                        regex = Pattern.compile(cible, 32);
                        result = regex.matcher(colonne);
                        if (result.find()) {
                            label = result.group(1).trim();
                        }
                        act.debut = deb;
                        act.fin = fin;
                        act.label = label;
                        try {
                            act.debutUTCD = sdfParis1.parse(act.debut);
                            act.finUTCD = sdfParis1.parse(act.fin);
                        }
                        catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        calDeb.setTime(act.debutUTCD);
                        calFin.setTime(act.finUTCD);
                        if (calDeb.after(calFin)) {
                            calFin.add(5, 1);
                            act.finUTCD = calFin.getTime();
                            act.fin = sdfParis1.format(act.finUTCD);
                        }
                    }
                }
                if (resultRotation.find()) {
                    isActRot = true;
                    colonne = ligne.get(4);
                    if (colonne.indexOf("RESERVE") >= 0 && act == null) {
                        act = new ActiviteSol();
                        act.code = "RBR";
                        act.label = "RESERVE";
                        act.jour = String.valueOf(jour) + "/" + mois + "/" + annee;
                        final String cible = ">(.*?)(\\d{2}h\\d{2})(.*?)</";
                        final Pattern regex = Pattern.compile(cible, 32);
                        colonne = ligne.get(3);
                        String hdeb = "&nbsp;";
                        Matcher result = regex.matcher(colonne);
                        if (result.find()) {
                            hdeb = result.group(2).trim();
                        }
                        if (hdeb.equals("&nbsp;") || hdeb.equals("")) {
                            act.debut = String.valueOf(act.jour) + " \u00e0 00h00";
                        }
                        else {
                            act.debut = String.valueOf(act.jour) + " \u00e0 " + hdeb;
                        }
                        colonne = ligne.get(5);
                        String hfin = "&nbsp;";
                        result = regex.matcher(colonne);
                        if (result.find()) {
                            hfin = result.group(2).trim();
                        }
                        if (hfin.equals("&nbsp;") || hfin.equals("")) {
                            act.fin = String.valueOf(act.jour) + " \u00e0 23h59";
                        }
                        else {
                            act.fin = String.valueOf(act.jour) + " \u00e0 " + hfin;
                        }
                        try {
                            act.debutUTCD = sdfParis1.parse(act.debut);
                            act.finUTCD = sdfParis1.parse(act.fin);
                        }
                        catch (Exception e5) {
                            e5.printStackTrace();
                        }
                    }
                }
                if (act != null) {
                    if (bloc != null) {
                        act.bloc = bloc;
                        act.isBlocActivite = true;
                    }
                    boolean isDivers = false;
                    if (act.code.equals("PRB") && typeAct == 2) {
                        act.categorie = "Repos";
                        act.isH24 = true;
                        act.isDispo = true;
                        act.isCredite = false;
                    }
                    else if (act.code.equals("MAD") && typeAct == 1) {
                        act.categorie = "Repos additionnel";
                        act.isH24 = false;
                        act.isDispo = true;
                        act.isCredite = false;
                    }
                    else if (act.code.equals("MDV") && typeAct == 3) {
                        act.categorie = "Sans solde";
                        act.isH24 = true;
                        act.isDispo = true;
                        act.isCredite = false;
                    }
                    else if (act.code.equals("MAS") && typeAct == 3) {
                        act.categorie = "Arr\u00eat maladie";
                        act.isH24 = true;
                        act.isDispo = true;
                        act.isCredite = false;
                    }
                    else if (act.code.equals("MCA") && typeAct == 2) {
                        act.categorie = "Cong\u00e9s";
                        act.isH24 = true;
                        act.isDispo = true;
                        act.isCredite = false;
                        final int index1 = act.label.indexOf("\"") + 1;
                        final int index2 = act.label.indexOf("\"", index1);
                        if (index1 >= 0 && index2 >= 0) {
                            act.label = act.label.substring(index1, index2).trim();
                        }
                    }
                    else if (act.code.equals("MCE") && typeAct == 3) {
                        act.categorie = "Cong\u00e9s";
                        act.isH24 = true;
                        act.isDispo = true;
                        act.isCredite = false;
                    }
                    else if (act.code.equals("MCP") && typeAct == 3) {
                        act.categorie = "Contrainte planning";
                        act.isH24 = false;
                        act.isDispo = false;
                        act.isCredite = false;
                    }
                    else if (act.code.equals("MIM") && act.label.contains("REPRES")) {
                        act.code = "SYN";
                        act.categorie = "Journ\u00e9e syndicale";
                        act.isH24 = true;
                        act.isDispo = true;
                        act.isCredite = true;
                    }
                    else if (act.code.equals("MIM")) {
                        act.categorie = "Autre sol";
                        act.isH24 = false;
                        act.isDispo = false;
                        act.isCredite = true;
                    }
                    else if (act.code.equals("MVM") && (typeAct == 3 || typeAct == 1)) {
                        act.categorie = "Visite m\u00e9dicale";
                        act.isH24 = false;
                        act.isDispo = false;
                        act.isCredite = true;
                    }
                    else if (act.code.equals("RBR") && (typeAct == 1 || typeAct == 0)) {
                        act.categorie = "R\u00e9serve";
                        act.isH24 = false;
                        act.isDispo = false;
                        act.isCredite = true;
                        act.label = "RESERVE";
                    }
                    else if (act.code.equals("SST") && typeAct == 1) {
                        act.categorie = "Stage ECP";
                        act.isH24 = false;
                        act.isDispo = false;
                        act.isCredite = true;
                    }
                    else if (act.code.equals("MCI") && typeAct == 1) {
                        act.categorie = "Instruction";
                        act.isH24 = false;
                        act.isDispo = false;
                        act.isCredite = true;
                    }
                    else {
                        act.categorie = "Divers";
                        final ActiviteSol activiteSol = act;
                        activiteSol.label = String.valueOf(activiteSol.label) + " (inconnu) ";
                        final ActiviteSol activiteSol2 = act;
                        activiteSol2.label = String.valueOf(activiteSol2.label) + String.valueOf(typeAct);
                        isDivers = true;
                    }
                    calDeb.setTime(act.debutUTCD);
                    calFin.setTime(act.finUTCD);
                    if (calDeb.get(2) + 1 == this.moisInt || calFin.get(2) + 1 == this.moisInt) {
                        if (!this.listSol.contains(act) && !act.label.contains("&nbsp;") && !isDivers) {
                            this.listSol.add(act);
                        }
                        else if (!this.listSol.contains(act) && !act.label.contains("&nbsp;") && isDivers && ChopeCrew.options.isSolTotal) {
                            this.listSol.add(act);
                        }
                    }
                }
                if (act == null && !isActRot) {
                    ++compteurLignesVides;
                }
            }
            if (compteurLignesVides == journee.size()) {
                final ActiviteSol act2 = new ActiviteSol();
                act2.code = "DSP";
                act2.jour = String.valueOf(jour) + "/" + mois + "/" + annee;
                act2.label = "DISPERSION";
                act2.categorie = "Dispersion";
                act2.debut = String.valueOf(act2.jour) + " \u00e0 00h00";
                act2.fin = String.valueOf(act2.jour) + " \u00e0 23h59";
                act2.isH24 = true;
                act2.isDispo = true;
                act2.isCredite = false;
                try {
                    act2.debutUTCD = sdfParis1.parse(act2.debut);
                    act2.finUTCD = sdfParis1.parse(act2.fin);
                }
                catch (Exception e6) {
                    e6.printStackTrace();
                }
                if (lastBlocJournee != null) {
                    act2.bloc = lastBlocJournee;
                    if (act2.bloc.code.equals("MBR")) {
                        act2.label = "DISPERSION (Bloc r\u00e9serve)";
                    }
                    else if (act2.bloc.code.equals("SST")) {
                        act2.label = "DISPERSION (Bloc stage)";
                    }
                    else {
                        act2.label = "DISPERSION (Bloc activit\u00e9s)";
                    }
                    act2.isBlocActivite = true;
                    act2.isCredite = true;
                }
                if (this.listSol.contains(act2)) {
                    continue;
                }
                calDeb.setTime(act2.debutUTCD);
                if (calDeb.get(2) + 1 != this.moisInt) {
                    continue;
                }
                this.listSol.add(act2);
            }
        }
        for (int sizeInit = this.listSol.size(), i = 0; i < sizeInit; ++i) {
            final ActiviteSol act3 = this.listSol.get(i);
            act3.label = act3.label.replaceAll("<.*?>", "");
            if (act3.isH24) {
                calDeb.setTime(act3.debutUTCD);
                calFin.setTime(act3.finUTCD);
                if (calDeb.get(11) > 11) {
                    calDeb.set(11, 0);
                    calDeb.set(12, 0);
                    calDeb.set(13, 0);
                    calDeb.set(14, 0);
                    calDeb.add(5, 1);
                }
                else {
                    calDeb.set(11, 0);
                    calDeb.set(12, 0);
                    calDeb.set(13, 0);
                    calDeb.set(14, 0);
                }
                if (calFin.get(11) < 12) {
                    calFin.set(11, 0);
                    calFin.set(12, 0);
                    calFin.set(13, 0);
                    calFin.set(14, 0);
                }
                else {
                    calFin.set(11, 0);
                    calFin.set(12, 0);
                    calFin.set(13, 0);
                    calFin.set(14, 0);
                    calFin.add(5, 1);
                }
                final int deltaOffset = calFin.get(15) + calFin.get(16) - calDeb.get(15) - calDeb.get(16);
                for (int nbjours = (int)(calFin.getTimeInMillis() - calDeb.getTimeInMillis() + deltaOffset) / 86400000, j = 0; j < nbjours; ++j) {
                    final ActiviteSol newAct = new ActiviteSol();
                    newAct.code = act3.code;
                    newAct.jour = act3.jour;
                    newAct.debut = act3.debut;
                    newAct.fin = act3.fin;
                    newAct.label = act3.label;
                    newAct.lieu = act3.lieu;
                    newAct.debutUTCD = act3.debutUTCD;
                    newAct.finUTCD = act3.finUTCD;
                    newAct.bloc = act3.bloc;
                    newAct.isBlocActivite = act3.isBlocActivite;
                    newAct.categorie = act3.categorie;
                    newAct.isH24 = act3.isH24;
                    newAct.isDispo = act3.isDispo;
                    newAct.isCredite = act3.isCredite;
                    newAct.debutUTCD = calDeb.getTime();
                    final int moisNewAct = calDeb.get(2) + 1;
                    calDeb.add(5, 1);
                    calDeb.add(12, -1);
                    newAct.finUTCD = calDeb.getTime();
                    calDeb.add(12, 1);
                    newAct.debut = sdfParis1.format(newAct.debutUTCD);
                    newAct.fin = sdfParis1.format(newAct.finUTCD);
                    newAct.jour = sdfParis2.format(newAct.debutUTCD);
                    if (moisNewAct == this.moisInt) {
                        this.listSol.add(newAct);
                    }
                }
                this.listSol.remove(i);
                --i;
                --sizeInit;
            }
        }
        Collections.sort(this.listSol);
    }
    
    private void chope_DetailsStage_PageStage(final String pageStage) {
        String jour = "";
        String cible = "Stage du \\w{2} (\\d{2}/\\d{2})";
        Pattern regex = Pattern.compile(cible);
        Matcher result = regex.matcher(pageStage);
        if (result.find()) {
            jour = result.group(1).trim();
        }
        else {
            cible = "Stage du (\\d{2}/\\d{2}/\\d{4})";
            regex = Pattern.compile(cible);
            result = regex.matcher(pageStage);
            if (result.find()) {
                jour = result.group(1).trim().substring(0, 5);
            }
        }
        String label = "";
        cible = "Intitul\u00e9 du stage :";
        if (pageStage.indexOf(cible) >= 0) {
            final int indexdebut = pageStage.indexOf("<strong>", pageStage.indexOf(cible)) + "<strong>".length();
            final int indexfin = pageStage.indexOf("</strong>", indexdebut);
            label = pageStage.substring(indexdebut, indexfin).trim();
        }
        String maj = "";
        cible = "Date M\u00e0J :";
        if (pageStage.indexOf(cible) >= 0) {
            final int indexdebut = pageStage.indexOf("<strong>", pageStage.indexOf(cible)) + "<strong>".length();
            final int indexfin = pageStage.indexOf("</strong>", indexdebut);
            maj = pageStage.substring(indexdebut, indexfin).trim();
        }
        String lieu = "";
        cible = "Lieu :";
        if (pageStage.indexOf(cible) >= 0) {
            final int indexdebut = pageStage.indexOf("<strong>", pageStage.indexOf(cible)) + "<strong>".length();
            final int indexfin = pageStage.indexOf("</strong>", indexdebut);
            lieu = pageStage.substring(indexdebut, indexfin).trim();
        }
        String salle = "";
        cible = "Salle :";
        if (pageStage.indexOf(cible) >= 0) {
            final int indexdebut = pageStage.indexOf("<strong>", pageStage.indexOf(cible)) + "<strong>".length();
            final int indexfin = pageStage.indexOf("</strong>", indexdebut);
            salle = pageStage.substring(indexdebut, indexfin).trim();
        }
        String comment = "";
        cible = "Commentaire";
        if (pageStage.indexOf(cible) >= 0) {
            final int indexdebut = pageStage.indexOf("<td>", pageStage.indexOf(cible)) + "<td>".length();
            final int indexfin = pageStage.indexOf("</td>", indexdebut);
            if (indexdebut >= 0 && indexfin >= 0) {
                comment = pageStage.substring(indexdebut, indexfin).trim();
            }
        }
        final ArrayList<Participant> listParticipant = new ArrayList<Participant>();
        cible = "<tr Class=\"ligneStandard\">";
        regex = Pattern.compile(cible);
        result = regex.matcher(pageStage);
        while (result.find()) {
            final Participant p = new Participant();
            int compteur = 0;
            final String cibleParticipant = ">(.*?)</td>";
            final Pattern regexParticipant = Pattern.compile(cibleParticipant);
            final Matcher resultParticipant = regexParticipant.matcher(pageStage);
            resultParticipant.region(result.start(), pageStage.length());
            while (resultParticipant.find()) {
                if (++compteur == 1) {
                    p.nom = resultParticipant.group(1).trim();
                }
                else if (compteur == 2) {
                    p.prenom = resultParticipant.group(1).trim();
                    if (p.prenom.length() <= 0) {
                        continue;
                    }
                    p.prenom = String.valueOf(p.prenom.substring(0, 1)) + p.prenom.substring(1).toLowerCase();
                }
                else if (compteur == 3) {
                    p.matricule = resultParticipant.group(1).trim();
                }
                else if (compteur == 4) {
                    p.fab = resultParticipant.group(1).trim();
                }
                else if (compteur == 5) {
                    p.statut = resultParticipant.group(1).trim();
                }
                else {
                    if (compteur != 6) {
                        break;
                    }
                    p.affectation = resultParticipant.group(1).trim();
                }
            }
            listParticipant.add(p);
        }
        if (jour == null || label == null) {
            return;
        }
        for (final ActiviteSol act : this.listSol) {
            if (jour.equals(act.jour.substring(0, 5)) && label.contains(act.label)) {
                if (!maj.equals("")) {
                    act.maj = maj;
                }
                if (!lieu.equals("")) {
                    act.lieu = lieu;
                }
                if (!salle.equals("")) {
                    act.salle = salle;
                }
                if (!comment.equals("")) {
                    act.commentaire = comment;
                }
                act.listParticipant = listParticipant;
            }
        }
    }
    
    private void chope_MoisAnneeGeneralites_XML(final String planningXML) {
        String cible = "mois\\s*=\\s*\"\\s*(\\d{2})/(\\d{4})\\s*\"";
        Pattern regex = Pattern.compile(cible);
        Matcher result = regex.matcher(planningXML);
        if (result.find()) {
            this.anneeInt = Integer.parseInt(result.group(2));
            this.moisInt = Integer.parseInt(result.group(1));
            if (this.moisInt == 1) {
                this.moisNum = "01";
                this.moisLit = "Janvier";
            }
            else if (this.moisInt == 2) {
                this.moisNum = "02";
                this.moisLit = "F\u00e9vrier";
            }
            else if (this.moisInt == 3) {
                this.moisNum = "03";
                this.moisLit = "Mars";
            }
            else if (this.moisInt == 4) {
                this.moisNum = "04";
                this.moisLit = "Avril";
            }
            else if (this.moisInt == 5) {
                this.moisNum = "05";
                this.moisLit = "Mai";
            }
            else if (this.moisInt == 6) {
                this.moisNum = "06";
                this.moisLit = "Juin";
            }
            else if (this.moisInt == 7) {
                this.moisNum = "07";
                this.moisLit = "Juillet";
            }
            else if (this.moisInt == 8) {
                this.moisNum = "08";
                this.moisLit = "Ao\u00fbt";
            }
            else if (this.moisInt == 9) {
                this.moisNum = "09";
                this.moisLit = "Septembre";
            }
            else if (this.moisInt == 10) {
                this.moisNum = "10";
                this.moisLit = "Octobre";
            }
            else if (this.moisInt == 11) {
                this.moisNum = "11";
                this.moisLit = "Novembre";
            }
            else if (this.moisInt == 12) {
                this.moisNum = "12";
                this.moisLit = "D\u00e9cembre";
            }
        }
        cible = "isPnt\\s*=\\s*\"\\s*(\\w+)\\s*\"";
        regex = Pattern.compile(cible);
        result = regex.matcher(planningXML);
        if (result.find()) {
            this.isPNT = Boolean.parseBoolean(result.group(1).trim());
        }
        cible = "deltaMois\\s*=\\s*\"\\s*(\\d{1})\\s*\"";
        regex = Pattern.compile(cible);
        result = regex.matcher(planningXML);
        if (result.find()) {
            this.deltaMois = Integer.parseInt(result.group(1).trim());
        }
        cible = "matricule\\s*=\\s*\"\\s*(\\w+)\\s*\"";
        regex = Pattern.compile(cible);
        result = regex.matcher(planningXML);
        if (result.find()) {
            this.matricule = result.group(1).trim();
        }
        cible = "isFlash\\s*=\\s*\"\\s*(\\w+)\\s*\"";
        regex = Pattern.compile(cible);
        result = regex.matcher(planningXML);
        if (result.find()) {
            this.isFlash = Boolean.parseBoolean(result.group(1).trim());
        }
    }
    
    private void chope_ActivitesVol_XML(final String planningXML) {
        final SimpleDateFormat sdfUtc = new SimpleDateFormat("dd/MM/yyyy '\u00e0' HH'h'mm");
        sdfUtc.setTimeZone(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar cal_utc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final HashMap<String, Escale> dbEscales = this.importBaseEscales();
        try {
            final String cibleRot = "<rotation>";
            final Pattern regexRot = Pattern.compile(cibleRot);
            final Matcher resultRot = regexRot.matcher(planningXML);
            while (resultRot.find()) {
                final int indexDebutRotation = resultRot.end();
                final int indexFinRotation = planningXML.indexOf("</rotation>", indexDebutRotation);
                final String rotXML = planningXML.substring(indexDebutRotation, indexFinRotation);
                final Rotation rot = new Rotation();
                String cible = "<rotationId>(.*?)</rotationId>";
                Pattern regex = Pattern.compile(cible);
                Matcher result = regex.matcher(rotXML);
                if (result.find()) {
                    rot.label = result.group(1).replace("&amp;", "&").trim();
                }
                cible = "<isOd>(.*?)</isOd>";
                regex = Pattern.compile(cible);
                result = regex.matcher(rotXML);
                if (result.find()) {
                    if (result.group(1).trim().equals("true")) {
                        rot.isOD = true;
                    }
                    else {
                        rot.isOD = false;
                    }
                }
                else {
                    rot.isOD = false;
                }
                cible = "<particularite>(.*?)</particularite>";
                regex = Pattern.compile(cible);
                result = regex.matcher(rotXML);
                if (result.find()) {
                    rot.particularite = result.group(1).replace("&amp;", "&").trim();
                }
                cible = "<sab>(.*?)</sab>";
                regex = Pattern.compile(cible);
                result = regex.matcher(rotXML);
                if (result.find()) {
                    rot.sab = result.group(1).replace("&amp;", "&").trim();
                }
                cible = "<dureePac>(.*?)</dureePac>";
                regex = Pattern.compile(cible);
                result = regex.matcher(rotXML);
                if (result.find()) {
                    rot.dureePac = result.group(1).trim();
                }
                cible = "<dureeRpc>(.*?)</dureeRpc>";
                regex = Pattern.compile(cible);
                result = regex.matcher(rotXML);
                if (result.find()) {
                    rot.dureeRpc = result.group(1).trim();
                }
                cible = "<nbCdb>(.*?)</nbCdb>";
                regex = Pattern.compile(cible);
                result = regex.matcher(rotXML);
                if (result.find()) {
                    rot.nbCDB = result.group(1).trim();
                }
                cible = "<nbOpl>(.*?)</nbOpl>";
                regex = Pattern.compile(cible);
                result = regex.matcher(rotXML);
                if (result.find()) {
                    rot.nbOPL = result.group(1).trim();
                }
                String ciblePeq = "<peqRot>";
                Pattern regexPeq = Pattern.compile(ciblePeq);
                Matcher resultPeq = regexPeq.matcher(rotXML);
                while (resultPeq.find()) {
                    final int indexDebutPeqRot = resultPeq.end();
                    final int indexFinPeqRot = rotXML.indexOf("</peqRot>", indexDebutPeqRot);
                    final Peq peq = new Peq();
                    cible = "<nom>(.*?)</nom>";
                    regex = Pattern.compile(cible);
                    result = regex.matcher(rotXML);
                    result.region(indexDebutPeqRot, indexFinPeqRot);
                    if (result.find()) {
                        peq.nom = result.group(1).trim();
                    }
                    cible = "<prenom>(.*?)</prenom>";
                    regex = Pattern.compile(cible);
                    result = regex.matcher(rotXML);
                    result.region(indexDebutPeqRot, indexFinPeqRot);
                    if (result.find()) {
                        peq.prenom = result.group(1).trim();
                    }
                    cible = "<fab>(.*?)</fab>";
                    regex = Pattern.compile(cible);
                    result = regex.matcher(rotXML);
                    result.region(indexDebutPeqRot, indexFinPeqRot);
                    if (result.find()) {
                        peq.fab = result.group(1).replace("&amp;", "&").trim();
                    }
                    rot.listPeqRot.add(peq);
                }
                final String cibleSv = "<sv>";
                final Pattern regexSv = Pattern.compile(cibleSv);
                final Matcher resultSv = regexSv.matcher(rotXML);
                while (resultSv.find()) {
                    final int indexDebutSV = resultSv.end();
                    final int indexFinSV = rotXML.indexOf("</sv>", indexDebutSV);
                    final ServiceVol sv = new ServiceVol();
                    cible = "<date>(\\d{2})/?(\\d{2})?</date>";
                    regex = Pattern.compile(cible);
                    result = regex.matcher(rotXML);
                    result.region(indexDebutSV, indexFinSV);
                    if (result.find()) {
                        if (result.group(2) == null) {
                            sv.dateSV = String.valueOf(result.group(1).trim()) + "/" + this.moisNum + "/" + this.anneeInt;
                        }
                        else if (this.moisNum.equals("01") && result.group(2).trim().equals("12")) {
                            sv.dateSV = String.valueOf(result.group(1).trim()) + "/" + result.group(2).trim() + "/" + String.valueOf(this.anneeInt - 1);
                        }
                        else if (this.moisNum.equals("12") && result.group(2).trim().equals("01")) {
                            sv.dateSV = String.valueOf(result.group(1).trim()) + "/" + result.group(2).trim() + "/" + String.valueOf(this.anneeInt + 1);
                        }
                        else {
                            sv.dateSV = String.valueOf(result.group(1).trim()) + "/" + result.group(2).trim() + "/" + this.anneeInt;
                        }
                    }
                    final String cibleVol = "<vol>";
                    final Pattern regexVol = Pattern.compile(cibleVol);
                    final Matcher resultVol = regexVol.matcher(rotXML);
                    resultVol.region(indexDebutSV, indexFinSV);
                    while (resultVol.find()) {
                        final int indexDebutVol = resultVol.end();
                        final int indexFinVol = rotXML.indexOf("</vol>", indexDebutVol);
                        final String volXML = rotXML.substring(indexDebutVol, indexFinVol);
                        final Troncon vol = new Troncon();
                        cible = "<numVol>(.*?)</numVol>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.numVol = result.group(1).trim();
                        }
                        else {
                            vol.numVol = "AF XXX";
                        }
                        cible = "<from>(.*?)</from>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.from = result.group(1).trim().toUpperCase();
                            vol.fromClair = result.group(1).trim().toUpperCase();
                        }
                        cible = "<to>(.*?)</to>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.to = result.group(1).trim().toUpperCase();
                            vol.toClair = result.group(1).trim().toUpperCase();
                        }
                        cible = "<dep>(.*?)</dep>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.departUTC = String.valueOf(sv.dateSV) + " \u00e0 " + result.group(1).trim();
                            vol.debutUTCD = sdfUtc.parse(vol.departUTC);
                        }
                        cible = "<arr>(.*?)</arr>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.arriveeUTC = String.valueOf(sv.dateSV) + " \u00e0 " + result.group(1).trim();
                            vol.finUTCD = sdfUtc.parse(vol.arriveeUTC);
                        }
                        cible = "<isMep>(.*?)</isMep>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            if (result.group(1).trim().equals("true")) {
                                vol.isMep = true;
                            }
                            else {
                                vol.isMep = false;
                            }
                        }
                        else {
                            vol.isMep = false;
                        }
                        cible = "<lagFrom>(.*?)</lagFrom>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.lagFrom = result.group(1).trim();
                        }
                        cible = "<lagTo>(.*?)</lagTo>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.lagTo = result.group(1).trim();
                        }
                        cible = "<type>(.*?)</type>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.typeAvion = result.group(1).trim();
                        }
                        else {
                            vol.typeAvion = this.qualifPNT;
                        }
                        cible = "<version>(.*?)</version>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.versionExploitation = result.group(1).trim();
                        }
                        cible = "<hotel>(.*?)</hotel>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.hotel = result.group(1).replace("&amp;", "&").trim();
                        }
                        cible = "<out>.*?(\\d{2})h(\\d{2}).*?</out>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.isTVreal = true;
                            final int out_h = Integer.parseInt(result.group(1).trim());
                            final int out_m = Integer.parseInt(result.group(2).trim());
                            cal_utc.setTime(vol.debutUTCD);
                            cal_utc.set(11, out_h);
                            cal_utc.set(12, out_m);
                            cal_utc.set(13, 0);
                            final long sameDay = cal_utc.getTimeInMillis();
                            cal_utc.add(5, 1);
                            final long dayAfter = cal_utc.getTimeInMillis();
                            cal_utc.add(5, -2);
                            final long dayBefore = cal_utc.getTimeInMillis();
                            final long deltaBefore = Math.abs(vol.debutUTCD.getTime() - dayBefore);
                            final long deltaAfter = Math.abs(vol.debutUTCD.getTime() - dayAfter);
                            final long deltaSame = Math.abs(vol.debutUTCD.getTime() - sameDay);
                            if (deltaBefore < deltaSame && deltaBefore < deltaAfter) {
                                cal_utc.setTimeInMillis(dayBefore);
                            }
                            else if (deltaAfter < deltaSame && deltaAfter < deltaBefore) {
                                cal_utc.setTimeInMillis(dayAfter);
                            }
                            else {
                                cal_utc.setTimeInMillis(sameDay);
                            }
                            vol.DEPr = cal_utc.getTimeInMillis();
                        }
                        cible = "<tv>.*?(\\d{1,2})h(\\d{2}).*?</tv>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            vol.isTVreal = true;
                            final int tv_h = Integer.parseInt(result.group(1).trim());
                            final int tv_m = Integer.parseInt(result.group(2).trim());
                            if (!vol.isMep) {
                                vol.TVr = Utils.arrondi(tv_h + tv_m / 60.0, 2);
                                vol.MEPr = 0.0;
                            }
                            else {
                                vol.TVr = 0.0;
                                vol.MEPr = Utils.arrondi(tv_h + tv_m / 60.0, 2);
                            }
                        }
                        ciblePeq = "<peq>";
                        regexPeq = Pattern.compile(ciblePeq);
                        resultPeq = regexPeq.matcher(volXML);
                        while (resultPeq.find()) {
                            final int indexDebutPeq = resultPeq.end();
                            final int indexFinPeq = volXML.indexOf("</peq>", indexDebutPeq);
                            final Peq peq2 = new Peq();
                            cible = "<nom>(.*?)</nom>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutPeq, indexFinPeq);
                            if (result.find()) {
                                peq2.nom = result.group(1).trim();
                            }
                            cible = "<prenom>(.*?)</prenom>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutPeq, indexFinPeq);
                            if (result.find()) {
                                peq2.prenom = result.group(1).trim();
                            }
                            cible = "<fab>(.*?)</fab>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutPeq, indexFinPeq);
                            if (result.find()) {
                                peq2.fab = result.group(1).replace("&amp;", "&").trim();
                            }
                            vol.listPeqTroncon.add(peq2);
                        }
                        final int indexDebutIndemFrom = volXML.indexOf("<indemFrom>");
                        final int indexFinIndemFrom = volXML.indexOf("</indemFrom>");
                        if (indexDebutIndemFrom > 0) {
                            vol.indemFrom = new Indem();
                            cible = "<monnaie>(.*?)</monnaie>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemFrom, indexFinIndemFrom);
                            if (result.find()) {
                                vol.indemFrom.monnaieLoc = result.group(1).trim();
                            }
                            cible = "<change>(.*?)</change>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemFrom, indexFinIndemFrom);
                            if (result.find()) {
                                vol.indemFrom.change = result.group(1).trim();
                            }
                            cible = "<ir>(.*?)</ir>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemFrom, indexFinIndemFrom);
                            if (result.find()) {
                                vol.indemFrom.irLoc = result.group(1).trim();
                            }
                            cible = "<mf>(.*?)</mf>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemFrom, indexFinIndemFrom);
                            if (result.find()) {
                                vol.indemFrom.mfLoc = result.group(1).trim();
                            }
                            cible = "<date>(.*?)</date>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemFrom, indexFinIndemFrom);
                            if (result.find()) {
                                vol.indemFrom.dateEffet = result.group(1).trim();
                            }
                            final double irLoc = Double.parseDouble(vol.indemFrom.irLoc.replaceAll(",", "."));
                            final double mfLoc = Double.parseDouble(vol.indemFrom.mfLoc.replaceAll(",", "."));
                            final double change = Double.parseDouble(vol.indemFrom.change.replaceAll(",", "."));
                            vol.indemFrom.ir = Utils.arrondi(irLoc * change, 2);
                            vol.indemFrom.mf = Utils.arrondi(mfLoc * change, 2);
                            vol.indemFrom.irEur = String.valueOf(vol.indemFrom.ir).replaceAll("\\.", ",");
                            vol.indemFrom.mfEur = String.valueOf(vol.indemFrom.mf).replaceAll("\\.", ",");
                            vol.indemFrom.escale = vol.from;
                        }
                        final int indexDebutIndemTo = volXML.indexOf("<indemTo>");
                        final int indexFinIndemTo = volXML.indexOf("</indemTo>");
                        if (indexDebutIndemTo > 0) {
                            vol.indemTo = new Indem();
                            cible = "<monnaie>(.*?)</monnaie>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemTo, indexFinIndemTo);
                            if (result.find()) {
                                vol.indemTo.monnaieLoc = result.group(1).trim();
                            }
                            cible = "<change>(.*?)</change>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemTo, indexFinIndemTo);
                            if (result.find()) {
                                vol.indemTo.change = result.group(1).trim();
                            }
                            cible = "<ir>(.*?)</ir>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemTo, indexFinIndemTo);
                            if (result.find()) {
                                vol.indemTo.irLoc = result.group(1).trim();
                            }
                            cible = "<mf>(.*?)</mf>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemTo, indexFinIndemTo);
                            if (result.find()) {
                                vol.indemTo.mfLoc = result.group(1).trim();
                            }
                            cible = "<date>(.*?)</date>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutIndemTo, indexFinIndemTo);
                            if (result.find()) {
                                vol.indemTo.dateEffet = result.group(1).trim();
                            }
                            final double irLoc2 = Double.parseDouble(vol.indemTo.irLoc.replaceAll(",", "."));
                            final double mfLoc2 = Double.parseDouble(vol.indemTo.mfLoc.replaceAll(",", "."));
                            final double change2 = Double.parseDouble(vol.indemTo.change.replaceAll(",", "."));
                            vol.indemTo.ir = Utils.arrondi(irLoc2 * change2, 2);
                            vol.indemTo.mf = Utils.arrondi(mfLoc2 * change2, 2);
                            vol.indemTo.irEur = String.valueOf(vol.indemTo.ir).replaceAll("\\.", ",");
                            vol.indemTo.mfEur = String.valueOf(vol.indemTo.mf).replaceAll("\\.", ",");
                            vol.indemTo.escale = vol.to;
                        }
                        cible = "<presta>(.*?)</presta>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        while (result.find()) {
                            final Presta presta = new Presta();
                            presta.escaleEmb = vol.from;
                            presta.type = result.group(1).replace("&amp;", "&").trim();
                            vol.listPresta.add(presta);
                        }
                        cible = "<recoDest>(.*?)</recoDest>";
                        regex = Pattern.compile(cible);
                        result = regex.matcher(volXML);
                        if (result.find()) {
                            final Dest_Reco reco = new Dest_Reco();
                            reco.dest = vol.to;
                            reco.categorie = result.group(1).trim();
                            vol.listRecoDest.add(reco);
                            if (!rot.listDestReco.contains(reco)) {
                                rot.listDestReco.add(reco);
                            }
                        }
                        final String cibleReco = "<recoDeg>";
                        final Pattern regexReco = Pattern.compile(cibleReco);
                        final Matcher resultReco = regexReco.matcher(volXML);
                        while (resultReco.find()) {
                            final int indexDebutReco = resultReco.end();
                            final int indexFinReco = volXML.indexOf("</recoDeg>", indexDebutReco);
                            final Deg_Reco reco2 = new Deg_Reco();
                            cible = "<deg>(.*?)</deg>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutReco, indexFinReco);
                            if (result.find()) {
                                reco2.dest = vol.to;
                                reco2.deg = result.group(1).trim();
                            }
                            cible = "<categorie>(.*?)</categorie>";
                            regex = Pattern.compile(cible);
                            result = regex.matcher(volXML);
                            result.region(indexDebutReco, indexFinReco);
                            if (result.find()) {
                                reco2.categorie = result.group(1).trim();
                            }
                            vol.listRecoDeg.add(reco2);
                            if (!rot.listDegReco.contains(reco2)) {
                                rot.listDegReco.add(reco2);
                            }
                        }
                        sv.listTroncon.add(vol);
                    }
                    rot.listSV.add(sv);
                }
                this.listRotation.add(rot);
            }
            for (int i = 0; i < this.listRotation.size(); ++i) {
                final Rotation rot2 = this.listRotation.get(i);
                rot2.numRot = i + 1;
                for (int j = 0; j < rot2.listSV.size(); ++j) {
                    final ServiceVol sv2 = rot2.listSV.get(j);
                    sv2.numRot = i + 1;
                    sv2.numSV = j + 1;
                    for (int k = 0; k < sv2.listTroncon.size(); ++k) {
                        final Troncon tro = sv2.listTroncon.get(k);
                        tro.numRot = i + 1;
                        tro.numSV = j + 1;
                        tro.numTroncon = k + 1;
                        if (tro.finUTCD.before(tro.debutUTCD)) {
                            cal_utc.setTime(tro.finUTCD);
                            cal_utc.add(5, 1);
                            tro.finUTCD = cal_utc.getTime();
                            tro.arriveeUTC = sdfUtc.format(tro.finUTCD);
                        }
                    }
                    for (int k = 0; k < sv2.listTroncon.size() - 1; ++k) {
                        final Troncon t1 = sv2.listTroncon.get(k);
                        final Troncon t2 = sv2.listTroncon.get(k + 1);
                        if (t2.debutUTCD.before(t1.finUTCD)) {
                            cal_utc.setTime(t2.debutUTCD);
                            cal_utc.add(5, 1);
                            t2.debutUTCD = cal_utc.getTime();
                            t2.departUTC = sdfUtc.format(t2.debutUTCD);
                            cal_utc.setTime(t2.finUTCD);
                            cal_utc.add(5, 1);
                            t2.finUTCD = cal_utc.getTime();
                            t2.arriveeUTC = sdfUtc.format(t2.finUTCD);
                            cal_utc.setTimeInMillis(t2.DEPr);
                            cal_utc.add(5, 1);
                            t2.DEPr = cal_utc.getTimeInMillis();
                        }
                    }
                }
            }
            for (final Rotation rotation : this.listRotation) {
                final ServiceVol firstSV = rotation.listSV.get(0);
                final ServiceVol lastSV = rotation.listSV.get(rotation.listSV.size() - 1);
                final Troncon firstTronconRot = firstSV.listTroncon.get(0);
                final Troncon lastTronconRot = lastSV.listTroncon.get(lastSV.listTroncon.size() - 1);
                rotation.departUTC = firstTronconRot.departUTC;
                rotation.debutUTCD = firstTronconRot.debutUTCD;
                rotation.arriveeUTC = lastTronconRot.arriveeUTC;
                rotation.finUTCD = lastTronconRot.finUTCD;
                final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
                final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
                caldeb.setTime(rotation.debutUTCD);
                calfin.setTime(rotation.finUTCD);
                caldeb.set(11, 0);
                caldeb.set(12, 0);
                caldeb.set(13, 0);
                caldeb.set(14, 0);
                calfin.set(11, 23);
                calfin.set(12, 59);
                calfin.set(13, 59);
                calfin.set(14, 1000);
                final long deltaOffset = calfin.get(15) + calfin.get(16) - caldeb.get(15) - caldeb.get(16);
                rotation.nON = (int)Math.round((calfin.getTimeInMillis() - caldeb.getTimeInMillis() + deltaOffset) / 8.64E7);
                cal_utc.setTime(rotation.debutUTCD);
                final int mois_depart = cal_utc.get(2) + 1;
                cal_utc.setTime(rotation.finUTCD);
                final int mois_arrivee = cal_utc.get(2) + 1;
                if (mois_arrivee != mois_depart) {
                    rotation.isRotationACheval = true;
                }
                else {
                    rotation.isRotationACheval = false;
                }
                if (!firstTronconRot.from.equals(lastTronconRot.to)) {
                    rotation.isCourrierCroise = true;
                }
                else {
                    rotation.isCourrierCroise = false;
                }
                double tabs = (lastTronconRot.finUTCD.getTime() - firstTronconRot.debutUTCD.getTime()) / 3600000.0;
                if (!this.isPNT) {
                    if (firstTronconRot.isMep && firstSV.listTroncon.size() == 1) {
                        tabs += 0.0;
                    }
                    else {
                        ++tabs;
                    }
                    if (lastTronconRot.isMep && lastSV.listTroncon.size() == 1) {
                        tabs += 0.0;
                    }
                    else {
                        tabs += 0.5;
                    }
                    tabs = Math.round(tabs * 100.0) / 100.0;
                    if (!rotation.isOD) {
                        rotation.tempsAbsence = Utils.timetoString(tabs);
                    }
                }
                else if (this.isPNT) {
                    if (firstTronconRot.isMep) {
                        ++tabs;
                    }
                    else if (firstTronconRot.typeAvion.equals("318") || firstTronconRot.typeAvion.equals("319") || firstTronconRot.typeAvion.equals("320") || firstTronconRot.typeAvion.equals("321")) {
                        tabs += 1.25;
                    }
                    else {
                        tabs += 1.5;
                    }
                    tabs += 0.25;
                    tabs = Math.round(tabs * 100.0) / 100.0;
                    if (!rotation.isOD) {
                        rotation.tempsAbsence = Utils.timetoString(tabs);
                    }
                }
                cal_utc.setTime(rotation.finUTCD);
                cal_utc.add(14, 1800000);
                cal_utc.add(14, (int)Utils.timeStringtoMs(rotation.dureeRpc));
                if (!rotation.isOD) {
                    rotation.reengagementUTCD = cal_utc.getTime();
                    rotation.reengagementUTC = sdfUtc.format(rotation.reengagementUTCD);
                }
                cal_utc.setTime(rotation.debutUTCD);
                if (!this.isPNT) {
                    if (firstTronconRot.isMep && firstSV.listTroncon.size() == 1) {
                        cal_utc.add(11, 0);
                    }
                    else {
                        cal_utc.add(11, -1);
                    }
                }
                else if (this.isBP) {
                    cal_utc.add(12, -60);
                }
                else if (firstTronconRot.isMep) {
                    cal_utc.add(12, -75);
                }
                else if (firstTronconRot.typeAvion.equals("318") || firstTronconRot.typeAvion.equals("319") || firstTronconRot.typeAvion.equals("320") || firstTronconRot.typeAvion.equals("321")) {
                    cal_utc.add(12, -90);
                }
                else {
                    cal_utc.add(12, -105);
                }
                if (!rotation.isOD) {
                    rotation.presentationUTCD = cal_utc.getTime();
                    rotation.presentationUTC = sdfUtc.format(rotation.presentationUTCD);
                }
                double tvt_rot = 0.0;
                double tvmep_rot = 0.0;
                for (final ServiceVol sv3 : rotation.listSV) {
                    final Troncon firstTronconSV = sv3.listTroncon.get(0);
                    final Troncon lastTronconSV = sv3.listTroncon.get(sv3.listTroncon.size() - 1);
                    long tsv = 0L;
                    if (firstTronconSV.isMep) {
                        tsv = lastTronconSV.finUTCD.getTime() - firstTronconSV.debutUTCD.getTime() + 3600000L + 900000L;
                    }
                    else if (sv3.numSV == 1 && this.isMC) {
                        tsv = lastTronconSV.finUTCD.getTime() - firstTronconSV.debutUTCD.getTime() + 4500000L + 900000L;
                    }
                    else if (sv3.numSV == 1 && !this.isMC) {
                        tsv = lastTronconSV.finUTCD.getTime() - firstTronconSV.debutUTCD.getTime() + 5400000L + 900000L;
                    }
                    else if (sv3.numSV > 1 && this.isMC) {
                        tsv = lastTronconSV.finUTCD.getTime() - firstTronconSV.debutUTCD.getTime() + 3600000L + 900000L;
                    }
                    else if (sv3.numSV > 1 && !this.isMC) {
                        tsv = lastTronconSV.finUTCD.getTime() - firstTronconSV.debutUTCD.getTime() + 4500000L + 900000L;
                    }
                    if (!rotation.isOD) {
                        sv3.TSV = Utils.timetoString(tsv / 3600000.0);
                    }
                    if (sv3.numSV < rotation.listSV.size()) {
                        sv3.isDecoucher = true;
                        final Troncon firstTroNextSV = rotation.listSV.get(sv3.numSV).listTroncon.get(0);
                        long repos = 0L;
                        if (!firstTroNextSV.isMep && !this.isMC) {
                            repos = firstTroNextSV.debutUTCD.getTime() - lastTronconSV.finUTCD.getTime() - 900000L - 4500000L;
                        }
                        else {
                            repos = firstTroNextSV.debutUTCD.getTime() - lastTronconSV.finUTCD.getTime() - 900000L - 3600000L;
                        }
                        sv3.repos = Utils.timetoString(repos / 3600000.0);
                    }
                    else {
                        sv3.isDecoucher = false;
                        sv3.repos = null;
                    }
                    double tvsv = 0.0;
                    for (int l = 0; l < sv3.listTroncon.size(); ++l) {
                        final Troncon troncon = sv3.listTroncon.get(l);
                        troncon.numTroncon = l + 1;
                        double tvt = (troncon.finUTCD.getTime() - troncon.debutUTCD.getTime()) / 3600000.0;
                        tvt = Utils.arrondi(tvt, 2);
                        troncon.TVT = Utils.timetoString(tvt);
                        if (!troncon.isMep) {
                            tvsv = Utils.arrondi(tvsv + tvt, 2);
                            tvt_rot = Utils.arrondi(tvt_rot + tvt, 2);
                        }
                        else if (troncon.isMep) {
                            if (!rotation.isOD) {
                                tvsv = Utils.arrondi(tvsv + tvt / 2.0, 2);
                                tvt_rot = Utils.arrondi(tvt_rot + tvt / 2.0, 2);
                            }
                            tvmep_rot = Utils.arrondi(tvmep_rot + tvt, 2);
                        }
                        if (l == sv3.listTroncon.size() - 1 && sv3.isDecoucher) {
                            troncon.isDecoucher = true;
                            troncon.repos = sv3.repos;
                            rotation.listDecouchers.add(troncon.to);
                        }
                        else {
                            troncon.isDecoucher = false;
                            troncon.repos = null;
                        }
                        if (l != sv3.listTroncon.size() - 1) {
                            final Troncon troNext = sv3.listTroncon.get(l + 1);
                            troncon.tempsEscale = Utils.timetoString((troNext.debutUTCD.getTime() - troncon.finUTCD.getTime()) / 3600000.0);
                        }
                        if (dbEscales.containsKey(troncon.from)) {
                            troncon.fromClair = dbEscales.get(troncon.from).nom;
                            final TimeZone tzFrom = TimeZone.getTimeZone(dbEscales.get(troncon.from).tz);
                            cal_utc.setTime(troncon.debutUTCD);
                            troncon.LAGfromMillis = tzFrom.getOffset(cal_utc.getTimeInMillis());
                            troncon.lagFrom = String.valueOf(Utils.arrondi(troncon.LAGfromMillis / 1000 / 60 / 60, 1));
                        }
                        else if (troncon.lagFrom != null) {
                            troncon.LAGfromMillis = (int)(Float.parseFloat(troncon.lagFrom) * 60.0f * 60.0f * 1000.0f);
                        }
                        else {
                            troncon.lagFrom = Float.toString(TimeZone.getTimeZone("Europe/Paris").getOffset(troncon.debutUTCD.getTime()) / 3600000.0f);
                            troncon.LAGfromMillis = (int)(Float.parseFloat(troncon.lagFrom) * 60.0f * 60.0f * 1000.0f);
                        }
                        if (dbEscales.containsKey(troncon.to)) {
                            troncon.toClair = dbEscales.get(troncon.to).nom;
                            final TimeZone tzTo = TimeZone.getTimeZone(dbEscales.get(troncon.to).tz);
                            cal_utc.setTime(troncon.finUTCD);
                            troncon.LAGtoMillis = tzTo.getOffset(cal_utc.getTimeInMillis());
                            troncon.lagTo = String.valueOf(Utils.arrondi(troncon.LAGtoMillis / 1000 / 60 / 60, 1));
                        }
                        else if (troncon.lagTo != null) {
                            troncon.LAGtoMillis = (int)(Float.parseFloat(troncon.lagTo) * 60.0f * 60.0f * 1000.0f);
                        }
                        else {
                            troncon.lagTo = Float.toString(TimeZone.getTimeZone("Europe/Paris").getOffset(troncon.finUTCD.getTime()) / 3600000.0f);
                            troncon.LAGtoMillis = (int)(Float.parseFloat(troncon.lagFrom) * 60.0f * 60.0f * 1000.0f);
                        }
                    }
                    sv3.TVSV = Utils.timetoString(tvsv);
                }
                if (!rotation.isOD) {
                    rotation.tempsVolTotal = Utils.timetoString(tvt_rot);
                    rotation.tempsVolMep = Utils.timetoString(tvmep_rot);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void chope_ActivitesSol_XML(final String planningXML) {
        final SimpleDateFormat sdfParis = new SimpleDateFormat("dd/MM/yyyy '\u00e0' HH'h'mm");
        sdfParis.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final SimpleDateFormat sdfParis2 = new SimpleDateFormat("dd/MM/yyyy");
        sdfParis2.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calParis = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final String cibleSol = "<sol>";
        final Pattern regexSol = Pattern.compile(cibleSol);
        final Matcher resultSol = regexSol.matcher(planningXML);
        while (resultSol.find()) {
            final int indexDebutSol = resultSol.end();
            final int indexFinSol = planningXML.indexOf("</sol>", indexDebutSol);
            final String solXML = planningXML.substring(indexDebutSol, indexFinSol);
            final ActiviteSol actSol = new ActiviteSol();
            String date = null;
            String cible = "<date>(.*?)(/.*?)?</date>";
            Pattern regex = Pattern.compile(cible);
            Matcher result = regex.matcher(solXML);
            if (result.find()) {
                if (result.group(2) == null) {
                    date = String.valueOf(result.group(1).trim()) + "/" + this.moisNum + "/" + this.anneeInt;
                }
                else {
                    date = String.valueOf(result.group(1).trim()) + result.group(2).trim() + "/" + this.anneeInt;
                }
            }
            actSol.jour = date;
            cible = "<debut>(.*?)</debut>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.debut = String.valueOf(date) + " \u00e0 " + result.group(1).trim();
            }
            String jourOffset = null;
            cible = "<fin>(.*?)(/+.*?)?</fin>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.fin = String.valueOf(date) + " \u00e0 " + result.group(1).trim();
                if (result.group(2) != null) {
                    jourOffset = result.group(2).substring(2);
                }
            }
            try {
                actSol.debutUTCD = sdfParis.parse(actSol.debut);
                actSol.finUTCD = sdfParis.parse(actSol.fin);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (jourOffset != null) {
                calParis.setTime(actSol.finUTCD);
                calParis.add(5, Integer.parseInt(jourOffset));
                actSol.finUTCD = calParis.getTime();
                actSol.fin = sdfParis.format(actSol.finUTCD);
            }
            if (actSol.finUTCD.before(actSol.debutUTCD)) {
                calParis.setTime(actSol.finUTCD);
                calParis.add(5, 1);
                actSol.finUTCD = calParis.getTime();
                actSol.fin = sdfParis.format(actSol.finUTCD);
            }
            cible = "<code>(.*?)</code>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.code = result.group(1).replace("&amp;", "&").trim();
            }
            cible = "<hc>(.*?)</hc>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.isHCreal = true;
                actSol.HCS = Double.parseDouble(result.group(1).trim());
            }
            cible = "<hcr>(.*?)</hcr>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.isHCreal = true;
                actSol.HCRS = Double.parseDouble(result.group(1).trim());
            }
            cible = "<ikv>(.*?)</ikv>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.isIKVreal = true;
                actSol.IKVar = result.group(1).trim();
            }
            cible = "<intitule>(.*?)</intitule>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.label = result.group(1).replace("&amp;", "&").trim();
            }
            cible = "<maj>(.*?)</maj>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.maj = result.group(1).replace("&amp;", "&").trim();
            }
            cible = "<lieu>(.*?)</lieu>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.lieu = result.group(1).replace("&amp;", "&").trim();
            }
            cible = "<salle>(.*?)</salle>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.salle = result.group(1).replace("&amp;", "&").trim();
            }
            cible = "<commentaire>(.*?)</commentaire>";
            regex = Pattern.compile(cible);
            result = regex.matcher(solXML);
            if (result.find()) {
                actSol.commentaire = result.group(1).replace("&amp;", "&").trim();
            }
            final String ciblePart = "<participant>";
            final Pattern regexPart = Pattern.compile(ciblePart);
            final Matcher resultPart = regexPart.matcher(solXML);
            while (resultPart.find()) {
                final int indexDebutPartSol = resultPart.end();
                final int indexFinPartSol = solXML.indexOf("</participant>", indexDebutPartSol);
                final Participant part = new Participant();
                cible = "<nom>(.*?)</nom>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutPartSol, indexFinPartSol);
                if (result.find()) {
                    part.nom = result.group(1).trim();
                }
                cible = "<prenom>(.*?)</prenom>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutPartSol, indexFinPartSol);
                if (result.find()) {
                    part.prenom = result.group(1).trim();
                }
                cible = "<fab>(.*?)</fab>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutPartSol, indexFinPartSol);
                if (result.find()) {
                    part.fab = result.group(1).trim();
                }
                cible = "<matricule>(.*?)</matricule>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutPartSol, indexFinPartSol);
                if (result.find()) {
                    part.matricule = result.group(1).trim();
                }
                cible = "<affectation>(.*?)</affectation>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutPartSol, indexFinPartSol);
                if (result.find()) {
                    part.affectation = result.group(1).trim();
                }
                cible = "<statut>(.*?)</statut>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutPartSol, indexFinPartSol);
                if (result.find()) {
                    part.statut = result.group(1).replace("&amp;", "&").trim();
                }
                actSol.listParticipant.add(part);
            }
            final String cibleBloc = "<bloc>";
            final Pattern regexBloc = Pattern.compile(cibleBloc);
            final Matcher resultBloc = regexBloc.matcher(solXML);
            if (resultBloc.find()) {
                final int indexDebutBlocSol = resultBloc.end();
                final int indexFinBlocSol = solXML.indexOf("</bloc>", indexDebutBlocSol);
                final BlocActivite bloc = new BlocActivite();
                cible = "<code>(.*?)</code>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutBlocSol, indexFinBlocSol);
                if (result.find()) {
                    bloc.code = result.group(1).replace("&amp;", "&").trim();
                }
                cible = "<debut>(.*?)</debut>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutBlocSol, indexFinBlocSol);
                if (result.find()) {
                    bloc.debut = result.group(1).trim();
                }
                cible = "<fin>(.*?)</fin>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutBlocSol, indexFinBlocSol);
                if (result.find()) {
                    bloc.fin = result.group(1).trim();
                }
                cible = "<label>(.*?)</label>";
                regex = Pattern.compile(cible);
                result = regex.matcher(solXML);
                result.region(indexDebutBlocSol, indexFinBlocSol);
                if (result.find()) {
                    bloc.label = result.group(1).replace("&amp;", "&").trim();
                }
                if (bloc.code.equals("SST")) {
                    bloc.categorie = "Bloc stage";
                    if (bloc.label == null) {
                        bloc.label = "BLOC STAGE";
                    }
                }
                else if (bloc.code.equals("MBR")) {
                    bloc.categorie = "Bloc r\u00e9serve";
                    if (bloc.label == null) {
                        bloc.label = "BLOC RESERVE";
                    }
                }
                else {
                    bloc.categorie = "Bloc activit\u00e9s";
                    if (bloc.label == null) {
                        bloc.label = "BLOC ACTIVITES";
                    }
                }
                actSol.isBlocActivite = true;
                actSol.bloc = bloc;
            }
            if (actSol.code.equals("PRB")) {
                actSol.categorie = "Repos";
                actSol.isH24 = true;
                actSol.isDispo = true;
                actSol.isCredite = false;
            }
            else if (actSol.code.equals("DSP")) {
                actSol.categorie = "Dispersion";
                actSol.isH24 = true;
                actSol.isDispo = true;
            }
            else if (actSol.code.equals("MCA") || actSol.code.equals("MCE")) {
                actSol.categorie = "Cong\u00e9s";
                actSol.isH24 = true;
                actSol.isDispo = true;
                actSol.isCredite = false;
            }
            else if (actSol.code.equals("MDV")) {
                actSol.categorie = "Sans Solde";
                actSol.isH24 = true;
                actSol.isDispo = true;
                actSol.isCredite = false;
            }
            else if (actSol.code.equals("MAS")) {
                actSol.categorie = "Arr\u00eat Maladie";
                actSol.isH24 = true;
                actSol.isDispo = true;
                actSol.isCredite = false;
            }
            else if (actSol.code.equals("SST")) {
                actSol.categorie = "Stage ECP";
                actSol.isH24 = false;
                actSol.isDispo = false;
                actSol.isCredite = true;
            }
            else if (actSol.code.equals("RBR")) {
                actSol.categorie = "R\u00e9serve";
                actSol.isH24 = false;
                actSol.isDispo = false;
                actSol.isCredite = true;
            }
            else if (actSol.code.equals("MVM")) {
                actSol.categorie = "Visite m\u00e9dicale";
                actSol.isH24 = false;
                actSol.isDispo = false;
                actSol.isCredite = true;
            }
            else if (actSol.code.equals("MIM")) {
                actSol.categorie = "Autre Sol";
                actSol.isH24 = false;
                actSol.isDispo = false;
                actSol.isCredite = true;
            }
            else if (actSol.code.equals("SYN")) {
                actSol.categorie = "Journ\u00e9e syndicale";
                actSol.isH24 = true;
                actSol.isDispo = true;
                actSol.isCredite = true;
            }
            else if (actSol.code.equals("MCP")) {
                actSol.categorie = "Contrainte planning";
                actSol.isH24 = false;
                actSol.isDispo = false;
                actSol.isCredite = false;
            }
            else if (actSol.code.equals("MCI")) {
                actSol.categorie = "Instruction";
                actSol.isH24 = false;
                actSol.isDispo = false;
                actSol.isCredite = true;
            }
            else if (actSol.code.equals("MAD")) {
                actSol.categorie = "Repos additionnel";
                actSol.isH24 = false;
                actSol.isDispo = true;
                actSol.isCredite = false;
            }
            else if (actSol.code.equals("PAC")) {
                actSol.categorie = "Repos pr\u00e9-courrier";
                actSol.isH24 = true;
                actSol.isDispo = true;
                actSol.isCredite = false;
            }
            else if (actSol.code.equals("RPC")) {
                actSol.categorie = "Repos post-courrier";
                actSol.isH24 = true;
                actSol.isDispo = true;
                actSol.isCredite = false;
            }
            if (!this.listSol.contains(actSol)) {
                this.listSol.add(actSol);
            }
        }
        calParis.set(1, this.anneeInt);
        calParis.set(2, this.moisInt - 1);
        calParis.set(5, 1);
        calParis.set(11, 0);
        calParis.set(12, 0);
        calParis.set(13, 0);
        calParis.set(14, 0);
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        while (calParis.get(2) == this.moisInt - 1) {
            boolean isActSol = false;
            boolean isActVol = false;
            for (final Rotation rot : this.listRotation) {
                cal.setTime(rot.debutUTCD);
                cal.set(11, 0);
                cal.set(12, 0);
                cal.set(13, 0);
                cal.set(14, 0);
                if (calParis.getTime().compareTo(cal.getTime()) >= 0 && calParis.getTime().before(rot.finUTCD)) {
                    isActVol = true;
                    break;
                }
            }
            for (final ActiviteSol actSol : this.listSol) {
                cal.setTime(actSol.debutUTCD);
                cal.set(11, 0);
                cal.set(12, 0);
                cal.set(13, 0);
                cal.set(14, 0);
                if (calParis.getTime().compareTo(cal.getTime()) >= 0 && calParis.getTime().before(actSol.finUTCD)) {
                    isActSol = true;
                    break;
                }
            }
            if (!isActSol && !isActVol) {
                final ActiviteSol actSol = new ActiviteSol();
                actSol.jour = sdfParis2.format(calParis.getTime());
                actSol.code = "DSP";
                actSol.label = "DISPERSION";
                actSol.categorie = "Dispersion";
                actSol.debut = String.valueOf(actSol.jour) + " \u00e0 00h00";
                actSol.fin = String.valueOf(actSol.jour) + " \u00e0 23h59";
                try {
                    actSol.debutUTCD = sdfParis.parse(actSol.debut);
                    actSol.finUTCD = sdfParis.parse(actSol.fin);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
                actSol.isH24 = true;
                actSol.isDispo = true;
                actSol.isCredite = false;
                this.listSol.add(actSol);
            }
            calParis.add(5, 1);
        }
    }
    
    private void calculHCp_ActivitesVol() {
        String baseTVref;
        if (!this.isPNT && this.isMC) {
            baseTVref = this.importBaseTVref("MC");
        }
        else if (!this.isPNT && !this.isMC) {
            baseTVref = this.importBaseTVref("LC");
        }
        else {
            baseTVref = this.importBaseTVref(this.qualifPNT);
        }
        final GregorianCalendar cal = new GregorianCalendar();
        final GregorianCalendar caldeb = new GregorianCalendar();
        final GregorianCalendar calfin = new GregorianCalendar();
        double bonus;
        int infNuit;
        int supNuit;
        if (!this.isMC) {
            bonus = 0.58;
            infNuit = 6;
            supNuit = 18;
        }
        else {
            if (this.isBP) {
                bonus = 0.25;
            }
            else {
                bonus = 0.25;
            }
            infNuit = 9;
            supNuit = 21;
        }
        for (final Rotation rotation : this.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                final Troncon firstTroSv = sv.listTroncon.get(0);
                final SimpleTimeZone zoneFirstTroSv = new SimpleTimeZone(firstTroSv.LAGfromMillis, "");
                for (final Troncon troncon : sv.listTroncon) {
                    if (troncon.isMep) {
                        troncon.MEPp = Utils.arrondi((troncon.finUTCD.getTime() - troncon.debutUTCD.getTime()) / 3600000.0, 2);
                        troncon.TVp = 0.0;
                    }
                    else {
                        troncon.TVp = Utils.arrondi((troncon.finUTCD.getTime() - troncon.debutUTCD.getTime()) / 3600000.0, 2);
                        troncon.MEPp = 0.0;
                    }
                    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                    cal.setTime(troncon.debutUTCD);
                    troncon.DEPp = cal.getTimeInMillis();
                    troncon.DEBp = cal.get(11) + cal.get(12) / 60.0;
                    troncon.DEBp = Math.rint(troncon.DEBp * 100.0) / 100.0;
                    troncon.ARRp = troncon.DEPp + (long)Utils.arrondi(troncon.TVp * 60.0, 0) * 60L * 1000L + (long)Utils.arrondi(troncon.MEPp * 60.0, 0) * 60L * 1000L;
                    troncon.FINp = Utils.arrondi(troncon.DEBp + troncon.TVp + troncon.MEPp, 2);
                    if (sv.numSV == 1 && troncon.numTroncon == 1) {
                        rotation.DEPp = troncon.DEPp;
                    }
                    if (sv.numSV == rotation.listSV.size() && troncon.numTroncon == sv.listTroncon.size()) {
                        rotation.ARRp = troncon.ARRp;
                    }
                    caldeb.setTimeZone(TimeZone.getTimeZone("UTC"));
                    calfin.setTimeZone(TimeZone.getTimeZone("UTC"));
                    caldeb.setTime(cal.getTime());
                    caldeb.set(2, 2);
                    caldeb.set(7, 7);
                    caldeb.set(8, -1);
                    caldeb.set(11, 0);
                    caldeb.set(12, 0);
                    caldeb.set(13, 0);
                    caldeb.set(14, 0);
                    calfin.setTime(cal.getTime());
                    calfin.set(2, 9);
                    calfin.set(7, 7);
                    calfin.set(8, -1);
                    calfin.set(11, 23);
                    calfin.set(12, 59);
                    calfin.set(13, 59);
                    calfin.set(14, 1000);
                    if (cal.before(calfin) && cal.after(caldeb)) {
                        troncon.isSaisonEte = true;
                    }
                    else {
                        troncon.isSaisonEte = false;
                    }
                    troncon.isTVref = false;
                    troncon.TVref = troncon.TVp;
                    String motif;
                    if (troncon.isSaisonEte) {
                        motif = "S\\s*;\\s*";
                    }
                    else {
                        motif = "W\\s*;\\s*";
                    }
                    motif = String.valueOf(motif) + troncon.from + "\\s*;\\s*" + troncon.to + "\\s*;\\s*" + "(([0-9]{2})h([0-9]{2}))" + "\\s*;\\s*" + troncon.numVol;
                    if (!this.isPNT && (troncon.typeAvion.equals("318") || troncon.typeAvion.equals("319") || troncon.typeAvion.equals("320") || troncon.typeAvion.equals("321"))) {
                        motif = String.valueOf(motif) + "\\s*;\\s*" + "320";
                    }
                    else if (!this.isPNT && (troncon.typeAvion.equals("332") || troncon.typeAvion.equals("343"))) {
                        motif = String.valueOf(motif) + "\\s*;\\s*" + "340";
                    }
                    else if (!this.isPNT && (troncon.typeAvion.equals("772") || troncon.typeAvion.equals("77W") || troncon.typeAvion.equals("77X"))) {
                        motif = String.valueOf(motif) + "\\s*;\\s*" + "777";
                    }
                    else if (!this.isPNT && troncon.typeAvion.equals("787")) {
                        motif = String.valueOf(motif) + "\\s*;\\s*" + "787";
                    }
                    else if (!this.isPNT && troncon.typeAvion.equals("380")) {
                        motif = String.valueOf(motif) + "\\s*;\\s*" + "380";
                    }
                    if (this.isPNT && troncon.typeAvion.equals("77X")) {
                        motif = String.valueOf(motif) + "\\s*;\\s*" + "cargo";
                    }
                    Pattern regex = Pattern.compile(motif);
                    Matcher result = regex.matcher(baseTVref);
                    if (result.find()) {
                        troncon.isTVref = true;
                        final double h = Double.parseDouble(result.group(2));
                        final double m = Double.parseDouble(result.group(3));
                        troncon.TVref = Utils.arrondi(h + m / 60.0, 2);
                    }
                    if (!troncon.isTVref) {
                        if (troncon.isSaisonEte) {
                            motif = "S\\s*;\\s*";
                        }
                        else {
                            motif = "W\\s*;\\s*";
                        }
                        motif = String.valueOf(troncon.from) + "\\s*;\\s*" + troncon.to + "\\s*;\\s*" + "(([0-9]{2})h([0-9]{2}))";
                        if (!this.isPNT && (troncon.typeAvion.equals("318") || troncon.typeAvion.equals("319") || troncon.typeAvion.equals("320") || troncon.typeAvion.equals("321"))) {
                            motif = String.valueOf(motif) + "\\s*;\\s*;\\s*" + "320";
                        }
                        else if (!this.isPNT && (troncon.typeAvion.equals("332") || troncon.typeAvion.equals("343"))) {
                            motif = String.valueOf(motif) + "\\s*;\\s*;\\s*" + "340";
                        }
                        else if (!this.isPNT && (troncon.typeAvion.equals("772") || troncon.typeAvion.equals("77W") || troncon.typeAvion.equals("77X"))) {
                            motif = String.valueOf(motif) + "\\s*;\\s*;\\s*" + "777";
                        }
                        else if (!this.isPNT && troncon.typeAvion.equals("787")) {
                            motif = String.valueOf(motif) + "\\s*;\\s*;\\s*" + "787";
                        }
                        if (!this.isPNT && troncon.typeAvion.equals("380")) {
                            motif = String.valueOf(motif) + "\\s*;\\s*;\\s*" + "380";
                        }
                        if (this.isPNT && troncon.typeAvion.equals("77X")) {
                            motif = String.valueOf(motif) + "\\s*;\\s*;\\s*" + "cargo";
                        }
                        motif = String.valueOf(motif) + "\\r?\\n";
                        regex = Pattern.compile(motif);
                        result = regex.matcher(baseTVref);
                        if (result.find()) {
                            troncon.isTVref = true;
                            final double h = Double.parseDouble(result.group(2));
                            final double m = Double.parseDouble(result.group(3));
                            troncon.TVref = Utils.arrondi(h + m / 60.0, 2);
                        }
                    }
                    if (troncon.isMep) {
                        troncon.TVref = 0.0;
                        troncon.isTVref = true;
                    }
                    if (rotation.isOD) {
                        troncon.MEPp = 0.0;
                    }
                    if (!troncon.isMep) {
                        if (troncon.TVref > troncon.TVp) {
                            troncon.HV100p = troncon.TVref;
                        }
                        else {
                            troncon.HV100p = troncon.TVp;
                        }
                        troncon.HV100Rp = Utils.arrondi(troncon.TVref + bonus, 2);
                    }
                    else {
                        troncon.HV100p = 0.0;
                        troncon.HV100Rp = 0.0;
                    }
                    troncon.TVNp = 0.0;
                    if (this.isMC) {
                        caldeb.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                        calfin.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    }
                    else {
                        caldeb.setTimeZone(zoneFirstTroSv);
                        calfin.setTimeZone(zoneFirstTroSv);
                    }
                    if (!troncon.isMep) {
                        caldeb.setTimeInMillis(troncon.DEPp);
                        calfin.setTimeInMillis(troncon.DEPp + (long)(bonus * 60.0 * 60.0 * 1000.0) + (long)(troncon.TVref * 60.0 * 60.0 * 1000.0));
                        troncon.TVNp = (double)Utils.calculNuit(caldeb, calfin, supNuit, infNuit);
                        troncon.TVNp = Utils.arrondi(troncon.TVNp / 3600000.0, 2);
                    }
                    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                    caldeb.setTimeZone(TimeZone.getTimeZone("UTC"));
                    caldeb.setTimeInMillis(troncon.DEPp);
                    calfin.setTimeZone(TimeZone.getTimeZone("UTC"));
                    calfin.setTimeInMillis(troncon.ARRp);
                    final int moisdeb = caldeb.get(2);
                    final int moisfin = calfin.get(2);
                    if (moisdeb == moisfin) {
                        troncon.isTronconAChevalP = false;
                    }
                    else {
                        troncon.isTronconAChevalP = true;
                        cal.setTimeInMillis(troncon.ARRp);
                        cal.set(11, 0);
                        cal.set(12, 0);
                        cal.set(13, 0);
                        cal.set(14, 0);
                        troncon.TVp1 = Utils.arrondi((cal.getTimeInMillis() - caldeb.getTimeInMillis()) / 3600000.0, 2);
                        troncon.TVp2 = Utils.arrondi((calfin.getTimeInMillis() - cal.getTimeInMillis()) / 3600000.0, 2);
                    }
                }
                sv.debTSVp = sv.listTroncon.get(0).DEPp - 3600000L;
                sv.finTSVp = sv.listTroncon.get(sv.listTroncon.size() - 1).ARRp + 1800000L;
                if (rotation.isCourrierCroise && sv.numSV == rotation.listSV.size()) {
                    sv.finTSVp += 3600000L;
                }
                if (!rotation.isOD) {
                    sv.TSVp = Utils.arrondi((sv.finTSVp - sv.debTSVp) / 3600000.0, 2);
                }
                else {
                    sv.TSVp = 0.0;
                }
                sv.TSVNp = 0.0;
                if (this.isMC) {
                    caldeb.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    calfin.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                }
                else {
                    caldeb.setTimeZone(zoneFirstTroSv);
                    calfin.setTimeZone(zoneFirstTroSv);
                }
                caldeb.setTimeInMillis(sv.debTSVp);
                calfin.setTimeInMillis(sv.finTSVp);
                sv.TSVNp = (double)Utils.calculNuit(caldeb, calfin, supNuit, infNuit);
                sv.TSVNp = Utils.arrondi(sv.TSVNp / 3600000.0, 2);
                if (this.isMC) {
                    if (sv.TSVp < 5.74 && (sv.listTroncon.size() > 1 || !sv.listTroncon.get(0).isMep)) {
                        sv.TSVp = 5.74;
                    }
                    if (sv.TSVp <= 10.0) {
                        sv.HCTp = Utils.arrondi(sv.TSVp / 1.64, 2);
                    }
                    else {
                        sv.HCTp = Utils.arrondi(sv.TSVp / 1.45, 2);
                    }
                }
                else if (!this.isMC) {
                    if (sv.TSVp < 7.0 && (sv.listTroncon.size() > 1 || !sv.listTroncon.get(0).isMep)) {
                        sv.TSVp = 7.0;
                    }
                    sv.HCTp = Utils.arrondi(sv.TSVp / 1.75, 2);
                }
                sv.SHV100p = 0.0;
                sv.SHV100Rp = 0.0;
                sv.STVp = 0.0;
                sv.SMEPp = 0.0;
                sv.NETAPEp = 0.0;
                for (final Troncon tro : sv.listTroncon) {
                    final ServiceVol serviceVol = sv;
                    serviceVol.SHV100p += tro.HV100p;
                    final ServiceVol serviceVol2 = sv;
                    serviceVol2.SHV100Rp += tro.HV100Rp;
                    final ServiceVol serviceVol3 = sv;
                    serviceVol3.STVp += tro.TVp;
                    final ServiceVol serviceVol4 = sv;
                    serviceVol4.SMEPp += tro.MEPp;
                    if (!tro.isMep) {
                        ++sv.NETAPEp;
                    }
                }
                sv.SHV100p = Utils.arrondi(sv.SHV100p, 2);
                sv.SHV100Rp = Utils.arrondi(sv.SHV100Rp, 2);
                sv.STVp = Utils.arrondi(sv.STVp, 2);
                sv.SMEPp = Utils.arrondi(sv.SMEPp, 2);
                sv.NETAPEp = Utils.arrondi(sv.NETAPEp, 2);
                if (sv.NETAPEp > 0.0) {
                    sv.TMEp = sv.STVp / sv.NETAPEp;
                    if (sv.TMEp < 1.0) {
                        sv.TMEp = 1.0;
                    }
                    sv.TMEp = Utils.arrondi(sv.TMEp, 2);
                    sv.CMTp = 70.0 / (21.0 * sv.TMEp + 30.0);
                    if (sv.CMTp < 1.0) {
                        sv.CMTp = 1.0;
                    }
                    sv.CMTp = Utils.arrondi(sv.CMTp, 4);
                }
                else {
                    sv.TMEp = 0.0;
                    sv.CMTp = 0.0;
                }
                for (final Troncon troncon : sv.listTroncon) {
                    if (!troncon.isMep) {
                        troncon.HCNp = Utils.arrondi(0.5 * troncon.TVNp, 3);
                        troncon.HCNp = Utils.arrondi(troncon.HCNp * sv.CMTp, 4);
                        if (!troncon.isTronconAChevalP) {
                            continue;
                        }
                        final double kp1 = Utils.arrondi(troncon.TVp1 / troncon.TVp, 4);
                        troncon.HCNp1 = Utils.arrondi(0.5 * troncon.TVNp * kp1, 3);
                        troncon.HCNp1 = Utils.arrondi(troncon.HCNp1 * sv.CMTp, 4);
                        final double kp2 = Utils.arrondi(troncon.TVp2 / troncon.TVp, 4);
                        troncon.HCNp2 = Utils.arrondi(0.5 * troncon.TVNp * kp2, 3);
                        troncon.HCNp2 = Utils.arrondi(troncon.HCNp2 * sv.CMTp, 4);
                    }
                }
                if (!this.isBP) {
                    sv.HCVp = Utils.arrondi(sv.SHV100p * sv.CMTp, 4) + Utils.arrondi(sv.SMEPp / 2.0, 4);
                    sv.HCVRp = Utils.arrondi(sv.SHV100Rp * sv.CMTp, 4) + Utils.arrondi(sv.SMEPp / 2.0, 4);
                }
                else {
                    sv.HCVp = Utils.arrondi(sv.SHV100p, 4) + Utils.arrondi(sv.SMEPp / 2.0, 4);
                    sv.HCVRp = Utils.arrondi(sv.SHV100Rp * sv.CMTp, 4) + Utils.arrondi(sv.SMEPp / 2.0, 4);
                }
                sv.HCVp = Utils.arrondi(sv.HCVp, 2);
                sv.HCVRp = Utils.arrondi(sv.HCVRp, 2);
                sv.H1p = Math.max(sv.HCVp, sv.HCTp);
                sv.H1Rp = Math.max(sv.HCVRp, sv.HCTp);
            }
            rotation.debTAp = rotation.listSV.get(0).debTSVp;
            rotation.finTAp = rotation.listSV.get(rotation.listSV.size() - 1).finTSVp;
            if (!rotation.isOD) {
                rotation.TAp = Utils.arrondi((rotation.finTAp - rotation.debTAp) / 3600000.0, 2);
            }
            else {
                rotation.TAp = 0.0;
            }
            caldeb.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            calfin.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            caldeb.setTime(rotation.debutUTCD);
            caldeb.set(11, 0);
            caldeb.set(12, 0);
            caldeb.set(13, 0);
            caldeb.set(14, 0);
            calfin.setTime(rotation.finUTCD);
            calfin.set(11, 23);
            calfin.set(12, 59);
            calfin.set(13, 59);
            calfin.set(14, 1000);
            rotation.NJEp = (double)Math.round((calfin.getTimeInMillis() - caldeb.getTimeInMillis()) / 8.64E7);
            if (rotation.isOD) {
                rotation.HCAp = 0.0;
            }
            else if (this.isMC) {
                rotation.HCAp = 4.0 * rotation.NJEp;
            }
            else if (!this.isMC) {
                rotation.HCAp = rotation.TAp * 5.0 / 24.0;
            }
            rotation.HCAp = Utils.arrondi(rotation.HCAp, 2);
            rotation.SH1p = 0.0;
            rotation.SH1Rp = 0.0;
            for (final ServiceVol sv : rotation.listSV) {
                final Rotation rotation2 = rotation;
                rotation2.SH1p += sv.H1p;
                final Rotation rotation3 = rotation;
                rotation3.SH1Rp += sv.H1Rp;
            }
            rotation.SH1p = Utils.arrondi(rotation.SH1p, 2);
            rotation.SH1Rp = Utils.arrondi(rotation.SH1Rp, 2);
            rotation.H2p = Math.max(rotation.HCAp, rotation.SH1p);
            rotation.H2Rp = Math.max(rotation.HCAp, rotation.SH1Rp);
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            final ServiceVol firstSV = rotation.listSV.get(0);
            final ServiceVol lastSV = rotation.listSV.get(rotation.listSV.size() - 1);
            final Troncon firstTro = firstSV.listTroncon.get(0);
            final Troncon lastTro = lastSV.listTroncon.get(lastSV.listTroncon.size() - 1);
            caldeb.setTimeZone(TimeZone.getTimeZone("UTC"));
            caldeb.setTimeInMillis(firstTro.DEPp);
            calfin.setTimeZone(TimeZone.getTimeZone("UTC"));
            calfin.setTimeInMillis(lastTro.ARRp);
            final int moisdeb2 = caldeb.get(2);
            final int moisfin2 = calfin.get(2);
            rotation.STVp = 0.0;
            rotation.STVp1 = 0.0;
            rotation.STVp2 = 0.0;
            if (moisdeb2 == moisfin2) {
                rotation.isRotAChevalP = false;
                for (final ServiceVol sv2 : rotation.listSV) {
                    for (final Troncon troncon2 : sv2.listTroncon) {
                        final Rotation rotation4 = rotation;
                        rotation4.STVp += Utils.arrondi(troncon2.TVp, 2) + Utils.arrondi(troncon2.MEPp / 2.0, 2);
                        rotation.STVp = Utils.arrondi(rotation.STVp, 2);
                    }
                }
            }
            else {
                rotation.isRotAChevalP = true;
                for (final ServiceVol sv2 : rotation.listSV) {
                    for (final Troncon troncon2 : sv2.listTroncon) {
                        final Rotation rotation5 = rotation;
                        rotation5.STVp += Utils.arrondi(troncon2.TVp, 2) + Utils.arrondi(troncon2.MEPp / 2.0, 2);
                        rotation.STVp = Utils.arrondi(rotation.STVp, 2);
                        caldeb.setTimeInMillis(troncon2.DEPp);
                        calfin.setTimeInMillis(troncon2.ARRp);
                        final int moisdebtro = caldeb.get(2);
                        final int moisfintro = calfin.get(2);
                        if (moisfintro == moisdeb2) {
                            final Rotation rotation6 = rotation;
                            rotation6.STVp1 += Utils.arrondi(troncon2.TVp, 2) + Utils.arrondi(troncon2.MEPp / 2.0, 2);
                            rotation.STVp1 = Utils.arrondi(rotation.STVp1, 2);
                        }
                        else if (moisdebtro == moisfin2) {
                            final Rotation rotation7 = rotation;
                            rotation7.STVp2 += Utils.arrondi(troncon2.TVp, 2) + Utils.arrondi(troncon2.MEPp / 2.0, 2);
                            rotation.STVp2 = Utils.arrondi(rotation.STVp2, 2);
                        }
                        else {
                            cal.setTimeInMillis(troncon2.ARRp);
                            cal.set(11, 0);
                            cal.set(12, 0);
                            cal.set(13, 0);
                            cal.set(14, 0);
                            if (!troncon2.isMep) {
                                final Rotation rotation8 = rotation;
                                rotation8.STVp1 += Utils.arrondi((cal.getTimeInMillis() - caldeb.getTimeInMillis()) / 3600000.0, 2);
                                final Rotation rotation9 = rotation;
                                rotation9.STVp2 += Utils.arrondi((calfin.getTimeInMillis() - cal.getTimeInMillis()) / 3600000.0, 2);
                            }
                            else {
                                final Rotation rotation10 = rotation;
                                rotation10.STVp1 += Utils.arrondi((cal.getTimeInMillis() - caldeb.getTimeInMillis()) / 7200000.0, 2);
                                final Rotation rotation11 = rotation;
                                rotation11.STVp2 += Utils.arrondi((calfin.getTimeInMillis() - cal.getTimeInMillis()) / 7200000.0, 2);
                            }
                            rotation.STVp1 = Utils.arrondi(rotation.STVp1, 2);
                            rotation.STVp2 = Utils.arrondi(rotation.STVp2, 2);
                        }
                    }
                }
            }
            if (!rotation.isOD) {
                rotation.IKVallerP = firstTro.from;
                rotation.IKVretourP = lastTro.to;
            }
        }
    }
    
    private void calculHCr_ActivitesVol() {
        final GregorianCalendar cal = new GregorianCalendar();
        final GregorianCalendar caldeb = new GregorianCalendar();
        final GregorianCalendar calfin = new GregorianCalendar();
        double bonus;
        int infNuit;
        int supNuit;
        if (!this.isMC) {
            bonus = 0.58;
            infNuit = 6;
            supNuit = 18;
        }
        else {
            if (this.isBP) {
                bonus = 0.25;
            }
            else {
                bonus = 0.25;
            }
            infNuit = 9;
            supNuit = 21;
        }
        for (final Rotation rotation : this.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                final Troncon firstTroSv = sv.listTroncon.get(0);
                final SimpleTimeZone zoneFirstTroSv = new SimpleTimeZone(firstTroSv.LAGfromMillis, "");
                for (final Troncon troncon : sv.listTroncon) {
                    if (!troncon.isTVreal) {
                        troncon.DEPr = troncon.DEPp;
                        troncon.TVr = troncon.TVp;
                        if (!rotation.isOD) {
                            troncon.MEPr = troncon.MEPp;
                        }
                        else {
                            troncon.MEPr = Utils.arrondi((troncon.finUTCD.getTime() - troncon.debutUTCD.getTime()) / 3600000.0, 2);
                        }
                    }
                    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                    cal.setTimeInMillis(troncon.DEPr);
                    troncon.DEBr = cal.get(11) + cal.get(12) / 60.0;
                    troncon.DEBr = Math.rint(troncon.DEBr * 100.0) / 100.0;
                    troncon.ARRr = troncon.DEPr + (long)Utils.arrondi(troncon.TVr * 60.0, 0) * 60L * 1000L + (long)Utils.arrondi(troncon.MEPr * 60.0, 0) * 60L * 1000L;
                    troncon.FINr = Utils.arrondi(troncon.DEBr + troncon.TVr + troncon.MEPr, 2);
                    if (sv.numSV == 1 && troncon.numTroncon == 1) {
                        rotation.DEPr = troncon.DEPr;
                    }
                    if (sv.numSV == rotation.listSV.size() && troncon.numTroncon == sv.listTroncon.size()) {
                        rotation.ARRr = troncon.ARRr;
                    }
                    if (rotation.isOD) {
                        troncon.MEPr = 0.0;
                    }
                    if (!troncon.isMep) {
                        if (troncon.TVref > troncon.TVr) {
                            troncon.HV100r = troncon.TVref;
                        }
                        else {
                            troncon.HV100r = troncon.TVr;
                        }
                        troncon.HV100Rr = Utils.arrondi(troncon.TVref + bonus, 2);
                    }
                    else {
                        troncon.HV100r = 0.0;
                        troncon.HV100Rr = 0.0;
                    }
                    troncon.TVNr = 0.0;
                    if (this.isMC) {
                        caldeb.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                        calfin.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    }
                    else {
                        caldeb.setTimeZone(zoneFirstTroSv);
                        calfin.setTimeZone(zoneFirstTroSv);
                    }
                    if (!troncon.isMep) {
                        caldeb.setTimeInMillis(troncon.DEPr);
                        calfin.setTimeInMillis(troncon.DEPr + (long)(bonus * 60.0 * 60.0 * 1000.0) + (long)(troncon.TVref * 60.0 * 60.0 * 1000.0));
                        troncon.TVNr = (double)Utils.calculNuit(caldeb, calfin, supNuit, infNuit);
                        troncon.TVNr = Utils.arrondi(troncon.TVNr / 3600000.0, 2);
                    }
                    cal.setTimeZone(TimeZone.getTimeZone("UTC"));
                    caldeb.setTimeZone(TimeZone.getTimeZone("UTC"));
                    caldeb.setTimeInMillis(troncon.DEPr);
                    calfin.setTimeZone(TimeZone.getTimeZone("UTC"));
                    calfin.setTimeInMillis(troncon.ARRr);
                    final int moisdeb = caldeb.get(2);
                    final int moisfin = calfin.get(2);
                    if (moisdeb == moisfin) {
                        troncon.isTronconAChevalR = false;
                    }
                    else {
                        troncon.isTronconAChevalR = true;
                        cal.setTimeInMillis(troncon.ARRr);
                        cal.set(11, 0);
                        cal.set(12, 0);
                        cal.set(13, 0);
                        cal.set(14, 0);
                        troncon.TVr1 = Utils.arrondi((cal.getTimeInMillis() - caldeb.getTimeInMillis()) / 3600000.0, 2);
                        troncon.TVr2 = Utils.arrondi((calfin.getTimeInMillis() - cal.getTimeInMillis()) / 3600000.0, 2);
                    }
                }
                sv.debTSVr = sv.listTroncon.get(0).DEPr - 3600000L;
                sv.finTSVr = sv.listTroncon.get(sv.listTroncon.size() - 1).ARRr + 1800000L;
                if (rotation.isCourrierCroise && sv.numSV == rotation.listSV.size()) {
                    sv.finTSVr += 3600000L;
                }
                if (sv.debTSVp < sv.debTSVr) {
                    sv.debTSVr = sv.debTSVp;
                }
                if (!rotation.isOD) {
                    sv.TSVr = Utils.arrondi((sv.finTSVr - sv.debTSVr) / 3600000.0, 2);
                }
                else {
                    sv.TSVr = 0.0;
                }
                sv.TSVNr = 0.0;
                if (this.isMC) {
                    caldeb.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    calfin.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                }
                else {
                    caldeb.setTimeZone(zoneFirstTroSv);
                    calfin.setTimeZone(zoneFirstTroSv);
                }
                caldeb.setTimeInMillis(sv.debTSVr);
                calfin.setTimeInMillis(sv.finTSVr);
                sv.TSVNr = (double)Utils.calculNuit(caldeb, calfin, supNuit, infNuit);
                sv.TSVNr = Utils.arrondi(sv.TSVNr / 3600000.0, 2);
                if (this.isMC) {
                    if (sv.TSVr < 5.74 && (sv.listTroncon.size() > 1 || !sv.listTroncon.get(0).isMep)) {
                        sv.TSVr = 5.74;
                    }
                    if (sv.TSVr <= 10.0) {
                        sv.HCTr = Utils.arrondi(sv.TSVr / 1.64, 2);
                    }
                    else {
                        sv.HCTr = Utils.arrondi(sv.TSVr / 1.45, 2);
                    }
                }
                else if (!this.isMC) {
                    if (sv.TSVr < 7.0 && (sv.listTroncon.size() > 1 || !sv.listTroncon.get(0).isMep)) {
                        sv.TSVr = 7.0;
                    }
                    sv.HCTr = Utils.arrondi(sv.TSVr / 1.75, 2);
                }
                sv.SHV100r = 0.0;
                sv.SHV100Rr = 0.0;
                sv.STVr = 0.0;
                sv.SMEPr = 0.0;
                sv.NETAPEr = 0.0;
                for (final Troncon troncon : sv.listTroncon) {
                    final ServiceVol serviceVol = sv;
                    serviceVol.SHV100r += troncon.HV100r;
                    final ServiceVol serviceVol2 = sv;
                    serviceVol2.SHV100Rr += troncon.HV100Rr;
                    final ServiceVol serviceVol3 = sv;
                    serviceVol3.STVr += troncon.TVr;
                    final ServiceVol serviceVol4 = sv;
                    serviceVol4.SMEPr += troncon.MEPr;
                    if (!troncon.isMep) {
                        ++sv.NETAPEr;
                    }
                }
                sv.SHV100r = Utils.arrondi(sv.SHV100r, 2);
                sv.SHV100Rr = Utils.arrondi(sv.SHV100Rr, 2);
                sv.STVr = Utils.arrondi(sv.STVr, 2);
                sv.SMEPr = Utils.arrondi(sv.SMEPr, 2);
                sv.NETAPEr = Utils.arrondi(sv.NETAPEr, 2);
                if (sv.NETAPEr > 0.0) {
                    sv.TMEr = sv.STVr / sv.NETAPEr;
                    if (sv.TMEr < 1.0) {
                        sv.TMEr = 1.0;
                    }
                    sv.TMEr = Utils.arrondi(sv.TMEr, 2);
                    sv.CMTr = 70.0 / (21.0 * sv.TMEr + 30.0);
                    if (sv.CMTr < 1.0) {
                        sv.CMTr = 1.0;
                    }
                    sv.CMTr = Utils.arrondi(sv.CMTr, 4);
                }
                else {
                    sv.TMEr = 0.0;
                    sv.CMTr = 0.0;
                }
                for (final Troncon troncon : sv.listTroncon) {
                    if (!troncon.isMep) {
                        troncon.HCNr = Utils.arrondi(0.5 * troncon.TVNr, 3);
                        troncon.HCNr = Utils.arrondi(troncon.HCNr * sv.CMTr, 4);
                        if (!troncon.isTronconAChevalR) {
                            continue;
                        }
                        final double kr1 = Utils.arrondi(troncon.TVr1 / troncon.TVr, 4);
                        troncon.HCNr1 = Utils.arrondi(0.5 * troncon.TVNr * kr1, 3);
                        troncon.HCNr1 = Utils.arrondi(troncon.HCNr1 * sv.CMTr, 4);
                        final double kr2 = Utils.arrondi(troncon.TVr2 / troncon.TVr, 4);
                        troncon.HCNr2 = Utils.arrondi(0.5 * troncon.TVNr * kr2, 3);
                        troncon.HCNr2 = Utils.arrondi(troncon.HCNr2 * sv.CMTr, 4);
                    }
                }
                if (!this.isBP) {
                    sv.HCVr = Utils.arrondi(sv.SHV100r * sv.CMTr, 4) + Utils.arrondi(sv.SMEPr / 2.0, 4);
                    sv.HCVRr = Utils.arrondi(sv.SHV100Rr * sv.CMTr, 4) + Utils.arrondi(sv.SMEPr / 2.0, 4);
                }
                else {
                    sv.HCVr = Utils.arrondi(sv.SHV100r, 4) + Utils.arrondi(sv.SMEPr / 2.0, 4);
                    sv.HCVRr = Utils.arrondi(sv.SHV100Rr * sv.CMTr, 4) + Utils.arrondi(sv.SMEPr / 2.0, 4);
                }
                sv.HCVr = Utils.arrondi(sv.HCVr, 2);
                sv.HCVRr = Utils.arrondi(sv.HCVRr, 2);
                sv.H1r = Math.max(sv.HCVr, sv.HCTr);
                sv.H1Rr = Math.max(sv.HCVRr, sv.HCTr);
            }
            rotation.debTAr = rotation.listSV.get(0).debTSVr;
            rotation.finTAr = rotation.listSV.get(rotation.listSV.size() - 1).finTSVr;
            if (!rotation.isOD) {
                rotation.TAr = Utils.arrondi((rotation.finTAr - rotation.debTAr) / 3600000.0, 2);
            }
            else {
                rotation.TAr = 0.0;
            }
            cal.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            caldeb.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            calfin.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            caldeb.setTimeInMillis(rotation.debTAr);
            caldeb.add(14, 12001);
            caldeb.set(11, 0);
            caldeb.set(12, 0);
            caldeb.set(13, 0);
            caldeb.set(14, 0);
            calfin.setTimeInMillis(rotation.finTAr);
            calfin.add(14, -12001);
            cal.setTimeInMillis(rotation.finTAp);
            cal.add(14, -12001);
            if (rotation.finTAr > rotation.finTAp && !this.isBP && calfin.get(5) != cal.get(5) && calfin.get(11) == 0) {
                calfin.set(11, 0);
                calfin.set(12, 0);
                calfin.set(13, 0);
                calfin.set(14, 0);
            }
            else if (rotation.finTAr > rotation.finTAp && this.isBP && calfin.get(5) != cal.get(5) && calfin.get(11) == 0 && calfin.get(12) < 45) {
                calfin.set(11, 0);
                calfin.set(12, 0);
                calfin.set(13, 0);
                calfin.set(14, 0);
            }
            else {
                calfin.set(11, 23);
                calfin.set(12, 59);
                calfin.set(13, 59);
                calfin.set(14, 1000);
            }
            rotation.NJEr = (double)Math.round((calfin.getTimeInMillis() - caldeb.getTimeInMillis()) / 8.64E7);
            if (rotation.isOD) {
                rotation.HCAr = 0.0;
            }
            else if (this.isMC) {
                rotation.HCAr = 4.0 * rotation.NJEr;
            }
            else if (!this.isMC) {
                rotation.HCAr = rotation.TAr * 5.0 / 24.0;
            }
            rotation.HCAr = Utils.arrondi(rotation.HCAr, 2);
            rotation.SH1r = 0.0;
            rotation.SH1Rr = 0.0;
            for (final ServiceVol sv : rotation.listSV) {
                final Rotation rotation2 = rotation;
                rotation2.SH1r += sv.H1r;
                final Rotation rotation3 = rotation;
                rotation3.SH1Rr += sv.H1Rr;
            }
            rotation.SH1r = Utils.arrondi(rotation.SH1r, 2);
            rotation.SH1Rr = Utils.arrondi(rotation.SH1Rr, 2);
            rotation.H2r = Math.max(rotation.HCAr, rotation.SH1r);
            rotation.H2Rr = Math.max(rotation.HCAr, rotation.SH1Rr);
            cal.setTimeZone(TimeZone.getTimeZone("UTC"));
            final ServiceVol firstSV = rotation.listSV.get(0);
            final ServiceVol lastSV = rotation.listSV.get(rotation.listSV.size() - 1);
            final Troncon firstTro = firstSV.listTroncon.get(0);
            final Troncon lastTro = lastSV.listTroncon.get(lastSV.listTroncon.size() - 1);
            caldeb.setTimeZone(TimeZone.getTimeZone("UTC"));
            caldeb.setTimeInMillis(firstTro.DEPr);
            calfin.setTimeZone(TimeZone.getTimeZone("UTC"));
            calfin.setTimeInMillis(lastTro.ARRr);
            final int moisdeb2 = caldeb.get(2);
            final int moisfin2 = calfin.get(2);
            rotation.STVr = 0.0;
            rotation.STVr1 = 0.0;
            rotation.STVr2 = 0.0;
            if (moisdeb2 == moisfin2) {
                rotation.isRotAChevalR = false;
                for (final ServiceVol sv2 : rotation.listSV) {
                    for (final Troncon troncon2 : sv2.listTroncon) {
                        final Rotation rotation4 = rotation;
                        rotation4.STVr += Utils.arrondi(troncon2.TVr, 2) + Utils.arrondi(troncon2.MEPr / 2.0, 2);
                        rotation.STVr = Utils.arrondi(rotation.STVr, 2);
                    }
                }
            }
            else {
                rotation.isRotAChevalR = true;
                for (final ServiceVol sv2 : rotation.listSV) {
                    for (final Troncon troncon2 : sv2.listTroncon) {
                        final Rotation rotation5 = rotation;
                        rotation5.STVr += Utils.arrondi(troncon2.TVr, 2) + Utils.arrondi(troncon2.MEPr / 2.0, 2);
                        rotation.STVr = Utils.arrondi(rotation.STVr, 2);
                        caldeb.setTimeInMillis(troncon2.DEPr);
                        calfin.setTimeInMillis(troncon2.ARRr);
                        final int moisdebtro = caldeb.get(2);
                        final int moisfintro = calfin.get(2);
                        if (moisfintro == moisdeb2) {
                            final Rotation rotation6 = rotation;
                            rotation6.STVr1 += Utils.arrondi(troncon2.TVr, 2) + Utils.arrondi(troncon2.MEPr / 2.0, 2);
                            rotation.STVr1 = Utils.arrondi(rotation.STVr1, 2);
                        }
                        else if (moisdebtro == moisfin2) {
                            final Rotation rotation7 = rotation;
                            rotation7.STVr2 += Utils.arrondi(troncon2.TVr, 2) + Utils.arrondi(troncon2.MEPr / 2.0, 2);
                            rotation.STVr2 = Utils.arrondi(rotation.STVr2, 2);
                        }
                        else {
                            cal.setTimeInMillis(troncon2.ARRr);
                            cal.set(11, 0);
                            cal.set(12, 0);
                            cal.set(13, 0);
                            cal.set(14, 0);
                            if (!troncon2.isMep) {
                                final Rotation rotation8 = rotation;
                                rotation8.STVr1 += Utils.arrondi((cal.getTimeInMillis() - caldeb.getTimeInMillis()) / 3600000.0, 2);
                                final Rotation rotation9 = rotation;
                                rotation9.STVr2 += Utils.arrondi((calfin.getTimeInMillis() - cal.getTimeInMillis()) / 3600000.0, 2);
                            }
                            else {
                                final Rotation rotation10 = rotation;
                                rotation10.STVr1 += Utils.arrondi((cal.getTimeInMillis() - caldeb.getTimeInMillis()) / 7200000.0, 2);
                                final Rotation rotation11 = rotation;
                                rotation11.STVr2 += Utils.arrondi((calfin.getTimeInMillis() - cal.getTimeInMillis()) / 7200000.0, 2);
                            }
                            rotation.STVr1 = Utils.arrondi(rotation.STVr1, 2);
                            rotation.STVr2 = Utils.arrondi(rotation.STVr2, 2);
                        }
                    }
                }
            }
            if (!rotation.isOD) {
                rotation.IKVallerR = firstTro.from;
                rotation.IKVretourR = lastTro.to;
            }
        }
    }
    
    private void calculIRp_MC_ActivitesVol() {
        final HashMap<String, Escale> escales = this.importBaseEscales();
        for (final Rotation rotation : this.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    troncon.indemFrom.nIRMidiP = 0;
                    troncon.indemFrom.nIRSoirP = 0;
                    troncon.indemTo.nIRMidiP = 0;
                    troncon.indemTo.nIRSoirP = 0;
                    troncon.indemFrom.nMFMidiP = 0;
                    troncon.indemFrom.nMFSoirP = 0;
                    troncon.indemTo.nMFMidiP = 0;
                    troncon.indemTo.nMFSoirP = 0;
                    troncon.nDecP = 0;
                }
            }
        }
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        for (final Rotation rotation2 : this.listRotation) {
            if (rotation2.isOD) {
                continue;
            }
            final ArrayList<Long> listIRmidi = new ArrayList<Long>();
            final ArrayList<Long> listIRsoir = new ArrayList<Long>();
            cal.setTimeInMillis(rotation2.debTAp);
            cal.set(11, 11);
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            while (cal.getTimeInMillis() <= rotation2.finTAp) {
                if (rotation2.debTAp <= cal.getTimeInMillis() + 10800000L && rotation2.finTAp >= cal.getTimeInMillis() + 3600000L) {
                    listIRmidi.add(cal.getTimeInMillis());
                }
                cal.add(5, 1);
            }
            cal.setTimeInMillis(rotation2.debTAp);
            cal.set(11, 18);
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            while (cal.getTimeInMillis() <= rotation2.finTAp) {
                if (rotation2.debTAp <= cal.getTimeInMillis() + 10800000L && rotation2.finTAp >= cal.getTimeInMillis() + 3600000L) {
                    listIRsoir.add(cal.getTimeInMillis());
                }
                cal.add(5, 1);
            }
            for (final long top : listIRmidi) {
                boolean flagIRmidi = false;
                for (int j = 0; j < rotation2.listSV.size() && !flagIRmidi; ++j) {
                    final ServiceVol sv2 = rotation2.listSV.get(j);
                    final Troncon firstTroSv = sv2.listTroncon.get(0);
                    cal.setTimeInMillis(firstTroSv.DEPp);
                    cal.set(11, 11);
                    cal.set(12, 0);
                    cal.set(13, 0);
                    if (cal.getTimeInMillis() == top) {
                        for (int k = 0; k < sv2.listTroncon.size(); ++k) {
                            if (flagIRmidi) {
                                break;
                            }
                            final Troncon troncon2 = sv2.listTroncon.get(k);
                            if (troncon2.DEPp > top && k == 0) {
                                flagIRmidi = true;
                                final Indem indemFrom = troncon2.indemFrom;
                                ++indemFrom.nIRMidiP;
                                final Indem indemFrom2 = troncon2.indemFrom;
                                ++indemFrom2.nMFMidiP;
                            }
                            else if (troncon2.DEPp > top && k > 0) {
                                flagIRmidi = true;
                                final Troncon troPrecedent = sv2.listTroncon.get(k - 1);
                                final Indem indemTo = troPrecedent.indemTo;
                                ++indemTo.nIRMidiP;
                                if (troncon2.DEPp - troPrecedent.ARRp >= 10800000L) {
                                    final Indem indemTo2 = troPrecedent.indemTo;
                                    ++indemTo2.nMFMidiP;
                                }
                                else if (escales.containsKey(troPrecedent.to) && escales.get(troPrecedent.to).pays.equals("France")) {
                                    final Indem indemTo3 = troPrecedent.indemTo;
                                    ++indemTo3.nMFMidiP;
                                }
                            }
                            else if (k == sv2.listTroncon.size() - 1) {
                                flagIRmidi = true;
                                final Indem indemTo4 = troncon2.indemTo;
                                ++indemTo4.nIRMidiP;
                                final Indem indemTo5 = troncon2.indemTo;
                                ++indemTo5.nMFMidiP;
                            }
                        }
                    }
                    else if (cal.getTimeInMillis() > top) {
                        flagIRmidi = true;
                        final ServiceVol svPrecedent = rotation2.listSV.get(j - 1);
                        final Troncon lastTroSvPrecedent = svPrecedent.listTroncon.get(svPrecedent.listTroncon.size() - 1);
                        final Indem indemTo6 = lastTroSvPrecedent.indemTo;
                        ++indemTo6.nIRMidiP;
                        final Indem indemTo7 = lastTroSvPrecedent.indemTo;
                        ++indemTo7.nMFMidiP;
                    }
                }
            }
            for (final long top2 : listIRsoir) {
                boolean flagIRsoir = false;
                for (int i = 0; i < rotation2.listSV.size() && !flagIRsoir; ++i) {
                    final ServiceVol sv3 = rotation2.listSV.get(i);
                    final Troncon firstTroSv2 = sv3.listTroncon.get(0);
                    cal.setTimeInMillis(firstTroSv2.DEPp);
                    cal.set(11, 18);
                    cal.set(12, 0);
                    cal.set(13, 0);
                    if (cal.getTimeInMillis() == top2) {
                        if (sv3.isDecoucher) {
                            flagIRsoir = true;
                            final Troncon lastTroSv = sv3.listTroncon.get(sv3.listTroncon.size() - 1);
                            final Indem indemTo8 = lastTroSv.indemTo;
                            ++indemTo8.nIRSoirP;
                            final Indem indemTo9 = lastTroSv.indemTo;
                            ++indemTo9.nMFSoirP;
                            final Troncon troncon4 = lastTroSv;
                            ++troncon4.nDecP;
                        }
                        else if (!sv3.isDecoucher) {
                            for (int l = 0; l < sv3.listTroncon.size(); ++l) {
                                if (flagIRsoir) {
                                    break;
                                }
                                final Troncon troncon3 = sv3.listTroncon.get(l);
                                if (troncon3.DEPp > top2 && l == 0) {
                                    flagIRsoir = true;
                                    final Indem indemFrom3 = troncon3.indemFrom;
                                    ++indemFrom3.nIRSoirP;
                                    final Indem indemFrom4 = troncon3.indemFrom;
                                    ++indemFrom4.nMFSoirP;
                                }
                                else if (troncon3.DEPp > top2 && l > 0) {
                                    flagIRsoir = true;
                                    final Troncon troPrecedent2 = sv3.listTroncon.get(l - 1);
                                    final Indem indemTo10 = troPrecedent2.indemTo;
                                    ++indemTo10.nIRSoirP;
                                    if (troncon3.DEPp - troPrecedent2.ARRp >= 10800000L) {
                                        final Indem indemTo11 = troPrecedent2.indemTo;
                                        ++indemTo11.nMFSoirP;
                                    }
                                    else if (escales.containsKey(troPrecedent2.to) && escales.get(troPrecedent2.to).pays.equals("France")) {
                                        final Indem indemTo12 = troPrecedent2.indemTo;
                                        ++indemTo12.nMFSoirP;
                                    }
                                }
                                else if (l == sv3.listTroncon.size() - 1) {
                                    flagIRsoir = true;
                                    final Indem indemTo13 = troncon3.indemTo;
                                    ++indemTo13.nIRSoirP;
                                    final Indem indemTo14 = troncon3.indemTo;
                                    ++indemTo14.nMFSoirP;
                                }
                            }
                        }
                    }
                    else if (cal.getTimeInMillis() > top2) {
                        flagIRsoir = true;
                        final ServiceVol svPrecedent2 = rotation2.listSV.get(i - 1);
                        final Troncon lastTroSvPrecedent2 = svPrecedent2.listTroncon.get(svPrecedent2.listTroncon.size() - 1);
                        final Indem indemTo15 = lastTroSvPrecedent2.indemTo;
                        ++indemTo15.nIRSoirP;
                        final Indem indemTo16 = lastTroSvPrecedent2.indemTo;
                        ++indemTo16.nMFSoirP;
                        final Troncon troncon5 = lastTroSvPrecedent2;
                        ++troncon5.nDecP;
                    }
                }
            }
        }
    }
    
    private void calculIRr_MC_ActivitesVol() {
        final HashMap<String, Escale> escales = this.importBaseEscales();
        for (final Rotation rotation : this.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    troncon.indemFrom.nIRMidiR = 0;
                    troncon.indemFrom.nIRSoirR = 0;
                    troncon.indemTo.nIRMidiR = 0;
                    troncon.indemTo.nIRSoirR = 0;
                    troncon.indemFrom.nMFMidiR = 0;
                    troncon.indemFrom.nMFSoirR = 0;
                    troncon.indemTo.nMFMidiR = 0;
                    troncon.indemTo.nMFSoirR = 0;
                    troncon.nDecR = 0;
                }
            }
        }
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        for (final Rotation rotation2 : this.listRotation) {
            if (rotation2.isOD) {
                continue;
            }
            final long debTAr = rotation2.debTAr;
            final long finTAr = Math.max(rotation2.finTAr, rotation2.finTAp);
            final ArrayList<Long> listIRmidi = new ArrayList<Long>();
            final ArrayList<Long> listIRsoir = new ArrayList<Long>();
            cal.setTimeInMillis(debTAr);
            cal.set(11, 11);
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            while (cal.getTimeInMillis() <= finTAr) {
                if (debTAr <= cal.getTimeInMillis() + 10800000L && finTAr >= cal.getTimeInMillis() + 3600000L) {
                    listIRmidi.add(cal.getTimeInMillis());
                }
                cal.add(5, 1);
            }
            cal.setTimeInMillis(debTAr);
            cal.set(11, 18);
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            while (cal.getTimeInMillis() <= finTAr) {
                if (debTAr <= cal.getTimeInMillis() + 10800000L && finTAr >= cal.getTimeInMillis() + 3600000L) {
                    listIRsoir.add(cal.getTimeInMillis());
                }
                cal.add(5, 1);
            }
            for (final long top : listIRmidi) {
                boolean flagIRmidi = false;
                for (int j = 0; j < rotation2.listSV.size() && !flagIRmidi; ++j) {
                    final ServiceVol sv2 = rotation2.listSV.get(j);
                    final Troncon firstTroSv = sv2.listTroncon.get(0);
                    cal.setTimeInMillis(firstTroSv.DEPr);
                    cal.set(11, 11);
                    cal.set(12, 0);
                    cal.set(13, 0);
                    if (cal.getTimeInMillis() == top) {
                        for (int k = 0; k < sv2.listTroncon.size(); ++k) {
                            if (flagIRmidi) {
                                break;
                            }
                            final Troncon troncon2 = sv2.listTroncon.get(k);
                            if (troncon2.DEPr > top && k == 0) {
                                flagIRmidi = true;
                                final Indem indemFrom = troncon2.indemFrom;
                                ++indemFrom.nIRMidiR;
                                final Indem indemFrom2 = troncon2.indemFrom;
                                ++indemFrom2.nMFMidiR;
                            }
                            else if (troncon2.DEPr > top && k > 0) {
                                flagIRmidi = true;
                                final Troncon troPrecedent = sv2.listTroncon.get(k - 1);
                                final Indem indemTo = troPrecedent.indemTo;
                                ++indemTo.nIRMidiR;
                                if (troncon2.DEPr - troPrecedent.ARRr >= 10800000L) {
                                    final Indem indemTo2 = troPrecedent.indemTo;
                                    ++indemTo2.nMFMidiR;
                                }
                                else if (escales.containsKey(troPrecedent.to) && escales.get(troPrecedent.to).pays.equals("France")) {
                                    final Indem indemTo3 = troPrecedent.indemTo;
                                    ++indemTo3.nMFMidiR;
                                }
                            }
                            else if (k == sv2.listTroncon.size() - 1) {
                                flagIRmidi = true;
                                final Indem indemTo4 = troncon2.indemTo;
                                ++indemTo4.nIRMidiR;
                                final Indem indemTo5 = troncon2.indemTo;
                                ++indemTo5.nMFMidiR;
                            }
                        }
                    }
                    else if (cal.getTimeInMillis() > top) {
                        flagIRmidi = true;
                        final ServiceVol svPrecedent = rotation2.listSV.get(j - 1);
                        final Troncon lastTroSvPrecedent = svPrecedent.listTroncon.get(svPrecedent.listTroncon.size() - 1);
                        final Indem indemTo6 = lastTroSvPrecedent.indemTo;
                        ++indemTo6.nIRMidiR;
                        final Indem indemTo7 = lastTroSvPrecedent.indemTo;
                        ++indemTo7.nMFMidiR;
                    }
                }
            }
            for (final long top2 : listIRsoir) {
                boolean flagIRsoir = false;
                for (int i = 0; i < rotation2.listSV.size() && !flagIRsoir; ++i) {
                    final ServiceVol sv3 = rotation2.listSV.get(i);
                    final Troncon firstTroSv2 = sv3.listTroncon.get(0);
                    cal.setTimeInMillis(firstTroSv2.DEPp);
                    cal.set(11, 18);
                    cal.set(12, 0);
                    cal.set(13, 0);
                    if (cal.getTimeInMillis() == top2) {
                        if (sv3.isDecoucher) {
                            flagIRsoir = true;
                            final Troncon lastTroSv = sv3.listTroncon.get(sv3.listTroncon.size() - 1);
                            final Indem indemTo8 = lastTroSv.indemTo;
                            ++indemTo8.nIRSoirR;
                            final Indem indemTo9 = lastTroSv.indemTo;
                            ++indemTo9.nMFSoirR;
                            final Troncon troncon3 = lastTroSv;
                            ++troncon3.nDecR;
                        }
                        else if (!sv3.isDecoucher) {
                            for (int l = 0; l < sv3.listTroncon.size(); ++l) {
                                if (flagIRsoir) {
                                    break;
                                }
                                final Troncon tro = sv3.listTroncon.get(l);
                                if (tro.DEPr > top2 && l == 0) {
                                    flagIRsoir = true;
                                    final Indem indemFrom3 = tro.indemFrom;
                                    ++indemFrom3.nIRSoirR;
                                    final Indem indemFrom4 = tro.indemFrom;
                                    ++indemFrom4.nMFSoirR;
                                }
                                else if (tro.DEPr > top2 && l > 0) {
                                    flagIRsoir = true;
                                    final Troncon troPrecedent2 = sv3.listTroncon.get(l - 1);
                                    final Indem indemTo10 = troPrecedent2.indemTo;
                                    ++indemTo10.nIRSoirR;
                                    if (tro.DEPr - troPrecedent2.ARRr >= 10800000L) {
                                        final Indem indemTo11 = troPrecedent2.indemTo;
                                        ++indemTo11.nMFSoirR;
                                    }
                                    else if (escales.containsKey(troPrecedent2.to) && escales.get(troPrecedent2.to).pays.equals("France")) {
                                        final Indem indemTo12 = troPrecedent2.indemTo;
                                        ++indemTo12.nMFSoirR;
                                    }
                                }
                                else if (l == sv3.listTroncon.size() - 1) {
                                    flagIRsoir = true;
                                    final Indem indemTo13 = tro.indemTo;
                                    ++indemTo13.nIRSoirR;
                                    final Indem indemTo14 = tro.indemTo;
                                    ++indemTo14.nMFSoirR;
                                }
                            }
                        }
                    }
                    else if (cal.getTimeInMillis() > top2) {
                        flagIRsoir = true;
                        final ServiceVol svPrecedent2 = rotation2.listSV.get(i - 1);
                        final Troncon lastTroSvPrecedent2 = svPrecedent2.listTroncon.get(svPrecedent2.listTroncon.size() - 1);
                        final Indem indemTo15 = lastTroSvPrecedent2.indemTo;
                        ++indemTo15.nIRSoirR;
                        final Indem indemTo16 = lastTroSvPrecedent2.indemTo;
                        ++indemTo16.nMFSoirR;
                        final Troncon troncon4 = lastTroSvPrecedent2;
                        ++troncon4.nDecR;
                    }
                }
            }
        }
    }
    
    private void calculDC_LC_ActivitesVol() {
        final GregorianCalendar cal = new GregorianCalendar();
        for (final Rotation rotation : this.listRotation) {
            if (rotation.isOD) {
                continue;
            }
            for (final ServiceVol sv : rotation.listSV) {
                final Troncon firstTroSv = sv.listTroncon.get(0);
                final Troncon lastTroSv = sv.listTroncon.get(sv.listTroncon.size() - 1);
                final long debTSVP = firstTroSv.debutUTCD.getTime() - 4500000L;
                final long finTSVP = lastTroSv.finUTCD.getTime() + 900000L;
                final long dureeTSVP = finTSVP - debTSVP;
                SimpleTimeZone tz = new SimpleTimeZone(firstTroSv.LAGfromMillis, "");
                cal.setTimeZone(tz);
                cal.setTimeInMillis(debTSVP);
                int h = cal.get(11);
                int m = cal.get(12);
                double debTSVPd = Utils.arrondi(h + m / 60.0, 2);
                double dureeTSVPd = Utils.arrondi(dureeTSVP / 3600000.0, 2);
                boolean isMultiTronconEscaleLongue;
                if (sv.listTroncon.size() > 1) {
                    for (int k = 0; k < sv.listTroncon.size() - 1; ++k) {
                        final Troncon tro = sv.listTroncon.get(k);
                        final Troncon nextTro = sv.listTroncon.get(k + 1);
                        final long tempsEscale = nextTro.debutUTCD.getTime() - tro.finUTCD.getTime();
                        if (tempsEscale > 6300000L) {
                            isMultiTronconEscaleLongue = true;
                            break;
                        }
                    }
                    isMultiTronconEscaleLongue = false;
                }
                else {
                    isMultiTronconEscaleLongue = false;
                }
                if (!isMultiTronconEscaleLongue) {
                    if (debTSVPd <= 1.0) {
                        if (dureeTSVPd >= 11.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd >= 1.0 && debTSVPd <= 5.0) {
                        final double lim = 12.0 - debTSVPd;
                        if (dureeTSVPd >= lim) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd >= 5.0 && debTSVPd <= 7.0) {
                        final double lim = 12.0 - debTSVPd;
                        if (dureeTSVPd >= 7.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else if (dureeTSVPd >= lim) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd >= 7.0 && debTSVPd <= 14.0) {
                        if (dureeTSVPd >= 7.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else if (dureeTSVPd >= 5.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd > 14.0 && debTSVPd <= 21.0) {
                        if (dureeTSVPd >= 7.0) {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = true;
                        }
                        else if (dureeTSVPd >= 5.0) {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = true;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd >= 21.0) {
                        if (dureeTSVPd >= 11.0) {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = true;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                }
                else if (isMultiTronconEscaleLongue) {
                    if (debTSVPd <= 1.0) {
                        if (dureeTSVPd >= 11.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd >= 1.0 && debTSVPd <= 5.0) {
                        final double lim = 12.0 - debTSVPd;
                        if (dureeTSVPd >= lim) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd >= 5.0 && debTSVPd <= 7.0) {
                        final double lim = 12.0 - debTSVPd;
                        if (dureeTSVPd >= 7.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else if (dureeTSVPd >= lim) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd >= 7.0 && debTSVPd <= 11.0) {
                        final double lim = 12.0 - debTSVPd;
                        if (dureeTSVPd >= 7.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else if (dureeTSVPd >= 5.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else if (dureeTSVPd >= lim) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd >= 11.0 && debTSVPd <= 14.0) {
                        if (dureeTSVPd >= 7.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else if (dureeTSVPd >= 5.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else if (dureeTSVPd >= 1.0) {
                            sv.isDCdepMidi = true;
                            sv.isDCdepSoir = false;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                    else if (debTSVPd > 14.0 && debTSVPd <= 18.0) {
                        final double lim = 19.0 - debTSVPd;
                        if (dureeTSVPd >= 7.0) {
                            sv.isDCdepSoir = true;
                            sv.isDCdepMidi = false;
                        }
                        else if (dureeTSVPd >= 5.0) {
                            sv.isDCdepSoir = true;
                            sv.isDCdepMidi = false;
                        }
                        else if (dureeTSVPd >= lim) {
                            sv.isDCdepSoir = true;
                            sv.isDCdepMidi = false;
                        }
                        else {
                            sv.isDCdepSoir = false;
                            sv.isDCdepMidi = false;
                        }
                    }
                    else if (debTSVPd >= 18.0 && debTSVPd <= 21.0) {
                        if (dureeTSVPd >= 7.0) {
                            sv.isDCdepSoir = true;
                            sv.isDCdepMidi = false;
                        }
                        else if (dureeTSVPd >= 5.0) {
                            sv.isDCdepSoir = true;
                            sv.isDCdepMidi = false;
                        }
                        else if (dureeTSVPd >= 1.0) {
                            sv.isDCdepSoir = true;
                            sv.isDCdepMidi = false;
                        }
                        else {
                            sv.isDCdepSoir = false;
                            sv.isDCdepMidi = false;
                        }
                    }
                    else if (debTSVPd >= 21.0) {
                        if (dureeTSVPd >= 11.0) {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = true;
                        }
                        else {
                            sv.isDCdepMidi = false;
                            sv.isDCdepSoir = false;
                        }
                    }
                }
                tz = new SimpleTimeZone(lastTroSv.LAGtoMillis, "");
                cal.setTimeZone(tz);
                cal.setTimeInMillis(finTSVP);
                h = cal.get(11);
                m = cal.get(12);
                final double finTSVPd = Utils.arrondi(h + m / 60.0, 2);
                dureeTSVPd = Utils.arrondi(dureeTSVP / 3600000.0, 2);
                debTSVPd = Utils.arrondi(finTSVPd - dureeTSVPd, 2);
                if (dureeTSVP < 45000000L) {
                    if (finTSVPd >= 12.0 && debTSVPd <= 15.0 && !sv.isDCdepMidi && !sv.isDCdepSoir) {
                        sv.isDCarrMidi = true;
                        sv.isDCarrSoir = false;
                    }
                    else {
                        if (finTSVPd < 19.0 || debTSVPd > 22.0 || sv.isDCdepMidi || sv.isDCdepSoir) {
                            continue;
                        }
                        sv.isDCarrSoir = true;
                        sv.isDCarrMidi = false;
                    }
                }
                else {
                    sv.isDCarrMidi = false;
                    sv.isDCarrSoir = false;
                }
            }
        }
    }
    
    private void calculIR_LC_ActivitesVol() {
        for (final Rotation rotation : this.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    troncon.indemFrom.nIRMidiP = 0;
                    troncon.indemFrom.nIRSoirP = 0;
                    troncon.indemTo.nIRMidiP = 0;
                    troncon.indemTo.nIRSoirP = 0;
                    troncon.indemFrom.nMFMidiP = 0;
                    troncon.indemFrom.nMFSoirP = 0;
                    troncon.indemTo.nMFMidiP = 0;
                    troncon.indemTo.nMFSoirP = 0;
                    troncon.nDecP = 0;
                    troncon.indemFrom.nIRMidiR = 0;
                    troncon.indemFrom.nIRSoirR = 0;
                    troncon.indemTo.nIRMidiR = 0;
                    troncon.indemTo.nIRSoirR = 0;
                    troncon.indemFrom.nMFMidiR = 0;
                    troncon.indemFrom.nMFSoirR = 0;
                    troncon.indemTo.nMFMidiR = 0;
                    troncon.indemTo.nMFSoirR = 0;
                    troncon.nDecR = 0;
                }
            }
        }
        final GregorianCalendar calDep = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calArr = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calNextDep = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation2 : this.listRotation) {
            if (rotation2.isOD) {
                continue;
            }
            for (int j = 0; j < rotation2.listSV.size(); ++j) {
                final ServiceVol sv2 = rotation2.listSV.get(j);
                final Troncon firstTroSv = sv2.listTroncon.get(0);
                final Troncon lastTroSv = sv2.listTroncon.get(sv2.listTroncon.size() - 1);
                final SimpleTimeZone tzDep = new SimpleTimeZone(firstTroSv.LAGfromMillis, "");
                calDep.setTimeZone(tzDep);
                calDep.setTimeInMillis(firstTroSv.debutUTCD.getTime() - 4500000L);
                final SimpleTimeZone tzArr = new SimpleTimeZone(lastTroSv.LAGtoMillis, "");
                calArr.setTimeZone(tzArr);
                calArr.setTimeInMillis(lastTroSv.finUTCD.getTime() + 900000L);
                if (j < rotation2.listSV.size() - 1) {
                    final ServiceVol svNext = rotation2.listSV.get(j + 1);
                    final Troncon firstTroNextSv = svNext.listTroncon.get(0);
                    final SimpleTimeZone tzNextDep = new SimpleTimeZone(firstTroNextSv.LAGfromMillis, "");
                    calNextDep.setTimeZone(tzNextDep);
                    calNextDep.setTimeInMillis(firstTroNextSv.debutUTCD.getTime() - 4500000L);
                    lastTroSv.nDecP = Utils.calculDecouchersLC(calArr, calNextDep, 1260, 540, 540);
                    lastTroSv.nDecR = Utils.calculDecouchersLC(calArr, calNextDep, 1260, 540, 540);
                    if (sv2.isDCdepMidi) {
                        calArr.set(1, calDep.get(1));
                        calArr.set(2, calDep.get(2));
                        calArr.set(5, calDep.get(5));
                        calArr.set(11, 15);
                        calArr.set(12, 0);
                        calArr.set(13, 0);
                        calArr.set(14, 0);
                    }
                    else if (sv2.isDCdepSoir) {
                        calArr.set(1, calDep.get(1));
                        calArr.set(2, calDep.get(2));
                        calArr.set(5, calDep.get(5));
                        calArr.set(11, 22);
                        calArr.set(12, 0);
                        calArr.set(13, 0);
                        calArr.set(14, 0);
                    }
                    if (svNext.isDCdepMidi) {
                        calNextDep.set(11, 11);
                        calNextDep.set(12, 0);
                        calNextDep.set(13, 0);
                        calNextDep.set(14, 0);
                    }
                    else if (svNext.isDCdepSoir) {
                        calNextDep.set(11, 18);
                        calNextDep.set(12, 0);
                        calNextDep.set(13, 0);
                        calNextDep.set(14, 0);
                    }
                    final long dureeEscale = firstTroNextSv.debutUTCD.getTime() - lastTroSv.finUTCD.getTime();
                    final boolean isMF = dureeEscale >= 10800000L;
                    final int x = calArr.get(11) * 60 + calArr.get(12);
                    if (x <= 840) {
                        if (!sv2.isDCarrMidi) {
                            final Indem indemTo = lastTroSv.indemTo;
                            ++indemTo.nIRMidiP;
                            final Indem indemTo2 = lastTroSv.indemTo;
                            ++indemTo2.nIRMidiR;
                            final Indem indemTo3 = lastTroSv.indemTo;
                            ++indemTo3.nIRSoirP;
                            final Indem indemTo4 = lastTroSv.indemTo;
                            ++indemTo4.nIRSoirR;
                            if (isMF) {
                                final Indem indemTo5 = lastTroSv.indemTo;
                                ++indemTo5.nMFMidiP;
                                final Indem indemTo6 = lastTroSv.indemTo;
                                ++indemTo6.nMFMidiR;
                                final Indem indemTo7 = lastTroSv.indemTo;
                                ++indemTo7.nMFSoirP;
                                final Indem indemTo8 = lastTroSv.indemTo;
                                ++indemTo8.nMFSoirR;
                            }
                        }
                        final Indem indemTo9 = lastTroSv.indemTo;
                        ++indemTo9.nIRSoirP;
                        final Indem indemTo10 = lastTroSv.indemTo;
                        ++indemTo10.nIRSoirR;
                        if (isMF) {
                            final Indem indemTo11 = lastTroSv.indemTo;
                            ++indemTo11.nMFSoirP;
                            final Indem indemTo12 = lastTroSv.indemTo;
                            ++indemTo12.nMFSoirR;
                        }
                    }
                    else if (x <= 1260 && !sv2.isDCarrSoir) {
                        final Indem indemTo13 = lastTroSv.indemTo;
                        ++indemTo13.nIRSoirP;
                        final Indem indemTo14 = lastTroSv.indemTo;
                        ++indemTo14.nIRSoirR;
                        if (isMF) {
                            final Indem indemTo15 = lastTroSv.indemTo;
                            ++indemTo15.nMFSoirP;
                            final Indem indemTo16 = lastTroSv.indemTo;
                            ++indemTo16.nMFSoirR;
                        }
                    }
                    final int y = calNextDep.get(11) * 60 + calNextDep.get(12);
                    if (y >= 1140) {
                        final Indem indemTo17 = lastTroSv.indemTo;
                        ++indemTo17.nIRMidiP;
                        final Indem indemTo18 = lastTroSv.indemTo;
                        ++indemTo18.nIRMidiR;
                        final Indem indemTo19 = lastTroSv.indemTo;
                        ++indemTo19.nIRSoirP;
                        final Indem indemTo20 = lastTroSv.indemTo;
                        ++indemTo20.nIRSoirR;
                        if (isMF) {
                            final Indem indemTo21 = lastTroSv.indemTo;
                            ++indemTo21.nMFMidiP;
                            final Indem indemTo22 = lastTroSv.indemTo;
                            ++indemTo22.nMFMidiR;
                            final Indem indemTo23 = lastTroSv.indemTo;
                            ++indemTo23.nMFSoirP;
                            final Indem indemTo24 = lastTroSv.indemTo;
                            ++indemTo24.nMFSoirR;
                        }
                    }
                    else if (y >= 720) {
                        final Indem indemTo25 = lastTroSv.indemTo;
                        ++indemTo25.nIRMidiP;
                        final Indem indemTo26 = lastTroSv.indemTo;
                        ++indemTo26.nIRMidiR;
                        if (isMF) {
                            final Indem indemTo27 = lastTroSv.indemTo;
                            ++indemTo27.nMFMidiP;
                            final Indem indemTo28 = lastTroSv.indemTo;
                            ++indemTo28.nMFMidiR;
                        }
                    }
                    calArr.set(11, 23);
                    calArr.set(12, 59);
                    calArr.set(13, 59);
                    calArr.set(14, 1000);
                    calNextDep.set(11, 0);
                    calNextDep.set(12, 0);
                    calNextDep.set(13, 0);
                    calNextDep.set(14, 0);
                    final int deltaOffset = firstTroNextSv.LAGfromMillis - lastTroSv.LAGtoMillis;
                    final int nJoursEntiers = (int)((calNextDep.getTimeInMillis() - calArr.getTimeInMillis() + deltaOffset) / 86400000L);
                    final Indem indemTo29 = lastTroSv.indemTo;
                    indemTo29.nIRMidiP += nJoursEntiers;
                    final Indem indemTo30 = lastTroSv.indemTo;
                    indemTo30.nIRMidiR += nJoursEntiers;
                    final Indem indemTo31 = lastTroSv.indemTo;
                    indemTo31.nIRSoirP += nJoursEntiers;
                    final Indem indemTo32 = lastTroSv.indemTo;
                    indemTo32.nIRSoirR += nJoursEntiers;
                    if (isMF) {
                        final Indem indemTo33 = lastTroSv.indemTo;
                        indemTo33.nMFMidiP += nJoursEntiers;
                        final Indem indemTo34 = lastTroSv.indemTo;
                        indemTo34.nMFMidiR += nJoursEntiers;
                        final Indem indemTo35 = lastTroSv.indemTo;
                        indemTo35.nMFSoirP += nJoursEntiers;
                        final Indem indemTo36 = lastTroSv.indemTo;
                        indemTo36.nMFSoirR += nJoursEntiers;
                    }
                }
                else {
                    calDep.setTime(firstTroSv.debutUTCD);
                    calArr.setTime(lastTroSv.finUTCD);
                    if (sv2.isDCdepMidi) {
                        calDep.set(11, 15);
                        calDep.set(12, 0);
                        calDep.set(13, 0);
                        calDep.set(14, 0);
                    }
                    else if (sv2.isDCdepSoir) {
                        calDep.set(11, 22);
                        calDep.set(12, 0);
                        calDep.set(13, 0);
                        calDep.set(14, 0);
                    }
                    else if (sv2.isDCarrMidi) {
                        calArr.set(11, 11);
                        calArr.set(12, 0);
                        calArr.set(13, 0);
                        calArr.set(14, 0);
                    }
                    else if (sv2.isDCarrSoir) {
                        calArr.set(11, 18);
                        calArr.set(12, 0);
                        calArr.set(13, 0);
                        calArr.set(14, 0);
                    }
                    final int x2 = calDep.get(11) * 60 + calDep.get(12);
                    int y2 = calArr.get(11) * 60 + calArr.get(12);
                    if (calArr.get(5) != calDep.get(5)) {
                        y2 = 1440;
                    }
                    if (x2 <= 840 && y2 >= 720) {
                        final Indem indemTo37 = lastTroSv.indemTo;
                        ++indemTo37.nIRMidiP;
                        final Indem indemTo38 = lastTroSv.indemTo;
                        ++indemTo38.nIRMidiR;
                    }
                    if (x2 <= 1260 && y2 >= 1080) {
                        final Indem indemTo39 = lastTroSv.indemTo;
                        ++indemTo39.nIRSoirP;
                        final Indem indemTo40 = lastTroSv.indemTo;
                        ++indemTo40.nIRSoirR;
                    }
                    if (calArr.get(5) != calDep.get(5)) {
                        final int z = calArr.get(11) * 60 + calArr.get(12);
                        if (z >= 1140) {
                            final Indem indemTo41 = lastTroSv.indemTo;
                            ++indemTo41.nIRMidiP;
                            final Indem indemTo42 = lastTroSv.indemTo;
                            ++indemTo42.nIRMidiR;
                            final Indem indemTo43 = lastTroSv.indemTo;
                            ++indemTo43.nIRSoirP;
                            final Indem indemTo44 = lastTroSv.indemTo;
                            ++indemTo44.nIRSoirR;
                        }
                        else if (z >= 720) {
                            final Indem indemTo45 = lastTroSv.indemTo;
                            ++indemTo45.nIRMidiP;
                            final Indem indemTo46 = lastTroSv.indemTo;
                            ++indemTo46.nIRMidiR;
                        }
                    }
                }
            }
        }
    }
    
    private void transfoDSP_PacRpc() {
        for (int i = 0; i < this.listSol.size(); ++i) {
            final ActiviteSol actSol = this.listSol.get(i);
            if (actSol.code.equals("DSP")) {
                for (final Rotation rotation : this.listRotation) {
                    if (actSol.finUTCD.after(rotation.debutUTCD) && actSol.debutUTCD.before(rotation.finUTCD)) {
                        this.listSol.remove(actSol);
                        --i;
                        break;
                    }
                }
            }
        }
        for (final ActiviteSol actSol2 : this.listSol) {
            if (!actSol2.code.equals("DSP")) {
                continue;
            }
            for (final Rotation rotation : this.listRotation) {
                if (!rotation.isOD && rotation.debutUTCD.before(actSol2.debutUTCD) && (rotation.reengagementUTCD.after(actSol2.finUTCD) || rotation.reengagementUTCD.equals(actSol2.finUTCD))) {
                    actSol2.categorie = "Repos post-courrier";
                    actSol2.code = "RPC";
                    actSol2.label = "RPC";
                    break;
                }
            }
        }
        for (final ActiviteSol actSol2 : this.listSol) {
            if (!actSol2.code.equals("DSP")) {
                continue;
            }
            for (final Rotation rotation : this.listRotation) {
                if (!rotation.isOD) {
                    final GregorianCalendar calUtc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    calUtc.setTime(rotation.presentationUTCD);
                    final int pac = (int)Utils.timeStringtoMs(rotation.dureePac);
                    calUtc.add(14, -pac);
                    if (rotation.debutUTCD.after(actSol2.finUTCD) && (calUtc.getTime().before(actSol2.debutUTCD) || calUtc.getTime().equals(actSol2.debutUTCD))) {
                        actSol2.categorie = "Repos pr\u00e9-courrier";
                        actSol2.code = "PAC";
                        actSol2.label = "PAC";
                        break;
                    }
                    continue;
                }
            }
        }
    }
    
    private void declenchementReserve() {
        final SimpleDateFormat sdfParis = new SimpleDateFormat("dd/MM/yyyy");
        sdfParis.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        for (int i = 0; i < this.listCrew.size(); ++i) {
            final Object obj = this.listCrew.get(i);
            if (obj instanceof ActiviteSol) {
                final ActiviteSol act = (ActiviteSol)obj;
                if (act.code.equals("RBR") && i < this.listCrew.size() - 1) {
                    final Object after = this.listCrew.get(i + 1);
                    if (after instanceof Rotation) {
                        final Rotation rotafter = (Rotation)after;
                        final String jourRotafter = sdfParis.format(rotafter.debutUTCD);
                        final String jourReserve = sdfParis.format(act.debutUTCD);
                        if (jourReserve.equals(jourRotafter)) {
                            act.isReserveDeclenchee = true;
                            act.label = "RESERVE DECLENCHEE";
                            act.isDispo = false;
                            act.isCredite = false;
                        }
                    }
                }
            }
        }
    }
    
    private void calculHC_ActivitesSol() {
        final GregorianCalendar calDeb = new GregorianCalendar();
        calDeb.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calFin = new GregorianCalendar();
        calFin.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        for (final ActiviteSol actSol : this.listSol) {
            calDeb.setTime(actSol.debutUTCD);
            calFin.setTime(actSol.finUTCD);
            boolean isACheval = false;
            if (calFin.get(5) != calDeb.get(5)) {
                isACheval = true;
            }
            boolean isSupAHuit = false;
            final long dureeM = (actSol.finUTCD.getTime() - actSol.debutUTCD.getTime()) / 60000L;
            if (dureeM > 480L) {
                isSupAHuit = true;
            }
            int nbjours = 1;
            if (isACheval && isSupAHuit) {
                calDeb.set(11, 0);
                calDeb.set(12, 0);
                calDeb.set(13, 0);
                calDeb.set(14, 0);
                calFin.set(11, 23);
                calFin.set(12, 59);
                calFin.set(13, 59);
                calFin.set(14, 1000);
                final int deltaOffset = calFin.get(15) + calFin.get(16) - calDeb.get(15) - calDeb.get(16);
                nbjours = (int)(calFin.getTimeInMillis() - calDeb.getTimeInMillis() + deltaOffset) / 86400000;
            }
            if (actSol.code.equals("SYN")) {
                if (!actSol.isHCreal) {
                    actSol.HCS = 4 * nbjours;
                    actSol.HCRS = 6 * nbjours;
                }
                if (actSol.isIKVreal) {
                    continue;
                }
                actSol.IKVar = "NIL";
            }
            else if (actSol.code.equals("MIM")) {
                final double dureeH = (actSol.finUTCD.getTime() - actSol.debutUTCD.getTime()) / 3600000.0;
                if (!actSol.isHCreal) {
                    if (dureeH < 4.0) {
                        if (actSol.label.contains("SIMU")) {
                            actSol.HCS = 3 * nbjours;
                            actSol.HCRS = 3 * nbjours;
                        }
                        else {
                            actSol.HCS = 2 * nbjours;
                            actSol.HCRS = 2 * nbjours;
                        }
                    }
                    else if (actSol.label.contains("SIMU")) {
                        actSol.HCS = 5 * nbjours;
                        actSol.HCRS = 5 * nbjours;
                    }
                    else {
                        actSol.HCS = 4 * nbjours;
                        actSol.HCRS = 4 * nbjours;
                    }
                }
                if (actSol.isIKVreal) {
                    continue;
                }
                if (actSol.lieu == null || actSol.lieu.equals("null")) {
                    actSol.IKVar = "CDG";
                }
                else {
                    actSol.IKVar = actSol.lieu;
                }
            }
            else if (actSol.code.equals("MVM")) {
                if (!actSol.isHCreal) {
                    actSol.HCS = 2 * nbjours;
                    actSol.HCRS = 2 * nbjours;
                }
                if (actSol.isIKVreal) {
                    continue;
                }
                if (actSol.lieu == null || actSol.lieu.equals("null")) {
                    actSol.IKVar = "CDG";
                }
                else {
                    actSol.IKVar = actSol.lieu;
                }
            }
            else if (actSol.code.equals("RBR")) {
                if (actSol.isReserveDeclenchee) {
                    continue;
                }
                if (!actSol.isHCreal && this.isPNT) {
                    actSol.HCS = 4 * nbjours;
                    actSol.HCRS = 4 * nbjours;
                }
                if (!actSol.isHCreal && !this.isPNT) {
                    actSol.HCS = 3 * nbjours;
                    actSol.HCRS = 3 * nbjours;
                }
                if (actSol.isIKVreal) {
                    continue;
                }
                if (actSol.lieu == null || actSol.lieu.equals("null")) {
                    actSol.IKVar = "CDG";
                }
                else {
                    actSol.IKVar = actSol.lieu;
                }
            }
            else if (actSol.code.equals("SST")) {
                final double dureeH = (actSol.finUTCD.getTime() - actSol.debutUTCD.getTime()) / 3600000.0;
                if (!actSol.isHCreal) {
                    if (dureeH < 4.0) {
                        if (actSol.label.contains("SIMU")) {
                            actSol.HCS = 3 * nbjours;
                            actSol.HCRS = 3 * nbjours;
                        }
                        else {
                            actSol.HCS = 2 * nbjours;
                            actSol.HCRS = 2 * nbjours;
                        }
                    }
                    else if (actSol.label.contains("SIMU")) {
                        actSol.HCS = 5 * nbjours;
                        actSol.HCRS = 5 * nbjours;
                    }
                    else {
                        actSol.HCS = 4 * nbjours;
                        actSol.HCRS = 4 * nbjours;
                    }
                }
                if (actSol.isIKVreal) {
                    continue;
                }
                if (actSol.lieu == null || actSol.lieu.equals("null")) {
                    actSol.IKVar = "CDG";
                }
                else {
                    actSol.IKVar = actSol.lieu;
                }
            }
            else {
                if (!actSol.code.equals("MCI")) {
                    continue;
                }
                final double dureeH = (actSol.finUTCD.getTime() - actSol.debutUTCD.getTime()) / 3600000.0;
                if (!actSol.isHCreal) {
                    if (actSol.label.contains("SIMU")) {
                        if (dureeH <= 2.0) {
                            actSol.HCS = 3 * nbjours;
                            actSol.HCRS = 4.5 * nbjours;
                        }
                        else {
                            actSol.HCS = 5 * nbjours;
                            actSol.HCRS = 7.5 * nbjours;
                        }
                    }
                    else if (dureeH < 4.0) {
                        actSol.HCS = 2.2 * nbjours;
                        actSol.HCRS = 3.3 * nbjours;
                    }
                    else {
                        actSol.HCS = 4.4 * nbjours;
                        actSol.HCRS = 6.6 * nbjours;
                    }
                }
                if (actSol.isIKVreal) {
                    continue;
                }
                if (actSol.lieu == null || actSol.lieu.equals("null")) {
                    actSol.IKVar = "CDG";
                }
                else {
                    actSol.IKVar = actSol.lieu;
                }
            }
        }
    }
    
    public void updateAnalyseCrew() {
        this.chope_isMC_isBP_qualifPNT();
        this.calculHCIRpAnalyseCrew();
        this.calculHCIRrAnalyseCrew();
    }
    
    public void chargeAnalyseCrewFromGoogle(final ConnexionGoogle conGoogle) {
        this.clear();
    }
    
    public void chargeAnalyseCrewFromXml(final String planningXML) {
        this.clear();
        this.chope_MoisAnneeGeneralites_XML(planningXML);
        this.chope_isMC_isBP_qualifPNT();
        this.chope_ActivitesVol_XML(planningXML);
        this.chope_ActivitesSol_XML(planningXML);
        this.transfoDSP_PacRpc();
        for (final Rotation rotation : this.listRotation) {
            this.listCrew.add(rotation);
        }
        for (final ActiviteSol actSol : this.listSol) {
            this.listCrew.add(actSol);
        }
        Collections.sort(this.listCrew);
        this.declenchementReserve();
        this.calculHCIRpAnalyseCrew();
        this.calculHCIRrAnalyseCrew();
    }
    
    public void chargeAnalyseCrewFromConnexion(final ConnexionCrew con) {
        this.clear();
        this.deltaMois = con.getDeltaMois();
        if (con.getExportPdaIcs() == null) {
            this.isFlash = true;
        }
        else {
            this.isFlash = false;
        }
        this.chope_TypePN_PageIntroPlanning(con.getPageIntroPlanning());
        this.chope_MoisAnneeMatricule_PageMensuelle(con.getPageMensuelle());
        this.chope_isMC_isBP_qualifPNT();
        if (this.isFlash) {
            this.chope_ActivitesVol_PageImpression(con.getPageImpression());
            this.chope_ActivitesVol_Complement();
        }
        if (!this.isFlash) {
            this.chope_ActivitesVol_PageImpression(con.getPageImpression());
            this.chope_ActivitesVol_Complement();
            this.chope_DetailsVol_ExportPdaIcs(con.getExportPdaIcs());
        }
        this.chope_ActivitesSol_PageMensuelle(con.getPageMensuelle());
        this.transfoDSP_PacRpc();
        if (this.isPNT) {
            for (final String pageStage : con.getPagesStage()) {
                this.chope_DetailsStage_PageStage(pageStage);
            }
        }
        this.chope_PartSabVol_PageMensuelle(con.getPageMensuelle());
        for (final Rotation rotation : this.listRotation) {
            this.listCrew.add(rotation);
        }
        for (final ActiviteSol actSol : this.listSol) {
            this.listCrew.add(actSol);
        }
        Collections.sort(this.listCrew);
        this.declenchementReserve();
        this.calculHCIRpAnalyseCrew();
        this.calculHCIRrAnalyseCrew();
    }
    
    public void chargeAnalyseCrewFromRequeteur(final ConnexionRequeteur con) {
        this.clear();
        this.deltaMois = 99;
        this.isFlash = true;
        this.isPNT = true;
        final GregorianCalendar cal = new GregorianCalendar();
        this.anneeInt = cal.get(1);
        this.moisInt = cal.get(2) + 1;
        if (this.moisInt == 1) {
            this.moisNum = "01";
            this.moisLit = "Janvier";
        }
        else if (this.moisInt == 2) {
            this.moisNum = "02";
            this.moisLit = "F\u00e9vrier";
        }
        else if (this.moisInt == 3) {
            this.moisNum = "03";
            this.moisLit = "Mars";
        }
        else if (this.moisInt == 4) {
            this.moisNum = "04";
            this.moisLit = "Avril";
        }
        else if (this.moisInt == 5) {
            this.moisNum = "05";
            this.moisLit = "Mai";
        }
        else if (this.moisInt == 6) {
            this.moisNum = "06";
            this.moisLit = "Juin";
        }
        else if (this.moisInt == 7) {
            this.moisNum = "07";
            this.moisLit = "Juillet";
        }
        else if (this.moisInt == 8) {
            this.moisNum = "08";
            this.moisLit = "Ao\u00fbt";
        }
        else if (this.moisInt == 9) {
            this.moisNum = "09";
            this.moisLit = "Septembre";
        }
        else if (this.moisInt == 10) {
            this.moisNum = "10";
            this.moisLit = "Octobre";
        }
        else if (this.moisInt == 11) {
            this.moisNum = "11";
            this.moisLit = "Novembre";
        }
        else if (this.moisInt == 12) {
            this.moisNum = "12";
            this.moisLit = "D\u00e9cembre";
        }
        this.matricule = "m999999";
        this.chope_isMC_isBP_qualifPNT();
        if (this.isFlash) {
            this.chope_ActivitesVol_ListPagesImpression(con.getListPagesImpression());
            this.chope_ActivitesVol_Complement();
        }
        for (final Rotation rotation : this.listRotation) {
            this.listCrew.add(rotation);
        }
        Collections.sort(this.listCrew);
        this.calculHCIRpAnalyseCrew();
        this.calculHCIRrAnalyseCrew();
    }
    
    public void calculHCIRpAnalyseCrew() {
        this.calculHCp_ActivitesVol();
        this.calculHC_ActivitesSol();
        if (this.isMC) {
            this.calculIRp_MC_ActivitesVol();
        }
        else if (!this.isMC) {
            this.calculDC_LC_ActivitesVol();
            this.calculIR_LC_ActivitesVol();
        }
    }
    
    public void calculHCIRrAnalyseCrew() {
        this.calculHCr_ActivitesVol();
        this.calculHC_ActivitesSol();
        if (this.isMC) {
            this.calculIRr_MC_ActivitesVol();
        }
        else if (!this.isMC) {
            this.calculDC_LC_ActivitesVol();
            this.calculIR_LC_ActivitesVol();
        }
    }
    
    private ArrayList<ArrayList<ArrayList<String>>> decomposePageMensuelle(final String pageMensuelle) {
        final ArrayList<ArrayList<ArrayList<String>>> listJours = new ArrayList<ArrayList<ArrayList<String>>>();
        final String cibleDeb = "<tr.*?>\\s*?<td.*?>\\s*?(\\w{2}) (\\d{2})(/\\d{2})?\\s*?</td>";
        final String cibleFin = "<tr.*?>\\s*?<td.*?>\\s*?(\\w{2}) (\\d{2})(/\\d{2})?\\s*?</td>|</tbody>";
        final Pattern regexDeb = Pattern.compile(cibleDeb, 2);
        final Pattern regexFin = Pattern.compile(cibleFin, 2);
        final Matcher resultDeb = regexDeb.matcher(pageMensuelle);
        final Matcher resultFin = regexFin.matcher(pageMensuelle);
        while (resultDeb.find() && resultFin.find(resultDeb.end())) {
            final String jour = pageMensuelle.substring(resultDeb.start(), resultFin.start()).trim();
            final String[] lignes = jour.split("</tr>");
            final ArrayList<ArrayList<String>> listLignes = new ArrayList<ArrayList<String>>();
            String[] array;
            for (int length = (array = lignes).length, i = 0; i < length; ++i) {
                final String ligne = array[i];
                final String[] colonnes = ligne.trim().split("</td>");
                final ArrayList<String> listColonnes = new ArrayList<String>();
                String[] array2;
                for (int length2 = (array2 = colonnes).length, j = 0; j < length2; ++j) {
                    String colonne = array2[j];
                    colonne = Pattern.compile("<script>.*?</script>", 32).matcher(colonne).replaceAll("");
                    listColonnes.add(String.valueOf(colonne.trim()) + "</td>");
                }
                listLignes.add(listColonnes);
            }
            listJours.add(listLignes);
        }
        return listJours;
    }
    
    private ArrayList<String> decomposePageImpression(final String pageImpression) {
        final ArrayList<String> resultat = new ArrayList<String>();
        final String cible = "<table border.*?Rotation \\w+ du \\d{2}/\\d{2}/\\d{4}";
        final Pattern regex = Pattern.compile(cible, 32);
        final Matcher result = regex.matcher(pageImpression);
        while (result.find()) {
            final int indexdebut = result.start();
            final int indexfin = Math.max(pageImpression.indexOf("<br>", indexdebut), pageImpression.indexOf("<BR>", indexdebut));
            resultat.add(pageImpression.substring(indexdebut, indexfin).trim());
        }
        return resultat;
    }
    
    private ArrayList<String> decomposeExportPda(final String exportPda) {
        final ArrayList<String> resultat = new ArrayList<String>();
        String rotIDprecedent = "";
        String dateRotprecedent = "";
        String rotID = "";
        String dateRot = "";
        final String cible = "\"([A-Z 0-9]+)\".*?Date d\u00e9part UTC : (\\d{2}/\\d{2}/\\d{4})";
        final Pattern regex = Pattern.compile(cible, 32);
        final Matcher result = regex.matcher(exportPda);
        while (result.find()) {
            rotID = result.group(1);
            dateRot = result.group(2);
            if (!rotID.equals(rotIDprecedent) || !dateRot.equals(dateRotprecedent)) {
                resultat.add(exportPda.substring(result.start()).trim());
            }
            rotIDprecedent = rotID;
            dateRotprecedent = dateRot;
        }
        for (int i = 0; i < resultat.size(); ++i) {
            String s = resultat.get(i);
            result.reset(s);
            if (result.find()) {
                rotIDprecedent = result.group(1);
                dateRotprecedent = result.group(2);
            }
            while (result.find()) {
                rotID = result.group(1);
                dateRot = result.group(2);
                if (!rotID.equals(rotIDprecedent) || !dateRot.equals(dateRotprecedent)) {
                    s = s.substring(0, result.start());
                    resultat.set(i, s);
                    break;
                }
            }
        }
        return resultat;
    }
    
    private String importBaseTVref(final String type) {
        String dbTVRef = new String();
        if (type == null) {
            return dbTVRef;
        }
        try {
            InputStream is;
            if (this.isPNT) {
                if (ChopeCrew.options.dbTVRef_pnt.equals("")) {
                    final String ficSource = "/res_databases/dbTVREF_" + type + ".txt";
                    is = this.getClass().getResourceAsStream(ficSource);
                }
                else {
                    final String ficSource = ChopeCrew.options.dbTVRef_pnt;
                    is = new FileInputStream(ficSource);
                }
            }
            else if (ChopeCrew.options.dbTVRef_pnc.equals("")) {
                final String ficSource = "/res_databases/dbTVREF_" + type + ".txt";
                is = this.getClass().getResourceAsStream(ficSource);
            }
            else {
                final String ficSource = ChopeCrew.options.dbTVRef_pnc;
                is = new FileInputStream(ficSource);
            }
            final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            final String nl = System.getProperty("line.separator");
            final StringBuilder sb = new StringBuilder();
            String tmp;
            while ((tmp = in.readLine()) != null) {
                sb.append(tmp).append(nl);
            }
            in.close();
            is.close();
            dbTVRef = sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dbTVRef;
    }
    
    private HashMap<String, Escale> importBaseEscales() {
        final HashMap<String, Escale> dbEscales = new HashMap<String, Escale>(250);
        try {
            InputStream is;
            if (ChopeCrew.options.dbEscales.equals("")) {
                final String ficSource = "/res_databases/dbESCALES.txt";
                is = this.getClass().getResourceAsStream(ficSource);
            }
            else {
                final String ficSource = ChopeCrew.options.dbEscales;
                is = new FileInputStream(ficSource);
            }
            final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String tmp;
            while ((tmp = in.readLine()) != null) {
                final String[] spl = tmp.split(";");
                if (spl.length == 6) {
                    final Escale esc = new Escale();
                    esc.code = spl[0].trim();
                    esc.nom = spl[1].trim();
                    esc.pays = spl[2].trim();
                    esc.tz = spl[3].trim();
                    esc.zone = spl[4].trim();
                    esc.bigramme = spl[5].trim();
                    dbEscales.put(esc.code, esc);
                }
            }
            in.close();
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dbEscales;
    }
    
    private void clear() {
        this.matricule = null;
        this.deltaMois = 0;
        this.isFlash = false;
        this.moisInt = 0;
        this.moisNum = null;
        this.moisLit = null;
        this.anneeInt = 0;
        this.isPNT = false;
        this.isMC = true;
        this.isBP = false;
        this.qualifPNT = null;
        this.listRotation.clear();
        this.listSol.clear();
        this.listCrew.clear();
    }
}
