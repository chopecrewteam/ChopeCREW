// 
// Decompiled by Procyon v0.5.36
// 

package ccOptions;

import java.io.InputStream;
import java.io.FileInputStream;
import chopeCrew.ChopeCrew;
import java.util.Properties;
import java.util.prefs.Preferences;

public class DonneesPerso_PNC
{
    public static final int FONCTION_PNC_HOT = 0;
    public static final int FONCTION_PNC_CC = 1;
    public static final int FONCTION_PNC_CCP = 2;
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
    public static final int CLASSE_ADAPTATION = 0;
    public static final int CLASSE4 = 1;
    public static final int CLASSE3 = 2;
    public static final int CLASSE2 = 3;
    public static final int CLASSE1 = 4;
    public static final int CLASSE_HC = 5;
    public static final int RESEAU_MC = 0;
    public static final int RESEAU_MC_BP = 1;
    public static final int RESEAU_LC = 2;
    public int fonction_pnc;
    public int echelon_pnc;
    public int classe_pnc;
    public int reseau_pnc;
    public double fixe_pnc;
    public double pv_pnc;
    public double pfonction_pnc;
    public double phabillement_pnc;
    public double ikv_pnc;
    public double congePv_pnc;
    public boolean abattementSecu_pnc;
    public double kmCdg_pnc;
    public double kmOry_pnc;
    public double kmBase_pnc;
    public double cBF_pnc;
    public double cIKV_pnc;
    public double cIRimp_pnc;
    public double cIRnimp_pnc;
    
    public void initDonneesPerso() {
        this.fonction_pnc = 0;
        this.echelon_pnc = 0;
        this.classe_pnc = 0;
        this.reseau_pnc = 0;
        this.fixe_pnc = 0.0;
        this.pv_pnc = 0.0;
        this.pfonction_pnc = 0.0;
        this.phabillement_pnc = 0.0;
        this.ikv_pnc = 0.0;
        this.calculFixePv();
        this.congePv_pnc = 0.0;
        this.abattementSecu_pnc = false;
        this.kmCdg_pnc = 0.0;
        this.kmOry_pnc = 0.0;
        this.kmBase_pnc = 0.0;
        this.cBF_pnc = 0.0;
        this.cIKV_pnc = 0.0;
        this.cIRimp_pnc = 0.0;
        this.cIRnimp_pnc = 0.0;
    }
    
    public void loadPreferences() {
        final Preferences prefs = Preferences.userRoot().node("ChopeCREW/profilPNC");
        this.fonction_pnc = prefs.getInt("fonction_pnc", this.fonction_pnc);
        this.echelon_pnc = prefs.getInt("echelon_pnc", this.echelon_pnc);
        this.classe_pnc = prefs.getInt("classe_pnc", this.classe_pnc);
        this.reseau_pnc = prefs.getInt("reseau_pnc", this.reseau_pnc);
        this.fixe_pnc = prefs.getDouble("fixe_pnc", this.fixe_pnc);
        this.pv_pnc = prefs.getDouble("pv_pnc", this.pv_pnc);
        this.pfonction_pnc = prefs.getDouble("pfonction_pnc", this.pfonction_pnc);
        this.phabillement_pnc = prefs.getDouble("phabillement_pnc", this.phabillement_pnc);
        this.ikv_pnc = prefs.getDouble("ikv_pnc", this.ikv_pnc);
        this.congePv_pnc = prefs.getDouble("congePv_pnc", this.congePv_pnc);
        this.abattementSecu_pnc = prefs.getBoolean("abattementSecu_pnc", this.abattementSecu_pnc);
        this.kmCdg_pnc = prefs.getDouble("kmCdg_pnc", this.kmCdg_pnc);
        this.kmOry_pnc = prefs.getDouble("kmOry_pnc", this.kmOry_pnc);
        this.kmBase_pnc = prefs.getDouble("kmBase_pnc", this.kmBase_pnc);
        this.cBF_pnc = prefs.getDouble("cBF_pnc", this.cBF_pnc);
        this.cIKV_pnc = prefs.getDouble("cIKV_pnc", this.cIKV_pnc);
        this.cIRimp_pnc = prefs.getDouble("cIRimp_pnc", this.cIRimp_pnc);
        this.cIRnimp_pnc = prefs.getDouble("cIRnimp_pnc", this.cIRnimp_pnc);
    }
    
