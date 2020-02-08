// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI.EP4Table;

import java.util.Iterator;
import ccStructures.ActiviteSol;
import ccUtils.Utils;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import java.util.TimeZone;
import ccStructures.Rotation;
import java.text.SimpleDateFormat;
import ccEngine.AnalyseCrew;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class EP4TableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;
    private ArrayList<String> columnIdentifiers;
    private ArrayList<ArrayList<String>> dataVector;
    
    public EP4TableModel() {
        this.columnIdentifiers = null;
        this.dataVector = null;
        this.columnIdentifiers = new ArrayList<String>();
        this.dataVector = new ArrayList<ArrayList<String>>();
    }
    
    @Override
    public int getRowCount() {
        return this.dataVector.size();
    }
    
    @Override
    public int getColumnCount() {
        return this.columnIdentifiers.size();
    }
    
    @Override
    public Object getValueAt(final int row, final int column) {
        return this.dataVector.get(row).get(column);
    }
    
    @Override
    public String getColumnName(final int columnIndex) {
        return this.columnIdentifiers.get(columnIndex);
    }
    
    public void loadTableTempsVol(final AnalyseCrew analyse) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        final SimpleDateFormat hourFormat = new SimpleDateFormat("HH'h'mm");
        this.columnIdentifiers.clear();
        this.dataVector.clear();
        this.columnIdentifiers.add("Date");
        this.columnIdentifiers.add("N° de vol");
        this.columnIdentifiers.add("MEP");
        this.columnIdentifiers.add("De");
        this.columnIdentifiers.add("A");
        this.columnIdentifiers.add("OUT prog.");
        this.columnIdentifiers.add("IN prog.");
        this.columnIdentifiers.add("TV/MEP prog.");
        this.columnIdentifiers.add("OUT r\u00e9al.");
        this.columnIdentifiers.add("IN r\u00e9al.");
        this.columnIdentifiers.add("TV/MEP r\u00e9al.");
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                hourFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        final ArrayList<String> row = new ArrayList<String>();
                        row.add(dateFormat.format(troncon.debutUTCD));
                        row.add(troncon.numVol);
                        if (troncon.isMep && !rotation.isOD) {
                            row.add("MEP");
                        }
                        else if (troncon.isMep && rotation.isOD) {
                            row.add("OD");
                        }
                        else if (!troncon.isMep) {
                            row.add(null);
                        }
                        row.add(troncon.from);
                        row.add(troncon.to);
                        row.add(Utils.hourtoString(troncon.DEBp));
                        row.add(Utils.hourtoString(Utils.arrondi(troncon.FINp % 24.0, 2)));
                        if (!troncon.isMep) {
                            row.add(Utils.timetoString(troncon.TVp));
                        }
                        else if (troncon.isMep) {
                            row.add(Utils.timetoString(troncon.MEPp));
                        }
                        row.add(Utils.hourtoString(troncon.DEBr));
                        row.add(Utils.hourtoString(Utils.arrondi(troncon.FINr % 24.0, 2)));
                        if (!troncon.isMep) {
                            row.add(Utils.timetoString(troncon.TVr));
                        }
                        else if (troncon.isMep) {
                            row.add(Utils.timetoString(troncon.MEPr));
                        }
                        this.dataVector.add(row);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                hourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                final ArrayList<String> row2 = new ArrayList<String>();
                row2.add(dateFormat.format(act.debutUTCD));
                row2.add(act.code);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                this.dataVector.add(row2);
            }
        }
    }
    
    public void loadTableHCp(final AnalyseCrew analyse) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        this.columnIdentifiers.clear();
        this.dataVector.clear();
        this.columnIdentifiers.add("Date");
        this.columnIdentifiers.add("De");
        this.columnIdentifiers.add("A");
        this.columnIdentifiers.add("TV prog.");
        this.columnIdentifiers.add("TV ref.");
        this.columnIdentifiers.add("HV100% prog.");
        this.columnIdentifiers.add("MEP prog.");
        this.columnIdentifiers.add("TME prog.");
        this.columnIdentifiers.add("CMT prog.");
        this.columnIdentifiers.add("HCV prog.");
        this.columnIdentifiers.add("HCT prog.");
        this.columnIdentifiers.add("\u03a3H1 prog.");
        this.columnIdentifiers.add("HCA prog.");
        this.columnIdentifiers.add("TVN prog.");
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        final ArrayList<String> row = new ArrayList<String>();
                        row.add(dateFormat.format(troncon.debutUTCD));
                        row.add(troncon.from);
                        row.add(troncon.to);
                        row.add(String.valueOf(troncon.TVp));
                        row.add(String.valueOf(troncon.TVref));
                        row.add(String.valueOf(troncon.HV100p));
                        row.add(String.valueOf(troncon.MEPp));
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TMEp));
                            row.add(String.valueOf(sv.CMTp));
                            row.add(String.valueOf(sv.HCVp));
                            row.add(String.valueOf(sv.HCTp));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size() && sv.numSV == rotation.listSV.size()) {
                            row.add(String.valueOf(rotation.SH1p));
                            row.add(String.valueOf(rotation.HCAp));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TSVNp));
                        }
                        else {
                            row.add(null);
                        }
                        this.dataVector.add(row);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                final ArrayList<String> row2 = new ArrayList<String>();
                row2.add(dateFormat.format(act.debutUTCD));
                row2.add(act.code);
                row2.add(null);
                row2.add(String.valueOf(act.HCS));
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(String.valueOf(act.HCS));
                row2.add(null);
                this.dataVector.add(row2);
            }
        }
    }
    
    public void loadTableHCr(final AnalyseCrew analyse) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        this.columnIdentifiers.clear();
        this.dataVector.clear();
        this.columnIdentifiers.add("Date");
        this.columnIdentifiers.add("De");
        this.columnIdentifiers.add("A");
        this.columnIdentifiers.add("TV r\u00e9al.");
        this.columnIdentifiers.add("TV ref.");
        this.columnIdentifiers.add("HV100% r\u00e9al.");
        this.columnIdentifiers.add("MEP r\u00e9al.");
        this.columnIdentifiers.add("TME r\u00e9al.");
        this.columnIdentifiers.add("CMT r\u00e9al.");
        this.columnIdentifiers.add("HCV r\u00e9al.");
        this.columnIdentifiers.add("HCT r\u00e9al.");
        this.columnIdentifiers.add("\u03a3H1 r\u00e9al.");
        this.columnIdentifiers.add("HCA r\u00e9al.");
        this.columnIdentifiers.add("TVN r\u00e9al.");
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        final ArrayList<String> row = new ArrayList<String>();
                        row.add(dateFormat.format(troncon.debutUTCD));
                        row.add(troncon.from);
                        row.add(troncon.to);
                        row.add(String.valueOf(troncon.TVr));
                        row.add(String.valueOf(troncon.TVref));
                        row.add(String.valueOf(troncon.HV100r));
                        row.add(String.valueOf(troncon.MEPr));
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TMEr));
                            row.add(String.valueOf(sv.CMTr));
                            row.add(String.valueOf(sv.HCVr));
                            row.add(String.valueOf(sv.HCTr));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size() && sv.numSV == rotation.listSV.size()) {
                            row.add(String.valueOf(rotation.SH1r));
                            row.add(String.valueOf(rotation.HCAr));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TSVNr));
                        }
                        else {
                            row.add(null);
                        }
                        this.dataVector.add(row);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                final ArrayList<String> row2 = new ArrayList<String>();
                row2.add(dateFormat.format(act.debutUTCD));
                row2.add(act.code);
                row2.add(null);
                row2.add(String.valueOf(act.HCS));
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(String.valueOf(act.HCS));
                row2.add(null);
                this.dataVector.add(row2);
            }
        }
    }
    
    public void loadTableHCRp(final AnalyseCrew analyse) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        this.columnIdentifiers.clear();
        this.dataVector.clear();
        this.columnIdentifiers.add("Date");
        this.columnIdentifiers.add("De");
        this.columnIdentifiers.add("A");
        this.columnIdentifiers.add("TV prog.");
        this.columnIdentifiers.add("TV ref.");
        this.columnIdentifiers.add("HV100R% prog.");
        this.columnIdentifiers.add("MEP prog.");
        this.columnIdentifiers.add("TME prog.");
        this.columnIdentifiers.add("CMT prog.");
        this.columnIdentifiers.add("HCVR prog.");
        this.columnIdentifiers.add("HCT prog.");
        this.columnIdentifiers.add("\u03a3H1R prog.");
        this.columnIdentifiers.add("HCA prog.");
        this.columnIdentifiers.add("TVN prog.");
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        final ArrayList<String> row = new ArrayList<String>();
                        row.add(dateFormat.format(troncon.debutUTCD));
                        row.add(troncon.from);
                        row.add(troncon.to);
                        row.add(String.valueOf(troncon.TVp));
                        row.add(String.valueOf(troncon.TVref));
                        row.add(String.valueOf(troncon.HV100Rp));
                        row.add(String.valueOf(troncon.MEPp));
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TMEp));
                            row.add(String.valueOf(sv.CMTp));
                            row.add(String.valueOf(sv.HCVRp));
                            row.add(String.valueOf(sv.HCTp));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size() && sv.numSV == rotation.listSV.size()) {
                            row.add(String.valueOf(rotation.SH1Rp));
                            row.add(String.valueOf(rotation.HCAp));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TSVNp));
                        }
                        else {
                            row.add(null);
                        }
                        this.dataVector.add(row);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                final ArrayList<String> row2 = new ArrayList<String>();
                row2.add(dateFormat.format(act.debutUTCD));
                row2.add(act.code);
                row2.add(null);
                row2.add(String.valueOf(act.HCRS));
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(String.valueOf(act.HCRS));
                row2.add(null);
                this.dataVector.add(row2);
            }
        }
    }
    
    public void loadTableHCRr(final AnalyseCrew analyse) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        this.columnIdentifiers.clear();
        this.dataVector.clear();
        this.columnIdentifiers.add("Date");
        this.columnIdentifiers.add("De");
        this.columnIdentifiers.add("A");
        this.columnIdentifiers.add("TV r\u00e9al.");
        this.columnIdentifiers.add("TV ref.");
        this.columnIdentifiers.add("HV100R% r\u00e9al.");
        this.columnIdentifiers.add("MEP r\u00e9al.");
        this.columnIdentifiers.add("TME r\u00e9al.");
        this.columnIdentifiers.add("CMT r\u00e9al.");
        this.columnIdentifiers.add("HCVR r\u00e9al.");
        this.columnIdentifiers.add("HCT r\u00e9al.");
        this.columnIdentifiers.add("\u03a3H1R r\u00e9al.");
        this.columnIdentifiers.add("HCA r\u00e9al.");
        this.columnIdentifiers.add("TVN r\u00e9al.");
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        final ArrayList<String> row = new ArrayList<String>();
                        row.add(dateFormat.format(troncon.debutUTCD));
                        row.add(troncon.from);
                        row.add(troncon.to);
                        row.add(String.valueOf(troncon.TVr));
                        row.add(String.valueOf(troncon.TVref));
                        row.add(String.valueOf(troncon.HV100Rr));
                        row.add(String.valueOf(troncon.MEPr));
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TMEr));
                            row.add(String.valueOf(sv.CMTr));
                            row.add(String.valueOf(sv.HCVRr));
                            row.add(String.valueOf(sv.HCTr));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size() && sv.numSV == rotation.listSV.size()) {
                            row.add(String.valueOf(rotation.SH1Rr));
                            row.add(String.valueOf(rotation.HCAr));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TSVNr));
                        }
                        else {
                            row.add(null);
                        }
                        this.dataVector.add(row);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                final ArrayList<String> row2 = new ArrayList<String>();
                row2.add(dateFormat.format(act.debutUTCD));
                row2.add(act.code);
                row2.add(null);
                row2.add(String.valueOf(act.HCRS));
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(String.valueOf(act.HCRS));
                row2.add(null);
                this.dataVector.add(row2);
            }
        }
    }
    
    public void loadTableIRp(final AnalyseCrew analyse) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        this.columnIdentifiers.clear();
        this.dataVector.clear();
        this.columnIdentifiers.add("Date");
        this.columnIdentifiers.add("N° de vol");
        this.columnIdentifiers.add("D\u00e9part prog.");
        this.columnIdentifiers.add("De");
        this.columnIdentifiers.add("A");
        this.columnIdentifiers.add("Arriv\u00e9e prog.");
        this.columnIdentifiers.add("IR d\u00e9part");
        this.columnIdentifiers.add("MF d\u00e9part");
        this.columnIdentifiers.add("D\u00e9c.");
        this.columnIdentifiers.add("IR arriv\u00e9e");
        this.columnIdentifiers.add("MF arriv\u00e9e");
        this.columnIdentifiers.add("Transp. aller");
        this.columnIdentifiers.add("Transp. retour");
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        final ArrayList<String> row = new ArrayList<String>();
                        row.add(dateFormat.format(troncon.debutUTCD));
                        row.add(troncon.numVol);
                        row.add(String.valueOf(troncon.DEBp));
                        row.add(troncon.from);
                        row.add(troncon.to);
                        row.add(String.valueOf(Utils.arrondi(troncon.FINp % 24.0, 2)));
                        row.add(String.valueOf(troncon.indemFrom.nIRMidiP + troncon.indemFrom.nIRSoirP));
                        row.add(String.valueOf(troncon.indemFrom.nMFMidiP + troncon.indemFrom.nMFSoirP));
                        row.add(String.valueOf(troncon.nDecP));
                        row.add(String.valueOf(troncon.indemTo.nIRMidiP + troncon.indemTo.nIRSoirP));
                        row.add(String.valueOf(troncon.indemTo.nMFMidiP + troncon.indemTo.nMFSoirP));
                        if (sv.numSV == 1 && troncon.numTroncon == 1) {
                            row.add(rotation.IKVallerP);
                        }
                        else {
                            row.add(null);
                        }
                        if (sv.numSV == rotation.listSV.size() && troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(rotation.IKVretourP);
                        }
                        else {
                            row.add(null);
                        }
                        this.dataVector.add(row);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                final ArrayList<String> row2 = new ArrayList<String>();
                row2.add(dateFormat.format(act.debutUTCD));
                row2.add(act.code);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(act.IKVar);
                row2.add(act.IKVar);
                this.dataVector.add(row2);
            }
        }
    }
    
    public void loadTableIRr(final AnalyseCrew analyse) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        this.columnIdentifiers.clear();
        this.dataVector.clear();
        this.columnIdentifiers.add("Date");
        this.columnIdentifiers.add("N° de vol");
        this.columnIdentifiers.add("D\u00e9part r\u00e9al.");
        this.columnIdentifiers.add("De");
        this.columnIdentifiers.add("A");
        this.columnIdentifiers.add("Arriv\u00e9e r\u00e9al.");
        this.columnIdentifiers.add("IR d\u00e9part");
        this.columnIdentifiers.add("MF d\u00e9part");
        this.columnIdentifiers.add("D\u00e9c.");
        this.columnIdentifiers.add("IR arriv\u00e9e");
        this.columnIdentifiers.add("MF arriv\u00e9e");
        this.columnIdentifiers.add("Transp. aller");
        this.columnIdentifiers.add("Transp. retour");
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        final ArrayList<String> row = new ArrayList<String>();
                        row.add(dateFormat.format(troncon.debutUTCD));
                        row.add(troncon.numVol);
                        row.add(String.valueOf(troncon.DEBr));
                        row.add(troncon.from);
                        row.add(troncon.to);
                        row.add(String.valueOf(Utils.arrondi(troncon.FINr % 24.0, 2)));
                        row.add(String.valueOf(troncon.indemFrom.nIRMidiR + troncon.indemFrom.nIRSoirR));
                        row.add(String.valueOf(troncon.indemFrom.nMFMidiR + troncon.indemFrom.nMFSoirR));
                        row.add(String.valueOf(troncon.nDecR));
                        row.add(String.valueOf(troncon.indemTo.nIRMidiR + troncon.indemTo.nIRSoirR));
                        row.add(String.valueOf(troncon.indemTo.nMFMidiR + troncon.indemTo.nMFSoirR));
                        if (sv.numSV == 1 && troncon.numTroncon == 1) {
                            row.add(rotation.IKVallerR);
                        }
                        else {
                            row.add(null);
                        }
                        if (sv.numSV == rotation.listSV.size() && troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(rotation.IKVretourR);
                        }
                        else {
                            row.add(null);
                        }
                        this.dataVector.add(row);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                final ArrayList<String> row2 = new ArrayList<String>();
                row2.add(dateFormat.format(act.debutUTCD));
                row2.add(act.code);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(act.IKVar);
                row2.add(act.IKVar);
                this.dataVector.add(row2);
            }
        }
    }
    
    public void loadTableDecompteEP4(final AnalyseCrew analyse) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        this.columnIdentifiers.clear();
        this.dataVector.clear();
        this.columnIdentifiers.add("Date");
        this.columnIdentifiers.add("De");
        this.columnIdentifiers.add("A");
        this.columnIdentifiers.add("HV r\u00e9al.");
        this.columnIdentifiers.add("TME");
        this.columnIdentifiers.add("CMT");
        this.columnIdentifiers.add("HV100%");
        this.columnIdentifiers.add("HCV");
        this.columnIdentifiers.add("HCT");
        this.columnIdentifiers.add("HCA");
        this.columnIdentifiers.add("H1");
        this.columnIdentifiers.add("H2");
        this.columnIdentifiers.add("HV100%R");
        this.columnIdentifiers.add("HCVR");
        this.columnIdentifiers.add("H1R");
        this.columnIdentifiers.add("H2R");
        this.columnIdentifiers.add("Majo Nuit");
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        final ArrayList<String> row = new ArrayList<String>();
                        row.add(dateFormat.format(troncon.debutUTCD));
                        row.add(troncon.from);
                        row.add(troncon.to);
                        row.add(String.valueOf(troncon.TVr));
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TMEr));
                            row.add(String.valueOf(sv.CMTr));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                        }
                        row.add(String.valueOf(troncon.HV100r));
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.HCVr));
                            row.add(String.valueOf(sv.HCTr));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size() && sv.numSV == rotation.listSV.size()) {
                            row.add(String.valueOf(rotation.HCAr));
                        }
                        else {
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.H1r));
                        }
                        else {
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size() && sv.numSV == rotation.listSV.size()) {
                            row.add(String.valueOf(rotation.H2r));
                        }
                        else {
                            row.add(null);
                        }
                        row.add(String.valueOf(troncon.HV100Rr));
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.HCVRr));
                            row.add(String.valueOf(sv.H1Rr));
                        }
                        else {
                            row.add(null);
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size() && sv.numSV == rotation.listSV.size()) {
                            row.add(String.valueOf(rotation.H2Rr));
                        }
                        else {
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TSVNr / 2.0));
                        }
                        else {
                            row.add(null);
                        }
                        this.dataVector.add(row);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                final ArrayList<String> row2 = new ArrayList<String>();
                row2.add(dateFormat.format(act.debutUTCD));
                row2.add(act.code);
                row2.add(null);
                row2.add(String.valueOf(act.HCS));
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(String.valueOf(act.HCS));
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(String.valueOf(act.HCRS));
                row2.add(null);
                this.dataVector.add(row2);
            }
        }
    }
    
    public void loadTableHoraireEP4(final AnalyseCrew analyse) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        this.columnIdentifiers.clear();
        this.dataVector.clear();
        this.columnIdentifiers.add("Date");
        this.columnIdentifiers.add("N° de vol");
        this.columnIdentifiers.add("D\u00e9part prog.");
        this.columnIdentifiers.add("D\u00e9part r\u00e9al.");
        this.columnIdentifiers.add("De");
        this.columnIdentifiers.add("A");
        this.columnIdentifiers.add("Arriv\u00e9e r\u00e9al.");
        this.columnIdentifiers.add("Arriv\u00e9e prog.");
        this.columnIdentifiers.add("TV r\u00e9al.");
        this.columnIdentifiers.add("TV prog.");
        this.columnIdentifiers.add("TV ref.");
        this.columnIdentifiers.add("TSV");
        this.columnIdentifiers.add("TA");
        this.columnIdentifiers.add("TV nuit");
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                final Rotation rotation = (Rotation)obj;
                for (final ServiceVol sv : rotation.listSV) {
                    for (final Troncon troncon : sv.listTroncon) {
                        final ArrayList<String> row = new ArrayList<String>();
                        row.add(dateFormat.format(troncon.debutUTCD));
                        row.add(troncon.numVol);
                        row.add(String.valueOf(troncon.DEBp));
                        row.add(String.valueOf(troncon.DEBr));
                        row.add(troncon.from);
                        row.add(troncon.to);
                        row.add(String.valueOf(Utils.arrondi(troncon.FINr % 24.0, 2)));
                        row.add(String.valueOf(Utils.arrondi(troncon.FINp % 24.0, 2)));
                        row.add(String.valueOf(troncon.TVr));
                        row.add(String.valueOf(troncon.TVp));
                        row.add(String.valueOf(troncon.TVref));
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TSVr));
                        }
                        else {
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size() && sv.numSV == rotation.listSV.size()) {
                            row.add(String.valueOf(rotation.TAr));
                        }
                        else {
                            row.add(null);
                        }
                        if (troncon.numTroncon == sv.listTroncon.size()) {
                            row.add(String.valueOf(sv.TSVNr));
                        }
                        else {
                            row.add(null);
                        }
                        this.dataVector.add(row);
                    }
                }
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                final ActiviteSol act = (ActiviteSol)obj;
                if (!act.isCredite) {
                    continue;
                }
                final ArrayList<String> row2 = new ArrayList<String>();
                row2.add(dateFormat.format(act.debutUTCD));
                row2.add(act.code);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(String.valueOf(act.HCS));
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                row2.add(null);
                this.dataVector.add(row2);
            }
        }
    }
    
    @Override
    public boolean isCellEditable(final int row, final int col) {
        return false;
    }
}
