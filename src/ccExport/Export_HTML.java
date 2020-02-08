// 
// Decompiled by Procyon v0.5.36
// 

package ccExport;

import ccEngine.AnalyseCrew;
import java.util.Iterator;
import ccUtils.Utils;
import ccStructures.Presta;
import java.util.SimpleTimeZone;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.Rotation;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import ccStructures.ActiviteSol;
import chopeCrew.ChopeCrew;

public class Export_HTML
{
    private StringBuilder html;
    private String nl;
    private String jourSemaineColor;
    private String jourWeColor;
    private String rotationColor1;
    private String rotationColor2;
    private String rotationInstructionColor1;
    private String rotationInstructionColor2;
    private String escaleColor;
    private String reposColor;
    private String congeColor;
    private String reposPrePostColor;
    private String dispColor;
    private String absenceColor;
    private String instructionColor;
    private String autreColor;
    private String lDate;
    private String lHeure;
    private String lFromTo;
    private String lDur\u00e9e;
    private String lNumVol;
    private String lMep;
    private String lPresta;
    private String lTypeAvion;
    private String lTVTSV;
    private String lTA;
    private String police;
    private int memoRowSpan;
    private String memoDate;
    
    public Export_HTML() {
        this.html = null;
        this.nl = null;
        this.jourSemaineColor = null;
        this.jourWeColor = null;
        this.rotationColor1 = null;
        this.rotationColor2 = null;
        this.rotationInstructionColor1 = null;
        this.rotationInstructionColor2 = null;
        this.escaleColor = null;
        this.reposColor = null;
        this.congeColor = null;
        this.reposPrePostColor = null;
        this.dispColor = null;
        this.absenceColor = null;
        this.instructionColor = null;
        this.autreColor = null;
        this.lDate = "70";
        this.lHeure = "70";
        this.lFromTo = "150";
        this.lDur\u00e9e = "50";
        this.lNumVol = "50";
        this.lMep = "32";
        this.lPresta = "50";
        this.lTypeAvion = "40";
        this.lTVTSV = "76";
        this.lTA = "60";
        this.police = null;
        this.memoDate = "";
        this.nl = System.getProperty("line.separator");
        this.html = new StringBuilder();
        if (ChopeCrew.options.webColor == 0) {
            this.jourSemaineColor = "#9DA9B4";
            this.jourWeColor = "#778899";
            this.rotationColor1 = "#0080FF";
            this.rotationColor2 = "#00B0FF";
            this.rotationInstructionColor1 = "#8080FF";
            this.rotationInstructionColor2 = "#B0B0FF";
            this.escaleColor = "#3399FF";
            this.reposColor = "#00FF80";
            this.congeColor = "#008000";
            this.reposPrePostColor = "#00FFA0";
            this.dispColor = "#FFFFFF";
            this.absenceColor = "#FCD94B";
            this.instructionColor = "#B2B222";
            this.autreColor = "#B22222";
        }
        else if (ChopeCrew.options.webColor == 1) {
            this.jourSemaineColor = "#EEEEEE";
            this.jourWeColor = "#E0E0E0";
            this.rotationColor1 = "#AFD6FE";
            this.rotationColor2 = "#C0DFFF";
            this.rotationInstructionColor1 = "#D6D6FE";
            this.rotationInstructionColor2 = "DFDFFF";
            this.escaleColor = "#AFDFFF";
            this.reposColor = "#BFFFE0";
            this.congeColor = "#73E6AF";
            this.reposPrePostColor = "#BFFFFE";
            this.dispColor = "#FFFFFF";
            this.absenceColor = "#FFEDA3";
            this.instructionColor = "#ECECC8";
            this.autreColor = "#ECC8C8";
        }
        else {
            this.jourSemaineColor = "#FFFFFF";
            this.jourWeColor = "#F1F1F1";
            this.rotationColor1 = "#FFFFFF";
            this.rotationColor2 = "#FFFFFF";
            this.rotationInstructionColor1 = "#FFFFFF";
            this.rotationInstructionColor2 = "#FFFFFF";
            this.escaleColor = "#FFFFFF";
            this.reposColor = "#FFFFFF";
            this.congeColor = "#FFFFFF";
            this.reposPrePostColor = "#FFFFFF";
            this.dispColor = "#FFFFFF";
            this.absenceColor = "#FFFFFF";
            this.instructionColor = "#FFFFFF";
            this.autreColor = "#FFFFFF";
        }
        if (ChopeCrew.options.webFont == 0) {
            this.police = "Arial";
        }
        else if (ChopeCrew.options.webFont == 1) {
            this.police = "Times New Roman";
        }
        else {
            this.police = "Courier New";
        }
    }
    
