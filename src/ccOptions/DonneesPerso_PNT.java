// 
// Decompiled by Procyon v0.5.36
// 

package ccOptions;

import java.io.InputStream;
import ccUtils.Utils;
import java.io.FileInputStream;
import chopeCrew.ChopeCrew;
import java.util.Properties;
import java.util.prefs.Preferences;

public class DonneesPerso_PNT
{
    public static final int FONCTION_PNT_OPL = 0;
    public static final int FONCTION_PNT_CDB = 1;
    public static final int FONCTION_PNT_OPL_INS = 2;
    public static final int FONCTION_PNT_CDB_INS = 3;
    public static final int ECH1 = 0;
    public static final int ECH2 = 1;
    public static final int ECH3 = 2;
    public static final int ECH4 = 3;
    public static final int ECH5 = 4;
    public static final int ECH6 = 5;
    public static final int ECH7 = 6;
    public static final int ECH8 = 7;
    public static final int ECH9 = 8;
    public static final int ECH10 = 9;
    public static final int CLASSE5 = 0;
    public static final int CLASSE4 = 1;
    public static final int CLASSE3 = 2;
    public static final int CLASSE2 = 3;
    public static final int CLASSE1 = 4;
    public static final int LICENSE_CPL = 0;
    public static final int LICENSE_ATPL = 1;
    public static final int QUALIF_320 = 0;
    public static final int QUALIF_320_BP = 1;
    public static final int QUALIF_330 = 2;
    public static final int QUALIF_340 = 3;
    public static final int QUALIF_330_340 = 4;
    public static final int QUALIF_787 = 5;
    public static final int QUALIF_777 = 6;
    public static final int QUALIF_380 = 7;
    public int fonction_pnt;
    public int echelon_pnt;
    public int classe_pnt;
    public int license_pnt;
    public int qualif_pnt;
    public double fixe_pnt;
    public double pv_pnt;
    public double ikv_pnt;
    public double pfonction_pnt;
    public boolean isResidentEtranger;
    public boolean abattementSecu_pnt;
    public double kmCdg_pnt;
    public double kmOry_pnt;
    public double kmBase_pnt;
    public double congePv_pnt;
    public double congeHs_pnt;
    public double congeAs_pnt;
    public double cBF_pnt;
    public double cIKV_pnt;
    public double cIRimp_pnt;
    public double cIRnimp_pnt;
    
    public void initDonneesPerso() {
        this.fonction_pnt = 0;
        this.echelon_pnt = 0;
        this.classe_pnt = 0;
        this.license_pnt = 0;
        this.qualif_pnt = 0;
        this.fixe_pnt = 0.0;
        this.pv_pnt = 0.0;
        this.pfonction_pnt = 0.0;
        this.ikv_pnt = 0.0;
        this.calculFixePv();
        this.congePv_pnt = 0.0;
        this.congeHs_pnt = 0.0;
        this.congeAs_pnt = 0.0;
        this.isResidentEtranger = false;
        this.abattementSecu_pnt = false;
        this.kmCdg_pnt = 0.0;
        this.kmOry_pnt = 0.0;
        this.kmBase_pnt = 0.0;
        this.cBF_pnt = 0.0;
        this.cIKV_pnt = 0.0;
        this.cIRimp_pnt = 0.0;
        this.cIRnimp_pnt = 0.0;
    }
    
