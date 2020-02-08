// 
// Decompiled by Procyon v0.5.36
// 

package ccEngine;

import ccStructures.Troncon;
import ccStructures.ServiceVol;
import ccStructures.ActiviteSol;
import java.util.Iterator;
import ccStructures.Rotation;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;
import chopeCrew.ChopeCrew;
import ccUtils.Utils;
import ccOptions.DonneesPerso_PNT;

public class EP4_Pnt
{
    public double fixe;
    public double pv;
    public double pfonction;
    public double ikv;
    public double congePv;
    public double congeHs;
    public double congeAs;
    public boolean abattementSecu;
    public boolean isResidentEtranger;
    public double kmCdg;
    public double kmOry;
    public double kmBase;
    public double cBF;
    public double cIKV;
    public double cIRimp;
    public double cIRnimp;
    public double kpvhc;
    public double kpvhcNuit;
    public double mga;
    public boolean isInsMC;
    public boolean isCdb;
    public int nJEvol;
    public int nJEsol;
    public int nDispersions;
    public int nLeversTot;
    public int nConges;
    public int nMaladies;
    public int nSansSolde;
    public double volSTVp;
    public double volSTVr;
    public double volHCp;
    public double volHCr;
    public double volHCRp;
    public double volHCRr;
    public double volHCNp;
    public double volHCNr;
    public double solHC;
    public double solHCR;
    public double seuilMGA;
    public double seuilHS;
    public double volHSp;
    public double volHSr;
    public double montantSolHCRS;
    public double montantVolHCRp;
    public double montantVolHCRr;
    public double montantVolHCNp;
    public double montantVolHCNr;
    public double montantVolHCSVNp;
    public double montantVolHCSVNr;
    public double montantVolPrimeCdbp;
    public double montantVolPrimeCdbr;
    public double montantVolHCMajo15p;
    public double montantVolHCMajo15r;
    public double montantVolIndemP;
    public double montantVolIndemR;
    public double montantVolIndemImposableP;
    public double montantVolIndemNonImposableP;
    public double montantVolIndemImposableR;
    public double montantVolIndemNonImposableR;
    public double montantIKVp;
    public double montantIKVr;
    public double montantDECp;
    public double montantDECr;
    public double tauxHSFixe;
    public double tauxHSVolp;
    public double tauxHSVolr;
    public double plafss;
    public double irfr;
    public double decfr;
    public double ssm;
    public double ssv;
    public double ssvc;
    public double ssme;
    public double ass;
    public double crpnr;
    public double crpna;
    public double crpnm;
    public double mnpaf;
    public double mnpafp;
    public double iad;
    public double iadp;
    public double pdt1;
    public double pdt1B;
    public double pdt2;
    public double pdt3;
    public double pdt4;
    public double pdt5;
    public double pdfp;
    public double pdt1p;
    public double pdt1Bp;
    public double pdt2p;
    public double pdt3p;
    public double pdt4p;
    public double pdt5p;
    public double plmta;
    public double plmtb;
    public double plmtc;
    public double plmtap;
    public double plmtbp;
    public double plmtcp;
    public double pclmp;
    public double art83t1p;
    public double art83t2p;
    public double art83vifp;
    public double csgd;
    public double csgnd;
    public double kcsg;
    public double kss;
    public double fppBaseFixe;
    public double fppFixe;
    public double fppBasePrimeFonction;
    public double fppPrimeFonction;
    public double fppPv;
    public double fppHsFixe;
    public double fppHsPv;
    public double fppBaseCongePv;
    public double fppCongePv;
    public double fppBaseCongeHs;
    public double fppCongeHs;
    public double fppBaseCongeAs;
    public double fppCongeAs;
    public double fprBaseFixe;
    public double fprFixe;
    public double fprBasePrimeFonction;
    public double fprPrimeFonction;
    public double fprPv;
    public double fprHsFixe;
    public double fprHsPv;
    public double fprBaseCongePv;
    public double fprCongePv;
    public double fprBaseCongeHs;
    public double fprCongeHs;
    public double fprBaseCongeAs;
    public double fprCongeAs;
    public double fppBaseMaladie;
    public double fppMaladie;
    public double fprBaseMaladie;
    public double fprMaladie;
    public double fppBaseMga;
    public double fppMga;
    public double fppCompMga;
    public double fprBaseMga;
    public double fprMga;
    public double fprCompMga;
    public double fppRepasImp;
    public double fppIkv;
    public double fprRepasImp;
    public double fprIkv;
    public double fppBrutFiscal;
    public double fprBrutFiscal;
    public double fppBaseSSM;
    public double fppBaseSSV;
    public double fppBaseSSVC;
    public double fppBaseASS;
    public double fprBaseSSM;
    public double fprBaseSSV;
    public double fprBaseSSVC;
    public double fprBaseASS;
    public double fppBaseCRPNr;
    public double fppBaseCRPNa;
    public double fppBaseCRPNm;
    public double fprBaseCRPNr;
    public double fprBaseCRPNa;
    public double fprBaseCRPNm;
    public double fppBaseMNPAF;
    public double fprBaseMNPAF;
    public double fppBasePIAD;
    public double fppBasePDT1;
    public double fppBasePDT1B;
    public double fppBasePDT2;
    public double fppBasePDT3;
    public double fppBasePDT4;
    public double fppBasePDT5;
    public double fprBasePIAD;
    public double fprBasePDT1;
    public double fprBasePDT1B;
    public double fprBasePDT2;
    public double fprBasePDT3;
    public double fprBasePDT4;
    public double fprBasePDT5;
    public double fppBasePLMTA;
    public double fppBasePLMTB;
    public double fppBasePLMTC;
    public double fppBasePCLM;
    public double fprBasePLMTA;
    public double fprBasePLMTB;
    public double fprBasePLMTC;
    public double fprBasePCLM;
    public double fppBaseART83T1;
    public double fppBaseART83T2;
    public double fppBaseART83VIF;
    public double fprBaseART83T1;
    public double fprBaseART83T2;
    public double fprBaseART83VIF;
    public double fppBaseCSGd;
    public double fppBaseCSGnd;
    public double fprBaseCSGd;
    public double fprBaseCSGnd;
    public double fppCotisSSM;
    public double fppCotisSSV;
    public double fppCotisSSVC;
    public double fppCotisASS;
    public double fprCotisSSM;
    public double fprCotisSSV;
    public double fprCotisSSVC;
    public double fprCotisASS;
    public double fppCotisCRPNr;
    public double fppCotisCRPNa;
    public double fppCotisCRPNm;
    public double fprCotisCRPNr;
    public double fprCotisCRPNa;
    public double fprCotisCRPNm;
    public double fppCotisMNPAF;
    public double fprCotisMNPAF;
    public double fppCotisPIAD;
    public double fppCotisPDT1;
    public double fppCotisPDT1B;
    public double fppCotisPDT2;
    public double fppCotisPDT3;
    public double fppCotisPDT4;
    public double fppCotisPDT5;
    public double fprCotisPIAD;
    public double fprCotisPDT1;
    public double fprCotisPDT1B;
    public double fprCotisPDT2;
    public double fprCotisPDT3;
    public double fprCotisPDT4;
    public double fprCotisPDT5;
    public double fppCotisPLMTA;
    public double fppCotisPLMTB;
    public double fppCotisPLMTC;
    public double fprCotisPLMTA;
    public double fprCotisPLMTB;
    public double fprCotisPLMTC;
    public double fppCotisArt83_1;
    public double fppCotisArt83_2;
    public double fppCotisArt83Vif;
    public double fprCotisArt83_1;
    public double fprCotisArt83_2;
    public double fprCotisArt83Vif;
    public double fppCotisCSGd;
    public double fppCotisCSGnd;
    public double fprCotisCSGd;
    public double fprCotisCSGnd;
    public double fppRepasNonImp;
    public double fprRepasNonImp;
    public double fppNetBulletin;
    public double fprNetBulletin;
    public double fppDEC;
    public double fprDEC;
    public double fppCotisEmploye;
    public double fprCotisEmploye;
    public double fppFraisPro;
    public double fprFraisPro;
    public double fppNetImposable;
    public double fprNetImposable;
    
    public void calculEP4(final int mois, final DonneesPerso_PNT dp) {
        this.chopeNJEvol(mois);
        this.chopeNJEsol(mois);
        this.chopeNDisp(mois);
        this.chopeNLeversTot(mois);
        this.chopeNConges(mois);
        this.chopeNMaladies(mois);
        this.chopeNSansSolde(mois);
        this.chopeVolSTVpr(mois);
        this.chopeSolHC(mois);
        this.chopeVolHCpr(mois);
        this.chopeVolHCNpr(mois);
        this.chopeSeuilMGA();
        this.chopeVolHS();
        this.fixe = dp.fixe_pnt;
        this.pv = dp.pv_pnt;
        this.pfonction = dp.pfonction_pnt;
        this.ikv = dp.ikv_pnt;
        this.congePv = dp.congePv_pnt;
        this.congeHs = dp.congeHs_pnt;
        this.congeAs = dp.congeAs_pnt;
        this.abattementSecu = dp.abattementSecu_pnt;
        this.isResidentEtranger = dp.isResidentEtranger;
        this.kmCdg = dp.kmCdg_pnt;
        this.kmOry = dp.kmOry_pnt;
        this.kmBase = dp.kmBase_pnt;
        this.cBF = dp.cBF_pnt;
        this.cIKV = dp.cIKV_pnt;
        this.cIRimp = dp.cIRimp_pnt;
        this.cIRnimp = dp.cIRnimp_pnt;
        if (dp.qualif_pnt == 0) {
            this.kpvhc = 1.13;
            this.kpvhcNuit = 1.0;
            this.mga = Utils.arrondi(80.0 * dp.pv_pnt, 2);
        }
        else if (dp.qualif_pnt == 1) {
            this.kpvhc = 1.13;
            this.kpvhcNuit = 1.0;
            this.mga = Utils.arrondi(80.0 * dp.pv_pnt, 2);
        }
        else {
            this.kpvhc = 1.07;
            this.kpvhcNuit = 1.07;
            this.mga = Utils.arrondi(80.0 * dp.pv_pnt * this.kpvhc, 2);
        }
        if ((dp.qualif_pnt == 0 || dp.qualif_pnt == 1) && (dp.fonction_pnt == 3 || dp.fonction_pnt == 2)) {
            this.isInsMC = true;
        }
        else {
            this.isInsMC = false;
        }
        if (dp.fonction_pnt == 1 || dp.fonction_pnt == 3) {
            this.isCdb = true;
        }
        else {
            this.isCdb = false;
        }
        this.loadTauxFromDB();
        this.chopeSolMontantHCRS(mois);
        this.chopeVolMontantHCRpr(mois);
        this.chopeVolMontantHCNpr(mois);
        this.chopeVolMontantHCSVNpr(mois);
        this.chopeVolMontantPrimeCpt(mois);
        this.chopeVolMontantHCMajo15pr(mois);
        this.chopeVolMontantIndemPR(mois);
        this.chopeMontantIKVpr(mois);
        this.chopeMontantDecPR(mois);
        this.chopeTauxHSFixe();
        this.chopeTauxHSVol();
        this.calculPayeP();
        this.calculPayeR();
    }
    
