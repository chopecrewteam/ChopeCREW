// 
// Decompiled by Procyon v0.5.36
// 

package ccExport;

import chopeCrew.ChopeCrew;
import java.io.InputStream;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Comparator;
import java.util.SimpleTimeZone;
import java.util.GregorianCalendar;
import ccUtils.Utils;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import ccEngine.AnalyseCrew;
import ccStructures.Escale;
import ccStructures.ActiviteSol;
import ccStructures.Rotation;
import java.util.ArrayList;
import java.io.File;

public class Export_Stats
{
    private File repPlannings;
    private ArrayList<File> listPlannings;
    private ArrayList<Rotation> listRotations;
    private ArrayList<ActiviteSol> listActSol;
    public int compteurEtapes;
    public double compteurHDVp;
    public double compteurHDVr;
    public double compteurMEPp;
    public double compteurMEPr;
    public double compteurTSVp;
    public double compteurTSVr;
    public double compteurTAp;
    public double compteurTAr;
    public int compteurPonctuDepartD0;
    public int compteurPonctuDepartD3;
    public int compteurPonctuDepartD15;
    public int compteurPonctuDepartD60;
    public int compteurPonctuArriveeD0;
    public int compteurPonctuArriveeD3;
    public int compteurPonctuArriveeD15;
    public int compteurPonctuArriveeD60;
    public int compteurJE;
    public int compteurJDisp;
    public int compteurJConges;
    public int compteurJSansSolde;
    public int compteurJMaladie;
    public int compteurJRepos;
    public int compteurJSyndicat;
    public int compteurJActSol;
    public int compteurJRpcPac;
    public StringBuilder sbFRstats;
    public StringBuilder sbFRtableur;
    public ArrayList<Escale> listEscale;
    public ArrayList<Escale> listFrais;
    
    public Export_Stats(final File repPlannings) {
        this.listPlannings = new ArrayList<File>();
        this.listRotations = new ArrayList<Rotation>();
        this.listActSol = new ArrayList<ActiviteSol>();
        this.compteurEtapes = 0;
        this.compteurHDVp = 0.0;
        this.compteurHDVr = 0.0;
        this.compteurMEPp = 0.0;
        this.compteurMEPr = 0.0;
        this.compteurTSVp = 0.0;
        this.compteurTSVr = 0.0;
        this.compteurTAp = 0.0;
        this.compteurTAr = 0.0;
        this.compteurPonctuDepartD0 = 0;
        this.compteurPonctuDepartD3 = 0;
        this.compteurPonctuDepartD15 = 0;
        this.compteurPonctuDepartD60 = 0;
        this.compteurPonctuArriveeD0 = 0;
        this.compteurPonctuArriveeD3 = 0;
        this.compteurPonctuArriveeD15 = 0;
        this.compteurPonctuArriveeD60 = 0;
        this.compteurJE = 0;
        this.compteurJDisp = 0;
        this.compteurJConges = 0;
        this.compteurJSansSolde = 0;
        this.compteurJMaladie = 0;
        this.compteurJRepos = 0;
        this.compteurJSyndicat = 0;
        this.compteurJActSol = 0;
        this.compteurJRpcPac = 0;
        this.sbFRstats = new StringBuilder();
        this.sbFRtableur = new StringBuilder();
        this.listEscale = new ArrayList<Escale>();
        this.listFrais = new ArrayList<Escale>();
        this.repPlannings = repPlannings;
    }
    
