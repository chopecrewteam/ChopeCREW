// 
// Decompiled by Procyon v0.5.36
// 

package ccExport;

import ccEngine.AnalyseCrew;
import ccStructures.Participant;
import ccStructures.ActiviteSol;
import ccStructures.Presta;
import java.util.Iterator;
import ccStructures.Deg_Reco;
import ccStructures.Dest_Reco;
import ccStructures.Peq;
import java.util.SimpleTimeZone;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import java.util.TimeZone;
import chopeCrew.ChopeCrew;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import ccStructures.Rotation;
import java.util.Date;

public class Export_Ical
{
    private String iCal;
    private Date datecourante;
    private String nl;
    private String endline;
    private String endlineHtml;
    
    public Export_Ical() {
        this.nl = "\r\n";
        this.endline = "\\n";
        this.endlineHtml = "<br>";
        this.datecourante = new Date();
    }
    
    private String writeRotation(final Rotation rotation) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        final SimpleDateFormat sdfBis = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat sdf3 = new SimpleDateFormat("dd MMMM");
        final GregorianCalendar cal = new GregorianCalendar();
        final ArrayList<String> joursEntetesRotdeb = new ArrayList<String>();
        final ArrayList<String> joursEntetesRotfin = new ArrayList<String>();
        String tzid = "";
        if (ChopeCrew.options.idxTimeRef == 0) {
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            sdfBis.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            sdf2.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            sdf3.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            cal.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            tzid = ";TZID=Paris";
        }
        else if (ChopeCrew.options.idxTimeRef == 1) {
            final ServiceVol firstSV = rotation.listSV.get(0);
            final ServiceVol lastSV = rotation.listSV.get(rotation.listSV.size() - 1);
            final Troncon firstTronconRotation = firstSV.listTroncon.get(0);
            final Troncon lastTronconRotation = lastSV.listTroncon.get(lastSV.listTroncon.size() - 1);
            final int lagOrigine = firstTronconRotation.LAGfromMillis;
            final SimpleTimeZone tzOrigine = new SimpleTimeZone(lagOrigine, "");
            final int lagFin = lastTronconRotation.LAGtoMillis;
            final SimpleTimeZone tzFin = new SimpleTimeZone(lagFin, "");
            sdf.setTimeZone(tzOrigine);
            sdfBis.setTimeZone(tzFin);
            sdf2.setTimeZone(tzOrigine);
            sdf3.setTimeZone(tzOrigine);
            cal.setTimeZone(tzOrigine);
            tzid = "";
        }
        cal.setTime(rotation.debutUTCD);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        joursEntetesRotdeb.add(sdf2.format(cal.getTime()));
        cal.add(5, 1);
        joursEntetesRotfin.add(sdf2.format(cal.getTime()));
        if (ChopeCrew.options.idxPositionRot == 0) {
            while (cal.getTimeInMillis() <= rotation.finUTCD.getTime()) {
                joursEntetesRotdeb.add(sdf2.format(cal.getTime()));
                cal.add(5, 1);
                joursEntetesRotfin.add(sdf2.format(cal.getTime()));
            }
        }
        else if (ChopeCrew.options.idxPositionRot == 4) {
            while (cal.getTimeInMillis() <= rotation.finUTCD.getTime()) {
                cal.add(5, 1);
                joursEntetesRotfin.set(0, sdf2.format(cal.getTime()));
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (int z = 0; z < joursEntetesRotdeb.size(); ++z) {
            sb.append("BEGIN:VEVENT").append(this.nl);
            sb.append("UID:").append(rotation.label).append(sdf.format(rotation.debutUTCD)).append(z).append(sdf.format(this.datecourante)).append(this.nl);
            sb.append("DTSTAMP:").append(sdf.format(this.datecourante)).append(this.nl);
            if (!ChopeCrew.options.categRot.equals("")) {
                sb.append("CATEGORIES:").append(ChopeCrew.options.categRot).append(this.nl);
            }
            if (ChopeCrew.options.idxPositionRot == 0 || ChopeCrew.options.idxPositionRot == 1 || ChopeCrew.options.idxPositionRot == 4) {
                sb.append("DTSTART").append(";VALUE=DATE:").append(joursEntetesRotdeb.get(z)).append(this.nl);
                sb.append("DTEND").append(";VALUE=DATE:").append(joursEntetesRotfin.get(z)).append(this.nl);
            }
            else if (ChopeCrew.options.idxPositionRot == 2) {
                sb.append("DTSTART").append(";VALUE=DATE-TIME").append(tzid).append(":").append(sdf.format(rotation.debutUTCD)).append(this.nl);
                sb.append("DTEND").append(";VALUE=DATE-TIME").append(tzid).append(":").append(sdf.format(rotation.debutUTCD)).append(this.nl);
            }
            else if (ChopeCrew.options.idxPositionRot == 3) {
                sb.append("DTSTART").append(";VALUE=DATE-TIME").append(tzid).append(":").append(sdf.format(rotation.debutUTCD)).append(this.nl);
                sb.append("DTEND").append(";VALUE=DATE-TIME").append(tzid).append(":").append(sdfBis.format(rotation.finUTCD)).append(this.nl);
            }
            sb.append("SUMMARY:").append(rotation.label).append(" du ").append(sdf3.format(rotation.debutUTCD)).append(this.nl);
            final StringBuilder desc = new StringBuilder();
            if (!ChopeCrew.options.persoRot.equals("")) {
                desc.append(ChopeCrew.options.persoRot).append(this.endline);
            }
            if (ChopeCrew.options.geneRot) {
                desc.append("D\u00e9part UTC : ").append(rotation.departUTC).append(this.endline);
                desc.append("Arriv\u00e9e UTC : ").append(rotation.arriveeUTC).append(this.endline);
                desc.append(this.endline);
                desc.append("Temps de vol total  : ").append(rotation.tempsVolTotal).append(this.endline);
                desc.append("Temps de vol en MEP : ").append(rotation.tempsVolMep).append(this.endline);
                desc.append("Temps d'absence : ").append(rotation.tempsAbsence).append(this.endline);
                desc.append(this.endline);
                desc.append("Dur\u00e9e PAC : ").append(rotation.dureePac).append(this.endline);
                desc.append("Dur\u00e9e RPC : ").append(rotation.dureeRpc).append(this.endline);
                desc.append("R\u00e9eng. UTC : ").append(rotation.reengagementUTC).append(this.endline);
                boolean flag = false;
                if (rotation.particularite != null && !rotation.particularite.equals("")) {
                    flag = true;
                    desc.append(this.endline);
                    desc.append("Particularit\u00e9 : ").append(rotation.particularite).append(this.endline);
                }
                if (rotation.sab != null && !rotation.sab.equals("")) {
                    if (!flag) {
                        desc.append(this.endline);
                    }
                    desc.append("Situation \u00e0 bord : ").append(rotation.sab).append(this.endline);
                }
                if (rotation.nbCDB != null && rotation.nbOPL != null) {
                    desc.append(this.endline);
                    desc.append("CDB : ").append(rotation.nbCDB).append(", OPL : ").append(rotation.nbOPL).append(this.endline);
                }
            }
            if (ChopeCrew.options.peqRot && rotation.listPeqRot.size() > 0) {
                desc.append(this.endline);
                desc.append("Equipage de la rotation :").append(this.endline);
                for (final Peq peq : rotation.listPeqRot) {
                    desc.append(peq.fab).append(" : ").append(peq.nom).append(" ").append(peq.prenom).append(this.endline);
                }
            }
            if (ChopeCrew.options.recoRot) {
                if (rotation.listDestReco.size() > 0) {
                    desc.append(this.endline);
                    desc.append("Liste reco. destination :").append(this.endline);
                    for (final Dest_Reco reco : rotation.listDestReco) {
                        desc.append(reco.dest).append(" : cat.").append(reco.categorie).append(this.endline);
                    }
                }
                if (rotation.listDegReco.size() > 0) {
                    desc.append(this.endline);
                    desc.append("Liste reco. d\u00e9gagements :").append(this.endline);
                    for (final Deg_Reco reco2 : rotation.listDegReco) {
                        desc.append(reco2.deg).append(" : cat.").append(reco2.categorie).append(" (").append(reco2.dest).append(")").append(this.endline);
                    }
                }
            }
            if (ChopeCrew.options.payeRot) {
                desc.append(this.endline);
                desc.append("Heures d\u00e9compt\u00e9es / remun\u00e9r\u00e9es :").append(this.endline);
                desc.append("HCA : ").append(rotation.HCAp).append("  /  ").append(rotation.HCAp).append(this.endline);
                desc.append("SH1 : ").append(rotation.SH1p).append("  /  ").append(rotation.SH1Rp).append(this.endline);
                desc.append("H2 :  ").append(rotation.H2p).append("  /  ").append(rotation.H2Rp).append(this.endline);
            }
            if (!desc.toString().equals("")) {
                sb.append("DESCRIPTION:");
                sb.append(desc.toString());
                sb.append(this.nl);
                sb.append("X-ALT-DESC;FMTTYPE=text/html:");
                sb.append("<FONT SIZE=\"2\">");
                sb.append(desc.toString().replaceAll("\\\\n", this.endlineHtml));
                sb.append("</FONT>");
                sb.append(this.nl);
            }
            if (ChopeCrew.options.lieuSAB && rotation.sab != null && rotation.sab != "") {
                final StringBuilder strbld = new StringBuilder("LOCATION:");
                strbld.append(rotation.sab);
                sb.append(strbld.toString()).append(this.nl);
            }
            sb.append("END:VEVENT").append(this.nl);
            sb.append(this.nl);
        }
        return sb.toString();
    }
    
    private String writeTroncon(final Troncon troncon, final ServiceVol sv, final Rotation rotation) {
        final SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        final SimpleDateFormat sdfTo = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        TimeZone tzFrom = TimeZone.getTimeZone("Europe/Paris");
        TimeZone tzTo = TimeZone.getTimeZone("Europe/Paris");
        if (ChopeCrew.options.idxTimeRef == 0) {
            tzFrom = TimeZone.getTimeZone("Europe/Paris");
            tzTo = TimeZone.getTimeZone("Europe/Paris");
            sdfFrom.setTimeZone(tzFrom);
            sdfTo.setTimeZone(tzTo);
        }
        else if (ChopeCrew.options.idxTimeRef == 1) {
            tzFrom = new SimpleTimeZone(troncon.LAGfromMillis, "");
            sdfFrom.setTimeZone(tzFrom);
            tzTo = new SimpleTimeZone(troncon.LAGtoMillis, "");
            sdfTo.setTimeZone(tzTo);
        }
        final String dtstart = sdfFrom.format(troncon.debutUTCD);
        final String dtend = sdfTo.format(troncon.finUTCD);
        final GregorianCalendar calStart = new GregorianCalendar();
        calStart.setTimeZone(tzFrom);
        calStart.setTime(troncon.debutUTCD);
        final GregorianCalendar calEnd = new GregorianCalendar();
        calEnd.setTimeZone(tzTo);
        calEnd.setTime(troncon.finUTCD);
        final StringBuilder buffer = new StringBuilder();
        buffer.append("BEGIN:VEVENT").append(this.nl);
        buffer.append("UID:").append(troncon.numVol).append(sdfFrom.format(troncon.debutUTCD)).append(dtstart).append(sdfFrom.format(this.datecourante)).append(this.nl);
        buffer.append("DTSTAMP:").append(sdfFrom.format(this.datecourante)).append(this.nl);
        if (!ChopeCrew.options.categVol.equals("")) {
            buffer.append("CATEGORIES:").append(ChopeCrew.options.categVol).append(this.nl);
        }
        if (ChopeCrew.options.idxTimeRef == 0) {
            buffer.append("DTSTART;VALUE=DATE-TIME;TZID=Paris:").append(dtstart).append(this.nl);
            buffer.append("DTEND;VALUE=DATE-TIME;TZID=Paris:").append(dtend).append(this.nl);
        }
        else {
            buffer.append("DTSTART;VALUE=DATE-TIME:").append(dtstart).append(this.nl);
            buffer.append("DTEND;VALUE=DATE-TIME:").append(dtend).append(this.nl);
        }
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
        buffer.append("SUMMARY:").append(titre).append(this.nl);
        final StringBuilder desc = new StringBuilder();
        if (!ChopeCrew.options.persoVol.equals("")) {
            desc.append(ChopeCrew.options.persoVol).append(this.endline);
        }
        if (ChopeCrew.options.geneTroncon) {
            desc.append("Type avion : ").append(troncon.typeAvion).append(this.endline);
            desc.append("Version : ").append(troncon.versionExploitation).append(this.endline);
            desc.append(this.endline);
            desc.append("D\u00e9part UTC : ").append(troncon.departUTC).append(this.endline);
            desc.append("Arriv\u00e9e UTC : ").append(troncon.arriveeUTC).append(this.endline);
            desc.append(this.endline);
            desc.append("TVSV : ").append(sv.TVSV).append(this.endline);
            desc.append("TSV : ").append(sv.TSV).append(this.endline);
            desc.append(this.endline);
            if (troncon.LAGfromMillis >= 0) {
                desc.append(troncon.from).append(" : TU ").append("+").append(troncon.lagFrom).append(this.endline);
            }
            else {
                desc.append(troncon.from).append(" : TU ").append(troncon.lagFrom).append(this.endline);
            }
            if (troncon.LAGtoMillis >= 0) {
                desc.append(troncon.to).append(" : TU ").append("+").append(troncon.lagTo).append(this.endline);
            }
            else {
                desc.append(troncon.to).append(" : TU ").append(troncon.lagTo).append(this.endline);
            }
            if (rotation.sab != null && !rotation.sab.equals("")) {
                desc.append(this.endline);
                desc.append("Situation \u00e0 bord : ").append(rotation.sab).append(this.endline);
            }
        }
        if (ChopeCrew.options.recoTroncon && troncon.listRecoDest.size() > 0) {
            desc.append(this.endline);
            desc.append("Reco. destination :").append(this.endline);
            for (final Dest_Reco reco : troncon.listRecoDest) {
                desc.append(reco.dest).append(" : cat.").append(reco.categorie).append(this.endline);
            }
        }
        if (ChopeCrew.options.recoTroncon && troncon.listRecoDeg.size() > 0) {
            desc.append(this.endline);
            desc.append("Reco. d\u00e9gagements :").append(this.endline);
            for (final Deg_Reco reco2 : troncon.listRecoDeg) {
                desc.append(reco2.deg).append(" : cat.").append(reco2.categorie).append(this.endline);
            }
        }
        if (ChopeCrew.options.peqTroncon && troncon.listPeqTroncon.size() > 0) {
            desc.append(this.endline);
            desc.append("Equipage :").append(this.endline);
            for (final Peq peq : troncon.listPeqTroncon) {
                desc.append(peq.fab).append(" : ").append(peq.nom).append(" ").append(peq.prenom).append(this.endline);
            }
        }
        if (ChopeCrew.options.indemTroncon && troncon.indemTo.escale != null) {
            desc.append(this.endline);
            desc.append("Indemnit\u00e9s ").append(troncon.to).append(" :").append(this.endline);
            desc.append("IR : ").append(troncon.indemTo.irLoc).append(" ").append(troncon.indemTo.monnaieLoc).append(" (").append(troncon.indemTo.irEur).append(" \u20ac)").append(this.endline);
            desc.append("MF : ").append(troncon.indemTo.mfLoc).append(" ").append(troncon.indemTo.monnaieLoc).append(" (").append(troncon.indemTo.mfEur).append(" \u20ac)").append(this.endline);
            desc.append("Change AF : ").append(troncon.indemTo.change).append(this.endline);
            desc.append("Date effet : ").append(troncon.indemTo.dateEffet).append(this.endline);
        }
        if (ChopeCrew.options.prestaTroncon && troncon.listPresta.size() > 0) {
            desc.append(this.endline);
            desc.append("Prestations ").append(troncon.from).append(" :").append(this.endline);
            for (final Presta presta : troncon.listPresta) {
                desc.append(presta.type).append(this.endline);
            }
        }
        if (ChopeCrew.options.hotelTroncon && troncon.isDecoucher) {
            desc.append(this.endline);
            desc.append("Repos : ").append(sv.repos).append(this.endline);
            if (troncon.hotel != null) {
                desc.append("H\u00f4tel : ").append(troncon.hotel).append(this.endline);
            }
        }
        if (ChopeCrew.options.payeTroncon) {
            desc.append(this.endline);
            desc.append("Heures d\u00e9compt\u00e9es / remun\u00e9r\u00e9es :").append(this.endline);
            desc.append("HCV : ").append(sv.HCVp).append("  /  ").append(sv.HCVRp).append(this.endline);
            desc.append("HCT : ").append(sv.HCTp).append("  /  ").append(sv.HCTp).append(this.endline);
            desc.append("CMT : ").append(sv.CMTp).append("  /  ").append(sv.CMTp).append(this.endline);
            desc.append("H1 :  ").append(sv.H1p).append("  /  ").append(sv.H1Rp).append(this.endline);
            desc.append(this.endline);
            desc.append("TVref : ").append(troncon.TVref);
            if (!troncon.isTVref) {
                desc.append(" (non valid\u00e9: \u00e9gal \u00e0 TVp)").append(this.endline);
            }
            else {
                desc.append(this.endline);
            }
            desc.append("TVp : ").append(troncon.TVp).append(this.endline);
            desc.append("HV100% : ").append(troncon.HV100p).append("  /  ").append(troncon.HV100Rp).append(this.endline);
            desc.append("MEP : ").append(troncon.MEPp).append("  /  ").append(troncon.MEPp).append(this.endline);
            desc.append("TVN : ").append(troncon.TVNp).append("  /  ").append(troncon.TVNp).append(this.endline);
        }
        if (!desc.toString().equals("")) {
            buffer.append("DESCRIPTION:");
            buffer.append(desc.toString());
            buffer.append(this.nl);
            buffer.append("X-ALT-DESC;FMTTYPE=text/html:");
            buffer.append("<FONT SIZE=\"2\">");
            buffer.append(desc.toString().replaceAll("\\\\n", this.endlineHtml));
            buffer.append("</FONT>");
            buffer.append(this.nl);
        }
        final StringBuilder sb = new StringBuilder("LOCATION:");
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
            buffer.append(sb.toString()).append(this.nl);
        }
        buffer.append("END:VEVENT").append(this.nl);
        buffer.append(this.nl);
        return buffer.toString();
    }
    
