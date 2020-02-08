// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI.RosterTable;

import chopeCrew.ChopeCrew;
import java.awt.event.MouseAdapter;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JToolTip;
import java.awt.Point;
import java.awt.event.MouseEvent;
import ccStructures.Participant;
import ccStructures.Indem;
import ccStructures.Presta;
import ccStructures.Peq;
import ccStructures.ServiceVol;
import java.util.Iterator;
import ccStructures.Deg_Reco;
import ccStructures.Dest_Reco;
import ccStructures.Troncon;
import ccStructures.ActiviteSol;
import ccStructures.Rotation;
import java.awt.event.MouseListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import ccStructures.CrewPlanningEvent;
import javax.swing.table.TableModel;
import ccGUI.MainFrame;
import javax.swing.JTable;

public class RosterTable extends JTable
{
    private static final long serialVersionUID = 1L;
    private RosterTableModel model;
    private MainFrame parent;
    private int row;
    
    public RosterTable(final MainFrame parent) {
        this.parent = parent;
        this.model = new RosterTableModel();
        this.row = 0;
        this.initComponent();
    }
    
    private void initComponent() {
        this.setModel(this.model);
        this.setDefaultRenderer(CrewPlanningEvent.class, new RosterTableCellRenderer());
        this.setTableHeader(null);
        this.setRowHeight(24);
        this.setSelectionMode(0);
        this.setAutoResizeMode(2);
        this.getColumnModel().getColumn(0).setPreferredWidth(96);
        this.getColumnModel().getColumn(1).setPreferredWidth(438);
        this.addMouseListener(new MyMouseAdapter());
    }
    
    private void displayDetailsDialog(final CrewPlanningEvent event) {
        if (event instanceof Rotation) {
            this.parent.showDetails(event.label, this.generateDetailsString((Rotation)event));
        }
        else if (event instanceof ActiviteSol) {
            this.parent.showDetails(event.label, this.generateDetailsString((ActiviteSol)event));
        }
    }
    
    private void displayDetailsDialog(final CrewPlanningEvent troncon, final CrewPlanningEvent rotation) {
        final Troncon t = (Troncon)troncon;
        final String header = String.valueOf(t.numVol) + "   " + t.from + " - " + t.to;
        this.parent.showDetails(header, this.generateDetailsString((Troncon)troncon, (Rotation)rotation));
    }
    
    private String generateDetailsString(final Rotation event) {
        final StringBuilder strb = new StringBuilder();
        final String nl = "<br>";
        strb.append("<html>");
        strb.append("<body style=\"font-family: sans-serif\">");
        strb.append("D\u00e9but : ").append(event.departUTC).append(" UTC").append(nl);
        strb.append("Fin   : ").append(event.arriveeUTC).append(" UTC").append(nl);
        if (event.presentationUTC != null) {
            strb.append("Pr\u00e9sentation : ").append(event.presentationUTC).append(" UTC").append(nl);
        }
        if (event.reengagementUTC != null) {
            strb.append("R\u00e9engagement : ").append(event.reengagementUTC).append(" UTC").append(nl);
        }
        if (event.particularite != null || event.sab != null) {
            strb.append(nl);
        }
        if (event.particularite != null) {
            strb.append("Particularit\u00e9 : ").append(event.particularite).append(nl);
        }
        if (event.sab != null) {
            strb.append("Situation \u00e0 bord : ").append(event.sab).append(nl);
        }
        if (event.nbCDB != null && event.nbOPL != null) {
            strb.append(nl);
            strb.append("CDB : ").append(event.nbCDB).append(", OPL : ").append(event.nbOPL).append(nl);
        }
        if (event.tempsVolTotal != null || event.tempsVolMep != null || event.tempsAbsence != null) {
            strb.append(nl);
        }
        if (event.tempsVolTotal != null) {
            strb.append("Temps de vol total  : ").append(event.tempsVolTotal).append(nl);
        }
        if (event.tempsVolMep != null) {
            strb.append("Temps de vol en Mep : ").append(event.tempsVolMep).append(nl);
        }
        if (event.tempsAbsence != null) {
            strb.append("Temps d'absence : ").append(event.tempsAbsence).append(nl);
        }
        if (event.dureePac != null || event.dureeRpc != null) {
            strb.append(nl);
        }
        if (event.dureePac != null) {
            strb.append("Repos pr\u00e9-courrier : ").append(event.dureePac).append(nl);
        }
        if (event.dureeRpc != null) {
            strb.append("Repos post-courrier : ").append(event.dureeRpc).append(nl);
        }
        if (event.listDestReco.size() > 0) {
            strb.append(nl);
            strb.append("Reconnaissances destination :").append(nl);
            for (final Dest_Reco reco : event.listDestReco) {
                strb.append(reco.dest).append(" : cat.").append(reco.categorie).append(nl);
            }
        }
        if (event.listDegReco.size() > 0) {
            strb.append(nl);
            strb.append("Reconnaissances d\u00e9gagements :").append(nl);
            for (final Deg_Reco reco2 : event.listDegReco) {
                strb.append(reco2.deg).append(" : cat.").append(reco2.categorie).append(" (").append(reco2.dest).append(")").append(nl);
            }
        }
        strb.append(nl);
        strb.append("Heures d\u00e9compt\u00e9es / r\u00e9mun\u00e9r\u00e9es : ").append(nl);
        strb.append("HCA : ").append(event.HCAp).append("    /    ").append(event.HCAp).append(nl);
        strb.append("\u03a3H1 : ").append(event.SH1p).append("    /    ").append(event.SH1Rp).append(nl);
        strb.append("H2 :  ").append(event.H2p).append("    /    ").append(event.H2Rp).append(nl);
        strb.append("</body>");
        strb.append("</html>");
        return strb.toString().replaceAll("null", "NIL");
    }
    
