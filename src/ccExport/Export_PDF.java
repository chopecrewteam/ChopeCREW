// 
// Decompiled by Procyon v0.5.36
// 

package ccExport;

import ccStructures.Presta;
import ccUtils.Utils;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.Iterator;
import ccStructures.ActiviteSol;
import ccStructures.Rotation;
import gnu.jpdf.BoundingBox;
import java.awt.Dimension;
import java.awt.Point;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import ccEngine.AnalyseCrew;
import java.awt.print.Paper;
import chopeCrew.ChopeCrew;
import java.awt.print.PageFormat;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import gnu.jpdf.PDFJob;

public class Export_PDF
{
    private PDFJob job;
    private Graphics graphics;
    private Font fBig;
    private Font fNorm;
    private Font fSmall;
    private Font fXsmall;
    private FontMetrics fmBig;
    private FontMetrics fmNorm;
    private FontMetrics fmSmall;
    private FontMetrics fmXsmall;
    private Font fNormI;
    private Font fSmallI;
    private Font fXsmallI;
    private FontMetrics fmNormI;
    private FontMetrics fmSmallI;
    private FontMetrics fmXsmallI;
    private Color jourSemaineColor;
    private Color jourWeColor;
    private Color rotationColor1;
    private Color rotationColor2;
    private Color rotationInstructionColor1;
    private Color rotationInstructionColor2;
    private Color escaleColor;
    private Color reposColor;
    private Color congeColor;
    private Color reposPrePostColor;
    private Color dispColor;
    private Color absenceColor;
    private Color instructionColor;
    private Color autreColor;
    private int lDate;
    private int lInfo;
    private int lTypeAvion;
    private int lTVSV;
    private int lTA;
    private int hLigne;
    private int hTitre;
    private int margeMiniGD;
    private int margeMiniB;
    private PageFormat pageFormat;
    private String memoStringBoxDate;
    private int memoHBoxDate;
    
    public Export_PDF() {
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
        String policeJava = new String();
        if (ChopeCrew.options.webFont == 0) {
            policeJava = "SansSerif";
        }
        else if (ChopeCrew.options.webFont == 1) {
            policeJava = "Serif";
        }
        else {
            policeJava = "Monospaced";
        }
        this.fBig = new Font(policeJava, 0, 16);
        this.fNorm = new Font(policeJava, 0, 12);
        this.fSmall = new Font(policeJava, 0, 10);
        this.fXsmall = new Font(policeJava, 0, 8);
        this.fNormI = new Font(policeJava, 2, 12);
        this.fSmallI = new Font(policeJava, 2, 10);
        this.fXsmallI = new Font(policeJava, 2, 8);
        if (ChopeCrew.options.webColor == 0) {
            this.jourSemaineColor = new Color(157, 169, 180);
            this.jourWeColor = new Color(119, 136, 153);
            this.rotationColor1 = new Color(0, 128, 255);
            this.rotationColor2 = new Color(0, 176, 255);
            this.rotationInstructionColor1 = new Color(128, 128, 255);
            this.rotationInstructionColor2 = new Color(176, 176, 255);
            this.escaleColor = new Color(51, 153, 255);
            this.reposColor = new Color(0, 255, 128);
            this.congeColor = new Color(0, 128, 0);
            this.reposPrePostColor = new Color(0, 255, 160);
            this.dispColor = new Color(255, 255, 255);
            this.absenceColor = new Color(252, 217, 75);
            this.instructionColor = new Color(178, 178, 34);
            this.autreColor = new Color(178, 34, 34);
        }
        else if (ChopeCrew.options.webColor == 1) {
            this.jourSemaineColor = new Color(238, 238, 238);
            this.jourWeColor = new Color(224, 224, 224);
            this.rotationColor1 = new Color(175, 214, 254);
            this.rotationColor2 = new Color(192, 223, 255);
            this.rotationInstructionColor1 = new Color(214, 214, 254);
            this.rotationInstructionColor2 = new Color(223, 223, 255);
            this.escaleColor = new Color(175, 223, 255);
            this.reposColor = new Color(191, 255, 224);
            this.congeColor = new Color(115, 230, 175);
            this.reposPrePostColor = new Color(191, 255, 254);
            this.dispColor = new Color(255, 255, 255);
            this.absenceColor = new Color(255, 237, 163);
            this.instructionColor = new Color(236, 236, 200);
            this.autreColor = new Color(236, 200, 200);
        }
        else {
            this.jourSemaineColor = new Color(255, 255, 255);
            this.jourWeColor = new Color(241, 241, 241);
            this.rotationColor1 = new Color(255, 255, 255);
            this.rotationColor2 = new Color(255, 255, 255);
            this.rotationInstructionColor1 = new Color(255, 255, 255);
            this.rotationInstructionColor2 = new Color(255, 255, 255);
            this.escaleColor = new Color(255, 255, 255);
            this.reposColor = new Color(255, 255, 255);
            this.congeColor = new Color(255, 255, 255);
            this.reposPrePostColor = new Color(255, 255, 255);
            this.dispColor = new Color(255, 255, 255);
            this.absenceColor = new Color(255, 255, 255);
            this.instructionColor = new Color(255, 255, 255);
            this.autreColor = new Color(255, 255, 255);
        }
        this.lDate = 54;
        this.lInfo = 332;
        if (ChopeCrew.options.webTempsVol) {
            this.lInfo += 44;
        }
        if (ChopeCrew.options.webNumVol) {
            this.lInfo += 44;
        }
        if (ChopeCrew.options.webTempsEscale) {
            this.lInfo += 35;
        }
        if (ChopeCrew.options.webPresta) {
            this.lInfo += 45;
        }
        this.lTypeAvion = 0;
        if (ChopeCrew.options.webTypeAvion) {
            this.lTypeAvion += 32;
        }
        this.lTVSV = 0;
        if (ChopeCrew.options.webTempsVolSv) {
            this.lTVSV += 76;
        }
        this.lTA = 0;
        if (ChopeCrew.options.webTempsAbsence) {
            this.lTA += 54;
        }
        this.hTitre = 36;
        this.margeMiniGD = 10;
        this.margeMiniB = 10;
        this.hLigne = 16;
        final Paper a4 = new Paper();
        a4.setSize(595.0, 842.0);
        if (this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA <= 595 - 2 * this.margeMiniGD) {
            (this.pageFormat = new PageFormat()).setOrientation(1);
            this.pageFormat.setPaper(a4);
        }
        else {
            (this.pageFormat = new PageFormat()).setOrientation(0);
            this.pageFormat.setPaper(a4);
        }
        if (ChopeCrew.options.isPdfPersoLaurent) {
            (this.pageFormat = new PageFormat()).setOrientation(0);
            this.pageFormat.setPaper(a4);
        }
    }
    