    private void loadTauxFromDB() {
        try {
            InputStream is;
            if (ChopeCrew.options.dbCotis_pnt.equals("")) {
                final String ficSource = "/res_databases/dbCOTIS_PNT.txt";
                is = this.getClass().getResourceAsStream(ficSource);
            }
            else {
                final String ficSource = ChopeCrew.options.dbCotis_pnt;
                is = new FileInputStream(ficSource);
            }
            final Properties props = new Properties();
            props.load(is);
            this.plafss = Double.parseDouble(props.getProperty("PLAFSS"));
            this.irfr = Double.parseDouble(props.getProperty("IRFR"));
            this.decfr = Double.parseDouble(props.getProperty("DECFR"));
            this.ssm = Double.parseDouble(props.getProperty("SSM"));
            this.ssv = Double.parseDouble(props.getProperty("SSV"));
            this.ssvc = Double.parseDouble(props.getProperty("SSVC"));
            this.ssme = Double.parseDouble(props.getProperty("SSME"));
            this.ass = Double.parseDouble(props.getProperty("ASS"));
            this.crpnr = Double.parseDouble(props.getProperty("CRPNR"));
            this.crpna = Double.parseDouble(props.getProperty("CRPNA"));
            this.crpnm = Double.parseDouble(props.getProperty("CRPNM"));
            this.mnpaf = Double.parseDouble(props.getProperty("MNPAF"));
            this.mnpafp = Double.parseDouble(props.getProperty("MNPAFp"));
            this.iad = Double.parseDouble(props.getProperty("IAD"));
            this.iadp = Double.parseDouble(props.getProperty("IADp"));
            this.pdt1 = Double.parseDouble(props.getProperty("PDT1"));
            this.pdt1B = Double.parseDouble(props.getProperty("PDT1B"));
            this.pdt2 = Double.parseDouble(props.getProperty("PDT2"));
            this.pdt3 = Double.parseDouble(props.getProperty("PDT3"));
            this.pdt4 = Double.parseDouble(props.getProperty("PDT4"));
            this.pdt5 = Double.parseDouble(props.getProperty("PDT5"));
            this.pdfp = Double.parseDouble(props.getProperty("PDFp"));
            this.pdt1p = Double.parseDouble(props.getProperty("PDT1p"));
            this.pdt1Bp = Double.parseDouble(props.getProperty("PDT1Bp"));
            this.pdt2p = Double.parseDouble(props.getProperty("PDT2p"));
            this.pdt3p = Double.parseDouble(props.getProperty("PDT3p"));
            this.pdt4p = Double.parseDouble(props.getProperty("PDT4p"));
            this.pdt5p = Double.parseDouble(props.getProperty("PDT5p"));
            this.plmta = Double.parseDouble(props.getProperty("PLMTA"));
            this.plmtb = Double.parseDouble(props.getProperty("PLMTB"));
            this.plmtc = Double.parseDouble(props.getProperty("PLMTC"));
            this.plmtap = Double.parseDouble(props.getProperty("PLMTAp"));
            this.plmtbp = Double.parseDouble(props.getProperty("PLMTBp"));
            this.plmtcp = Double.parseDouble(props.getProperty("PLMTCp"));
            this.pclmp = Double.parseDouble(props.getProperty("PCLMp"));
            this.art83t1p = Double.parseDouble(props.getProperty("ART83T<4Pp"));
            this.art83t2p = Double.parseDouble(props.getProperty("ART83T>4Pp"));
            this.art83vifp = Double.parseDouble(props.getProperty("ART83VIFp"));
            this.csgd = Double.parseDouble(props.getProperty("CSGD"));
            this.csgnd = Double.parseDouble(props.getProperty("CSGND"));
            this.kcsg = Double.parseDouble(props.getProperty("%CSG"));
            this.kss = Double.parseDouble(props.getProperty("%SS"));
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void chopeNJEvol(final int mois) {
        int njevol = 0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            caldeb.setTime(rotation.debutUTCD);
            final int moisDepart = caldeb.get(2) + 1;
            calfin.setTime(rotation.finUTCD);
            final int moisArrivee = calfin.get(2) + 1;
            caldeb.set(11, 0);
            caldeb.set(12, 0);
            caldeb.set(13, 0);
            caldeb.set(14, 0);
            calfin.set(11, 23);
            calfin.set(12, 59);
            calfin.set(13, 59);
            calfin.set(14, 1000);
            if (moisDepart != mois) {
                caldeb.add(2, 1);
                caldeb.set(5, 1);
            }
            if (moisArrivee != mois) {
                calfin.set(5, 1);
                calfin.set(11, 0);
                calfin.set(12, 0);
                calfin.set(13, 0);
                calfin.set(14, 0);
            }
            final long deltaOffset = calfin.get(15) + calfin.get(16) - caldeb.get(15) - caldeb.get(16);
            njevol += (int)Math.round((calfin.getTimeInMillis() - caldeb.getTimeInMillis() + deltaOffset) / 8.64E7);
        }
        this.nJEvol = njevol;
    }
    
    private void chopeNJEsol(final int mois) {
        int njesol = 0;
        String jour_ActiviteEngageantePrecedente = "";
        for (final ActiviteSol act : ChopeCrew.analyse.listSol) {
            if (act.jour.equals(jour_ActiviteEngageantePrecedente)) {
                continue;
            }
            if (!act.isCredite || (act.HCS == 0.0 && act.HCRS == 0.0)) {
                continue;
            }
            ++njesol;
            jour_ActiviteEngageantePrecedente = act.jour;
        }
        this.nJEsol = njesol;
    }
    
    private void chopeNDisp(final int mois) {
        int ndisp = 0;
        for (final ActiviteSol act : ChopeCrew.analyse.listSol) {
            if (act.code.equals("DSP") && act.HCS == 0.0 && act.HCRS == 0.0) {
                ++ndisp;
            }
        }
        this.nDispersions = ndisp;
    }
    
    private void chopeNLeversTot(final int mois) {
        int nleverstot = 0;
        final GregorianCalendar cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                cal.setTime(sv.listTroncon.get(0).debutUTCD);
                final int moisSV = cal.get(2) + 1;
                if (moisSV == mois) {
                    final int h = cal.get(11);
                    if (h > 8) {
                        continue;
                    }
                    ++nleverstot;
                }
            }
        }
        this.nLeversTot = nleverstot;
    }
    
    private void chopeNConges(final int mois) {
        int nconges = 0;
        for (final ActiviteSol act : ChopeCrew.analyse.listSol) {
            if (act.code.equals("MCA") || act.code.equals("MCE")) {
                ++nconges;
            }
        }
        this.nConges = nconges;
    }
    
    private void chopeNMaladies(final int mois) {
        int nmaladies = 0;
        for (final ActiviteSol act : ChopeCrew.analyse.listSol) {
            if (act.code.equals("MAS")) {
                ++nmaladies;
            }
        }
        this.nMaladies = nmaladies;
    }
    
    private void chopeNSansSolde(final int mois) {
        int nsanssolde = 0;
        for (final ActiviteSol act : ChopeCrew.analyse.listSol) {
            if (act.code.equals("MDV")) {
                ++nsanssolde;
            }
        }
        this.nSansSolde = nsanssolde;
    }
    
    private void chopeVolSTVpr(final int mois) {
        double volstvp = 0.0;
        double volstvr = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    caldeb.setTimeInMillis(troncon.DEPp);
                    calfin.setTimeInMillis(troncon.ARRp);
                    int moisdebtro = caldeb.get(2) + 1;
                    int moisfintro = calfin.get(2) + 1;
                    if (moisdebtro == mois && moisfintro == mois) {
                        volstvp += troncon.TVp;
                    }
                    else if (moisdebtro != mois && moisfintro == mois) {
                        volstvp += troncon.TVp2;
                    }
                    else if (moisdebtro == mois && moisfintro != mois) {
                        volstvp += troncon.TVp1;
                    }
                    caldeb.setTimeInMillis(troncon.DEPr);
                    calfin.setTimeInMillis(troncon.ARRr);
                    moisdebtro = caldeb.get(2) + 1;
                    moisfintro = calfin.get(2) + 1;
                    if (moisdebtro == mois && moisfintro == mois) {
                        volstvr += troncon.TVr;
                    }
                    else if (moisdebtro != mois && moisfintro == mois) {
                        volstvr += troncon.TVr2;
                    }
                    else {
                        if (moisdebtro != mois || moisfintro == mois) {
                            continue;
                        }
                        volstvr += troncon.TVr1;
                    }
                }
            }
        }
        this.volSTVp = Utils.arrondi(volstvp, 2);
        this.volSTVr = Utils.arrondi(volstvr, 2);
    }
    
    private void chopeSolHC(final int mois) {
        double solhc = 0.0;
        double solhcr = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("Europe/Paris"));
        for (final ActiviteSol act : ChopeCrew.analyse.listSol) {
            caldeb.setTime(act.debutUTCD);
            final int moisdepart = caldeb.get(2) + 1;
            calfin.setTime(act.finUTCD);
            final int moisarrivee = calfin.get(2) + 1;
            if (moisdepart == mois && moisarrivee == mois) {
                solhc += act.HCS;
                solhcr += act.HCRS;
            }
            else {
                if (moisdepart != mois) {
                    continue;
                }
                solhc += act.HCS;
                solhcr += act.HCRS;
            }
        }
        this.solHC = Utils.arrondi(solhc, 2);
        this.solHCR = Utils.arrondi(solhcr, 2);
    }
    
    private void chopeVolHCpr(final int mois) {
        double volhcp = 0.0;
        double volhcrp = 0.0;
        double volhcr = 0.0;
        double volhcrr = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            caldeb.setTimeInMillis(rotation.DEPp);
            int moisdepart = caldeb.get(2) + 1;
            calfin.setTimeInMillis(rotation.ARRp);
            int moisarrivee = calfin.get(2) + 1;
            if (moisdepart == mois && moisarrivee == mois) {
                volhcp += rotation.H2p;
                volhcrp += rotation.H2Rp;
            }
            else if (moisdepart == mois) {
                final double k = Utils.arrondi(rotation.STVp1 / rotation.STVp, 4);
                volhcp += Utils.arrondi(rotation.H2p * k, 2);
                volhcrp += Utils.arrondi(rotation.H2Rp * k, 2);
            }
            else if (moisarrivee == mois) {
                final double k = Utils.arrondi(rotation.STVp2 / rotation.STVp, 4);
                volhcp += Utils.arrondi(rotation.H2p * k, 2);
                volhcrp += Utils.arrondi(rotation.H2Rp * k, 2);
            }
            caldeb.setTimeInMillis(rotation.DEPr);
            moisdepart = caldeb.get(2) + 1;
            calfin.setTimeInMillis(rotation.ARRr);
            moisarrivee = calfin.get(2) + 1;
            if (moisdepart == mois && moisarrivee == mois) {
                volhcr += rotation.H2r;
                volhcrr += rotation.H2Rr;
            }
            else if (moisdepart == mois) {
                final double k = Utils.arrondi(rotation.STVr1 / rotation.STVr, 4);
                volhcr += Utils.arrondi(rotation.H2r * k, 2);
                volhcrr += Utils.arrondi(rotation.H2Rr * k, 2);
            }
            else {
                if (moisarrivee != mois) {
                    continue;
                }
                final double k = Utils.arrondi(rotation.STVr2 / rotation.STVr, 4);
                volhcr += Utils.arrondi(rotation.H2r * k, 2);
                volhcrr += Utils.arrondi(rotation.H2Rr * k, 2);
            }
        }
        this.volHCp = Utils.arrondi(volhcp, 2);
        this.volHCRp = Utils.arrondi(volhcrp, 2);
        this.volHCr = Utils.arrondi(volhcr, 2);
        this.volHCRr = Utils.arrondi(volhcrr, 2);
    }
    
    private void chopeVolHCNpr(final int mois) {
        double volhcnp = 0.0;
        double volhcnr = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    caldeb.setTimeInMillis(troncon.DEPp);
                    calfin.setTimeInMillis(troncon.ARRp);
                    int moisdebtro = caldeb.get(2) + 1;
                    int moisfintro = calfin.get(2) + 1;
                    if (moisdebtro == mois && moisfintro == mois) {
                        volhcnp += troncon.HCNp;
                    }
                    else if (moisdebtro != mois && moisfintro == mois) {
                        volhcnp += troncon.HCNp2;
                    }
                    else if (moisdebtro == mois && moisfintro != mois) {
                        volhcnp += troncon.HCNp1;
                    }
                    caldeb.setTimeInMillis(troncon.DEPr);
                    calfin.setTimeInMillis(troncon.ARRr);
                    moisdebtro = caldeb.get(2) + 1;
                    moisfintro = calfin.get(2) + 1;
                    if (moisdebtro == mois && moisfintro == mois) {
                        volhcnr += troncon.HCNr;
                    }
                    else if (moisdebtro != mois && moisfintro == mois) {
                        volhcnr += troncon.HCNr2;
                    }
                    else {
                        if (moisdebtro != mois || moisfintro == mois) {
                            continue;
                        }
                        volhcnr += troncon.HCNr1;
                    }
                }
            }
        }
        this.volHCNp = Utils.arrondi(volhcnp, 4);
        this.volHCNr = Utils.arrondi(volhcnr, 4);
    }
    
    private void chopeSolMontantHCRS(final int mois) {
        double montantsolhcrs = 0.0;
        for (final ActiviteSol act : ChopeCrew.analyse.listSol) {
            montantsolhcrs += Utils.arrondi(act.HCRS * this.pv * this.kpvhc, 2);
        }
        this.montantSolHCRS = Utils.arrondi(montantsolhcrs, 2);
    }
    
    private void chopeVolMontantHCRpr(final int mois) {
        double montantvolhcrp = 0.0;
        double montantvolhcrr = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            caldeb.setTimeInMillis(rotation.DEPp);
            int moisdepart = caldeb.get(2) + 1;
            calfin.setTimeInMillis(rotation.ARRp);
            int moisarrivee = calfin.get(2) + 1;
            if (moisdepart == mois && moisarrivee == mois) {
                montantvolhcrp += Utils.arrondi(rotation.H2Rp * this.pv * this.kpvhc, 2);
            }
            else if (moisarrivee == mois) {
                final double k = Utils.arrondi(rotation.STVp2 / rotation.STVp, 4);
                double x = Utils.arrondi(rotation.H2Rp * k, 2);
                x = Utils.arrondi(x * this.pv * this.kpvhc, 2);
                montantvolhcrp += x;
            }
            else if (moisdepart == mois) {
                final double k = Utils.arrondi(rotation.STVp1 / rotation.STVp, 4);
                double x = Utils.arrondi(rotation.H2Rp * k, 2);
                x = Utils.arrondi(x * this.pv * this.kpvhc, 2);
                montantvolhcrp += x;
            }
            caldeb.setTimeInMillis(rotation.DEPr);
            moisdepart = caldeb.get(2) + 1;
            calfin.setTimeInMillis(rotation.ARRr);
            moisarrivee = calfin.get(2) + 1;
            if (moisdepart == mois && moisarrivee == mois) {
                montantvolhcrr += Utils.arrondi(rotation.H2Rr * this.pv * this.kpvhc, 2);
            }
            else if (moisarrivee == mois) {
                final double k = Utils.arrondi(rotation.STVr2 / rotation.STVr, 4);
                double x = Utils.arrondi(rotation.H2Rr * k, 2);
                x = Utils.arrondi(x * this.pv * this.kpvhc, 2);
                montantvolhcrr += x;
            }
            else {
                if (moisdepart != mois) {
                    continue;
                }
                final double k = Utils.arrondi(rotation.STVr1 / rotation.STVr, 4);
                double x = Utils.arrondi(rotation.H2Rr * k, 2);
                x = Utils.arrondi(x * this.pv * this.kpvhc, 2);
                montantvolhcrr += x;
            }
        }
        this.montantVolHCRp = Utils.arrondi(montantvolhcrp, 2);
        this.montantVolHCRr = Utils.arrondi(montantvolhcrr, 2);
    }
    
    private void chopeVolMontantHCNpr(final int mois) {
        double montantvolhcnp = 0.0;
        double montantvolhcnr = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    caldeb.setTimeInMillis(troncon.DEPp);
                    calfin.setTimeInMillis(troncon.ARRp);
                    int moisdebtro = caldeb.get(2) + 1;
                    int moisfintro = calfin.get(2) + 1;
                    if (moisdebtro == mois && moisfintro == mois && !troncon.isMep) {
                        montantvolhcnp += Utils.arrondi(troncon.TVNp * 0.5 * sv.CMTp * this.pv * this.kpvhc, 2);
                    }
                    else if (moisfintro == mois && !troncon.isMep) {
                        final double k = Utils.arrondi(troncon.TVp2 / troncon.TVp, 4);
                        double x = Utils.arrondi(troncon.TVNp * 0.5 * k, 3);
                        x = Utils.arrondi(x * sv.CMTp * this.pv * this.kpvhc, 2);
                        montantvolhcnp += x;
                    }
                    else if (moisdebtro == mois && moisfintro != mois && !troncon.isMep) {
                        final double k = Utils.arrondi(troncon.TVp1 / troncon.TVp, 4);
                        double x = Utils.arrondi(troncon.TVNp * 0.5 * k, 3);
                        x = Utils.arrondi(x * sv.CMTp * this.pv * this.kpvhc, 2);
                        montantvolhcnp += x;
                    }
                    caldeb.setTimeInMillis(troncon.DEPr);
                    calfin.setTimeInMillis(troncon.ARRr);
                    moisdebtro = caldeb.get(2) + 1;
                    moisfintro = calfin.get(2) + 1;
                    if (moisdebtro == mois && moisfintro == mois && !troncon.isMep) {
                        montantvolhcnr += Utils.arrondi(troncon.TVNr * 0.5 * sv.CMTr * this.pv * this.kpvhc, 2);
                    }
                    else if (moisfintro == mois && !troncon.isMep) {
                        final double k = Utils.arrondi(troncon.TVr2 / troncon.TVr, 4);
                        double x = Utils.arrondi(troncon.TVNr * 0.5 * k, 3);
                        x = Utils.arrondi(x * sv.CMTr * this.pv * this.kpvhc, 2);
                        montantvolhcnr += x;
                    }
                    else {
                        if (moisdebtro != mois || moisfintro == mois || troncon.isMep) {
                            continue;
                        }
                        final double k = Utils.arrondi(troncon.TVr1 / troncon.TVr, 4);
                        double x = Utils.arrondi(troncon.TVNr * 0.5 * k, 3);
                        x = Utils.arrondi(x * sv.CMTr * this.pv * this.kpvhc, 2);
                        montantvolhcnr += x;
                    }
                }
            }
        }
        this.montantVolHCNp = Utils.arrondi(montantvolhcnp, 2);
        this.montantVolHCNr = Utils.arrondi(montantvolhcnr, 2);
    }
    
    private void chopeVolMontantHCSVNpr(final int mois) {
        double montantvolhcsvnp = 0.0;
        double montantvolhcsvnr = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                montantvolhcsvnp += Utils.arrondi(sv.TSVNp * 0.5 * this.pv * this.kpvhcNuit, 2);
                montantvolhcsvnr += Utils.arrondi(sv.TSVNr * 0.5 * this.pv * this.kpvhcNuit, 2);
            }
        }
        this.montantVolHCSVNp = Utils.arrondi(montantvolhcsvnp, 2);
        this.montantVolHCSVNr = Utils.arrondi(montantvolhcsvnr, 2);
    }
    
    private void chopeVolMontantPrimeCpt(final int mois) {
        double montantvolprimecaptp = 0.0;
        double montantvolprimecaptr = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    caldeb.setTimeInMillis(troncon.DEPp);
                    calfin.setTimeInMillis(troncon.ARRp);
                    int moisdebtro = caldeb.get(2) + 1;
                    int moisfintro = calfin.get(2) + 1;
                    if (moisdebtro == mois && moisfintro == mois && !troncon.isMep && this.isCdb) {
                        montantvolprimecaptp += Utils.arrondi(0.2 * this.pv, 2);
                    }
                    else if ((moisdebtro == mois || moisfintro != mois || troncon.isMep || !this.isCdb) && moisdebtro == mois && moisfintro != mois && !troncon.isMep && this.isCdb) {
                        montantvolprimecaptp += Utils.arrondi(0.2 * this.pv, 2);
                    }
                    caldeb.setTimeInMillis(troncon.DEPr);
                    calfin.setTimeInMillis(troncon.ARRr);
                    moisdebtro = caldeb.get(2) + 1;
                    moisfintro = calfin.get(2) + 1;
                    if (moisdebtro == mois && moisfintro == mois && !troncon.isMep && this.isCdb) {
                        montantvolprimecaptr += Utils.arrondi(0.2 * this.pv, 2);
                    }
                    else {
                        if ((moisdebtro != mois && moisfintro == mois && !troncon.isMep && this.isCdb) || moisdebtro != mois || moisfintro == mois || troncon.isMep || !this.isCdb) {
                            continue;
                        }
                        montantvolprimecaptr += Utils.arrondi(0.2 * this.pv, 2);
                    }
                }
            }
        }
        this.montantVolPrimeCdbp = Utils.arrondi(montantvolprimecaptp, 2);
        this.montantVolPrimeCdbr = Utils.arrondi(montantvolprimecaptr, 2);
    }
    
    private void chopeVolMontantHCMajo15pr(final int mois) {
        double montantvolhcmajo15p = 0.0;
        double montantvolhcmajo15r = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            if (this.isInsMC && rotation.sab != null && (rotation.sab.contains("IPLJS") || rotation.sab.contains("IPLG") || rotation.sab.contains("IPLD"))) {
                caldeb.setTimeInMillis(rotation.DEPp);
                int moisdepart = caldeb.get(2) + 1;
                calfin.setTimeInMillis(rotation.ARRp);
                int moisarrivee = calfin.get(2) + 1;
                if (moisdepart == mois && moisarrivee == mois) {
                    montantvolhcmajo15p += rotation.H2p * 0.15 * this.pv;
                }
                else if (moisdepart == mois) {
                    final double k = Utils.arrondi(rotation.STVp1 / rotation.STVp, 4);
                    montantvolhcmajo15p += Utils.arrondi(rotation.H2p * k * 0.15 * this.pv, 2);
                }
                else if (moisarrivee == mois) {
                    final double k = Utils.arrondi(rotation.STVp2 / rotation.STVp, 4);
                    montantvolhcmajo15p += Utils.arrondi(rotation.H2p * k * 0.15 * this.pv, 2);
                }
                caldeb.setTimeInMillis(rotation.DEPr);
                moisdepart = caldeb.get(2) + 1;
                calfin.setTimeInMillis(rotation.ARRr);
                moisarrivee = calfin.get(2) + 1;
                if (moisdepart == mois && moisarrivee == mois) {
                    montantvolhcmajo15r += rotation.H2r * 0.15 * this.pv;
                }
                else if (moisdepart == mois) {
                    final double k = Utils.arrondi(rotation.STVr1 / rotation.STVr, 4);
                    montantvolhcmajo15r += Utils.arrondi(rotation.H2r * k * 0.15 * this.pv, 2);
                }
                else {
                    if (moisarrivee != mois) {
                        continue;
                    }
                    final double k = Utils.arrondi(rotation.STVr2 / rotation.STVr, 4);
                    montantvolhcmajo15r += Utils.arrondi(rotation.H2r * k * 0.15 * this.pv, 2);
                }
            }
        }
        this.montantVolHCMajo15p = Utils.arrondi(montantvolhcmajo15p, 2);
        this.montantVolHCMajo15r = Utils.arrondi(montantvolhcmajo15r, 2);
    }
    
    private void chopeVolMontantIndemPR(final int mois) {
        double montantIndemP = 0.0;
        double montantIndemImpP = 0.0;
        double montantIndemNonImpP = 0.0;
        double montantIndemR = 0.0;
        double montantIndemImpR = 0.0;
        double montantIndemNonImpR = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    caldeb.setTimeInMillis(troncon.DEPp);
                    calfin.setTimeInMillis(troncon.ARRp);
                    int moisdepart = caldeb.get(2) + 1;
                    int moisarrivee = calfin.get(2) + 1;
                    if (moisarrivee == mois) {
                        double x = 0.0;
                        double ximp = 0.0;
                        double xnimp = 0.0;
                        int n = troncon.indemTo.nIRMidiP + troncon.indemTo.nIRSoirP;
                        x += Utils.arrondi(troncon.indemTo.ir * n, 2);
                        ximp += Math.min(Utils.arrondi(this.irfr * n, 2), Utils.arrondi(troncon.indemTo.ir * n, 2));
                        n = troncon.indemTo.nMFMidiP + troncon.indemTo.nMFSoirP;
                        x += Utils.arrondi(troncon.indemTo.mf * n, 2);
                        ximp += Math.min(Utils.arrondi(this.irfr * 0.2 * n, 2), Utils.arrondi(troncon.indemTo.mf * n, 2));
                        xnimp = Math.max(0.0, Utils.arrondi(x - ximp, 2));
                        montantIndemP += Utils.arrondi(x, 2);
                        montantIndemImpP += Utils.arrondi(ximp, 2);
                        montantIndemNonImpP += Utils.arrondi(xnimp, 2);
                    }
                    if (moisdepart == mois) {
                        double x = 0.0;
                        double ximp = 0.0;
                        double xnimp = 0.0;
                        int n = troncon.indemFrom.nIRMidiP + troncon.indemFrom.nIRSoirP;
                        x += Utils.arrondi(troncon.indemFrom.ir * n, 2);
                        ximp += Math.min(Utils.arrondi(this.irfr * n, 2), Utils.arrondi(troncon.indemFrom.ir * n, 2));
                        n = troncon.indemFrom.nMFMidiP + troncon.indemFrom.nMFSoirP;
                        x += Utils.arrondi(troncon.indemFrom.mf * n, 2);
                        ximp += Math.min(Utils.arrondi(this.irfr * 0.2 * n, 2), Utils.arrondi(troncon.indemFrom.mf * n, 2));
                        xnimp = Math.max(0.0, Utils.arrondi(x - ximp, 2));
                        montantIndemP += Utils.arrondi(x, 2);
                        montantIndemImpP += Utils.arrondi(ximp, 2);
                        montantIndemNonImpP += Utils.arrondi(xnimp, 2);
                    }
                    caldeb.setTimeInMillis(troncon.DEPr);
                    calfin.setTimeInMillis(troncon.ARRr);
                    moisdepart = caldeb.get(2) + 1;
                    moisarrivee = calfin.get(2) + 1;
                    if (moisarrivee == mois) {
                        double x = 0.0;
                        double ximp = 0.0;
                        double xnimp = 0.0;
                        int n = troncon.indemTo.nIRMidiR + troncon.indemTo.nIRSoirR;
                        x += Utils.arrondi(troncon.indemTo.ir * n, 2);
                        ximp += Math.min(Utils.arrondi(this.irfr * n, 2), Utils.arrondi(troncon.indemTo.ir * n, 2));
                        n = troncon.indemTo.nMFMidiR + troncon.indemTo.nMFSoirR;
                        x += Utils.arrondi(troncon.indemTo.mf * n, 2);
                        ximp += Math.min(Utils.arrondi(this.irfr * 0.2 * n, 2), Utils.arrondi(troncon.indemTo.mf * n, 2));
                        xnimp = Math.max(0.0, Utils.arrondi(x - ximp, 2));
                        montantIndemR += Utils.arrondi(x, 2);
                        montantIndemImpR += Utils.arrondi(ximp, 2);
                        montantIndemNonImpR += Utils.arrondi(xnimp, 2);
                    }
                    if (moisdepart == mois) {
                        double x = 0.0;
                        double ximp = 0.0;
                        double xnimp = 0.0;
                        int n = troncon.indemFrom.nIRMidiR + troncon.indemFrom.nIRSoirR;
                        x += Utils.arrondi(troncon.indemFrom.ir * n, 2);
                        ximp += Math.min(Utils.arrondi(this.irfr * n, 2), Utils.arrondi(troncon.indemFrom.ir * n, 2));
                        n = troncon.indemFrom.nMFMidiR + troncon.indemFrom.nMFSoirR;
                        x += Utils.arrondi(troncon.indemFrom.mf * n, 2);
                        ximp += Math.min(Utils.arrondi(this.irfr * 0.2 * n, 2), Utils.arrondi(troncon.indemFrom.mf * n, 2));
                        xnimp = Math.max(0.0, Utils.arrondi(x - ximp, 2));
                        montantIndemR += Utils.arrondi(x, 2);
                        montantIndemImpR += Utils.arrondi(ximp, 2);
                        montantIndemNonImpR += Utils.arrondi(xnimp, 2);
                    }
                }
            }
        }
        this.montantVolIndemP = Utils.arrondi(montantIndemP, 2);
        this.montantVolIndemImposableP = Utils.arrondi(montantIndemImpP, 2);
        this.montantVolIndemNonImposableP = Utils.arrondi(montantIndemNonImpP, 2);
        this.montantVolIndemR = Utils.arrondi(montantIndemR, 2);
        this.montantVolIndemImposableR = Utils.arrondi(montantIndemImpR, 2);
        this.montantVolIndemNonImposableR = Utils.arrondi(montantIndemNonImpR, 2);
    }
    
    private void chopeMontantIKVpr(final int mois) {
        double montantIKVp = 0.0;
        double montantIKVr = 0.0;
        final GregorianCalendar caldeb = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        final double ikvCdg = Math.floor(this.ikv * this.kmCdg * 1000.0) / 1000.0;
        final double ikvOry = Math.floor(this.ikv * this.kmOry * 1000.0) / 1000.0;
        final double ikvBase = Math.floor(this.ikv * this.kmBase * 1000.0) / 1000.0;
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            caldeb.setTimeInMillis(rotation.DEPp);
            calfin.setTimeInMillis(rotation.ARRp);
            int moisdepart = caldeb.get(2) + 1;
            int moisarrivee = calfin.get(2) + 1;
            if (moisdepart == mois && rotation.IKVallerP != null) {
                if (rotation.IKVallerP.equals("CDG")) {
                    montantIKVp += ikvCdg;
                }
                else if (rotation.IKVallerP.equals("ORY")) {
                    montantIKVp += ikvOry;
                }
                else {
                    montantIKVp += ikvBase;
                }
            }
            if (moisarrivee == mois && rotation.IKVretourP != null) {
                if (rotation.IKVretourP.equals("CDG")) {
                    montantIKVp += ikvCdg;
                }
                else if (rotation.IKVretourP.equals("ORY")) {
                    montantIKVp += ikvOry;
                }
                else {
                    montantIKVp += ikvBase;
                }
            }
            caldeb.setTimeInMillis(rotation.DEPr);
            calfin.setTimeInMillis(rotation.ARRr);
            moisdepart = caldeb.get(2) + 1;
            moisarrivee = calfin.get(2) + 1;
            if (moisdepart == mois && rotation.IKVallerR != null) {
                if (rotation.IKVallerR.equals("CDG")) {
                    montantIKVr += ikvCdg;
                }
                else if (rotation.IKVallerR.equals("ORY")) {
                    montantIKVr += ikvOry;
                }
                else {
                    montantIKVr += ikvBase;
                }
            }
            if (moisarrivee == mois && rotation.IKVretourR != null) {
                if (rotation.IKVretourR.equals("CDG")) {
                    montantIKVr += ikvCdg;
                }
                else if (rotation.IKVretourR.equals("ORY")) {
                    montantIKVr += ikvOry;
                }
                else {
                    montantIKVr += ikvBase;
                }
            }
        }
        for (final ActiviteSol act : ChopeCrew.analyse.listSol) {
            if (act.IKVar != null) {
                if (act.IKVar.equals("CDG")) {
                    montantIKVp += ikvCdg * 2.0;
                    montantIKVr += ikvCdg * 2.0;
                }
                else {
                    if (!act.IKVar.equals("ORY")) {
                        continue;
                    }
                    montantIKVp += ikvOry * 2.0;
                    montantIKVr += ikvOry * 2.0;
                }
            }
        }
        this.montantIKVp = Utils.arrondi(montantIKVp, 2);
        this.montantIKVr = Utils.arrondi(montantIKVr, 2);
    }
    
    private void chopeMontantDecPR(final int mois) {
        double montantDecP = 0.0;
        double montantDecR = 0.0;
        final GregorianCalendar calfin = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        for (final Rotation rotation : ChopeCrew.analyse.listRotation) {
            for (final ServiceVol sv : rotation.listSV) {
                for (final Troncon troncon : sv.listTroncon) {
                    calfin.setTimeInMillis(troncon.ARRp);
                    int moisarrivee = calfin.get(2) + 1;
                    if (moisarrivee == mois) {
                        montantDecP += Utils.arrondi(troncon.nDecP * this.decfr, 2);
                    }
                    calfin.setTimeInMillis(troncon.ARRr);
                    moisarrivee = calfin.get(2) + 1;
                    if (moisarrivee == mois) {
                        montantDecR += Utils.arrondi(troncon.nDecR * this.decfr, 2);
                    }
                }
            }
        }
        this.montantDECp = Utils.arrondi(montantDecP, 2);
        this.montantDECr = Utils.arrondi(montantDecR, 2);
    }
    
    private void chopeSeuilMGA() {
        double n = 0.0;
        double kpvhc = 0.0;
        if (ChopeCrew.analyse.isMC && !ChopeCrew.analyse.isBP) {
            n = 80.0;
            kpvhc = 1.13;
        }
        else if (ChopeCrew.analyse.isMC && ChopeCrew.analyse.isBP) {
            n = 80.0;
            kpvhc = 1.13;
        }
        else {
            n = 85.60000000000001;
            kpvhc = 1.07;
        }
        this.seuilMGA = Utils.arrondi(n * Math.max(0, 30 - this.nSansSolde - this.nConges - this.nMaladies) / (kpvhc * 30.0), 2);
    }
    
    private void chopeVolHS() {
        this.seuilHS = 75.0 - 2.5 * (this.nConges + this.nMaladies + this.nSansSolde);
        if (this.seuilHS < 16.0) {
            this.seuilHS = 16.0;
        }
        this.seuilHS = Utils.arrondi(this.seuilHS, 2);
        this.volHSp = this.volHCp + this.solHC - this.seuilHS;
        if (this.volHSp < 0.0) {
            this.volHSp = 0.0;
        }
        this.volHSp = Utils.arrondi(this.volHSp, 2);
        this.volHSr = this.volHCr + this.solHC - this.seuilHS;
        if (this.volHSr < 0.0) {
            this.volHSr = 0.0;
        }
        this.volHSr = Utils.arrondi(this.volHSr, 2);
    }
    
    private void chopeTauxHSFixe() {
        this.tauxHSFixe = Utils.arrondi(this.fixe * 1.25 / 75.0, 2);
    }
    
    private void chopeTauxHSVol() {
        final double montantHCRNuitP = Utils.arrondi(this.montantVolHCRp + this.montantSolHCRS + this.montantVolHCNp + this.montantVolPrimeCdbp, 2);
        final double montantHCRNuitR = Utils.arrondi(this.montantVolHCRr + this.montantSolHCRS + this.montantVolHCNr + this.montantVolPrimeCdbr, 2);
        final double HCp = Utils.arrondi(this.volHCp + this.solHC, 2);
        final double HCr = Utils.arrondi(this.volHCr + this.solHC, 2);
        this.tauxHSVolp = 0.0;
        this.tauxHSVolr = 0.0;
        if (HCp != 0.0) {
            this.tauxHSVolp = Utils.arrondi(0.25 * montantHCRNuitP / HCp, 2);
        }
        if (HCr != 0.0) {
            this.tauxHSVolr = Utils.arrondi(0.25 * montantHCRNuitR / HCr, 2);
        }
    }
    
    private void calculPayeP() {
        this.fppBaseFixe = this.fixe;
        this.fppFixe = Utils.arrondi(this.fppBaseFixe * Math.max(0, 30 - this.nSansSolde) / 30.0, 2);
        this.fppBasePrimeFonction = this.pfonction;
        this.fppPrimeFonction = Utils.arrondi(this.fppBasePrimeFonction * Math.max(0, 30 - this.nSansSolde) / 30.0, 2);
        this.fppPv = Utils.arrondi(this.montantVolHCRp + this.montantVolHCSVNp + this.montantSolHCRS + this.montantVolPrimeCdbp + this.montantVolHCMajo15p, 2);
        this.fppHsFixe = Utils.arrondi(this.volHSp * this.tauxHSFixe, 2);
        this.fppHsPv = Utils.arrondi(this.volHSp * this.tauxHSVolp, 2);
        this.fppBaseCongePv = this.congePv;
        this.fppBaseCongeHs = this.congeHs;
        this.fppBaseCongeAs = this.congeAs;
        this.fppCongePv = Utils.arrondi(this.fppBaseCongePv * this.nConges, 2);
        this.fppCongeHs = Utils.arrondi(this.fppBaseCongeHs * this.nConges, 2);
        this.fppCongeAs = Utils.arrondi(this.fppBaseCongeAs * this.nConges, 2);
        this.fppBaseMaladie = Utils.arrondi(this.mga / 30.0, 2);
        this.fppMaladie = Utils.arrondi(this.fppBaseMaladie * this.nMaladies, 2);
        this.fppBaseMga = this.mga;
        this.fppMga = Utils.arrondi(this.fppBaseMga * Math.max(0, 30 - this.nSansSolde - this.nConges - this.nMaladies) / 30.0, 2);
        this.fppCompMga = Utils.arrondi(this.fppMga - (this.fppPv + this.fppHsFixe + this.fppHsPv), 2);
        this.fppCompMga = Math.max(0.0, this.fppCompMga);
        this.fppBrutFiscal = Utils.arrondi(this.cBF + this.fppFixe + this.fppPrimeFonction + this.fppPv + this.fppHsFixe + this.fppHsPv + this.fppCongePv + this.fppCongeHs + this.fppCongeAs + this.fppMaladie + this.fppCompMga, 2);
        this.fppRepasImp = Utils.arrondi(this.cIRimp + this.montantVolIndemImposableP, 2);
        this.fppRepasNonImp = Utils.arrondi(this.cIRnimp + this.montantVolIndemNonImposableP, 2);
        this.fppIkv = Utils.arrondi(this.cIKV + this.montantIKVp, 2);
        double x = 0.0;
        x = this.fppBrutFiscal * this.iadp / 100.0;
        x = Utils.arrondi(x, 2);
        double baseSS = 0.0;
        if (this.abattementSecu) {
            baseSS = (this.fppBrutFiscal + this.fppIkv + this.fppRepasImp + x) * this.kss;
        }
        else if (!this.abattementSecu) {
            baseSS = this.fppBrutFiscal + this.fppIkv + this.fppRepasImp + x;
        }
        baseSS = Utils.arrondi(baseSS, 2);
        this.fppBaseSSM = baseSS;
        this.fppBaseSSV = Math.min(baseSS, this.plafss);
        this.fppBaseSSVC = baseSS;
        this.fppBaseASS = Math.min(baseSS, 4.0 * this.plafss);
        double baseCRPN = 0.0;
        baseCRPN = this.fppBrutFiscal;
        baseCRPN = Utils.arrondi(baseCRPN, 2);
        this.fppBaseCRPNr = Math.min(baseCRPN, 8.0 * this.plafss);
        this.fppBaseCRPNa = Math.min(baseCRPN, 8.0 * this.plafss);
        this.fppBaseCRPNm = Math.min(baseCRPN, this.plafss);
        if (baseCRPN <= 4.0 * this.plafss) {
            this.fppBaseART83T1 = baseCRPN;
            this.fppBaseART83T2 = 0.0;
            this.fppBaseART83VIF = baseCRPN;
        }
        else {
            this.fppBaseART83T1 = 4.0 * this.plafss;
            this.fppBaseART83T2 = baseCRPN - 4.0 * this.plafss;
            this.fppBaseART83VIF = 4.0 * this.plafss;
        }
        this.fppBaseART83T1 = Utils.arrondi(this.fppBaseART83T1, 2);
        this.fppBaseART83T2 = Utils.arrondi(this.fppBaseART83T2, 2);
        this.fppBaseART83VIF = Utils.arrondi(this.fppBaseART83VIF, 2);
        double basePREV = 0.0;
        basePREV = this.fppBrutFiscal;
        basePREV = Utils.arrondi(basePREV, 2);
        this.fppBasePIAD = basePREV;
        this.fppBasePCLM = basePREV;
        if (basePREV <= this.plafss / 2.0) {
            this.fppBaseMNPAF = this.plafss / 2.0;
        }
        else if (basePREV > this.plafss * 2.0) {
            this.fppBaseMNPAF = 2.0 * this.plafss;
        }
        else {
            this.fppBaseMNPAF = basePREV;
        }
        this.fppBaseMNPAF = Utils.arrondi(this.fppBaseMNPAF, 2);
        if (basePREV <= this.plafss / 2.0) {
            this.fppBasePDT1 = basePREV;
            this.fppBasePDT1B = 0.0;
            this.fppBasePDT2 = 0.0;
            this.fppBasePDT3 = 0.0;
            this.fppBasePDT4 = 0.0;
            this.fppBasePDT5 = 0.0;
        }
        else if (basePREV > this.plafss / 2.0 && basePREV <= this.plafss) {
            this.fppBasePDT1 = this.plafss / 2.0;
            this.fppBasePDT1B = basePREV - this.plafss / 2.0;
            this.fppBasePDT2 = 0.0;
            this.fppBasePDT3 = 0.0;
            this.fppBasePDT4 = 0.0;
            this.fppBasePDT5 = 0.0;
        }
        else if (basePREV > this.plafss && basePREV <= 2.0 * this.plafss) {
            this.fppBasePDT1 = this.plafss / 2.0;
            this.fppBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fppBasePDT2 = basePREV - this.plafss;
            this.fppBasePDT3 = 0.0;
            this.fppBasePDT4 = 0.0;
            this.fppBasePDT5 = 0.0;
        }
        else if (basePREV > 2.0 * this.plafss && basePREV <= 4.0 * this.plafss) {
            this.fppBasePDT1 = this.plafss / 2.0;
            this.fppBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fppBasePDT2 = 2.0 * this.plafss - this.plafss;
            this.fppBasePDT3 = basePREV - 2.0 * this.plafss;
            this.fppBasePDT4 = 0.0;
            this.fppBasePDT5 = 0.0;
        }
        else if (basePREV > 4.0 * this.plafss && basePREV <= 6.0 * this.plafss) {
            this.fppBasePDT1 = this.plafss / 2.0;
            this.fppBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fppBasePDT2 = 2.0 * this.plafss - this.plafss;
            this.fppBasePDT3 = 4.0 * this.plafss - 2.0 * this.plafss;
            this.fppBasePDT4 = basePREV - 4.0 * this.plafss;
            this.fppBasePDT5 = 0.0;
        }
        else if (basePREV > 6.0 * this.plafss && basePREV <= 8.0 * this.plafss) {
            this.fppBasePDT1 = this.plafss / 2.0;
            this.fppBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fppBasePDT2 = 2.0 * this.plafss - this.plafss;
            this.fppBasePDT3 = 4.0 * this.plafss - 2.0 * this.plafss;
            this.fppBasePDT4 = 6.0 * this.plafss - 4.0 * this.plafss;
            this.fppBasePDT5 = basePREV - 6.0 * this.plafss;
        }
        else if (basePREV > 8.0 * this.plafss) {
            this.fppBasePDT1 = this.plafss / 2.0;
            this.fppBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fppBasePDT2 = 2.0 * this.plafss - this.plafss;
            this.fppBasePDT3 = 4.0 * this.plafss - 2.0 * this.plafss;
            this.fppBasePDT4 = 6.0 * this.plafss - 4.0 * this.plafss;
            this.fppBasePDT5 = 8.0 * this.plafss - 6.0 * this.plafss;
        }
        this.fppBasePDT1 = Utils.arrondi(this.fppBasePDT1, 2);
        this.fppBasePDT1B = Utils.arrondi(this.fppBasePDT1B, 2);
        this.fppBasePDT2 = Utils.arrondi(this.fppBasePDT2, 2);
        this.fppBasePDT3 = Utils.arrondi(this.fppBasePDT3, 2);
        this.fppBasePDT4 = Utils.arrondi(this.fppBasePDT4, 2);
        this.fppBasePDT5 = Utils.arrondi(this.fppBasePDT5, 2);
        if (basePREV <= this.plafss) {
            this.fppBasePLMTA = basePREV;
            this.fppBasePLMTB = 0.0;
            this.fppBasePLMTC = 0.0;
        }
        else if (basePREV > this.plafss && basePREV <= 6.0 * this.plafss) {
            this.fppBasePLMTA = this.plafss;
            this.fppBasePLMTB = basePREV - this.plafss;
            this.fppBasePLMTC = 0.0;
        }
        else if (basePREV > 6.0 * this.plafss && basePREV <= 8.0 * this.plafss) {
            this.fppBasePLMTA = this.plafss;
            this.fppBasePLMTB = 6.0 * this.plafss - this.plafss;
            this.fppBasePLMTC = basePREV - 6.0 * this.plafss;
        }
        else if (basePREV > 8.0 * this.plafss) {
            this.fppBasePLMTA = this.plafss;
            this.fppBasePLMTB = 6.0 * this.plafss - this.plafss;
            this.fppBasePLMTC = 8.0 * this.plafss - 6.0 * this.plafss;
        }
        this.fppBasePLMTA = Utils.arrondi(this.fppBasePLMTA, 2);
        this.fppBasePLMTB = Utils.arrondi(this.fppBasePLMTB, 2);
        this.fppBasePLMTC = Utils.arrondi(this.fppBasePLMTC, 2);
        double cotisP = 0.0;
        cotisP += Utils.arrondi(this.fppBaseCRPNa * this.crpna / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBaseMNPAF * this.mnpafp / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePIAD * this.iadp / 100.0, 2);
        cotisP += this.pdfp;
        cotisP += Utils.arrondi(this.fppBasePDT1 * this.pdt1p / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePDT1B * this.pdt1Bp / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePDT2 * this.pdt2p / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePDT3 * this.pdt3p / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePDT4 * this.pdt4p / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePDT5 * this.pdt5p / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePLMTA * this.plmtap / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePLMTB * this.plmtbp / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePLMTC * this.plmtcp / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBasePCLM * this.pclmp / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBaseART83T1 * this.art83t1p / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBaseART83T2 * this.art83t2p / 100.0, 2);
        cotisP += Utils.arrondi(this.fppBaseART83VIF * this.art83vifp / 100.0, 2);
        cotisP = Utils.arrondi(cotisP, 2);
        this.fppCotisArt83_1 = Utils.arrondi(this.fppBaseART83T1 * this.art83t1p / 100.0, 2);
        this.fppCotisArt83_2 = Utils.arrondi(this.fppBaseART83T2 * this.art83t2p / 100.0, 2);
        this.fppCotisArt83Vif = Utils.arrondi(this.fppBaseART83VIF * this.art83vifp / 100.0, 2);
        if (!this.isResidentEtranger) {
            if (this.fppBrutFiscal <= 4.0 * this.plafss) {
                this.fppBaseCSGd = Utils.arrondi(this.fppBrutFiscal * this.kcsg + cotisP, 2);
                this.fppBaseCSGnd = Utils.arrondi(this.fppBrutFiscal * this.kcsg + cotisP, 2);
            }
            else {
                this.fppBaseCSGd = Utils.arrondi(4.0 * this.plafss * this.kcsg + cotisP + (this.fppBrutFiscal - 4.0 * this.plafss), 2);
                this.fppBaseCSGnd = Utils.arrondi(4.0 * this.plafss * this.kcsg + cotisP + (this.fppBrutFiscal - 4.0 * this.plafss), 2);
            }
        }
        else {
            this.fppBaseCSGd = 0.0;
            this.fppBaseCSGnd = 0.0;
        }
        if (!this.isResidentEtranger) {
            this.fppCotisSSM = Utils.arrondi(this.fppBaseSSM * this.ssm / 100.0, 2);
        }
        else {
            this.fppCotisSSM = Utils.arrondi(this.fppBaseSSM * this.ssme / 100.0, 2);
        }
        this.fppCotisSSV = Utils.arrondi(this.fppBaseSSV * this.ssv / 100.0, 2);
        this.fppCotisSSVC = Utils.arrondi(this.fppBaseSSVC * this.ssvc / 100.0, 2);
        this.fppCotisASS = Utils.arrondi(this.fppBaseASS * this.ass / 100.0, 2);
        this.fppCotisCRPNr = Utils.arrondi(this.fppBaseCRPNr * this.crpnr / 100.0, 2);
        this.fppCotisCRPNa = Utils.arrondi(this.fppBaseCRPNa * this.crpna / 100.0, 2);
        this.fppCotisCRPNm = Utils.arrondi(this.fppBaseCRPNm * this.crpnm / 100.0, 2);
        this.fppCotisMNPAF = Utils.arrondi(this.fppBaseMNPAF * this.mnpaf / 100.0, 2);
        this.fppCotisPIAD = Utils.arrondi(this.fppBasePIAD * this.iad / 100.0, 2);
        this.fppCotisPDT1 = Utils.arrondi(this.fppBasePDT1 * this.pdt1 / 100.0, 2);
        this.fppCotisPDT1B = Utils.arrondi(this.fppBasePDT1B * this.pdt1B / 100.0, 2);
        this.fppCotisPDT2 = Utils.arrondi(this.fppBasePDT2 * this.pdt2 / 100.0, 2);
        this.fppCotisPDT3 = Utils.arrondi(this.fppBasePDT3 * this.pdt3 / 100.0, 2);
        this.fppCotisPDT4 = Utils.arrondi(this.fppBasePDT4 * this.pdt4 / 100.0, 2);
        this.fppCotisPDT5 = Utils.arrondi(this.fppBasePDT5 * this.pdt5 / 100.0, 2);
        this.fppCotisPLMTA = Utils.arrondi(this.fppBasePLMTA * this.plmta / 100.0, 2);
        this.fppCotisPLMTB = Utils.arrondi(this.fppBasePLMTB * this.plmtb / 100.0, 2);
        this.fppCotisPLMTC = Utils.arrondi(this.fppBasePLMTC * this.plmtc / 100.0, 2);
        this.fppCotisCSGd = Utils.arrondi(this.fppBaseCSGd * this.csgd / 100.0, 2);
        this.fppCotisCSGnd = Utils.arrondi(this.fppBaseCSGnd * this.csgnd / 100.0, 2);
        this.fppCotisEmploye = this.fppCotisSSM + this.fppCotisSSV + this.fppCotisSSVC + this.fppCotisASS + this.fppCotisMNPAF;
        this.fppCotisEmploye += this.fppCotisCRPNr + this.fppCotisCRPNa + this.fppCotisCRPNm;
        this.fppCotisEmploye += this.fppCotisPIAD + this.fppCotisPDT1 + this.fppCotisPDT1B + this.fppCotisPDT2 + this.fppCotisPDT3 + this.fppCotisPDT4 + this.fppCotisPDT5;
        this.fppCotisEmploye += this.fppCotisPLMTA + this.fppCotisPLMTB + this.fppCotisPLMTC;
        this.fppCotisEmploye += this.fppCotisCSGd + this.fppCotisCSGnd;
        this.fppCotisEmploye = Utils.arrondi(this.fppCotisEmploye, 2);
        this.fppNetBulletin = Utils.arrondi(this.fppBrutFiscal + this.fppRepasImp + this.fppRepasNonImp + this.fppIkv - this.fppCotisEmploye, 2);
        this.fppNetImposable = Utils.arrondi(Utils.arrondi(this.fppBrutFiscal - this.fppCotisEmploye + this.fppCotisCSGnd, 2) + Utils.arrondi(this.fppBaseMNPAF * this.mnpafp / 100.0, 2), 2);
        this.fppDEC = this.montantDECp;
        this.fppFraisPro = Utils.arrondi(this.fppRepasImp + this.fppIkv + this.fppDEC, 2);
    }
    
    private void calculPayeR() {
        this.fprBaseFixe = this.fixe;
        this.fprFixe = Utils.arrondi(this.fprBaseFixe * Math.max(0, 30 - this.nSansSolde) / 30.0, 2);
        this.fprBasePrimeFonction = this.pfonction;
        this.fprPrimeFonction = Utils.arrondi(this.fprBasePrimeFonction * Math.max(0, 30 - this.nSansSolde) / 30.0, 2);
        this.fprPv = Utils.arrondi(this.montantVolHCRr + this.montantVolHCSVNr + this.montantSolHCRS + this.montantVolPrimeCdbr + this.montantVolHCMajo15r, 2);
        this.fprHsFixe = Utils.arrondi(this.volHSr * this.tauxHSFixe, 2);
        this.fprHsPv = Utils.arrondi(this.volHSr * this.tauxHSVolr, 2);
        this.fprBaseCongePv = this.congePv;
        this.fprBaseCongeHs = this.congeHs;
        this.fprBaseCongeAs = this.congeAs;
        this.fprCongePv = Utils.arrondi(this.fprBaseCongePv * this.nConges, 2);
        this.fprCongeHs = Utils.arrondi(this.fprBaseCongeHs * this.nConges, 2);
        this.fprCongeAs = Utils.arrondi(this.fprBaseCongeAs * this.nConges, 2);
        this.fprBaseMaladie = Utils.arrondi(this.mga / 30.0, 2);
        this.fprMaladie = Utils.arrondi(this.fprBaseMaladie * this.nMaladies, 2);
        this.fprBaseMga = this.mga;
        this.fprMga = Utils.arrondi(this.fprBaseMga * Math.max(0, 30 - this.nSansSolde - this.nConges - this.nMaladies) / 30.0, 2);
        this.fprCompMga = Utils.arrondi(this.fprMga - (this.fprPv + this.fprHsFixe + this.fprHsPv), 2);
        this.fprCompMga = Math.max(0.0, this.fprCompMga);
        this.fprBrutFiscal = Utils.arrondi(this.cBF + this.fprFixe + this.fprPrimeFonction + this.fprPv + this.fprHsFixe + this.fprHsPv + this.fprCongePv + this.fprCongeHs + this.fprCongeAs + this.fprMaladie + this.fprCompMga, 2);
        this.fprRepasImp = Utils.arrondi(this.cIRimp + this.montantVolIndemImposableR, 2);
        this.fprRepasNonImp = Utils.arrondi(this.cIRnimp + this.montantVolIndemNonImposableR, 2);
        this.fprIkv = Utils.arrondi(this.cIKV + this.montantIKVr, 2);
        double x = 0.0;
        x = this.fprBrutFiscal * this.iadp / 100.0;
        x = Utils.arrondi(x, 2);
        double baseSS = 0.0;
        if (this.abattementSecu) {
            baseSS = (this.fprBrutFiscal + this.fprIkv + this.fprRepasImp + x) * this.kss;
        }
        else if (!this.abattementSecu) {
            baseSS = this.fprBrutFiscal + this.fprIkv + this.fprRepasImp + x;
        }
        baseSS = Utils.arrondi(baseSS, 2);
        this.fprBaseSSM = baseSS;
        this.fprBaseSSV = Math.min(baseSS, this.plafss);
        this.fprBaseSSVC = baseSS;
        this.fprBaseASS = Math.min(baseSS, 4.0 * this.plafss);
        double baseCRPN = 0.0;
        baseCRPN = this.fprBrutFiscal;
        baseCRPN = Utils.arrondi(baseCRPN, 2);
        this.fprBaseCRPNr = Math.min(baseCRPN, 8.0 * this.plafss);
        this.fprBaseCRPNa = Math.min(baseCRPN, 8.0 * this.plafss);
        this.fprBaseCRPNm = Math.min(baseCRPN, this.plafss);
        if (baseCRPN <= 4.0 * this.plafss) {
            this.fprBaseART83T1 = baseCRPN;
            this.fprBaseART83T2 = 0.0;
            this.fprBaseART83VIF = baseCRPN;
        }
        else {
            this.fprBaseART83T1 = 4.0 * this.plafss;
            this.fprBaseART83T2 = baseCRPN - 4.0 * this.plafss;
            this.fprBaseART83VIF = 4.0 * this.plafss;
        }
        this.fprBaseART83T1 = Utils.arrondi(this.fprBaseART83T1, 2);
        this.fprBaseART83T2 = Utils.arrondi(this.fprBaseART83T2, 2);
        this.fprBaseART83VIF = Utils.arrondi(this.fprBaseART83VIF, 2);
        double basePREV = 0.0;
        basePREV = this.fprBrutFiscal;
        basePREV = Utils.arrondi(basePREV, 2);
        this.fprBasePIAD = basePREV;
        this.fprBasePCLM = basePREV;
        if (basePREV <= this.plafss / 2.0) {
            this.fprBaseMNPAF = this.plafss / 2.0;
        }
        else if (basePREV > this.plafss * 2.0) {
            this.fprBaseMNPAF = 2.0 * this.plafss;
        }
        else {
            this.fprBaseMNPAF = basePREV;
        }
        this.fprBaseMNPAF = Utils.arrondi(this.fprBaseMNPAF, 2);
        if (basePREV <= this.plafss / 2.0) {
            this.fprBasePDT1 = basePREV;
            this.fprBasePDT1B = 0.0;
            this.fprBasePDT2 = 0.0;
            this.fprBasePDT3 = 0.0;
            this.fprBasePDT4 = 0.0;
            this.fprBasePDT5 = 0.0;
        }
        else if (basePREV > this.plafss / 2.0 && basePREV <= this.plafss) {
            this.fprBasePDT1 = this.plafss / 2.0;
            this.fprBasePDT1B = basePREV - this.plafss / 2.0;
            this.fprBasePDT2 = 0.0;
            this.fprBasePDT3 = 0.0;
            this.fprBasePDT4 = 0.0;
            this.fprBasePDT5 = 0.0;
        }
        else if (basePREV > this.plafss && basePREV <= 2.0 * this.plafss) {
            this.fprBasePDT1 = this.plafss / 2.0;
            this.fprBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fprBasePDT2 = basePREV - this.plafss;
            this.fprBasePDT3 = 0.0;
            this.fprBasePDT4 = 0.0;
            this.fprBasePDT5 = 0.0;
        }
        else if (basePREV > 2.0 * this.plafss && basePREV <= 4.0 * this.plafss) {
            this.fprBasePDT1 = this.plafss / 2.0;
            this.fprBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fprBasePDT2 = 2.0 * this.plafss - this.plafss;
            this.fprBasePDT3 = basePREV - 2.0 * this.plafss;
            this.fprBasePDT4 = 0.0;
            this.fprBasePDT5 = 0.0;
        }
        else if (basePREV > 4.0 * this.plafss && basePREV <= 6.0 * this.plafss) {
            this.fprBasePDT1 = this.plafss / 2.0;
            this.fprBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fprBasePDT2 = 2.0 * this.plafss - this.plafss;
            this.fprBasePDT3 = 4.0 * this.plafss - 2.0 * this.plafss;
            this.fprBasePDT4 = basePREV - 4.0 * this.plafss;
            this.fprBasePDT5 = 0.0;
        }
        else if (basePREV > 6.0 * this.plafss && basePREV <= 8.0 * this.plafss) {
            this.fprBasePDT1 = this.plafss / 2.0;
            this.fprBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fprBasePDT2 = 2.0 * this.plafss - this.plafss;
            this.fprBasePDT3 = 4.0 * this.plafss - 2.0 * this.plafss;
            this.fprBasePDT4 = 6.0 * this.plafss - 4.0 * this.plafss;
            this.fprBasePDT5 = basePREV - 6.0 * this.plafss;
        }
        else if (basePREV > 8.0 * this.plafss) {
            this.fprBasePDT1 = this.plafss / 2.0;
            this.fprBasePDT1B = this.plafss - this.plafss / 2.0;
            this.fprBasePDT2 = 2.0 * this.plafss - this.plafss;
            this.fprBasePDT3 = 4.0 * this.plafss - 2.0 * this.plafss;
            this.fprBasePDT4 = 6.0 * this.plafss - 4.0 * this.plafss;
            this.fprBasePDT5 = 8.0 * this.plafss - 6.0 * this.plafss;
        }
        this.fprBasePDT1 = Utils.arrondi(this.fprBasePDT1, 2);
        this.fprBasePDT1B = Utils.arrondi(this.fprBasePDT1B, 2);
        this.fprBasePDT2 = Utils.arrondi(this.fprBasePDT2, 2);
        this.fprBasePDT3 = Utils.arrondi(this.fprBasePDT3, 2);
        this.fprBasePDT4 = Utils.arrondi(this.fprBasePDT4, 2);
        this.fprBasePDT5 = Utils.arrondi(this.fprBasePDT5, 2);
        if (basePREV <= this.plafss) {
            this.fprBasePLMTA = basePREV;
            this.fprBasePLMTB = 0.0;
            this.fprBasePLMTC = 0.0;
        }
        else if (basePREV > this.plafss && basePREV <= 6.0 * this.plafss) {
            this.fprBasePLMTA = this.plafss;
            this.fprBasePLMTB = basePREV - this.plafss;
            this.fprBasePLMTC = 0.0;
        }
        else if (basePREV > 6.0 * this.plafss && basePREV <= 8.0 * this.plafss) {
            this.fprBasePLMTA = this.plafss;
            this.fprBasePLMTB = 6.0 * this.plafss - this.plafss;
            this.fprBasePLMTC = basePREV - 6.0 * this.plafss;
        }
        else if (basePREV > 8.0 * this.plafss) {
            this.fprBasePLMTA = this.plafss;
            this.fprBasePLMTB = 6.0 * this.plafss - this.plafss;
            this.fprBasePLMTC = 8.0 * this.plafss - 6.0 * this.plafss;
        }
        this.fprBasePLMTA = Utils.arrondi(this.fprBasePLMTA, 2);
        this.fprBasePLMTB = Utils.arrondi(this.fprBasePLMTB, 2);
        this.fprBasePLMTC = Utils.arrondi(this.fprBasePLMTC, 2);
        double cotisP = 0.0;
        cotisP += Utils.arrondi(this.fprBaseCRPNa * this.crpna / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBaseMNPAF * this.mnpafp / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePIAD * this.iadp / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePDT1 * this.pdt1p / 100.0, 2);
        cotisP += this.pdfp;
        cotisP += Utils.arrondi(this.fprBasePDT1B * this.pdt1Bp / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePDT2 * this.pdt2p / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePDT3 * this.pdt3p / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePDT4 * this.pdt4p / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePDT5 * this.pdt5p / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePLMTA * this.plmtap / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePLMTB * this.plmtbp / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePLMTC * this.plmtcp / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBasePCLM * this.pclmp / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBaseART83T1 * this.art83t1p / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBaseART83T2 * this.art83t2p / 100.0, 2);
        cotisP += Utils.arrondi(this.fprBaseART83VIF * this.art83vifp / 100.0, 2);
        cotisP = Utils.arrondi(cotisP, 2);
        this.fprCotisArt83_1 = Utils.arrondi(this.fprBaseART83T1 * this.art83t1p / 100.0, 2);
        this.fprCotisArt83_2 = Utils.arrondi(this.fprBaseART83T2 * this.art83t2p / 100.0, 2);
        this.fprCotisArt83Vif = Utils.arrondi(this.fprBaseART83VIF * this.art83vifp / 100.0, 2);
        if (!this.isResidentEtranger) {
            if (this.fprBrutFiscal <= 4.0 * this.plafss) {
                this.fprBaseCSGd = Utils.arrondi(this.fprBrutFiscal * this.kcsg + cotisP, 2);
                this.fprBaseCSGnd = Utils.arrondi(this.fprBrutFiscal * this.kcsg + cotisP, 2);
            }
            else {
                this.fprBaseCSGd = Utils.arrondi(4.0 * this.plafss * this.kcsg + cotisP + (this.fprBrutFiscal - 4.0 * this.plafss), 2);
                this.fprBaseCSGnd = Utils.arrondi(4.0 * this.plafss * this.kcsg + cotisP + (this.fprBrutFiscal - 4.0 * this.plafss), 2);
            }
        }
        else {
            this.fprBaseCSGd = 0.0;
            this.fprBaseCSGnd = 0.0;
        }
        if (!this.isResidentEtranger) {
            this.fprCotisSSM = Utils.arrondi(this.fprBaseSSM * this.ssm / 100.0, 2);
        }
        else {
            this.fprCotisSSM = Utils.arrondi(this.fprBaseSSM * this.ssme / 100.0, 2);
        }
        this.fprCotisSSV = Utils.arrondi(this.fprBaseSSV * this.ssv / 100.0, 2);
        this.fprCotisSSVC = Utils.arrondi(this.fprBaseSSVC * this.ssvc / 100.0, 2);
        this.fprCotisASS = Utils.arrondi(this.fprBaseASS * this.ass / 100.0, 2);
        this.fprCotisCRPNr = Utils.arrondi(this.fprBaseCRPNr * this.crpnr / 100.0, 2);
        this.fprCotisCRPNa = Utils.arrondi(this.fprBaseCRPNa * this.crpna / 100.0, 2);
        this.fprCotisCRPNm = Utils.arrondi(this.fprBaseCRPNm * this.crpnm / 100.0, 2);
        this.fprCotisMNPAF = Utils.arrondi(this.fprBaseMNPAF * this.mnpaf / 100.0, 2);
        this.fprCotisPIAD = Utils.arrondi(this.fprBasePIAD * this.iad / 100.0, 2);
        this.fprCotisPDT1 = Utils.arrondi(this.fprBasePDT1 * this.pdt1 / 100.0, 2);
        this.fprCotisPDT1B = Utils.arrondi(this.fprBasePDT1B * this.pdt1B / 100.0, 2);
        this.fprCotisPDT2 = Utils.arrondi(this.fprBasePDT2 * this.pdt2 / 100.0, 2);
        this.fprCotisPDT3 = Utils.arrondi(this.fprBasePDT3 * this.pdt3 / 100.0, 2);
        this.fprCotisPDT4 = Utils.arrondi(this.fprBasePDT4 * this.pdt4 / 100.0, 2);
        this.fprCotisPDT5 = Utils.arrondi(this.fprBasePDT5 * this.pdt5 / 100.0, 2);
        this.fprCotisPLMTA = Utils.arrondi(this.fprBasePLMTA * this.plmta / 100.0, 2);
        this.fprCotisPLMTB = Utils.arrondi(this.fprBasePLMTB * this.plmtb / 100.0, 2);
        this.fprCotisPLMTC = Utils.arrondi(this.fprBasePLMTC * this.plmtc / 100.0, 2);
        this.fprCotisCSGd = Utils.arrondi(this.fprBaseCSGd * this.csgd / 100.0, 2);
        this.fprCotisCSGnd = Utils.arrondi(this.fprBaseCSGnd * this.csgnd / 100.0, 2);
        this.fprCotisEmploye = this.fprCotisSSM + this.fprCotisSSV + this.fprCotisSSVC + this.fprCotisASS + this.fprCotisMNPAF;
        this.fprCotisEmploye += this.fprCotisCRPNr + this.fprCotisCRPNa + this.fprCotisCRPNm;
        this.fprCotisEmploye += this.fprCotisPIAD + this.fprCotisPDT1 + this.fprCotisPDT1B + this.fprCotisPDT2 + this.fprCotisPDT3 + this.fprCotisPDT4 + this.fprCotisPDT5;
        this.fprCotisEmploye += this.fprCotisPLMTA + this.fprCotisPLMTB + this.fprCotisPLMTC;
        this.fprCotisEmploye += this.fprCotisCSGd + this.fprCotisCSGnd;
        this.fprCotisEmploye = Utils.arrondi(this.fprCotisEmploye, 2);
        this.fprNetBulletin = Utils.arrondi(this.fprBrutFiscal + this.fprRepasImp + this.fprRepasNonImp + this.fprIkv - this.fprCotisEmploye, 2);
        this.fprNetImposable = Utils.arrondi(Utils.arrondi(this.fprBrutFiscal - this.fprCotisEmploye + this.fprCotisCSGnd, 2) + Utils.arrondi(this.fprBaseMNPAF * this.mnpafp / 100.0, 2), 2);
        this.fprDEC = this.montantDECr;
        this.fprFraisPro = Utils.arrondi(this.fprRepasImp + this.fprIkv + this.fprDEC, 2);
    }
}