    private String generateDetailsString(final Troncon troncon, final Rotation rotation) {
        final ServiceVol sv = rotation.listSV.get(troncon.numSV - 1);
        final StringBuilder strb = new StringBuilder();
        final String nl = "<br>";
        strb.append("<html>");
        strb.append("<body style=\"font-family: sans-serif\">");
        strb.append("D\u00e9part : ").append(troncon.departUTC).append(" UTC").append(nl);
        strb.append("Arriv\u00e9e : ").append(troncon.arriveeUTC).append(" UTC").append(nl);
        if (troncon.versionExploitation != null) {
            strb.append(nl);
            strb.append("Version : ").append(troncon.versionExploitation).append(nl);
        }
        strb.append(nl);
        strb.append(troncon.from).append(" : T.U. ");
        if (troncon.LAGfromMillis >= 0) {
            strb.append("+");
        }
        strb.append(troncon.lagFrom).append(nl);
        strb.append(troncon.to).append(" : T.U. ");
        if (troncon.LAGtoMillis >= 0) {
            strb.append("+");
        }
        strb.append(troncon.lagTo).append(nl);
        if (troncon.listRecoDest.size() > 0) {
            strb.append(nl);
            strb.append("Reco. destination :").append(nl);
            for (final Dest_Reco reco : troncon.listRecoDest) {
                strb.append(reco.dest).append(" : cat.").append(reco.categorie).append(nl);
            }
        }
        if (troncon.listRecoDeg.size() > 0) {
            strb.append(nl);
            strb.append("Reco. d\u00e9gagements :").append(nl);
            for (final Deg_Reco reco2 : troncon.listRecoDeg) {
                strb.append(reco2.deg).append(" : cat.").append(reco2.categorie).append(nl);
            }
        }
        if (rotation.sab != null) {
            strb.append(nl);
            strb.append("Situation \u00e0 bord : ").append(rotation.sab).append(nl);
        }
        if (troncon.listPeqTroncon.size() > 0) {
            strb.append(nl);
            strb.append("Equipage :").append(nl);
            for (final Peq peq : troncon.listPeqTroncon) {
                strb.append(peq.fab).append(" : ").append(peq.nom).append(" ").append(peq.prenom).append(nl);
            }
        }
        final Indem indem = troncon.indemTo;
        if (indem.escale != null) {
            strb.append(nl);
            strb.append("Indemnit\u00e9s ").append(indem.escale).append(" :").append(nl);
            strb.append(" Monnaie : ").append(indem.monnaieLoc).append(nl);
            strb.append(" Ind. repas : ").append(indem.irLoc).append(" (").append(indem.irEur).append(" \u20ac)").append(nl);
            strb.append(" Menu frais : ").append(indem.mfLoc).append(" (").append(indem.mfEur).append(" \u20ac)").append(nl);
            strb.append(" Taux de change AF : ").append(indem.change).append(nl);
            strb.append(" Date effet : ").append(indem.dateEffet).append(nl);
        }
        if (troncon.listPresta.size() > 0) {
            strb.append(nl);
            strb.append("Prestations :").append(nl);
            for (final Presta presta : troncon.listPresta) {
                strb.append(presta.type).append(" emb. \u00e0 ").append(presta.escaleEmb).append(nl);
            }
        }
        if (troncon.isDecoucher) {
            strb.append(nl);
            strb.append("Repos : ").append(troncon.repos).append(nl);
        }
        else if (troncon.tempsEscale != null) {
            strb.append(nl);
            strb.append("Escale : ").append(troncon.tempsEscale).append(nl);
        }
        if (troncon.hotel != null) {
            strb.append("H\u00f4tel : ").append(troncon.hotel).append(nl);
        }
        strb.append(nl);
        strb.append("Heures d\u00e9compt\u00e9es / r\u00e9mun\u00e9r\u00e9es :").append(nl);
        strb.append("HCV : ").append(sv.HCVp).append("    /    ").append(sv.HCVRp).append(nl);
        strb.append("HCT : ").append(sv.HCTp).append("    /    ").append(sv.HCTp).append(nl);
        strb.append("CMT : ").append(sv.CMTp).append("    /    ").append(sv.CMTp).append(nl);
        strb.append("H1 :  ").append(sv.H1p).append("    /    ").append(sv.H1Rp).append(nl);
        strb.append(nl);
        strb.append("TVref : ").append(troncon.TVref);
        if (!troncon.isTVref) {
            strb.append(" (non valid\u00e9 -> TVref=TVp)").append(nl);
        }
        else {
            strb.append(nl);
        }
        strb.append("TVp : ").append(troncon.TVp).append(nl);
        strb.append("HV100% : ").append(troncon.HV100p).append("    /    ").append(troncon.HV100Rp).append(nl);
        strb.append("MEP : ").append(troncon.MEPp).append("    /    ").append(troncon.MEPp).append(nl);
        strb.append("TVN : ").append(troncon.TVNp).append("    /    ").append(troncon.TVNp).append(nl);
        strb.append("</body>");
        strb.append("</html>");
        return strb.toString().replaceAll("null", "NIL");
    }
    