    public void createPDF(final AnalyseCrew analyse, final File file) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            String title = "";
            if (analyse.deltaMois == 0) {
                title = "Planning " + analyse.moisLit + " " + analyse.anneeInt;
            }
            else if (analyse.deltaMois == 1) {
                title = "Planning previsionnel " + analyse.moisLit + " " + analyse.anneeInt;
            }
            else if (analyse.deltaMois == 2) {
                title = "Planning previsionnel " + analyse.moisLit + " " + analyse.anneeInt;
            }
            this.job = new PDFJob(fileOutputStream, title);
            this.graphics = this.job.getGraphics(this.pageFormat);
            final Dimension d = this.job.getPageDimension();
            this.fmBig = this.graphics.getFontMetrics(this.fBig);
            this.fmNorm = this.graphics.getFontMetrics(this.fNorm);
            this.fmSmall = this.graphics.getFontMetrics(this.fSmall);
            this.fmXsmall = this.graphics.getFontMetrics(this.fXsmall);
            this.fmNormI = this.graphics.getFontMetrics(this.fNormI);
            this.fmSmallI = this.graphics.getFontMetrics(this.fSmallI);
            this.fmXsmallI = this.graphics.getFontMetrics(this.fXsmallI);
            final int nbLignes = (d.height - this.hTitre - this.margeMiniB) / this.hLigne;
            System.out.println("nbre de  lignes par page : " + nbLignes);
            final int hPlanning = this.getPlanningSize(analyse, 0, analyse.listCrew.size());
            final int nbLignesPlanning = hPlanning / this.hLigne;
            System.out.println("nbre de lignes du planning : " + nbLignesPlanning);
            final int nbPages = (int)Math.ceil(nbLignesPlanning / (double)nbLignes);
            System.out.println("nbre mini de pages du planning : " + nbPages);
            final int nbLignesMoyen = (int)Math.ceil(nbLignesPlanning / (double)nbPages);
            System.out.println("nbre moyen de lignes par page : " + nbLignesMoyen);
            final int hEquil = nbLignesMoyen * this.hLigne;
            System.out.println("hauteur moyenne d'une page en pix : " + hEquil);
            String string = "";
            if (analyse.deltaMois == 0) {
                string = "Planning " + analyse.moisLit + " " + analyse.anneeInt;
            }
            else if (analyse.deltaMois == 1) {
                string = "Planning pr\u00e9visionnel " + analyse.moisLit + " " + analyse.anneeInt;
            }
            else if (analyse.deltaMois == 2) {
                string = "Planning pr\u00e9visionnel " + analyse.moisLit + " " + analyse.anneeInt;
            }
            if (ChopeCrew.options.idxTimeRef == 0) {
                string = String.valueOf(string) + "  (Heures de Paris)";
            }
            else if (ChopeCrew.options.idxTimeRef == 1) {
                string = String.valueOf(string) + "  (Heures locales)";
            }
            final int margeB = this.margeMiniB;
            final int margeGD = (d.width - (this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA)) / 2;
            final int padding = 0;
            final BoundingBox zoneTitre = new BoundingBox(new Point(margeGD, 0), new Dimension(d.width - 2 * margeGD, this.hTitre));
            BoundingBox zonePlanning = new BoundingBox(new Point(margeGD, this.hTitre), new Dimension(d.width - 2 * margeGD, d.height - this.hTitre - margeB));
            if (ChopeCrew.options.isPdfPersoLaurent) {
                zonePlanning = new BoundingBox(new Point(this.margeMiniGD, this.hTitre), new Dimension(d.width - 2 * this.margeMiniGD, d.height - this.hTitre - margeB));
            }
            this.graphics.setFont(this.fBig);
            this.graphics.setColor(Color.BLACK);
            BoundingBox cZoneTitre = zoneTitre.getStringBounds(string, 3, 0, this.fmBig, padding);
            cZoneTitre.drawWrappedString(this.graphics, this.fmBig, padding, 3);
            this.graphics.setColor(Color.GRAY);
            this.graphics.drawLine(zonePlanning.getAbsoluteLocation().x, zonePlanning.getAbsoluteLocation().y, zonePlanning.getAbsoluteLocation().x + zonePlanning.getSize().width, zonePlanning.getAbsoluteLocation().y);
            int zi = 0;
            for (final Object obj : analyse.listCrew) {
                if (obj instanceof Rotation) {
                    final Rotation rotation = (Rotation)obj;
                    if (!rotation.isExportable() && !ChopeCrew.options.webMoisComplet) {
                        continue;
                    }
                    final int hRot = this.getRotationSize(rotation);
                    if (zi + hRot > d.height - this.hTitre - margeB) {
                        this.graphics.setColor(Color.GRAY);
                        this.graphics.drawLine(zonePlanning.getAbsoluteLocation().x, zonePlanning.getAbsoluteLocation().y + zi, zonePlanning.getAbsoluteLocation().x + zonePlanning.getSize().width, zonePlanning.getAbsoluteLocation().y + zi);
                        this.graphics.dispose();
                        (this.graphics = this.job.getGraphics(this.pageFormat)).setFont(this.fBig);
                        this.graphics.setColor(Color.BLACK);
                        cZoneTitre = zoneTitre.getStringBounds(string, 3, 0, this.fmBig, padding);
                        cZoneTitre.drawWrappedString(this.graphics, this.fmBig, padding, 3);
                        this.graphics.setColor(Color.GRAY);
                        this.graphics.drawLine(zonePlanning.getAbsoluteLocation().x, zonePlanning.getAbsoluteLocation().y, zonePlanning.getAbsoluteLocation().x + zonePlanning.getSize().width, zonePlanning.getAbsoluteLocation().y);
                        zi = 0;
                    }
                    final BoundingBox boxRotation = new BoundingBox(new Point(0, zi), new Dimension(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, hRot));
                    zonePlanning.add(boxRotation);
                    if (rotation.numRot % 2 == 0) {
                        if (rotation.sab != null && (rotation.sab.contains("IPLG") || rotation.sab.contains("IPLD") || rotation.sab.contains("IPLJPS") || rotation.sab.contains("IPLJS"))) {
                            this.fillBox(this.graphics, boxRotation, this.rotationInstructionColor1);
                        }
                        else {
                            this.fillBox(this.graphics, boxRotation, this.rotationColor1);
                        }
                    }
                    else if (rotation.numRot % 2 == 1) {
                        if (rotation.sab != null && (rotation.sab.contains("IPLG") || rotation.sab.contains("IPLD") || rotation.sab.contains("IPLJPS") || rotation.sab.contains("IPLJS"))) {
                            this.fillBox(this.graphics, boxRotation, this.rotationInstructionColor2);
                        }
                        else {
                            this.fillBox(this.graphics, boxRotation, this.rotationColor2);
                        }
                    }
                    this.drawRotation(this.graphics, boxRotation, rotation);
                    zi += hRot;
                }
                else {
                    if (!(obj instanceof ActiviteSol)) {
                        continue;
                    }
                    final ActiviteSol act = (ActiviteSol)obj;
                    if (!act.isExportable() && !ChopeCrew.options.webMoisComplet) {
                        continue;
                    }
                    final int hAct = this.getActiviteSolSize(act);
                    if (zi + hAct > d.height - this.hTitre - margeB) {
                        this.graphics.setColor(Color.GRAY);
                        this.graphics.drawLine(zonePlanning.getAbsoluteLocation().x, zonePlanning.getAbsoluteLocation().y + zi, zonePlanning.getAbsoluteLocation().x + zonePlanning.getSize().width, zonePlanning.getAbsoluteLocation().y + zi);
                        this.graphics.dispose();
                        (this.graphics = this.job.getGraphics(this.pageFormat)).setFont(this.fBig);
                        this.graphics.setColor(Color.BLACK);
                        cZoneTitre = zoneTitre.getStringBounds(string, 3, 0, this.fmBig, padding);
                        cZoneTitre.drawWrappedString(this.graphics, this.fmBig, padding, 3);
                        this.graphics.setColor(Color.GRAY);
                        this.graphics.drawLine(zonePlanning.getAbsoluteLocation().x, zonePlanning.getAbsoluteLocation().y, zonePlanning.getAbsoluteLocation().x + zonePlanning.getSize().width, zonePlanning.getAbsoluteLocation().y);
                        zi = 0;
                    }
                    final BoundingBox boxSol = new BoundingBox(new Point(0, zi), new Dimension(this.lDate + this.lInfo, hAct));
                    zonePlanning.add(boxSol);
                    Color actColor;
                    if (act.code.equals("PRB") || act.code.equals("MAD")) {
                        actColor = this.reposColor;
                    }
                    else if (act.code.equals("PAC") || act.code.equals("RPC")) {
                        actColor = this.reposPrePostColor;
                    }
                    else if (act.code.equals("MCA") || act.code.equals("MCE")) {
                        actColor = this.congeColor;
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
                    this.fillBox(this.graphics, boxSol, actColor);
                    this.drawBox(this.graphics, boxSol, Color.GRAY);
                    this.drawActiviteSol(this.graphics, boxSol, act);
                    final BoundingBox boxVide = new BoundingBox(new Point(this.lDate + this.lInfo, zi), new Dimension(this.lTypeAvion + this.lTVSV + this.lTA, hAct));
                    zonePlanning.add(boxVide);
                    this.graphics.setColor(Color.GRAY);
                    this.graphics.drawLine(boxVide.getAbsoluteLocation().x + boxVide.getSize().width, boxVide.getAbsoluteLocation().y, boxVide.getAbsoluteLocation().x + boxVide.getSize().width, boxVide.getAbsoluteLocation().y + hAct);
                    zi += hAct;
                }
            }
            this.graphics.setColor(Color.GRAY);
            this.graphics.drawLine(zonePlanning.getAbsoluteLocation().x, zonePlanning.getAbsoluteLocation().y + zi, zonePlanning.getAbsoluteLocation().x + zonePlanning.getSize().width, zonePlanning.getAbsoluteLocation().y + zi);
            this.graphics.dispose();
            this.job.end();
            fileOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private int getPlanningSize(final AnalyseCrew analyse, final int indiceListCrew, final int nbElements) {
        int hPlanning = 0;
        for (int n = 0, i = indiceListCrew; i < analyse.listCrew.size() && n <= nbElements; ++i) {
            final Object object = analyse.listCrew.get(i);
            if (object instanceof Rotation) {
                final Rotation rotation = (Rotation)object;
                if (rotation.isExportable() || ChopeCrew.options.webMoisComplet) {
                    ++n;
                    hPlanning += this.getRotationSize(rotation);
                }
            }
            if (object instanceof ActiviteSol) {
                final ActiviteSol actSol = (ActiviteSol)object;
                if (actSol.isExportable() || ChopeCrew.options.webMoisComplet) {
                    ++n;
                    hPlanning += this.getActiviteSolSize(actSol);
                }
            }
        }
        return hPlanning;
    }
    
    private int getActiviteSolSize(final ActiviteSol actSol) {
        int hAct = 0;
        if (actSol.isH24) {
            hAct = this.hLigne;
        }
        else if (!actSol.isH24) {
            final GregorianCalendar cal1 = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
            final GregorianCalendar cal2 = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
            cal1.setTime(actSol.debutUTCD);
            cal1.set(11, 0);
            cal1.set(12, 0);
            cal1.set(13, 0);
            cal1.set(14, 0);
            cal2.setTime(actSol.finUTCD);
            cal2.set(11, 0);
            cal2.set(12, 0);
            cal2.set(13, 0);
            cal2.set(14, 0);
            cal2.add(5, 1);
            final long deltaOffset = cal2.get(15) + cal2.get(16) - cal1.get(15) - cal1.get(16);
            hAct = (int)(cal2.getTimeInMillis() - cal1.getTimeInMillis() + deltaOffset) / 1000 / 60 / 60 / 24;
            hAct *= this.hLigne;
        }
        return hAct;
    }
    
    private int getRotationSize(final Rotation rotation) {
        final GregorianCalendar calLastTroSv = new GregorianCalendar();
        final GregorianCalendar calFirstTroNextSv = new GregorianCalendar();
        int hRot = 0;
        for (final ServiceVol sv : rotation.listSV) {
            final int rowspanSV = this.getServiceVolRowspan(sv, rotation);
            final int hSV = this.getServiceVolSize(sv, rotation, rowspanSV);
            hRot += hSV;
            if (sv.numSV < rotation.listSV.size()) {
                final ServiceVol nextSv = rotation.listSV.get(sv.numSV);
                final Troncon lastTroSv = sv.listTroncon.get(sv.listTroncon.size() - 1);
                final Troncon firstTroNextSv = nextSv.listTroncon.get(0);
                if (ChopeCrew.options.idxTimeRef == 0) {
                    calLastTroSv.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    calFirstTroNextSv.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                }
                else if (ChopeCrew.options.idxTimeRef == 1) {
                    final SimpleTimeZone tzLastTroSv = new SimpleTimeZone(lastTroSv.LAGtoMillis, "");
                    final SimpleTimeZone tzFirstTroNextSv = new SimpleTimeZone(firstTroNextSv.LAGfromMillis, "");
                    calLastTroSv.setTimeZone(tzLastTroSv);
                    calFirstTroNextSv.setTimeZone(tzFirstTroNextSv);
                }
                calLastTroSv.setTime(lastTroSv.finUTCD);
                calLastTroSv.set(11, 24);
                calLastTroSv.set(12, 0);
                calLastTroSv.set(13, 0);
                calLastTroSv.set(14, 0);
                calFirstTroNextSv.setTime(firstTroNextSv.debutUTCD);
                calFirstTroNextSv.set(11, 0);
                calFirstTroNextSv.set(12, 0);
                calFirstTroNextSv.set(13, 0);
                calFirstTroNextSv.set(14, 0);
                final long deltaOffset = calFirstTroNextSv.get(15) + calFirstTroNextSv.get(16) - calLastTroSv.get(15) - calLastTroSv.get(16);
                final long deltaJ = (calFirstTroNextSv.getTimeInMillis() - calLastTroSv.getTimeInMillis() + deltaOffset) / 86400000L;
                for (int z = 0; z < deltaJ; ++z) {
                    hRot += this.hLigne;
                }
            }
        }
        return hRot;
    }
    
    private int getServiceVolRowspan(final ServiceVol sv, final Rotation rotation) {
        final GregorianCalendar calDeb = new GregorianCalendar();
        final GregorianCalendar calFin = new GregorianCalendar();
        final Troncon firstTroSv = sv.listTroncon.get(0);
        final Troncon lastTroSv = sv.listTroncon.get(sv.listTroncon.size() - 1);
        if (ChopeCrew.options.idxTimeRef == 0) {
            calDeb.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            calDeb.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        }
        else if (ChopeCrew.options.idxTimeRef == 1) {
            final SimpleTimeZone tzFirstTroSv = new SimpleTimeZone(firstTroSv.LAGfromMillis, "");
            final SimpleTimeZone tzLastTroSv = new SimpleTimeZone(lastTroSv.LAGtoMillis, "");
            calDeb.setTimeZone(tzFirstTroSv);
            calFin.setTimeZone(tzLastTroSv);
        }
        calDeb.setTime(firstTroSv.debutUTCD);
        calFin.setTime(lastTroSv.finUTCD);
        final boolean isSvAcheval = calDeb.get(5) != calFin.get(5);
        int rowspanSv;
        if (!isSvAcheval) {
            rowspanSv = 1;
        }
        else if (sv.numSV < rotation.listSV.size()) {
            final ServiceVol nextSv = rotation.listSV.get(sv.numSV);
            final Troncon firstTroNextSv = nextSv.listTroncon.get(0);
            final GregorianCalendar calFirstNext = new GregorianCalendar();
            if (ChopeCrew.options.idxTimeRef == 0) {
                calFirstNext.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            }
            else if (ChopeCrew.options.idxTimeRef == 1) {
                final SimpleTimeZone tzFirstNext = new SimpleTimeZone(firstTroNextSv.LAGfromMillis, "LOCfirstTroNextSVfrom");
                calFirstNext.setTimeZone(tzFirstNext);
            }
            calFirstNext.setTime(firstTroNextSv.debutUTCD);
            if (calFirstNext.get(5) != calFin.get(5)) {
                rowspanSv = 2;
            }
            else {
                rowspanSv = 1;
            }
        }
        else {
            rowspanSv = 2;
        }
        return rowspanSv;
    }
    
    private int getServiceVolSize(final ServiceVol sv, final Rotation rotation, final int rowspan) {
        int iflag1 = 0;
        int iflag2 = 0;
        if (sv.isDecoucher && (ChopeCrew.options.webDureeDecoucher || ChopeCrew.options.webHotel || ChopeCrew.options.webDecalage)) {
            iflag1 = 1;
        }
        else if (!sv.isDecoucher && ((ChopeCrew.options.webRpc && rotation.dureeRpc != null) || (ChopeCrew.options.webReeng && rotation.reengagementUTC != null))) {
            iflag2 = 1;
        }
        int hSV = sv.listTroncon.size() * this.hLigne + iflag1 * this.hLigne + iflag2 * this.hLigne;
        if (hSV == this.hLigne && rowspan == 2) {
            hSV = this.hLigne * 2;
        }
        if (hSV == this.hLigne && ChopeCrew.options.webTempsVolSv) {
            hSV = this.hLigne * 2;
        }
        return hSV;
    }
    
    private void drawRotation(final Graphics g, final BoundingBox parent, final Rotation rotation) {
        final GregorianCalendar calLastTroSv = new GregorianCalendar();
        final GregorianCalendar calFirstTroNextSv = new GregorianCalendar();
        try {
            int zj = 0;
            for (final ServiceVol sv : rotation.listSV) {
                final int rowspanSv = this.getServiceVolRowspan(sv, rotation);
                final int hSV = this.getServiceVolSize(sv, rotation, rowspanSv);
                final BoundingBox boxServiceVol = new BoundingBox(new Point(0, zj), new Dimension(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV, hSV));
                parent.add(boxServiceVol);
                this.drawBox(g, boxServiceVol, Color.GRAY);
                this.drawSV(g, boxServiceVol, sv, rotation);
                zj += hSV;
                if (sv.numSV < rotation.listSV.size()) {
                    final ServiceVol nextSv = rotation.listSV.get(sv.numSV);
                    final Troncon lastTroSv = sv.listTroncon.get(sv.listTroncon.size() - 1);
                    final Troncon firstTroNextSv = nextSv.listTroncon.get(0);
                    if (ChopeCrew.options.idxTimeRef == 0) {
                        calLastTroSv.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                        calFirstTroNextSv.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    }
                    else if (ChopeCrew.options.idxTimeRef == 1) {
                        final SimpleTimeZone tzLastTroSv = new SimpleTimeZone(lastTroSv.LAGtoMillis, "");
                        final SimpleTimeZone tzFirstTroNextSv = new SimpleTimeZone(firstTroNextSv.LAGfromMillis, "");
                        calLastTroSv.setTimeZone(tzLastTroSv);
                        calFirstTroNextSv.setTimeZone(tzFirstTroNextSv);
                    }
                    calLastTroSv.setTime(lastTroSv.finUTCD);
                    calLastTroSv.set(11, 24);
                    calLastTroSv.set(12, 0);
                    calLastTroSv.set(13, 0);
                    calLastTroSv.set(14, 0);
                    calFirstTroNextSv.setTime(firstTroNextSv.debutUTCD);
                    calFirstTroNextSv.set(11, 0);
                    calFirstTroNextSv.set(12, 0);
                    calFirstTroNextSv.set(13, 0);
                    calFirstTroNextSv.set(14, 0);
                    final long deltaOffset = calFirstTroNextSv.get(15) + calFirstTroNextSv.get(16) - calLastTroSv.get(15) - calLastTroSv.get(16);
                    final long deltaJ = (calFirstTroNextSv.getTimeInMillis() - calLastTroSv.getTimeInMillis() + deltaOffset) / 86400000L;
                    for (int z = 0; z < deltaJ; ++z) {
                        final BoundingBox boxEscale = new BoundingBox(new Point(0, zj), new Dimension(this.lDate + this.lInfo, this.hLigne));
                        parent.add(boxEscale);
                        this.drawEscale(g, boxEscale, lastTroSv, z + 1, (int)deltaJ);
                        zj += this.hLigne;
                    }
                }
            }
            if (ChopeCrew.options.webTempsAbsence) {
                final BoundingBox boxTa = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV, 0), new Dimension(this.lTA, parent.height));
                parent.add(boxTa);
                this.fillBox(g, boxTa, Color.WHITE);
                this.drawBox(g, boxTa, Color.GRAY);
                final String string = "TA =\n" + rotation.tempsAbsence;
                final int padding = 0;
                g.setFont(this.fSmall);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxTa = boxTa.getStringBounds(string, 3, 0, this.fmSmall, padding);
                cBoxTa.drawWrappedString(g, this.fmSmall, padding, 3);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void drawEscale(final Graphics g, final BoundingBox parent, final Troncon lastTroncon, final int numEscale, final int nbreJourEscale) {
        final int padding = 0;
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd ");
        final GregorianCalendar cal = new GregorianCalendar();
        if (ChopeCrew.options.idxTimeRef == 0) {
            cal.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        }
        else if (ChopeCrew.options.idxTimeRef == 1) {
            final SimpleTimeZone tzLastTro = new SimpleTimeZone(lastTroncon.LAGtoMillis, "");
            cal.setTimeZone(tzLastTro);
            dateFormat.setTimeZone(tzLastTro);
        }
        cal.setTime(lastTroncon.finUTCD);
        cal.add(5, numEscale);
        try {
            final BoundingBox boxDate = new BoundingBox(new Point(0, 0), new Dimension(this.lDate, this.hLigne));
            parent.add(boxDate);
            if (cal.get(7) == 1 || cal.get(7) == 7) {
                this.fillBox(g, boxDate, this.jourWeColor);
            }
            else {
                this.fillBox(g, boxDate, this.jourSemaineColor);
            }
            this.drawBox(g, boxDate, Color.GRAY);
            String string = dateFormat.format(cal.getTime());
            g.setFont(this.fSmall);
            g.setColor(Color.BLACK);
            final BoundingBox cBoxDate = boxDate.getStringBounds(string, 3, 0, this.fmSmall, padding);
            cBoxDate.drawWrappedString(g, this.fmNorm, padding, 3);
            if (ChopeCrew.options.isPdfPersoLaurent) {
                final BoundingBox boxPerso = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, 0), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, this.hLigne));
                parent.add(boxPerso);
                this.drawBox(g, boxPerso, Color.GRAY);
            }
            if (numEscale == 1) {
                final BoundingBox boxEscale = new BoundingBox(new Point(this.lDate, 0), new Dimension(this.lInfo, this.hLigne * nbreJourEscale));
                parent.add(boxEscale);
                this.fillBox(g, boxEscale, this.escaleColor);
                this.drawBox(g, boxEscale, Color.GRAY);
                string = "Escale " + lastTroncon.to;
                g.setFont(this.fNorm);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxEscale = boxEscale.getStringBounds(string, 3, 0, this.fmNorm, padding);
                cBoxEscale.drawWrappedString(g, this.fmNorm, padding, 3);
            }
            if (numEscale == 1) {
                final BoundingBox boxVide = new BoundingBox(new Point(this.lDate + this.lInfo, 0), new Dimension(this.lTypeAvion + this.lTVSV, this.hLigne * nbreJourEscale));
                parent.add(boxVide);
                this.fillBox(g, boxVide, Color.WHITE);
                this.drawBox(g, boxVide, Color.GRAY);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void drawSV(final Graphics g, final BoundingBox parent, final ServiceVol sv, final Rotation rotation) {
        final int padding = 0;
        try {
            final GregorianCalendar cal = new GregorianCalendar();
            final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd ");
            final SimpleDateFormat dateHourFormat = new SimpleDateFormat("dd/MM/yyy '\u00e0' HH'h'mm");
            dateHourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            final int rowspanSv = this.getServiceVolRowspan(sv, rotation);
            final int hSV = this.getServiceVolSize(sv, rotation, rowspanSv);
            if (rowspanSv == 1) {
                final Troncon first = sv.listTroncon.get(0);
                if (ChopeCrew.options.idxTimeRef == 0) {
                    cal.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                }
                else if (ChopeCrew.options.idxTimeRef == 1) {
                    final SimpleTimeZone tz = new SimpleTimeZone(first.LAGfromMillis, "");
                    cal.setTimeZone(tz);
                    dateFormat.setTimeZone(tz);
                }
                cal.setTime(first.debutUTCD);
                final String string = dateFormat.format(cal.getTime());
                BoundingBox boxDate;
                if (string.equals(this.memoStringBoxDate) && parent.getAbsoluteLocation().y >= this.memoHBoxDate) {
                    boxDate = new BoundingBox(new Point(0, -1 * this.memoHBoxDate), new Dimension(this.lDate, this.memoHBoxDate + hSV));
                    if (ChopeCrew.options.isPdfPersoLaurent) {
                        final BoundingBox boxPerso = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, -1 * this.memoHBoxDate), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, this.memoHBoxDate + hSV));
                        parent.add(boxPerso);
                        this.fillBox(g, boxPerso, Color.WHITE);
                        this.drawBox(g, boxPerso, Color.GRAY);
                    }
                    this.memoHBoxDate += hSV;
                    this.memoStringBoxDate = string;
                }
                else {
                    boxDate = new BoundingBox(new Point(0, 0), new Dimension(this.lDate, hSV));
                    if (ChopeCrew.options.isPdfPersoLaurent) {
                        final BoundingBox boxPerso = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, 0), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, hSV));
                        parent.add(boxPerso);
                        this.fillBox(g, boxPerso, Color.WHITE);
                        this.drawBox(g, boxPerso, Color.GRAY);
                    }
                    this.memoHBoxDate = hSV;
                    this.memoStringBoxDate = string;
                }
                parent.add(boxDate);
                if (cal.get(7) == 1 || cal.get(7) == 7) {
                    this.fillBox(g, boxDate, this.jourWeColor);
                }
                else {
                    this.fillBox(g, boxDate, this.jourSemaineColor);
                }
                this.drawBox(g, boxDate, Color.GRAY);
                g.setFont(this.fSmall);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxDate = boxDate.getStringBounds(string, 3, 0, this.fmSmall, padding);
                cBoxDate.drawWrappedString(g, this.fmSmall, padding, 3);
            }
            else if (rowspanSv == 2) {
                final Troncon first = sv.listTroncon.get(0);
                if (ChopeCrew.options.idxTimeRef == 0) {
                    cal.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                }
                else if (ChopeCrew.options.idxTimeRef == 1) {
                    final SimpleTimeZone tzFirst = new SimpleTimeZone(first.LAGfromMillis, "");
                    cal.setTimeZone(tzFirst);
                    dateFormat.setTimeZone(tzFirst);
                }
                cal.setTime(first.debutUTCD);
                String string = dateFormat.format(cal.getTime());
                BoundingBox boxDate2;
                if (string.equals(this.memoStringBoxDate) && parent.getAbsoluteLocation().y >= this.memoHBoxDate) {
                    boxDate2 = new BoundingBox(new Point(0, -1 * this.memoHBoxDate), new Dimension(this.lDate, this.memoHBoxDate + hSV / 2));
                    if (ChopeCrew.options.isPdfPersoLaurent) {
                        final BoundingBox boxPerso2 = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, -1 * this.memoHBoxDate), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, this.memoHBoxDate + hSV / 2));
                        parent.add(boxPerso2);
                        this.fillBox(g, boxPerso2, Color.WHITE);
                        this.drawBox(g, boxPerso2, Color.GRAY);
                    }
                    this.memoHBoxDate += hSV / 2;
                    this.memoStringBoxDate = string;
                }
                else {
                    boxDate2 = new BoundingBox(new Point(0, 0), new Dimension(this.lDate, hSV / 2));
                    if (ChopeCrew.options.isPdfPersoLaurent) {
                        final BoundingBox boxPerso2 = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, 0), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, hSV / 2));
                        parent.add(boxPerso2);
                        this.fillBox(g, boxPerso2, Color.WHITE);
                        this.drawBox(g, boxPerso2, Color.GRAY);
                    }
                    this.memoHBoxDate = hSV / 2;
                    this.memoStringBoxDate = string;
                }
                parent.add(boxDate2);
                if (cal.get(7) == 1 || cal.get(7) == 7) {
                    this.fillBox(g, boxDate2, this.jourWeColor);
                }
                else {
                    this.fillBox(g, boxDate2, this.jourSemaineColor);
                }
                this.drawBox(g, boxDate2, Color.GRAY);
                g.setFont(this.fSmall);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxDate2 = boxDate2.getStringBounds(string, 3, 0, this.fmSmall, padding);
                cBoxDate2.drawWrappedString(g, this.fmSmall, padding, 3);
                final Troncon last = sv.listTroncon.get(sv.listTroncon.size() - 1);
                if (ChopeCrew.options.idxTimeRef == 0) {
                    cal.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                    dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                }
                else if (ChopeCrew.options.idxTimeRef == 1) {
                    final SimpleTimeZone tzLast = new SimpleTimeZone(last.LAGtoMillis, "");
                    cal.setTimeZone(tzLast);
                    dateFormat.setTimeZone(tzLast);
                }
                cal.setTime(last.finUTCD);
                string = dateFormat.format(cal.getTime());
                BoundingBox boxDate3;
                if (string.equals(this.memoStringBoxDate) && parent.getAbsoluteLocation().y >= this.memoHBoxDate) {
                    boxDate3 = new BoundingBox(new Point(0, -1 * this.memoHBoxDate), new Dimension(this.lDate, this.memoHBoxDate + hSV / 2));
                    if (ChopeCrew.options.isPdfPersoLaurent) {
                        final BoundingBox boxPerso3 = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, -1 * this.memoHBoxDate), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, this.memoHBoxDate + hSV / 2));
                        parent.add(boxPerso3);
                        this.fillBox(g, boxPerso3, Color.WHITE);
                        this.drawBox(g, boxPerso3, Color.GRAY);
                    }
                    this.memoHBoxDate += hSV / 2;
                    this.memoStringBoxDate = string;
                }
                else {
                    boxDate3 = new BoundingBox(new Point(0, hSV / 2), new Dimension(this.lDate, hSV / 2));
                    if (ChopeCrew.options.isPdfPersoLaurent) {
                        final BoundingBox boxPerso3 = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, hSV / 2), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, hSV / 2));
                        parent.add(boxPerso3);
                        this.fillBox(g, boxPerso3, Color.WHITE);
                        this.drawBox(g, boxPerso3, Color.GRAY);
                    }
                    this.memoHBoxDate = hSV / 2;
                    this.memoStringBoxDate = string;
                }
                parent.add(boxDate3);
                if (cal.get(7) == 1 || cal.get(7) == 7) {
                    this.fillBox(g, boxDate3, this.jourWeColor);
                }
                else {
                    this.fillBox(g, boxDate3, this.jourSemaineColor);
                }
                this.drawBox(g, boxDate3, Color.GRAY);
                g.setFont(this.fSmall);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxDate3 = boxDate3.getStringBounds(string, 3, 0, this.fmSmall, padding);
                cBoxDate3.drawWrappedString(g, this.fmSmall, padding, 3);
            }
            boolean flag1 = false;
            boolean flag2 = false;
            if (sv.isDecoucher && (ChopeCrew.options.webDureeDecoucher || ChopeCrew.options.webHotel || ChopeCrew.options.webDecalage)) {
                flag1 = true;
            }
            else if (!sv.isDecoucher && (ChopeCrew.options.webRpc || ChopeCrew.options.webReeng)) {
                flag2 = true;
            }
            int zk = 0;
            for (final Troncon tro : sv.listTroncon) {
                if (!flag1 && !flag2 && sv.listTroncon.size() == 1 && hSV == 2 * this.hLigne) {
                    zk = this.hLigne / 2;
                }
                final BoundingBox troncon = new BoundingBox(new Point(this.lDate, zk), new Dimension(this.lInfo, this.hLigne));
                parent.add(troncon);
                this.drawTroncon(g, troncon, tro);
                zk += this.hLigne;
            }
            if (flag1) {
                final BoundingBox decoucher = new BoundingBox(new Point(this.lDate, zk), new Dimension(this.lInfo, this.hLigne));
                parent.add(decoucher);
                final Troncon lastTroSv = sv.listTroncon.get(sv.listTroncon.size() - 1);
                boolean flagRepos = false;
                boolean flagHotel = false;
                String string = "  ";
                if (ChopeCrew.options.webDureeDecoucher) {
                    string = String.valueOf(string) + "D\u00e9coucher : " + sv.repos;
                    flagRepos = true;
                }
                if (lastTroSv.hotel != null && ChopeCrew.options.webHotel) {
                    if (flagRepos) {
                        string = String.valueOf(string) + ", ";
                    }
                    string = String.valueOf(string) + "H\u00f4tel : " + lastTroSv.hotel;
                    flagHotel = true;
                }
                if (ChopeCrew.options.webDecalage) {
                    final GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
                    gc.setTime(lastTroSv.finUTCD);
                    final int lagParis = gc.get(15) + gc.get(16);
                    double decalage = (lastTroSv.LAGtoMillis - lagParis) / 3600000.0;
                    decalage = Utils.arrondi(decalage, 1);
                    if (decalage != 0.0 && (flagRepos || flagHotel)) {
                        string = String.valueOf(string) + ", ";
                    }
                    if (decalage > 0.0) {
                        string = String.valueOf(string) + "D\u00e9calage : +" + decalage;
                    }
                    else if (decalage < 0.0) {
                        string = String.valueOf(string) + "D\u00e9calage : " + decalage;
                    }
                }
                g.setFont(this.fXsmall);
                g.setColor(Color.BLACK);
                final BoundingBox cdecoucher = decoucher.getStringBounds(string, 4, 0, this.fmXsmall, padding);
                cdecoucher.drawWrappedString(g, this.fmXsmall, padding, 3);
            }
            if (flag2) {
                final BoundingBox repreeng = new BoundingBox(new Point(this.lDate, zk), new Dimension(this.lInfo, this.hLigne));
                parent.add(repreeng);
                String string = "  ";
                if (ChopeCrew.options.webRpc && rotation.dureeRpc != null) {
                    string = String.valueOf(string) + "Repos post-courrier : " + rotation.dureeRpc;
                }
                if (ChopeCrew.options.webReeng && rotation.reengagementUTCD != null) {
                    if (ChopeCrew.options.webRpc && rotation.dureeRpc != null) {
                        string = String.valueOf(string) + ", ";
                    }
                    string = String.valueOf(string) + "R\u00e9eng. : " + dateHourFormat.format(rotation.reengagementUTCD);
                }
                g.setFont(this.fXsmall);
                g.setColor(Color.BLACK);
                final BoundingBox crepreeng = repreeng.getStringBounds(string, 4, 0, this.fmXsmall, padding);
                crepreeng.drawWrappedString(g, this.fmXsmall, padding, 3);
            }
            if (ChopeCrew.options.webTypeAvion) {
                final BoundingBox boxTypeAvion = new BoundingBox(new Point(this.lDate + this.lInfo, 0), new Dimension(this.lTypeAvion, hSV));
                parent.add(boxTypeAvion);
                String string = "";
                for (final Troncon tro2 : sv.listTroncon) {
                    if (!tro2.isMep) {
                        string = tro2.typeAvion;
                    }
                    else if (tro2.isMep && !rotation.isOD && string.equals("")) {
                        string = "MEP";
                    }
                    else {
                        if (!tro2.isMep || !rotation.isOD || !string.equals("")) {
                            continue;
                        }
                        string = "OD";
                    }
                }
                this.fillBox(g, boxTypeAvion, Color.WHITE);
                this.drawBox(g, boxTypeAvion, Color.GRAY);
                g.setFont(this.fSmall);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxTypeAvion = boxTypeAvion.getStringBounds(string, 3, 0, this.fmSmall, padding);
                cBoxTypeAvion.drawWrappedString(g, this.fmSmall, padding, 3);
            }
            if (ChopeCrew.options.webTempsVolSv) {
                final BoundingBox boxTVSV = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion, 0), new Dimension(this.lTVSV, hSV));
                parent.add(boxTVSV);
                final String string = "TV = " + sv.TVSV + "\nTSV = " + sv.TSV;
                this.fillBox(g, boxTVSV, Color.WHITE);
                this.drawBox(g, boxTVSV, Color.GRAY);
                g.setFont(this.fSmall);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxTVSV = boxTVSV.getStringBounds(string, 3, 0, this.fmSmall, padding);
                cBoxTVSV.drawWrappedString(g, this.fmSmall, padding, 3);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void drawTroncon(final Graphics g, final BoundingBox parent, final Troncon troncon) {
        final int padding = 0;
        int x = 0;
        try {
            Font fnorm;
            Font fsmall;
            Font fxsmall;
            FontMetrics fmnorm;
            FontMetrics fmsmall;
            FontMetrics fmxsmall;
            if (troncon.isMep) {
                fnorm = this.fNormI;
                fsmall = this.fSmallI;
                fxsmall = this.fXsmallI;
                fmnorm = this.fmNormI;
                fmsmall = this.fmSmallI;
                fmxsmall = this.fmXsmallI;
            }
            else {
                fnorm = this.fNorm;
                fsmall = this.fSmall;
                fxsmall = this.fXsmall;
                fmnorm = this.fmNorm;
                fmsmall = this.fmSmall;
                fmxsmall = this.fmXsmall;
            }
            final SimpleDateFormat hourFormat = new SimpleDateFormat("HH'h'mm");
            final BoundingBox dep = new BoundingBox(new Point(x, 0), new Dimension(50, this.hLigne));
            x += 50;
            parent.add(dep);
            if (ChopeCrew.options.idxTimeRef == 0) {
                hourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            }
            else if (ChopeCrew.options.idxTimeRef == 1) {
                final SimpleTimeZone tzFrom = new SimpleTimeZone(troncon.LAGfromMillis, "");
                hourFormat.setTimeZone(tzFrom);
            }
            String string = hourFormat.format(troncon.debutUTCD);
            g.setFont(fnorm);
            g.setColor(Color.BLACK);
            final BoundingBox cdep = dep.getStringBounds(string, 3, 0, fmnorm, padding);
            cdep.drawWrappedString(g, fmnorm, padding, 3);
            final BoundingBox tiret1 = new BoundingBox(new Point(x, 0), new Dimension(10, this.hLigne));
            x += 10;
            parent.add(tiret1);
            string = "-";
            g.setFont(fsmall);
            g.setColor(Color.BLACK);
            final BoundingBox ctiret1 = tiret1.getStringBounds(string, 3, 0, fmsmall, padding);
            ctiret1.drawWrappedString(g, fmsmall, padding, 3);
            final BoundingBox arr = new BoundingBox(new Point(x, 0), new Dimension(50, this.hLigne));
            x += 50;
            parent.add(arr);
            if (ChopeCrew.options.idxTimeRef == 0) {
                hourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
            }
            else if (ChopeCrew.options.idxTimeRef == 1) {
                final SimpleTimeZone tzTo = new SimpleTimeZone(troncon.LAGtoMillis, "");
                hourFormat.setTimeZone(tzTo);
            }
            string = hourFormat.format(troncon.finUTCD);
            g.setFont(fnorm);
            g.setColor(Color.BLACK);
            final BoundingBox carr = arr.getStringBounds(string, 3, 0, fmnorm, padding);
            carr.drawWrappedString(g, fmnorm, padding, 3);
            if (ChopeCrew.options.webTempsVol) {
                final BoundingBox duree = new BoundingBox(new Point(x, 0), new Dimension(44, this.hLigne));
                x += 44;
                parent.add(duree);
                string = "(" + troncon.TVT + ")";
                g.setFont(fxsmall);
                g.setColor(Color.BLACK);
                final BoundingBox cduree = duree.getStringBounds(string, 3, 0, fmxsmall, padding);
                cduree.drawWrappedString(g, fmxsmall, padding, 3);
            }
            final BoundingBox mep = new BoundingBox(new Point(x, 0), new Dimension(20, this.hLigne));
            x += 20;
            parent.add(mep);
            if (troncon.isMep) {
                string = "X";
            }
            else {
                string = "";
            }
            g.setFont(fxsmall);
            g.setColor(Color.BLACK);
            final BoundingBox cmep = mep.getStringBounds(string, 3, 0, fmxsmall, padding);
            cmep.drawWrappedString(g, fmxsmall, padding, 3);
            if (ChopeCrew.options.webNumVol) {
                final BoundingBox numVol = new BoundingBox(new Point(x, 0), new Dimension(44, this.hLigne));
                x += 44;
                parent.add(numVol);
                string = troncon.numVol;
                g.setFont(fxsmall);
                g.setColor(Color.BLACK);
                final BoundingBox cnumVol = numVol.getStringBounds(string, 3, 0, fmxsmall, padding);
                cnumVol.drawWrappedString(g, fmxsmall, padding, 3);
            }
            final BoundingBox from = new BoundingBox(new Point(x, 0), new Dimension(96, this.hLigne));
            x += 96;
            parent.add(from);
            string = troncon.fromClair;
            g.setFont(fnorm);
            g.setColor(Color.BLACK);
            final BoundingBox cfrom = from.getStringBounds(string, 3, 0, fmnorm, padding);
            cfrom.drawWrappedString(g, fmnorm, padding, 3);
            final BoundingBox tiret2 = new BoundingBox(new Point(x, 0), new Dimension(10, this.hLigne));
            x += 10;
            parent.add(tiret2);
            string = "-";
            g.setFont(fsmall);
            g.setColor(Color.BLACK);
            final BoundingBox ctiret2 = tiret2.getStringBounds(string, 3, 0, fmsmall, padding);
            ctiret2.drawWrappedString(g, fmsmall, padding, 3);
            final BoundingBox to = new BoundingBox(new Point(x, 0), new Dimension(96, this.hLigne));
            x += 96;
            parent.add(to);
            string = troncon.toClair;
            g.setFont(fnorm);
            g.setColor(Color.BLACK);
            final BoundingBox cto = to.getStringBounds(string, 3, 0, fmnorm, padding);
            cto.drawWrappedString(g, fmnorm, padding, 3);
            if (ChopeCrew.options.webTempsEscale) {
                final BoundingBox tescale = new BoundingBox(new Point(x, 0), new Dimension(35, this.hLigne));
                x += 35;
                parent.add(tescale);
                string = "";
                if (troncon.tempsEscale != null) {
                    string = troncon.tempsEscale;
                }
                g.setFont(fxsmall);
                g.setColor(Color.BLACK);
                final BoundingBox ctescale = tescale.getStringBounds(string, 3, 0, fmxsmall, padding);
                ctescale.drawWrappedString(g, fmxsmall, padding, 3);
            }
            if (ChopeCrew.options.webPresta) {
                final BoundingBox presta = new BoundingBox(new Point(x, 0), new Dimension(45, this.hLigne));
                x += 45;
                parent.add(presta);
                string = "";
                if (troncon.listPresta.size() > 0) {
                    for (final Presta p : troncon.listPresta) {
                        if (p.type.contains("Snack Hot")) {
                            string = String.valueOf(string) + "SH ";
                        }
                        else if (p.type.contains("Encas Day")) {
                            string = String.valueOf(string) + "ED ";
                        }
                        else if (p.type.contains("Breakfast Hot")) {
                            string = String.valueOf(string) + "BH ";
                        }
                        else if (p.type.contains("Breakfast Fresh")) {
                            string = String.valueOf(string) + "BF ";
                        }
                        else {
                            if (!p.type.contains("Encas Morning")) {
                                continue;
                            }
                            string = String.valueOf(string) + "EM ";
                        }
                    }
                    string = string.trim();
                }
                g.setFont(fxsmall);
                g.setColor(Color.BLACK);
                final BoundingBox cpresta = presta.getStringBounds(string, 3, 0, fmxsmall, padding);
                cpresta.drawWrappedString(g, fmxsmall, padding, 3);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void drawActiviteSol(final Graphics g, final BoundingBox parent, final ActiviteSol act) {
        final int padding = 0;
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final SimpleDateFormat hourFormat = new SimpleDateFormat("HH'h'mm");
        hourFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        final int hAct = this.getActiviteSolSize(act);
        final int rowspan = hAct / this.hLigne;
        try {
            cal.setTime(act.debutUTCD);
            String string = dateFormat.format(cal.getTime());
            BoundingBox boxDate;
            if (string.equals(this.memoStringBoxDate) && parent.getAbsoluteLocation().y >= this.memoHBoxDate) {
                boxDate = new BoundingBox(new Point(0, -1 * this.memoHBoxDate), new Dimension(this.lDate, this.memoHBoxDate + this.hLigne));
                if (ChopeCrew.options.isPdfPersoLaurent) {
                    final BoundingBox boxPerso = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, -1 * this.memoHBoxDate), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, this.memoHBoxDate + this.hLigne));
                    parent.add(boxPerso);
                    this.fillBox(g, boxPerso, Color.WHITE);
                    this.drawBox(g, boxPerso, Color.GRAY);
                }
                this.memoHBoxDate += this.hLigne;
                this.memoStringBoxDate = string;
            }
            else {
                boxDate = new BoundingBox(new Point(0, 0), new Dimension(this.lDate, this.hLigne));
                if (ChopeCrew.options.isPdfPersoLaurent) {
                    final BoundingBox boxPerso = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, 0), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, this.hLigne));
                    parent.add(boxPerso);
                    this.fillBox(g, boxPerso, Color.WHITE);
                    this.drawBox(g, boxPerso, Color.GRAY);
                }
                this.memoHBoxDate = this.hLigne;
                this.memoStringBoxDate = string;
            }
            parent.add(boxDate);
            if (cal.get(7) == 1 || cal.get(7) == 7) {
                this.fillBox(g, boxDate, this.jourWeColor);
            }
            else {
                this.fillBox(g, boxDate, this.jourSemaineColor);
            }
            this.drawBox(g, boxDate, Color.GRAY);
            g.setFont(this.fSmall);
            g.setColor(Color.BLACK);
            final BoundingBox cBoxDate = boxDate.getStringBounds(string, 3, 0, this.fmSmall, padding);
            cBoxDate.drawWrappedString(g, this.fmSmall, padding, 3);
            if (act.isH24) {
                if (act.code.equals("DSP") && act.isBlocActivite) {
                    if (act.bloc.code.equals("MBR")) {
                        string = "Dispersion (Bloc r\u00e9serve)";
                    }
                    else if (act.bloc.code.equals("SST")) {
                        string = "Dispersion (Bloc stage)";
                    }
                    else {
                        string = "Dispersion (Bloc activit\u00e9s)";
                    }
                }
                else if (!act.equals(null) && !act.label.equals("")) {
                    string = act.label;
                }
                else {
                    string = act.categorie;
                }
                final BoundingBox boxAct = new BoundingBox(new Point(this.lDate, 0), new Dimension(this.lInfo, hAct));
                parent.add(boxAct);
                g.setFont(this.fSmall);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxAct = boxAct.getStringBounds(string, 3, 0, this.fmSmall, padding);
                cBoxAct.drawWrappedString(g, this.fmSmall, padding, 3);
            }
            else if (!act.isH24) {
                string = hourFormat.format(act.debutUTCD);
                final BoundingBox boxDep = new BoundingBox(new Point(this.lDate, 0), new Dimension(50, hAct));
                parent.add(boxDep);
                g.setFont(this.fNorm);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxDep = boxDep.getStringBounds(string, 3, 0, this.fmNorm, padding);
                cBoxDep.drawWrappedString(g, this.fmNorm, padding, 3);
                string = "-";
                final BoundingBox boxTiret = new BoundingBox(new Point(this.lDate + 50, 0), new Dimension(10, hAct));
                parent.add(boxTiret);
                g.setFont(this.fSmall);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxTiret = boxTiret.getStringBounds(string, 3, 0, this.fmSmall, padding);
                cBoxTiret.drawWrappedString(g, this.fmSmall, padding, 3);
                string = hourFormat.format(act.finUTCD);
                final BoundingBox boxArr = new BoundingBox(new Point(this.lDate + 50 + 10, 0), new Dimension(50, hAct));
                parent.add(boxArr);
                g.setFont(this.fNorm);
                g.setColor(Color.BLACK);
                final BoundingBox cBoxArr = boxArr.getStringBounds(string, 3, 0, this.fmNorm, padding);
                cBoxArr.drawWrappedString(g, this.fmNorm, padding, 3);
                string = act.label;
                final BoundingBox boxVirtuelle = new BoundingBox(new Point(this.lDate, 0), new Dimension(this.lInfo, hAct));
                final BoundingBox cBoxVirtuelle = boxVirtuelle.getStringBounds(string, 3, 0, this.fmNorm, padding);
                final int w = (int)cBoxVirtuelle.getSize().getWidth();
                final int normalWidth = this.lInfo - 220;
                if (w <= normalWidth) {
                    final BoundingBox boxlabel = new BoundingBox(new Point(this.lDate + 50 + 10 + 50, 0), new Dimension(normalWidth, hAct));
                    parent.add(boxlabel);
                    g.setFont(this.fNorm);
                    g.setColor(Color.BLACK);
                    final BoundingBox cBoxInt = boxlabel.getStringBounds(string, 3, 0, this.fmNorm, padding);
                    cBoxInt.drawWrappedString(g, this.fmNorm, padding, 3);
                    string = "";
                    final BoundingBox boxVide = new BoundingBox(new Point(this.lDate + this.lInfo - 110, 0), new Dimension(110, hAct));
                    parent.add(boxVide);
                    g.setFont(this.fNorm);
                    g.setColor(Color.BLACK);
                    final BoundingBox cBoxVide = boxVide.getStringBounds(string, 3, 0, this.fmNorm, padding);
                    cBoxVide.drawWrappedString(g, this.fmNorm, padding, 3);
                }
                else {
                    final BoundingBox boxlabel = new BoundingBox(new Point(this.lDate + 50 + 10 + 50, 0), new Dimension(w, hAct));
                    parent.add(boxlabel);
                    g.setFont(this.fNorm);
                    g.setColor(Color.BLACK);
                    final BoundingBox cBoxInt = boxlabel.getStringBounds(string, 3, 0, this.fmNorm, padding);
                    cBoxInt.drawWrappedString(g, this.fmNorm, padding, 3);
                    string = "";
                    final BoundingBox boxVide = new BoundingBox(new Point(this.lDate + 110 + w, 0), new Dimension(this.lInfo - 110 - w, hAct));
                    parent.add(boxVide);
                    g.setFont(this.fNorm);
                    g.setColor(Color.BLACK);
                    final BoundingBox cBoxVide = boxVide.getStringBounds(string, 3, 0, this.fmNorm, padding);
                    cBoxVide.drawWrappedString(g, this.fmNorm, padding, 3);
                }
                for (int i = 2; i <= rowspan; ++i) {
                    cal.add(5, 1);
                    string = dateFormat.format(cal.getTime());
                    BoundingBox boxNextDate;
                    if (string.equals(this.memoStringBoxDate) && parent.getAbsoluteLocation().y >= this.memoHBoxDate) {
                        boxNextDate = new BoundingBox(new Point(0, -1 * this.memoHBoxDate), new Dimension(this.lDate, this.memoHBoxDate + this.hLigne));
                        if (ChopeCrew.options.isPdfPersoLaurent) {
                            final BoundingBox boxPerso2 = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, -1 * this.memoHBoxDate), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, this.memoHBoxDate + this.hLigne));
                            parent.add(boxPerso2);
                            this.fillBox(g, boxPerso2, Color.WHITE);
                            this.drawBox(g, boxPerso2, Color.GRAY);
                        }
                        this.memoHBoxDate += this.hLigne;
                        this.memoStringBoxDate = string;
                    }
                    else {
                        boxNextDate = new BoundingBox(new Point(0, (i - 1) * this.hLigne), new Dimension(this.lDate, this.hLigne));
                        if (ChopeCrew.options.isPdfPersoLaurent) {
                            final BoundingBox boxPerso2 = new BoundingBox(new Point(this.lDate + this.lInfo + this.lTypeAvion + this.lTVSV + this.lTA, (i - 1) * this.hLigne), new Dimension(842 - 2 * this.margeMiniGD - this.lDate - this.lInfo - this.lTypeAvion - this.lTVSV - this.lTA, this.hLigne));
                            parent.add(boxPerso2);
                            this.fillBox(g, boxPerso2, Color.WHITE);
                            this.drawBox(g, boxPerso2, Color.GRAY);
                        }
                        this.memoHBoxDate = this.hLigne;
                        this.memoStringBoxDate = string;
                    }
                    parent.add(boxNextDate);
                    if (cal.get(7) == 1 || cal.get(7) == 7) {
                        this.fillBox(g, boxNextDate, this.jourWeColor);
                    }
                    else {
                        this.fillBox(g, boxNextDate, this.jourSemaineColor);
                    }
                    this.drawBox(g, boxNextDate, Color.GRAY);
                    g.setFont(this.fSmall);
                    g.setColor(Color.BLACK);
                    final BoundingBox cBoxNextDate = boxNextDate.getStringBounds(string, 3, 0, this.fmSmall, padding);
                    cBoxNextDate.drawWrappedString(g, this.fmSmall, padding, 3);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void drawBox(final Graphics g, final BoundingBox box, final Color color) {
        final Color couleurOrigine = g.getColor();
        g.setColor(color);
        g.drawRect((int)box.getAbsoluteLocation().getX(), (int)box.getAbsoluteLocation().getY(), (int)box.getSize().getWidth(), (int)box.getSize().getHeight());
        g.setColor(couleurOrigine);
    }
    
    private void fillBox(final Graphics g, final BoundingBox box, final Color color) {
        final Color couleurOrigine = g.getColor();
        g.setColor(color);
        g.fillRect((int)box.getAbsoluteLocation().getX(), (int)box.getAbsoluteLocation().getY(), (int)box.getSize().getWidth(), (int)box.getSize().getHeight());
        g.setColor(couleurOrigine);
    }
}
