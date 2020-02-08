// 
// Decompiled by Procyon v0.5.36
// 

package ccUtils;

import java.awt.Color;

public class Constantes
{
    public static final int INF_NUIT_MC = 9;
    public static final int SUP_NUIT_MC = 21;
    public static final int INF_NUIT_LC = 6;
    public static final int SUP_NUIT_LC = 18;
    public static final double BONUS_MC = 0.25;
    public static final double BONUS_LC = 0.58;
    public static final double BONUS_MC_BASE = 0.25;
    public static final double K_PVHC_MC = 1.13;
    public static final double K_PVHC_LC = 1.07;
    public static final double MGA_NPV_MC = 80.0;
    public static final double MGA_NPV_LC = 80.0;
    public static final double MGA_NPV_MC_BASE = 80.0;
    public static final double SEUIL_HS = 75.0;
    public static final double SEUIL_HS_MINI = 16.0;
    public static final double MAJO_HS = 0.25;
    public static final double MAJO_NUIT = 0.5;
    public static final int ROSTER_ROW_HEIGHT = 24;
    public static final Color COLOR_GREEN_BCKGRD_ENABLED;
    public static final Color COLOR_GREEN_BCKGRD_DISABLED;
    public static final Color COLOR_ROSE_BCKGRD_ENABLED;
    public static final Color COLOR_ROSE_BCKGRD_DISABLED;
    public static final Color COLOR_LINEN_BCKGRD_ENABLED;
    public static final Color COLOR_LINEN_BCKGRD_DISABLED;
    public static final Color COLOR_GOLD_BCKGRD_ENABLED;
    public static final Color COLOR_GOLD_BCKGRD_DISABLED;
    public static final Color COLOR_AZUR_BCKGRD_ENABLED;
    public static final Color COLOR_AZUR_BACKGRD_DISABLED;
    public static final Color COLOR_PURPLE_BCKGRD_ENABLED;
    public static final Color COLOR_PURPLE_BACKGRD_DISABLED;
    public static final Color COLOR_CYAN_BCKGRD_ENABLED;
    public static final Color COLOR_CYAN_BCKGRD_DISABLED;
    public static final Color COLOR_SV_SEPARATOR_ENABLED;
    public static final Color COLOR_SV_SEPARATOR_DISABLED;
    public static final Color COLOR_SV_SEPARATOR_INST_ENABLED;
    public static final Color COLOR_SV_SEPARATOR_INST_DISABLED;
    public static final String GOOGLE_EVENT_AUCUNE_COULEUR = "0";
    public static final String GOOGLE_EVENT_BLEU = "1";
    public static final String GOOGLE_EVENT_VERT = "2";
    public static final String GOOGLE_EVENT_VIOLET = "3";
    public static final String GOOGLE_EVENT_ROUGE = "4";
    public static final String GOOGLE_EVENT_JAUNE = "5";
    public static final String GOOGLE_EVENT_ORANGE = "6";
    public static final String GOOGLE_EVENT_TURQUOISE = "7";
    public static final String GOOGLE_EVENT_GRIS = "8";
    public static final String GOOGLE_EVENT_BLEU_AZUR = "9";
    public static final String GOOGLE_EVENT_VERT_VIF = "10";
    public static final String GOOGLE_EVENT_ROUGE_VIF = "11";
    public static final String[] GOOGLE_EVENT_COLORS_VALUES;
    public static final String[] GOOGLE_EVENT_COLOR_NAMES;
    
    static {
        COLOR_GREEN_BCKGRD_ENABLED = new Color(191, 254, 224, 255);
        COLOR_GREEN_BCKGRD_DISABLED = new Color(191, 254, 224, 128);
        COLOR_ROSE_BCKGRD_ENABLED = new Color(235, 199, 199, 255);
        COLOR_ROSE_BCKGRD_DISABLED = new Color(235, 199, 199, 128);
        COLOR_LINEN_BCKGRD_ENABLED = new Color(239, 239, 239, 255);
        COLOR_LINEN_BCKGRD_DISABLED = new Color(239, 239, 239, 128);
        COLOR_GOLD_BCKGRD_ENABLED = new Color(255, 235, 128, 255);
        COLOR_GOLD_BCKGRD_DISABLED = new Color(255, 235, 128, 128);
        COLOR_AZUR_BCKGRD_ENABLED = new Color(191, 223, 254, 255);
        COLOR_AZUR_BACKGRD_DISABLED = new Color(191, 223, 254, 128);
        COLOR_PURPLE_BCKGRD_ENABLED = new Color(250, 180, 254, 255);
        COLOR_PURPLE_BACKGRD_DISABLED = new Color(250, 180, 254, 128);
        COLOR_CYAN_BCKGRD_ENABLED = new Color(237, 245, 254, 255);
        COLOR_CYAN_BCKGRD_DISABLED = new Color(237, 245, 254, 128);
        COLOR_SV_SEPARATOR_ENABLED = new Color(0, 0, 255, 128);
        COLOR_SV_SEPARATOR_DISABLED = new Color(0, 0, 255, 48);
        COLOR_SV_SEPARATOR_INST_ENABLED = new Color(128, 128, 255, 128);
        COLOR_SV_SEPARATOR_INST_DISABLED = new Color(128, 128, 255, 48);
        GOOGLE_EVENT_COLORS_VALUES = new String[] { null, "#a4bdfc", "#7ae7bf", "#dbadff", "#ff887c", "#fbd75b", "#ffb878", "#46d6db", "#e1e1e1", "#5484ed", "#51b749", "#dc2127" };
        GOOGLE_EVENT_COLOR_NAMES = new String[] { "Aucune couleur", "Bleu", "Vert", "Violet", "Rouge", "Jaune", "Orange", "Turquoise", "Gris", "Bleu azur", "Vert vif", "Rouge vif" };
    }
}
