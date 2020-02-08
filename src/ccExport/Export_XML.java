// 
// Decompiled by Procyon v0.5.36
// 

package ccExport;

import java.util.Iterator;
import ccStructures.Participant;
import ccStructures.ActiviteSol;
import ccStructures.Deg_Reco;
import ccStructures.Dest_Reco;
import ccStructures.Presta;
import ccUtils.Utils;
import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.Peq;
import ccStructures.Rotation;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import ccEngine.AnalyseCrew;

public class Export_XML
{
    private String xml;
    
    public void createXML(final AnalyseCrew analyse) {
        final String nl = System.getProperty("line.separator");
        final StringBuilder sb = new StringBuilder();
        final GregorianCalendar calUtc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calParis1 = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calParis2 = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final SimpleDateFormat sdf1 = new SimpleDateFormat("HH'h'mm");
        final SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM");
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(nl);
        sb.append("<planning");
        sb.append(" mois = \"").append(analyse.moisNum).append("/").append(analyse.anneeInt).append("\"");
        sb.append(" isPnt = \"").append(analyse.isPNT).append("\"");
        sb.append(" deltaMois = \"").append(analyse.deltaMois).append("\"");
        sb.append(" isFlash = \"").append(analyse.isFlash).append("\"");
        if (analyse.matricule != null) {
            sb.append(" matricule = \"").append(analyse.matricule).append("\"");
        }
        sb.append(">").append(nl);
        for (final Object obj : analyse.listCrew) {
            if (obj instanceof Rotation) {
                final Rotation rotation = (Rotation)obj;
                sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
                sdf2.setTimeZone(TimeZone.getTimeZone("UTC"));
                sb.append("\t").append("<rotation>").append(nl);
                if (rotation.label != null) {
                    sb.append("\t\t").append("<rotationId>").append(rotation.label.replace("&", "&amp;")).append("</rotationId>").append(nl);
                }
                if (rotation.isOD) {
                    sb.append("\t\t").append("<isOd>").append(rotation.isOD).append("</isOd>").append(nl);
                }
                if (rotation.particularite != null) {
                    sb.append("\t\t").append("<particularite>").append(rotation.particularite.replace("&", "&amp;")).append("</particularite>").append(nl);
                }
                if (rotation.sab != null) {
                    sb.append("\t\t").append("<sab>").append(rotation.sab.replace("&", "&amp;")).append("</sab>").append(nl);
                }
                if (rotation.dureePac != null) {
                    sb.append("\t\t").append("<dureePac>").append(rotation.dureePac).append("</dureePac>").append(nl);
                }
                if (rotation.dureeRpc != null) {
                    sb.append("\t\t").append("<dureeRpc>").append(rotation.dureeRpc).append("</dureeRpc>").append(nl);
                }
                if (rotation.nbCDB != null) {
                    sb.append("\t\t").append("<nbCdb>").append(rotation.nbCDB).append("</nbCdb>").append(nl);
                }
                if (rotation.nbOPL != null) {
                    sb.append("\t\t").append("<nbOpl>").append(rotation.nbOPL).append("</nbOpl>").append(nl);
                }
                if (rotation.listPeqRot.size() > 0) {
                    sb.append("\t\t").append("<listPeqRot>").append(nl);
                    for (final Peq p : rotation.listPeqRot) {
                        sb.append("\t\t\t").append("<peqRot>").append(nl);
                        if (p.nom != null) {
                            sb.append("\t\t\t\t").append("<nom>").append(p.nom).append("</nom>").append(nl);
                        }
                        if (p.prenom != null) {
                            sb.append("\t\t\t\t").append("<prenom>").append(p.prenom).append("</prenom>").append(nl);
                        }
                        if (p.fab != null) {
                            sb.append("\t\t\t\t").append("<fab>").append(p.fab.replace("&", "&amp;")).append("</fab>").append(nl);
                        }
                        sb.append("\t\t\t").append("</peqRot>").append(nl);
                    }
                    sb.append("\t\t").append("</listPeqRot>").append(nl);
                }
                for (final ServiceVol sv : rotation.listSV) {
                    sb.append("\t\t").append("<sv>").append(nl);
                    final Troncon firstTro = sv.listTroncon.get(0);
                    sb.append("\t\t\t").append("<date>").append(sdf2.format(firstTro.debutUTCD)).append("</date>").append(nl);
                    for (final Troncon troncon : sv.listTroncon) {
                        sb.append("\t\t\t").append("<vol>").append(nl);
                        if (troncon.numVol != null) {
                            sb.append("\t\t\t\t").append("<numVol>").append(troncon.numVol).append("</numVol>").append(nl);
                        }
                        sb.append("\t\t\t\t").append("<from>").append(troncon.from).append("</from>").append(nl);
                        sb.append("\t\t\t\t").append("<to>").append(troncon.to).append("</to>").append(nl);
                        sb.append("\t\t\t\t").append("<dep>").append(sdf1.format(troncon.debutUTCD)).append("</dep>").append(nl);
                        sb.append("\t\t\t\t").append("<arr>").append(sdf1.format(troncon.finUTCD)).append("</arr>").append(nl);
                        if (troncon.isMep) {
                            sb.append("\t\t\t\t").append("<isMep>").append(troncon.isMep).append("</isMep>").append(nl);
                        }
                        if (troncon.lagFrom != null) {
                            sb.append("\t\t\t\t").append("<lagFrom>").append(troncon.lagFrom).append("</lagFrom>").append(nl);
                        }
                        if (troncon.lagTo != null) {
                            sb.append("\t\t\t\t").append("<lagTo>").append(troncon.lagTo).append("</lagTo>").append(nl);
                        }
                        if (troncon.typeAvion != null) {
                            sb.append("\t\t\t\t").append("<type>").append(troncon.typeAvion).append("</type>").append(nl);
                        }
                        if (troncon.versionExploitation != null) {
                            sb.append("\t\t\t\t").append("<version>").append(troncon.versionExploitation.replace("&", "&amp;")).append("</version>").append(nl);
                        }
                        if (troncon.hotel != null) {
                            sb.append("\t\t\t\t").append("<hotel>").append(troncon.hotel.replace("&", "&amp;")).append("</hotel>").append(nl);
                        }
                        if (troncon.isTVreal) {
                            calUtc.setTimeInMillis(troncon.DEPr);
                            final String out = sdf1.format(calUtc.getTime());
                            String tv;
                            if (!troncon.isMep) {
                                tv = Utils.timetoString(troncon.TVr);
                            }
                            else {
                                tv = Utils.timetoString(troncon.MEPr);
                            }
                            sb.append("\t\t\t\t").append("<out>").append(out).append("</out>").append(nl);
                            sb.append("\t\t\t\t").append("<tv>").append(tv).append("</tv>").append(nl);
                        }
                        if (troncon.listPeqTroncon.size() > 0) {
                            sb.append("\t\t\t\t").append("<listPeq>").append(nl);
                            for (final Peq p2 : troncon.listPeqTroncon) {
                                sb.append("\t\t\t\t\t").append("<peq>").append(nl);
                                if (p2.nom != null) {
                                    sb.append("\t\t\t\t\t\t").append("<nom>").append(p2.nom).append("</nom>").append(nl);
                                }
                                if (p2.prenom != null) {
                                    sb.append("\t\t\t\t\t\t").append("<prenom>").append(p2.prenom).append("</prenom>").append(nl);
                                }
                                if (p2.fab != null) {
                                    sb.append("\t\t\t\t\t\t").append("<fab>").append(p2.fab.replace("&", "&amp;")).append("</fab>").append(nl);
                                }
                                sb.append("\t\t\t\t\t").append("</peq>").append(nl);
                            }
                            sb.append("\t\t\t\t").append("</listPeq>").append(nl);
                        }
                        if (troncon.indemFrom.escale != null) {
                            sb.append("\t\t\t\t").append("<indemFrom>").append(nl);
                            if (troncon.indemFrom.monnaieLoc != null) {
                                sb.append("\t\t\t\t\t").append("<monnaie>").append(troncon.indemFrom.monnaieLoc).append("</monnaie>").append(nl);
                            }
                            if (troncon.indemFrom.change != null) {
                                sb.append("\t\t\t\t\t").append("<change>").append(troncon.indemFrom.change).append("</change>").append(nl);
                            }
                            if (troncon.indemFrom.irLoc != null) {
                                sb.append("\t\t\t\t\t").append("<ir>").append(troncon.indemFrom.irLoc).append("</ir>").append(nl);
                            }
                            if (troncon.indemFrom.mfLoc != null) {
                                sb.append("\t\t\t\t\t").append("<mf>").append(troncon.indemFrom.mfLoc).append("</mf>").append(nl);
                            }
                            if (troncon.indemFrom.dateEffet != null) {
                                sb.append("\t\t\t\t\t").append("<date>").append(troncon.indemFrom.dateEffet).append("</date>").append(nl);
                            }
                            sb.append("\t\t\t\t").append("</indemFrom>").append(nl);
                        }
                        if (troncon.indemTo.escale != null) {
                            sb.append("\t\t\t\t").append("<indemTo>").append(nl);
                            if (troncon.indemTo.monnaieLoc != null) {
                                sb.append("\t\t\t\t\t").append("<monnaie>").append(troncon.indemTo.monnaieLoc).append("</monnaie>").append(nl);
                            }
                            if (troncon.indemTo.change != null) {
                                sb.append("\t\t\t\t\t").append("<change>").append(troncon.indemTo.change).append("</change>").append(nl);
                            }
                            if (troncon.indemTo.irLoc != null) {
                                sb.append("\t\t\t\t\t").append("<ir>").append(troncon.indemTo.irLoc).append("</ir>").append(nl);
                            }
                            if (troncon.indemTo.mfLoc != null) {
                                sb.append("\t\t\t\t\t").append("<mf>").append(troncon.indemTo.mfLoc).append("</mf>").append(nl);
                            }
                            if (troncon.indemTo.dateEffet != null) {
                                sb.append("\t\t\t\t\t").append("<date>").append(troncon.indemTo.dateEffet).append("</date>").append(nl);
                            }
                            sb.append("\t\t\t\t").append("</indemTo>").append(nl);
                        }
                        if (troncon.listPresta.size() > 0) {
                            sb.append("\t\t\t\t").append("<listPresta>").append(nl);
                            for (final Presta p3 : troncon.listPresta) {
                                if (p3.type != null) {
                                    sb.append("\t\t\t\t\t").append("<presta>").append(p3.type.replace("&", "&amp;")).append("</presta>").append(nl);
                                }
                            }
                            sb.append("\t\t\t\t").append("</listPresta>").append(nl);
                        }
                        if (troncon.listRecoDest.size() > 0 && troncon.listRecoDest.get(0).categorie != null) {
                            sb.append("\t\t\t\t").append("<recoDest>").append(troncon.listRecoDest.get(0).categorie).append("</recoDest>").append(nl);
                        }
                        if (troncon.listRecoDeg.size() > 0) {
                            sb.append("\t\t\t\t").append("<listRecoDeg>").append(nl);
                            for (final Deg_Reco deg : troncon.listRecoDeg) {
                                sb.append("\t\t\t\t\t").append("<recoDeg>").append(nl);
                                if (deg.deg != null) {
                                    sb.append("\t\t\t\t\t\t").append("<deg>").append(deg.deg).append("</deg>").append(nl);
                                }
                                if (deg.categorie != null) {
                                    sb.append("\t\t\t\t\t\t").append("<categorie>").append(deg.categorie).append("</categorie>").append(nl);
                                }
                                sb.append("\t\t\t\t\t").append("</recoDeg>").append(nl);
                            }
                            sb.append("\t\t\t\t").append("</listRecoDeg>").append(nl);
                        }
                        sb.append("\t\t\t").append("</vol>").append(nl);
                    }
                    sb.append("\t\t").append("</sv>").append(nl);
                }
                sb.append("\t").append("</rotation>").append(nl);
            }
            else {
                if (!(obj instanceof ActiviteSol)) {
                    continue;
                }
                final ActiviteSol act = (ActiviteSol)obj;
                sdf1.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                sdf2.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                calParis1.setTime(act.debutUTCD);
                calParis2.setTime(act.finUTCD);
                calParis1.set(11, 0);
                calParis1.set(12, 0);
                calParis1.set(13, 0);
                calParis1.set(14, 0);
                calParis2.set(11, 0);
                calParis2.set(12, 0);
                calParis2.set(13, 0);
                calParis2.set(14, 0);
                calParis2.add(5, 1);
                final int deltaOffset = calParis2.get(15) + calParis2.get(16) - calParis1.get(15) - calParis1.get(16);
                final int nbjours = (int)(calParis2.getTimeInMillis() - calParis1.getTimeInMillis() + deltaOffset) / 86400000;
                sb.append("\t").append("<sol>").append(nl);
                sb.append("\t\t").append("<date>").append(sdf2.format(act.debutUTCD)).append("</date>").append(nl);
                sb.append("\t\t").append("<debut>").append(sdf1.format(act.debutUTCD)).append("</debut>").append(nl);
                if (nbjours > 2) {
                    sb.append("\t\t").append("<fin>").append(sdf1.format(act.finUTCD)).append("/+").append(String.valueOf(nbjours - 1)).append("</fin>").append(nl);
                }
                else {
                    sb.append("\t\t").append("<fin>").append(sdf1.format(act.finUTCD)).append("</fin>").append(nl);
                }
                sb.append("\t\t").append("<code>").append(act.code.replace("&", "&amp;")).append("</code>").append(nl);
                if (act.isHCreal) {
                    sb.append("\t\t").append("<hc>").append(act.HCS).append("</hc>").append(nl);
                    sb.append("\t\t").append("<hcr>").append(act.HCRS).append("</hcr>").append(nl);
                }
                if (act.isIKVreal) {
                    sb.append("\t\t").append("<ikv>").append(act.IKVar).append("</ikv>").append(nl);
                }
                if (act.label != null) {
                    sb.append("\t\t").append("<intitule>").append(act.label.replace("&", "&amp;")).append("</intitule>").append(nl);
                }
                if (act.maj != null) {
                    sb.append("\t\t").append("<maj>").append(act.maj.replace("&", "&amp;")).append("</maj>").append(nl);
                }
                if (act.lieu != null) {
                    sb.append("\t\t").append("<lieu>").append(act.lieu.replace("&", "&amp;")).append("</lieu>").append(nl);
                }
                if (act.salle != null) {
                    sb.append("\t\t").append("<salle>").append(act.salle.replace("&", "&amp;")).append("</salle>").append(nl);
                }
                if (act.commentaire != null) {
                    sb.append("\t\t").append("<commentaire>").append(act.commentaire.replace("&", "&amp;")).append("</commentaire>").append(nl);
                }
                if (act.listParticipant.size() > 0) {
                    sb.append("\t\t").append("<listParticipant>").append(nl);
                    for (final Participant p4 : act.listParticipant) {
                        sb.append("\t\t\t").append("<participant>").append(nl);
                        if (p4.nom != null) {
                            sb.append("\t\t\t\t").append("<nom>").append(p4.nom).append("</nom>").append(nl);
                        }
                        if (p4.prenom != null) {
                            sb.append("\t\t\t\t").append("<prenom>").append(p4.prenom).append("</prenom>").append(nl);
                        }
                        if (p4.fab != null) {
                            sb.append("\t\t\t\t").append("<fab>").append(p4.fab).append("</fab>").append(nl);
                        }
                        if (p4.matricule != null) {
                            sb.append("\t\t\t\t").append("<matricule>").append(p4.matricule).append("</matricule>").append(nl);
                        }
                        if (p4.affectation != null) {
                            sb.append("\t\t\t\t").append("<affectation>").append(p4.affectation).append("</affectation>").append(nl);
                        }
                        if (p4.statut != null) {
                            sb.append("\t\t\t\t").append("<statut>").append(p4.statut.replace("&", "&amp;")).append("</statut>").append(nl);
                        }
                        sb.append("\t\t\t").append("</participant>").append(nl);
                    }
                    sb.append("\t\t").append("</listParticipant>").append(nl);
                }
                if (act.isBlocActivite) {
                    sb.append("\t\t").append("<bloc>").append(nl);
                    sb.append("\t\t\t").append("<debut>").append(act.bloc.debut).append("</debut>").append(nl);
                    sb.append("\t\t\t").append("<fin>").append(act.bloc.fin).append("</fin>").append(nl);
                    sb.append("\t\t\t").append("<intitule>").append(act.bloc.label.replace("&", "&amp;")).append("</intitule>").append(nl);
                    sb.append("\t\t\t").append("<code>").append(act.bloc.code.replace("&", "&amp;")).append("</code>").append(nl);
                    sb.append("\t\t").append("</bloc>").append(nl);
                }
                sb.append("\t").append("</sol>").append(nl);
            }
        }
        sb.append("</planning>");
        this.xml = sb.toString();
    }
    
    public String getXml() {
        return this.xml;
    }
}