    public void savePreferences() {
        final Preferences prefs = Preferences.userRoot().node("ChopeCREW/profilPNC");
        prefs.putInt("fonction_pnc", this.fonction_pnc);
        prefs.putInt("echelon_pnc", this.echelon_pnc);
        prefs.putInt("classe_pnc", this.classe_pnc);
        prefs.putInt("reseau_pnc", this.reseau_pnc);
        prefs.putDouble("fixe_pnc", this.fixe_pnc);
        prefs.putDouble("pv_pnc", this.pv_pnc);
        prefs.putDouble("pfonction_pnc", this.pfonction_pnc);
        prefs.putDouble("phabillement_pnc", this.phabillement_pnc);
        prefs.putDouble("ikv_pnc", this.ikv_pnc);
        prefs.putDouble("congePv_pnc", this.congePv_pnc);
        prefs.putBoolean("abattementSecu_pnc", this.abattementSecu_pnc);
        prefs.putDouble("kmCdg_pnc", this.kmCdg_pnc);
        prefs.putDouble("kmOry_pnc", this.kmOry_pnc);
        prefs.putDouble("kmBase_pnc", this.kmBase_pnc);
        prefs.putDouble("cBF_pnc", this.cBF_pnc);
        prefs.putDouble("cIKV_pnc", this.cIKV_pnc);
        prefs.putDouble("cIRimp_pnc", this.cIRimp_pnc);
        prefs.putDouble("cIRnimp_pnc", this.cIRnimp_pnc);
    }
    
    public void calculFixePv() {
        String sfonction = "";
        String sechelon = "";
        String sclasse = "";
        if (this.fonction_pnc == 0) {
            sfonction = "Hot";
        }
        else if (this.fonction_pnc == 1) {
            sfonction = "Cc";
        }
        else if (this.fonction_pnc == 2) {
            sfonction = "Ccp";
        }
        if (this.echelon_pnc == 0) {
            sechelon = "1";
        }
        else if (this.echelon_pnc == 1) {
            sechelon = "2";
        }
        else if (this.echelon_pnc == 2) {
            sechelon = "3";
        }
        else if (this.echelon_pnc == 3) {
            sechelon = "4";
        }
        else if (this.echelon_pnc == 4) {
            sechelon = "5";
        }
        else if (this.echelon_pnc == 5) {
            sechelon = "6";
        }
        else if (this.echelon_pnc == 6) {
            sechelon = "7";
        }
        else if (this.echelon_pnc == 7) {
            sechelon = "8";
        }
        else if (this.echelon_pnc == 8) {
            sechelon = "9";
        }
        else if (this.echelon_pnc == 9) {
            sechelon = "10";
        }
        if (this.classe_pnc == 0) {
            sclasse = "5";
        }
        else if (this.classe_pnc == 1) {
            sclasse = "4";
        }
        else if (this.classe_pnc == 2) {
            sclasse = "3";
        }
        else if (this.classe_pnc == 3) {
            sclasse = "2";
        }
        else if (this.classe_pnc == 4) {
            sclasse = "1";
        }
        else if (this.classe_pnc == 5) {
            sclasse = "0";
        }
        final Properties props = new Properties();
        try {
            InputStream is;
            if (ChopeCrew.options.dbBaremes_pnc.equals("")) {
                final String fichierSource = "/res_databases/dbBAREMES_PNC.txt";
                is = this.getClass().getResourceAsStream(fichierSource);
            }
            else {
                final String fichierSource = ChopeCrew.options.dbBaremes_pnc;
                is = new FileInputStream(fichierSource);
            }
            props.load(is);
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.fixe_pnc = Double.parseDouble(props.getProperty("Fixe" + sfonction + "Ech" + sechelon + "Cla" + sclasse));
        this.pv_pnc = Double.parseDouble(props.getProperty("Pv" + sfonction + "Ech" + sechelon + "Cla" + sclasse));
        if (this.fonction_pnc == 1 && this.reseau_pnc == 0) {
            this.pfonction_pnc = Double.parseDouble(props.getProperty("PrimeCcMc"));
        }
        if (this.fonction_pnc == 1 && this.reseau_pnc == 1) {
            this.pfonction_pnc = Double.parseDouble(props.getProperty("PrimeCcMc"));
        }
        else if (this.fonction_pnc == 1 && this.reseau_pnc == 2) {
            this.pfonction_pnc = Double.parseDouble(props.getProperty("PrimeCcLc"));
        }
        else if (this.fonction_pnc == 2 && this.classe_pnc == 3) {
            this.pfonction_pnc = Double.parseDouble(props.getProperty("PrimeCcpCl2"));
        }
        else if (this.fonction_pnc == 2 && this.classe_pnc == 4) {
            this.pfonction_pnc = Double.parseDouble(props.getProperty("PrimeCcpCl1"));
        }
        else if (this.fonction_pnc == 2 && this.classe_pnc == 5) {
            this.pfonction_pnc = Double.parseDouble(props.getProperty("PrimeCcpCl0"));
        }
        else {
            this.pfonction_pnc = 0.0;
        }
        this.phabillement_pnc = Double.parseDouble(props.getProperty("HABILLEMENT"));
        this.ikv_pnc = Double.parseDouble(props.getProperty("IKV"));
    }
}
