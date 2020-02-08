// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI.RosterTable;

import ccStructures.Participant;
import ccStructures.Indem;
import ccStructures.Presta;
import ccStructures.Deg_Reco;
import ccStructures.Dest_Reco;
import ccStructures.Peq;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Iterator;
import ccStructures.ActiviteSol;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import ccGUI.MainFrame;
import java.util.SimpleTimeZone;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import java.util.TimeZone;
import chopeCrew.ChopeCrew;
import java.awt.Color;
import ccUtils.Constantes;
import ccStructures.Rotation;
import ccStructures.CrewPlanningEvent;
import java.awt.Component;
import javax.swing.JTable;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
import java.awt.Font;
import java.text.DateFormat;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

public class RosterTableCellRenderer extends JLabel implements TableCellRenderer
{
    private static final long serialVersionUID = 1L;
    private boolean isTroncon;
    private boolean isTronconInstruction;
    private boolean isDecoucher;
    private boolean isBlocActivite;
    private boolean isColonneActivite;
    private boolean isExportable;
    private DateFormat dateFormatFrom;
    private DateFormat hourFormatFrom;
    private DateFormat hourFormatTo;
    
    public RosterTableCellRenderer() {
        this.dateFormatFrom = null;
        this.hourFormatFrom = null;
        this.hourFormatTo = null;
        this.setOpaque(true);
        this.setFont(new Font("Courier New", 0, 16));
        final String[] capitalDays = { "", "Di", "Lu", "Ma", "Me", "Je", "Ve", "Sa" };
        final DateFormatSymbols symbols = new DateFormatSymbols();
        symbols.setShortWeekdays(capitalDays);
        this.dateFormatFrom = new SimpleDateFormat(" E dd", symbols);
        this.hourFormatFrom = new SimpleDateFormat("HH'h'mm");
        this.hourFormatTo = new SimpleDateFormat("HH'h'mm");
    }
    
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object obj, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        if (column == 0) {
            this.isColonneActivite = false;
        }
        else if (column == 1) {
            this.isColonneActivite = true;
        }
        final CrewPlanningEvent cpe = (CrewPlanningEvent)obj;
        if (cpe == null) {
            this.setText("NULL");
            return this;
        }
        if (cpe instanceof Rotation) {
            final Rotation rotation = (Rotation)cpe;
            this.isTroncon = false;
            this.isTronconInstruction = false;
            this.isDecoucher = false;
            this.isBlocActivite = false;
            if (rotation.sab != null && (rotation.sab.contains("IPLG") || rotation.sab.contains("IPLD") || rotation.sab.contains("IPLJPS") || rotation.sab.contains("IPLJS"))) {
                if (rotation.isExportable()) {
                    this.setBackground(Constantes.COLOR_PURPLE_BCKGRD_ENABLED);
                    this.setForeground(Color.DARK_GRAY);
                }
                else {
                    this.setBackground(Constantes.COLOR_PURPLE_BACKGRD_DISABLED);
                    this.setForeground(Color.LIGHT_GRAY);
                }
            }
            else if (rotation.isExportable()) {
                this.setBackground(Constantes.COLOR_AZUR_BCKGRD_ENABLED);
                this.setForeground(Color.DARK_GRAY);
            }
            else {
                this.setBackground(Constantes.COLOR_AZUR_BACKGRD_DISABLED);
                this.setForeground(Color.LIGHT_GRAY);
            }
            if (!ChopeCrew.options.isRosterTableLocal) {
                final TimeZone tzFrom = TimeZone.getTimeZone("Europe/Paris");
                this.dateFormatFrom.setTimeZone(tzFrom);
            }
            else if (ChopeCrew.options.isRosterTableLocal) {
                final ServiceVol firstSV = rotation.listSV.get(0);
                final Troncon firstTro = firstSV.listTroncon.get(0);
                final TimeZone tzFrom2 = new SimpleTimeZone(firstTro.LAGfromMillis, "");
                this.dateFormatFrom.setTimeZone(tzFrom2);
            }
            switch (column) {
                case 0: {
                    this.setHorizontalAlignment(2);
                    this.setHorizontalTextPosition(2);
                    this.setText(this.dateFormatFrom.format(cpe.debutUTCD));
                    if (cpe.isExpanded()) {
                        this.setIcon(new ImageIcon(MainFrame.class.getResource("/res_images/icon_retract.png")));
                    }
                    if (!cpe.isExpanded()) {
                        this.setIcon(new ImageIcon(MainFrame.class.getResource("/res_images/icon_expand.png")));
                    }
                    this.setToolTipText("");
                    break;
                }
                case 1: {
                    this.setHorizontalAlignment(2);
                    this.setHorizontalTextPosition(2);
                    String affichage = " " + cpe.label + "   " + rotation.nON + "ON";
                    for (final String decoucher : rotation.listDecouchers) {
                        affichage = String.valueOf(affichage) + " " + decoucher;
                    }
                    if (rotation.sab != null && !rotation.sab.equals("")) {
                        affichage = String.valueOf(affichage) + "   " + rotation.sab;
                    }
                    this.setText(affichage);
                    this.setIcon(null);
                    if (ChopeCrew.options.isTooltipMan) {
                        this.setToolTipText(this.generateTipString(rotation));
                        break;
                    }
                    break;
                }
            }
        }
        else if (cpe instanceof Troncon) {
            final Troncon troncon = (Troncon)cpe;
            final Rotation rotation2 = ChopeCrew.analyse.listRotation.get(troncon.numRot - 1);
            this.isTroncon = true;
            this.isDecoucher = troncon.isDecoucher;
            this.isBlocActivite = false;
            this.isExportable = cpe.isExportable();
            if (rotation2.sab != null && (rotation2.sab.contains("IPLG") || rotation2.sab.contains("IPLD") || rotation2.sab.contains("IPLJPS") || rotation2.sab.contains("IPLJS"))) {
                this.isTronconInstruction = true;
            }
            else {
                this.isTronconInstruction = false;
            }
            if (troncon.isExportable()) {
                this.setBackground(Constantes.COLOR_CYAN_BCKGRD_ENABLED);
                this.setForeground(Color.DARK_GRAY);
            }
            else {
                this.setBackground(Constantes.COLOR_CYAN_BCKGRD_DISABLED);
                this.setForeground(Color.LIGHT_GRAY);
            }
            if (!ChopeCrew.options.isRosterTableLocal) {
                final TimeZone tzFrom3 = TimeZone.getTimeZone("Europe/Paris");
                final TimeZone tzTo = TimeZone.getTimeZone("Europe/Paris");
                this.dateFormatFrom.setTimeZone(tzFrom3);
                this.hourFormatFrom.setTimeZone(tzFrom3);
                this.hourFormatTo.setTimeZone(tzTo);
            }
            else if (ChopeCrew.options.isRosterTableLocal) {
                final TimeZone tzFrom3 = new SimpleTimeZone(((Troncon)cpe).LAGfromMillis, "");
                final TimeZone tzTo = new SimpleTimeZone(((Troncon)cpe).LAGtoMillis, "");
                this.dateFormatFrom.setTimeZone(tzFrom3);
                this.hourFormatFrom.setTimeZone(tzFrom3);
                this.hourFormatTo.setTimeZone(tzTo);
            }
            Date debut = new Date();
            Date fin = new Date();
            if (!ChopeCrew.options.isRosterTableReal) {
                debut = troncon.debutUTCD;
                fin = troncon.finUTCD;
            }
            else if (ChopeCrew.options.isRosterTableReal) {
                debut.setTime(troncon.DEPr);
                fin.setTime(troncon.ARRr);
            }
            switch (column) {
                case 0: {
                    this.setHorizontalAlignment(2);
                    if (troncon.numTroncon == 1) {
                        this.setText(this.dateFormatFrom.format(debut));
                    }
                    else {
                        this.setText(null);
                    }
                    this.setIcon(null);
                    this.setToolTipText("");
                    break;
                }
                case 1: {
                    this.setHorizontalAlignment(0);
                    final String str = String.valueOf(troncon.numVol) + "   " + troncon.from + " " + this.hourFormatFrom.format(debut) + "   " + troncon.to + " " + this.hourFormatTo.format(fin);
                    if (troncon.isMep) {
                        this.setText(String.valueOf(str) + "   MEP");
                    }
                    else {
                        this.setText(String.valueOf(str) + "   " + troncon.typeAvion);
                    }
                    this.setIcon(null);
                    if (ChopeCrew.options.isTooltipMan) {
                        this.setToolTipText(this.generateTipString(troncon, rotation2));
                        break;
                    }
                    break;
                }
            }
        }
        else if (cpe instanceof ActiviteSol) {
            final ActiviteSol act = (ActiviteSol)cpe;
            this.isTroncon = false;
            this.isDecoucher = false;
            this.isBlocActivite = act.isBlocActivite;
            this.isTronconInstruction = false;
            if (act.code.equals("PRB") || act.code.equals("MAD")) {
                if (act.isExportable()) {
                    this.setBackground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
                    this.setForeground(Color.DARK_GRAY);
                }
                else {
                    this.setBackground(Constantes.COLOR_GREEN_BCKGRD_DISABLED);
                    this.setForeground(Color.LIGHT_GRAY);
                }
            }
            else if (act.code.equals("MCA") || act.code.equals("MCE")) {
                if (act.isExportable()) {
                    this.setBackground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
                    this.setForeground(Color.DARK_GRAY);
                }
                else {
                    this.setBackground(Constantes.COLOR_GREEN_BCKGRD_DISABLED);
                    this.setForeground(Color.LIGHT_GRAY);
                }
            }
            else if (act.code.equals("PAC") || act.code.equals("RPC")) {
                if (act.isExportable()) {
                    this.setBackground(Constantes.COLOR_GREEN_BCKGRD_ENABLED);
                    this.setForeground(Color.DARK_GRAY);
                }
                else {
                    this.setBackground(Constantes.COLOR_GREEN_BCKGRD_DISABLED);
                    this.setForeground(Color.LIGHT_GRAY);
                }
            }
            else if (act.code.equals("MAS") || act.code.equals("MDV")) {
                if (act.isExportable()) {
                    this.setBackground(Constantes.COLOR_GOLD_BCKGRD_ENABLED);
                    this.setForeground(Color.DARK_GRAY);
                }
                else {
                    this.setBackground(Constantes.COLOR_GOLD_BCKGRD_DISABLED);
                    this.setForeground(Color.LIGHT_GRAY);
                }
            }
            else if (act.code.equals("MCI")) {
                if (act.isExportable()) {
                    this.setBackground(Constantes.COLOR_GOLD_BCKGRD_ENABLED);
                    this.setForeground(Color.DARK_GRAY);
                }
                else {
                    this.setBackground(Constantes.COLOR_GOLD_BCKGRD_DISABLED);
                    this.setForeground(Color.LIGHT_GRAY);
                }
            }
            else if (act.code.equals("DSP")) {
                if (act.isExportable()) {
                    this.setBackground(Constantes.COLOR_LINEN_BCKGRD_ENABLED);
                    this.setForeground(Color.DARK_GRAY);
                }
                else {
                    this.setBackground(Constantes.COLOR_LINEN_BCKGRD_DISABLED);
                    this.setForeground(Color.LIGHT_GRAY);
                }
            }
            else if (act.isExportable()) {
                this.setBackground(Constantes.COLOR_ROSE_BCKGRD_ENABLED);
                this.setForeground(Color.DARK_GRAY);
            }
            else {
                this.setBackground(Constantes.COLOR_ROSE_BCKGRD_DISABLED);
                this.setForeground(Color.LIGHT_GRAY);
            }
            this.setHorizontalAlignment(2);
            final TimeZone tzFrom = TimeZone.getTimeZone("Europe/Paris");
            this.dateFormatFrom.setTimeZone(tzFrom);
            switch (column) {
                case 0: {
                    this.setHorizontalAlignment(2);
                    this.setText(this.dateFormatFrom.format(act.debutUTCD));
                    this.setIcon(null);
                    this.setToolTipText("");
                    break;
                }
                case 1: {
                    this.setHorizontalAlignment(2);
                    this.setText(" " + act.label);
                    this.setIcon(null);
                    if (ChopeCrew.options.isTooltipMan) {
                        this.setToolTipText(this.generateTipString(act));
                        break;
                    }
                    break;
                }
            }
        }
        else {
            this.setBackground(Constantes.COLOR_LINEN_BCKGRD_ENABLED);
            this.setText((String)obj);
        }
        return this;
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        if (this.isTroncon) {
            final Graphics2D g2d = (Graphics2D)g;
            if (this.isDecoucher) {
                if (!this.isTronconInstruction) {
                    if (this.isExportable) {
                        g2d.setColor(Constantes.COLOR_SV_SEPARATOR_ENABLED);
                    }
                    else {
                        g2d.setColor(Constantes.COLOR_SV_SEPARATOR_DISABLED);
                    }
                    g2d.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1);
                }
                else {
                    if (this.isExportable) {
                        g2d.setColor(Constantes.COLOR_SV_SEPARATOR_INST_ENABLED);
                    }
                    else {
                        g2d.setColor(Constantes.COLOR_SV_SEPARATOR_INST_DISABLED);
                    }
                    g2d.drawLine(0, this.getHeight() - 1, this.getWidth() - 1, this.getHeight() - 1);
                }
            }
        }
        else if (this.isBlocActivite && this.isColonneActivite) {
            final Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(new Color(178, 34, 34, 255));
            g2d.fillRect(0, 0, 1, 24);
        }
    }
    
    private String generateTipString(final Rotation rotation) {
        final StringBuilder strb = new StringBuilder();
        final String nl = "<br>";
        strb.append("<html>");
        strb.append(rotation.label).append(nl);
        strb.append(nl);
        strb.append("D\u00e9but : ").append(rotation.departUTC).append(" UTC").append(nl);
        strb.append("Fin   : ").append(rotation.arriveeUTC).append(" UTC").append(nl);
        if (rotation.presentationUTC != null) {
            strb.append("Pr\u00e9sentation : ").append(rotation.presentationUTC).append(" UTC").append(nl);
        }
        if (rotation.reengagementUTC != null) {
            strb.append("R\u00e9engagement : ").append(rotation.reengagementUTC).append(" UTC").append(nl);
        }
        if (rotation.particularite != null || rotation.sab != null) {
            strb.append(nl);
        }
        if (rotation.particularite != null) {
            strb.append("Particularit\u00e9 : ").append(rotation.particularite).append(nl);
        }
        if (rotation.sab != null) {
            strb.append("Situation \u00e0 bord : ").append(rotation.sab).append(nl);
        }
        if (rotation.nbCDB != null && rotation.nbOPL != null) {
            strb.append(nl);
            strb.append("CDB : ").append(rotation.nbCDB).append(", OPL : ").append(rotation.nbOPL).append(nl);
        }
        if (rotation.listPeqRot.size() > 0) {
            strb.append(nl);
            strb.append("Equipage :").append(nl);
            for (final Peq peq : rotation.listPeqRot) {
                strb.append(peq.fab).append(" : ").append(peq.nom).append(" ").append(peq.prenom).append(nl);
            }
        }
        if (rotation.tempsVolTotal != null || rotation.tempsVolMep != null || rotation.tempsAbsence != null) {
            strb.append(nl);
        }
        if (rotation.tempsVolTotal != null) {
            strb.append("Temps de vol total  : ").append(rotation.tempsVolTotal).append(nl);
        }
        if (rotation.tempsVolMep != null) {
            strb.append("Temps de vol en Mep : ").append(rotation.tempsVolMep).append(nl);
        }
        if (rotation.tempsAbsence != null) {
            strb.append("Temps d'absence : ").append(rotation.tempsAbsence).append(nl);
        }
        if (rotation.dureePac != null || rotation.dureeRpc != null) {
            strb.append(nl);
        }
        if (rotation.dureePac != null) {
            strb.append("Repos pr\u00e9-courrier : ").append(rotation.dureePac).append(nl);
        }
        if (rotation.dureeRpc != null) {
            strb.append("Repos post-courrier : ").append(rotation.dureeRpc).append(nl);
        }
        if (rotation.listDestReco.size() > 0) {
            strb.append(nl);
            strb.append("Reconnaissances destination :").append(nl);
            for (final Dest_Reco reco : rotation.listDestReco) {
                strb.append(reco.dest).append(" : cat.").append(reco.categorie).append(nl);
            }
        }
        if (rotation.listDegReco.size() > 0) {
            strb.append(nl);
            strb.append("Reconnaissances d\u00e9gagements :").append(nl);
            for (final Deg_Reco reco2 : rotation.listDegReco) {
                strb.append(reco2.deg).append(" : cat.").append(reco2.categorie).append(" (").append(reco2.dest).append(")").append(nl);
            }
        }
        strb.append(nl);
        strb.append("Heures d\u00e9compt\u00e9es / r\u00e9mun\u00e9r\u00e9es : ").append(nl);
        strb.append("HCA : ").append(rotation.HCAp).append("    /    ").append(rotation.HCAp).append(nl);
        strb.append("\u03a3H1 : ").append(rotation.SH1p).append("    /    ").append(rotation.SH1Rp).append(nl);
        strb.append("H2 :  ").append(rotation.H2p).append("    /    ").append(rotation.H2Rp).append(nl);
        strb.append("</html>");
        return strb.toString().replaceAll("null", "NIL");
    }
    
    private String generateTipString(final Troncon troncon, final Rotation rotation) {
        final ServiceVol sv = rotation.listSV.get(troncon.numSV - 1);
        final StringBuilder strb = new StringBuilder();
        final String nl = "<br>";
        strb.append("<html>");
        strb.append(troncon.numVol).append(nl);
        strb.append(nl);
        strb.append("D\u00e9part : ").append(troncon.departUTC).append(" UTC").append(nl);
        strb.append("Arriv\u00e9e : ").append(troncon.arriveeUTC).append(" UTC").append(nl);
        if (troncon.versionExploitation != null && !troncon.versionExploitation.equals("")) {
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
        strb.append("</html>");
        return strb.toString().replaceAll("null", "NIL");
    }
    
    private String generateTipString(final ActiviteSol act) {
        final StringBuilder strb = new StringBuilder();
        final String nl = "<br>";
        strb.append("<html>");
        strb.append(act.label).append(nl);
        strb.append(nl);
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
        strb.append("</html>");
        return strb.toString().replaceAll("null", "NIL");
    }
}