    public void loadPreferences() {
        final Preferences prefs = Preferences.userRoot().node("ChopeCREW/profilPNT");
        this.fonction_pnt = prefs.getInt("fonction_pnt", this.fonction_pnt);
        this.echelon_pnt = prefs.getInt("echelon_pnt", this.echelon_pnt);
        this.classe_pnt = prefs.getInt("classe_pnt", this.classe_pnt);
        this.license_pnt = prefs.getInt("license_pnt", this.license_pnt);
        this.qualif_pnt = prefs.getInt("qualif_pnt", this.qualif_pnt);
        this.fixe_pnt = prefs.getDouble("fixe_pnt", this.fixe_pnt);
        this.pv_pnt = prefs.getDouble("pv_pnt", this.pv_pnt);
        this.pfonction_pnt = prefs.getDouble("pfonction_pnt", this.pfonction_pnt);
        this.ikv_pnt = prefs.getDouble("ikv_pnt", this.ikv_pnt);
        this.congePv_pnt = prefs.getDouble("congePv_pnt", this.congePv_pnt);
        this.congeHs_pnt = prefs.getDouble("congeHs_pnt", this.congeHs_pnt);
        this.congeAs_pnt = prefs.getDouble("congeAs_pnt", this.congeAs_pnt);
        this.isResidentEtranger = prefs.getBoolean("isResidentEtranger", this.isResidentEtranger);
        this.abattementSecu_pnt = prefs.getBoolean("abattementSecu_pnt", this.abattementSecu_pnt);
        this.kmCdg_pnt = prefs.getDouble("kmCdg_pnt", this.kmCdg_pnt);
        this.kmOry_pnt = prefs.getDouble("kmOry_pnt", this.kmOry_pnt);
        this.kmBase_pnt = prefs.getDouble("kmBase_pnt", this.kmBase_pnt);
        this.cBF_pnt = prefs.getDouble("cBF_pnt", this.cBF_pnt);
        this.cIKV_pnt = prefs.getDouble("cIKV_pnt", this.cIKV_pnt);
        this.cIRimp_pnt = prefs.getDouble("cIRimp_pnt", this.cIRimp_pnt);
        this.cIRnimp_pnt = prefs.getDouble("cIRnimp_pnt", this.cIRnimp_pnt);
    }
    
    public void savePreferences() {
        final Preferences prefs = Preferences.userRoot().node("ChopeCREW/profilPNT");
        prefs.putInt("fonction_pnt", this.fonction_pnt);
        prefs.putInt("echelon_pnt", this.echelon_pnt);
        prefs.putInt("classe_pnt", this.classe_pnt);
        prefs.putInt("license_pnt", this.license_pnt);
        prefs.putInt("qualif_pnt", this.qualif_pnt);
        prefs.putDouble("fixe_pnt", this.fixe_pnt);
        prefs.putDouble("pv_pnt", this.pv_pnt);
        prefs.putDouble("pfonction_pnt", this.pfonction_pnt);
        prefs.putDouble("ikv_pnt", this.ikv_pnt);
        prefs.putDouble("congePv_pnt", this.congePv_pnt);
        prefs.putDouble("congeHs_pnt", this.congeHs_pnt);
        prefs.putDouble("congeAs_pnt", this.congeAs_pnt);
        prefs.putBoolean("isResidentEtranger", this.isResidentEtranger);
        prefs.putBoolean("abattementSecu_pnt", this.abattementSecu_pnt);
        prefs.putDouble("kmCdg_pnt", this.kmCdg_pnt);
        prefs.putDouble("kmOry_pnt", this.kmOry_pnt);
        prefs.putDouble("kmBase_pnt", this.kmBase_pnt);
        prefs.putDouble("cBF_pnt", this.cBF_pnt);
        prefs.putDouble("cIKV_pnt", this.cIKV_pnt);
        prefs.putDouble("cIRimp_pnt", this.cIRimp_pnt);
        prefs.putDouble("cIRnimp_pnt", this.cIRnimp_pnt);
    }
    