    private String generateDetailsString(final ActiviteSol act) {
        final StringBuilder strb = new StringBuilder();
        final String nl = "<br>";
        strb.append("<html>");
        strb.append("<body style=\"font-family: sans-serif\">");
        strb.append("D\u00e9but : ").append(act.debut).append(" LT").append(nl);
        strb.append("Fin   : ").append(act.fin).append(" LT").append(nl);
        strb.append(nl);
        strb.append("Cat\u00e9gorie : ").append(act.categorie).append(" (").append(act.code).append(")").append(nl);
        if (act.lieu != null || act.salle != null || act.commentaire != null) {
            strb.append(nl);
            strb.append("Lieu : ").append(act.lieu).append(nl);
            strb.append("Salle : ").append(act.salle).append(nl);
            strb.append("Commentaire : ").append(act.commentaire).append(nl);
        }
        if (act.listParticipant.size() > 0) {
            strb.append(nl);
            strb.append("Participants : ").append(nl);
            for (final Participant part : act.listParticipant) {
                strb.append(part.nom).append(" ").append(part.prenom).append(" (").append(part.statut).append(")").append(nl);
            }
        }
        strb.append(nl);
        strb.append("Heures d\u00e9compt\u00e9es / r\u00e9mun\u00e9r\u00e9es : ").append(nl);
        strb.append("HCS : ").append(act.HCS).append("    /    ").append(act.HCRS).append(nl);
        strb.append("</body>");
        strb.append("</html>");
        return strb.toString().replaceAll("null", "NIL");
    }
    
    @Override
    public Point getToolTipLocation(final MouseEvent e) {
        final Point p = new Point(e.getX(), e.getY());
        this.row = this.rowAtPoint(p);
        return new Point(this.getWidth() + 230, this.row * 24);
    }
    
    @Override
    public JToolTip createToolTip() {
        final JToolTip tt = super.createToolTip();
        final Color clr = ((RosterTableCellRenderer)this.getCellRenderer(this.row, 0)).getBackground();
        tt.setBackground(new Color(clr.getRed(), clr.getGreen(), clr.getBlue(), clr.getAlpha() / 2));
        tt.setFont(new Font("Tahoma", 0, 14));
        return tt;
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            final JTable target = (JTable)e.getSource();
            final int row = target.rowAtPoint(e.getPoint());
            final int col = target.columnAtPoint(e.getPoint());
            if (row == -1) {
                return;
            }
            final CrewPlanningEvent event = RosterTable.this.model.events.get(row);
            if (e.getButton() == 1) {
                if (col == 0 && event instanceof Rotation) {
                    event.switchExpandedState();
                    RosterTable.this.model.loadRosterTable(ChopeCrew.analyse);
                }
                else if (col == 1) {
                    if (event instanceof Troncon) {
                        boolean rotationFound = false;
                        int n = 1;
                        CrewPlanningEvent parentRotation = null;
                        while (!rotationFound) {
                            parentRotation = RosterTable.this.model.events.get(row - n);
                            if (parentRotation instanceof Rotation) {
                                rotationFound = true;
                            }
                            ++n;
                        }
                        if (parentRotation != null) {
                            RosterTable.this.displayDetailsDialog(event, parentRotation);
                        }
                    }
                    else {
                        RosterTable.this.displayDetailsDialog(event);
                    }
                }
            }
            else if (e.getButton() == 3) {
                if (event instanceof Rotation) {
                    for (final ServiceVol sv : ((Rotation)event).listSV) {
                        for (final Troncon t : sv.listTroncon) {
                            t.setExportable(!event.isExportable());
                        }
                    }
                }
                event.switchExportableState();
                RosterTable.this.model.fireTableDataChanged();
            }
        }
    }
}