    public void genereFR() {
        final HashMap<String, Escale> dbEscales = this.importBaseEscales();
        final File[] lf = this.repPlannings.listFiles();
        if (lf != null) {
            File[] array;
            for (int length = (array = lf).length, n = 0; n < length; ++n) {
                final File f = array[n];
                if (f.isFile() && f.getName().endsWith(".xml")) {
                    this.listPlannings.add(f);
                    System.out.println(f.getName());
                }
            }
        }
        final Iterator<File> iterator = this.listPlannings.iterator();
        while (iterator.hasNext()) {
            final File f = iterator.next();
            final AnalyseCrew analyse = new AnalyseCrew();
            try {
                final InputStream is = new FileInputStream(f);
                final BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                final StringBuilder sb = new StringBuilder();
                String tmp;
                while ((tmp = in.readLine()) != null) {
                    sb.append(tmp).append(System.getProperty("line.separator"));
                }
                in.close();
                is.close();
                analyse.chargeAnalyseCrewFromXml(sb.toString());
                for (final Rotation rot : analyse.listRotation) {
                    if (!this.listRotations.contains(rot)) {
                        this.listRotations.add(rot);
                    }
                }
                for (final ActiviteSol act : analyse.listSol) {
                    if (!this.listActSol.contains(act)) {
                        this.listActSol.add(act);
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Erreur Fichier");
            }
        }
        Collections.sort(this.listRotations);
        Collections.sort(this.listActSol);
        final HashSet<String> listDateSol = new HashSet<String>();
        for (final ActiviteSol act2 : this.listActSol) {
            if (act2.code.equals("DSP")) {
                ++this.compteurJDisp;
            }
            else if (act2.code.equals("PAC") || act2.code.equals("RPC")) {
                ++this.compteurJRpcPac;
            }
            else if (act2.code.equals("MCA") || act2.code.equals("MCE")) {
                ++this.compteurJConges;
            }
            else if (act2.code.equals("MDV")) {
                ++this.compteurJSansSolde;
            }
            else if (act2.code.equals("MAS")) {
                ++this.compteurJMaladie;
            }
            else if (act2.code.equals("PRB") || act2.code.equals("MAD")) {
                ++this.compteurJRepos;
            }
            else if (act2.code.equals("SYN")) {
                ++this.compteurJSyndicat;
            }
            else {
                if (act2.code.equals("MCP") || act2.isReserveDeclenchee) {
                    continue;
                }
                listDateSol.add(act2.jour);
            }
        }
        this.compteurJActSol = listDateSol.size();
        for (int i = 0; i < this.listRotations.size(); ++i) {
            final Rotation rot2 = this.listRotations.get(i);
            this.compteurJE += (int)rot2.NJEp;
            this.compteurTAp += rot2.TAp;
            this.compteurTAr += rot2.TAr;
            boolean isRotationZoneLong = false;
            String descriptionRotation = "";
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            for (int j = 0; j < rot2.listSV.size(); ++j) {
                final ServiceVol sv = rot2.listSV.get(j);
                this.compteurTSVp += sv.TSVp;
                this.compteurTSVr += sv.TSVr;
                for (int k = 0; k < sv.listTroncon.size(); ++k) {
                    final Troncon troncon = sv.listTroncon.get(k);
                    if (!troncon.isMep) {
                        ++this.compteurEtapes;
                        final long retardDepart = troncon.DEPr - troncon.DEPp;
                        final long retardArrivee = troncon.ARRr - troncon.ARRp;
                        if (retardDepart < 60000L) {
                            ++this.compteurPonctuDepartD0;
                        }
                        if (retardDepart < 240000L) {
                            ++this.compteurPonctuDepartD3;
                        }
                        if (retardDepart < 960000L) {
                            ++this.compteurPonctuDepartD15;
                        }
                        if (retardDepart < 3660000L) {
                            ++this.compteurPonctuDepartD60;
                        }
                        if (retardArrivee < 60000L) {
                            ++this.compteurPonctuArriveeD0;
                        }
                        if (retardArrivee < 240000L) {
                            ++this.compteurPonctuArriveeD3;
                        }
                        if (retardArrivee < 960000L) {
                            ++this.compteurPonctuArriveeD15;
                        }
                        if (retardArrivee < 3660000L) {
                            ++this.compteurPonctuArriveeD60;
                        }
                    }
                    this.compteurHDVp += troncon.TVp;
                    this.compteurHDVr += troncon.TVr;
                    this.compteurMEPp += troncon.MEPp;
                    this.compteurMEPr += troncon.MEPr;
                    Escale esc = dbEscales.get(troncon.to);
                    if (esc == null) {
                        esc = new Escale();
                        esc.code = troncon.to;
                    }
                    this.listEscale.remove(esc);
                    final Escale escale2 = esc;
                    ++escale2.compteurNE;
                    this.listEscale.add(esc);
                    final long escale = Utils.timeStringtoMs(troncon.tempsEscale);
                    final long repos = Utils.timeStringtoMs(troncon.repos);
                    String zone = null;
                    try {
                        zone = dbEscales.get(troncon.to).zone;
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (zone != null && zone.equals("L") && (escale >= 25200000L || repos >= 25200000L)) {
                        isRotationZoneLong = true;
                    }
                    if (k == 0 && j == 0) {
                        descriptionRotation = String.valueOf(descriptionRotation) + troncon.from;
                    }
                    if (troncon.isDecoucher) {
                        descriptionRotation = String.valueOf(descriptionRotation) + " -- " + troncon.to + " (" + troncon.repos + ")";
                    }
                    if (k == sv.listTroncon.size() - 1 && j == rot2.listSV.size() - 1) {
                        descriptionRotation = String.valueOf(descriptionRotation) + " -- " + troncon.to;
                    }
                }
            }
            final String debutRotation = sdf.format(rot2.debutUTCD);
            final String finRotation = sdf.format(rot2.finUTCD);
            String zoneRotation;
            if (!isRotationZoneLong) {
                zoneRotation = "M";
            }
            else {
                zoneRotation = "L";
            }
            this.sbFRstats.append(debutRotation).append(" - ").append(finRotation).append("\t").append(descriptionRotation).append("\n");
            this.sbFRtableur.append(debutRotation).append(";").append(finRotation).append(";").append(zoneRotation).append(";").append(descriptionRotation).append("\n");
            double nbIndemRotation;
            if (!isRotationZoneLong) {
                nbIndemRotation = rot2.NJEp - 0.5;
            }
            else {
                nbIndemRotation = rot2.NJEp;
            }
            final ArrayList<ArrayList<Object>> fraisRot = new ArrayList<ArrayList<Object>>();
            double compteur = nbIndemRotation;
            for (int l = 0; l < rot2.listSV.size(); ++l) {
                final ServiceVol sv2 = rot2.listSV.get(l);
                if (rot2.listSV.size() == 1) {
                    final Troncon lastTroncon = sv2.listTroncon.get(sv2.listTroncon.size() - 1);
                    final ArrayList<Object> val = new ArrayList<Object>();
                    val.add(lastTroncon.to);
                    val.add(lastTroncon.toClair);
                    val.add(dbEscales.get(lastTroncon.to).pays);
                    val.add(compteur);
                    val.add(dbEscales.get(lastTroncon.to).zone);
                    fraisRot.add(val);
                    break;
                }
                for (int m = 0; m < sv2.listTroncon.size(); ++m) {
                    final Troncon troncon2 = sv2.listTroncon.get(m);
                    if (troncon2.isDecoucher) {
                        final GregorianCalendar calArr = new GregorianCalendar();
                        final SimpleTimeZone tzArr = new SimpleTimeZone(troncon2.LAGtoMillis, "");
                        calArr.setTimeZone(tzArr);
                        calArr.setTime(troncon2.finUTCD);
                        final ServiceVol svNext = rot2.listSV.get(l + 1);
                        final Troncon firstTroNextSv = svNext.listTroncon.get(0);
                        final GregorianCalendar calNextDep = new GregorianCalendar();
                        final SimpleTimeZone tzNextDep = new SimpleTimeZone(firstTroNextSv.LAGfromMillis, "");
                        calNextDep.setTimeZone(tzNextDep);
                        calNextDep.setTime(firstTroNextSv.debutUTCD);
                        final int nbDec = Utils.calculDecouchersLC(calArr, calNextDep, 1260, 540, 539);
                        if (l < rot2.listSV.size() - 2) {
                            final ArrayList<Object> val2 = new ArrayList<Object>();
                            val2.add(troncon2.to);
                            val2.add(troncon2.toClair);
                            val2.add(dbEscales.get(troncon2.to).pays);
                            val2.add((double)nbDec);
                            val2.add(dbEscales.get(troncon2.to).zone);
                            fraisRot.add(val2);
                            compteur -= nbDec;
                        }
                        else {
                            final ArrayList<Object> val2 = new ArrayList<Object>();
                            val2.add(troncon2.to);
                            val2.add(troncon2.toClair);
                            val2.add(dbEscales.get(troncon2.to).pays);
                            val2.add(compteur);
                            val2.add(dbEscales.get(troncon2.to).zone);
                            fraisRot.add(val2);
                        }
                    }
                }
            }
            this.sbFRstats.append("\t           ");
            for (final ArrayList<Object> vals : fraisRot) {
                this.sbFRstats.append(vals.get(0) + "  " + vals.get(3)).append("          ");
                this.sbFRtableur.append(vals.get(0) + ";" + vals.get(1) + ";" + vals.get(2)).append("\n");
            }
            this.sbFRstats.append("\n").append("\n");
            this.sbFRtableur.append("\n");
            for (final ArrayList<Object> vals : fraisRot) {
                Escale esc2 = dbEscales.get(vals.get(0));
                if (esc2 == null) {
                    esc2 = new Escale();
                    //esc2.code = vals.get(0);
                }
                this.listFrais.remove(esc2);
                final Escale escale3 = esc2;
                //escale3.compteurFR += vals.get(3);
                this.listFrais.add(esc2);
            }
        }
        this.compteurHDVp = Utils.arrondi(this.compteurHDVp, 2);
        this.compteurHDVr = Utils.arrondi(this.compteurHDVr, 2);
        this.compteurMEPp = Utils.arrondi(this.compteurMEPp, 2);
        this.compteurMEPr = Utils.arrondi(this.compteurMEPr, 2);
        this.compteurTSVp = Utils.arrondi(this.compteurTSVp, 2);
        this.compteurTAp = Utils.arrondi(this.compteurTAp, 2);
        this.compteurTSVr = Utils.arrondi(this.compteurTSVr, 2);
        this.compteurTAr = Utils.arrondi(this.compteurTAr, 2);
        Collections.sort(this.listEscale, new Comparator<Escale>() {
            @Override
            public int compare(final Escale e1, final Escale e2) {
                return e1.code.compareTo(e2.code);
            }
        });
        Collections.sort(this.listEscale, new Comparator<Escale>() {
            @Override
            public int compare(final Escale e1, final Escale e2) {
                if (e2.compteurNE - e1.compteurNE < 0) {
                    return -1;
                }
                if (e2.compteurNE - e1.compteurNE > 0) {
                    return 1;
                }
                return 0;
            }
        });
        Collections.sort(this.listFrais, new Comparator<Escale>() {
            @Override
            public int compare(final Escale e1, final Escale e2) {
                return e1.code.compareTo(e2.code);
            }
        });
        Collections.sort(this.listFrais, new Comparator<Escale>() {
            @Override
            public int compare(final Escale e1, final Escale e2) {
                return e1.pays.compareTo(e2.pays);
            }
        });
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
}