    private String writeSol(final ActiviteSol act) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        sdf2.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final String dtstart = sdf.format(act.debutUTCD);
        String dstart = sdf2.format(act.debutUTCD);
        final String dtend = sdf.format(act.finUTCD);
        String dend = sdf2.format(act.finUTCD);
        final GregorianCalendar calStart = new GregorianCalendar();
        calStart.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        calStart.setTime(act.debutUTCD);
        final GregorianCalendar calEnd = new GregorianCalendar();
        calEnd.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        calEnd.setTime(act.finUTCD);
        final StringBuilder buffer = new StringBuilder();
        dstart = sdf2.format(calStart.getTime());
        calEnd.add(5, 1);
        dend = sdf2.format(calEnd.getTime());
        buffer.append("BEGIN:VEVENT").append(this.nl);
        buffer.append("UID:").append(act.code).append(sdf.format(act.debutUTCD)).append(dtstart).append(sdf.format(this.datecourante)).append(this.nl);
        buffer.append("DTSTAMP:").append(sdf.format(this.datecourante)).append(this.nl);
        if (act.code.equals("MCA") || act.code.equals("MCE")) {
            if (!ChopeCrew.options.categConges.equals("")) {
                buffer.append("CATEGORIES:").append(ChopeCrew.options.categConges).append(this.nl);
            }
        }
        else if (act.code.equals("PRB") || act.code.equals("MAD") || act.code.equals("PAC") || act.code.equals("RPC")) {
            if (!ChopeCrew.options.categRepos.equals("")) {
                buffer.append("CATEGORIES:").append(ChopeCrew.options.categRepos).append(this.nl);
            }
        }
        else if (act.code.equals("MDV") || act.code.equals("MAS")) {
            if (!ChopeCrew.options.categAbsences.equals("")) {
                buffer.append("CATEGORIES:").append(ChopeCrew.options.categAbsences).append(this.nl);
            }
        }
        else if (act.code.equals("DSP")) {
            if (!ChopeCrew.options.categDispersions.equals("")) {
                buffer.append("CATEGORIES:").append(ChopeCrew.options.categDispersions).append(this.nl);
            }
        }
        else if (!ChopeCrew.options.categActSol.equals("")) {
            buffer.append("CATEGORIES:").append(ChopeCrew.options.categActSol).append(this.nl);
        }
        if (!act.isH24) {
            if (ChopeCrew.options.idxTimeRef == 0) {
                buffer.append("DTSTART;VALUE=DATE-TIME;TZID=Paris:").append(dtstart).append(this.nl);
                buffer.append("DTEND;VALUE=DATE-TIME;TZID=Paris:").append(dtend).append(this.nl);
            }
            else {
                buffer.append("DTSTART;VALUE=DATE-TIME:").append(dtstart).append(this.nl);
                buffer.append("DTEND;VALUE=DATE-TIME:").append(dtend).append(this.nl);
            }
        }
        else {
            buffer.append("DTSTART;VALUE=DATE:").append(dstart).append(this.nl);
            buffer.append("DTEND;VALUE=DATE:").append(dend).append(this.nl);
        }
        buffer.append("SUMMARY:");
        if (act.code.equals("PAC") || act.code.equals("RPC") || act.code.equals("PRB") || act.code.equals("MAD")) {
            buffer.append("Repos : ").append(act.label);
        }
        else if (act.code.equals("MCA") || act.code.equals("MCE")) {
            buffer.append("Cong\u00e9s : ").append(act.label);
        }
        else if (act.code.equals("SST")) {
            buffer.append("ECP : ").append(act.label);
        }
        else if (act.code.equals("MCI")) {
            buffer.append("INS : ").append(act.label);
        }
        else if (act.code.equals("DSP") && act.isBlocActivite) {
            if (act.bloc.code.equals("MBR")) {
                buffer.append("Dispersion (Bloc r\u00e9serve)");
            }
            else if (act.bloc.code.equals("SST")) {
                buffer.append("Dispersion (Bloc stage)");
            }
            else {
                buffer.append("Dispersion (Bloc activit\u00e9s)");
            }
        }
        else if (!act.label.equals(null) && !act.label.equals("")) {
            buffer.append(act.label);
        }
        else {
            buffer.append(act.categorie);
        }
        buffer.append(this.nl);
        if ((act.lieu != null && act.lieu != "") || (act.salle != null && act.salle != "")) {
            buffer.append("LOCATION:");
            if (act.lieu != null && act.lieu != "") {
                buffer.append(act.lieu);
                if (act.salle != null && act.salle != "") {
                    buffer.append(" ");
                }
            }
            if (act.salle != null && act.salle != "") {
                buffer.append(act.salle);
            }
            buffer.append(this.nl);
        }
        final StringBuilder desc = new StringBuilder();
        if (act.code.equals("MCA") || act.code.equals("MCE")) {
            if (!ChopeCrew.options.persoConges.equals("")) {
                desc.append(ChopeCrew.options.persoConges).append(this.endline);
            }
        }
        else if (act.code.equals("PRB") || act.code.equals("MAD") || act.code.equals("PAC") || act.code.equals("RPC")) {
            if (!ChopeCrew.options.persoRepos.equals("")) {
                desc.append(ChopeCrew.options.persoRepos).append(this.endline);
            }
        }
        else if (act.code.equals("MDV") || act.code.equals("MAS")) {
            if (!ChopeCrew.options.persoAbsences.equals("")) {
                desc.append(ChopeCrew.options.persoAbsences).append(this.endline);
            }
        }
        else if (act.code.equals("DSP")) {
            if (!ChopeCrew.options.persoDispersions.equals("")) {
                desc.append(ChopeCrew.options.persoDispersions).append(this.endline);
            }
        }
        else if (!ChopeCrew.options.persoActSol.equals("")) {
            desc.append(ChopeCrew.options.persoActSol).append(this.endline);
        }
        if (ChopeCrew.options.detailsSol) {
            desc.append(act.label).append(this.endline);
            desc.append("Cat\u00e9gorie (FAST): ").append(act.categorie).append(" (").append(act.code).append(")").append(this.endline);
            desc.append("D\u00e9but : ").append(act.debut).append(" LT").append(this.endline);
            desc.append("Fin : ").append(act.fin).append(" LT").append(this.endline);
            if (act.lieu != null || act.salle != null || act.commentaire != null) {
                desc.append(this.endline);
                desc.append("Lieu : ").append(act.lieu).append(this.endline);
                desc.append("Salle : ").append(act.salle).append(this.endline);
                desc.append("Commentaires : ").append(act.commentaire).append(this.endline);
            }
            if (act.listParticipant.size() > 0) {
                desc.append(this.endline);
                desc.append("Participants : ").append(this.endline);
                for (final Participant part : act.listParticipant) {
                    desc.append(part.nom).append(" ").append(part.prenom).append(" (").append(part.statut).append(")").append(this.endline);
                }
            }
        }
        if (ChopeCrew.options.payeSol) {
            desc.append(this.endline);
            desc.append("HCS : ").append(act.HCS).append(this.endline);
        }
        if (!desc.toString().equals("")) {
            buffer.append("DESCRIPTION:");
            buffer.append(desc.toString());
            buffer.append(this.nl);
            buffer.append("X-ALT-DESC;FMTTYPE=text/html:");
            buffer.append("<FONT SIZE=\"2\">");
            buffer.append(desc.toString().replaceAll("\\\\n", this.endlineHtml));
            buffer.append("</FONT>");
            buffer.append(this.nl);
        }
        buffer.append("END:VEVENT").append(this.nl);
        buffer.append(this.nl);
        return buffer.toString();
    }
    
    public void createICal(final AnalyseCrew analyse) {
        final StringBuilder sbIcal = new StringBuilder();
        sbIcal.append("BEGIN:VCALENDAR").append(this.nl);
        sbIcal.append("PRODID:ChopeCREW ").append("3.8").append(this.nl);
        sbIcal.append("VERSION:2.0").append(this.nl);
        sbIcal.append("METHOD:PUBLISH").append(this.nl).append(this.nl);
        sbIcal.append("BEGIN:VTIMEZONE").append(this.nl);
        sbIcal.append("TZID:Paris").append(this.nl);
        sbIcal.append("BEGIN:STANDARD").append(this.nl);
        sbIcal.append("DTSTART:16011028T030000").append(this.nl);
        sbIcal.append("RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=10").append(this.nl);
        sbIcal.append("TZOFFSETFROM:+0200").append(this.nl);
        sbIcal.append("TZOFFSETTO:+0100").append(this.nl);
        sbIcal.append("END:STANDARD").append(this.nl);
        sbIcal.append("BEGIN:DAYLIGHT").append(this.nl);
        sbIcal.append("DTSTART:16010325T020000").append(this.nl);
        sbIcal.append("RRULE:FREQ=YEARLY;BYDAY=-1SU;BYMONTH=3").append(this.nl);
        sbIcal.append("TZOFFSETFROM:+0100").append(this.nl);
        sbIcal.append("TZOFFSETTO:+0200").append(this.nl);
        sbIcal.append("END:DAYLIGHT").append(this.nl);
        sbIcal.append("END:VTIMEZONE").append(this.nl).append(this.nl);
        for (final Rotation rotation : analyse.listRotation) {
            if (rotation.isExportable()) {
                sbIcal.append(this.writeRotation(rotation));
            }
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    if (troncon.isExportable()) {
                        sbIcal.append(this.writeTroncon(troncon, sv, rotation));
                    }
                }
            }
        }
        for (final ActiviteSol act : analyse.listSol) {
            if (act.isExportable()) {
                sbIcal.append(this.writeSol(act));
            }
        }
        sbIcal.append("END:VCALENDAR");
        this.iCal = sbIcal.toString().replaceAll(",", "\\\\,").replaceAll("null", "NIL");
    }
    
    public String getICal() {
        return this.iCal;
    }
}