    public void calculFixePv() {
        double fixeCdbA1 = 0.0;
        double thb = 0.0;
        double kCategorie = 0.0;
        double kEchelon = 0.0;
        double kSpecialite = 0.0;
        double kClasse = 0.0;
        double kBiqualif = 0.0;
        double kBonusAtpl = 0.0;
        final Properties props = new Properties();
        try {
            InputStream is;
            if (ChopeCrew.options.dbBaremes_pnt.equals("")) {
                final String fichierSource = "/res_databases/dbBAREMES_PNT.txt";
                is = this.getClass().getResourceAsStream(fichierSource);
            }
            else {
                final String fichierSource = ChopeCrew.options.dbBaremes_pnt;
                is = new FileInputStream(fichierSource);
            }
            props.load(is);
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (this.echelon_pnt == 0) {
            kCategorie = 0.7;
            kEchelon = 1.0;
        }
        else if (this.echelon_pnt == 1) {
            kCategorie = 0.85;
            kEchelon = 1.15;
        }
        else if (this.echelon_pnt == 2) {
            kCategorie = 1.0;
            kEchelon = 1.3;
        }
        else if (this.echelon_pnt == 3) {
            kCategorie = 1.0;
            kEchelon = 1.4;
        }
        else if (this.echelon_pnt == 4) {
            kCategorie = 1.0;
            kEchelon = 1.5;
        }
        else if (this.echelon_pnt == 5) {
            kCategorie = 1.0;
            kEchelon = 1.6;
        }
        else if (this.echelon_pnt == 6) {
            kCategorie = 1.0;
            kEchelon = 1.7;
        }
        else if (this.echelon_pnt == 7) {
            kCategorie = 1.0;
            kEchelon = 1.8;
        }
        else if (this.echelon_pnt == 8) {
            kCategorie = 1.0;
            kEchelon = 1.9;
        }
        else if (this.echelon_pnt == 9) {
            kCategorie = 1.0;
            kEchelon = 2.0;
        }
        if (this.fonction_pnt == 0 || this.fonction_pnt == 2) {
            kSpecialite = 0.665;
            if (this.classe_pnt == 0) {
                kClasse = 0.88;
            }
            else if (this.classe_pnt == 1) {
                kClasse = 0.93;
            }
            else if (this.classe_pnt == 2) {
                kClasse = 0.98;
            }
            else if (this.classe_pnt == 3) {
                kClasse = 1.03;
            }
            else if (this.classe_pnt == 4) {
                kClasse = 1.08;
            }
        }
        else if (this.fonction_pnt == 1 || this.fonction_pnt == 3) {
            kSpecialite = 1.0;
            if (this.classe_pnt == 0) {
                kClasse = 1.25;
            }
            else if (this.classe_pnt == 1) {
                kClasse = 1.3;
            }
            else if (this.classe_pnt == 2) {
                kClasse = 1.35;
            }
            else if (this.classe_pnt == 3) {
                kClasse = 1.45;
            }
            else if (this.classe_pnt == 4) {
                kClasse = 1.55;
            }
        }
        if (this.qualif_pnt == 0) {
            thb = Double.parseDouble(props.getProperty("THBA320"));
            kBiqualif = 1.0;
        }
        if (this.qualif_pnt == 1) {
            thb = Double.parseDouble(props.getProperty("THBA320"));
            kBiqualif = 1.0;
        }
        else if (this.qualif_pnt == 2) {
            thb = Double.parseDouble(props.getProperty("THBA340"));
            kBiqualif = 1.0;
        }
        else if (this.qualif_pnt == 3) {
            thb = Double.parseDouble(props.getProperty("THBA340"));
            kBiqualif = 1.0;
        }
        else if (this.qualif_pnt == 4) {
            thb = Double.parseDouble(props.getProperty("THBA340"));
            kBiqualif = 1.02;
        }
        else if (this.qualif_pnt == 5) {
            thb = Double.parseDouble(props.getProperty("THBB787"));
            kBiqualif = 1.0;
        }
        else if (this.qualif_pnt == 6) {
            thb = Double.parseDouble(props.getProperty("THBB777"));
            kBiqualif = 1.0;
        }
        else if (this.qualif_pnt == 7) {
            thb = Double.parseDouble(props.getProperty("THBA380"));
            kBiqualif = 1.0;
        }
        if (this.license_pnt == 0) {
            kBonusAtpl = 0.0;
        }
        else if (this.license_pnt == 1 && (this.fonction_pnt == 0 || this.fonction_pnt == 2)) {
            kBonusAtpl = 0.06;
        }
        if (this.fonction_pnt == 0 || this.fonction_pnt == 1) {
            this.pfonction_pnt = 0.0;
        }
        else if (this.fonction_pnt == 2) {
            this.pfonction_pnt = Double.parseDouble(props.getProperty("INSOPL"));
        }
        else if (this.fonction_pnt == 3) {
            this.pfonction_pnt = Double.parseDouble(props.getProperty("INSCDB"));
        }
        fixeCdbA1 = Double.parseDouble(props.getProperty("FixeCDBA1"));
        this.fixe_pnt = Utils.arrondi(fixeCdbA1 * kSpecialite, 2);
        this.fixe_pnt = Utils.arrondi(this.fixe_pnt * kEchelon, 2);
        this.pv_pnt = Utils.arrondi(thb * kCategorie * kBiqualif * (kClasse + kBonusAtpl), 2);
        this.ikv_pnt = Double.parseDouble(props.getProperty("IKV"));
    }
}