    private String writeSol(final ActiviteSol act) {
        final StringBuilder sb = new StringBuilder();
        final GregorianCalendar cal1 = new GregorianCalendar();
        cal1.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar cal2 = new GregorianCalendar();
        cal2.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final SimpleDateFormat hourFormat = new SimpleDateFormat("HH'h'mm");
        hourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        String actColor;
        if (act.code.equals("PRB") || act.code.equals("MAD")) {
            actColor = this.reposColor;
        }
        else if (act.code.equals("MCA") || act.code.equals("MCE")) {
            actColor = this.congeColor;
        }
        else if (act.code.equals("PAC") || act.code.equals("RPC")) {
            actColor = this.reposPrePostColor;
        }
        else if (act.code.equals("DSP")) {
            actColor = this.dispColor;
        }
        else if (act.code.equals("MDV")) {
            actColor = this.absenceColor;
        }
        else if (act.code.equals("MAS")) {
            actColor = this.absenceColor;
        }
        else if (act.code.equals("SYN")) {
            actColor = this.autreColor;
        }
        else if (act.code.equals("MCI")) {
            actColor = this.instructionColor;
        }
        else {
            actColor = this.autreColor;
        }
        cal1.setTime(act.debutUTCD);
        cal1.set(11, 0);
        cal1.set(12, 0);
        cal1.set(13, 0);
        cal1.set(14, 0);
        cal2.setTime(act.finUTCD);
        cal2.set(11, 0);
        cal2.set(12, 0);
        cal2.set(13, 0);
        cal2.set(14, 0);
        cal2.add(5, 1);
        final int deltaOffset = cal2.get(15) + cal2.get(16) - cal1.get(15) - cal1.get(16);
        final int rowspan = (int)(cal2.getTimeInMillis() - cal1.getTimeInMillis() + deltaOffset) / 86400000;
        cal1.setTime(act.debutUTCD);
        String jourColor;
        if (cal1.get(7) == 1 || cal1.get(7) == 7) {
            jourColor = this.jourWeColor;
        }
        else {
            jourColor = this.jourSemaineColor;
        }
        if (this.memoDate.equals(dateFormat.format(cal1.getTime()))) {
            ++this.memoRowSpan;
            final int index = this.html.lastIndexOf(">" + this.nl + "\t\t\t" + dateFormat.format(cal1.getTime()) + this.nl + "\t\t" + "</td>");
            if (index >= 0) {
                this.html.insert(index, " rowspan=\"" + this.memoRowSpan + "\"");
                sb.append(this.nl);
                sb.append("\t").append("<tr>").append(this.nl);
            }
        }
        else {
            sb.append(this.nl);
            sb.append("\t").append("<tr>").append(this.nl);
            sb.append("\t\t").append("<td width=\"").append(this.lDate).append("\" align=\"center\" nowrap=\"nowrap\" class=\"small\" bgcolor=\"").append(jourColor).append("\">").append(this.nl);
            sb.append("\t\t\t").append(dateFormat.format(cal1.getTime())).append(this.nl);
            sb.append("\t\t").append("</td>").append(this.nl);
            sb.append(this.nl);
            this.memoDate = dateFormat.format(cal1.getTime());
            this.memoRowSpan = 1;
        }
        if (act.isH24) {
            String libelle = new String();
            if (act.code.equals("DSP") && act.isBlocActivite) {
                if (act.bloc.code.equals("MBR")) {
                    libelle = "Dispersion (Bloc r\u00e9serve)";
                }
                else if (act.bloc.code.equals("SST")) {
                    libelle = "Dispersion (Bloc stage)";
                }
                else {
                    libelle = "Dispersion (Bloc activit\u00e9s)";
                }
            }
            else if (!act.label.equals(null) && !act.label.equals("")) {
                libelle = act.label;
            }
            else {
                libelle = act.categorie;
            }
            sb.append("\t\t").append("<td align=\"center\"  nowrap=\"nowrap\" class=\"normal\" bgcolor=\"").append(actColor).append("\">").append(this.nl);
            sb.append("\t\t\t").append(libelle).append(this.nl);
            sb.append("\t\t").append("</td>").append(this.nl);
            sb.append("\t").append("</tr>").append(this.nl);
        }
        else if (!act.isH24) {
            String libelle = new String();
            libelle = act.label;
            sb.append("\t\t").append("<td bgcolor=\"").append(actColor).append("\" rowspan=\"").append(rowspan).append("\">").append(this.nl);
            sb.append("\t\t\t").append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">").append(this.nl);
            sb.append("\t\t\t\t").append("<tr>").append(this.nl);
            sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lHeure).append("\" align=\"center\" nowrap=\"nowrap\" class=\"normal\">").append(hourFormat.format(act.debutUTCD)).append("</td>").append(this.nl);
            sb.append("\t\t\t\t\t").append("<td width=\"10\" align=\"center\" nowrap=\"nowrap\" class=\"small\">").append("-").append("</td>").append(this.nl);
            sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lHeure).append("\" align=\"center\" nowrap=\"nowrap\" class=\"normal\">").append(hourFormat.format(act.finUTCD)).append("</td>").append(this.nl);
            sb.append("\t\t\t\t\t").append("<td align=\"center\" nowrap=\"nowrap\" class=\"normal\">").append(libelle).append("</td>").append(this.nl);
            sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lHeure).append("\" align=\"center\" nowrap=\"nowrap\" class=\"normal\">").append("&nbsp;").append("</td>").append(this.nl);
            sb.append("\t\t\t\t\t").append("<td width=\"10\" align=\"center\" nowrap=\"nowrap\" class=\"small\">").append("&nbsp;").append("</td>").append(this.nl);
            sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lHeure).append("\" align=\"center\" nowrap=\"nowrap\" class=\"normal\">").append("&nbsp;").append("</td>").append(this.nl);
            sb.append("\t\t\t\t").append("</tr>").append(this.nl);
            sb.append("\t\t\t").append("</table>").append(this.nl);
            sb.append("\t\t").append("</td>").append(this.nl);
            sb.append("\t").append("</tr>").append(this.nl);
            for (int i = 2; i <= rowspan; ++i) {
                cal1.add(5, 1);
                if (cal1.get(7) == 1 || cal1.get(7) == 7) {
                    jourColor = this.jourWeColor;
                }
                else {
                    jourColor = this.jourSemaineColor;
                }
                sb.append(this.nl);
                sb.append("\t").append("<tr>").append(this.nl);
                sb.append("\t\t").append("<td width=\"").append(this.lDate).append("\" align=\"center\" nowrap=\"nowrap\" class=\"small\" bgcolor=\"").append(jourColor).append("\">").append(this.nl);
                sb.append("\t\t\t").append(dateFormat.format(cal1.getTime())).append(this.nl);
                sb.append("\t\t").append("</td>").append(this.nl);
                sb.append("\t").append("</tr>").append(this.nl);
                this.memoDate = dateFormat.format(cal1.getTime());
                this.memoRowSpan = 1;
            }
        }
        return sb.toString();
    }
    
    private String writeRotation(final Rotation rotation) {
        final StringBuilder sb = new StringBuilder();
        final ServiceVol firstSVRot = rotation.listSV.get(0);
        final ServiceVol lastSVRot = rotation.listSV.get(rotation.listSV.size() - 1);
        final Troncon firstTroRot = firstSVRot.listTroncon.get(0);
        final Troncon lastTroRot = lastSVRot.listTroncon.get(lastSVRot.listTroncon.size() - 1);
        final GregorianCalendar calDebRot = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calFinRot = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        calDebRot.setTime(firstTroRot.debutUTCD);
        calDebRot.set(11, 0);
        calDebRot.set(12, 0);
        calDebRot.set(13, 0);
        calDebRot.set(14, 0);
        calFinRot.setTime(lastTroRot.finUTCD);
        calFinRot.set(11, 23);
        calFinRot.set(12, 59);
        calFinRot.set(13, 59);
        calFinRot.set(14, 1000);
        final long deltaOffset = calFinRot.get(15) + calFinRot.get(16) - calDebRot.get(15) - calDebRot.get(16);
        final long njEng = (calFinRot.getTimeInMillis() - calDebRot.getTimeInMillis() + deltaOffset) / 86400000L;
        String rotationColor;
        if (rotation.numRot % 2 == 0) {
            if (rotation.sab != null && (rotation.sab.contains("IPLG") || rotation.sab.contains("IPLD") || rotation.sab.contains("IPLJPS") || rotation.sab.contains("IPLJS"))) {
                rotationColor = this.rotationInstructionColor1;
            }
            else {
                rotationColor = this.rotationColor1;
            }
        }
        else if (rotation.sab != null && (rotation.sab.contains("IPLG") || rotation.sab.contains("IPLD") || rotation.sab.contains("IPLJPS") || rotation.sab.contains("IPLJS"))) {
            rotationColor = this.rotationInstructionColor2;
        }
        else {
            rotationColor = this.rotationColor2;
        }
        int nbColTroncon = 7;
        if (ChopeCrew.options.webTempsVol) {
            ++nbColTroncon;
        }
        if (ChopeCrew.options.webNumVol) {
            ++nbColTroncon;
        }
        if (ChopeCrew.options.webTempsEscale) {
            ++nbColTroncon;
        }
        if (ChopeCrew.options.webPresta) {
            ++nbColTroncon;
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final SimpleDateFormat dateHourFormat = new SimpleDateFormat("dd/MM/yyyy '\u00e0' HH'h'mm");
        dateHourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calFirstTroSv = new GregorianCalendar();
        final GregorianCalendar calLastTroSv = new GregorianCalendar();
        cal.setTime(rotation.debutUTCD);
        Troncon lastTronconFlag = null;
        int memoA = -10;
        int rowspanEscale = 0;
        for (int a = 0; a < njEng; ++a) {
            String jourColor;
            if (cal.get(7) == 1 || cal.get(7) == 7) {
                jourColor = this.jourWeColor;
            }
            else {
                jourColor = this.jourSemaineColor;
            }
            if (this.memoDate.equals(dateFormat.format(cal.getTime()))) {
                ++this.memoRowSpan;
                final int index = this.html.lastIndexOf(">" + this.nl + "\t\t\t" + dateFormat.format(cal.getTime()) + this.nl + "\t\t" + "</td>");
                if (index >= 0) {
                    this.html.insert(index, " rowspan=\"" + this.memoRowSpan + "\"");
                    sb.append(this.nl);
                    sb.append("\t").append("<tr>").append(this.nl);
                }
            }
            else {
                sb.append(this.nl);
                sb.append("\t").append("<tr>").append(this.nl);
                sb.append("\t\t").append("<td width=\"").append(this.lDate).append("\" align=\"center\" nowrap=\"nowrap\" class=\"small\" bgcolor=\"").append(jourColor).append("\">").append(this.nl);
                sb.append("\t\t\t").append(dateFormat.format(cal.getTime())).append(this.nl);
                sb.append("\t\t").append("</td>").append(this.nl);
                sb.append(this.nl);
                this.memoDate = dateFormat.format(cal.getTime());
                this.memoRowSpan = 1;
            }
            boolean flagVol = false;
            for (int j = 0; j < rotation.listSV.size() && !flagVol; ++j) {
                final ServiceVol sv = rotation.listSV.get(j);
                final Troncon firstTroSV = sv.listTroncon.get(0);
                final Troncon lastTroSV = sv.listTroncon.get(sv.listTroncon.size() - 1);
                if (ChopeCrew.options.idxTimeRef == 0) {
                    calFirstTroSv.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    calLastTroSv.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                }
                else if (ChopeCrew.options.idxTimeRef == 1) {
                    final SimpleTimeZone tzFirstTroSVfrom = new SimpleTimeZone(firstTroSV.LAGfromMillis, "");
                    final SimpleTimeZone tzLastTroSVto = new SimpleTimeZone(lastTroSV.LAGtoMillis, "");
                    calFirstTroSv.setTimeZone(tzFirstTroSVfrom);
                    calLastTroSv.setTimeZone(tzLastTroSVto);
                }
                calFirstTroSv.setTime(firstTroSV.debutUTCD);
                calLastTroSv.setTime(lastTroSV.finUTCD);
                final boolean isSvSameDate = calFirstTroSv.get(5) == cal.get(5);
                if (isSvSameDate) {
                    final boolean isSvAcheval = calFirstTroSv.get(5) != calLastTroSv.get(5);
                    int rowspanSV;
                    if (!isSvAcheval) {
                        rowspanSV = 1;
                    }
                    else if (j < rotation.listSV.size() - 1) {
                        final ServiceVol nextSv = rotation.listSV.get(j + 1);
                        final Troncon firstTroNextSv = nextSv.listTroncon.get(0);
                        final GregorianCalendar calFirstNext = new GregorianCalendar();
                        if (ChopeCrew.options.idxTimeRef == 0) {
                            calFirstNext.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                        }
                        else if (ChopeCrew.options.idxTimeRef == 1) {
                            final SimpleTimeZone tzFirstNext = new SimpleTimeZone(firstTroNextSv.LAGfromMillis, "");
                            calFirstNext.setTimeZone(tzFirstNext);
                        }
                        calFirstNext.setTime(firstTroNextSv.debutUTCD);
                        if (calFirstNext.get(5) != calLastTroSv.get(5)) {
                            rowspanSV = 2;
                        }
                        else {
                            rowspanSV = 1;
                        }
                    }
                    else {
                        rowspanSV = 2;
                    }
                    flagVol = true;
                    rowspanEscale = 0;
                    lastTronconFlag = lastTroSV;
                    if (isSvAcheval) {
                        memoA = a;
                    }
                    sb.append(this.nl);
                    sb.append("\t\t").append("<td bgcolor=\"").append(rotationColor).append("\" rowspan=\"").append(rowspanSV).append("\"> ").append(this.nl);
                    sb.append("\t\t\t").append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> ").append(this.nl);
                    String typeAvionSV = "";
                    for (int k = 0; k < sv.listTroncon.size(); ++k) {
                        final Troncon tro = sv.listTroncon.get(k);
                        if (!tro.isMep) {
                            typeAvionSV = tro.typeAvion;
                        }
                        else if (tro.isMep && !rotation.isOD && typeAvionSV.equals("")) {
                            typeAvionSV = "MEP";
                        }
                        else if (tro.isMep && rotation.isOD && typeAvionSV.equals("")) {
                            typeAvionSV = "OD";
                        }
                        final SimpleDateFormat hourFormatFrom = new SimpleDateFormat("HH'h'mm");
                        final SimpleDateFormat hourFormatTo = new SimpleDateFormat("HH'h'mm");
                        if (ChopeCrew.options.idxTimeRef == 0) {
                            hourFormatFrom.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                            hourFormatTo.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                        }
                        else if (ChopeCrew.options.idxTimeRef == 1) {
                            final SimpleTimeZone tzOrigine = new SimpleTimeZone(tro.LAGfromMillis, "");
                            final SimpleTimeZone tzDestination = new SimpleTimeZone(tro.LAGtoMillis, "");
                            hourFormatFrom.setTimeZone(tzOrigine);
                            hourFormatTo.setTimeZone(tzDestination);
                        }
                        final String hDep = hourFormatFrom.format(tro.debutUTCD);
                        final String hArr = hourFormatTo.format(tro.finUTCD);
                        final String TVT = "(" + tro.TVT + ")";
                        String mep = "&nbsp;";
                        if (tro.isMep) {
                            mep = "X";
                        }
                        final String numVol = tro.numVol;
                        final String from = tro.fromClair;
                        final String to = tro.toClair;
                        String tempsEscale = "&nbsp;";
                        if (tro.tempsEscale != null) {
                            tempsEscale = tro.tempsEscale;
                        }
                        String presta = "&nbsp;";
                        if (tro.listPresta.size() > 0) {
                            presta = "";
                            for (final Presta p : tro.listPresta) {
                                if (p.type.contains("Snack Hot")) {
                                    presta = String.valueOf(presta) + "SH ";
                                }
                                else if (p.type.contains("Encas Day")) {
                                    presta = String.valueOf(presta) + "ED ";
                                }
                                else if (p.type.contains("Breakfast Hot")) {
                                    presta = String.valueOf(presta) + "BH ";
                                }
                                else if (p.type.contains("Breakfast Fresh")) {
                                    presta = String.valueOf(presta) + "BF ";
                                }
                                else {
                                    if (!p.type.contains("Encas Morning")) {
                                        continue;
                                    }
                                    presta = String.valueOf(presta) + "EM ";
                                }
                            }
                            presta = presta.trim();
                        }
                        String baliseItalique1 = "";
                        String baliseItalique2 = "";
                        if (tro.isMep) {
                            baliseItalique1 = "<em>";
                            baliseItalique2 = "</em>";
                        }
                        sb.append("\t\t\t\t").append("<tr>").append(this.nl);
                        sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lHeure).append("\" align=\"center\" nowrap=\"nowrap\" class=\"normal\" >").append(baliseItalique1).append(hDep).append(baliseItalique2).append("</td>").append(this.nl);
                        sb.append("\t\t\t\t\t").append("<td width=\"10\" align=\"center\" nowrap=\"nowrap\" class=\"small\" >").append(baliseItalique1).append("-").append(baliseItalique2).append("</td>").append(this.nl);
                        sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lHeure).append("\" align=\"center\" nowrap=\"nowrap\" class=\"normal\" >").append(baliseItalique1).append(hArr).append(baliseItalique2).append("</td>").append(this.nl);
                        if (ChopeCrew.options.webTempsVol) {
                            sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lDur\u00e9e).append("\" align=\"center\" nowrap=\"nowrap\" class=\"xsmall\" >").append(baliseItalique1).append(TVT).append(baliseItalique2).append("</td>").append(this.nl);
                        }
                        sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lMep).append("\" align=\"center\" nowrap=\"nowrap\" class=\"xsmall\" >").append(baliseItalique1).append(mep).append(baliseItalique2).append("</td>").append(this.nl);
                        if (ChopeCrew.options.webNumVol) {
                            sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lNumVol).append("\" align=\"center\" nowrap=\"nowrap\" class=\"xsmall\" >").append(baliseItalique1).append(numVol).append(baliseItalique2).append("</td>").append(this.nl);
                        }
                        sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lFromTo).append("\" align=\"center\" nowrap=\"nowrap\" class=\"normal\" >").append(baliseItalique1).append(from).append(baliseItalique2).append("</td>").append(this.nl);
                        sb.append("\t\t\t\t\t").append("<td width=\"10\" align=\"center\" nowrap=\"nowrap\" class=\"small\" >").append(baliseItalique1).append("-").append(baliseItalique2).append("</td>").append(this.nl);
                        sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lFromTo).append("\" align=\"center\" nowrap=\"nowrap\" class=\"normal\" >").append(baliseItalique1).append(to).append(baliseItalique2).append("</td>").append(this.nl);
                        if (ChopeCrew.options.webTempsEscale) {
                            sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lDur\u00e9e).append("\" align=\"center\" nowrap=\"nowrap\" class=\"xsmall\" >").append(baliseItalique1).append(tempsEscale).append(baliseItalique2).append("</td>").append(this.nl);
                        }
                        if (ChopeCrew.options.webPresta) {
                            sb.append("\t\t\t\t\t").append("<td width=\"").append(this.lPresta).append("\" align=\"center\" nowrap=\"nowrap\" class=\"xsmall\" >").append(baliseItalique1).append(presta).append(baliseItalique2).append("</td>").append(this.nl);
                        }
                        sb.append("\t\t\t\t").append("</tr>").append(this.nl);
                        if (k == sv.listTroncon.size() - 1 && tro.isDecoucher && (ChopeCrew.options.webDureeDecoucher || ChopeCrew.options.webHotel || ChopeCrew.options.webDecalage)) {
                            boolean flagRepos = false;
                            boolean flagHotel = false;
                            sb.append(this.nl);
                            sb.append("\t\t\t\t").append("<tr>").append(this.nl);
                            sb.append("\t\t\t\t\t").append("<td colspan=\"").append(nbColTroncon).append("\" nowrap=\"nowrap\" class=\"xsmall\" align=\"left\">");
                            if (ChopeCrew.options.webDureeDecoucher) {
                                sb.append("D\u00e9coucher : ").append(tro.repos);
                                flagRepos = true;
                            }
                            if (tro.hotel != null && ChopeCrew.options.webHotel) {
                                if (flagRepos) {
                                    sb.append(", ");
                                }
                                sb.append("H\u00f4tel : ").append(tro.hotel);
                                flagHotel = true;
                            }
                            if (ChopeCrew.options.webDecalage) {
                                final GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
                                gc.setTime(tro.finUTCD);
                                final int lagParis = gc.get(15) + gc.get(16);
                                double decalage = (tro.LAGtoMillis - lagParis) / 3600000.0;
                                decalage = Utils.arrondi(decalage, 1);
                                if (decalage != 0.0 && (flagRepos || flagHotel)) {
                                    sb.append(", ");
                                }
                                if (decalage > 0.0) {
                                    sb.append("D\u00e9calage : +").append(decalage);
                                }
                                else if (decalage < 0.0) {
                                    sb.append("D\u00e9calage : ").append(decalage);
                                }
                            }
                            sb.append("</td>").append(this.nl);
                            sb.append("\t\t\t\t").append("</tr>").append(this.nl);
                        }
                        else if (k == sv.listTroncon.size() - 1 && !tro.isDecoucher && ((ChopeCrew.options.webRpc && rotation.dureeRpc != null) || (ChopeCrew.options.webReeng && rotation.reengagementUTCD != null))) {
                            sb.append(this.nl);
                            sb.append("\t\t\t\t").append("<tr>").append(this.nl);
                            sb.append("\t\t\t\t\t").append("<td colspan=\"").append(nbColTroncon).append("\" nowrap=\"nowrap\" class=\"xsmall\" align=\"left\">");
                            if (ChopeCrew.options.webRpc && rotation.dureeRpc != null) {
                                sb.append("Repos post-courrier : ").append(rotation.dureeRpc);
                            }
                            if (ChopeCrew.options.webReeng && rotation.reengagementUTCD != null) {
                                if (ChopeCrew.options.webRpc && rotation.dureeRpc != null) {
                                    sb.append(", ");
                                }
                                sb.append("R\u00e9eng. : ").append(dateHourFormat.format(rotation.reengagementUTCD));
                            }
                            sb.append("</td>").append(this.nl);
                            sb.append("\t\t\t\t").append("</tr>").append(this.nl);
                        }
                    }
                    sb.append("\t\t\t").append("</table>").append(this.nl);
                    sb.append("\t\t").append("</td>").append(this.nl);
                    if (ChopeCrew.options.webTypeAvion) {
                        sb.append(this.nl);
                        sb.append("\t\t").append("<td width=\"").append(this.lTypeAvion).append("\" align=\"center\" nowrap=\"nowrap\" rowspan=\"").append(rowspanSV).append("\" class=\"small\">").append(this.nl);
                        sb.append("\t\t\t").append(typeAvionSV).append(this.nl);
                        sb.append("\t\t").append("</td>").append(this.nl);
                    }
                    if (ChopeCrew.options.webTempsVolSv) {
                        sb.append(this.nl);
                        sb.append("\t\t").append("<td width=\"").append(this.lTVTSV).append("\" nowrap=\"nowrap\" rowspan=\"").append(rowspanSV).append("\" class=\"small\">").append(this.nl);
                        sb.append("\t\t\t").append("TV = ").append(sv.TVSV).append("<br>").append("TSV = ").append(sv.TSV).append(this.nl);
                        sb.append("\t\t").append("</td>").append(this.nl);
                    }
                    if (j == 0 && ChopeCrew.options.webTempsAbsence) {
                        sb.append(this.nl);
                        sb.append("\t\t").append("<td width=\"").append(this.lTA).append("\" nowrap=\"nowrap\" rowspan=\"").append((int)njEng).append("\" class=\"small\">").append(this.nl);
                        sb.append("\t\t\t").append("TA =<br>").append(rotation.tempsAbsence).append(this.nl);
                        sb.append("\t\t").append("</td>").append(this.nl);
                    }
                }
            }
            if (!flagVol && lastTronconFlag != null && lastTronconFlag.isDecoucher && memoA + 1 != a) {
                if (++rowspanEscale > 1) {
                    final int index2 = sb.lastIndexOf(">" + this.nl + "\t\t\t" + "Escale \u00e0");
                    if (index2 >= 0) {
                        sb.insert(index2, " rowspan=\"" + rowspanEscale + "\"");
                    }
                }
                else {
                    sb.append(this.nl);
                    sb.append("\t\t").append("<td nowrap=\"nowrap\" class=\"normal\" align=\"center\"  bgcolor=\"").append(this.escaleColor).append("\">").append(this.nl);
                    sb.append("\t\t\t").append("Escale \u00e0 ").append(lastTronconFlag.to).append(this.nl);
                    sb.append("\t\t").append("</td>").append(this.nl);
                    sb.append(this.nl);
                }
            }
            sb.append("\t").append("</tr>").append(this.nl);
            cal.add(5, 1);
        }
        return sb.toString();
    }
    
    public void createHtml(final AnalyseCrew analyse) {
        String titre = new String();
        if (analyse.deltaMois == 0) {
            titre = "Planning " + analyse.moisLit + " " + analyse.anneeInt;
        }
        else if (analyse.deltaMois == 1) {
            titre = "Planning pr\u00e9visionnel " + analyse.moisLit + " " + analyse.anneeInt;
        }
        else if (analyse.deltaMois == 2) {
            titre = "Planning pr\u00e9visionnel " + analyse.moisLit + " " + analyse.anneeInt;
        }
        if (ChopeCrew.options.idxTimeRef == 0) {
            titre = String.valueOf(titre) + "  (Heures de Paris)";
        }
        else if (ChopeCrew.options.idxTimeRef == 1) {
            titre = String.valueOf(titre) + "  (Heures locales)";
        }
        this.html.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">").append(this.nl);
        this.html.append(this.nl);
        this.html.append("<html>").append(this.nl);
        this.html.append(this.nl);
        this.html.append("<head>").append(this.nl);
        this.html.append("<title>").append(this.nl);
        this.html.append("\t").append(titre).append(this.nl);
        this.html.append("</title>").append(this.nl);
        this.html.append("<style type=\"text/css\">").append(this.nl);
        this.html.append("\t").append("<!--").append(this.nl);
        this.html.append("\t").append("p{font-family : ").append(this.police).append("; font-size : 18;}").append(this.nl);
        this.html.append("\t").append("td{font-family : ").append(this.police).append(";}").append(this.nl);
        this.html.append("\t").append("td.normal{font-size : 16;}").append(this.nl);
        this.html.append("\t").append("td.small {font-size : 14;}").append(this.nl);
        this.html.append("\t").append("td.xsmall {font-size : 12;}").append(this.nl);
        this.html.append("\t").append("tr {height:2em;}").append(this.nl);
        this.html.append("\t").append("-->").append(this.nl);
        this.html.append("</style>").append(this.nl);
        this.html.append("</head>").append(this.nl);
        this.html.append(this.nl);
        this.html.append("<body>").append(this.nl);
        this.html.append(this.nl).append(this.nl);
        this.html.append("<p align=\"center\">").append(titre).append("</p>").append(this.nl);
        this.html.append("<br />").append(this.nl);
        this.html.append(this.nl);
        this.html.append("<table align=\"center\" border=\"1\" cellpadding=\"2\" cellspacing=\"0\">").append(this.nl);
        this.html.append(this.nl);
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                final Rotation rotation = (Rotation)obj;
                if (!rotation.isExportable() && !ChopeCrew.options.webMoisComplet) {
                    continue;
                }
                this.html.append(this.writeRotation(rotation));
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isExportable() && !ChopeCrew.options.webMoisComplet) {
                    continue;
                }
                this.html.append(this.writeSol(act));
            }
        }
        this.html.append("</table>").append(this.nl);
        this.html.append("</body>").append(this.nl);
        this.html.append("</html>");
    }
    
    public String getHtmlWithEncoding() {
        final int index = this.html.indexOf("<title>");
        final StringBuilder sb = new StringBuilder();
        sb.append(this.html.substring(0, index));
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">").append(this.nl);
        sb.append(this.html.substring(index));
        return sb.toString();
    }
    
    public String getHtml() {
        return this.html.toString();
    }
}
